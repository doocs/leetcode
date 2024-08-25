---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3265.Count%20Almost%20Equal%20Pairs%20I/README_EN.md
---

<!-- problem:start -->

# [3265. Count Almost Equal Pairs I](https://leetcode.com/problems/count-almost-equal-pairs-i)

[中文文档](/solution/3200-3299/3265.Count%20Almost%20Equal%20Pairs%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> consisting of positive integers.</p>

<p>We call two integers <code>x</code> and <code>y</code> in this problem <strong>almost equal</strong> if both integers can become equal after performing the following operation <strong>at most once</strong>:</p>

<ul>
	<li>Choose <strong>either</strong> <code>x</code> or <code>y</code> and swap any two digits within the chosen number.</li>
</ul>

<p>Return the number of indices <code>i</code> and <code>j</code> in <code>nums</code> where <code>i &lt; j</code> such that <code>nums[i]</code> and <code>nums[j]</code> are <strong>almost equal</strong>.</p>

<p><strong>Note</strong> that it is allowed for an integer to have leading zeros after performing an operation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,12,30,17,21]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The almost equal pairs of elements are:</p>

<ul>
	<li>3 and 30. By swapping 3 and 0 in 30, you get 3.</li>
	<li>12 and 21. By swapping 1 and 2 in 12, you get 21.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>Every two elements in the array are almost equal.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [123,231]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>We cannot swap any two digits of <code>123</code> or <code>231</code> to reach the other.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
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
