# [766. 托普利茨矩阵](https://leetcode.cn/problems/toeplitz-matrix)

[English Version](/solution/0700-0799/0766.Toeplitz%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>m x n</code> 的矩阵 <code>matrix</code> 。如果这个矩阵是托普利茨矩阵，返回 <code>true</code> ；否则，返回<em> </em><code>false</code><em> 。</em></p>

<p>如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是<em> </em><strong>托普利茨矩阵</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0766.Toeplitz%20Matrix/images/ex1.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>输入：</strong>matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
<strong>输出：</strong>true
<strong>解释：</strong>
在上述矩阵中, 其对角线为: 
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。 
各条对角线上的所有元素均相同, 因此答案是 True 。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0766.Toeplitz%20Matrix/images/ex2.jpg" style="width: 162px; height: 162px;" />
<pre>
<strong>输入：</strong>matrix = [[1,2],[2,2]]
<strong>输出：</strong>false
<strong>解释：</strong>
对角线 "[1, 2]" 上的元素不同。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 <= m, n <= 20</code></li>
	<li><code>0 <= matrix[i][j] <= 99</code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong></p>

<ul>
	<li>如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？</li>
	<li>如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：一次遍历**

遍历矩阵，若出现元素与其左上角的元素不等的情况，返回 `false`。否则，遍历结束后返回 `true`。

时间复杂度 $O(m \times n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isToeplitzMatrix(self, matrix: List[List[int]]) -> bool:
        m, n = len(matrix), len(matrix[0])
        return all(matrix[i][j] == matrix[i - 1][j - 1] for i in range(1, m) for j in range(1, n))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isToeplitzMatrix(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
};
```

### **Go**

```go
func isToeplitzMatrix(matrix [][]int) bool {
	m, n := len(matrix), len(matrix[0])
	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			if matrix[i][j] != matrix[i-1][j-1] {
				return false
			}
		}
	}
	return true
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} matrix
 * @return {boolean}
 */
var isToeplitzMatrix = function (matrix) {
    const m = matrix.length;
    const n = matrix[0].length;
    for (let i = 1; i < m; ++i) {
        for (let j = 1; j < n; ++j) {
            if (matrix[i][j] != matrix[i - 1][j - 1]) {
                return false;
            }
        }
    }
    return true;
};
```

### **...**

```

```

<!-- tabs:end -->
