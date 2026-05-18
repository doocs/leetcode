---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3934.Smallest%20Unique%20Subarray/README_EN.md
---

<!-- problem:start -->

# [3934. Smallest Unique Subarray](https://leetcode.com/problems/smallest-unique-subarray)

[中文文档](/solution/3900-3999/3934.Smallest%20Unique%20Subarray/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Find the <strong>minimum </strong>length of a <span data-keyword="subarray">subarray</span> that is <strong>not</strong> <strong>identical</strong> to any other <strong>subarray</strong> in <code>nums</code>.</p>

<p>Return an integer denoting the <strong>minimum possible length</strong> of such a <strong>subarray</strong>.</p>

<p>Two <strong>subarrays</strong> are considered identical if they have the same length and the same elements in corresponding positions.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,3,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Subarrays of length 1: <code>[3]</code> &rarr; appears 3 times</li>
	<li>Subarrays of length 2: <code>[3, 3]</code> &rarr; appears 2 times</li>
	<li>Subarrays of length 3: <code>[3, 3, 3]</code> &rarr; appears once</li>
</ul>

<p>The subarray <code>[3, 3, 3]</code> is unique, so the smallest unique subarray length is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,2,3,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Subarrays of length 1:</p>

<ul>
	<li><code>[2]</code> &rarr; appears 2 times</li>
	<li><code>[1]</code> &rarr; appears once</li>
	<li><code>[3]</code> &rarr; appears 2 times</li>
</ul>
The subarray <code>[1]</code> is unique, so the smallest unique subarray length is 1.</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Subarrays of length 1:</p>

<ul>
	<li><code>[1]</code> &rarr; appears 3 times</li>
	<li><code>[2]</code> &rarr; appears 2 times</li>
</ul>

<p>Subarrays of length 2:</p>

<ul>
	<li><code>[1, 1]</code> &rarr; appears once</li>
	<li><code>[1, 2]</code> &rarr; appears once</li>
	<li><code>[2, 2]</code> &rarr; appears once</li>
	<li><code>[2, 1]</code> &rarr; appears once</li>
</ul>
There is at least one subarray of length 2 that is unique, so the smallest unique subarray length is 2.</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
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
