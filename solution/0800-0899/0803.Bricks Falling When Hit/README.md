---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0803.Bricks%20Falling%20When%20Hit/README.md
tags:
    - 并查集
    - 数组
    - 矩阵
---

<!-- problem:start -->

# [803. 打砖块](https://leetcode.cn/problems/bricks-falling-when-hit)

[English Version](/solution/0800-0899/0803.Bricks%20Falling%20When%20Hit/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有一个 <code>m x n</code> 的二元网格<meta charset="UTF-8" />&nbsp;<code>grid</code>&nbsp;，其中 <code>1</code> 表示砖块，<code>0</code> 表示空白。砖块 <strong>稳定</strong>（不会掉落）的前提是：</p>

<ul>
	<li>一块砖直接连接到网格的顶部，或者</li>
	<li>至少有一块相邻（4&nbsp;个方向之一）砖块<strong> 稳定 </strong>不会掉落时</li>
</ul>

<p>给你一个数组 <code>hits</code> ，这是需要依次消除砖块的位置。每当消除&nbsp;<code>hits[i] = (rowi, coli)</code> 位置上的砖块时，对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而 <strong>掉落</strong> 。一旦砖块掉落，它会 <strong>立即</strong> 从网格&nbsp;<code>grid</code>&nbsp;中消失（即，它不会落在其他稳定的砖块上）。</p>

<p>返回一个数组 <code>result</code> ，其中 <code>result[i]</code> 表示第 <code>i</code> 次消除操作对应掉落的砖块数目。</p>

<p><strong>注意</strong>，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
<strong>输出：</strong>[2]
<strong>解释：</strong>网格开始为：
[[1,0,0,0]，
 [<strong>1</strong>,1,1,0]]
消除 (1,0) 处加粗的砖块，得到网格：
[[1,0,0,0]
 [0,<strong>1</strong>,<strong>1</strong>,0]]
两个加粗的砖不再稳定，因为它们不再与顶部相连，也不再与另一个稳定的砖相邻，因此它们将掉落。得到网格：
[[1,0,0,0],
 [0,0,0,0]]
因此，结果为 [2] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
<strong>输出：</strong>[0,0]
<strong>解释：</strong>网格开始为：
[[1,0,0,0],
 [1,<strong>1</strong>,0,0]]
消除 (1,1) 处加粗的砖块，得到网格：
[[1,0,0,0],
 [1,0,0,0]]
剩下的砖都很稳定，所以不会掉落。网格保持不变：
[[1,0,0,0], 
 [<strong>1</strong>,0,0,0]]
接下来消除 (1,0) 处加粗的砖块，得到网格：
[[1,0,0,0],
 [0,0,0,0]]
剩下的砖块仍然是稳定的，所以不会有砖块掉落。
因此，结果为 [0,0] 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
	<li><code>1 &lt;= hits.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>hits[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i&nbsp;</sub>&lt;= m - 1</code></li>
	<li><code>0 &lt;=&nbsp;y<sub>i</sub> &lt;= n - 1</code></li>
	<li>所有 <code>(x<sub>i</sub>, y<sub>i</sub>)</code> 互不相同</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def hitBricks(self, grid: List[List[int]], hits: List[List[int]]) -> List[int]:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa != pb:
                size[pb] += size[pa]
                p[pa] = pb

        m, n = len(grid), len(grid[0])
        p = list(range(m * n + 1))
        size = [1] * len(p)
        g = deepcopy(grid)
        for i, j in hits:
            g[i][j] = 0
        for j in range(n):
            if g[0][j] == 1:
                union(j, m * n)
        for i in range(1, m):
            for j in range(n):
                if g[i][j] == 0:
                    continue
                if g[i - 1][j] == 1:
                    union(i * n + j, (i - 1) * n + j)
                if j > 0 and g[i][j - 1] == 1:
                    union(i * n + j, i * n + j - 1)
        ans = []
        for i, j in hits[::-1]:
            if grid[i][j] == 0:
                ans.append(0)
                continue
            g[i][j] = 1
            prev = size[find(m * n)]
            if i == 0:
                union(j, m * n)
            for a, b in [(-1, 0), (1, 0), (0, 1), (0, -1)]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and g[x][y] == 1:
                    union(i * n + j, x * n + y)
            curr = size[find(m * n)]
            ans.append(max(0, curr - prev - 1))
        return ans[::-1]
```

```java
class Solution {
    private int[] p;
    private int[] size;

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int m = grid.length;
        int n = grid[0].length;
        p = new int[m * n + 1];
        size = new int[m * n + 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        int[][] g = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                g[i][j] = grid[i][j];
            }
        }
        for (int[] h : hits) {
            g[h[0]][h[1]] = 0;
        }
        for (int j = 0; j < n; ++j) {
            if (g[0][j] == 1) {
                union(j, m * n);
            }
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (g[i][j] == 0) {
                    continue;
                }
                if (g[i - 1][j] == 1) {
                    union(i * n + j, (i - 1) * n + j);
                }
                if (j > 0 && g[i][j - 1] == 1) {
                    union(i * n + j, i * n + j - 1);
                }
            }
        }
        int[] ans = new int[hits.length];
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = hits.length - 1; k >= 0; --k) {
            int i = hits[k][0];
            int j = hits[k][1];
            if (grid[i][j] == 0) {
                continue;
            }
            g[i][j] = 1;
            int prev = size[find(m * n)];
            if (i == 0) {
                union(j, m * n);
            }
            for (int l = 0; l < 4; ++l) {
                int x = i + dirs[l];
                int y = j + dirs[l + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 1) {
                    union(i * n + j, x * n + y);
                }
            }
            int curr = size[find(m * n)];
            ans[k] = Math.max(0, curr - prev - 1);
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) {
            size[pb] += size[pa];
            p[pa] = pb;
        }
    }
}
```

```cpp
class Solution {
public:
    vector<int> p;
    vector<int> size;

