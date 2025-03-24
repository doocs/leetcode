---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3493.Properties%20Graph/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 图
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [3493. 属性图](https://leetcode.cn/problems/properties-graph)

[English Version](/solution/3400-3499/3493.Properties%20Graph/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维整数数组 <code>properties</code>，其维度为 <code>n x m</code>，以及一个整数 <code>k</code>。</p>

<p>定义一个函数 <code>intersect(a, b)</code>，它返回数组 <code>a</code> 和 <code>b</code> 中<strong> 共有的不同整数的数量 </strong>。</p>

<p>构造一个 <strong>无向图</strong>，其中每个索引 <code>i</code> 对应 <code>properties[i]</code>。如果且仅当 <code>intersect(properties[i], properties[j]) &gt;= k</code>（其中 <code>i</code> 和 <code>j</code> 的范围为 <code>[0, n - 1]</code> 且 <code>i != j</code>），节点 <code>i</code> 和节点 <code>j</code> 之间有一条边。</p>

<p>返回结果图中<strong> 连通分量 </strong>的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">properties = [[1,2],[1,1],[3,4],[4,5],[5,6],[7,7]], k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>生成的图有 3 个连通分量：</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3493.Properties%20Graph/images/1742665594-CDVPWz-image.png" style="width: 279px; height: 171px;" /></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">properties = [[1,2,3],[2,3,4],[4,3,5]], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>生成的图有 1 个连通分量：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3493.Properties%20Graph/images/1742665565-NzYlYH-screenshot-from-2025-02-27-23-58-34.png" style="width: 219px; height: 171px;" /></p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">properties = [[1,1],[1,1]], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><code>intersect(properties[0], properties[1]) = 1</code>，小于 <code>k</code>。因此在图中 <code>properties[0]</code> 和 <code>properties[1]</code> 之间没有边。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == properties.length &lt;= 100</code></li>
	<li><code>1 &lt;= m == properties[i].length &lt;= 100</code></li>
	<li><code>1 &lt;= properties[i][j] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= m</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + DFS

我们先将每个属性数组转换为一个哈希表，存储在哈希表数组 $\textit{ss}$ 中。定义一个图 $\textit{g}$，其中 $\textit{g}[i]$ 存储了与属性数组 $\textit{properties}[i]$ 有边相连的属性数组的索引。

然后我们遍历所有的属性哈希表，对于每一对属性哈希表 $(i, j)$，其中 $j < i$，我们检查这两个属性哈希表中的交集元素个数是否大于等于 $k$，如果是，则在图 $\textit{g}$ 中添加一条从 $i$ 到 $j$ 的边，同时在图 $\textit{g}$ 中添加一条从 $j$ 到 $i$ 的边。

最后，我们使用深度优先搜索计算图 $\textit{g}$ 的连通分量的数量。

时间复杂度 $O(n^2 \times m)$，空间复杂度 $O(n \times m)$。其中 $n$ 是属性数组的长度，而 $m$ 是属性数组中的元素个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfComponents(self, properties: List[List[int]], k: int) -> int:
        def dfs(i: int) -> None:
            vis[i] = True
            for j in g[i]:
                if not vis[j]:
                    dfs(j)

        n = len(properties)
        ss = list(map(set, properties))
        g = [[] for _ in range(n)]
        for i, s1 in enumerate(ss):
            for j in range(i):
                s2 = ss[j]
                if len(s1 & s2) >= k:
                    g[i].append(j)
                    g[j].append(i)
        ans = 0
        vis = [False] * n
        for i in range(n):
            if not vis[i]:
                dfs(i)
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private boolean[] vis;

    public int numberOfComponents(int[][] properties, int k) {
        int n = properties.length;
        g = new List[n];
        Set<Integer>[] ss = new Set[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        Arrays.setAll(ss, i -> new HashSet<>());
        for (int i = 0; i < n; ++i) {
            for (int x : properties[i]) {
                ss[i].add(x);
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int cnt = 0;
                for (int x : ss[i]) {
                    if (ss[j].contains(x)) {
                        ++cnt;
                    }
                }
                if (cnt >= k) {
                    g[i].add(j);
                    g[j].add(i);
                }
            }
        }

        int ans = 0;
        vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                dfs(i);
                ++ans;
            }
        }
        return ans;
    }

    private void dfs(int i) {
        vis[i] = true;
        for (int j : g[i]) {
            if (!vis[j]) {
                dfs(j);
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfComponents(vector<vector<int>>& properties, int k) {
        int n = properties.size();
        unordered_set<int> ss[n];
        vector<int> g[n];
        for (int i = 0; i < n; ++i) {
            for (int x : properties[i]) {
                ss[i].insert(x);
            }
        }
        for (int i = 0; i < n; ++i) {
            auto& s1 = ss[i];
            for (int j = 0; j < i; ++j) {
                auto& s2 = ss[j];
                int cnt = 0;
                for (int x : s1) {
                    if (s2.contains(x)) {
                        ++cnt;
                    }
                }
                if (cnt >= k) {
                    g[i].push_back(j);
                    g[j].push_back(i);
                }
            }
        }
        int ans = 0;
        vector<bool> vis(n);
        auto dfs = [&](this auto&& dfs, int i) -> void {
            vis[i] = true;
            for (int j : g[i]) {
                if (!vis[j]) {
                    dfs(j);
                }
            }
        };
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                dfs(i);
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfComponents(properties [][]int, k int) (ans int) {
	n := len(properties)
	ss := make([]map[int]struct{}, n)
	g := make([][]int, n)

	for i := 0; i < n; i++ {
		ss[i] = make(map[int]struct{})
		for _, x := range properties[i] {
			ss[i][x] = struct{}{}
		}
	}

	for i := 0; i < n; i++ {
		for j := 0; j < i; j++ {
			cnt := 0
			for x := range ss[i] {
				if _, ok := ss[j][x]; ok {
					cnt++
				}
			}
			if cnt >= k {
				g[i] = append(g[i], j)
				g[j] = append(g[j], i)
			}
		}
	}

	vis := make([]bool, n)
	var dfs func(int)
	dfs = func(i int) {
		vis[i] = true
		for _, j := range g[i] {
			if !vis[j] {
				dfs(j)
			}
		}
	}

	for i := 0; i < n; i++ {
		if !vis[i] {
			dfs(i)
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function numberOfComponents(properties: number[][], k: number): number {
    const n = properties.length;
    const ss: Set<number>[] = Array.from({ length: n }, () => new Set());
    const g: number[][] = Array.from({ length: n }, () => []);

    for (let i = 0; i < n; i++) {
        for (const x of properties[i]) {
            ss[i].add(x);
        }
    }

    for (let i = 0; i < n; i++) {
        for (let j = 0; j < i; j++) {
            let cnt = 0;
            for (const x of ss[i]) {
                if (ss[j].has(x)) {
                    cnt++;
                }
            }
            if (cnt >= k) {
                g[i].push(j);
                g[j].push(i);
            }
        }
    }

    let ans = 0;
    const vis: boolean[] = Array(n).fill(false);

    const dfs = (i: number) => {
        vis[i] = true;
        for (const j of g[i]) {
            if (!vis[j]) {
                dfs(j);
            }
        }
    };

    for (let i = 0; i < n; i++) {
        if (!vis[i]) {
            dfs(i);
            ans++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
