# [1548. The Most Similar Path in a Graph 🔒](https://leetcode.com/problems/the-most-similar-path-in-a-graph)

[中文文档](/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/README.md)

<!-- tags:Graph,Dynamic Programming -->

## Description

<p>We have <code>n</code> cities and <code>m</code> bi-directional <code>roads</code> where <code>roads[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> connects city <code>a<sub>i</sub></code> with city <code>b<sub>i</sub></code>. Each city has a name consisting of exactly three upper-case English letters given in the string array <code>names</code>. Starting at any city <code>x</code>, you can reach any city <code>y</code> where <code>y != x</code> (i.e., the cities and the roads are forming an undirected connected graph).</p>

<p>You will be given a string array <code>targetPath</code>. You should find a path in the graph of the <strong>same length</strong> and with the <strong>minimum edit distance</strong> to <code>targetPath</code>.</p>

<p>You need to return <em>the order of the nodes in the path with the minimum edit distance</em>. The&nbsp;path should be of the same length of <code>targetPath</code> and should be valid (i.e., there should be a direct road between <code>ans[i]</code> and <code>ans[i + 1]</code>). If there are multiple answers return any one of them.</p>

<p>The <strong>edit distance</strong> is defined as follows:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/images/edit.jpg" style="width: 403px; height: 273px;" />
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/images/e1.jpg" style="width: 213px; height: 300px;" />
<pre>
<strong>Input:</strong> n = 5, roads = [[0,2],[0,3],[1,2],[1,3],[1,4],[2,4]], names = [&quot;ATL&quot;,&quot;PEK&quot;,&quot;LAX&quot;,&quot;DXB&quot;,&quot;HND&quot;], targetPath = [&quot;ATL&quot;,&quot;DXB&quot;,&quot;HND&quot;,&quot;LAX&quot;]
<strong>Output:</strong> [0,2,4,2]
<strong>Explanation:</strong> [0,2,4,2], [0,3,0,2] and [0,3,1,2] are accepted answers.
[0,2,4,2] is equivalent to [&quot;ATL&quot;,&quot;LAX&quot;,&quot;HND&quot;,&quot;LAX&quot;] which has edit distance = 1 with targetPath.
[0,3,0,2] is equivalent to [&quot;ATL&quot;,&quot;DXB&quot;,&quot;ATL&quot;,&quot;LAX&quot;] which has edit distance = 1 with targetPath.
[0,3,1,2] is equivalent to [&quot;ATL&quot;,&quot;DXB&quot;,&quot;PEK&quot;,&quot;LAX&quot;] which has edit distance = 1 with targetPath.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/images/e2.jpg" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> n = 4, roads = [[1,0],[2,0],[3,0],[2,1],[3,1],[3,2]], names = [&quot;ATL&quot;,&quot;PEK&quot;,&quot;LAX&quot;,&quot;DXB&quot;], targetPath = [&quot;ABC&quot;,&quot;DEF&quot;,&quot;GHI&quot;,&quot;JKL&quot;,&quot;MNO&quot;,&quot;PQR&quot;,&quot;STU&quot;,&quot;VWX&quot;]
<strong>Output:</strong> [0,1,0,1,0,1,0,1]
<strong>Explanation:</strong> Any path in this graph has edit distance = 8 with targetPath.
</pre>

<p><strong class="example">Example 3:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/images/e3.jpg" style="width: 600px; height: 106px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 6, roads = [[0,1],[1,2],[2,3],[3,4],[4,5]], names = [&quot;ATL&quot;,&quot;PEK&quot;,&quot;LAX&quot;,&quot;ATL&quot;,&quot;DXB&quot;,&quot;HND&quot;], targetPath = [&quot;ATL&quot;,&quot;DXB&quot;,&quot;HND&quot;,&quot;DXB&quot;,&quot;ATL&quot;,&quot;LAX&quot;,&quot;PEK&quot;]
<strong>Output:</strong> [3,4,5,4,3,2,1]
<strong>Explanation:</strong> [3,4,5,4,3,2,1] is the only path with edit distance = 0 with targetPath.
It&#39;s equivalent to [&quot;ATL&quot;,&quot;DXB&quot;,&quot;HND&quot;,&quot;DXB&quot;,&quot;ATL&quot;,&quot;LAX&quot;,&quot;PEK&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>m == roads.length</code></li>
	<li><code>n - 1 &lt;= m &lt;= (n * (n - 1) / 2)</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>The graph is guaranteed to be <strong>connected</strong> and each pair of nodes may have <strong>at most one</strong> direct road.</li>
	<li><code>names.length == n</code></li>
	<li><code>names[i].length == 3</code></li>
	<li><code>names[i]</code> consists of upper-case English letters.</li>
	<li>There can be two cities with <strong>the same</strong> name.</li>
	<li><code>1 &lt;= targetPath.length &lt;= 100</code></li>
	<li><code>targetPath[i].length == 3</code></li>
	<li><code>targetPath[i]</code> consists of upper-case English letters.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> If each node can be visited only once in the path, What should you change in your solution?</p>

## Solutions

### Solution 1: Dynamic Programming

We first build an adjacency list $g$ based on the given roads, where $g[i]$ represents the list of cities directly connected to city $i$.

Then we define $f[i][j]$ to be the minimum edit distance of the first $i$ cities of $targetPath$ and the first $j$ cities of $names$ when city $i$ of $targetPath$ matches city $j$ of $names$.

Then we can get the following recurrence equation:

$$
f[i][j] = \min_{k \in g[j]} f[i - 1][k] + (targetPath[i] \neq names[j])
$$

In the process of state transition, we record the predecessor city of each state, and finally restore the optimal path from the end to the beginning according to the predecessor city array $pre$.

The time complexity is $O(m \times n^2)$, and the space complexity is $O(m \times n)$. Where $m$ and $n$ are the lengths of $targetPath$ and $names$ respectively.

<!-- tabs:start -->

```python
class Solution:
    def mostSimilar(
        self, n: int, roads: List[List[int]], names: List[str], targetPath: List[str]
    ) -> List[int]:
        g = [[] for _ in range(n)]
        for a, b in roads:
            g[a].append(b)
            g[b].append(a)
        m = len(targetPath)
        f = [[inf] * n for _ in range(m)]
        pre = [[-1] * n for _ in range(m)]
        for j, s in enumerate(names):
            f[0][j] = targetPath[0] != s
        for i in range(1, m):
            for j in range(n):
                for k in g[j]:
                    if (t := f[i - 1][k] + (targetPath[i] != names[j])) < f[i][j]:
                        f[i][j] = t
                        pre[i][j] = k
        k = 0
        mi = inf
        for j in range(n):
            if f[-1][j] < mi:
                mi = f[-1][j]
                k = j
        ans = [0] * m
        for i in range(m - 1, -1, -1):
            ans[i] = k
            k = pre[i][k]
        return ans
```

```java
class Solution {
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] r : roads) {
            int a = r[0], b = r[1];
            g[a].add(b);
            g[b].add(a);
        }
        int m = targetPath.length;
        final int inf = 1 << 30;
        int[][] f = new int[m][n];
        int[][] pre = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(f[i], inf);
            Arrays.fill(pre[i], -1);
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = targetPath[0].equals(names[j]) ? 0 : 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k : g[j]) {
                    int t = f[i - 1][k] + (targetPath[i].equals(names[j]) ? 0 : 1);
                    if (t < f[i][j]) {
                        f[i][j] = t;
                        pre[i][j] = k;
                    }
                }
            }
        }
        int mi = inf, k = 0;
        for (int j = 0; j < n; ++j) {
            if (f[m - 1][j] < mi) {
                mi = f[m - 1][j];
                k = j;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = m - 1; i >= 0; --i) {
            ans.add(k);
            k = pre[i][k];
        }
        Collections.reverse(ans);
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> mostSimilar(int n, vector<vector<int>>& roads, vector<string>& names, vector<string>& targetPath) {
        vector<int> g[n];
        for (auto& r : roads) {
            int a = r[0], b = r[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        int m = targetPath.size();
        int f[m][n];
        int pre[m][n];
        memset(f, 0x3f, sizeof(f));
        memset(pre, -1, sizeof(pre));
        for (int j = 0; j < n; ++j) {
            f[0][j] = targetPath[0] != names[j];
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k : g[j]) {
                    int t = f[i - 1][k] + (targetPath[i] != names[j]);
                    if (t < f[i][j]) {
                        f[i][j] = t;
                        pre[i][j] = k;
                    }
                }
            }
        }
        int k = 0;
        int mi = 1 << 30;
        for (int j = 0; j < n; ++j) {
            if (f[m - 1][j] < mi) {
                mi = f[m - 1][j];
                k = j;
            }
        }
        vector<int> ans(m);
        for (int i = m - 1; ~i; --i) {
            ans[i] = k;
            k = pre[i][k];
        }
        return ans;
    }
};
```

```go
func mostSimilar(n int, roads [][]int, names []string, targetPath []string) []int {
	g := make([][]int, n)
	for _, r := range roads {
		a, b := r[0], r[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	m := len(targetPath)
	const inf = 1 << 30
	f := make([][]int, m)
	pre := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
		pre[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = inf
			pre[i][j] = -1
		}
	}
	for j, s := range names {
		if targetPath[0] != s {
			f[0][j] = 1
		} else {
			f[0][j] = 0
		}
	}
	for i := 1; i < m; i++ {
		for j := 0; j < n; j++ {
			for _, k := range g[j] {
				t := f[i-1][k]
				if targetPath[i] != names[j] {
					t++
				}
				if t < f[i][j] {
					f[i][j] = t
					pre[i][j] = k
				}
			}
		}
	}
	mi, k := inf, 0
	for j := 0; j < n; j++ {
		if f[m-1][j] < mi {
			mi = f[m-1][j]
			k = j
		}
	}
	ans := make([]int, m)
	for i := m - 1; i >= 0; i-- {
		ans[i] = k
		k = pre[i][k]
	}
	return ans
}
```

```ts
function mostSimilar(
    n: number,
    roads: number[][],
    names: string[],
    targetPath: string[],
): number[] {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of roads) {
        g[a].push(b);
        g[b].push(a);
    }
    const m = targetPath.length;
    const f = Array.from({ length: m }, () => Array.from({ length: n }, () => Infinity));
    const pre: number[][] = Array.from({ length: m }, () => Array.from({ length: n }, () => -1));
    for (let j = 0; j < n; ++j) {
        f[0][j] = names[j] === targetPath[0] ? 0 : 1;
    }
    for (let i = 1; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (const k of g[j]) {
                const t = f[i - 1][k] + (names[j] === targetPath[i] ? 0 : 1);
                if (t < f[i][j]) {
                    f[i][j] = t;
                    pre[i][j] = k;
                }
            }
        }
    }
    let k = 0;
    let mi = Infinity;
    for (let j = 0; j < n; ++j) {
        if (f[m - 1][j] < mi) {
            mi = f[m - 1][j];
            k = j;
        }
    }
    const ans: number[] = Array(m).fill(0);
    for (let i = m - 1; ~i; --i) {
        ans[i] = k;
        k = pre[i][k];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
