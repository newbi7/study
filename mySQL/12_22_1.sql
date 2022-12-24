
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