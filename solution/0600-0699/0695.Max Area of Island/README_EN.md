# [695. Max Area of Island](https://leetcode.com/problems/max-area-of-island)

[中文文档](/solution/0600-0699/0695.Max%20Area%20of%20Island/README.md)

## Description

<p>Given a non-empty 2D array <code>grid</code> of 0&#39;s and 1&#39;s, an <b>island</b> is a group of <code>1</code>&#39;s (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.</p>



<p>Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)</p>



<p><b>Example 1:</b></p>



<pre>

[[0,0,1,0,0,0,0,1,0,0,0,0,0],

 [0,0,0,0,0,0,0,1,1,1,0,0,0],

 [0,1,1,0,1,0,0,0,0,0,0,0,0],

 [0,1,0,0,1,1,0,0,<b>1</b>,0,<b>1</b>,0,0],

 [0,1,0,0,1,1,0,0,<b>1</b>,<b>1</b>,<b>1</b>,0,0],

 [0,0,0,0,0,0,0,0,0,0,<b>1</b>,0,0],

 [0,0,0,0,0,0,0,1,1,1,0,0,0],

 [0,0,0,0,0,0,0,1,1,0,0,0,0]]

</pre>

Given the above grid, return <code>6</code>. Note the answer is not 11, because the island must be connected 4-directionally.



<p><b>Example 2:</b></p>



<pre>

[[0,0,0,0,0,0,0,0]]</pre>

Given the above grid, return <code>0</code>.



<p><b>Note:</b> The length of each dimension in the given <code>grid</code> does not exceed 50.</p>



## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        def dfs(grid, i, j, m, n):
            if i < 0 or i >= m or j < 0 or j >= n or grid[i][j] == 0:
                return 0
            grid[i][j] = 0
            res = 1
            for x, y in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                res += dfs(grid, i + x, j + y, m, n)
            return res
        
        m, n = len(grid), len(grid[0])
        res = 0
        for i in range(m):
            for j in range(n):
                t = dfs(grid, i, j, m, n)
                res = max(res, t)
        return res
```

### **Java**

```java
class Solution {
    private int[][] directions = {{0, 1}, {0, - 1}, {1, 0}, {-1, 0}};

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int t = dfs(grid, i, j, m, n);
                res = Math.max(res, t);
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        int res = 1;
        for (int[] direction : directions) {
            res += dfs(grid, i + direction[0], j + direction[1], m, n);
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function maxAreaOfIsland(grid: number[][]): number {
    let m = grid.length, n = grid[0].length;
    let visited = Array.from({ length: m }, v => new Array(n).fill(false));
    let res = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (!visited[i][j]) {
                res = Math.max(dfs(grid, i, j, visited), res);
            }
        }
    }
    return res;
};

function dfs(grid: number[][], i: number, j: number, visited: boolean[][]): number {
    let m = grid.length, n = grid[0].length;
    if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || visited[i][j]) {
        return 0;
    }
    visited[i][j] = true;
    if (grid[i][j] == 0) {
        return 0;
    }
    let res = 1;
    for (let [dx, dy] of [[0, 1], [0, -1], [1, 0], [-1, 0]]) {
        res += dfs(grid, i + dx, j + dy, visited);
    }
    return res;
}
```

### **C++**

```cpp
class Solution {
public:
    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int t = dfs(grid, i, j, m, n);
                res = max(res, t);
            }
        }
        return res;
    }
private:
    vector<vector<int>> directions = {{0, 1}, {0, - 1}, {1, 0}, {-1, 0}};

    int dfs(vector<vector<int>>& grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        int res = 1;
        for (auto direction : directions) {
            res += dfs(grid, i + direction[0], j + direction[1], m, n);
        }
        return res;
    }

};
```

### **...**

```

```

<!-- tabs:end -->
