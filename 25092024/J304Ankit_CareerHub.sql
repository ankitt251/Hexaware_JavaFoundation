CREATE DATABASE if NOT EXISTS CareerHub;

Use CareerHub;

DROP TABLE if EXISTS Companies;
# OR can use For IF TABLE EXIST or not
CREATE TABLE IF NOT EXISTS Companies(
	CompanyID int PRIMARY KEY AUTO_INCREMENT,
    CompanyName varchar(100),
    Location varchar(100),
    UNIQUE(CompanyID)
    );
    
DROP TABLE IF EXISTS Jobs;
CREATE TABLE IF NOT EXISTS Jobs(
	JobID int PRIMARY KEY UNIQUE AUTO_INCREMENT,
	CompanyID int NOT NULL,
	JobTitle varchar(20) NOT NULL,
	JobDescription TEXT,
	JobLocation varchar(100),
	Salary decimal(10,2),
	JobType varchar(40),
	PostedDate DATETIME default CURRENT_TIMESTAMP,
    FOREIGN KEY(CompanyID) references Companies(CompanyID) ON DELETE CASCADE
	);
        
DROP TABLE IF EXISTS Applicants;
CREATE TABLE IF NOT EXISTS Applicants(
		ApplicantID int PRIMARY KEY UNIQUE AUTO_INCREMENT,
		FirstName varchar(40),
		LastName varchar(40),
		Email varchar(100) UNIQUE NOT NULL,
		Phone varchar(15),
		Resume TEXT
		);

DROP TABLE IF EXISTS Applications;
CREATE TABLE IF NOT EXISTS Applications (
		ApplicationID int PRIMARY KEY AUTO_INCREMENT,
        JobID int NOT NULL,
        ApplicantID int NOT NULL,
        ApplicationDate DATETIME default CURRENT_TIMESTAMP,
        CoverLetter TEXT,
        FOREIGN KEY(JobID) references Jobs(JobID) ON DELETE CASCADE,
        FOREIGN KEY(ApplicantID) references Applicants(ApplicantID) ON DELETE CASCADE
        );
        
        
INSERT INTO Companies
(CompanyName, Location) 
VALUES
	('Hexaware', 'Pune'),
	('LMindTree', 'MumbaI'),
	('TraceBrains', 'Chennai'),
	('Accenture', 'Nagpur');
    
INSERT INTO Jobs (CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) VALUES
(1, 'Software Developer', 'Developing and maintaining software systems', 'Pune', 70000, 'Full-time', '2024-08-01 10:00:00'),
(2, 'Frontend Engineer', 'Building and optimizing frontend applications', 'Mumbai', 75000, 'Full-time', '2024-08-02 11:30:00'),
(3, 'Data Analyst', 'Analyzing and interpreting data for decision making', 'Chennai', 65000, 'Full-time', '2024-08-03 09:15:00'),
(4, 'Backend Developer', 'Developing server-side applications', 'Nagpur', 80000, 'Contract', '2024-08-05 14:00:00');

INSERT INTO Applicants (FirstName, LastName, Email, Phone, Resume) VALUES
('Ankit', 'Gorane', 'ankitgorane@example.com', '555-1234', 'Experienced software developer with 5 years experience.'),
('Niti', 'Dwivedi', 'niti@example.com', '555-5678', 'Data analyst with 3 years experience in big data.'),
('Kaustubh', 'Shingare', 'ks@example.com', '555-8765', 'Frontend engineer with 2 years experience.'),
('Sunny', 'Barve', 'sb@example.com', '555-4321', 'Software developer with 4 years experience in backend systems.');

INSERT INTO Applications (JobID, ApplicantID, ApplicationDate, CoverLetter) VALUES
(1, 1, '2024-08-10 09:00:00', 'I am very interested in the Software Developer role.'),
(2, 3, '2024-08-11 13:45:00', 'I would like to apply for the Frontend Engineer position.'),
(3, 2, '2024-08-12 11:20:00', 'I am excited about the Data Analyst opportunity.'),
(4, 4, '2024-08-13 08:30:00', 'I have extensive experience in backend development and would love to join your team.');


#Q5
/* Write an SQL query to count the number of applications received for each job listing in the"Jobs" table. Display the job title and the corresponding application count. Ensure that it lists all
jobs, even if they have no applications.
*/

SELECT JobTitle, COUNT(ApplicationID) 
FROM Jobs
LEFT JOIN Applications ON Jobs.JobID = Applications.JobID
GROUP BY Jobs.JobID, Jobs.JobTitle;

INSERT INTO Companies
(CompanyName, Location) 
VALUES
	('Tech Mahindra', 'Kolkatta');
