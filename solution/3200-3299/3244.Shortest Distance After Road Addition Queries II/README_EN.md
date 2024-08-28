---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3244.Shortest%20Distance%20After%20Road%20Addition%20Queries%20II/README_EN.md
rating: 2270
source: Weekly Contest 409 Q3
tags:
    - Greedy
    - Graph
    - Array
    - Ordered Set
---

<!-- problem:start -->

# [3244. Shortest Distance After Road Addition Queries II](https://leetcode.com/problems/shortest-distance-after-road-addition-queries-ii)

[中文文档](/solution/3200-3299/3244.Shortest%20Distance%20After%20Road%20Addition%20Queries%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> and a 2D integer array <code>queries</code>.</p>

<p>There are <code>n</code> cities numbered from <code>0</code> to <code>n - 1</code>. Initially, there is a <strong>unidirectional</strong> road from city <code>i</code> to city <code>i + 1</code> for all <code>0 &lt;= i &lt; n - 1</code>.</p>

<p><code>queries[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> represents the addition of a new <strong>unidirectional</strong> road from city <code>u<sub>i</sub></code> to city <code>v<sub>i</sub></code>. After each query, you need to find the <strong>length</strong> of the <strong>shortest path</strong> from city <code>0</code> to city <code>n - 1</code>.</p>

<p>There are no two queries such that <code>queries[i][0] &lt; queries[j][0] &lt; queries[i][1] &lt; queries[j][1]</code>.</p>

<p>Return an array <code>answer</code> where for each <code>i</code> in the range <code>[0, queries.length - 1]</code>, <code>answer[i]</code> is the <em>length of the shortest path</em> from city <code>0</code> to city <code>n - 1</code> after processing the <strong>first </strong><code>i + 1</code> queries.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, queries = [[2,4],[0,2],[0,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,2,1]</span></p>

<p><strong>Explanation: </strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3244.Shortest%20Distance%20After%20Road%20Addition%20Queries%20II/images/image8.jpg" style="width: 350px; height: 60px;" /></p>

<p>After the addition of the road from 2 to 4, the length of the shortest path from 0 to 4 is 3.</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3244.Shortest%20Distance%20After%20Road%20Addition%20Queries%20II/images/image9.jpg" style="width: 350px; height: 60px;" /></p>

<p>After the addition of the road from 0 to 2, the length of the shortest path from 0 to 4 is 2.</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3244.Shortest%20Distance%20After%20Road%20Addition%20Queries%20II/images/image10.jpg" style="width: 350px; height: 96px;" /></p>

<p>After the addition of the road from 0 to 4, the length of the shortest path from 0 to 4 is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, queries = [[0,3],[0,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3244.Shortest%20Distance%20After%20Road%20Addition%20Queries%20II/images/image11.jpg" style="width: 300px; height: 70px;" /></p>

<p>After the addition of the road from 0 to 3, the length of the shortest path from 0 to 3 is 1.</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3244.Shortest%20Distance%20After%20Road%20Addition%20Queries%20II/images/image12.jpg" style="width: 300px; height: 70px;" /></p>

<p>After the addition of the road from 0 to 2, the length of the shortest path remains 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= queries[i][0] &lt; queries[i][1] &lt; n</code></li>
	<li><code>1 &lt; queries[i][1] - queries[i][0]</code></li>
	<li>There are no repeated roads among the queries.</li>
	<li>There are no two queries such that <code>i != j</code> and <code>queries[i][0] &lt; queries[j][0] &lt; queries[i][1] &lt; queries[j][1]</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Recording Jump Positions

We define an array $\textit{nxt}$ of length $n - 1$, where $\textit{nxt}[i]$ represents the next city that can be reached from city $i$. Initially, $\textit{nxt}[i] = i + 1$.

For each query $[u, v]$, if $u'$ and $v'$ have already been connected before, and $u' \leq u < v \leq v'$, then we can skip this query. Otherwise, we need to set the next city number for cities from $\textit{nxt}[u]$ to $\textit{nxt}[v - 1]$ to $0$, and set $\textit{nxt}[u]$ to $v$.

During this process, we maintain a variable $\textit{cnt}$, which represents the length of the shortest path from city $0$ to city $n - 1$. Initially, $\textit{cnt} = n - 1$. Each time we set the next city number for cities in $[\textit{nxt}[u], \textit{v})$ to $0$, $\textit{cnt}$ decreases by $1$.

Time complexity is $O(n + q)$, and space complexity is $O(n)$. Here, $n$ and $q$ are the number of cities and the number of queries, respectively.

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
