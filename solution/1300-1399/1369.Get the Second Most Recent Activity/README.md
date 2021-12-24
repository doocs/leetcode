# [1369. 获取最近第二次的活动](https://leetcode-cn.com/problems/get-the-second-most-recent-activity)

[English Version](/solution/1300-1399/1369.Get%20the%20Second%20Most%20Recent%20Activity/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>UserActivity</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| username      | varchar |
| activity      | varchar |
| startDate     | Date    |
| endDate       | Date    |
+---------------+---------+
该表不包含主键
该表包含每个用户在一段时间内进行的活动的信息
名为 username 的用户在 startDate 到 endDate 日内有一次活动
</pre>

<p> </p>

<p>写一条SQL查询展示每一位用户 <strong>最近第二次</strong> 的活动</p>

<p>如果用户仅有一次活动，返回该活动</p>

<p>一个用户不能同时进行超过一项活动，以<strong> 任意 </strong>顺序返回结果</p>

<p>下面是查询结果格式的例子：</p>

<pre>
<code>UserActivity</code> 表:
+------------+--------------+-------------+-------------+
| username   | activity     | startDate   | endDate     |
+------------+--------------+-------------+-------------+
| Alice      | Travel       | 2020-02-12  | 2020-02-20  |
| Alice      | Dancing      | 2020-02-21  | 2020-02-23  |
| Alice      | Travel       | 2020-02-24  | 2020-02-28  |
| Bob        | Travel       | 2020-02-11  | 2020-02-18  |
+------------+--------------+-------------+-------------+

Result 表:
+------------+--------------+-------------+-------------+
| username   | activity     | startDate   | endDate     |
+------------+--------------+-------------+-------------+
| Alice      | Dancing      | 2020-02-21  | 2020-02-23  |
| Bob        | Travel       | 2020-02-11  | 2020-02-18  |
+------------+--------------+-------------+-------------+

Alice 最近一次的活动是从 2020-02-24 到 2020-02-28 的旅行, 在此之前的 2020-02-21 到 2020-02-23 她进行了舞蹈
Bob 只有一条记录，我们就取这条记录
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
