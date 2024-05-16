---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3054.Binary%20Tree%20Nodes/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3054. 二叉树节点 🔒](https://leetcode.cn/problems/binary-tree-nodes)

[English Version](/solution/3000-3099/3054.Binary%20Tree%20Nodes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<font face="monospace"><code>Tree</code></font></p>

<pre>
+-------------+------+ 
| Column Name | Type | 
+-------------+------+ 
| N           | int  | 
| P           | int  |
+-------------+------+
N 是这张表中具有不同值的列。
每一行中包含 N 和 P，其中 N 表示二叉树中节点的值，P 是 N 的父亲。
</pre>

<p>编写一个解决方案来找到二进制树节点的类型。对于每个节点输出：</p>

<ul>
	<li><strong>Root</strong>：如果节点是根节点。</li>
	<li><strong>Leaf</strong>：如果节点是叶子节点。</li>
	<li><strong>Inner</strong>: 如果节点既不是根节点，也不是叶子节点。</li>
</ul>

<p>返回结果表，根据节点值 <strong>升序排序</strong>。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Tree 表：
+---+------+
| N | P    | 
+---+------+
| 1 | 2    |
| 3 | 2    | 
| 6 | 8    | 
| 9 | 8    | 
| 2 | 5    | 
| 8 | 5    | 
| 5 | null | 
+---+------+
<strong>输出：</strong>
+---+-------+
| N | Type  | 
+---+-------+
| 1 | Leaf  | 
| 2 | Inner |
| 3 | Leaf  |
| 5 | Root  |
| 6 | Leaf  |
| 8 | Inner |
| 9 | Leaf  |    
+---+-------+
<strong>解释：</strong>
- 节点 5 是根节点，因为它没有父节点。
- 节点 1，3，6 和 8 是叶节点，因为它们没有任何子节点。
- 节点 2，4，7 是内部节点，因为它们充当结构中某些节点的父节点。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：左连接

如果一个节点的父节点为空，则它是根节点；如果一个节点不是任何节点的父节点，则它是叶子节点；否则它是内部节点。

因此，我们使用左连接来连接两次 `Tree` 表，连接条件是 `t1.N = t2.P`。那么如果 `t1.P` 为空，则 `t1.N` 是根节点；如果 `t2.P` 为空，则 `t1.N` 是叶子节点；否则 `t1.N` 是内部节点。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT DISTINCT
    t1.N AS N,
    IF(t1.P IS NULL, 'Root', IF(t2.P IS NULL, 'Leaf', 'Inner')) AS Type
FROM
    Tree AS t1
    LEFT JOIN Tree AS t2 ON t1.N = t2.p
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
