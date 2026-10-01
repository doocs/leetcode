---
comments: true
difficulty: Medium
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [120. Triangle](https://leetcode.com/problems/triangle)

[中文文档](/solution/0100-0199/0120.Triangle/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng <code>triangle</code> dạng tam giác, hãy trả về <em>tổng đường đi nhỏ nhất từ trên xuống dưới</em>.</p>

<p>Ở mỗi bước, bạn có thể di chuyển đến một số liền kề ở hàng bên dưới. Cụ thể hơn, nếu bạn đang ở chỉ số <code>i</code> trên hàng hiện tại, bạn có thể di chuyển đến chỉ số <code>i</code> hoặc <code>i + 1</code> trên hàng tiếp theo.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
<strong>Đầu ra:</strong> 11
<strong>Giải thích:</strong> Tam giác có dạng:
   <u>2</u>
  <u>3</u> 4
 6 <u>5</u> 7
4 <u>1</u> 8 3
Tổng đường đi nhỏ nhất từ trên xuống dưới là 2 + 3 + 5 + 1 = 11 (được gạch chân ở trên).
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> triangle = [[-10]]
<strong>Đầu ra:</strong> -10
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= triangle.length &lt;= 200</code></li>
	<li><code>triangle[0].length == 1</code></li>
	<li><code>triangle[i].length == triangle[i - 1].length + 1</code></li>
	<li><code>-10<sup>4</sup> &lt;= triangle[i][j] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Câu hỏi mở rộng:</strong> Bạn có thể thực hiện việc này chỉ với <code>O(n)</code> không gian bổ sung hay không, trong đó <code>n</code> là tổng số hàng trong tam giác?

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quy hoạch động

<!-- thinking:start -->

> **Tư duy**
>
> Mỗi bước chỉ có thể di chuyển đến một ô liền kề ở hàng tiếp theo; việc liệt kê các đường đi tăng theo cấp số mũ theo số hàng. Các bài toán con chồng lấp: đường đi nhỏ nhất từ một ô chỉ phụ thuộc vào hai ô bên dưới.
>
> Định nghĩa $f[i][j]$ theo hướng từ dưới lên là đường đi nhỏ nhất từ ô đó đến hàng cuối. Mỗi ô lấy giá trị nhỏ hơn của hai ô bên dưới cộng với chính nó; $f[0][0]$ là đáp án.

<!-- thinking:end -->

Chúng ta định nghĩa $f[i][j]$ là tổng đường đi nhỏ nhất từ đáy của tam giác đến vị trí $(i, j)$. Ở đây, vị trí $(i, j)$ là vị trí ở hàng $i$ và cột $j$ của tam giác (cả hai đều được đánh chỉ số từ $0$). Ta có phương trình chuyển trạng thái sau:

$$
f[i][j] = \min(f[i + 1][j], f[i + 1][j + 1]) + \text{triangle}[i][j]
$$

Đáp án là $f[0][0]$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        n = len(triangle)
        f = [[0] * (n + 1) for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1):
                f[i][j] = min(f[i + 1][j], f[i + 1][j + 1]) + triangle[i][j]
        return f[0][0]
```

#### Java

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                f[i][j] = Math.min(f[i + 1][j], f[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return f[0][0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumTotal(vector<vector<int>>& triangle) {
        int n = triangle.size();
        vector<vector<int>> f(n + 1, vector<int>(n + 1, 0));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                f[i][j] = min(f[i + 1][j], f[i + 1][j + 1]) + triangle[i][j];
            }
        }
        return f[0][0];
    }
};
```

#### Go

```go
func minimumTotal(triangle [][]int) int {
	n := len(triangle)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for i := n - 1; i >= 0; i-- {
		for j := 0; j <= i; j++ {
			f[i][j] = min(f[i+1][j], f[i+1][j+1]) + triangle[i][j]
		}
	}
	return f[0][0]
}
```

#### TypeScript

```ts
function minimumTotal(triangle: number[][]): number {
    const n = triangle.length;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(n + 1).fill(0));
    for (let i = n - 1; i >= 0; --i) {
        for (let j = 0; j <= i; ++j) {
            f[i][j] = Math.min(f[i + 1][j], f[i + 1][j + 1]) + triangle[i][j];
        }
    }
    return f[0][0];
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_total(triangle: Vec<Vec<i32>>) -> i32 {
        let n = triangle.len();
        let mut f = vec![vec![0; n + 1]; n + 1];
        for i in (0..n).rev() {
            for j in 0..=i {
                f[i][j] = f[i + 1][j].min(f[i + 1][j + 1]) + triangle[i][j];
            }
        }
        f[0][0]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Quy hoạch động (Tối ưu hóa không gian)

<!-- thinking:start -->

> **Tư duy**
>
> Trong Lời giải 1, $f[i][j]$ chỉ phụ thuộc vào hàng tiếp theo. Sử dụng một mảng một chiều khi đi ngược lên trên làm giảm không gian từ $O(n^2)$ xuống $O(n)$, phù hợp với câu hỏi mở rộng.

<!-- thinking:end -->

Ta nhận thấy trạng thái $f[i][j]$ chỉ phụ thuộc vào các trạng thái $f[i + 1][j]$ và $f[i + 1][j + 1]$. Do đó, chúng ta có thể sử dụng một mảng một chiều thay cho mảng hai chiều, giảm độ phức tạp không gian từ $O(n^2)$ xuống $O(n)$.

Độ phức tạp thời gian là $O(n^2)$, và độ phức tạp không gian là $O(n)$, trong đó $n$ là số hàng trong tam giác.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        n = len(triangle)
        f = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            for j in range(i + 1):
                f[j] = min(f[j], f[j + 1]) + triangle[i][j]
        return f[0]
```

#### Java

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                f[j] = Math.min(f[j], f[j + 1]) + triangle.get(i).get(j);
            }
        }
        return f[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumTotal(vector<vector<int>>& triangle) {
        int n = triangle.size();
        vector<int> f(n + 1, 0);
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                f[j] = min(f[j], f[j + 1]) + triangle[i][j];
            }
        }
        return f[0];
    }
};
```

#### Go

```go
func minimumTotal(triangle [][]int) int {
	n := len(triangle)
	f := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		for j := 0; j <= i; j++ {
			f[j] = min(f[j], f[j+1]) + triangle[i][j]
		}
	}
	return f[0]
}
```

#### TypeScript

```ts
function minimumTotal(triangle: number[][]): number {
    const n = triangle.length;
    const f: number[] = Array(n + 1).fill(0);
    for (let i = n - 1; i >= 0; --i) {
        for (let j = 0; j <= i; ++j) {
            f[j] = Math.min(f[j], f[j + 1]) + triangle[i][j];
        }
    }
    return f[0];
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_total(triangle: Vec<Vec<i32>>) -> i32 {
        let n = triangle.len();
        let mut f = vec![0; n + 1];
        for i in (0..n).rev() {
            for j in 0..=i {
                f[j] = f[j].min(f[j + 1]) + triangle[i][j];
            }
        }
        f[0]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
