---
comments: true
difficulty: 中等
rating: 1732
source: 第 14 场双周赛 Q3
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 数组
    - 树形 DP
---

<!-- problem:start -->

# [1273. 删除树节点 🔒](https://leetcode.cn/problems/delete-tree-nodes)

[English Version](/solution/1200-1299/1273.Delete%20Tree%20Nodes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵以节点 0 为根节点的树，定义如下：</p>

<ul>
	<li>节点的总数为&nbsp;<code>nodes</code>&nbsp;个；</li>
	<li>第&nbsp;<code>i</code> 个节点的值为&nbsp;<code>value[i]</code>&nbsp;；</li>
	<li>第&nbsp;<code>i</code> 个节点的父节点是&nbsp;<code>parent[i]</code>&nbsp;。</li>
</ul>

<p>请你删除节点值之和为 0 的每一棵子树。</p>

<p>在完成所有删除之后，返回树中剩余节点的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1273.Delete%20Tree%20Nodes/images/1421_sample_1.png" style="height: 347px; width: 403px;"></p>

<pre><strong>输入：</strong>nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-2]
<strong>输出：</strong>6
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nodes = 5, parent = [-1,0,1,0,0], value = [-672,441,18,728,378]
<strong>输出：</strong>5
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>nodes = 5, parent = [-1,0,0,1,1], value = [-686,-842,616,-739,-746]
<strong>输出：</strong>5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nodes &lt;= 10^4</code></li>
	<li><code>parent.length == nodes</code></li>
	<li><code>0 &lt;= parent[i] &lt;= nodes - 1</code></li>
	<li><code>parent[0] == -1</code>&nbsp;表示节点 <code>0</code> 是树的根。</li>
	<li><code>value.length == nodes</code></li>
	<li><code>-10^5 &lt;= value[i] &lt;= 10^5</code></li>
	<li>题目输入数据 <strong>保证</strong> 是一棵 <strong>有效的树</strong> 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

<!-- thinking:start -->

> **思考**
>
> 子树权和为 $0$ 则整棵子树删除。 $n \le 10^4$，自底向上一次 DFS 即可：先汇总子女的权与残存节点数，若本子树权和为 $0$ 则残存数置 $0$。返回根的残存数。后序保证删子再决定父亲。

<!-- thinking:end -->

我们先将树转换成图 $g$，其中 $g[i]$ 表示节点 $i$ 的所有子节点。

然后我们设计一个函数 $dfs(i)$，表示以节点 $i$ 为根的子树的节点数目和权值之和。那么答案就是 $dfs(0)[1]$。

