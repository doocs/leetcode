---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3927.Minimize%20Array%20Sum%20Using%20Divisible%20Replacements/README_EN.md
rating: 1651
source: Weekly Contest 501 Q3
---

<!-- problem:start -->

# [3927. Minimize Array Sum Using Divisible Replacements](https://leetcode.com/problems/minimize-array-sum-using-divisible-replacements)

[中文文档](/solution/3900-3999/3927.Minimize%20Array%20Sum%20Using%20Divisible%20Replacements/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>You can perform the following operation any number of times:</p>

<ul>
	<li>Choose two indices <code>a</code> and <code>b</code> such that <code>nums[a] % nums[b] == 0</code>.</li>
	<li>Replace <code>nums[a]</code> with <code>nums[b]</code>.</li>
</ul>

<p>Return the <strong>minimum</strong> possible sum of the array after performing any number of operations.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,6,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose <code>a = 1</code>, <code>b = 2</code>, where <code>nums[a] = 6</code> and <code>nums[b] = 2</code>. Since <code>6 % 2 == 0</code>, replace <code>nums[1]</code> with <code>nums[2]</code>.</li>
	<li>The array becomes <code>[3, 2, 2]</code>.</li>
	<li>No further operation reduces the sum. Thus, the final sum is <code>3 + 2 + 2 = 7</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,2,8,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose <code>a = 0</code>, <code>b = 1</code>, where <code>nums[a] = 4</code> and <code>nums[b] = 2</code>. Since <code>4 % 2 == 0</code>, replace <code>nums[0]</code> with <code>nums[1]</code>.</li>
	<li>Choose <code>a = 2</code>, <code>b = 1</code>, where <code>nums[a] = 8</code> and <code>nums[b] = 2</code>. Since <code>8 % 2 == 0</code>, replace <code>nums[2]</code> with <code>nums[1]</code>.</li>
	<li>The array becomes <code>[2, 2, 2, 3]</code>.</li>
	<li>No further operation reduces the sum. Thus, the final sum is <code>2 + 2 + 2 + 3 = 9</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,5,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">21</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>There is no pair <code>(a, b)</code> such that <code>nums[a] % nums[b] == 0</code>.</li>
	<li>Hence, no operation can be performed. The sum remains <code>7 + 5 + 9 = 21</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>​​​​​​​5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
