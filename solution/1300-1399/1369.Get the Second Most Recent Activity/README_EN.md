# [1369. Get the Second Most Recent Activity](https://leetcode.com/problems/get-the-second-most-recent-activity)

[中文文档](/solution/1300-1399/1369.Get%20the%20Second%20Most%20Recent%20Activity/README.md)

## Description

<p>Table: <code>UserActivity</code></p>

<pre>

+---------------+---------+

| Column Name   | Type    |

+---------------+---------+

| username      | varchar |

| activity      | varchar |

| startDate     | Date    |

| endDate       | Date    |

+---------------+---------+

This table does not contain primary key.

This table contain information about the activity performed of each user in a period of time.

A person with username performed a activity from startDate to endDate.



</pre>

<p>Write an SQL query to&nbsp;show&nbsp;the <strong>second most recent activity </strong>of each user.</p>

<p>If the user only has one activity, return that one.&nbsp;</p>

<p>A&nbsp;user can&#39;t perform more than one activity at the same time. Return the result table in <strong>any</strong> order.</p>

<p>The query result format is in the following example:</p>

<pre>

<code>UserActivity</code> table:

+------------+--------------+-------------+-------------+

| username   | activity     | startDate   | endDate     |

+------------+--------------+-------------+-------------+

| Alice      | Travel       | 2020-02-12  | 2020-02-20  |

| Alice      | Dancing      | 2020-02-21  | 2020-02-23  |

| Alice      | Travel       | 2020-02-24  | 2020-02-28  |

| Bob        | Travel       | 2020-02-11  | 2020-02-18  |

+------------+--------------+-------------+-------------+



Result table:

+------------+--------------+-------------+-------------+

| username   | activity     | startDate   | endDate     |

+------------+--------------+-------------+-------------+

| Alice      | Dancing      | 2020-02-21  | 2020-02-23  |

| Bob        | Travel       | 2020-02-11  | 2020-02-18  |

+------------+--------------+-------------+-------------+



The most recent activity of Alice is Travel from 2020-02-24 to 2020-02-28, before that she was dancing from 2020-02-21 to 2020-02-23.

Bob only has one record, we just take that one.

</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
