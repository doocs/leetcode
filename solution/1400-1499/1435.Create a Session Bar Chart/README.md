# [1435. 制作会话柱状图 🔒](https://leetcode.cn/problems/create-a-session-bar-chart)

[English Version](/solution/1400-1499/1435.Create%20a%20Session%20Bar%20Chart/README_EN.md)

<!-- tags:数据库 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Sessions</code></p>

<pre>
+---------------------+---------+
| Column Name         | Type    |
+---------------------+---------+
| session_id          | int     |
| duration            | int     |
+---------------------+---------+
session_id 是该表主键
duration 是用户访问应用的时间, 以秒为单位
</pre>

<p>&nbsp;</p>

<p>你想知道用户在你的 app 上的访问时长情况。因此你决定统计访问时长区间分别为 <code>"[0-5&gt;"</code>，<code>"[5-10&gt;"</code>，<code>"[10-15&gt;"</code>&nbsp;和&nbsp;<code>"15&nbsp;minutes&nbsp;or more"</code>&nbsp;的会话数量，并以此绘制柱状图。</p>

<p>写一个解决方案来报告 <code>(bin, total)</code> 。</p>

<p>返回结果 <strong>无顺序要求</strong> 。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Sessions 表：
+-------------+---------------+
| session_id  | duration      |
+-------------+---------------+
| 1           | 30            |
| 2           | 199           |
| 3           | 299           |
| 4           | 580           |
| 5           | 1000          |
+-------------+---------------+
<strong>输出：</strong>
+--------------+--------------+
| bin          | total        |
+--------------+--------------+
| [0-5&gt;        | 3            |
| [5-10&gt;       | 1            |
| [10-15&gt;      | 0            |
| 15 or more   | 1            |
+--------------+--------------+
<strong>解释：</strong>
对于 session_id 1，2 和 3 ，它们的访问时间大于等于 0 分钟且小于 5 分钟。
对于 session_id 4，它的访问时间大于等于 5 分钟且小于 10 分钟。
没有会话的访问时间大于等于 10 分钟且小于 15 分钟。
对于 session_id 5, 它的访问时间大于等于 15 分钟。
</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
SELECT '[0-5>' AS bin, COUNT(1) AS total FROM Sessions WHERE duration < 300
UNION
SELECT '[5-10>' AS bin, COUNT(1) AS total FROM Sessions WHERE 300 <= duration AND duration < 600
UNION
SELECT '[10-15>' AS bin, COUNT(1) AS total FROM Sessions WHERE 600 <= duration AND duration < 900
UNION
SELECT '15 or more' AS bin, COUNT(1) AS total FROM Sessions WHERE 900 <= duration;
```

<!-- tabs:end -->

<!-- end -->
