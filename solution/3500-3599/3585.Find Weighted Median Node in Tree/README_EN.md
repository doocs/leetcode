---
comments: true
difficulty: Hard
rating: 2428
source: Weekly Contest 454 Q4
tags:
    - Bit Manipulation
    - Tree
    - Depth-First Search
    - Array
    - Binary Search
    - Dynamic Programming
---

<!-- problem:start -->

# [3585. Find Weighted Median Node in Tree](https://leetcode.com/problems/find-weighted-median-node-in-tree)

[中文文档](/solution/3500-3599/3585.Find%20Weighted%20Median%20Node%20in%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> and an <strong>undirected, weighted</strong> tree rooted at node 0 with <code>n</code> nodes numbered from 0 to <code>n - 1</code>. This is represented by a 2D array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> indicates an edge from node <code>u<sub>i</sub></code> to <code>v<sub>i</sub></code> with weight <code>w<sub>i</sub></code>.</p>

<p>The <strong>weighted median node</strong> is defined as the <strong>first</strong> node <code>x</code> on the path from <code>u<sub>i</sub></code> to <code>v<sub>i</sub></code> such that the sum of edge weights from <code>u<sub>i</sub></code> to <code>x</code> is <strong>greater than or equal to half</strong> of the total path weight.</p>

<p>You are given a 2D integer array <code>queries</code>. For each <code>queries[j] = [u<sub>j</sub>, v<sub>j</sub>]</code>, determine the weighted median node along the path from <code>u<sub>j</sub></code> to <code>v<sub>j</sub></code>.</p>

<p>Return an array <code>ans</code>, where <code>ans[j]</code> is the node index of the weighted median for <code>queries[j]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, edges = [[0,1,7]], queries = [[1,0],[0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,1]</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3585.Find%20Weighted%20Median%20Node%20in%20Tree/images/screenshot-2025-05-26-at-193447.png" style="width: 200px; height: 64px;" /></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Query</th>
			<th style="border: 1px solid black;">Path</th>
			<th style="border: 1px solid black;">Edge<br />
			Weights</th>
			<th style="border: 1px solid black;">Total<br />
			Path<br />
			Weight</th>
			<th style="border: 1px solid black;">Half</th>
			<th style="border: 1px solid black;">Explanation</th>
			<th style="border: 1px solid black;">Answer</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 0]</code></td>
			<td style="border: 1px solid black;"><code>1 &rarr; 0</code></td>
			<td style="border: 1px solid black;"><code>[7]</code></td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">3.5</td>
			<td style="border: 1px solid black;">Sum from <code>1 &rarr; 0 = 7 &gt;= 3.5</code>, median is node 0.</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[0, 1]</code></td>
			<td style="border: 1px solid black;"><code>0 &rarr; 1</code></td>
			<td style="border: 1px solid black;"><code>[7]</code></td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">3.5</td>
			<td style="border: 1px solid black;">Sum from <code>0 &rarr; 1 = 7 &gt;= 3.5</code>, median is node 1.</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,2],[2,0,4]], queries = [[0,1],[2,0],[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,0,2]</span></p>

<p><strong>E</strong><strong>xplanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3585.Find%20Weighted%20Median%20Node%20in%20Tree/images/screenshot-2025-05-26-at-193610.png" style="width: 180px; height: 149px;" /></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Query</th>
			<th style="border: 1px solid black;">Path</th>
			<th style="border: 1px solid black;">Edge<br />
			Weights</th>
			<th style="border: 1px solid black;">Total<br />
			Path<br />
			Weight</th>
			<th style="border: 1px solid black;">Half</th>
			<th style="border: 1px solid black;">Explanation</th>
			<th style="border: 1px solid black;">Answer</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;"><code>[0, 1]</code></td>
			<td style="border: 1px solid black;"><code>0 &rarr; 1</code></td>
			<td style="border: 1px solid black;"><code>[2]</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Sum from <code>0 &rarr; 1 = 2 &gt;= 1</code>, median is node 1.</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2, 0]</code></td>
			<td style="border: 1px solid black;"><code>2 &rarr; 0</code></td>
			<td style="border: 1px solid black;"><code>[4]</code></td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">Sum from <code>2 &rarr; 0 = 4 &gt;= 2</code>, median is node 0.</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 2]</code></td>
			<td style="border: 1px solid black;"><code>1 &rarr; 0 &rarr; 2</code></td>
			<td style="border: 1px solid black;"><code>[2, 4]</code></td>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">Sum from <code>1 &rarr; 0 = 2 &lt; 3</code>.<br />
			Sum from <code>1 &rarr; 2 = 2 + 4 = 6 &gt;= 3</code>, median is node 2.</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, edges = [[0,1,2],[0,2,5],[1,3,1],[2,4,3]], queries = [[3,4],[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,2]</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3585.Find%20Weighted%20Median%20Node%20in%20Tree/images/screenshot-2025-05-26-at-193857.png" style="width: 150px; height: 229px;" /></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Query</th>
			<th style="border: 1px solid black;">Path</th>
			<th style="border: 1px solid black;">Edge<br />
			Weights</th>
			<th style="border: 1px solid black;">Total<br />
			Path<br />
			Weight</th>
			<th style="border: 1px solid black;">Half</th>
			<th style="border: 1px solid black;">Explanation</th>
			<th style="border: 1px solid black;">Answer</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;"><code>[3, 4]</code></td>
			<td style="border: 1px solid black;"><code>3 &rarr; 1 &rarr; 0 &rarr; 2 &rarr; 4</code></td>
			<td style="border: 1px solid black;"><code>[1, 2, 5, 3]</code></td>
			<td style="border: 1px solid black;">11</td>
			<td style="border: 1px solid black;">5.5</td>
			<td style="border: 1px solid black;">Sum from <code>3 &rarr; 1 = 1 &lt; 5.5</code>.<br />
			Sum from <code>3 &rarr; 0 = 1 + 2 = 3 &lt; 5.5</code>.<br />
			Sum from <code>3 &rarr; 2 = 1 + 2 + 5 = 8 &gt;= 5.5</code>, median is node 2.</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 2]</code></td>
			<td style="border: 1px solid black;"><code>1 &rarr; 0 &rarr; 2</code></td>
			<td style="border: 1px solid black;"><code>[2, 5]</code></td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">3.5</td>
			<td style="border: 1px solid black;">
			<p>Sum from <code>1 &rarr; 0 = 2 &lt; 3.5</code>.<br />
			Sum from <code>1 &rarr; 2 = 2 + 5 = 7 &gt;= 3.5</code>, median is node 2.</p>
			</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
	</tbody>
