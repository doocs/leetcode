# [2132. Stamping the Grid](https://leetcode.com/problems/stamping-the-grid)

[中文文档](/solution/2100-2199/2132.Stamping%20the%20Grid/README.md)

## Description

<p>You are given an <code>m x n</code> binary matrix <code>grid</code> where each cell is either <code>0</code> (empty) or <code>1</code> (occupied).</p>

<p>You are then given stamps of size <code>stampHeight x stampWidth</code>. We want to fit the stamps such that they follow the given <strong>restrictions</strong> and <strong>requirements</strong>:</p>

<ol>
	<li>Cover all the <strong>empty</strong> cells.</li>
	<li>Do not cover any of the <strong>occupied</strong> cells.</li>
	<li>We can put as <strong>many</strong> stamps as we want.</li>
	<li>Stamps can <strong>overlap</strong> with each other.</li>
	<li>Stamps are not allowed to be <strong>rotated</strong>.</li>
	<li>Stamps must stay completely <strong>inside</strong> the grid.</li>
</ol>

<p>Return <code>true</code> <em>if it is possible to fit the stamps while following the given restrictions and requirements. Otherwise, return</em> <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2132.Stamping%20the%20Grid/images/ex1.png" style="width: 180px; height: 237px;" />
<pre>
<strong>Input:</strong> grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
<strong>Output:</strong> true
<strong>Explanation:</strong> We have two overlapping stamps (labeled 1 and 2 in the image) that are able to cover all the empty cells.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2132.Stamping%20the%20Grid/images/ex2.png" style="width: 170px; height: 179px;" />
<pre>
<strong>Input:</strong> grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2 
<strong>Output:</strong> false 
<strong>Explanation:</strong> There is no way to fit the stamps onto all the empty cells without the stamps going outside the grid.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[r].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>grid[r][c]</code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>1 &lt;= stampHeight, stampWidth &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def possibleToStamp(
        self, grid: List[List[int]], stampHeight: int, stampWidth: int
    ) -> bool:
        m, n = len(grid), len(grid[0])
        s = [[0] * (n + 1) for _ in range(m + 1)]
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + v

        d = [[0] * (n + 1) for _ in range(m + 1)]
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v == 0:
                    x, y = i + stampHeight, j + stampWidth
                    if x <= m and y <= n and s[x][y] - s[x][j] - s[i][y] + s[i][j] == 0:
                        d[i][j] += 1
                        d[i][y] -= 1
                        d[x][j] -= 1
                        d[x][y] += 1

        cnt = [[0] * (n + 1) for _ in range(m + 1)]
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                cnt[i + 1][j + 1] = cnt[i + 1][j] + cnt[i][j + 1] - cnt[i][j] + d[i][j]
                if v == 0 and cnt[i + 1][j + 1] == 0:
                    return False
        return True
```

### **Java**

```java
class Solution {
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length, n = grid[0].length;
        int[][] s = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + grid[i][j];
            }
        }
        int[][] d = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int x = i + stampHeight, y = j + stampWidth;
                    if (x <= m && y <= n && s[x][y] - s[x][j] - s[i][y] + s[i][j] == 0) {
                        d[i][j]++;
                        d[i][y]--;
                        d[x][j]--;
                        d[x][y]++;
                    }
                }
            }
        }
        int[][] cnt = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                cnt[i + 1][j + 1] = cnt[i + 1][j] + cnt[i][j + 1] - cnt[i][j] + d[i][j];
                if (grid[i][j] == 0 && cnt[i + 1][j + 1] == 0) {
                    return false;
                }
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
    bool possibleToStamp(vector<vector<int>>& grid, int stampHeight, int stampWidth) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> s(m + 1, vector<int>(n + 1));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + grid[i][j];
            }
        }
        vector<vector<int>> d(m + 1, vector<int>(n + 1));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) continue;
                int x = i + stampHeight, y = j + stampWidth;
                if (x <= m && y <= n && s[x][y] - s[i][y] - s[x][j] + s[i][j] == 0) {
                    d[i][j]++;
                    d[x][j]--;
                    d[i][y]--;
                    d[x][y]++;
                }
            }
        }
        vector<vector<int>> cnt(m + 1, vector<int>(n + 1));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                cnt[i + 1][j + 1] = cnt[i + 1][j] + cnt[i][j + 1] - cnt[i][j] + d[i][j];
                if (grid[i][j] == 0 && cnt[i + 1][j + 1] == 0) return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func possibleToStamp(grid [][]int, stampHeight int, stampWidth int) bool {
	m, n := len(grid), len(grid[0])
	s := make([][]int, m+1)
	d := make([][]int, m+1)
	cnt := make([][]int, m+1)
	for i := range s {
		s[i] = make([]int, n+1)
		d[i] = make([]int, n+1)
		cnt[i] = make([]int, n+1)
	}
	for i, row := range grid {
		for j, v := range row {
			s[i+1][j+1] = s[i+1][j] + s[i][j+1] - s[i][j] + v
		}
	}
	for i, row := range grid {
		for j, v := range row {
			if v == 0 {
				x, y := i+stampHeight, j+stampWidth
				if x <= m && y <= n && s[x][y]-s[i][y]-s[x][j]+s[i][j] == 0 {
					d[i][j]++
					d[i][y]--
					d[x][j]--
					d[x][y]++
				}
			}
		}
	}
	for i, row := range grid {
		for j, v := range row {
			cnt[i+1][j+1] = cnt[i+1][j] + cnt[i][j+1] - cnt[i][j] + d[i][j]
			if v == 0 && cnt[i+1][j+1] == 0 {
				return false
			}
		}
	}
	return true
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} grid
 * @param {number} stampHeight
 * @param {number} stampWidth
 * @return {boolean}
 */
var possibleToStamp = function (grid, stampHeight, stampWidth) {
    const m = grid.length;
    const n = grid[0].length;
    let s = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    let d = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    let cnt = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + grid[i][j];
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == 0) {
                let [x, y] = [i + stampHeight, j + stampWidth];
                if (
                    x <= m &&
                    y <= n &&
                    s[x][y] - s[i][y] - s[x][j] + s[i][j] == 0
                ) {
                    d[i][j]++;
                    d[i][y]--;
                    d[x][j]--;
                    d[x][y]++;
                }
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            cnt[i + 1][j + 1] =
                cnt[i + 1][j] + cnt[i][j + 1] - cnt[i][j] + d[i][j];
            if (grid[i][j] == 0 && cnt[i + 1][j + 1] == 0) {
                return false;
            }
        }
    }
    return true;
};
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
