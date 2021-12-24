# [1380. Lucky Numbers in a Matrix](https://leetcode.com/problems/lucky-numbers-in-a-matrix)

[中文文档](/solution/1300-1399/1380.Lucky%20Numbers%20in%20a%20Matrix/README.md)

## Description

<p>Given a <code>m * n</code> matrix of <strong>distinct </strong>numbers, return all lucky numbers in the&nbsp;matrix in <strong>any </strong>order.</p>

<p>A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[3,7,8],[9,11,13],[15,16,17]]
<strong>Output:</strong> [15]
<strong>Explanation:</strong> 15 is the only lucky number since it is the minimum in its row and the maximum in its column
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
<strong>Output:</strong> [12]
<strong>Explanation:</strong> 12 is the only lucky number since it is the minimum in its row and the maximum in its column.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[7,8],[1,2]]
<strong>Output:</strong> [7]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= n, m &lt;= 50</code></li>
	<li><code>1 &lt;=&nbsp;matrix[i][j]&nbsp;&lt;= 10^5</code>.</li>
	<li>All elements in the matrix are distinct.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def luckyNumbers (self, matrix: List[List[int]]) -> List[int]:
        row_min = {min(rows) for rows in matrix}
        col_max = {max(cols) for cols in zip(*matrix)}
        return [e for e in row_min if e in col_max]
```

### **Java**

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
