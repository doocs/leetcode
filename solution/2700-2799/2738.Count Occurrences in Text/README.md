---
comments: true
difficulty: 中等
tags:
    - 数据库
---

<!-- problem:start -->

# [2738. 统计文本中单词的出现次数 🔒](https://leetcode.cn/problems/count-occurrences-in-text)

[English Version](/solution/2700-2799/2738.Count%20Occurrences%20in%20Text/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<font face="monospace"><code>Files</code></font></p>

<pre>
+-------------+---------+
| 列名        | 类型    |
+-- ----------+---------+
| file_name   | varchar |
| content     | text    |
+-------------+---------+
file_name 为该表的主键（具有唯一值的列）。
每行包含 file_name 和该文件的内容。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，找出单词 <strong>'bull' </strong>和 <strong>'bear'</strong> 作为 <strong>独立词</strong> 有出现的文件数量，不考虑任何它出现在两侧没有空格的情况（例如，'bullet',&nbsp;'bears', 'bull.'，或者 'bear'&nbsp;在句首或句尾&nbsp;<strong>不会</strong> 被考虑）。</p>

<p>返回单词 'bull' 和 'bear' 以及它们对应的出现文件数量，<strong>顺序没有限制</strong>&nbsp;。</p>

<p>结果的格式如下所示：</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>
Files 表:
+------------+----------------------------------------------------------------------------------+
| file_name  | content                                                                         | 
+------------+----------------------------------------------------------------------------------+
| draft1.txt | The stock exchange predicts a bull market which would make many investors happy. | 
| draft2.txt | The stock exchange predicts a bull market which would make many investors happy, |
|&nbsp;           | but analysts warn of possibility of too much optimism and that in fact we are    |
|&nbsp;           | awaiting a bear market.                                                          | 
| draft3.txt | The stock exchange predicts a bull market which would make many investors happy, |
|&nbsp;           | but analysts warn of possibility of too much optimism and that in fact we are    |
|&nbsp;           | awaiting a bear market. As always predicting the future market is an uncertain   |
|            | game and all investors should follow their instincts and best practices.         | 
+------------+----------------------------------------------------------------------------------+
<strong>输出：</strong>&nbsp;
+------+-------+
| word | count | &nbsp;
+------+-------+
| bull |&nbsp;3     |&nbsp;
| bear |&nbsp;2     | 
+------+-------+
<b>解释：</b>
- 单词 "bull" 在 "draft1.txt" 中出现1次，在 "draft2.txt" 中出现 1 次，在 "draft3.txt" 中出现 1 次。因此，单词 "bull" 出现在 3 个文件中。
- 单词 "bear" 在 "draft2.txt" 中出现1次，在 "draft3.txt" 中出现 1 次。因此，单词 "bear" 出现在 2 个文件中。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 统计文本中作为独立单词出现的 $bull$ 与 $bear$。用正则拆词再过滤可行，但目标词只有两个，且必须左右为空格。
>
> 对两词分别用 $LIKE\ '\%\ word\ \%'$ 计数，再用 $UNION$ 拼成两行结果，避免把词的子串误计入。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT 'bull' AS word, COUNT(*) AS count
FROM Files
WHERE content LIKE '% bull %'
UNION
SELECT 'bear' AS word, COUNT(*) AS count
FROM Files
WHERE content LIKE '% bear %';
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
