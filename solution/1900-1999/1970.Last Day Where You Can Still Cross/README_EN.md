# [1970. Last Day Where You Can Still Cross](https://leetcode.com/problems/last-day-where-you-can-still-cross)

[中文文档](/solution/1900-1999/1970.Last%20Day%20Where%20You%20Can%20Still%20Cross/README.md)

## Description

<p>There is a <strong>1-based</strong> binary matrix where <code>0</code> represents land and <code>1</code> represents water. You are given integers <code>row</code> and <code>col</code> representing the number of rows and columns in the matrix, respectively.</p>

<p>Initially on day <code>0</code>, the <strong>entire</strong> matrix is <strong>land</strong>. However, each day a new cell becomes flooded with <strong>water</strong>. You are given a <strong>1-based</strong> 2D array <code>cells</code>, where <code>cells[i] = [r<sub>i</sub>, c<sub>i</sub>]</code> represents that on the <code>i<sup>th</sup></code> day, the cell on the <code>r<sub>i</sub><sup>th</sup></code> row and <code>c<sub>i</sub><sup>th</sup></code> column (<strong>1-based</strong> coordinates) will be covered with <strong>water</strong> (i.e., changed to <code>1</code>).</p>

<p>You want to find the <strong>last</strong> day that it is possible to walk from the <strong>top</strong> to the <strong>bottom</strong> by only walking on land cells. You can start from <strong>any</strong> cell in the top row and end at <strong>any</strong> cell in the bottom row. You can only travel in the<strong> four</strong> cardinal directions (left, right, up, and down).</p>

<p>Return <em>the <strong>last</strong> day where it is possible to walk from the <strong>top</strong> to the <strong>bottom</strong> by only walking on land cells</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1970.Last%20Day%20Where%20You%20Can%20Still%20Cross/images/1.png" style="width: 624px; height: 162px;" />
<pre>
<strong>Input:</strong> row = 2, col = 2, cells = [[1,1],[2,1],[1,2],[2,2]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The above image depicts how the matrix changes each day starting from day 0.
The last day where it is possible to cross from top to bottom is on day 2.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1970.Last%20Day%20Where%20You%20Can%20Still%20Cross/images/2.png" style="width: 504px; height: 178px;" />
<pre>
<strong>Input:</strong> row = 2, col = 2, cells = [[1,1],[1,2],[2,1],[2,2]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The above image depicts how the matrix changes each day starting from day 0.
The last day where it is possible to cross from top to bottom is on day 1.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1970.Last%20Day%20Where%20You%20Can%20Still%20Cross/images/3.png" style="width: 666px; height: 167px;" />
<pre>
<strong>Input:</strong> row = 3, col = 3, cells = [[1,2],[2,1],[3,3],[2,2],[1,1],[1,3],[2,3],[3,2],[3,1]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The above image depicts how the matrix changes each day starting from day 0.
The last day where it is possible to cross from top to bottom is on day 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= row, col &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>4 &lt;= row * col &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>cells.length == row * col</code></li>
	<li><code>1 &lt;= r<sub>i</sub> &lt;= row</code></li>
	<li><code>1 &lt;= c<sub>i</sub> &lt;= col</code></li>
	<li>All the values of <code>cells</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
