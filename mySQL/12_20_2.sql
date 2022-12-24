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