---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3651.Minimum%20Cost%20Path%20with%20Teleportations/README_EN.md
rating: 2411
source: Biweekly Contest 163 Q4
---

<!-- problem:start -->

# [3651. Minimum Cost Path with Teleportations](https://leetcode.com/problems/minimum-cost-path-with-teleportations)

[中文文档](/solution/3600-3699/3651.Minimum%20Cost%20Path%20with%20Teleportations/README.md)

## Description

<!-- description:start -->

<p>You are given a <code>m x n</code> 2D integer array <code>grid</code> and an integer <code>k</code>. You start at the top-left cell <code>(0, 0)</code> and your goal is to reach the bottom‐right cell <code>(m - 1, n - 1)</code>.</p>

<p>There are two types of moves available:</p>

<ul>
	<li>
	<p><strong>Normal move</strong>: You can move right or down from your current cell <code>(i, j)</code>, i.e. you can move to <code>(i, j + 1)</code> (right) or <code>(i + 1, j)</code> (down). The cost is the value of the destination cell.</p>
	</li>
	<li>
	<p><strong>Teleportation</strong>: You can teleport from any cell <code>(i, j)</code>, to any cell <code>(x, y)</code> such that <code>grid[x][y] &lt;= grid[i][j]</code>; the cost of this move is 0. You may teleport at most <code>k</code> times.</p>
	</li>
</ul>

<p>Return the <strong>minimum</strong> total cost to reach cell <code>(m - 1, n - 1)</code> from <code>(0, 0)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,3,3],[2,5,4],[4,3,5]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>Initially we are at (0, 0) and cost is 0.</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Current Position</th>
			<th style="border: 1px solid black;">Move</th>
			<th style="border: 1px solid black;">New Position</th>
			<th style="border: 1px solid black;">Total Cost</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>(0, 0)</code></td>
			<td style="border: 1px solid black;">Move Down</td>
			<td style="border: 1px solid black;"><code>(1, 0)</code></td>
			<td style="border: 1px solid black;"><code>0 + 2 = 2</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>(1, 0)</code></td>
			<td style="border: 1px solid black;">Move Right</td>
			<td style="border: 1px solid black;"><code>(1, 1)</code></td>
			<td style="border: 1px solid black;"><code>2 + 5 = 7</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>(1, 1)</code></td>
			<td style="border: 1px solid black;">Teleport to <code>(2, 2)</code></td>
			<td style="border: 1px solid black;"><code>(2, 2)</code></td>
			<td style="border: 1px solid black;"><code>7 + 0 = 7</code></td>
		</tr>
	</tbody>
</table>

<p>The minimum cost to reach bottom-right cell is 7.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,2],[2,3],[3,4]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation: </strong></p>

<p>Initially we are at (0, 0) and cost is 0.</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Current Position</th>
			<th style="border: 1px solid black;">Move</th>
			<th style="border: 1px solid black;">New Position</th>
			<th style="border: 1px solid black;">Total Cost</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>(0, 0)</code></td>
			<td style="border: 1px solid black;">Move Down</td>
			<td style="border: 1px solid black;"><code>(1, 0)</code></td>
			<td style="border: 1px solid black;"><code>0 + 2 = 2</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>(1, 0)</code></td>
			<td style="border: 1px solid black;">Move Right</td>
			<td style="border: 1px solid black;"><code>(1, 1)</code></td>
			<td style="border: 1px solid black;"><code>2 + 3 = 5</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>(1, 1)</code></td>
			<td style="border: 1px solid black;">Move Down</td>
			<td style="border: 1px solid black;"><code>(2, 1)</code></td>
			<td style="border: 1px solid black;"><code>5 + 4 = 9</code></td>
		</tr>
	</tbody>
</table>

<p>The minimum cost to reach bottom-right cell is 9.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= m, n &lt;= 80</code></li>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[t][i][j]$ as the minimum cost to reach cell $(i, j)$ using exactly $t$ teleportations. Initially, $f[0][0][0] = 0$, and all other states are infinity.

