# [578. 查询回答率最高的问题](https://leetcode.cn/problems/get-highest-answer-rate-question)

[English Version](/solution/0500-0599/0578.Get%20Highest%20Answer%20Rate%20Question/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>SurveyLog</code> 表：</p>

<div class="original__bRMd">
<div>
<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| action      | ENUM |
| question_id | int  |
| answer_id   | int  |
| q_num       | int  |
| timestamp   | int  |
+-------------+------+
这张表没有主键，其中可能包含重复项。
action 是一个 ENUM 数据，可以是 "show"、"answer" 或者 "skip" 。
这张表的每一行表示：ID = id 的用户对 question_id 的问题在 timestamp 时间进行了 action 操作。
如果用户对应的操作是 "answer" ，answer_id 将会是对应答案的 id ，否则，值为 null 。
q_num 是该问题在当前会话中的数字顺序。
</pre>

<p>&nbsp;</p>

<p><strong>回答率</strong> 是指：同一问题编号中回答次数占显示次数的比率。</p>

<p>编写一个 SQL 查询以报告 <strong>回答率</strong> 最高的问题。如果有多个问题具有相同的最大 <strong>回答率</strong> ，返回 <code>question_id</code> 最小的那个。</p>

<p>查询结果如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
SurveyLog table:
+----+--------+-------------+-----------+-------+-----------+
| id | action | question_id | answer_id | q_num | timestamp |
+----+--------+-------------+-----------+-------+-----------+
| 5  | show   | 285         | null      | 1     | 123       |
| 5  | answer | 285         | 124124    | 1     | 124       |
| 5  | show   | 369         | null      | 2     | 125       |
| 5  | skip   | 369         | null      | 2     | 126       |
+----+--------+-------------+-----------+-------+-----------+
<strong>输出：</strong>
+------------+
| survey_log |
+------------+
| 285        |
+------------+
<strong>解释：</strong>
问题 285 显示 1 次、回答 1 次。回答率为 1.0 。
问题 369 显示 1 次、回答 0 次。回答率为 0.0 。
问题 285 回答率最高。</pre>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT question_id AS survey_log
FROM   SurveyLog
GROUP  BY 1
ORDER  BY SUM(action = 'answer') / SUM(action = 'show') DESC
LIMIT  1;
```

<!-- tabs:end -->
