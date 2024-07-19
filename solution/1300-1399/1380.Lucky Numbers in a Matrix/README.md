---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1380.Lucky%20Numbers%20in%20a%20Matrix/README.md
rating: 1207
source: 第 180 场周赛 Q1
tags:
    - 数组
    - 矩阵
---

<!-- problem:start -->

# [1380. 矩阵中的幸运数](https://leetcode.cn/problems/lucky-numbers-in-a-matrix)

[English Version](/solution/1300-1399/1380.Lucky%20Numbers%20in%20a%20Matrix/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <code>m * n</code> 的矩阵，矩阵中的数字 <strong>各不相同</strong> 。请你按 <strong>任意</strong> 顺序返回矩阵中的所有幸运数。</p>

<p><strong>幸运数</strong> 是指矩阵中满足同时下列两个条件的元素：</p>

<ul>
	<li>在同一行的所有元素中最小</li>
	<li>在同一列的所有元素中最大</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[3,7,8],[9,11,13],[15,16,17]]
<strong>输出：</strong>[15]
<strong>解释：</strong>15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
<strong>输出：</strong>[12]
<strong>解释：</strong>12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[7,8],[1,2]]
<strong>输出：</strong>[7]
<strong>解释：</strong>7是唯一的幸运数字，因为它是行中的最小值，列中的最大值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= n, m &lt;= 50</code></li>
	<li><code>1 &lt;=&nbsp;matrix[i][j]&nbsp;&lt;= 10^5</code></li>
	<li>矩阵中的所有元素都是不同的</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：维护行最小值和列最大值

我们可以使用两个数组 $rows$ 和 $cols$ 记录矩阵中每一行的最小值和每一列的最大值，然后遍历矩阵中的每一个元素，检查该元素是否为所在行的最小值且为所在列的最大值，如果是则该元素为幸运数，我们将其加入答案数组。

遍历结束后，我们返回答案数组即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m + n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def luckyNumbers(self, matrix: List[List[int]]) -> List[int]:
        rows = {min(row) for row in matrix}
        cols = {max(col) for col in zip(*matrix)}
        return list(rows & cols)
```

#### Java

```java
class Solution {
    public List<Integer> luckyNumbers(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        Arrays.fill(rows, 1 << 30);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rows[i] = Math.min(rows[i], matrix[i][j]);
                cols[j] = Math.max(cols[j], matrix[i][j]);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rows[i] == cols[j]) {
                    ans.add(rows[i]);
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
    vector<int> luckyNumbers(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        int rows[m];
        int cols[n];
        memset(rows, 0x3f, sizeof(rows));
        memset(cols, 0, sizeof(cols));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rows[i] = min(rows[i], matrix[i][j]);
                cols[j] = max(cols[j], matrix[i][j]);
            }
        }
        vector<int> ans;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rows[i] == cols[j]) {
                    ans.push_back(rows[i]);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func luckyNumbers(matrix [][]int) (ans []int) {
	m, n := len(matrix), len(matrix[0])
	rows, cols := make([]int, m), make([]int, n)
	for i := range rows {
		rows[i] = 1 << 30
	}
	for i, row := range matrix {
		for j, x := range row {
			rows[i] = min(rows[i], x)
			cols[j] = max(cols[j], x)
		}
	}
	for i, row := range matrix {
		for j, x := range row {
			if rows[i] == cols[j] {
				ans = append(ans, x)
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function luckyNumbers(matrix: number[][]): number[] {
    const m = matrix.length;
    const n = matrix[0].length;
    const rows: number[] = new Array(m).fill(1 << 30);
    const cols: number[] = new Array(n).fill(0);
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; j++) {
            rows[i] = Math.min(rows[i], matrix[i][j]);
            cols[j] = Math.max(cols[j], matrix[i][j]);
        }
    }
    const ans: number[] = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; j++) {
            if (rows[i] === cols[j]) {
                ans.push(rows[i]);
            }
        }
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var luckyNumbers = function (matrix) {
    const m = matrix.length;
    const n = matrix[0].length;
    const rows = new Array(m).fill(1 << 30);
    const cols = new Array(n).fill(0);
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; j++) {
            rows[i] = Math.min(rows[i], matrix[i][j]);
            cols[j] = Math.max(cols[j], matrix[i][j]);
        }
    }
    const ans = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; j++) {
            if (rows[i] === cols[j]) {
                ans.push(rows[i]);
            }
        }
    }
    return ans;
};
```

#### Rust

```rust
impl Solution {
    pub fn lucky_numbers(matrix: Vec<Vec<i32>>) -> Vec<i32> {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut res = vec![];
        let mut col = vec![0; n];
        for j in 0..n {
            for i in 0..m {
                col[j] = col[j].max(matrix[i][j]);
            }
        }
        for x in 0..m {
            let mut i = 0;
            for y in 1..n {
                if matrix[x][y] < matrix[x][i] {
                    i = y;
                }
            }
            if matrix[x][i] == col[i] {
                res.push(col[i]);
            }
        }
        res
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
