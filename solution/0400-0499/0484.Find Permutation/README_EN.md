# [484. Find Permutation](https://leetcode.com/problems/find-permutation)

[中文文档](/solution/0400-0499/0484.Find%20Permutation/README.md)

## Description

<p>A permutation <code>perm</code> of <code>n</code>&nbsp;integers of all the integers in the range <code>[1, n]</code> can be represented as a string <code>s</code> of length <code>n - 1</code> where:</p>

<ul>
	<li><code>s[i] == &#39;I&#39;</code> if <code>perm[i] &lt; perm[i + 1]</code>, and</li>
	<li><code>s[i] == &#39;D&#39;</code> if <code>perm[i] &gt; perm[i + 1]</code>.</li>
</ul>

<p>Given a string <code>s</code>, reconstruct the lexicographically smallest permutation <code>perm</code> and return it.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;I&quot;
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> [1,2] is the only legal permutation that can represented by s, where the number 1 and 2 construct an increasing relationship.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;DI&quot;
<strong>Output:</strong> [2,1,3]
<strong>Explanation:</strong> Both [2,1,3] and [3,1,2] can be represented as &quot;DI&quot;, but since we want to find the smallest lexicographical permutation, you should return [2,1,3]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
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
