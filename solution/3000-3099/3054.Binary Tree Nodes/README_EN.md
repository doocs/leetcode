---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3054.Binary%20Tree%20Nodes/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3054. Binary Tree Nodes 🔒](https://leetcode.com/problems/binary-tree-nodes)

[中文文档](/solution/3000-3099/3054.Binary%20Tree%20Nodes/README.md)

## Description

<!-- description:start -->

<p>Table: <font face="monospace"><code>Tree</code></font></p>

<pre>
+-------------+------+ 
| Column Name | Type | 
+-------------+------+ 
| N           | int  | 
| P           | int  |
+-------------+------+
N is the column of unique values for this table.
Each row includes N and P, where N represents the value of a node in Binary Tree, and P is the parent of N.
</pre>

<p>Write a solution to find the node type of the Binary Tree. Output one of the following for each node:</p>

<ul>
	<li><strong>Root</strong>: if the node is the root node.</li>
	<li><strong>Leaf</strong>: if the node is the leaf node.</li>
	<li><strong>Inner</strong>: if the node is neither root nor leaf node.</li>
</ul>

<p>Return <em>the result table ordered by node value in <strong>ascending order</strong></em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Tree table:
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
<strong>Output:</strong> 
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
<strong>Explanation:</strong> 
- Node 5 is the root node since it has no parent node.
- Nodes 1, 3, 6, and 8 are leaf nodes because they don&#39;t have any child nodes.
- Nodes 2, 4, and 7 are inner nodes as they serve as parents to some of the nodes in the structure.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Left Join

If a node's parent is null, then it is a root node; if a node is not the parent of any node, then it is a leaf node; otherwise, it is an internal node.

Therefore, we use left join to join the `Tree` table twice, with the join condition being `t1.N = t2.P`. If `t1.P` is null, then `t1.N` is a root node; if `t2.P` is null, then `t1.N` is a leaf node; otherwise, `t1.N` is an internal node.

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
