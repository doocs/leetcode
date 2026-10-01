---
comments: true
difficulty: Medium
tags:
    - Math
    - Dynamic Programming
    - Combinatorics
---

<!-- problem:start -->

# [62. Unique Paths](https://leetcode.com/problems/unique-paths)

[中文文档](/solution/0000-0099/0062.Unique%20Paths/README.md)

## Mô tả

<!-- description:start -->

<p>Có một robot trên một lưới <code>m x n</code>. Ban đầu robot nằm ở <strong>góc trên bên trái</strong> (tức là <code>grid[0][0]</code>). Robot cố gắng di chuyển đến <strong>góc dưới bên phải</strong> (tức là <code>grid[m - 1][n - 1]</code>). Tại bất kỳ thời điểm nào, robot chỉ có thể di chuyển xuống dưới hoặc sang phải.</p>

<p>Cho hai số nguyên <code>m</code> và <code>n</code>, hãy trả về <em>số đường đi duy nhất có thể có mà robot có thể thực hiện để đến góc dưới bên phải</em>.</p>

<p>Các trường hợp kiểm thử được tạo sao cho đáp án nhỏ hơn hoặc bằng <code>2 * 10<sup>9</sup></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0062.Unique%20Paths/images/robot_maze.png" style="width: 400px; height: 183px;" />
<pre>
<strong>Đầu vào:</strong> m = 3, n = 7
<strong>Đầu ra:</strong> 28
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> m = 3, n = 2
<strong>Đầu ra:</strong> 3
<strong>Giải thích:</strong> Từ góc trên bên trái, có tổng cộng 3 cách để đến góc dưới bên phải:
1. Right -&gt; Down -&gt; Down
2. Down -&gt; Down -&gt; Right
3. Down -&gt; Right -&gt; Down
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quy hoạch động

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là tìm kiếm: vì chỉ có thể đi sang phải hoặc xuống dưới, ta liệt kê mọi đường đi. Với $m, n \le 100$, số lượng đường đi mang tính tổ hợp nên việc tìm kiếm trực tiếp sẽ bùng nổ.
>
> Điểm nghẽn là việc duyệt lại cùng một ô. Các đường đi đến $(i, j)$ chỉ đến từ phía trên hoặc bên trái và không chồng lấp, vì vậy các bài toán con được cộng lại.
>
> Ta lưu số lượng đó trong $f[i][j]$ và điền theo thứ tự từng hàng để các giá trị phụ thuộc đã tồn tại. Bắt đầu từ $1$; ô dưới cùng bên phải chính là đáp án.

<!-- thinking:end -->

Ta định nghĩa $f[i][j]$ là số đường đi từ góc trên bên trái đến $(i, j)$, ban đầu $f[0][0] = 1$, và đáp án là $f[m - 1][n - 1]$.

Xét $f[i][j]$:

- Nếu $i > 0$, thì có thể đến $f[i][j]$ bằng cách đi một bước từ $f[i - 1][j]$, do đó $f[i][j] = f[i][j] + f[i - 1][j]$;
- Nếu $j > 0$, thì có thể đến $f[i][j]$ bằng cách đi một bước từ $f[i][j - 1]$, do đó $f[i][j] = f[i][j] + f[i][j - 1]$.

Do đó, ta có phương trình chuyển trạng thái sau:

$$
f[i][j] = \begin{cases}
1 & i = 0, j = 0 \\
f[i - 1][j] + f[i][j - 1] & \textit{otherwise}
\end{cases}
$$

Đáp án cuối cùng là $f[m - 1][n - 1]$.

Độ phức tạp thời gian là $O(m \times n)$, và độ phức tạp không gian là $O(m \times n)$. Trong đó, $m$ và $n$ lần lượt là số hàng và số cột của lưới.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        f = [[0] * n for _ in range(m)]
        f[0][0] = 1
        for i in range(m):
            for j in range(n):
                if i:
                    f[i][j] += f[i - 1][j]
                if j:
                    f[i][j] += f[i][j - 1]
        return f[-1][-1]
```

#### Java

```java
class Solution {
    public int uniquePaths(int m, int n) {
        var f = new int[m][n];
        f[0][0] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i > 0) {
                    f[i][j] += f[i - 1][j];
                }
                if (j > 0) {
                    f[i][j] += f[i][j - 1];
                }
            }
        }
        return f[m - 1][n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int uniquePaths(int m, int n) {
        vector<vector<int>> f(m, vector<int>(n));
        f[0][0] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i) {
                    f[i][j] += f[i - 1][j];
                }
                if (j) {
                    f[i][j] += f[i][j - 1];
                }
            }
        }
        return f[m - 1][n - 1];
    }
};
```

#### Go

```go
func uniquePaths(m int, n int) int {
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	f[0][0] = 1
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if i > 0 {
				f[i][j] += f[i-1][j]
			}
			if j > 0 {
				f[i][j] += f[i][j-1]
			}
		}
	}
	return f[m-1][n-1]
}
```

#### TypeScript

```ts
function uniquePaths(m: number, n: number): number {
    const f: number[][] = Array(m)
        .fill(0)
        .map(() => Array(n).fill(0));
    f[0][0] = 1;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i > 0) {
                f[i][j] += f[i - 1][j];
            }
            if (j > 0) {
                f[i][j] += f[i][j - 1];
            }
        }
    }
    return f[m - 1][n - 1];
}
```

#### Rust

```rust
impl Solution {
    pub fn unique_paths(m: i32, n: i32) -> i32 {
        let (m, n) = (m as usize, n as usize);
        let mut f = vec![1; n];
        for i in 1..m {
            for j in 1..n {
                f[j] += f[j - 1];
            }
        }
        f[n - 1]
    }
}
```

#### JavaScript

```js
/**
 * @param {number} m
 * @param {number} n
 * @return {number}
 */
