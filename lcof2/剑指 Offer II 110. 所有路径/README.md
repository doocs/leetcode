# [剑指 Offer II 110. 所有路径](https://leetcode.cn/problems/bP4bmD)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个有&nbsp;<code>n</code>&nbsp;个节点的有向无环图，用二维数组&nbsp;<code>graph</code>&nbsp;表示，请找到所有从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n-1</code>&nbsp;的路径并输出（不要求按顺序）。</p>

<p><code>graph</code>&nbsp;的第 <code>i</code> 个数组中的单元都表示有向图中 <code>i</code>&nbsp;号节点所能到达的下一些结点（译者注：有向图是有方向的，即规定了 a&rarr;b 你就不能从 b&rarr;a ），若为空，就是没有下一个节点了。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20110.%20%E6%89%80%E6%9C%89%E8%B7%AF%E5%BE%84/images/all_1.jpg" style="height: 242px; width: 242px;" /></p>

<pre>
<strong>输入：</strong>graph = [[1,2],[3],[3],[]]
<strong>输出：</strong>[[0,1,3],[0,2,3]]
<strong>解释：</strong>有两条路径 0 -&gt; 1 -&gt; 3 和 0 -&gt; 2 -&gt; 3
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20110.%20%E6%89%80%E6%9C%89%E8%B7%AF%E5%BE%84/images/all_2.jpg" style="height: 301px; width: 423px;" /></p>

<pre>
<strong>输入：</strong>graph = [[4,3,1],[3,2,4],[3],[4],[]]
<strong>输出：</strong>[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>graph = [[1],[]]
<strong>输出：</strong>[[0,1]]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>graph = [[1,2,3],[2],[3],[]]
<strong>输出：</strong>[[0,1,2,3],[0,2,3],[0,3]]
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>graph = [[1,3],[2],[3],[]]
<strong>输出：</strong>[[0,1,2,3],[0,3]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == graph.length</code></li>
	<li><code>2 &lt;= n &lt;= 15</code></li>
	<li><code>0 &lt;= graph[i][j] &lt; n</code></li>
	<li><code>graph[i][j] != i</code>&nbsp;</li>
	<li>保证输入为有向无环图 <code>(GAD)</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 797&nbsp;题相同：<a href="https://leetcode.cn/problems/all-paths-from-source-to-target/">https://leetcode.cn/problems/all-paths-from-source-to-target/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def allPathsSourceTarget(self, graph: List[List[int]]) -> List[List[int]]:
        ans = []

        def dfs(i, path):
            if i == len(graph) - 1:
                ans.append(path.copy())
                return
            for j in graph[i]:
                path.append(j)
                dfs(j, path)
                path.pop(-1)

        dfs(0, [0])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<List<Integer>> ans;
    private int[][] graph;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        ans = new ArrayList<>();
        this.graph = graph;
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(0, path);
        return ans;
    }

    private void dfs(int i, List<Integer> path) {
        if (i == graph.length - 1) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j : graph[i]) {
            path.add(j);
            dfs(j, path);
            path.remove(path.size() - 1);
        }
    }
}
```

### **C++**

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
        if (i == graph.size() - 1) {
            ans.push_back(path);
            return;
        }
        for (int j : graph[i]) {
            path.push_back(j);
            dfs(j, path);
            path.pop_back();
        }
    }
};
```

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

### **...**

```

```

<!-- tabs:end -->
