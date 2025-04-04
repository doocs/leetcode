---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1036.Escape%20a%20Large%20Maze/README.md
rating: 2164
source: 第 134 场周赛 Q4
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [1036. 逃离大迷宫](https://leetcode.cn/problems/escape-a-large-maze)

[English Version](/solution/1000-1099/1036.Escape%20a%20Large%20Maze/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在一个 10<sup>6</sup> x 10<sup>6</sup> 的网格中，每个网格上方格的坐标为 <code>(x, y)</code> 。</p>

<p>现在从源方格 <code>source = [s<sub>x</sub>, s<sub>y</sub>]</code> 开始出发，意图赶往目标方格 <code>target = [t<sub>x</sub>, t<sub>y</sub>]</code> 。数组 <code>blocked</code> 是封锁的方格列表，其中每个 <code>blocked[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 表示坐标为 <code>(x<sub>i</sub>, y<sub>i</sub>)</code> 的方格是禁止通行的。</p>

<p>每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格 <strong>不</strong> 在给出的封锁列表 <code>blocked</code> 上。同时，不允许走出网格。</p>

<p>只有在可以通过一系列的移动从源方格 <code>source</code> 到达目标方格 <code>target</code> 时才返回 <code>true</code>。否则，返回 <code>false</code>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
<strong>输出：</strong>false
<strong>解释：</strong>
从源方格无法到达目标方格，因为我们无法在网格中移动。
无法向北或者向东移动是因为方格禁止通行。
无法向南或者向西移动是因为不能走出网格。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>blocked = [], source = [0,0], target = [999999,999999]
<strong>输出：</strong>true
<strong>解释：</strong>
因为没有方格被封锁，所以一定可以到达目标方格。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= blocked.length <= 200</code></li>
	<li><code>blocked[i].length == 2</code></li>
	<li><code>0 <= x<sub>i</sub>, y<sub>i</sub> < 10<sup>6</sup></code></li>
	<li><code>source.length == target.length == 2</code></li>
	<li><code>0 <= s<sub>x</sub>, s<sub>y</sub>, t<sub>x</sub>, t<sub>y</sub> < 10<sup>6</sup></code></li>
	<li><code>source != target</code></li>
	<li>题目数据保证 <code>source</code> 和 <code>target</code> 不在封锁列表内</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

题目相当于在一个 $10^6 \times 10^6$ 的网格中，给定源点和目标点，以及一小部分被封锁的点，问是否可以从源点到达目标点。

由于被封锁的点数量很少，最终能封锁的区域大小不超过 $|blocked|^2 / 2$，因此，我们可以从源点和目标点出发，进行深度优先搜索，直到搜索到目标点或者搜索到的点数超过 $|blocked|^2 / 2$，如果都满足，则返回 $\textit{true}$。否则返回 $\textit{false}$。

时间复杂度 $O(m)$，空间复杂度 $O(m)$，其中 $m$ 是被封锁的区域的大小，本题中 $m \leq |blocked|^2 / 2 = 200^2 / 2 = 20000$。

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
