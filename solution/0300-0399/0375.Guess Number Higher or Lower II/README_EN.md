---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0375.Guess%20Number%20Higher%20or%20Lower%20II/README_EN.md
tags:
    - Math
    - Dynamic Programming
    - Game Theory
---

<!-- problem:start -->

# [375. Guess Number Higher or Lower II](https://leetcode.com/problems/guess-number-higher-or-lower-ii)

[中文文档](/solution/0300-0399/0375.Guess%20Number%20Higher%20or%20Lower%20II/README.md)

## Description

<!-- description:start -->

<p>We are playing the Guessing Game. The game will work as follows:</p>

<ol>
	<li>I pick a number between&nbsp;<code>1</code>&nbsp;and&nbsp;<code>n</code>.</li>
	<li>You guess a number.</li>
	<li>If you guess the right number, <strong>you win the game</strong>.</li>
	<li>If you guess the wrong number, then I will tell you whether the number I picked is <strong>higher or lower</strong>, and you will continue guessing.</li>
	<li>Every time you guess a wrong number&nbsp;<code>x</code>, you will pay&nbsp;<code>x</code>&nbsp;dollars. If you run out of money, <strong>you lose the game</strong>.</li>
</ol>

<p>Given a particular&nbsp;<code>n</code>, return&nbsp;<em>the minimum amount of money you need to&nbsp;<strong>guarantee a win regardless of what number I pick</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0375.Guess%20Number%20Higher%20or%20Lower%20II/images/graph.png" style="width: 505px; height: 388px;" />
<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 16
<strong>Explanation:</strong> The winning strategy is as follows:
- The range is [1,10]. Guess 7.
&nbsp;   - If this is my number, your total is $0. Otherwise, you pay $7.
&nbsp;   - If my number is higher, the range is [8,10]. Guess 9.
&nbsp;       - If this is my number, your total is $7. Otherwise, you pay $9.
&nbsp;       - If my number is higher, it must be 10. Guess 10. Your total is $7 + $9 = $16.
&nbsp;       - If my number is lower, it must be 8. Guess 8. Your total is $7 + $9 = $16.
&nbsp;   - If my number is lower, the range is [1,6]. Guess 3.
&nbsp;       - If this is my number, your total is $7. Otherwise, you pay $3.
&nbsp;       - If my number is higher, the range is [4,6]. Guess 5.
&nbsp;           - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $5.
&nbsp;           - If my number is higher, it must be 6. Guess 6. Your total is $7 + $3 + $5 = $15.
&nbsp;           - If my number is lower, it must be 4. Guess 4. Your total is $7 + $3 + $5 = $15.
&nbsp;       - If my number is lower, the range is [1,2]. Guess 1.
&nbsp;           - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $1.
&nbsp;           - If my number is higher, it must be 2. Guess 2. Your total is $7 + $3 + $1 = $11.
The worst case in all these scenarios is that you pay $16. Hence, you only need $16 to guarantee a win.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong>&nbsp;There is only one possible number, so you can guess 1 and not have to pay anything.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong>&nbsp;There are two possible numbers, 1 and 2.
- Guess 1.
&nbsp;   - If this is my number, your total is $0. Otherwise, you pay $1.
&nbsp;   - If my number is higher, it must be 2. Guess 2. Your total is $1.
The worst case is that you pay $1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 200</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j]$ as the minimum cost required to guess any number in the interval $[i, j]$. Initially, $f[i][i] = 0$ because there is no cost to guess the only number, and for $i > j$, we also have $f[i][j] = 0$. The answer is $f[1][n]$.

For $f[i][j]$, we can enumerate any number $k$ in $[i, j]$, divide the interval $[i, j]$ into two parts, $[i, k - 1]$ and $[k + 1, j]$, choose the larger value of the two parts plus the cost of $k$,

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getMoneyAmount(self, n: int) -> int:
        f = [[0] * (n + 1) for _ in range(n + 1)]
        for i in range(n - 1, 0, -1):
            for j in range(i + 1, n + 1):
                f[i][j] = j + f[i][j - 1]
                for k in range(i, j):
                    f[i][j] = min(f[i][j], max(f[i][k - 1], f[k + 1][j]) + k)
        return f[1][n]
```

#### Java

```java
class Solution {
    public int getMoneyAmount(int n) {
        int[][] f = new int[n + 1][n + 1];
        for (int i = n - 1; i > 0; --i) {
            for (int j = i + 1; j <= n; ++j) {
                f[i][j] = j + f[i][j - 1];
                for (int k = i; k < j; ++k) {
                    f[i][j] = Math.min(f[i][j], Math.max(f[i][k - 1], f[k + 1][j]) + k);
                }
            }
        }
        return f[1][n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int getMoneyAmount(int n) {
        int f[n + 1][n + 1];
        memset(f, 0, sizeof(f));
        for (int i = n - 1; i; --i) {
            for (int j = i + 1; j <= n; ++j) {
                f[i][j] = j + f[i][j - 1];
                for (int k = i; k < j; ++k) {
                    f[i][j] = min(f[i][j], max(f[i][k - 1], f[k + 1][j]) + k);
                }
            }
        }
        return f[1][n];
    }
};
```

#### Go

```go
func getMoneyAmount(n int) int {
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for i := n - 1; i > 0; i-- {
		for j := i + 1; j <= n; j++ {
			f[i][j] = j + f[i][j-1]
			for k := i; k < j; k++ {
				f[i][j] = min(f[i][j], k+max(f[i][k-1], f[k+1][j]))
			}
		}
	}
	return f[1][n]
}
```

#### TypeScript

```ts
function getMoneyAmount(n: number): number {
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(n + 1).fill(0));
    for (let i = n - 1; i; --i) {
        for (let j = i + 1; j <= n; ++j) {
            f[i][j] = j + f[i][j - 1];
            for (let k = i; k < j; ++k) {
                f[i][j] = Math.min(f[i][j], k + Math.max(f[i][k - 1], f[k + 1][j]));
            }
        }
    }
    return f[1][n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
