# [2017. Grid Game](https://leetcode.com/problems/grid-game)

[中文文档](/solution/2000-2099/2017.Grid%20Game/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> 2D array <code>grid</code> of size <code>2 x n</code>, where <code>grid[r][c]</code> represents the number of points at position <code>(r, c)</code> on the matrix. Two robots are playing a game on this matrix.</p>

<p>Both robots initially start at <code>(0, 0)</code> and want to reach <code>(1, n-1)</code>. Each robot may only move to the <strong>right</strong> (<code>(r, c)</code> to <code>(r, c + 1)</code>) or <strong>down </strong>(<code>(r, c)</code> to <code>(r + 1, c)</code>).</p>

<p>At the start of the game, the <strong>first</strong> robot moves from <code>(0, 0)</code> to <code>(1, n-1)</code>, collecting all the points from the cells on its path. For all cells <code>(r, c)</code> traversed on the path, <code>grid[r][c]</code> is set to <code>0</code>. Then, the <strong>second</strong> robot moves from <code>(0, 0)</code> to <code>(1, n-1)</code>, collecting the points on its path. Note that their paths may intersect with one another.</p>

<p>The <strong>first</strong> robot wants to <strong>minimize</strong> the number of points collected by the <strong>second</strong> robot. In contrast, the <strong>second </strong>robot wants to <strong>maximize</strong> the number of points it collects. If both robots play <strong>optimally</strong>, return <em>the <b>number of points</b> collected by the <strong>second</strong> robot.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2017.Grid%20Game/images/a1.png" style="width: 388px; height: 103px;" />
<pre>
<strong>Input:</strong> grid = [[2,5,4],[1,5,1]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
The cells visited by the first robot are set to 0.
The second robot will collect 0 + 0 + 4 + 0 = 4 points.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2017.Grid%20Game/images/a2.png" style="width: 384px; height: 105px;" />
<pre>
<strong>Input:</strong> grid = [[3,3,1],[8,5,2]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
The cells visited by the first robot are set to 0.
The second robot will collect 0 + 3 + 1 + 0 = 4 points.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2017.Grid%20Game/images/a3.png" style="width: 493px; height: 103px;" />
<pre>
<strong>Input:</strong> grid = [[1,3,1,15],[1,3,3,1]]
<strong>Output:</strong> 7
<strong>Explanation: </strong>The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
The cells visited by the first robot are set to 0.
The second robot will collect 0 + 1 + 3 + 3 + 0 = 7 points.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>grid.length == 2</code></li>
	<li><code>n == grid[r].length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= grid[r][c] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def gridGame(self, grid: List[List[int]]) -> int:
        ans = inf
        s1, s2 = sum(grid[0]), 0
        for j, v in enumerate(grid[0]):
            s1 -= v
            ans = min(ans, max(s1, s2))
            s2 += grid[1][j]
        return ans
```

### **Java**

```java
class Solution {
    public long gridGame(int[][] grid) {
        long ans = Long.MAX_VALUE;
        long s1 = 0, s2 = 0;
        for (int v : grid[0]) {
            s1 += v;
        }
        int n = grid[0].length;
        for (int j = 0; j < n; ++j) {
            s1 -= grid[0][j];
            ans = Math.min(ans, Math.max(s1, s2));
            s2 += grid[1][j];
        }
        return ans;
    }
}
```

### **C++**

```cpp
using ll = long long;

class Solution {
public:
    long long gridGame(vector<vector<int>>& grid) {
        ll ans = LONG_MAX;
        int n = grid[0].size();
        ll s1 = 0, s2 = 0;
        for (int& v : grid[0]) s1 += v;
        for (int j = 0; j < n; ++j) {
            s1 -= grid[0][j];
            ans = min(ans, max(s1, s2));
            s2 += grid[1][j];
        }
        return ans;
    }
};
```

### **Go**

```go
func gridGame(grid [][]int) int64 {
	ans := math.MaxInt64
	s1, s2 := 0, 0
	for _, v := range grid[0] {
		s1 += v
	}
	for j, v := range grid[0] {
		s1 -= v
		ans = min(ans, max(s1, s2))
		s2 += grid[1][j]
	}
	return int64(ans)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function gridGame(grid: number[][]): number {
    let ans = Number.MAX_SAFE_INTEGER;
    let s1 = grid[0].reduce((a, b) => a + b, 0);
    let s2 = 0;
    for (let j = 0; j < grid[0].length; ++j) {
        s1 -= grid[0][j];
        ans = Math.min(ans, Math.max(s1, s2));
        s2 += grid[1][j];
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
