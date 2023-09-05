select * from "public".student
where age >=10 and age<=25;

select student."name" from "public".student;


SELECT * FROM "public".student
WHERE name LIKE '%о%';

select * from "public".student
where age<id;

select * from "public".student
order by age;

select s.* from "public".student as s ,"public".faculty as f
where s.faculty_id=f."id";

select f.* from "public".student as s ,"public".faculty as f
where f.student_id=s."id";

select * from "public".student as s ,"public".faculty as f
where s.faculty_id=f."id";

select * from "public".student as s ,"public".faculty as f
where f.student_id=s."id";