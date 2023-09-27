
-- 제일 처음은 사용할 데이터베이스 지정
-- USE 데이터베이스 이름
USE employees;

-- SQL은 대소문자를 구분하지 않지만 가독성(Readablity)을 위해 키워드는 대문자, 사용자 정의어는 소문자로 사용한다.
-- 데이터베이스 조회
SHOW DATABASES;

-- 테이블의 상태를 조회
SHOW TABLE STATUS;

-- 특정 테이블의 명세를 조회
DESC employees;

-- SELECT 구문 : 데이터를 추출하기 위해 사용하는 구문
-- SELECT 컬럼명들
SELECT first_name, last_name, birth_date
FROM employees;
-- 이거 실행하면 결과집합이 리턴된다
-- resultset, 결과레코드집합, Result Grid

-- 결과집합의 컬럼명을 바꾸고자 한다면 Alias를 이용하면 된다
SELECT  first_name As '나의 이름',
		last_name AS 성,
		birth_date AS '생년월일'
FROM employees;

-- 항상 모든 데이터를 가져와야하나? 조건절이 없으면 모든 데이터를 갖고온다
-- 원하는 조건이 있으면 조건절을 이용해야 한다

-- 이거하기 전에..데이터셋을 하나 만들어야한다
-- 테이블 생성하고 데이터도 INSERT해 놓았어요(한글처리에 조심)


-- 이름이 김경호인 사람의 정보를 출력하셍요.
select *
from usertbl
where name = '김경호';

-- 1970년 이후에 출생하고 신장이 182이상인 사람의 아이디와 이름을 조회하세요!
select userid, name
from usertbl
where (birthYear >= 1970) AND (heingt >= 182);

-- 키가 180~ 183인 사람의 이름과 아이디를 조회하세요!
select userid, name
from usertbl
where (height>=180) AND (height<=183);
-- 위와 식이 같다 between은 포함 and 포함이다.
select userid, name
from usertbl
where height BETWEEN 180 AND 183;

-- 지역이 경남, 전남, 경북 인 사람의 이름과 지역을 출력하세요
select userid, name
from usertbl
where addr='경남' or addr='전남' or addr='경북';
select userid, name
from usertbl
where addr IN ('경남', '전남', '경북');

-- 패턴매칭
-- 김씨성을 가진 사람들의 이름과 키를 조회하세요
-- 와일드카드문자 ( % , _ )
-- % : 0개이상의 문자열
-- ex) 자바% = 자바, 자바1, 자바어려워요, %자바% = 자바라는 글자가 들어간 모든 문자열
-- _ : 1개 문자열
-- 자바_ = 자바킹, 자바왕, 자바열... 
-- (=)연산자를 쓰면 안된다 Like 써야함
select height, name
from usertbl
where name LIKE '김%';

-- 김경호보다 키가 크거나 같은 사람의 이름과 키를 출력하세요 (서브쿼리)
select name, height
from usertbl
where height >= (select height from usertbl where name = '김경호');

-- 지역이 '경님'인 사람의 키보다 크거나 같은 사람을 조회하세요!, any = 조건 전부보다 하나라도 만족할시 all = 전부만족
select name, height
from usertbl
where height >= ANY(select height from usertbl where addr = '경남');

-- 먼저 가입한 순서대로 회원의 이름과 가입일을 출력하세요
select name, mdate
from usertbl
-- where 있으면 쓰고 order by는 맨 밑에 쓴다.  오름차순 ASC <-> DESC
ORDER BY mdate DESC;

-- 회원의 이름과 가입일을 출력하는데 키가 큰 순서로 정렬하세요 - 만약 키가 같으면 가입일이 빠른 순서대로 정렬하세요
select name, mdate, height
from usertbl
ORDER BY height desc, mdate ASC;

-- 회원들의 거주지역이 어디인지를 출력하세요 - 중복 주소지 뺴는 키워드
select DISTINCT addr
from usertbl;

-- 회원가입이 오래된 사람 3명을 출력하세요
select *
from usertbl
ORDER BY mdate ASC
LIMIT 3;

-- 테이블을 복사하는 가장 간단한 방법 , 주의사항 : 제약사항은 포함이 안된다(EX:PRIMARY KEY, fOREIGN kEY,...)
CREATE TABLE usertbl2 (
	select *
	FROM usertbl
);
-- Grouping이 나온다. 상대적으로 어렵다
-- 구매테이블에서 사용자가 구매한 물품의 개수를 출력하세요
-- 각각의 사용자별로 제품은 상관없이 모두 몇개를 지금까지 구입했는지 알고싶어요
SELECT userID, SUM(amount) AS '총 구매 개수'
FROM usertbl
GROUP BY userID;
-- 총 5개의 그룹이 만들어진다. 그룹별로 ID값을 뽑아내면 10개 -> 5개가 나온다. userID에 또 다른 name을 추가할시 나타낼 수가 없어서 오류가 뜬다.

-- 각 사용자별 구매액의 총합을 구하고 구매액이 큰 순서대로 출력하시요
SELECT userID, SUM(amount*price) AS '구매액의 총합'
FROM usertbl
GROUP BY userID
ORDER BY SUM(amount*price) DESC;
-- ORDER BY는 응답속도가 느려질 수 있기 때문에 잘 사용 안한다.

-- 전체 구매자가 구매한 물품의 평균개수는 몇개인가요?
SELECT AVG(amount)
FROM buytbl;

-- usertbl에서 가장 큰 키와 가장 작은키의 회원 이름과 키를 출력하세요 -- MAX(), MIN()
SELECT name, height
FROM usertbl
where height = (select max(height) from usertbl) OR height = (select MIN(height) from usertbl);

-- usertbl에서 휴대폰이 있는 사용자의 수를 출력하세요 -- count = 숫자세는것
SELECT count(mobile1)
FROM usertbl;

-- 사용자 별 총 구매금액이 1,000 이상인 사용자의 ID와 금액을 출력하세요 -- 그룹핑한 조건과 일반 조건이 있는데 "사용자별"은 그룹핑이기 때문에 그룹바이 다음에 써야한다.
SELECT userID, SUM(amount*price) AS total
FROM buytbl
GROUP BY userID
HAVING total >= 1000;

-- 기본적인 sql 구문순서는
-- SELECT
-- FROM
-- WHERE
-- GROUP BY
-- HAVING
-- ORDER BY