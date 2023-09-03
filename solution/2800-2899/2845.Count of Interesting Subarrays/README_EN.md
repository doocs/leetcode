# [2845. Count of Interesting Subarrays](https://leetcode.com/problems/count-of-interesting-subarrays)

[中文文档](/solution/2800-2899/2845.Count%20of%20Interesting%20Subarrays/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>, an integer <code>modulo</code>, and an integer <code>k</code>.</p>

<p>Your task is to find the count of subarrays that are <strong>interesting</strong>.</p>

<p>A <strong>subarray</strong> <code>nums[l..r]</code> is <strong>interesting</strong> if the following condition holds:</p>

<ul>
	<li>Let <code>cnt</code> be the number of indices <code>i</code> in the range <code>[l, r]</code> such that <code>nums[i] % modulo == k</code>. Then, <code>cnt % modulo == k</code>.</li>
</ul>

<p>Return <em>an integer denoting the count of interesting subarrays. </em></p>

<p><span><strong>Note:</strong> A subarray is <em>a contiguous non-empty sequence of elements within an array</em>.</span></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,4], modulo = 2, k = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong> In this example the interesting subarrays are: 
The subarray nums[0..0] which is [3]. 
- There is only one index, i = 0, in the range [0, 0] that satisfies nums[i] % modulo == k. 
- Hence, cnt = 1 and cnt % modulo == k.  
The subarray nums[0..1] which is [3,2].
- There is only one index, i = 0, in the range [0, 1] that satisfies nums[i] % modulo == k.  
- Hence, cnt = 1 and cnt % modulo == k.
The subarray nums[0..2] which is [3,2,4]. 
- There is only one index, i = 0, in the range [0, 2] that satisfies nums[i] % modulo == k. 
- Hence, cnt = 1 and cnt % modulo == k. 
It can be shown that there are no other interesting subarrays. So, the answer is 3.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,9,6], modulo = 3, k = 0
<strong>Output:</strong> 2
<strong>Explanation: </strong>In this example the interesting subarrays are: 
The subarray nums[0..3] which is [3,1,9,6]. 
- There are three indices, i = 0, 2, 3, in the range [0, 3] that satisfy nums[i] % modulo == k. 
- Hence, cnt = 3 and cnt % modulo == k. 
The subarray nums[1..1] which is [1]. 
- There is no index, i, in the range [1, 1] that satisfies nums[i] % modulo == k. 
- Hence, cnt = 0 and cnt % modulo == k. 
It can be shown that there are no other interesting subarrays. So, the answer is 2.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5 </sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= modulo &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt; modulo</code></li>
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