INSERT INTO Jobs (CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) VALUES
(1, 'Software Developer', 'Developing and maintaining software systems', 'Indore', 45000, 'Full-time', '2024-10-07 12:00:00');

#Q6
-- Develop an SQL query that retrieves job listings from the "Jobs" table within a specified salary
-- range. Allow parameters for the minimum and maximum salary values. Display the job title,
-- company name, location, and salary for each matching job.

SELECT J.JobTitle, C.CompanyName, J.JobLocation, J.Salary
From Jobs J
JOIN Companies C ON J.CompanyID = C.CompanyID
WHERE J.Salary BETWEEN 60000 AND 70000;

#Q7
-- Write an SQL query that retrieves the job application history for a specific applicant. Allow a
-- parameter for the ApplicantID, and return a result set with the job titles, company names, and
-- application dates for all the jobs the applicant has applied to.
INSERT INTO Jobs (JobID, CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) VALUES 
(10, 1, 'Data Scientist', 'Analyze data to provide insights', 'San Francisco', 95000, 'Full-time', '2024-09-05');

SELECT J.JobTitle, C.CompanyName, A.ApplicationDate
FROM Applications A
JOIN Jobs J ON A.JobID = J.JobID
JOIN Companies C ON J.CompanyID = C.CompanyID
WHERE A.ApplicantID = 4;

#Q8
-- Create an SQL query that calculates and displays the average salary offered by all companies for
-- job listings in the "Jobs" table. Ensure that the query filters out jobs with a salary of zero.

SELECT AVG(Salary)
FROM Jobs
Where Salary > 0;

#Q9
-- Write an SQL query to identify the company that has posted the most job listings. Display the
-- company name along with the count of job listings they have posted. Handle ties if multiple
-- companies have the same maximum count.

INSERT INTO Jobs (JobID, CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) VALUES
(11, 1, 'Software Engineer', 'Develop software solutions.', 'Pune', 80000, 'Full-time', '2024-01-15'),
(12, 1, 'Project Manager', 'Manage project timelines.', 'Mumbai', 90000, 'Full-time', '2024-01-20'),
(13, 1, 'System Architect', 'Design system architecture.', 'Surat', 95000, 'Full-time', '2024-01-25'),

(14, 2, 'Web Developer', 'Create and maintain websites.', 'Indore', 70000, 'Full-time', '2024-02-10'),
(15, 2, 'UX Designer', 'Design user experiences.', 'Nashik', 75000, 'Full-time', '2024-02-15'),
(16, 2, 'SEO Specialist', 'Optimize website for search engines.', 'Ahmedabad', 65000, 'Part-time', '2024-02-20'),

(17, 3, 'Data Analyst', 'Analyze data for business insights.', 'Pune', 80000, 'Full-time', '2024-03-05'),
(18, 3, 'Data Scientist', 'Build predictive models.', 'Chennai', 85000, 'Full-time', '2024-03-10'),
(19, 3, 'Business Analyst', 'Gather requirements and analyze business needs.', 'Kolkatta', 90000, 'Full-time', '2024-03-15'),

(20, 4, 'Marketing Manager', 'Oversee marketing strategies.', 'Bangalore', 85000, 'Full-time', '2024-03-20');

SELECT CompanyName, COUNT(JobID) FROM Jobs
JOIN Companies ON Jobs.CompanyID = Companies.CompanyID
GROUP BY CompanyName
HAVING COUNT(JobID) = (SELECT MAX(JobCount)
		FROM(
			SELECT COUNT(JobID) 
            FROM Jobs
            GROUP BY CompanyID
)
);

#Q10

-- Find the applicants who have applied for positions in companies located in 'CityX' and have at
-- least 3 years of experience.
CREATE TABLE ApplicantExperience (
    ApplicantID INT PRIMARY KEY,
    ExperienceYears INT,
    FOREIGN KEY (ApplicantID) REFERENCES Applicants(ApplicantID)
);

INSERT INTO ApplicantExperience (ApplicantID, ExperienceYears)
VALUES
(1, 4), 
(2, 2), 
(3, 3), 
(4, 5);  

SELECT * FROM Applicants A
JOIN Applications ON A.ApplicantID = Applications.ApplicantID
JOIN Jobs J ON Applications.JobID = J.JobID
JOIN Companies C on J.CompanyID = C.CompanyID
JOIN ApplicantExperience AE ON A.ApplicantID = AE.ApplicantID
WHERE C.Location = "Pune" AND AE.ExperienceYears >=3;

#Q11
-- Retrieve a list of distinct job titles with salaries between $60,000 and $80,000.
SELECT DISTINCT JobTitle
FROM Jobs
WHERE Salary BETWEEN 60000 AND 80000;

