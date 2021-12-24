# [1380. 矩阵中的幸运数](https://leetcode-cn.com/problems/lucky-numbers-in-a-matrix)

[English Version](/solution/1300-1399/1380.Lucky%20Numbers%20in%20a%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>m * n</code> 的矩阵，矩阵中的数字 <strong>各不相同</strong> 。请你按 <strong>任意</strong> 顺序返回矩阵中的所有幸运数。</p>

<p>幸运数是指矩阵中满足同时下列两个条件的元素：</p>

<ul>
	<li>在同一行的所有元素中最小</li>
	<li>在同一列的所有元素中最大</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>matrix = [[3,7,8],[9,11,13],[15,16,17]]
<strong>输出：</strong>[15]
<strong>解释：</strong>15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
<strong>输出：</strong>[12]
<strong>解释：</strong>12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>matrix = [[7,8],[1,2]]
<strong>输出：</strong>[7]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= n, m &lt;= 50</code></li>
	<li><code>1 &lt;=&nbsp;matrix[i][j]&nbsp;&lt;= 10^5</code></li>
	<li>矩阵中的所有元素都是不同的</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

取行最小值与列最大值的交集即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def luckyNumbers (self, matrix: List[List[int]]) -> List[int]:
        row_min = {min(rows) for rows in matrix}
        col_max = {max(cols) for cols in zip(*matrix)}
        return [e for e in row_min if e in col_max]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> luckyNumbers (int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        Set<Integer> rowMin = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; ++j) {
                min = Math.min(min, matrix[i][j]);
            }
            rowMin.add(min);
        }

        for (int j = 0; j < n; ++j) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < m; ++i) {
                max = Math.max(max, matrix[i][j]);
            }
            if (rowMin.contains(max)) {
                res.add(max);
            }

        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
