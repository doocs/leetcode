# [578. Get Highest Answer Rate Question](https://leetcode.com/problems/get-highest-answer-rate-question)

[中文文档](/solution/0500-0599/0578.Get%20Highest%20Answer%20Rate%20Question/README.md)

<!-- tags:Database -->

## Description

<p>Table: <code>SurveyLog</code></p>

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
This table may contain duplicate rows.
action is an ENUM (category) of the type: &quot;show&quot;, &quot;answer&quot;, or &quot;skip&quot;.
Each row of this table indicates the user with ID = id has taken an action with the question question_id at time timestamp.
If the action taken by the user is &quot;answer&quot;, answer_id will contain the id of that answer, otherwise, it will be null.
q_num is the numeral order of the question in the current session.
</pre>

<p>&nbsp;</p>

<p>The <strong>answer rate</strong> for a question is the number of times a user answered the question by the number of times a user showed the question.</p>

<p>Write a solution to report the question that has the highest <strong>answer rate</strong>. If multiple questions have the same maximum <strong>answer rate</strong>, report the question with the smallest <code>question_id</code>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
SurveyLog table:
+----+--------+-------------+-----------+-------+-----------+
| id | action | question_id | answer_id | q_num | timestamp |
+----+--------+-------------+-----------+-------+-----------+
| 5  | show   | 285         | null      | 1     | 123       |
| 5  | answer | 285         | 124124    | 1     | 124       |
| 5  | show   | 369         | null      | 2     | 125       |
| 5  | skip   | 369         | null      | 2     | 126       |
+----+--------+-------------+-----------+-------+-----------+
<strong>Output:</strong> 
+------------+
| survey_log |
+------------+
| 285        |
+------------+
<strong>Explanation:</strong> 
Question 285 was showed 1 time and answered 1 time. The answer rate of question 285 is 1.0
Question 369 was showed 1 time and was not answered. The answer rate of question 369 is 0.0
Question 285 has the highest answer rate.</pre>

## Solutions

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT question_id AS survey_log
FROM SurveyLog
GROUP BY 1
ORDER BY SUM(action = 'answer') / SUM(action = 'show') DESC, 1
LIMIT 1;
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

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

<!-- end -->
