---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4117. Minimum Range After Sum Operations](https://leetcode.com/problems/minimum-range-after-sum-operations)

[中文文档](/solution/4100-4199/4117.Minimum%20Range%20After%20Sum%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code>a</code>, <code>b</code>, and <code>c</code>.</p>

<p>You may perform the following operation <strong>any</strong> number of times (including zero):</p>

<ul>
	<li>Choose one of the three integers and <strong>replace</strong> it with the sum of the other two integers. The other two integers remain unchanged.</li>
</ul>

<p>The <strong>range</strong> of three integers is defined as the difference between the <strong>maximum value</strong> and the <strong>minimum value</strong>.</p>

<p>Return the <strong>minimum range</strong> that can be obtained after performing the operation any number of times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">a = 9, b = 2, c = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>One sequence of operations which results in the minimum range is as follows:</p>

<ul>
	<li>Set <code>a</code> to <code>b + c = 2 + 5 = 7</code>. The three integers are now <code>a = 7</code>, <code>b = 2</code>, <code>c = 5</code>.</li>
	<li>The range of the three integers is <code>max(7, 2, 5) - min(7, 2, 5) = 7 - 2 = 5</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">a = 5, b = 7, c = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Without doing any operations, the range of the three integers is <code>max(5, 7, 6) - min(5, 7, 6) = 7 - 5 = 2</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">a = 3, b = 3, c = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>Without doing any operations, the range of the three integers is <code>max(3, 3, 3) - min(3, 3, 3) = 3 - 3 = 0</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a, b, c &lt;= 100</code></li>
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
