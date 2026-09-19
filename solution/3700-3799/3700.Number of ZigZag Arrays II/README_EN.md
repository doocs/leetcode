---
comments: true
difficulty: Hard
rating: 2435
source: Weekly Contest 469 Q4
---

<!-- problem:start -->

# [3700. Number of ZigZag Arrays II](https://leetcode.com/problems/number-of-zigzag-arrays-ii)

[中文文档](/solution/3700-3799/3700.Number%20of%20ZigZag%20Arrays%20II/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code>n</code>, <code>l</code>, and <code>r</code>.</p>

<p>A <strong>ZigZag</strong> array of length <code>n</code> is defined as follows:</p>

<ul>
	<li>Each element lies in the range <code>[l, r]</code>.</li>
	<li>No <strong>two</strong> adjacent elements are equal.</li>
	<li>No <strong>three</strong> consecutive elements form a <strong>strictly increasing</strong> or <strong>strictly decreasing</strong> sequence.</li>
</ul>

<p>Return the total number of valid <strong>ZigZag</strong> arrays.</p>

<p>Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>sequence</strong> is said to be <strong>strictly increasing</strong> if each element is strictly greater than its previous one (if exists).</p>

<p>A <strong>sequence</strong> is said to be <strong>strictly decreasing</strong> if each element is strictly smaller than its previous one (if exists).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, l = 4, r = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>There are only 2 valid ZigZag arrays of length <code>n = 3</code> using values in the range <code>[4, 5]</code>:</p>

<ul>
	<li><code>[4, 5, 4]</code></li>
	<li><code>[5, 4, 5]</code></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, l = 1, r = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>​​​​​​​There are 10 valid ZigZag arrays of length <code>n = 3</code> using values in the range <code>[1, 3]</code>:</p>

<ul>
	<li><code>[1, 2, 1]</code>, <code>[1, 3, 1]</code>, <code>[1, 3, 2]</code></li>
	<li><code>[2, 1, 2]</code>, <code>[2, 1, 3]</code>, <code>[2, 3, 1]</code>, <code>[2, 3, 2]</code></li>
	<li><code>[3, 1, 2]</code>, <code>[3, 1, 3]</code>, <code>[3, 2, 3]</code></li>
</ul>

<p>All arrays meet the ZigZag conditions.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= l &lt; r &lt;= 75</code>​​​​​​​</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> $n$ can reach $10^9$, so a length-by-length DP is impossible; the value range has length $m=r-l+1\le 75$, which keeps the state space small. A zigzag array forbids equal neighbors and any strictly monotone triple, which means the comparison direction must flip at every step. We therefore encode a state by the last value and last direction ($2m$ states). The transition is independent of the remaining length, so we build a $2m\times 2m$ matrix, raise it to the $(n-1)$-st power, and multiply by the length-$1$ initial vector.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def zigZagArrays(self, n: int, l: int, r: int) -> int:
        mod = 10**9 + 7
        m = r - l + 1
        size = 2 * m
        trans = [[0] * size for _ in range(size)]
        for x in range(m):
            for y in range(x):
                trans[y][m + x] = 1
        for x in range(m):
            for y in range(x + 1, m):
                trans[m + y][x] = 1

        def mul_mat(a, b):
            res = [[0] * size for _ in range(size)]
            for i in range(size):
                for k in range(size):
                    if a[i][k] == 0:
                        continue
                    aik = a[i][k]
                    for j in range(size):
                        if b[k][j]:
                            res[i][j] = (res[i][j] + aik * b[k][j]) % mod
            return res

        def mul_vec(mat, vec):
            res = [0] * size
            for i in range(size):
                s = 0
                for j in range(size):
                    s = (s + mat[i][j] * vec[j]) % mod
                res[i] = s
            return res

        power = [[int(i == j) for j in range(size)] for i in range(size)]
        exp = n - 1
        while exp:
            if exp & 1:
                power = mul_mat(power, trans)
            trans = mul_mat(trans, trans)
            exp >>= 1
        init = [1] * size
        return sum(mul_vec(power, init)) % mod
```

#### Java

```java
class Solution {
    private static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int size = 2 * m;

        long[][] trans = new long[size][size];
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < x; y++) {
                trans[y][m + x] = 1;
            }
        }

        // down[x] -> up[y] where y > x
        for (int x = 0; x < m; x++) {
            for (int y = x + 1; y < m; y++) {
                trans[m + y][x] = 1;
            }
        }

        long[][] power = matrixPow(trans, n - 1);

        long[] init = new long[size];
        for (int i = 0; i < m; i++) {
            init[i] = 1;
            init[m + i] = 1;
        }

        long[] result = multiply(power, init);

        long ans = 0;
        for (long v : result) {
            ans = (ans + v) % MOD;
        }

        return (int) ans;
    }

    private long[] multiply(long[][] mat, long[] vec) {
        int n = mat.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = 0; j < n; j++) {
                sum = (sum + mat[i][j] * vec[j]) % MOD;
            }
            res[i] = sum;
        }

        return res;
    }

    private long[][] matrixPow(long[][] mat, long exp) {
        int n = mat.length;

        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, mat);
            }

            mat = multiply(mat, mat);
            exp >>= 1;
        }

        return res;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;

                long aik = a[i][k];

                for (int j = 0; j < n; j++) {
                    if (b[k][j] == 0) continue;

                    res[i][j] = (res[i][j] + aik * b[k][j]) % MOD;
                }
            }
        }

        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int zigZagArrays(int n, int l, int r) {
        const int mod = 1e9 + 7;
        int m = r - l + 1;
        int size = 2 * m;
        vector<vector<long long>> trans(size, vector<long long>(size));
        for (int x = 0; x < m; ++x) {
            for (int y = 0; y < x; ++y) {
                trans[y][m + x] = 1;
            }
        }
        for (int x = 0; x < m; ++x) {
            for (int y = x + 1; y < m; ++y) {
                trans[m + y][x] = 1;
            }
        }
        auto power = matrixPow(trans, n - 1, mod);
        vector<long long> init(size, 1);
        auto result = multiply(power, init, mod);
        long long ans = 0;
        for (long long v : result) {
            ans = (ans + v) % mod;
        }
        return ans;
    }

