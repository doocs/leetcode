# [766. 托普利茨矩阵](https://leetcode-cn.com/problems/toeplitz-matrix)

[English Version](/solution/0700-0799/0766.Toeplitz%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>如果一个矩阵的每一方向由左上到右下的对角线上具有相同元素，那么这个矩阵是<em>托普利茨矩阵</em>。</p>

<p>给定一个&nbsp;<code>M x N</code>&nbsp;的矩阵，当且仅当它是<em>托普利茨矩阵</em>时返回&nbsp;<code>True</code>。</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong> 
matrix = [
&nbsp; [1,2,3,4],
&nbsp; [5,1,2,3],
&nbsp; [9,5,1,2]
]
<strong>输出:</strong> True
<strong>解释:</strong>
在上述矩阵中, 其对角线为:
&quot;[9]&quot;, &quot;[5, 5]&quot;, &quot;[1, 1, 1]&quot;, &quot;[2, 2, 2]&quot;, &quot;[3, 3]&quot;, &quot;[4]&quot;。
各条对角线上的所有元素均相同, 因此答案是True。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong>
matrix = [
&nbsp; [1,2],
&nbsp; [2,2]
]
<strong>输出:</strong> False
<strong>解释: 
</strong>对角线&quot;[1, 2]&quot;上的元素不同。
</pre>

<p><strong>说明:</strong></p>

<ol>
	<li>&nbsp;<code>matrix</code>&nbsp;是一个包含整数的二维数组。</li>
	<li><code>matrix</code>&nbsp;的行数和列数均在&nbsp;<code>[1, 20]</code>范围内。</li>
	<li><code>matrix[i][j]</code>&nbsp;包含的整数在&nbsp;<code>[0, 99]</code>范围内。</li>
</ol>

<p><strong>进阶:</strong></p>

<ol>
	<li>如果矩阵存储在磁盘上，并且磁盘内存是有限的，因此一次最多只能将一行矩阵加载到内存中，该怎么办？</li>
	<li>如果矩阵太大以至于只能一次将部分行加载到内存中，该怎么办？</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

遍历矩阵，若出现元素与其左上角的元素不等的情况，返回 `false`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isToeplitzMatrix(self, matrix: List[List[int]]) -> bool:
        m, n = len(matrix), len(matrix[0])
        for i in range(1, m):
            for j in range(1, n):
                if matrix[i][j] != matrix[i - 1][j - 1]:
                    return False
        return True
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

### **...**

```

```

<!-- tabs:end -->
