create database dummy;
use dummy;

CREATE TABLE employees (     employee_id INT PRIMARY KEY,     name VARCHAR(50),     department VARCHAR(50),     salary DECIMAL(10, 2) );

INSERT INTO employees (employee_id, name, department, salary) VALUES(1, 'Preeti', 'HR', 5000), (2, 'Liza', 'IT', 7000), (3, 'Ankita', 'HR', 4500), (4, 'Hema', 'IT', 8000), (5, 'Namita', 'Finance', 6000);

SELECT department, SUM(salary) AS total_salary
FROM employees
GROUP BY department
HAVING SUM(salary) > 10000
ORDER BY total_salary DESC;

SELECT department, AVG(salary) AS average_salary
FROM employees
GROUP BY department
HAVING COUNT(employee_id) > 1
ORDER BY average_salary DESC;

