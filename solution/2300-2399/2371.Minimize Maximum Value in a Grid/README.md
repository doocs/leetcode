---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2371.Minimize%20Maximum%20Value%20in%20a%20Grid/README.md
tags:
    - 并查集
    - 图
    - 拓扑排序
    - 数组
    - 矩阵
    - 排序
---

<!-- problem:start -->

# [2371. 最小化网格中的最大值 🔒](https://leetcode.cn/problems/minimize-maximum-value-in-a-grid)

[English Version](/solution/2300-2399/2371.Minimize%20Maximum%20Value%20in%20a%20Grid/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个包含&nbsp;<strong>不同&nbsp;</strong>正整数的 <code>m × n</code> 整数矩阵 <code>grid</code>。</p>

<p>必须将矩阵中的每一个整数替换为正整数，且满足以下条件:</p>

<ul>
	<li>在替换之后，同行或同列中的每两个元素的&nbsp;<strong>相对&nbsp;</strong>顺序应该保持&nbsp;<strong>不变</strong>。</li>
	<li>替换后矩阵中的 <strong>最大</strong> 数目应尽可能 <strong>小</strong>。</li>
</ul>

<p>如果对于原始矩阵中的所有元素对，使&nbsp;<code>grid[r<sub>1</sub>][c<sub>1</sub>] &gt; grid[r<sub>2</sub>][c<sub>2</sub>]</code>，其中要么&nbsp;<code>r<sub>1</sub> == r<sub>2</sub></code> ，要么&nbsp;<code>c<sub>1</sub> == c<sub>2</sub></code>，则相对顺序保持不变。那么在替换之后一定满足&nbsp;<code>grid[r<sub>1</sub>][c<sub>1</sub>] &gt; grid[r<sub>2</sub>][c<sub>2</sub>]</code>。</p>

<p>例如，如果&nbsp;<code>grid = [[2, 4, 5], [7, 3, 9]]</code>，那么一个好的替换可以是 <code>grid = [[1, 2, 3], [2, 1, 4]]</code> 或 <code>grid = [[1, 2, 3], [3, 1, 4]]</code>。</p>

<p>返回&nbsp;<em><strong>结果&nbsp;</strong>矩阵</em>。如果有多个答案，则返回其中&nbsp;<strong>任何&nbsp;</strong>一个。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2371.Minimize%20Maximum%20Value%20in%20a%20Grid/images/grid2drawio.png" />
<pre>
<strong>输入:</strong> grid = [[3,1],[2,5]]
<strong>输出:</strong> [[2,1],[1,2]]
<strong>解释:</strong> 上面的图显示了一个有效的替换。
矩阵中的最大值是 2。可以证明，不能得到更小的值。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> grid = [[10]]
<strong>输出:</strong> [[1]]
<strong>解释:</strong> 我们将矩阵中唯一的数字替换为 1。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>9</sup></code></li>
	<li><code>grid</code> 由不同的整数组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 贪心

由于可以将每一个数字重新填入并且使最终矩阵的最大值最小化，可考虑贪心。

矩阵中每一个数字不一样，可考虑哈希表或数组记录每个数字对应的位置。

将所有数字排序。然后从小到大填入新的数字，每次填入的数字为当前行和列的较大值再加一，同时用新填入的数字更新当前行和列的最大值。

时间复杂度 $O(mn\log mn)$，空间复杂度 $O(mn)$。其中 $m$ 和 $n$ 是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minScore(self, grid: List[List[int]]) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        nums = [(v, i, j) for i, row in enumerate(grid) for j, v in enumerate(row)]
        nums.sort()
        row_max = [0] * m
        col_max = [0] * n
        ans = [[0] * n for _ in range(m)]
        for _, i, j in nums:
            ans[i][j] = max(row_max[i], col_max[j]) + 1
            row_max[i] = col_max[j] = ans[i][j]
        return ans
```

#### Java

```java
class Solution {
    public int[][] minScore(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<int[]> nums = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                nums.add(new int[] {grid[i][j], i, j});
            }
        }
        Collections.sort(nums, (a, b) -> a[0] - b[0]);
        int[] rowMax = new int[m];
        int[] colMax = new int[n];
        int[][] ans = new int[m][n];
        for (int[] num : nums) {
            int i = num[1], j = num[2];
            ans[i][j] = Math.max(rowMax[i], colMax[j]) + 1;
            rowMax[i] = ans[i][j];
            colMax[j] = ans[i][j];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> minScore(vector<vector<int>>& grid) {
        vector<tuple<int, int, int>> nums;
        int m = grid.size(), n = grid[0].size();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                nums.push_back({grid[i][j], i, j});
            }
        }
        sort(nums.begin(), nums.end());
        vector<int> rowMax(m);
        vector<int> colMax(n);
        vector<vector<int>> ans(m, vector<int>(n));
        for (auto [_, i, j] : nums) {
            ans[i][j] = max(rowMax[i], colMax[j]) + 1;
            rowMax[i] = colMax[j] = ans[i][j];
        }
        return ans;
    }
};
```

#### Go

```go
func minScore(grid [][]int) [][]int {
	m, n := len(grid), len(grid[0])
	nums := [][]int{}
	for i, row := range grid {
		for j, v := range row {
			nums = append(nums, []int{v, i, j})
		}
	}
	sort.Slice(nums, func(i, j int) bool { return nums[i][0] < nums[j][0] })
	rowMax := make([]int, m)
	colMax := make([]int, n)
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	for _, num := range nums {
		i, j := num[1], num[2]
		ans[i][j] = max(rowMax[i], colMax[j]) + 1
		rowMax[i] = ans[i][j]
		colMax[j] = ans[i][j]
	}
	return ans
}
```

#### TypeScript

```ts
function minScore(grid: number[][]): number[][] {
    const m = grid.length;
    const n = grid[0].length;
    const nums = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            nums.push([grid[i][j], i, j]);
        }
    }
    nums.sort((a, b) => a[0] - b[0]);
    const rowMax = new Array(m).fill(0);
    const colMax = new Array(n).fill(0);
    const ans = Array.from({ length: m }, _ => new Array(n));
    for (const [_, i, j] of nums) {
        ans[i][j] = Math.max(rowMax[i], colMax[j]) + 1;
        rowMax[i] = colMax[j] = ans[i][j];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
