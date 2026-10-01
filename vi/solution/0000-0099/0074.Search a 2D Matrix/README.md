---
comments: true
difficulty: Medium
tags:
    - Array
    - Binary Search
    - Matrix
---

<!-- problem:start -->

# [74. Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix)

[中文文档](/solution/0000-0099/0074.Search%20a%202D%20Matrix/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một ma trận số nguyên <code>matrix</code> kích thước <code>m x n</code> có hai tính chất sau:</p>

<ul>
	<li>Mỗi hàng được sắp xếp theo thứ tự không giảm.</li>
	<li>Số nguyên đầu tiên của mỗi hàng lớn hơn số nguyên cuối cùng của hàng trước đó.</li>
</ul>

<p>Cho một số nguyên <code>target</code>, hãy trả về <code>true</code> <em>nếu</em> <code>target</code> <em>nằm trong</em> <code>matrix</code><em>, hoặc</em> <code>false</code> <em>trong trường hợp ngược lại</em>.</p>

<p>Bạn phải viết lời giải có độ phức tạp thời gian <code>O(log(m * n))</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0074.Search%20a%202D%20Matrix/images/mat.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Đầu vào:</strong> matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
<strong>Đầu ra:</strong> true
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0074.Search%20a%202D%20Matrix/images/mat2.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Đầu vào:</strong> matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
<strong>Đầu ra:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>-10<sup>4</sup> &lt;= matrix[i][j], target &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm nhị phân

<!-- thinking:start -->

> **Tư duy**
>
> Quét toàn bộ ma trận tốn $O(mn)$. Với $m,n \le 100$ thì cách này vẫn qua được, nhưng đề bài yêu cầu $O(\log(mn))$.
>
> Đằng sau hai chiều là một lần tìm kiếm nhị phân duy nhất. Mỗi hàng tăng dần, và phần tử cuối của hàng $i$ nhỏ hơn phần tử đầu của hàng $i+1$, nên nối các hàng lại với nhau sẽ được một mảng đã sắp xếp có độ dài $mn$. Chỉ số logic $mid$ ứng với $matrix[\lfloor mid/n\rfloor][mid \bmod n]$, nên ta tìm kiếm nhị phân trên $[0,mn-1]$ mà không cần thực sự tạo ra mảng đã trải phẳng.

<!-- thinking:end -->

Chúng ta có thể trải phẳng ma trận hai chiều về mặt logic rồi thực hiện tìm kiếm nhị phân.

Độ phức tạp thời gian là $O(\log(m \times n))$, trong đó $m$ và $n$ lần lượt là số hàng và số cột của ma trận. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        m, n = len(matrix), len(matrix[0])
        left, right = 0, m * n - 1
        while left < right:
            mid = (left + right) >> 1
            x, y = divmod(mid, n)
            if matrix[x][y] >= target:
                right = mid
            else:
                left = mid + 1
        return matrix[left // n][left % n] == target
```

#### Java

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            int x = mid / n, y = mid % n;
            if (matrix[x][y] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return matrix[left / n][left % n] == target;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        int m = matrix.size(), n = matrix[0].size();
        int left = 0, right = m * n - 1;
        while (left < right) {
            int mid = left + right >> 1;
            int x = mid / n, y = mid % n;
            if (matrix[x][y] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return matrix[left / n][left % n] == target;
    }
};
```

#### Go

```go
func searchMatrix(matrix [][]int, target int) bool {
	m, n := len(matrix), len(matrix[0])
	left, right := 0, m*n-1
	for left < right {
		mid := (left + right) >> 1
		x, y := mid/n, mid%n
		if matrix[x][y] >= target {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return matrix[left/n][left%n] == target
}
```

#### TypeScript

```ts
function searchMatrix(matrix: number[][], target: number): boolean {
    const m = matrix.length;
    const n = matrix[0].length;
    let left = 0;
    let right = m * n;
    while (left < right) {
        const mid = (left + right) >>> 1;
        const i = Math.floor(mid / n);
        const j = mid % n;
        if (matrix[i][j] === target) {
            return true;
        }

        if (matrix[i][j] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return false;
}
```

#### Rust

```rust
use std::cmp::Ordering;
impl Solution {
    pub fn search_matrix(matrix: Vec<Vec<i32>>, target: i32) -> bool {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut i = 0;
        let mut j = n;
        while i < m && j > 0 {
            match matrix[i][j - 1].cmp(&target) {
                Ordering::Equal => {
                    return true;
                }
                Ordering::Less => {
                    i += 1;
                }
                Ordering::Greater => {
                    j -= 1;
                }
            }
        }
        false
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var searchMatrix = function (matrix, target) {
    const m = matrix.length,
        n = matrix[0].length;
    let left = 0,
        right = m * n - 1;
    while (left < right) {
        const mid = (left + right + 1) >> 1;
        const x = Math.floor(mid / n);
        const y = mid % n;
        if (matrix[x][y] <= target) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return matrix[Math.floor(left / n)][left % n] == target;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Tìm từ góc dưới bên trái hoặc góc trên bên phải

<!-- thinking:start -->

> **Tư duy**
>
> Cách 1 trải phẳng ma trận rồi tìm kiếm nhị phân, tốn $O(\log(mn))$, nhưng phải ánh xạ $mid$ sang tọa độ và cần phần tử cuối của một hàng nhỏ hơn phần tử đầu của hàng kế tiếp. Không cần phép ánh xạ chỉ số đó, tính đơn điệu theo hàng/cột cho phép đi từ một góc và loại bỏ trọn một hàng hoặc một cột sau mỗi lần so sánh. Code đơn giản hơn; cái giá phải trả là thời gian $O(m+n)$.

<!-- thinking:end -->

Ở đây, chúng ta bắt đầu tìm từ góc dưới bên trái và di chuyển dần về phía trên bên phải. Ta so sánh phần tử hiện tại $matrix[i][j]$ với $target$:

- Nếu $matrix[i][j] = target$, ta đã tìm thấy giá trị cần tìm và trả về `true`.
- Nếu $matrix[i][j] > target$, mọi phần tử bên phải vị trí hiện tại trong hàng này đều lớn hơn target, vì vậy ta nên di chuyển con trỏ $i$ lên trên, tức là $i = i - 1$.
- Nếu $matrix[i][j] < target$, mọi phần tử phía trên vị trí hiện tại trong cột này đều nhỏ hơn target, vì vậy ta nên di chuyển con trỏ $j$ sang phải, tức là $j = j + 1$.

Nếu sau khi tìm kiếm vẫn không thấy $target$, trả về `false`.

Độ phức tạp thời gian là $O(m + n)$, trong đó $m$ và $n$ lần lượt là số hàng và số cột của ma trận. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        m, n = len(matrix), len(matrix[0])
        i, j = m - 1, 0
        while i >= 0 and j < n:
            if matrix[i][j] == target:
                return True
            if matrix[i][j] > target:
                i -= 1
            else:
                j += 1
        return False
```

#### Java

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = m - 1, j = 0; i >= 0 && j < n;) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                --i;
            } else {
                ++j;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        int m = matrix.size(), n = matrix[0].size();
        for (int i = m - 1, j = 0; i >= 0 && j < n;) {
            if (matrix[i][j] == target) return true;
            if (matrix[i][j] > target)
                --i;
            else
                ++j;
        }
        return false;
    }
};
```

#### Go

```go
func searchMatrix(matrix [][]int, target int) bool {
	m, n := len(matrix), len(matrix[0])
	for i, j := m-1, 0; i >= 0 && j < n; {
		if matrix[i][j] == target {
			return true
		}
		if matrix[i][j] > target {
			i--
		} else {
			j++
		}
	}
	return false
}
```

#### Rust

```rust
use std::cmp::Ordering;
impl Solution {
    pub fn search_matrix(matrix: Vec<Vec<i32>>, target: i32) -> bool {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut left = 0;
        let mut right = m * n;
        while left < right {
            let mid = left + (right - left) / 2;
            let i = mid / n;
            let j = mid % n;
            match matrix[i][j].cmp(&target) {
                Ordering::Equal => {
                    return true;
                }
                Ordering::Less => {
                    left = mid + 1;
                }
                Ordering::Greater => {
                    right = mid;
                }
            }
        }
        false
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var searchMatrix = function (matrix, target) {
    const m = matrix.length,
        n = matrix[0].length;
    for (let i = m - 1, j = 0; i >= 0 && j < n;) {
        if (matrix[i][j] == target) {
            return true;
        }
        if (matrix[i][j] > target) {
            --i;
        } else {
            ++j;
        }
    }
    return false;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
