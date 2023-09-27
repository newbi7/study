
Use sqldb;

START TRANSACTION;

SELECT *
FROM usertbl2;

SELECT *
FROM usertbl2;

rollback;

SELECT *
FROM usertbl2;

-- COMMIT(작업한 SQL구문을 정말로 적용), ROLLBACK(Transaction으로 설정된 작업을 무시) -- 여기까지 트랜젝션을 설정한다.


-- Insert 구문, TABLE안에 데이터를 삽입하기 위한 SQL구문(DML)
USE sqldb;
-- DDL을 이용해서 TABLE을 하나 생성해 보아요
-- 기본적으로 TRANSACTION을 적용할 수 없어요(DML에 적용, DDL에는 적용안된다.)

CREATE TABLE testtbl1 (
	id			INT,
    username	CHAR(3),
    age			INT
);
-- 가장 일반적인 데이터 삽입하는 INSERT 구문
INSERT INTO testtbl1
VALUES (1, '홍길동', 20);
-- 이렇게도 할 수 있다.
INSERT INTO testtbl1(id, age)
VALUES (2, 30);
INSERT INTO testtbl1(username, age, id)
VALUES ('최길동', 40, 3);

-- 색다른 테이블 만들어 보아요
CREATE TABLE testtbl2 (
	id			INT			AUTO_INCREMENT PRIMARY KEY,
    userName	CHAR(3),
    age			INT
);
INSERT INTO testtbl2
VALUES (NULL, '홍길동', 20);
INSERT INTO testtbl2
VALUES (NULL, '최길동', 30);
-- AUTO_INCREMENT - 기본적으로 1부터 시작해서 1씪 증가하며 시작값과 증가하는 값을 변경 가능, PRIMARY_KEY를 설정 해줘야 한다, UNIQUE를 설정시 꼭 설정 안해도 된다.
SELECT *
FROM testtbl2;

-- 수정하려면 UPDATE 구문을 이용해야한다.
UPDATE testtbl2
SET username = '홍길동'
WHERE age = 30;

-- 삭제하려면 DELETE 구문을 이용해야 해요(DROP아니다.)
START transaction;
DELETE
FROM testtbl2
WHERE age = 30;
ROLLBACK;

-- 삭제관련한 명령어는 3가지가 있다.
-- DELETE(테이블에서 row를 삭제)-transaction log를 기록해서 시간이 오래걸린다., DROP(테이블 자체 삭제), TRUNCATE(테이블에서 row를 삭제)-transaction log를 기록하지 않는다.
-- --------------------------------
-- SQL 형변환
SELECT AVG(amount) AS '평균 구매 개수'
FROM buytbl;
-- 실수를 정수로 변환 (반올림 처리)
SELECT CAST(AVG(amount) AS UNSIGNED INTEGER) AS '평균 구매 개수'
FROM buytbl;

-- 다양한 구분자로 되어있는 날짜를 날짜형식으로 바꾸기 -FROM절은 테이블을 갖고 오는게 아니여서 안 갖고 온다., -연산하기 쉬워지기 때문에 바꾼다.
SELECT cast('2022/12/22' as date);
-- CONCAT은 문자열을 이어주는 함수다.
SELECT num, CONCAT(CAST(price AS CHAR(4)), '*', CAST(amount AS CHAR(4)))  AS '단가', '평균',
price*amount AS '구매액'
FROM buytbl;

select *
from buytbl;

-- MySQL은 묵시형 형 변환이 특이하다.
-- 다 외우기는 힘들지만 이상한것은 기억하고 있어야 한다.
SELECT '100' + '200'; -- 자바는 문자열+문자열을 합치면 이어지지만 mysql은 숫자로 변환해 합쳐진다.
SELECT 'Hello' + 'World'; -- 숫자 0으로 나온다.

SELECT CONCAT('Hello','World');

SELECT 1 > '2hoho'; -- true = 1, false = 0, 결과값 = 0
SELECT 0 = 'Hello'; -- 결과값 = 1

-- --------------------------------
-- MySQL에서 제공되는 많은 함수들 
-- 외울필요는 없지만 기본적인 함수는 알 필요가 있다.

-- 가장 많이 사용되는 함수는 문자열 관련함수이다.

SELECT LENGTH('abcde');
SELECT LENGTH('홍긷롱'); -- 결과값 = 9, 한글은 1글자 저장하는데 3바이트가 필요하다., 문자열의 길이가 아니라 문자를 저장하는 byte수

SELECT char_length('abcde');
SELECT char_length('홍길동'); -- 결과값 = 3

