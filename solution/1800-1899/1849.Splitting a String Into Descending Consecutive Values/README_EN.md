# [1849. Splitting a String Into Descending Consecutive Values](https://leetcode.com/problems/splitting-a-string-into-descending-consecutive-values)

[中文文档](/solution/1800-1899/1849.Splitting%20a%20String%20Into%20Descending%20Consecutive%20Values/README.md)

## Description

<p>You are given a string <code>s</code> that consists of only digits.</p>

<p>Check if we can split <code>s</code> into <strong>two or more non-empty substrings</strong> such that the <strong>numerical values</strong> of the substrings are in <strong>descending order</strong> and the <strong>difference</strong> between numerical values of every two <strong>adjacent</strong> <strong>substrings</strong> is equal to <code>1</code>.</p>

<ul>
	<li>For example, the string <code>s = &quot;0090089&quot;</code> can be split into <code>[&quot;0090&quot;, &quot;089&quot;]</code> with numerical values <code>[90,89]</code>. The values are in descending order and adjacent values differ by <code>1</code>, so this way is valid.</li>
	<li>Another example, the string <code>s = &quot;001&quot;</code> can be split into <code>[&quot;0&quot;, &quot;01&quot;]</code>, <code>[&quot;00&quot;, &quot;1&quot;]</code>, or <code>[&quot;0&quot;, &quot;0&quot;, &quot;1&quot;]</code>. However all the ways are invalid because they have numerical values <code>[0,1]</code>, <code>[0,1]</code>, and <code>[0,0,1]</code> respectively, all of which are not in descending order.</li>
</ul>

<p>Return <code>true</code> <em>if it is possible to split</em> <code>s</code>​​​​​​ <em>as described above</em><em>, or </em><code>false</code><em> otherwise.</em></p>

<p>A <strong>substring</strong> is a contiguous sequence of characters in a string.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1234&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no valid way to split s.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;050043&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> s can be split into [&quot;05&quot;, &quot;004&quot;, &quot;3&quot;] with numerical values [5,4,3].
The values are in descending order with adjacent values differing by 1.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;9080701&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no valid way to split s.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 20</code></li>
	<li><code>s</code> only consists of digits.</li>
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
