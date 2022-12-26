-- 1--------------------------------
select count(department_no) as 학생수
from tb_student s
where s.department_no = (select department_no
						from tb_department
						where DEPARTMENT_NAME = '사학과')
                        and date_format(entrance_date, '%Y')='2001' ;
-- 2------------------------------------------------------------------
select category as '계열' , department_name as '학과이름', capacity as '정원'
from tb_department
where capacity>='20' and capacity<='30' and category='공학'
order by department_name asc;
-- 3----------------------------------------------------------------
select category as '계열', count(category) as '학과수'
from tb_department
where category like ('%학%')
group by category;
-- 4------------------------------------------------------
select professor_name as '교수이름', left(professor_ssn,2) as '출생년도', professor_address as '주소'
from tb_professor
where department_no =(select department_no
from tb_department
where department_name = '영어영문학과')
order by left(professor_ssn,2) asc;
-- 5---------------------------------------------
select department_no as '학과번호', student_name as '학생이름', replace(replace(absence_yn,'Y','휴학'),'N','정상') as '휴학여부'
from tb_student
where department_no = (select department_no
from tb_department
where department_name = '국어국문학과') and student_address like '%서울%';
-- 6----------------------------------------------
select concat('[',RPAD(SUBSTR(student_ssn,1,8),LENGTH(student_ssn),'*'),']') as '주민번호', student_name as '이름'
from tb_student
where left(student_ssn,2)= '80' and substring(student_ssn, 8, 1) = 2 and left(student_name,1)='김';
-- 7------------------------------------------------
select d1.department_name as '학과이름', d1.capacity as '정원', if(d2.capacity>=40,'대강의실',if(d2.capacity>=30,'중강의실','소강의실'))  as '강의실크기'
from tb_department d1
left join tb_department d2
on d1.DEPARTMENT_NAME = d2.department_name
where d1.category = '예체능'
order by d2.capacity desc;
-- 8-----------------------------------------
select student_name as '학생이름', date_format(entrance_date,'%y년%m월%d일') as '입학일자', concat(date_format(now(),'%Y')-date_format(entrance_date,'%Y'),'년') as '입학후기간(년)'
from (select *
from tb_student
where left(date_format(entrance_date,'%Y'),2) = '19') a
where left(student_address,2) = '경기' or left(student_address,2) = '인천';
-- 9----------------------------------------
select department_no as '학과번호', date_format(now(),'1%y')-left(professor_ssn,2) as '나이'
from tb_professor
where professor_address like '%서울%'
order by 나이 asc;
-- 10-----------------------------------------
select department_no as '학과번호', student_name as '학생이름', COACH_PROFESSOR_NO as '지도교수번호', date_format(entrance_date, '%Y년') as '입학년도'
from tb_student
where STUDENT_ADDRESS is null and date_format(entrance_date,'%Y%m%d')>'20050101' and '20061231'>date_format(entrance_date,'%Y%m%d')
order by 입학년도 asc, 학생이름;
-- 11----------------------------------------------
select professor_name as '교수이름', professor_address as '주소'
from tb_professor p
inner join (select department_no
from tb_department
where (select category
from tb_department
where department_name like '%간호학과%')=category) a
on a.department_no = p.department_no 
order by 교수이름;
-- 12------------------------------------------
select replace(a.department_no,'001','국어국문학과') as '학과명', student_name as '학생이름', professor_name as '지도교수이름'
from (select *
from tb_student s
where (select department_no
from tb_department
where department_name = '국어국문학과') = department_no) a
left join tb_professor p
on a.coach_professor_no = p.professor_no
order by 학생이름 ;
-- 13-----------------------------------------
(select category as '계열', department_name as '학과이름', 학생수 as '학생수', rank() over (order by 학생수 desc) as '순위'
from (select department_name as '학과이름', count(s.department_no) as '학생수'
from tb_department d
left join tb_student s
on d.department_no = s.department_no
where category = '공학'
group by department_name) a
left join tb_department d
on d.department_name = a.학과이름
order by 학생수 desc
limit 1)
union
(select category as '계열', department_name as '학과이름', 학생수 as '학생수', rank() over (order by 학생수 desc) as '순위'
from (select department_name as '학과이름', count(s.department_no) as '학생수'
from tb_department d
left join tb_student s
on d.department_no = s.department_no
where category = '자연과학'
group by department_name) a
left join tb_department d
on d.department_name = a.학과이름
order by 학생수 desc
limit 1);
-- 14------------------------------
select department_name as '학과이름', student_name as '학생이름', date_format(entrance_date, '%y년%m%d') as '입학일자'
from tb_student s
left join tb_department d
on s.department_no = d.department_no
where department_name = '생태시스템공학과'
order by date_format(entrance_date, '%Y%m%d')
limit 5;
-- 15---------------------------------
select class_name as '과목이름', department_name as '학과이름'
from (select c.department_no, class_name
from (select class_no, cp.professor_no, department_no
from tb_class_professor cp
left join tb_professor p
on p.professor_no = cp.professor_no) a
right join tb_class c
on a.class_no = c.class_no
where class_name like '%논문%' and professor_no is null) a2
left join tb_department d
on a2.department_no = d.department_no
order by 학과이름 asc;