    vector<int> hitBricks(vector<vector<int>>& grid, vector<vector<int>>& hits) {
        int m = grid.size(), n = grid[0].size();
        p.resize(m * n + 1);
        size.resize(m * n + 1);
        for (int i = 0; i < p.size(); ++i) {
            p[i] = i;
            size[i] = 1;
        }
        vector<vector<int>> g(m, vector<int>(n));
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                g[i][j] = grid[i][j];
        for (auto& h : hits) g[h[0]][h[1]] = 0;
        for (int j = 0; j < n; ++j)
            if (g[0][j] == 1)
                merge(j, m * n);
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (g[i][j] == 0) continue;
                if (g[i - 1][j] == 1) merge(i * n + j, (i - 1) * n + j);
                if (j > 0 && g[i][j - 1] == 1) merge(i * n + j, i * n + j - 1);
            }
        }
        vector<int> ans(hits.size());
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = hits.size() - 1; k >= 0; --k) {
            int i = hits[k][0], j = hits[k][1];
            if (grid[i][j] == 0) continue;
            g[i][j] = 1;
            int prev = size[find(m * n)];
            if (i == 0) merge(j, m * n);
            for (int l = 0; l < 4; ++l) {
                int x = i + dirs[l], y = j + dirs[l + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 1)
                    merge(i * n + j, x * n + y);
            }
            int curr = size[find(m * n)];
            ans[k] = max(0, curr - prev - 1);
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void merge(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            size[pb] += size[pa];
            p[pa] = pb;
        }
    }
};
```

```go
func hitBricks(grid [][]int, hits [][]int) []int {
	m, n := len(grid), len(grid[0])
	p := make([]int, m*n+1)
	size := make([]int, len(p))
	for i := range p {
		p[i] = i
		size[i] = 1
	}

	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	union := func(a, b int) {
		pa, pb := find(a), find(b)
		if pa != pb {
			size[pb] += size[pa]
			p[pa] = pb
		}
	}

	g := make([][]int, m)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = grid[i][j]
		}
	}
	for _, h := range hits {
		g[h[0]][h[1]] = 0
	}
	for j, v := range g[0] {
		if v == 1 {
			union(j, m*n)
		}
	}
	for i := 1; i < m; i++ {
		for j := 0; j < n; j++ {
			if g[i][j] == 0 {
				continue
			}
			if g[i-1][j] == 1 {
				union(i*n+j, (i-1)*n+j)
			}
			if j > 0 && g[i][j-1] == 1 {
				union(i*n+j, i*n+j-1)
			}
		}
	}
	ans := make([]int, len(hits))
	dirs := []int{-1, 0, 1, 0, -1}
	for k := len(hits) - 1; k >= 0; k-- {
		i, j := hits[k][0], hits[k][1]
		if grid[i][j] == 0 {
			continue
		}
		g[i][j] = 1
		prev := size[find(m*n)]
		if i == 0 {
			union(j, m*n)
		}
		for l := 0; l < 4; l++ {
			x, y := i+dirs[l], j+dirs[l+1]
			if x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 1 {
				union(i*n+j, x*n+y)
			}
		}
		curr := size[find(m*n)]
		ans[k] = max(0, curr-prev-1)
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
