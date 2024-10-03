---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1928.Minimum%20Cost%20to%20Reach%20Destination%20in%20Time/README_EN.md
rating: 2413
source: Biweekly Contest 56 Q4
tags:
    - Graph
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [1928. Minimum Cost to Reach Destination in Time](https://leetcode.com/problems/minimum-cost-to-reach-destination-in-time)

[中文文档](/solution/1900-1999/1928.Minimum%20Cost%20to%20Reach%20Destination%20in%20Time/README.md)

## Description

<!-- description:start -->

<p>There is a country of <code>n</code> cities numbered from <code>0</code> to <code>n - 1</code> where <strong>all the cities are connected</strong> by bi-directional roads. The roads are represented as a 2D integer array <code>edges</code> where <code>edges[i] = [x<sub>i</sub>, y<sub>i</sub>, time<sub>i</sub>]</code> denotes a road between cities <code>x<sub>i</sub></code> and <code>y<sub>i</sub></code> that takes <code>time<sub>i</sub></code> minutes to travel. There may be multiple roads of differing travel times connecting the same two cities, but no road connects a city to itself.</p>

<p>Each time you pass through a city, you must pay a passing fee. This is represented as a <strong>0-indexed</strong> integer array <code>passingFees</code> of length <code>n</code> where <code>passingFees[j]</code> is the amount of dollars you must pay when you pass through city <code>j</code>.</p>

<p>In the beginning, you are at city <code>0</code> and want to reach city <code>n - 1</code> in <code>maxTime</code><strong> minutes or less</strong>. The <strong>cost</strong> of your journey is the <strong>summation of passing fees</strong> for each city that you passed through at some moment of your journey (<strong>including</strong> the source and destination cities).</p>

<p>Given <code>maxTime</code>, <code>edges</code>, and <code>passingFees</code>, return <em>the <strong>minimum cost</strong> to complete your journey, or </em><code>-1</code><em> if you cannot complete it within </em><code>maxTime</code><em> minutes</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1928.Minimum%20Cost%20to%20Reach%20Destination%20in%20Time/images/leetgraph1-1.png" style="width: 371px; height: 171px;" /></p>

<pre>
<strong>Input:</strong> maxTime = 30, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
<strong>Output:</strong> 11
<strong>Explanation:</strong> The path to take is 0 -&gt; 1 -&gt; 2 -&gt; 5, which takes 30 minutes and has $11 worth of passing fees.
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1928.Minimum%20Cost%20to%20Reach%20Destination%20in%20Time/images/copy-of-leetgraph1-1.png" style="width: 371px; height: 171px;" /></strong></p>

<pre>
<strong>Input:</strong> maxTime = 29, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
<strong>Output:</strong> 48
<strong>Explanation:</strong> The path to take is 0 -&gt; 3 -&gt; 4 -&gt; 5, which takes 26 minutes and has $48 worth of passing fees.
You cannot take path 0 -&gt; 1 -&gt; 2 -&gt; 5 since it would take too long.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> maxTime = 25, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no way to reach city 5 from city 0 within 25 minutes.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= maxTime &lt;= 1000</code></li>
	<li><code>n == passingFees.length</code></li>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>n - 1 &lt;= edges.length &lt;= 1000</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= time<sub>i</sub> &lt;= 1000</code></li>
	<li><code>1 &lt;= passingFees[j] &lt;= 1000</code>&nbsp;</li>
	<li>The graph may contain multiple edges between two nodes.</li>
	<li>The graph does not contain self loops.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j]$ to represent the minimum cost to reach city $j$ from city $0$ after $i$ minutes. Initially, $f[0][0] = \textit{passingFees}[0]$, and the rest $f[0][j] = +\infty$.

Next, within the time range $[1, \textit{maxTime}]$, we traverse all edges. For each edge $(x, y, t)$, if $t \leq i$, then we:

-   Can first spend $i - t$ minutes to reach city $y$ from city $0$, then spend $t$ minutes to reach city $x$ from city $y$, and add the passing fee to reach city $x$, i.e., $f[i][x] = \min(f[i][x], f[i - t][y] + \textit{passingFees}[x])$;
-   Can also first spend $i - t$ minutes to reach city $x$ from city $0$, then spend $t$ minutes to reach city $y$ from city $x$, and add the passing fee to reach city $y$, i.e., $f[i][y] = \min(f[i][y], f[i - t][x] + \textit{passingFees}[y])$.

