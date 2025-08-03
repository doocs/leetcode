---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3634.Minimum%20Removals%20to%20Balance%20Array/README_EN.md
---

<!-- problem:start -->

# [3634. Minimum Removals to Balance Array](https://leetcode.com/problems/minimum-removals-to-balance-array)

[中文文档](/solution/3600-3699/3634.Minimum%20Removals%20to%20Balance%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>An array is considered <strong>balanced</strong> if the value of its <strong>maximum</strong> element is <strong>at most</strong> <code>k</code> times the <strong>minimum</strong> element.</p>

<p>You may remove <strong>any</strong> number of elements from <code>nums</code>​​​​​​​ without making it <strong>empty</strong>.</p>

<p>Return the <strong>minimum</strong> number of elements to remove so that the remaining array is balanced.</p>

<p><strong>Note:</strong> An array of size 1 is considered balanced as its maximum and minimum are equal, and the condition always holds true.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,5], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove <code>nums[2] = 5</code> to get <code>nums = [2, 1]</code>.</li>
	<li>Now <code>max = 2</code>, <code>min = 1</code> and <code>max &lt;= min * k</code> as <code>2 &lt;= 1 * 2</code>. Thus, the answer is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,6,2,9], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove <code>nums[0] = 1</code> and <code>nums[3] = 9</code> to get <code>nums = [6, 2]</code>.</li>
	<li>Now <code>max = 6</code>, <code>min = 2</code> and <code>max &lt;= min * k</code> as <code>6 &lt;= 2 * 3</code>. Thus, the answer is 2.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,6], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Since <code>nums</code> is already balanced as <code>6 &lt;= 4 * 2</code>, no elements need to be removed.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
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
