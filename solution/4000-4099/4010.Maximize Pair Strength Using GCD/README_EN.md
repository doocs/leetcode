---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4010.Maximize%20Pair%20Strength%20Using%20GCD/README_EN.md
---

<!-- problem:start -->

# [4010. Maximize Pair Strength Using GCD](https://leetcode.com/problems/maximize-pair-strength-using-gcd)

[中文文档](/solution/4000-4099/4010.Maximize%20Pair%20Strength%20Using%20GCD/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Choose <strong>exactly one</strong> pair of <strong>distinct</strong> indices <code>i</code> and <code>j</code>. The <strong>strength</strong> of the pair is defined as <code>(nums[i] * nums[j]) / gcd(nums[i], nums[j])<sup>2</sup></code>.</p>

<p>Return the <strong>maximum</strong> strength over all possible pairs.</p>

<p>The term <code>gcd(a, b)</code> denotes the <strong>greatest common divisor</strong> of <code>a</code> and <code>b</code>.</p>
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<p>Choosing <code>i = 1</code> and <code>j = 2</code> gives strength <code>(3 * 5) / gcd(3, 5)<sup>2</sup> = 15 / 1 = 15</code>, which is the maximum over all pairs.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,6,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>Choosing <code>i = 1</code> and <code>j = 2</code> gives strength <code>(6 * 8) / gcd(6, 8)<sup>2</sup> = 48 / 4 = 12</code>, which is the maximum over all pairs.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Choosing <code>i = 0</code> and <code>j = 1</code> gives strength <code>(3 * 3) / gcd(3, 3)<sup>2</sup> = 9 / 9 = 1</code>, the maximum over all pairs.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
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
