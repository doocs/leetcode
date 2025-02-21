---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0785.Is%20Graph%20Bipartite/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 图
---

<!-- problem:start -->

# [785. 判断二分图](https://leetcode.cn/problems/is-graph-bipartite)

[English Version](/solution/0700-0799/0785.Is%20Graph%20Bipartite/README_EN.md)

## 题目描述

<!-- description:start -->

存在一个 <strong>无向图</strong> ，图中有 <code>n</code> 个节点。其中每个节点都有一个介于 <code>0</code> 到 <code>n - 1</code> 之间的唯一编号。给你一个二维数组 <code>graph</code> ，其中 <code>graph[u]</code> 是一个节点数组，由节点 <code>u</code> 的邻接节点组成。形式上，对于 <code>graph[u]</code> 中的每个 <code>v</code> ，都存在一条位于节点 <code>u</code> 和节点 <code>v</code> 之间的无向边。该无向图同时具有以下属性：

<ul>
	<li>不存在自环（<code>graph[u]</code> 不包含 <code>u</code>）。</li>
	<li>不存在平行边（<code>graph[u]</code> 不包含重复值）。</li>
	<li>如果 <code>v</code> 在 <code>graph[u]</code> 内，那么 <code>u</code> 也应该在 <code>graph[v]</code> 内（该图是无向图）</li>
	<li>这个图可能不是连通图，也就是说两个节点 <code>u</code> 和 <code>v</code> 之间可能不存在一条连通彼此的路径。</li>
</ul>

<p><strong>二分图</strong> 定义：如果能将一个图的节点集合分割成两个独立的子集 <code>A</code> 和 <code>B</code> ，并使图中的每一条边的两个节点一个来自 <code>A</code> 集合，一个来自 <code>B</code> 集合，就将这个图称为 <strong>二分图</strong> 。</p>

<p>如果图是二分图，返回 <code>true</code><em> </em>；否则，返回 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0785.Is%20Graph%20Bipartite/images/bi2.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>输入：</strong>graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
<strong>输出：</strong>false
<strong>解释：</strong><code>不能将节点分割成两个独立的子集，</code>以使每条边都连通一个子集中的一个节点与另一个子集中的一个节点。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0785.Is%20Graph%20Bipartite/images/bi1.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>输入：</strong>graph = [[1,3],[0,2],[1,3],[0,2]]
<strong>输出：</strong>true
<strong>解释：</strong><code>可以将节点分成两组: {0, 2} 和 {1, 3} 。</code></pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>graph.length == n</code></li>
	<li><code>1 <= n <= 100</code></li>
	<li><code>0 <= graph[u].length < n</code></li>
	<li><code>0 <= graph[u][i] <= n - 1</code></li>
	<li><code>graph[u]</code> 不会包含 <code>u</code></li>
	<li><code>graph[u]</code> 的所有值 <strong>互不相同</strong></li>
	<li>如果 <code>graph[u]</code> 包含 <code>v</code>，那么 <code>graph[v]</code> 也会包含 <code>u</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：染色法判定二分图

遍历所有节点进行染色，比如初始为白色，DFS 对节点相邻的点染上另外一种颜色。如果要染色某节点时，要染的目标颜色和该节点的已经染过的颜色不同，则说明不能构成二分图。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        def dfs(a: int, c: int) -> bool:
            color[a] = c
            for b in graph[a]:
                if color[b] == c or (color[b] == 0 and not dfs(b, -c)):
                    return False
            return True

        n = len(graph)
        color = [0] * n
        for i in range(n):
            if color[i] == 0 and not dfs(i, 1):
                return False
        return True
