select count(department_no) as 학생수
from tb_student s
where s.department_no = (select department_no
						from tb_department
						where DEPARTMENT_NAME = '사학과')
                        and date_format(entrance_date, '%Y')='2001' ;
-- ------------------------------------------------------------------
select category as '계열' , department_name as '학과이름', capacity as '정원'
from tb_department
where capacity>='20' and capacity<='30' and category='공학'
order by department_name asc;
-- ----------------------------------------------------------------
select category as '계열', count(category) as '학과수'
from tb_department
where category like ('%학%')
group by category;
-- ------------------------------------------------------
select professor_name as '교수이름', left(professor_ssn,2) as '출생년도', professor_address as '주소'
from tb_professor
where department_no =(select department_no
from tb_department
where department_name = '영어영문학과')
order by left(professor_ssn,2) asc;
-- ---------------------------------------------
select department_no as '학과번호', student_name as '학생이름', replace(replace(absence_yn,'Y','휴학'),'N','정상') as '휴학여부'
from tb_student
where department_no = (select department_no
from tb_department
where department_name = '국어국문학과') and student_address like '%서울%';
-- ----------------------------------------------
select concat('[',RPAD(SUBSTR(student_ssn,1,8),LENGTH(student_ssn),'*'),']') as '주민번호', student_name as '이름'
from tb_student
where left(student_ssn,2)= '80' and substring(student_ssn, 8, 1) = 2 and left(student_name,1)='김';
-- ------------------------------------------------
select d1.department_name as '학과이름', d1.capacity as '정원', replace(d2.capacity>=40,'1','대강의실') or replace(d2.capacity>=30,'1','중강의실') as '강의실크기'
from tb_department d1
left join tb_department d2
on d1.DEPARTMENT_NAME = d2.department_name
where d1.category = '예체능'