---
comment: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/04.01.Route%20Between%20Nodes/README.md
---

# [面试题 04.01. 节点间通路](https://leetcode.cn/problems/route-between-nodes-lcci)

[English Version](/lcci/04.01.Route%20Between%20Nodes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。</p>

<p><strong>示例1:</strong></p>

<pre><strong> 输入</strong>：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
<strong> 输出</strong>：true
</pre>

<p><strong>示例2:</strong></p>

<pre><strong> 输入</strong>：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]], start = 0, target = 4
<strong> 输出</strong> true
</pre>

<p><strong>提示：</strong></p>

<ol>
	<li>节点数量n在[0, 1e5]范围内。</li>
	<li>节点编号大于等于 0 小于 n。</li>
	<li>图中可能存在自环和平行边。</li>
</ol>

## 解法

### 方法一：DFS

我们先根据给定的图构建一个邻接表 $g$，其中 $g[i]$ 表示节点 $i$ 的所有邻居节点，用一个哈希表或数组 $vis$ 记录访问过的节点，然后从节点 $start$ 开始深度优先搜索，如果搜索到节点 $target$，则返回 `true`，否则返回 `false`。

深度优先搜索的过程如下：

1. 如果当前节点 $i$ 等于目标节点 $target$，返回 `true`；
2. 如果当前节点 $i$ 已经访问过，返回 `false`；
3. 否则，将当前节点 $i$ 标记为已访问，然后遍历节点 $i$ 的所有邻居节点 $j$，递归地搜索节点 $j$。

