---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3905.Multi%20Source%20Flood%20Fill/README_EN.md
rating: 1671
source: Weekly Contest 498 Q3
tags:
    - Breadth-First Search
    - Array
    - Matrix
---

<!-- problem:start -->

# [3905. Multi Source Flood Fill](https://leetcode.com/problems/multi-source-flood-fill)

[中文文档](/solution/3900-3999/3905.Multi%20Source%20Flood%20Fill/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>n</code> and <code>m</code> representing the number of rows and columns of a grid, respectively.</p>

<p>You are also given a 2D integer array <code>sources</code>, where <code>sources[i] = [r<sub>i</sub>, c<sub>i</sub>, color<sub>​​​​​​​i</sub>]</code> indicates that the cell <code>(r<sub>i</sub>, c<sub>i</sub>)</code> is initially colored with <code>color<sub>i</sub></code>. All other cells are initially uncolored and represented as 0.</p>

<p>At each time step, every currently colored cell spreads its color to all adjacent <strong>uncolored</strong> cells in the four directions: up, down, left, and right. All spreads happen simultaneously.</p>

<p>If <strong>multiple</strong> colors reach the same uncolored cell at the same time step, the cell takes the color with the <strong>maximum</strong> value.</p>

<p>The process continues until no more cells can be colored.</p>

<p>Return a 2D integer array representing the final state of the grid, where each cell contains its final color.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, m = 3, sources = [[0,0,1],[2,2,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[1,1,2],[1,2,2],[2,2,2]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The grid at each time step is as follows:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3905.Multi%20Source%20Flood%20Fill/images/g50new.png" style="width: 500px; height: 174px;" />​​​​​​​</p>

<p>At time step 2, cells <code>(0, 2)</code>, <code>(1, 1)</code>, and <code>(2, 0)</code> are reached by both colors, so they are assigned color 2 as it has the maximum value among them.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, m = 3, sources = [[0,1,3],[1,1,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[3,3,3],[5,5,5],[5,5,5]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The grid at each time step is as follows:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3905.Multi%20Source%20Flood%20Fill/images/g51new.png" style="width: 500px; height: 177px;" /></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, m = 2, sources = [[1,1,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[5,5],[5,5]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The grid at each time step is as follows:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3905.Multi%20Source%20Flood%20Fill/images/g52new.png" style="width: 500px; height: 150px;" />​​​​​​​</p>

<p>Since there is only one source, all cells are assigned the same color.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, m &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= n * m &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= sources.length &lt;= n * m</code></li>
	<li><code>sources[i] = [r<sub>i</sub>, c<sub>i</sub>, color<sub>i</sub>]</code></li>
	<li><code>0 &lt;= r<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>0 &lt;= c<sub>i</sub> &lt;= m - 1</code></li>
	<li><code>1 &lt;= color<sub>i</sub> &lt;= 10<sup>6</sup>​​​​​​​</code></li>
	<li>All <code>(r<sub>i</sub>, c<sub>i</sub>​​​​​​​)</code> in <code>sources</code> are distinct.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Multi-source BFS

We can use multi-source BFS to simulate this process.

Define a queue $q$ to store the cells that are currently spreading their color. Initially, add all source cells to the queue and set their colors in the answer array $\textit{ans}$.

In each iteration, we use a hash table $\textit{vis}$ to record the cells visited at the current time step and the maximum color value for each cell. For each cell in the queue, we try to spread its color to the four adjacent directions (up, down, left, right). If a neighboring cell is uncolored, we add it to $\textit{vis}$ and update its color to be the maximum of the current cell's color and any existing color in $\textit{vis}$.

After processing all cells in the current queue, we clear the queue and add the cells from $\textit{vis}$ to the queue, updating their colors in the answer array $\textit{ans}$ accordingly.

We repeat this process until the queue is empty, meaning no more cells can be colored.

The time complexity is $O(n \times m)$ and the space complexity is $O(n \times m)$, where $n$ and $m$ are the number of rows and columns in the grid, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def colorGrid(self, n: int, m: int, sources: list[list[int]]) -> list[list[int]]:
        ans = [[0] * m for _ in range(n)]
        q = sources
        dirs = (-1, 0, 1, 0, -1)
        for r, c, color in q:
            ans[r][c] = color
        while q:
            vis = defaultdict(int)
            for r, c, color in q:
                for a, b in pairwise(dirs):
                    x, y = r + a, c + b
                    if not 0 <= x < n or not 0 <= y < m or ans[x][y]:
                        continue
                    vis[(x, y)] = max(vis[(x, y)], color)
            q.clear()
            for (x, y), color in vis.items():
                q.append((x, y, color))
                ans[x][y] = color
        return ans
```

#### Java

```java
class Solution {
    public int[][] colorGrid(int n, int m, int[][] sources) {
        int[][] ans = new int[n][m];
        List<int[]> q = new ArrayList<>();
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int[] s : sources) {
            ans[s[0]][s[1]] = s[2];
            q.add(new int[] {s[0], s[1], s[2]});
        }
        while (!q.isEmpty()) {
            Map<Long, Integer> vis = new HashMap<>();
            for (int[] curr : q) {
                int r = curr[0], c = curr[1], color = curr[2];
                for (int i = 0; i < 4; i++) {
                    int x = r + dirs[i], y = c + dirs[i + 1];
                    if (x >= 0 && x < n && y >= 0 && y < m && ans[x][y] == 0) {
                        long key = (long) x * m + y;
                        vis.put(key, Math.max(vis.getOrDefault(key, 0), color));
                    }
                }
            }
            q.clear();
            for (Map.Entry<Long, Integer> entry : vis.entrySet()) {
                int x = (int) (entry.getKey() / m);
                int y = (int) (entry.getKey() % m);
                int color = entry.getValue();
                ans[x][y] = color;
                q.add(new int[] {x, y, color});
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> colorGrid(int n, int m, vector<vector<int>>& sources) {
        vector<vector<int>> ans(n, vector<int>(m, 0));
        vector<array<int, 3>> q;
        int dirs[] = {-1, 0, 1, 0, -1};
        for (auto& s : sources) {
            ans[s[0]][s[1]] = s[2];
            q.push_back({s[0], s[1], s[2]});
        }
        while (!q.empty()) {
            unordered_map<long long, int> vis;
            for (auto& curr : q) {
                int r = curr[0], c = curr[1], color = curr[2];
                for (int i = 0; i < 4; i++) {
                    int x = r + dirs[i], y = c + dirs[i + 1];
                    if (x >= 0 && x < n && y >= 0 && y < m && ans[x][y] == 0) {
                        long long key = (long long) x * m + y;
                        if (color > vis[key]) {
                            vis[key] = color;
                        }
                    }
                }
            }
            q.clear();
            for (auto const& [key, color] : vis) {
                int x = key / m;
                int y = key % m;
                ans[x][y] = color;
                q.push_back({x, y, color});
            }
        }
        return ans;
    }
};
```

#### Go

```go
func colorGrid(n int, m int, sources [][]int) [][]int {
	ans := make([][]int, n)
	for i := range ans {
		ans[i] = make([]int, m)
	}
	q := make([][]int, len(sources))
	copy(q, sources)
	dirs := []int{-1, 0, 1, 0, -1}
	for _, s := range q {
		ans[s[0]][s[1]] = s[2]
	}
	for len(q) > 0 {
		vis := make(map[[2]int]int)
		for _, curr := range q {
			r, c, color := curr[0], curr[1], curr[2]
			for i := 0; i < 4; i++ {
				x, y := r+dirs[i], c+dirs[i+1]
				if x >= 0 && x < n && y >= 0 && y < m && ans[x][y] == 0 {
					if color > vis[[2]int{x, y}] {
						vis[[2]int{x, y}] = color
					}
				}
			}
		}
		q = nil
		for pos, color := range vis {
			ans[pos[0]][pos[1]] = color
			q = append(q, []int{pos[0], pos[1], color})
		}
	}
	return ans
}
```

#### TypeScript

```ts
function colorGrid(n: number, m: number, sources: number[][]): number[][] {
    const ans: number[][] = Array.from({ length: n }, () => Array(m).fill(0));
    let q: number[][] = [...sources.map(s => [...s])];
    const dirs = [-1, 0, 1, 0, -1];
    for (const [r, c, color] of q) {
        ans[r][c] = color;
    }
    while (q.length > 0) {
        const vis: Map<string, number> = new Map();
        for (const [r, c, color] of q) {
            for (let i = 0; i < 4; i++) {
                const x = r + dirs[i],
                    y = c + dirs[i + 1];
                if (x >= 0 && x < n && y >= 0 && y < m && ans[x][y] === 0) {
                    const key = `${x},${y}`;
                    vis.set(key, Math.max(vis.get(key) || 0, color));
                }
            }
        }
        q = [];
        for (const [key, color] of vis.entries()) {
            const [x, y] = key.split(',').map(Number);
            ans[x][y] = color;
            q.push([x, y, color]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
