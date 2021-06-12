# [1890. The Latest Login in 2020](https://leetcode-cn.com/problems/the-latest-login-in-2020)

[English Version](/solution/1800-1899/1890.The%20Latest%20Login%20in%202020/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Logins</code></p>

<pre>

+----------------+----------+

| Column Name    | Type     |

+----------------+----------+

| user_id        | int      |

| time_stamp     | datetime |

+----------------+----------+

(user_id, time_stamp) is the primary key for this table.

Each row contains information about the login time for the user with ID user_id.

</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the <strong>latest</strong> login for all users in the year <code>2020</code>. Do <strong>not</strong> include the users who did not login in <code>2020</code>.</p>

<p>Return the result table <strong>in any order</strong>.</p>

<p>The query result format is in the following example:</p>

<p>&nbsp;</p>

<pre>

Logins table:

+---------+---------------------+

| user_id | time_stamp          |

+---------+---------------------+

| 6       | 2020-06-30 15:06:07 |

| 6       | 2021-04-21 14:06:06 |

| 6       | 2019-03-07 00:18:15 |

| 8       | 2020-02-01 05:10:53 |

| 8       | 2020-12-30 00:46:50 |

| 2       | 2020-01-16 02:49:50 |

| 2       | 2019-08-25 07:59:08 |

| 14      | 2019-07-14 09:00:00 |

| 14      | 2021-01-06 11:59:59 |

+---------+---------------------+



Result table:

+---------+---------------------+

| user_id | last_stamp          |

+---------+---------------------+

| 6       | 2020-06-30 15:06:07 |

| 8       | 2020-12-30 00:46:50 |

| 2       | 2020-01-16 02:49:50 |

+---------+---------------------+



User 6 logged into their account 3 times but only once in 2020, so we include this login in the result table.

User 8 logged into their account 2 times in 2020, once in February and once in December. We include only the latest one (December) in the result table.

User 2 logged into their account 2 times but only once in 2020, so we include this login in the result table.

User 14 did not login in 2020, so we do not include them in the result table.

</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql
# Write your MySQL query statement below
SELECT
    user_id,
    max(time_stamp) AS last_stamp
FROM
    Logins
WHERE YEAR(time_stamp) = 2020
GROUP BY user_id;
```

<!-- tabs:end -->