private:
    vector<long long> multiply(vector<vector<long long>>& mat, vector<long long>& vec, int mod) {
        int n = mat.size();
        vector<long long> res(n);
        for (int i = 0; i < n; ++i) {
            long long sum = 0;
            for (int j = 0; j < n; ++j) {
                sum = (sum + mat[i][j] * vec[j]) % mod;
            }
            res[i] = sum;
        }
        return res;
    }

    vector<vector<long long>> multiply(
        vector<vector<long long>>& a, vector<vector<long long>>& b, int mod) {
        int n = a.size();
        vector<vector<long long>> res(n, vector<long long>(n));
        for (int i = 0; i < n; ++i) {
            for (int k = 0; k < n; ++k) {
                if (a[i][k] == 0) {
                    continue;
                }
                long long aik = a[i][k];
                for (int j = 0; j < n; ++j) {
                    if (b[k][j] == 0) {
                        continue;
                    }
                    res[i][j] = (res[i][j] + aik * b[k][j]) % mod;
                }
            }
        }
        return res;
    }

    vector<vector<long long>> matrixPow(vector<vector<long long>>& mat, long long exp, int mod) {
        int n = mat.size();
        vector<vector<long long>> res(n, vector<long long>(n));
        for (int i = 0; i < n; ++i) {
            res[i][i] = 1;
        }
        while (exp > 0) {
            if (exp & 1) {
                res = multiply(res, mat, mod);
            }
            mat = multiply(mat, mat, mod);
            exp >>= 1;
        }
        return res;
    }
};
```

#### Go

```go
func zigZagArrays(n int, l int, r int) int {
	const mod = 1_000_000_007
	m := r - l + 1
	size := 2 * m
	trans := make([][]int, size)
	for i := range trans {
		trans[i] = make([]int, size)
	}
	for x := 0; x < m; x++ {
		for y := 0; y < x; y++ {
			trans[y][m+x] = 1
		}
	}
	for x := 0; x < m; x++ {
		for y := x + 1; y < m; y++ {
			trans[m+y][x] = 1
		}
	}
	power := matrixPow(trans, n-1, mod)
	init := make([]int, size)
	for i := range init {
		init[i] = 1
	}
	result := mulVec(power, init, mod)
	ans := 0
	for _, v := range result {
		ans = (ans + v) % mod
	}
	return ans
}

func mulVec(mat [][]int, vec []int, mod int) []int {
	n := len(mat)
	res := make([]int, n)
	for i := 0; i < n; i++ {
		sum := 0
		for j := 0; j < n; j++ {
			sum = (sum + mat[i][j]*vec[j]) % mod
		}
		res[i] = sum
	}
	return res
}

func mulMat(a, b [][]int, mod int) [][]int {
	n := len(a)
	res := make([][]int, n)
	for i := range res {
		res[i] = make([]int, n)
	}
	for i := 0; i < n; i++ {
		for k := 0; k < n; k++ {
			if a[i][k] == 0 {
				continue
			}
			aik := a[i][k]
			for j := 0; j < n; j++ {
				if b[k][j] == 0 {
					continue
				}
				res[i][j] = (res[i][j] + aik*b[k][j]) % mod
			}
		}
	}
	return res
}

func matrixPow(mat [][]int, exp int, mod int) [][]int {
	n := len(mat)
	res := make([][]int, n)
	for i := range res {
		res[i] = make([]int, n)
		res[i][i] = 1
	}
	for exp > 0 {
		if exp&1 == 1 {
			res = mulMat(res, mat, mod)
		}
		mat = mulMat(mat, mat, mod)
		exp >>= 1
	}
	return res
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