#Q12
-- Find the jobs that have not received any applications

SELECT J.JobTitle, J.CompanyID, J.JobLocation
From Jobs J
LEFT JOIN Applications A ON J.JobID = A.JobID
WHERE A.ApplicationID IS NULL;

#Q13
-- Retrieve a list of job applicants along with the companies they have applied to and the positions
-- they have applied for.
SELECT 
    A.FirstName, A.LastName, C.CompanyName,  J.JobTitle
FROM Applicants A
JOIN Applications App ON A.ApplicantID = App.ApplicantID
JOIN Jobs J ON App.JobID = J.JobID
JOIN Companies C ON J.CompanyID = C.CompanyID;

#Q14
-- Retrieve a list of companies along with the count of jobs they have posted, even if they have not
-- received any applications.
SELECT C.CompanyName, COUNT(J.JobID) AS JobCount
FROM Companies C
LEFT JOIN Jobs J ON C.CompanyID = J.CompanyID
GROUP BY C.CompanyID, C.CompanyName;

#Q15
-- List all applicants along with the companies and positions they have applied for, including those
-- who have not applied.

INSERT INTO Applicants (ApplicantID, FirstName, LastName, Email, Phone, Resume) VALUES
(11, 'Aman', 'Jaiswal', 'aj@example.com', '123-456-7890', 'resume1.txt'),
(12, 'Ankush', 'Rathore', 'ar@example.com', '123-456-7891', 'resume2.txt'),
(13, 'Sidharth', 'Gautam', 'sg@example.com', '123-456-7892', 'resume3.txt'),
(14, 'Yashraj', 'Nikam', 'yn@example.com', '123-456-7893', 'resume4.txt'); 


SELECT A.FirstName, A.LastName, C.CompanyName, J.JobTitle
FROM Applicants A
LEFT JOIN Applications Ap ON A.ApplicantID = Ap.ApplicantID
LEFT JOIN Jobs J ON Ap.JobID = J.JobID
LEFT JOIN Companies C ON J.CompanyID = C.CompanyID;

#Q16
-- Find companies that have posted jobs with a salary higher than the average salary of all jobs.
SELECT C.CompanyID, C.CompanyName,J.Salary
FROM Companies C
JOIN Jobs J ON C.CompanyID = J.CompanyID
WHERE J.Salary > (SELECT AVG(Salary) FROM Jobs WHERE Salary > 0)  -- Filter out zero salaries
ORDER BY C.CompanyID, J.Salary DESC; 

#Q17
-- Display a list of applicants with their names and a concatenated string of their city and state.
ALTER TABLE Applicants
ADD City VARCHAR(100),
ADD State VARCHAR(100);

UPDATE Applicants SET City = 'Pune', State = 'Maharashtra' WHERE ApplicantID = 1;
UPDATE Applicants SET City = 'Mumbai', State = 'Maharashtra' WHERE ApplicantID = 2;
UPDATE Applicants SET City = 'Chennai', State = 'Tamilnadu' WHERE ApplicantID = 3;
UPDATE Applicants SET City = 'Bangalore', State = 'Karnataka' WHERE ApplicantID = 4;


SELECT 
    CONCAT(A.FirstName, ' ', A.LastName) AS FullName,
    CONCAT(A.City, ', ', A.State) AS Location
FROM 
    Applicants A;

#Q18
-- Retrieve a list of jobs with titles containing either 'Developer' or 'Engineer'
SELECT JobID, JobTitle, CompanyID, JobDescription, JobLocation, Salary, JobType, PostedDate
FROM Jobs
WHERE JobTitle LIKE '%Developer%' OR JobTitle LIKE '%Engineer%';

#Q19
-- Retrieve a list of applicants and the jobs they have applied for, including those who have not
-- applied and jobs without applicants

SELECT A.ApplicantID, A.FirstName, A.LastName, J.JobID, J.JobTitle, J.CompanyID
FROM Applicants A
FULL OUTER JOIN Applications Ap ON A.ApplicantID = Ap.ApplicantID
FULL OUTER JOIN Jobs J ON Ap.JobID = J.JobID;

#Q20
-- List all combinations of applicants and companies where the company is in a specific city and the
-- applicant has more than 2 years of experience. For example: city=Chennai

SELECT A.ApplicantID, A.FirstName, A.LastName, C.CompanyID, C.CompanyName, C.Location
FROM Applicants A
JOIN  Applications Ap ON A.ApplicantID = Ap.ApplicantID
JOIN Jobs J ON Ap.JobID = J.JobID
JOIN Companies C ON J.CompanyID = C.CompanyID
WHERE C.Location = 'Chennai' AND A.ExperienceYears > 2;
