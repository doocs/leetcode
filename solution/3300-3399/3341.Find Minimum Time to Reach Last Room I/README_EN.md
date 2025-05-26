---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3341.Find%20Minimum%20Time%20to%20Reach%20Last%20Room%20I/README_EN.md
rating: 1721
source: Weekly Contest 422 Q2
tags:
    - Graph
    - Array
    - Matrix
    - Shortest Path
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3341. Find Minimum Time to Reach Last Room I](https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i)

[中文文档](/solution/3300-3399/3341.Find%20Minimum%20Time%20to%20Reach%20Last%20Room%20I/README.md)

## Description

<!-- description:start -->

<p>There is a dungeon with <code>n x m</code> rooms arranged as a grid.</p>

<p>You are given a 2D array <code>moveTime</code> of size <code>n x m</code>, where <code>moveTime[i][j]</code> represents the <strong>minimum</strong> time in seconds <strong>after</strong> which the room opens and can be moved to. You start from the room <code>(0, 0)</code> at time <code>t = 0</code> and can move to an <strong>adjacent</strong> room. Moving between adjacent rooms takes <em>exactly</em> one second.</p>

<p>Return the <strong>minimum</strong> time to reach the room <code>(n - 1, m - 1)</code>.</p>

<p>Two rooms are <strong>adjacent</strong> if they share a common wall, either <em>horizontally</em> or <em>vertically</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">moveTime = [[0,4],[4,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>The minimum time required is 6 seconds.</p>

<ul>
	<li>At time <code>t == 4</code>, move from room <code>(0, 0)</code> to room <code>(1, 0)</code> in one second.</li>
	<li>At time <code>t == 5</code>, move from room <code>(1, 0)</code> to room <code>(1, 1)</code> in one second.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">moveTime = [[0,0,0],[0,0,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The minimum time required is 3 seconds.</p>

<ul>
	<li>At time <code>t == 0</code>, move from room <code>(0, 0)</code> to room <code>(1, 0)</code> in one second.</li>
	<li>At time <code>t == 1</code>, move from room <code>(1, 0)</code> to room <code>(1, 1)</code> in one second.</li>
	<li>At time <code>t == 2</code>, move from room <code>(1, 1)</code> to room <code>(1, 2)</code> in one second.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">moveTime = [[0,1],[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == moveTime.length &lt;= 50</code></li>
	<li><code>2 &lt;= m == moveTime[i].length &lt;= 50</code></li>
	<li><code>0 &lt;= moveTime[i][j] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dijkstra's Algorithm

We define a two-dimensional array $\textit{dist}$, where $\textit{dist}[i][j]$ represents the minimum time required to reach room $(i, j)$ from the starting point. Initially, we set all elements in the $\textit{dist}$ array to infinity, and then set the $\textit{dist}$ value of the starting point $(0, 0)$ to $0$.

We use a priority queue $\textit{pq}$ to store each state, where each state consists of three values $(d, i, j)$, representing the time $d$ required to reach room $(i, j)$ from the starting point. Initially, we add the starting point $(0, 0, 0)$ to $\textit{pq}$.

In each iteration, we take the front element $(d, i, j)$ from $\textit{pq}$. If $(i, j)$ is the endpoint, we return $d$. If $d$ is greater than $\textit{dist}[i][j]$, we skip this state. Otherwise, we enumerate the four adjacent positions $(x, y)$ of $(i, j)$. If $(x, y)$ is within the map, we calculate the final time $t$ from $(i, j)$ to $(x, y)$ as $t = \max(\textit{moveTime}[x][y], \textit{dist}[i][j]) + 1$. If $t$ is less than $\textit{dist}[x][y]$, we update the value of $\textit{dist}[x][y]$ and add $(t, x, y)$ to $\textit{pq}$.

