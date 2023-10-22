# [2908. Minimum Sum of Mountain Triplets I](https://leetcode.com/problems/minimum-sum-of-mountain-triplets-i)

[中文文档](/solution/2900-2999/2908.Minimum%20Sum%20of%20Mountain%20Triplets%20I/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> of integers.</p>

<p>A triplet of indices <code>(i, j, k)</code> is a <strong>mountain</strong> if:</p>

<ul>
	<li><code>i &lt; j &lt; k</code></li>
	<li><code>nums[i] &lt; nums[j]</code> and <code>nums[k] &lt; nums[j]</code></li>
</ul>

<p>Return <em>the <strong>minimum possible sum</strong> of a mountain triplet of</em> <code>nums</code>. <em>If no such triplet exists, return</em> <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [8,6,1,5,3]
<strong>Output:</strong> 9
<strong>Explanation:</strong> Triplet (2, 3, 4) is a mountain triplet of sum 9 since: 
- 2 &lt; 3 &lt; 4
- nums[2] &lt; nums[3] and nums[4] &lt; nums[3]
And the sum of this triplet is nums[2] + nums[3] + nums[4] = 9. It can be shown that there are no mountain triplets with a sum of less than 9.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,4,8,7,10,2]
<strong>Output:</strong> 13
<strong>Explanation:</strong> Triplet (1, 3, 5) is a mountain triplet of sum 13 since: 
- 1 &lt; 3 &lt; 5
- nums[1] &lt; nums[3] and nums[5] &lt; nums[3]
And the sum of this triplet is nums[1] + nums[3] + nums[5] = 13. It can be shown that there are no mountain triplets with a sum of less than 13.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,5,4,3,4,5]
<strong>Output:</strong> -1
<strong>Explanation:</strong> It can be shown that there are no mountain triplets in nums.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 50</code></li>
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
