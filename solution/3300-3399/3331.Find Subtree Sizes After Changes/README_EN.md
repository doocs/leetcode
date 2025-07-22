---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3331.Find%20Subtree%20Sizes%20After%20Changes/README_EN.md
rating: 2045
source: Biweekly Contest 142 Q2
tags:
    - Tree
    - Depth-First Search
    - Array
    - Hash Table
    - String
---

<!-- problem:start -->

# [3331. Find Subtree Sizes After Changes](https://leetcode.com/problems/find-subtree-sizes-after-changes)

[中文文档](/solution/3300-3399/3331.Find%20Subtree%20Sizes%20After%20Changes/README.md)

## Description

<!-- description:start -->

<p>You are given a tree rooted at node 0 that consists of <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code>. The tree is represented by an array <code>parent</code> of size <code>n</code>, where <code>parent[i]</code> is the parent of node <code>i</code>. Since node 0 is the root, <code>parent[0] == -1</code>.</p>

<p>You are also given a string <code>s</code> of length <code>n</code>, where <code>s[i]</code> is the character assigned to node <code>i</code>.</p>

<p>We make the following changes on the tree <strong>one</strong> time <strong>simultaneously</strong> for all nodes <code>x</code> from <code>1</code> to <code>n - 1</code>:</p>

<ul>
	<li>Find the <strong>closest</strong> node <code>y</code> to node <code>x</code> such that <code>y</code> is an ancestor of <code>x</code>, and <code>s[x] == s[y]</code>.</li>
	<li>If node <code>y</code> does not exist, do nothing.</li>
	<li>Otherwise, <strong>remove</strong> the edge between <code>x</code> and its current parent and make node <code>y</code> the new parent of <code>x</code> by adding an edge between them.</li>
</ul>

<p>Return an array <code>answer</code> of size <code>n</code> where <code>answer[i]</code> is the <strong>size</strong> of the <span data-keyword="subtree">subtree</span> rooted at node <code>i</code> in the <strong>final</strong> tree.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">parent = [-1,0,0,1,1,1], s = &quot;abaabc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[6,3,1,1,1,1]</span></p>

<p><strong>Explanation:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3331.Find%20Subtree%20Sizes%20After%20Changes/images/graphex1drawio.png" style="width: 230px; height: 277px;" />
<p>The parent of node 3 will change from node 1 to node 0.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">parent = [-1,0,4,0,1], s = &quot;abbba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[5,2,1,1,1]</span></p>

<p><strong>Explanation:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3331.Find%20Subtree%20Sizes%20After%20Changes/images/exgraph2drawio.png" style="width: 160px; height: 308px;" />
<p>The following changes will happen at the same time:</p>

<ul>
	<li>The parent of node 4 will change from node 1 to node 0.</li>
	<li>The parent of node 2 will change from node 4 to node 1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == parent.length == s.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= parent[i] &lt;= n - 1</code> for all <code>i &gt;= 1</code>.</li>
	<li><code>parent[0] == -1</code></li>
	<li><code>parent</code> represents a valid tree.</li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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
