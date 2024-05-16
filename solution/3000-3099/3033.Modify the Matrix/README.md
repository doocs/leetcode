---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3033.Modify%20the%20Matrix/README.md
rating: 1180
source: 第 384 场周赛 Q1
tags:
    - 数组
    - 矩阵
---

# [3033. 修改矩阵](https://leetcode.cn/problems/modify-the-matrix)

[English Version](/solution/3000-3099/3033.Modify%20the%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始、大小为 <code>m x n</code> 的整数矩阵 <code>matrix</code> ，新建一个下标从 <strong>0</strong> 开始、名为 <code>answer</code> 的矩阵。使 <code>answer</code> 与 <code>matrix</code> 相等，接着将其中每个值为 <code>-1</code> 的元素替换为所在列的 <strong>最大</strong> 元素。</p>

<p>返回矩阵 <code>answer</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3033.Modify%20the%20Matrix/images/matrix1.png" style="width: 491px; height: 161px;" />
<pre>
<strong>输入：</strong>matrix = [[1,2,-1],[4,-1,6],[7,8,9]]
<strong>输出：</strong>[[1,2,9],[4,8,6],[7,8,9]]
<strong>解释：</strong>上图显示了发生替换的元素（蓝色区域）。
- 将单元格 [1][1] 中的值替换为列 1 中的最大值 8 。
- 将单元格 [0][2] 中的值替换为列 2 中的最大值 9 。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3033.Modify%20the%20Matrix/images/matrix2.png" style="width: 411px; height: 111px;" />
<pre>
<strong>输入：</strong>matrix = [[3,-1],[5,2]]
<strong>输出：</strong>[[3,2],[5,2]]
<strong>解释：</strong>上图显示了发生替换的元素（蓝色区域）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 50</code></li>
	<li><code>-1 &lt;= matrix[i][j] &lt;= 100</code></li>
	<li>测试用例中生成的输入满足每列至少包含一个非负整数。</li>
</ul>

## 解法

### 方法一：模拟

我们可以根据题目描述，遍历每一列，找到每一列的最大值，然后再遍历每一列，将值为 -1 的元素替换为该列的最大值。

时间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别是矩阵的行数和列数。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def modifiedMatrix(self, matrix: List[List[int]]) -> List[List[int]]:
        m, n = len(matrix), len(matrix[0])
        for j in range(n):
            mx = max(matrix[i][j] for i in range(m))
            for i in range(m):
                if matrix[i][j] == -1:
                    matrix[i][j] = mx
        return matrix
```

```java
class Solution {
    public int[][] modifiedMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int j = 0; j < n; ++j) {
            int mx = -1;
            for (int i = 0; i < m; ++i) {
                mx = Math.max(mx, matrix[i][j]);
            }
            for (int i = 0; i < m; ++i) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = mx;
                }
            }
        }
        return matrix;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> modifiedMatrix(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        for (int j = 0; j < n; ++j) {
            int mx = -1;
            for (int i = 0; i < m; ++i) {
                mx = max(mx, matrix[i][j]);
            }
            for (int i = 0; i < m; ++i) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = mx;
                }
            }
        }
        return matrix;
    }
};
```

```go
func modifiedMatrix(matrix [][]int) [][]int {
	m, n := len(matrix), len(matrix[0])
	for j := 0; j < n; j++ {
		mx := -1
		for i := 0; i < m; i++ {
			mx = max(mx, matrix[i][j])
		}
		for i := 0; i < m; i++ {
			if matrix[i][j] == -1 {
				matrix[i][j] = mx
			}
		}
	}
	return matrix
}
```

```ts
function modifiedMatrix(matrix: number[][]): number[][] {
    const [m, n] = [matrix.length, matrix[0].length];
    for (let j = 0; j < n; ++j) {
        let mx = -1;
        for (let i = 0; i < m; ++i) {
            mx = Math.max(mx, matrix[i][j]);
        }
        for (let i = 0; i < m; ++i) {
            if (matrix[i][j] === -1) {
                matrix[i][j] = mx;
            }
        }
    }
    return matrix;
}
```

```cs
public class Solution {
    public int[][] ModifiedMatrix(int[][] matrix) {
        int m = matrix.Length, n = matrix[0].Length;
        for (int j = 0; j < n; ++j) {
            int mx = -1;
            for (int i = 0; i < m; ++i) {
                mx = Math.Max(mx, matrix[i][j]);
            }
            for (int i = 0; i < m; ++i) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = mx;
                }
            }
        }
        return matrix;
    }
}
```

<!-- tabs:end -->

<!-- end -->
