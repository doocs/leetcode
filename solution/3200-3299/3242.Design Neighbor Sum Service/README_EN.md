---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3242.Design%20Neighbor%20Sum%20Service/README_EN.md
tags:
    - Design
    - Array
    - Hash Table
    - Matrix
    - Simulation
---

<!-- problem:start -->

# [3242. Design Neighbor Sum Service](https://leetcode.com/problems/design-neighbor-sum-service)

[中文文档](/solution/3200-3299/3242.Design%20Neighbor%20Sum%20Service/README.md)

## Description

<!-- description:start -->

<p>You are given a <code>n x n</code> 2D array <code>grid</code> containing <strong>distinct</strong> elements in the range <code>[0, n<sup>2</sup> - 1]</code>.</p>

<p>Implement the <code>neighborSum</code> class:</p>

<ul>
	<li><code>neighborSum(int [][]grid)</code> initializes the object.</li>
	<li><code>int adjacentSum(int value)</code> returns the <strong>sum</strong> of elements which are adjacent neighbors of <code>value</code>, that is either to the top, left, right, or bottom of <code>value</code> in <code>grid</code>.</li>
	<li><code>int diagonalSum(int value)</code> returns the <strong>sum</strong> of elements which are diagonal neighbors of <code>value</code>, that is either to the top-left, top-right, bottom-left, or bottom-right of <code>value</code> in <code>grid</code>.</li>
</ul>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3242.Design%20Neighbor%20Sum%20Service/images/design.png" style="width: 400px; height: 248px;" /></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>[&quot;neighborSum&quot;, &quot;adjacentSum&quot;, &quot;adjacentSum&quot;, &quot;diagonalSum&quot;, &quot;diagonalSum&quot;]</p>

<p>[[[[0, 1, 2], [3, 4, 5], [6, 7, 8]]], [1], [4], [4], [8]]</p>

<p><strong>Output:</strong> [null, 6, 16, 16, 4]</p>

<p><strong>Explanation:</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3242.Design%20Neighbor%20Sum%20Service/images/designexample0.png" style="width: 250px; height: 249px;" /></strong></p>

<ul>
	<li>The adjacent neighbors of 1 are 0, 2, and 4.</li>
	<li>The adjacent neighbors of 4 are 1, 3, 5, and 7.</li>
	<li>The diagonal neighbors of 4 are 0, 2, 6, and 8.</li>
	<li>The diagonal neighbor of 8 is 4.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>[&quot;neighborSum&quot;, &quot;adjacentSum&quot;, &quot;diagonalSum&quot;]</p>

<p>[[[[1, 2, 0, 3], [4, 7, 15, 6], [8, 9, 10, 11], [12, 13, 14, 5]]], [15], [9]]</p>

<p><strong>Output:</strong> [null, 23, 45]</p>

<p><strong>Explanation:</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3242.Design%20Neighbor%20Sum%20Service/images/designexample2.png" style="width: 300px; height: 300px;" /></strong></p>

<ul>
	<li>The adjacent neighbors of 15 are 0, 10, 7, and 6.</li>
	<li>The diagonal neighbors of 9 are 4, 12, 14, and 15.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n == grid.length == grid[0].length &lt;= 10</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= n<sup>2</sup> - 1</code></li>
	<li>All <code>grid[i][j]</code> are distinct.</li>
	<li><code>value</code> in <code>adjacentSum</code> and <code>diagonalSum</code> will be in the range <code>[0, n<sup>2</sup> - 1]</code>.</li>
	<li>At most <code>2 * n<sup>2</sup></code> calls will be made to <code>adjacentSum</code> and <code>diagonalSum</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can use a hash table $\textit{d}$ to store the coordinates of each element. Then, according to the problem description, we separately calculate the sum of adjacent elements and diagonally adjacent elements.

In terms of time complexity, initializing the hash table has a time complexity of $O(m \times n)$, and calculating the sum of adjacent elements and diagonally adjacent elements has a time complexity of $O(1)$. The space complexity is $O(m \times n)$.

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
