select job as '직무', TRUNCATE(avg(sal),-2) as '급여 평균'
from sqldb.emp
where deptno=30
group by job;
-- ----------------------------
SELECT Dname as '부서명', count(dname) as '직원수'
	FROM sqldb.dept d
	LEFT OUTER JOIN sqldb.emp e
	ON d.deptno = e.deptno
group by dname
HAVING count(dname)>3;
-- ------------------------------
(select job as '직무명', FORMAT(sum(sal),0) as '급여의 합'
from sqldb.emp
group by job
order by job asc)
union all
(select 'TOTAL', FORMAT(sum(sal),0)
from sqldb.emp
);
-- -------------------------------
select ename as '직원명', sal as '급여', 
(select grade from sqldb.salgrade where losal<(select max(sal) from sqldb.emp) and (select max(sal) from sqldb.emp)<hisal) as '급여 등급'
FROM sqldb.emp
where sal = (select max(sal) from sqldb.emp);
-- -----------------------------------
select ename as '직원명', concat(FORMAT(sal+ifnull(comm,0),0),'원') as '급여'
from sqldb.emp
where year(hiredate) = '1981' 
order by sal+ifnull(comm,0) desc;
-- --------------------------
select ename as '직원명', (date_format(hiredate, '%Y년 %m월 %d일')) as '입사년월일',  (case date_format(hiredate, '%Y')
when '1980' then 'A'
when '1981' then 'B'
when '1982' then 'C'
when '1983' then 'D'
end)
 as '그룹'
from sqldb.emp;
-- ----------------------------
SELECT e1.empno as '사원 사번', e1.ename as '사원 이름', e1.mgr as '관리자 사번', e2.ename as '관리자 이름'
FROM sqldb.emp e1
inner JOIN sqldb.emp e2
on e1.mgr = e2.EMPNO;
-- ---------------------------------------
select empno, ename, e.deptno
FROM sqldb.emp e
right JOIN sqldb.dept d
on e.deptno = d.deptno 
right join sqldb.locations l
on d.loc_code = l.loc_code
where l.city='chicago';
-- ------------------------------
select ename, sal
from sqldb.emp
where sal > (select max(sal)
from sqldb.emp
where deptno=30);