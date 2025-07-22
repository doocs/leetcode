---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3480.Maximize%20Subarrays%20After%20Removing%20One%20Conflicting%20Pair/README_EN.md
rating: 2763
source: Weekly Contest 440 Q4
tags:
    - Segment Tree
    - Array
    - Enumeration
    - Prefix Sum
---

<!-- problem:start -->

# [3480. Maximize Subarrays After Removing One Conflicting Pair](https://leetcode.com/problems/maximize-subarrays-after-removing-one-conflicting-pair)

[中文文档](/solution/3400-3499/3480.Maximize%20Subarrays%20After%20Removing%20One%20Conflicting%20Pair/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> which represents an array <code>nums</code> containing the numbers from 1 to <code>n</code> in order. Additionally, you are given a 2D array <code>conflictingPairs</code>, where <code>conflictingPairs[i] = [a, b]</code> indicates that <code>a</code> and <code>b</code> form a conflicting pair.</p>

<p>Remove <strong>exactly</strong> one element from <code>conflictingPairs</code>. Afterward, count the number of <span data-keyword="subarray-nonempty">non-empty subarrays</span> of <code>nums</code> which do not contain both <code>a</code> and <code>b</code> for any remaining conflicting pair <code>[a, b]</code>.</p>

<p>Return the <strong>maximum</strong> number of subarrays possible after removing <strong>exactly</strong> one conflicting pair.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, conflictingPairs = [[2,3],[1,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove <code>[2, 3]</code> from <code>conflictingPairs</code>. Now, <code>conflictingPairs = [[1, 4]]</code>.</li>
	<li>There are 9 subarrays in <code>nums</code> where <code>[1, 4]</code> do not appear together. They are <code>[1]</code>, <code>[2]</code>, <code>[3]</code>, <code>[4]</code>, <code>[1, 2]</code>, <code>[2, 3]</code>, <code>[3, 4]</code>, <code>[1, 2, 3]</code> and <code>[2, 3, 4]</code>.</li>
	<li>The maximum number of subarrays we can achieve after removing one element from <code>conflictingPairs</code> is 9.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, conflictingPairs = [[1,2],[2,5],[3,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove <code>[1, 2]</code> from <code>conflictingPairs</code>. Now, <code>conflictingPairs = [[2, 5], [3, 5]]</code>.</li>
	<li>There are 12 subarrays in <code>nums</code> where <code>[2, 5]</code> and <code>[3, 5]</code> do not appear together.</li>
	<li>The maximum number of subarrays we can achieve after removing one element from <code>conflictingPairs</code> is 12.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= conflictingPairs.length &lt;= 2 * n</code></li>
	<li><code>conflictingPairs[i].length == 2</code></li>
	<li><code>1 &lt;= conflictingPairs[i][j] &lt;= n</code></li>
	<li><code>conflictingPairs[i][0] != conflictingPairs[i][1]</code></li>
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
