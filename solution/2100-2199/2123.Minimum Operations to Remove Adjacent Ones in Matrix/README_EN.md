# [2123. Minimum Operations to Remove Adjacent Ones in Matrix 🔒](https://leetcode.com/problems/minimum-operations-to-remove-adjacent-ones-in-matrix)

[中文文档](/solution/2100-2199/2123.Minimum%20Operations%20to%20Remove%20Adjacent%20Ones%20in%20Matrix/README.md)

<!-- tags:Graph,Array,Matrix -->

## Description

<p>You are given a <strong>0-indexed</strong> binary matrix <code>grid</code>. In one operation, you can flip any <code>1</code> in <code>grid</code> to be <code>0</code>.</p>

<p>A binary matrix is <strong>well-isolated</strong> if there is no <code>1</code> in the matrix that is <strong>4-directionally connected</strong> (i.e., horizontal and vertical) to another <code>1</code>.</p>

<p>Return <em>the minimum number of operations to make </em><code>grid</code><em> <strong>well-isolated</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2123.Minimum%20Operations%20to%20Remove%20Adjacent%20Ones%20in%20Matrix/images/image-20211223181501-1.png" style="width: 644px; height: 250px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,0],[0,1,1],[1,1,1]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Use 3 operations to change grid[0][1], grid[1][2], and grid[2][1] to 0.
After, no more 1&#39;s are 4-directionally connected and grid is well-isolated.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2123.Minimum%20Operations%20to%20Remove%20Adjacent%20Ones%20in%20Matrix/images/image-20211223181518-2.png" style="height: 250px; width: 255px;" />
<pre>
<strong>Input:</strong> grid = [[0,0,0],[0,0,0],[0,0,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no 1&#39;s in grid and it is well-isolated.
No operations were done so return 0.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2123.Minimum%20Operations%20to%20Remove%20Adjacent%20Ones%20in%20Matrix/images/image-20211223181817-3.png" style="width: 165px; height: 167px;" />
<pre>
<strong>Input:</strong> grid = [[0,1],[1,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> None of the 1&#39;s are 4-directionally connected and grid is well-isolated.
No operations were done so return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def minimumOperations(self, grid: List[List[int]]) -> int:
        def find(i: int) -> int:
            for j in g[i]:
                if j not in vis:
                    vis.add(j)
                    if match[j] == -1 or find(match[j]):
                        match[j] = i
                        return 1
            return 0

        g = defaultdict(list)
        m, n = len(grid), len(grid[0])
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if (i + j) % 2 and v:
                    x = i * n + j
                    if i < m - 1 and grid[i + 1][j]:
                        g[x].append(x + n)
                    if i and grid[i - 1][j]:
                        g[x].append(x - n)
                    if j < n - 1 and grid[i][j + 1]:
                        g[x].append(x + 1)
                    if j and grid[i][j - 1]:
                        g[x].append(x - 1)

        match = [-1] * (m * n)
        ans = 0
        for i in g.keys():
            vis = set()
            ans += find(i)
        return ans
```

```java
class Solution {
    private Map<Integer, List<Integer>> g = new HashMap<>();
    private Set<Integer> vis = new HashSet<>();
    private int[] match;

    public int minimumOperations(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i + j) % 2 == 1 && grid[i][j] == 1) {
                    int x = i * n + j;
                    if (i < m - 1 && grid[i + 1][j] == 1) {
                        g.computeIfAbsent(x, z -> new ArrayList<>()).add(x + n);
                    }
                    if (i > 0 && grid[i - 1][j] == 1) {
                        g.computeIfAbsent(x, z -> new ArrayList<>()).add(x - n);
                    }
                    if (j < n - 1 && grid[i][j + 1] == 1) {
                        g.computeIfAbsent(x, z -> new ArrayList<>()).add(x + 1);
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        g.computeIfAbsent(x, z -> new ArrayList<>()).add(x - 1);
                    }
                }
            }
        }
        match = new int[m * n];
        Arrays.fill(match, -1);
        int ans = 0;
        for (int i : g.keySet()) {
            ans += find(i);
            vis.clear();
        }
        return ans;
    }

    private int find(int i) {
        for (int j : g.get(i)) {
            if (vis.add(j)) {
                if (match[j] == -1 || find(match[j]) == 1) {
                    match[j] = i;
                    return 1;
                }
            }
        }
        return 0;
    }
}
```

```cpp
class Solution {
public:
    int minimumOperations(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<int> match(m * n, -1);
        unordered_set<int> vis;
        unordered_map<int, vector<int>> g;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i + j) % 2 && grid[i][j]) {
                    int x = i * n + j;
                    if (i < m - 1 && grid[i + 1][j]) {
                        g[x].push_back(x + n);
                    }
                    if (i && grid[i - 1][j]) {
                        g[x].push_back(x - n);
                    }
                    if (j < n - 1 && grid[i][j + 1]) {
                        g[x].push_back(x + 1);
                    }
                    if (j && grid[i][j - 1]) {
                        g[x].push_back(x - 1);
                    }
                }
            }
        }
        int ans = 0;
        function<int(int)> find = [&](int i) -> int {
            for (int& j : g[i]) {
                if (!vis.count(j)) {
                    vis.insert(j);
                    if (match[j] == -1 || find(match[j])) {
                        match[j] = i;
                        return 1;
                    }
                }
            }
            return 0;
        };
        for (auto& [i, _] : g) {
            ans += find(i);
            vis.clear();
        }
        return ans;
    }
};
```

```go
func minimumOperations(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	vis := map[int]bool{}
	match := make([]int, m*n)
	for i := range match {
		match[i] = -1
	}
	g := map[int][]int{}
	for i, row := range grid {
		for j, v := range row {
			if (i+j)&1 == 1 && v == 1 {
				x := i*n + j
				if i < m-1 && grid[i+1][j] == 1 {
					g[x] = append(g[x], x+n)
				}
				if i > 0 && grid[i-1][j] == 1 {
					g[x] = append(g[x], x-n)
				}
				if j < n-1 && grid[i][j+1] == 1 {
					g[x] = append(g[x], x+1)
				}
				if j > 0 && grid[i][j-1] == 1 {
					g[x] = append(g[x], x-1)
				}
			}
		}
	}
	var find func(int) int
	find = func(i int) int {
		for _, j := range g[i] {
			if !vis[j] {
				vis[j] = true
				if match[j] == -1 || find(match[j]) == 1 {
					match[j] = i
					return 1
				}
			}
		}
		return 0
	}
	for i := range g {
		ans += find(i)
		vis = map[int]bool{}
	}
	return
}
```

```ts
function minimumOperations(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const match: number[] = Array(m * n).fill(-1);
    const vis: Set<number> = new Set();
    const g: Map<number, number[]> = new Map();
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if ((i + j) % 2 && grid[i][j]) {
                const x = i * n + j;
                g.set(x, []);
                if (i < m - 1 && grid[i + 1][j]) {
                    g.get(x)!.push(x + n);
                }
                if (i && grid[i - 1][j]) {
                    g.get(x)!.push(x - n);
                }
                if (j < n - 1 && grid[i][j + 1]) {
                    g.get(x)!.push(x + 1);
                }
                if (j && grid[i][j - 1]) {
                    g.get(x)!.push(x - 1);
                }
            }
        }
    }
    const find = (i: number): number => {
        for (const j of g.get(i)!) {
            if (!vis.has(j)) {
                vis.add(j);
                if (match[j] === -1 || find(match[j])) {
                    match[j] = i;
                    return 1;
                }
            }
        }
        return 0;
    };
    let ans = 0;
    for (const i of g.keys()) {
        ans += find(i);
        vis.clear();
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