var uniquePaths = function (m, n) {
    const f = Array(m)
        .fill(0)
        .map(() => Array(n).fill(0));
    f[0][0] = 1;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i > 0) {
                f[i][j] += f[i - 1][j];
            }
            if (j > 0) {
                f[i][j] += f[i][j - 1];
            }
        }
    }
    return f[m - 1][n - 1];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Quy hoạch động (Biên được điền sẵn)

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 kiểm tra “có phía trên / có bên trái” trên mọi ô, nên biên phải liên tục nhận thêm các nhánh.
>
> Hàng đầu tiên chỉ có thể đi đến từ bên trái, cột đầu tiên chỉ có thể đi đến từ phía trên, và cả hai đều toàn là $1$. Điền sẵn các biên đó để phần bên trong cộng mà không cần điều kiện. Độ phức tạp không đổi, code rõ ràng hơn.

<!-- thinking:end -->

Điền $1$ cho hàng đầu tiên và cột đầu tiên, sau đó chỉ tính các ô bên trong theo công thức $f[i][j] = f[i-1][j] + f[i][j-1]$. Độ phức tạp thời gian và không gian vẫn là $O(m \times n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        f = [[1] * n for _ in range(m)]
        for i in range(1, m):
            for j in range(1, n):
                f[i][j] = f[i - 1][j] + f[i][j - 1]
        return f[-1][-1]
```

#### Java

```java
class Solution {
    public int uniquePaths(int m, int n) {
        var f = new int[m][n];
        for (var g : f) {
            Arrays.fill(g, 1);
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; j++) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int uniquePaths(int m, int n) {
        vector<vector<int>> f(m, vector<int>(n, 1));
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }
};
```

#### Go

```go
func uniquePaths(m int, n int) int {
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = 1
		}
	}
	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			f[i][j] = f[i-1][j] + f[i][j-1]
		}
	}
	return f[m-1][n-1]
}
```

#### TypeScript

```ts
function uniquePaths(m: number, n: number): number {
    const f: number[][] = Array(m)
        .fill(0)
        .map(() => Array(n).fill(1));
    for (let i = 1; i < m; ++i) {
        for (let j = 1; j < n; ++j) {
            f[i][j] = f[i - 1][j] + f[i][j - 1];
        }
    }
    return f[m - 1][n - 1];
}
```

#### JavaScript

```js
/**
 * @param {number} m
 * @param {number} n
 * @return {number}
 */
var uniquePaths = function (m, n) {
    const f = Array(m)
        .fill(0)
        .map(() => Array(n).fill(1));
    for (let i = 1; i < m; ++i) {
        for (let j = 1; j < n; ++j) {
            f[i][j] = f[i - 1][j] + f[i][j - 1];
        }
    }
    return f[m - 1][n - 1];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 3: Quy hoạch động (Mảng cuộn)

<!-- thinking:start -->

> **Tư duy**
>
> Hai lời giải đầu giữ một bảng đầy đủ kích thước $m \times n$. $f[i][j]$ chỉ cần hàng trước đó $f[i-1][j]$ và ô bên trái $f[i][j-1]$, nên có thể loại bỏ chiều thứ nhất.
>
> Sau khi nén thành một chiều, $f[j]$ vẫn là hàng trước đó cho đến khi ta cập nhật nó; cộng $f[j-1]$ để nhận được hàng hiện tại. Không gian giảm xuống $O(n)$, còn thời gian không đổi.

<!-- thinking:end -->

$f[i][j]$ chỉ phụ thuộc vào hàng trước đó và ô bên trái, vì vậy một mảng một chiều có độ dài $n$ là đủ. Độ phức tạp thời gian là $O(m \times n)$ và độ phức tạp không gian là $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        f = [1] * n
        for _ in range(1, m):
            for j in range(1, n):
                f[j] += f[j - 1]
        return f[-1]
```

#### Java

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[j] += f[j - 1];
            }
        }
        return f[n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int uniquePaths(int m, int n) {
        vector<int> f(n, 1);
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[j] += f[j - 1];
            }
        }
        return f[n - 1];
    }
};
```

#### Go

```go
func uniquePaths(m int, n int) int {
	f := make([]int, n+1)
	for i := range f {
		f[i] = 1
	}
	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			f[j] += f[j-1]
		}
	}
	return f[n-1]
}
```

#### TypeScript

```ts
function uniquePaths(m: number, n: number): number {
    const f: number[] = Array(n).fill(1);
    for (let i = 1; i < m; ++i) {
        for (let j = 1; j < n; ++j) {
            f[j] += f[j - 1];
        }
    }
    return f[n - 1];
}
```

#### JavaScript

```js
/**
 * @param {number} m
 * @param {number} n
 * @return {number}
 */
var uniquePaths = function (m, n) {
    const f = Array(n).fill(1);
    for (let i = 1; i < m; ++i) {
        for (let j = 1; j < n; ++j) {
            f[j] += f[j - 1];
        }
    }
    return f[n - 1];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
