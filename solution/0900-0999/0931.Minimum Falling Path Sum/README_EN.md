# [931. Minimum Falling Path Sum](https://leetcode.com/problems/minimum-falling-path-sum)

[中文文档](/solution/0900-0999/0931.Minimum%20Falling%20Path%20Sum/README.md)

<!-- tags:Array,Dynamic Programming,Matrix -->

## Description

<p>Given an <code>n x n</code> array of integers <code>matrix</code>, return <em>the <strong>minimum sum</strong> of any <strong>falling path</strong> through</em> <code>matrix</code>.</p>

<p>A <strong>falling path</strong> starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position <code>(row, col)</code> will be <code>(row + 1, col - 1)</code>, <code>(row + 1, col)</code>, or <code>(row + 1, col + 1)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0931.Minimum%20Falling%20Path%20Sum/images/failing1-grid.jpg" style="width: 499px; height: 500px;" />
<pre>
<strong>Input:</strong> matrix = [[2,1,3],[6,5,4],[7,8,9]]
<strong>Output:</strong> 13
<strong>Explanation:</strong> There are two falling paths with a minimum sum as shown.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0931.Minimum%20Falling%20Path%20Sum/images/failing2-grid.jpg" style="width: 164px; height: 365px;" />
<pre>
<strong>Input:</strong> matrix = [[-19,57],[-40,-5]]
<strong>Output:</strong> -59
<strong>Explanation:</strong> The falling path with a minimum sum is shown.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == matrix.length == matrix[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>-100 &lt;= matrix[i][j] &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def minFallingPathSum(self, matrix: List[List[int]]) -> int:
        n = len(matrix)
        f = [0] * n
        for row in matrix:
            g = [0] * n
            for j, x in enumerate(row):
                l, r = max(0, j - 1), min(n, j + 2)
                g[j] = min(f[l:r]) + x
            f = g
        return min(f)
```

```java
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        var f = new int[n];
        for (var row : matrix) {
            var g = f.clone();
            for (int j = 0; j < n; ++j) {
                if (j > 0) {
                    g[j] = Math.min(g[j], f[j - 1]);
                }
                if (j + 1 < n) {
                    g[j] = Math.min(g[j], f[j + 1]);
                }
                g[j] += row[j];
            }
            f = g;
        }
        // return Arrays.stream(f).min().getAsInt();
        int ans = 1 << 30;
        for (int x : f) {
            ans = Math.min(ans, x);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minFallingPathSum(vector<vector<int>>& matrix) {
        int n = matrix.size();
        vector<int> f(n);
        for (auto& row : matrix) {
            auto g = f;
            for (int j = 0; j < n; ++j) {
                if (j) {
                    g[j] = min(g[j], f[j - 1]);
                }
                if (j + 1 < n) {
                    g[j] = min(g[j], f[j + 1]);
                }
                g[j] += row[j];
            }
            f = move(g);
        }
        return *min_element(f.begin(), f.end());
    }
};
```

```go
func minFallingPathSum(matrix [][]int) int {
	n := len(matrix)
	f := make([]int, n)
	for _, row := range matrix {
		g := make([]int, n)
		copy(g, f)
		for j, x := range row {
			if j > 0 {
				g[j] = min(g[j], f[j-1])
			}
			if j+1 < n {
				g[j] = min(g[j], f[j+1])
			}
			g[j] += x
		}
		f = g
	}
	return slices.Min(f)
}
```

```ts
function minFallingPathSum(matrix: number[][]): number {
    const n = matrix.length;
    const f: number[] = new Array(n).fill(0);
    for (const row of matrix) {
        const g = f.slice();
        for (let j = 0; j < n; ++j) {
            if (j > 0) {
                g[j] = Math.min(g[j], f[j - 1]);
            }
            if (j + 1 < n) {
                g[j] = Math.min(g[j], f[j + 1]);
            }
            g[j] += row[j];
        }
        f.splice(0, n, ...g);
    }
    return Math.min(...f);
}
```

<!-- tabs:end -->

<!-- end -->
