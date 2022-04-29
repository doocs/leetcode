# [614. 二级关注者](https://leetcode.cn/problems/second-degree-follower)

[English Version](/solution/0600-0699/0614.Second%20Degree%20Follower/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在 facebook 中，表&nbsp;<code>follow</code>&nbsp;会有 2 个字段： <strong>followee</strong>, <strong>follower</strong>&nbsp;，分别表示被关注者和关注者。</p>

<p>请写一个 sql 查询语句，对每一个关注者，查询关注他的关注者的数目。</p>

<p>比方说：</p>

<pre>+-------------+------------+
| followee    | follower   |
+-------------+------------+
|     A       |     B      |
|     B       |     C      |
|     B       |     D      |
|     D       |     E      |
+-------------+------------+
</pre>

<p>应该输出：</p>

<pre>+-------------+------------+
| follower    | num        |
+-------------+------------+
|     B       |  2         |
|     D       |  1         |
+-------------+------------+
</pre>

<p><strong>解释：</strong></p>

<p>B 和 D 都在在&nbsp;<strong>follower</strong>&nbsp;字段中出现，作为被关注者，B 被 C 和 D 关注，D 被 E 关注。A 不在 <strong>follower</strong>&nbsp;字段内，所以A不在输出列表中。</p>

<p>&nbsp;</p>

<p><strong>注意：</strong></p>

<ul>
	<li>被关注者永远不会被他 / 她自己关注。</li>
	<li>将结果按照字典序返回。</li>
</ul>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
