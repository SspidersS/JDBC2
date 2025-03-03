-- Створення бази даних MegaSoft (в H2 це не потрібно, якщо ви підключаєтеся до існуючої БД)
-- Створення таблиць
CREATE TABLE IF NOT EXISTS worker (
    ID INT PRIMARY KEY,
    Name NVARCHAR(1000) NOT NULL CHECK (LENGTH(Name) BETWEEN 2 AND 1000),
    BIRTHDAY DATE CHECK(YEAR(BIRTHDAY) > 1900),
    LEVEL NVARCHAR(20) NOT NULL CHECK (LEVEL IN ('Trainee', 'Junior', 'Middle', 'Senior')),
    SALARY INT CHECK(SALARY BETWEEN 100 AND 100000)
);

CREATE TABLE IF NOT EXISTS client (
    ID INT PRIMARY KEY,
    Name NVARCHAR(1000) NOT NULL CHECK (LENGTH(Name) BETWEEN 2 AND 1000)
);

CREATE TABLE IF NOT EXISTS project (
    ID INT PRIMARY KEY,
    CLIENT_ID INT,
    START_DATE DATE,
    FINISH_DATE DATE
);

CREATE TABLE IF NOT EXISTS project_worker (
    PROJECT_ID INT,
    WORKER_ID INT,
    PRIMARY KEY (PROJECT_ID, WORKER_ID),
    FOREIGN KEY (PROJECT_ID) REFERENCES project(ID),
    FOREIGN KEY (WORKER_ID) REFERENCES worker(ID)
);
