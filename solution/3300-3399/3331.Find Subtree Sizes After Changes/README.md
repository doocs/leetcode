---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3331.Find%20Subtree%20Sizes%20After%20Changes/README.md
rating: 2045
source: 第 142 场双周赛 Q2
tags:
    - 树
    - 深度优先搜索
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [3331. 修改后子树的大小](https://leetcode.cn/problems/find-subtree-sizes-after-changes)

[English Version](/solution/3300-3399/3331.Find%20Subtree%20Sizes%20After%20Changes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵 <code>n</code>&nbsp;个节点且根节点为编号 0 的树，节点编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;。这棵树用一个长度为&nbsp;<code>n</code>&nbsp;的数组&nbsp;<code>parent</code>&nbsp;表示，其中&nbsp;<code>parent[i]</code>&nbsp;是第 <code>i</code>&nbsp;个节点的父亲节点的编号。由于节点 0 是根，<code>parent[0] == -1</code>&nbsp;。</p>

<p>给你一个长度为 <code>n</code>&nbsp;的字符串&nbsp;<code>s</code>&nbsp;，其中&nbsp;<code>s[i]</code>&nbsp;是节点 <code>i</code>&nbsp;对应的字符。</p>

<p>对于节点编号从 <code>1</code>&nbsp;到 <code>n - 1</code>&nbsp;的每个节点 <code>x</code>&nbsp;，我们 <strong>同时</strong> 执行以下操作 <strong>一次</strong>&nbsp;：</p>

<ul>
	<li>找到距离节点 <code>x</code>&nbsp;<strong>最近</strong>&nbsp;的祖先节点 <code>y</code>&nbsp;，且&nbsp;<code>s[x] == s[y]</code>&nbsp;。</li>
	<li>如果节点 <code>y</code>&nbsp;不存在，那么不做任何修改。</li>
	<li>否则，将节点 <code>x</code>&nbsp;与它父亲节点之间的边 <strong>删除</strong>&nbsp;，在 <code>x</code>&nbsp;与 <code>y</code>&nbsp;之间连接一条边，使&nbsp;<code>y</code>&nbsp;变为 <code>x</code>&nbsp;新的父节点。</li>
</ul>

<p>请你返回一个长度为 <code>n</code>&nbsp;的数组&nbsp;<code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code>&nbsp;是 <strong>最终</strong>&nbsp;树中，节点 <code>i</code>&nbsp;为根的 <span data-keyword="subtree">子树</span> 的 <strong>大小</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>parent = [-1,0,0,1,1,1], s = "abaabc"</span></p>

<p><span class="example-io"><b>输出：</b>[6,3,1,1,1,1]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3331.Find%20Subtree%20Sizes%20After%20Changes/images/graphex1drawio.png" style="width: 230px; height: 277px;" /></p>

<p>节点 3 的父节点从节点 1 变为节点 0 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>parent = [-1,0,4,0,1], s = "abbba"</span></p>

<p><span class="example-io"><b>输出：</b>[5,2,1,1,1]</span></p>

<p><b>解释：</b></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3331.Find%20Subtree%20Sizes%20After%20Changes/images/exgraph2drawio.png" style="width: 160px; height: 308px;" /></p>

<p>以下变化会同时发生：</p>

<ul>
	<li>节点 4 的父节点从节点 1 变为节点 0 。</li>
	<li>节点 2 的父节点从节点 4 变为节点 1 。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == parent.length == s.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li>对于所有的&nbsp;<code>i &gt;= 1</code>&nbsp;，都有&nbsp;<code>0 &lt;= parent[i] &lt;= n - 1</code>&nbsp;。</li>
	<li><code>parent[0] == -1</code></li>
	<li><code>parent</code>&nbsp;表示一棵合法的树。</li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findSubtreeSizes(self, parent: List[int], s: str) -> List[int]:
        def dfs(i: int, fa: int):
            ans[i] = 1
            d[s[i]].append(i)
            for j in g[i]:
                dfs(j, i)
            k = fa
            if len(d[s[i]]) > 1:
                k = d[s[i]][-2]
            if k != -1:
                ans[k] += ans[i]
            d[s[i]].pop()

        n = len(s)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parent[i]].append(i)
        d = defaultdict(list)
        ans = [0] * n
        dfs(0, -1)
        return ans
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private List<Integer>[] d;
    private char[] s;
    private int[] ans;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = s.length();
        g = new List[n];
        d = new List[26];
        this.s = s.toCharArray();
        Arrays.setAll(g, k -> new ArrayList<>());
        Arrays.setAll(d, k -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            g[parent[i]].add(i);
        }
        ans = new int[n];
        dfs(0, -1);
        return ans;
    }

    private void dfs(int i, int fa) {
        ans[i] = 1;
        int idx = s[i] - 'a';
        d[idx].add(i);
        for (int j : g[i]) {
            dfs(j, i);
        }
        int k = d[idx].size() > 1 ? d[idx].get(d[idx].size() - 2) : fa;
        if (k >= 0) {
            ans[k] += ans[i];
        }
        d[idx].remove(d[idx].size() - 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findSubtreeSizes(vector<int>& parent, string s) {
        int n = s.size();
        vector<int> g[n];
        vector<int> d[26];
        for (int i = 1; i < n; ++i) {
            g[parent[i]].push_back(i);
        }
        vector<int> ans(n);
        auto dfs = [&](this auto&& dfs, int i, int fa) -> void {
            ans[i] = 1;
            int idx = s[i] - 'a';
            d[idx].push_back(i);
            for (int j : g[i]) {
                dfs(j, i);
            }
            int k = d[idx].size() > 1 ? d[idx][d[idx].size() - 2] : fa;
            if (k >= 0) {
                ans[k] += ans[i];
            }
            d[idx].pop_back();
        };
        dfs(0, -1);
        return ans;
    }
};
```

#### Go

```go
func findSubtreeSizes(parent []int, s string) []int {
	n := len(s)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}
	d := [26][]int{}
	ans := make([]int, n)
	var dfs func(int, int)
	dfs = func(i, fa int) {
		ans[i] = 1
		idx := int(s[i] - 'a')
		d[idx] = append(d[idx], i)
		for _, j := range g[i] {
			dfs(j, i)
		}
		k := fa
		if len(d[idx]) > 1 {
			k = d[idx][len(d[idx])-2]
		}
		if k != -1 {
			ans[k] += ans[i]
		}
		d[idx] = d[idx][:len(d[idx])-1]
	}
	dfs(0, -1)
	return ans
}
```

#### TypeScript

```ts
function findSubtreeSizes(parent: number[], s: string): number[] {
    const n = parent.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    const d: number[][] = Array.from({ length: 26 }, () => []);
    for (let i = 1; i < n; ++i) {
        g[parent[i]].push(i);
    }
    const ans: number[] = Array(n).fill(1);
    const dfs = (i: number, fa: number): void => {
        const idx = s.charCodeAt(i) - 97;
        d[idx].push(i);
        for (const j of g[i]) {
            dfs(j, i);
        }
        const k = d[idx].length > 1 ? d[idx].at(-2)! : fa;
        if (k >= 0) {
            ans[k] += ans[i];
        }
        d[idx].pop();
    };
    dfs(0, -1);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
