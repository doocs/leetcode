# [175. Combine Two Tables](https://leetcode.com/problems/combine-two-tables)

[中文文档](/solution/0100-0199/0175.Combine%20Two%20Tables/README.md)

## Description
<p>Table: <code>Person</code></p>



<pre>

+-------------+---------+

| Column Name | Type    |

+-------------+---------+

| PersonId    | int     |

| FirstName   | varchar |

| LastName    | varchar |

+-------------+---------+

PersonId is the primary key column for this table.

</pre>



<p>Table: <code>Address</code></p>



<pre>

+-------------+---------+

| Column Name | Type    |

+-------------+---------+

| AddressId   | int     |

| PersonId    | int     |

| City        | varchar |

| State       | varchar |

+-------------+---------+

AddressId is the primary key column for this table.

</pre>



<p>&nbsp;</p>



<p>Write a SQL query for a report that provides the following information for each person in the Person table, regardless if there is an address for each of those people:</p>



<pre>

FirstName, LastName, City, State

</pre>




## Solutions

<!-- tabs:start -->

### **SQL**

```
select p.FirstName, p.LastName, a.City, a.State from Person p left join Address a on p.PersonId = a.PersonId;
```

<!-- tabs:end -->