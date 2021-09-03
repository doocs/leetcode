# [778. 水位上升的泳池中游泳](https://leetcode-cn.com/problems/swim-in-rising-water)

[English Version](/solution/0700-0799/0778.Swim%20in%20Rising%20Water/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个 N x N 的坐标方格 <code>grid</code> 中，每一个方格的值 <code>grid[i][j]</code> 表示在位置 <code>(i,j)</code> 的平台高度。</p>

<p>现在开始下雨了。当时间为 <code>t</code> 时，此时雨水导致水池中任意位置的水位为 <code>t</code> 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。</p>

<p>你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 <code>(N-1, N-1)</code>？</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> [[0,2],[1,3]]
<strong>输出:</strong> 3
<strong>解释:</strong>
时间为0时，你位于坐标方格的位置为 <code>(0, 0)。</code>
此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。

等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
</pre>

<p><strong>示例2:</strong></p>

<pre>
<strong>输入:</strong> [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
<strong>输出:</strong> 16
<strong>解释:</strong>
<strong> 0  1  2  3  4</strong>
24 23 22 21  <strong>5</strong>
<strong>12 13 14 15 16</strong>
<strong>11</strong> 17 18 19 20
<strong>10  9  8  7  6</strong>

最终的路线用加粗进行了标记。
我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ol>
	<li><code>2 <= N <= 50</code>.</li>
	<li><code>grid[i][j]</code> 是 <code>[0, ..., N*N - 1]</code> 的排列。</li>
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
    def swimInWater(self, grid: List[List[int]]) -> int:
        n = len(grid)
        p = list(range(n * n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def index(i, j):
            return i * n + j

        def check(i, j):
            return 0 <= i < n and 0 <= j < n

        hi = [0] * (n * n)
        for i in range(n):
            for j in range(n):
                hi[grid[i][j]] = index(i, j)
        for h in range(n * n):
            x, y = hi[h] // n, hi[h] % n
            for a, b in [(0, -1), (0, 1), (1, 0), (-1, 0)]:
                x1, y1 = x + a, y + b
                if check(x1, y1) and grid[x1][y1] <= h:
                    p[find(index(x1, y1))] = find(hi[h])
                if find(0) == find(n * n - 1):
                    return h
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;
    private int n;
    private int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int swimInWater(int[][] grid) {
        n = grid.length;
        p = new int[n * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        int[] hi = new int[n * n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                hi[grid[i][j]] = index(i, j);
            }
        }
        for (int h = 0; h < n * n; ++h) {
            int x = hi[h] / n, y = hi[h] % n;
            for (int[] dir : dirs) {
                int x1 = x + dir[0], y1 = y + dir[1];
                if (check(x1, y1) && grid[x1][y1] <= h) {
                    p[find(index(x1, y1))] = find(hi[h]);
                }
                if (find(0) == find(n * n - 1)) {
                    return h;
                }
            }
        }
        return -1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private int index(int i, int j) {
        return i * n + j;
    }

    private boolean check(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;
    int n;
    int dirs[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    int swimInWater(vector<vector<int>> &grid) {
        n = grid.size();
        for (int i = 0; i < n * n; ++i)
            p.push_back(i);
        vector<int> hi(n * n, 0);
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                hi[grid[i][j]] = index(i, j);
        for (int h = 0; h < n * n; ++h)
        {
            int x = hi[h] / n, y = hi[h] % n;
            for (auto dir : dirs)
            {
                int x1 = x + dir[0], y1 = y + dir[1];
                if (check(x1, y1) && grid[x1][y1] <= h)
                    p[find(index(x1, y1))] = find(hi[h]);
                if (find(0) == find(n * n - 1))
                    return h;
            }
        }
        return -1;
    }

    int find(int x) {
        if (p[x] != x)
            p[x] = find(p[x]);
        return p[x];
    }

    int index(int i, int j) {
        return i * n + j;
    }

    bool check(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }
};
```

### **Go**

```go
var p []int
var n int

func swimInWater(grid [][]int) int {
	n = len(grid)
	p = make([]int, n*n)
	hi := make([]int, n*n)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			hi[grid[i][j]] = index(i, j)
		}
	}
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for h := 0; h < n*n; h++ {
		x, y := hi[h]/n, hi[h]%n
		for _, dir := range dirs {
			x1, y1 := x+dir[0], y+dir[1]
			if check(x1, y1) && grid[x1][y1] <= h {
				p[find(index(x1, y1))] = find(hi[h])
			}
			if find(0) == find(n*n-1) {
				return h
			}
		}
	}
	return -1
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}

func index(i, j int) int {
	return i*n + j
}

func check(i, j int) bool {
	return i >= 0 && i < n && j >= 0 && j < n
}
```

### **...**

```

```

<!-- tabs:end -->
