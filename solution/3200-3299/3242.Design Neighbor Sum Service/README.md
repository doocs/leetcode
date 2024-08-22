---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3242.Design%20Neighbor%20Sum%20Service/README.md
tags:
    - 设计
    - 数组
    - 哈希表
    - 矩阵
    - 模拟
---

<!-- problem:start -->

# [3242. 设计相邻元素求和服务](https://leetcode.cn/problems/design-neighbor-sum-service)

[English Version](/solution/3200-3299/3242.Design%20Neighbor%20Sum%20Service/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <code>n x n</code> 的二维数组 <code>grid</code>，它包含范围 <code>[0, n<sup>2</sup> - 1]</code> 内的<strong>不重复</strong>元素。</p>

<p>实现 <code>neighborSum</code> 类：</p>

<ul>
	<li><code>neighborSum(int [][]grid)</code> 初始化对象。</li>
	<li><code>int adjacentSum(int value)</code> 返回在 <code>grid</code> 中与 <code>value</code> 相邻的元素之<strong>和</strong>，相邻指的是与 <code>value</code> 在上、左、右或下的元素。</li>
	<li><code>int diagonalSum(int value)</code> 返回在 <code>grid</code> 中与 <code>value</code> 对角线相邻的元素之<strong>和</strong>，对角线相邻指的是与 <code>value</code> 在左上、右上、左下或右下的元素。</li>
</ul>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3242.Design%20Neighbor%20Sum%20Service/images/design.png" style="width: 400px; height: 248px;" /></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>["neighborSum", "adjacentSum", "adjacentSum", "diagonalSum", "diagonalSum"]</p>

<p>[[[[0, 1, 2], [3, 4, 5], [6, 7, 8]]], [1], [4], [4], [8]]</p>

<p><strong>输出：</strong> [null, 6, 16, 16, 4]</p>

<p><strong>解释：</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3242.Design%20Neighbor%20Sum%20Service/images/designexample0.png" style="width: 250px; height: 249px;" /></strong></p>

<ul>
	<li>1 的相邻元素是 0、2 和 4。</li>
	<li>4 的相邻元素是 1、3、5 和 7。</li>
	<li>4 的对角线相邻元素是 0、2、6 和 8。</li>
	<li>8 的对角线相邻元素是 4。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>["neighborSum", "adjacentSum", "diagonalSum"]</p>

<p>[[[[1, 2, 0, 3], [4, 7, 15, 6], [8, 9, 10, 11], [12, 13, 14, 5]]], [15], [9]]</p>

<p><strong>输出：</strong> [null, 23, 45]</p>

<p><strong>解释：</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3242.Design%20Neighbor%20Sum%20Service/images/designexample2.png" style="width: 300px; height: 300px;" /></strong></p>

<ul>
	<li>15 的相邻元素是 0、10、7 和 6。</li>
	<li>9 的对角线相邻元素是 4、12、14 和 15。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n == grid.length == grid[0].length &lt;= 10</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= n<sup>2</sup> - 1</code></li>
	<li>所有 <code>grid[i][j]</code> 值均不重复。</li>
	<li><code>adjacentSum</code> 和 <code>diagonalSum</code> 中的 <code>value</code> 均在范围 <code>[0, n<sup>2</sup> - 1]</code> 内。</li>
	<li>最多会调用 <code>adjacentSum</code> 和 <code>diagonalSum</code> 总共 <code>2 * n<sup>2</sup></code> 次。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以用一个哈希表 $\textit{d}$ 来存储每个元素的坐标，然后根据题意，分别计算相邻元素和对角线相邻元素的和。

时间复杂度方面，初始化哈希表的时间复杂度为 $O(m \times n)$，计算相邻元素和对角线相邻元素的和的时间复杂度为 $O(1)$。空间复杂度为 $O(m \times n)$。

<!-- tabs:start -->

#### Python3

```python
class neighborSum:

    def __init__(self, grid: List[List[int]]):
        self.grid = grid
        self.d = {}
        self.dirs = ((-1, 0, 1, 0, -1), (-1, 1, 1, -1, -1))
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                self.d[x] = (i, j)

    def adjacentSum(self, value: int) -> int:
        return self.cal(value, 0)

    def cal(self, value: int, k: int):
        i, j = self.d[value]
        s = 0
        for a, b in pairwise(self.dirs[k]):
            x, y = i + a, j + b
            if 0 <= x < len(self.grid) and 0 <= y < len(self.grid[0]):
                s += self.grid[x][y]
        return s

    def diagonalSum(self, value: int) -> int:
        return self.cal(value, 1)


# Your neighborSum object will be instantiated and called as such:
# obj = neighborSum(grid)
# param_1 = obj.adjacentSum(value)
# param_2 = obj.diagonalSum(value)
```

#### Java

```java
class neighborSum {
    private int[][] grid;
    private final Map<Integer, int[]> d = new HashMap<>();
    private final int[][] dirs = {{-1, 0, 1, 0, -1}, {-1, 1, 1, -1, -1}};

    public neighborSum(int[][] grid) {
        this.grid = grid;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                d.put(grid[i][j], new int[] {i, j});
            }
        }
    }

    public int adjacentSum(int value) {
        return cal(value, 0);
    }

    public int diagonalSum(int value) {
        return cal(value, 1);
    }

    private int cal(int value, int k) {
        int[] p = d.get(value);
        int s = 0;
        for (int q = 0; q < 4; ++q) {
            int x = p[0] + dirs[k][q], y = p[1] + dirs[k][q + 1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
                s += grid[x][y];
            }
        }
        return s;
    }
}

/**
 * Your neighborSum object will be instantiated and called as such:
 * neighborSum obj = new neighborSum(grid);
 * int param_1 = obj.adjacentSum(value);
 * int param_2 = obj.diagonalSum(value);
 */
```

#### C++

```cpp
class neighborSum {
public:
    neighborSum(vector<vector<int>>& grid) {
        this->grid = grid;
        int m = grid.size(), n = grid[0].size();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                d[grid[i][j]] = {i, j};
            }
        }
    }

    int adjacentSum(int value) {
        return cal(value, 0);
    }

    int diagonalSum(int value) {
        return cal(value, 1);
    }

private:
    vector<vector<int>> grid;
    unordered_map<int, pair<int, int>> d;
    int dirs[2][5] = {{-1, 0, 1, 0, -1}, {-1, 1, 1, -1, -1}};

    int cal(int value, int k) {
        auto [i, j] = d[value];
        int s = 0;
        for (int q = 0; q < 4; ++q) {
            int x = i + dirs[k][q], y = j + dirs[k][q + 1];
            if (x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size()) {
                s += grid[x][y];
            }
        }
        return s;
    }
};

/**
 * Your neighborSum object will be instantiated and called as such:
 * neighborSum* obj = new neighborSum(grid);
 * int param_1 = obj->adjacentSum(value);
 * int param_2 = obj->diagonalSum(value);
 */
```

#### Go

```go
type neighborSum struct {
	grid [][]int
	d    map[int][2]int
	dirs [2][5]int
}

func Constructor(grid [][]int) neighborSum {
	d := map[int][2]int{}
	for i, row := range grid {
		for j, x := range row {
			d[x] = [2]int{i, j}
		}
	}
	dirs := [2][5]int{{-1, 0, 1, 0, -1}, {-1, 1, 1, -1, -1}}
	return neighborSum{grid, d, dirs}
}

func (this *neighborSum) AdjacentSum(value int) int {
	return this.cal(value, 0)
}

func (this *neighborSum) DiagonalSum(value int) int {
	return this.cal(value, 1)
}

func (this *neighborSum) cal(value, k int) int {
	p := this.d[value]
	s := 0
	for q := 0; q < 4; q++ {
		x, y := p[0]+this.dirs[k][q], p[1]+this.dirs[k][q+1]
		if x >= 0 && x < len(this.grid) && y >= 0 && y < len(this.grid[0]) {
			s += this.grid[x][y]
		}
	}
	return s
}

/**
 * Your neighborSum object will be instantiated and called as such:
 * obj := Constructor(grid);
 * param_1 := obj.AdjacentSum(value);
 * param_2 := obj.DiagonalSum(value);
 */
```

#### TypeScript

```ts
class neighborSum {
    private grid: number[][];
    private d: Map<number, [number, number]> = new Map();
    private dirs: number[][] = [
        [-1, 0, 1, 0, -1],
        [-1, 1, 1, -1, -1],
    ];
    constructor(grid: number[][]) {
        for (let i = 0; i < grid.length; ++i) {
            for (let j = 0; j < grid[0].length; ++j) {
                this.d.set(grid[i][j], [i, j]);
            }
        }
        this.grid = grid;
    }

    adjacentSum(value: number): number {
        return this.cal(value, 0);
    }

    diagonalSum(value: number): number {
        return this.cal(value, 1);
    }

    cal(value: number, k: number): number {
        const [i, j] = this.d.get(value)!;
        let s = 0;
        for (let q = 0; q < 4; ++q) {
            const [x, y] = [i + this.dirs[k][q], j + this.dirs[k][q + 1]];
            if (x >= 0 && x < this.grid.length && y >= 0 && y < this.grid[0].length) {
                s += this.grid[x][y];
            }
        }
        return s;
    }
}

/**
 * Your neighborSum object will be instantiated and called as such:
 * var obj = new neighborSum(grid)
 * var param_1 = obj.adjacentSum(value)
 * var param_2 = obj.diagonalSum(value)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
