---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2713.Maximum%20Strictly%20Increasing%20Cells%20in%20a%20Matrix/README_EN.md
rating: 2387
source: Weekly Contest 347 Q4
tags:
    - Memoization
    - Array
    - Binary Search
    - Dynamic Programming
    - Matrix
    - Sorting
---

<!-- problem:start -->

# [2713. Maximum Strictly Increasing Cells in a Matrix](https://leetcode.com/problems/maximum-strictly-increasing-cells-in-a-matrix)

[中文文档](/solution/2700-2799/2713.Maximum%20Strictly%20Increasing%20Cells%20in%20a%20Matrix/README.md)

## Description

<!-- description:start -->

<p>Given a <strong>1-indexed</strong>&nbsp;<code>m x n</code> integer matrix <code>mat</code>, you can select any cell in the matrix as your <strong>starting cell</strong>.</p>

<p>From the starting cell, you can move to any other cell <strong>in the</strong> <strong>same row or column</strong>, but only if the value of the destination cell is <strong>strictly greater</strong> than the value of the current cell. You can repeat this process as many times as possible, moving from cell to cell until you can no longer make any moves.</p>

<p>Your task is to find the <strong>maximum number of cells</strong> that you can visit in the matrix by starting from some cell.</p>

<p>Return <em>an integer denoting the maximum number of cells that can be visited.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2713.Maximum%20Strictly%20Increasing%20Cells%20in%20a%20Matrix/images/diag1drawio.png" style="width: 200px; height: 176px;" /></strong></p>

<pre>
<strong>Input:</strong> mat = [[3,1],[3,4]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The image shows how we can visit 2 cells starting from row 1, column 2. It can be shown that we cannot visit more than 2 cells no matter where we start from, so the answer is 2. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2713.Maximum%20Strictly%20Increasing%20Cells%20in%20a%20Matrix/images/diag3drawio.png" style="width: 200px; height: 176px;" /></strong></p>

<pre>
<strong>Input:</strong> mat = [[1,1],[1,1]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> Since the cells must be strictly increasing, we can only visit one cell in this example. 
</pre>

<p><strong class="example">Example 3:</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2713.Maximum%20Strictly%20Increasing%20Cells%20in%20a%20Matrix/images/diag4drawio.png" style="width: 350px; height: 250px;" /></strong></p>

<pre>
<strong>Input:</strong> mat = [[3,1,6],[-9,5,7]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The image above shows how we can visit 4 cells starting from row 2, column 1. It can be shown that we cannot visit more than 4 cells no matter where we start from, so the answer is 4. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length&nbsp;</code></li>
	<li><code>n == mat[i].length&nbsp;</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= mat[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def maxIncreasingCells(self, mat: List[List[int]]) -> int:
        m, n = len(mat), len(mat[0])
        g = defaultdict(list)
        for i in range(m):
            for j in range(n):
                g[mat[i][j]].append((i, j))
        rowMax = [0] * m
        colMax = [0] * n
        ans = 0
        for _, pos in sorted(g.items()):
            mx = []
            for i, j in pos:
                mx.append(1 + max(rowMax[i], colMax[j]))
                ans = max(ans, mx[-1])
            for k, (i, j) in enumerate(pos):
                rowMax[i] = max(rowMax[i], mx[k])
                colMax[j] = max(colMax[j], mx[k])
        return ans
```

```java
class Solution {
    public int maxIncreasingCells(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        TreeMap<Integer, List<int[]>> g = new TreeMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                g.computeIfAbsent(mat[i][j], k -> new ArrayList<>()).add(new int[] {i, j});
            }
        }
        int[] rowMax = new int[m];
        int[] colMax = new int[n];
        int ans = 0;
        for (var e : g.entrySet()) {
            var pos = e.getValue();
            int[] mx = new int[pos.size()];
            int k = 0;
            for (var p : pos) {
                int i = p[0], j = p[1];
                mx[k] = Math.max(rowMax[i], colMax[j]) + 1;
                ans = Math.max(ans, mx[k++]);
            }
            for (k = 0; k < mx.length; ++k) {
                int i = pos.get(k)[0], j = pos.get(k)[1];
                rowMax[i] = Math.max(rowMax[i], mx[k]);
                colMax[j] = Math.max(colMax[j], mx[k]);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxIncreasingCells(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        map<int, vector<pair<int, int>>> g;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                g[mat[i][j]].emplace_back(i, j);
            }
        }
        vector<int> rowMax(m);
        vector<int> colMax(n);
        int ans = 0;
        for (auto& [_, pos] : g) {
            vector<int> mx;
            for (auto& [i, j] : pos) {
                mx.push_back(max(rowMax[i], colMax[j]) + 1);
                ans = max(ans, mx.back());
            }
            for (int k = 0; k < mx.size(); ++k) {
                auto& [i, j] = pos[k];
                rowMax[i] = max(rowMax[i], mx[k]);
                colMax[j] = max(colMax[j], mx[k]);
            }
        }
        return ans;
    }
};
```

```go
func maxIncreasingCells(mat [][]int) (ans int) {
	m, n := len(mat), len(mat[0])
	g := map[int][][2]int{}
	for i, row := range mat {
		for j, v := range row {
			g[v] = append(g[v], [2]int{i, j})
		}
	}
	nums := make([]int, 0, len(g))
	for k := range g {
		nums = append(nums, k)
	}
	sort.Ints(nums)
	rowMax := make([]int, m)
	colMax := make([]int, n)
	for _, k := range nums {
		pos := g[k]
		mx := make([]int, len(pos))
		for i, p := range pos {
			mx[i] = max(rowMax[p[0]], colMax[p[1]]) + 1
			ans = max(ans, mx[i])
		}
		for i, p := range pos {
			rowMax[p[0]] = max(rowMax[p[0]], mx[i])
			colMax[p[1]] = max(colMax[p[1]], mx[i])
		}
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
