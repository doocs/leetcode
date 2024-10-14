---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3315.Construct%20the%20Minimum%20Bitwise%20Array%20II/README_EN.md
---

<!-- problem:start -->

# [3315. Construct the Minimum Bitwise Array II](https://leetcode.com/problems/construct-the-minimum-bitwise-array-ii)

[中文文档](/solution/3300-3399/3315.Construct%20the%20Minimum%20Bitwise%20Array%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> consisting of <code>n</code> <span data-keyword="prime-number">prime</span> integers.</p>

<p>You need to construct an array <code>ans</code> of length <code>n</code>, such that, for each index <code>i</code>, the bitwise <code>OR</code> of <code>ans[i]</code> and <code>ans[i] + 1</code> is equal to <code>nums[i]</code>, i.e. <code>ans[i] OR (ans[i] + 1) == nums[i]</code>.</p>

<p>Additionally, you must <strong>minimize</strong> each value of <code>ans[i]</code> in the resulting array.</p>

<p>If it is <em>not possible</em> to find such a value for <code>ans[i]</code> that satisfies the <strong>condition</strong>, then set <code>ans[i] = -1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,5,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,1,4,3]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>i = 0</code>, as there is no value for <code>ans[0]</code> that satisfies <code>ans[0] OR (ans[0] + 1) = 2</code>, so <code>ans[0] = -1</code>.</li>
	<li>For <code>i = 1</code>, the smallest <code>ans[1]</code> that satisfies <code>ans[1] OR (ans[1] + 1) = 3</code> is <code>1</code>, because <code>1 OR (1 + 1) = 3</code>.</li>
	<li>For <code>i = 2</code>, the smallest <code>ans[2]</code> that satisfies <code>ans[2] OR (ans[2] + 1) = 5</code> is <code>4</code>, because <code>4 OR (4 + 1) = 5</code>.</li>
	<li>For <code>i = 3</code>, the smallest <code>ans[3]</code> that satisfies <code>ans[3] OR (ans[3] + 1) = 7</code> is <code>3</code>, because <code>3 OR (3 + 1) = 7</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [11,13,31]</span></p>

<p><strong>Output:</strong> <span class="example-io">[9,12,15]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>i = 0</code>, the smallest <code>ans[0]</code> that satisfies <code>ans[0] OR (ans[0] + 1) = 11</code> is <code>9</code>, because <code>9 OR (9 + 1) = 11</code>.</li>
	<li>For <code>i = 1</code>, the smallest <code>ans[1]</code> that satisfies <code>ans[1] OR (ans[1] + 1) = 13</code> is <code>12</code>, because <code>12 OR (12 + 1) = 13</code>.</li>
	<li>For <code>i = 2</code>, the smallest <code>ans[2]</code> that satisfies <code>ans[2] OR (ans[2] + 1) = 31</code> is <code>15</code>, because <code>15 OR (15 + 1) = 31</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>2 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums[i]</code> is a prime number.</li>
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
