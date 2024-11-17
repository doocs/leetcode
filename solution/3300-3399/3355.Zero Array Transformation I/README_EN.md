---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3355.Zero%20Array%20Transformation%20I/README_EN.md
---

<!-- problem:start -->

# [3355. Zero Array Transformation I](https://leetcode.com/problems/zero-array-transformation-i)

[中文文档](/solution/3300-3399/3355.Zero%20Array%20Transformation%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and a 2D array <code>queries</code>, where <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code>.</p>

<p>For each <code>queries[i]</code>:</p>

<ul>
	<li>Select a subset of indices within the range <code>[l<sub>i</sub>, r<sub>i</sub>]</code> in <code>nums</code>.</li>
	<li>Decrement the values at the selected indices by 1.</li>
</ul>

<p>A <strong>Zero Array</strong> is an array where all elements are equal to 0.</p>

<p>Return <code>true</code> if it is <em>possible</em> to transform <code>nums</code> into a <strong>Zero Array </strong>after processing all the queries sequentially, otherwise return <code>false</code>.</p>

<p>A <strong>subset</strong> of an array is a selection of elements (possibly none) of the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,0,1], queries = [[0,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>For i = 0:</strong>

    <ul>
    	<li>Select the subset of indices as <code>[0, 2]</code> and decrement the values at these indices by 1.</li>
    	<li>The array will become <code>[0, 0, 0]</code>, which is a Zero Array.</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,2,1], queries = [[1,3],[0,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>For i = 0:</strong>

    <ul>
    	<li>Select the subset of indices as <code>[1, 2, 3]</code> and decrement the values at these indices by 1.</li>
    	<li>The array will become <code>[4, 2, 1, 0]</code>.</li>
    </ul>
    </li>
    <li><strong>For i = 1:</strong>
    <ul>
    	<li>Select the subset of indices as <code>[0, 1, 2]</code> and decrement the values at these indices by 1.</li>
    	<li>The array will become <code>[3, 1, 0, 0]</code>, which is not a Zero Array.</li>
    </ul>
    </li>

</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; nums.length</code></li>
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
