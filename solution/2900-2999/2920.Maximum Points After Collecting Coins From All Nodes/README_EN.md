# [2920. Maximum Points After Collecting Coins From All Nodes](https://leetcode.com/problems/maximum-points-after-collecting-coins-from-all-nodes)

[中文文档](/solution/2900-2999/2920.Maximum%20Points%20After%20Collecting%20Coins%20From%20All%20Nodes/README.md)

## Description

<p>There exists an undirected tree rooted at node <code>0</code> with <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>. You are given a 2D <strong>integer</strong> array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree. You are also given a <strong>0-indexed</strong> array <code>coins</code> of size <code>n</code> where <code>coins[i]</code> indicates the number of coins in the vertex <code>i</code>, and an integer <code>k</code>.</p>

<p>Starting from the root, you have to collect all the coins such that the coins at a node can only be collected if the coins of its ancestors have been already collected.</p>

<p>Coins at <code>node<sub>i</sub></code> can be collected in one of the following ways:</p>

<ul>
	<li>Collect all the coins, but you will get <code>coins[i] - k</code> points. If <code>coins[i] - k</code> is negative then you will lose <code>abs(coins[i] - k)</code> points.</li>
	<li>Collect all the coins, but you will get <code>floor(coins[i] / 2)</code> points. If this way is used, then for all the <code>node<sub>j</sub></code> present in the subtree of <code>node<sub>i</sub></code>, <code>coins[j]</code> will get reduced to <code>floor(coins[j] / 2)</code>.</li>
</ul>

<p>Return <em>the <strong>maximum points</strong> you can get after collecting the coins from <strong>all</strong> the tree nodes.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2920.Maximum%20Points%20After%20Collecting%20Coins%20From%20All%20Nodes/images/ex1-copy.png" style="width: 60px; height: 316px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem;" />
<pre>
<strong>Input:</strong> edges = [[0,1],[1,2],[2,3]], coins = [10,10,3,3], k = 5
<strong>Output:</strong> 11                        
<strong>Explanation:</strong> 
Collect all the coins from node 0 using the first way. Total points = 10 - 5 = 5.
Collect all the coins from node 1 using the first way. Total points = 5 + (10 - 5) = 10.
Collect all the coins from node 2 using the second way so coins left at node 3 will be floor(3 / 2) = 1. Total points = 10 + floor(3 / 2) = 11.
Collect all the coins from node 3 using the second way. Total points = 11 + floor(1 / 2) = 11.
It can be shown that the maximum points we can get after collecting coins from all the nodes is 11. 
</pre>

<p><strong class="example">Example 2:</strong></p>
<strong class="example"> <img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2920.Maximum%20Points%20After%20Collecting%20Coins%20From%20All%20Nodes/images/ex2.png" style="width: 140px; height: 147px; padding: 10px; background: #fff; border-radius: .5rem;" /></strong>

<pre>
<strong>Input:</strong> edges = [[0,1],[0,2]], coins = [8,4,4], k = 0
<strong>Output:</strong> 16
<strong>Explanation:</strong> 
Coins will be collected from all the nodes using the first way. Therefore, total points = (8 - 0) + (4 - 0) + (4 - 0) = 16.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == coins.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code><font face="monospace">0 &lt;= coins[i] &lt;= 10<sup>4</sup></font></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code><font face="monospace">0 &lt;= edges[i][0], edges[i][1] &lt; n</font></code></li>
	<li><code><font face="monospace">0 &lt;= k &lt;= 10<sup>4</sup></font></code></li>
</ul>

## Solutions

### Solution 1: Memoization Search

First, we construct a graph $g$ based on the edges given in the problem, where $g[i]$ represents all adjacent nodes of node $i$. Then we can use the method of memoization search to solve this problem.

We design a function $dfs(i, fa, j)$, which represents that the current node is $i$, the parent node is $fa$, the number of gold coins of the current node needs to be shifted to the right by $j$ bits, and the maximum score that can be obtained.

The execution process of the function $dfs(i, fa, j)$ is as follows:

If we use the first method to collect the gold coins of the current node, then the score of the current node is $(coins[i] >> j) - k$. Then we traverse all the adjacent nodes $c$ of the current node. If $c$ is not equal to $fa$, then we add the result of $dfs(c, i, j)$ to the score of the current node.

