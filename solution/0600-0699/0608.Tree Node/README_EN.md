---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0608.Tree%20Node/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [608. Tree Node](https://leetcode.com/problems/tree-node)

[中文文档](/solution/0600-0699/0608.Tree%20Node/README.md)

## Description

<p>Table: <code>Tree</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| p_id        | int  |
+-------------+------+
id is the column with unique values for this table.
Each row of this table contains information about the id of a node and the id of its parent node in a tree.
The given structure is always a valid tree.
</pre>

<p>&nbsp;</p>

<p>Each node in the tree can be one of three types:</p>

<ul>
	<li><strong>&quot;Leaf&quot;</strong>: if the node is a leaf node.</li>
	<li><strong>&quot;Root&quot;</strong>: if the node is the root of the tree.</li>
	<li><strong>&quot;Inner&quot;</strong>: If the node is neither a leaf node nor a root node.</li>
</ul>

<p>Write a solution to report the type of each node in the tree.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0608.Tree%20Node/images/tree1.jpg" style="width: 304px; height: 224px;" />
<pre>
<strong>Input:</strong> 
Tree table:
+----+------+
| id | p_id |
+----+------+
| 1  | null |
| 2  | 1    |
| 3  | 1    |
| 4  | 2    |
| 5  | 2    |
+----+------+
<strong>Output:</strong> 
+----+-------+
| id | type  |
+----+-------+
| 1  | Root  |
| 2  | Inner |
| 3  | Leaf  |
| 4  | Leaf  |
| 5  | Leaf  |
+----+-------+
<strong>Explanation:</strong> 
Node 1 is the root node because its parent node is null and it has child nodes 2 and 3.
Node 2 is an inner node because it has parent node 1 and child node 4 and 5.
Nodes 3, 4, and 5 are leaf nodes because they have parent nodes and they do not have child nodes.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0608.Tree%20Node/images/tree2.jpg" style="width: 64px; height: 65px;" />
<pre>
<strong>Input:</strong> 
Tree table:
+----+------+
| id | p_id |
+----+------+
| 1  | null |
+----+------+
<strong>Output:</strong> 
+----+-------+
| id | type  |
+----+-------+
| 1  | Root  |
+----+-------+
<strong>Explanation:</strong> If there is only one node on the tree, you only need to output its root attributes.
</pre>

## Solutions

<!-- solution:start -->

### Solution 1: Conditional Statements + Subquery

We can use the `CASE WHEN` conditional statement to determine the type of each node as follows:

-   If a node's `p_id` is `NULL`, then it is a root node.
-   Otherwise, if a node is the parent node of another node (we use a subquery to determine this), then it is an internal node.
-   Otherwise, it is a leaf node.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    id,
    CASE
        WHEN p_id IS NULL THEN 'Root'
        WHEN id IN (SELECT p_id FROM Tree) THEN 'Inner'
        ELSE 'Leaf'
    END AS type
FROM Tree;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
