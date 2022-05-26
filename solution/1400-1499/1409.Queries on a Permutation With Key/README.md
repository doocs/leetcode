# [1409. 查询带键的排列](https://leetcode-cn.com/problems/queries-on-a-permutation-with-key)

[English Version](/solution/1400-1499/1409.Queries%20on%20a%20Permutation%20With%20Key/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给你一个待查数组 <code>queries</code> ，数组中的元素为 <code>1</code> 到 <code>m</code> 之间的正整数。 请你根据以下规则处理所有待查项 <code>queries[i]</code>（从 <code>i=0</code> 到 <code>i=queries.length-1</code>）：</p>

<ul>
	<li>一开始，排列 <code>P=[1,2,3,...,m]</code>。</li>
	<li>对于当前的 <code>i</code> ，请你找出待查项 <code>queries[i]</code> 在排列 <code>P</code> 中的位置（<strong>下标从 0 开始</strong>），然后将其从原位置移动到排列 <code>P</code> 的起始位置（即下标为 0 处）。注意， <code>queries[i]</code> 在 <code>P</code> 中的位置就是 <code>queries[i]</code> 的查询结果。</li>
</ul>

<p>请你以数组形式返回待查数组&nbsp; <code>queries</code> 的查询结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>queries = [3,1,2,1], m = 5
<strong>输出：</strong>[2,1,2,1] 
<strong>解释：</strong>待查数组 queries 处理如下：
对于 i=0: queries[i]=3, P=[1,2,3,4,5], 3 在 P 中的位置是 <strong>2</strong>，接着我们把 3 移动到 P 的起始位置，得到 P=[3,1,2,4,5] 。
对于 i=1: queries[i]=1, P=[3,1,2,4,5], 1 在 P 中的位置是 <strong>1</strong>，接着我们把 1 移动到 P 的起始位置，得到 P=[1,3,2,4,5] 。 
对于 i=2: queries[i]=2, P=[1,3,2,4,5], 2 在 P 中的位置是 <strong>2</strong>，接着我们把 2 移动到 P 的起始位置，得到 P=[2,1,3,4,5] 。
对于 i=3: queries[i]=1, P=[2,1,3,4,5], 1 在 P 中的位置是 <strong>1</strong>，接着我们把 1 移动到 P 的起始位置，得到 P=[1,2,3,4,5] 。 
因此，返回的结果数组为 [2,1,2,1] 。  
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>queries = [4,1,2,2], m = 4
<strong>输出：</strong>[3,1,2,0]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>queries = [7,5,5,8,3], m = 8
<strong>输出：</strong>[6,5,0,7,5]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m &lt;= 10^3</code></li>
	<li><code>1 &lt;= queries.length &lt;= m</code></li>
	<li><code>1 &lt;= queries[i] &lt;= m</code></li>
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
