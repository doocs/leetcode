---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2246.Longest%20Path%20With%20Different%20Adjacent%20Characters/README.md
rating: 2126
source: 第 289 场周赛 Q4
tags:
    - 树
    - 深度优先搜索
    - 图
    - 拓扑排序
    - 数组
    - 字符串
---

# [2246. 相邻字符不同的最长路径](https://leetcode.cn/problems/longest-path-with-different-adjacent-characters)

[English Version](/solution/2200-2299/2246.Longest%20Path%20With%20Different%20Adjacent%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵 <strong>树</strong>（即一个连通、无向、无环图），根节点是节点 <code>0</code> ，这棵树由编号从 <code>0</code> 到 <code>n - 1</code> 的 <code>n</code> 个节点组成。用下标从 <strong>0</strong> 开始、长度为 <code>n</code> 的数组 <code>parent</code> 来表示这棵树，其中 <code>parent[i]</code> 是节点 <code>i</code> 的父节点，由于节点 <code>0</code> 是根节点，所以 <code>parent[0] == -1</code> 。</p>

<p>另给你一个字符串 <code>s</code> ，长度也是 <code>n</code> ，其中 <code>s[i]</code> 表示分配给节点 <code>i</code> 的字符。</p>

<p>请你找出路径上任意一对相邻节点都没有分配到相同字符的 <strong>最长路径</strong> ，并返回该路径的长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2246.Longest%20Path%20With%20Different%20Adjacent%20Characters/images/testingdrawio.png" style="width: 201px; height: 241px;" /></p>

<pre>
<strong>输入：</strong>parent = [-1,0,0,1,1,2], s = "abacbe"
<strong>输出：</strong>3
<strong>解释：</strong>任意一对相邻节点字符都不同的最长路径是：0 -&gt; 1 -&gt; 3 。该路径的长度是 3 ，所以返回 3 。
可以证明不存在满足上述条件且比 3 更长的路径。 
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2246.Longest%20Path%20With%20Different%20Adjacent%20Characters/images/graph2drawio.png" style="width: 201px; height: 221px;" /></p>

<pre>
<strong>输入：</strong>parent = [-1,0,0,0], s = "aabc"
<strong>输出：</strong>3
<strong>解释：</strong>任意一对相邻节点字符都不同的最长路径是：2 -&gt; 0 -&gt; 3 。该路径的长度为 3 ，所以返回 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == parent.length == s.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li>对所有 <code>i &gt;= 1</code> ，<code>0 &lt;= parent[i] &lt;= n - 1</code> 均成立</li>
	<li><code>parent[0] == -1</code></li>
	<li><code>parent</code> 表示一棵有效的树</li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

## 解法

### 方法一：树形 DP

我们先根据数组 $parent$ 构建邻接表 $g$，其中 $g[i]$ 表示节点 $i$ 的所有子节点。

然后我们从根节点开始 DFS，对于每个节点 $i$，我们遍历 $g[i]$ 中的每个子节点 $j$，如果 $s[i] \neq s[j]$，那么我们就可以从 $i$ 节点出发，经过 $j$ 节点，到达某个叶子节点，这条路径的长度为 $x = 1 + dfs(j)$，我们用 $mx$ 记录最长的一条从节点 $i$ 出发的路径长度。同时，在遍历的过程中，更新答案 $ans = \max(ans, mx + x)$。

最后，我们返回 $ans + 1$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点个数。

<!-- tabs:start -->

```python
class Solution:
    def longestPath(self, parent: List[int], s: str) -> int:
        def dfs(i: int) -> int:
            mx = 0
            nonlocal ans
            for j in g[i]:
                x = dfs(j) + 1
                if s[i] != s[j]:
                    ans = max(ans, mx + x)
                    mx = max(mx, x)
            return mx

        g = defaultdict(list)
        for i in range(1, len(parent)):
            g[parent[i]].append(i)
        ans = 0
        dfs(0)
        return ans + 1
```

```java
class Solution {
    private List<Integer>[] g;
    private String s;
    private int ans;

    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        g = new List[n];
        this.s = s;
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            g[parent[i]].add(i);
        }
        dfs(0);
        return ans + 1;
    }

    private int dfs(int i) {
        int mx = 0;
        for (int j : g[i]) {
            int x = dfs(j) + 1;
            if (s.charAt(i) != s.charAt(j)) {
                ans = Math.max(ans, mx + x);
                mx = Math.max(mx, x);
            }
        }
        return mx;
    }
}
```

```cpp
class Solution {
public:
    int longestPath(vector<int>& parent, string s) {
        int n = parent.size();
        vector<int> g[n];
        for (int i = 1; i < n; ++i) {
            g[parent[i]].push_back(i);
        }
        int ans = 0;
        function<int(int)> dfs = [&](int i) -> int {
            int mx = 0;
            for (int j : g[i]) {
                int x = dfs(j) + 1;
                if (s[i] != s[j]) {
                    ans = max(ans, mx + x);
                    mx = max(mx, x);
                }
            }
            return mx;
        };
        dfs(0);
        return ans + 1;
    }
};
```

```go
func longestPath(parent []int, s string) int {
	n := len(parent)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}
	ans := 0
	var dfs func(int) int
	dfs = func(i int) int {
		mx := 0
		for _, j := range g[i] {
			x := dfs(j) + 1
			if s[i] != s[j] {
				ans = max(ans, x+mx)
				mx = max(mx, x)
			}
		}
		return mx
	}
	dfs(0)
	return ans + 1
}
```

```ts
function longestPath(parent: number[], s: string): number {
    const n = parent.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 1; i < n; ++i) {
        g[parent[i]].push(i);
    }
    let ans = 0;
    const dfs = (i: number): number => {
        let mx = 0;
        for (const j of g[i]) {
            const x = dfs(j) + 1;
            if (s[i] !== s[j]) {
                ans = Math.max(ans, mx + x);
                mx = Math.max(mx, x);
            }
        }
        return mx;
    };
    dfs(0);
    return ans + 1;
}
```

<!-- tabs:end -->

<!-- end -->
