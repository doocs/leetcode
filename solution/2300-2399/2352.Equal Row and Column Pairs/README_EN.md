# [2352. Equal Row and Column Pairs](https://leetcode.com/problems/equal-row-and-column-pairs)

[中文文档](/solution/2300-2399/2352.Equal%20Row%20and%20Column%20Pairs/README.md)

## Description

<p>Given a <strong>0-indexed</strong> <code>n x n</code> integer matrix <code>grid</code>, <em>return the number of pairs </em><code>(R<sub>i</sub>, C<sub>j</sub>)</code><em> such that row </em><code>R<sub>i</sub></code><em> and column </em><code>C<sub>j</sub></code><em> are equal</em>.</p>

<p>A row and column pair is considered equal if they contain the same elements in the same order (i.e. an equal array).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2352.Equal%20Row%20and%20Column%20Pairs/images/ex1.jpg" style="width: 150px; height: 153px;" />
<pre>
<strong>Input:</strong> grid = [[3,2,1],[1,7,6],[2,7,7]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is 1 equal row and column pair:
- (Row 2, Column 1): [2,7,7]
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2352.Equal%20Row%20and%20Column%20Pairs/images/ex2.jpg" style="width: 200px; height: 209px;" />
<pre>
<strong>Input:</strong> grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 equal row and column pairs:
- (Row 0, Column 0): [3,1,2,2]
- (Row 2, Column 2): [2,4,2,2]
- (Row 3, Column 2): [2,4,2,2]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def equalPairs(self, grid: List[List[int]]) -> int:
        n = len(grid)
        g = []
        for j in range(n):
            t = []
            for i in range(n):
                t.append(grid[i][j])
            g.append(t)
        return sum(row == col for row in grid for col in g)
```

### **Java**

```java
class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        int[][] g = new int[n][n];
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < n; ++i) {
                g[i][j] = grid[j][i];
            }
        }
        int ans = 0;
        for (int[] row : grid) {
            for (int[] col : g) {
                boolean ok = true;
                for (int i = 0; i < n; ++i) {
                    if (row[i] != col[i]) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int equalPairs(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<vector<int>> g(n, vector<int>(n));
        for (int j = 0; j < n; ++j)
            for (int i = 0; i < n; ++i)
                g[i][j] = grid[j][i];
        int ans = 0;
        for (auto row : grid)
            for (auto col : g)
                ans += row == col;
        return ans;
    }
};
```

### **Go**

```go
func equalPairs(grid [][]int) int {
	n := len(grid)
	g := make([][]int, n)
	for i := range g {
		g[i] = make([]int, n)
	}
	for j := 0; j < n; j++ {
		for i := 0; i < n; i++ {
			g[i][j] = grid[j][i]
		}
	}
	ans := 0
	for _, row := range grid {
		for _, col := range g {
			ok := true
			for i, v := range row {
				if v != col[i] {
					ok = false
					break
				}
			}
			if ok {
				ans++
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
