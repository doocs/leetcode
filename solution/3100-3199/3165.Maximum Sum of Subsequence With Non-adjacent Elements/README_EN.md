---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3165.Maximum%20Sum%20of%20Subsequence%20With%20Non-adjacent%20Elements/README_EN.md
---

<!-- problem:start -->

# [3165. Maximum Sum of Subsequence With Non-adjacent Elements](https://leetcode.com/problems/maximum-sum-of-subsequence-with-non-adjacent-elements)

[中文文档](/solution/3100-3199/3165.Maximum%20Sum%20of%20Subsequence%20With%20Non-adjacent%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> consisting of integers. You are also given a 2D array <code>queries</code>, where <code>queries[i] = [pos<sub>i</sub>, x<sub>i</sub>]</code>.</p>

<p>For query <code>i</code>, we first set <code>nums[pos<sub>i</sub>]</code> equal to <code>x<sub>i</sub></code>, then we calculate the answer to query <code>i</code> which is the <strong>maximum</strong> sum of a <span data-keyword="subsequence-array">subsequence</span> of <code>nums</code> where <strong>no two adjacent elements are selected</strong>.</p>

<p>Return the <em>sum</em> of the answers to all queries.</p>

<p>Since the final answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>subsequence</strong> is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,5,9], queries = [[1,-2],[0,-3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">21</span></p>

<p><strong>Explanation:</strong><br />
After the 1<sup>st</sup> query, <code>nums = [3,-2,9]</code> and the maximum sum of a subsequence with non-adjacent elements is <code>3 + 9 = 12</code>.<br />
After the 2<sup>nd</sup> query, <code>nums = [-3,-2,9]</code> and the maximum sum of a subsequence with non-adjacent elements is 9.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,-1], queries = [[0,-5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong><br />
After the 1<sup>st</sup> query, <code>nums = [-5,-1]</code> and the maximum sum of a subsequence with non-adjacent elements is 0 (choosing an empty subsequence).</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>queries[i] == [pos<sub>i</sub>, x<sub>i</sub>]</code></li>
	<li><code>0 &lt;= pos<sub>i</sub> &lt;= nums.length - 1</code></li>
	<li><code>-10<sup>5</sup> &lt;= x<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
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
