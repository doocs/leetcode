# [608. 树节点](https://leetcode.cn/problems/tree-node)

[English Version](/solution/0600-0699/0608.Tree%20Node/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个表&nbsp;<code>tree</code>，<strong>id</strong> 是树节点的编号，&nbsp;<strong>p_id</strong>&nbsp;是它父节点的&nbsp;<strong>id 。</strong></p>

<pre>+----+------+
| id | p_id |
+----+------+
| 1  | null |
| 2  | 1    |
| 3  | 1    |
| 4  | 2    |
| 5  | 2    |
+----+------+</pre>

<p>树中每个节点属于以下三种类型之一：</p>

<ul>
	<li>叶子：如果这个节点没有任何孩子节点。</li>
	<li>根：如果这个节点是整棵树的根，即没有父节点。</li>
	<li>内部节点：如果这个节点既不是叶子节点也不是根节点。</li>
</ul>

<p>&nbsp;</p>

<p>写一个查询语句，输出所有节点的编号和节点的类型，并将结果按照节点编号排序。上面样例的结果为：</p>

<p>&nbsp;</p>

<pre>+----+------+
| id | Type |
+----+------+
| 1  | Root |
| 2  | Inner|
| 3  | Leaf |
| 4  | Leaf |
| 5  | Leaf |
+----+------+
</pre>

<p>&nbsp;</p>

<p><strong>解释</strong></p>

<ul>
	<li>节点 &#39;1&#39; 是根节点，因为它的父节点是 NULL ，同时它有孩子节点 &#39;2&#39; 和 &#39;3&#39; 。</li>
	<li>节点 &#39;2&#39; 是内部节点，因为它有父节点 &#39;1&#39; ，也有孩子节点 &#39;4&#39; 和 &#39;5&#39; 。</li>
	<li>节点 &#39;3&#39;, &#39;4&#39; 和 &#39;5&#39; 都是叶子节点，因为它们都有父节点同时没有孩子节点。</li>
	<li>样例中树的形态如下：
	<p>&nbsp;</p>

    <pre>			  1
    		/   \
                      2       3
                    /   \
                  4       5

</pre>

    <p>&nbsp;</p>
    </li>

</ul>

<p><strong>注意</strong></p>

<p>如果树中只有一个节点，你只需要输出它的根属性。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT id,
    (
        CASE
            WHEN p_id IS NULL THEN 'Root'
            WHEN id IN (
                SELECT p_id
                FROM tree
            ) THEN 'Inner'
            ELSE 'Leaf'
        END
    ) AS type
FROM tree;
```

<!-- tabs:end -->
