# [2492. Minimum Score of a Path Between Two Cities](https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities)

[中文文档](/solution/2400-2499/2492.Minimum%20Score%20of%20a%20Path%20Between%20Two%20Cities/README.md)

## Description

<p>You are given a positive integer <code>n</code> representing <code>n</code> cities numbered from <code>1</code> to <code>n</code>. You are also given a <strong>2D</strong> array <code>roads</code> where <code>roads[i] = [a<sub>i</sub>, b<sub>i</sub>, distance<sub>i</sub>]</code> indicates that there is a <strong>bidirectional </strong>road between cities <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> with a distance equal to <code>distance<sub>i</sub></code>. The cities graph is not necessarily connected.</p>

<p>The <strong>score</strong> of a path between two cities is defined as the <strong>minimum </strong>distance of a road in this path.</p>

<p>Return <em>the <strong>minimum </strong>possible score of a path between cities </em><code>1</code><em> and </em><code>n</code>.</p>

<p><strong>Note</strong>:</p>

<ul>
	<li>A path is a sequence of roads between two cities.</li>
	<li>It is allowed for a path to contain the same road <strong>multiple</strong> times, and you can visit cities <code>1</code> and <code>n</code> multiple times along the path.</li>
	<li>The test cases are generated such that there is <strong>at least</strong> one path between <code>1</code> and <code>n</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2492.Minimum%20Score%20of%20a%20Path%20Between%20Two%20Cities/images/graph11.png" style="width: 190px; height: 231px;" />
<pre>
<strong>Input:</strong> n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The path from city 1 to 4 with the minimum score is: 1 -&gt; 2 -&gt; 4. The score of this path is min(9,5) = 5.
It can be shown that no other path has less score.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2492.Minimum%20Score%20of%20a%20Path%20Between%20Two%20Cities/images/graph22.png" style="width: 190px; height: 231px;" />
<pre>
<strong>Input:</strong> n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The path from city 1 to 4 with the minimum score is: 1 -&gt; 2 -&gt; 1 -&gt; 3 -&gt; 4. The score of this path is min(2,2,4,7) = 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= roads.length &lt;= 10<sup>5</sup></code></li>
	<li><code>roads[i].length == 3</code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>1 &lt;= distance<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>There are no repeated edges.</li>
	<li>There is at least one path between <code>1</code> and <code>n</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minScore(self, n: int, roads: List[List[int]]) -> int:
        def dfs(i):
            nonlocal ans
            for j, d in g[i]:
                ans = min(ans, d)
                if not vis[j]:
                    vis[j] = True
                    dfs(j)

        g = defaultdict(list)
        for a, b, d in roads:
            g[a].append((b, d))
            g[b].append((a, d))
        vis = [False] * (n + 1)
        ans = inf
        dfs(1)
        return ans
```

```python
class Solution:
    def minScore(self, n: int, roads: List[List[int]]) -> int:
        g = defaultdict(list)
        for a, b, d in roads:
            g[a].append((b, d))
            g[b].append((a, d))
        vis = [False] * (n + 1)
        vis[1] = True
        ans = inf
        q = deque([1])
        while q:
            for _ in range(len(q)):
                i = q.popleft()
                for j, d in g[i]:
                    ans = min(ans, d)
                    if not vis[j]:
                        vis[j] = True
                        q.append(j)
        return ans
```

### **Java**

```java
class Solution {
    private List<int[]>[] g;
    private boolean[] vis;
    private int ans = 1 << 30;

