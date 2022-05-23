# [1329. 将矩阵按对角线排序](https://leetcode.cn/problems/sort-the-matrix-diagonally)

[English Version](/solution/1300-1399/1329.Sort%20the%20Matrix%20Diagonally/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>矩阵对角线</strong> 是一条从矩阵最上面行或者最左侧列中的某个元素开始的对角线，沿右下方向一直到矩阵末尾的元素。例如，矩阵 <code>mat</code> 有 <code>6</code> 行 <code>3</code> 列，从 <code>mat[2][0]</code> 开始的 <strong>矩阵对角线</strong> 将会经过 <code>mat[2][0]</code>、<code>mat[3][1]</code> 和 <code>mat[4][2]</code> 。</p>

<p>给你一个 <code>m * n</code> 的整数矩阵 <code>mat</code> ，请你将同一条 <strong>矩阵对角线 </strong>上的元素按升序排序后，返回排好序的矩阵。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1329.Sort%20the%20Matrix%20Diagonally/images/1482_example_1_2.png" style="height: 198px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
<strong>输出：</strong>[[1,1,1,1],[1,2,2,2],[1,2,3,3]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
<strong>输出：</strong>[[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 <= m, n <= 100</code></li>
	<li><code>1 <= mat[i][j] <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def diagonalSort(self, mat: List[List[int]]) -> List[List[int]]:
        m, n = len(mat), len(mat[0])
        for k in range(min(m, n) - 1):
            for i in range(m - 1):
                for j in range(n - 1):
                    if mat[i][j] > mat[i + 1][j + 1]:
                        mat[i][j], mat[i + 1][j + 1] = mat[i + 1][j + 1], mat[i][j]
        return mat
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        for (int k = 0; k < Math.min(m, n) - 1; ++k) {
            for (int i = 0; i < m - 1; ++i) {
                for (int j = 0; j < n - 1; ++j) {
                    if (mat[i][j] > mat[i + 1][j + 1]) {
                        int t = mat[i][j];
                        mat[i][j] = mat[i + 1][j + 1];
                        mat[i + 1][j + 1] = t;
                    }
                }
            }
        }
        return mat;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> diagonalSort(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        for (int k = 0; k < min(m, n) - 1; ++k)
            for (int i = 0; i < m - 1; ++i)
                for (int j = 0; j < n - 1; ++j)
                    if (mat[i][j] > mat[i + 1][j + 1])
                        swap(mat[i][j], mat[i + 1][j + 1]);
        return mat;
    }
};
```

### **Go**

```go
func diagonalSort(mat [][]int) [][]int {
	m, n := len(mat), len(mat[0])
	for k := 0; k < m-1 && k < n-1; k++ {
		for i := 0; i < m-1; i++ {
			for j := 0; j < n-1; j++ {
				if mat[i][j] > mat[i+1][j+1] {
					mat[i][j], mat[i+1][j+1] = mat[i+1][j+1], mat[i][j]
				}
			}
		}
	}
	return mat
}
```

### **...**

```

```

<!-- tabs:end -->
