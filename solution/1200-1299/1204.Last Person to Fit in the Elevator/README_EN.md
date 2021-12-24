# [1204. Last Person to Fit in the Elevator](https://leetcode.com/problems/last-person-to-fit-in-the-elevator)

[中文文档](/solution/1200-1299/1204.Last%20Person%20to%20Fit%20in%20the%20Elevator/README.md)

## Description

<p>Table: <code>Queue</code></p>

<pre>

+-------------+---------+

| Column Name | Type    |

+-------------+---------+

| person_id   | int     |

| person_name | varchar |

| weight      | int     |

| turn        | int     |

+-------------+---------+

person_id is the primary key column for this table.

This table has the information about all people waiting for an elevator.

The <code>person_id</code>&nbsp;and <code>turn</code> columns will contain all numbers from 1 to n, where n is the number of rows in the table.

</pre>

<p>&nbsp;</p>

<p>The maximum weight the elevator can hold is <strong>1000</strong>.</p>

<p>Write an SQL query to find the&nbsp;<code>person_name</code> of the last person who will fit in the elevator without exceeding the weight limit. It is guaranteed that the person who is&nbsp;first in the queue can fit in the elevator.</p>

<p>The query result format is in the following example:</p>

<pre>

Queue table

+-----------+-------------------+--------+------+

| person_id | person_name       | weight | turn |

+-----------+-------------------+--------+------+

| 5         | George Washington | 250    | 1    |

| 3         | John Adams        | 350    | 2    |

| 6         | Thomas Jefferson  | 400    | 3    |

| 2         | Will Johnliams    | 200    | 4    |

| 4         | Thomas Jefferson  | 175    | 5    |

| 1         | James Elephant    | 500    | 6    |

+-----------+-------------------+--------+------+



Result table

+-------------------+

| person_name       |

+-------------------+

| Thomas Jefferson  |

+-------------------+



Queue table is ordered by turn in the example for simplicity.

In the example George Washington(id 5), John Adams(id 3) and Thomas Jefferson(id 6) will enter the elevator as their weight sum is 250 + 350 + 400 = 1000.

Thomas Jefferson(id 6) is the last person to fit in the elevator because he has the last turn in these three people.

</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