If we use the second method to collect the gold coins of the current node, then the score of the current node is $coins[i] >> (j + 1)$. Then we traverse all the adjacent nodes $c$ of the current node. If $c$ is not equal to $fa$, then we add the result of $dfs(c, i, j + 1)$ to the score of the current node. Note that since the maximum value of $coins[i]$ given in the problem is $10^4$, we can only shift to the right by at most $14$ bits, so that the value of $coins[i] >> (j + 1)$ is $0$.

Finally, we return the maximum score that can be obtained by using the two methods at the current node.

In order to avoid repeated calculations, we use the method of memoization search and store the result of $dfs(i, fa, j)$ in $f[i][j]$, where $f[i][j]$ represents that the current node is $i$, the parent node is $fa$, the number of gold coins of the current node needs to be shifted to the right by $j$ bits, and the maximum score that can be obtained.

The time complexity is $O(n \times \log M)$, and the space complexity is $O(n \times \log M)$. Where $M$ represents the maximum value of $coins[i]$.

<!-- tabs:start -->

```python
class Solution:
    def maximumPoints(self, edges: List[List[int]], coins: List[int], k: int) -> int:
        @cache
        def dfs(i: int, fa: int, j: int) -> int:
            a = (coins[i] >> j) - k
            b = coins[i] >> (j + 1)
            for c in g[i]:
                if c != fa:
                    a += dfs(c, i, j)
                    if j < 14:
                        b += dfs(c, i, j + 1)
            return max(a, b)

        n = len(coins)
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        ans = dfs(0, -1, 0)
        dfs.cache_clear()
        return ans
```

```java
class Solution {
    private int k;
    private int[] coins;
    private Integer[][] f;
    private List<Integer>[] g;

    public int maximumPoints(int[][] edges, int[] coins, int k) {
        this.k = k;
        this.coins = coins;
        int n = coins.length;
        f = new Integer[n][15];
        g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        return dfs(0, -1, 0);
    }

    private int dfs(int i, int fa, int j) {
        if (f[i][j] != null) {
            return f[i][j];
        }
        int a = (coins[i] >> j) - k;
        int b = coins[i] >> (j + 1);
        for (int c : g[i]) {
            if (c != fa) {
                a += dfs(c, i, j);
                if (j < 14) {
                    b += dfs(c, i, j + 1);
                }
            }
        }
        return f[i][j] = Math.max(a, b);
    }
}
```

```cpp
class Solution {
public:
    int maximumPoints(vector<vector<int>>& edges, vector<int>& coins, int k) {
        int n = coins.size();
        int f[n][15];
        memset(f, -1, sizeof(f));
        vector<int> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        function<int(int, int, int)> dfs = [&](int i, int fa, int j) {
            if (f[i][j] != -1) {
                return f[i][j];
            }
            int a = (coins[i] >> j) - k;
            int b = coins[i] >> (j + 1);
            for (int c : g[i]) {
                if (c != fa) {
                    a += dfs(c, i, j);
                    if (j < 14) {
                        b += dfs(c, i, j + 1);
                    }
                }
            }
            return f[i][j] = max(a, b);
        };
        return dfs(0, -1, 0);
    }
};
```

```go
func maximumPoints(edges [][]int, coins []int, k int) int {
	n := len(coins)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, 15)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var dfs func(int, int, int) int
	dfs = func(i, fa, j int) int {
		if f[i][j] != -1 {
			return f[i][j]
		}
		a := (coins[i] >> j) - k
		b := coins[i] >> (j + 1)
		for _, c := range g[i] {
			if c != fa {
				a += dfs(c, i, j)
				if j < 14 {
					b += dfs(c, i, j+1)
				}
			}
		}
		f[i][j] = max(a, b)
		return f[i][j]
	}
	return dfs(0, -1, 0)
}
```

```ts
function maximumPoints(edges: number[][], coins: number[], k: number): number {
    const n = coins.length;
    const f: number[][] = Array.from({ length: n }, () => Array(15).fill(-1));
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const dfs = (i: number, fa: number, j: number): number => {
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        let a = (coins[i] >> j) - k;
        let b = coins[i] >> (j + 1);
        for (const c of g[i]) {
            if (c !== fa) {
                a += dfs(c, i, j);
                if (j < 14) {
                    b += dfs(c, i, j + 1);
                }
            }
        }
        return (f[i][j] = Math.max(a, b));
    };
    return dfs(0, -1, 0);
}
```

<!-- tabs:end -->

<!-- end -->
