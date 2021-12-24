# [1527. Patients With a Condition](https://leetcode.com/problems/patients-with-a-condition)

[中文文档](/solution/1500-1599/1527.Patients%20With%20a%20Condition/README.md)

## Description

<p>Table:&nbsp;<code>Patients</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| patient_id   | int     |
| patient_name | varchar |
| conditions   | varchar |
+--------------+---------+
patient_id is the primary key for this table.
&#39;conditions&#39; contains 0 or more code separated by spaces. 
This table contains information of the patients in the hospital.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the patient_id, patient_name all conditions of patients who have Type I Diabetes. Type I Diabetes always starts with <code>DIAB1</code> prefix</p>

<p>Return the result table in any order.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>

<pre>
<code>Patients</code>
+------------+--------------+--------------+
| patient_id | patient_name | conditions   |
+------------+--------------+--------------+
| 1          | Daniel      &nbsp;| YFEV COUGH   |
| 2    &nbsp;     | Alice        |            &nbsp; |
| 3    &nbsp;     | Bob         &nbsp;| DIAB100 MYOP&nbsp;|
| 4 &nbsp;        | George      &nbsp;| ACNE DIAB100&nbsp;|
| 5 &nbsp;        | Alain       &nbsp;| DIAB201     &nbsp;|
+------------+--------------+--------------+

Result table:
+------------+--------------+--------------+
| patient_id | patient_name | conditions   |
+------------+--------------+--------------+
| 3    &nbsp;     | Bob         &nbsp;| DIAB100 MYOP&nbsp;|
| 4 &nbsp;        | George   &nbsp;   | ACNE DIAB100&nbsp;| 
+------------+--------------+--------------+
Bob and George both have a condition that starts with DIAB1.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
