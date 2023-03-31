# [807. Max Increase to Keep City Skyline](https://leetcode.com/problems/max-increase-to-keep-city-skyline)

[中文文档](/solution/0800-0899/0807.Max%20Increase%20to%20Keep%20City%20Skyline/README.md)

## Description

<p>There is a city composed of <code>n x n</code> blocks, where each block contains a single building shaped like a vertical square prism. You are given a <strong>0-indexed</strong> <code>n x n</code> integer matrix <code>grid</code> where <code>grid[r][c]</code> represents the <strong>height</strong> of the building located in the block at row <code>r</code> and column <code>c</code>.</p>

<p>A city&#39;s <strong>skyline</strong> is the&nbsp;outer contour formed by all the building when viewing the side of the city from a distance. The <strong>skyline</strong> from each cardinal direction north, east, south, and west may be different.</p>

<p>We are allowed to increase the height of <strong>any number of buildings by any amount</strong> (the amount can be different per building). The height of a <code>0</code>-height building can also be increased. However, increasing the height of a building should <strong>not</strong> affect the city&#39;s <strong>skyline</strong> from any cardinal direction.</p>

<p>Return <em>the <strong>maximum total sum</strong> that the height of the buildings can be increased by <strong>without</strong> changing the city&#39;s <strong>skyline</strong> from any cardinal direction</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0807.Max%20Increase%20to%20Keep%20City%20Skyline/images/807-ex1.png" style="width: 700px; height: 603px;" />
<pre>
<strong>Input:</strong> grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]
<strong>Output:</strong> 35
<strong>Explanation:</strong> The building heights are shown in the center of the above image.
The skylines when viewed from each cardinal direction are drawn in red.
The grid after increasing the height of buildings without affecting skylines is:
gridNew = [ [8, 4, 8, 7],
            [7, 4, 7, 7],
            [9, 4, 8, 7],
            [3, 3, 3, 3] ]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[0,0,0],[0,0,0],[0,0,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Increasing the height of any building will result in the skyline changing.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[r].length</code></li>
	<li><code>2 &lt;= n &lt;= 50</code></li>
	<li><code>0 &lt;= grid[r][c] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxIncreaseKeepingSkyline(self, grid: List[List[int]]) -> int:
        rmx = [max(row) for row in grid]
        cmx = [max(col) for col in zip(*grid)]
        return sum(
            (min(rmx[i], cmx[j]) - grid[i][j])
            for i in range(len(grid))
            for j in range(len(grid[0]))
        )
```

### **Java**

```java
class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rmx = new int[m];
        int[] cmx = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rmx[i] = Math.max(rmx[i], grid[i][j]);
                cmx[j] = Math.max(cmx[j], grid[i][j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans += Math.min(rmx[i], cmx[j]) - grid[i][j];
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function maxIncreaseKeepingSkyline(grid: number[][]): number {
    let rows = grid.map(arr => Math.max(...arr)),
        cols = [];
    let m = grid.length,
        n = grid[0].length;
    for (let j = 0; j < n; ++j) {
        cols[j] = grid[0][j];
        for (let i = 1; i < m; ++i) {
            cols[j] = Math.max(cols[j], grid[i][j]);
        }
    }

    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            ans += Math.min(rows[i], cols[j]) - grid[i][j];
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int maxIncreaseKeepingSkyline(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<int> rmx(m, 0);
        vector<int> cmx(n, 0);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rmx[i] = max(rmx[i], grid[i][j]);
                cmx[j] = max(cmx[j], grid[i][j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                ans += min(rmx[i], cmx[j]) - grid[i][j];
        return ans;
    }
};
```

### **Go**

```go
func maxIncreaseKeepingSkyline(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	rmx := make([]int, m)
	cmx := make([]int, n)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			rmx[i] = max(rmx[i], grid[i][j])
			cmx[j] = max(cmx[j], grid[i][j])
		}
	}
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			ans += min(rmx[i], cmx[j]) - grid[i][j]
		}
	}
	return ans
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

### **...**

```

```

<!-- tabs:end -->
