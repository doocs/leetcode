---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1252.Cells%20with%20Odd%20Values%20in%20a%20Matrix/README.md
rating: 1283
source: 第 162 场周赛 Q1
tags:
    - 数组
    - 数学
    - 模拟
---

<!-- problem:start -->

# [1252. 奇数值单元格的数目](https://leetcode.cn/problems/cells-with-odd-values-in-a-matrix)

[English Version](/solution/1200-1299/1252.Cells%20with%20Odd%20Values%20in%20a%20Matrix/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <code>m x n</code> 的矩阵，最开始的时候，每个单元格中的值都是 <code>0</code>。</p>

<p>另有一个二维索引数组 <code>indices</code>，<code>indices[i] = [ri, ci]</code> 指向矩阵中的某个位置，其中 <code>ri</code> 和 <code>ci</code> 分别表示指定的行和列（<strong>从 <code>0</code> 开始编号</strong>）。</p>

<p>对 <code>indices[i]</code> 所指向的每个位置，应同时执行下述增量操作：</p>

<ol>
	<li><code>r<sub>i</sub></code> 行上的所有单元格，加 <code>1</code> 。</li>
	<li><code>c<sub>i</sub></code> 列上的所有单元格，加 <code>1</code> 。</li>
</ol>

<p>给你 <code>m</code>、<code>n</code> 和 <code>indices</code> 。请你在执行完所有 <code>indices</code> 指定的增量操作后，返回矩阵中 <strong>奇数值单元格</strong> 的数目。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1252.Cells%20with%20Odd%20Values%20in%20a%20Matrix/images/e1.png" style="height: 118px; width: 600px;" /></p>

<pre>
<strong>输入：</strong>m = 2, n = 3, indices = [[0,1],[1,1]]
<strong>输出：</strong>6
<strong>解释：</strong>最开始的矩阵是 [[0,0,0],[0,0,0]]。
第一次增量操作后得到 [[1,2,1],[0,1,0]]。
最后的矩阵是 [[1,3,1],[1,3,1]]，里面有 6 个奇数。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1252.Cells%20with%20Odd%20Values%20in%20a%20Matrix/images/e2.png" style="height: 150px; width: 600px;" /></p>

<pre>
<strong>输入：</strong>m = 2, n = 2, indices = [[1,1],[0,0]]
<strong>输出：</strong>0
<strong>解释：</strong>最后的矩阵是 [[2,2],[2,2]]，里面没有奇数。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= m, n <= 50</code></li>
	<li><code>1 <= indices.length <= 100</code></li>
	<li><code>0 <= r<sub>i</sub> < m</code></li>
	<li><code>0 <= c<sub>i</sub> < n</code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你可以设计一个时间复杂度为 <code>O(n + m + indices.length)</code> 且仅用 <code>O(n + m)</code> 额外空间的算法来解决此问题吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们创建一个矩阵 $g$ 来存放操作的结果。对于 $\text{indices}$ 中的每一对 $(r_i, c_i)$，我们将矩阵第 $r_i$ 行的所有数加 $1$，第 $c_i$ 列的所有元素加 $1$。

模拟结束后，遍历矩阵，统计奇数的个数。

时间复杂度 $O(k \times (m + n) + m \times n)$，空间复杂度 $O(m \times n)$。其中 $k$ 为 $\text{indices}$ 的长度。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

```cpp
class Solution {
public:
    int oddCells(int m, int n, vector<vector<int>>& indices) {
        vector<vector<int>> g(m, vector<int>(n));
        for (auto& e : indices) {
            int r = e[0], c = e[1];
            for (int i = 0; i < m; ++i) {
                ++g[i][c];
            }
            for (int j = 0; j < n; ++j) {
                ++g[r][j];
            }
        }
        int ans = 0;
        for (auto& row : g) {
            for (int v : row) {
                ans += v % 2;
            }
        }
        return ans;
    }
};
```

#### Go

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

### 方法二：空间优化

我们可以使用行数组 $\text{row}$ 和列数组 $\text{col}$ 来记录每一行、每一列被增加的次数。对于 $\text{indices}$ 中的每一对 $(r_i, c_i)$，我们将 $\text{row}[r_i]$ 和 $\text{col}[c_i]$ 分别加 $1$。

操作结束后，可以算出 $(i, j)$ 位置的计数为 $\text{row}[i]+\text{col}[j]$。遍历矩阵，统计奇数的个数。

时间复杂度 $O(k + m \times n)$，空间复杂度 $O(m + n)$。其中 $k$ 为 $\text{indices}$ 的长度。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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
        for (int i : row) {
            for (int j : col) {
                ans += (i + j) % 2;
            }
        }
        return ans;
    }
};
```

#### Go

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

### 方法三：数学优化

我们注意到，只有当 $\text{row}[i]$ 和 $\text{col}[j]$ 中恰好为“一奇一偶”时，矩阵 $(i, j)$ 位置的数才会是奇数。

我们统计 $\text{row}$ 中的奇数个数，记为 $\text{cnt1}$；而 $\text{col}$ 中的奇数个数，记为 $\text{cnt2}$。那么最终得到的奇数个数为 $\text{cnt1} \times (n - \text{cnt2}) + \text{cnt2} \times (m - \text{cnt1})$。

时间复杂度 $O(k + m + n)$，空间复杂度 $O(m + n)$。其中 $k$ 为 $\text{indices}$ 的长度。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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
        for (int v : row) {
            cnt1 += v % 2;
        }
        for (int v : col) {
            cnt2 += v % 2;
        }
        return cnt1 * (n - cnt2) + cnt2 * (m - cnt1);
    }
};
```

#### Go

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
