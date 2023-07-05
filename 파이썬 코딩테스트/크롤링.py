from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import StaleElementReferenceException
import pandas as pd

# Chrome WebDriver 옵션 설정
options = Options()
# options.add_argument('--headless')
options.add_argument('--no-sandbox')
options.add_argument('--disable-dev-shm-usage')

# Chrome WebDriver 실행
driver = webdriver.Chrome(executable_path=r'C:\Program Files\Google\Chrome\Application\chromedriver', options=options)

# URL로 이동합니다.
driver.get('http://info.childcare.go.kr/info/cera/search/findNurseryGrade.jsp')

# 시/도 선택
select_sido = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.XPATH, "//select[@id='sidoCode']")))
select_sido.send_keys("서울특별시")

# 시/군/구 선택
sigungu_list = ["종로구"]

data = []
column_count = None
for sigungu in sigungu_list:
    print(sigungu)
    select_sigungu = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.ID, "sigunguCode")))
    select_sigungu.send_keys(sigungu)

    # 검색 버튼 클릭
    search_button = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.XPATH, "//img[@alt='검색']")))
    driver.execute_script("arguments[0].click();", search_button)

    while True:
        try:
            # 테이블의 모든 내용을 긁어옵니다.
            table = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.CSS_SELECTOR, "#pageLoadResult .tab_content .table_childcare tbody")))
            rows = table.find_elements(By.XPATH, ".//tr[position()>=1]")
            for row in rows:
                cells = row.find_elements(By.XPATH, ".//td")
                row_data = [cell.text for cell in cells]

                            # Determine the number of columns
                if column_count is None:
                    column_count = len(cells)
                    columns = [f"Column{i+1}" for i in range(column_count)]
                elif len(cells) != column_count:
                    continue
                data.append(row_data)

            # 다음 페이지로 이동합니다.
            next_page_button = driver.find_element(By.CSS_SELECTOR, ".paging.btn a.next1")
            driver.execute_script("arguments[0].click();", next_page_button)

            # 페이지 로딩을 기다립니다.
            WebDriverWait(driver, 10).until(EC.staleness_of(table))
        except StaleElementReferenceException:
            continue
        except:
            # 마지막 페이지이면 루프를 종료합니다.
            break

# webdriver를 종료합니다.
driver.quit()

# 데이터를 DataFrame으로 변환합니다.
df = pd.DataFrame(data, columns=columns)

# 데이터를 Excel 파일로 저장합니다.
df.to_excel("data모음.xlsx", index=False)
