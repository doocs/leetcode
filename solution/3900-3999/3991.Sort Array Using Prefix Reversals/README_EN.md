---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3991.Sort%20Array%20Using%20Prefix%20Reversals/README_EN.md
---

<!-- problem:start -->

# [3991. Sort Array Using Prefix Reversals 🔒](https://leetcode.com/problems/sort-array-using-prefix-reversals)

[中文文档](/solution/3900-3999/3991.Sort%20Array%20Using%20Prefix%20Reversals/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>, where <code>nums</code> is a <span data-keyword="permutation-array">permutation</span> of the integers in the range <code>[0, n - 1]</code>.</p>

<p>You are also given an integer array <code>pre</code>, where each <code>pre[i]</code> is a valid <span data-keyword="array-prefix">prefix</span> length.</p>

<p>In one operation, you may choose any length <code>x</code> from <code>pre</code> and reverse the first <code>x</code> elements of <code>nums</code>.</p>

<p>For example, applying a prefix reversal of length <code>3</code> on <code>[4, 1, 2, 3]</code> results in <code>[2, 1, 4, 3]</code>.</p>

<p>Return the minimum number of operations required to sort <code>nums</code> in ascending order. If it is impossible to sort <code>nums</code>, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,0,1], pre = [2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Reverse <code>pre[1] = 3</code> elements to get <code>nums = [1, 0, 2]</code>.</li>
	<li>Then reverse <code>pre[0] = 2</code> elements to get <code>nums = [0, 1, 2]</code>.</li>
	<li>Thus, the minimum number of prefix reversal required is 2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,0,2], pre = [1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>It is impossible to sort the array using the given prefix lengths, so the answer is -1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,1], pre = [2]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>Since <code>nums</code> is already sorted, no prefix reversals are needed. Thus, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 8</code></li>
	<li><code>0 &lt;= nums[i] &lt;= n - 1</code></li>
	<li><code>1 &lt;= pre.length &lt;= n</code></li>
	<li><code>1 &lt;= pre[i] &lt;= n</code></li>
	<li><code>​​​​​​​nums</code> is a permutation of integers from 0 to <code>n - 1</code>.</li>
	<li><code>pre</code> consists of <strong>unique</strong> integers.</li>
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