</table>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[j] == [u<sub>j</sub>, v<sub>j</sub>]</code></li>
	<li><code>0 &lt;= u<sub>j</sub>, v<sub>j</sub> &lt; n</code></li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> The weighted median is the first vertex on $u \to v$ whose prefix weight from $u$ is at least half the path. $n,q \le 10^5$ call for LCA and weighted prefixes.
>
> After $lca$ and the total $W$, binary-lift on $u \to lca$ or $lca \to v$ to the farthest node whose prefix is still $< W/2$, then take one more step.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp
class Solution {
public:
    vector<int> findMedian(int n, vector<vector<int>>& edges, vector<vector<int>>& queries) {
        
    }
};
```

#### Go

```go

```
#### TypeScript

```ts
function findMedian(n: number, edges: number[][], queries: number[][]): number[] {
    const LOG = 17;
    const adj: number[][][] = Array.from({length: n}, () => []);
    for (const [u, v, w] of edges) {
        adj[u].push([v, w]);
        adj[v].push([u, w]);
    }

    const depth = new Array<number>(n).fill(0);
    const dist = new Array<number>(n).fill(0);
    const up: number[][] = Array.from({length: LOG}, () => new Array<number>(n).fill(-1));

    // BFS from root 0 to compute depth, dist, and parent
    const visited = new Uint8Array(n);
    const queue: number[] = [0];
    visited[0] = 1;
    let head = 0;
    while (head < queue.length) {
        const u = queue[head++];
        for (const [v, w] of adj[u]) {
            if (!visited[v]) {
                visited[v] = 1;
                depth[v] = depth[u] + 1;
                dist[v] = dist[u] + w;
                up[0][v] = u;
                queue.push(v);
            }
        }
    }

    // Build binary lifting table
    for (let k = 1; k < LOG; k++) {
        for (let v = 0; v < n; v++) {
            if (up[k - 1][v] !== -1) up[k][v] = up[k - 1][up[k - 1][v]];
        }
    }

    function lca(a: number, b: number): number {
        if (depth[a] < depth[b]) [a, b] = [b, a];
        let diff = depth[a] - depth[b];
        for (let k = 0; diff > 0; k++, diff >>= 1) {
            if (diff & 1) a = up[k][a];
        }
        if (a === b) return a;
        for (let k = LOG - 1; k >= 0; k--) {
            if (up[k][a] !== up[k][b]) {
                a = up[k][a];
                b = up[k][b];
            }
        }
        return up[0][a];
    }

    const ans: number[] = [];

    for (const [u, v] of queries) {
        if (u === v) { ans.push(u); continue; }

        const l = lca(u, v);
        const W = dist[u] + dist[v] - 2 * dist[l];

        // Case 1: median is on u → LCA segment
        if (2 * (dist[u] - dist[l]) >= W) {
            let cur = u;
            for (let k = LOG - 1; k >= 0; k--) {
                const j = up[k][cur];
                if (j !== -1 && depth[j] >= depth[l] && 2 * (dist[u] - dist[j]) < W) {
                    cur = j;
                }
            }
            ans.push(up[0][cur]); // one step further = first node with prefix ≥ W/2
        }
        // Case 2: median is on LCA → v segment (below LCA)
        else {
            let cur = v;
            for (let k = LOG - 1; k >= 0; k--) {
                const j = up[k][cur];
                if (j !== -1 && depth[j] > depth[l] && 2 * (dist[u] + dist[j] - 2 * dist[l]) >= W) {
                    cur = j;
                }
            }
            ans.push(cur); // highest node on v-side where prefix ≥ W/2
        }
    }

    return ans;
}
```


<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
