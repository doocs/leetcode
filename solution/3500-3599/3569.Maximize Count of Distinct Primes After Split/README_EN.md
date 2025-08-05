---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3569.Maximize%20Count%20of%20Distinct%20Primes%20After%20Split/README_EN.md
rating: 2697
source: Weekly Contest 452 Q4
tags:
    - Segment Tree
    - Array
    - Math
    - Number Theory
---

<!-- problem:start -->

# [3569. Maximize Count of Distinct Primes After Split](https://leetcode.com/problems/maximize-count-of-distinct-primes-after-split)

[中文文档](/solution/3500-3599/3569.Maximize%20Count%20of%20Distinct%20Primes%20After%20Split/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> having length <code>n</code> and a 2D integer array <code>queries</code> where <code>queries[i] = [idx, val]</code>.</p>

<p>For each query:</p>

<ol>
	<li>Update <code>nums[idx] = val</code>.</li>
	<li>Choose an integer <code>k</code> with <code>1 &lt;= k &lt; n</code> to split the array into the non-empty prefix <code>nums[0..k-1]</code> and suffix <code>nums[k..n-1]</code> such that the sum of the counts of <strong>distinct</strong> <span data-keyword="prime-number">prime</span> values in each part is <strong>maximum</strong>.</li>
</ol>

<p><strong data-end="513" data-start="504">Note:</strong> The changes made to the array in one query persist into the next query.</p>

<p>Return an array containing the result for each query, in the order they are given.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,3,1,2], queries = [[1,2],[3,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,4]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Initially <code>nums = [2, 1, 3, 1, 2]</code>.</li>
	<li>After 1<sup>st</sup> query, <code>nums = [2, 2, 3, 1, 2]</code>. Split <code>nums</code> into <code>[2]</code> and <code>[2, 3, 1, 2]</code>. <code>[2]</code> consists of 1 distinct prime and <code>[2, 3, 1, 2]</code> consists of 2 distinct primes. Hence, the answer for this query is <code>1 + 2 = 3</code>.</li>
	<li>After 2<sup>nd</sup> query, <code>nums = [2, 2, 3, 3, 2]</code>. Split <code>nums</code> into <code>[2, 2, 3]</code> and <code>[3, 2]</code> with an answer of <code>2 + 2 = 4</code>.</li>
	<li>The output is <code>[3, 4]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,4], queries = [[0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Initially <code>nums = [2, 1, 4]</code>.</li>
	<li>After 1<sup>st</sup> query, <code>nums = [1, 1, 4]</code>. There are no prime numbers in <code>nums</code>, hence the answer for this query is 0.</li>
	<li>The output is <code>[0]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= queries[i][0] &lt; nums.length</code></li>
	<li><code>1 &lt;= queries[i][1] &lt;= 10<sup>5</sup></code></li>
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
