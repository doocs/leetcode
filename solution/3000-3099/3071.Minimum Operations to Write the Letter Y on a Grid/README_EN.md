# [3071. Minimum Operations to Write the Letter Y on a Grid](https://leetcode.com/problems/minimum-operations-to-write-the-letter-y-on-a-grid)

[中文文档](/solution/3000-3099/3071.Minimum%20Operations%20to%20Write%20the%20Letter%20Y%20on%20a%20Grid/README.md)

<!-- tags:Array,Hash Table,Counting,Matrix -->

<!-- difficulty:Medium -->

## Description

<p>You are given a <strong>0-indexed</strong> <code>n x n</code> grid where <code>n</code> is odd, and <code>grid[r][c]</code> is <code>0</code>, <code>1</code>, or <code>2</code>.</p>

<p>We say that a cell belongs to the Letter <strong>Y</strong> if it belongs to one of the following:</p>

<ul>
	<li>The diagonal starting at the top-left cell and ending at the center cell of the grid.</li>
	<li>The diagonal starting at the top-right cell and ending at the center cell of the grid.</li>
	<li>The vertical line starting at the center cell and ending at the bottom border of the grid.</li>
</ul>

<p>The Letter <strong>Y</strong> is written on the grid if and only if:</p>

<ul>
	<li>All values at cells belonging to the Y are equal.</li>
	<li>All values at cells not belonging to the Y are equal.</li>
	<li>The values at cells belonging to the Y are different from the values at cells not belonging to the Y.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of operations needed to write the letter Y on the grid given that in one operation you can change the value at any cell to</em> <code>0</code><em>,</em> <code>1</code><em>,</em> <em>or</em> <code>2</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3071.Minimum%20Operations%20to%20Write%20the%20Letter%20Y%20on%20a%20Grid/images/y2.png" style="width: 461px; height: 121px;" />
<pre>
<strong>Input:</strong> grid = [[1,2,2],[1,1,0],[0,1,0]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can write Y on the grid by applying the changes highlighted in blue in the image above. After the operations, all cells that belong to Y, denoted in bold, have the same value of 1 while those that do not belong to Y are equal to 0.
It can be shown that 3 is the minimum number of operations needed to write Y on the grid.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3071.Minimum%20Operations%20to%20Write%20the%20Letter%20Y%20on%20a%20Grid/images/y3.png" style="width: 701px; height: 201px;" />
<pre>
<strong>Input:</strong> grid = [[0,1,0,1,0],[2,1,0,1,2],[2,2,2,0,1],[2,2,2,2,2],[2,1,2,2,2]]
<strong>Output:</strong> 12
<strong>Explanation:</strong> We can write Y on the grid by applying the changes highlighted in blue in the image above. After the operations, all cells that belong to Y, denoted in bold, have the same value of 0 while those that do not belong to Y are equal to 2. 
It can be shown that 12 is the minimum number of operations needed to write Y on the grid.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 49 </code></li>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 2</code></li>
	<li><code>n</code> is odd.</li>
</ul>

## Solutions

### Solution 1: Counting

We use two arrays of length 3, `cnt1` and `cnt2`, to record the counts of cell values that belong to `Y` and do not belong to `Y`, respectively. Then we enumerate `i` and `j`, which represent the values of cells that belong to `Y` and do not belong to `Y`, respectively, to calculate the minimum number of operations.

The time complexity is $O(n^2)$, where $n$ is the size of the matrix. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minimumOperationsToWriteY(self, grid: List[List[int]]) -> int:
        n = len(grid)
        cnt1 = Counter()
        cnt2 = Counter()
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                a = i == j and i <= n // 2
                b = i + j == n - 1 and i <= n // 2
                c = j == n // 2 and i >= n // 2
                if a or b or c:
                    cnt1[x] += 1
                else:
                    cnt2[x] += 1
        return min(
            n * n - cnt1[i] - cnt2[j] for i in range(3) for j in range(3) if i != j
        )
```

```java
class Solution {
    public int minimumOperationsToWriteY(int[][] grid) {
        int n = grid.length;
        int[] cnt1 = new int[3];
        int[] cnt2 = new int[3];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                boolean a = i == j && i <= n / 2;
                boolean b = i + j == n - 1 && i <= n / 2;
                boolean c = j == n / 2 && i >= n / 2;
                if (a || b || c) {
                    ++cnt1[grid[i][j]];
                } else {
                    ++cnt2[grid[i][j]];
                }
            }
        }
        int ans = n * n;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (i != j) {
                    ans = Math.min(ans, n * n - cnt1[i] - cnt2[j]);
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumOperationsToWriteY(vector<vector<int>>& grid) {
        int n = grid.size();
        int cnt1[3]{};
        int cnt2[3]{};
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                bool a = i == j && i <= n / 2;
                bool b = i + j == n - 1 && i <= n / 2;
                bool c = j == n / 2 && i >= n / 2;
                if (a || b || c) {
                    ++cnt1[grid[i][j]];
                } else {
                    ++cnt2[grid[i][j]];
                }
            }
        }
        int ans = n * n;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (i != j) {
                    ans = min(ans, n * n - cnt1[i] - cnt2[j]);
                }
            }
        }
        return ans;
    }
};
```

```go
func minimumOperationsToWriteY(grid [][]int) int {
	n := len(grid)
	cnt1 := [3]int{}
	cnt2 := [3]int{}
	for i, row := range grid {
		for j, x := range row {
			a := i == j && i <= n/2
			b := i+j == n-1 && i <= n/2
			c := j == n/2 && i >= n/2
			if a || b || c {
				cnt1[x]++
			} else {
				cnt2[x]++
			}
		}
	}
	ans := n * n
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			if i != j {
				ans = min(ans, n*n-cnt1[i]-cnt2[j])
			}
		}
	}
	return ans
}
```

```ts
function minimumOperationsToWriteY(grid: number[][]): number {
    const n = grid.length;
    const cnt1: number[] = Array(3).fill(0);
    const cnt2: number[] = Array(3).fill(0);
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            const a = i === j && i <= n >> 1;
            const b = i + j === n - 1 && i <= n >> 1;
            const c = j === n >> 1 && i >= n >> 1;
            if (a || b || c) {
                ++cnt1[grid[i][j]];
            } else {
                ++cnt2[grid[i][j]];
            }
        }
    }
    let ans = n * n;
    for (let i = 0; i < 3; ++i) {
        for (let j = 0; j < 3; ++j) {
            if (i !== j) {
                ans = Math.min(ans, n * n - cnt1[i] - cnt2[j]);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