First, we need to initialize $f[0][i][j]$. Without using teleportation, we can only reach cell $(i, j)$ by moving right or down.

If $i > 0$, we can move from the cell above $(i-1, j)$, updating the state as:

$$f[0][i][j] = \min(f[0][i][j], f[0][i-1][j] + grid[i][j])$$

If $j > 0$, we can move from the cell to the left $(i, j-1)$, updating the state as:

$$f[0][i][j] = \min(f[0][i][j], f[0][i][j-1] + grid[i][j])$$

To handle teleportation, we need to group the cells in the grid by their values. We use a hash map $g$, where the key is the cell value and the value is a list of coordinates of cells with that value.

For each teleportation count $t$ from $1$ to $k$, we process each group in descending order of cell values. For each cell $(i, j)$ in a group, we first update a global minimum $mn$, representing the minimum cost to reach these cells using $t-1$ teleportations:

$$mn = \min(mn, f[t-1][i][j])$$

Then, we update the state of all cells in the group to $mn$, representing the minimum cost to reach these cells via teleportation.

Next, we traverse the entire grid again to update $f[t][i][j]$, considering moves from the top or left cells:

If $i > 0$, then:

$$f[t][i][j] = \min(f[t][i][j], f[t][i-1][j] + grid[i][j])$$

If $j > 0$, then:

$$f[t][i][j] = \min(f[t][i][j], f[t][i][j-1] + grid[i][j])$$

Finally, our answer is $\min(f[t][m-1][n-1])$, where $t$ ranges from $0$ to $k$.

The time complexity is $O((k + \log mn) \times mn)$, and the space complexity is $O(k \times mn)$. Here, $m$ and $n$ are the number of rows and columns of the grid, respectively, and $k$ is the maximum allowed number of teleportations.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, grid: List[List[int]], k: int) -> int:
        m, n = len(grid), len(grid[0])
        f = [[[inf] * n for _ in range(m)] for _ in range(k + 1)]
        f[0][0][0] = 0
        for i in range(m):
            for j in range(n):
                if i:
                    f[0][i][j] = min(f[0][i][j], f[0][i - 1][j] + grid[i][j])
                if j:
                    f[0][i][j] = min(f[0][i][j], f[0][i][j - 1] + grid[i][j])
        g = defaultdict(list)
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                g[x].append((i, j))
        keys = sorted(g, reverse=True)
        for t in range(1, k + 1):
            mn = inf
            for key in keys:
                pos = g[key]
                for i, j in pos:
                    mn = min(mn, f[t - 1][i][j])
                for i, j in pos:
                    f[t][i][j] = mn
            for i in range(m):
                for j in range(n):
                    if i:
                        f[t][i][j] = min(f[t][i][j], f[t][i - 1][j] + grid[i][j])
                    if j:
                        f[t][i][j] = min(f[t][i][j], f[t][i][j - 1] + grid[i][j])
        return min(f[t][m - 1][n - 1] for t in range(k + 1))
