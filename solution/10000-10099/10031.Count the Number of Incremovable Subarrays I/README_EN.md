# [10031. Count the Number of Incremovable Subarrays I](https://leetcode.com/problems/count-the-number-of-incremovable-subarrays-i)

[中文文档](/solution/10000-10099/10031.Count%20the%20Number%20of%20Incremovable%20Subarrays%20I/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of <strong>positive</strong> integers <code>nums</code>.</p>

<p>A subarray of <code>nums</code> is called <strong>incremovable</strong> if <code>nums</code> becomes <strong>strictly increasing</strong> on removing the subarray. For example, the subarray <code>[3, 4]</code> is an incremovable subarray of <code>[5, 3, 4, 6, 7]</code> because removing this subarray changes the array <code>[5, 3, 4, 6, 7]</code> to <code>[5, 6, 7]</code> which is strictly increasing.</p>

<p>Return <em>the total number of <strong>incremovable</strong> subarrays of</em> <code>nums</code>.</p>

<p><strong>Note</strong> that an empty array is considered strictly increasing.</p>

<p>A <strong>subarray</strong> is a contiguous non-empty sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> 10
<strong>Explanation:</strong> The 10 incremovable subarrays are: [1], [2], [3], [4], [1,2], [2,3], [3,4], [1,2,3], [2,3,4], and [1,2,3,4], because on removing any one of these subarrays nums becomes strictly increasing. Note that you cannot select an empty subarray.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,5,7,8]
<strong>Output:</strong> 7
<strong>Explanation:</strong> The 7 incremovable subarrays are: [5], [6], [5,7], [6,5], [5,7,8], [6,5,7] and [6,5,7,8].
It can be shown that there are only 7 incremovable subarrays in nums.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [8,7,6,6]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The 3 incremovable subarrays are: [8,7,6], [7,6,6], and [8,7,6,6]. Note that [8,7] is not an incremovable subarray because after removing [8,7] nums becomes [6,6], which is sorted in ascending order but not strictly increasing.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
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
