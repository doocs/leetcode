---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2492.Minimum%20Score%20of%20a%20Path%20Between%20Two%20Cities/README_EN.md
rating: 1679
source: Weekly Contest 322 Q3
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Graph
---

<!-- problem:start -->

# [2492. Minimum Score of a Path Between Two Cities](https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities)

[中文文档](/solution/2400-2499/2492.Minimum%20Score%20of%20a%20Path%20Between%20Two%20Cities/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer <code>n</code> representing <code>n</code> cities numbered from <code>1</code> to <code>n</code>. You are also given a <strong>2D</strong> array <code>roads</code> where <code>roads[i] = [a<sub>i</sub>, b<sub>i</sub>, distance<sub>i</sub>]</code> indicates that there is a <strong>bidirectional </strong>road between cities <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> with a distance equal to <code>distance<sub>i</sub></code>. The cities graph is not necessarily connected.</p>

<p>The <strong>score</strong> of a path between two cities is defined as the <strong>minimum </strong>distance of a road in this path.</p>

<p>Return <em>the <strong>minimum </strong>possible score of a path between cities </em><code>1</code><em> and </em><code>n</code>.</p>

<p><strong>Note</strong>:</p>

<ul>
	<li>A path is a sequence of roads between two cities.</li>
	<li>It is allowed for a path to contain the same road <strong>multiple</strong> times, and you can visit cities <code>1</code> and <code>n</code> multiple times along the path.</li>
	<li>The test cases are generated such that there is <strong>at least</strong> one path between <code>1</code> and <code>n</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2492.Minimum%20Score%20of%20a%20Path%20Between%20Two%20Cities/images/graph11.png" style="width: 190px; height: 231px;" />
<pre>
<strong>Input:</strong> n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The path from city 1 to 4 with the minimum score is: 1 -&gt; 2 -&gt; 4. The score of this path is min(9,5) = 5.
It can be shown that no other path has less score.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2492.Minimum%20Score%20of%20a%20Path%20Between%20Two%20Cities/images/graph22.png" style="width: 190px; height: 231px;" />
<pre>
<strong>Input:</strong> n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The path from city 1 to 4 with the minimum score is: 1 -&gt; 2 -&gt; 1 -&gt; 3 -&gt; 4. The score of this path is min(2,2,4,7) = 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= roads.length &lt;= 10<sup>5</sup></code></li>
	<li><code>roads[i].length == 3</code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>1 &lt;= distance<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>There are no repeated edges.</li>
	<li>There is at least one path between <code>1</code> and <code>n</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

According to the problem description, each edge can be traversed multiple times, and it is guaranteed that node $1$ and node $n$ are in the same connected component. Therefore, the problem is actually asking for the minimum edge weight in the connected component containing node $1$.

We first build an undirected graph $g$ from $\textit{roads}$, then perform DFS starting from node $1$. While traversing the connected component, we update the answer with $\textit{ans} = \min(\textit{ans}, w)$ for each edge visited.

The time complexity is $O(n + m)$, and the space complexity is $O(n + m)$, where $n$ and $m$ are the number of nodes and edges, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minScore(self, n: int, roads: List[List[int]]) -> int:
        def dfs(a: int):
            vis[a] = True
            nonlocal ans
            for b, w in g[a]:
                ans = min(ans, w)
                if not vis[b]:
                    dfs(b)

        g = [[] for _ in range(n + 1)]
        for a, b, w in roads:
            g[a].append((b, w))
            g[b].append((a, w))
        ans = inf
        vis = [False] * (n + 1)
        dfs(1)
        return ans
```

#### Java

```java
class Solution {
    private int ans;
    private boolean[] vis;
    private List<int[]>[] g;

    public int minScore(int n, int[][] roads) {
        g = new ArrayList[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());

        for (int[] e : roads) {
            int a = e[0], b = e[1], w = e[2];
            g[a].add(new int[]{b, w});
            g[b].add(new int[]{a, w});
        }

        ans = Integer.MAX_VALUE;
        vis = new boolean[n + 1];

        dfs(1);
        return ans;
    }

    private void dfs(int a) {
        vis[a] = true;
        for (int[] nb : g[a]) {
            int b = nb[0], w = nb[1];
            ans = Math.min(ans, w);
            if (!vis[b]) {
                dfs(b);
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minScore(int n, vector<vector<int>>& roads) {
        vector<vector<pair<int,int>>> g(n + 1);
        for (auto &e : roads) {
            int a = e[0], b = e[1], w = e[2];
            g[a].push_back({b, w});
            g[b].push_back({a, w});
        }

        vector<bool> vis(n + 1, false);
        int ans = INT_MAX;

        auto dfs = [&](this auto&& dfs, int a) -> void {
            vis[a] = true;
            for (auto &[b, w] : g[a]) {
                ans = min(ans, w);
                if (!vis[b]) {
                    dfs(b);
                }
            }
        };

        dfs(1);
        return ans;
    }
};
```

#### Go

```go
func minScore(n int, roads [][]int) int {
	g := make([][][2]int, n+1)
	for _, e := range roads {
		a, b, w := e[0], e[1], e[2]
		g[a] = append(g[a], [2]int{b, w})
		g[b] = append(g[b], [2]int{a, w})
	}

	vis := make([]bool, n+1)
	ans := int(1e9)

	var dfs func(int)
	dfs = func(a int) {
		vis[a] = true
		for _, nb := range g[a] {
			b, w := nb[0], nb[1]
			ans = min(ans, w)
			if !vis[b] {
				dfs(b)
			}
		}
	}

	dfs(1)
	return ans
}
```

#### TypeScript

```ts
function minScore(n: number, roads: number[][]): number {
    const g: [number, number][][] = Array.from({ length: n + 1 }, () => []);
    for (const [a, b, w] of roads) {
        g[a].push([b, w]);
        g[b].push([a, w]);
    }

    const vis = new Array(n + 1).fill(false);
    let ans = Infinity;

    const dfs = (a: number): void => {
        vis[a] = true;
        for (const [b, w] of g[a]) {
            ans = Math.min(ans, w);
            if (!vis[b]) {
                dfs(b);
            }
        }
    };

    dfs(1);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_score(n: i32, roads: Vec<Vec<i32>>) -> i32 {
        let n = n as usize;
        let mut g: Vec<Vec<(usize, i32)>> = vec![vec![]; n + 1];

        for e in roads {
            let a = e[0] as usize;
            let b = e[1] as usize;
            let w = e[2];
            g[a].push((b, w));
            g[b].push((a, w));
        }

        let mut vis = vec![false; n + 1];
        let mut ans = i32::MAX;

        fn dfs(
            a: usize,
            g: &Vec<Vec<(usize, i32)>>,
            vis: &mut Vec<bool>,
            ans: &mut i32,
        ) {
            vis[a] = true;

            for &(b, w) in &g[a] {
                *ans = (*ans).min(w);
                if !vis[b] {
                    dfs(b, g, vis, ans);
                }
            }
        }

        dfs(1, &g, &mut vis, &mut ans);
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @param {number[][]} roads
 * @return {number}
 */
var minScore = function (n, roads) {
    const g = Array.from({ length: n + 1 }, () => []);

    for (const [a, b, w] of roads) {
        g[a].push([b, w]);
        g[b].push([a, w]);
    }

    const vis = new Array(n + 1).fill(false);
    let ans = Infinity;

    const dfs = a => {
        vis[a] = true;
        for (const [b, w] of g[a]) {
            ans = Math.min(ans, w);
            if (!vis[b]) dfs(b);
        }
    };

    dfs(1);
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: BFS

We can also use BFS to solve this problem. Enqueue node $1$ and expand the connected component layer by layer, updating the answer with $\textit{ans} = \min(\textit{ans}, w)$ whenever an edge is visited.

The time complexity is $O(n + m)$, and the space complexity is $O(n + m)$, where $n$ and $m$ are the number of nodes and edges, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minScore(self, n: int, roads: List[List[int]]) -> int:
        g = [[] for _ in range(n + 1)]
        for a, b, w in roads:
            g[a].append((b, w))
            g[b].append((a, w))
        vis = [False] * (n + 1)
        vis[1] = True
        ans = inf
        q = deque([1])
        while q:
            for _ in range(len(q)):
                a = q.popleft()
                for b, w in g[a]:
                    ans = min(ans, w)
                    if not vis[b]:
                        vis[b] = True
                        q.append(b)
        return ans
```

#### Java

```java
class Solution {
    public int minScore(int n, int[][] roads) {
        List<int[]>[] g = new ArrayList[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());

        for (int[] e : roads) {
            int a = e[0], b = e[1], w = e[2];
            g[a].add(new int[] {b, w});
            g[b].add(new int[] {a, w});
        }

        boolean[] vis = new boolean[n + 1];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(1);
        vis[1] = true;
        int ans = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                int a = q.pollFirst();
                for (int[] nb : g[a]) {
                    int b = nb[0], w = nb[1];
                    ans = Math.min(ans, w);
                    if (!vis[b]) {
                        vis[b] = true;
                        q.offer(b);
                    }
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
    int minScore(int n, vector<vector<int>>& roads) {
        vector<vector<pair<int, int>>> g(n + 1);
        for (auto& e : roads) {
            int a = e[0], b = e[1], w = e[2];
            g[a].push_back({b, w});
            g[b].push_back({a, w});
        }

        vector<bool> vis(n + 1, false);
        int ans = INT_MAX;
        queue<int> q{{1}};
        vis[1] = true;

        while (!q.empty()) {
            for (int k = q.size(); k; --k) {
                int a = q.front();
                q.pop();
                for (auto [b, w] : g[a]) {
                    ans = min(ans, w);
                    if (!vis[b]) {
                        vis[b] = true;
                        q.push(b);
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minScore(n int, roads [][]int) int {
	g := make([][][2]int, n+1)
	for _, e := range roads {
		a, b, w := e[0], e[1], e[2]
		g[a] = append(g[a], [2]int{b, w})
		g[b] = append(g[b], [2]int{a, w})
	}

	vis := make([]bool, n+1)
	ans := int(1e9)
	q := []int{1}
	vis[1] = true

	for len(q) > 0 {
		for k := len(q); k > 0; k-- {
			a := q[0]
			q = q[1:]
			for _, nb := range g[a] {
				b, w := nb[0], nb[1]
				ans = min(ans, w)
				if !vis[b] {
					vis[b] = true
					q = append(q, b)
				}
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function minScore(n: number, roads: number[][]): number {
    const g: [number, number][][] = Array.from({ length: n + 1 }, () => []);
    for (const [a, b, w] of roads) {
        g[a].push([b, w]);
        g[b].push([a, w]);
    }

    const vis = new Array(n + 1).fill(false);
    let ans = Infinity;
    let q: number[] = [1];
    vis[1] = true;

    while (q.length > 0) {
        const nq: number[] = [];
        for (const a of q) {
            for (const [b, w] of g[a]) {
                ans = Math.min(ans, w);
                if (!vis[b]) {
                    vis[b] = true;
                    nq.push(b);
                }
            }
        }
        q = nq;
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::VecDeque;

impl Solution {
    pub fn min_score(n: i32, roads: Vec<Vec<i32>>) -> i32 {
        let n = n as usize;
        let mut g: Vec<Vec<(usize, i32)>> = vec![vec![]; n + 1];

        for e in roads {
            let a = e[0] as usize;
            let b = e[1] as usize;
            let w = e[2];
            g[a].push((b, w));
            g[b].push((a, w));
        }

        let mut vis = vec![false; n + 1];
        let mut ans = i32::MAX;
        let mut q = VecDeque::new();
        q.push_back(1);
        vis[1] = true;

        while !q.is_empty() {
            for _ in 0..q.len() {
                let a = q.pop_front().unwrap();
                for &(b, w) in &g[a] {
                    ans = ans.min(w);
                    if !vis[b] {
                        vis[b] = true;
                        q.push_back(b);
                    }
                }
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @param {number[][]} roads
 * @return {number}
 */
var minScore = function (n, roads) {
    const g = Array.from({ length: n + 1 }, () => []);

    for (const [a, b, w] of roads) {
        g[a].push([b, w]);
        g[b].push([a, w]);
    }

    const vis = new Array(n + 1).fill(false);
    let ans = Infinity;
    let q = [1];
    vis[1] = true;

    while (q.length > 0) {
        const nq = [];
        for (const a of q) {
            for (const [b, w] of g[a]) {
                ans = Math.min(ans, w);
                if (!vis[b]) {
                    vis[b] = true;
                    nq.push(b);
                }
            }
        }
        q = nq;
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
