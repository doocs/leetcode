# [1764. Form Array by Concatenating Subarrays of Another Array](https://leetcode.com/problems/form-array-by-concatenating-subarrays-of-another-array)

[中文文档](/solution/1700-1799/1764.Form%20Array%20by%20Concatenating%20Subarrays%20of%20Another%20Array/README.md)

## Description

<p>You are given a 2D integer array <code>groups</code> of length <code>n</code>. You are also given an integer array <code>nums</code>.</p>

<p>You are asked if you can choose <code>n</code> <strong>disjoint </strong>subarrays from the array <code>nums</code> such that the <code>i<sup>th</sup></code> subarray is equal to <code>groups[i]</code> (<b>0-indexed</b>), and if <code>i &gt; 0</code>, the <code>(i-1)<sup>th</sup></code> subarray appears <strong>before</strong> the <code>i<sup>th</sup></code> subarray in <code>nums</code> (i.e. the subarrays must be in the same order as <code>groups</code>).</p>

<p>Return <code>true</code> <em>if you can do this task, and</em> <code>false</code> <em>otherwise</em>.</p>

<p>Note that the subarrays are <strong>disjoint</strong> if and only if there is no index <code>k</code> such that <code>nums[k]</code> belongs to more than one subarray. A subarray is a contiguous sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> groups = [[1,-1,-1],[3,-2,0]], nums = [1,-1,0,1,-1,-1,3,-2,0]
<strong>Output:</strong> true
<strong>Explanation:</strong> You can choose the 0<sup>th</sup> subarray as [1,-1,0,<u><strong>1,-1,-1</strong></u>,3,-2,0] and the 1<sup>st</sup> one as [1,-1,0,1,-1,-1,<u><strong>3,-2,0</strong></u>].
These subarrays are disjoint as they share no common nums[k] element.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> groups = [[10,-2],[1,2,3,4]], nums = [1,2,3,4,10,-2]
<strong>Output:</strong> false
<strong>Explanation: </strong>Note that choosing the subarrays [<u><strong>1,2,3,4</strong></u>,10,-2] and [1,2,3,4,<u><strong>10,-2</strong></u>] is incorrect because they are not in the same order as in groups.
[10,-2] must come before [1,2,3,4].
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> groups = [[1,2,3],[3,4]], nums = [7,7,1,2,3,4,7,7]
<strong>Output:</strong> false
<strong>Explanation: </strong>Note that choosing the subarrays [7,7,<u><strong>1,2,3</strong></u>,4,7,7] and [7,7,1,2,<u><strong>3,4</strong></u>,7,7] is invalid because they are not disjoint.
They share a common elements nums[4] (0-indexed).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>groups.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= groups[i].length, sum(groups[i].length) &lt;= 10<sup><span style="font-size: 10.8333px;">3</span></sup></code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>3</sup></code></li>
	<li><code>-10<sup>7</sup> &lt;= groups[i][j], nums[k] &lt;= 10<sup>7</sup></code></li>
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
