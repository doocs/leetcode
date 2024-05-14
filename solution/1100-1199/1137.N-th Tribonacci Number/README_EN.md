---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1137.N-th%20Tribonacci%20Number/README_EN.md
rating: 1142
tags:
    - Memoization
    - Math
    - Dynamic Programming
---

# [1137. N-th Tribonacci Number](https://leetcode.com/problems/n-th-tribonacci-number)

[中文文档](/solution/1100-1199/1137.N-th%20Tribonacci%20Number/README.md)

## Description

<p>The Tribonacci sequence T<sub>n</sub> is defined as follows:&nbsp;</p>

<p>T<sub>0</sub> = 0, T<sub>1</sub> = 1, T<sub>2</sub> = 1, and T<sub>n+3</sub> = T<sub>n</sub> + T<sub>n+1</sub> + T<sub>n+2</sub> for n &gt;= 0.</p>

<p>Given <code>n</code>, return the value of T<sub>n</sub>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> n = 4

<strong>Output:</strong> 4

<strong>Explanation:</strong>

T_3 = 0 + 1 + 1 = 2

T_4 = 1 + 1 + 2 = 4

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> n = 25

<strong>Output:</strong> 1389537

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>0 &lt;= n &lt;= 37</code></li>

    <li>The answer is guaranteed to fit within a 32-bit integer, ie. <code>answer &lt;= 2^31 - 1</code>.</li>

</ul>

## Solutions

### Solution 1: Dynamic Programming

According to the recurrence relation given in the problem, we can use dynamic programming to solve it.

We define three variables $a$, $b$, $c$ to represent $T_{n-3}$, $T_{n-2}$, $T_{n-1}$, respectively, with initial values of $0$, $1$, $1$.

Then we decrease $n$ to $0$, updating the values of $a$, $b$, $c$ each time, until $n$ is $0$, at which point the answer is $a$.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Here, $n$ is the given integer.

<!-- tabs:start -->

```python
class Solution:
    def tribonacci(self, n: int) -> int:
        a, b, c = 0, 1, 1
        for _ in range(n):
            a, b, c = b, c, a + b + c
        return a
```

```java
class Solution {
    public int tribonacci(int n) {
        int a = 0, b = 1, c = 1;
        while (n-- > 0) {
            int d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return a;
    }
}
```

```cpp
class Solution {
public:
    int tribonacci(int n) {
        long long a = 0, b = 1, c = 1;
        while (n--) {
            long long d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return (int) a;
    }
};
```

```go
func tribonacci(n int) int {
	a, b, c := 0, 1, 1
	for i := 0; i < n; i++ {
		a, b, c = b, c, a+b+c
	}
	return a
}
```

```ts
function tribonacci(n: number): number {
    if (n === 0) {
        return 0;
    }
    if (n < 3) {
        return 1;
    }
    const a = [
        [1, 1, 0],
        [1, 0, 1],
        [1, 0, 0],
    ];
    return pow(a, n - 3)[0].reduce((a, b) => a + b);
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
    let res = [[1, 1, 0]];
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

```js
/**
 * @param {number} n
 * @return {number}
 */
