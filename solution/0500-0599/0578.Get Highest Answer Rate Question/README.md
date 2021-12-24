# [578. 查询回答率最高的问题](https://leetcode-cn.com/problems/get-highest-answer-rate-question)

[English Version](/solution/0500-0599/0578.Get%20Highest%20Answer%20Rate%20Question/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>从 <code>survey_log</code> 表中获得回答率最高的问题，<code>survey_log</code> 表包含这些列<strong>：id</strong>, <strong>action</strong>, <strong>question_id</strong>, <strong>answer_id</strong>, <strong>q_num</strong>, <strong>timestamp</strong>。</p>

<p>id 表示用户 id；action 有以下几种值：&quot;show&quot;，&quot;answer&quot;，&quot;skip&quot;；当 action 值为 &quot;answer&quot; 时 answer_id 非空，而 action 值为 &quot;show&quot; 或者 &quot;skip&quot; 时 answer_id 为空；q_num 表示当前会话中问题的编号。</p>

<p>请编写 SQL 查询来找到具有最高回答率的问题。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>
+------+-----------+--------------+------------+-----------+------------+
| id   | action    | question_id  | answer_id  | q_num     | timestamp  |
+------+-----------+--------------+------------+-----------+------------+
| 5    | show      | 285          | null       | 1         | 123        |
| 5    | answer    | 285          | 124124     | 1         | 124        |
| 5    | show      | 369          | null       | 2         | 125        |
| 5    | skip      | 369          | null       | 2         | 126        |
+------+-----------+--------------+------------+-----------+------------+
<strong>输出：</strong>
+-------------+
| survey_log  |
+-------------+
|    285      |
+-------------+
<strong>解释：</strong>
问题 285 的回答率为 1/1，而问题 369 回答率为 0/1，因此输出 285 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong>回答率最高的含义是：同一问题编号中回答数占显示数的比例最高。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
