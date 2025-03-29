---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1036.Escape%20a%20Large%20Maze/README_EN.md
rating: 2164
source: Weekly Contest 134 Q4
tags:
    - Depth-First Search
    - Breadth-First Search
    - Array
    - Hash Table
---

<!-- problem:start -->

# [1036. Escape a Large Maze](https://leetcode.com/problems/escape-a-large-maze)

[中文文档](/solution/1000-1099/1036.Escape%20a%20Large%20Maze/README.md)

## Description

<!-- description:start -->

<p>There is a 1 million by 1 million grid on an XY-plane, and the coordinates of each grid square are <code>(x, y)</code>.</p>

<p>We start at the <code>source = [s<sub>x</sub>, s<sub>y</sub>]</code> square and want to reach the <code>target = [t<sub>x</sub>, t<sub>y</sub>]</code> square. There is also an array of <code>blocked</code> squares, where each <code>blocked[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents a blocked square with coordinates <code>(x<sub>i</sub>, y<sub>i</sub>)</code>.</p>

<p>Each move, we can walk one square north, east, south, or west if the square is <strong>not</strong> in the array of <code>blocked</code> squares. We are also not allowed to walk outside of the grid.</p>

<p>Return <code>true</code><em> if and only if it is possible to reach the </em><code>target</code><em> square from the </em><code>source</code><em> square through a sequence of valid moves</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
<strong>Output:</strong> false
<strong>Explanation:</strong> The target square is inaccessible starting from the source square because we cannot move.
We cannot move north or east because those squares are blocked.
We cannot move south or west because we cannot go outside of the grid.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> blocked = [], source = [0,0], target = [999999,999999]
<strong>Output:</strong> true
<strong>Explanation:</strong> Because there are no blocked cells, it is possible to reach the target square.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= blocked.length &lt;= 200</code></li>
	<li><code>blocked[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt; 10<sup>6</sup></code></li>
	<li><code>source.length == target.length == 2</code></li>
	<li><code>0 &lt;= s<sub>x</sub>, s<sub>y</sub>, t<sub>x</sub>, t<sub>y</sub> &lt; 10<sup>6</sup></code></li>
	<li><code>source != target</code></li>
	<li>It is guaranteed that <code>source</code> and <code>target</code> are not blocked.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

The problem can be interpreted as determining whether it is possible to move from a source point to a target point in a $10^6 \times 10^6$ grid, given a small number of blocked points.

Since the number of blocked points is small, the maximum area that can be blocked is no more than $|blocked|^2 / 2$. Therefore, we can perform a depth-first search (DFS) starting from both the source and the target points. The search continues until either the target point is reached or the number of visited points exceeds $|blocked|^2 / 2$. If either condition is satisfied, return $\textit{true}$. Otherwise, return $\textit{false}$.

Time complexity is $O(m)$, and space complexity is $O(m)$, where $m$ is the size of the blocked region. In this problem, $m \leq |blocked|^2 / 2 = 200^2 / 2 = 20000$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isEscapePossible(
        self, blocked: List[List[int]], source: List[int], target: List[int]
    ) -> bool:
        def dfs(source: List[int], target: List[int], vis: set) -> bool:
            vis.add(tuple(source))
            if len(vis) > m:
                return True
            for a, b in pairwise(dirs):
                x, y = source[0] + a, source[1] + b
                if 0 <= x < n and 0 <= y < n and (x, y) not in s and (x, y) not in vis:
                    if [x, y] == target or dfs([x, y], target, vis):
                        return True
            return False

        s = {(x, y) for x, y in blocked}
        dirs = (-1, 0, 1, 0, -1)
        n = 10**6
        m = len(blocked) ** 2 // 2
        return dfs(source, target, set()) and dfs(target, source, set())
```

#### Java

```java
class Solution {
    private final int n = (int) 1e6;
    private int m;
    private Set<Long> s = new HashSet<>();
    private final int[] dirs = {-1, 0, 1, 0, -1};

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        for (var b : blocked) {
            s.add(f(b[0], b[1]));
        }
        m = blocked.length * blocked.length / 2;
        int sx = source[0], sy = source[1];
        int tx = target[0], ty = target[1];
        return dfs(sx, sy, tx, ty, new HashSet<>()) && dfs(tx, ty, sx, sy, new HashSet<>());
    }

    private boolean dfs(int sx, int sy, int tx, int ty, Set<Long> vis) {
        if (vis.size() > m) {
            return true;
        }
        for (int k = 0; k < 4; ++k) {
            int x = sx + dirs[k], y = sy + dirs[k + 1];
            if (x >= 0 && x < n && y >= 0 && y < n) {
                if (x == tx && y == ty) {
                    return true;
                }
                long key = f(x, y);
                if (!s.contains(key) && vis.add(key) && dfs(x, y, tx, ty, vis)) {
                    return true;
                }
            }
        }
        return false;
    }

    private long f(int i, int j) {
        return (long) i * n + j;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isEscapePossible(vector<vector<int>>& blocked, vector<int>& source, vector<int>& target) {
        const int n = 1e6;
        int m = blocked.size() * blocked.size() / 2;
        using ll = long long;
        unordered_set<ll> s;
        const int dirs[5] = {-1, 0, 1, 0, -1};
        auto f = [&](int i, int j) {
            return (ll) i * n + j;
        };
        for (const auto& b : blocked) {
            s.insert(f(b[0], b[1]));
        }
        int sx = source[0], sy = source[1];
        int tx = target[0], ty = target[1];
        unordered_set<ll> vis1, vis2;
        auto dfs = [&](this auto&& dfs, int sx, int sy, int tx, int ty, unordered_set<ll>& vis) -> bool {
            vis.insert(f(sx, sy));
            if (vis.size() > m) {
                return true;
            }
            for (int k = 0; k < 4; ++k) {
                int x = sx + dirs[k], y = sy + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n) {
                    if (x == tx && y == ty) {
                        return true;
                    }
                    auto key = f(x, y);
                    if (!s.contains(key) && !vis.contains(key) && dfs(x, y, tx, ty, vis)) {
                        return true;
                    }
                }
            }
            return false;
        };
        return dfs(sx, sy, tx, ty, vis1) && dfs(tx, ty, sx, sy, vis2);
    }
};
```

#### Go

```go
func isEscapePossible(blocked [][]int, source []int, target []int) bool {
	const n = 1_000_000
	m := len(blocked) * len(blocked) / 2
	dirs := [5]int{-1, 0, 1, 0, -1}

	f := func(i, j int) int64 {
		return int64(i*n + j)
	}

	s := make(map[int64]bool)
	for _, b := range blocked {
		s[f(b[0], b[1])] = true
	}

	var dfs func(sx, sy, tx, ty int, vis map[int64]bool) bool
	dfs = func(sx, sy, tx, ty int, vis map[int64]bool) bool {
		key := f(sx, sy)
		vis[key] = true
		if len(vis) > m {
			return true
		}
		for k := 0; k < 4; k++ {
			x, y := sx+dirs[k], sy+dirs[k+1]
			if x >= 0 && x < n && y >= 0 && y < n {
				if x == tx && y == ty {
					return true
				}
				key := f(x, y)
				if !s[key] && !vis[key] && dfs(x, y, tx, ty, vis) {
					return true
				}
			}
		}
		return false
	}

	sx, sy := source[0], source[1]
	tx, ty := target[0], target[1]
	return dfs(sx, sy, tx, ty, map[int64]bool{}) && dfs(tx, ty, sx, sy, map[int64]bool{})
}
```

#### TypeScript

```ts
function isEscapePossible(blocked: number[][], source: number[], target: number[]): boolean {
    const n = 10 ** 6;
    const m = (blocked.length ** 2) >> 1;
    const dirs = [-1, 0, 1, 0, -1];

    const s = new Set<number>();
    const f = (i: number, j: number): number => i * n + j;

    for (const [x, y] of blocked) {
        s.add(f(x, y));
    }

    const dfs = (sx: number, sy: number, tx: number, ty: number, vis: Set<number>): boolean => {
        vis.add(f(sx, sy));
        if (vis.size > m) {
            return true;
        }
        for (let k = 0; k < 4; k++) {
            const x = sx + dirs[k],
                y = sy + dirs[k + 1];
            if (x >= 0 && x < n && y >= 0 && y < n) {
                if (x === tx && y === ty) {
                    return true;
                }
                const key = f(x, y);
                if (!s.has(key) && !vis.has(key) && dfs(x, y, tx, ty, vis)) {
                    return true;
                }
            }
        }
        return false;
    };

    return (
        dfs(source[0], source[1], target[0], target[1], new Set()) &&
        dfs(target[0], target[1], source[0], source[1], new Set())
    );
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn is_escape_possible(blocked: Vec<Vec<i32>>, source: Vec<i32>, target: Vec<i32>) -> bool {
        const N: i64 = 1_000_000;
        let m = (blocked.len() * blocked.len()) as i64 / 2;

        let f = |i: i64, j: i64| -> i64 { i * N + j };

        let mut s: HashSet<i64> = HashSet::new();
        for b in &blocked {
            s.insert(f(b[0] as i64, b[1] as i64));
        }

        fn dfs(
            sx: i64,
            sy: i64,
            tx: i64,
            ty: i64,
            s: &HashSet<i64>,
            m: i64,
            vis: &mut HashSet<i64>,
        ) -> bool {
            static DIRS: [i64; 5] = [-1, 0, 1, 0, -1];
            let key = sx * 1_000_000 + sy;
            vis.insert(key);
            if vis.len() as i64 > m {
                return true;
            }
            for k in 0..4 {
                let x = sx + DIRS[k];
                let y = sy + DIRS[k + 1];
                let key = x * 1_000_000 + y;
                if x >= 0 && x < 1_000_000 && y >= 0 && y < 1_000_000 {
                    if x == tx && y == ty {
                        return true;
                    }
                    if !s.contains(&key) && vis.insert(key) && dfs(x, y, tx, ty, s, m, vis) {
                        return true;
                    }
                }
            }
            false
        }

        dfs(
            source[0] as i64,
            source[1] as i64,
            target[0] as i64,
            target[1] as i64,
            &s,
            m,
            &mut HashSet::new(),
        ) && dfs(
            target[0] as i64,
            target[1] as i64,
            source[0] as i64,
            source[1] as i64,
            &s,
            m,
            &mut HashSet::new(),
        )
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
