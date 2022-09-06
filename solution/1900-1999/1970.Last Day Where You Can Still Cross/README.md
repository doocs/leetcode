# [1970. 你能穿过矩阵的最后一天](https://leetcode.cn/problems/last-day-where-you-can-still-cross)

[English Version](/solution/1900-1999/1970.Last%20Day%20Where%20You%20Can%20Still%20Cross/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>1</strong>&nbsp;开始的二进制矩阵，其中&nbsp;<code>0</code>&nbsp;表示陆地，<code>1</code>&nbsp;表示水域。同时给你&nbsp;<code>row</code> 和&nbsp;<code>col</code>&nbsp;分别表示矩阵中行和列的数目。</p>

<p>一开始在第&nbsp;<code>0</code>&nbsp;天，<strong>整个</strong>&nbsp;矩阵都是&nbsp;<strong>陆地</strong>&nbsp;。但每一天都会有一块新陆地被&nbsp;<strong>水</strong>&nbsp;淹没变成水域。给你一个下标从&nbsp;<strong>1</strong>&nbsp;开始的二维数组&nbsp;<code>cells</code>&nbsp;，其中&nbsp;<code>cells[i] = [r<sub>i</sub>, c<sub>i</sub>]</code>&nbsp;表示在第&nbsp;<code>i</code>&nbsp;天，第&nbsp;<code>r<sub>i</sub></code>&nbsp;行&nbsp;<code>c<sub>i</sub></code>&nbsp;列（下标都是从 <strong>1</strong>&nbsp;开始）的陆地会变成 <strong>水域</strong>&nbsp;（也就是 <code>0</code>&nbsp;变成 <code>1</code>&nbsp;）。</p>

<p>你想知道从矩阵最 <strong>上面</strong>&nbsp;一行走到最 <strong>下面</strong>&nbsp;一行，且只经过陆地格子的 <strong>最后一天</strong>&nbsp;是哪一天。你可以从最上面一行的&nbsp;<strong>任意</strong>&nbsp;格子出发，到达最下面一行的&nbsp;<strong>任意</strong>&nbsp;格子。你只能沿着&nbsp;<strong>四个</strong>&nbsp;基本方向移动（也就是上下左右）。</p>

<p>请返回只经过陆地格子能从最 <strong>上面</strong>&nbsp;一行走到最 <strong>下面</strong>&nbsp;一行的 <strong>最后一天</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1970.Last%20Day%20Where%20You%20Can%20Still%20Cross/images/1.png" style="width: 624px; height: 162px;">
<pre><b>输入：</b>row = 2, col = 2, cells = [[1,1],[2,1],[1,2],[2,2]]
<b>输出：</b>2
<b>解释：</b>上图描述了矩阵从第 0 天开始是如何变化的。
可以从最上面一行到最下面一行的最后一天是第 2 天。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1970.Last%20Day%20Where%20You%20Can%20Still%20Cross/images/2.png" style="width: 504px; height: 178px;">
<pre><b>输入：</b>row = 2, col = 2, cells = [[1,1],[1,2],[2,1],[2,2]]
<b>输出：</b>1
<b>解释：</b>上图描述了矩阵从第 0 天开始是如何变化的。
可以从最上面一行到最下面一行的最后一天是第 1 天。
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1970.Last%20Day%20Where%20You%20Can%20Still%20Cross/images/3.png" style="width: 666px; height: 167px;">
<pre><b>输入：</b>row = 3, col = 3, cells = [[1,2],[2,1],[3,3],[2,2],[1,1],[1,3],[2,3],[3,2],[3,1]]
<b>输出：</b>3
<b>解释：</b>上图描述了矩阵从第 0 天开始是如何变化的。
可以从最上面一行到最下面一行的最后一天是第 3 天。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= row, col &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>4 &lt;= row * col &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>cells.length == row * col</code></li>
	<li><code>1 &lt;= r<sub>i</sub> &lt;= row</code></li>
	<li><code>1 &lt;= c<sub>i</sub> &lt;= col</code></li>
	<li><code>cells</code>&nbsp;中的所有格子坐标都是 <strong>唯一</strong>&nbsp;的。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

逆序并查集。

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
    def latestDayToCross(self, row: int, col: int, cells: List[List[int]]) -> int:
        n = row * col
        p = list(range(n + 2))
        grid = [[False] * col for _ in range(row)]
        top, bottom = n, n + 1

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def check(i, j):
            return 0 <= i < row and 0 <= j < col and grid[i][j]

        for k in range(len(cells) - 1, -1, -1):
            i, j = cells[k][0] - 1, cells[k][1] - 1
            grid[i][j] = True
            for x, y in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                if check(i + x, j + y):
                    p[find(i * col + j)] = find((i + x) * col + j + y)
            if i == 0:
                p[find(i * col + j)] = find(top)
            if i == row - 1:
                p[find(i * col + j)] = find(bottom)
            if find(top) == find(bottom):
                return k
        return 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;
    private int row;
    private int col;
    private boolean[][] grid;
    private int[][] dirs = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int latestDayToCross(int row, int col, int[][] cells) {
        int n = row * col;
        this.row = row;
        this.col = col;
        p = new int[n + 2];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        grid = new boolean[row][col];
        int top = n, bottom = n + 1;
        for (int k = cells.length - 1; k >= 0; --k) {
            int i = cells[k][0] - 1, j = cells[k][1] - 1;
            grid[i][j] = true;
            for (int[] e : dirs) {
                if (check(i + e[0], j + e[1])) {
                    p[find(i * col + j)] = find((i + e[0]) * col + j + e[1]);
                }
            }
            if (i == 0) {
                p[find(i * col + j)] = find(top);
            }
            if (i == row - 1) {
                p[find(i * col + j)] = find(bottom);
            }
            if (find(top) == find(bottom)) {
                return k;
            }
        }
        return 0;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private boolean check(int i, int j) {
        return i >= 0 && i < row && j >= 0 && j < col && grid[i][j];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;
    int dirs[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    int row, col;

    int latestDayToCross(int row, int col, vector<vector<int>>& cells) {
        int n = row * col;
        this->row = row;
        this->col = col;
        p.resize(n + 2);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<vector<bool>> grid(row, vector<bool>(col, false));
        int top = n, bottom = n + 1;
        for (int k = cells.size() - 1; k >= 0; --k) {
            int i = cells[k][0] - 1, j = cells[k][1] - 1;
            grid[i][j] = true;
            for (auto e : dirs) {
                if (check(i + e[0], j + e[1], grid)) {
                    p[find(i * col + j)] = find((i + e[0]) * col + j + e[1]);
                }
            }
            if (i == 0) p[find(i * col + j)] = find(top);
            if (i == row - 1) p[find(i * col + j)] = find(bottom);
            if (find(top) == find(bottom)) return k;
        }
        return 0;
    }

    bool check(int i, int j, vector<vector<bool>>& grid) {
        return i >= 0 && i < row && j >= 0 && j < col && grid[i][j];
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

func latestDayToCross(row int, col int, cells [][]int) int {
	n := row * col
	p = make([]int, n+2)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	grid := make([][]bool, row)
	for i := 0; i < row; i++ {
		grid[i] = make([]bool, col)
	}
	top, bottom := n, n+1
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for k := len(cells) - 1; k >= 0; k-- {
		i, j := cells[k][0]-1, cells[k][1]-1
		grid[i][j] = true
		for _, e := range dirs {
			if check(i+e[0], j+e[1], grid) {
				p[find(i*col+j)] = find((i+e[0])*col + j + e[1])
			}
		}
		if i == 0 {
			p[find(i*col+j)] = find(top)
		}
		if i == row-1 {
			p[find(i*col+j)] = find(bottom)
		}
		if find(top) == find(bottom) {
			return k
		}
	}
	return 0
}

func check(i, j int, grid [][]bool) bool {
	return i >= 0 && i < len(grid) && j >= 0 && j < len(grid[0]) && grid[i][j]
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
