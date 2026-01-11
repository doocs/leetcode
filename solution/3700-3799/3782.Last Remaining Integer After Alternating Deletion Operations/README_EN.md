---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3782.Last%20Remaining%20Integer%20After%20Alternating%20Deletion%20Operations/README_EN.md
rating: 2074
source: Biweekly Contest 172 Q4
tags:
    - Recursion
    - Math
---

<!-- problem:start -->

# [3782. Last Remaining Integer After Alternating Deletion Operations](https://leetcode.com/problems/last-remaining-integer-after-alternating-deletion-operations)

[中文文档](/solution/3700-3799/3782.Last%20Remaining%20Integer%20After%20Alternating%20Deletion%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>.</p>

<p>We write the integers from 1 to <code>n</code> in a sequence from left to right. Then, <strong>alternately</strong> apply the following two operations until only one integer remains, starting with operation 1:</p>

<ul>
	<li><strong>Operation 1</strong>: Starting from the left, delete every second number.</li>
	<li><strong>Operation 2</strong>: Starting from the right, delete every second number.</li>
</ul>

<p>Return the last remaining integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 8</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Write <code>[1, 2, 3, 4, 5, 6, 7, 8]</code> in a sequence.</li>
	<li>Starting from the left, we delete every second number: <code>[1, <u><strong>2</strong></u>, 3, <u><strong>4</strong></u>, 5, <u><strong>6</strong></u>, 7, <u><strong>8</strong></u>]</code>. The remaining integers are <code>[1, 3, 5, 7]</code>.</li>
	<li>Starting from the right, we delete every second number: <code>[<u><strong>1</strong></u>, 3, <u><strong>5</strong></u>, 7]</code>. The remaining integers are <code>[3, 7]</code>.</li>
	<li>Starting from the left, we delete every second number: <code>[3, <u><strong>7</strong></u>]</code>. The remaining integer is <code>[3]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Write <code>[1, 2, 3, 4, 5]</code> in a sequence.</li>
	<li>Starting from the left, we delete every second number: <code>[1, <u><strong>2</strong></u>, 3, <u><strong>4</strong></u>, 5]</code>. The remaining integers are <code>[1, 3, 5]</code>.</li>
	<li>Starting from the right, we delete every second number: <code>[1, <u><strong>3</strong></u>, 5]</code>. The remaining integers are <code>[1, 5]</code>.</li>
	<li>Starting from the left, we delete every second number: <code>[1, <u><strong>5</strong></u>]</code>. The remaining integer is <code>[1]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Write <code>[1]</code> in a sequence.</li>
	<li>The last remaining integer is 1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>15</sup></code></li>
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
