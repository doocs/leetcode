---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.11.Coin/README_EN.md
---

# [08.11. Coin](https://leetcode.cn/problems/coin-lcci)

[中文文档](/lcci/08.11.Coin/README.md)

## Description

<p>Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and pennies (1 cent), write code to calculate the number of ways of representing n cents.&nbsp;(The result may be large, so you should return it modulo 1000000007)</p>
<p><strong>Example1:</strong></p>
<pre>

<strong> Input</strong>: n = 5

<strong> Output</strong>: 2

<strong> Explanation</strong>: There are two ways:

5=5

5=1+1+1+1+1

</pre>
<p><strong>Example2:</strong></p>
<pre>

<strong> Input</strong>: n = 10

<strong> Output</strong>: 4

<strong> Explanation</strong>: There are four ways:

10=10

10=5+5

10=5+1+1+1+1+1

10=1+1+1+1+1+1+1+1+1+1

</pre>
<p><strong>Notes: </strong></p>
<p>You can assume:</p>
<ul>
	<li>0 &lt;= n&nbsp;&lt;= 1000000</li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

We define $f[i][j]$ as the number of ways to make up the total amount $j$ using only the first $i$ types of coins. Initially, $f[0][0]=1$, and the rest of the elements are $0$. The answer is $f[4][n]$.

Considering $f[i][j]$, we can enumerate the number of the $i$-th type of coin used, $k$, where $0 \leq k \leq j / c_i$, then $f[i][j]$ is equal to the sum of all $f[i−1][j−k \times c_i]$. Since the number of coins is infinite, $k$ can start from $0$. That is, the state transition equation is as follows:

$$
f[i][j] = f[i - 1][j] + f[i - 1][j - c_i] + \cdots + f[i - 1][j - k \times c_i]
$$

Let $j = j - c_i$, then the above state transition equation can be written as:

$$
f[i][j - c_i] = f[i - 1][j - c_i] + f[i - 1][j - 2 \times c_i] + \cdots + f[i - 1][j - k \times c_i]
$$

Substitute the second equation into the first equation to get:

$$
f[i][j]=
\begin{cases}
f[i - 1][j] + f[i][j - c_i], & j \geq c_i \\
f[i - 1][j], & j < c_i
\end{cases}
$$

The final answer is $f[4][n]$.

The time complexity is $O(C \times n)$, and the space complexity is $O(C \times n)$, where $C$ is the number of types of coins.

<!-- tabs:start -->

```python
class Solution:
    def waysToChange(self, n: int) -> int:
        mod = 10**9 + 7
        coins = [25, 10, 5, 1]
        f = [[0] * (n + 1) for _ in range(5)]
        f[0][0] = 1
        for i, c in enumerate(coins, 1):
            for j in range(n + 1):
                f[i][j] = f[i - 1][j]
                if j >= c:
                    f[i][j] = (f[i][j] + f[i][j - c]) % mod
        return f[-1][n]
```

```java
class Solution {
    public int waysToChange(int n) {
        final int mod = (int) 1e9 + 7;
        int[] coins = {25, 10, 5, 1};
        int[][] f = new int[5][n + 1];
        f[0][0] = 1;
        for (int i = 1; i <= 4; ++i) {
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= coins[i - 1]) {
                    f[i][j] = (f[i][j] + f[i][j - coins[i - 1]]) % mod;
                }
            }
        }
        return f[4][n];
    }
}
```

```cpp
class Solution {
public:
    int waysToChange(int n) {
        const int mod = 1e9 + 7;
        vector<int> coins = {25, 10, 5, 1};
        int f[5][n + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= 4; ++i) {
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= coins[i - 1]) {
                    f[i][j] = (f[i][j] + f[i][j - coins[i - 1]]) % mod;
                }
            }
        }
        return f[4][n];
    }
};
```

```go
func waysToChange(n int) int {
	const mod int = 1e9 + 7
	coins := []int{25, 10, 5, 1}
	f := make([][]int, 5)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	f[0][0] = 1
	for i := 1; i <= 4; i++ {
		for j := 0; j <= n; j++ {
			f[i][j] = f[i-1][j]
			if j >= coins[i-1] {
				f[i][j] = (f[i][j] + f[i][j-coins[i-1]]) % mod
			}
		}
	}
	return f[4][n]
}
```

```ts
function waysToChange(n: number): number {
    const mod = 10 ** 9 + 7;
    const coins: number[] = [25, 10, 5, 1];
    const f: number[][] = Array.from({ length: 5 }, () => Array(n + 1).fill(0));
    f[0][0] = 1;
    for (let i = 1; i <= 4; ++i) {
        for (let j = 0; j <= n; ++j) {
            f[i][j] = f[i - 1][j];
            if (j >= coins[i - 1]) {
                f[i][j] = (f[i][j] + f[i][j - coins[i - 1]]) % mod;
            }
        }
    }
    return f[4][n];
}
```

```swift
class Solution {
    func waysToChange(_ n: Int) -> Int {
        let mod = Int(1e9 + 7)
        let coins = [25, 10, 5, 1]
        var f = Array(repeating: Array(repeating: 0, count: n + 1), count: 5)
        f[0][0] = 1

        for i in 1...4 {
            for j in 0...n {
                f[i][j] = f[i - 1][j]
                if j >= coins[i - 1] {
                    f[i][j] = (f[i][j] + f[i][j - coins[i - 1]]) % mod
                }
            }
        }
        return f[4][n]
    }
}
```

<!-- tabs:end -->

### Solution 2: Dynamic Programming (Space Optimization)

We notice that the calculation of $f[i][j]$ is only related to $f[i−1][..]$. Therefore, we can remove the first dimension and optimize the space complexity to $O(n)$.

<!-- tabs:start -->

```python
class Solution:
    def waysToChange(self, n: int) -> int:
        mod = 10**9 + 7
        coins = [25, 10, 5, 1]
        f = [1] + [0] * n
        for c in coins:
            for j in range(c, n + 1):
                f[j] = (f[j] + f[j - c]) % mod
        return f[n]
```

```java
class Solution {
    public int waysToChange(int n) {
        final int mod = (int) 1e9 + 7;
        int[] coins = {25, 10, 5, 1};
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int c : coins) {
            for (int j = c; j <= n; ++j) {
                f[j] = (f[j] + f[j - c]) % mod;
            }
        }
        return f[n];
    }
}
```

```cpp
class Solution {
public:
    int waysToChange(int n) {
        const int mod = 1e9 + 7;
        vector<int> coins = {25, 10, 5, 1};
        int f[n + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (int c : coins) {
            for (int j = c; j <= n; ++j) {
                f[j] = (f[j] + f[j - c]) % mod;
            }
        }
        return f[n];
    }
};
```

```go
func waysToChange(n int) int {
	const mod int = 1e9 + 7
	coins := []int{25, 10, 5, 1}
	f := make([]int, n+1)
	f[0] = 1
	for _, c := range coins {
		for j := c; j <= n; j++ {
			f[j] = (f[j] + f[j-c]) % mod
		}
	}
	return f[n]
}
```

```ts
function waysToChange(n: number): number {
    const mod = 10 ** 9 + 7;
    const coins: number[] = [25, 10, 5, 1];
    const f: number[] = new Array(n + 1).fill(0);
    f[0] = 1;
    for (const c of coins) {
        for (let i = c; i <= n; ++i) {
            f[i] = (f[i] + f[i - c]) % mod;
        }
    }
    return f[n];
}
```

<!-- tabs:end -->

<!-- end -->
