---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2959.Number%20of%20Possible%20Sets%20of%20Closing%20Branches/README.md
rating: 2077
source: 第 119 场双周赛 Q4
tags:
    - 位运算
    - 图
    - 枚举
    - 最短路
    - 堆（优先队列）
---

<!-- problem:start -->

# [2959. 关闭分部的可行集合数目](https://leetcode.cn/problems/number-of-possible-sets-of-closing-branches)

[English Version](/solution/2900-2999/2959.Number%20of%20Possible%20Sets%20of%20Closing%20Branches/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个公司在全国有 <code>n</code>&nbsp;个分部，它们之间有的有道路连接。一开始，所有分部通过这些道路两两之间互相可以到达。</p>

<p>公司意识到在分部之间旅行花费了太多时间，所以它们决定关闭一些分部（<b>也可能不关闭任何分部</b>），同时保证剩下的分部之间两两互相可以到达且最远距离不超过&nbsp;<code>maxDistance</code>&nbsp;。</p>

<p>两个分部之间的 <strong>距离</strong> 是通过道路长度之和的 <strong>最小值</strong>&nbsp;。</p>

<p>给你整数&nbsp;<code>n</code>&nbsp;，<code>maxDistance</code>&nbsp;和下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>roads</code>&nbsp;，其中&nbsp;<code>roads[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code>&nbsp;表示一条从&nbsp;<code>u<sub>i</sub></code>&nbsp;到&nbsp;<code>v<sub>i</sub></code>&nbsp;长度为&nbsp;<code>w<sub>i</sub></code>的&nbsp;<strong>无向</strong>&nbsp;道路。</p>

<p>请你返回关闭分部的可行方案数目，满足每个方案里剩余分部之间的最远距离不超过<em>&nbsp;</em><code>maxDistance</code>。</p>

<p><strong>注意</strong>，关闭一个分部后，与之相连的所有道路不可通行。</p>

<p><b>注意</b>，两个分部之间可能会有多条道路。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2959.Number%20of%20Possible%20Sets%20of%20Closing%20Branches/images/example11.png" style="width: 221px; height: 191px;" /></p>

<pre>
<b>输入：</b>n = 3, maxDistance = 5, roads = [[0,1,2],[1,2,10],[0,2,10]]
<b>输出：</b>5
<b>解释：</b>可行的关闭分部方案有：
- 关闭分部集合 [2] ，剩余分部为 [0,1] ，它们之间的距离为 2 。
- 关闭分部集合 [0,1] ，剩余分部为 [2] 。
- 关闭分部集合 [1,2] ，剩余分部为 [0] 。
- 关闭分部集合 [0,2] ，剩余分部为 [1] 。
- 关闭分部集合 [0,1,2] ，关闭后没有剩余分部。
总共有 5 种可行的关闭方案。
</pre>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2959.Number%20of%20Possible%20Sets%20of%20Closing%20Branches/images/example22.png" style="width: 221px; height: 241px;" /></p>

<pre>
<b>输入：</b>n = 3, maxDistance = 5, roads = [[0,1,20],[0,1,10],[1,2,2],[0,2,2]]
<b>输出：</b>7
<b>解释：</b>可行的关闭分部方案有：
- 关闭分部集合 [] ，剩余分部为 [0,1,2] ，它们之间的最远距离为 4 。
- 关闭分部集合 [0] ，剩余分部为 [1,2] ，它们之间的距离为 2 。
- 关闭分部集合 [1] ，剩余分部为 [0,2] ，它们之间的距离为 2 。
- 关闭分部集合 [0,1] ，剩余分部为 [2] 。
- 关闭分部集合 [1,2] ，剩余分部为 [0] 。
- 关闭分部集合 [0,2] ，剩余分部为 [1] 。
- 关闭分部集合 [0,1,2] ，关闭后没有剩余分部。
总共有 7 种可行的关闭方案。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>n = 1, maxDistance = 10, roads = []
<b>输出：</b>2
<b>解释：</b>可行的关闭分部方案有：
- 关闭分部集合 [] ，剩余分部为 [0] 。
- 关闭分部集合 [0] ，关闭后没有剩余分部。
总共有 2 种可行的关闭方案。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>1 &lt;= maxDistance &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= roads.length &lt;= 1000</code></li>
	<li><code>roads[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 1000</code></li>
	<li>一开始所有分部之间通过道路互相可以到达。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二进制枚举 + Floyd 算法

我们注意到 $n \leq 10$，所以我们不妨考虑使用二进制枚举的方法来枚举所有的分部集合。

对于每个分部集合，我们可以使用 Floyd 算法来计算出剩余分部之间的最短距离，然后判断是否满足题目要求即可。具体地，我们先枚举中间点 $k$，再枚举起点 $i$ 和终点 $j$，如果 $g[i][k] + g[k][j] \lt g[i][j]$，那么我们就用更短的距离 $g[i][k] + g[k][j]$ 更新 $g[i][j]$。

时间复杂度 $O(2^n \times (n^3 + m))$，空间复杂度 $O(n^2)$。其中 $n$ 和 $m$ 分别是分部数量和道路数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfSets(self, n: int, maxDistance: int, roads: List[List[int]]) -> int:
        ans = 0
        for mask in range(1 << n):
            g = [[inf] * n for _ in range(n)]
            for u, v, w in roads:
                if mask >> u & 1 and mask >> v & 1:
                    g[u][v] = min(g[u][v], w)
                    g[v][u] = min(g[v][u], w)
            for k in range(n):
                if mask >> k & 1:
                    g[k][k] = 0
                    for i in range(n):
                        for j in range(n):
                            # g[i][j] = min(g[i][j], g[i][k] + g[k][j])
                            if g[i][k] + g[k][j] < g[i][j]:
                                g[i][j] = g[i][k] + g[k][j]
            if all(
                g[i][j] <= maxDistance
                for i in range(n)
                for j in range(n)
                if mask >> i & 1 and mask >> j & 1
            ):
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        int ans = 0;
        for (int mask = 0; mask < 1 << n; ++mask) {
            int[][] g = new int[n][n];
            for (var e : g) {
                Arrays.fill(e, 1 << 29);
            }
            for (var e : roads) {
                int u = e[0], v = e[1], w = e[2];
                if ((mask >> u & 1) == 1 && (mask >> v & 1) == 1) {
                    g[u][v] = Math.min(g[u][v], w);
                    g[v][u] = Math.min(g[v][u], w);
                }
            }
            for (int k = 0; k < n; ++k) {
                if ((mask >> k & 1) == 1) {
                    g[k][k] = 0;
                    for (int i = 0; i < n; ++i) {
                        for (int j = 0; j < n; ++j) {
                            g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                        }
                    }
                }
            }
            int ok = 1;
            for (int i = 0; i < n && ok == 1; ++i) {
                for (int j = 0; j < n && ok == 1; ++j) {
                    if ((mask >> i & 1) == 1 && (mask >> j & 1) == 1) {
                        if (g[i][j] > maxDistance) {
                            ok = 0;
                        }
                    }
                }
            }
            ans += ok;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfSets(int n, int maxDistance, vector<vector<int>>& roads) {
        int ans = 0;
        for (int mask = 0; mask < 1 << n; ++mask) {
            int g[n][n];
            memset(g, 0x3f, sizeof(g));
            for (auto& e : roads) {
                int u = e[0], v = e[1], w = e[2];
                if ((mask >> u & 1) & (mask >> v & 1)) {
                    g[u][v] = min(g[u][v], w);
                    g[v][u] = min(g[v][u], w);
                }
            }
            for (int k = 0; k < n; ++k) {
                if (mask >> k & 1) {
                    g[k][k] = 0;
                    for (int i = 0; i < n; ++i) {
                        for (int j = 0; j < n; ++j) {
                            g[i][j] = min(g[i][j], g[i][k] + g[k][j]);
                        }
                    }
                }
            }
            int ok = 1;
            for (int i = 0; i < n && ok == 1; ++i) {
                for (int j = 0; j < n && ok == 1; ++j) {
                    if ((mask >> i & 1) & (mask >> j & 1) && g[i][j] > maxDistance) {
                        ok = 0;
                    }
                }
            }
            ans += ok;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfSets(n int, maxDistance int, roads [][]int) (ans int) {
	for mask := 0; mask < 1<<n; mask++ {
		g := make([][]int, n)
		for i := range g {
			g[i] = make([]int, n)
			for j := range g[i] {
				g[i][j] = 1 << 29
			}
		}
		for _, e := range roads {
			u, v, w := e[0], e[1], e[2]
			if mask>>u&1 == 1 && mask>>v&1 == 1 {
				g[u][v] = min(g[u][v], w)
				g[v][u] = min(g[v][u], w)
			}
		}
		for k := 0; k < n; k++ {
			if mask>>k&1 == 1 {
				g[k][k] = 0
				for i := 0; i < n; i++ {
					for j := 0; j < n; j++ {
						g[i][j] = min(g[i][j], g[i][k]+g[k][j])
					}
				}
			}
		}
		ok := 1
		for i := 0; i < n && ok == 1; i++ {
			for j := 0; j < n && ok == 1; j++ {
				if mask>>i&1 == 1 && mask>>j&1 == 1 && g[i][j] > maxDistance {
					ok = 0
				}
			}
		}
		ans += ok
	}
	return
}
```

#### TypeScript

```ts
function numberOfSets(n: number, maxDistance: number, roads: number[][]): number {
    let ans = 0;
    for (let mask = 0; mask < 1 << n; ++mask) {
        const g: number[][] = Array.from({ length: n }, () => Array(n).fill(Infinity));
        for (const [u, v, w] of roads) {
            if ((mask >> u) & 1 && (mask >> v) & 1) {
                g[u][v] = Math.min(g[u][v], w);
                g[v][u] = Math.min(g[v][u], w);
            }
        }
        for (let k = 0; k < n; ++k) {
            if ((mask >> k) & 1) {
                g[k][k] = 0;
                for (let i = 0; i < n; ++i) {
                    for (let j = 0; j < n; ++j) {
                        g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                    }
                }
            }
        }
        let ok = 1;
        for (let i = 0; i < n && ok; ++i) {
            for (let j = 0; j < n && ok; ++j) {
                if ((mask >> i) & 1 && (mask >> j) & 1 && g[i][j] > maxDistance) {
                    ok = 0;
                }
            }
        }
        ans += ok;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
