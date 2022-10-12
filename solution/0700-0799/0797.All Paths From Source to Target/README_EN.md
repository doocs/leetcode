# [797. All Paths From Source to Target](https://leetcode.com/problems/all-paths-from-source-to-target)

[中文文档](/solution/0700-0799/0797.All%20Paths%20From%20Source%20to%20Target/README.md)

## Description

<p>Given a directed acyclic graph (<strong>DAG</strong>) of <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>, find all possible paths from node <code>0</code> to node <code>n - 1</code> and return them in <strong>any order</strong>.</p>

<p>The graph is given as follows: <code>graph[i]</code> is a list of all nodes you can visit from node <code>i</code> (i.e., there is a directed edge from node <code>i</code> to node <code>graph[i][j]</code>).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0797.All%20Paths%20From%20Source%20to%20Target/images/all_1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> graph = [[1,2],[3],[3],[]]
<strong>Output:</strong> [[0,1,3],[0,2,3]]
<strong>Explanation:</strong> There are two paths: 0 -&gt; 1 -&gt; 3 and 0 -&gt; 2 -&gt; 3.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0797.All%20Paths%20From%20Source%20to%20Target/images/all_2.jpg" style="width: 423px; height: 301px;" />
<pre>
<strong>Input:</strong> graph = [[4,3,1],[3,2,4],[3],[4],[]]
<strong>Output:</strong> [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == graph.length</code></li>
	<li><code>2 &lt;= n &lt;= 15</code></li>
	<li><code>0 &lt;= graph[i][j] &lt; n</code></li>
	<li><code>graph[i][j] != i</code> (i.e., there will be no self-loops).</li>
	<li>All the elements of <code>graph[i]</code> are <strong>unique</strong>.</li>
	<li>The input graph is <strong>guaranteed</strong> to be a <strong>DAG</strong>.</li>
</ul>

## Solutions

Since there is no ring in the graph, you can simply use DFS or BFS to traverse it.

<!-- tabs:start -->

### **Python3**

BFS.

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

DFS:

```python
class Solution:
    def allPathsSourceTarget(self, graph: List[List[int]]) -> List[List[int]]:
        def dfs(t):
            if t[-1] == len(graph) - 1:
                ans.append(t[:])
                return
            for v in graph[t[-1]]:
                t.append(v)
                dfs(t)
                t.pop()

        ans = []
        dfs([0])
        return ans
```

### **Java**

BFS:

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

DFS:

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

DFS:

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

DFS:

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

### **Rust**

```rust
impl Solution {
    fn dfs(i: usize, path: &mut Vec<i32>, res: &mut Vec<Vec<i32>>, graph: &Vec<Vec<i32>>) {
        path.push(i as i32);
        if i == graph.len() - 1 {
            res.push(path.clone());
        }
        for j in graph[i].iter() {
            Self::dfs(*j as usize, path, res, graph)
        }
        path.pop();
    }

    pub fn all_paths_source_target(graph: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let mut res = Vec::new();
        Self::dfs(0, &mut vec![], &mut res, &graph);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
