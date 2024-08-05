---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3244.Shortest%20Distance%20After%20Road%20Addition%20Queries%20II/README.md
---

<!-- problem:start -->

# [3244. 新增道路查询后的最短距离 II](https://leetcode.cn/problems/shortest-distance-after-road-addition-queries-ii)

[English Version](/solution/3200-3299/3244.Shortest%20Distance%20After%20Road%20Addition%20Queries%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code> 和一个二维整数数组 <code>queries</code>。</p>

<p>有 <code>n</code> 个城市，编号从 <code>0</code> 到 <code>n - 1</code>。初始时，每个城市 <code>i</code> 都有一条<strong>单向</strong>道路通往城市 <code>i + 1</code>（ <code>0 &lt;= i &lt; n - 1</code>）。</p>

<p><code>queries[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示新建一条从城市 <code>u<sub>i</sub></code> 到城市 <code>v<sub>i</sub></code> 的<strong>单向</strong>道路。每次查询后，你需要找到从城市 <code>0</code> 到城市 <code>n - 1</code> 的<strong>最短路径</strong>的<strong>长度</strong>。</p>

<p>所有查询中不会存在两个查询都满足 <code>queries[i][0] &lt; queries[j][0] &lt; queries[i][1] &lt; queries[j][1]</code>。</p>

<p>返回一个数组 <code>answer</code>，对于范围 <code>[0, queries.length - 1]</code> 中的每个 <code>i</code>，<code>answer[i]</code> 是处理完<strong>前</strong> <code>i + 1</code> 个查询后，从城市 <code>0</code> 到城市 <code>n - 1</code> 的最短路径的<em>长度</em>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 5, queries = [[2, 4], [0, 2], [0, 4]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[3, 2, 1]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3244.Shortest%20Distance%20After%20Road%20Addition%20Queries%20II/images/image8.jpg" style="width: 350px; height: 60px;" /></p>

<p>新增一条从 2 到 4 的道路后，从 0 到 4 的最短路径长度为 3。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3244.Shortest%20Distance%20After%20Road%20Addition%20Queries%20II/images/image9.jpg" style="width: 350px; height: 60px;" /></p>

<p>新增一条从 0 到 2 的道路后，从 0 到 4 的最短路径长度为 2。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3244.Shortest%20Distance%20After%20Road%20Addition%20Queries%20II/images/image10.jpg" style="width: 350px; height: 96px;" /></p>

<p>新增一条从 0 到 4 的道路后，从 0 到 4 的最短路径长度为 1。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 4, queries = [[0, 3], [0, 2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1, 1]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3244.Shortest%20Distance%20After%20Road%20Addition%20Queries%20II/images/image11.jpg" style="width: 300px; height: 70px;" /></p>

<p>新增一条从 0 到 3 的道路后，从 0 到 3 的最短路径长度为 1。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3244.Shortest%20Distance%20After%20Road%20Addition%20Queries%20II/images/image12.jpg" style="width: 300px; height: 70px;" /></p>

<p>新增一条从 0 到 2 的道路后，从 0 到 3 的最短路径长度仍为 1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= queries[i][0] &lt; queries[i][1] &lt; n</code></li>
	<li><code>1 &lt; queries[i][1] - queries[i][0]</code></li>
	<li>查询中不存在重复的道路。</li>
	<li>不存在两个查询都满足 <code>i != j</code> 且 <code>queries[i][0] &lt; queries[j][0] &lt; queries[i][1] &lt; queries[j][1]</code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 记录跳转位置

我们定义一个长度为 $n - 1$ 的数组 $\textit{nxt}$，其中 $\textit{nxt}[i]$ 表示从城市 $i$ 可以到达的下一个城市的编号。初始时 $\textit{nxt}[i] = i + 1$。

对于每次查询 $[u, v]$，如果此前已经连通了 $u'$ 和 $v'$，且 $u' <= u < v <= v'$，那么我们可以跳过这次查询。否则，我们需要将 $nxt[u]$ 到 $nxt[v - 1]$ 这些城市的下一个城市编号设置为 $0$，并将 $nxt[u]$ 设置为 $v$。

在这个过程中，我们维护一个变量 $\textit{cnt}$，表示从城市 $0$ 到城市 $n - 1$ 的最短路径的长度。初始时 $\textit{cnt} = n - 1$。每一次，如果我们将 $[\textit{nxt}[u], \textit{v})$ 这些城市的下一个城市编号设置为 $0$，那么 $\textit{cnt}$ 就会减少 $1$。

时间复杂度 $O(n + q)$，空间复杂度 $O(n)$。其中 $n$ 和 $q$ 分别是城市数量和查询数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def shortestDistanceAfterQueries(
        self, n: int, queries: List[List[int]]
    ) -> List[int]:
        nxt = list(range(1, n))
        ans = []
        cnt = n - 1
        for u, v in queries:
            if 0 < nxt[u] < v:
                i = nxt[u]
                while i < v:
                    cnt -= 1
                    nxt[i], i = 0, nxt[i]
                nxt[u] = v
            ans.append(cnt)
        return ans
```

#### Java

```java
class Solution {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int[] nxt = new int[n - 1];
        for (int i = 1; i < n; ++i) {
            nxt[i - 1] = i;
        }
        int m = queries.length;
        int cnt = n - 1;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int u = queries[i][0], v = queries[i][1];
            if (nxt[u] > 0 && nxt[u] < v) {
                int j = nxt[u];
                while (j < v) {
                    --cnt;
                    int t = nxt[j];
                    nxt[j] = 0;
                    j = t;
                }
                nxt[u] = v;
            }
            ans[i] = cnt;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> shortestDistanceAfterQueries(int n, vector<vector<int>>& queries) {
        vector<int> nxt(n - 1);
        iota(nxt.begin(), nxt.end(), 1);
        int cnt = n - 1;
        vector<int> ans;
        for (const auto& q : queries) {
            int u = q[0], v = q[1];
            if (nxt[u] && nxt[u] < v) {
                int i = nxt[u];
                while (i < v) {
                    --cnt;
                    int t = nxt[i];
                    nxt[i] = 0;
                    i = t;
                }
                nxt[u] = v;
            }
            ans.push_back(cnt);
        }
        return ans;
    }
};
```

#### Go

```go
func shortestDistanceAfterQueries(n int, queries [][]int) (ans []int) {
	nxt := make([]int, n-1)
	for i := range nxt {
		nxt[i] = i + 1
	}
	cnt := n - 1
	for _, q := range queries {
		u, v := q[0], q[1]
		if nxt[u] > 0 && nxt[u] < v {
			i := nxt[u]
			for i < v {
				cnt--
				nxt[i], i = 0, nxt[i]
			}
			nxt[u] = v
		}
		ans = append(ans, cnt)
	}
	return
}
```

#### TypeScript

```ts
function shortestDistanceAfterQueries(n: number, queries: number[][]): number[] {
    const nxt: number[] = Array.from({ length: n - 1 }, (_, i) => i + 1);
    const ans: number[] = [];
    let cnt = n - 1;
    for (const [u, v] of queries) {
        if (nxt[u] && nxt[u] < v) {
            let i = nxt[u];
            while (i < v) {
                --cnt;
                [nxt[i], i] = [0, nxt[i]];
            }
            nxt[u] = v;
        }
        ans.push(cnt);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
