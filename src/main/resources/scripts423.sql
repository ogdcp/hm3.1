select s.name, s.age, f.id
from student s left join faculty f on s.faculty id = f.id

select s.name, a.data
from student s inner join avatar a on s.id = a.student id