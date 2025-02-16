---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3457.Eat%20Pizzas%21/README_EN.md
---

<!-- problem:start -->

# [3457. Eat Pizzas!](https://leetcode.com/problems/eat-pizzas)

[中文文档](/solution/3400-3499/3457.Eat%20Pizzas%21/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>pizzas</code> of size <code>n</code>, where <code>pizzas[i]</code> represents the weight of the <code>i<sup>th</sup></code> pizza. Every day, you eat <strong>exactly</strong> 4 pizzas. Due to your incredible metabolism, when you eat pizzas of weights <code>W</code>, <code>X</code>, <code>Y</code>, and <code>Z</code>, where <code>W &lt;= X &lt;= Y &lt;= Z</code>, you gain the weight of only 1 pizza!</p>

<ul>
	<li>On <strong><span style="box-sizing: border-box; margin: 0px; padding: 0px;">odd-numbered</span></strong> days <strong>(1-indexed)</strong>, you gain a weight of <code>Z</code>.</li>
	<li>On <strong>even-numbered</strong> days, you gain a weight of <code>Y</code>.</li>
</ul>

<p>Find the <strong>maximum</strong> total weight you can gain by eating <strong>all</strong> pizzas optimally.</p>

<p><strong>Note</strong>: It is guaranteed that <code>n</code> is a multiple of 4, and each pizza can be eaten only once.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">pizzas = [1,2,3,4,5,6,7,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>On day 1, you eat pizzas at indices <code>[1, 2, 4, 7] = [2, 3, 5, 8]</code>. You gain a weight of 8.</li>
	<li>On day 2, you eat pizzas at indices <code>[0, 3, 5, 6] = [1, 4, 6, 7]</code>. You gain a weight of 6.</li>
</ul>

<p>The total weight gained after eating all the pizzas is <code>8 + 6 = 14</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">pizzas = [2,1,1,1,1,1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>On day 1, you eat pizzas at indices <code>[4, 5, 6, 0] = [1, 1, 1, 2]</code>. You gain a weight of 2.</li>
	<li>On day 2, you eat pizzas at indices <code>[1, 2, 3, 7] = [1, 1, 1, 1]</code>. You gain a weight of 1.</li>
</ul>

<p>The total weight gained after eating all the pizzas is <code>2 + 1 = 3.</code></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>4 &lt;= n == pizzas.length &lt;= 2 * 10<sup><span style="font-size: 10.8333px;">5</span></sup></code></li>
	<li><code>1 &lt;= pizzas[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code> is a multiple of 4.</li>
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
