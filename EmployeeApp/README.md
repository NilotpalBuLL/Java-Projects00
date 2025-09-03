# Employee Database App (Java + JDBC)

## 📌 Overview
A simple Java application that connects to MySQL using JDBC and performs CRUD operations on an employee database.

## ⚙️ Features
- Add Employee
- View All Employees
- Update Employee (position, salary)
- Delete Employee

## 🚀 How to Run
1. Create MySQL database and table (see `employees.sql`).
2. Update DB credentials in `EmployeeApp.java`.
3. Add MySQL JDBC driver in `lib/` folder (download from official MySQL site).

📌 Note: Download MySQL Connector/J from https://downloads.mysql.com/archives/c-j/ 
and place the .jar file inside lib/ folder before compiling.
4. Compile and run:
   ```bash
   javac -cp ".;lib/mysql-connector-j-8.0.33.jar" src/EmployeeApp.java
   java -cp ".;lib/mysql-connector-j-8.0.33.jar;src" EmployeeApp
   ```

   ⚠️ On Linux/Mac, replace `;` with `:` in classpath.

## 🎯 What I Learned
- JDBC connection handling
- Using PreparedStatement (prevents SQL Injection)
- CRUD operations
- Handling SQL exceptions
