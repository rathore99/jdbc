##  **Homework: JDBC Practice Tasks (Individual Concepts)**

These tasks are designed to cover **all important JDBC concepts** before jumping into the mini project.

---

### ✅ **1. JDBC Setup & Connectivity**

* Write a Java program to connect to a MySQL/PostgreSQL/Oracle DB using JDBC.
* Load database properties from a `.properties` file.

---

### ✅ **2. Statement vs PreparedStatement**

* Create a table `students(id, name, age)` using Statement.
* Insert student data using both `Statement` and `PreparedStatement`.
* Compare the performance of batch inserts using both.

---

### ✅ **3. ResultSet Navigation**

* Retrieve all records from the `students` table.
* Use `ResultSet.TYPE_SCROLL_INSENSITIVE` to move forward/backward and demonstrate cursor movement.
* Extract metadata using `ResultSetMetaData`.

---

### ✅ **4. CallableStatement (Stored Procedures)**

* Create a stored procedure to update student age based on ID.
* Call the procedure from Java using `CallableStatement`.

---

### ✅ **5. Transaction Management**

* Create two tables: `accounts(account_id, balance)` and `transactions(id, from_account, to_account, amount, timestamp)`.
* Write a transfer method that debits one account and credits another using **manual commit and rollback**.

---

### ✅ **6. Batch Processing**

* Insert 100 dummy rows into a `logs(id, message, timestamp)` table using **batch processing**.
* Track performance improvement with/without batch.

---

### ✅ **7. Joins**

* Create tables: `students`, `courses`, `enrollments(student_id, course_id)`.
* Write a query using INNER JOIN to fetch all students enrolled in a particular course.

---