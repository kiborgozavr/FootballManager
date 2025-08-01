-- TEAMS
INSERT INTO team (team_name, commission, balance)
VALUES ('FC Thunder', 0.05, 10000000),
       ( 'Red Dragons', 0.07, 8500000),
       ('Blue Sharks', 0.03, 12000000);

-- Members of team 1 (FC Thunder)
INSERT INTO player (first_name, last_name, birth_date, career_start, joining_team_date, team_id)
VALUES ( 'John', 'Smith', '1995-04-12', '2015-06-01', '2020-03-15', 1),
       ( 'Alex', 'Johnson', '1998-08-30', '2017-01-10', '2021-07-01', 1),
       ('Mike', 'Brown', '1993-02-19', '2012-08-20', '2022-01-05', 1),
       ( 'Chris', 'Evans', '2000-10-25', '2019-03-01', '2023-04-20', 1),
       ('Steve', 'Clark', '1996-06-07', '2016-09-15', '2021-10-10', 1);

--  Members of team 2 (Red Dragons)
INSERT INTO player ( first_name, last_name, birth_date, career_start, joining_team_date, team_id)
VALUES ( 'Daniel', 'Adams', '1994-12-01', '2014-02-01', '2020-05-10', 2),
       ( 'Victor', 'Cruz', '1997-07-17', '2016-03-01', '2021-08-25', 2),
       ('Brian', 'Lee', '1992-03-11', '2011-01-01', '2022-12-12', 2),
       ( 'Ryan', 'Moore', '1999-09-09', '2018-05-10', '2023-03-14', 2),
       ('James', 'Scott', '1995-01-20', '2014-06-30', '2020-11-03', 2);

--  Members of team (Blue Sharks)
INSERT INTO player (first_name, last_name, birth_date, career_start, joining_team_date, team_id)
VALUES ( 'Adam', 'Reed', '1996-05-23', '2015-05-01', '2021-06-22', 3),
       ('Tom', 'Walker', '1993-11-04', '2013-04-20', '2022-02-18', 3),
       ( 'Leo', 'Morgan', '1998-02-14', '2017-10-10', '2023-01-01', 3),
       ( 'Oscar', 'Turner', '1997-03-05', '2016-11-11', '2021-09-09', 3),
       ( 'Harry', 'White', '2001-07-19', '2020-08-01', '2024-01-30', 3);