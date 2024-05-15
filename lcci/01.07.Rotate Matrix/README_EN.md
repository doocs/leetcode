---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/01.07.Rotate%20Matrix/README_EN.md
---

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

### Solution 1: In-place Rotation

According to the problem requirements, we actually need to rotate $matrix[i][j]$ to $matrix[j][n - i - 1]$.

We can first flip the matrix upside down, that is, swap $matrix[i][j]$ and $matrix[n - i - 1][j]$, and then flip the matrix along the main diagonal, that is, swap $matrix[i][j]$ and $matrix[j][i]$. This way, we can rotate $matrix[i][j]$ to $matrix[j][n - i - 1]$.

The time complexity is $O(n^2)$, where $n$ is the side length of the matrix. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        n = len(matrix)
        for i in range(n >> 1):
            for j in range(n):
                matrix[i][j], matrix[n - i - 1][j] = matrix[n - i - 1][j], matrix[i][j]
        for i in range(n):
            for j in range(i):
                matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]
```

```java
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n >> 1; ++i) {
            for (int j = 0; j < n; ++j) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = t;
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
    }
}
```

```cpp
class Solution {
public:
    void rotate(vector<vector<int>>& matrix) {
        int n = matrix.size();
        for (int i = 0; i < n >> 1; ++i) {
            for (int j = 0; j < n; ++j) {
                swap(matrix[i][j], matrix[n - i - 1][j]);
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                swap(matrix[i][j], matrix[j][i]);
            }
        }
    }
};
```

```go
func rotate(matrix [][]int) {
	n := len(matrix)
	for i := 0; i < n>>1; i++ {
		for j := 0; j < n; j++ {
			matrix[i][j], matrix[n-i-1][j] = matrix[n-i-1][j], matrix[i][j]
		}
	}
	for i := 0; i < n; i++ {
		for j := 0; j < i; j++ {
			matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]
		}
	}
}
```

```ts
/**
 Do not return anything, modify matrix in-place instead.
 */
function rotate(matrix: number[][]): void {
    matrix.reverse();
    for (let i = 0; i < matrix.length; ++i) {
        for (let j = 0; j < i; ++j) {
            const t = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = t;
        }
    }
}
```

```rust
impl Solution {
    pub fn rotate(matrix: &mut Vec<Vec<i32>>) {
        let n = matrix.len();
        for i in 0..n / 2 {
            for j in 0..n {
                let t = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = t;
            }
        }
        for i in 0..n {
            for j in 0..i {
                let t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
    }
}
```

```js
/**
 * @param {number[][]} matrix
 * @return {void} Do not return anything, modify matrix in-place instead.
 */
var rotate = function (matrix) {
    matrix.reverse();
    for (let i = 0; i < matrix.length; ++i) {
        for (let j = 0; j < i; ++j) {
            [matrix[i][j], matrix[j][i]] = [matrix[j][i], matrix[i][j]];
        }
    }
};
```

```cs
public class Solution {
    public void Rotate(int[][] matrix) {
        int n = matrix.Length;
        for (int i = 0; i < n >> 1; ++i) {
            for (int j = 0; j < n; ++j) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = t;
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
    }
}
```

```swift
class Solution {
    func rotate(_ matrix: inout [[Int]]) {
        let n = matrix.count

        for i in 0..<(n >> 1) {
            for j in 0..<n {
                let t = matrix[i][j]
                matrix[i][j] = matrix[n - i - 1][j]
                matrix[n - i - 1][j] = t
            }
        }

        for i in 0..<n {
            for j in 0..<i {
                let t = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j][i] = t
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- end -->
