# [2596. Check Knight Tour Configuration](https://leetcode.com/problems/check-knight-tour-configuration)

[中文文档](/solution/2500-2599/2596.Check%20Knight%20Tour%20Configuration/README.md)

## Description

<p>There is a knight on an <code>n x n</code> chessboard. In a valid configuration, the knight starts <strong>at the top-left cell</strong> of the board and visits every cell on the board <strong>exactly once</strong>.</p>

<p>You are given an <code>n x n</code> integer matrix <code>grid</code> consisting of distinct integers from the range <code>[0, n * n - 1]</code> where <code>grid[row][col]</code> indicates that the cell <code>(row, col)</code> is the <code>grid[row][col]<sup>th</sup></code> cell that the knight visited. The moves are <strong>0-indexed</strong>.</p>

<p>Return <code>true</code> <em>if</em> <code>grid</code> <em>represents a valid configuration of the knight&#39;s movements or</em> <code>false</code> <em>otherwise</em>.</p>

<p><strong>Note</strong> that a valid knight move consists of moving two squares vertically and one square horizontally, or two squares horizontally and one square vertically. The figure below illustrates all the possible eight moves of a knight from some cell.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2596.Check%20Knight%20Tour%20Configuration/images/knight.png" style="width: 300px; height: 300px;" />
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2596.Check%20Knight%20Tour%20Configuration/images/yetgriddrawio-5.png" style="width: 251px; height: 251px;" />
<pre>
<strong>Input:</strong> grid = [[0,11,16,5,20],[17,4,19,10,15],[12,1,8,21,6],[3,18,23,14,9],[24,13,2,7,22]]
<strong>Output:</strong> true
<strong>Explanation:</strong> The above diagram represents the grid. It can be shown that it is a valid configuration.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2596.Check%20Knight%20Tour%20Configuration/images/yetgriddrawio-6.png" style="width: 151px; height: 151px;" />
<pre>
<strong>Input:</strong> grid = [[0,3,6],[5,8,1],[2,7,4]]
<strong>Output:</strong> false
<strong>Explanation:</strong> The above diagram represents the grid. The 8<sup>th</sup> move of the knight is not valid considering its position after the 7<sup>th</sup> move.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>3 &lt;= n &lt;= 7</code></li>
	<li><code>0 &lt;= grid[row][col] &lt; n * n</code></li>
	<li>All integers in <code>grid</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

**Solution 1: Simulation**

We first use the array $pos$ to record the coordinates of the grid visited by the knight, and then traverse the $pos$ array to check whether the difference between the adjacent two grid coordinates is $(1, 2)$ or $(2, 1)$. If not, return `false`.

Otherwise, return `true` after the traversal ends.

The time complexity is $O(n^2)$ and the space complexity is $O(n^2)$, where $n$ is the length of the chessboard.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def checkValidGrid(self, grid: List[List[int]]) -> bool:
        if grid[0][0]:
            return False
        n = len(grid)
        pos = [None] * (n * n)
        for i in range(n):
            for j in range(n):
                pos[grid[i][j]] = (i, j)
        for (x1, y1), (x2, y2) in pairwise(pos):
            dx, dy = abs(x1 - x2), abs(y1 - y2)
            ok = (dx == 1 and dy == 2) or (dx == 2 and dy == 1)
            if not ok:
                return False
        return True
```

### **Java**

```java
class Solution {
    public boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) {
            return false;
        }
        int n = grid.length;
        int[][] pos = new int[n * n][2];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                pos[grid[i][j]] = new int[] {i, j};
            }
        }
        for (int i = 1; i < n * n; ++i) {
            int[] p1 = pos[i - 1];
            int[] p2 = pos[i];
            int dx = Math.abs(p1[0] - p2[0]);
            int dy = Math.abs(p1[1] - p2[1]);
            boolean ok = (dx == 1 && dy == 2) || (dx == 2 && dy == 1);
            if (!ok) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkValidGrid(vector<vector<int>>& grid) {
        if (grid[0][0] != 0) {
            return false;
        }
        int n = grid.size();
        vector<pair<int, int>> pos(n * n);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                pos[grid[i][j]] = {i, j};
            }
        }
        for (int i = 1; i < n * n; ++i) {
            auto [x1, y1] = pos[i - 1];
            auto [x2, y2] = pos[i];
            int dx = abs(x1 - x2);
            int dy = abs(y1 - y2);
            bool ok = (dx == 1 && dy == 2) || (dx == 2 && dy == 1);
            if (!ok) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func checkValidGrid(grid [][]int) bool {
	if grid[0][0] != 0 {
		return false
	}
	n := len(grid)
	type pair struct{ x, y int }
	pos := make([]pair, n*n)
	for i, row := range grid {
		for j, x := range row {
			pos[x] = pair{i, j}
		}
	}
	for i := 1; i < n*n; i++ {
		p1, p2 := pos[i-1], pos[i]
		dx := abs(p1.x - p2.x)
		dy := abs(p1.y - p2.y)
		ok := (dx == 2 && dy == 1) || (dx == 1 && dy == 2)
		if !ok {
			return false
		}
	}
	return true
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts
function checkValidGrid(grid: number[][]): boolean {
    if (grid[0][0] !== 0) {
        return false;
    }
    const n = grid.length;
    const pos = Array.from(new Array(n * n), () => new Array(2).fill(0));
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            pos[grid[i][j]] = [i, j];
        }
    }
    for (let i = 1; i < n * n; ++i) {
        const p1 = pos[i - 1];
        const p2 = pos[i];
        const dx = Math.abs(p1[0] - p2[0]);
        const dy = Math.abs(p1[1] - p2[1]);
        const ok = (dx === 1 && dy === 2) || (dx === 2 && dy === 1);
        if (!ok) {
            return false;
        }
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
