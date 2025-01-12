---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3418.Maximum%20Amount%20of%20Money%20Robot%20Can%20Earn/README_EN.md
---

<!-- problem:start -->

# [3418. Maximum Amount of Money Robot Can Earn](https://leetcode.com/problems/maximum-amount-of-money-robot-can-earn)

[中文文档](/solution/3400-3499/3418.Maximum%20Amount%20of%20Money%20Robot%20Can%20Earn/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> grid. A robot starts at the top-left corner of the grid <code>(0, 0)</code> and wants to reach the bottom-right corner <code>(m - 1, n - 1)</code>. The robot can move either right or down at any point in time.</p>

<p>The grid contains a value <code>coins[i][j]</code> in each cell:</p>

<ul>
	<li>If <code>coins[i][j] &gt;= 0</code>, the robot gains that many coins.</li>
	<li>If <code>coins[i][j] &lt; 0</code>, the robot encounters a robber, and the robber steals the <strong>absolute</strong> value of <code>coins[i][j]</code> coins.</li>
</ul>

<p>The robot has a special ability to <strong>neutralize robbers</strong> in at most <strong>2 cells</strong> on its path, preventing them from stealing coins in those cells.</p>

<p><strong>Note:</strong> The robot&#39;s total coins can be negative.</p>

<p>Return the <strong>maximum</strong> profit the robot can gain on the route.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">coins = [[0,1,-1],[1,-2,3],[2,-3,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p>An optimal path for maximum coins is:</p>

<ol>
	<li>Start at <code>(0, 0)</code> with <code>0</code> coins (total coins = <code>0</code>).</li>
	<li>Move to <code>(0, 1)</code>, gaining <code>1</code> coin (total coins = <code>0 + 1 = 1</code>).</li>
	<li>Move to <code>(1, 1)</code>, where there&#39;s a robber stealing <code>2</code> coins. The robot uses one neutralization here, avoiding the robbery (total coins = <code>1</code>).</li>
	<li>Move to <code>(1, 2)</code>, gaining <code>3</code> coins (total coins = <code>1 + 3 = 4</code>).</li>
	<li>Move to <code>(2, 2)</code>, gaining <code>4</code> coins (total coins = <code>4 + 4 = 8</code>).</li>
</ol>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">coins = [[10,10,10],[10,10,10]]</span></p>

<p><strong>Output:</strong> <span class="example-io">40</span></p>

<p><strong>Explanation:</strong></p>

<p>An optimal path for maximum coins is:</p>

<ol>
	<li>Start at <code>(0, 0)</code> with <code>10</code> coins (total coins = <code>10</code>).</li>
	<li>Move to <code>(0, 1)</code>, gaining <code>10</code> coins (total coins = <code>10 + 10 = 20</code>).</li>
	<li>Move to <code>(0, 2)</code>, gaining another <code>10</code> coins (total coins = <code>20 + 10 = 30</code>).</li>
	<li>Move to <code>(1, 2)</code>, gaining the final <code>10</code> coins (total coins = <code>30 + 10 = 40</code>).</li>
</ol>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == coins.length</code></li>
	<li><code>n == coins[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>-1000 &lt;= coins[i][j] &lt;= 1000</code></li>
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
