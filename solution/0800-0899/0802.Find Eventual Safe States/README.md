# [802. 找到最终的安全状态](https://leetcode.cn/problems/find-eventual-safe-states)

[English Version](/solution/0800-0899/0802.Find%20Eventual%20Safe%20States/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个有 <code>n</code> 个节点的有向图，节点按 <code>0</code> 到 <code>n - 1</code> 编号。图由一个 <strong>索引从 0 开始</strong> 的 2D 整数数组&nbsp;<code>graph</code>表示，&nbsp;<code>graph[i]</code>是与节点 <code>i</code> 相邻的节点的整数数组，这意味着从节点 <code>i</code> 到&nbsp;<code>graph[i]</code>中的每个节点都有一条边。</p>

<p>如果一个节点没有连出的有向边，则它是 <strong>终端节点</strong> 。如果没有出边，则节点为终端节点。如果从该节点开始的所有可能路径都通向 <strong>终端节点</strong> ，则该节点为 <strong>安全节点</strong> 。</p>

<p>返回一个由图中所有 <strong>安全节点</strong> 组成的数组作为答案。答案数组中的元素应当按 <strong>升序</strong> 排列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="Illustration of graph" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0802.Find%20Eventual%20Safe%20States/images/picture1.png" /></p>

<pre>
<strong>输入：</strong>graph = [[1,2],[2,3],[5],[0],[5],[],[]]
<strong>输出：</strong>[2,4,5,6]
<strong>解释：</strong>示意图如上。
节点 5 和节点 6 是终端节点，因为它们都没有出边。
从节点 2、4、5 和 6 开始的所有路径都指向节点 5 或 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
<strong>输出：</strong>[4]
<strong>解释:</strong>
只有节点 4 是终端节点，从节点 4 开始的所有路径都通向节点 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == graph.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= graph[i].length &lt;= n</code></li>
	<li><code>0 &lt;= graph[i][j] &lt;= n - 1</code></li>
	<li><code>graph[i]</code> 按严格递增顺序排列。</li>
	<li>图中可能包含自环。</li>
	<li>图中边的数目在范围 <code>[1, 4 * 10<sup>4</sup>]</code> 内。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：拓扑排序**

出度为零的点是安全的，如果一个点**只能**到达安全的点，那么它同样是安全的，所以问题转换成了拓扑排序。

我们可以将图中所有边反向，得到一个反图，然后在反图上运行拓扑排序。

时间复杂度 $O(n+m)$，其中 $n$ 表示图中的点数，$m$ 表示图中的边数。

**方法二：DFS + 三色标记法**

若起始节点位于一个环内，或者能到达一个环，则该节点不是安全的。否则，该节点是安全的。

-   白色（用 0 表示）：该节点尚未被访问；
-   灰色（用 1 表示）：该节点位于递归栈中，或者在某个环上；
-   黑色（用 2 表示）：该节点搜索完毕，是一个安全节点。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def eventualSafeNodes(self, graph: List[List[int]]) -> List[int]:
        rg = defaultdict(list)
        indeg = [0] * len(graph)
        for i, vs in enumerate(graph):
            for j in vs:
                rg[j].append(i)
            indeg[i] = len(vs)
        q = deque([i for i, v in enumerate(indeg) if v == 0])
        while q:
            i = q.popleft()
            for j in rg[i]:
                indeg[j] -= 1
                if indeg[j] == 0:
                    q.append(j)
        return [i for i, v in enumerate(indeg) if v == 0]
```

```python
class Solution:
    def eventualSafeNodes(self, graph: List[List[int]]) -> List[int]:
        def dfs(i):
            if color[i]:
                return color[i] == 2
            color[i] = 1
            for j in graph[i]:
                if not dfs(j):
                    return False
            color[i] = 2
            return True

        n = len(graph)
        color = [0] * n
        return [i for i in range(n) if dfs(i)]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] indeg = new int[n];
        List<Integer>[] rg = new List[n];
        Arrays.setAll(rg, k -> new ArrayList<>());
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            for (int j : graph[i]) {
                rg[j].add(i);
            }
            indeg[i] = graph[i].length;
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int i = q.pollFirst();
            for (int j : rg[i]) {
                if (--indeg[j] == 0) {
                    q.offer(j);
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    private int[] color;
    private int[][] g;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        color = new int[n];
        g = graph;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (dfs(i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    private boolean dfs(int i) {
        if (color[i] > 0) {
            return color[i] == 2;
        }
        color[i] = 1;
        for (int j : g[i]) {
            if (!dfs(j)) {
                return false;
            }
        }
        color[i] = 2;
        return true;
    }
}
```

### **Go**

```go
func eventualSafeNodes(graph [][]int) []int {
	n := len(graph)
	indeg := make([]int, n)
	rg := make([][]int, n)
	q := []int{}
	for i, vs := range graph {
		for _, j := range vs {
			rg[j] = append(rg[j], i)
		}
		indeg[i] = len(vs)
		if indeg[i] == 0 {
			q = append(q, i)
		}
	}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		for _, j := range rg[i] {
			indeg[j]--
			if indeg[j] == 0 {
				q = append(q, j)
			}
		}
	}
	ans := []int{}
	for i, v := range indeg {
		if v == 0 {
			ans = append(ans, i)
		}
	}
	return ans
}
```

```go
func eventualSafeNodes(graph [][]int) []int {
	n := len(graph)
	color := make([]int, n)
	var dfs func(int) bool
	dfs = func(i int) bool {
		if color[i] > 0 {
			return color[i] == 2
		}
		color[i] = 1
		for _, j := range graph[i] {
			if !dfs(j) {
				return false
			}
		}
		color[i] = 2
		return true
	}
	ans := []int{}
	for i := range graph {
		if dfs(i) {
			ans = append(ans, i)
		}
	}
	return ans
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> eventualSafeNodes(vector<vector<int>>& graph) {
        int n = graph.size();
        vector<int> indeg(n);
        vector<vector<int>> rg(n);
        queue<int> q;
        for (int i = 0; i < n; ++i) {
            for (int j : graph[i]) rg[j].push_back(i);
            indeg[i] = graph[i].size();
            if (indeg[i] == 0) q.push(i);
        }
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            for (int j : rg[i])
                if (--indeg[j] == 0) q.push(j);
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i)
            if (indeg[i] == 0) ans.push_back(i);
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> color;

    vector<int> eventualSafeNodes(vector<vector<int>>& graph) {
        int n = graph.size();
        color.assign(n, 0);
        vector<int> ans;
        for (int i = 0; i < n; ++i) if (dfs(i, graph)) ans.push_back(i);
        return ans;
    }

    bool dfs(int i, vector<vector<int>>& g) {
        if (color[i]) return color[i] == 2;
        color[i] = 1;
        for (int j : g[i]) if (!dfs(j, g)) return false;
        color[i] = 2;
        return true;
    }
};
```

### **JavaScript**

```js
/**
 * @param {number[][]} graph
 * @return {number[]}
 */
var eventualSafeNodes = function (graph) {
    const n = graph.length;
    const rg = new Array(n).fill(0).map(() => new Array());
    const indeg = new Array(n).fill(0);
    const q = [];
    for (let i = 0; i < n; ++i) {
        for (let j of graph[i]) {
            rg[j].push(i);
        }
        indeg[i] = graph[i].length;
        if (indeg[i] == 0) {
            q.push(i);
        }
    }
    while (q.length) {
        const i = q.shift();
        for (let j of rg[i]) {
            if (--indeg[j] == 0) {
                q.push(j);
            }
        }
    }
    let ans = [];
    for (let i = 0; i < n; ++i) {
        if (indeg[i] == 0) {
            ans.push(i);
        }
    }
    return ans;
};
```

```js
/**
 * @param {number[][]} graph
 * @return {number[]}
 */
var eventualSafeNodes = function (graph) {
    const n = graph.length;
    const color = new Array(n).fill(0);
    function dfs(i) {
        if (color[i]) {
            return color[i] == 2;
        }
        color[i] = 1;
        for (const j of graph[i]) {
            if (!dfs(j)) {
                return false;
            }
        }
        color[i] = 2;
        return true;
    }
    let ans = [];
    for (let i = 0; i < n; ++i) {
        if (dfs(i)) {
            ans.push(i);
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
