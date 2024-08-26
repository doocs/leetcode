---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1462.Course%20Schedule%20IV/README_EN.md
rating: 1692
source: Biweekly Contest 27 Q3
tags:
    - Depth-First Search
    - Breadth-First Search
    - Graph
    - Topological Sort
---

<!-- problem:start -->

# [1462. Course Schedule IV](https://leetcode.com/problems/course-schedule-iv)

[中文文档](/solution/1400-1499/1462.Course%20Schedule%20IV/README.md)

## Description

<!-- description:start -->

<p>There are a total of <code>numCourses</code> courses you have to take, labeled from <code>0</code> to <code>numCourses - 1</code>. You are given an array <code>prerequisites</code> where <code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that you <strong>must</strong> take course <code>a<sub>i</sub></code> first if you want to take course <code>b<sub>i</sub></code>.</p>

<ul>
	<li>For example, the pair <code>[0, 1]</code> indicates that you have to take course <code>0</code> before you can take course <code>1</code>.</li>
</ul>

<p>Prerequisites can also be <strong>indirect</strong>. If course <code>a</code> is a prerequisite of course <code>b</code>, and course <code>b</code> is a prerequisite of course <code>c</code>, then course <code>a</code> is a prerequisite of course <code>c</code>.</p>

<p>You are also given an array <code>queries</code> where <code>queries[j] = [u<sub>j</sub>, v<sub>j</sub>]</code>. For the <code>j<sup>th</sup></code> query, you should answer whether course <code>u<sub>j</sub></code> is a prerequisite of course <code>v<sub>j</sub></code> or not.</p>

