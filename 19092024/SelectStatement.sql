create database Ecommerce;
use Ecommerce;


create table User(
	UserId int primary key,
    UserName varchar(20),
    UserEmail varchar(20),
    UserContact int
);

Insert into User values(1, 'Ankit', 'test@gmail.com' , '2020202020');
Insert into User values(2, 'Manjeet', 'manjeet@gmail.com' , '252525255');
Insert into User values(3, 'Riya', 'riya@gmail.com' , '32152321');
Insert into User values(4, 'Chayan', 'chayan@gmail.com' , '1234567890');


select * from User;


select 2*4 as product;
select now();

select current_date;

select concat("Ankit", " ","Gorane") as Fullname;

CREATE TABLE employees (
    employee_id INT PRIMARY KEY,           
    first_name VARCHAR(50),               
    last_name VARCHAR(50),               
    job_title VARCHAR(100),               
    department_id INT,                   
    hire_date DATE,                       
    salary DECIMAL(10, 2)              
);

INSERT INTO employees (employee_id, first_name, last_name, job_title, department_id, hire_date, salary)
VALUES
(1, 'John', 'Doe', 'Software Engineer', 101, '2021-01-15', 60000.00),
(2, 'Jane', 'Smith', 'HR Manager', 102, '2019-05-23', 75000.00),
(3, 'Alice', 'Johnson', 'Sales Executive', 103, '2020-11-10', 55000.00),
(4, 'Bob', 'Williams', 'Marketing Analyst', 104, '2018-03-12', 50000.00),
(5, 'Charlie', 'Brown', 'IT Support Specialist', 105, '2022-07-19', 45000.00);



CREATE TEMPORARY TABLE temp_employees AS
SELECT * FROM employees;

INSERT INTO temp_employees
SELECT * FROM employees
LIMIT 3;

-- Step 3: Verify the inserted records
SELECT * FROM temp_employees;


#Write a SQL statement to create a simple table countries including columns country_id,country_name and region_id.


 CREATE TABLE IF NOT EXISTS Countries (
	id int PRIMARY KEY,
    count_name varchar(20),
    region_id int
    );
 
  #Write a SQL statement to create a table countries including columns country_id, country_name and region_id and make sure that the combination of columns country_id and region_id will be unique.
  CREATE TABLE IF NOT EXISTS Countries (
	id int PRIMARY KEY,
    count_name varchar(20),
    region_id int,
    UNIQUE(id, region_id)
    );
    
    
    #Write a SQL statement to create a table employees including columns employee_id, first_name, last_name, email, phone_number hire_date, job_id, salary, commission,
    #manager_id and department_id and make sure that, the employee_id column does not contain any duplicate value at the time of insertion and the 
    #foreign key columns combined by department_id and SQL Code Questions 9 manager_id columns contain only those unique combination values, 
    #which combinations are exists in the departments table. Assume the structure of departments table below
 
 CREATE TABLE departments (
		department_id int PRIMARY KEY,
        dept_name varchar(40)
        );
 
 CREATE TABLE Employees (
	emp_id int PRIMARY KEY,
    first_name varchar(40),
    last_name VARCHAR(50),               
    email VARCHAR(100) UNIQUE,             
    phone_number VARCHAR(20),             
    hire_date DATE,                        
    job_id VARCHAR(10),                  
    salary DECIMAL(10, 2),                 
    commission DECIMAL(5, 2),              
    manager_id INT,                       
    department_id INT,
    
    CONSTRAINT fk_department_manager 
        FOREIGN KEY (department_id, manager_id)
        REFERENCES departments (department_id, manager_id),
    UNIQUE (department_id, manager_id)
    );
    