The time complexity is $O(n \times m \times \log (n \times m))$, and the space complexity is $O(n \times m)$. Here, $n$ and $m$ are the number of rows and columns of the map, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minTimeToReach(self, moveTime: List[List[int]]) -> int:
        n, m = len(moveTime), len(moveTime[0])
        dist = [[inf] * m for _ in range(n)]
        dist[0][0] = 0
        pq = [(0, 0, 0)]
        dirs = (-1, 0, 1, 0, -1)
        while 1:
            d, i, j = heappop(pq)
            if i == n - 1 and j == m - 1:
                return d
            if d > dist[i][j]:
                continue
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < n and 0 <= y < m:
                    t = max(moveTime[x][y], dist[i][j]) + 1
                    if dist[x][y] > t:
                        dist[x][y] = t
                        heappush(pq, (t, x, y))
```

#### Java

```java
class Solution {
    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        int[][] dist = new int[n][m];
        for (var row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] {0, 0, 0});
        int[] dirs = {-1, 0, 1, 0, -1};
        while (true) {
            int[] p = pq.poll();
            int d = p[0], i = p[1], j = p[2];

            if (i == n - 1 && j == m - 1) {
                return d;
            }
            if (d > dist[i][j]) {
                continue;
            }

            for (int k = 0; k < 4; k++) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < m) {
                    int t = Math.max(moveTime[x][y], dist[i][j]) + 1;
                    if (dist[x][y] > t) {
                        dist[x][y] = t;
                        pq.offer(new int[] {t, x, y});
                    }
                }
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minTimeToReach(vector<vector<int>>& moveTime) {
        int n = moveTime.size();
        int m = moveTime[0].size();
        vector<vector<int>> dist(n, vector<int>(m, INT_MAX));
        dist[0][0] = 0;
        priority_queue<array<int, 3>, vector<array<int, 3>>, greater<>> pq;
        pq.push({0, 0, 0});
        int dirs[5] = {-1, 0, 1, 0, -1};

        while (1) {
            auto [d, i, j] = pq.top();
            pq.pop();

            if (i == n - 1 && j == m - 1) {
                return d;
            }
            if (d > dist[i][j]) {
                continue;
            }

            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];

                if (x >= 0 && x < n && y >= 0 && y < m) {
                    int t = max(moveTime[x][y], dist[i][j]) + 1;
                    if (dist[x][y] > t) {
                        dist[x][y] = t;
                        pq.push({t, x, y});
                    }
                }
            }
        }
    }
};
```

#### Go

```go
func minTimeToReach(moveTime [][]int) int {
	n, m := len(moveTime), len(moveTime[0])
	dist := make([][]int, n)
	for i := range dist {
		dist[i] = make([]int, m)
		for j := range dist[i] {
			dist[i][j] = math.MaxInt32
		}
	}
	dist[0][0] = 0

	pq := &hp{}
	heap.Init(pq)
	heap.Push(pq, tuple{0, 0, 0})

	dirs := []int{-1, 0, 1, 0, -1}
	for {
		p := heap.Pop(pq).(tuple)
		d, i, j := p.dis, p.x, p.y

		if i == n-1 && j == m-1 {
			return d
		}
		if d > dist[i][j] {
			continue
		}

		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < n && y >= 0 && y < m {
				t := max(moveTime[x][y], dist[i][j]) + 1
				if dist[x][y] > t {
					dist[x][y] = t
					heap.Push(pq, tuple{t, x, y})
				}
			}
		}
	}
}

type tuple struct{ dis, x, y int }
type hp []tuple

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].dis < h[j].dis }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() (v any)      { a := *h; *h, v = a[:len(a)-1], a[len(a)-1]; return }
```

#### TypeScript

```ts
function minTimeToReach(moveTime: number[][]): number {
    const n = moveTime.length;
    const m = moveTime[0].length;
    const dist = Array.from({ length: n }, () => Array(m).fill(Infinity));
    dist[0][0] = 0;
    type Node = [number, number, number];
    const pq = new PriorityQueue<Node>((a, b) => a[0] - b[0]);
    pq.enqueue([0, 0, 0]);
    const dirs = [-1, 0, 1, 0, -1];
    while (!pq.isEmpty()) {
        const [d, i, j] = pq.dequeue();
        if (d > dist[i][j]) continue;
        if (i === n - 1 && j === m - 1) return d;
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < n && y >= 0 && y < m) {
                const t = Math.max(moveTime[x][y], d) + 1;
                if (t < dist[x][y]) {
                    dist[x][y] = t;
                    pq.enqueue([t, x, y]);
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