```

#### Java

```java
class Solution {
    public int minCost(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int inf = Integer.MAX_VALUE / 2;

        int[][][] f = new int[k + 1][m][n];
        for (int t = 0; t <= k; t++) {
            for (int i = 0; i < m; i++) {
                Arrays.fill(f[t][i], inf);
            }
        }

        f[0][0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    f[0][i][j] = Math.min(f[0][i][j], f[0][i - 1][j] + grid[i][j]);
                }
                if (j > 0) {
                    f[0][i][j] = Math.min(f[0][i][j], f[0][i][j - 1] + grid[i][j]);
                }
            }
        }

        Map<Integer, List<int[]>> g = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = grid[i][j];
                g.computeIfAbsent(x, z -> new ArrayList<>()).add(new int[] {i, j});
            }
        }

        List<Integer> keys = new ArrayList<>(g.keySet());
        keys.sort(Collections.reverseOrder());

        for (int t = 1; t <= k; t++) {
            int mn = inf;
            for (int key : keys) {
                List<int[]> pos = g.get(key);
                for (int[] p : pos) {
                    mn = Math.min(mn, f[t - 1][p[0]][p[1]]);
                }
                for (int[] p : pos) {
                    f[t][p[0]][p[1]] = mn;
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i > 0) {
                        f[t][i][j] = Math.min(f[t][i][j], f[t][i - 1][j] + grid[i][j]);
                    }
                    if (j > 0) {
                        f[t][i][j] = Math.min(f[t][i][j], f[t][i][j - 1] + grid[i][j]);
                    }
                }
            }
        }

        int ans = inf;
        for (int t = 0; t <= k; t++) {
            ans = Math.min(ans, f[t][m - 1][n - 1]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCost(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        int inf = INT_MAX / 2;

        vector<vector<vector<int>>> f(k + 1, vector<vector<int>>(m, vector<int>(n, inf)));

        f[0][0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    f[0][i][j] = min(f[0][i][j], f[0][i - 1][j] + grid[i][j]);
                }
                if (j > 0) {
                    f[0][i][j] = min(f[0][i][j], f[0][i][j - 1] + grid[i][j]);
                }
            }
        }

        unordered_map<int, vector<pair<int, int>>> g;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = grid[i][j];
                g[x].push_back({i, j});
            }
        }

        vector<int> keys;
        keys.reserve(g.size());
        for (auto& e : g) {
            keys.push_back(e.first);
        }
        sort(keys.begin(), keys.end(), greater<int>());

        for (int t = 1; t <= k; t++) {
            int mn = inf;
            for (int key : keys) {
                auto& pos = g[key];
                for (auto& p : pos) {
                    mn = min(mn, f[t - 1][p.first][p.second]);
                }
                for (auto& p : pos) {
                    f[t][p.first][p.second] = mn;
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i > 0) {
                        f[t][i][j] = min(f[t][i][j], f[t][i - 1][j] + grid[i][j]);
                    }
                    if (j > 0) {
                        f[t][i][j] = min(f[t][i][j], f[t][i][j - 1] + grid[i][j]);
                    }
                }
            }
        }

        int ans = inf;
        for (int t = 0; t <= k; t++) {
            ans = min(ans, f[t][m - 1][n - 1]);
        }
        return ans;
    }
};
```

#### Go

```go
func minCost(grid [][]int, k int) int {
	m, n := len(grid), len(grid[0])
	inf := int(^uint(0)>>1) / 4

	f := make([][][]int, k+1)
	for t := 0; t <= k; t++ {
		f[t] = make([][]int, m)
		for i := 0; i < m; i++ {
			f[t][i] = make([]int, n)
			for j := 0; j < n; j++ {
				f[t][i][j] = inf
			}
		}
	}

	f[0][0][0] = 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if i > 0 {
				f[0][i][j] = min(f[0][i][j], f[0][i-1][j]+grid[i][j])
			}
			if j > 0 {
				f[0][i][j] = min(f[0][i][j], f[0][i][j-1]+grid[i][j])
			}
		}
	}

	g := make(map[int][][]int)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			x := grid[i][j]
			g[x] = append(g[x], []int{i, j})
		}
	}

	keys := make([]int, 0, len(g))
	for key := range g {
		keys = append(keys, key)
	}
	sort.Sort(sort.Reverse(sort.IntSlice(keys)))

	for t := 1; t <= k; t++ {
		mn := inf
		for _, key := range keys {
			pos := g[key]
			for _, p := range pos {
				mn = min(mn, f[t-1][p[0]][p[1]])
			}
			for _, p := range pos {
				f[t][p[0]][p[1]] = mn
			}
		}
		for i := 0; i < m; i++ {
			for j := 0; j < n; j++ {
				if i > 0 {
					f[t][i][j] = min(f[t][i][j], f[t][i-1][j]+grid[i][j])
				}
				if j > 0 {
					f[t][i][j] = min(f[t][i][j], f[t][i][j-1]+grid[i][j])
				}
			}
		}
	}

	ans := inf
	for t := 0; t <= k; t++ {
		ans = min(ans, f[t][m-1][n-1])
	}
	return ans
}
```

#### TypeScript

```ts
function minCost(grid: number[][], k: number): number {
    const [m, n] = [grid.length, grid[0].length];
    const INF = 1e18;

    const f: number[][][] = Array.from({ length: k + 1 }, () =>
        Array.from({ length: m }, () => Array(n).fill(INF)),
    );

    f[0][0][0] = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (i > 0) f[0][i][j] = Math.min(f[0][i][j], f[0][i - 1][j] + grid[i][j]);
            if (j > 0) f[0][i][j] = Math.min(f[0][i][j], f[0][i][j - 1] + grid[i][j]);
        }
    }

    const g = new Map<number, Array<[number, number]>>();
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const x = grid[i][j];
            if (!g.has(x)) {
                g.set(x, []);
            }
            g.get(x)!.push([i, j]);
        }
    }

    const keys = Array.from(g.keys()).sort((a, b) => b - a);

    for (let t = 1; t <= k; t++) {
        let mn = INF;

        for (const key of keys) {
            const pos = g.get(key)!;
            for (const [x, y] of pos) {
                mn = Math.min(mn, f[t - 1][x][y]);
            }
            for (const [x, y] of pos) {
                f[t][x][y] = mn;
            }
        }

        for (let i = 0; i < m; i++) {
            for (let j = 0; j < n; j++) {
                if (i > 0) {
                    f[t][i][j] = Math.min(f[t][i][j], f[t][i - 1][j] + grid[i][j]);
                }
                if (j > 0) {
                    f[t][i][j] = Math.min(f[t][i][j], f[t][i][j - 1] + grid[i][j]);
                }
            }
        }
    }

    let ans = INF;
    for (let t = 0; t <= k; t++) {
        ans = Math.min(ans, f[t][m - 1][n - 1]);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn min_cost(grid: Vec<Vec<i32>>, k: i32) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let k = k as usize;
        let inf: i32 = i32::MAX / 2;

        let mut f = vec![vec![vec![inf; n]; m]; k + 1];

        f[0][0][0] = 0;
        for i in 0..m {
            for j in 0..n {
                if i > 0 {
                    f[0][i][j] = f[0][i][j].min(f[0][i - 1][j] + grid[i][j]);
                }
                if j > 0 {
                    f[0][i][j] = f[0][i][j].min(f[0][i][j - 1] + grid[i][j]);
                }
            }
        }

        let mut g: HashMap<i32, Vec<(usize, usize)>> = HashMap::new();
        for i in 0..m {
            for j in 0..n {
                g.entry(grid[i][j]).or_default().push((i, j));
            }
        }

        let mut keys: Vec<i32> = g.keys().cloned().collect();
        keys.sort_by(|a, b| b.cmp(a));

        for t in 1..=k {
            let mut mn = inf;
            for &key in &keys {
                let pos = &g[&key];
                for &(i, j) in pos {
                    mn = mn.min(f[t - 1][i][j]);
                }
                for &(i, j) in pos {
                    f[t][i][j] = mn;
                }
            }
            for i in 0..m {
                for j in 0..n {
                    if i > 0 {
                        f[t][i][j] = f[t][i][j].min(f[t][i - 1][j] + grid[i][j]);
                    }
                    if j > 0 {
                        f[t][i][j] = f[t][i][j].min(f[t][i][j - 1] + grid[i][j]);
                    }
                }
            }
        }

        let mut ans = inf;
        for t in 0..=k {
            ans = ans.min(f[t][m - 1][n - 1]);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
