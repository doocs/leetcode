---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2787.Ways%20to%20Express%20an%20Integer%20as%20Sum%20of%20Powers/README_EN.md
rating: 1817
source: Biweekly Contest 109 Q4
tags:
    - Dynamic Programming
---

# [2787. Ways to Express an Integer as Sum of Powers](https://leetcode.com/problems/ways-to-express-an-integer-as-sum-of-powers)

[中文文档](/solution/2700-2799/2787.Ways%20to%20Express%20an%20Integer%20as%20Sum%20of%20Powers/README.md)

## Description

<p>Given two <strong>positive</strong> integers <code>n</code> and <code>x</code>.</p>

<p>Return <em>the number of ways </em><code>n</code><em> can be expressed as the sum of the </em><code>x<sup>th</sup></code><em> power of <strong>unique</strong> positive integers, in other words, the number of sets of unique integers </em><code>[n<sub>1</sub>, n<sub>2</sub>, ..., n<sub>k</sub>]</code><em> where </em><code>n = n<sub>1</sub><sup>x</sup> + n<sub>2</sub><sup>x</sup> + ... + n<sub>k</sub><sup>x</sup></code><em>.</em></p>

<p>Since the result can be very large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>For example, if <code>n = 160</code> and <code>x = 3</code>, one way to express <code>n</code> is <code>n = 2<sup>3</sup> + 3<sup>3</sup> + 5<sup>3</sup></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 10, x = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can express n as the following: n = 3<sup>2</sup> + 1<sup>2</sup> = 10.
It can be shown that it is the only way to express 10 as the sum of the 2<sup>nd</sup> power of unique integers.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4, x = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can express n in the following ways:
- n = 4<sup>1</sup> = 4.
- n = 3<sup>1</sup> + 1<sup>1</sup> = 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 300</code></li>
	<li><code>1 &lt;= x &lt;= 5</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def numberOfWays(self, n: int, x: int) -> int:
        mod = 10**9 + 7
        f = [[0] * (n + 1) for _ in range(n + 1)]
        f[0][0] = 1
        for i in range(1, n + 1):
            k = pow(i, x)
            for j in range(n + 1):
                f[i][j] = f[i - 1][j]
                if k <= j:
                    f[i][j] = (f[i][j] + f[i - 1][j - k]) % mod
        return f[n][n]
```

```java
class Solution {
    public int numberOfWays(int n, int x) {
        final int mod = (int) 1e9 + 7;
        int[][] f = new int[n + 1][n + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            long k = (long) Math.pow(i, x);
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j];
                if (k <= j) {
                    f[i][j] = (f[i][j] + f[i - 1][j - (int) k]) % mod;
                }
            }
        }
        return f[n][n];
    }
}
```

```cpp
class Solution {
public:
    int numberOfWays(int n, int x) {
        const int mod = 1e9 + 7;
        int f[n + 1][n + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            long long k = (long long) pow(i, x);
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j];
                if (k <= j) {
                    f[i][j] = (f[i][j] + f[i - 1][j - k]) % mod;
                }
            }
        }
        return f[n][n];
    }
};
```

```go
func numberOfWays(n int, x int) int {
	const mod int = 1e9 + 7
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	f[0][0] = 1
	for i := 1; i <= n; i++ {
		k := int(math.Pow(float64(i), float64(x)))
		for j := 0; j <= n; j++ {
			f[i][j] = f[i-1][j]
			if k <= j {
				f[i][j] = (f[i][j] + f[i-1][j-k]) % mod
			}
		}
	}
	return f[n][n]
}
```

```ts
function numberOfWays(n: number, x: number): number {
    const mod = 10 ** 9 + 7;
    const f: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
    f[0][0] = 1;
    for (let i = 1; i <= n; ++i) {
        const k = Math.pow(i, x);
        for (let j = 0; j <= n; ++j) {
            f[i][j] = f[i - 1][j];
            if (k <= j) {
                f[i][j] = (f[i][j] + f[i - 1][j - k]) % mod;
            }
        }
    }
    return f[n][n];
}
```

<!-- tabs:end -->

<!-- end -->
