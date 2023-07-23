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

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Go**

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

### **TypeScript**

```ts
function waysToChange(n: number): number {
    const mod = 10 ** 9 + 7;
    const coins: number[] = [25, 10, 5, 1];
    const f: number[][] = Array(5)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
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

### **...**

```

```

<!-- tabs:end -->
