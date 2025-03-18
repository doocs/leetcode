---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3489.Zero%20Array%20Transformation%20IV/README_EN.md
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3489. Zero Array Transformation IV](https://leetcode.com/problems/zero-array-transformation-iv)

[中文文档](/solution/3400-3499/3489.Zero%20Array%20Transformation%20IV/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and a 2D array <code>queries</code>, where <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>, val<sub>i</sub>]</code>.</p>

<p>Each <code>queries[i]</code> represents the following action on <code>nums</code>:</p>

<ul>
	<li>Select a <span data-keyword="subset">subset</span> of indices in the range <code>[l<sub>i</sub>, r<sub>i</sub>]</code> from <code>nums</code>.</li>
	<li>Decrement the value at each selected index by <strong>exactly</strong> <code>val<sub>i</sub></code>.</li>
</ul>

<p>A <strong>Zero Array</strong> is an array with all its elements equal to 0.</p>

<p>Return the <strong>minimum</strong> possible <strong>non-negative</strong> value of <code>k</code>, such that after processing the first <code>k</code> queries in <strong>sequence</strong>, <code>nums</code> becomes a <strong>Zero Array</strong>. If no such <code>k</code> exists, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,0,2], queries = [[0,2,1],[0,2,1],[1,1,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>For query 0 (l = 0, r = 2, val = 1):</strong>

    <ul>
    	<li>Decrement the values at indices <code>[0, 2]</code> by 1.</li>
    	<li>The array will become <code>[1, 0, 1]</code>.</li>
    </ul>
    </li>
    <li><strong>For query 1 (l = 0, r = 2, val = 1):</strong>
    <ul>
    	<li>Decrement the values at indices <code>[0, 2]</code> by 1.</li>
    	<li>The array will become <code>[0, 0, 0]</code>, which is a Zero Array. Therefore, the minimum value of <code>k</code> is 2.</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,2,1], queries = [[1,3,2],[0,2,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>It is impossible to make nums a Zero Array even after all the queries.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,2,1], queries = [[0,1,1],[1,2,1],[2,3,2],[3,4,1],[4,4,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>For query 0 (l = 0, r = 1, val = 1):</strong>

    <ul>
    	<li>Decrement the values at indices <code>[0, 1]</code> by <code><font face="monospace">1</font></code>.</li>
    	<li>The array will become <code>[0, 1, 3, 2, 1]</code>.</li>
    </ul>
    </li>
    <li><strong>For query 1 (l = 1, r = 2, val = 1):</strong>
    <ul>
    	<li>Decrement the values at indices <code>[1, 2]</code> by 1.</li>
    	<li>The array will become <code>[0, 0, 2, 2, 1]</code>.</li>
    </ul>
    </li>
    <li><strong>For query 2 (l = 2, r = 3, val = 2):</strong>
    <ul>
    	<li>Decrement the values at indices <code>[2, 3]</code> by 2.</li>
    	<li>The array will become <code>[0, 0, 0, 0, 1]</code>.</li>
    </ul>
    </li>
    <li><strong>For query 3 (l = 3, r = 4, val = 1):</strong>
    <ul>
    	<li>Decrement the value at index 4 by 1.</li>
    	<li>The array will become <code>[0, 0, 0, 0, 0]</code>. Therefore, the minimum value of <code>k</code> is 4.</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,2,6], queries = [[0,1,1],[0,2,1],[1,4,2],[4,4,4],[3,4,1],[4,4,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= queries.length &lt;= 1000</code></li>
	<li><code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>, val<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; nums.length</code></li>
	<li><code>1 &lt;= val<sub>i</sub> &lt;= 10</code></li>
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
