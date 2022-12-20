# [1761. 一个图中连通三元组的最小度数](https://leetcode.cn/problems/minimum-degree-of-a-connected-trio-in-a-graph)

[English Version](/solution/1700-1799/1761.Minimum%20Degree%20of%20a%20Connected%20Trio%20in%20a%20Graph/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个无向图，整数 <code>n</code> 表示图中节点的数目，<code>edges</code> 数组表示图中的边，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> ，表示 <code>u<sub>i</sub></code> 和 <code>v<sub>i</sub></code><sub> </sub>之间有一条无向边。</p>

<p>一个 <strong>连通三元组</strong> 指的是 <strong>三个</strong> 节点组成的集合且这三个点之间 <strong>两两</strong> 有边。</p>

<p><strong>连通三元组的度数</strong> 是所有满足此条件的边的数目：一个顶点在这个三元组内，而另一个顶点不在这个三元组内。</p>

<p>请你返回所有连通三元组中度数的 <strong>最小值</strong> ，如果图中没有连通三元组，那么返回 <code>-1</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1761.Minimum%20Degree%20of%20a%20Connected%20Trio%20in%20a%20Graph/images/trios1.png" style="width: 388px; height: 164px;" />
<pre>
<b>输入：</b>n = 6, edges = [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]
<b>输出：</b>3
<b>解释：</b>只有一个三元组 [1,2,3] 。构成度数的边在上图中已被加粗。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1761.Minimum%20Degree%20of%20a%20Connected%20Trio%20in%20a%20Graph/images/trios2.png" style="width: 388px; height: 164px;" />
<pre>
<b>输入：</b>n = 7, edges = [[1,3],[4,1],[4,3],[2,5],[5,6],[6,7],[7,5],[2,6]]
<b>输出：</b>0
<b>解释：</b>有 3 个三元组：
1) [1,4,3]，度数为 0 。
2) [2,5,6]，度数为 2 。
3) [5,6,7]，度数为 2 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 400</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 <= edges.length <= n * (n-1) / 2</code></li>
	<li><code>1 <= u<sub>i</sub>, v<sub>i</sub> <= n</code></li>
	<li><code>u<sub>i </sub>!= v<sub>i</sub></code></li>
	<li>图中没有重复的边。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：暴力枚举**

我们先将所有边存入邻接矩阵 $g$ 中，再将每个节点的度数存入数组 $deg$ 中。

然后枚举所有的三元组 $(i, j, k)$，其中 $i \lt j \lt k$，如果 $g[i][j] = g[j][k] = g[i][k] = 1$，则说明这三个节点构成了一个连通三元组，此时更新答案为 $deg[i] + deg[j] + deg[k] - 6$。返回最小的符合条件的答案即可。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 为节点数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minTrioDegree(self, n: int, edges: List[List[int]]) -> int:
        g = [[False] * n for _ in range(n)]
        deg = [0] * n
        for u, v in edges:
            u, v = u - 1, v - 1
            g[u][v] = g[v][u] = True
            deg[u] += 1
            deg[v] += 1
        ans = inf
        for i in range(n):
            for j in range(i + 1, n):
                if g[i][j]:
                    for k in range(j + 1, n):
                        if g[i][k] and g[j][k]:
                            ans = min(ans, deg[i] + deg[j] + deg[k] - 6)
        return -1 if ans == inf else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minTrioDegree(int n, int[][] edges) {
        boolean[][] g = new boolean[n][n];
        int[] deg = new int[n];
        for (var e : edges) {
            int u = e[0] - 1, v = e[1] - 1;
            g[u][v] = true;
            g[v][u] = true;
            ++deg[u];
            ++deg[v];
        }
        int ans = 1 << 30;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (g[i][j]) {
                    for (int k = j + 1; k < n; ++k) {
                        if (g[i][k] && g[j][k]) {
                            ans = Math.min(ans, deg[i] + deg[j] + deg[k] - 6);
                        }
                    }
                }
            }
        }
        return ans == 1 << 30 ? -1 : ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minTrioDegree(int n, vector<vector<int>>& edges) {
        bool g[n][n];
        memset(g, 0, sizeof g);
        int deg[n];
        memset(deg, 0, sizeof deg);
        for (auto& e : edges) {
            int u = e[0] - 1, v = e[1] - 1;
            g[u][v] = g[v][u] = true;
            deg[u]++, deg[v]++;
        }
        int ans = INT_MAX;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (g[i][j]) {
                    for (int k = j + 1; k < n; ++k) {
                        if (g[j][k] && g[i][k]) {
                            ans = min(ans, deg[i] + deg[j] + deg[k] - 6);
                        }
                    }
                }
            }
        }
        return ans == INT_MAX ? -1 : ans;
    }
};
```

### **Go**

```go
func minTrioDegree(n int, edges [][]int) int {
	g := make([][]bool, n)
	deg := make([]int, n)
	for i := range g {
		g[i] = make([]bool, n)
	}
	for _, e := range edges {
		u, v := e[0]-1, e[1]-1
		g[u][v], g[v][u] = true, true
		deg[u]++
		deg[v]++
	}
	ans := 1 << 30
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if g[i][j] {
				for k := j + 1; k < n; k++ {
					if g[i][k] && g[j][k] {
						ans = min(ans, deg[i]+deg[j]+deg[k]-6)
					}
				}
			}
		}
	}
	if ans == 1<<30 {
		return -1
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
