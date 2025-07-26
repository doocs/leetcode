---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3603.Minimum%20Cost%20Path%20with%20Alternating%20Directions%20II/README_EN.md
tags:
    - Array
    - Dynamic Programming
    - Matrix
---

<!-- problem:start -->

# [3603. Minimum Cost Path with Alternating Directions II](https://leetcode.com/problems/minimum-cost-path-with-alternating-directions-ii)

[中文文档](/solution/3600-3699/3603.Minimum%20Cost%20Path%20with%20Alternating%20Directions%20II/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>m</code> and <code>n</code> representing the number of rows and columns of a grid, respectively.</p>

<p>The cost to enter cell <code>(i, j)</code> is defined as <code>(i + 1) * (j + 1)</code>.</p>

<p>You are also given a 2D integer array <code>waitCost</code> where <code>waitCost[i][j]</code> defines the cost to <strong>wait</strong> on that cell.</p>

<p>The path will always begin by entering cell <code>(0, 0)</code> on move 1 and paying the entrance cost.</p>

<p>At each step, you follow an alternating pattern:</p>

<ul>
	<li>On <strong>odd-numbered</strong> seconds, you must move <strong>right</strong> or <strong>down</strong> to an <strong>adjacent</strong> cell, paying its entry cost.</li>
	<li>On <strong>even-numbered</strong> seconds, you must <strong>wait</strong> in place for <strong>exactly</strong> one second and pay <code>waitCost[i][j]</code> during that second.</li>
</ul>

<p>Return the <strong>minimum</strong> total cost required to reach <code>(m - 1, n - 1)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 1, n = 2, waitCost = [[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal path is:</p>

<ul>
	<li>Start at cell <code>(0, 0)</code> at second 1 with entry cost <code>(0 + 1) * (0 + 1) = 1</code>.</li>
	<li><strong>Second 1</strong>: Move right to cell <code>(0, 1)</code> with entry cost <code>(0 + 1) * (1 + 1) = 2</code>.</li>
</ul>

<p>Thus, the total cost is <code>1 + 2 = 3</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 2, n = 2, waitCost = [[3,5],[2,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal path is:</p>

<ul>
	<li>Start at cell <code>(0, 0)</code> at second 1 with entry cost <code>(0 + 1) * (0 + 1) = 1</code>.</li>
	<li><strong>Second 1</strong>: Move down to cell <code>(1, 0)</code> with entry cost <code>(1 + 1) * (0 + 1) = 2</code>.</li>
	<li><strong>Second 2</strong>: Wait at cell <code>(1, 0)</code>, paying <code>waitCost[1][0] = 2</code>.</li>
	<li><strong>Second 3</strong>: Move right to cell <code>(1, 1)</code> with entry cost <code>(1 + 1) * (1 + 1) = 4</code>.</li>
</ul>

<p>Thus, the total cost is <code>1 + 2 + 2 + 4 = 9</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 2, n = 3, waitCost = [[6,1,4],[3,2,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">16</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal path is:</p>

<ul>
	<li>Start at cell <code>(0, 0)</code> at second 1 with entry cost <code>(0 + 1) * (0 + 1) = 1</code>.</li>
	<li><strong>Second 1</strong>: Move right to cell <code>(0, 1)</code> with entry cost <code>(0 + 1) * (1 + 1) = 2</code>.</li>
	<li><strong>Second 2</strong>: Wait at cell <code>(0, 1)</code>, paying <code>waitCost[0][1] = 1</code>.</li>
	<li><strong>Second 3</strong>: Move down to cell <code>(1, 1)</code> with entry cost <code>(1 + 1) * (1 + 1) = 4</code>.</li>
	<li><strong>Second 4</strong>: Wait at cell <code>(1, 1)</code>, paying <code>waitCost[1][1] = 2</code>.</li>
	<li><strong>Second 5</strong>: Move right to cell <code>(1, 2)</code> with entry cost <code>(1 + 1) * (2 + 1) = 6</code>.</li>
</ul>

<p>Thus, the total cost is <code>1 + 2 + 1 + 4 + 2 + 6 = 16</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>waitCost.length == m</code></li>
	<li><code>waitCost[0].length == n</code></li>
	<li><code>0 &lt;= waitCost[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, m: int, n: int, waitCost: List[List[int]]) -> int:
        directions = [(1, 0), (0, 1)]  # only down and right
        visited = dict()

        heap = [(1 * 1, 0, 0, 1)]  # (cost, i, j, time)

        while heap:
            cost, i, j, time = heapq.heappop(heap)

            if (i, j, time % 2) in visited and visited[(i, j, time % 2)] <= cost:
                continue
            visited[(i, j, time % 2)] = cost

            if i == m - 1 and j == n - 1:
                return cost

            if time % 2 == 1:  # move step
                for dx, dy in directions:
                    ni, nj = i + dx, j + dy
                    if 0 <= ni < m and 0 <= nj < n:
                        next_cost = cost + (ni + 1) * (nj + 1)
                        heapq.heappush(heap, (next_cost, ni, nj, time + 1))
            else:  # wait step
                next_cost = cost + waitCost[i][j]
                heapq.heappush(heap, (next_cost, i, j, time + 1))

        return -1
```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go
func minCost(m int, n int, cost [][]int) int64 {
	dp := make([]int64, n)
	for i := 0; i < n; i++ {
		dp[i] = int64(i + 1)
	}
	for i := 1; i < n; i++ {
		dp[i] += dp[i-1] + int64(cost[0][i])
	}

	for y := 1; y < m; y++ {
		dp[0] += int64(cost[y][0]) + int64(y+1)
		for x := 1; x < n; x++ {
			enter := int64(y+1) * int64(x+1)
			dp[x] = min(dp[x], dp[x-1]) + int64(cost[y][x]) + enter
		}
	}

	return dp[n-1] - int64(cost[m-1][n-1])
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
