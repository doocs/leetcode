---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4049. Count Values With Equally Spaced Occurrences II](https://leetcode.com/problems/count-values-with-equally-spaced-occurrences-ii)

[中文文档](/solution/4000-4099/4049.Count%20Values%20With%20Equally%20Spaced%20Occurrences%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>An integer <code>x</code> is called <strong>special</strong> if:</p>

<ul>
	<li><code>x</code> appears <strong>at least three</strong> times in <code>nums</code>.</li>
	<li><strong>All</strong> occurrences of <code>x</code> are <strong>equally spaced</strong> in <code>nums</code>. In other words, if all occurrences of <code>x</code> are at indices <code>i<sub>1</sub> &lt; i<sub>2</sub> &lt; ... &lt; i<sub>m</sub></code>, then <code>i<sub>2</sub> - i<sub>1</sub> = i<sub>3</sub> - i<sub>2</sub> = ... = i<sub>m</sub> - i<sub>m-1</sub></code>.</li>
</ul>

<p>Return the number of <strong>distinct</strong> special integers in <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,8,1,5,1,5,8,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>1 is special because it occurs at equally spaced indices 0, 2, and 4.</li>
	<li>5 is special because it occurs at equally spaced indices 3, 5, and 7.</li>
	<li>8 is not special because it occurs only twice.</li>
</ul>

<p>Therefore, the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [8,8,8,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>8 is special because it occurs at equally spaced indices 0, 1, 2, and 3. Therefore, the answer is 1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [8,6,6,8,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>8 occurs at indices 0, 3, and 4, which are not equally spaced. 6 occurs only twice. Therefore, no integer is special.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
