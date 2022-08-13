# [694. 不同岛屿的数量](https://leetcode.cn/problems/number-of-distinct-islands)

[English Version](/solution/0600-0699/0694.Number%20of%20Distinct%20Islands/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非空 01 二维数组表示的网格，一个岛屿由四连通（上、下、左、右四个方向）的 <code>1</code> 组成，你可以认为网格的四周被海水包围。</p>

<p>请你计算这个网格中共有多少个形状不同的岛屿。两个岛屿被认为是相同的，当且仅当一个岛屿可以通过平移变换（不可以旋转、翻转）和另一个岛屿重合。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>11000
11000
00011
00011
</pre>

<p>给定上图，返回结果 <code>1</code> 。</p>

<p><strong>示例 2：</strong></p>

<pre>11011
10000
00001
11011</pre>

<p>给定上图，返回结果 <code>3</code> 。<br>
<br>
<strong>注意：</strong></p>

<pre>11
1
</pre>

<p>和</p>

<pre> 1
11
</pre>

<p>是不同的岛屿，因为我们不考虑旋转、翻转操作。</p>

<p>&nbsp;</p>

<p><strong>提示：</strong>二维数组每维的大小都不会超过 50 。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

遍历网格，若坐标点 `(i, j)` 对应的值是 1，进行 DFS 遍历，找到对应的岛屿，并且将遍历方向进行序列化，用哈希表存储。最后返回哈希表的大小即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numDistinctIslands(self, grid: List[List[int]]) -> int:
        def dfs(i, j, direction, path):
            grid[i][j] = 0
            path.append(str(direction))
            dirs = [-1, 0, 1, 0, -1]
            for k in range(1, 5):
                x, y = i + dirs[k - 1], j + dirs[k]
                if 0 <= x < m and 0 <= y < n and grid[x][y] == 1:
                    dfs(x, y, k, path)
            path.append(str(-direction))

        paths = set()
        path = []
        m, n = len(grid), len(grid[0])
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    dfs(i, j, 0, path)
                    paths.add(''.join(path))
                    path.clear()
        return len(paths)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int m;
    private int n;
    private int[][] grid;

    public int numDistinctIslands(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        Set<String> paths = new HashSet<>();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    StringBuilder path = new StringBuilder();
                    dfs(i, j, 0, path);
                    paths.add(path.toString());
                }
            }
        }
        return paths.size();
    }

    private void dfs(int i, int j, int direction, StringBuilder path) {
        grid[i][j] = 0;
        path.append(direction);
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 1; k < 5; ++k) {
            int x = i + dirs[k - 1];
            int y = j + dirs[k];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                dfs(x, y, k, path);
            }
        }
        path.append(direction);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numDistinctIslands(vector<vector<int>>& grid) {
        unordered_set<string> paths;
        string path;
        for (int i = 0; i < grid.size(); ++i) {
            for (int j = 0; j < grid[0].size(); ++j) {
                if (grid[i][j] == 1) {
                    path = "";
                    dfs(i, j, 0, grid, path);
                    paths.insert(path);
                }
            }
        }
        return paths.size();
    }

    void dfs(int i, int j, int direction, vector<vector<int>>& grid, string& path) {
        grid[i][j] = 0;
        path += to_string(direction);
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 1; k < 5; ++k) {
            int x = i + dirs[k - 1], y = j + dirs[k];
            if (x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size() && grid[x][y] == 1)
                dfs(x, y, k, grid, path);
        }
        path += to_string(direction);
    }
};
```

### **Go**

```go
func numDistinctIslands(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	paths := make(map[string]bool)
	path := ""
	var dfs func(i, j, direction int)
	dfs = func(i, j, direction int) {
		grid[i][j] = 0
		path += strconv.Itoa(direction)
		dirs := []int{-1, 0, 1, 0, -1}
		for k := 1; k < 5; k++ {
			x, y := i+dirs[k-1], j+dirs[k]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
				dfs(x, y, k)
			}
		}
		path += strconv.Itoa(direction)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				path = ""
				dfs(i, j, 0)
				paths[path] = true
			}
		}
	}
	return len(paths)
}
```

### **...**

```

```

<!-- tabs:end -->
