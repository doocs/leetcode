# [1625. Lexicographically Smallest String After Applying Operations](https://leetcode.com/problems/lexicographically-smallest-string-after-applying-operations)

[中文文档](/solution/1600-1699/1625.Lexicographically%20Smallest%20String%20After%20Applying%20Operations/README.md)

## Description

<p>You are given a string <code>s</code> of <strong>even length</strong> consisting of digits from <code>0</code> to <code>9</code>, and two integers <code>a</code> and <code>b</code>.</p>

<p>You can apply either of the following two operations any number of times and in any order on <code>s</code>:</p>

<ul>
	<li>Add <code>a</code> to all odd indices of <code>s</code> <strong>(0-indexed)</strong>. Digits post <code>9</code> are cycled back to <code>0</code>. For example, if <code>s = &quot;3456&quot;</code> and <code>a = 5</code>, <code>s</code> becomes <code>&quot;3951&quot;</code>.</li>
	<li>Rotate <code>s</code> to the right by <code>b</code> positions. For example, if <code>s = &quot;3456&quot;</code> and <code>b = 1</code>, <code>s</code> becomes <code>&quot;6345&quot;</code>.</li>
</ul>

<p>Return <em>the <strong>lexicographically smallest</strong> string you can obtain by applying the above operations any number of times on</em> <code>s</code>.</p>

<p>A string <code>a</code> is lexicographically smaller than a string <code>b</code> (of the same length) if in the first position where <code>a</code> and <code>b</code> differ, string <code>a</code> has a letter that appears earlier in the alphabet than the corresponding letter in <code>b</code>. For example, <code>&quot;0158&quot;</code> is lexicographically smaller than <code>&quot;0190&quot;</code> because the first position they differ is at the third letter, and <code>&#39;5&#39;</code> comes before <code>&#39;9&#39;</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> s = &quot;5525&quot;, a = 9, b = 2

<strong>Output:</strong> &quot;2050&quot;

<strong>Explanation: </strong>We can apply the following operations:

Start:  &quot;5525&quot;

Rotate: &quot;2555&quot;

Add:    &quot;2454&quot;

Add:    &quot;2353&quot;

Rotate: &quot;5323&quot;

Add:    &quot;5222&quot;

​​​​​​​Add:    &quot;5121&quot;

​​​​​​​Rotate: &quot;2151&quot;

​​​​​​​Add:    &quot;2050&quot;​​​​​​​​​​​​

There is no way to obtain a string that is lexicographically smaller then &quot;2050&quot;.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> s = &quot;74&quot;, a = 5, b = 1

<strong>Output:</strong> &quot;24&quot;

<strong>Explanation: </strong>We can apply the following operations:

Start:  &quot;74&quot;

Rotate: &quot;47&quot;

​​​​​​​Add:    &quot;42&quot;

​​​​​​​Rotate: &quot;24&quot;​​​​​​​​​​​​

There is no way to obtain a string that is lexicographically smaller then &quot;24&quot;.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> s = &quot;0011&quot;, a = 4, b = 2

<strong>Output:</strong> &quot;0011&quot;

<strong>Explanation: </strong>There are no sequence of operations that will give us a lexicographically smaller string than &quot;0011&quot;.

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> s = &quot;43987654&quot;, a = 7, b = 3

<strong>Output:</strong> &quot;00553311&quot;

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s.length</code> is even.</li>
	<li><code>s</code> consists of digits from <code>0</code> to <code>9</code> only.</li>
	<li><code>1 &lt;= a &lt;= 9</code></li>
	<li><code>1 &lt;= b &lt;= s.length - 1</code></li>
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
