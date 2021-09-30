# [802. 找到最终的安全状态](https://leetcode-cn.com/problems/find-eventual-safe-states)

[English Version](/solution/0800-0899/0802.Find%20Eventual%20Safe%20States/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在有向图中，从某个节点和每个转向处开始出发，沿着图的有向边走。如果到达的节点是终点（即它没有连出的有向边），则停止。</p>

<p>如果从起始节点出发，最后必然能走到终点，就认为起始节点是 <strong>最终安全</strong> 的。更具体地说，对于最终安全的起始节点而言，存在一个自然数 <code>k</code> ，<strong>无论选择沿哪条有向边行走</strong> ，走了不到 <code>k</code> 步后必能停止在一个终点上。</p>

<p>返回一个由图中所有最终安全的起始节点组成的数组作为答案。答案数组中的元素应当按 <strong>升序</strong> 排列。</p>

<p>该有向图有 <code>n</code> 个节点，按 <code>0</code> 到 <code>n - 1</code> 编号，其中 <code>n</code> 是 <code>graph</code> 的节点数。图以下述形式给出：<code>graph[i]</code> 是编号 <code>j</code> 节点的一个列表，满足 <code>(i, j)</code> 是图的一条有向边。</p>

<p> </p>

<div class="original__bRMd">
<div>
<p><strong>示例 1：</strong></p>
<img alt="Illustration of graph" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0802.Find%20Eventual%20Safe%20States/images/picture1.png" style="height: 171px; width: 600px;" />
<pre>
<strong>输入：</strong>graph = [[1,2],[2,3],[5],[0],[5],[],[]]
<strong>输出：</strong>[2,4,5,6]
<strong>解释：</strong>示意图如上。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
<strong>输出：</strong>[4]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == graph.length</code></li>
	<li><code>1 <= n <= 10<sup>4</sup></code></li>
	<li><code>0 <= graph[i].legnth <= n</code></li>
	<li><code>graph[i]</code> 按严格递增顺序排列。</li>
	<li>图中可能包含自环。</li>
	<li>图中边的数目在范围 <code>[1, 4 * 10<sup>4</sup>]</code> 内。</li>
</ul>
</div>
</div>


## 解法

<!-- 这里可写通用的实现逻辑 -->

出度为零的点是安全的，如果一个点**只能**到达安全的点，那么它同样是安全的，所以问题转换成了拓扑排序

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def eventualSafeNodes(self, graph: List[List[int]]) -> List[int]:
        n = len(graph)
        outDegree = [len(vs) for vs in graph]
        revGraph = [[] for _ in range(n)]
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

### **...**

```

```

<!-- tabs:end -->
