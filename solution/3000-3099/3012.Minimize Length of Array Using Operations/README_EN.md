# [3012. Minimize Length of Array Using Operations](https://leetcode.com/problems/minimize-length-of-array-using-operations)

[中文文档](/solution/3000-3099/3012.Minimize%20Length%20of%20Array%20Using%20Operations/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> containing <strong>positive</strong> integers.</p>

<p>Your task is to <strong>minimize</strong> the length of <code>nums</code> by performing the following operations <strong>any</strong> number of times (including zero):</p>

<ul>
	<li>Select <strong>two</strong> <strong>distinct</strong> indices <code>i</code> and <code>j</code> from <code>nums</code>, such that <code>nums[i] &gt; 0</code> and <code>nums[j] &gt; 0</code>.</li>
	<li>Insert the result of <code>nums[i] % nums[j]</code> at the end of <code>nums</code>.</li>
	<li>Delete the elements at indices <code>i</code> and <code>j</code> from <code>nums</code>.</li>
</ul>

<p>Return <em>an integer denoting the <strong>minimum</strong> <strong>length</strong> of </em><code>nums</code><em> after performing the operation any number of times.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,3,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> One way to minimize the length of the array is as follows:
Operation 1: Select indices 2 and 1, insert nums[2] % nums[1] at the end and it becomes [1,4,3,1,3], then delete elements at indices 2 and 1.
nums becomes [1,1,3].
Operation 2: Select indices 1 and 2, insert nums[1] % nums[2] at the end and it becomes [1,1,3,1], then delete elements at indices 1 and 2.
nums becomes [1,1].
Operation 3: Select indices 1 and 0, insert nums[1] % nums[0] at the end and it becomes [1,1,0], then delete elements at indices 1 and 0.
nums becomes [0].
The length of nums cannot be reduced further. Hence, the answer is 1.
It can be shown that 1 is the minimum achievable length. </pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,5,5,10,5]
<strong>Output:</strong> 2
<strong>Explanation:</strong> One way to minimize the length of the array is as follows:
Operation 1: Select indices 0 and 3, insert nums[0] % nums[3] at the end and it becomes [5,5,5,10,5,5], then delete elements at indices 0 and 3.
nums becomes [5,5,5,5]. 
Operation 2: Select indices 2 and 3, insert nums[2] % nums[3] at the end and it becomes [5,5,5,5,0], then delete elements at indices 2 and 3. 
nums becomes [5,5,0]. 
Operation 3: Select indices 0 and 1, insert nums[0] % nums[1] at the end and it becomes [5,5,0,0], then delete elements at indices 0 and 1.
nums becomes [0,0].
The length of nums cannot be reduced further. Hence, the answer is 2.
It can be shown that 2 is the minimum achievable length. </pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,4]
<strong>Output:</strong> 1
<strong>Explanation:</strong> One way to minimize the length of the array is as follows: 
Operation 1: Select indices 1 and 2, insert nums[1] % nums[2] at the end and it becomes [2,3,4,3], then delete elements at indices 1 and 2.
nums becomes [2,3].
Operation 2: Select indices 1 and 0, insert nums[1] % nums[0] at the end and it becomes [2,3,1], then delete elements at indices 1 and 0.
nums becomes [1].
The length of nums cannot be reduced further. Hence, the answer is 1.
It can be shown that 1 is the minimum achievable length.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
