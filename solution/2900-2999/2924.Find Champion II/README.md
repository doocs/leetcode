---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2924.Find%20Champion%20II/README.md
rating: 1430
source: 第 370 场周赛 Q2
tags:
    - 图
---

<!-- problem:start -->

# [2924. 找到冠军 II](https://leetcode.cn/problems/find-champion-ii)

[English Version](/solution/2900-2999/2924.Find%20Champion%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一场比赛中共有 <code>n</code> 支队伍，按从 <code>0</code> 到&nbsp; <code>n - 1</code> 编号。每支队伍也是 <strong>有向无环图（DAG）</strong> 上的一个节点。</p>

<p>给你一个整数 <code>n</code> 和一个下标从 <strong>0</strong> 开始、长度为 <code>m</code> 的二维整数数组 <code>edges</code> 表示这个有向无环图，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示图中存在一条从 <code>u<sub>i</sub></code> 队到 <code>v<sub>i</sub></code> 队的有向边。</p>

<p>从 <code>a</code> 队到 <code>b</code> 队的有向边意味着 <code>a</code> 队比 <code>b</code> 队 <strong>强</strong> ，也就是 <code>b</code> 队比 <code>a</code> 队 <strong>弱</strong> 。</p>

<p>在这场比赛中，如果不存在某支强于 <code>a</code> 队的队伍，则认为 <code>a</code> 队将会是 <strong>冠军</strong> 。</p>

<p>如果这场比赛存在 <strong>唯一</strong> 一个冠军，则返回将会成为冠军的队伍。否则，返回<em> </em><code>-1</code><em> 。</em></p>

<p><strong>注意</strong></p>

<ul>
	<li><strong>环</strong> 是形如 <code>a<sub>1</sub>, a<sub>2</sub>, ..., a<sub>n</sub>, a<sub>n+1</sub></code> 的一个序列，且满足：节点 <code>a<sub>1</sub></code> 与节点 <code>a<sub>n+1</sub></code> 是同一个节点；节点 <code>a<sub>1</sub>, a<sub>2</sub>, ..., a<sub>n</sub></code> 互不相同；对于范围&nbsp;<code>[1, n]</code> 中的每个 <code>i</code> ，均存在一条从节点 <code>a<sub>i</sub></code> 到节点 <code>a<sub>i+1</sub></code> 的有向边。</li>
	<li><strong>有向无环图</strong> 是不存在任何环的有向图。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img height="300" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2924.Find%20Champion%20II/images/graph-3.png" width="300" /></p>

<pre>
<strong>输入：</strong>n = 3, edges = [[0,1],[1,2]]
<strong>输出：</strong>0
<strong>解释：</strong>1 队比 0 队弱。2 队比 1 队弱。所以冠军是 0 队。
</pre>

<p><strong class="example">示例 2：</strong></p>

<p><img height="300" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2924.Find%20Champion%20II/images/graph-4.png" width="300" /></p>

<pre>
<strong>输入：</strong>n = 4, edges = [[0,2],[1,3],[1,2]]
<strong>输出：</strong>-1
<strong>解释：</strong>2 队比 0 队和 1 队弱。3 队比 1 队弱。但是 1 队和 0 队之间不存在强弱对比。所以答案是 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>m == edges.length</code></li>
	<li><code>0 &lt;= m &lt;= n * (n - 1) / 2</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= edge[i][j] &lt;= n - 1</code></li>
	<li><code>edges[i][0] != edges[i][1]</code></li>
	<li>生成的输入满足：如果 <code>a</code> 队比 <code>b</code> 队强，就不存在 <code>b</code> 队比 <code>a</code> 队强</li>
	<li>生成的输入满足：如果 <code>a</code> 队比 <code>b</code> 队强，<code>b</code> 队比 <code>c</code> 队强，那么 <code>a</code> 队比 <code>c</code> 队强</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：统计入度

根据题目描述，我们只需要统计每个节点的入度，记录在数组 $indeg$ 中。如果只有一个节点的入度为 $0$，那么这个节点就是冠军，否则不存在唯一的冠军。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是节点的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findChampion(self, n: int, edges: List[List[int]]) -> int:
        indeg = [0] * n
        for _, v in edges:
            indeg[v] += 1
        return -1 if indeg.count(0) != 1 else indeg.index(0)
```

#### Java

```java
class Solution {
    public int findChampion(int n, int[][] edges) {
        int[] indeg = new int[n];
        for (var e : edges) {
            ++indeg[e[1]];
        }
        int ans = -1, cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                ++cnt;
                ans = i;
            }
        }
        return cnt == 1 ? ans : -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findChampion(int n, vector<vector<int>>& edges) {
        int indeg[n];
        memset(indeg, 0, sizeof(indeg));
        for (auto& e : edges) {
            ++indeg[e[1]];
        }
        int ans = -1, cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                ++cnt;
                ans = i;
            }
        }
        return cnt == 1 ? ans : -1;
    }
};
```

#### Go

```go
func findChampion(n int, edges [][]int) int {
	indeg := make([]int, n)
	for _, e := range edges {
		indeg[e[1]]++
	}
	ans, cnt := -1, 0
	for i, x := range indeg {
		if x == 0 {
			cnt++
			ans = i
		}
	}
	if cnt == 1 {
		return ans
	}
	return -1
}
```

#### TypeScript

```ts
function findChampion(n: number, edges: number[][]): number {
    const indeg: number[] = Array(n).fill(0);
    for (const [_, v] of edges) {
        ++indeg[v];
    }
    let [ans, cnt] = [-1, 0];
    for (let i = 0; i < n; ++i) {
        if (indeg[i] === 0) {
            ++cnt;
            ans = i;
        }
    }
    return cnt === 1 ? ans : -1;
}
```

#### JavaScript

```js
function findChampion(n, edges) {
    const indeg = Array(n).fill(0);
    for (const [_, v] of edges) {
        ++indeg[v];
    }
    let [ans, cnt] = [-1, 0];
    for (let i = 0; i < n; ++i) {
        if (indeg[i] === 0) {
            ++cnt;
            ans = i;
        }
    }
    return cnt === 1 ? ans : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### TypeScript

```ts
function findChampion(n: number, edges: number[][]): number {
    const vertexes = new Set<number>(Array.from({ length: n }, (_, i) => i));

    for (const [_, v] of edges) {
        vertexes.delete(v);
    }

    return vertexes.size === 1 ? vertexes[Symbol.iterator]().next().value! : -1;
}
```

#### JavaScript

```js
function findChampion(n, edges) {
    const vertexes = new Set(Array.from({ length: n }, (_, i) => i));
    for (const [_, v] of edges) {
        vertexes.delete(v);
    }
    return vertexes.size === 1 ? vertexes[Symbol.iterator]().next().value : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
