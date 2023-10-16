# [1113. 报告的记录](https://leetcode.cn/problems/reported-posts)

[English Version](/solution/1100-1199/1113.Reported%20Posts/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>动作表：<code>Actions</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| post_id       | int     |
| action_date   | date    | 
| action        | enum    |
| extra         | varchar |
+---------------+---------+
此表可能会有重复的行。
action 字段是 ENUM 类型的，包含:('view', 'like', 'reaction', 'comment', <em><strong>'</strong></em>report<em><strong>'</strong></em>, 'share')
extra 包含关于 action 的可选信息，例如举报的原因或反馈的类型。
当 action 为 'report' 时 extra 不会为 NULL。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，针对每个举报原因统计昨天的举报帖子数量。假设今天是&nbsp;<code>2019-07-05</code>&nbsp;。</p>

<p>返回结果表 <strong>无顺序要求 </strong>。</p>

<p>结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Actions table:
+---------+---------+-------------+--------+--------+
| user_id | post_id | action_date | action | extra  |
+---------+---------+-------------+--------+--------+
| 1       | 1       | 2019-07-01  | view   | null   |
| 1       | 1       | 2019-07-01  | like   | null   |
| 1       | 1       | 2019-07-01  | share  | null   |
| 2       | 4       | 2019-07-04  | view   | null   |
| 2       | 4       | 2019-07-04  | report | spam   |
| 3       | 4       | 2019-07-04  | view   | null   |
| 3       | 4       | 2019-07-04  | report | spam   |
| 4       | 3       | 2019-07-02  | view   | null   |
| 4       | 3       | 2019-07-02  | report | spam   |
| 5       | 2       | 2019-07-04  | view   | null   |
| 5       | 2       | 2019-07-04  | report | racism |
| 5       | 5       | 2019-07-04  | view   | null   |
| 5       | 5       | 2019-07-04  | report | racism |
+---------+---------+-------------+--------+--------+
<strong>输出：</strong>
+---------------+--------------+
| report_reason | report_count |
+---------------+--------------+
| spam          | 1            |
| racism        | 2            |
+---------------+--------------+ 
<strong>解释：</strong>注意，我们只关心举报帖数量非零的举报原因。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT extra AS report_reason, COUNT(DISTINCT post_id) AS report_count
FROM Actions
WHERE action_date = '2019-07-04' AND action = 'report'
GROUP BY 1;
```

<!-- tabs:end -->
