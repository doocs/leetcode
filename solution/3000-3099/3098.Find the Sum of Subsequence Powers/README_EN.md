# [3098. Find the Sum of Subsequence Powers](https://leetcode.com/problems/find-the-sum-of-subsequence-powers)

[中文文档](/solution/3000-3099/3098.Find%20the%20Sum%20of%20Subsequence%20Powers/README.md)

<!-- tags: -->

## Description

<p>You are given an integer array <code>nums</code> of length <code>n</code>, and a <strong>positive</strong> integer <code>k</code>.</p>

<p>The <strong>power</strong> of a <span data-keyword="subsequence-array">subsequence</span> is defined as the <strong>minimum</strong> absolute difference between <strong>any</strong> two elements in the subsequence.</p>

<p>Return <em>the <strong>sum</strong> of <strong>powers</strong> of <strong>all</strong> subsequences of </em><code>nums</code><em> which have length</em> <strong><em>equal to</em></strong> <code>k</code>.</p>

<p>Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9 </sup>+ 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 4 subsequences in <code>nums</code> which have length 3: <code>[1,2,3]</code>, <code>[1,3,4]</code>, <code>[1,2,4]</code>, and <code>[2,3,4]</code>. The sum of powers is <code>|2 - 3| + |3 - 4| + |2 - 1| + |3 - 4| = 4</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The only subsequence in <code>nums</code> which has length 2 is&nbsp;<code>[2,2]</code>. The sum of powers is <code>|2 - 2| = 0</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,-1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 3 subsequences in <code>nums</code> which have length 2: <code>[4,3]</code>, <code>[4,-1]</code>, and <code>[3,-1]</code>. The sum of powers is <code>|4 - 3| + |4 - (-1)| + |3 - (-1)| = 10</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 50</code></li>
	<li><code>-10<sup>8</sup> &lt;= nums[i] &lt;= 10<sup>8</sup> </code></li>
	<li><code>2 &lt;= k &lt;= n</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
