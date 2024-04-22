# [3122. Minimum Number of Operations to Satisfy Conditions](https://leetcode.com/problems/minimum-number-of-operations-to-satisfy-conditions)

[中文文档](/solution/3100-3199/3122.Minimum%20Number%20of%20Operations%20to%20Satisfy%20Conditions/README.md)

<!-- tags: -->

## Description

<p>You are given a 2D matrix <code>grid</code> of size <code>m x n</code>. In one <strong>operation</strong>, you can change the value of <strong>any</strong> cell to <strong>any</strong> non-negative number. You need to perform some <strong>operations</strong> such that each cell <code>grid[i][j]</code> is:</p>

<ul>
	<li>Equal to the cell below it, i.e. <code>grid[i][j] == grid[i + 1][j]</code> (if it exists).</li>
	<li>Different from the cell to its right, i.e. <code>grid[i][j] != grid[i][j + 1]</code> (if it exists).</li>
</ul>

<p>Return the <strong>minimum</strong> number of operations needed.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,0,2],[1,0,2]]</span></p>

<p><strong>Output:</strong> 0</p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3122.Minimum%20Number%20of%20Operations%20to%20Satisfy%20Conditions/images/examplechanged.png" style="width: 254px; height: 186px;padding: 10px; background: #fff; border-radius: .5rem;" /></strong></p>

<p>All the cells in the matrix already satisfy the properties.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,1,1],[0,0,0]]</span></p>

<p><strong>Output:</strong> 3</p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3122.Minimum%20Number%20of%20Operations%20to%20Satisfy%20Conditions/images/example21.png" style="width: 254px; height: 186px;padding: 10px; background: #fff; border-radius: .5rem;" /></strong></p>

<p>The matrix becomes <code>[[1,0,1],[1,0,1]]</code> which satisfies the properties, by doing these 3 operations:</p>

<ul>
	<li>Change <code>grid[1][0]</code> to 1.</li>
	<li>Change <code>grid[0][1]</code> to 0.</li>
	<li>Change <code>grid[1][2]</code> to 1.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1],[2],[3]]</span></p>

<p><strong>Output:</strong> 2</p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3122.Minimum%20Number%20of%20Operations%20to%20Satisfy%20Conditions/images/changed.png" style="width: 86px; height: 277px;padding: 10px; background: #fff; border-radius: .5rem;" /></p>

<p>There is a single column. We can change the value to 1 in each cell using 2 operations.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, m &lt;= 1000</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 9</code></li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

We notice that the values in the cells of the matrix only have 10 possibilities. The problem requires us to find the minimum number of operations for each column to have the same number, and the numbers in adjacent columns are different. Therefore, we only need to consider the case of modifying the number to 0 to 9.

We define the state $f[i][j]$ to represent the minimum number of operations for the numbers in the first $[0,..i]$ columns, and the number in the $i$-th column is $j$. Then we can get the state transition equation:

$$
f[i][j] = \min_{k \neq j} (f[i-1][k] + m - \text{cnt}[j])
$$

Where $\text{cnt}[j]$ represents the number of cells in the $i$-th column that are $j$.

Finally, we only need to find the minimum value of $f[n-1][j]$.

The time complexity is $O(n \times (m + C^2))$, and the space complexity is $O(n \times C)$. Where $m$ and $n$ represent the number of rows and columns in the matrix respectively; and $C$ represents the number of types of numbers, here $C = 10$.

<!-- tabs:start -->

```python
class Solution:
    def minimumOperations(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        f = [[inf] * 10 for _ in range(n)]
        for i in range(n):
            cnt = [0] * 10
            for j in range(m):
                cnt[grid[j][i]] += 1
            if i == 0:
                for j in range(10):
                    f[i][j] = m - cnt[j]
            else:
                for j in range(10):
                    for k in range(10):
                        if k != j:
                            f[i][j] = min(f[i][j], f[i - 1][k] + m - cnt[j])
        return min(f[-1])
```

```java
class Solution {
    public int minimumOperations(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[n][10];
        final int inf = 1 << 29;
        for (var g : f) {
            Arrays.fill(g, inf);
        }
        for (int i = 0; i < n; ++i) {
            int[] cnt = new int[10];
            for (int j = 0; j < m; ++j) {
                ++cnt[grid[j][i]];
            }
            if (i == 0) {
                for (int j = 0; j < 10; ++j) {
                    f[i][j] = m - cnt[j];
                }
            } else {
                for (int j = 0; j < 10; ++j) {
                    for (int k = 0; k < 10; ++k) {
                        if (k != j) {
                            f[i][j] = Math.min(f[i][j], f[i - 1][k] + m - cnt[j]);
                        }
                    }
                }
            }
        }
        int ans = inf;
        for (int j = 0; j < 10; ++j) {
            ans = Math.min(ans, f[n - 1][j]);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumOperations(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int f[n][10];
        memset(f, 0x3f, sizeof(f));
        for (int i = 0; i < n; ++i) {
            int cnt[10]{};
            for (int j = 0; j < m; ++j) {
                ++cnt[grid[j][i]];
            }
            if (i == 0) {
                for (int j = 0; j < 10; ++j) {
                    f[i][j] = m - cnt[j];
                }
            } else {
                for (int j = 0; j < 10; ++j) {
                    for (int k = 0; k < 10; ++k) {
                        if (k != j) {
                            f[i][j] = min(f[i][j], f[i - 1][k] + m - cnt[j]);
                        }
                    }
                }
            }
        }
        return *min_element(f[n - 1], f[n - 1] + 10);
    }
};
```

```go
func minimumOperations(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, 10)
		for j := range f[i] {
			f[i][j] = 1 << 29
		}
	}
	for i := 0; i < n; i++ {
		cnt := [10]int{}
		for j := 0; j < m; j++ {
			cnt[grid[j][i]]++
		}
		if i == 0 {
			for j := 0; j < 10; j++ {
				f[i][j] = m - cnt[j]
			}
		} else {
			for j := 0; j < 10; j++ {
				for k := 0; k < 10; k++ {
					if j != k {
						f[i][j] = min(f[i][j], f[i-1][k]+m-cnt[j])
					}
				}
			}
		}
	}
	return slices.Min(f[n-1])
}
```

```ts
function minimumOperations(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const f: number[][] = Array.from({ length: n }, () =>
        Array.from({ length: 10 }, () => Infinity),
    );
    for (let i = 0; i < n; ++i) {
        const cnt: number[] = Array(10).fill(0);
        for (let j = 0; j < m; ++j) {
            cnt[grid[j][i]]++;
        }
        if (i === 0) {
            for (let j = 0; j < 10; ++j) {
                f[i][j] = m - cnt[j];
            }
        } else {
            for (let j = 0; j < 10; ++j) {
                for (let k = 0; k < 10; ++k) {
                    if (j !== k) {
                        f[i][j] = Math.min(f[i][j], f[i - 1][k] + m - cnt[j]);
                    }
                }
            }
        }
    }
    return Math.min(...f[n - 1]);
}
```

<!-- tabs:end -->

<!-- end -->
