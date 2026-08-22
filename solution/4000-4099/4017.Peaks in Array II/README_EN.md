---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4017.Peaks%20in%20Array%20II/README_EN.md
rating: 2515
source: Weekly Contest 514 Q4
tags:
    - Segment Tree
    - Array
    - Divide and Conquer
---

<!-- problem:start -->

# [4017. Peaks in Array II](https://leetcode.com/problems/peaks-in-array-ii)

[中文文档](/solution/4000-4099/4017.Peaks%20in%20Array%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and a 2D integer array <code>queries</code>.</p>

<p>A <strong><span data-keyword="subarray-nonempty">subarray</span></strong> <code>nums[i..j]</code> is called a <strong>peak subarray</strong> if:</p>

<ul>
	<li>Its length is <strong>at least</strong> 3.</li>
	<li>There exists an index <code>k</code> such that <code>i &lt; k &lt; j</code> and:
	<ul>
		<li><code>nums[k] &gt; nums[k - 1]</code></li>
		<li><code>nums[k] &gt; nums[k + 1]</code></li>
	</ul>
	</li>
</ul>

<p>You have to process queries of two types:</p>

<ul>
	<li><code>[1, l<sub>i</sub>, r<sub>i</sub>]</code>: Calculate the number of <strong>peak subarrays</strong> fully contained within <code>nums[l<sub>i</sub>..r<sub>i</sub>]</code>.</li>
	<li><code>[2, index<sub>i</sub>, val<sub>i</sub>]</code>: Update <code>nums[index<sub>i</sub>]</code> to <code>val<sub>i</sub></code>. This update applies to all subsequent queries.</li>
</ul>

<p>Return an array <code>answer</code>, where <code>answer[i]</code> is the answer to the <code>i<sup>th</sup></code> query of type 1 in the order they appear.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,2,4], queries = [[1,0,3],[2,1,1],[1,0,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,0]</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Query <code>[1, 0, 3]</code>:

    <ul>
    	<li><code>[1, 3, 2]</code>: choose <code>k = 1</code>. Then <code>nums[k] = 3</code>, <code>nums[k - 1] = 1</code>, and <code>nums[k + 1] = 2</code>. Since <code>3 &gt; 1</code> and <code>3 &gt; 2</code>, this is a peak subarray.</li>
    	<li><code>[1, 3, 2, 4]</code>: choose <code>k = 1</code>. Then <code>nums[k] = 3</code>, <code>nums[k - 1] = 1</code>, and <code>nums[k + 1] = 2</code>. Since <code>3 &gt; 1</code> and <code>3 &gt; 2</code>, this is a peak subarray.</li>
    </ul>
    </li>
    <li>Query <code>[2, 1, 1]</code>: Update <code>nums[1]</code> to 1. The array becomes <code>[1, 1, 2, 4]</code>.</li>
    <li>Query <code>[1, 0, 3]</code>: There are no peak subarrays now.</li>
    <li>Thus, <code>answer = [2, 0]</code>.</li>

</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [9,8,9,8], queries = [[1,1,3],[2,2,1],[1,0,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Query <code>[1, 1, 3]</code>:

    <ul>
    	<li><code>nums[1..3] = [8, 9, 8]</code>: choose <code>k = 2</code>. Then <code>nums[k] = 9</code>, <code>nums[k - 1] = 8</code>, and <code>nums[k + 1] = 8</code>. Since <code>9 &gt; 8</code> and <code>9 &gt; 8</code>, this is a peak subarray.</li>
    </ul>
    </li>
    <li>Query <code>[2, 2, 1]</code>: Update <code>nums[2]</code> to 1. The array becomes <code>[9, 8, 1, 8]</code>.</li>
    <li>Query <code>[1, 0, 2]</code>: There are no peak subarrays.</li>
    <li>Thus, <code>answer = [1, 0]</code>.</li>

</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,6,2,7,1], queries = [[1,1,3],[2,3,0],[1,0,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,3]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Query <code>[1, 1, 3]</code>: The only subarray of length at least 3 is <code>[6, 2, 7]</code>. Its only possible peak index is <code>k = 2</code>, but <code>nums[2] = 2</code> is less than both <code>nums[1] = 6</code> and <code>nums[3] = 7</code>, so it is not a peak subarray.</li>
	<li>Query <code>[2, 3, 0]</code>: Update <code>nums[3]</code> to 0. The array becomes <code>[3, 6, 2, 0, 1]</code>.</li>
	<li>Query <code>[1, 0, 4]</code>:
	<ul>
		<li><code>[3, 6, 2]</code>: choose <code>k = 1</code>. Then <code>nums[k] = 6</code>, <code>nums[k - 1] = 3</code>, and <code>nums[k + 1] = 2</code>. Since <code>6 &gt; 3</code> and <code>6 &gt; 2</code>, this is a peak subarray.</li>
		<li><code>[3, 6, 2, 0]</code>: choose <code>k = 1</code>. Then <code>nums[k] = 6</code>, <code>nums[k - 1] = 3</code>, and <code>nums[k + 1] = 2</code>. Since <code>6 &gt; 3</code> and <code>6 &gt; 2</code>, this is a peak subarray.</li>
		<li><code>[3, 6, 2, 0, 1]</code>: choose <code>k = 1</code>. Then <code>nums[k] = 6</code>, <code>nums[k - 1] = 3</code>, and <code>nums[k + 1] = 2</code>. Since <code>6 &gt; 3</code> and <code>6 &gt; 2</code>, this is a peak subarray.</li>
	</ul>
	</li>
	<li>Thus, <code>answer = [0, 3]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i] = [1, l<sub>i</sub>, r<sub>i</sub>]</code> or <code>queries[i] = [2, index<sub>i</sub>, val<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt; r<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>0 &lt;= index<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>0 &lt;= val<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
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