SELECT CONCAT_WS('??','Hello','World','HaHa'); -- CONCAT과 비슷하지만 문자를 사이사이 끼울때 쓰인다. 결과값 = Hello??World??HaHa

SELECT INSTR('이것은소리없는아우성!!', '소리'); -- 기존문자열에서 찾고자하는 문자열을 찾아서 시작위치를 알려준다. 주의-DBMS쵸준은 시작이 1부터다. 결과값 = 4, 없을시 결과값 = 0

SELECT FORMAT(123456.1231242,3); -- 숫자를 소수점 아래 자리수까지 표현하는 방법, 3자리마다 콤마도 찍어준다,반올림.

SELECT ROUND(3.141592); -- 소수점 반올림

SELECT CURDATE(); -- 현재날짜
SELECT NOW(); -- 현재시간,분,초
SELECT YEAR(CURDATE()); -- 년도만 나온다.
SELECT ADDDATE('2022-12-22', INTERVAl 2 MONTH);

-- Join----------------------
select *
from buytbl;

SELECT *
FROM (usertbl
	 INNER JOIN buytbl -- Inner join 먼저하고 select 순서, 기존 테이블-usertbl
	 ON usertbl.userID = buytbl.userID
     ); 
-- 표(A) ID, 이름  표(B) ID, 나이 
--       a   12        b  56
--       b   12        c  67
-- A,B결합시 a 12 b 56, a 12 b 67 로 4개가 만들어진다.
-- 따라서 on으로 결합시켜줘야한다.

SELECT usertbl.userid, name -- name과 id의 순서를 바꾸고 싶을때 사용, userid가 겹칠시 어떤 테이블의 이름인지 표시해야한다.
FROM usertbl U -- alias를 잡을 수 있다.
	 INNER JOIN buytbl B 
	 ON u.userID = B.userID ; 
-- 똑같지만 아래쪽은 비표준이다.
SELECT usertbl.userID, name
FROM usertbl, buytbl
WHERE usertbl.userid = buytbl.userid;

-- 물품을 한번이라도 구매한 기록이 있는 회원들에게 감사의 안내문을 보내봅시다. 한번이라도 구매한 사람의 이름과 주소룰 출력하세요
select distinct name, addr
FROM usertbl u
	INNER JOIN buytbl b
    ON u.userID = B.userID;
-- 위 문제를 subQuery를 이용해서 해결할 수도 있다. join은 시간이 오래걸리기 때문에 아래것이 실행시 효율적이다.
SELECT U.userID, u.name, U.addr
FROM usertbl U
where EXISTS( -- 존재한다면
SELECT *
FROM buytbl B
WHERE u.userID = B.userid);

-- SCRIPT에서 실행시켜서 데이터와 테이블을 생성하고 문제를 해결하세요
-- 1.학생을 기준으로 학생이름, 지역, 가입한 동아리, 동아리방을 출력하세요
select s.stdname, s.addr, sc.clubname, c.roomNo
from stdtbl s
where EXISTS(
SELECT *
FROM stdclubtbl sc
where s.stdname = std.stdname) or
(SELECT *
FROM clubtbl c
where s.clubname = c.clubname);

SELECT s.stdname, s.addr, sc.clubname, c.roomno
FROM stdtbl S
	Inner join stdclubtbl SC
    On s.stdname = sc.stdname
    Inner join clubtbl c;
-- INNER JOIN 끝

-- 전체 회원의 구매기록을 조회하세요, 그리고 구매 기록이 없는 회원도 출력하세요
SELECT *
	FROM usertbl U
	LEFT OUTER JOIN buytbl B
	ON u.userid = b.userid;
-- 한번도 구매한 적이 없는 회원의 이름과 주소를 출력하세요 (null 비교시 등호가 아닌 is사용)
SELECT name, addr
	from usertbl u
    left outer join buytbl b
    on u.userid = b.userid
    where amount is null;
    
-- UNION-------------------------------
(select name, height 
FROM usertbl
where height > 180)
union -- 테이블 구조는 위에를 따라간다, 위와 아래의 컬럼개수가 동일해야한다, 위쪽 결과의 데이터타입이 아래쪽 결과의 데이터타입이 같아야한다.
-- UNION(중복 배제), UNION ALL(무조건 붙여요)
(SELECT userid, price
FROM buytbl
WHERE price > 500);

-- -----------------
-- 모든 사용자를 조회하는데 전화가 없는 사람을 제외하고 츌력하세요
SELECT name, CONCAT_WS('-', mobile1, mobile2)
FROM usertbl
Where mobile1 IS NOT NULL;