# [3139. Minimum Cost to Equalize Array](https://leetcode.com/problems/minimum-cost-to-equalize-array)

[中文文档](/solution/3100-3199/3139.Minimum%20Cost%20to%20Equalize%20Array/README.md)

<!-- tags:Greedy,Array,Enumeration -->

<!-- difficulty:Hard -->

## Description

<p>You are given an integer array <code>nums</code> and two integers <code>cost1</code> and <code>cost2</code>. You are allowed to perform <strong>either</strong> of the following operations <strong>any</strong> number of times:</p>

<ul>
	<li>Choose an index <code>i</code> from <code>nums</code> and <strong>increase</strong> <code>nums[i]</code> by <code>1</code> for a cost of <code>cost1</code>.</li>
	<li>Choose two <strong>different</strong> indices <code>i</code>, <code>j</code>, from <code>nums</code> and <strong>increase</strong> <code>nums[i]</code> and <code>nums[j]</code> by <code>1</code> for a cost of <code>cost2</code>.</li>
</ul>

<p>Return the <strong>minimum</strong> <strong>cost</strong> required to make all elements in the array <strong>equal</strong><em>. </em></p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,1], cost1 = 5, cost2 = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation: </strong></p>

<p>The following operations can be performed to make the values equal:</p>

<ul>
	<li>Increase <code>nums[1]</code> by 1 for a cost of 5. <code>nums</code> becomes <code>[4,2]</code>.</li>
	<li>Increase <code>nums[1]</code> by 1 for a cost of 5. <code>nums</code> becomes <code>[4,3]</code>.</li>
	<li>Increase <code>nums[1]</code> by 1 for a cost of 5. <code>nums</code> becomes <code>[4,4]</code>.</li>
</ul>

<p>The total cost is 15.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,3,3,5], cost1 = 2, cost2 = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation: </strong></p>

<p>The following operations can be performed to make the values equal:</p>

<ul>
	<li>Increase <code>nums[0]</code> and <code>nums[1]</code> by 1 for a cost of 1. <code>nums</code> becomes <code>[3,4,3,3,5]</code>.</li>
	<li>Increase <code>nums[0]</code> and <code>nums[2]</code> by 1 for a cost of 1. <code>nums</code> becomes <code>[4,4,4,3,5]</code>.</li>
	<li>Increase <code>nums[0]</code> and <code>nums[3]</code> by 1 for a cost of 1. <code>nums</code> becomes <code>[5,4,4,4,5]</code>.</li>
	<li>Increase <code>nums[1]</code> and <code>nums[2]</code> by 1 for a cost of 1. <code>nums</code> becomes <code>[5,5,5,4,5]</code>.</li>
	<li>Increase <code>nums[3]</code> by 1 for a cost of 2. <code>nums</code> becomes <code>[5,5,5,5,5]</code>.</li>
</ul>

<p>The total cost is 6.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,5,3], cost1 = 1, cost2 = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The following operations can be performed to make the values equal:</p>

<ul>
	<li>Increase <code>nums[0]</code> by 1 for a cost of 1. <code>nums</code> becomes <code>[4,5,3]</code>.</li>
	<li>Increase <code>nums[0]</code> by 1 for a cost of 1. <code>nums</code> becomes <code>[5,5,3]</code>.</li>
	<li>Increase <code>nums[2]</code> by 1 for a cost of 1. <code>nums</code> becomes <code>[5,5,4]</code>.</li>
	<li>Increase <code>nums[2]</code> by 1 for a cost of 1. <code>nums</code> becomes <code>[5,5,5]</code>.</li>
</ul>

<p>The total cost is 4.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= cost1 &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= cost2 &lt;= 10<sup>6</sup></code></li>
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
