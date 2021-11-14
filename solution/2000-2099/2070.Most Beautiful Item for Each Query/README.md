# [2070. 每一个查询的最大美丽值](https://leetcode-cn.com/problems/most-beautiful-item-for-each-query)

[English Version](/solution/2000-2099/2070.Most%20Beautiful%20Item%20for%20Each%20Query/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组&nbsp;<code>items</code>&nbsp;，其中&nbsp;<code>items[i] = [price<sub>i</sub>, beauty<sub>i</sub>]</code>&nbsp;分别表示每一个物品的 <strong>价格</strong>&nbsp;和 <strong>美丽值</strong>&nbsp;。</p>

<p>同时给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>queries</code>&nbsp;。对于每个查询&nbsp;<code>queries[j]</code>&nbsp;，你想求出价格小于等于&nbsp;<code>queries[j]</code>&nbsp;的物品中，<strong>最大的美丽值</strong>&nbsp;是多少。如果不存在符合条件的物品，那么查询的结果为&nbsp;<code>0</code>&nbsp;。</p>

<p>请你返回一个长度与 <code>queries</code>&nbsp;相同的数组<em>&nbsp;</em><code>answer</code>，其中<em>&nbsp;</em><code>answer[j]</code>是第&nbsp;<code>j</code>&nbsp;个查询的答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
<b>输出：</b>[2,4,5,5,6,6]
<strong>解释：</strong>
- queries[0]=1 ，[1,2] 是唯一价格 &lt;= 1 的物品。所以这个查询的答案为 2 。
- queries[1]=2 ，符合条件的物品有 [1,2] 和 [2,4] 。
  它们中的最大美丽值为 4 。
- queries[2]=3 和 queries[3]=4 ，符合条件的物品都为 [1,2] ，[3,2] ，[2,4] 和 [3,5] 。
  它们中的最大美丽值为 5 。
- queries[4]=5 和 queries[5]=6 ，所有物品都符合条件。
  所以，答案为所有物品中的最大美丽值，为 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
<b>输出：</b>[4]
<b>解释：</b>
每个物品的价格均为 1 ，所以我们选择最大美丽值 4 。
注意，多个物品可能有相同的价格和美丽值。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>items = [[10,1000]], queries = [5]
<b>输出：</b>[0]
<strong>解释：</strong>
没有物品的价格小于等于 5 ，所以没有物品可以选择。
因此，查询的结果为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= items.length, queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>items[i].length == 2</code></li>
	<li><code>1 &lt;= price<sub>i</sub>, beauty<sub>i</sub>, queries[j] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
