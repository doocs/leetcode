# [1415. The k-th Lexicographical String of All Happy Strings of Length n](https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n)

[中文文档](/solution/1400-1499/1415.The%20k-th%20Lexicographical%20String%20of%20All%20Happy%20Strings%20of%20Length%20n/README.md)

## Description

<p>A <strong>happy string</strong> is a string that:</p>

<ul>
	<li>consists only of letters of the set <code>[&#39;a&#39;, &#39;b&#39;, &#39;c&#39;]</code>.</li>
	<li><code>s[i] != s[i + 1]</code>&nbsp;for all values of <code>i</code> from <code>1</code> to <code>s.length - 1</code> (string is 1-indexed).</li>
</ul>

<p>For example, strings <strong>&quot;abc&quot;, &quot;ac&quot;, &quot;b&quot;</strong> and <strong>&quot;abcbabcbcb&quot;</strong> are all happy strings and strings <strong>&quot;aa&quot;, &quot;baa&quot;</strong> and&nbsp;<strong>&quot;ababbc&quot;</strong> are not happy strings.</p>

<p>Given two integers <code>n</code> and <code>k</code>, consider a list of all happy strings of length <code>n</code> sorted in lexicographical order.</p>

<p>Return <em>the kth string</em> of this list or return an <strong>empty string</strong>&nbsp;if there are less than <code>k</code> happy strings of length <code>n</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> n = 1, k = 3

<strong>Output:</strong> &quot;c&quot;

<strong>Explanation:</strong> The list [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;] contains all happy strings of length 1. The third string is &quot;c&quot;.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> n = 1, k = 4

<strong>Output:</strong> &quot;&quot;

<strong>Explanation:</strong> There are only 3 happy strings of length 1.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> n = 3, k = 9

<strong>Output:</strong> &quot;cab&quot;

<strong>Explanation:</strong> There are 12 different happy string of length 3 [&quot;aba&quot;, &quot;abc&quot;, &quot;aca&quot;, &quot;acb&quot;, &quot;bab&quot;, &quot;bac&quot;, &quot;bca&quot;, &quot;bcb&quot;, &quot;cab&quot;, &quot;cac&quot;, &quot;cba&quot;, &quot;cbc&quot;]. You will find the 9th string = &quot;cab&quot;

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> n = 2, k = 7

<strong>Output:</strong> &quot;&quot;

</pre>

<p><strong>Example 5:</strong></p>

<pre>

<strong>Input:</strong> n = 10, k = 100

<strong>Output:</strong> &quot;abacbabacb&quot;

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
</ul>

<div id="vidyowebrtcscreenshare_is_installed">&nbsp;</div>

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
