# [面试题 17.23. 最大黑方阵](https://leetcode.cn/problems/max-black-square-lcci)

[English Version](/lcci/17.23.Max%20Black%20Square/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个方阵，其中每个单元(像素)非黑即白。设计一个算法，找出 4 条边皆为黑色像素的最大子方阵。</p>
<p>返回一个数组 <code>[r, c, size]</code> ，其中&nbsp;<code>r</code>,&nbsp;<code>c</code>&nbsp;分别代表子方阵左上角的行号和列号，<code>size</code> 是子方阵的边长。若有多个满足条件的子方阵，返回 <code>r</code> 最小的，若 <code>r</code> 相同，返回 <code>c</code> 最小的子方阵。若无满足条件的子方阵，返回空数组。</p>
<p><strong>示例 1:</strong></p>
<pre><strong>输入:
</strong>[
&nbsp;  [1,0,1],
&nbsp;  [<strong>0,0</strong>,1],
&nbsp;  [<strong>0,0</strong>,1]
]
<strong>输出: </strong>[1,0,2]
<strong>解释: </strong>输入中 0 代表黑色，1 代表白色，标粗的元素即为满足条件的最大子方阵
</pre>
<p><strong>示例 2:</strong></p>
<pre><strong>输入:
</strong>[
&nbsp;  [<strong>0</strong>,1,1],
&nbsp;  [1,0,1],
&nbsp;  [1,1,0]
]
<strong>输出: </strong>[0,0,1]
</pre>
<p><strong>提示：</strong></p>
<ul>
	<li><code>matrix.length == matrix[0].length &lt;= 200</code></li>
</ul>

## 解法

### 方法一：预处理 + 枚举

我们可以预处理出每个位置 $(i, j)$ 向下和向右的连续 $0$ （黑色像素）的个数，记为 $down[i][j]$ 和 $right[i][j]$。递推公式如下：

$$
down[i][j] = \begin{cases}
down[i + 1][j] + 1, & matrix[i][j] = 0 \text{ 且 } i + 1 < n \\
1, & matrix[i][j] = 0 \text{ 且 } i + 1 = n \\
0, & matrix[i][j] = 1
\end{cases}
$$

$$
right[i][j] = \begin{cases}
right[i][j + 1] + 1, & matrix[i][j] = 0 \text{ 且 } j + 1 < n \\
1, & matrix[i][j] = 0 \text{ 且 } j + 1 = n \\
0, & matrix[i][j] = 1
\end{cases}
$$

需要注意的是，由于 $down[i][j]$ 依赖于 $down[i + 1][j]$，而 $right[i][j]$ 依赖于 $right[i][j + 1]$，所以，我们在预处理 $down[i][j]$ 和 $right[i][j]$ 时，是从大到小枚举 $i$ 和 $j$ 的。

接下来，我们从大到小枚举正方形的边长 $k$，从小到大枚举正方形的左上角位置 $(i, j)$，如果满足 $down[i][j] \ge k$ 且 $right[i][j] \ge k$ 且 $right[i + k - 1][j] \ge k$ 且 $down[i][j + k - 1] \ge k$，说明我们找到了一个边长最大为 $k$ 且左上角位置为 $(i, j)$ 的黑方阵，直接返回 $[i, j, k]$ 即可。

如果枚举完所有的正方形都没有满足条件的，那么返回空数组。

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcci/17.23.Max%20Black%20Square/images/max_black_square.png" /></p>

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 是方阵的边长。

相似题目：

-   [1139. 最大的以 1 为边界的正方形](https://github.com/doocs/leetcode/blob/main/solution/1100-1199/1139.Largest%201-Bordered%20Square/README.md)

<!-- tabs:start -->

```python
class Solution:
    def findSquare(self, matrix: List[List[int]]) -> List[int]:
        n = len(matrix)
        down = [[0] * n for _ in range(n)]
        right = [[0] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                if matrix[i][j] == 0:
                    down[i][j] = down[i + 1][j] + 1 if i + 1 < n else 1
                    right[i][j] = right[i][j + 1] + 1 if j + 1 < n else 1
        for k in range(n, 0, -1):
            for i in range(n - k + 1):
                for j in range(n - k + 1):
                    if (
                        down[i][j] >= k
                        and right[i][j] >= k
                        and right[i + k - 1][j] >= k
                        and down[i][j + k - 1] >= k
                    ):
                        return [i, j, k]
        return []
```

```java
class Solution {
    public int[] findSquare(int[][] matrix) {
        int n = matrix.length;
        int[][] down = new int[n][n];
        int[][] right = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (matrix[i][j] == 0) {
                    down[i][j] = i + 1 < n ? down[i + 1][j] + 1 : 1;
                    right[i][j] = j + 1 < n ? right[i][j + 1] + 1 : 1;
                }
            }
        }
        for (int k = n; k > 0; --k) {
            for (int i = 0; i <= n - k; ++i) {
                for (int j = 0; j <= n - k; ++j) {
                    if (down[i][j] >= k && right[i][j] >= k && right[i + k - 1][j] >= k
                        && down[i][j + k - 1] >= k) {
                        return new int[] {i, j, k};
                    }
                }
            }
        }
        return new int[0];
    }
}
```

```cpp
class Solution {
public:
    vector<int> findSquare(vector<vector<int>>& matrix) {
        int n = matrix.size();
        int down[n][n];
        int right[n][n];
        memset(down, 0, sizeof(down));
        memset(right, 0, sizeof(right));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (matrix[i][j] == 0) {
                    down[i][j] = i + 1 < n ? down[i + 1][j] + 1 : 1;
                    right[i][j] = j + 1 < n ? right[i][j + 1] + 1 : 1;
                }
            }
        }
        for (int k = n; k > 0; --k) {
            for (int i = 0; i <= n - k; ++i) {
                for (int j = 0; j <= n - k; ++j) {
                    if (down[i][j] >= k && right[i][j] >= k && right[i + k - 1][j] >= k && down[i][j + k - 1] >= k) {
                        return {i, j, k};
                    }
                }
            }
        }
        return {};
    }
};
```

```go
func findSquare(matrix [][]int) []int {
	n := len(matrix)
	down := make([][]int, n)
	right := make([][]int, n)
	for i := range down {
		down[i] = make([]int, n)
		right[i] = make([]int, n)
	}
	for i := n - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if matrix[i][j] == 0 {
				down[i][j], right[i][j] = 1, 1
				if i+1 < n {
					down[i][j] += down[i+1][j]
				}
				if j+1 < n {
					right[i][j] += right[i][j+1]
				}
			}
		}
	}
	for k := n; k > 0; k-- {
		for i := 0; i <= n-k; i++ {
			for j := 0; j <= n-k; j++ {
				if down[i][j] >= k && right[i][j] >= k && right[i+k-1][j] >= k && down[i][j+k-1] >= k {
					return []int{i, j, k}
				}
			}
		}
	}
	return []int{}
}
```

```ts
function findSquare(matrix: number[][]): number[] {
    const n = matrix.length;
    const down: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    const right: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    for (let i = n - 1; i >= 0; --i) {
        for (let j = n - 1; j >= 0; --j) {
            if (matrix[i][j] === 0) {
                down[i][j] = i + 1 < n ? down[i + 1][j] + 1 : 1;
                right[i][j] = j + 1 < n ? right[i][j + 1] + 1 : 1;
            }
        }
    }
    for (let k = n; k > 0; --k) {
        for (let i = 0; i <= n - k; ++i) {
            for (let j = 0; j <= n - k; ++j) {
                if (
                    down[i][j] >= k &&
                    right[i][j] >= k &&
                    right[i + k - 1][j] >= k &&
                    down[i][j + k - 1] >= k
                ) {
                    return [i, j, k];
                }
            }
        }
    }
    return [];
}
```

<!-- tabs:end -->

<!-- end -->
