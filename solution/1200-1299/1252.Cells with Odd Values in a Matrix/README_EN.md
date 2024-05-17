---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1252.Cells%20with%20Odd%20Values%20in%20a%20Matrix/README_EN.md
rating: 1283
source: Weekly Contest 162 Q1
tags:
    - Array
    - Math
    - Simulation
---

<!-- problem:start -->

# [1252. Cells with Odd Values in a Matrix](https://leetcode.com/problems/cells-with-odd-values-in-a-matrix)

[中文文档](/solution/1200-1299/1252.Cells%20with%20Odd%20Values%20in%20a%20Matrix/README.md)

## Description

<!-- description:start -->

<p>There is an <code>m x n</code> matrix that is initialized to all <code>0</code>&#39;s. There is also a 2D array <code>indices</code> where each <code>indices[i] = [r<sub>i</sub>, c<sub>i</sub>]</code> represents a <strong>0-indexed location</strong> to perform some increment operations on the matrix.</p>

<p>For each location <code>indices[i]</code>, do <strong>both</strong> of the following:</p>

<ol>
	<li>Increment <strong>all</strong> the cells on row <code>r<sub>i</sub></code>.</li>
	<li>Increment <strong>all</strong> the cells on column <code>c<sub>i</sub></code>.</li>
</ol>