var tribonacci = function (n) {
    let a = 0;
    let b = 1;
    let c = 1;
    while (n--) {
        let d = a + b + c;
        a = b;
        b = c;
        c = d;
    }
    return a;
};
```

```php
class Solution {
    /**
     * @param Integer $n
     * @return Integer
     */
    function tribonacci($n) {
        if ($n == 0) {
            return 0;
        } elseif ($n == 1 || $n == 2) {
            return 1;
        }
        $dp = [0, 1, 1];
        for ($i = 3; $i <= $n; $i++) {
            $dp[$i] = $dp[$i - 1] + $dp[$i - 2] + $dp[$i - 3];
        }
        return $dp[$n];
    }
}
```

<!-- tabs:end -->

### Solution 2: Matrix Exponentiation to Accelerate Recurrence

We define $Tib(n)$ as a $1 \times 3$ matrix $\begin{bmatrix} T_n & T_{n - 1} & T_{n - 2} \end{bmatrix}$, where $T_n$, $T_{n - 1}$ and $T_{n - 2}$ represent the $n$th, $(n - 1)$th and $(n - 2)$th Tribonacci numbers, respectively.

We hope to derive $Tib(n)$ from $Tib(n-1) = \begin{bmatrix} T_{n - 1} & T_{n - 2} & T_{n - 3} \end{bmatrix}$. That is, we need a matrix $base$ such that $Tib(n - 1) \times base = Tib(n)$, i.e.,

$$
\begin{bmatrix}
T_{n - 1} & T_{n - 2} & T_{n - 3}
\end{bmatrix} \times base = \begin{bmatrix} T_n & T_{n - 1} & T_{n - 2} \end{bmatrix}
$$

Since $T_n = T_{n - 1} + T_{n - 2} + T_{n - 3}$, the matrix $base$ is:

$$
\begin{bmatrix}
 1 & 1 & 0 \\
 1 & 0 & 1 \\
 1 & 0 & 0
\end{bmatrix}
$$

We define the initial matrix $res = \begin{bmatrix} 1 & 1  & 0 \end{bmatrix}$, then $T_n$ is equal to the sum of all elements in the result matrix of $res$ multiplied by $base^{n - 3}$. This can be solved using matrix exponentiation.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def tribonacci(self, n: int) -> int:
        def mul(a: List[List[int]], b: List[List[int]]) -> List[List[int]]:
            m, n = len(a), len(b[0])
            c = [[0] * n for _ in range(m)]
            for i in range(m):
                for j in range(n):
                    for k in range(len(a[0])):
                        c[i][j] = c[i][j] + a[i][k] * b[k][j]
            return c

        def pow(a: List[List[int]], n: int) -> List[List[int]]:
            res = [[1, 1, 0]]
            while n:
                if n & 1:
                    res = mul(res, a)
                n >>= 1
                a = mul(a, a)
            return res

        if n == 0:
            return 0
        if n < 3:
            return 1
        a = [[1, 1, 0], [1, 0, 1], [1, 0, 0]]
        return sum(pow(a, n - 3)[0])
```

```java
class Solution {
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }
        int[][] a = {{1, 1, 0}, {1, 0, 1}, {1, 0, 0}};
        int[][] res = pow(a, n - 3);
        int ans = 0;
        for (int x : res[0]) {
            ans += x;
        }
        return ans;
    }

    private int[][] mul(int[][] a, int[][] b) {
        int m = a.length, n = b[0].length;
        int[][] c = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < b.length; ++k) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    private int[][] pow(int[][] a, int n) {
        int[][] res = {{1, 1, 0}};
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

```cpp
class Solution {
public:
    int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }
        vector<vector<ll>> a = {{1, 1, 0}, {1, 0, 1}, {1, 0, 0}};
        vector<vector<ll>> res = pow(a, n - 3);
        return accumulate(res[0].begin(), res[0].end(), 0);
    }

private:
    using ll = long long;
    vector<vector<ll>> mul(vector<vector<ll>>& a, vector<vector<ll>>& b) {
        int m = a.size(), n = b[0].size();
        vector<vector<ll>> c(m, vector<ll>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < b.size(); ++k) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    vector<vector<ll>> pow(vector<vector<ll>>& a, int n) {
        vector<vector<ll>> res = {{1, 1, 0}};
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

```go
func tribonacci(n int) (ans int) {
	if n == 0 {
		return 0
	}
	if n < 3 {
		return 1
	}
	a := [][]int{{1, 1, 0}, {1, 0, 1}, {1, 0, 0}}
	res := pow(a, n-3)
	for _, x := range res[0] {
		ans += x
	}
	return
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
				c[i][j] += a[i][k] * b[k][j]
			}
		}
	}
	return c
}

func pow(a [][]int, n int) [][]int {
	res := [][]int{{1, 1, 0}}
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

```js
/**
 * @param {number} n
 * @return {number}
 */
var tribonacci = function (n) {
    if (n === 0) {
        return 0;
    }
    if (n < 3) {
        return 1;
    }
    const a = [
        [1, 1, 0],
        [1, 0, 1],
        [1, 0, 0],
    ];
    return pow(a, n - 3)[0].reduce((a, b) => a + b);
};

function mul(a, b) {
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

function pow(a, n) {
    let res = [[1, 1, 0]];
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

### Solution 3

<!-- tabs:start -->

```python
import numpy as np


class Solution:
    def tribonacci(self, n: int) -> int:
        if n == 0:
            return 0
        if n < 3:
            return 1
        factor = np.mat([(1, 1, 0), (1, 0, 1), (1, 0, 0)], np.dtype("O"))
        res = np.mat([(1, 1, 0)], np.dtype("O"))
        n -= 3
        while n:
            if n & 1:
                res *= factor
            factor *= factor
            n >>= 1
        return res.sum()
```

<!-- tabs:end -->

<!-- end -->
