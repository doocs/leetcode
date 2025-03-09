---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3478.Choose%20K%20Elements%20With%20Maximum%20Sum/README_EN.md
---

<!-- problem:start -->

# [3478. Choose K Elements With Maximum Sum](https://leetcode.com/problems/choose-k-elements-with-maximum-sum)

[中文文档](/solution/3400-3499/3478.Choose%20K%20Elements%20With%20Maximum%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays, <code>nums1</code> and <code>nums2</code>, both of length <code>n</code>, along with a positive integer <code>k</code>.</p>

<p>For each index <code>i</code> from <code>0</code> to <code>n - 1</code>, perform the following:</p>

<ul>
	<li>Find <strong>all</strong> indices <code>j</code> where <code>nums1[j]</code> is less than <code>nums1[i]</code>.</li>
	<li>Choose <strong>at most</strong> <code>k</code> values of <code>nums2[j]</code> at these indices to <strong>maximize</strong> the total sum.</li>
</ul>

<p>Return an array <code>answer</code> of size <code>n</code>, where <code>answer[i]</code> represents the result for the corresponding index <code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [4,2,1,5,3], nums2 = [10,20,30,40,50], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[80,30,0,80,50]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>i = 0</code>: Select the 2 largest values from <code>nums2</code> at indices <code>[1, 2, 4]</code> where <code>nums1[j] &lt; nums1[0]</code>, resulting in <code>50 + 30 = 80</code>.</li>
	<li>For <code>i = 1</code>: Select the 2 largest values from <code>nums2</code> at index <code>[2]</code> where <code>nums1[j] &lt; nums1[1]</code>, resulting in 30.</li>
	<li>For <code>i = 2</code>: No indices satisfy <code>nums1[j] &lt; nums1[2]</code>, resulting in 0.</li>
	<li>For <code>i = 3</code>: Select the 2 largest values from <code>nums2</code> at indices <code>[0, 1, 2, 4]</code> where <code>nums1[j] &lt; nums1[3]</code>, resulting in <code>50 + 30 = 80</code>.</li>
	<li>For <code>i = 4</code>: Select the 2 largest values from <code>nums2</code> at indices <code>[1, 2]</code> where <code>nums1[j] &lt; nums1[4]</code>, resulting in <code>30 + 20 = 50</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [2,2,2,2], nums2 = [3,1,2,3], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,0,0,0]</span></p>

<p><strong>Explanation:</strong></p>

<p>Since all elements in <code>nums1</code> are equal, no indices satisfy the condition <code>nums1[j] &lt; nums1[i]</code> for any <code>i</code>, resulting in 0 for all positions.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
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
