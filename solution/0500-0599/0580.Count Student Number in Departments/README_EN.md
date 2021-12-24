# [580. Count Student Number in Departments](https://leetcode.com/problems/count-student-number-in-departments)

[中文文档](/solution/0500-0599/0580.Count%20Student%20Number%20in%20Departments/README.md)

## Description

<p>A university uses 2 data tables, <b><i>student</i></b> and <b><i>department</i></b>, to store data about its students and the departments associated with each major.</p>

<p>Write a query to print the respective department name and number of students majoring in each department for all departments in the <b><i>department</i></b> table (even ones with no current students).</p>

<p>Sort your results by descending number of students; if two or more departments have the same number of students, then sort those departments alphabetically by department name.</p>

<p>The <b><i>student</i></b> is described as follow:</p>

<pre>

| Column Name  | Type      |

|--------------|-----------|

| student_id   | Integer   |

| student_name | String    |

| gender       | Character |

| dept_id      | Integer   |

</pre>

<p>where student_id is the student&#39;s ID number, student_name is the student&#39;s name, gender is their gender, and dept_id is the department ID associated with their declared major.</p>

<p>And the <b><i>department</i></b> table is described as below:</p>

<pre>

| Column Name | Type    |

|-------------|---------|

| dept_id     | Integer |

| dept_name   | String  |

</pre>

<p>where dept_id is the department&#39;s ID number and dept_name is the department name.</p>

<p>Here is an example <b>input</b>:<br />

<b><i>student</i></b> table:</p>

<pre>

| student_id | student_name | gender | dept_id |

|------------|--------------|--------|---------|

| 1          | Jack         | M      | 1       |

| 2          | Jane         | F      | 1       |

| 3          | Mark         | M      | 2       |

</pre>

<p><b><i>department</i></b> table:</p>

<pre>

| dept_id | dept_name   |

|---------|-------------|

| 1       | Engineering |

| 2       | Science     |

| 3       | Law         |

</pre>

<p>The <b>Output</b> should be:</p>

<pre>

| dept_name   | student_number |

|-------------|----------------|

| Engineering | 2              |

| Science     | 1              |

| Law         | 0              |

</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
