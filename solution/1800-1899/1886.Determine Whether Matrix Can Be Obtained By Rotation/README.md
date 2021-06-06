# [1886. 判断矩阵经轮转后是否一致](https://leetcode-cn.com/problems/determine-whether-matrix-can-be-obtained-by-rotation)

[English Version](/solution/1800-1899/1886.Determine%20Whether%20Matrix%20Can%20Be%20Obtained%20By%20Rotation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个大小为 <code>n x n</code> 的二进制矩阵 <code>mat</code> 和 <code>target</code> 。现<strong> 以 90 度顺时针轮转 </strong>矩阵 <code>mat</code> 中的元素 <strong>若干次</strong> ，如果能够使 <code>mat</code> 与 <code>target</code> 一致，返回 <code>true</code> ；否则，返回<em> </em><code>false</code><em> 。</em></p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1886.Determine%20Whether%20Matrix%20Can%20Be%20Obtained%20By%20Rotation/images/grid3.png" style="width: 301px; height: 121px;" />
<pre>
<strong>输入：</strong>mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
<strong>输出：</strong>true
<strong>解释：</strong>顺时针轮转 90 度一次可以使 mat 和 target 一致。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1886.Determine%20Whether%20Matrix%20Can%20Be%20Obtained%20By%20Rotation/images/grid4.png" style="width: 301px; height: 121px;" />
<pre>
<strong>输入：</strong>mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
<strong>输出：</strong>false
<strong>解释：</strong>无法通过轮转矩阵中的元素使 equal 与 target 一致。
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1886.Determine%20Whether%20Matrix%20Can%20Be%20Obtained%20By%20Rotation/images/grid4-1.png" style="width: 661px; height: 184px;" />
<pre>
<strong>输入：</strong>mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
<strong>输出：</strong>true
<strong>解释：</strong>顺时针轮转 90 度两次可以使 mat 和 target 一致。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == mat.length == target.length</code></li>
	<li><code>n == mat[i].length == target[i].length</code></li>
	<li><code>1 <= n <= 10</code></li>
	<li><code>mat[i][j]</code> 和 <code>target[i][j]</code> 不是 <code>0</code> 就是 <code>1</code></li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

旋转矩阵，判断矩阵是否一致。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findRotation(self, mat: List[List[int]], target: List[List[int]]) -> bool:
        def rotate(matrix):
            n = len(matrix)
            for i in range(n // 2):
                for j in range(i, n - 1 - i):
                    t = matrix[i][j]
                    matrix[i][j] = matrix[n - j - 1][i]
                    matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1]
                    matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1]
                    matrix[j][n - i - 1] = t
        for _ in range(4):
            if mat == target:
                return True
            rotate(mat)
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int times = 4;
        while (times-- > 0) {
            if (equals(mat, target)) {
                return true;
            }
            rotate(mat);
        }
        return false;
    }
    
    private void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = i; j < n - 1 - i; ++j) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = t;
            }
        }
    }
    
    private boolean equals(int[][] nums1, int[][] nums2) {
        int n = nums1.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (nums1[i][j] != nums2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
