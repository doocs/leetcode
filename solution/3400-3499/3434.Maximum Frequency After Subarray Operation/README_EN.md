---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3434.Maximum%20Frequency%20After%20Subarray%20Operation/README_EN.md
rating: 2093
source: Weekly Contest 434 Q3
tags:
    - Greedy
    - Array
    - Hash Table
    - Dynamic Programming
    - Enumeration
    - Prefix Sum
---

<!-- problem:start -->

# [3434. Maximum Frequency After Subarray Operation](https://leetcode.com/problems/maximum-frequency-after-subarray-operation)

[中文文档](/solution/3400-3499/3434.Maximum%20Frequency%20After%20Subarray%20Operation/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> of length <code>n</code>. You are also given an integer <code>k</code>.</p>

<p>You perform the following operation on <code>nums</code> <strong>once</strong>:</p>

<ul>
	<li>Select a <span data-keyword="subarray-nonempty">subarray</span> <code>nums[i..j]</code> where <code>0 &lt;= i &lt;= j &lt;= n - 1</code>.</li>
	<li>Select an integer <code>x</code> and add <code>x</code> to <strong>all</strong> the elements in <code>nums[i..j]</code>.</li>
</ul>

<p>Find the <strong>maximum</strong> frequency of the value <code>k</code> after the operation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5,6], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>After adding -5 to <code>nums[2..5]</code>, 1 has a frequency of 2 in <code>[1, 2, -2, -1, 0, 1]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [10,2,3,4,5,5,4,3,2,2], k = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>After adding 8 to <code>nums[1..9]</code>, 10 has a frequency of 4 in <code>[10, 10, 11, 12, 13, 13, 12, 11, 10, 10]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
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
