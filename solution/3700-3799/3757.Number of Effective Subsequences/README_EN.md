---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3757.Number%20of%20Effective%20Subsequences/README_EN.md
rating: 2519
source: Weekly Contest 477 Q4
---

<!-- problem:start -->

# [3757. Number of Effective Subsequences](https://leetcode.com/problems/number-of-effective-subsequences)

[中文文档](/solution/3700-3799/3757.Number%20of%20Effective%20Subsequences/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>The <strong>strength</strong> of the array is defined as the <strong>bitwise OR</strong> of all its elements.</p>

<p>A <strong><span data-keyword="subsequence-array-nonempty">subsequence</span></strong> is considered <strong>effective</strong> if removing that subsequence <strong>strictly decreases</strong> the strength of the remaining elements.</p>

<p>Return the number of <strong>effective subsequences</strong> in <code>nums</code>. Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>The bitwise OR of an empty array is 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The Bitwise OR of the array is <code>1 OR 2 OR 3 = 3</code>.</li>
	<li>Subsequences that are effective are:
	<ul>
		<li><code>[1, 3]</code>: The remaining element <code>[2]</code> has a Bitwise OR of 2.</li>
		<li><code>[2, 3]</code>: The remaining element <code>[1]</code> has a Bitwise OR of 1.</li>
		<li><code>[1, 2, 3]</code>: The remaining elements <code>[]</code> have a Bitwise OR of 0.</li>
	</ul>
	</li>
	<li>Thus, the total number of effective subsequences is 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,4,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>The Bitwise OR of the array is <code>7 OR 4 OR 6 = 7</code>.</li>
	<li>Subsequences that are effective are:
	<ul>
		<li><code>[7]</code>: The remaining elements <code>[4, 6]</code> have a Bitwise OR of 6.</li>
		<li><code>[7, 4]</code>: The remaining element <code>[6]</code> has a Bitwise OR of 6.</li>
		<li><code>[7, 6]</code>: The remaining element <code>[4]</code> has a Bitwise OR of 4.</li>
		<li><code>[7, 4, 6]</code>: The remaining elements <code>[]</code> have a Bitwise OR of 0.</li>
	</ul>
	</li>
	<li>Thus, the total number of effective subsequences is 4.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [8,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The Bitwise OR of the array is <code>8 OR 8 = 8</code>.</li>
	<li>Only the subsequence <code>[8, 8]</code> is effective since removing it leaves <code>[]</code> which has a Bitwise OR of 0.</li>
	<li>Thus, the total number of effective subsequences is 1.</li>
</ul>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The Bitwise OR of the array is <code>2 OR 2 OR 1 = 3</code>.</li>
	<li>Subsequences that are effective are:
	<ul>
		<li><code>[1]</code>: The remaining elements <code>[2, 2]</code> have a Bitwise OR of 2.</li>
		<li><code>[2, 1]</code> (using <code>nums[0]</code>, <code>nums[2]</code>): The remaining element <code>[2]</code> has a Bitwise OR of 2.</li>
		<li><code>[2, 1]</code> (using <code>nums[1]</code>, <code>nums[2]</code>): The remaining element <code>[2]</code> has a Bitwise OR of 2.</li>
		<li><code>[2, 2]</code>: The remaining element <code>[1]</code> has a Bitwise OR of 1.</li>
		<li><code>[2, 2, 1]</code>: The remaining elements <code>[]</code> have a Bitwise OR of 0.</li>
	</ul>
	</li>
	<li>Thus, the total number of effective subsequences is 5.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
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
