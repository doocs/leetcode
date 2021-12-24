# [903. Valid Permutations for DI Sequence](https://leetcode.com/problems/valid-permutations-for-di-sequence)

[中文文档](/solution/0900-0999/0903.Valid%20Permutations%20for%20DI%20Sequence/README.md)

## Description

<p>We are given <code>S</code>, a length <code>n</code> string of characters from the set <code>{&#39;D&#39;, &#39;I&#39;}</code>. (These letters stand for &quot;decreasing&quot; and &quot;increasing&quot;.)</p>

<p>A&nbsp;<em>valid permutation</em>&nbsp;is a permutation <code>P[0], P[1], ..., P[n]</code> of integers&nbsp;<code>{0, 1, ..., n}</code>, such that for all <code>i</code>:</p>

<ul>
	<li>If <code>S[i] == &#39;D&#39;</code>, then <code>P[i] &gt; P[i+1]</code>, and;</li>
	<li>If <code>S[i] == &#39;I&#39;</code>, then <code>P[i] &lt; P[i+1]</code>.</li>
</ul>

<p>How many valid permutations are there?&nbsp; Since the answer may be large, <strong>return your answer modulo <code>10^9 + 7</code></strong>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">&quot;DID&quot;</span>

<strong>Output: </strong><span id="example-output-1">5</span>

<strong>Explanation: </strong>

The 5 valid permutations of (0, 1, 2, 3) are:

(1, 0, 3, 2)

(2, 0, 3, 1)

(2, 1, 3, 0)

(3, 0, 2, 1)

(3, 1, 2, 0)

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= S.length &lt;= 200</code></li>
	<li><code>S</code> consists only of characters from the set <code>{&#39;D&#39;, &#39;I&#39;}</code>.</li>
</ol>

<div>

<p>&nbsp;</p>

</div>

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
