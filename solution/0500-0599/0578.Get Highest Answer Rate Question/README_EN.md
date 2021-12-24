# [578. Get Highest Answer Rate Question](https://leetcode.com/problems/get-highest-answer-rate-question)

[中文文档](/solution/0500-0599/0578.Get%20Highest%20Answer%20Rate%20Question/README.md)

## Description

<p>Get the highest answer rate question from a table <code>survey_log</code> with these columns: <b>id</b>, <b>action</b>, <b>question_id</b>, <b>answer_id</b>, <b>q_num</b>, <b>timestamp</b>.</p>

<p>id means user id; action has these kind of values: &quot;show&quot;, &quot;answer&quot;, &quot;skip&quot;; answer_id is not null when action column is &quot;answer&quot;, while is null for &quot;show&quot; and &quot;skip&quot;; q_num is the numeral order of the question in current session.</p>

<p>Write a sql query to identify the question which has the highest answer rate.</p>

<p><b>Example:</b></p>

<pre>
<b>Input:</b>
+------+-----------+--------------+------------+-----------+------------+
| id   | action    | question_id  | answer_id  | q_num     | timestamp  |
+------+-----------+--------------+------------+-----------+------------+
| 5    | show      | 285          | null       | 1         | 123        |
| 5    | answer    | 285          | 124124     | 1         | 124        |
| 5    | show      | 369          | null       | 2         | 125        |
| 5    | skip      | 369          | null       | 2         | 126        |
+------+-----------+--------------+------------+-----------+------------+
<b>Output:</b>
+-------------+
| survey_log  |
+-------------+
|    285      |
+-------------+
<b>Explanation:</b>
question 285 has answer rate 1/1, while question 369 has 0/1 answer rate, so output 285.
</pre>

<p>&nbsp;</p>

<p><b>Note:</b> The highest answer rate meaning is: answer number&#39;s ratio in show number in the same question.</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
