---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3836.Maximum%20Score%20Using%20Exactly%20K%20Pairs/README_EN.md
---

<!-- problem:start -->

# [3836. Maximum Score Using Exactly K Pairs](https://leetcode.com/problems/maximum-score-using-exactly-k-pairs)

[中文文档](/solution/3800-3899/3836.Maximum%20Score%20Using%20Exactly%20K%20Pairs/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>nums1</code> and <code>nums2</code> of lengths <code>n</code> and <code>m</code> respectively, and an integer <code>k</code>.</p>

<p>You must choose <strong>exactly</strong> <code>k</code> pairs of indices <code>(i<sub>1</sub>, j<sub>1</sub>), (i<sub>2</sub>, j<sub>2</sub>), ..., (i<sub>k</sub>, j<sub>k</sub>)</code> such that:</p>

<ul>
	<li><code>0 &lt;= i<sub>1</sub> &lt; i<sub>2</sub> &lt; ... &lt; i<sub>k</sub> &lt; n</code></li>
	<li><code>0 &lt;= j<sub>1</sub> &lt; j<sub>2</sub> &lt; ... &lt; j<sub>k</sub> &lt; m</code></li>
</ul>

<p>For each chosen pair <code>(i, j)</code>, you gain a score of <code>nums1[i] * nums2[j]</code>.</p>

<p>The total <strong>score</strong> is the <strong>sum</strong> of the products of all selected pairs.</p>

<p>Return an integer representing the <strong>maximum</strong> achievable total score.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [1,3,2], nums2 = [4,5,1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">22</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal choice of index pairs is:</p>

<ul>
	<li><code>(i<sub>1</sub>, j<sub>1</sub>) = (1, 0)</code> which scores <code>3 * 4 = 12</code></li>
	<li><code>(i<sub>2</sub>, j<sub>2</sub>) = (2, 1)</code> which scores <code>2 * 5 = 10</code></li>
</ul>

<p>This gives a total score of <code>12 + 10 = 22</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [-2,0,5], nums2 = [-3,4,-1,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">26</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal choice of index pairs is:</p>

<ul>
	<li><code>(i<sub>1</sub>, j<sub>1</sub>) = (0, 0)</code> which scores <code>-2 * -3 = 6</code></li>
	<li><code>(i<sub>2</sub>, j<sub>2</sub>) = (2, 1)</code> which scores <code>5 * 4 = 20</code></li>
</ul>

<p>The total score is <code>6 + 20 = 26</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [-3,-2], nums2 = [1,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">-7</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal choice of index pairs is:</p>

<ul>
	<li><code>(i<sub>1</sub>, j<sub>1</sub>) = (0, 0)</code> which scores <code>-3 * 1 = -3</code></li>
	<li><code>(i<sub>2</sub>, j<sub>2</sub>) = (1, 1)</code> which scores <code>-2 * 2 = -4</code></li>
</ul>

<p>The total score is <code>-3 + (-4) = -7</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums1.length &lt;= 100</code></li>
	<li><code>1 &lt;= m == nums2.length &lt;= 100</code></li>
	<li><code>-10<sup>6</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= min(n, m)</code></li>
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
