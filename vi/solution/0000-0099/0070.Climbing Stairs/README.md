---
comments: true
difficulty: Easy
tags:
    - Memoization
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs)

[中文文档](/solution/0000-0099/0070.Climbing%20Stairs/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn đang leo cầu thang. Cần <code>n</code> bậc để lên đến đỉnh.</p>

<p>Mỗi lần bạn có thể leo <code>1</code> hoặc <code>2</code> bậc. Có bao nhiêu cách khác nhau để bạn có thể leo lên đỉnh?</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 2
<strong>Đầu ra:</strong> 2
<strong>Giải thích:</strong> Có hai cách để leo lên đỉnh.
1. 1 step + 1 step
2. 2 steps
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 3
<strong>Đầu ra:</strong> 3
<strong>Giải thích:</strong> Có ba cách để leo lên đỉnh.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 45</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Đệ quy

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là đệ quy: bước cuối cùng là $1$ hoặc $2$, nên $f(n) = f(n-1) + f(n-2)$. $n \le 45$, và đệ quy ngây thơ sẽ khai triển lại các bài toán con giống nhau, khiến số lượng tăng bùng nổ.
>
> Nút thắt là sự chồng lặp. Đây là dãy Fibonacci, vì vậy chúng ta có thể tính từ dưới lên.
>
> $f[i]$ chỉ phụ thuộc vào hai phần tử trước đó, nên chỉ cần hai biến luân phiên: thời gian $O(n)$ và không gian bổ sung $O(1)$.

<!-- thinking:end -->

Chúng ta định nghĩa $f[i]$ biểu diễn số cách để leo đến bậc thứ $i$, khi đó $f[i]$ có thể được chuyển từ $f[i - 1]$ và $f[i - 2]$, tức là:

$$
f[i] = f[i - 1] + f[i - 2]
$$

Điều kiện ban đầu là $f[0] = 1$ và $f[1] = 1$, tức là số cách để leo đến bậc thứ 0 là 1, và số cách để leo đến bậc thứ 1 cũng là 1.

Đáp án là $f[n]$.

Vì $f[i]$ chỉ liên quan đến $f[i - 1]$ và $f[i - 2]$, chúng ta có thể dùng hai biến $a$ và $b$ để duy trì số cách hiện tại, giảm độ phức tạp không gian xuống $O(1)$.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def climbStairs(self, n: int) -> int:
        a, b = 0, 1
        for _ in range(n):
            a, b = b, a + b
        return b
```

#### Java

```java
class Solution {
    public int climbStairs(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; ++i) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int climbStairs(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; ++i) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
};
```

#### Go

```go
func climbStairs(n int) int {
	a, b := 0, 1
	for i := 0; i < n; i++ {
		a, b = b, a+b
	}
	return b
}
```

#### TypeScript

```ts
function climbStairs(n: number): number {
    let p = 1;
    let q = 1;
    for (let i = 1; i < n; i++) {
        [p, q] = [q, p + q];
    }
    return q;
}
```

#### Rust

```rust
impl Solution {
    pub fn climb_stairs(n: i32) -> i32 {
        let (mut p, mut q) = (1, 1);
        for i in 1..n {
            let t = p + q;
            p = q;
            q = t;
        }
        q
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number}
 */
var climbStairs = function (n) {
    let a = 0,
        b = 1;
    for (let i = 0; i < n; ++i) {
        const c = a + b;
        a = b;
        b = c;
    }
    return b;
};
```

#### PHP

```php
class Solution {
    /**
     * @param Integer $n
     * @return Integer
     */
    function climbStairs($n) {
        if ($n <= 2) {
            return $n;
        }
        $dp = [0, 1, 2];
        for ($i = 3; $i <= $n; $i++) {
            $dp[$i] = $dp[$i - 2] + $dp[$i - 1];
        }
        return $dp[$n];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Lũy thừa nhanh ma trận để tăng tốc đệ quy

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 đã là $O(n)$, đủ cho $n \le 45$, nhưng một truy hồi tuyến tính vẫn chậm khi $n$ tăng lên.
>
> Truy hồi tuyến tính thuần nhất có thể được biểu diễn bằng phép nhân ma trận: $F_n$ được suy ra từ $F_{n-1}$ nhân với một ma trận chuyển cố định $2 \times 2$. Lũy thừa ma trận nhanh nén $n$ bước thành $O(\log n)$. Hằng số lớn hơn; hãy dùng nó khi cùng một truy hồi cần được tăng tốc.

<!-- thinking:end -->

Chúng ta đặt $Fib(n)$ biểu diễn một ma trận $1 \times 2$ $\begin{bmatrix} F_n & F_{n - 1} \end{bmatrix}$, trong đó $F_n$ và $F_{n - 1}$ lần lượt là số Fibonacci thứ $n$ và thứ $(n - 1)$.

Chúng ta muốn suy ra $Fib(n)$ dựa trên $Fib(n-1) = \begin{bmatrix} F_{n - 1} & F_{n - 2} \end{bmatrix}$. Nói cách khác, chúng ta cần một ma trận $base$, sao cho $Fib(n - 1) \times base = Fib(n)$, tức là:

$$
\begin{bmatrix}
F_{n - 1} & F_{n - 2}
\end{bmatrix} \times base = \begin{bmatrix} F_n & F_{n - 1} \end{bmatrix}
$$

Vì $F_n = F_{n - 1} + F_{n - 2}$, cột đầu tiên của ma trận $base$ là:

$$
\begin{bmatrix}
1 \\
1
\end{bmatrix}
$$

Cột thứ hai là:

$$
\begin{bmatrix}
1 \\
0
\end{bmatrix}
$$

Do đó:

$$
\begin{bmatrix} F_{n - 1} & F_{n - 2} \end{bmatrix} \times \begin{bmatrix}1 & 1 \\ 1 & 0\end{bmatrix} = \begin{bmatrix} F_n & F_{n - 1} \end{bmatrix}
$$

Chúng ta định nghĩa ma trận ban đầu $res = \begin{bmatrix} 1 & 1 \end{bmatrix}$, khi đó $F_n$ bằng phần tử đầu tiên của hàng đầu tiên trong ma trận kết quả của $res$ nhân với $base^{n - 1}$. Chúng ta có thể giải bằng lũy thừa nhanh ma trận.

Độ phức tạp thời gian là $O(\log n)$, và độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
import numpy as np


class Solution:
    def climbStairs(self, n: int) -> int:
        res = np.asmatrix([(1, 1)], np.dtype("O"))
        factor = np.asmatrix([(1, 1), (1, 0)], np.dtype("O"))
        n -= 1
        while n:
            if n & 1:
                res *= factor
            factor *= factor
            n >>= 1
        return res[0, 0]
```

#### Java

```java
class Solution {
    private final int[][] a = {{1, 1}, {1, 0}};

    public int climbStairs(int n) {
        return pow(a, n - 1)[0][0];
    }

    private int[][] mul(int[][] a, int[][] b) {
        int m = a.length, n = b[0].length;
        int[][] c = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < a[0].length; ++k) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    private int[][] pow(int[][] a, int n) {
        int[][] res = {{1, 1}, {0, 0}};
        while (n > 0) {
            if ((n & 1) == 1) {
                res = mul(res, a);
            }
            n >>= 1;
            a = mul(a, a);
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int climbStairs(int n) {
        vector<vector<long long>> a = {{1, 1}, {1, 0}};
        return pow(a, n - 1)[0][0];
    }

private:
    vector<vector<long long>> mul(vector<vector<long long>>& a, vector<vector<long long>>& b) {
        int m = a.size(), n = b[0].size();
        vector<vector<long long>> res(m, vector<long long>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < a[0].size(); ++k) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }

    vector<vector<long long>> pow(vector<vector<long long>>& a, int n) {
        vector<vector<long long>> res = {{1, 1}, {0, 0}};
        while (n) {
            if (n & 1) {
                res = mul(res, a);
            }
            a = mul(a, a);
            n >>= 1;
        }
        return res;
    }
};
```

#### Go

```go
type matrix [2][2]int

func climbStairs(n int) int {
	a := matrix{{1, 1}, {1, 0}}
	return pow(a, n-1)[0][0]
}

func mul(a, b matrix) (c matrix) {
	m, n := len(a), len(b[0])
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for k := 0; k < len(a[0]); k++ {
				c[i][j] += a[i][k] * b[k][j]
			}
		}
	}
	return
}

func pow(a matrix, n int) matrix {
	res := matrix{{1, 1}, {0, 0}}
	for n > 0 {
		if n&1 == 1 {
			res = mul(res, a)
		}
		a = mul(a, a)
		n >>= 1
	}
	return res
}
```

#### TypeScript

```ts
function climbStairs(n: number): number {
    const a = [
        [1, 1],
        [1, 0],
    ];
    return pow(a, n - 1)[0][0];
}

function mul(a: number[][], b: number[][]): number[][] {
    const [m, n] = [a.length, b[0].length];
    const c = Array(m)
        .fill(0)
        .map(() => Array(n).fill(0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (let k = 0; k < a[0].length; ++k) {
                c[i][j] += a[i][k] * b[k][j];
            }
        }
    }
    return c;
}

function pow(a: number[][], n: number): number[][] {
    let res = [
        [1, 1],
        [0, 0],
    ];
    while (n) {
        if (n & 1) {
            res = mul(res, a);
        }
        a = mul(a, a);
        n >>= 1;
    }
    return res;
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number}
 */
var climbStairs = function (n) {
    const a = [
        [1, 1],
        [1, 0],
    ];
    return pow(a, n - 1)[0][0];
};

function mul(a, b) {
    const [m, n] = [a.length, b[0].length];
    const c = Array(m)
        .fill(0)
        .map(() => Array(n).fill(0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (let k = 0; k < a[0].length; ++k) {
                c[i][j] += a[i][k] * b[k][j];
            }
        }
    }
    return c;
}

function pow(a, n) {
    let res = [
        [1, 1],
        [0, 0],
    ];
    while (n) {
        if (n & 1) {
            res = mul(res, a);
        }
        a = mul(a, a);
        n >>= 1;
    }
    return res;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
