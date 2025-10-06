---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0778.Swim%20in%20Rising%20Water/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Array
    - Binary Search
    - Matrix
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [778. Swim in Rising Water](https://leetcode.com/problems/swim-in-rising-water)

[中文文档](/solution/0700-0799/0778.Swim%20in%20Rising%20Water/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>n x n</code> integer matrix <code>grid</code> where each value <code>grid[i][j]</code> represents the elevation at that point <code>(i, j)</code>.</p>

<p>It starts raining, and water gradually rises over time. At time <code>t</code>, the water level is <code>t</code>, meaning <strong>any</strong> cell with elevation less than equal to <code>t</code> is submerged or reachable.</p>

<p>You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most <code>t</code>. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.</p>

<p>Return <em>the minimum time until you can reach the bottom right square </em><code>(n - 1, n - 1)</code><em> if you start at the top left square </em><code>(0, 0)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0778.Swim%20in%20Rising%20Water/images/swim1-grid.jpg" style="width: 164px; height: 165px;" />
<pre>
<strong>Input:</strong> grid = [[0,2],[1,3]]
<strong>Output:</strong> 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0778.Swim%20in%20Rising%20Water/images/swim2-grid-1.jpg" style="width: 404px; height: 405px;" />
<pre>
<strong>Input:</strong> grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
<strong>Output:</strong> 16
<strong>Explanation:</strong> The final route is shown.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;&nbsp;n<sup>2</sup></code></li>
	<li>Each value <code>grid[i][j]</code> is <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Union Find

We can map each position $(i, j)$ to an ID $id = i \times n + j$, and use a union-find data structure to maintain connected components.

First, we use a one-dimensional array $hi$ to record the position ID corresponding to each height, i.e., $hi[h]$ represents the position ID with height $h$.

Then we traverse from height $0$ to height $n^2 - 1$. For each height $t$, we merge position $hi[t]$ with its four adjacent positions that have heights not exceeding $t$. If after merging, position $0$ and position $n^2 - 1$ are connected, then we have found the minimum time $t$, and we return $t$.

The time complexity is $O(n^2 \times \log n)$ and the space complexity is $O(n^2)$, where $n$ is the side length of the matrix.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def swimInWater(self, grid: List[List[int]]) -> int:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = len(grid)
        m = n * n
        p = list(range(m))
        hi = [0] * m
        for i, row in enumerate(grid):
            for j, h in enumerate(row):
                hi[h] = i * n + j
        dirs = (-1, 0, 1, 0, -1)
        for t in range(m):
            x, y = divmod(hi[t], n)
            for dx, dy in pairwise(dirs):
                nx, ny = x + dx, y + dy
                if 0 <= nx < n and 0 <= ny < n and grid[nx][ny] <= t:
                    p[find(x * n + y)] = find(nx * n + ny)
            if find(0) == find(m - 1):
                return t
        return 0
```

#### Java

```java
class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int m = n * n;
        int[] p = new int[m];
        Arrays.setAll(p, i -> i);
        IntUnaryOperator find = new IntUnaryOperator() {
            @Override
            public int applyAsInt(int x) {
                if (p[x] != x) p[x] = applyAsInt(p[x]);
                return p[x];
            }
        };

        int[] hi = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                hi[grid[i][j]] = i * n + j;
            }
        }

        int[] dirs = {-1, 0, 1, 0, -1};

        for (int t = 0; t < m; t++) {
            int id = hi[t];
            int x = id / n, y = id % n;
            for (int k = 0; k < 4; k++) {
                int nx = x + dirs[k], ny = y + dirs[k + 1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] <= t) {
                    int a = find.applyAsInt(x * n + y);
                    int b = find.applyAsInt(nx * n + ny);
                    p[a] = b;
                }
            }
            if (find.applyAsInt(0) == find.applyAsInt(m - 1)) {
                return t;
            }
        }
        return 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int swimInWater(vector<vector<int>>& grid) {
        int n = grid.size();
        int m = n * n;
        vector<int> p(m);
        iota(p.begin(), p.end(), 0);

        auto find = [&](this auto&& find, int x) -> int {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };

        vector<int> hi(m);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                hi[grid[i][j]] = i * n + j;
            }
        }

        array<int, 5> dirs{-1, 0, 1, 0, -1};

        for (int t = 0; t < m; ++t) {
            int id = hi[t];
            int x = id / n, y = id % n;
            for (int k = 0; k < 4; ++k) {
                int nx = x + dirs[k], ny = y + dirs[k + 1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] <= t) {
                    int a = find(x * n + y);
                    int b = find(nx * n + ny);
                    p[a] = b;
                }
            }
            if (find(0) == find(m - 1)) {
                return t;
            }
        }
        return 0;
    }
};
```

#### Go

```go
func swimInWater(grid [][]int) int {
	n := len(grid)
	m := n * n
	p := make([]int, m)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	hi := make([]int, m)
	for i := range grid {
		for j, h := range grid[i] {
			hi[h] = i*n + j
		}
	}
	dirs := []int{-1, 0, 1, 0, -1}
	for t := 0; t < m; t++ {
		id := hi[t]
		x, y := id/n, id%n
		for k := 0; k < 4; k++ {
			nx, ny := x+dirs[k], y+dirs[k+1]
			if nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] <= t {
				a := find(x*n + y)
				b := find(nx*n + ny)
				p[a] = b
			}
		}
		if find(0) == find(m-1) {
			return t
		}
	}
	return 0
}
```

#### TypeScript

```ts
function swimInWater(grid: number[][]): number {
    const n = grid.length;
    const m = n * n;
    const p = Array.from({ length: m }, (_, i) => i);
    const hi = new Array<number>(m);
    const find = (x: number): number => (p[x] === x ? x : (p[x] = find(p[x])));

    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            hi[grid[i][j]] = i * n + j;
        }
    }

    const dirs = [-1, 0, 1, 0, -1];

    for (let t = 0; t < m; ++t) {
        const id = hi[t];
        const x = Math.floor(id / n);
        const y = id % n;

        for (let k = 0; k < 4; ++k) {
            const nx = x + dirs[k];
            const ny = y + dirs[k + 1];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] <= t) {
                p[find(x * n + y)] = find(nx * n + ny);
            }
        }
        if (find(0) === find(m - 1)) {
            return t;
        }
    }

    return 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn swim_in_water(grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let m = n * n;
        let mut p: Vec<usize> = (0..m).collect();
        let mut hi = vec![0usize; m];

        for i in 0..n {
            for j in 0..n {
                hi[grid[i][j] as usize] = i * n + j;
            }
        }

        fn find(x: usize, p: &mut Vec<usize>) -> usize {
            if p[x] != x {
                p[x] = find(p[x], p);
            }
            p[x]
        }

        let dirs = [-1isize, 0, 1, 0, -1];

        for t in 0..m {
            let id = hi[t];
            let x = id / n;
            let y = id % n;

            for k in 0..4 {
                let nx = x as isize + dirs[k];
                let ny = y as isize + dirs[k + 1];
                if nx >= 0 && nx < n as isize && ny >= 0 && ny < n as isize {
                    let nx = nx as usize;
                    let ny = ny as usize;
                    if grid[nx][ny] as usize <= t {
                        let a = find(x * n + y, &mut p);
                        let b = find(nx * n + ny, &mut p);
                        p[a] = b;
                    }
                }
            }

            if find(0, &mut p) == find(m - 1, &mut p) {
                return t as i32;
            }
        }

        0
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
