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

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def modifiedMatrix(self, matrix: List[List[int]]) -> List[List[int]]:
        rows = len(matrix)
        cols = len(matrix[0])
        for i in range(cols):
            max_val = float('-inf')
            for j in range(rows):
                max_val = max(max_val, matrix[j][i])

            for j in range(rows):
                if matrix[j][i] == -1:
                    matrix[j][i] = max_val

        return matrix
```

```java
class Solution {
    public int[][] modifiedMatrix(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        for (int i = 0; i < c; i++) {
            int maxs = Integer.MIN_VALUE;
            for (int j = 0; j < r; j++) {
                maxs = Math.max(maxs, matrix[j][i]);
            }
            for (int j = 0; j < r; j++) {
                if (matrix[j][i] == -1) {
                    matrix[j][i] = maxs;
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
        int r = matrix.size();
        int c = matrix[0].size();
        for (int i = 0; i < c; i++) {
            int maxs = INT_MIN;
            for (int j = 0; j < r; j++) {
                maxs = max(maxs, matrix[j][i]);
            }
            for (int j = 0; j < r; j++) {
                if (matrix[j][i] == -1) {
                    matrix[j][i] = maxs;
                }
            }
        }
        return matrix;
    }
};
```

```go
func modifiedMatrix(matrix [][]int) [][]int {
	r := len(matrix)
	c := len(matrix[0])
	for i := 0; i < c; i++ {
		maxs := math.MinInt32
		for j := 0; j < r; j++ {
			if matrix[j][i] > maxs {
				maxs = matrix[j][i]
			}
		}
		for j := 0; j < r; j++ {
			if matrix[j][i] == -1 {
				matrix[j][i] = maxs
			}
		}
	}
	return matrix
}
```

```ts
function modifiedMatrix(matrix: number[][]): number[][] {
    const rows = matrix.length;
    const cols = matrix[0].length;
    for (let i = 0; i < cols; i++) {
        let maxVal = Number.MIN_SAFE_INTEGER;
        for (let j = 0; j < rows; j++) {
            maxVal = Math.max(maxVal, matrix[j][i]);
        }
        for (let j = 0; j < rows; j++) {
            if (matrix[j][i] === -1) {
                matrix[j][i] = maxVal;
            }
        }
    }
    return matrix;
}
```

<!-- tabs:end -->

<!-- end -->
