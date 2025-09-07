---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3613.Minimize%20Maximum%20Component%20Cost/README_EN.md
rating: 1641
source: Weekly Contest 458 Q2
tags:
    - Union Find
    - Graph
    - Binary Search
    - Sorting
---

<!-- problem:start -->

# [3613. Minimize Maximum Component Cost](https://leetcode.com/problems/minimize-maximum-component-cost)

[中文文档](/solution/3600-3699/3613.Minimize%20Maximum%20Component%20Cost/README.md)

## Description

<!-- description:start -->

<p data-end="331" data-start="85">You are given an undirected connected graph with <code data-end="137" data-start="134">n</code> nodes labeled from 0 to <code data-end="171" data-start="164">n - 1</code> and a 2D integer array <code data-end="202" data-start="195">edges</code> where <code data-end="234" data-start="209">edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> denotes an undirected edge between node <code data-end="279" data-start="275">u<sub>i</sub></code> and node <code data-end="293" data-start="289">v<sub>i</sub></code> with weight <code data-end="310" data-start="306">w<sub>i</sub></code>, and an integer <code data-end="330" data-start="327">k</code>.</p>

<p data-end="461" data-start="333">You are allowed to remove any number of edges from the graph such that the resulting graph has <strong>at most</strong> <code data-end="439" data-start="436">k</code> connected components.</p>

<p data-end="589" data-start="463">The <strong>cost</strong> of a component is defined as the <strong>maximum</strong> edge weight in that component. If a component has no edges, its cost is 0.</p>

<p data-end="760" data-start="661">Return the <strong>minimum</strong> possible value of the <strong>maximum</strong> cost among all components <strong data-end="759" data-start="736">after such removals</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, edges = [[0,1,4],[1,2,3],[1,3,2],[3,4,6]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3613.Minimize%20Maximum%20Component%20Cost/images/minimizemaximumm.jpg" style="width: 535px; height: 225px;" /></p>

<ul>
	<li data-end="1070" data-start="1021">Remove the edge between nodes 3 and 4 (weight 6).</li>
	<li data-end="1141" data-start="1073">The resulting components have costs of 0 and 4, so the overall maximum cost is 4.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[0,1,5],[1,2,5],[2,3,5]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3613.Minimize%20Maximum%20Component%20Cost/images/minmax2.jpg" style="width: 315px; height: 55px;" /></p>

<ul>
	<li data-end="1315" data-start="1251">No edge can be removed, since allowing only one component (<code>k = 1</code>) requires the graph to stay fully connected.</li>
	<li data-end="1389" data-start="1318">That single component&rsquo;s cost equals its largest edge weight, which is 5.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li>The input graph is connected.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Union-Find

If $k = n$, it means all edges can be removed. In this case, all connected components are isolated nodes, and the maximum cost is 0.

Otherwise, we can sort all edges by weight in ascending order, then use a union-find data structure to maintain connected components.

We assume that initially all nodes are not connected, with each node being an independent connected component. Starting from the edge with the smallest weight, we try to add it to the current connected components. If the number of connected components becomes less than or equal to $k$ after adding the edge, it means all remaining edges can be removed, and the weight of the current edge is the maximum cost we are looking for. We return this weight. Otherwise, we continue processing the next edge.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, n: int, edges: List[List[int]], k: int) -> int:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        if k == n:
            return 0
        edges.sort(key=lambda x: x[2])
        cnt = n
        p = list(range(n))
        for u, v, w in edges:
            pu, pv = find(u), find(v)
            if pu != pv:
                p[pu] = pv
                cnt -= 1
                if cnt <= k:
                    return w
        return 0
```

#### Java

```java
class Solution {
    private int[] p;

    public int minCost(int n, int[][] edges, int k) {
        if (k == n) {
            return 0;
        }
        p = new int[n];
        Arrays.setAll(p, i -> i);
        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));
        int cnt = n;
        for (var e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int pu = find(u), pv = find(v);
            if (pu != pv) {
                p[pu] = pv;
                if (--cnt <= k) {
                    return w;
                }
            }
        }
        return 0;
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
    int minCost(int n, vector<vector<int>>& edges, int k) {
        if (k == n) {
            return 0;
        }
        vector<int> p(n);
        ranges::iota(p, 0);
        ranges::sort(edges, {}, [](const auto& e) { return e[2]; });
        auto find = [&](this auto&& find, int x) -> int {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        int cnt = n;
        for (const auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int pu = find(u), pv = find(v);
            if (pu != pv) {
                p[pu] = pv;
                if (--cnt <= k) {
                    return w;
                }
            }
        }
        return 0;
    }
};
```

#### Go

```go
func minCost(n int, edges [][]int, k int) int {
	p := make([]int, n)
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

	if k == n {
		return 0
	}

	slices.SortFunc(edges, func(a, b []int) int {
		return a[2] - b[2]
	})

	cnt := n
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		pu, pv := find(u), find(v)
		if pu != pv {
			p[pu] = pv
			if cnt--; cnt <= k {
				return w
			}
		}
	}

	return 0
}
```

#### TypeScript

```ts
function minCost(n: number, edges: number[][], k: number): number {
    const p: number[] = Array.from({ length: n }, (_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };

    if (k === n) {
        return 0;
    }

    edges.sort((a, b) => a[2] - b[2]);
    let cnt = n;
    for (const [u, v, w] of edges) {
        const pu = find(u),
            pv = find(v);
        if (pu !== pv) {
            p[pu] = pv;
            if (--cnt <= k) {
                return w;
            }
        }
    }
    return 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
