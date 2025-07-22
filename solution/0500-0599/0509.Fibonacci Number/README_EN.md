---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0509.Fibonacci%20Number/README_EN.md
tags:
    - Recursion
    - Memoization
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [509. Fibonacci Number](https://leetcode.com/problems/fibonacci-number)

[中文文档](/solution/0500-0599/0509.Fibonacci%20Number/README.md)

## Description

<!-- description:start -->

<p>The <b>Fibonacci numbers</b>, commonly denoted <code>F(n)</code> form a sequence, called the <b>Fibonacci sequence</b>, such that each number is the sum of the two preceding ones, starting from <code>0</code> and <code>1</code>. That is,</p>

<pre>
F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n &gt; 1.
</pre>

<p>Given <code>n</code>, calculate <code>F(n)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> F(2) = F(1) + F(0) = 1 + 0 = 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> F(3) = F(2) + F(1) = 1 + 1 = 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> 3
<strong>Explanation:</strong> F(4) = F(3) + F(2) = 2 + 1 = 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 30</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Recurrence

We define two variables $a$ and $b$, initially $a = 0$ and $b = 1$.

Next, we perform $n$ iterations. In each iteration, we update the values of $a$ and $b$ to $b$ and $a + b$, respectively.

Finally, we return $a$.

The time complexity is $O(n)$, where $n$ is the given integer. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def fib(self, n: int) -> int:
        a, b = 0, 1
        for _ in range(n):
            a, b = b, a + b
        return a
```

#### Java

```java
class Solution {
    public int fib(int n) {
        int a = 0, b = 1;
        while (n-- > 0) {
            int c = a + b;
            a = b;
            b = c;
        }
        return a;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int fib(int n) {
        int a = 0, b = 1;
        while (n--) {
            int c = a + b;
            a = b;
            b = c;
        }
        return a;
    }
};
```

#### Go

```go
func fib(n int) int {
	a, b := 0, 1
	for i := 0; i < n; i++ {
		a, b = b, a+b
	}
	return a
}
```

#### TypeScript

```ts
function fib(n: number): number {
    let [a, b] = [0, 1];
    while (n--) {
        [a, b] = [b, a + b];
    }
    return a;
}
```

#### Rust

```rust
impl Solution {
    pub fn fib(n: i32) -> i32 {
        let mut a = 0;
        let mut b = 1;
        for _ in 0..n {
            let t = b;
            b = a + b;
            a = t;
        }
        a
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number}
 */
var fib = function (n) {
    let [a, b] = [0, 1];
    while (n--) {
        [a, b] = [b, a + b];
    }
    return a;
};
```

#### PHP

```php
class Solution {
    /**
     * @param Integer $n
     * @return Integer
     */
    function fib($n) {
        $a = 0;
        $b = 1;
        for ($i = 0; $i < $n; $i++) {
            $temp = $a;
            $a = $b;
            $b = $temp + $b;
        }
        return $a;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Matrix Exponentiation

We define $\textit{Fib}(n)$ as a $1 \times 2$ matrix $\begin{bmatrix} F_n & F_{n - 1} \end{bmatrix}$, where $F_n$ and $F_{n - 1}$ are the $n$-th and $(n - 1)$-th Fibonacci numbers, respectively.

We want to derive $\textit{Fib}(n)$ from $\textit{Fib}(n - 1) = \begin{bmatrix} F_{n - 1} & F_{n - 2} \end{bmatrix}$. In other words, we need a matrix $\textit{base}$ such that $\textit{Fib}(n - 1) \times \textit{base} = \textit{Fib}(n)$, i.e.:

$$
\begin{bmatrix}
F_{n - 1} & F_{n - 2}
\end{bmatrix} \times \textit{base} = \begin{bmatrix} F_n & F_{n - 1} \end{bmatrix}
$$

Since $F_n = F_{n - 1} + F_{n - 2}$, the first column of the matrix $\textit{base}$ is:

$$
\begin{bmatrix}
1 \\
1
\end{bmatrix}
$$

The second column is:

$$
\begin{bmatrix}
1 \\
0
\end{bmatrix}
$$

Thus, we have:

$$
\begin{bmatrix} F_{n - 1} & F_{n - 2} \end{bmatrix} \times \begin{bmatrix}1 & 1 \\ 1 & 0\end{bmatrix} = \begin{bmatrix} F_n & F_{n - 1} \end{bmatrix}
$$

We define the initial matrix $res = \begin{bmatrix} 1 & 0 \end{bmatrix}$, then $F_n$ is equal to the second element of the first row of the result matrix obtained by multiplying $res$ with $\textit{base}^{n}$. We can solve this using matrix exponentiation.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
import numpy as np


class Solution:
    def fib(self, n: int) -> int:
        factor = np.asmatrix([(1, 1), (1, 0)], np.dtype("O"))
        res = np.asmatrix([(1, 0)], np.dtype("O"))
        while n:
            if n & 1:
                res = res * factor
            factor = factor * factor
            n >>= 1
        return res[0, 1]
```

#### Java

```java
class Solution {
    public int fib(int n) {
        int[][] a = {{1, 1}, {1, 0}};
        return pow(a, n)[0][1];
    }

    private int[][] mul(int[][] a, int[][] b) {
        int m = a.length, n = b[0].length;
        int[][] c = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < b.length; ++k) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    private int[][] pow(int[][] a, int n) {
        int[][] res = {{1, 0}};
        while (n > 0) {
            if ((n & 1) == 1) {
                res = mul(res, a);
            }
            a = mul(a, a);
            n >>= 1;
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int fib(int n) {
        vector<vector<int>> a = {{1, 1}, {1, 0}};
        return qpow(a, n)[0][1];
    }

    vector<vector<int>> mul(vector<vector<int>>& a, vector<vector<int>>& b) {
        int m = a.size(), n = b[0].size();
        vector<vector<int>> c(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < b.size(); ++k) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    vector<vector<int>> qpow(vector<vector<int>>& a, int n) {
        vector<vector<int>> res = {{1, 0}};
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
func fib(n int) int {
	a := [][]int{{1, 1}, {1, 0}}
	return pow(a, n)[0][1]
}

func mul(a, b [][]int) [][]int {
	m, n := len(a), len(b[0])
	c := make([][]int, m)
	for i := range c {
		c[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for k := 0; k < len(b); k++ {
				c[i][j] = c[i][j] + a[i][k]*b[k][j]
			}
		}
	}
	return c
}

func pow(a [][]int, n int) [][]int {
	res := [][]int{{1, 0}}
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
function fib(n: number): number {
    const a: number[][] = [
        [1, 1],
        [1, 0],
    ];
    return pow(a, n)[0][1];
}

function mul(a: number[][], b: number[][]): number[][] {
    const [m, n] = [a.length, b[0].length];
    const c = Array.from({ length: m }, () => Array.from({ length: n }, () => 0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (let k = 0; k < b.length; ++k) {
                c[i][j] += a[i][k] * b[k][j];
            }
        }
    }
    return c;
}

function pow(a: number[][], n: number): number[][] {
    let res = [[1, 0]];
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

#### Rust

```rust
impl Solution {
    pub fn fib(n: i32) -> i32 {
        let a = vec![vec![1, 1], vec![1, 0]];
        pow(a, n as usize)[0][1]
    }
}

fn mul(a: Vec<Vec<i32>>, b: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
    let m = a.len();
    let n = b[0].len();
    let mut c = vec![vec![0; n]; m];

    for i in 0..m {
        for j in 0..n {
            for k in 0..b.len() {
                c[i][j] += a[i][k] * b[k][j];
            }
        }
    }
    c
}

fn pow(mut a: Vec<Vec<i32>>, mut n: usize) -> Vec<Vec<i32>> {
    let mut res = vec![vec![1, 0], vec![0, 1]];

    while n > 0 {
        if n & 1 == 1 {
            res = mul(res, a.clone());
        }
        a = mul(a.clone(), a);
        n >>= 1;
    }
    res
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number}
 */
var fib = function (n) {
    const a = [
        [1, 1],
        [1, 0],
    ];
    return pow(a, n)[0][1];
};

function mul(a, b) {
    const m = a.length,
        n = b[0].length;
    const c = Array.from({ length: m }, () => Array(n).fill(0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (let k = 0; k < b.length; ++k) {
                c[i][j] += a[i][k] * b[k][j];
            }
        }
    }
    return c;
}

function pow(a, n) {
    let res = [
        [1, 0],
        [0, 1],
    ];
    while (n > 0) {
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
