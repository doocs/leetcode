---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/01.07.Rotate%20Matrix/README.md
---

<!-- problem:start -->

# [面试题 01.07. 旋转矩阵](https://leetcode.cn/problems/rotate-matrix-lcci)

[English Version](/lcci/01.07.Rotate%20Matrix/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一幅由N × N矩阵表示的图像，其中每个像素的大小为4字节，编写一种方法，将图像旋转90度。</p>

<p>不占用额外内存空间能否做到？</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>给定 <strong>matrix</strong> =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

<strong>原地</strong>旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
</pre>

<p><strong>示例 2:</strong></p>

<pre>给定 <strong>matrix</strong> =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
],

<strong>原地</strong>旋转输入矩阵，使其变为:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：原地翻转

根据题目要求，我们实际上需要将 $matrix[i][j]$ 旋转至 $matrix[j][n - i - 1]$。

我们可以先对矩阵进行上下翻转，即 $matrix[i][j]$ 和 $matrix[n - i - 1][j]$ 进行交换，然后再对矩阵进行主对角线翻转，即 $matrix[i][j]$ 和 $matrix[j][i]$ 进行交换。这样就能将 $matrix[i][j]$ 旋转至 $matrix[j][n - i - 1]$ 了。

时间复杂度 $O(n^2)$，其中 $n$ 是矩阵的边长。空间复杂度 $O(1)$。

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

<!-- solution:end -->

<!-- problem:end -->
