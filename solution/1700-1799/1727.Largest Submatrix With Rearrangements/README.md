# [1727. 重新排列后的最大子矩阵](https://leetcode.cn/problems/largest-submatrix-with-rearrangements)

[English Version](/solution/1700-1799/1727.Largest%20Submatrix%20With%20Rearrangements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二进制矩阵 <code>matrix</code> ，它的大小为 <code>m x n</code> ，你可以将 <code>matrix</code> 中的 <strong>列</strong> 按任意顺序重新排列。</p>

<p>请你返回最优方案下将 <code>matrix</code> 重新排列后，全是 <code>1</code> 的子矩阵面积。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1727.Largest%20Submatrix%20With%20Rearrangements/images/screenshot-2020-12-30-at-40536-pm.png" style="width: 300px; height: 144px;" /></strong></p>

<pre>
<b>输入：</b>matrix = [[0,0,1],[1,1,1],[1,0,1]]
<b>输出：</b>4
<b>解释：</b>你可以按照上图方式重新排列矩阵的每一列。
最大的全 1 子矩阵是上图中加粗的部分，面积为 4 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1727.Largest%20Submatrix%20With%20Rearrangements/images/screenshot-2020-12-30-at-40852-pm.png" style="width: 500px; height: 62px;" /></p>

<pre>
<b>输入：</b>matrix = [[1,0,1,0,1]]
<b>输出：</b>3
<b>解释：</b>你可以按照上图方式重新排列矩阵的每一列。
最大的全 1 子矩阵是上图中加粗的部分，面积为 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>matrix = [[1,1,0],[1,0,1]]
<b>输出：</b>2
<b>解释：</b>由于你只能整列整列重新排布，所以没有比面积为 2 更大的全 1 子矩形。</pre>

<p><strong>示例 4：</strong></p>

<pre>
<b>输入：</b>matrix = [[0,0],[0,0]]
<b>输出：</b>0
<b>解释：</b>由于矩阵中没有 1 ，没有任何全 1 的子矩阵，所以面积为 0 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 <= m * n <= 10<sup>5</sup></code></li>
	<li><code>matrix[i][j]</code> 要么是 <code>0</code> ，要么是 <code>1</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：预处理 + 排序**

由于题目中矩阵是按列进行重排，因此，我们可以先对矩阵的每一列进行预处理。

对于每个值为 $1$ 的元素，我们更新其值为该元素向上的最大连续的 $1$ 的个数，即 $matrix[i][j]=matrix[i-1][j]+1$。

接下来，我们可以对更新后的矩阵的每一行进行排序。然后遍历每一行，计算以该行作为底边的最大全 $1$ 子矩阵的面积。具体计算逻辑如下：

对于矩阵的某一行，我们记第 $k$ 大元素的值为 $val_k$，其中 $k \geq 1$，那么该行至少有 $k$ 个元素不小于 $val_k$，组成的全 $1$ 子矩阵面积为 $val_k \times k$。从大到小遍历矩阵该行的每个元素，取 $val_k \times k$ 的最大值，更新答案。

时间复杂度 $O(m\times n\times \log n)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def largestSubmatrix(self, matrix: List[List[int]]) -> int:
        for i in range(1, len(matrix)):
            for j in range(len(matrix[0])):
                if matrix[i][j]:
                    matrix[i][j] = matrix[i - 1][j] + 1
        ans = 0
        for row in matrix:
            row.sort(reverse=True)
            for j, v in enumerate(row, 1):
                ans = max(ans, j * v)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = matrix[i - 1][j] + 1;
                }
            }
        }
        int ans = 0;
        for (var row : matrix) {
            Arrays.sort(row);
            for (int j = n - 1, k = 1; j >= 0 && row[j] > 0; --j, ++k) {
                int s = row[j] * k;
                ans = Math.max(ans, s);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int largestSubmatrix(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j]) {
                    matrix[i][j] = matrix[i - 1][j] + 1;
                }
            }
        }
        int ans = 0;
        for (auto& row : matrix) {
            sort(row.rbegin(), row.rend());
            for (int j = 0; j < n; ++j) {
                ans = max(ans, (j + 1) * row[j]);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func largestSubmatrix(matrix [][]int) int {
	m, n := len(matrix), len(matrix[0])
	for i := 1; i < m; i++ {
		for j := 0; j < n; j++ {
			if matrix[i][j] == 1 {
				matrix[i][j] = matrix[i-1][j] + 1
			}
		}
	}
	ans := 0
	for _, row := range matrix {
		sort.Ints(row)
		for j, k := n-1, 1; j >= 0 && row[j] > 0; j, k = j-1, k+1 {
			ans = max(ans, row[j]*k)
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
