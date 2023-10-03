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
select class_name as '과목이름', department_name as '학과이름'
from tb_class c
left join tb_department d
on c.department_no = d.department_no
left join tb_class_professor cp
on c.class_no = cp.class_no
left join tb_professor p
on cp.professor_no = p.professor_no
where d.category = "예체능" and c.class_name like '논문%' and professor_name is null
order by department_name asc;
-- ------------------------------------------
select c4.category as '계열', c4.department_name as '학과', count(c4.professor_no) as "교수 수" 
from (select distinct professor_no, department_name, category
	from (select category, department_name, c2.department_no
		from (select a1.category , d2.department_name, d2.department_no
			from (select category
				from tb_class c
					left join tb_department d
					on c.department_no = d.department_no
					where d.DEPARTMENT_NAME = '환경조경학과'
					limit 1) a1
						left join tb_department d2
						on a1.category = d2.category) b2
							left join tb_class c2
							on b2.department_no = c2.department_no) c3
								left join tb_professor p3
								on c3.department_no = p3.department_no
								) c4
									group by c4.category, c4.department_name
									having count(c4.professor_no) = 0 ;
-- ---------------------------------------------------------------------------------
select left(term_no,4) as '년도', round(avg(point),1) as '평점'
	FROM (select student_no, DATE_FORMAT(entrance_date,'%Y%m%d')
		FROM (SELECT professor_no
			FROM (SELECT coach_professor_no
					FROM tb_student
					where student_name = '서가람') a
						left join tb_professor p
						on a.coach_professor_no = p.professor_no) a2
							left join tb_student s
							on a2.PROFESSOR_NO = s.coach_professor_no
                            where DATE_FORMAT(entrance_date,'%Y%m%d') >=20010101) a3
left join tb_grade g
on a3.student_no = g.student_no
group by left(term_no,4)
order by '년도';
-- --------------------------------------------------------------       
select professor_name as '지도교수', count(professor_name) as '학생 수'
	from (select professor_name, professor_no
		from (select p.professor_no,professor_name, class_no
			from tb_professor p
			left join tb_class_professor cp
			on p.professor_no = cp.PROFESSOR_NO) a
				left join tb_class c
				on c.class_no = a.CLASS_NO
				where c.class_no is null) a2
					left join tb_student s
					on a2.professor_no = s.COACH_PROFESSOR_NO
group by professor_name
order by professor_name;
-- -----------------------------------------------
select category as 계열, a2.department_name as 학과이름,  학생수 as '학생수'
		from(select a.department_name , count(s.department_no) as '학생수'
			 from (select department_name, department_no
				   from tb_department
				   where category = '예체능' or category = '공학') a
						left join tb_student s
						on a.department_no = s.department_no
						group by a.department_name) a2
								left join tb_department d
								on d.department_name = a2.department_name
								order by category,학생수 desc;
-- --------------------------------------------
select a1.class_no as 과목번호, class_name as 과목이름
	from (select class_no, count(class_no)
	  from tb_grade
	  where term_no>='200101' and term_no<='200512'
group by class_no
order by count(class_no) desc) a1
left join tb_class c 
on a1.class_no = c.class_no
limit 3;