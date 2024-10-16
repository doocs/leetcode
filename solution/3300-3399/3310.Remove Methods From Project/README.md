---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3310.Remove%20Methods%20From%20Project/README.md
rating: 1710
source: 第 418 场周赛 Q2
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
---

<!-- problem:start -->

# [3310. 移除可疑的方法](https://leetcode.cn/problems/remove-methods-from-project)

[English Version](/solution/3300-3399/3310.Remove%20Methods%20From%20Project/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你正在维护一个项目，该项目有 <code>n</code> 个方法，编号从 <code>0</code> 到 <code>n - 1</code>。</p>

<p>给你两个整数 <code>n</code> 和 <code>k</code>，以及一个二维整数数组 <code>invocations</code>，其中 <code>invocations[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 表示方法 <code>a<sub>i</sub></code> 调用了方法 <code>b<sub>i</sub></code>。</p>

<p>已知如果方法 <code>k</code> 存在一个已知的 bug。那么方法 <code>k</code> 以及它直接或间接调用的任何方法都被视为<strong> </strong><strong>可疑方法</strong> ，我们需要从项目中移除这些方法。</p>

<p><span class="text-only" data-eleid="13" style="white-space: pre;">只有当一组方法没有被这组之外的任何方法调用时，这组方法才能被移除。</span></p>

<p>返回一个数组，包含移除所有<strong> </strong><strong>可疑方法</strong> 后剩下的所有方法。你可以以任意顺序返回答案。如果无法移除<strong> 所有 </strong>可疑方法，则<strong> 不 </strong>移除任何方法。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 4, k = 1, invocations = [[1,2],[0,1],[3,2]]</span></p>

<p><strong>输出:</strong> <span class="example-io">[0,1,2,3]</span></p>

<p><strong>解释:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3310.Remove%20Methods%20From%20Project/images/graph-2.png" style="width: 200px; height: 200px;" /></p>

<p>方法 2 和方法 1 是可疑方法，但它们分别直接被方法 3 和方法 0 调用。由于方法 3 和方法 0 不是可疑方法，我们无法移除任何方法，故返回所有方法。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 5, k = 0, invocations = [[1,2],[0,2],[0,1],[3,4]]</span></p>

<p><strong>输出:</strong> <span class="example-io">[3,4]</span></p>

<p><strong>解释:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3310.Remove%20Methods%20From%20Project/images/graph-3.png" style="width: 200px; height: 200px;" /></p>

<p>方法 0、方法 1 和方法 2 是可疑方法，且没有被任何其他方法直接调用。我们可以移除它们。</p>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 3, k = 2, invocations = [[1,2],[0,1],[2,0]]</span></p>

<p><strong>输出:</strong> <span class="example-io">[]</span></p>

<p><strong>解释:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3310.Remove%20Methods%20From%20Project/images/graph.png" style="width: 200px; height: 200px;" /></p>

<p>所有方法都是可疑方法。我们可以移除它们。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= n - 1</code></li>
	<li><code>0 &lt;= invocations.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>invocations[i] == [a<sub>i</sub>, b<sub>i</sub>]</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>invocations[i] != invocations[j]</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：两次 DFS

我们可以先从 $k$ 出发，找到所有可疑方法，用数组 $\textit{suspicious}$ 记录。然后再从 $0$ 到 $n-1$ 遍历，从所有不可疑方法出发，将所有可达的方法标记为不可疑方法。最后返回所有不可疑方法。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别表示方法数量和调用关系数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def remainingMethods(
        self, n: int, k: int, invocations: List[List[int]]
    ) -> List[int]:
        def dfs(i: int):
            suspicious[i] = True
            for j in g[i]:
                if not suspicious[j]:
                    dfs(j)

        def dfs2(i: int):
            vis[i] = True
            for j in f[i]:
                if not vis[j]:
                    suspicious[j] = False
                    dfs2(j)

        f = [[] for _ in range(n)]
        g = [[] for _ in range(n)]
        for a, b in invocations:
            f[a].append(b)
            f[b].append(a)
            g[a].append(b)
        suspicious = [False] * n
        dfs(k)

        vis = [False] * n
        ans = []
        for i in range(n):
            if not suspicious[i] and not vis[i]:
                dfs2(i)
        return [i for i in range(n) if not suspicious[i]]
```

#### Java

```java
class Solution {
    private boolean[] suspicious;
    private boolean[] vis;
    private List<Integer>[] f;
    private List<Integer>[] g;

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        suspicious = new boolean[n];
        vis = new boolean[n];
        f = new List[n];
        g = new List[n];
        Arrays.setAll(f, i -> new ArrayList<>());
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : invocations) {
            int a = e[0], b = e[1];
            f[a].add(b);
            f[b].add(a);
            g[a].add(b);
        }
        dfs(k);
        for (int i = 0; i < n; ++i) {
            if (!suspicious[i] && !vis[i]) {
                dfs2(i);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }
        return ans;
    }

    private void dfs(int i) {
        suspicious[i] = true;
        for (int j : g[i]) {
            if (!suspicious[j]) {
                dfs(j);
            }
        }
    }

    private void dfs2(int i) {
        vis[i] = true;
        for (int j : f[i]) {
            if (!vis[j]) {
                suspicious[j] = false;
                dfs2(j);
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> remainingMethods(int n, int k, vector<vector<int>>& invocations) {
        vector<bool> suspicious(n);
        vector<bool> vis(n);
        vector<int> f[n];
        vector<int> g[n];
        for (const auto& e : invocations) {
            int a = e[0], b = e[1];
            f[a].push_back(b);
            f[b].push_back(a);
            g[a].push_back(b);
        }
        auto dfs = [&](auto&& dfs, int i) -> void {
            suspicious[i] = true;
            for (int j : g[i]) {
                if (!suspicious[j]) {
                    dfs(dfs, j);
                }
            }
        };
        dfs(dfs, k);
        auto dfs2 = [&](auto&& dfs2, int i) -> void {
            vis[i] = true;
            for (int j : f[i]) {
                if (!vis[j]) {
                    suspicious[j] = false;
                    dfs2(dfs2, j);
                }
            }
        };
        for (int i = 0; i < n; ++i) {
            if (!suspicious[i] && !vis[i]) {
                dfs2(dfs2, i);
            }
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            if (!suspicious[i]) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func remainingMethods(n int, k int, invocations [][]int) []int {
	suspicious := make([]bool, n)
	vis := make([]bool, n)
	f := make([][]int, n)
	g := make([][]int, n)

	for _, e := range invocations {
		a, b := e[0], e[1]
		f[a] = append(f[a], b)
		f[b] = append(f[b], a)
		g[a] = append(g[a], b)
	}

	var dfs func(int)
	dfs = func(i int) {
		suspicious[i] = true
		for _, j := range g[i] {
			if !suspicious[j] {
				dfs(j)
			}
		}
	}

	dfs(k)

	var dfs2 func(int)
	dfs2 = func(i int) {
		vis[i] = true
		for _, j := range f[i] {
			if !vis[j] {
				suspicious[j] = false
				dfs2(j)
			}
		}
	}

	for i := 0; i < n; i++ {
		if !suspicious[i] && !vis[i] {
			dfs2(i)
		}
	}

	var ans []int
	for i := 0; i < n; i++ {
		if !suspicious[i] {
			ans = append(ans, i)
		}
	}

	return ans
}
```

#### TypeScript

```ts
function remainingMethods(n: number, k: number, invocations: number[][]): number[] {
    const suspicious: boolean[] = Array(n).fill(false);
    const vis: boolean[] = Array(n).fill(false);
    const f: number[][] = Array.from({ length: n }, () => []);
    const g: number[][] = Array.from({ length: n }, () => []);

    for (const [a, b] of invocations) {
        f[a].push(b);
        f[b].push(a);
        g[a].push(b);
    }

    const dfs = (i: number) => {
        suspicious[i] = true;
        for (const j of g[i]) {
            if (!suspicious[j]) {
                dfs(j);
            }
        }
    };

    dfs(k);

    const dfs2 = (i: number) => {
        vis[i] = true;
        for (const j of f[i]) {
            if (!vis[j]) {
                suspicious[j] = false;
                dfs2(j);
            }
        }
    };

    for (let i = 0; i < n; i++) {
        if (!suspicious[i] && !vis[i]) {
            dfs2(i);
        }
    }

    return Array.from({ length: n }, (_, i) => i).filter(i => !suspicious[i]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
