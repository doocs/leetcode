---
comments: true
difficulty: Medium
rating: 1732
source: Biweekly Contest 14 Q3
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Array
    - Tree DP
---

<!-- problem:start -->

# [1273. Delete Tree Nodes 🔒](https://leetcode.com/problems/delete-tree-nodes)

[中文文档](/solution/1200-1299/1273.Delete%20Tree%20Nodes/README.md)

## Description

<!-- description:start -->

<p>A tree rooted at node 0 is given as follows:</p>

<ul>
	<li>The number of nodes is <code>nodes</code>;</li>
	<li>The value of the <code>i<sup>th</sup></code> node is <code>value[i]</code>;</li>
	<li>The parent of the <code>i<sup>th</sup></code> node is <code>parent[i]</code>.</li>
</ul>

<p>Remove every subtree whose sum of values of nodes is zero.</p>

<p>Return <em>the number of the remaining nodes in the tree</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1273.Delete%20Tree%20Nodes/images/1421_sample_1.png" style="width: 403px; height: 347px;" />
<pre>
<strong>Input:</strong> nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-2]
<strong>Output:</strong> 6
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nodes &lt;= 10<sup>4</sup></code></li>
	<li><code>parent.length == nodes</code></li>
	<li><code>0 &lt;= parent[i] &lt;= nodes - 1</code></li>
	<li><code>parent[0] == -1</code> which indicates that <code>0</code> is the root.</li>
	<li><code>value.length == nodes</code></li>
	<li><code>-10<sup>5</sup> &lt;= value[i] &lt;= 10<sup>5</sup></code></li>
	<li>The given input is <strong>guaranteed</strong> to represent a <strong>valid tree</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

<!-- thinking:start -->

> **Thinking**
>
> A subtree whose values sum to $0$ is deleted. $n \le 10^4$, so one bottom-up DFS: sum children's values and surviving sizes, then zero the size if this subtree sums to $0$. The root's surviving size is the answer. Post-order deletes children before the parent decides.

<!-- thinking:end -->

First, we convert the tree into a graph $g$, where $g[i]$ represents all the child nodes of node $i$.

Then we design a function $dfs(i)$, which represents the number of nodes and the sum of the weights in the subtree rooted at node $i$. The answer is $dfs(0)[1]$.

In this function, we recursively calculate the number of nodes and the sum of the weights in the subtree rooted at each child node $j$, and then accumulate these values. If the accumulated value is zero, we set the number of nodes in this subtree to zero. Finally, we return the number of nodes and the sum of the weights in the subtree rooted at node $i$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the number of nodes in the tree.

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

### Solution 2: Explicit Stack

<!-- thinking:start -->

> **Thinking**
>
> A subtree whose values sum to $0$ is deleted. With $n \le 10^4$, one bottom-up pass is enough: sum children's values and surviving sizes, then zero the size if this subtree sums to $0$. Recursing into each child is too deep: a chain makes the call depth $n$.
>
> An explicit stack of $(node, state)$ runs that postorder. On entry we push the exit marker and then the children; on exit we add each child's sum and surviving size, and zero the size when the sum is $0$. The root's surviving size is the answer.

<!-- thinking:end -->

First, we convert the tree into a graph $g$, where $g[i]$ represents all the child nodes of node $i$.

Then an explicit stack walks the tree in postorder. When node $i$ is left, we add the sum and the surviving size of each child. If the accumulated sum is zero, we set this subtree's surviving size to zero. The answer is the surviving size of the root.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the number of nodes in the tree.

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
