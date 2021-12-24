# [196. Delete Duplicate Emails](https://leetcode.com/problems/delete-duplicate-emails)

[中文文档](/solution/0100-0199/0196.Delete%20Duplicate%20Emails/README.md)

## Description

<p>Write a SQL query to <strong>delete</strong> all duplicate email entries in a table named <code>Person</code>, keeping only unique emails based on its <i>smallest</i> <b>Id</b>.</p>

<pre>

+----+------------------+

| Id | Email            |

+----+------------------+

| 1  | john@example.com |

| 2  | bob@example.com  |

| 3  | john@example.com |

+----+------------------+

Id is the primary key column for this table.

</pre>

<p>For example, after running your query, the above <code>Person</code> table should have the following rows:</p>

<pre>

+----+------------------+

| Id | Email            |

+----+------------------+

| 1  | john@example.com |

| 2  | bob@example.com  |

+----+------------------+

</pre>

<p><strong>Note:</strong></p>

<p>Your output is the whole <code>Person</code>&nbsp;table after executing your sql. Use <code>delete</code> statement.</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```
delete from Person where Id not in (select min(Id) from (select * from Person) as p group by p.Email)
```

<!-- tabs:end -->
