# [778. Swim in Rising Water](https://leetcode.com/problems/swim-in-rising-water)

[中文文档](/solution/0700-0799/0778.Swim%20in%20Rising%20Water/README.md)

## Description

<p>On an N x N <code>grid</code>, each square <code>grid[i][j]</code> represents the elevation at that point <code>(i,j)</code>.</p>

<p>Now rain starts to fall. At time <code>t</code>, the depth of the water everywhere is <code>t</code>. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are&nbsp;at most&nbsp;<code>t</code>. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.</p>

<p>You start at the top left square <code>(0, 0)</code>. What is the least time until you can reach the bottom right square <code>(N-1, N-1)</code>?</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> [[0,2],[1,3]]

<strong>Output:</strong> 3

<strong>Explanation:</strong>

At time <code>0</code>, you are in grid location <code>(0, 0)</code>.

You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.



You cannot reach point <code>(1, 1)</code> until time <code>3</code>.

When the depth of water is <code>3</code>, we can swim anywhere inside the grid.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]

<strong>Output:</strong> 16

<strong>Explanation:</strong>

<strong> 0  1  2  3  4</strong>

24 23 22 21  <strong>5</strong>

<strong>12 13 14 15 16</strong>

<strong>11</strong> 17 18 19 20

<strong>10  9  8  7  6</strong>



The final route is marked in bold.

We need to wait until time 16 so that (0, 0) and (4, 4) are connected.

</pre>

<p><strong>Note:</strong></p>

<ol>
	<li><code>2 &lt;= N &lt;= 50</code>.</li>
	<li>grid[i][j] is a permutation of [0, ..., N*N - 1].</li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

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
