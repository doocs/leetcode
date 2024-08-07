---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3239.Minimum%20Number%20of%20Flips%20to%20Make%20Binary%20Grid%20Palindromic%20I/README.md
tags:
    - 数组
    - 双指针
    - 矩阵
---

<!-- problem:start -->

# [3239. 最少翻转次数使二进制矩阵回文 I](https://leetcode.cn/problems/minimum-number-of-flips-to-make-binary-grid-palindromic-i)

[English Version](/solution/3200-3299/3239.Minimum%20Number%20of%20Flips%20to%20Make%20Binary%20Grid%20Palindromic%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的二进制矩阵&nbsp;<code>grid</code>&nbsp;。</p>

<p>如果矩阵中一行或者一列从前往后与从后往前读是一样的，那么我们称这一行或者这一列是 <strong>回文</strong> 的。</p>

<p>你可以将 <code>grid</code>&nbsp;中任意格子的值 <strong>翻转</strong>&nbsp;，也就是将格子里的值从 <code>0</code>&nbsp;变成 <code>1</code>&nbsp;，或者从 <code>1</code>&nbsp;变成 <code>0</code>&nbsp;。</p>

<p>请你返回 <strong>最少</strong>&nbsp;翻转次数，使得矩阵 <strong>要么</strong>&nbsp;所有行是 <strong>回文的</strong>&nbsp;，要么所有列是 <strong>回文的</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[1,0,0],[0,0,0],[0,0,1]]</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><b>解释：</b></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3239.Minimum%20Number%20of%20Flips%20to%20Make%20Binary%20Grid%20Palindromic%20I/images/screenshot-from-2024-07-08-00-20-10.png" style="width: 420px; height: 108px;" /></p>

<p>将高亮的格子翻转，得到所有行都是回文的。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = </span>[[0,1],[0,1],[0,0]]</p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3239.Minimum%20Number%20of%20Flips%20to%20Make%20Binary%20Grid%20Palindromic%20I/images/screenshot-from-2024-07-08-00-31-23.png" style="width: 300px; height: 100px;" /></p>

<p>将高亮的格子翻转，得到所有列都是回文的。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[1],[0]]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p>所有行已经是回文的。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m * n &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们分别计算行和列的翻转次数，记为 $\textit{cnt1}$ 和 $\textit{cnt2}$，最后取二者的最小值即可。

时间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别是矩阵 $\textit{grid}$ 的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minFlips(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        cnt1 = cnt2 = 0
        for row in grid:
            for j in range(n // 2):
                if row[j] != row[n - j - 1]:
                    cnt1 += 1
        for j in range(n):
            for i in range(m // 2):
                if grid[i][j] != grid[m - i - 1][j]:
                    cnt2 += 1
        return min(cnt1, cnt2)
```

#### Java

```java
class Solution {
    public int minFlips(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int cnt1 = 0, cnt2 = 0;
        for (var row : grid) {
            for (int j = 0; j < n / 2; ++j) {
                if (row[j] != row[n - j - 1]) {
                    ++cnt1;
                }
            }
        }
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m / 2; ++i) {
                if (grid[i][j] != grid[m - i - 1][j]) {
                    ++cnt2;
                }
            }
        }
        return Math.min(cnt1, cnt2);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minFlips(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int cnt1 = 0, cnt2 = 0;
        for (const auto& row : grid) {
            for (int j = 0; j < n / 2; ++j) {
                if (row[j] != row[n - j - 1]) {
                    ++cnt1;
                }
            }
        }
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m / 2; ++i) {
                if (grid[i][j] != grid[m - i - 1][j]) {
                    ++cnt2;
                }
            }
        }
        return min(cnt1, cnt2);
    }
};
```

#### Go

```go
func minFlips(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	cnt1, cnt2 := 0, 0
	for _, row := range grid {
		for j := 0; j < n/2; j++ {
			if row[j] != row[n-j-1] {
				cnt1++
			}
		}
	}
	for j := 0; j < n; j++ {
		for i := 0; i < m/2; i++ {
			if grid[i][j] != grid[m-i-1][j] {
				cnt2++
			}
		}
	}
	return min(cnt1, cnt2)
}
```

#### TypeScript

```ts
function minFlips(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    let [cnt1, cnt2] = [0, 0];
    for (const row of grid) {
        for (let j = 0; j < n / 2; ++j) {
            if (row[j] !== row[n - 1 - j]) {
                ++cnt1;
            }
        }
    }
    for (let j = 0; j < n; ++j) {
        for (let i = 0; i < m / 2; ++i) {
            if (grid[i][j] !== grid[m - 1 - i][j]) {
                ++cnt2;
            }
        }
    }
    return Math.min(cnt1, cnt2);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
