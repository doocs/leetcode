# [182. Duplicate Emails](https://leetcode.com/problems/duplicate-emails)

[中文文档](/solution/0100-0199/0182.Duplicate%20Emails/README.md)

## Description

<p>Write a SQL query to find all duplicate emails in a table named <code>Person</code>.</p>

<pre>

+----+---------+

| Id | Email   |

+----+---------+

| 1  | a@b.com |

| 2  | c@d.com |

| 3  | a@b.com |

+----+---------+

</pre>

<p>For example, your query should return the following for the above table:</p>

<pre>

+---------+

| Email   |

+---------+

| a@b.com |

+---------+

</pre>

<p><strong>Note</strong>: All emails are in lowercase.</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```
select Email from Person group by Email having count(Email) > 1
```

<!-- tabs:end -->
