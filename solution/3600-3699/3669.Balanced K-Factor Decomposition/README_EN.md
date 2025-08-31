---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3669.Balanced%20K-Factor%20Decomposition/README_EN.md
---

<!-- problem:start -->

# [3669. Balanced K-Factor Decomposition](https://leetcode.com/problems/balanced-k-factor-decomposition)

[中文文档](/solution/3600-3699/3669.Balanced%20K-Factor%20Decomposition/README.md)

## Description

<!-- description:start -->

<p>Given two integers <code>n</code> and <code>k</code>, split the number <code>n</code> into exactly <code>k</code> positive integers such that the <strong>product</strong> of these integers is equal to <code>n</code>.</p>

<p>Return <em>any</em> <em>one</em> split in which the <strong>maximum</strong> difference between any two numbers is <strong>minimized</strong>. You may return the result in <em>any order</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 100, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[10,10]</span></p>

<p><strong>Explanation:</strong></p>

<p data-end="157" data-start="0">The split <code>[10, 10]</code> yields <code>10 * 10 = 100</code> and a max-min difference of 0, which is minimal.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 44, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,2,11]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li data-end="46" data-start="2">Split <code>[1, 1, 44]</code> yields a difference of 43</li>
	<li data-end="93" data-start="49">Split <code>[1, 2, 22]</code> yields a difference of 21</li>
	<li data-end="140" data-start="96">Split <code>[1, 4, 11]</code> yields a difference of 10</li>
	<li data-end="186" data-start="143">Split <code>[2, 2, 11]</code> yields a difference of 9</li>
</ul>

<p data-end="264" data-is-last-node="" data-is-only-node="" data-start="188">Therefore, <code>[2, 2, 11]</code> is the optimal split with the smallest difference 9.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li data-end="54" data-start="37"><code data-end="52" data-start="37">4 &lt;= n &lt;= 10<sup><span style="font-size: 10.8333px;">5</span></sup></code></li>
	<li data-end="71" data-start="57"><code data-end="69" data-start="57">2 &lt;= k &lt;= 5</code></li>
	<li data-end="145" data-is-last-node="" data-start="74"><code data-end="77" data-start="74">k</code> is strictly less than the total number of positive divisors of <code data-end="144" data-is-only-node="" data-start="141">n</code>.</li>
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
