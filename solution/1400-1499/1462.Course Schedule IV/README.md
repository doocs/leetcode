---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1462.Course%20Schedule%20IV/README.md
rating: 1692
source: 第 27 场双周赛 Q3
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 拓扑排序
---

<!-- problem:start -->

# [1462. 课程表 IV](https://leetcode.cn/problems/course-schedule-iv)

[English Version](/solution/1400-1499/1462.Course%20Schedule%20IV/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你总共需要上<meta charset="UTF-8" />&nbsp;<code>numCourses</code>&nbsp;门课，课程编号依次为 <code>0</code>&nbsp;到&nbsp;<code>numCourses-1</code>&nbsp;。你会得到一个数组&nbsp;<code>prerequisite</code> ，其中<meta charset="UTF-8" />&nbsp;<code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示如果你想选<meta charset="UTF-8" />&nbsp;<code>b<sub>i</sub></code> 课程，你<strong> 必须</strong> 先选<meta charset="UTF-8" />&nbsp;<code>a<sub>i</sub></code>&nbsp;课程。</p>

<ul>
	<li>有的课会有直接的先修课程，比如如果想上课程 <code>1</code>&nbsp;，你必须先上课程 <code>0</code>&nbsp;，那么会以 <code>[0,1]</code>&nbsp;数对的形式给出先修课程数对。</li>
</ul>

<p>先决条件也可以是 <strong>间接</strong> 的。如果课程 <code>a</code> 是课程 <code>b</code> 的先决条件，课程 <code>b</code> 是课程 <code>c</code> 的先决条件，那么课程 <code>a</code> 就是课程 <code>c</code> 的先决条件。</p>

<p>你也得到一个数组<meta charset="UTF-8" />&nbsp;<code>queries</code>&nbsp;，其中<meta charset="UTF-8" />&nbsp;<code>queries[j] = [u<sub>j</sub>, v<sub>j</sub>]</code>。对于第 <code>j</code> 个查询，您应该回答课程<meta charset="UTF-8" />&nbsp;<code>u<sub>j</sub></code>&nbsp;是否是课程<meta charset="UTF-8" />&nbsp;<code>v<sub>j</sub></code>&nbsp;的先决条件。</p>

<p>返回一个布尔数组 <code>answer</code> ，其中 <code>answer[j]</code> 是第 <code>j</code> 个查询的答案。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1462.Course%20Schedule%20IV/images/courses4-1-graph.jpg" /></p>

<pre>
<strong>输入：</strong>numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
<strong>输出：</strong>[false,true]
<strong>解释：</strong>[1, 0] 数对表示在你上课程 0 之前必须先上课程 1。
课程 0 不是课程 1 的先修课程，但课程 1 是课程 0 的先修课程。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
<strong>输出：</strong>[false,false]
<strong>解释：</strong>没有先修课程对，所以每门课程之间是独立的。
</pre>

<p><strong class="example">示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1462.Course%20Schedule%20IV/images/courses4-3-graph.jpg" /></p>

<pre>
<strong>输入：</strong>numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
<strong>输出：</strong>[true,true]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>2 &lt;= numCourses &lt;= 100</code></li>
	<li><code>0 &lt;= prerequisites.length &lt;= (numCourses * (numCourses - 1) / 2)</code></li>
	<li><code>prerequisites[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub>&nbsp;&lt;= numCourses - 1</code></li>
	<li><code>a<sub>i</sub>&nbsp;!= b<sub>i</sub></code></li>
	<li>每一对<meta charset="UTF-8" />&nbsp;<code>[a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;都 <strong>不同</strong></li>
	<li>先修课程图中没有环。</li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub>&nbsp;&lt;= numCourses - 1</code></li>
	<li><code>u<sub>i</sub>&nbsp;!= v<sub>i</sub></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：Floyd 算法

我们创建一个二维数组 $f$，其中 $f[i][j]$ 表示节点 $i$ 到节点 $j$ 是否可达。

接下来，我们遍历先修课程数组 $prerequisites$，对于其中的每一项 $[a, b]$，我们将 $f[a][b]$ 设为 $true$。

然后，我们使用 Floyd 算法计算出所有节点对之间的可达性。

具体地，我们使用三重循环，首先枚举中间点 $k$，接下来枚举起点 $i$，最后枚举终点 $j$。对于每一次循环，如果节点 $i$ 到节点 $k$ 可达，且节点 $k$ 到节点 $j$ 可达，那么节点 $i$ 到节点 $j$ 也是可达的，我们将 $f[i][j]$ 设为 $true$。

在计算完所有节点对之间的可达性之后，对于每一个查询 $[a, b]$，我们直接返回 $f[a][b]$ 即可。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 为节点数。

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

### 方法二：拓扑排序

与方法一类似，我们创建一个二维数组 $f$，其中 $f[i][j]$ 表示节点 $i$ 到节点 $j$ 是否可达。另外，我们创建一个邻接表 $g$，其中 $g[i]$ 表示节点 $i$ 的所有后继节点；创建一个数组 $indeg$，其中 $indeg[i]$ 表示节点 $i$ 的入度。

接下来，我们遍历先修课程数组 $prerequisites$，对于其中的每一项 $[a, b]$，我们更新邻接表 $g$ 和入度数组 $indeg$。

然后，我们使用拓扑排序计算出所有节点对之间的可达性。

定义一个队列 $q$，初始时将所有入度为 $0$ 的节点加入队列中。随后不断进行以下操作：取出队首节点 $i$，然后遍历 $g[i]$ 中的所有节点 $j$，将 $f[i][j]$ 设为 $true$。接下来，我们枚举节点 $h$，如果 $f[h][i]$ 为 $true$，那么我们也将 $f[h][j]$ 设为 $true$。在这之后，我们将 $j$ 的入度减少 $1$。如果此时 $j$ 的入度为 $0$，那么我们就将 $j$ 加入队列中。

在计算完所有节点对之间的可达性之后，对于每一个查询 $[a, b]$，我们直接返回 $f[a][b]$ 即可。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 为节点数。

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