The final answer is $\min\{f[i][n - 1]\}$, where $i \in [0, \textit{maxTime}]$. If the answer is $+\infty$, return $-1$.

The time complexity is $O(\textit{maxTime} \times (m + n))$, where $m$ and $n$ are the number of edges and cities, respectively. The space complexity is $O(\textit{maxTime} \times n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(
        self, maxTime: int, edges: List[List[int]], passingFees: List[int]
    ) -> int:
        m, n = maxTime, len(passingFees)
        f = [[inf] * n for _ in range(m + 1)]
        f[0][0] = passingFees[0]
        for i in range(1, m + 1):
            for x, y, t in edges:
                if t <= i:
                    f[i][x] = min(f[i][x], f[i - t][y] + passingFees[x])
                    f[i][y] = min(f[i][y], f[i - t][x] + passingFees[y])
        ans = min(f[i][n - 1] for i in range(m + 1))
        return ans if ans < inf else -1
```

#### Java

```java
class Solution {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int m = maxTime, n = passingFees.length;
        int[][] f = new int[m + 1][n];
        final int inf = 1 << 30;
        for (var g : f) {
            Arrays.fill(g, inf);
        }
        f[0][0] = passingFees[0];
        for (int i = 1; i <= m; ++i) {
            for (var e : edges) {
                int x = e[0], y = e[1], t = e[2];
                if (t <= i) {
                    f[i][x] = Math.min(f[i][x], f[i - t][y] + passingFees[x]);
                    f[i][y] = Math.min(f[i][y], f[i - t][x] + passingFees[y]);
                }
            }
        }
        int ans = inf;
        for (int i = 0; i <= m; ++i) {
            ans = Math.min(ans, f[i][n - 1]);
        }
        return ans == inf ? -1 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCost(int maxTime, vector<vector<int>>& edges, vector<int>& passingFees) {
        int m = maxTime, n = passingFees.size();
        const int inf = 1 << 30;
        vector<vector<int>> f(m + 1, vector<int>(n, inf));
        f[0][0] = passingFees[0];
        for (int i = 1; i <= m; ++i) {
            for (const auto& e : edges) {
                int x = e[0], y = e[1], t = e[2];
                if (t <= i) {
                    f[i][x] = min(f[i][x], f[i - t][y] + passingFees[x]);
                    f[i][y] = min(f[i][y], f[i - t][x] + passingFees[y]);
                }
            }
        }
        int ans = inf;
        for (int i = 1; i <= m; ++i) {
            ans = min(ans, f[i][n - 1]);
        }
        return ans == inf ? -1 : ans;
    }
};
```

#### Go

```go
func minCost(maxTime int, edges [][]int, passingFees []int) int {
	m, n := maxTime, len(passingFees)
	f := make([][]int, m+1)
	const inf int = 1 << 30
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = inf
		}
	}
	f[0][0] = passingFees[0]
	for i := 1; i <= m; i++ {
		for _, e := range edges {
			x, y, t := e[0], e[1], e[2]
			if t <= i {
				f[i][x] = min(f[i][x], f[i-t][y]+passingFees[x])
				f[i][y] = min(f[i][y], f[i-t][x]+passingFees[y])
			}
		}
	}
	ans := inf
	for i := 1; i <= m; i++ {
		ans = min(ans, f[i][n-1])
	}
	if ans == inf {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function minCost(maxTime: number, edges: number[][], passingFees: number[]): number {
    const [m, n] = [maxTime, passingFees.length];
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n).fill(Infinity));
    f[0][0] = passingFees[0];
    for (let i = 1; i <= m; ++i) {
        for (const [x, y, t] of edges) {
            if (t <= i) {
                f[i][x] = Math.min(f[i][x], f[i - t][y] + passingFees[x]);
                f[i][y] = Math.min(f[i][y], f[i - t][x] + passingFees[y]);
            }
        }
    }
    let ans = Infinity;
    for (let i = 1; i <= m; ++i) {
        ans = Math.min(ans, f[i][n - 1]);
    }
    return ans === Infinity ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
