# [1730. 获取食物的最短路径](https://leetcode.cn/problems/shortest-path-to-get-food)

[English Version](/solution/1700-1799/1730.Shortest%20Path%20to%20Get%20Food/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你现在很饿，想要尽快找东西吃。你需要找到最短的路径到达一个食物所在的格子。</p>

<p>给定一个&nbsp;<code>m x n</code>&nbsp;的字符矩阵&nbsp;<code>grid</code>&nbsp;，包含下列不同类型的格子：</p>

<ul>
	<li><code>'*'</code>&nbsp;是你的位置。矩阵中<strong>有且只有一个&nbsp;</strong><code>'*'</code>&nbsp;格子。</li>
	<li><code>'#'</code> 是食物。矩阵中可能存在<strong>多个</strong>食物。</li>
	<li><code>'O'</code>&nbsp;是空地，你可以穿过这些格子。</li>
	<li><code>'X'</code>&nbsp;是障碍，你不可以穿过这些格子。</li>
</ul>

<p>返回你到任意食物的最短路径的长度。如果不存在你到任意食物的路径，返回&nbsp;<code>-1</code>。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1730.Shortest%20Path%20to%20Get%20Food/images/img1.jpg" style="width: 300px; height: 201px;" />
<pre>
<b>输入：</b> grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
<b>输出：</b> 3
<b>解释： </b>要拿到食物，你需要走 3 步。</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1730.Shortest%20Path%20to%20Get%20Food/images/img2.jpg" style="width: 300px; height: 241px;" />
<pre>
<b>输入：</b> grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
<b>输出：</b> -1
<b>解释：</b> 你不可能拿到食物。
</pre>

<p><strong>示例&nbsp;3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1730.Shortest%20Path%20to%20Get%20Food/images/img3.jpg" style="width: 300px; height: 188px;" />
<pre>
<strong>输入:</strong> grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
<strong>输出:</strong> 6
<strong>解释:</strong> 这里有多个食物。拿到下边的食物仅需走 6 步。</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>grid[row][col]</code>&nbsp;是&nbsp;<code>'*'</code>、&nbsp;<code>'X'</code>、&nbsp;<code>'O'</code>&nbsp;或&nbsp;<code>'#'</code>&nbsp;。</li>
	<li><code>grid</code>&nbsp;中<strong>有且只有一个</strong>&nbsp;<code>'*'</code>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

根据题意，我们需要从 `*` 出发，找到最近的 `#`，返回最短路径长度。

首先，我们遍历整个二维数组，找到 `*` 的位置，将其作为 BFS 的起点，放入队列中。

然后，我们开始 BFS，遍历队列中的元素，每次遍历到一个元素，我们将其上下左右四个方向的元素加入队列中，直到遇到 `#`，返回当前层数。

时间复杂度 $O(m \times n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别为二维数组的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getFood(self, grid: List[List[str]]) -> int:
        m, n = len(grid), len(grid[0])
        i, j = next((i, j) for i in range(m)
                    for j in range(n) if grid[i][j] == '*')
        q = deque([(i, j)])
        dirs = (-1, 0, 1, 0, -1)
        ans = 0
        while q:
            ans += 1
            for _ in range(len(q)):
                i, j = q.popleft()
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n:
                        if grid[x][y] == '#':
                            return ans
                        if grid[x][y] == 'O':
                            grid[x][y] = 'X'
                            q.append((x, y))
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] dirs = {-1, 0, 1, 0, -1};

    public int getFood(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0, x = 1; i < m && x == 1; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '*') {
                    q.offer(new int[] {i, j});
                    x = 0;
                    break;
                }
            }
        }
        int ans = 0;
        while (!q.isEmpty()) {
            ++ans;
            for (int t = q.size(); t > 0; --t) {
                var p = q.poll();
                for (int k = 0; k < 4; ++k) {
                    int x = p[0] + dirs[k];
                    int y = p[1] + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        if (grid[x][y] == '#') {
                            return ans;
                        }
                        if (grid[x][y] == 'O') {
                            grid[x][y] = 'X';
                            q.offer(new int[] {x, y});
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
    const static inline vector<int> dirs = {-1, 0, 1, 0, -1};

    int getFood(vector<vector<char>>& grid) {
        int m = grid.size(), n = grid[0].size();
        queue<pair<int, int>> q;
        for (int i = 0, x = 1; i < m && x == 1; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '*') {
                    q.emplace(i, j);
                    x = 0;
                    break;
                }
            }
        }
        int ans = 0;
        while (!q.empty()) {
            ++ans;
            for (int t = q.size(); t; --t) {
                auto [i, j] = q.front();
                q.pop();
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        if (grid[x][y] == '#') return ans;
                        if (grid[x][y] == 'O') {
                            grid[x][y] = 'X';
                            q.emplace(x, y);
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
func getFood(grid [][]byte) (ans int) {
	m, n := len(grid), len(grid[0])
	dirs := []int{-1, 0, 1, 0, -1}
	type pair struct{ i, j int }
	q := []pair{}
	for i, x := 0, 1; i < m && x == 1; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == '*' {
				q = append(q, pair{i, j})
				x = 0
				break
			}
		}
	}
	for len(q) > 0 {
		ans++
		for t := len(q); t > 0; t-- {
			p := q[0]
			q = q[1:]
			for k := 0; k < 4; k++ {
				x, y := p.i+dirs[k], p.j+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n {
					if grid[x][y] == '#' {
						return ans
					}
					if grid[x][y] == 'O' {
						grid[x][y] = 'X'
						q = append(q, pair{x, y})
					}
				}
			}
		}
	}
	return -1
}
```

### **JavaScript**

```js
/**
 * @param {character[][]} grid
 * @return {number}
 */
var getFood = function (grid) {
    const m = grid.length;
    const n = grid[0].length;
    const dirs = [-1, 0, 1, 0, -1];
    const q = [];
    for (let i = 0, x = 1; i < m && x == 1; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == '*') {
                q.push([i, j]);
                x = 0;
                break;
            }
        }
    }
    let ans = 0;
    while (q.length) {
        ++ans;
        for (let t = q.length; t > 0; --t) {
            const [i, j] = q.shift();
            for (let k = 0; k < 4; ++k) {
                const x = i + dirs[k];
                const y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    if (grid[x][y] == '#') {
                        return ans;
                    }
                    if (grid[x][y] == 'O') {
                        grid[x][y] = 'X';
                        q.push([x, y]);
                    }
                }
            }
        }
    }
    return -1;
};
```

### **...**

```

```

<!-- tabs:end -->
