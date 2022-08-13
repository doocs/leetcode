# [827. 最大人工岛](https://leetcode.cn/problems/making-a-large-island)

[English Version](/solution/0800-0899/0827.Making%20A%20Large%20Island/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>n x n</code> 二进制矩阵 <code>grid</code> 。<strong>最多</strong> 只能将一格 <code>0</code> 变成 <code>1</code> 。</p>

<p>返回执行此操作后，<code>grid</code> 中最大的岛屿面积是多少？</p>

<p><strong>岛屿</strong> 由一组上、下、左、右四个方向相连的 <code>1</code> 形成。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>grid = [[1, 0], [0, 1]]
<strong>输出:</strong> 3
<strong>解释:</strong> 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>grid =<strong> </strong>[[1, 1], [1, 0]]
<strong>输出:</strong> 4
<strong>解释:</strong> 将一格0变成1，岛屿的面积扩大为 4。</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入: </strong>grid = [[1, 1], [1, 1]]
<strong>输出:</strong> 4
<strong>解释:</strong> 没有0可以让我们变成1，面积依然为 4。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 <= n <= 500</code></li>
	<li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

并查集。

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
    def largestIsland(self, grid: List[List[int]]) -> int:
        n = len(grid)
        p = list(range(n * n))
        size = [1] * (n * n)

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa != pb:
                size[pb] += size[pa]
                p[pa] = pb

        def check(i, j):
            return 0 <= i < n and 0 <= j < n and grid[i][j] == 1

        for i in range(n):
            for j in range(n):
                if grid[i][j] == 1:
                    for x, y in [[1, 0], [0, 1]]:
                        if check(i + x, j +y):
                            union(i * n + j, (i + x) * n + j + y)

        res = max(size)
        for i in range(n):
            for j in range(n):
                if grid[i][j] == 0:
                    t = 1
                    s = set()
                    for x, y in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                        if check(i + x, j + y):
                            root = find((i + x) * n + j + y)
                            if root not in s:
                                t += size[root]
                                s.add(root)
                    res = max(res, t)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int n;
    private int[] p;
    private int[] size;
    private int mx;
    private int[][] grid;
    private int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int largestIsland(int[][] grid) {
        n = grid.length;
        mx = 1;
        this.grid = grid;
        p = new int[n * n];
        size = new int[n * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    for (int[] e : dirs) {
                        if (check(i + e[0], j + e[1])) {
                            union(i * n + j, (i + e[0]) * n + j + e[1]);
                        }
                    }
                }
            }
        }
        int res = mx;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int t = 1;
                    Set<Integer> s = new HashSet<>();
                    for (int[] e : dirs) {
                        if (check(i + e[0], j + e[1])) {
                            int root = find((i + e[0]) * n + j + e[1]);
                            if (!s.contains(root)) {
                                t += size[root];
                                s.add(root);
                            }
                        }
                    }
                    res = Math.max(res, t);
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

    private void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            size[pb] += size[pa];
            mx = Math.max(mx, size[pb]);
            p[pa] = pb;
        }
    }

    private boolean check(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < n && grid[i][j] == 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;
    vector<int> size;
    int n, mx;
    int dirs[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    int largestIsland(vector<vector<int>>& grid) {
        n = grid.size();
        mx = 1;
        p.resize(n * n);
        size.resize(n * n);
        for (int i = 0; i < p.size(); ++i) {
            p[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    for (auto e : dirs) {
                        if (check(i + e[0], j + e[1], grid)) unite(i * n + j, (i + e[0]) * n + j + e[1]);
                    }
                }
            }
        }
        int res = mx;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int t = 1;
                    unordered_set<int> s;
                    for (auto e : dirs) {
                        if (check(i + e[0], j + e[1], grid)) {
                            int root = find((i + e[0]) * n + j + e[1]);
                            if (!s.count(root)) {
                                t += size[root];
                                s.insert(root);
                            }
                        }
                    }
                    res = max(res, t);
                }
            }
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            size[pb] += size[pa];
            mx = max(mx, size[pb]);
            p[pa] = pb;
        }
    }

    bool check(int i, int j, vector<vector<int>>& grid) {
        return i >= 0 && i < n && j >= 0 && j < n && grid[i][j] == 1;
    }
};
```

### **Go**

```go
var p []int
var size []int
var n int
var mx int

func largestIsland(grid [][]int) int {
	n, mx = len(grid), 1
	p = make([]int, n*n)
	size = make([]int, n*n)
	for i := 0; i < len(p); i++ {
		p[i] = i
		size[i] = 1
	}

	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				for _, e := range dirs {
					if check(i+e[0], j+e[1], grid) {
						union(i*n+j, (i+e[0])*n+j+e[1])
					}
				}
			}
		}
	}
	res := mx
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 {
				t := 1
				s := make(map[int]bool)
				for _, e := range dirs {
					if check(i+e[0], j+e[1], grid) {
						root := find((i+e[0])*n + j + e[1])
						if !s[root] {
							t += size[root]
							s[root] = true
						}
					}
				}
				res = max(res, t)
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

func union(a, b int) {
	pa, pb := find(a), find(b)
	if pa != pb {
		size[pb] += size[pa]
		mx = max(mx, size[pb])
		p[pa] = pb
	}
}

func check(i, j int, grid [][]int) bool {
	return i >= 0 && i < n && j >= 0 && j < n && grid[i][j] == 1
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
