INSERT INTO PUBLIC.worker (ID, NAME, BIRTHDAY, LEVEL, SALARY)
VALUES 
(1, 'Alice Brown', '1995-04-15', 'Trainee', 800),        
(2, 'Bob Smith', '1988-11-20', 'Junior', 2500),
(3, 'Charlie Johnson', '1990-06-30', 'Middle', 4000),
(4, 'Diana Adams', '1985-02-10', 'Senior', 6000),      
(5, 'Ethan Carter', '1998-12-05', 'Trainee', 1200),
(6, 'Fiona Davis', '1993-08-25', 'Junior', 2200),
(7, 'George Hall', '1992-03-18', 'Middle', 3500),
(8, 'Hannah White', '1986-09-12', 'Senior', 5500),   
(9, 'Ivy Scott', '2000-05-07', 'Trainee', 950),        
(10, 'Jack Turner', '1991-01-21', 'Middle', 4200);

INSERT INTO PUBLIC.client(ID, NAME)
VALUES
(1, 'Ethan Foster'),
(2, 'Ava Mitchell'),
(3, 'Lucas Parker'),
(4, 'Isabella Reed'),
(5, 'Mason Hayes');

INSERT INTO PUBLIC.project(ID, CLIENT_ID, START_DATE, FINISH_DATE)
VALUES
(1, 1, '2015-01-01', '2022-01-01'), 
(2, 2, '2018-03-15', '2018-04-15'),
(3, 3, '2020-06-01', '2028-06-01'), 
(4, 4, '2017-09-10', '2025-09-10'), 
(5, 5, '2016-12-01', '2019-12-01'), 
(6, 1, '2019-01-01', '2026-01-01'), 
(7, 2, '2021-05-01', '2021-08-01'), 
(8, 3, '2014-11-01', '2022-11-01'), 
(9, 4, '2012-07-01', '2020-07-01'),
(10, 5, '2010-01-01', '2018-01-01'); 

INSERT INTO PUBLIC.project_worker(PROJECT_Id, WORKER_ID)
VALUES
(1, 2),
(1, 3),
(2, 1),
(3, 7),
(3, 8),
(3, 10),
(3, 1),
(3, 4),
(4, 8),
(4, 4),
(5, 4),
(5, 10),
(5, 7),
(6, 1),
(6, 2),
(7, 5),
(7, 10),
(7, 4),
(7, 8),
(8, 6),
(9, 3),
(9, 7),
(10, 9),
(10, 8),
(10, 2);