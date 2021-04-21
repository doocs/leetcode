# [228. Summary Ranges](https://leetcode.com/problems/summary-ranges)

[中文文档](/solution/0200-0299/0228.Summary%20Ranges/README.md)

## Description

<p>You are given a <strong>sorted unique</strong> integer array <code>nums</code>.</p>

<p>Return <em>the <strong>smallest sorted</strong> list of ranges that <strong>cover all the numbers in the array exactly</strong></em>. That is, each element of <code>nums</code> is covered by exactly one of the ranges, and there is no integer <code>x</code> such that <code>x</code> is in one of the ranges but not in <code>nums</code>.</p>

<p>Each range <code>[a,b]</code> in the list should be output as:</p>

<ul>
	<li><code>&quot;a-&gt;b&quot;</code> if <code>a != b</code></li>
	<li><code>&quot;a&quot;</code> if <code>a == b</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,2,4,5,7]
<strong>Output:</strong> [&quot;0-&gt;2&quot;,&quot;4-&gt;5&quot;,&quot;7&quot;]
<strong>Explanation:</strong> The ranges are:
[0,2] --&gt; &quot;0-&gt;2&quot;
[4,5] --&gt; &quot;4-&gt;5&quot;
[7,7] --&gt; &quot;7&quot;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,2,3,4,6,8,9]
<strong>Output:</strong> [&quot;0&quot;,&quot;2-&gt;4&quot;,&quot;6&quot;,&quot;8-&gt;9&quot;]
<strong>Explanation:</strong> The ranges are:
[0,0] --&gt; &quot;0&quot;
[2,4] --&gt; &quot;2-&gt;4&quot;
[6,6] --&gt; &quot;6&quot;
[8,9] --&gt; &quot;8-&gt;9&quot;
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = []
<strong>Output:</strong> []
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1]
<strong>Output:</strong> [&quot;-1&quot;]
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> nums = [0]
<strong>Output:</strong> [&quot;0&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 20</code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li>All the values of <code>nums</code> are <strong>unique</strong>.</li>
	<li><code>nums</code> is sorted in ascending order.</li>
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