```

#### Java

```java
class Solution {
    private int[] color;
    private int[][] g;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new int[n];
        g = graph;
        for (int i = 0; i < n; ++i) {
            if (color[i] == 0 && !dfs(i, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int a, int c) {
        color[a] = c;
        for (int b : g[a]) {
            if (color[b] == c || (color[b] == 0 && !dfs(b, -c))) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isBipartite(vector<vector<int>>& graph) {
        int n = graph.size();
        vector<int> color(n);
        auto dfs = [&](this auto&& dfs, int a, int c) -> bool {
            color[a] = c;
            for (int b : graph[a]) {
                if (color[b] == c || (color[b] == 0 && !dfs(b, -c))) {
                    return false;
                }
            }
            return true;
        };
        for (int i = 0; i < n; ++i) {
            if (color[i] == 0 && !dfs(i, 1)) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isBipartite(graph [][]int) bool {
	n := len(graph)
	color := make([]int, n)
	var dfs func(int, int) bool
	dfs = func(a, c int) bool {
		color[a] = c
		for _, b := range graph[a] {
			if color[b] == c || (color[b] == 0 && !dfs(b, -c)) {
				return false
			}
		}
		return true
	}
	for i := range graph {
		if color[i] == 0 && !dfs(i, 1) {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function isBipartite(graph: number[][]): boolean {
    const n = graph.length;
    const color: number[] = Array(n).fill(0);
    const dfs = (a: number, c: number): boolean => {
        color[a] = c;
        for (const b of graph[a]) {
            if (color[b] === c || (color[b] === 0 && !dfs(b, -c))) {
                return false;
            }
        }
        return true;
    };
    for (let i = 0; i < n; i++) {
        if (color[i] === 0 && !dfs(i, 1)) {
            return false;
        }
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_bipartite(graph: Vec<Vec<i32>>) -> bool {
        let n = graph.len();
        let mut color = vec![0; n];

        fn dfs(a: usize, c: i32, graph: &Vec<Vec<i32>>, color: &mut Vec<i32>) -> bool {
            color[a] = c;
            for &b in &graph[a] {
                if color[b as usize] == c
                    || (color[b as usize] == 0 && !dfs(b as usize, -c, graph, color))
                {
                    return false;
                }
            }
            true
        }

        for i in 0..n {
            if color[i] == 0 && !dfs(i, 1, &graph, &mut color) {
                return false;
            }
        }
        true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：并查集

对于本题，如果是二分图，那么图中每个顶点的所有邻接点都应该属于同一集合，且不与顶点处于同一集合，因此我们可以使用并查集。遍历图中每个顶点，如果发现存在当前顶点与对应的邻接点处于同一个集合，说明不是二分图。否则将当前节点的邻接点相互进行合并。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为节点数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(len(graph)))
        for a, bs in enumerate(graph):
            for b in bs:
                pa, pb = find(a), find(b)
                if pa == pb:
                    return False
                p[pb] = find(bs[0])
        return True
```

#### Java

```java
class Solution {
    private int[] p;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int a = 0; a < n; ++a) {
            for (int b : graph[a]) {
                int pa = find(a), pb = find(b);
                if (pa == pb) {
                    return false;
                }
                p[pb] = find(graph[a][0]);
            }
        }
        return true;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isBipartite(vector<vector<int>>& graph) {
        int n = graph.size();
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        auto find = [&](this auto&& find, int x) -> int {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        for (int a = 0; a < n; ++a) {
            for (int b : graph[a]) {
                int pa = find(a), pb = find(b);
                if (pa == pb) {
                    return false;
                }
                p[pb] = find(graph[a][0]);
            }
        }
        return true;
    }
};
```

#### Go

```go
func isBipartite(graph [][]int) bool {
	n := len(graph)
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for a, bs := range graph {
		for _, b := range bs {
			pa, pb := find(a), find(b)
			if pa == pb {
				return false
			}
			p[pb] = find(bs[0])
		}
	}
	return true
}
```

#### TypeScript

```ts
function isBipartite(graph: number[][]): boolean {
    const n = graph.length;
    const p: number[] = Array.from({ length: n }, (_, i) => i);
    const find = (x: number): number => {
        if (x !== p[x]) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    for (let a = 0; a < n; ++a) {
        for (const b of graph[a]) {
            const [pa, pb] = [find(a), find(b)];
            if (pa === pb) {
                return false;
            }
            p[pb] = find(graph[a][0]);
        }
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_bipartite(graph: Vec<Vec<i32>>) -> bool {
        let n = graph.len();
        let mut p: Vec<usize> = (0..n).collect();

        fn find(x: usize, p: &mut Vec<usize>) -> usize {
            if p[x] != x {
                p[x] = find(p[x], p);
            }
            p[x]
        }

        for a in 0..n {
            for &b in &graph[a] {
                let pa = find(a, &mut p);
                let pb = find(b as usize, &mut p);
                if pa == pb {
                    return false;
                }
                p[pb] = find(graph[a][0] as usize, &mut p);
            }
        }
        true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
