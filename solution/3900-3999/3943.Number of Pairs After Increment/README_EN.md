---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3943.Number%20of%20Pairs%20After%20Increment/README_EN.md
rating: 2409
source: Weekly Contest 503 Q4
tags:
    - Array
    - Hash Table
    - Divide and Conquer
    - Counting
---

<!-- problem:start -->

# [3943. Number of Pairs After Increment](https://leetcode.com/problems/number-of-pairs-after-increment)

[中文文档](/solution/3900-3999/3943.Number%20of%20Pairs%20After%20Increment/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>nums1</code> and <code>nums2</code>, and a 2D integer array <code>queries</code>.</p>

<p>Each <code>queries[i]</code> is one of the following types:</p>

<ul>
	<li><code>[1, x, y, val]</code> &ndash; <strong>Add</strong> <code>val</code> to every element in <code>nums2[x..y]</code>.</li>
	<li><code>[2, tot]</code> &ndash; <strong>Compute</strong> the number of pairs <code>(j, k)</code> such that <code>nums1[j] + nums2[k] == tot</code>.</li>
</ul>

<p>Return an integer array <code>answer</code>, where <code>answer[j]</code> is the number of pairs for the <code>j<sup>th</sup></code> query of type 2.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [1,2], nums2 = [3,4], queries = [[2,5],[1,0,0,2],[2,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,1]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>queries[0] = [2, 5]</code>: Valid pairs are <code>nums1[0] + nums2[1] = 1 + 4 = 5</code> and <code>nums1[1] + nums2[0] = 2 + 3 = 5</code>.</li>
	<li><code>queries[1] = [1, 0, 0, 2]</code>: Add 2 to <code>nums2[0]</code>, resulting in <code>nums2 = [5, 4]</code>.</li>
	<li><code>queries[2] = [2, 5]</code>: Valid pair is <code>nums1[0] + nums2[1] = 1 + 4 = 5</code>.</li>
	<li>Thus, the <code>answer = [2, 1]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [1,1], nums2 = [2,2,3], queries = [[2,4],[1,0,1,1],[2,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,6]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>queries[0] = [2, 4]</code>: Valid pairs are <code>nums1[0] + nums2[2] = 1 + 3</code> and <code>nums1[1] + nums2[2] = 1 + 3</code>.</li>
	<li><code>queries[1] = [1, 0, 1, 1]</code>: Add 1 to <code>nums2[0]</code> and <code>nums2[1]</code>, resulting in <code>nums2 = [3, 3, 3]</code>.</li>
	<li><code>queries[2] = [2, 4]</code>: Every element of <code>nums1 = [1, 1]</code> pairs with every element of <code>nums2 = [3, 3, 3]</code> as <code>1 + 3 = 4</code>. That gives <code>2 &times; 3 = 6</code> pairs in total.</li>
	<li>Thus, the <code>answer = [2, 6]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [2,5,8,4], nums2 = [1,3,8], queries = [[2,9],[1,1,2,1],[2,10]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>queries[0] = [2, 9]</code>: Only valid pair is <code>nums1[2] + nums2[0] = 8 + 1 = 9</code>.</li>
	<li><code>queries[1] = [1, 1, 2, 1]</code>: Add 1 to <code>nums2[1]</code> and <code>nums2[2]</code>, resulting in​​​​​​​ <code>nums2 = [1, 4, 9]</code>.</li>
	<li><code>queries[2] = [2, 10]</code>: No pair sums to <code>10</code>.</li>
	<li>Thus, the <code>answer = [1, 0]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length &lt;= 5</code></li>
	<li><code>1 &lt;= nums2.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>queries[i].length == 2 or 4</code>
	<ul>
		<li><code>queries[i] == [1, x, y, val], or</code></li>
		<li><code>queries[i] == [2, tot]</code></li>
		<li><code>0 &lt;= x &lt;= y &lt; nums2.length</code></li>
		<li><code>1 &lt;= val &lt;= 10<sup>5</sup></code></li>
		<li><code>1 &lt;= tot &lt;= 10<sup>9</sup>​​​​​​​</code></li>
	</ul>
	</li>
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
