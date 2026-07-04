---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2492.Minimum%20Score%20of%20a%20Path%20Between%20Two%20Cities/README.md
rating: 1679
source: 第 322 场周赛 Q3
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 图
---

<!-- problem:start -->

# [2492. 两个城市间路径的最小分数](https://leetcode.cn/problems/minimum-score-of-a-path-between-two-cities)

[English Version](/solution/2400-2499/2492.Minimum%20Score%20of%20a%20Path%20Between%20Two%20Cities/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数&nbsp;<code>n</code>&nbsp;，表示总共有&nbsp;<code>n</code>&nbsp;个城市，城市从&nbsp;<code>1</code>&nbsp;到&nbsp;<code>n</code>&nbsp;编号。给你一个二维数组&nbsp;<code>roads</code>&nbsp;，其中&nbsp;<code>roads[i] = [a<sub>i</sub>, b<sub>i</sub>, distance<sub>i</sub>]</code>&nbsp;表示城市&nbsp;<code>a<sub>i</sub></code> 和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条 <strong>双向</strong>&nbsp;道路，道路距离为&nbsp;<code>distance<sub>i</sub></code>&nbsp;。城市构成的图不一定是连通的。</p>

<p>两个城市之间一条路径的 <strong>分数</strong>&nbsp;定义为这条路径中道路的 <strong>最小</strong>&nbsp;距离。</p>

<p><span class="text-only" data-eleid="20" style="white-space: pre;">返回城市</span><span class="text-only text-font-italic" data-eleid="21" style="white-space: pre;"> </span><code><span class="text-only" data-eleid="22" style="white-space: pre;">1</span></code><span class="text-only text-font-italic" data-eleid="23" style="white-space: pre;"> </span><span class="text-only" data-eleid="24" style="white-space: pre;">和城市</span><span class="text-only text-font-italic" data-eleid="25" style="white-space: pre;"> </span><span class="text-only" data-eleid="26" style="white-space: pre;"><code>n</code> 之间的所有路径的 </span><strong><span class="text-only" data-eleid="27" style="white-space: pre;">最小</span></strong><span class="text-only" data-eleid="28" style="white-space: pre;"> 分数。</span></p>

<p><b>注意：</b></p>

<ul>
	<li>一条路径指的是两个城市之间的道路序列。</li>
	<li>一条路径可以 <strong>多次</strong> 包含同一条道路，你也可以沿着路径多次到达城市 <code>1</code>&nbsp;和城市 <code>n</code>&nbsp;。</li>
	<li>测试数据保证城市 <code>1</code>&nbsp;和城市<code>n</code>&nbsp;之间 <strong>至少</strong>&nbsp;有一条路径。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2492.Minimum%20Score%20of%20a%20Path%20Between%20Two%20Cities/images/graph11.png" style="width: 190px; height: 231px;" /></p>

<pre>
<b>输入：</b>n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
<b>输出：</b>5
<b>解释：</b>城市 1 到城市 4 的路径中，分数最小的一条为：1 -&gt; 2 -&gt; 4 。这条路径的分数是 min(9,5) = 5 。
不存在分数更小的路径。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2492.Minimum%20Score%20of%20a%20Path%20Between%20Two%20Cities/images/graph22.png" style="width: 190px; height: 231px;" /></p>

<pre>
<b>输入：</b>n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
<b>输出：</b>2
<b>解释：</b>城市 1 到城市 4 分数最小的路径是：1 -&gt; 2 -&gt; 1 -&gt; 3 -&gt; 4 。这条路径的分数是 min(2,2,4,7) = 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= roads.length &lt;= 10<sup>5</sup></code></li>
	<li><code>roads[i].length == 3</code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>1 &lt;= distance<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>不会有重复的边。</li>
	<li>城市 <code>1</code>&nbsp;和城市 <code>n</code>&nbsp;之间至少有一条路径。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

根据题目描述，每条边可以经过多次，并且保证节点 $1$ 和节点 $n$ 在同一个连通块中。因此，题目实际上求的是节点 $1$ 所在连通块中的最小边权。

我们首先根据 $\textit{roads}$ 构建无向图 $g$，然后从节点 $1$ 开始 DFS。在遍历连通块的过程中，对每条经过的边用 $\textit{ans} = \min(\textit{ans}, w)$ 更新答案。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是节点数和边数。

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

### 方法二：BFS

我们也可以用 BFS 来求解。将节点 $1$ 加入队列，逐层扩展与节点 $1$ 连通的子图，每次访问边时用 $\textit{ans} = \min(\textit{ans}, w)$ 更新答案。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是节点数和边数。

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
