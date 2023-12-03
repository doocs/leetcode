# [2953. Count Complete Substrings](https://leetcode.com/problems/count-complete-substrings)

[中文文档](/solution/2900-2999/2953.Count%20Complete%20Substrings/README.md)

## Description

<p>You are given a string <code>word</code> and an integer <code>k</code>.</p>

<p>A substring <code>s</code> of <code>word</code> is <strong>complete</strong> if:</p>

<ul>
	<li>Each character in <code>s</code> occurs <strong>exactly</strong> <code>k</code> times.</li>
	<li>The difference between two adjacent characters is <strong>at most</strong> <code>2</code>. That is, for any two adjacent characters <code>c1</code> and <code>c2</code> in <code>s</code>, the absolute difference in their positions in the alphabet is <strong>at most</strong> <code>2</code>.</li>
</ul>

<p>Return <em>the number of <strong>complete </strong>substrings of</em> <code>word</code>.</p>

<p>A <strong>substring</strong> is a <strong>non-empty</strong> contiguous sequence of characters in a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;igigee&quot;, k = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> The complete substrings where each character appears exactly twice and the difference between adjacent characters is at most 2 are: <u><strong>igig</strong></u>ee, igig<u><strong>ee</strong></u>, <u><strong>igigee</strong></u>.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;aaabbbccc&quot;, k = 3
<strong>Output:</strong> 6
<strong>Explanation:</strong> The complete substrings where each character appears exactly three times and the difference between adjacent characters is at most 2 are: <strong><u>aaa</u></strong>bbbccc, aaa<u><strong>bbb</strong></u>ccc, aaabbb<u><strong>ccc</strong></u>, <strong><u>aaabbb</u></strong>ccc, aaa<u><strong>bbbccc</strong></u>, <u><strong>aaabbbccc</strong></u>.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 10<sup>5</sup></code></li>
	<li><code>word</code> consists only of lowercase English letters.</li>
	<li><code>1 &lt;= k &lt;= word.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
