# [931. 下降路径最小和](https://leetcode.cn/problems/minimum-falling-path-sum)

[English Version](/solution/0900-0999/0931.Minimum%20Falling%20Path%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>n x n</code> 的<strong> 方形 </strong>整数数组&nbsp;<code>matrix</code> ，请你找出并返回通过 <code>matrix</code> 的<strong>下降路径</strong><em> </em>的<strong> </strong><strong>最小和</strong> 。</p>

<p><strong>下降路径</strong> 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 <code>(row, col)</code> 的下一个元素应当是 <code>(row + 1, col - 1)</code>、<code>(row + 1, col)</code> 或者 <code>(row + 1, col + 1)</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0931.Minimum%20Falling%20Path%20Sum/images/failing1-grid.jpg" style="height: 500px; width: 499px;" /></p>

<pre>
<strong>输入：</strong>matrix = [[2,1,3],[6,5,4],[7,8,9]]
<strong>输出：</strong>13
<strong>解释：</strong>如图所示，为和最小的两条下降路径
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0931.Minimum%20Falling%20Path%20Sum/images/failing2-grid.jpg" style="height: 365px; width: 164px;" /></p>

<pre>
<strong>输入：</strong>matrix = [[-19,57],[-40,-5]]
<strong>输出：</strong>-59
<strong>解释：</strong>如图所示，为和最小的下降路径
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == matrix.length == matrix[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>-100 &lt;= matrix[i][j] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minFallingPathSum(self, matrix: List[List[int]]) -> int:
        n = len(matrix)
        for i in range(1, n):
            for j in range(n):
                mi = matrix[i - 1][j]
                if j > 0:
                    mi = min(mi, matrix[i - 1][j - 1])
                if j < n - 1:
                    mi = min(mi, matrix[i - 1][j + 1])
                matrix[i][j] += mi
        return min(matrix[n - 1])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int mi = matrix[i - 1][j];
                if (j > 0) {
                    mi = Math.min(mi, matrix[i - 1][j - 1]);
                }
                if (j < n - 1) {
                    mi = Math.min(mi, matrix[i - 1][j + 1]);
                }
                matrix[i][j] += mi;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < n; ++j) {
            res = Math.min(res, matrix[n - 1][j]);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minFallingPathSum(vector<vector<int>>& matrix) {
        int n = matrix.size();
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int mi = matrix[i - 1][j];
                if (j > 0) mi = min(mi, matrix[i - 1][j - 1]);
                if (j < n - 1) mi = min(mi, matrix[i - 1][j + 1]);
                matrix[i][j] += mi;
            }
        }
        int res = INT_MAX;
        for (int j = 0; j < n; ++j) {
            res = min(res, matrix[n - 1][j]);
        }
        return res;
    }
};
```

### **Go**

```go
func minFallingPathSum(matrix [][]int) int {
    n := len(matrix)
    for i := 1; i < n; i++ {
        for j := 0; j < n; j++ {
            mi := matrix[i - 1][j]
            if j > 0 && mi > matrix[i - 1][j - 1] {
                mi = matrix[i - 1][j - 1]
            }
            if j < n - 1 && mi > matrix[i - 1][j + 1] {
                mi = matrix[i - 1][j + 1]
            }
            matrix[i][j] += mi
        }
    }
    res := 10000
    for j := 0; j < n; j++ {
        if res > matrix[n - 1][j] {
            res = matrix[n - 1][j]
        }
    }
    return res
}
```

### **...**

```

```

<!-- tabs:end -->
