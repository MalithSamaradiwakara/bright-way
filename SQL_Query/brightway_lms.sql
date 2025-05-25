-- ========================
-- Create Database
-- ========================
CREATE DATABASE IF NOT EXISTS brightway_lms;
USE brightway_lms;
-- drop database brightway_lms;
-- 1. Student
CREATE TABLE Student (
  S_ID INT AUTO_INCREMENT PRIMARY KEY,
  S_Name VARCHAR(100) NOT NULL,
  S_Email VARCHAR(100) NOT NULL UNIQUE,
  S_Contact CHAR(10) NOT NULL,
  S_Photo VARCHAR(255) NOT NULL UNIQUE
);

-- 2. Teacher
CREATE TABLE Teacher (
  T_ID INT AUTO_INCREMENT PRIMARY KEY,
  T_Name VARCHAR(100) NOT NULL,
  T_Email VARCHAR(150) NOT NULL UNIQUE,
  T_Description TEXT,
  T_Photo VARCHAR(255)
);

-- 3. Admin
CREATE TABLE Admin_t (
  Admin_ID INT AUTO_INCREMENT PRIMARY KEY,
  A_Name VARCHAR(100) NOT NULL,
  A_Email VARCHAR(150) NOT NULL UNIQUE
);

-- 4. Login
CREATE TABLE Login (
  Login_ID INT AUTO_INCREMENT PRIMARY KEY,
  Username VARCHAR(100) NOT NULL UNIQUE,
  Password VARCHAR(255) NOT NULL,
  User_Type ENUM('Student','Teacher','Admin') NOT NULL,
  S_ID INT DEFAULT NULL,
  T_ID INT DEFAULT NULL,
  Admin_ID INT DEFAULT NULL,
  FOREIGN KEY (S_ID) REFERENCES Student(S_ID) ON DELETE CASCADE,
  FOREIGN KEY (T_ID) REFERENCES Teacher(T_ID) ON DELETE CASCADE,
  FOREIGN KEY (Admin_ID) REFERENCES Admin_t(Admin_ID) ON DELETE CASCADE
);

-- 5. Courses
CREATE TABLE Courses (
  C_ID INT AUTO_INCREMENT PRIMARY KEY,
  Title VARCHAR(255) NOT NULL,
  Category VARCHAR(255) NOT NULL DEFAULT 'General',
  C_Description VARCHAR(255),
  Full_Description TEXT,
  Duration VARCHAR(255) NOT NULL,
  Level VARCHAR(255) NOT NULL,
  Price DOUBLE NOT NULL,
  Icon VARCHAR(255),
  Prerequisites VARCHAR(255),
  Is_Active BIT(1) NOT NULL
);

-- 6. Course_topic
CREATE TABLE Course_topic (
  C_ID INT NOT NULL,
  Topic VARCHAR(255),
  FOREIGN KEY (C_ID) REFERENCES Courses(C_ID)
);

-- 7. Enroll
CREATE TABLE Enroll (
  S_ID INT NOT NULL,
  C_ID INT NOT NULL,
  Date DATE,
  Payment_Ref_Num VARCHAR(50),
  PRIMARY KEY (S_ID, C_ID),
  FOREIGN KEY (S_ID) REFERENCES Student(S_ID),
  FOREIGN KEY (C_ID) REFERENCES Courses(C_ID)
);

-- 8. T_Qualification
CREATE TABLE T_Qualification (
  T_ID INT NOT NULL,
  Qualification VARCHAR(100) NOT NULL,
  FOREIGN KEY (T_ID) REFERENCES Teacher(T_ID)
);

-- 9. Quiz
CREATE TABLE Quiz (
  Quiz_ID INT AUTO_INCREMENT PRIMARY KEY,
  S_ID INT,
  C_ID INT,
  T_ID INT,
  Q_Availability BOOLEAN,
  Q_Title VARCHAR(150) NOT NULL,
  Q_Description TEXT,
  FOREIGN KEY (S_ID) REFERENCES Student(S_ID),
  FOREIGN KEY (C_ID) REFERENCES Courses(C_ID),
  FOREIGN KEY (T_ID) REFERENCES Teacher(T_ID)
);

-- 10. Question
CREATE TABLE Question (
  Q_ID INT AUTO_INCREMENT PRIMARY KEY,
  Question TEXT NOT NULL,
  Answers TEXT,
  Correct INT,
  Quiz_ID INT,
  FOREIGN KEY (Quiz_ID) REFERENCES Quiz(Quiz_ID)
);

-- 11. Assignment
CREATE TABLE Assignment (
  A_ID INT AUTO_INCREMENT PRIMARY KEY,
  S_ID INT,
  C_ID INT,
  T_ID INT,
  A_Availability BOOLEAN,
  A_Title VARCHAR(150) NOT NULL,
  A_Description TEXT,
  A_File_path VARCHAR(255),
  FOREIGN KEY (S_ID) REFERENCES Student(S_ID),
  FOREIGN KEY (C_ID) REFERENCES Courses(C_ID),
  FOREIGN KEY (T_ID) REFERENCES Teacher(T_ID)
);

