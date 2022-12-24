-- 새로운 테이블을 하나 만들어서
-- 아래에서 가져온 데이터를 입력할꺼에요!
-- 테이블부터 만들어야 해요!
CREATE TABLE indexTBL (
	first_name VARCHAR(14),
    last_name VARCHAR(16),
    hire_date DATE
);

INSERT INTO indexTBL
	SELECT first_name, last_name, hire_date
	FROM employees.employees
	LIMIT 500;
    
-- 들어갔는지 확인

SELECT *
FROM indexTBL;

-- 데이터가 들어갔고 index는 설정하지 않았다.
-- 특정사람을 검색해 보아요! first_name이 Mary인 사람을 찾아서 이름과 성, 입사일을 출력하세요
SELECT FIRST_NAME, LAST_NAME, HIRE_DATE
FROM indexTBL
WHERE first_name = 'MARY';

-- index를 생성해 보아요!
CREATE INDEX idx_indexTBL_firstname
ON indexTBL(first_name);

ALTER TABLE indextbl DROP INDEX idx_indexTBL_firstname;
