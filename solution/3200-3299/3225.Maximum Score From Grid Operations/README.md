---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3225.Maximum%20Score%20From%20Grid%20Operations/README.md
tags:
    - 数组
    - 动态规划
    - 矩阵
    - 前缀和
---

<!-- problem:start -->

# [3225. 网格图操作后的最大分数](https://leetcode.cn/problems/maximum-score-from-grid-operations)

[English Version](/solution/3200-3299/3225.Maximum%20Score%20From%20Grid%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为 <code>n x n</code>&nbsp;的二维矩阵&nbsp;<code>grid</code>&nbsp;，一开始所有格子都是白色的。一次操作中，你可以选择任意下标为&nbsp;<code>(i, j)</code>&nbsp;的格子，并将第&nbsp;<code>j</code>&nbsp;列中从最上面到第&nbsp;<code>i</code>&nbsp;行所有格子改成黑色。</p>

<p>如果格子 <code>(i, j)</code>&nbsp;为白色，且左边或者右边的格子至少一个格子为黑色，那么我们将 <code>grid[i][j]</code>&nbsp;加到最后网格图的总分中去。</p>

<p>请你返回执行任意次操作以后，最终网格图的 <strong>最大</strong>&nbsp;总分数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[0,0,0,0,0],[0,0,3,0,0],[0,1,0,0,0],[5,0,0,3,0],[0,0,0,0,2]]</span></p>

<p><span class="example-io"><b>输出：</b>11</span></p>

<p><strong>解释：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3225.Maximum%20Score%20From%20Grid%20Operations/images/one.png" style="width: 300px; height: 200px;" />
<p>第一次操作中，我们将第 1 列中，最上面的格子到第 3 行的格子染成黑色。第二次操作中，我们将第 4 列中，最上面的格子到最后一行的格子染成黑色。最后网格图总分为&nbsp;<code>grid[3][0] + grid[1][2] + grid[3][3]</code>&nbsp;等于 11 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[10,9,0,0,15],[7,1,0,8,0],[5,20,0,11,0],[0,0,0,1,2],[8,12,1,10,3]]</span></p>

<p><span class="example-io"><b>输出：</b>94</span></p>

<p><strong>解释：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3225.Maximum%20Score%20From%20Grid%20Operations/images/two-1.png" style="width: 300px; height: 200px;" />
<p>我们对第 1 ，2 ，3 列分别从上往下染黑色到第 1 ，4， 0 行。最后网格图总分为&nbsp;<code>grid[0][0] + grid[1][0] + grid[2][1] + grid[4][1] + grid[1][3] + grid[2][3] + grid[3][3] + grid[4][3] + grid[0][4]</code>&nbsp;等于 94 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;n == grid.length &lt;= 100</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
