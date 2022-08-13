# [1391. 检查网格中是否存在有效路径](https://leetcode.cn/problems/check-if-there-is-a-valid-path-in-a-grid)

[English Version](/solution/1300-1399/1391.Check%20if%20There%20is%20a%20Valid%20Path%20in%20a%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <em>m</em> x <em>n</em> 的网格 <code>grid</code>。网格里的每个单元都代表一条街道。<code>grid[i][j]</code> 的街道可以是：</p>

<ul>
	<li><strong>1</strong> 表示连接左单元格和右单元格的街道。</li>
	<li><strong>2</strong> 表示连接上单元格和下单元格的街道。</li>
	<li><strong>3</strong>&nbsp;表示连接左单元格和下单元格的街道。</li>
	<li><strong>4</strong> 表示连接右单元格和下单元格的街道。</li>
	<li><strong>5</strong> 表示连接左单元格和上单元格的街道。</li>
	<li><strong>6</strong> 表示连接右单元格和上单元格的街道。</li>
</ul>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1391.Check%20if%20There%20is%20a%20Valid%20Path%20in%20a%20Grid/images/main.png" style="height: 708px; width: 450px;"></p>

<p>你最开始从左上角的单元格 <code>(0,0)</code> 开始出发，网格中的「有效路径」是指从左上方的单元格 <code>(0,0)</code> 开始、一直到右下方的 <code>(m-1,n-1)</code> 结束的路径。<strong>该路径必须只沿着街道走</strong>。</p>

<p><strong>注意：</strong>你 <strong>不能</strong> 变更街道。</p>

<p>如果网格中存在有效的路径，则返回 <code>true</code>，否则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1391.Check%20if%20There%20is%20a%20Valid%20Path%20in%20a%20Grid/images/e1.png" style="height: 311px; width: 455px;"></p>

<pre><strong>输入：</strong>grid = [[2,4,3],[6,5,2]]
<strong>输出：</strong>true
<strong>解释：</strong>如图所示，你可以从 (0, 0) 开始，访问网格中的所有单元格并到达 (m - 1, n - 1) 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1391.Check%20if%20There%20is%20a%20Valid%20Path%20in%20a%20Grid/images/e2.png" style="height: 293px; width: 455px;"></p>

<pre><strong>输入：</strong>grid = [[1,2,1],[1,2,1]]
<strong>输出：</strong>false
<strong>解释：</strong>如图所示，单元格 (0, 0) 上的街道没有与任何其他单元格上的街道相连，你只会停在 (0, 0) 处。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>grid = [[1,1,2]]
<strong>输出：</strong>false
<strong>解释：</strong>你会停在 (0, 1)，而且无法到达 (0, 2) 。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>grid = [[1,1,1,1,1,1,3]]
<strong>输出：</strong>true
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>grid = [[2],[2],[2],[2],[2],[2],[6]]
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 6</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

并查集。判断每个单元格相邻节点是否连通，是则将其合并。最后判断 `grid[0][0]` 与 `grid[m - 1][n - 1]` 是否连通。

并查集模板：

模板 1——朴素并查集：

```python
# 初始化，p存储每个点的父节点
p = list(range(n))

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        # 路径压缩
        p[x] = find(p[x])
    return p[x]


# 合并a和b所在的两个集合
p[find(a)] = find(b)
```

模板 2——维护 size 的并查集：

```python
# 初始化，p存储每个点的父节点，size只有当节点是祖宗节点时才有意义，表示祖宗节点所在集合中，点的数量
p = list(range(n))
size = [1] * n

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        # 路径压缩
        p[x] = find(p[x])
    return p[x]

# 合并a和b所在的两个集合
if find(a) != find(b):
    size[find(b)] += size[find(a)]
    p[find(a)] = find(b)
```

模板 3——维护到祖宗节点距离的并查集：

```python
# 初始化，p存储每个点的父节点，d[x]存储x到p[x]的距离
p = list(range(n))
d = [0] * n

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        t = find(p[x])
        d[x] += d[p[x]]
        p[x] = t
    return p[x]

# 合并a和b所在的两个集合
p[find(a)] = find(b)
d[find(a)] = distance
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def hasValidPath(self, grid: List[List[int]]) -> bool:
        m, n = len(grid), len(grid[0])
        p = list(range(m * n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def left(i, j):
            if j > 0 and grid[i][j - 1] in (1, 4, 6):
                p[find(i * n + j)] = find(i * n + j - 1)

        def right(i, j):
            if j < n - 1 and grid[i][j + 1] in (1, 3, 5):
                p[find(i * n + j)] = find(i * n + j + 1)

        def up(i, j):
            if i > 0 and grid[i - 1][j] in (2, 3, 4):
                p[find(i * n + j)] = find((i - 1) * n + j)

        def down(i, j):
            if i < m - 1 and grid[i + 1][j] in (2, 5, 6):
                p[find(i * n + j)] = find((i + 1) * n + j)

        for i in range(m):
            for j in range(n):
                e = grid[i][j]
                if e == 1:
                    left(i, j)
                    right(i, j)
                elif e == 2:
                    up(i, j)
                    down(i, j)
                elif e == 3:
                    left(i, j)
                    down(i, j)
                elif e == 4:
                    right(i, j)
                    down(i, j)
                elif e == 5:
                    left(i, j)
                    up(i, j)
                else:
                    right(i, j)
                    up(i, j)
        return find(0) == find(m * n - 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;
    private int[][] grid;
    private int m;
    private int n;

    public boolean hasValidPath(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int e = grid[i][j];
                if (e == 1) {
                    left(i, j);
                    right(i, j);
                } else if (e == 2) {
                    up(i, j);
                    down(i, j);
                } else if (e == 3) {
                    left(i, j);
                    down(i, j);
                } else if (e == 4) {
                    right(i, j);
                    down(i, j);
                } else if (e == 5) {
                    left(i, j);
                    up(i, j);
                } else {
                    right(i, j);
                    up(i, j);
                }
            }
        }
        return find(0) == find(m * n - 1);
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void left(int i, int j) {
        if (j > 0 && (grid[i][j - 1] == 1 || grid[i][j - 1] == 4 || grid[i][j - 1] == 6)) {
            p[find(i * n + j)] = find(i * n + j - 1);
        }
    }

    private void right(int i, int j) {
        if (j < n - 1 && (grid[i][j + 1] == 1 || grid[i][j + 1] == 3 || grid[i][j + 1] == 5)) {
            p[find(i * n + j)] = find(i * n + j + 1);
        }
    }

    private void up(int i, int j) {
        if (i > 0 && (grid[i - 1][j] == 2 || grid[i - 1][j] == 3 || grid[i - 1][j] == 4)) {
            p[find(i * n + j)] = find((i - 1) * n + j);
        }
    }

    private void down(int i, int j) {
        if (i < m - 1 && (grid[i + 1][j] == 2 || grid[i + 1][j] == 5 || grid[i + 1][j] == 6)) {
            p[find(i * n + j)] = find((i + 1) * n + j);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    bool hasValidPath(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        p.resize(m * n);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        auto left = [&](int i, int j) {
            if (j > 0 && (grid[i][j - 1] == 1 || grid[i][j - 1] == 4 || grid[i][j - 1] == 6)) {
                p[find(i * n + j)] = find(i * n + j - 1);
            }
        };
        auto right = [&](int i, int j) {
            if (j < n - 1 && (grid[i][j + 1] == 1 || grid[i][j + 1] == 3 || grid[i][j + 1] == 5)) {
                p[find(i * n + j)] = find(i * n + j + 1);
            }
        };
        auto up = [&](int i, int j) {
            if (i > 0 && (grid[i - 1][j] == 2 || grid[i - 1][j] == 3 || grid[i - 1][j] == 4)) {
                p[find(i * n + j)] = find((i - 1) * n + j);
            }
        };
        auto down = [&](int i, int j) {
            if (i < m - 1 && (grid[i + 1][j] == 2 || grid[i + 1][j] == 5 || grid[i + 1][j] == 6)) {
                p[find(i * n + j)] = find((i + 1) * n + j);
            }
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int e = grid[i][j];
                if (e == 1) {
                    left(i, j);
                    right(i, j);
                } else if (e == 2) {
                    up(i, j);
                    down(i, j);
                } else if (e == 3) {
                    left(i, j);
                    down(i, j);
                } else if (e == 4) {
                    right(i, j);
                    down(i, j);
                } else if (e == 5) {
                    left(i, j);
                    up(i, j);
                } else {
                    right(i, j);
                    up(i, j);
                }
            }
        }
        return find(0) == find(m * n - 1);
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func hasValidPath(grid [][]int) bool {
	m, n := len(grid), len(grid[0])
	p := make([]int, m*n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	left := func(i, j int) {
		if j > 0 && (grid[i][j-1] == 1 || grid[i][j-1] == 4 || grid[i][j-1] == 6) {
			p[find(i*n+j)] = find(i*n + j - 1)
		}
	}
	right := func(i, j int) {
		if j < n-1 && (grid[i][j+1] == 1 || grid[i][j+1] == 3 || grid[i][j+1] == 5) {
			p[find(i*n+j)] = find(i*n + j + 1)
		}
	}
	up := func(i, j int) {
		if i > 0 && (grid[i-1][j] == 2 || grid[i-1][j] == 3 || grid[i-1][j] == 4) {
			p[find(i*n+j)] = find((i-1)*n + j)
		}
	}
	down := func(i, j int) {
		if i < m-1 && (grid[i+1][j] == 2 || grid[i+1][j] == 5 || grid[i+1][j] == 6) {
			p[find(i*n+j)] = find((i+1)*n + j)
		}
	}
	for i, row := range grid {
		for j, e := range row {
			if e == 1 {
				left(i, j)
				right(i, j)
			} else if e == 2 {
				up(i, j)
				down(i, j)
			} else if e == 3 {
				left(i, j)
				down(i, j)
			} else if e == 4 {
				right(i, j)
				down(i, j)
			} else if e == 5 {
				left(i, j)
				up(i, j)
			} else {
				right(i, j)
				up(i, j)
			}
		}
	}
	return find(0) == find(m*n-1)
}
```

### **...**

```

```

<!-- tabs:end -->
