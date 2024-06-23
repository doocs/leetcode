---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3193.Count%20the%20Number%20of%20Inversions/README_EN.md
---

<!-- problem:start -->

# [3193. Count the Number of Inversions](https://leetcode.com/problems/count-the-number-of-inversions)

[中文文档](/solution/3100-3199/3193.Count%20the%20Number%20of%20Inversions/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> and a 2D array <code>requirements</code>, where <code>requirements[i] = [end<sub>i</sub>, cnt<sub>i</sub>]</code> represents the end index and the <strong>inversion</strong> count of each requirement.</p>

<p>A pair of indices <code>(i, j)</code> from an integer array <code>nums</code> is called an <strong>inversion</strong> if:</p>

<ul>
	<li><code>i &lt; j</code> and <code>nums[i] &gt; nums[j]</code></li>
</ul>

<p>Return the number of <span data-keyword="permutation">permutations</span> <code>perm</code> of <code>[0, 1, 2, ..., n - 1]</code> such that for <strong>all</strong> <code>requirements[i]</code>, <code>perm[0..end<sub>i</sub>]</code> has exactly <code>cnt<sub>i</sub></code> inversions.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, requirements = [[2,2],[0,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The two permutations are:</p>

<ul>
	<li><code>[2, 0, 1]</code>

    <ul>
    	<li>Prefix <code>[2, 0, 1]</code> has inversions <code>(0, 1)</code> and <code>(0, 2)</code>.</li>
    	<li>Prefix <code>[2]</code> has 0 inversions.</li>
    </ul>
    </li>
    <li><code>[1, 2, 0]</code>
    <ul>
    	<li>Prefix <code>[1, 2, 0]</code> has inversions <code>(0, 2)</code> and <code>(1, 2)</code>.</li>
    	<li>Prefix <code>[1]</code> has 0 inversions.</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, requirements = [[2,2],[1,1],[0,0]]</span></p>

<p><strong>Output:</strong> 1</p>

<p><strong>Explanation:</strong></p>

<p>The only satisfying permutation is <code>[2, 0, 1]</code>:</p>

<ul>
	<li>Prefix <code>[2, 0, 1]</code> has inversions <code>(0, 1)</code> and <code>(0, 2)</code>.</li>
	<li>Prefix <code>[2, 0]</code> has an inversion <code>(0, 1)</code>.</li>
	<li>Prefix <code>[2]</code> has 0 inversions.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, requirements = [[0,0],[1,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The only satisfying permutation is <code>[0, 1]</code>:</p>

<ul>
	<li>Prefix <code>[0]</code> has 0 inversions.</li>
	<li>Prefix <code>[0, 1]</code> has an inversion <code>(0, 1)</code>.</li>
</ul>
</div>

<div id="gtx-trans" style="position: absolute; left: 198px; top: 756px;">
<div class="gtx-trans-icon">&nbsp;</div>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 300</code></li>
	<li><code>1 &lt;= requirements.length &lt;= n</code></li>
	<li><code>requirements[i] = [end<sub>i</sub>, cnt<sub>i</sub>]</code></li>
	<li><code>0 &lt;= end<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>0 &lt;= cnt<sub>i</sub> &lt;= 400</code></li>
	<li>The input is generated such that there is at least one <code>i</code> such that <code>end<sub>i</sub> == n - 1</code>.</li>
	<li>The input is generated such that all <code>end<sub>i</sub></code> are unique.</li>
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
