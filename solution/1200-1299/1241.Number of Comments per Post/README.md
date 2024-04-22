# [1241. 每个帖子的评论数 🔒](https://leetcode.cn/problems/number-of-comments-per-post)

[English Version](/solution/1200-1299/1241.Number%20of%20Comments%20per%20Post/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表&nbsp;<code>Submissions</code>&nbsp;：</p>

<pre>
+---------------+----------+
| 列名          | 类型     |
+---------------+----------+
| sub_id        | int      |
| parent_id     | int      |
+---------------+----------+
上表可能会出现重复的行。
每行可以是一个帖子或对该帖子的评论。
如果这是一篇帖子，则 parent_id 为 null。
如果这是一条评论，则 parent_id 对应帖子的 sub_id。
</pre>

<p>&nbsp;</p>

<p>编写一个解决方案以查找每个帖子的评论数。</p>

<p>结果表应包含帖子的&nbsp;<code>post_id</code> 和对应的评论数&nbsp;<code>number_of_comments</code> 。</p>

<p><code>Submissions</code> 可能包含重复的评论。你应该统计出每个帖子的 <strong>唯一评论</strong> 的数目。</p>

<p><code>Submissions</code> 可能包含重复的帖子。你应该将它们视为一个帖子。</p>

<p>返回结果表应该按 <code>post_id</code> <strong>升序排序</strong>。</p>

<p>结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Submissions table:
+---------+------------+
| sub_id  | parent_id  |
+---------+------------+
| 1       | Null       |
| 2       | Null       |
| 1       | Null       |
| 12      | Null       |
| 3       | 1          |
| 5       | 2          |
| 3       | 1          |
| 4       | 1          |
| 9       | 1          |
| 10      | 2          |
| 6       | 7          |
+---------+------------+
<strong>输出：</strong>
+---------+--------------------+
| post_id | number_of_comments |
+---------+--------------------+
| 1       | 3                  |
| 2       | 2                  |
| 12      | 0                  |
+---------+--------------------+
<strong>解释：</strong>
表中 ID 为 1 的帖子有 ID 为 3、4 和 9 的三个评论。表中 ID 为 3 的评论重复出现了，所以我们只对它进行了一次计数。
表中 ID 为 2 的帖子有 ID 为 5 和 10 的两个评论。
表中 ID 为 12 的帖子没有评论。
表中 ID 为 6 的评论是对 ID 为 7 的已删除帖子的评论，因此我们将其忽略。</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    t AS (
        SELECT DISTINCT s1.sub_id AS post_id, s2.sub_id AS sub_id
        FROM
            Submissions AS s1
            LEFT JOIN Submissions AS s2 ON s1.sub_id = s2.parent_id
        WHERE s1.parent_id IS NULL
    )
SELECT post_id, COUNT(sub_id) AS number_of_comments
FROM t
GROUP BY post_id
ORDER BY post_id;
```

<!-- tabs:end -->

<!-- end -->
