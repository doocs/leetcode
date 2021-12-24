# [903. DI 序列的有效排列](https://leetcode-cn.com/problems/valid-permutations-for-di-sequence)

[English Version](/solution/0900-0999/0903.Valid%20Permutations%20for%20DI%20Sequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们给出 <code>S</code>，一个源于&nbsp;<code>{&#39;D&#39;, &#39;I&#39;}</code>&nbsp;的长度为 <code>n</code>&nbsp;的字符串 。（这些字母代表 &ldquo;减少&rdquo; 和 &ldquo;增加&rdquo;。）<br>
<em>有效排列</em>&nbsp;是对整数 <code>{0, 1, ..., n}</code>&nbsp;的一个排列&nbsp;<code>P[0], P[1], ..., P[n]</code>，使得对所有的&nbsp;<code>i</code>：</p>

<ul>
	<li>如果 <code>S[i] == &#39;D&#39;</code>，那么&nbsp;<code>P[i] &gt; P[i+1]</code>，以及；</li>
	<li>如果 <code>S[i] == &#39;I&#39;</code>，那么 <code>P[i] &lt; P[i+1]</code>。</li>
</ul>

<p>有多少个有效排列？因为答案可能很大，所以请<strong>返回你的答案模</strong><strong> <code>10^9 + 7</code></strong>.</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>&quot;DID&quot;
<strong>输出：</strong>5
<strong>解释：</strong>
(0, 1, 2, 3) 的五个有效排列是：
(1, 0, 3, 2)
(2, 0, 3, 1)
(2, 1, 3, 0)
(3, 0, 2, 1)
(3, 1, 2, 0)
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= S.length &lt;= 200</code></li>
	<li><code>S</code> 仅由集合 <code>{&#39;D&#39;, &#39;I&#39;}</code>&nbsp;中的字符组成。</li>
</ol>

<p>&nbsp;</p>

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
