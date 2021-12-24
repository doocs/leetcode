# [1484. Group Sold Products By The Date](https://leetcode.com/problems/group-sold-products-by-the-date)

[中文文档](/solution/1400-1499/1484.Group%20Sold%20Products%20By%20The%20Date/README.md)

## Description

<p>Table <code>Activities</code>:</p>

<pre>

+-------------+---------+

| Column Name | Type    |

+-------------+---------+

| sell_date   | date    |

| product     | varchar |

+-------------+---------+

There is no primary key for this table, it may contains duplicates.

Each row of this table contains the product name and the date it was sold in a market.</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find for each date, the number of distinct products sold and their names.</p>

<p>The sold-products names for each date should be sorted lexicographically.&nbsp;</p>

<p>Return the result table ordered by <code>sell_date</code>.</p>

<p>The query result format is in the following example.</p>

<pre>

<code>Activities</code> table:

+------------+-------------+

| sell_date  | product     |

+------------+-------------+

| 2020-05-30 | Headphone   |

| 2020-06-01 | Pencil      |

| 2020-06-02 | Mask        |

| 2020-05-30 | Basketball  |

| 2020-06-01 | Bible       |

| 2020-06-02 | Mask        |

| 2020-05-30 | T-Shirt     |

+------------+-------------+



Result table:

+------------+----------+------------------------------+

| sell_date  | num_sold | products                     |

+------------+----------+------------------------------+

| 2020-05-30 | 3        | Basketball,Headphone,T-shirt |

| 2020-06-01 | 2        | Bible,Pencil                 |

| 2020-06-02 | 1        | Mask                         |

+------------+----------+------------------------------+

For 2020-05-30, Sold items were (Headphone, Basketball, T-shirt), we sort them lexicographically and separate them by comma.

For 2020-06-01, Sold items were (Pencil, Bible), we sort them lexicographically and separate them by comma.

For 2020-06-02, Sold item is (Masks), we just return it.



</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
