---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3402.Minimum%20Operations%20to%20Make%20Columns%20Strictly%20Increasing/README.md
rating: 1245
source: 第 430 场周赛 Q1
tags:
    - 贪心
    - 数组
    - 矩阵
---

<!-- problem:start -->

# [3402. 使每一列严格递增的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-make-columns-strictly-increasing)

[English Version](/solution/3400-3499/3402.Minimum%20Operations%20to%20Make%20Columns%20Strictly%20Increasing/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由&nbsp;<b>非负&nbsp;</b>整数组成的 <code>m x n</code> 矩阵 <code>grid</code>。</p>

<p>在一次操作中，你可以将任意元素 <code>grid[i][j]</code> 的值增加 1。</p>

<p>返回使 <code>grid</code> 的所有列&nbsp;<strong>严格递增&nbsp;</strong>所需的&nbsp;<strong>最少&nbsp;</strong>操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">grid = [[3,2],[1,3],[3,4],[0,1]]</span></p>

<p><strong>输出:</strong> <span class="example-io">15</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>为了让第 <code>0</code>&nbsp;列严格递增，可以对 <code>grid[1][0]</code> 执行 3 次操作，对 <code>grid[2][0]</code> 执行 2 次操作，对 <code>grid[3][0]</code> 执行 6 次操作。</li>
	<li>为了让第 <code>1</code>&nbsp;列严格递增，可以对 <code>grid[3][1]</code> 执行 4 次操作。</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3402.Minimum%20Operations%20to%20Make%20Columns%20Strictly%20Increasing/images/firstexample.png" style="width: 200px; height: 347px;" /></div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">grid = [[3,2,1],[2,1,0],[1,2,3]]</span></p>

<p><strong>输出:</strong> <span class="example-io">12</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>为了让第 <code>0</code>&nbsp;列严格递增，可以对 <code>grid[1][0]</code> 执行 2 次操作，对 <code>grid[2][0]</code> 执行 4 次操作。</li>
	<li>为了让第 <code>1</code>&nbsp;列严格递增，可以对 <code>grid[1][1]</code> 执行 2 次操作，对 <code>grid[2][1]</code> 执行 2 次操作。</li>
	<li>为了让第 <code>2</code>&nbsp;列严格递增，可以对 <code>grid[1][2]</code> 执行 2 次操作。</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3402.Minimum%20Operations%20to%20Make%20Columns%20Strictly%20Increasing/images/secondexample.png" style="width: 300px; height: 257px;" /></div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>0 &lt;= grid[i][j] &lt; 2500</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：逐列计算

我们可以逐列遍历矩阵，对于每一列，我们可以计算出使其严格递增所需的最少操作次数。具体地，对于每一列，我们可以维护一个变量 $\textit{pre}$，表示当前列中前一个元素的值。然后，我们从上到下遍历当前列，对于当前元素 $\textit{cur}$，如果 $\textit{pre} < \textit{cur}$，则说明当前元素已经大于前一个元素，我们只需要更新 $\textit{pre} = \textit{cur}$；否则，我们需要将当前元素增加到 $\textit{pre} + 1$，并将增加的次数累加到答案中。

时间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别是矩阵 $\textit{grid}$ 的行数和列数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumOperations(self, grid: List[List[int]]) -> int:
        ans = 0
        for col in zip(*grid):
            pre = -1
            for cur in col:
                if pre < cur:
                    pre = cur
                else:
                    pre += 1
                    ans += pre - cur
        return ans
```

#### Java

```java
class Solution {
    public int minimumOperations(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            int pre = -1;
            for (int i = 0; i < m; ++i) {
                int cur = grid[i][j];
                if (pre < cur) {
                    pre = cur;
                } else {
                    ++pre;
                    ans += pre - cur;
                }
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
    int minimumOperations(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            int pre = -1;
            for (int i = 0; i < m; ++i) {
                int cur = grid[i][j];
                if (pre < cur) {
                    pre = cur;
                } else {
                    ++pre;
                    ans += pre - cur;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minimumOperations(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	for j := 0; j < n; j++ {
		pre := -1
		for i := 0; i < m; i++ {
			cur := grid[i][j]
			if pre < cur {
				pre = cur
			} else {
				pre++
				ans += pre - cur
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function minimumOperations(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    let ans: number = 0;
    for (let j = 0; j < n; ++j) {
        let pre: number = -1;
        for (let i = 0; i < m; ++i) {
            const cur = grid[i][j];
            if (pre < cur) {
                pre = cur;
            } else {
                ++pre;
                ans += pre - cur;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
