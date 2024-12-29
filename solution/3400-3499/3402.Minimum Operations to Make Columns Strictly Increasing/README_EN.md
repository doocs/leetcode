---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3402.Minimum%20Operations%20to%20Make%20Columns%20Strictly%20Increasing/README_EN.md
---

<!-- problem:start -->

# [3402. Minimum Operations to Make Columns Strictly Increasing](https://leetcode.com/problems/minimum-operations-to-make-columns-strictly-increasing)

[中文文档](/solution/3400-3499/3402.Minimum%20Operations%20to%20Make%20Columns%20Strictly%20Increasing/README.md)

## Description

<!-- description:start -->

<p>You are given a <code>m x n</code> matrix <code>grid</code> consisting of <b>non-negative</b> integers.</p>

<p>In one operation, you can increment the value of any <code>grid[i][j]</code> by 1.</p>

<p>Return the <strong>minimum</strong> number of operations needed to make all columns of <code>grid</code> <strong>strictly increasing</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[3,2],[1,3],[3,4],[0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>To make the <code>0<sup>th</sup></code> column strictly increasing, we can apply 3 operations on <code>grid[1][0]</code>, 2 operations on <code>grid[2][0]</code>, and 6 operations on <code>grid[3][0]</code>.</li>
	<li>To make the <code>1<sup>st</sup></code> column strictly increasing, we can apply 4 operations on <code>grid[3][1]</code>.</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3402.Minimum%20Operations%20to%20Make%20Columns%20Strictly%20Increasing/images/firstexample.png" style="width: 200px; height: 347px;" /></div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[3,2,1],[2,1,0],[1,2,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>To make the <code>0<sup>th</sup></code> column strictly increasing, we can apply 2 operations on <code>grid[1][0]</code>, and 4 operations on <code>grid[2][0]</code>.</li>
	<li>To make the <code>1<sup>st</sup></code> column strictly increasing, we can apply 2 operations on <code>grid[1][1]</code>, and 2 operations on <code>grid[2][1]</code>.</li>
	<li>To make the <code>2<sup>nd</sup></code> column strictly increasing, we can apply 2 operations on <code>grid[1][2]</code>.</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3402.Minimum%20Operations%20to%20Make%20Columns%20Strictly%20Increasing/images/secondexample.png" style="width: 300px; height: 257px;" /></div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>0 &lt;= grid[i][j] &lt; 2500</code></li>
</ul>

<p>&nbsp;</p>
<div class="spoiler">
<div>
<pre>

&nbsp;</pre>

</div>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Column-wise Calculation

We can traverse the matrix column by column. For each column, we calculate the minimum number of operations required to make it strictly increasing. Specifically, for each column, we maintain a variable $\textit{pre}$ to represent the value of the previous element in the current column. Then, we traverse the current column from top to bottom. For the current element $\textit{cur}$, if $\textit{pre} < \textit{cur}$, it means the current element is already greater than the previous element, so we only need to update $\textit{pre} = \textit{cur}$. Otherwise, we need to increase the current element to $\textit{pre} + 1$ and add the number of increases to the answer.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the matrix $\textit{grid}$, respectively. The space complexity is $O(1)$.

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
