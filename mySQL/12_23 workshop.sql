(select * from (SELECT category as '계열', department_name as '학과이름', 학생수, (rank () over(ORDER BY 학생수 DESC)) as 순위
from tb_department d 
left outer join (SELECT department_no, COUNT(department_no) as 학생수 FROM tb_student GROUP BY department_no
HAVING COUNT(department_no)) al on d.department_no = al.department_no 
where category = '공학' order by 학생수 desc ) a
where 순위 <3)
union all
(select * from (SELECT category as '계열', department_name as '학과이름', 학생수, (rank () over(ORDER BY 학생수 DESC)) as 순위
from tb_department d 
left outer join (SELECT department_no, COUNT(department_no) as 학생수 FROM tb_student GROUP BY department_no
HAVING COUNT(department_no)) al on d.department_no = al.department_no 
where category = '자연과학' order by 학생수 desc ) a
where 순위 <3);
-- -----------------------------------------
select class_name as 과목이름, department_name as 학과이름
from tb_class c
left join tb_department d
on c.department_no = d.DEPARTMENT_NO
left join tb_professor p
on c.department_no = p.department_no
where class_name LIKE '논문%' and professor_name is null;

select *
from tb_class c
left join tb_department d
on c.department_no = d.DEPARTMENT_NO
left join tb_professor p
on c.department_no = p.department_no
where class_name LIKE '논문%' and professor_name is null;
-- -------------------------------------------