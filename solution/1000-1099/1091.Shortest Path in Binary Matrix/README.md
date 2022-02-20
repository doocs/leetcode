# [1091. 二进制矩阵中的最短路径](https://leetcode-cn.com/problems/shortest-path-in-binary-matrix)

[English Version](/solution/1000-1099/1091.Shortest%20Path%20in%20Binary%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>n x n</code> 的二进制矩阵 <code>grid</code> 中，返回矩阵中最短 <strong>畅通路径</strong> 的长度。如果不存在这样的路径，返回 <code>-1</code> 。</p>

<p>二进制矩阵中的 畅通路径 是一条从 <strong>左上角</strong> 单元格（即，<code>(0, 0)</code>）到 右下角 单元格（即，<code>(n - 1, n - 1)</code>）的路径，该路径同时满足下述要求：</p>

<ul>
	<li>路径途经的所有单元格都的值都是 <code>0</code> 。</li>
	<li>路径中所有相邻的单元格应当在 <strong>8 个方向之一</strong> 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。</li>
</ul>

<p><strong>畅通路径的长度</strong> 是该路径途经的单元格总数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1091.Shortest%20Path%20in%20Binary%20Matrix/images/example1_1.png" style="width: 500px; height: 234px;" />
<pre>
<strong>输入：</strong>grid = [[0,1],[1,0]]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1091.Shortest%20Path%20in%20Binary%20Matrix/images/example2_1.png" style="height: 216px; width: 500px;" />
<pre>
<strong>输入：</strong>grid = [[0,0,0],[1,1,0],[1,1,0]]
<strong>输出：</strong>4
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>grid = [[1,0,0],[1,1,0],[1,1,0]]
<strong>输出：</strong>-1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 <= n <= 100</code></li>
	<li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

BFS 最短路问题。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shortestPathBinaryMatrix(self, grid: List[List[int]]) -> int:
        if grid[0][0]:
            return -1
        n = len(grid)
        q = deque([(0, 0)])
        grid[0][0] = 1
        ans = 0
        while q:
            ans += 1
            for _ in range(len(q), 0, -1):
                i, j = q.popleft()
                if (i, j) == (n - 1, n - 1):
                    return ans
                for x in range(i - 1, i + 2):
                    for y in range(j - 1, j + 2):
                        if 0 <= x < n and 0 <= y < n and grid[x][y] == 0:
                            q.append((x, y))
                            grid[x][y] = 1
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }
        int n = grid.length;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        grid[0][0] = 1;
        int ans = 0;
        while (!q.isEmpty()) {
            ++ans;
            for (int m = q.size(); m > 0; --m) {
                int[] p = q.poll();
                int i = p[0], j = p[1];
                if (i == n - 1 && j == n - 1) {
                    return ans;
                }
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                            q.offer(new int[]{x, y});
                            grid[x][y] = 1;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int shortestPathBinaryMatrix(vector<vector<int>>& grid) {
        if (grid[0][0]) return -1;
        int n = grid.size();
        queue<pair<int, int>> q;
        q.push({0, 0});
        grid[0][0] = 1;
        int ans = 0;
        while (!q.empty())
        {
            ++ans;
            for (int m = q.size(); m > 0; --m)
            {
                auto p = q.front();
                q.pop();
                int i = p.first, j = p.second;
                if (i == n - 1 && j == n - 1) return ans;
                for (int x = i - 1; x <= i + 1; ++x)
                {
                    for (int y = j - 1; y <= j + 1; ++y)
                    {
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0)
                        {
                            q.push({x, y});
                            grid[x][y] = 1;
                        }
                    }
                }
            }
        }
        return -1;
    }
};
```

### **Go**

```go
func shortestPathBinaryMatrix(grid [][]int) int {
	if grid[0][0] == 1 {
		return -1
	}
	n := len(grid)
	q := [][]int{[]int{0, 0}}
	grid[0][0] = 1
	ans := 0
	for len(q) > 0 {
		ans++
		for m := len(q); m > 0; m-- {
			p := q[0]
			q = q[1:]
			i, j := p[0], p[1]
			if i == n-1 && j == n-1 {
				return ans
			}
			for x := i - 1; x <= i+1; x++ {
				for y := j - 1; y <= j+1; y++ {
					if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0 {
						q = append(q, []int{x, y})
						grid[x][y] = 1
					}
				}
			}
		}
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
