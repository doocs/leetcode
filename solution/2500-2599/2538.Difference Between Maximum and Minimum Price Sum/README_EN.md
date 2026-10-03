---
comments: true
difficulty: Hard
rating: 2397
source: Weekly Contest 328 Q4
tags:
    - Tree
    - Depth-First Search
    - Array
    - Dynamic Programming
    - Tree DP
---

<!-- problem:start -->

# [2538. Difference Between Maximum and Minimum Price Sum](https://leetcode.com/problems/difference-between-maximum-and-minimum-price-sum)

[中文文档](/solution/2500-2599/2538.Difference%20Between%20Maximum%20and%20Minimum%20Price%20Sum/README.md)

## Description

<!-- description:start -->

<p>There exists an undirected and initially unrooted tree with <code>n</code> nodes indexed from <code>0</code> to <code>n - 1</code>. You are given the integer <code>n</code> and a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree.</p>

<p>Each node has an associated price. You are given an integer array <code>price</code>, where <code>price[i]</code> is the price of the <code>i<sup>th</sup></code> node.</p>

<p>The <strong>price sum</strong> of a given path is the sum of the prices of all nodes lying on that path.</p>

<p>The tree can be rooted at any node <code>root</code> of your choice. The incurred <strong>cost</strong> after choosing <code>root</code> is the difference between the maximum and minimum <strong>price sum</strong> amongst all paths starting at <code>root</code>.</p>

