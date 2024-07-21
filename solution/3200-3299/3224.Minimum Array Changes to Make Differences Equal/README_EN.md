---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3224.Minimum%20Array%20Changes%20to%20Make%20Differences%20Equal/README_EN.md
---

<!-- problem:start -->

# [3224. Minimum Array Changes to Make Differences Equal](https://leetcode.com/problems/minimum-array-changes-to-make-differences-equal)

[中文文档](/solution/3200-3299/3224.Minimum%20Array%20Changes%20to%20Make%20Differences%20Equal/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of size <code>n</code> where <code>n</code> is <strong>even</strong>, and an integer <code>k</code>.</p>

<p>You can perform some changes on the array, where in one change you can replace <strong>any</strong> element in the array with <strong>any</strong> integer in the range from <code>0</code> to <code>k</code>.</p>

<p>You need to perform some changes (possibly none) such that the final array satisfies the following condition:</p>

<ul>
	<li>There exists an integer <code>X</code> such that <code>abs(a[i] - a[n - i - 1]) = X</code> for all <code>(0 &lt;= i &lt; n)</code>.</li>
</ul>

<p>Return the <strong>minimum</strong> number of changes required to satisfy the above condition.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,0,1,2,4,3], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong><br />
We can perform the following changes:</p>

<ul>
	<li>Replace <code>nums[1]</code> by 2. The resulting array is <code>nums = [1,<u><strong>2</strong></u>,1,2,4,3]</code>.</li>
	<li>Replace <code>nums[3]</code> by 3. The resulting array is <code>nums = [1,2,1,<u><strong>3</strong></u>,4,3]</code>.</li>
</ul>

<p>The integer <code>X</code> will be 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,1,2,3,3,6,5,4], k = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong><br />
We can perform the following operations:</p>

<ul>
	<li>Replace <code>nums[3]</code> by 0. The resulting array is <code>nums = [0,1,2,<u><strong>0</strong></u>,3,6,5,4]</code>.</li>
	<li>Replace <code>nums[4]</code> by 4. The resulting array is <code>nums = [0,1,2,0,<strong><u>4</u></strong>,6,5,4]</code>.</li>
</ul>

<p>The integer <code>X</code> will be 4.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code> is even.</li>
	<li><code>0 &lt;= nums[i] &lt;= k &lt;= 10<sup>5</sup></code></li>
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
