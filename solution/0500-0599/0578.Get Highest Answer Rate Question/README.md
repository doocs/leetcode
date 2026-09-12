---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0578.Get%20Highest%20Answer%20Rate%20Question/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [578. 查询回答率最高的问题 🔒](https://leetcode.cn/problems/get-highest-answer-rate-question)

[English Version](/solution/0500-0599/0578.Get%20Highest%20Answer%20Rate%20Question/README_EN.md)

## 题目描述

<!-- description:start -->

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
这张表可能包含重复项。
action 是一个 ENUM(category) 数据，可以是 "show"、"answer" 或者 "skip" 。
这张表的每一行表示：ID = id 的用户对 question_id 的问题在 timestamp 时间进行了 action 操作。
如果用户对应的操作是 "answer" ，answer_id 将会是对应答案的 id ，否则，值为 null 。
q_num 是该问题在当前会话中的数字顺序。
</pre>

<p>&nbsp;</p>

<p><strong>回答率</strong> 是指：同一问题编号中回答次数占显示次数的比率。</p>

<p>编写一个解决方案以报告 <strong>回答率</strong> 最高的问题。如果有多个问题具有相同的最大 <strong>回答率</strong> ，返回 <code>question_id</code> 最小的那个。</p>

<p>查询结果如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 答题率是 `answer` 次数除以 `show` 次数，取最高者，并列取最小 `question_id`。按题分组即可。
>
> `SUM(action = 'answer') / SUM(action = 'show')` 在分组内算出比率，按比率降序、id 升序，`LIMIT 1` 取出题目。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT question_id AS survey_log
FROM SurveyLog
GROUP BY 1
ORDER BY SUM(action = 'answer') / SUM(action = 'show') DESC, 1
LIMIT 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 方法一用 `GROUP BY` 聚合。窗口也可以按题目分区算出同一比率，再排序取第一。
>
> `SUM(...) OVER (PARTITION BY question_id)` 给每一行都带上该题的比率，外层去重排序。结果与分组聚合相同，便于与其他窗口列一起用。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
WITH
    T AS (
        SELECT
            question_id AS survey_log,
            (SUM(action = 'answer') OVER (PARTITION BY question_id)) / (
                SUM(action = 'show') OVER (PARTITION BY question_id)
            ) AS ratio
        FROM SurveyLog
    )
SELECT survey_log
FROM T
ORDER BY ratio DESC, 1
LIMIT 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
