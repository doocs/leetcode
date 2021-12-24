# [797. 所有可能的路径](https://leetcode-cn.com/problems/all-paths-from-source-to-target)

[English Version](/solution/0700-0799/0797.All%20Paths%20From%20Source%20to%20Target/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给一个有&nbsp;<code>n</code>&nbsp;个结点的有向无环图，找到所有从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n-1</code>&nbsp;的路径并输出（不要求按顺序）</p>

<p>二维数组的第 <code>i</code> 个数组中的单元都表示有向图中 <code>i</code> 号结点所能到达的下一些结点（译者注：有向图是有方向的，即规定了 a&rarr;b 你就不能从 b&rarr;a ）空就是没有下一个结点了。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0797.All%20Paths%20From%20Source%20to%20Target/images/all_1.jpg" style="height: 242px; width: 242px;"></p>

<pre><strong>输入：</strong>graph = [[1,2],[3],[3],[]]
<strong>输出：</strong>[[0,1,3],[0,2,3]]
<strong>解释：</strong>有两条路径 0 -&gt; 1 -&gt; 3 和 0 -&gt; 2 -&gt; 3
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0797.All%20Paths%20From%20Source%20to%20Target/images/all_2.jpg" style="height: 301px; width: 423px;"></p>

<pre><strong>输入：</strong>graph = [[4,3,1],[3,2,4],[3],[4],[]]
<strong>输出：</strong>[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>graph = [[1],[]]
<strong>输出：</strong>[[0,1]]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>graph = [[1,2,3],[2],[3],[]]
<strong>输出：</strong>[[0,1,2,3],[0,2,3],[0,3]]
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>graph = [[1,3],[2],[3],[]]
<strong>输出：</strong>[[0,1,2,3],[0,3]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>结点的数量会在范围&nbsp;<code>[2, 15]</code>&nbsp;内。</li>
	<li>你可以把路径以任意顺序输出，但在路径内的结点的顺序必须保证。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

因为图中不存在环，所以直接用 DFS 或 BFS 遍历即可

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

BFS：

```python
class Solution:
    def allPathsSourceTarget(self, graph: List[List[int]]) -> List[List[int]]:
        n = len(graph)
        q = deque([[0]])
        ans = []
        while q:
            path = q.popleft()
            u = path[-1]
            if u == n - 1:
                ans.append(path)
                continue
            for v in graph[u]:
                q.append(path + [v])
        return ans
```

DFS：

```python
class Solution:
    def allPathsSourceTarget(self, graph: List[List[int]]) -> List[List[int]]:
        ans = []

        def dfs(t):
            if t[-1] == len(graph) - 1:
                ans.append(t.copy())
                return

            for v in graph[t[-1]]:
                t.append(v)
                dfs(t)
                t.pop()

        dfs([0])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

BFS：

```java
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;
        Queue<List<Integer>> queue = new ArrayDeque<>();
        queue.offer(Arrays.asList(0));
        List<List<Integer>> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> path = queue.poll();
            int u = path.get(path.size() - 1);
            if (u == n - 1) {
                ans.add(path);
                continue;
            }
            for (int v : graph[u]) {
                List<Integer> next = new ArrayList<>(path);
                next.add(v);
                queue.offer(next);
            }
        }
        return ans;
    }
}
```

DFS：

```java
class Solution {
    private List<List<Integer>> ans;
    private int[][] graph;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        ans = new ArrayList<>();
        this.graph = graph;
        List<Integer> t = new ArrayList<>();
        t.add(0);
        dfs(t);
        return ans;
    }

    private void dfs(List<Integer> t) {
        int cur = t.get(t.size() - 1);
        if (cur == graph.length - 1) {
            ans.add(new ArrayList<>(t));
            return;
        }
        for (int v : graph[cur]) {
            t.add(v);
            dfs(t);
            t.remove(t.size() - 1);
        }
    }
}
```

### **C++**

DFS：

```cpp
class Solution {
public:
    vector<vector<int>> graph;
    vector<vector<int>> ans;

    vector<vector<int>> allPathsSourceTarget(vector<vector<int>>& graph) {
        this->graph = graph;
        vector<int> path;
        path.push_back(0);
        dfs(0, path);
        return ans;
    }

    void dfs(int i, vector<int> path) {
        if (i == graph.size() - 1)
        {
            ans.push_back(path);
            return;
        }
        for (int j : graph[i])
        {
            path.push_back(j);
            dfs(j, path);
            path.pop_back();
        }
    }
};
```

DFS：

### **Go**

```go
func allPathsSourceTarget(graph [][]int) [][]int {
	var path []int
	path = append(path, 0)
	var ans [][]int

	var dfs func(i int)
	dfs = func(i int) {
		if i == len(graph)-1 {
			ans = append(ans, append([]int(nil), path...))
			return
		}
		for _, j := range graph[i] {
			path = append(path, j)
			dfs(j)
			path = path[:len(path)-1]
		}
	}

	dfs(0)
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} graph
 * @return {number[][]}
 */
var allPathsSourceTarget = function (graph) {
    const ans = [];
    const t = [0];

    const dfs = t => {
        const cur = t[t.length - 1];
        if (cur == graph.length - 1) {
            ans.push([...t]);
            return;
        }
        for (const v of graph[cur]) {
            t.push(v);
            dfs(t);
            t.pop();
        }
    };

    dfs(t);
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
