# [1830. Minimum Number of Operations to Make String Sorted](https://leetcode.com/problems/minimum-number-of-operations-to-make-string-sorted)

[中文文档](/solution/1800-1899/1830.Minimum%20Number%20of%20Operations%20to%20Make%20String%20Sorted/README.md)

## Description

<p>You are given a string <code>s</code> (<strong>0-indexed</strong>)​​​​​​. You are asked to perform the following operation on <code>s</code>​​​​​​ until you get a sorted string:</p>

<ol>
	<li>Find <strong>the largest index</strong> <code>i</code> such that <code>1 &lt;= i &lt; s.length</code> and <code>s[i] &lt; s[i - 1]</code>.</li>
	<li>Find <strong>the largest index</strong> <code>j</code> such that <code>i &lt;= j &lt; s.length</code> and <code>s[k] &lt; s[i - 1]</code> for all the possible values of <code>k</code> in the range <code>[i, j]</code> inclusive.</li>
	<li>Swap the two characters at indices <code>i - 1</code>​​​​ and <code>j</code>​​​​​.</li>
	<li>Reverse the suffix starting at index <code>i</code>​​​​​​.</li>
</ol>

<p>Return <em>the number of operations needed to make the string sorted.</em> Since the answer can be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cba&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The simulation goes as follows:
Operation 1: i=2, j=2. Swap s[1] and s[2] to get s=&quot;cab&quot;, then reverse the suffix starting at 2. Now, s=&quot;cab&quot;.
Operation 2: i=1, j=2. Swap s[0] and s[2] to get s=&quot;bac&quot;, then reverse the suffix starting at 1. Now, s=&quot;bca&quot;.
Operation 3: i=2, j=2. Swap s[1] and s[2] to get s=&quot;bac&quot;, then reverse the suffix starting at 2. Now, s=&quot;bac&quot;.
Operation 4: i=1, j=1. Swap s[0] and s[1] to get s=&quot;abc&quot;, then reverse the suffix starting at 1. Now, s=&quot;acb&quot;.
Operation 5: i=2, j=2. Swap s[1] and s[2] to get s=&quot;abc&quot;, then reverse the suffix starting at 2. Now, s=&quot;abc&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabaa&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The simulation goes as follows:
Operation 1: i=3, j=4. Swap s[2] and s[4] to get s=&quot;aaaab&quot;, then reverse the substring starting at 3. Now, s=&quot;aaaba&quot;.
Operation 2: i=4, j=4. Swap s[3] and s[4] to get s=&quot;aaaab&quot;, then reverse the substring starting at 4. Now, s=&quot;aaaab&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3000</code></li>
	<li><code>s</code>​​​​​​ consists only of lowercase English letters.</li>
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
