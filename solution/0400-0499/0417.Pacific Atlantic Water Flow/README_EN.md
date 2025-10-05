---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0417.Pacific%20Atlantic%20Water%20Flow/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Array
    - Matrix
---

<!-- problem:start -->

# [417. Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow)

[中文文档](/solution/0400-0499/0417.Pacific%20Atlantic%20Water%20Flow/README.md)

## Description

<!-- description:start -->

<p>There is an <code>m x n</code> rectangular island that borders both the <strong>Pacific Ocean</strong> and <strong>Atlantic Ocean</strong>. The <strong>Pacific Ocean</strong> touches the island&#39;s left and top edges, and the <strong>Atlantic Ocean</strong> touches the island&#39;s right and bottom edges.</p>

<p>The island is partitioned into a grid of square cells. You are given an <code>m x n</code> integer matrix <code>heights</code> where <code>heights[r][c]</code> represents the <strong>height above sea level</strong> of the cell at coordinate <code>(r, c)</code>.</p>

<p>The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell&#39;s height is <strong>less than or equal to</strong> the current cell&#39;s height. Water can flow from any cell adjacent to an ocean into the ocean.</p>

<p>Return <em>a <strong>2D list</strong> of grid coordinates </em><code>result</code><em> where </em><code>result[i] = [r<sub>i</sub>, c<sub>i</sub>]</code><em> denotes that rain water can flow from cell </em><code>(r<sub>i</sub>, c<sub>i</sub>)</code><em> to <strong>both</strong> the Pacific and Atlantic oceans</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0417.Pacific%20Atlantic%20Water%20Flow/images/waterflow-grid.jpg" style="width: 400px; height: 400px;" />
<pre>
<strong>Input:</strong> heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
<strong>Output:</strong> [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
<strong>Explanation:</strong> The following cells can flow to the Pacific and Atlantic oceans, as shown below:
[0,4]: [0,4] -&gt; Pacific Ocean 
&nbsp;      [0,4] -&gt; Atlantic Ocean
[1,3]: [1,3] -&gt; [0,3] -&gt; Pacific Ocean 
&nbsp;      [1,3] -&gt; [1,4] -&gt; Atlantic Ocean
[1,4]: [1,4] -&gt; [1,3] -&gt; [0,3] -&gt; Pacific Ocean 
&nbsp;      [1,4] -&gt; Atlantic Ocean
[2,2]: [2,2] -&gt; [1,2] -&gt; [0,2] -&gt; Pacific Ocean 
&nbsp;      [2,2] -&gt; [2,3] -&gt; [2,4] -&gt; Atlantic Ocean
[3,0]: [3,0] -&gt; Pacific Ocean 
&nbsp;      [3,0] -&gt; [4,0] -&gt; Atlantic Ocean
[3,1]: [3,1] -&gt; [3,0] -&gt; Pacific Ocean 
&nbsp;      [3,1] -&gt; [4,1] -&gt; Atlantic Ocean
[4,0]: [4,0] -&gt; Pacific Ocean 
       [4,0] -&gt; Atlantic Ocean
Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> heights = [[1]]
<strong>Output:</strong> [[0,0]]
<strong>Explanation:</strong> The water can flow from the only cell to the Pacific and Atlantic oceans.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == heights.length</code></li>
	<li><code>n == heights[r].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>0 &lt;= heights[r][c] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: BFS

We can start from the boundaries of the Pacific and Atlantic oceans and perform breadth-first search (BFS) respectively to find all cells that can flow to the Pacific and Atlantic oceans. Finally, we take the intersection of the two results, which represents cells that can flow to both the Pacific and Atlantic oceans.

Specifically, we define a queue $q_1$ to store all cells adjacent to the Pacific ocean, and define a boolean matrix $vis_1$ to record which cells can flow to the Pacific ocean. Similarly, we define queue $q_2$ and boolean matrix $vis_2$ to handle the Atlantic ocean. Initially, we add all cells adjacent to the Pacific ocean to queue $q_1$ and mark them as visited in $vis_1$. Similarly, we add all cells adjacent to the Atlantic ocean to queue $q_2$ and mark them as visited in $vis_2$.

Then, we perform BFS on $q_1$ and $q_2$ respectively. In each BFS, we dequeue a cell $(x, y)$ from the queue and check its four adjacent cells $(nx, ny)$. If an adjacent cell is within the matrix bounds, has not been visited, and its height is not less than the current cell's height (i.e., water can flow to that cell), we add it to the queue and mark it as visited.

Finally, we traverse the entire matrix to find cells that are marked as visited in both $vis_1$ and $vis_2$. These cells are our answer.

The time complexity is $O(m \times n)$ and the space complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns in the matrix, respectively.

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