-- 12. Attempt
CREATE TABLE Attempt (
  Quiz_ID INT NOT NULL,
  S_ID INT NOT NULL,
  Sub_Date DATE,
  Q_Feedback TEXT,
  Q_Marks DECIMAL(5,2),
  PRIMARY KEY (Quiz_ID, S_ID),
  FOREIGN KEY (Quiz_ID) REFERENCES Quiz(Quiz_ID),
  FOREIGN KEY (S_ID) REFERENCES Student(S_ID)
);

-- 13. Submit
CREATE TABLE Submit (
  A_ID INT NOT NULL,
  S_ID INT NOT NULL,
  A_Marks DECIMAL(5,2),
  A_Feedback TEXT,
  Sub_Date DATE,
  S_File_path VARCHAR(255),
  PRIMARY KEY (A_ID, S_ID),
  FOREIGN KEY (A_ID) REFERENCES Assignment(A_ID),
  FOREIGN KEY (S_ID) REFERENCES Student(S_ID)
);


-- Students
INSERT INTO Student (S_Name, S_Email, S_Contact, S_Photo) VALUES
('Nimal Perera', 'nimal.p@gmail.com', '0712345678', '/photos/nimal.jpg'),
('Saman Silva', 'saman.s@gmail.com', '0778765432', '/photos/saman.jpg');

-- Teachers
INSERT INTO Teacher (T_Name, T_Email, T_Description, T_Photo) VALUES
('Dr. Anoja Wijesinghe', 'anoja.w@brightway.lk', 'Senior Lecturer in CS', '/photos/anoja.jpg'),
('Mr. Kasun Fernando', 'kasun.f@brightway.lk', 'Assistant Lecturer', '/photos/kasun.jpg');

-- Admins
INSERT INTO Admin_t (A_Name, A_Email) VALUES
('Mr. Zeyan Thowfeek', 'zeyan.t@brightway.lk'),
('Ms. Chamari Perera', 'chamari.p@brightway.lk');


-- Logins
INSERT INTO Login (Username, Password, User_Type, S_ID) VALUES
('nimal_p', 'hash1', 'Student', 1),
('saman_s', 'hash2', 'Student', 2);

INSERT INTO Login (Username, Password, User_Type,  S_ID,T_ID) VALUES
('anoja_w', 'hash3', 'Teacher', NULL, 1),
('kasun_f', 'hash4', 'Teacher', NULL, 2);

INSERT INTO Login (Username, Password, User_Type, S_ID,T_ID,Admin_ID) VALUES
('zeyan_t', 'hash5', 'Admin', NULL, NULL, 1),
('chamari_p', 'hash6', 'Admin', NULL, NULL, 2);

-- Courses
INSERT INTO Courses (Title, Category, C_Description, Full_Description, Duration, Level, Price, Icon, Prerequisites, Is_Active) VALUES
('Intro to Databases', 'Computer Science', 'Basics of relational DBs', 'Learn SQL, schema design...', '6 weeks', 'Beginner', 0.00, '/icons/db.png', NULL, b'1'),
('Advanced SQL', 'Computer Science', 'Deep-dive into SQL', 'Joins, optimization, stored procs...', '4 weeks', 'Advanced', 5000.00, '/icons/sql.png', 'Intro to Databases', b'1');

-- Course Topics
INSERT INTO Course_topic VALUES
(1, 'ER Modeling'),
(1, 'Normalization'),
(2, 'Window Functions'),
(2, 'Query Optimization');

-- Enrollments
INSERT INTO Enroll VALUES
(1, 1, '2025-01-10', 'PAY123'),
(2, 1, '2025-01-12', 'PAY124'),
(1, 2, '2025-02-01', 'PAY125');

-- Teacher Qualifications
INSERT INTO T_Qualification VALUES
(1, 'PhD in Computer Science'),
(2, 'MSc in Information Systems');

-- Quizzes
INSERT INTO Quiz (S_ID, C_ID, T_ID, Q_Availability, Q_Title, Q_Description) VALUES
(1, 1, 1, TRUE, 'DB Basics Quiz', 'Covers ERD and relations'),
(2, 2, 2, FALSE, 'SQL Advanced Quiz', 'Covers complex queries');

-- Questions
INSERT INTO Question (Question, Answers, Correct, Quiz_ID) VALUES
('What is a primary key?','A;B;C;D',1,1),
('Which clause filters rows?','WHERE;HAVING;GROUP BY;ORDER BY',1,1);

-- Assignments
INSERT INTO Assignment (S_ID, C_ID, T_ID, A_Availability, A_Title, A_Description, A_File_path) VALUES
(1, 1, 1, TRUE, 'ERD Design', 'Draw ER diagram for library', NULL),
(2, 2, 2, TRUE, 'SQL Script', 'Write SQL for reports', NULL);

-- Attempts
INSERT INTO Attempt (Quiz_ID, S_ID, Sub_Date, Q_Feedback, Q_Marks) VALUES
(1, 1, '2025-03-05', 'Good job', 85.00),
(2, 2, '2025-03-10', NULL, NULL);

-- Submissions
INSERT INTO Submit (A_ID, S_ID, A_Marks, A_Feedback, Sub_Date, S_File_path) VALUES
(1, 1, 90.00, 'Well done', '2025-03-15', '/submissions/erd1.pdf'),
(2, 2, NULL, NULL, '2025-03-20', '/submissions/sql2.sql');
