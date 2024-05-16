---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2711.Difference%20of%20Number%20of%20Distinct%20Values%20on%20Diagonals/README.md
rating: 1428
source: 第 347 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 矩阵
---

<!-- problem:start -->

# [2711. 对角线上不同值的数量差](https://leetcode.cn/problems/difference-of-number-of-distinct-values-on-diagonals)

[English Version](/solution/2700-2799/2711.Difference%20of%20Number%20of%20Distinct%20Values%20on%20Diagonals/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <code>0</code> 开始、大小为 <code>m x n</code> 的二维矩阵 <code>grid</code> ，请你求解大小同样为 <code>m x n</code> 的答案矩阵 <code>answer</code> 。</p>

<p>矩阵 <code>answer</code> 中每个单元格 <code>(r, c)</code> 的值可以按下述方式进行计算：</p>

<ul>
	<li>令 <code>topLeft[r][c]</code> 为矩阵 <code>grid</code> 中单元格 <code>(r, c)</code> 左上角对角线上 <strong>不同值</strong> 的数量。</li>
	<li>令 <code>bottomRight[r][c]</code> 为矩阵 <code>grid</code> 中单元格 <code>(r, c)</code> 右下角对角线上 <strong>不同值</strong> 的数量。</li>
</ul>

<p>然后 <code>answer[r][c] = |topLeft[r][c] - bottomRight[r][c]|</code> 。</p>

<p>返回矩阵 <code>answer</code> 。</p>

<p><strong>矩阵对角线</strong> 是从最顶行或最左列的某个单元格开始，向右下方向走到矩阵末尾的对角线。</p>

<p>如果单元格 <code>(r1, c1)</code> 和单元格 <code>(r, c) </code>属于同一条对角线且 <code>r1 &lt; r</code> ，则单元格 <code>(r1, c1)</code> 属于单元格 <code>(r, c)</code> 的左上对角线。类似地，可以定义右下对角线。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2711.Difference%20of%20Number%20of%20Distinct%20Values%20on%20Diagonals/images/ex2.png" style="width: 786px; height: 121px;" />
<pre>
<strong>输入：</strong>grid = [[1,2,3],[3,1,5],[3,2,1]]
<strong>输出：</strong>[[1,1,0],[1,0,1],[0,1,1]]
<strong>解释：</strong>第 1 个图表示最初的矩阵 grid 。&nbsp;
第 2 个图表示对单元格 (0,0) 计算，其中蓝色单元格是位于右下对角线的单元格。
第 3 个图表示对单元格 (1,2) 计算，其中红色单元格是位于左上对角线的单元格。
第 4 个图表示对单元格 (1,1) 计算，其中蓝色单元格是位于右下对角线的单元格，红色单元格是位于左上对角线的单元格。
- 单元格 (0,0) 的右下对角线包含 [1,1] ，而左上对角线包含 [] 。对应答案是 |1 - 0| = 1 。
- 单元格 (1,2) 的右下对角线包含 [] ，而左上对角线包含 [2] 。对应答案是 |0 - 1| = 1 。
- 单元格 (1,1) 的右下对角线包含 [1] ，而左上对角线包含 [1] 。对应答案是 |1 - 1| = 0 。
其他单元格的对应答案也可以按照这样的流程进行计算。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[1]]
<strong>输出：</strong>[[0]]
<strong>解释：</strong>- 单元格 (0,0) 的右下对角线包含 [] ，左上对角线包含 [] 。对应答案是 |0 - 0| = 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n, grid[i][j] &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def differenceOfDistinctValues(self, grid: List[List[int]]) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        ans = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                x, y = i, j
                s = set()
                while x and y:
                    x, y = x - 1, y - 1
                    s.add(grid[x][y])
                tl = len(s)
                x, y = i, j
                s = set()
                while x + 1 < m and y + 1 < n:
                    x, y = x + 1, y + 1
                    s.add(grid[x][y])
                br = len(s)
                ans[i][j] = abs(tl - br)
        return ans
```

```java
class Solution {
    public int[][] differenceOfDistinctValues(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int x = i, y = j;
                Set<Integer> s = new HashSet<>();
                while (x > 0 && y > 0) {
                    s.add(grid[--x][--y]);
                }
                int tl = s.size();
                x = i;
                y = j;
                s.clear();
                while (x < m - 1 && y < n - 1) {
                    s.add(grid[++x][++y]);
                }
                int br = s.size();
                ans[i][j] = Math.abs(tl - br);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> differenceOfDistinctValues(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> ans(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int x = i, y = j;
                unordered_set<int> s;
                while (x > 0 && y > 0) {
                    s.insert(grid[--x][--y]);
                }
                int tl = s.size();
                x = i;
                y = j;
                s.clear();
                while (x < m - 1 && y < n - 1) {
                    s.insert(grid[++x][++y]);
                }
                int br = s.size();
                ans[i][j] = abs(tl - br);
            }
        }
        return ans;
    }
};
```

```go
func differenceOfDistinctValues(grid [][]int) [][]int {
	m, n := len(grid), len(grid[0])
	ans := make([][]int, m)
	for i := range grid {
		ans[i] = make([]int, n)
		for j := range grid[i] {
			x, y := i, j
			s := map[int]bool{}
			for x > 0 && y > 0 {
				x, y = x-1, y-1
				s[grid[x][y]] = true
			}
			tl := len(s)
			x, y = i, j
			s = map[int]bool{}
			for x+1 < m && y+1 < n {
				x, y = x+1, y+1
				s[grid[x][y]] = true
			}
			br := len(s)
			ans[i][j] = abs(tl - br)
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function differenceOfDistinctValues(grid: number[][]): number[][] {
    const m = grid.length;
    const n = grid[0].length;
    const ans: number[][] = Array(m)
        .fill(0)
        .map(() => Array(n).fill(0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            let [x, y] = [i, j];
            const s = new Set<number>();
            while (x && y) {
                s.add(grid[--x][--y]);
            }
            const tl = s.size;
            [x, y] = [i, j];
            s.clear();
            while (x + 1 < m && y + 1 < n) {
                s.add(grid[++x][++y]);
            }
            const br = s.size;
            ans[i][j] = Math.abs(tl - br);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
