---
comments: true
difficulty: 简单
rating: 1246
source: 第 519 场周赛 Q1
---

<!-- problem:start -->

# [4052. 行列循环移位](https://leetcode.cn/problems/cyclically-shift-rows-and-columns)

[English Version](/solution/4000-4099/4052.Cyclically%20Shift%20Rows%20and%20Columns/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>、一个大小为 <code>n x n</code> 的二维整数数组 <code>grid</code>，以及两个长度均为 <code>n</code> 的整数数组 <code>rowShift</code> 和 <code>colShift</code>，其中：</p>

<ul>
	<li><code>rowShift[i]</code> 表示将 <code>grid</code> 的第 <code>i</code>&nbsp;行向左<strong>&nbsp;循环移位</strong>&nbsp;的位数。</li>
	<li><code>colShift[j]</code> 表示将 <code>grid</code> 的第 <code>j</code>&nbsp;列向上&nbsp;<strong>循环移位&nbsp;</strong>的位数。</li>
</ul>

<p>首先按照 <code>rowShift</code> 对每一行进行循环移位，然后按照 <code>colShift</code> 对每一列进行循环移位。</p>

<p>返回完成所有移位操作后的网格。</p>

<p>将第 <code>i</code>&nbsp;行向左<strong>&nbsp;循环移位</strong> <code>k</code> 位时，只移动该行。原本位于第 <code>j</code> 列的元素会移动到第 <code>(j - k + n) % n</code> 列，其余各行保持不变。</p>

<p>将第 <code>j</code>&nbsp;列向上<strong>&nbsp;循环移位</strong> <code>k</code> 位时，只移动该列。原本位于第 <code>i</code> 行的元素会移动到第 <code>(i - k + n) % n</code> 行，其余各列保持不变。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 2, <code>grid</code> = [[1,2],[3,4]], rowShift = [1,0], colShift = [0,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">[[2,4],[3,1]]</span></p>

<p><strong>解释：</strong></p>

<p><code>grid</code> 的变化过程如下：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4052.Cyclically%20Shift%20Rows%20and%20Columns/images/4743-1.png" style="width: 760px; height: 92px;" /></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 3, <code>grid</code> = [[1,2,3],[4,5,6],[7,8,9]], rowShift = [1,2,0], colShift = [2,2,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">[[7,8,5],[2,3,9],[6,4,1]]</span></p>

<p><strong>解释：</strong></p>

<p><code>grid</code> 的变化过程如下：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4052.Cyclically%20Shift%20Rows%20and%20Columns/images/4743-2.png" style="width: 750px; height: 349px;" /></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == grid.length == grid[i].length &lt;= 10</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 100</code></li>
	<li><code>rowShift.length == colShift.length == n</code></li>
	<li><code>0 &lt;= rowShift[i], colShift[i] &lt; n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

<!-- thinking:start -->

> **思考**
>
> $n \le 10$，按题意做两遍移位即可通过，不必先把映射压成一次下标计算。
>
> 必须先整行左移，再按**新的列下标**做上移。列循环用的是行移位之后的列，不能拿原来的 $j$ 去套 $\textit{colShift}$。
>
> 因此先用中间网格记下每一行的左移结果，再写到答案网格。

<!-- thinking:end -->

题目要求先按 $\textit{rowShift}$ 对每一行做循环左移，再按 $\textit{colShift}$ 对每一列做循环上移。

创建中间矩阵 $t$。原网格中的 $\textit{grid}[i][j]$ 向左循环 $\textit{rowShift}[i]$ 位后，落到

$$
t[i][(j - \textit{rowShift}[i] + n) \bmod n]
$$

再创建答案矩阵 $\textit{ans}$。$t[i][j]$ 向上循环 $\textit{colShift}[j]$ 位后，落到

$$
\textit{ans}[(i - \textit{colShift}[j] + n) \bmod n][j]
$$

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是网格的边长。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def cyclicShift(
        self, n: int, grid: list[list[int]], rowShift: list[int], colShift: list[int]
    ) -> list[list[int]]:
        t = [[0] * n for _ in range(n)]
        for i in range(n):
            for j in range(n):
                t[i][(j - rowShift[i] + n) % n] = grid[i][j]
        ans = [[0] * n for _ in range(n)]
        for j in range(n):
            for i in range(n):
                ans[(i - colShift[j] + n) % n][j] = t[i][j]
        return ans
```

#### Java

```java
class Solution {
    public int[][] cyclicShift(int n, int[][] grid, int[] rowShift, int[] colShift) {
        int[][] t = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                t[i][(j - rowShift[i] + n) % n] = grid[i][j];
            }
        }
        int[][] ans = new int[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                ans[(i - colShift[j] + n) % n][j] = t[i][j];
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
    vector<vector<int>> cyclicShift(int n, vector<vector<int>>& grid, vector<int>& rowShift, vector<int>& colShift) {
        vector<vector<int>> t(n, vector<int>(n));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                t[i][(j - rowShift[i] + n) % n] = grid[i][j];
            }
        }
        vector<vector<int>> ans(n, vector<int>(n));
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                ans[(i - colShift[j] + n) % n][j] = t[i][j];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func cyclicShift(n int, grid [][]int, rowShift []int, colShift []int) [][]int {
	t := make([][]int, n)
	for i := range t {
		t[i] = make([]int, n)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			t[i][(j-rowShift[i]+n)%n] = grid[i][j]
		}
	}
	ans := make([][]int, n)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	for j := 0; j < n; j++ {
		for i := 0; i < n; i++ {
			ans[(i-colShift[j]+n)%n][j] = t[i][j]
		}
	}
	return ans
}
```

#### TypeScript

```ts
function cyclicShift(
    n: number,
    grid: number[][],
    rowShift: number[],
    colShift: number[],
): number[][] {
    const t = Array.from({ length: n }, () => Array(n).fill(0));
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            t[i][(j - rowShift[i] + n) % n] = grid[i][j];
        }
    }
    const ans = Array.from({ length: n }, () => Array(n).fill(0));
    for (let j = 0; j < n; j++) {
        for (let i = 0; i < n; i++) {
            ans[(i - colShift[j] + n) % n][j] = t[i][j];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
