# [48. 旋转图像](https://leetcode-cn.com/problems/rotate-image)

[English Version](/solution/0000-0099/0048.Rotate%20Image/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 <em>n </em>× <em>n</em> 的二维矩阵 <code>matrix</code> 表示一个图像。请你将图像顺时针旋转 90 度。</p>

<p>你必须在<strong><a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank"> 原地</a></strong> 旋转图像，这意味着你需要直接修改输入的二维矩阵。<strong>请不要 </strong>使用另一个矩阵来旋转图像。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0048.Rotate%20Image/images/mat1.jpg" style="width: 642px; height: 242px;" />
<pre>
<strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]
<strong>输出：</strong>[[7,4,1],[8,5,2],[9,6,3]]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0048.Rotate%20Image/images/mat2.jpg" style="width: 800px; height: 321px;" />
<pre>
<strong>输入：</strong>matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
<strong>输出：</strong>[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[1]]
<strong>输出：</strong>[[1]]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[1,2],[3,4]]
<strong>输出：</strong>[[3,1],[4,2]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>matrix.length == n</code></li>
	<li><code>matrix[i].length == n</code></li>
	<li><code>1 <= n <= 20</code></li>
	<li><code>-1000 <= matrix[i][j] <= 1000</code></li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        s, n = 0, len(matrix)
        while s < (n >> 1):
            e = n - s - 1
            for i in range(s, e):
                t = matrix[i][e]
                matrix[i][e] = matrix[s][i]
                matrix[s][i] = matrix[n - i - 1][s]
                matrix[n - i - 1][s] = matrix[e][n - i - 1]
                matrix[e][n - i - 1] = t
            s += 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public void rotate(int[][] matrix) {
        int s = 0, n = matrix.length;
        while (s < (n >> 1)) {
            int e = n - s - 1;
            for (int i = s; i < e; ++i) {
                int t = matrix[i][e];
                matrix[i][e] = matrix[s][i];
                matrix[s][i] = matrix[n - i - 1][s];
                matrix[n - i - 1][s] = matrix[e][n - i - 1];
                matrix[e][n - i - 1] = t;
            }
            ++s;
        }
    }
}
```

### **TypeScript**

```ts
/**
 Do not return anything, modify matrix in-place instead.
 */
function rotate(matrix: number[][]): void {
    let n = matrix[0].length;
    for (let i = 0; i < Math.floor(n / 2); i++) {
        for (let j = 0; j < Math.floor((n + 1) / 2); j++) {
            let tmp = matrix[i][j];
            matrix[i][j] = matrix[n-1-j][i];
            matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
            matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
            matrix[j][n - 1 - i] = tmp;
        }
    }
};
```

### **...**

```

```

<!-- tabs:end -->
