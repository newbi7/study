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
-- 데이터베이스 선택구문
USE shopdb;

SELECT *
FROM membertbl;

-- View를 만들어보아요
CREATE VIEW v_memberTBL
AS
	SELECT memberID, memberName
    FROM memberTBL;
    
SELECT *
FROM v_memberTBL;

-- 구분자를 바꿀수 있다.
DELIMITER //
CREATE PROCEDURE myCall()
BEGIN
	SELECT memberName
	FROM memberTBL
	WHERE memberID='hong';
    
    SELECT memberName
	FROM memberTBL
	WHERE memberID='shin';
END //
DELIMITER ;

CALL myCall();

-- ------------------
-- 임시테이블 : memberTBL에서 회원이 삭제되면 삭제된 회원정보를 다른 Table에 저장하기
create table deleteMemberTBL (
--    컬럼명 	 데이터타입   제약사항
	memberID char(8) PRIMARY KEY,
    membername char(4) NOT NULL,
    memberAddr VARCHAR(20)
);

DELIMITER //
CREATE TRIGGER trg_memberTBL
	AFTER DELETE
	ON memberTBL
	FOR EACH ROW
BEGIN
	INSERT INTO deleteMemberTBL VALUES(
		OLD.memberID, OLD.memberName, OLD.memberAddr
    ) ;
END //
DELIMITER ;

DELETE FROM membertbl
WHERE memberID='hong';

SELECT *
FROM memberTBL;