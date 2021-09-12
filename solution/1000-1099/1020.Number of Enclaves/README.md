# [1020. 飞地的数量](https://leetcode-cn.com/problems/number-of-enclaves)

[English Version](/solution/1000-1099/1020.Number%20of%20Enclaves/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出一个二维数组&nbsp;<code>A</code>，每个单元格为 0（代表海）或 1（代表陆地）。</p>

<p>移动是指在陆地上从一个地方走到另一个地方（朝四个方向之一）或离开网格的边界。</p>

<p>返回网格中<strong>无法</strong>在任意次数的移动中离开网格边界的陆地单元格的数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
<strong>输出：</strong>3
<strong>解释： </strong>
有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
<strong>输出：</strong>0
<strong>解释：</strong>
所有 1 都在边界上或可以到达边界。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 500</code></li>
	<li><code>1 &lt;= A[i].length &lt;= 500</code></li>
	<li><code>0 &lt;= A[i][j] &lt;= 1</code></li>
	<li>所有行的大小都相同</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

并查集。

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
    def numEnclaves(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        p = list(range(m * n + 1))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    if i == 0 or i == m - 1 or j == 0 or j == n - 1:
                        p[find(i * n + j)] = find(m * n)
                    else:
                        for x, y in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                            if grid[i + x][j + y] == 1:
                                p[find(i * n + j)] = find((i + x) * n + j + y)

        res = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1 and find(i * n + j) != find(m * n):
                    res += 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;
    private int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        p = new int[m * n + 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        p[find(i * n + j)] = find(m * n);
                    } else {
                        for (int[] e : dirs) {
                            if (grid[i + e[0]][j + e[1]] == 1) {
                                p[find(i * n + j)] = find((i + e[0]) * n + j + e[1]);
                            }
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1 && find(i * n + j) != find(m * n)) {
                    ++res;
                }
            }
        }
        return res;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;
    int dirs[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    int numEnclaves(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        p.resize(m * n + 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (grid[i][j] == 1)
                {
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) p[find(i * n + j)] = find(m * n);
                    else
                    {
                        for (auto e : dirs)
                        {
                            if (grid[i + e[0]][j + e[1]] == 1) p[find(i * n + j)] = find((i + e[0]) * n + j + e[1]);
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (grid[i][j] == 1 && find(i * n + j) != find(m * n)) ++res;
            }
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
var p []int

func numEnclaves(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	p = make([]int, m*n+1)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				if i == 0 || i == m-1 || j == 0 || j == n-1 {
					p[find(i*n+j)] = find(m * n)
				} else {
					for _, e := range dirs {
						if grid[i+e[0]][j+e[1]] == 1 {
							p[find(i*n+j)] = find((i+e[0])*n + j + e[1])
						}
					}
				}
			}
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 && find(i*n+j) != find(m*n) {
				res++
			}
		}
	}
	return res
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}
```

### **...**

```

```

<!-- tabs:end -->
