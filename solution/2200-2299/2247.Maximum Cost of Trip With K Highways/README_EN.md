# [2247. Maximum Cost of Trip With K Highways 🔒](https://leetcode.com/problems/maximum-cost-of-trip-with-k-highways)

[中文文档](/solution/2200-2299/2247.Maximum%20Cost%20of%20Trip%20With%20K%20Highways/README.md)

<!-- tags:Bit Manipulation,Graph,Dynamic Programming,Bitmask -->

## Description

<p>A series of highways connect <code>n</code> cities numbered from <code>0</code> to <code>n - 1</code>. You are given a 2D integer array <code>highways</code> where <code>highways[i] = [city1<sub>i</sub>, city2<sub>i</sub>, toll<sub>i</sub>]</code> indicates that there is a highway that connects <code>city1<sub>i</sub></code> and <code>city2<sub>i</sub></code>, allowing a car to go from <code>city1<sub>i</sub></code> to <code>city2<sub>i</sub></code> and <strong>vice versa</strong> for a cost of <code>toll<sub>i</sub></code>.</p>

<p>You are also given an integer <code>k</code>. You are going on a trip that crosses <strong>exactly</strong> <code>k</code> highways. You may start at any city, but you may only visit each city <strong>at most</strong> once during your trip.</p>

<p>Return<em> the <strong>maximum</strong> cost of your trip. If there is no trip that meets the requirements, return </em><code>-1</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2247.Maximum%20Cost%20of%20Trip%20With%20K%20Highways/images/image-20220418173304-1.png" style="height: 200px; width: 327px;" />
<pre>
<strong>Input:</strong> n = 5, highways = [[0,1,4],[2,1,3],[1,4,11],[3,2,3],[3,4,2]], k = 3
<strong>Output:</strong> 17
<strong>Explanation:</strong>
One possible trip is to go from 0 -&gt; 1 -&gt; 4 -&gt; 3. The cost of this trip is 4 + 11 + 2 = 17.
Another possible trip is to go from 4 -&gt; 1 -&gt; 2 -&gt; 3. The cost of this trip is 11 + 3 + 3 = 17.
It can be proven that 17 is the maximum possible cost of any valid trip.

Note that the trip 4 -&gt; 1 -&gt; 0 -&gt; 1 is not allowed because you visit the city 1 twice.

</pre>

<p><strong class="example">Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2247.Maximum%20Cost%20of%20Trip%20With%20K%20Highways/images/image-20220418173342-2.png" style="height: 200px; width: 217px;" />
<pre>
<strong>Input:</strong> n = 4, highways = [[0,1,3],[2,3,2]], k = 2
<strong>Output:</strong> -1
<strong>Explanation:</strong> There are no valid trips of length 2, so return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 15</code></li>
	<li><code>1 &lt;= highways.length &lt;= 50</code></li>
	<li><code>highways[i].length == 3</code></li>
	<li><code>0 &lt;= city1<sub>i</sub>, city2<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>city1<sub>i</sub> != city2<sub>i</sub></code></li>
	<li><code>0 &lt;= toll<sub>i</sub> &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
	<li>There are no duplicate highways.</li>
</ul>

## Solutions

### Solution 1: State Compression Dynamic Programming

We notice that the problem requires exactly $k$ roads to be passed, and each city can only be visited once. The number of cities is $n$, so we can pass at most $n - 1$ roads. Therefore, if $k \ge n$, we cannot meet the requirements of the problem, and we can directly return $-1$.

In addition, we can also find that the number of cities $n$ does not exceed $15$, which suggests that we can consider using the method of state compression dynamic programming to solve this problem. We use a binary number of length $n$ to represent the cities that have been passed, where the $i$-th bit is $1$ indicates that the $i$-th city has been passed, and $0$ indicates that the $i$-th city has not been passed yet.

We use $f[i][j]$ to represent the maximum travel cost when the cities that have been passed are $i$ and the last city passed is $j$. Initially, $f[2^i][i]=0$, and the rest $f[i][j]=-\infty$.

Consider how $f[i][j]$ transitions. For $f[i]$, we enumerate all cities $j$. If the $j$-th bit of $i$ is $1$, then we can reach city $j$ from other city $h$ through the road, at this time the value of $f[i][j]$ is the maximum value of $f[i][h]+cost(h, j)$, where $cost(h, j)$ represents the travel cost from city $h$ to city $j$. Therefore, we can get the state transition equation:

$$
f[i][j]=\max_{h \in \text{city}}\{f[i \backslash j][h]+cost(h, j)\}
$$

where $i \backslash j$ represents changing the $j$-th bit of $i$ to $0$.

After calculating $f[i][j]$, we judge whether the number of cities passed is $k+1$, that is, whether the number of $1$s in the binary representation of $i$ is $k+1$. If so, we update the answer as $ans = \max(ans, f[i][j])$.

