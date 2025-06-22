---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3587.Minimum%20Adjacent%20Swaps%20to%20Alternate%20Parity/README_EN.md
---

<!-- problem:start -->

# [3587. Minimum Adjacent Swaps to Alternate Parity](https://leetcode.com/problems/minimum-adjacent-swaps-to-alternate-parity)

[中文文档](/solution/3500-3599/3587.Minimum%20Adjacent%20Swaps%20to%20Alternate%20Parity/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> of <strong>distinct</strong> integers.</p>

<p>In one operation, you can swap any two <strong>adjacent</strong> elements in the array.</p>

<p>An arrangement of the array is considered <strong>valid</strong> if the parity of adjacent elements <strong>alternates</strong>, meaning every pair of neighboring elements consists of one even and one odd number.</p>

<p>Return the <strong>minimum</strong> number of adjacent swaps required to transform <code>nums</code> into any valid arrangement.</p>

<p>If it is impossible to rearrange <code>nums</code> such that no two adjacent elements have the same parity, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,6,5,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Swapping 5 and 6, the array becomes <code>[2,4,5,6,7]</code></p>

<p>Swapping 5 and 4, the array becomes <code>[2,5,4,6,7]</code></p>

<p>Swapping 6 and 7, the array becomes <code>[2,5,4,7,6]</code>. The array is now a valid arrangement. Thus, the answer is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,5,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>By swapping 4 and 5, the array becomes <code>[2,5,4,7]</code>, which is a valid arrangement. Thus, the answer is 1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The array is already a valid arrangement. Thus, no operations are needed.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,5,6,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>No valid arrangement is possible. Thus, the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>All elements in <code>nums</code> are <strong>distinct</strong>.</li>
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
