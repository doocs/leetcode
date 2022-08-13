# [01.07. Rotate Matrix](https://leetcode.cn/problems/rotate-matrix-lcci)

[中文文档](/lcci/01.07.Rotate%20Matrix/README.md)

## Description

<p>Given an image represented by an N x N matrix, where each pixel in the image is 4 bytes, write a method to rotate the image by 90 degrees. Can you do this in place?</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

Given <strong>matrix</strong> =

[

  [1,2,3],

  [4,5,6],

  [7,8,9]

],



Rotate the matrix <strong>in place. </strong>It becomes:

[

  [7,4,1],

  [8,5,2],

  [9,6,3]

]

</pre>

<p><strong>Example 2:</strong></p>

<pre>

Given <strong>matrix</strong> =

[

  [ 5, 1, 9,11],

  [ 2, 4, 8,10],

  [13, 3, 6, 7],

  [15,14,12,16]

],



Rotate the matrix <strong>in place. </strong>It becomes:

[

  [15,13, 2, 5],

  [14, 3, 4, 1],

  [12, 6, 8, 9],

  [16, 7,10,11]

]

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        n = len(matrix)
        for i in range(n // 2):
            for j in range(i, n - 1 - i):
                t = matrix[i][j]
                matrix[i][j] = matrix[n - j - 1][i]
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1]
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1]
                matrix[j][n - i - 1] = t
```

### **Java**

```java
class Solution {
    public void rotate(int[][] matrix) {
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
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} matrix
 * @return {void} Do not return anything, modify matrix in-place instead.
 */
var rotate = function (matrix) {
    const n = matrix.length;
    for (let i = 0; i < n / 2; i++) {
        for (let j = i; j < n - i - 1; j++) {
            let t = matrix[i][j];
            matrix[i][j] = matrix[n - j - 1][i];
            matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
            matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
            matrix[j][n - i - 1] = t;
        }
    }
};
```

### **Go**

```go
func rotate(matrix [][]int) {
	n := len(matrix)
	r, c := n/2, (n+1)/2
	for i := 0; i < r; i++ {
		for j := 0; j < c; j++ {
			temp := matrix[i][j]
			matrix[i][j] = matrix[n-j-1][i]
			matrix[n-j-1][i] = matrix[n-i-1][n-j-1]
			matrix[n-i-1][n-j-1] = matrix[j][n-i-1]
			matrix[j][n-i-1] = temp
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
