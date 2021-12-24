# [1558. Minimum Numbers of Function Calls to Make Target Array](https://leetcode.com/problems/minimum-numbers-of-function-calls-to-make-target-array)

[中文文档](/solution/1500-1599/1558.Minimum%20Numbers%20of%20Function%20Calls%20to%20Make%20Target%20Array/README.md)

## Description

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1558.Minimum%20Numbers%20of%20Function%20Calls%20to%20Make%20Target%20Array/images/sample_2_1887.png" style="width: 573px; height: 294px;" /></p>

<p>Your task is to form&nbsp;an integer array <code>nums</code> from an initial array of zeros&nbsp;<code>arr</code> that is the&nbsp;same size&nbsp;as <code>nums</code>.</p>

<p>Return the minimum number of&nbsp;function calls to make <code>nums</code> from <code>arr</code>.</p>

<p>The answer is guaranteed to fit in a 32-bit signed integer.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5]
<strong>Output:</strong> 5
<strong>Explanation:</strong> Increment by 1 (second element): [0, 0] to get [0, 1] (1 operation).
Double all the elements: [0, 1] -&gt; [0, 2] -&gt; [0, 4] (2 operations).
Increment by 1 (both elements)  [0, 4] -&gt; [1, 4] -&gt; <strong>[1, 5]</strong> (2 operations).
Total of operations: 1 + 2 + 2 = 5.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Increment by 1 (both elements) [0, 0] -&gt; [0, 1] -&gt; [1, 1] (2 operations).
Double all the elements: [1, 1] -&gt; <strong>[2, 2]</strong> (1 operation).
Total of operations: 2 + 1 = 3.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,2,5]
<strong>Output:</strong> 6
<strong>Explanation:</strong> (initial)[0,0,0] -&gt; [1,0,0] -&gt; [1,0,1] -&gt; [2,0,2] -&gt; [2,1,2] -&gt; [4,2,4] -&gt; <strong>[4,2,5]</strong>(nums).
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,2,4]
<strong>Output:</strong> 7
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,4,8,16]
<strong>Output:</strong> 8
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10^5</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10^9</code></li>
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