在这个函数中，我们递归地计算出以每个子节点 $j$ 为根的子树的节点数目和权值之和，然后将这些值进行累加，如果累加后的值为零，那么我们就将这个子树的节点数目置为零。最后我们返回以节点 $i$ 为根的子树的节点数目和权值之和。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是树的节点数目。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def deleteTreeNodes(self, nodes: int, parent: List[int], value: List[int]) -> int:
        def dfs(i):
            s, m = value[i], 1
            for j in g[i]:
                t, n = dfs(j)
                s += t
                m += n
            if s == 0:
                m = 0
            return (s, m)

        g = defaultdict(list)
        for i in range(1, nodes):
            g[parent[i]].append(i)
        return dfs(0)[1]
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private int[] value;

    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        g = new List[nodes];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < nodes; ++i) {
            g[parent[i]].add(i);
        }
        this.value = value;
        return dfs(0)[1];
    }

    private int[] dfs(int i) {
        int[] res = new int[] {value[i], 1};
        for (int j : g[i]) {
            int[] t = dfs(j);
            res[0] += t[0];
            res[1] += t[1];
        }
        if (res[0] == 0) {
            res[1] = 0;
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int deleteTreeNodes(int nodes, vector<int>& parent, vector<int>& value) {
        vector<vector<int>> g(nodes);
        for (int i = 1; i < nodes; ++i) {
            g[parent[i]].emplace_back(i);
        }
        function<pair<int, int>(int)> dfs = [&](int i) -> pair<int, int> {
            int s = value[i], m = 1;
            for (int j : g[i]) {
                auto [t, n] = dfs(j);
                s += t;
                m += n;
            }
            if (s == 0) {
                m = 0;
            }
            return pair<int, int>{s, m};
        };
        return dfs(0).second;
    }
};
```

#### Go

```go
func deleteTreeNodes(nodes int, parent []int, value []int) int {
	g := make([][]int, nodes)
	for i := 1; i < nodes; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}
	type pair struct{ s, n int }
	var dfs func(int) pair
	dfs = func(i int) pair {
		s, m := value[i], 1
		for _, j := range g[i] {
			t := dfs(j)
			s += t.s
			m += t.n
		}
		if s == 0 {
			m = 0
		}
		return pair{s, m}
	}
	return dfs(0).n
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：显式栈

<!-- thinking:start -->

> **思考**
>
> 子树权和为 $0$ 则整棵子树删除。$n \le 10^4$，自底向上汇总一次即可：先汇总子女的权与残存节点数，若本子树权和为 $0$ 则残存数置 $0$。从根递归进入孩子，链上的调用深度就是 $n$，会超出递归栈。
>
> 显式栈按 $(节点, 状态)$ 做后序。进入时先压出栈标记再压孩子，离开时把子女的权和残存数累加到自己；和为 $0$ 则残存数置 $0$。答案是根的残存数。

<!-- thinking:end -->

我们先将树转换成图 $g$，其中 $g[i]$ 表示节点 $i$ 的所有子节点。

然后用显式栈做后序遍历。离开节点 $i$ 时，把每个子节点的权值和残存节点数累加进来。如果累加后的权值为零，就把这个子树的残存节点数置为零。答案是根节点的残存节点数。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是树的节点数目。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def deleteTreeNodes(self, nodes: int, parent: List[int], value: List[int]) -> int:
        g = [[] for _ in range(nodes)]
        for i in range(1, nodes):
            g[parent[i]].append(i)
        sub = [(0, 0)] * nodes
        stk = [(0, 0)]
        while stk:
            i, state = stk.pop()
            if state == 0:
                stk.append((i, 1))
                for j in g[i]:
                    stk.append((j, 0))
            else:
                s, m = value[i], 1
                for j in g[i]:
                    t, c = sub[j]
                    s += t
                    m += c
                if s == 0:
                    m = 0
                sub[i] = (s, m)
        return sub[0][1]
```

#### Java

```java
class Solution {
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        List<Integer>[] g = new List[nodes];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < nodes; ++i) {
            g[parent[i]].add(i);
        }
        int[] sum = new int[nodes];
        int[] cnt = new int[nodes];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], state = cur[1];
            if (state == 0) {
                stk.push(new int[] {i, 1});
                for (int j : g[i]) {
                    stk.push(new int[] {j, 0});
                }
            } else {
                int s = value[i], m = 1;
                for (int j : g[i]) {
                    s += sum[j];
                    m += cnt[j];
                }
                if (s == 0) {
                    m = 0;
                }
                sum[i] = s;
                cnt[i] = m;
            }
        }
        return cnt[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int deleteTreeNodes(int nodes, vector<int>& parent, vector<int>& value) {
        vector<vector<int>> g(nodes);
        for (int i = 1; i < nodes; ++i) {
            g[parent[i]].emplace_back(i);
        }
        vector<int> sum(nodes), cnt(nodes);
        vector<pair<int, int>> stk{{0, 0}};
        while (!stk.empty()) {
            auto [i, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.emplace_back(i, 1);
                for (int j : g[i]) {
                    stk.emplace_back(j, 0);
                }
            } else {
                int s = value[i], m = 1;
                for (int j : g[i]) {
                    s += sum[j];
                    m += cnt[j];
                }
                if (s == 0) {
                    m = 0;
                }
                sum[i] = s;
                cnt[i] = m;
            }
        }
        return cnt[0];
    }
};
```

#### Go

```go
func deleteTreeNodes(nodes int, parent []int, value []int) int {
	g := make([][]int, nodes)
	for i := 1; i < nodes; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}
	sum := make([]int, nodes)
	cnt := make([]int, nodes)
	stk := [][2]int{{0, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, state := cur[0], cur[1]
		if state == 0 {
			stk = append(stk, [2]int{i, 1})
			for _, j := range g[i] {
				stk = append(stk, [2]int{j, 0})
			}
		} else {
			s, m := value[i], 1
			for _, j := range g[i] {
				s += sum[j]
				m += cnt[j]
			}
			if s == 0 {
				m = 0
			}
			sum[i] = s
			cnt[i] = m
		}
	}
	return cnt[0]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