The time complexity is $O(2^n \times n^2)$, and the space complexity is $O(2^n \times n)$, where $n$ represents the number of cities.

<!-- tabs:start -->

```python
class Solution:
    def maximumCost(self, n: int, highways: List[List[int]], k: int) -> int:
        if k >= n:
            return -1
        g = defaultdict(list)
        for a, b, cost in highways:
            g[a].append((b, cost))
            g[b].append((a, cost))
        f = [[-inf] * n for _ in range(1 << n)]
        for i in range(n):
            f[1 << i][i] = 0
        ans = -1
        for i in range(1 << n):
            for j in range(n):
                if i >> j & 1:
                    for h, cost in g[j]:
                        if i >> h & 1:
                            f[i][j] = max(f[i][j], f[i ^ (1 << j)][h] + cost)
                if i.bit_count() == k + 1:
                    ans = max(ans, f[i][j])
        return ans
```

```java
class Solution {
    public int maximumCost(int n, int[][] highways, int k) {
        if (k >= n) {
            return -1;
        }
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, h -> new ArrayList<>());
        for (int[] h : highways) {
            int a = h[0], b = h[1], cost = h[2];
            g[a].add(new int[] {b, cost});
            g[b].add(new int[] {a, cost});
        }
        int[][] f = new int[1 << n][n];
        for (int[] e : f) {
            Arrays.fill(e, -(1 << 30));
        }
        for (int i = 0; i < n; ++i) {
            f[1 << i][i] = 0;
        }
        int ans = -1;
        for (int i = 0; i < 1 << n; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    for (var e : g[j]) {
                        int h = e[0], cost = e[1];
                        if ((i >> h & 1) == 1) {
                            f[i][j] = Math.max(f[i][j], f[i ^ (1 << j)][h] + cost);
                        }
                    }
                }
                if (Integer.bitCount(i) == k + 1) {
                    ans = Math.max(ans, f[i][j]);
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumCost(int n, vector<vector<int>>& highways, int k) {
        if (k >= n) {
            return -1;
        }
        vector<pair<int, int>> g[n];
        for (auto& h : highways) {
            int a = h[0], b = h[1], cost = h[2];
            g[a].emplace_back(b, cost);
            g[b].emplace_back(a, cost);
        }
        int f[1 << n][n];
        memset(f, -0x3f, sizeof(f));
        for (int i = 0; i < n; ++i) {
            f[1 << i][i] = 0;
        }
        int ans = -1;
        for (int i = 0; i < 1 << n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    for (auto& [h, cost] : g[j]) {
                        if (i >> h & 1) {
                            f[i][j] = max(f[i][j], f[i ^ (1 << j)][h] + cost);
                        }
                    }
                }
                if (__builtin_popcount(i) == k + 1) {
                    ans = max(ans, f[i][j]);
                }
            }
        }
        return ans;
    }
};
```

```go
func maximumCost(n int, highways [][]int, k int) int {
	if k >= n {
		return -1
	}
	g := make([][][2]int, n)
	for _, h := range highways {
		a, b, cost := h[0], h[1], h[2]
		g[a] = append(g[a], [2]int{b, cost})
		g[b] = append(g[b], [2]int{a, cost})
	}
	f := make([][]int, 1<<n)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -(1 << 30)
		}
	}
	for i := 0; i < n; i++ {
		f[1<<i][i] = 0
	}
	ans := -1
	for i := 0; i < 1<<n; i++ {
		for j := 0; j < n; j++ {
			if i>>j&1 == 1 {
				for _, e := range g[j] {
					h, cost := e[0], e[1]
					if i>>h&1 == 1 {
						f[i][j] = max(f[i][j], f[i^(1<<j)][h]+cost)
					}
				}
			}
			if bits.OnesCount(uint(i)) == k+1 {
				ans = max(ans, f[i][j])
			}
		}
	}
	return ans
}
```

```ts
function maximumCost(n: number, highways: number[][], k: number): number {
    if (k >= n) {
        return -1;
    }
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [a, b, cost] of highways) {
        g[a].push([b, cost]);
        g[b].push([a, cost]);
    }
    const f: number[][] = Array(1 << n)
        .fill(0)
        .map(() => Array(n).fill(-(1 << 30)));
    for (let i = 0; i < n; ++i) {
        f[1 << i][i] = 0;
    }
    let ans = -1;
    for (let i = 0; i < 1 << n; ++i) {
        for (let j = 0; j < n; ++j) {
            if ((i >> j) & 1) {
                for (const [h, cost] of g[j]) {
                    if ((i >> h) & 1) {
                        f[i][j] = Math.max(f[i][j], f[i ^ (1 << j)][h] + cost);
                    }
                }
            }
            if (bitCount(i) === k + 1) {
                ans = Math.max(ans, f[i][j]);
            }
        }
    }
    return ans;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

<!-- tabs:end -->

<!-- end -->
