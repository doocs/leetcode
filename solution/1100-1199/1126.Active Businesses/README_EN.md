# [1126. Active Businesses](https://leetcode.com/problems/active-businesses)

[中文文档](/solution/1100-1199/1126.Active%20Businesses/README.md)

## Description

<p>Table: <code>Events</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| business_id   | int     |
| event_type    | varchar |
| occurences    | int     | 
+---------------+---------+
(business_id, event_type) is the primary key of this table.
Each row in the table logs the info that an event of some type occured at some business for a number of times.</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find all <em>active businesses</em>.</p>

<p>An active business is a business that has more than one event type&nbsp;with occurences greater than the average occurences of that event type&nbsp;among all businesses.</p>

<p>The query result format is in the following example:</p>

<pre>
Events table:
+-------------+------------+------------+
| business_id | event_type | occurences |
+-------------+------------+------------+
| 1           | reviews    | 7          |
| 3           | reviews    | 3          |
| 1           | ads        | 11         |
| 2           | ads        | 7          |
| 3           | ads        | 6          |
| 1           | page views | 3          |
| 2           | page views | 12         |
+-------------+------------+------------+

Result table:
+-------------+
| business_id |
+-------------+
| 1           |
+-------------+ 
Average for &#39;reviews&#39;, &#39;ads&#39; and &#39;page views&#39; are (7+3)/2=5, (11+7+6)/3=8, (3+12)/2=7.5 respectively.
Business with id 1 has 7 &#39;reviews&#39; events (more than 5) and 11 &#39;ads&#39; events (more than 8) so it is an active business.</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
