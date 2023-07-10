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
driver.get('https://www.menupan.com/Cook/recipeview.asp?cookid=1875')

data = []

# wrap_info 수집
wrap_info = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.ID, "wrap_info")))
data.append(wrap_info.text)

# wrap_food 수집
wrap_food = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.ID, "wrap_food")))
data.append(wrap_food.text)

# wrap_recipe 수집
wrap_recipe = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.ID, "wrap_recipe")))
data.append(wrap_recipe.text)

# webdriver를 종료합니다.
driver.quit()

# 데이터를 DataFrame으로 변환합니다.
df = pd.DataFrame(data, columns=["Data"])

# 데이터를 Excel 파일로 저장합니다.
df.to_excel("data모음.xlsx", index=False)
