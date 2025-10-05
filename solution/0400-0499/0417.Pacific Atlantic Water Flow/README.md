---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0417.Pacific%20Atlantic%20Water%20Flow/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 数组
    - 矩阵
---

<!-- problem:start -->

# [417. 太平洋大西洋水流问题](https://leetcode.cn/problems/pacific-atlantic-water-flow)

[English Version](/solution/0400-0499/0417.Pacific%20Atlantic%20Water%20Flow/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有一个 <code>m × n</code> 的矩形岛屿，与 <strong>太平洋</strong> 和 <strong>大西洋</strong> 相邻。&nbsp;<strong>“太平洋”&nbsp;</strong>处于大陆的左边界和上边界，而 <strong>“大西洋”</strong> 处于大陆的右边界和下边界。</p>

<p>这个岛被分割成一个由若干方形单元格组成的网格。给定一个 <code>m x n</code> 的整数矩阵&nbsp;<code>heights</code>&nbsp;，&nbsp;<code>heights[r][c]</code>&nbsp;表示坐标 <code>(r, c)</code> 上单元格 <strong>高于海平面的高度</strong> 。</p>

<p>岛上雨水较多，如果相邻单元格的高度 <strong>小于或等于</strong> 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。</p>

<p>返回网格坐标 <code>result</code>&nbsp;的 <strong>2D 列表</strong> ，其中&nbsp;<code>result[i] = [r<sub>i</sub>, c<sub>i</sub>]</code>&nbsp;表示雨水从单元格 <code>(ri, ci)</code> 流动 <strong>既可流向太平洋也可流向大西洋</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0417.Pacific%20Atlantic%20Water%20Flow/images/waterflow-grid.jpg" /></p>

<pre>
<strong>输入:</strong> heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
<strong>输出:</strong> [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> heights = [[2,1],[1,2]]
<strong>输出:</strong> [[0,0],[0,1],[1,0],[1,1]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == heights.length</code></li>
	<li><code>n == heights[r].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>0 &lt;= heights[r][c] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

我们可以从太平洋和大西洋的边界出发，分别进行广度优先搜索（BFS），找到所有能够流向太平洋和大西洋的单元格。最后，我们取两个结果的交集，即为既能流向太平洋又能流向大西洋的单元格。

具体地，我们定义一个队列 $q_1$ 来存储所有与太平洋相邻的单元格，并定义一个布尔矩阵 $vis_1$ 来记录哪些单元格能够流向太平洋。类似地，我们定义队列 $q_2$ 和布尔矩阵 $vis_2$ 来处理大西洋。初始时，我们将所有与太平洋相邻的单元格加入队列 $q_1$，并将它们在 $vis_1$ 中标记为已访问。同样地，将所有与大西洋相邻的单元格加入队列 $q_2$，并在 $vis_2$ 中标记为已访问。

然后，我们分别对 $q_1$ 和 $q_2$ 进行 BFS。在每次 BFS 中，我们从队列中取出一个单元格 $(x, y)$，并检查它的四个相邻单元格 $(nx, ny)$。如果相邻单元格在矩阵范围内，且未被访问过，并且其高度不小于当前单元格的高度（即水可以流向该单元格），我们将其加入队列并标记为已访问。

最后，我们遍历整个矩阵，找出同时在 $vis_1$ 和 $vis_2$ 中被标记为已访问的单元格，这些单元格即为答案。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def pacificAtlantic(self, heights: List[List[int]]) -> List[List[int]]:
        def bfs(q: Deque[Tuple[int, int]], vis: List[List[bool]]) -> None:
            while q:
                x, y = q.popleft()
                for dx, dy in pairwise(dirs):
                    nx, ny = x + dx, y + dy
                    if (
                        0 <= nx < m
                        and 0 <= ny < n
                        and not vis[nx][ny]
                        and heights[nx][ny] >= heights[x][y]
                    ):
                        vis[nx][ny] = True
                        q.append((nx, ny))

        m, n = len(heights), len(heights[0])
        vis1 = [[False] * n for _ in range(m)]
        vis2 = [[False] * n for _ in range(m)]
        q1: Deque[Tuple[int, int]] = deque()
        q2: Deque[Tuple[int, int]] = deque()
        dirs = (-1, 0, 1, 0, -1)

        for i in range(m):
            q1.append((i, 0))
            vis1[i][0] = True
            q2.append((i, n - 1))
            vis2[i][n - 1] = True

        for j in range(n):
            q1.append((0, j))
            vis1[0][j] = True
            q2.append((m - 1, j))
            vis2[m - 1][j] = True

        bfs(q1, vis1)
        bfs(q2, vis2)

        return [(i, j) for i in range(m) for j in range(n) if vis1[i][j] and vis2[i][j]]
```

#### Java

```java
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        boolean[][] vis1 = new boolean[m][n];
        boolean[][] vis2 = new boolean[m][n];
        Deque<int[]> q1 = new ArrayDeque<>();
        Deque<int[]> q2 = new ArrayDeque<>();
        int[] dirs = {-1, 0, 1, 0, -1};

        for (int i = 0; i < m; ++i) {
            q1.offer(new int[] {i, 0});
            vis1[i][0] = true;
            q2.offer(new int[] {i, n - 1});
            vis2[i][n - 1] = true;
        }
        for (int j = 0; j < n; ++j) {
            q1.offer(new int[] {0, j});
            vis1[0][j] = true;
            q2.offer(new int[] {m - 1, j});
            vis2[m - 1][j] = true;
        }

        BiConsumer<Deque<int[]>, boolean[][]> bfs = (q, vis) -> {
            while (!q.isEmpty()) {
                var cell = q.poll();
                int x = cell[0], y = cell[1];
                for (int k = 0; k < 4; ++k) {
                    int nx = x + dirs[k], ny = y + dirs[k + 1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !vis[nx][ny]
                        && heights[nx][ny] >= heights[x][y]) {
                        vis[nx][ny] = true;
                        q.offer(new int[] {nx, ny});
                    }
                }
            }
        };

        bfs.accept(q1, vis1);
        bfs.accept(q2, vis2);

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (vis1[i][j] && vis2[i][j]) {
                    ans.add(List.of(i, j));
                }
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
    vector<vector<int>> pacificAtlantic(vector<vector<int>>& heights) {
        int m = heights.size(), n = heights[0].size();
        vector<vector<bool>> vis1(m, vector<bool>(n, false)), vis2(m, vector<bool>(n, false));
        queue<pair<int, int>> q1, q2;
        vector<int> dirs = {-1, 0, 1, 0, -1};

        for (int i = 0; i < m; ++i) {
            q1.emplace(i, 0);
            vis1[i][0] = true;
            q2.emplace(i, n - 1);
            vis2[i][n - 1] = true;
        }
        for (int j = 0; j < n; ++j) {
            q1.emplace(0, j);
            vis1[0][j] = true;
            q2.emplace(m - 1, j);
            vis2[m - 1][j] = true;
        }

        auto bfs = [&](queue<pair<int, int>>& q, vector<vector<bool>>& vis) {
            while (!q.empty()) {
                auto [x, y] = q.front();
                q.pop();
                for (int k = 0; k < 4; ++k) {
                    int nx = x + dirs[k], ny = y + dirs[k + 1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n
                        && !vis[nx][ny]
                        && heights[nx][ny] >= heights[x][y]) {
                        vis[nx][ny] = true;
                        q.emplace(nx, ny);
                    }
                }
            }
        };

        bfs(q1, vis1);
        bfs(q2, vis2);

        vector<vector<int>> ans;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (vis1[i][j] && vis2[i][j])
                    ans.push_back({i, j});
        return ans;
    }
};
```

#### Go

```go
func pacificAtlantic(heights [][]int) [][]int {
	m, n := len(heights), len(heights[0])
	vis1 := make([][]bool, m)
	vis2 := make([][]bool, m)
	for i := range vis1 {
		vis1[i] = make([]bool, n)
		vis2[i] = make([]bool, n)
	}
	q1, q2 := [][2]int{}, [][2]int{}
	dirs := [5]int{-1, 0, 1, 0, -1}

	for i := 0; i < m; i++ {
		q1 = append(q1, [2]int{i, 0})
		vis1[i][0] = true
		q2 = append(q2, [2]int{i, n - 1})
		vis2[i][n-1] = true
	}
	for j := 0; j < n; j++ {
		q1 = append(q1, [2]int{0, j})
		vis1[0][j] = true
		q2 = append(q2, [2]int{m - 1, j})
		vis2[m-1][j] = true
	}

	bfs := func(q [][2]int, vis [][]bool) {
		for len(q) > 0 {
			x, y := q[0][0], q[0][1]
			q = q[1:]
			for k := 0; k < 4; k++ {
				nx, ny := x+dirs[k], y+dirs[k+1]
				if nx >= 0 && nx < m && ny >= 0 && ny < n &&
					!vis[nx][ny] && heights[nx][ny] >= heights[x][y] {
					vis[nx][ny] = true
					q = append(q, [2]int{nx, ny})
				}
			}
		}
	}

	bfs(q1, vis1)
	bfs(q2, vis2)

	var ans [][]int
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if vis1[i][j] && vis2[i][j] {
				ans = append(ans, []int{i, j})
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function pacificAtlantic(heights: number[][]): number[][] {
    const m = heights.length,
        n = heights[0].length;
    const vis1: boolean[][] = Array.from({ length: m }, () => Array(n).fill(false));
    const vis2: boolean[][] = Array.from({ length: m }, () => Array(n).fill(false));
    const q1: [number, number][] = [];
    const q2: [number, number][] = [];
    const dirs = [-1, 0, 1, 0, -1];

    for (let i = 0; i < m; ++i) {
        q1.push([i, 0]);
        vis1[i][0] = true;
        q2.push([i, n - 1]);
        vis2[i][n - 1] = true;
    }
    for (let j = 0; j < n; ++j) {
        q1.push([0, j]);
        vis1[0][j] = true;
        q2.push([m - 1, j]);
        vis2[m - 1][j] = true;
    }

    const bfs = (q: [number, number][], vis: boolean[][]) => {
        while (q.length) {
            const [x, y] = q.shift()!;
            for (let k = 0; k < 4; ++k) {
                const nx = x + dirs[k],
                    ny = y + dirs[k + 1];
                if (
                    nx >= 0 &&
                    nx < m &&
                    ny >= 0 &&
                    ny < n &&
                    !vis[nx][ny] &&
                    heights[nx][ny] >= heights[x][y]
                ) {
                    vis[nx][ny] = true;
                    q.push([nx, ny]);
                }
            }
        }
    };

    bfs(q1, vis1);
    bfs(q2, vis2);

    const ans: number[][] = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (vis1[i][j] && vis2[i][j]) {
                ans.push([i, j]);
            }
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::VecDeque;

impl Solution {
    pub fn pacific_atlantic(heights: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let (m, n) = (heights.len(), heights[0].len());
        let mut vis1 = vec![vec![false; n]; m];
        let mut vis2 = vec![vec![false; n]; m];
        let mut q1 = VecDeque::new();
        let mut q2 = VecDeque::new();
        let dirs = [-1, 0, 1, 0, -1];

        for i in 0..m {
            q1.push_back((i, 0));
            vis1[i][0] = true;
            q2.push_back((i, n - 1));
            vis2[i][n - 1] = true;
        }
        for j in 0..n {
            q1.push_back((0, j));
            vis1[0][j] = true;
            q2.push_back((m - 1, j));
            vis2[m - 1][j] = true;
        }

        let bfs = |q: &mut VecDeque<(usize, usize)>, vis: &mut Vec<Vec<bool>>| {
            while let Some((x, y)) = q.pop_front() {
                for k in 0..4 {
                    let nx = x as i32 + dirs[k];
                    let ny = y as i32 + dirs[k + 1];
                    if nx >= 0
                        && nx < m as i32
                        && ny >= 0
                        && ny < n as i32
                        && !vis[nx as usize][ny as usize]
                        && heights[nx as usize][ny as usize] >= heights[x][y]
                    {
                        vis[nx as usize][ny as usize] = true;
                        q.push_back((nx as usize, ny as usize));
                    }
                }
            }
        };

        bfs(&mut q1, &mut vis1);
        bfs(&mut q2, &mut vis2);

        let mut ans = vec![];
        for i in 0..m {
            for j in 0..n {
                if vis1[i][j] && vis2[i][j] {
                    ans.push(vec![i as i32, j as i32]);
                }
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
