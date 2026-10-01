---
comments: true
difficulty: Medium
tags:
    - Array
    - Math
    - Matrix
---

<!-- problem:start -->

# [48. Rotate Image](https://leetcode.com/problems/rotate-image)

[中文文档](/solution/0000-0099/0048.Rotate%20Image/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cung cấp một <code>n x n</code> <code>matrix</code> hai chiều biểu diễn một hình ảnh, hãy xoay hình ảnh <strong>90</strong> độ (theo chiều kim đồng hồ).</p>

<p>Bạn phải xoay hình ảnh <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank"><strong>tại chỗ</strong></a>, nghĩa là bạn phải sửa đổi trực tiếp matrix hai chiều đầu vào. <strong>KHÔNG ĐƯỢC</strong> cấp phát một matrix hai chiều khác và thực hiện phép xoay.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0048.Rotate%20Image/images/mat1.jpg" style="width: 500px; height: 188px;" />
<pre>
<strong>Đầu vào:</strong> matrix = [[1,2,3],[4,5,6],[7,8,9]]
<strong>Đầu ra:</strong> [[7,4,1],[8,5,2],[9,6,3]]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0048.Rotate%20Image/images/mat2.jpg" style="width: 500px; height: 201px;" />
<pre>
<strong>Đầu vào:</strong> matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
<strong>Đầu ra:</strong> [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>n == matrix.length == matrix[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 20</code></li>
	<li><code>-1000 &lt;= matrix[i][j] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Xoay tại chỗ

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là tạo một ma trận mới với $\textit{matrix}[j][n-1-i] \gets \textit{matrix}[i][j]$. Đúng là thời gian và không gian $O(n^2)$. $n \le 20$ phù hợp, nhưng bài toán yêu cầu xoay tại chỗ.
>
> Ma trận bổ sung là điểm nghẽn. Một phép xoay $90^\circ$ theo chiều kim đồng hồ có thể tách thành hai phép lật tại chỗ: lật ngược theo chiều dọc, sau đó chuyển vị qua đường chéo chính.
>
> $(i, j)$ chuyển thành $(n-1-i, j)$, sau đó thành $(j, n-1-i)$ — chính là đích cần tìm. Hai lượt hoán đổi, sử dụng thêm $O(1)$ không gian.

<!-- thinking:end -->

Theo yêu cầu của bài toán, chúng ta cần xoay $\text{matrix}[i][j]$ thành $\text{matrix}[j][n - i - 1]$.

Trước tiên, chúng ta có thể lật ma trận theo chiều dọc, tức là hoán đổi $\text{matrix}[i][j]$ với $\text{matrix}[n - i - 1][j]$, sau đó lật ma trận theo đường chéo chính, tức là hoán đổi $\text{matrix}[i][j]$ với $\text{matrix}[j][i]$. Như vậy, $\text{matrix}[i][j]$ được xoay thành $\text{matrix}[j][n - i - 1]$.

Độ phức tạp thời gian là $O(n^2)$, trong đó $n$ là độ dài cạnh của ma trận. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

#### Rust

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

#### JavaScript

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

#### C#

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
