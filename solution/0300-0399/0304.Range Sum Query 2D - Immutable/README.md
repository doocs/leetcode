# [304. 二维区域和检索 - 矩阵不可变](https://leetcode-cn.com/problems/range-sum-query-2d-immutable)

[English Version](/solution/0300-0399/0304.Range%20Sum%20Query%202D%20-%20Immutable/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 <code>(row1, col1)</code> ，右下角为 <code>(row2, col2)</code> 。</p>

<p><img alt="Range Sum Query 2D" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0304.Range%20Sum%20Query%202D%20-%20Immutable/images/304.png" style="width: 130px;" /><br />
<small>上图子矩阵左上角 (row1, col1) = <strong>(2, 1)</strong> ，右下角(row2, col2) = <strong>(4, 3)，</strong>该子矩形内元素的总和为 8。</small></p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
给定 matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>你可以假设矩阵不可变。</li>
	<li>会多次调用 <code>sumRegion</code><em> </em>方法<em>。</em></li>
	<li>你可以假设 <code>row1 ≤ row2</code> 且 <code>col1 ≤ col2</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划-二维前缀和。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class NumMatrix:

    def __init__(self, matrix: List[List[int]]):
        m, n = len(matrix), len(matrix[0])
        self.pre = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                self.pre[i][j] = self.pre[i - 1][j] + self.pre[i][j - 1] - self.pre[i - 1][j - 1] + matrix[i - 1][j - 1]

    def sumRegion(self, row1: int, col1: int, row2: int, col2: int) -> int:
        return self.pre[row2 + 1][col2 + 1] - self.pre[row2 + 1][col1] - self.pre[row1][col2 + 1] + self.pre[row1][col1]


# Your NumMatrix object will be instantiated and called as such:
# obj = NumMatrix(matrix)
# param_1 = obj.sumRegion(row1,col1,row2,col2)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class NumMatrix {
    private int[][] pre;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        pre = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return pre[row2 + 1][col2 + 1] - pre[row2 + 1][col1] - pre[row1][col2 + 1] + pre[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
```

### **C++**

```cpp
class NumMatrix {
public:
    vector<vector<int>> pre;

    NumMatrix(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        pre.resize(m + 1, vector<int>(n + 1));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    int sumRegion(int row1, int col1, int row2, int col2) {
        return pre[row2 + 1][col2 + 1] - pre[row2 + 1][col1] - pre[row1][col2 + 1] + pre[row1][col1];
    }
};

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix* obj = new NumMatrix(matrix);
 * int param_1 = obj->sumRegion(row1,col1,row2,col2);
 */
```

### **Go**

```go
type NumMatrix struct {
	pre [][]int
}

func Constructor(matrix [][]int) NumMatrix {
	m, n := len(matrix), len(matrix[0])
	pre := make([][]int, m+1)
	for i := 0; i < m+1; i++ {
		pre[i] = make([]int, n+1)
	}
	for i := 1; i < m+1; i++ {
		for j := 1; j < n+1; j++ {
			pre[i][j] = pre[i-1][j] + pre[i][j-1] + -pre[i-1][j-1] + matrix[i-1][j-1]
		}
	}
	return NumMatrix{pre}
}

func (this *NumMatrix) SumRegion(row1 int, col1 int, row2 int, col2 int) int {
	return this.pre[row2+1][col2+1] - this.pre[row2+1][col1] - this.pre[row1][col2+1] + this.pre[row1][col1]
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * obj := Constructor(matrix);
 * param_1 := obj.SumRegion(row1,col1,row2,col2);
 */
```

### **...**

```

```

<!-- tabs:end -->
