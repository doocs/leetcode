# [2316. 统计无向图中无法互相到达点对数](https://leetcode.cn/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph)

[English Version](/solution/2300-2399/2316.Count%20Unreachable%20Pairs%20of%20Nodes%20in%20an%20Undirected%20Graph/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;，表示一张<strong>&nbsp;无向图</strong>&nbsp;中有 <code>n</code>&nbsp;个节点，编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;。同时给你一个二维整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示节点&nbsp;<code>a<sub>i</sub></code> 和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条&nbsp;<strong>无向</strong>&nbsp;边。</p>

<p>请你返回 <strong>无法互相到达</strong>&nbsp;的不同 <strong>点对数目</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2316.Count%20Unreachable%20Pairs%20of%20Nodes%20in%20an%20Undirected%20Graph/images/tc-3.png" style="width: 267px; height: 169px;"></p>

<pre><b>输入：</b>n = 3, edges = [[0,1],[0,2],[1,2]]
<b>输出：</b>0
<b>解释：</b>所有点都能互相到达，意味着没有点对无法互相到达，所以我们返回 0 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2316.Count%20Unreachable%20Pairs%20of%20Nodes%20in%20an%20Undirected%20Graph/images/tc-2.png" style="width: 295px; height: 269px;"></p>

<pre><b>输入：</b>n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
<b>输出：</b>14
<b>解释：</b>总共有 14 个点对互相无法到达：
[[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]]
所以我们返回 14 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>不会有重复边。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countPairs(self, n: int, edges: List[List[int]]) -> int:
        def dfs(i):
            vis[i] = True
            res = 1
            for j in g[i]:
                if not vis[j]:
                    res += dfs(j)
            return res

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = [False] * n
        arr = []
        for i in range(n):
            if not vis[i]:
                arr.append(dfs(i))
        ans = t = 0
        for v in arr:
            t += v
            ans += v * (n - t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private boolean[] vis;
    private List<Integer>[] g;

    public long countPairs(int n, int[][] edges) {
        vis = new boolean[n];
        g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                arr.add(dfs(i));
            }
        }
        int t = 0;
        long ans = 0;
        for (int v : arr) {
            t += v;
            ans += (long) v * (n - t);
        }
        return ans;
    }

    private int dfs(int i) {
        vis[i] = true;
        int res = 1;
        for (int j : g[i]) {
            if (!vis[j]) {
                res += dfs(j);
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> g;
    vector<bool> vis;

    long long countPairs(int n, vector<vector<int>>& edges) {
        vis.resize(n);
        g.resize(n, vector<int>());
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<int> arr;
        for (int i = 0; i < n; ++i)
            if (!vis[i]) arr.push_back(dfs(i));
        long long ans = 0;
        int t = 0;
        for (int& v : arr) {
            t += v;
            ans += 1ll * v * (n - t);
        }
        return ans;
    }

    int dfs(int i) {
        int res = 1;
        vis[i] = true;
        for (int j : g[i])
            if (!vis[j]) res += dfs(j);
        return res;
    }
};
```

### **Go**

```go
func countPairs(n int, edges [][]int) int64 {
	vis := make([]bool, n)
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var arr []int
	var dfs func(int) int
	dfs = func(i int) int {
		res := 1
		vis[i] = true
		for _, j := range g[i] {
			if !vis[j] {
				res += dfs(j)
			}
		}
		return res
	}

	for i := 0; i < n; i++ {
		if !vis[i] {
			arr = append(arr, dfs(i))
		}
	}
	ans := 0
	t := 0
	for _, v := range arr {
		t += v
		ans += v * (n - t)
	}
	return int64(ans)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
