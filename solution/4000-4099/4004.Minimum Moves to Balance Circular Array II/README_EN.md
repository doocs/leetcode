---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4004.Minimum%20Moves%20to%20Balance%20Circular%20Array%20II/README_EN.md
---

<!-- problem:start -->

# [4004. Minimum Moves to Balance Circular Array II 🔒](https://leetcode.com/problems/minimum-moves-to-balance-circular-array-ii)

[中文文档](/solution/4000-4099/4004.Minimum%20Moves%20to%20Balance%20Circular%20Array%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a <span data-keyword="circular-array">circular array</span> <code>balance</code> of length <code>n</code>, where <code>balance[i]</code> is the net balance of person <code>i</code>.</p>

<p>In one move, a person can transfer <strong>exactly</strong> 1 unit of balance to either their left or right neighbor.</p>

<p>Return the <strong>minimum</strong> number of moves required so that every person has a <strong>non-negative</strong> balance. If it is impossible, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">balance = [-1,2,-1]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal sequence of moves is:</p>

<ul>
	<li>Move 1 unit from <code>i = 1</code> to <code>i = 0</code>, resulting in <code>balance = [0, 1, -1]</code></li>
	<li>Move 1 unit from <code>i = 1</code> to <code>i = 2</code>, resulting in <code>balance = [0, 0, 0]</code></li>
</ul>

<p>Thus, the minimum number of moves required is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">balance = [4,-1,-2]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal sequence of moves is:</p>

<ul>
	<li>Move 1 unit from <code>i = 0</code> to <code>i = 1</code>, resulting in <code>balance = [3, 0, -2]</code></li>
	<li>Move 1 unit from <code>i = 0</code> to <code>i = 2</code>, resulting in <code>balance = [2, 0, -1]</code></li>
	<li>Move 1 unit from <code>i = 0</code> to <code>i = 2</code>, resulting in <code>balance = [1, 0, 0]</code></li>
</ul>

<p>Thus, the minimum number of moves required is 3.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">balance = [-3,-3,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>It is impossible to make all balances non-negative for <code>balance = [-3, -3, 5]</code>, so the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == balance.length &lt;= 1000</code></li>
	<li><code>-10<sup>5</sup> &lt;= balance[i] &lt;= 10<sup>5</sup></code></li>
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