<p>Return <em>the <strong>maximum</strong> possible <strong>cost</strong></em> <em>amongst all possible root choices</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2538.Difference%20Between%20Maximum%20and%20Minimum%20Price%20Sum/images/example14.png" style="width: 556px; height: 231px;" />
<pre>
<strong>Input:</strong> n = 6, edges = [[0,1],[1,2],[1,3],[3,4],[3,5]], price = [9,8,7,6,10,5]
<strong>Output:</strong> 24
<strong>Explanation:</strong> The diagram above denotes the tree after rooting it at node 2. The first part (colored in red) shows the path with the maximum price sum. The second part (colored in blue) shows the path with the minimum price sum.
- The first path contains nodes [2,1,3,4]: the prices are [7,8,6,10], and the sum of the prices is 31.
- The second path contains the node [2] with the price [7].
The difference between the maximum and minimum price sum is 24. It can be proved that 24 is the maximum cost.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2538.Difference%20Between%20Maximum%20and%20Minimum%20Price%20Sum/images/p1_example2.png" style="width: 352px; height: 184px;" />
<pre>
<strong>Input:</strong> n = 3, edges = [[0,1],[1,2]], price = [1,1,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The diagram above denotes the tree after rooting it at node 0. The first part (colored in red) shows the path with the maximum price sum. The second part (colored in blue) shows the path with the minimum price sum.
- The first path contains nodes [0,1,2]: the prices are [1,1,1], and the sum of the prices is 3.
- The second path contains node [0] with a price [1].
The difference between the maximum and minimum price sum is 2. It can be proved that 2 is the maximum cost.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>edges</code> represents a valid tree.</li>
	<li><code>price.length == n</code></li>
	<li><code>1 &lt;= price[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> A path costs the sum of node prices minus the smaller endpoint; we want the maximum over all paths. Prices are positive, so this is the path sum minus one endpoint. Enumerating paths is $O(n^2)$.
>
> Tree DP keeps two values per subtree: the longest downward chain $a$ that still includes the far endpoint, and the longest chain $b$ after dropping that endpoint. Combining $a$ with a child's $d$, or $b$ with a child's $c$, covers the best path through the current node.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxOutput(self, n: int, edges: List[List[int]], price: List[int]) -> int:
        def dfs(i, fa):
            a, b = price[i], 0
            for j in g[i]:
                if j != fa:
                    c, d = dfs(j, i)
                    nonlocal ans
                    ans = max(ans, a + d, b + c)
                    a = max(a, price[i] + c)
                    b = max(b, price[i] + d)
            return a, b

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        ans = 0
        dfs(0, -1)
        return ans
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private long ans;
    private int[] price;

    public long maxOutput(int n, int[][] edges, int[] price) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        this.price = price;
        dfs(0, -1);
        return ans;
    }

    private long[] dfs(int i, int fa) {
        long a = price[i], b = 0;
        for (int j : g[i]) {
            if (j != fa) {
                var e = dfs(j, i);
                long c = e[0], d = e[1];
                ans = Math.max(ans, Math.max(a + d, b + c));
                a = Math.max(a, price[i] + c);
                b = Math.max(b, price[i] + d);
            }
        }
        return new long[] {a, b};
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxOutput(int n, vector<vector<int>>& edges, vector<int>& price) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        using ll = long long;
        using pll = pair<ll, ll>;
        ll ans = 0;
        function<pll(int, int)> dfs = [&](int i, int fa) {
            ll a = price[i], b = 0;
            for (int j : g[i]) {
                if (j != fa) {
                    auto [c, d] = dfs(j, i);
                    ans = max({ans, a + d, b + c});
                    a = max(a, price[i] + c);
                    b = max(b, price[i] + d);
                }
            }
            return pll{a, b};
        };
        dfs(0, -1);
        return ans;
    }
};
```

#### Go

```go
func maxOutput(n int, edges [][]int, price []int) int64 {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	type pair struct{ a, b int }
	ans := 0
	var dfs func(i, fa int) pair
	dfs = func(i, fa int) pair {
		a, b := price[i], 0
		for _, j := range g[i] {
			if j != fa {
				e := dfs(j, i)
				c, d := e.a, e.b
				ans = max(ans, max(a+d, b+c))
				a = max(a, price[i]+c)
				b = max(b, price[i]+d)
			}
		}
		return pair{a, b}
	}
	dfs(0, -1)
	return int64(ans)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Tree DP + Explicit Stack

<!-- thinking:start -->

> **Thinking**
>
> A path costs the sum of node prices minus the smaller endpoint, and prices are positive, so that is the path sum minus one endpoint. With $n \le 10^5$, enumerating paths is $O(n^2)$, and recursing from the root for the two downward chains is too deep: a chain makes the call depth $n$.
>
> Each node keeps two values: the longest downward chain $a$ that still includes the far endpoint, and the longest chain $b$ after that endpoint is dropped. The best path through the node joins one of those chains with a child's chain of the other kind, $a$ with $d$ or $b$ with $c$.
>
> An explicit stack of $(node, parent, state)$ runs the postorder. On entry we push the exit marker and then the children, and on exit we update the answer from the children's two chains and store this node's $a$ and $b$.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxOutput(self, n: int, edges: List[List[int]], price: List[int]) -> int:
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        down = [(0, 0)] * n
        ans = 0
        stk = [(0, -1, 0)]
        while stk:
            i, fa, state = stk.pop()
            if state == 0:
                stk.append((i, fa, 1))
                for j in g[i]:
                    if j != fa:
                        stk.append((j, i, 0))
            else:
                a, b = price[i], 0
                for j in g[i]:
                    if j != fa:
                        c, d = down[j]
                        ans = max(ans, a + d, b + c)
                        a = max(a, price[i] + c)
                        b = max(b, price[i] + d)
                down[i] = (a, b)
        return ans
```

#### Java

```java
class Solution {
    public long maxOutput(int n, int[][] edges, int[] price) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        long[][] down = new long[n][2];
        long ans = 0;
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push(new int[] {i, fa, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push(new int[] {j, i, 0});
                    }
                }
            } else {
                long a = price[i], b = 0;
                for (int j : g[i]) {
                    if (j != fa) {
                        long c = down[j][0], d = down[j][1];
                        ans = Math.max(ans, Math.max(a + d, b + c));
                        a = Math.max(a, price[i] + c);
                        b = Math.max(b, price[i] + d);
                    }
                }
                down[i][0] = a;
                down[i][1] = b;
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
    long long maxOutput(int n, vector<vector<int>>& edges, vector<int>& price) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        using ll = long long;
        vector<pair<ll, ll>> down(n);
        ll ans = 0;
        vector<array<int, 3>> stk{{0, -1, 0}};
        while (!stk.empty()) {
            auto [i, fa, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({i, fa, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push_back({j, i, 0});
                    }
                }
            } else {
                ll a = price[i], b = 0;
                for (int j : g[i]) {
                    if (j != fa) {
                        auto [c, d] = down[j];
                        ans = max({ans, a + d, b + c});
                        a = max(a, price[i] + c);
                        b = max(b, price[i] + d);
                    }
                }
                down[i] = {a, b};
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxOutput(n int, edges [][]int, price []int) int64 {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	type pair struct{ a, b int }
	down := make([]pair, n)
	ans := 0
	stk := [][3]int{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, fa, state := cur[0], cur[1], cur[2]
		if state == 0 {
			stk = append(stk, [3]int{i, fa, 1})
			for _, j := range g[i] {
				if j != fa {
					stk = append(stk, [3]int{j, i, 0})
				}
			}
		} else {
			a, b := price[i], 0
			for _, j := range g[i] {
				if j != fa {
					c, d := down[j].a, down[j].b
					ans = max(ans, max(a+d, b+c))
					a = max(a, price[i]+c)
					b = max(b, price[i]+d)
				}
			}
			down[i] = pair{a, b}
		}
	}
	return int64(ans)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
