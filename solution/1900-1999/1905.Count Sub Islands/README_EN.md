# [1905. Count Sub Islands](https://leetcode.com/problems/count-sub-islands)

[中文文档](/solution/1900-1999/1905.Count%20Sub%20Islands/README.md)

## Description

<p>You are given two <code>m x n</code> binary matrices <code>grid1</code> and <code>grid2</code> containing only <code>0</code>&#39;s (representing water) and <code>1</code>&#39;s (representing land). An <strong>island</strong> is a group of <code>1</code>&#39;s connected <strong>4-directionally</strong> (horizontal or vertical). Any cells outside of the grid are considered water cells.</p>

<p>An island in <code>grid2</code> is considered a <strong>sub-island </strong>if there is an island in <code>grid1</code> that contains <strong>all</strong> the cells that make up <strong>this</strong> island in <code>grid2</code>.</p>

<p>Return the <em><strong>number</strong> of islands in </em><code>grid2</code> <em>that are considered <strong>sub-islands</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1905.Count%20Sub%20Islands/test1.png" style="width: 493px; height: 205px;" />
<pre>
<strong>Input:</strong> grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
<strong>Output:</strong> 3
<strong>Explanation: </strong>In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1905.Count%20Sub%20Islands/testcasex2.png" style="width: 491px; height: 201px;" />
<pre>
<strong>Input:</strong> grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
<strong>Output:</strong> 2 
<strong>Explanation: </strong>In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid1.length == grid2.length</code></li>
	<li><code>n == grid1[i].length == grid2[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>grid1[i][j]</code> and <code>grid2[i][j]</code> are either <code>0</code> or <code>1</code>.</li>
</ul>


## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countSubIslands(self, grid1: List[List[int]], grid2: List[List[int]]) -> int:
        def dfs(grid1, grid2, i, j, m, n) -> bool:
            res = grid1[i][j] == 1
            grid2[i][j] = 0
            for x, y in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                a, b = i + x, j + y
                if a >= 0 and a < m and b >= 0 and b < n and grid2[a][b] == 1:
                    if not dfs(grid1, grid2, a, b, m, n):
                        res = False
            return res
        
        m, n = len(grid1), len(grid1[0])
        count = 0
        for i in range(m):
            for j in range(n):
                if grid2[i][j] == 1 and dfs(grid1, grid2, i, j, m, n):
                    count += 1
        return count
```

### **Java**

```java
class Solution {
    private int[][] directions = {{0, 1}, {0, - 1}, {1, 0}, {-1, 0}};

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length, n = grid1[0].length;
        int count = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1 && dfs(grid1, grid2, i, j, m, n)) {
                    ++count;
                }
            }
        }
        return count;
    }

    private boolean dfs(int[][] grid1, int[][] grid2, int i, int j, int m, int n) {
        boolean res = grid1[i][j] == 1;
        grid2[i][j] = 0;

        for (int[] direction : directions) {
            int a = i + direction[0], b = j + direction[1];
            if (a >= 0 && a < m && b >= 0 && b < n && grid2[a][b] == 1) {
                if (!dfs(grid1, grid2, a, b, m, n)) {
                    res = false;
                }
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countSubIslands(vector<vector<int>>& grid1, vector<vector<int>>& grid2) {
        int m = grid1.size(), n = grid1[0].size();
        int count = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1 && dfs(grid1, grid2, i, j, m, n)) {
                    ++count;
                }
            }
        }
        return count;
    }

private:
    vector<vector<int>> directions = {{0, 1}, {0, - 1}, {1, 0}, {-1, 0}};
    bool dfs(vector<vector<int>>& grid1, vector<vector<int>>& grid2, int i, int j, int m, int n) {
        bool res = grid1[i][j] == 1;
        grid2[i][j] = 0;

        for (auto direction : directions) {
            int a = i + direction[0], b = j + direction[1];
            if (a >= 0 && a < m && b >= 0 && b < n && grid2[a][b] == 1) {
                if (!dfs(grid1, grid2, a, b, m, n)) {
                    res = false;
                }
            }
        }
        return res;
    }
};
```

### **Go**

```go
func countSubIslands(grid1 [][]int, grid2 [][]int) int {
	m, n := len(grid1), len(grid1[0])
	count := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid2[i][j] == 1 && dfs(grid1, grid2, i, j, m, n) {
				count++
			}
		}
	}
	return count
}

func dfs(grid1 [][]int, grid2 [][]int, i, j, m, n int) bool {
	res := grid1[i][j] == 1
	grid2[i][j] = 0
	directions := [4][2]int{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}
	for _, direction := range directions {
		a, b := i+direction[0], j+direction[1]
		if a >= 0 && a < m && b >= 0 && b < n && grid2[a][b] == 1 {
			if !dfs(grid1, grid2, a, b, m, n) {
				res = false
			}
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
