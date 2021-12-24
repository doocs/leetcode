# [1959. Minimum Total Space Wasted With K Resizing Operations](https://leetcode.com/problems/minimum-total-space-wasted-with-k-resizing-operations)

[中文文档](/solution/1900-1999/1959.Minimum%20Total%20Space%20Wasted%20With%20K%20Resizing%20Operations/README.md)

## Description

<p>You are currently designing a dynamic array. You are given a <strong>0-indexed</strong> integer array <code>nums</code>, where <code>nums[i]</code> is the number of elements that will be in the array at time <code>i</code>. In addition, you are given an integer <code>k</code>, the <strong>maximum</strong> number of times you can <strong>resize</strong> the array (to<strong> any</strong> size).</p>

<p>The size of the array at time <code>t</code>, <code>size<sub>t</sub></code>, must be at least <code>nums[t]</code> because there needs to be enough space in the array to hold all the elements. The <strong>space wasted</strong> at&nbsp;time <code>t</code> is defined as <code>size<sub>t</sub> - nums[t]</code>, and the <strong>total</strong> space wasted is the <strong>sum</strong> of the space wasted across every time <code>t</code> where <code>0 &lt;= t &lt; nums.length</code>.</p>

<p>Return <em>the <strong>minimum</strong> <strong>total space wasted</strong> if you can resize the array at most</em> <code>k</code> <em>times</em>.</p>

<p><strong>Note:</strong> The array can have <strong>any size</strong> at the start and does<strong> not </strong>count towards the number of resizing operations.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,20], k = 0
<strong>Output:</strong> 10
<strong>Explanation:</strong> size = [20,20].
We can set the initial size to be 20.
The total wasted space is (20 - 10) + (20 - 20) = 10.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,20,30], k = 1
<strong>Output:</strong> 10
<strong>Explanation:</strong> size = [20,20,30].
We can set the initial size to be 20 and resize to 30 at time 2. 
The total wasted space is (20 - 10) + (20 - 20) + (30 - 30) = 10.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,20,15,30,20], k = 2
<strong>Output:</strong> 15
<strong>Explanation:</strong> size = [10,20,20,30,30].
We can set the initial size to 10, resize to 20 at time 1, and resize to 30 at time 3.
The total wasted space is (10 - 10) + (20 - 20) + (20 - 15) + (30 - 30) + (30 - 20) = 15.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>0 &lt;= k &lt;= nums.length - 1</code></li>
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
