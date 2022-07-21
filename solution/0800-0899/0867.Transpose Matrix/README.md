# [867. 转置矩阵](https://leetcode.cn/problems/transpose-matrix)

[English Version](/solution/0800-0899/0867.Transpose%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组 <code>matrix</code>， 返回 <code>matrix</code> 的 <strong>转置矩阵</strong> 。</p>

<p>矩阵的 <strong>转置</strong> 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0867.Transpose%20Matrix/images/hint_transpose.png" style="width: 600px; height: 197px;" /></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]
<strong>输出：</strong>[[1,4,7],[2,5,8],[3,6,9]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[1,2,3],[4,5,6]]
<strong>输出：</strong>[[1,4],[2,5],[3,6]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 <= m, n <= 1000</code></li>
	<li><code>1 <= m * n <= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> <= matrix[i][j] <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def transpose(self, matrix: List[List[int]]) -> List[List[int]]:
        return list(zip(*matrix))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                ans[i][j] = matrix[j][i];
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
    vector<vector<int>> transpose(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        vector<vector<int>> ans(n, vector<int>(m));
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                ans[i][j] = matrix[j][i];
        return ans;
    }
};
```

### **Go**

```go
func transpose(matrix [][]int) [][]int {
	m, n := len(matrix), len(matrix[0])
	ans := make([][]int, n)
	for i := range ans {
		ans[i] = make([]int, m)
		for j := range ans[i] {
			ans[i][j] = matrix[j][i]
		}
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} matrix
 * @return {number[][]}
 */
var transpose = function (matrix) {
    const m = matrix.length;
    const n = matrix[0].length;
    const ans = new Array(n).fill(0).map(() => new Array(m).fill(0));
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < m; ++j) {
            ans[i][j] = matrix[j][i];
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
