# [163. Missing Ranges](https://leetcode.com/problems/missing-ranges)

[中文文档](/solution/0100-0199/0163.Missing%20Ranges/README.md)

## Description

<p>You are given an inclusive range <code>[lower, upper]</code> and a <strong>sorted unique</strong> integer array <code>nums</code>, where all elements are in the inclusive range.</p>

<p>A number <code>x</code> is considered <strong>missing</strong> if <code>x</code> is in the range <code>[lower, upper]</code> and <code>x</code> is not in <code>nums</code>.</p>

<p>Return <em>the <strong>smallest sorted</strong> list of ranges that <strong>cover every missing number exactly</strong></em>. That is, no element of <code>nums</code> is in any of the ranges, and each missing number is in one of the ranges.</p>

<p>Each range <code>[a,b]</code> in the list should be output as:</p>

<ul>
	<li><code>&quot;a-&gt;b&quot;</code> if <code>a != b</code></li>
	<li><code>&quot;a&quot;</code> if <code>a == b</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,3,50,75], lower = 0, upper = 99
<strong>Output:</strong> [&quot;2&quot;,&quot;4-&gt;49&quot;,&quot;51-&gt;74&quot;,&quot;76-&gt;99&quot;]
<strong>Explanation:</strong> The ranges are:
[2,2] --&gt; &quot;2&quot;
[4,49] --&gt; &quot;4-&gt;49&quot;
[51,74] --&gt; &quot;51-&gt;74&quot;
[76,99] --&gt; &quot;76-&gt;99&quot;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1], lower = -1, upper = -1
<strong>Output:</strong> []
<strong>Explanation:</strong> There are no missing ranges since there are no missing numbers.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-10<sup>9</sup> &lt;= lower &lt;= upper &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= nums.length &lt;= 100</code></li>
	<li><code>lower &lt;= nums[i] &lt;= upper</code></li>
	<li>All the values of <code>nums</code> are <strong>unique</strong>.</li>
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
