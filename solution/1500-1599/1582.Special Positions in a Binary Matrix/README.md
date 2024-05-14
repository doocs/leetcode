# [1582. 二进制矩阵中的特殊位置](https://leetcode.cn/problems/special-positions-in-a-binary-matrix)

[English Version](/solution/1500-1599/1582.Special%20Positions%20in%20a%20Binary%20Matrix/README_EN.md)

<!-- tags:数组,矩阵 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 <code>m x n</code> 的二进制矩阵 <code>mat</code>，返回矩阵 <code>mat</code> 中特殊位置的数量。</p>

<p>如果位置 <code>(i, j)</code> 满足 <code>mat[i][j] == 1</code> 并且行 <code>i</code> 与列 <code>j</code> 中的所有其他元素都是 <code>0</code>（行和列的下标从 <strong>0 </strong>开始计数），那么它被称为<strong> 特殊 </strong>位置。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1582.Special%20Positions%20in%20a%20Binary%20Matrix/images/special1.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>输入：</strong>mat = [[1,0,0],[0,0,1],[1,0,0]]
<strong>输出：</strong>1
<strong>解释：</strong>位置 (1, 2) 是一个特殊位置，因为 mat[1][2] == 1 且第 1 行和第 2 列的其他所有元素都是 0。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1582.Special%20Positions%20in%20a%20Binary%20Matrix/images/special-grid.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>输入：</strong>mat = [[1,0,0],[0,1,0],[0,0,1]]
<strong>输出：</strong>3
<strong>解释：</strong>位置 (0, 0)，(1, 1) 和 (2, 2) 都是特殊位置。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>mat[i][j]</code> 是 <code>0</code> 或 <code>1</code>。</li>
</ul>

## 解法

### 方法一：模拟

遍历矩阵 `mat`，先统计每一行，每一列中 `1` 的个数，分别记录在 `r` 和 `c` 数组中。

然后再遍历矩阵 `mat`，如果 `mat[i][j] == 1` 且 `row[i] == 1` 且 `col[j] == 1`，则 $(i, j)$ 是特殊位置。

时间复杂度 $O(m\times n)$，空间复杂度 $O(m+n)$。其中 $m$, $n$ 分别是矩阵 `mat` 的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def numSpecial(self, mat: List[List[int]]) -> int:
        m, n = len(mat), len(mat[0])
        r = [0] * m
        c = [0] * n
        for i, row in enumerate(mat):
            for j, v in enumerate(row):
                r[i] += v
                c[j] += v
        ans = 0
        for i in range(m):
            for j in range(n):
                if mat[i][j] == 1 and r[i] == 1 and c[j] == 1:
                    ans += 1
        return ans
```

```java
class Solution {
    public int numSpecial(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] r = new int[m];
        int[] c = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                r[i] += mat[i][j];
                c[j] += mat[i][j];
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 1 && r[i] == 1 && c[j] == 1) {
                    ++ans;
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
    int numSpecial(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<int> r(m), c(n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                r[i] += mat[i][j];
                c[j] += mat[i][j];
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 1 && r[i] == 1 && c[j] == 1) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

```go
func numSpecial(mat [][]int) int {
	m, n := len(mat), len(mat[0])
	r, c := make([]int, m), make([]int, n)
	for i, row := range mat {
		for j, v := range row {
			r[i] += v
			c[j] += v
		}
	}
	ans := 0
	for i, x := range r {
		for j, y := range c {
			if mat[i][j] == 1 && x == 1 && y == 1 {
				ans++
			}
		}
	}
	return ans
}
```

```ts
function numSpecial(mat: number[][]): number {
    const m = mat.length;
    const n = mat[0].length;
    const rows = new Array(m).fill(0);
    const cols = new Array(n).fill(0);
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (mat[i][j] === 1) {
                rows[i]++;
                cols[j]++;
            }
        }
    }

    let res = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (mat[i][j] === 1 && rows[i] === 1 && cols[j] === 1) {
                res++;
            }
        }
    }

    return res;
}
```

```rust
impl Solution {
    pub fn num_special(mat: Vec<Vec<i32>>) -> i32 {
        let m = mat.len();
        let n = mat[0].len();
        let mut rows = vec![0; m];
        let mut cols = vec![0; n];
        for i in 0..m {
            for j in 0..n {
                rows[i] += mat[i][j];
                cols[j] += mat[i][j];
            }
        }

        let mut res = 0;
        for i in 0..m {
            for j in 0..n {
                if mat[i][j] == 1 && rows[i] == 1 && cols[j] == 1 {
                    res += 1;
                }
            }
        }
        res
    }
}
```

```c
int numSpecial(int** mat, int matSize, int* matColSize) {
    int m = matSize;
    int n = *matColSize;
    int* rows = (int*) malloc(sizeof(int) * m);
    int* cols = (int*) malloc(sizeof(int) * n);
    memset(rows, 0, sizeof(int) * m);
    memset(cols, 0, sizeof(int) * n);
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (mat[i][j] == 1) {
                rows[i]++;
                cols[j]++;
            }
        }
    }
    int res = 0;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (mat[i][j] == 1 && rows[i] == 1 && cols[j] == 1) {
                res++;
            }
        }
    }
    free(rows);
    free(cols);
    return res;
}
```

<!-- tabs:end -->

<!-- end -->
