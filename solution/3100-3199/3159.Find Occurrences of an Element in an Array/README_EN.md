---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3159.Find%20Occurrences%20of%20an%20Element%20in%20an%20Array/README_EN.md
---

<!-- problem:start -->

# [3159. Find Occurrences of an Element in an Array](https://leetcode.com/problems/find-occurrences-of-an-element-in-an-array)

[中文文档](/solution/3100-3199/3159.Find%20Occurrences%20of%20an%20Element%20in%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>, an integer array <code>queries</code>, and an integer <code>x</code>.</p>

<p>For each <code>queries[i]</code>, you need to find the index of the <code>queries[i]<sup>th</sup></code> occurrence of <code>x</code> in the <code>nums</code> array. If there are fewer than <code>queries[i]</code> occurrences of <code>x</code>, the answer should be -1 for that query.</p>

<p>Return an integer array <code>answer</code> containing the answers to all queries.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,1,7], queries = [1,3,2,4], x = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,-1,2,-1]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For the 1<sup>st</sup> query, the first occurrence of 1 is at index 0.</li>
	<li>For the 2<sup>nd</sup> query, there are only two occurrences of 1 in <code>nums</code>, so the answer is -1.</li>
	<li>For the 3<sup>rd</sup> query, the second occurrence of 1 is at index 2.</li>
	<li>For the 4<sup>th</sup> query, there are only two occurrences of 1 in <code>nums</code>, so the answer is -1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], queries = [10], x = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For the 1<sup>st</sup> query, 5 doesn&#39;t exist in <code>nums</code>, so the answer is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], x &lt;= 10<sup>4</sup></code></li>
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