时间复杂度 $(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是节点数量和边数量。

<!-- tabs:start -->

```python
class Solution:
    def findWhetherExistsPath(
        self, n: int, graph: List[List[int]], start: int, target: int
    ) -> bool:
        def dfs(i: int):
            if i == target:
                return True
            if i in vis:
                return False
            vis.add(i)
            return any(dfs(j) for j in g[i])

        g = [[] for _ in range(n)]
        for a, b in graph:
            g[a].append(b)
        vis = set()
        return dfs(start)
```

```java
class Solution {
    private List<Integer>[] g;
    private boolean[] vis;
    private int target;

    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        vis = new boolean[n];
        g = new List[n];
        this.target = target;
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : graph) {
            g[e[0]].add(e[1]);
        }
        return dfs(start);
    }

    private boolean dfs(int i) {
        if (i == target) {
            return true;
        }
        if (vis[i]) {
            return false;
        }
        vis[i] = true;
        for (int j : g[i]) {
            if (dfs(j)) {
                return true;
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    bool findWhetherExistsPath(int n, vector<vector<int>>& graph, int start, int target) {
        vector<int> g[n];
        vector<bool> vis(n);
        for (auto& e : graph) {
            g[e[0]].push_back(e[1]);
        }
        function<bool(int)> dfs = [&](int i) {
            if (i == target) {
                return true;
            }
            if (vis[i]) {
                return false;
            }
            vis[i] = true;
            for (int j : g[i]) {
                if (dfs(j)) {
                    return true;
                }
            }
            return false;
        };
        return dfs(start);
    }
};
```

```go
func findWhetherExistsPath(n int, graph [][]int, start int, target int) bool {
	g := make([][]int, n)
	vis := make([]bool, n)
	for _, e := range graph {
		g[e[0]] = append(g[e[0]], e[1])
	}
	var dfs func(int) bool
	dfs = func(i int) bool {
		if i == target {
			return true
		}
		if vis[i] {
			return false
		}
		vis[i] = true
		for _, j := range g[i] {
			if dfs(j) {
				return true
			}
		}
		return false
	}
	return dfs(start)
}
```

```ts
function findWhetherExistsPath(
    n: number,
    graph: number[][],
    start: number,
    target: number,
): boolean {
    const g: number[][] = Array.from({ length: n }, () => []);
    const vis: boolean[] = Array.from({ length: n }, () => false);
    for (const [a, b] of graph) {
        g[a].push(b);
    }
    const dfs = (i: number): boolean => {
        if (i === target) {
            return true;
        }
        if (vis[i]) {
            return false;
        }
        vis[i] = true;
        return g[i].some(dfs);
    };
    return dfs(start);
}
```

```swift
class Solution {
    private var g: [[Int]]!
    private var vis: [Bool]!
    private var target: Int!

    func findWhetherExistsPath(_ n: Int, _ graph: [[Int]], _ start: Int, _ target: Int) -> Bool {
        vis = [Bool](repeating: false, count: n)
        g = [[Int]](repeating: [], count: n)
        self.target = target
        for e in graph {
            g[e[0]].append(e[1])
        }
        return dfs(start)
    }

    private func dfs(_ i: Int) -> Bool {
        if i == target {
            return true
        }
        if vis[i] {
            return false
        }
        vis[i] = true
        for j in g[i] {
            if dfs(j) {
                return true
            }
        }
        return false
    }
}
```

<!-- tabs:end -->

### 方法二：BFS

与方法一类似，我们先根据给定的图构建一个邻接表 $g$，其中 $g[i]$ 表示节点 $i$ 的所有邻居节点，用一个哈希表或数组 $vis$ 记录访问过的节点，然后从节点 $start$ 开始广度优先搜索，如果搜索到节点 $target$，则返回 `true`，否则返回 `false`。

时间复杂度 $(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是节点数量和边数量。

<!-- tabs:start -->

```python
class Solution:
    def findWhetherExistsPath(
        self, n: int, graph: List[List[int]], start: int, target: int
    ) -> bool:
        g = [[] for _ in range(n)]
        for a, b in graph:
            g[a].append(b)
        vis = {start}
        q = deque([start])
        while q:
            i = q.popleft()
            if i == target:
                return True
            for j in g[i]:
                if j not in vis:
                    vis.add(j)
                    q.append(j)
        return False
```

```java
class Solution {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        boolean[] vis = new boolean[n];
        for (int[] e : graph) {
            g[e[0]].add(e[1]);
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        vis[start] = true;
        while (!q.isEmpty()) {
            int i = q.poll();
            if (i == target) {
                return true;
            }
            for (int j : g[i]) {
                if (!vis[j]) {
                    q.offer(j);
                    vis[j] = true;
                }
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    bool findWhetherExistsPath(int n, vector<vector<int>>& graph, int start, int target) {
        vector<int> g[n];
        vector<bool> vis(n);
        for (auto& e : graph) {
            g[e[0]].push_back(e[1]);
        }
        queue<int> q{{start}};
        vis[start] = true;
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            if (i == target) {
                return true;
            }
            for (int j : g[i]) {
                if (!vis[j]) {
                    q.push(j);
                    vis[j] = true;
                }
            }
        }
        return false;
    }
};
```

```go
func findWhetherExistsPath(n int, graph [][]int, start int, target int) bool {
	g := make([][]int, n)
	vis := make([]bool, n)
	for _, e := range graph {
		g[e[0]] = append(g[e[0]], e[1])
	}
	q := []int{start}
	vis[start] = true
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		if i == target {
			return true
		}
		for _, j := range g[i] {
			if !vis[j] {
				vis[j] = true
				q = append(q, j)
			}
		}
	}
	return false
}
```

```ts
function findWhetherExistsPath(
    n: number,
    graph: number[][],
    start: number,
    target: number,
): boolean {
    const g: number[][] = Array.from({ length: n }, () => []);
    const vis: boolean[] = Array.from({ length: n }, () => false);
    for (const [a, b] of graph) {
        g[a].push(b);
    }
    const q: number[] = [start];
    vis[start] = true;
    while (q.length > 0) {
        const i = q.pop()!;
        if (i === target) {
            return true;
        }
        for (const j of g[i]) {
            if (!vis[j]) {
                vis[j] = true;
                q.push(j);
            }
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- end -->
