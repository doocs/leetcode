# [579. Find Cumulative Salary of an Employee](https://leetcode.com/problems/find-cumulative-salary-of-an-employee)

[中文文档](/solution/0500-0599/0579.Find%20Cumulative%20Salary%20of%20an%20Employee/README.md)

## Description

<p>The <b>Employee</b> table holds the salary information in a year.</p>

<p>Write a SQL to get the cumulative sum of an employee&#39;s salary over a period of 3 months but exclude the most recent month.</p>

<p>The result should be displayed by &#39;Id&#39; ascending, and then by &#39;Month&#39; descending.</p>

<p><b>Example</b><br />

<b>Input</b></p>

<pre>

| Id | Month | Salary |

|----|-------|--------|

| 1  | 1     | 20     |

| 2  | 1     | 20     |

| 1  | 2     | 30     |

| 2  | 2     | 30     |

| 3  | 2     | 40     |

| 1  | 3     | 40     |

| 3  | 3     | 60     |

| 1  | 4     | 60     |

| 3  | 4     | 70     |

</pre>

<b>Output</b>

<pre>



| Id | Month | Salary |

|----|-------|--------|

| 1  | 3     | 90     |

| 1  | 2     | 50     |

| 1  | 1     | 20     |

| 2  | 1     | 20     |

| 3  | 3     | 100    |

| 3  | 2     | 40     |

</pre>

<p>&nbsp;</p>

<b>Explanation</b>

<p>Employee &#39;1&#39; has 3 salary records for the following 3 months except the most recent month &#39;4&#39;: salary 40 for month &#39;3&#39;, 30 for month &#39;2&#39; and 20 for month &#39;1&#39;<br />

So the cumulative sum of salary of this employee over 3 months is 90(40+30+20), 50(30+20) and 20 respectively.</p>

<pre>

| Id | Month | Salary |

|----|-------|--------|

| 1  | 3     | 90     |

| 1  | 2     | 50     |

| 1  | 1     | 20     |

</pre>

Employee &#39;2&#39; only has one salary record (month &#39;1&#39;) except its most recent month &#39;2&#39;.

<pre>

| Id | Month | Salary |

|----|-------|--------|

| 2  | 1     | 20     |

</pre>

<p>&nbsp;</p>

Employ &#39;3&#39; has two salary records except its most recent pay month &#39;4&#39;: month &#39;3&#39; with 60 and month &#39;2&#39; with 40. So the cumulative salary is as following.

<pre>

| Id | Month | Salary |

|----|-------|--------|

| 3  | 3     | 100    |

| 3  | 2     | 40     |

</pre>

<p>&nbsp;</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
