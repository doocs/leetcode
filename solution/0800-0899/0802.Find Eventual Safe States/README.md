# [802. 找到最终的安全状态](https://leetcode-cn.com/problems/find-eventual-safe-states)

[English Version](/solution/0800-0899/0802.Find%20Eventual%20Safe%20States/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个有 <code>n</code> 个节点的有向图，节点按 <code>0</code> 到 <code>n - 1</code> 编号。图由一个 <strong>索引从 0 开始</strong> 的 2D 整数数组&nbsp;<code>graph</code>表示，&nbsp;<code>graph[i]</code>是与节点 <code>i</code> 相邻的节点的整数数组，这意味着从节点 <code>i</code> 到&nbsp;<code>graph[i]</code>中的每个节点都有一条边。</p>

<p>如果一个节点没有连出的有向边，则它是 <strong>终端节点</strong> 。如果没有出边，则节点为终端节点。如果从该节点开始的所有可能路径都通向一个 <strong>终端节点</strong> ，则该节点为 <strong>安全节点</strong> 。</p>

<p>返回一个由图中所有 <strong>安全节点</strong> 组成的数组作为答案。答案数组中的元素应当按 <strong>升序</strong> 排列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="Illustration of graph" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0802.Find%20Eventual%20Safe%20States/images/picture1.png" /></p>

<pre>
<strong>输入：</strong>graph = [[1,2],[2,3],[5],[0],[5],[],[]]
<strong>输出：</strong>[2,4,5,6]
<strong>解释：</strong>示意图如上。
节点5和节点6是终端节点，因为它们都没有出边。
从节点2、4、5和6开始的所有路径都指向节点5或6。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
<strong>输出：</strong>[4]
<strong>解释:</strong>
只有节点4是终端节点，从节点4开始的所有路径都通向节点4。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == graph.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= graph[i].length &lt;= n</code></li>
	<li><code>graph[i]</code> 按严格递增顺序排列。</li>
	<li>图中可能包含自环。</li>
	<li>图中边的数目在范围 <code>[1, 4 * 10<sup>4</sup>]</code> 内。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

出度为零的点是安全的，如果一个点**只能**到达安全的点，那么它同样是安全的，所以问题转换成了拓扑排序。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def eventualSafeNodes(self, graph: List[List[int]]) -> List[int]:
        n = len(graph)
        outDegree = [len(vs) for vs in graph]
        revGraph = defaultdict(list)
        for u, vs in enumerate(graph):
            for v in vs:
                revGraph[v].append(u)
        q = deque([i for i, d in enumerate(outDegree) if d == 0])
        while q:
            for u in revGraph[q.popleft()]:
                outDegree[u] -= 1
                if outDegree[u] == 0:
                    q.append(u)
        return [i for i, d in enumerate(outDegree) if d == 0]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] outDegrees = new int[n];
        Queue<Integer> queue = new ArrayDeque<>();
        List<List<Integer>> revGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            revGraph.add(new ArrayList<>());
        }
        for (int u = 0; u < n; u++) {
            for (int v : graph[u]) {
                revGraph.get(v).add(u);
            }
            outDegrees[u] = graph[u].length;
            if (outDegrees[u] == 0) {
                queue.offer(u);
            }
        }

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int u : revGraph.get(v)) {
                if (--outDegrees[u] == 0) {
                    queue.offer(u);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (outDegrees[i] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```

### **Go**

```go
func eventualSafeNodes(graph [][]int) []int {
	n := len(graph)
	outDegree := make([]int, n)
	revGraph := make([][]int, n)
	queue := make([]int, 0)
	ans := make([]int, 0)

	for u, vs := range graph {
		for _, v := range vs {
			revGraph[v] = append(revGraph[v], u)
		}
		outDegree[u] = len(vs)
		if outDegree[u] == 0 {
			queue = append(queue, u)
		}
	}

	for len(queue) > 0 {
		v := queue[0]
		queue = queue[1:]
		for _, u := range revGraph[v] {
			outDegree[u]--
			if outDegree[u] == 0 {
				queue = append(queue, u)
			}
		}
	}

	for i, d := range outDegree {
		if d == 0 {
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
    vector<int> eventualSafeNodes(vector<vector<int>> &graph) {
        int n = graph.size();
        vector<vector<int>> revGraph(n);
        vector<int> outDegree(n);
        for (int i = 0; i < n; ++i)
        {
            outDegree[i] += graph[i].size();
            for (int j : graph[i])
                revGraph[j].push_back(i);
        }
        queue<int> q;
        for (int i = 0; i < n; ++i)
            if (outDegree[i] == 0)
                q.push(i);
        while (!q.empty())
        {
            int i = q.front();
            q.pop();
            for (int j : revGraph[i])
            {
                if (--outDegree[j] == 0)
                    q.push(j);
            }
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i)
            if (outDegree[i] == 0)
                ans.push_back(i);
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