<p>Return <i>a boolean array </i><code>answer</code><i>, where </i><code>answer[j]</code><i> is the answer to the </i><code>j<sup>th</sup></code><i> query.</i></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1462.Course%20Schedule%20IV/images/courses4-1-graph.jpg" style="width: 222px; height: 62px;" />
<pre>
<strong>Input:</strong> numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
<strong>Output:</strong> [false,true]
<strong>Explanation:</strong> The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
Course 0 is not a prerequisite of course 1, but the opposite is true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
<strong>Output:</strong> [false,false]
<strong>Explanation:</strong> There are no prerequisites, and each course is independent.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1462.Course%20Schedule%20IV/images/courses4-3-graph.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>Input:</strong> numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
<strong>Output:</strong> [true,true]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= numCourses &lt;= 100</code></li>
	<li><code>0 &lt;= prerequisites.length &lt;= (numCourses * (numCourses - 1) / 2)</code></li>
	<li><code>prerequisites[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= numCourses - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>All the pairs <code>[a<sub>i</sub>, b<sub>i</sub>]</code> are <strong>unique</strong>.</li>
	<li>The prerequisites graph has no cycles.</li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= numCourses - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Floyd's Algorithm

We create a 2D array $f$, where $f[i][j]$ indicates whether node $i$ can reach node $j$.

Next, we iterate through the prerequisites array $prerequisites$. For each item $[a, b]$ in it, we set $f[a][b]$ to $true$.

Then, we use Floyd's algorithm to compute the reachability between all pairs of nodes.

Specifically, we use three nested loops: first enumerating the intermediate node $k$, then the starting node $i$, and finally the ending node $j$. For each iteration, if node $i$ can reach node $k$ and node $k$ can reach node $j$, then node $i$ can also reach node $j$, and we set $f[i][j]$ to $true$.

After computing the reachability between all pairs of nodes, for each query $[a, b]$, we can directly return $f[a][b]$.

The time complexity is $O(n^3)$, and the space complexity is $O(n^2)$, where $n$ is the number of nodes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkIfPrerequisite(
        self, n: int, prerequisites: List[List[int]], queries: List[List[int]]
    ) -> List[bool]:
        f = [[False] * n for _ in range(n)]
        for a, b in prerequisites:
            f[a][b] = True
        for k in range(n):
            for i in range(n):
                for j in range(n):
                    if f[i][k] and f[k][j]:
                        f[i][j] = True
        return [f[a][b] for a, b in queries]
```

#### Java

```java
class Solution {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] f = new boolean[n][n];
        for (var p : prerequisites) {
            f[p[0]][p[1]] = true;
        }
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    f[i][j] |= f[i][k] && f[k][j];
                }
            }
        }
        List<Boolean> ans = new ArrayList<>();
        for (var q : queries) {
            ans.add(f[q[0]][q[1]]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<bool> checkIfPrerequisite(int n, vector<vector<int>>& prerequisites, vector<vector<int>>& queries) {
        bool f[n][n];
        memset(f, false, sizeof(f));
        for (auto& p : prerequisites) {
            f[p[0]][p[1]] = true;
        }
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    f[i][j] |= (f[i][k] && f[k][j]);
                }
            }
        }
        vector<bool> ans;
        for (auto& q : queries) {
            ans.push_back(f[q[0]][q[1]]);
        }
        return ans;
    }
};
```

#### Go

```go
func checkIfPrerequisite(n int, prerequisites [][]int, queries [][]int) (ans []bool) {
	f := make([][]bool, n)
	for i := range f {
		f[i] = make([]bool, n)
	}
	for _, p := range prerequisites {
		f[p[0]][p[1]] = true
	}
	for k := 0; k < n; k++ {
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				f[i][j] = f[i][j] || (f[i][k] && f[k][j])
			}
		}
	}
	for _, q := range queries {
		ans = append(ans, f[q[0]][q[1]])
	}
	return
}
```

#### TypeScript

```ts
function checkIfPrerequisite(n: number, prerequisites: number[][], queries: number[][]): boolean[] {
    const f = Array.from({ length: n }, () => Array(n).fill(false));
    prerequisites.forEach(([a, b]) => (f[a][b] = true));
    for (let k = 0; k < n; ++k) {
        for (let i = 0; i < n; ++i) {
            for (let j = 0; j < n; ++j) {
                f[i][j] ||= f[i][k] && f[k][j];
            }
        }
    }
    return queries.map(([a, b]) => f[a][b]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Topological Sorting

Similar to Solution 1, we create a 2D array $f$, where $f[i][j]$ indicates whether node $i$ can reach node $j$. Additionally, we create an adjacency list $g$, where $g[i]$ represents all successor nodes of node $i$, and an array $indeg$, where $indeg[i]$ represents the in-degree of node $i$.

Next, we iterate through the prerequisites array $prerequisites$. For each item $[a, b]$ in it, we update the adjacency list $g$ and the in-degree array $indeg$.

Then, we use topological sorting to compute the reachability between all pairs of nodes.

We define a queue $q$, initially adding all nodes with an in-degree of $0$ to the queue. Then, we continuously perform the following operations: remove the front node $i$ from the queue, then iterate through all nodes $j$ in $g[i]$, setting $f[i][j]$ to $true$. Next, we enumerate node $h$, and if $f[h][i]$ is $true$, we also set $f[h][j]$ to $true$. After this, we decrease the in-degree of $j$ by $1$. If the in-degree of $j$ becomes $0$, we add $j$ to the queue.

After computing the reachability between all pairs of nodes, for each query $[a, b]$, we can directly return $f[a][b]$.

The time complexity is $O(n^3)$, and the space complexity is $O(n^2)$, where $n$ is the number of nodes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkIfPrerequisite(
        self, n: int, prerequisites: List[List[int]], queries: List[List[int]]
    ) -> List[bool]:
        f = [[False] * n for _ in range(n)]
        g = [[] for _ in range(n)]
        indeg = [0] * n
        for a, b in prerequisites:
            g[a].append(b)
            indeg[b] += 1
        q = deque(i for i, x in enumerate(indeg) if x == 0)
        while q:
            i = q.popleft()
            for j in g[i]:
                f[i][j] = True
                for h in range(n):
                    f[h][j] = f[h][j] or f[h][i]
                indeg[j] -= 1
                if indeg[j] == 0:
                    q.append(j)
        return [f[a][b] for a, b in queries]
```

#### Java

```java
class Solution {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] f = new boolean[n][n];
        List<Integer>[] g = new List[n];
        int[] indeg = new int[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var p : prerequisites) {
            g[p[0]].add(p[1]);
            ++indeg[p[1]];
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int i = q.poll();
            for (int j : g[i]) {
                f[i][j] = true;
                for (int h = 0; h < n; ++h) {
                    f[h][j] |= f[h][i];
                }
                if (--indeg[j] == 0) {
                    q.offer(j);
                }
            }
        }
        List<Boolean> ans = new ArrayList<>();
        for (var qry : queries) {
            ans.add(f[qry[0]][qry[1]]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<bool> checkIfPrerequisite(int n, vector<vector<int>>& prerequisites, vector<vector<int>>& queries) {
        bool f[n][n];
        memset(f, false, sizeof(f));
        vector<int> g[n];
        vector<int> indeg(n);
        for (auto& p : prerequisites) {
            g[p[0]].push_back(p[1]);
            ++indeg[p[1]];
        }
        queue<int> q;
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                q.push(i);
            }
        }
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            for (int j : g[i]) {
                f[i][j] = true;
                for (int h = 0; h < n; ++h) {
                    f[h][j] |= f[h][i];
                }
                if (--indeg[j] == 0) {
                    q.push(j);
                }
            }
        }
        vector<bool> ans;
        for (auto& qry : queries) {
            ans.push_back(f[qry[0]][qry[1]]);
        }
        return ans;
    }
};
```

#### Go

```go
func checkIfPrerequisite(n int, prerequisites [][]int, queries [][]int) (ans []bool) {
	f := make([][]bool, n)
	for i := range f {
		f[i] = make([]bool, n)
	}
	g := make([][]int, n)
	indeg := make([]int, n)
	for _, p := range prerequisites {
		a, b := p[0], p[1]
		g[a] = append(g[a], b)
		indeg[b]++
	}
	q := []int{}
	for i, x := range indeg {
		if x == 0 {
			q = append(q, i)
		}
	}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		for _, j := range g[i] {
			f[i][j] = true
			for h := 0; h < n; h++ {
				f[h][j] = f[h][j] || f[h][i]
			}
			indeg[j]--
			if indeg[j] == 0 {
				q = append(q, j)
			}
		}
	}
	for _, q := range queries {
		ans = append(ans, f[q[0]][q[1]])
	}
	return
}
```

#### TypeScript

```ts
function checkIfPrerequisite(n: number, prerequisites: number[][], queries: number[][]): boolean[] {
    const f = Array.from({ length: n }, () => Array(n).fill(false));
    const g: number[][] = Array.from({ length: n }, () => []);
    const indeg: number[] = Array(n).fill(0);
    for (const [a, b] of prerequisites) {
        g[a].push(b);
        ++indeg[b];
    }
    const q: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (indeg[i] === 0) {
            q.push(i);
        }
    }
    while (q.length) {
        const i = q.shift()!;
        for (const j of g[i]) {
            f[i][j] = true;
            for (let h = 0; h < n; ++h) {
                f[h][j] ||= f[h][i];
            }
            if (--indeg[j] === 0) {
                q.push(j);
            }
        }
    }
    return queries.map(([a, b]) => f[a][b]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
