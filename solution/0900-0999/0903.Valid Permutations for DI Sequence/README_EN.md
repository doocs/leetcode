# [903. Valid Permutations for DI Sequence](https://leetcode.com/problems/valid-permutations-for-di-sequence)

[中文文档](/solution/0900-0999/0903.Valid%20Permutations%20for%20DI%20Sequence/README.md)

## Description

<p>You are given a string <code>s</code> of length <code>n</code> where <code>s[i]</code> is either:</p>

<ul>
	<li><code>&#39;D&#39;</code> means decreasing, or</li>
	<li><code>&#39;I&#39;</code> means increasing.</li>
</ul>

<p>A permutation <code>perm</code> of <code>n + 1</code> integers of all the integers in the range <code>[0, n]</code> is called a <strong>valid permutation</strong> if for all valid <code>i</code>:</p>

<ul>
	<li>If <code>s[i] == &#39;D&#39;</code>, then <code>perm[i] &gt; perm[i + 1]</code>, and</li>
	<li>If <code>s[i] == &#39;I&#39;</code>, then <code>perm[i] &lt; perm[i + 1]</code>.</li>
</ul>

<p>Return <em>the number of <strong>valid permutations</strong> </em><code>perm</code>. Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;DID&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The 5 valid permutations of (0, 1, 2, 3) are:
(1, 0, 3, 2)
(2, 0, 3, 1)
(2, 1, 3, 0)
(3, 0, 2, 1)
(3, 1, 2, 0)
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;D&quot;
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == s.length</code></li>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>s[i]</code> is either <code>&#39;I&#39;</code> or <code>&#39;D&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
