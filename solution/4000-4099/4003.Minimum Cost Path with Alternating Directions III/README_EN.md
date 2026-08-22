---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4003.Minimum%20Cost%20Path%20with%20Alternating%20Directions%20III/README_EN.md
rating: 2122
source: Weekly Contest 512 Q4
tags:
    - Graph
    - Array
    - Matrix
    - Shortest Path
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [4003. Minimum Cost Path with Alternating Directions III](https://leetcode.com/problems/minimum-cost-path-with-alternating-directions-iii)

[中文文档](/solution/4000-4099/4003.Minimum%20Cost%20Path%20with%20Alternating%20Directions%20III/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>m</code> and <code>n</code> representing the number of rows and columns of a grid. Your goal is to reach cell <code>(m - 1, n - 1)</code>. You are also given a 2D integer array <code>penalty</code>.</p>

<p>The cost to enter cell <code>(i, j)</code> is <code>(i + 1) * (j + 1)</code>.</p>

<p>You begin at cell <code>(0, 0)</code> and initially pay its entrance cost. Actions performed after entering <code>(0, 0)</code> are numbered starting from 1.</p>

<p>On each action, you may move to an <strong>adjacent</strong> cell or wait in the current cell. A move follows the parity rule if:</p>

<ul>
	<li>On an <strong>odd-numbered</strong> action, you move <strong>right</strong> or <strong>down</strong>.</li>
	<li>On an <strong>even-numbered</strong> action, you move <strong>left</strong> or <strong>up</strong>.</li>
</ul>

<p>The cost of an action is determined as follows:</p>

<ul>
	<li>If you move according to the parity rule, pay only the entrance cost of the destination cell.</li>
	<li>If you move in a direction that <strong>violates</strong> the parity rule, pay the entrance cost of the destination cell plus <code>penalty[i][j]</code>, where <code>(i, j)</code> is the cell you move from.</li>
	<li>If you <strong>wait</strong> in cell <code>(i, j)</code>, pay <code>penalty[i][j]</code>.</li>
</ul>

<p>After every move or wait, the action number increases by 1. Therefore, the required parity alternates after every action, regardless of whether a penalty was paid.</p>

<p>Return the <strong>minimum</strong> total cost required to reach <code>(m - 1, n - 1)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 2, n = 2, penalty = [[5,3],[1,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal path is:</p>

<ul>
	<li>Start at cell <code>(0, 0)</code> with entry cost <code>(0 + 1) * (0 + 1) = 1</code>.</li>
	<li><strong>Move 1</strong>: Move down to cell <code>(1, 0)</code> with entry cost <code>(1 + 1) * (0 + 1) = 2</code>.</li>
	<li><strong>Move 2</strong>: Move right to cell <code>(1, 1)</code> with entry cost <code>(1 + 1) * (1 + 1) = 4</code> and an extra cost of <code>penalty[1][0] = 1</code> for violating the even parity rule.</li>
</ul>

<p>Thus, the total cost is <code>1 + 2 + 4 + 1 = 8</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 2, n = 2, penalty = [[0,7],[3,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal path is:</p>

<ul>
	<li>Start at cell <code>(0, 0)</code> with entry cost <code>(0 + 1) * (0 + 1) = 1</code>.</li>
	<li><strong>Move 1</strong>: Wait at cell <code>(0, 0)</code> with an extra cost of <code>penalty[0][0] = 0</code> to flip to even parity.</li>
	<li><strong>Move 2</strong>: Move right to cell <code>(0, 1)</code> with entry cost <code>(0 + 1) * (1 + 1) = 2</code> and an extra cost of <code>penalty[0][0] = 0</code> for violating the even parity rule.</li>
	<li><strong>Move 3</strong>: Move down to cell <code>(1, 1)</code> with entry cost <code>(1 + 1) * (1 + 1) = 4</code>.</li>
</ul>

<p>Thus, the total cost is <code>1 + 0 + 2 + 0 + 4 = 7</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 2, n = 3, penalty = [[8,0,9],[7,4,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal path is:</p>

<ul>
	<li>Start at cell <code>(0, 0)</code> with entry cost <code>(0 + 1) * (0 + 1) = 1</code>.</li>
	<li><strong>Move 1</strong>: Move right to cell <code>(0, 1)</code> with entry cost <code>(0 + 1) * (1 + 1) = 2</code>.</li>
	<li><strong>Move 2</strong>: Move right to cell <code>(0, 2)</code> with entry cost <code>(0 + 1) * (2 + 1) = 3</code> and an extra cost of <code>penalty[0][1] = 0</code> for violating the even parity rule.</li>
	<li><strong>Move 3</strong>: Move down to cell <code>(1, 2)</code> with entry cost <code>(1 + 1) * (2 + 1) = 6</code>.</li>
</ul>

<p>Thus, the total cost is <code>1 + 2 + 3 + 0 + 6 = 12</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>penalty.length == m</code></li>
	<li><code>penalty[i].length == n</code></li>
	<li><code>0 &lt;= penalty[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dijkstra

The cost to enter cell $(i, j)$ is $(i+1)(j+1)$. Actions are numbered from $1$: on odd actions you should move right or down, and on even actions left or up; you may also wait in place. Moving against the parity rule costs an extra $\textit{penalty}$ of the current cell, and waiting also costs $\textit{penalty}$. After every action the required parity flips.

Use state $(i, j, k)$ for the minimum cost of being at $(i, j)$ when the next action has parity $k$ ($k = 1$ for an odd action, $k = 0$ for an even action). The start is $(0, 0, 1)$ with cost $1$.

From the current state you may:

- **Wait**: add $\textit{penalty}[i][j]$ and flip the parity;
- **Move**: enumerate four directions, add the destination entrance cost; if the direction mismatches the current parity, also add $\textit{penalty}[i][j]$, then flip the parity at the new cell.

Run Dijkstra on this state graph; the first time $(m-1, n-1)$ is popped is the answer.

The time complexity is $O(mn \log (mn))$, and the space complexity is $O(mn)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, m: int, n: int, penalty: List[List[int]]) -> int:
        dist = [[[inf] * 2 for _ in range(n)] for _ in range(m)]
        dist[0][0][1] = 1
        pq = [(1, 0, 0, 1)]
        dirs = ((-1, 0), (0, 1), (0, -1), (1, 0))
        while pq:
            d, i, j, k = heappop(pq)
            if i == m - 1 and j == n - 1:
                return d
            if d > dist[i][j][k]:
                continue

            p = penalty[i][j]
            nd = d + p
            if nd < dist[i][j][k ^ 1]:
                dist[i][j][k ^ 1] = nd
                heappush(pq, (nd, i, j, k ^ 1))

            for idx, (dx, dy) in enumerate(dirs):
                x, y = i + dx, j + dy
                if 0 <= x < m and 0 <= y < n:
                    nd = d + (x + 1) * (y + 1) + (idx & 1 ^ k) * p
                    if nd < dist[x][y][k ^ 1]:
                        dist[x][y][k ^ 1] = nd
                        heappush(pq, (nd, x, y, k ^ 1))
```

#### Java

```java
class Solution {
    public long minCost(int m, int n, int[][] penalty) {
        long[][][] dist = new long[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dist[i][j], Long.MAX_VALUE);
            }
        }
        dist[0][0][1] = 1;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        pq.offer(new long[] {1, 0, 0, 1});

        int[][] dirs = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int i = (int) cur[1];
            int j = (int) cur[2];
            int k = (int) cur[3];

            if (i == m - 1 && j == n - 1) {
                return d;
            }
            if (d > dist[i][j][k]) {
                continue;
            }

            int p = penalty[i][j];

            long nd = d + p;
            if (nd < dist[i][j][k ^ 1]) {
                dist[i][j][k ^ 1] = nd;
                pq.offer(new long[] {nd, i, j, k ^ 1});
            }

            for (int idx = 0; idx < 4; idx++) {
                int x = i + dirs[idx][0];
                int y = j + dirs[idx][1];
                if (0 <= x && x < m && 0 <= y && y < n) {
                    nd = d + (long) (x + 1) * (y + 1) + ((idx & 1) ^ k) * (long) p;
                    if (nd < dist[x][y][k ^ 1]) {
                        dist[x][y][k ^ 1] = nd;
                        pq.offer(new long[] {nd, x, y, k ^ 1});
                    }
                }
            }
        }

        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minCost(int m, int n, vector<vector<int>>& penalty) {
        vector<vector<array<long long, 2>>> dist(
            m, vector<array<long long, 2>>(n, {LLONG_MAX, LLONG_MAX}));
        dist[0][0][1] = 1;

        priority_queue<
            array<long long, 4>,
            vector<array<long long, 4>>,
            greater<>>
            pq;
        pq.push({1, 0, 0, 1});

        int dirs[4][2] = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};

        while (!pq.empty()) {
            auto [d, i, j, k] = pq.top();
            pq.pop();

            if (i == m - 1 && j == n - 1) {
                return d;
            }
            if (d > dist[i][j][k]) {
                continue;
            }

            int p = penalty[i][j];

            long long nd = d + p;
            if (nd < dist[i][j][k ^ 1]) {
                dist[i][j][k ^ 1] = nd;
                pq.push({nd, i, j, k ^ 1});
            }

            for (int idx = 0; idx < 4; idx++) {
                int x = i + dirs[idx][0];
                int y = j + dirs[idx][1];
                if (0 <= x && x < m && 0 <= y && y < n) {
                    nd = d + 1LL * (x + 1) * (y + 1) + (((idx & 1) ^ k) ? p : 0);
                    if (nd < dist[x][y][k ^ 1]) {
                        dist[x][y][k ^ 1] = nd;
                        pq.push({nd, (long long) x, (long long) y, (long long) (k ^ 1)});
                    }
                }
            }
        }

        return -1;
    }
};
```

#### Go

```go
const inf int64 = 1 << 60

type tuple struct {
	d    int64
	i, j int
	k    int
}

type hp []tuple

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].d < h[j].d }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *hp) Push(x any) {
	*h = append(*h, x.(tuple))
}

func (h *hp) Pop() any {
	a := *h
	v := a[len(a)-1]
	*h = a[:len(a)-1]
	return v
}

func minCost(m int, n int, penalty [][]int) int64 {
	dist := make([][][]int64, m)
	for i := range dist {
		dist[i] = make([][]int64, n)
		for j := range dist[i] {
			dist[i][j] = []int64{inf, inf}
		}
	}
	dist[0][0][1] = 1

	pq := hp{{1, 0, 0, 1}}
	heap.Init(&pq)

	dirs := [][2]int{{-1, 0}, {0, 1}, {0, -1}, {1, 0}}

	for pq.Len() > 0 {
		cur := heap.Pop(&pq).(tuple)
		d, i, j, k := cur.d, cur.i, cur.j, cur.k

		if i == m-1 && j == n-1 {
			return d
		}
		if d > dist[i][j][k] {
			continue
		}

		p := penalty[i][j]

		nd := d + int64(p)
		if nd < dist[i][j][k^1] {
			dist[i][j][k^1] = nd
			heap.Push(&pq, tuple{nd, i, j, k ^ 1})
		}

		for idx, dir := range dirs {
			x, y := i+dir[0], j+dir[1]
			if 0 <= x && x < m && 0 <= y && y < n {
				nd = d + int64((x+1)*(y+1)+((idx&1)^k)*p)
				if nd < dist[x][y][k^1] {
					dist[x][y][k^1] = nd
					heap.Push(&pq, tuple{nd, x, y, k ^ 1})
				}
			}
		}
	}

	return -1
}
```

#### TypeScript

```ts
function minCost(m: number, n: number, penalty: number[][]): number {
    const dist = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => [Infinity, Infinity]),
    );
    dist[0][0][1] = 1;

    const pq = new MinPriorityQueue<number[]>(x => x[0]);
    pq.enqueue([1, 0, 0, 1]);

    const dirs = [
        [-1, 0],
        [0, 1],
        [0, -1],
        [1, 0],
    ];

    while (!pq.isEmpty()) {
        const [d, i, j, k] = pq.dequeue();

        if (i === m - 1 && j === n - 1) {
            return d;
        }
        if (d > dist[i][j][k]) {
            continue;
        }

        const p = penalty[i][j];

        let nd = d + p;
        if (nd < dist[i][j][k ^ 1]) {
            dist[i][j][k ^ 1] = nd;
            pq.enqueue([nd, i, j, k ^ 1]);
        }

        for (let idx = 0; idx < 4; idx++) {
            const [dx, dy] = dirs[idx];
            const x = i + dx;
            const y = j + dy;
            if (0 <= x && x < m && 0 <= y && y < n) {
                nd = d + (x + 1) * (y + 1) + ((idx & 1) ^ k) * p;
                if (nd < dist[x][y][k ^ 1]) {
                    dist[x][y][k ^ 1] = nd;
                    pq.enqueue([nd, x, y, k ^ 1]);
                }
            }
        }
    }

    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
