---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1572.Matrix%20Diagonal%20Sum/README.md
rating: 1280
source: 第 34 场双周赛 Q1
tags:
    - 数组
    - 矩阵
---

<!-- problem:start -->

# [1572. 矩阵对角线元素的和](https://leetcode.cn/problems/matrix-diagonal-sum)

[English Version](/solution/1500-1599/1572.Matrix%20Diagonal%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正方形矩阵 <code>mat</code>，请你返回矩阵对角线元素的和。</p>

<p>请你返回在矩阵主对角线上的元素和副对角线上且不在主对角线上元素的和。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp; 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1572.Matrix%20Diagonal%20Sum/images/sample_1911.png" style="height:174px; width:336px" /></p>

<pre>
<strong>输入：</strong>mat = [[<strong>1</strong>,2,<strong>3</strong>],
&nbsp;           [4,<strong>5</strong>,6],
&nbsp;           [<strong>7</strong>,8,<strong>9</strong>]]
<strong>输出：</strong>25
<strong>解释：</strong>对角线的和为：1 + 5 + 9 + 3 + 7 = 25
请注意，元素 mat[1][1] = 5 只会被计算一次。
</pre>

<p><strong>示例&nbsp; 2：</strong></p>

<pre>
<strong>输入：</strong>mat = [[<strong>1</strong>,1,1,<strong>1</strong>],
&nbsp;           [1,<strong>1</strong>,<strong>1</strong>,1],
&nbsp;           [1,<strong>1</strong>,<strong>1</strong>,1],
&nbsp;           [<strong>1</strong>,1,1,<strong>1</strong>]]
<strong>输出：</strong>8
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>mat = [[<strong>5</strong>]]
<strong>输出：</strong>5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == mat.length == mat[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：逐行遍历

我们可以遍历矩阵的每一行 $\textit{row}[i]$，对于每一行，我们可以计算出两个对角线上的元素，即 $\textit{row}[i][i]$ 和 $\textit{row}[i][n - i - 1]$，其中 $n$ 是矩阵的行数。如果 $i = n - i - 1$，则说明当前行的对角线上只有一个元素，否则有两个元素。我们将其加到答案中即可。
遍历完所有行后，即可得到答案。

时间复杂度 $O(n)$，其中 $n$ 是矩阵的行数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def diagonalSum(self, mat: List[List[int]]) -> int:
        ans = 0
        n = len(mat)
        for i, row in enumerate(mat):
            j = n - i - 1
            ans += row[i] + (0 if j == i else row[j])
        return ans
```

#### Java

```java
class Solution {
    public int diagonalSum(int[][] mat) {
        int ans = 0;
        int n = mat.length;
        for (int i = 0; i < n; ++i) {
            int j = n - i - 1;
            ans += mat[i][i] + (i == j ? 0 : mat[i][j]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int diagonalSum(vector<vector<int>>& mat) {
        int ans = 0;
        int n = mat.size();
        for (int i = 0; i < n; ++i) {
            int j = n - i - 1;
            ans += mat[i][i] + (i == j ? 0 : mat[i][j]);
        }
        return ans;
    }
};
```

#### Go

```go
func diagonalSum(mat [][]int) (ans int) {
	n := len(mat)
	for i, row := range mat {
		ans += row[i]
		if j := n - i - 1; j != i {
			ans += row[j]
		}
	}
	return
}
```

#### TypeScript

```ts
function diagonalSum(mat: number[][]): number {
    let ans = 0;
    const n = mat.length;
    for (let i = 0; i < n; ++i) {
        const j = n - i - 1;
        ans += mat[i][i] + (i === j ? 0 : mat[i][j]);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn diagonal_sum(mat: Vec<Vec<i32>>) -> i32 {
        let n = mat.len();
        let mut ans = 0;

        for i in 0..n {
            ans += mat[i][i];
            let j = n - i - 1;
            if j != i {
                ans += mat[i][j];
            }
        }

        ans
    }
}
```

#### C

```c
int diagonalSum(int** mat, int matSize, int* matColSize) {
    int ans = 0;
    for (int i = 0; i < matSize; ++i) {
        ans += mat[i][i];
        int j = matSize - i - 1;
        if (j != i) {
            ans += mat[i][j];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
