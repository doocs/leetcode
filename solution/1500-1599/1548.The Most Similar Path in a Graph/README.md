---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/README.md
tags:
    - 图
    - 动态规划
---

<!-- problem:start -->

# [1548. 图中最相似的路径 🔒](https://leetcode.cn/problems/the-most-similar-path-in-a-graph)

[English Version](/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/README_EN.md)

## 题目描述

<!-- description:start -->

<p>我们有&nbsp;<code>n</code>&nbsp;座城市和&nbsp;<code>m</code>&nbsp;条双向道路&nbsp;<code>roads</code>&nbsp;，其中&nbsp;<code>roads[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;连接城市&nbsp;<code>a<sub>i</sub></code>&nbsp;和城市&nbsp;<code>b<sub>i</sub></code>。每个城市的名称由字符串数组&nbsp;<code>names</code>&nbsp;中给出的三个大写英文字母组成。从任意城市&nbsp;<code>x</code>&nbsp;出发，你可以到达任意城市&nbsp;<code>y</code> ，其中&nbsp;<code>y != x</code>&nbsp;（即：城市和道路形成一张无向连通图）。</p>

<p>给定一个字符串数组&nbsp;<code>targetPath</code>，你需要找出图中与&nbsp;<code>targetPath</code>&nbsp;的<strong> 长度相同</strong> 且<strong> 编辑距离</strong><strong>最小</strong> 的路径。</p>

<p>你需要返回<em> </em><strong>编辑距离最小的路径中节点的顺序</strong><em> </em>。该路径应当与&nbsp;<code>targetPath</code>&nbsp;的长度相等，且路径需有效（即：&nbsp;<code>ans[i]</code>&nbsp;和&nbsp;<code>ans[i + 1]</code>&nbsp;间应存在直接连通的道路）。如果有多个答案，返回任意一个。</p>

<p><strong>编辑距离</strong> 的定义如下：</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/images/edit.jpg" /></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/images/e1.jpg" style="height: 300px; width: 213px;" /></p>

<pre>
<strong>输入：</strong>n = 5, roads = [[0,2],[0,3],[1,2],[1,3],[1,4],[2,4]], names = ["ATL","PEK","LAX","DXB","HND"], targetPath = ["ATL","DXB","HND","LAX"]
<strong>输出：</strong>[0,2,4,2]
<strong>解释：</strong>[0,2,4,2], [0,3,0,2] 和 [0,3,1,2] 都是正确答案。
[0,2,4,2] 等价于 ["ATL","LAX","HND","LAX"] ，与 targetPath 的编辑距离 = 1。
[0,3,0,2] 等价于 ["ATL","DXB","ATL","LAX"] ，与 targetPath 的编辑距离 = 1。
[0,3,1,2] 等价于 ["ATL","DXB","PEK","LAX"] ，与 targetPath 的编辑距离 = 1。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/images/e2.jpg" style="height: 200px; width: 200px;" /></p>

<pre>
<strong>输入：</strong>n = 4, roads = [[1,0],[2,0],[3,0],[2,1],[3,1],[3,2]], names = ["ATL","PEK","LAX","DXB"], targetPath = ["ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX"]
<strong>输出：</strong>[0,1,0,1,0,1,0,1]
<strong>解释：</strong>任意路径与 targetPath 的编辑距离都等于 8。
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/images/e3.jpg" style="height: 106px; width: 600px;" /></strong></p>

<pre>
<strong>输入：</strong>n = 6, roads = [[0,1],[1,2],[2,3],[3,4],[4,5]], names = ["ATL","PEK","LAX","ATL","DXB","HND"], targetPath = ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
<strong>输出：</strong>[3,4,5,4,3,2,1]
<strong>解释：</strong>[3,4,5,4,3,2,1] 是唯一与 targetPath 的编辑距离 = 0 的路径。
该路径等价于 ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>m == roads.length</code></li>
	<li><code>n - 1 &lt;= m &lt;= (n * (n - 1) / 2)</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub>&nbsp;</code></li>
	<li>给定的图保证是<strong>连通</strong>的，任意两个节点<strong>至多有一个</strong>直接连通的道路。</li>
	<li><code>names.length == n</code></li>
	<li><code>names[i].length == 3</code></li>
	<li><code>names[i]</code>&nbsp;包含大写英文字母。</li>
	<li>可能有两个名称<strong>相同</strong>的城市。</li>
	<li><code>1 &lt;= targetPath.length &lt;= 100</code></li>
	<li><code>targetPath[i].length == 3</code></li>
	<li><code>targetPath[i]</code> 由大写英文字母组成。</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果路径中每个节点只可访问一次，你该如何修改你的答案？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们先根据给定的道路构建一个邻接表 $g$，其中 $g[i]$ 表示与城市 $i$ 直接相连的城市列表。

然后我们定义 $f[i][j]$ 表示 $targetPath$ 的第 $i$ 个城市与 $names$ 的第 $j$ 个城市匹配时，前 $i$ 个城市的最小编辑距离。

那么我们可以得到状态转移方程：

$$
f[i][j] = \min_{k \in g[j]} f[i - 1][k] + (targetPath[i] \neq names[j])
$$

在状态转移的过程中，我们记录下每个状态的前驱城市，最后根据前驱城市数组 $pre$ 从后往前还原出最优路径。

时间复杂度 $O(m \times n^2)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是 $targetPath$ 和 $names$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mostSimilar(
        self, n: int, roads: List[List[int]], names: List[str], targetPath: List[str]
    ) -> List[int]:
        g = [[] for _ in range(n)]
        for a, b in roads:
            g[a].append(b)
            g[b].append(a)
        m = len(targetPath)
        f = [[inf] * n for _ in range(m)]
        pre = [[-1] * n for _ in range(m)]
        for j, s in enumerate(names):
            f[0][j] = targetPath[0] != s
        for i in range(1, m):
            for j in range(n):
                for k in g[j]:
                    if (t := f[i - 1][k] + (targetPath[i] != names[j])) < f[i][j]:
                        f[i][j] = t
                        pre[i][j] = k
        k = 0
        mi = inf
        for j in range(n):
            if f[-1][j] < mi:
                mi = f[-1][j]
                k = j
        ans = [0] * m
        for i in range(m - 1, -1, -1):
            ans[i] = k
            k = pre[i][k]
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] r : roads) {
            int a = r[0], b = r[1];
            g[a].add(b);
            g[b].add(a);
        }
        int m = targetPath.length;
        final int inf = 1 << 30;
        int[][] f = new int[m][n];
        int[][] pre = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(f[i], inf);
            Arrays.fill(pre[i], -1);
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = targetPath[0].equals(names[j]) ? 0 : 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k : g[j]) {
                    int t = f[i - 1][k] + (targetPath[i].equals(names[j]) ? 0 : 1);
                    if (t < f[i][j]) {
                        f[i][j] = t;
                        pre[i][j] = k;
                    }
                }
            }
        }
        int mi = inf, k = 0;
        for (int j = 0; j < n; ++j) {
            if (f[m - 1][j] < mi) {
                mi = f[m - 1][j];
                k = j;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = m - 1; i >= 0; --i) {
            ans.add(k);
            k = pre[i][k];
        }
        Collections.reverse(ans);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> mostSimilar(int n, vector<vector<int>>& roads, vector<string>& names, vector<string>& targetPath) {
        vector<int> g[n];
        for (auto& r : roads) {
            int a = r[0], b = r[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        int m = targetPath.size();
        int f[m][n];
        int pre[m][n];
        memset(f, 0x3f, sizeof(f));
        memset(pre, -1, sizeof(pre));
        for (int j = 0; j < n; ++j) {
            f[0][j] = targetPath[0] != names[j];
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k : g[j]) {
                    int t = f[i - 1][k] + (targetPath[i] != names[j]);
                    if (t < f[i][j]) {
                        f[i][j] = t;
                        pre[i][j] = k;
                    }
                }
            }
        }
        int k = 0;
        int mi = 1 << 30;
        for (int j = 0; j < n; ++j) {
            if (f[m - 1][j] < mi) {
                mi = f[m - 1][j];
                k = j;
            }
        }
        vector<int> ans(m);
        for (int i = m - 1; ~i; --i) {
            ans[i] = k;
            k = pre[i][k];
        }
        return ans;
    }
};
```

#### Go

```go
func mostSimilar(n int, roads [][]int, names []string, targetPath []string) []int {
	g := make([][]int, n)
	for _, r := range roads {
		a, b := r[0], r[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	m := len(targetPath)
	const inf = 1 << 30
	f := make([][]int, m)
	pre := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
		pre[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = inf
			pre[i][j] = -1
		}
	}
	for j, s := range names {
		if targetPath[0] != s {
			f[0][j] = 1
		} else {
			f[0][j] = 0
		}
	}
	for i := 1; i < m; i++ {
		for j := 0; j < n; j++ {
			for _, k := range g[j] {
				t := f[i-1][k]
				if targetPath[i] != names[j] {
					t++
				}
				if t < f[i][j] {
					f[i][j] = t
					pre[i][j] = k
				}
			}
		}
	}
	mi, k := inf, 0
	for j := 0; j < n; j++ {
		if f[m-1][j] < mi {
			mi = f[m-1][j]
			k = j
		}
	}
	ans := make([]int, m)
	for i := m - 1; i >= 0; i-- {
		ans[i] = k
		k = pre[i][k]
	}
	return ans
}
```

#### TypeScript

```ts
function mostSimilar(
    n: number,
    roads: number[][],
    names: string[],
    targetPath: string[],
): number[] {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of roads) {
        g[a].push(b);
        g[b].push(a);
    }
    const m = targetPath.length;
    const f = Array.from({ length: m }, () => Array.from({ length: n }, () => Infinity));
    const pre: number[][] = Array.from({ length: m }, () => Array.from({ length: n }, () => -1));
    for (let j = 0; j < n; ++j) {
        f[0][j] = names[j] === targetPath[0] ? 0 : 1;
    }
    for (let i = 1; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (const k of g[j]) {
                const t = f[i - 1][k] + (names[j] === targetPath[i] ? 0 : 1);
                if (t < f[i][j]) {
                    f[i][j] = t;
                    pre[i][j] = k;
                }
            }
        }
    }
    let k = 0;
    let mi = Infinity;
    for (let j = 0; j < n; ++j) {
        if (f[m - 1][j] < mi) {
            mi = f[m - 1][j];
            k = j;
        }
    }
    const ans: number[] = Array(m).fill(0);
    for (let i = m - 1; ~i; --i) {
        ans[i] = k;
        k = pre[i][k];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