<p>Given <code>m</code>, <code>n</code>, and <code>indices</code>, return <em>the <strong>number of odd-valued cells</strong> in the matrix after applying the increment to all locations in </em><code>indices</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1252.Cells%20with%20Odd%20Values%20in%20a%20Matrix/images/e1.png" style="width: 600px; height: 118px;" />
<pre>
<strong>Input:</strong> m = 2, n = 3, indices = [[0,1],[1,1]]
<strong>Output:</strong> 6
<strong>Explanation:</strong> Initial matrix = [[0,0,0],[0,0,0]].
After applying first increment it becomes [[1,2,1],[0,1,0]].
The final matrix is [[1,3,1],[1,3,1]], which contains 6 odd numbers.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1252.Cells%20with%20Odd%20Values%20in%20a%20Matrix/images/e2.png" style="width: 600px; height: 150px;" />
<pre>
<strong>Input:</strong> m = 2, n = 2, indices = [[1,1],[0,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Final matrix = [[2,2],[2,2]]. There are no odd numbers in the final matrix.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>1 &lt;= indices.length &lt;= 100</code></li>
	<li><code>0 &lt;= r<sub>i</sub> &lt; m</code></li>
	<li><code>0 &lt;= c<sub>i</sub> &lt; n</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve this in <code>O(n + m + indices.length)</code> time with only <code>O(n + m)</code> extra space?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We create a matrix $g$ to store the results of the operations. For each pair $(r_i, c_i)$ in $indices$, we add $1$ to all elements in the $r_i$th row and the $c_i$th column of the matrix.

After the simulation, we traverse the matrix and count the number of odd numbers.

The time complexity is $O(\text{indices.length} \times (m+n) + mn)$, and the space complexity is $O(mn)$.

<!-- tabs:start -->

```python
class Solution:
    def oddCells(self, m: int, n: int, indices: List[List[int]]) -> int:
        g = [[0] * n for _ in range(m)]
        for r, c in indices:
            for i in range(m):
                g[i][c] += 1
            for j in range(n):
                g[r][j] += 1
        return sum(v % 2 for row in g for v in row)
```

```java
class Solution {
    public int oddCells(int m, int n, int[][] indices) {
        int[][] g = new int[m][n];
        for (int[] e : indices) {
            int r = e[0], c = e[1];
            for (int i = 0; i < m; ++i) {
                g[i][c]++;
            }
            for (int j = 0; j < n; ++j) {
                g[r][j]++;
            }
        }
        int ans = 0;
        for (int[] row : g) {
            for (int v : row) {
                ans += v % 2;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int oddCells(int m, int n, vector<vector<int>>& indices) {
        vector<vector<int>> g(m, vector<int>(n));
        for (auto& e : indices) {
            int r = e[0], c = e[1];
            for (int i = 0; i < m; ++i) ++g[i][c];
            for (int j = 0; j < n; ++j) ++g[r][j];
        }
        int ans = 0;
        for (auto& row : g)
            for (int v : row) ans += v % 2;
        return ans;
    }
};
```

```go
func oddCells(m int, n int, indices [][]int) int {
	g := make([][]int, m)
	for i := range g {
		g[i] = make([]int, n)
	}
	for _, e := range indices {
		r, c := e[0], e[1]
		for i := 0; i < m; i++ {
			g[i][c]++
		}
		for j := 0; j < n; j++ {
			g[r][j]++
		}
	}
	ans := 0
	for _, row := range g {
		for _, v := range row {
			ans += v % 2
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Space Optimization

We use row array $row$ and column array $col$ to record the number of times each row and column are increased. For each pair $(r_i, c_i)$ in $indices$, we add $1$ to $row[r_i]$ and $col[c_i]$ respectively.

After the operation, we can calculate that the count at position $(i, j)$ is $row[i] + col[j]$. We traverse the matrix and count the number of odd numbers.

The time complexity is $O(\text{indices.length} + mn)$, and the space complexity is $O(m+n)$.

<!-- tabs:start -->

```python
class Solution:
    def oddCells(self, m: int, n: int, indices: List[List[int]]) -> int:
        row = [0] * m
        col = [0] * n
        for r, c in indices:
            row[r] += 1
            col[c] += 1
        return sum((i + j) % 2 for i in row for j in col)
```

```java
class Solution {
    public int oddCells(int m, int n, int[][] indices) {
        int[] row = new int[m];
        int[] col = new int[n];
        for (int[] e : indices) {
            int r = e[0], c = e[1];
            row[r]++;
            col[c]++;
        }
        int ans = 0;
        for (int i : row) {
            for (int j : col) {
                ans += (i + j) % 2;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int oddCells(int m, int n, vector<vector<int>>& indices) {
        vector<int> row(m);
        vector<int> col(n);
        for (auto& e : indices) {
            int r = e[0], c = e[1];
            row[r]++;
            col[c]++;
        }
        int ans = 0;
        for (int i : row)
            for (int j : col) ans += (i + j) % 2;
        return ans;
    }
};
```

```go
func oddCells(m int, n int, indices [][]int) int {
	row := make([]int, m)
	col := make([]int, n)
	for _, e := range indices {
		r, c := e[0], e[1]
		row[r]++
		col[c]++
	}
	ans := 0
	for _, i := range row {
		for _, j := range col {
			ans += (i + j) % 2
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3: Mathematical Optimization

We notice that only when exactly one of $row[i]$ and $col[j]$ is odd, the number at position $(i, j)$ in the matrix will be odd.

We count the number of odd numbers in $row$, denoted as $cnt1$; the number of odd numbers in $col$, denoted as $cnt2$. Then the final number of odd numbers is $cnt1 \times (n-cnt2) + cnt2 \times (m-cnt1)$.

The time complexity is $O(\text{indices.length} + m + n)$, and the space complexity is $O(m+n)$.

<!-- tabs:start -->

```python
class Solution:
    def oddCells(self, m: int, n: int, indices: List[List[int]]) -> int:
        row = [0] * m
        col = [0] * n
        for r, c in indices:
            row[r] += 1
            col[c] += 1
        cnt1 = sum(v % 2 for v in row)
        cnt2 = sum(v % 2 for v in col)
        return cnt1 * (n - cnt2) + cnt2 * (m - cnt1)
```

```java
class Solution {
    public int oddCells(int m, int n, int[][] indices) {
        int[] row = new int[m];
        int[] col = new int[n];
        for (int[] e : indices) {
            int r = e[0], c = e[1];
            row[r]++;
            col[c]++;
        }
        int cnt1 = 0, cnt2 = 0;
        for (int v : row) {
            cnt1 += v % 2;
        }
        for (int v : col) {
            cnt2 += v % 2;
        }
        return cnt1 * (n - cnt2) + cnt2 * (m - cnt1);
    }
}
```

```cpp
class Solution {
public:
    int oddCells(int m, int n, vector<vector<int>>& indices) {
        vector<int> row(m);
        vector<int> col(n);
        for (auto& e : indices) {
            int r = e[0], c = e[1];
            row[r]++;
            col[c]++;
        }
        int cnt1 = 0, cnt2 = 0;
        for (int v : row) cnt1 += v % 2;
        for (int v : col) cnt2 += v % 2;
        return cnt1 * (n - cnt2) + cnt2 * (m - cnt1);
    }
};
```

```go
func oddCells(m int, n int, indices [][]int) int {
	row := make([]int, m)
	col := make([]int, n)
	for _, e := range indices {
		r, c := e[0], e[1]
		row[r]++
		col[c]++
	}
	cnt1, cnt2 := 0, 0
	for _, v := range row {
		cnt1 += v % 2
	}
	for _, v := range col {
		cnt2 += v % 2
	}
	return cnt1*(n-cnt2) + cnt2*(m-cnt1)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
