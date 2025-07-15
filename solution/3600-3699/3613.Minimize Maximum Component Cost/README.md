---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3613.Minimize%20Maximum%20Component%20Cost/README.md
tags:
    - 排序
    - 并查集
    - 图
    - 二分查找
---

<!-- problem:start -->

# [3613. 最小化连通分量的最大成本](https://leetcode.cn/problems/minimize-maximum-component-cost)

[English Version](/solution/3600-3699/3613.Minimize%20Maximum%20Component%20Cost/README_EN.md)

## 题目描述

<!-- description:start -->

<p data-end="331" data-start="85">给你一个无向连通图，包含 <code data-end="137" data-start="134">n</code> 个节点，节点编号从 0 到 <code data-end="171" data-start="164">n - 1</code>，以及一个二维整数数组 <code data-end="202" data-start="195">edges</code>，其中 <code data-end="234" data-start="209">edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> 表示一条连接节点 <code data-end="279" data-start="275">u<sub>i</sub></code> 和节点 <code data-end="293" data-start="289">v<sub>i</sub></code> 的无向边，边权为 <code data-end="310" data-start="306">w<sub>i</sub></code>，另有一个整数 <code data-end="330" data-start="327">k</code>。</p>

<p data-end="461" data-start="333">你可以从图中移除任意数量的边，使得最终的图中&nbsp;<strong>最多&nbsp;</strong>只包含 <code data-end="439" data-start="436">k</code> 个连通分量。</p>

<p data-end="589" data-start="463">连通分量的 <strong>成本&nbsp;</strong>定义为该分量中边权的&nbsp;<strong>最大值&nbsp;</strong>。如果一个连通分量没有边，则其代价为 0。</p>

<p data-end="760" data-start="661">请返回在移除这些边之后，在所有连通分量之中的&nbsp;<strong>最大成本&nbsp;</strong>的&nbsp;<strong>最小可能值&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 5, edges = [[0,1,4],[1,2,3],[1,3,2],[3,4,6]], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3613.Minimize%20Maximum%20Component%20Cost/images/minimizemaximumm.jpg" style="width: 535px; height: 225px;" /></p>

<ul>
	<li data-end="1070" data-start="1021">移除节点 3 和节点 4 之间的边（权值为 6）。</li>
	<li data-end="1141" data-start="1073">最终的连通分量成本分别为 0 和 4，因此最大代价为 4。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 4, edges = [[0,1,5],[1,2,5],[2,3,5]], k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3613.Minimize%20Maximum%20Component%20Cost/images/minmax2.jpg" style="width: 315px; height: 55px;" /></p>

<ul>
	<li data-end="1315" data-start="1251">无法移除任何边，因为只允许一个连通分量（<code>k = 1</code>），图必须保持完全连通。</li>
	<li data-end="1389" data-start="1318">该连通分量的成本等于其最大边权，即 5。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li>输入图是连通图。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 并查集

如果 $k = n$，说明所有的边都可以被移除，此时所有的连通分量都是孤立的节点，最大成本为 0。

否则，我们可以将所有的边按权值从小到大排序，然后使用并查集来维护连通分量。

我们不妨假设初始时所有节点并不联通，初始时每个节点都是一个独立的连通分量。我们从权值最小的边开始，尝试将其加入到当前的连通分量中。如果加入后连通分量的数量已经小于等于 $k$，则说明剩余的边都可以被移除，此时当前边的权值就是我们要找的最大成本，返回该权值即可。否则，我们继续处理下一条边。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是节点的数量。

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
