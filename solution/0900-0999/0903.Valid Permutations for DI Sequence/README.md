# [903. DI 序列的有效排列](https://leetcode.cn/problems/valid-permutations-for-di-sequence)

[English Version](/solution/0900-0999/0903.Valid%20Permutations%20for%20DI%20Sequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个长度为 <code>n</code> 的字符串 <code>s</code> ，其中 <code>s[i]</code> 是:</p>

<ul>
	<li><code>“D”</code> 意味着减少，或者</li>
	<li><code>“I”</code> 意味着增加</li>
</ul>

<p><strong>有效排列</strong>&nbsp;是对有&nbsp;<code>n + 1</code>&nbsp;个在&nbsp;<code>[0, n]</code>&nbsp; 范围内的整数的一个排列&nbsp;<code>perm</code>&nbsp;，使得对所有的&nbsp;<code>i</code>：</p>

<ul>
	<li>如果 <code>s[i] == 'D'</code>，那么&nbsp;<code>perm[i] &gt; perm[i+1]</code>，以及；</li>
	<li>如果 <code>s[i] == 'I'</code>，那么 <code>perm[i] &lt; perm[i+1]</code>。</li>
</ul>

<p>返回 <em><strong>有效排列 </strong>&nbsp;</em><code>perm</code><em>的数量 </em>。因为答案可能很大，所以请<strong>返回你的答案对</strong>&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code><strong>&nbsp;取余</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "DID"
<strong>输出：</strong>5
<strong>解释：</strong>
(0, 1, 2, 3) 的五个有效排列是：
(1, 0, 3, 2)
(2, 0, 3, 1)
(2, 1, 3, 0)
(3, 0, 2, 1)
(3, 1, 2, 0)
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "D"
<strong>输出:</strong> 1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == s.length</code></li>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>s[i]</code>&nbsp;不是&nbsp;<code>'I'</code>&nbsp;就是&nbsp;<code>'D'</code></li>
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
