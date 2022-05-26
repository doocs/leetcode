# [1427. Perform String Shifts](https://leetcode.com/problems/perform-string-shifts)

[中文文档](/solution/1400-1499/1427.Perform%20String%20Shifts/README.md)

## Description

<p>You are given a string <code>s</code> containing lowercase English letters, and a matrix <code>shift</code>, where <code>shift[i] = [direction<sub>i</sub>, amount<sub>i</sub>]</code>:</p>

<ul>
	<li><code>direction<sub>i</sub></code> can be <code>0</code> (for left shift) or <code>1</code> (for right shift).</li>
	<li><code>amount<sub>i</sub></code> is the amount by which string <code>s</code> is to be shifted.</li>
	<li>A left shift by 1 means remove the first character of <code>s</code> and append it to the end.</li>
	<li>Similarly, a right shift by 1 means remove the last character of <code>s</code> and add it to the beginning.</li>
</ul>

<p>Return the final string after all operations.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc&quot;, shift = [[0,1],[1,2]]
<strong>Output:</strong> &quot;cab&quot;
<strong>Explanation:</strong>&nbsp;
[0,1] means shift to left by 1. &quot;abc&quot; -&gt; &quot;bca&quot;
[1,2] means shift to right by 2. &quot;bca&quot; -&gt; &quot;cab&quot;</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcdefg&quot;, shift = [[1,1],[1,1],[0,2],[1,3]]
<strong>Output:</strong> &quot;efgabcd&quot;
<strong>Explanation:</strong>&nbsp; 
[1,1] means shift to right by 1. &quot;abcdefg&quot; -&gt; &quot;gabcdef&quot;
[1,1] means shift to right by 1. &quot;gabcdef&quot; -&gt; &quot;fgabcde&quot;
[0,2] means shift to left by 2. &quot;fgabcde&quot; -&gt; &quot;abcdefg&quot;
[1,3] means shift to right by 3. &quot;abcdefg&quot; -&gt; &quot;efgabcd&quot;</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> only contains lower case English letters.</li>
	<li><code>1 &lt;= shift.length &lt;= 100</code></li>
	<li><code>shift[i].length == 2</code></li>
	<li><code>direction<sub>i</sub></code><sub> </sub>is either <code>0</code> or <code>1</code>.</li>
	<li><code>0 &lt;= amount<sub>i</sub> &lt;= 100</code></li>
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
