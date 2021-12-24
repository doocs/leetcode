# [615. Average Salary Departments VS Company](https://leetcode.com/problems/average-salary-departments-vs-company)

[中文文档](/solution/0600-0699/0615.Average%20Salary%20Departments%20VS%20Company/README.md)

## Description

Given two tables as below, write a query to display the comparison result (higher/lower/same) of the average salary of employees in a department to the company&#39;s average salary.

<p>&nbsp;</p>

Table: <code>salary</code>

<pre>

| id | employee_id | amount | pay_date   |

|----|-------------|--------|------------|

| 1  | 1           | 9000   | 2017-03-31 |

| 2  | 2           | 6000   | 2017-03-31 |

| 3  | 3           | 10000  | 2017-03-31 |

| 4  | 1           | 7000   | 2017-02-28 |

| 5  | 2           | 6000   | 2017-02-28 |

| 6  | 3           | 8000   | 2017-02-28 |

</pre>

<p>&nbsp;</p>

The <b>employee_id</b> column refers to the <b>employee_id</b> in the following table <code>employee</code>.

<p>&nbsp;</p>

<pre>

| employee_id | department_id |

|-------------|---------------|

| 1           | 1             |

| 2           | 2             |

| 3           | 2             |

</pre>

<p>&nbsp;</p>

So for the sample data above, the result is:

<p>&nbsp;</p>

<pre>

| pay_month | department_id | comparison  |

|-----------|---------------|-------------|

| 2017-03   | 1             | higher      |

| 2017-03   | 2             | lower       |

| 2017-02   | 1             | same        |

| 2017-02   | 2             | same        |

</pre>

<p>&nbsp;</p>

<b>Explanation</b>

<p>&nbsp;</p>

In March, the company&#39;s average salary is (9000+6000+10000)/3 = 8333.33...

<p>&nbsp;</p>

The average salary for department &#39;1&#39; is 9000, which is the salary of <b>employee_id</b> &#39;1&#39; since there is only one employee in this department. So the comparison result is &#39;higher&#39; since 9000 &gt; 8333.33 obviously.

<p>&nbsp;</p>

The average salary of department &#39;2&#39; is (6000 + 10000)/2 = 8000, which is the average of <b>employee_id</b> &#39;2&#39; and &#39;3&#39;. So the comparison result is &#39;lower&#39; since 8000 &lt; 8333.33.

<p>&nbsp;</p>

With he same formula for the average salary comparison in February, the result is &#39;same&#39; since both the department &#39;1&#39; and &#39;2&#39; have the same average salary with the company, which is 7000.

<p>&nbsp;</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```

```

<!-- tabs:end -->