    public int minScore(int n, int[][] roads) {
        g = new List[n];
        vis = new boolean[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : roads) {
            int a = e[0] - 1, b = e[1] - 1, d = e[2];
            g[a].add(new int[] {b, d});
            g[b].add(new int[] {a, d});
        }
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        for (var nxt : g[i]) {
            int j = nxt[0], d = nxt[1];
            ans = Math.min(ans, d);
            if (!vis[j]) {
                vis[j] = true;
                dfs(j);
            }
        }
    }
}
```

```java
class Solution {
    public int minScore(int n, int[][] roads) {
        List<int[]>[] g = new List[n];
        boolean[] vis = new boolean[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : roads) {
            int a = e[0] - 1, b = e[1] - 1, d = e[2];
            g[a].add(new int[] {b, d});
            g[b].add(new int[] {a, d});
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        vis[0] = true;
        int ans = 1 << 30;
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                int i = q.pollFirst();
                for (var nxt : g[i]) {
                    int j = nxt[0], d = nxt[1];
                    ans = Math.min(ans, d);
                    if (!vis[j]) {
                        vis[j] = true;
                        q.offer(j);
                    }
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minScore(int n, vector<vector<int>>& roads) {
        vector<vector<pair<int, int>>> g(n);
        bool vis[n];
        memset(vis, 0, sizeof vis);
        for (auto& e : roads) {
            int a = e[0] - 1, b = e[1] - 1, d = e[2];
            g[a].emplace_back(b, d);
            g[b].emplace_back(a, d);
        }
        int ans = INT_MAX;
        function<void(int)> dfs = [&](int i) {
            for (auto [j, d] : g[i]) {
                ans = min(ans, d);
                if (!vis[j]) {
                    vis[j] = true;
                    dfs(j);
                }
            }
        };
        dfs(0);
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int minScore(int n, vector<vector<int>>& roads) {
        vector<vector<pair<int, int>>> g(n);
        bool vis[n];
        memset(vis, 0, sizeof vis);
        for (auto& e : roads) {
            int a = e[0] - 1, b = e[1] - 1, d = e[2];
            g[a].emplace_back(b, d);
            g[b].emplace_back(a, d);
        }
        int ans = INT_MAX;
        queue<int> q{{0}};
        vis[0] = true;
        while (!q.empty()) {
            for (int k = q.size(); k; --k) {
                int i = q.front();
                q.pop();
                for (auto [j, d] : g[i]) {
                    ans = min(ans, d);
                    if (!vis[j]) {
                        vis[j] = true;
                        q.push(j);
                    }
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minScore(n int, roads [][]int) int {
	type pair struct{ i, v int }
	g := make([][]pair, n)
	for _, e := range roads {
		a, b, d := e[0]-1, e[1]-1, e[2]
		g[a] = append(g[a], pair{b, d})
		g[b] = append(g[b], pair{a, d})
	}
	vis := make([]bool, n)
	ans := 1 << 30
	var dfs func(int)
	dfs = func(i int) {
		for _, nxt := range g[i] {
			j, d := nxt.i, nxt.v
			ans = min(ans, d)
			if !vis[j] {
				vis[j] = true
				dfs(j)
			}
		}
	}
	dfs(0)
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func minScore(n int, roads [][]int) int {
	type pair struct{ i, v int }
	g := make([][]pair, n)
	for _, e := range roads {
		a, b, d := e[0]-1, e[1]-1, e[2]
		g[a] = append(g[a], pair{b, d})
		g[b] = append(g[b], pair{a, d})
	}
	vis := make([]bool, n)
	ans := 1 << 30
	q := []int{0}
	vis[0] = true
	for len(q) > 0 {
		for k := len(q); k > 0; k-- {
			i := q[0]
			q = q[1:]
			for _, nxt := range g[i] {
				j, d := nxt.i, nxt.v
				ans = min(ans, d)
				if !vis[j] {
					vis[j] = true
					q = append(q, j)
				}
			}
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
var minScore = function (n, roads) {
    // 构建点到点的映射表
    const graph = Array.from({ length: n + 1 }, () => new Map());
    for (let [u, v, w] of roads) {
        graph[u].set(v, w);
        graph[v].set(u, w);
    }

    // DFS
    const vis = new Array(n).fill(false);
    let ans = Infinity;
    var dfs = function (u) {
        vis[u] = true;
        for (const [v, w] of graph[u]) {
            ans = Math.min(ans, w);
            if (!vis[v]) dfs(v);
        }
    };
    dfs(1);

    return ans;
};
```

### **TypeScript**

```ts
function minScore(n: number, roads: number[][]): number {
    const vis = new Array(n + 1).fill(false);
    const g = Array.from({ length: n + 1 }, () => []);
    for (const [a, b, v] of roads) {
        g[a].push([b, v]);
        g[b].push([a, v]);
    }
    let ans = Infinity;
    const dfs = (i: number) => {
        if (vis[i]) {
            return;
        }
        vis[i] = true;
        for (const [j, v] of g[i]) {
            ans = Math.min(ans, v);
            dfs(j);
        }
    };
    dfs(1);
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(i: usize, mut ans: i32, g: &Vec<Vec<(usize, i32)>>, vis: &mut Vec<bool>) -> i32 {
        if vis[i] {
            return ans;
        }
        vis[i] = true;
        for (j, v) in g[i].iter() {
            ans = ans.min(*v.min(&Self::dfs(*j, ans, g, vis)));
        }
        ans
    }

    pub fn min_score(n: i32, roads: Vec<Vec<i32>>) -> i32 {
        let n = n as usize;
        let mut vis = vec![false; n + 1];
        let mut g = vec![Vec::new(); n + 1];
        for road in roads.iter() {
            let a = road[0] as usize;
            let b = road[1] as usize;
            let v = road[2];
            g[a].push((b, v));
            g[b].push((a, v));
        }
        Self::dfs(1, i32::MAX, &g, &mut vis)
    }
}
```

### **...**

```

```

<!-- tabs:end -->
