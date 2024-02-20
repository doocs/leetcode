# [608. 树节点](https://leetcode.cn/problems/tree-node)

[English Version](/solution/0600-0699/0608.Tree%20Node/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Tree</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| p_id        | int  |
+-------------+------+
id 是该表中具有唯一值的列。
该表的每行包含树中节点的 id 及其父节点的 id 信息。
给定的结构总是一个有效的树。
</pre>

<p>&nbsp;</p>

<p>树中的每个节点可以是以下三种类型之一：</p>

<ul>
	<li><strong>"Leaf"</strong>：节点是叶子节点。</li>
	<li><strong>"Root"</strong>：节点是树的根节点。</li>
	<li><strong>"lnner"</strong>：节点既不是叶子节点也不是根节点。</li>
</ul>

<p>编写一个解决方案来报告树中每个节点的类型。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0608.Tree%20Node/images/tree1.jpg" style="width: 304px; height: 224px;" />
<pre>
<b>输入：</b>
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
<b>输出：</b>
+----+-------+
| id | type  |
+----+-------+
| 1  | Root  |
| 2  | Inner |
| 3  | Leaf  |
| 4  | Leaf  |
| 5  | Leaf  |
+----+-------+
<b>解释：</b>
节点 1 是根节点，因为它的父节点为空，并且它有子节点 2 和 3。
节点 2 是一个内部节点，因为它有父节点 1 和子节点 4 和 5。
节点 3、4 和 5 是叶子节点，因为它们有父节点而没有子节点。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0608.Tree%20Node/images/tree2.jpg" style="width: 64px; height: 65px;" />
<pre>
<b>输入：</b>
Tree table:
+----+------+
| id | p_id |
+----+------+
| 1  | null |
+----+------+
<b>输出：</b>
+----+-------+
| id | type  |
+----+-------+
| 1  | Root  |
+----+-------+
<b>解释：</b>如果树中只有一个节点，则只需要输出其根属性。
</pre>

## 解法

### 方法一：条件判断 + 子查询

我们可以使用 `CASE WHEN` 条件判断语句来判断每个节点的类型，具体地：

-   如果一个节点的 `p_id` 为 `NULL`，则该节点为根节点；
-   否则，如果一个节点是另一个节点的父节点（这里我们使用子查询来判断），则该节点为内部节点；
-   否则，该节点为叶子节点。

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

<!-- end -->
