# [305. 岛屿数量 II](https://leetcode-cn.com/problems/number-of-islands-ii)

[English Version](/solution/0300-0399/0305.Number%20of%20Islands%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设你设计一个游戏，用一个&nbsp;<code>m</code>&nbsp;行&nbsp;<code>n</code>&nbsp;列的&nbsp;2D 网格来存储你的游戏地图。</p>

<p>起始的时候，每个格子的地形都被默认标记为「水」。我们可以通过使用&nbsp;<code>addLand</code>&nbsp;进行操作，将位置 <code>(row, col)</code> 的「水」变成「陆地」。</p>

<p>你将会被给定一个列表，来记录所有需要被操作的位置，然后你需要返回计算出来&nbsp;<strong>每次&nbsp;<em>addLand </em>操作后岛屿的数量</strong>。</p>

<p>注意：一个岛的定义是被「水」包围的「陆地」，通过水平方向或者垂直方向上相邻的陆地连接而成。你可以假设地图网格的四边均被无边无际的「水」所包围。</p>

<p>请仔细阅读下方示例与解析，更加深入了解岛屿的判定。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
<strong>输出:</strong> [1,1,2,3]
</pre>

<p><strong>解析:</strong></p>

<p>起初，二维网格&nbsp;<code>grid</code>&nbsp;被全部注入「水」。（0 代表「水」，1 代表「陆地」）</p>

<pre>0 0 0
0 0 0
0 0 0
</pre>

<p>操作&nbsp;#1：<code>addLand(0, 0)</code> 将&nbsp;<code>grid[0][0]</code> 的水变为陆地。</p>

<pre>1 0 0
0 0 0   Number of islands = 1
0 0 0
</pre>

<p>操作&nbsp;#2：<code>addLand(0, 1)</code> 将&nbsp;<code>grid[0][1]</code> 的水变为陆地。</p>

<pre>1 1 0
0 0 0   岛屿的数量为 1
0 0 0
</pre>

<p>操作&nbsp;#3：<code>addLand(1, 2)</code> 将&nbsp;<code>grid[1][2]</code> 的水变为陆地。</p>

<pre>1 1 0
0 0 1   岛屿的数量为 2
0 0 0
</pre>

<p>操作&nbsp;#4：<code>addLand(2, 1)</code> 将&nbsp;<code>grid[2][1]</code> 的水变为陆地。</p>

<pre>1 1 0
0 0 1   岛屿的数量为 3
0 1 0
</pre>

<p><strong>拓展：</strong></p>

<p>你是否能在 O(k log mn) 的时间复杂度程度内完成每次的计算？（k 表示&nbsp;<code>positions</code>&nbsp;的长度）</p>

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
    def numIslands2(self, m: int, n: int, positions: List[List[int]]) -> List[int]:
        p = list(range(m * n))
        grid = [[0] * n for _ in range(m)]

        def check(i, j):
            return 0 <= i < m and 0 <= j < n and grid[i][j] == 1

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        res = []
        cur = 0
        for i, j in positions:
            if grid[i][j] == 1:
                res.append(cur)
                continue
            grid[i][j] = 1
            cur += 1
            for x, y in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                if check(i + x, j + y) and find(i * n + j) != find((i + x) * n + j + y):
                    p[find(i * n + j)] = find((i + x) * n + j + y)
                    cur -= 1
            res.append(cur)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;
    private int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    private int[][] grid;
    private int m;
    private int n;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        grid = new int[m][n];
        this.m = m;
        this.n = n;
        List<Integer> res = new ArrayList<>();
        int cur = 0;
        for (int[] position : positions) {
            int i = position[0], j = position[1];
            if (grid[i][j] == 1) {
                res.add(cur);
                continue;
            }
            grid[i][j] = 1;
            ++cur;
            for (int[] e : dirs) {
                if (check(i + e[0], j + e[1]) && find(i * n + j) != find((i + e[0]) * n + j + e[1])) {
                    p[find(i * n + j)] = find((i + e[0]) * n + j + e[1]);
                    --cur;
                }
            }
            res.add(cur);
        }
        return res;
    }

    private boolean check(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == 1;
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

    vector<int> numIslands2(int m, int n, vector<vector<int>>& positions) {
        p.resize(m * n);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<vector<int>> grid(m, vector<int>(n, 0));
        vector<int> res;
        int cur = 0;
        for (auto position : positions)
        {
            int i = position[0], j = position[1];
            if (grid[i][j] == 1)
            {
                res.push_back(cur);
                continue;
            }
            grid[i][j] = 1;
            ++cur;
            for (auto e : dirs) {
                if (check(i + e[0], j + e[1], grid) && find(i * n + j) != find((i + e[0]) * n + j + e[1]))
                {
                    p[find(i * n + j)] = find((i + e[0]) * n + j + e[1]);
                    --cur;
                }
            }
            res.push_back(cur);
        }
        return res;
    }

    bool check(int i, int j, vector<vector<int>>& grid) {
        return i >= 0 && i < grid.size() && j >= 0 && j < grid[0].size() && grid[i][j] == 1;
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

func numIslands2(m int, n int, positions [][]int) []int {
	p = make([]int, m*n)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	grid := make([][]int, m)
	for i := 0; i < m; i++ {
		grid[i] = make([]int, n)
	}
	var res []int
	cur := 0
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for _, position := range positions {
		i, j := position[0], position[1]
		if grid[i][j] == 1 {
			res = append(res, cur)
			continue
		}
		grid[i][j] = 1
		cur++
		for _, e := range dirs {
			if check(i+e[0], j+e[1], grid) && find(i*n+j) != find((i+e[0])*n+j+e[1]) {
				p[find(i*n+j)] = find((i+e[0])*n + j + e[1])
				cur--
			}
		}
		res = append(res, cur)
	}
	return res
}

func check(i, j int, grid [][]int) bool {
	return i >= 0 && i < len(grid) && j >= 0 && j < len(grid[0]) && grid[i][j] == 1
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
