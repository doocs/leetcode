---
comments: true
difficulty: Hard
rating: 2008
source: Weekly Contest 158 Q3
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [1223. Dice Roll Simulation](https://leetcode.com/problems/dice-roll-simulation)

[中文文档](/solution/1200-1299/1223.Dice%20Roll%20Simulation/README.md)

## Description

<!-- description:start -->

<p>A die simulator generates a random number from <code>1</code> to <code>6</code> for each roll. You introduced a constraint to the generator such that it cannot roll the number <code>i</code> more than <code>rollMax[i]</code> (<strong>1-indexed</strong>) consecutive times.</p>

<p>Given an array of integers <code>rollMax</code> and an integer <code>n</code>, return <em>the number of distinct sequences that can be obtained with exact </em><code>n</code><em> rolls</em>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>Two sequences are considered different if at least one element differs from each other.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2, rollMax = [1,1,2,2,2,3]
<strong>Output:</strong> 34
<strong>Explanation:</strong> There will be 2 rolls of die, if there are no constraints on the die, there are 6 * 6 = 36 possible combinations. In this case, looking at rollMax array, the numbers 1 and 2 appear at most once consecutively, therefore sequences (1,1) and (2,2) cannot occur, so the final answer is 36-2 = 34.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, rollMax = [1,1,1,1,1,1]
<strong>Output:</strong> 30
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3, rollMax = [1,1,1,2,2,3]
<strong>Output:</strong> 181
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>rollMax.length == 6</code></li>
	<li><code>1 &lt;= rollMax[i] &lt;= 15</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

<!-- thinking:start -->

> **Thinking**
>
> A face may repeat only $rollMax$ times in a row. $n$ reaches $5000$, so listing sequences is impossible. The count depends only on remaining rolls, the last face, and its current streak.
>
> Memoized $dfs(i,j,x)$ starts at roll $i$ after face $j$ with streak $x$. The next face $k$ resets the streak if $k\ne j$, or may continue if the cap allows. The start state $(0,0,0)$ means no roll yet.
>
> There are $O(n\times 6\times 15)$ states and $6$ transitions; memoization shares subproblems.

<!-- thinking:end -->

We can design a function $dfs(i, j, x)$ to represent the number of schemes starting from the $i$-th dice roll, with the current dice roll being $j$, and the number of consecutive times $j$ is rolled being $x$. The range of $j$ is $[1, 6]$, and the range of $x$ is $[1, rollMax[j - 1]]$. The answer is $dfs(0, 0, 0)$.

The calculation process of the function $dfs(i, j, x)$ is as follows:

- If $i \ge n$, it means that $n$ dice have been rolled, return $1$.
- Otherwise, we enumerate the number $k$ rolled next time. If $k \ne j$, we can directly roll $k$, and the number of consecutive times $j$ is rolled will be reset to $1$, so the number of schemes is $dfs(i + 1, k, 1)$. If $k = j$, we need to judge whether $x$ is less than $rollMax[j - 1]$. If it is less, we can continue to roll $j$, and the number of consecutive times $j$ is rolled will increase by $1$, so the number of schemes is $dfs(i + 1, j, x + 1)$. Finally, add all the scheme numbers to get the value of $dfs(i, j, x)$. Note that the answer may be very large, so we need to take the modulus of $10^9 + 7$.

During the process, we can use memoization search to avoid repeated calculations.

The time complexity is $O(n \times k^2 \times M)$, and the space complexity is $O(n \times k \times M)$. Here, $k$ is the range of dice points, and $M$ is the maximum number of times a certain point can be rolled consecutively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def dieSimulator(self, n: int, rollMax: List[int]) -> int:
        @cache
        def dfs(i, j, x):
            if i >= n:
                return 1
            ans = 0
            for k in range(1, 7):
                if k != j:
                    ans += dfs(i + 1, k, 1)
                elif x < rollMax[j - 1]:
                    ans += dfs(i + 1, j, x + 1)
            return ans % (10**9 + 7)

        return dfs(0, 0, 0)
```

#### Java

```java
class Solution {
    private Integer[][][] f;
    private int[] rollMax;

    public int dieSimulator(int n, int[] rollMax) {
        f = new Integer[n][7][16];
        this.rollMax = rollMax;
        return dfs(0, 0, 0);
    }

    private int dfs(int i, int j, int x) {
        if (i >= f.length) {
            return 1;
        }
        if (f[i][j][x] != null) {
            return f[i][j][x];
        }
        long ans = 0;
        for (int k = 1; k <= 6; ++k) {
            if (k != j) {
                ans += dfs(i + 1, k, 1);
            } else if (x < rollMax[j - 1]) {
                ans += dfs(i + 1, j, x + 1);
            }
        }
        ans %= 1000000007;
        return f[i][j][x] = (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int dieSimulator(int n, vector<int>& rollMax) {
        int f[n][7][16];
        memset(f, 0, sizeof f);
        const int mod = 1e9 + 7;
        function<int(int, int, int)> dfs = [&](int i, int j, int x) -> int {
            if (i >= n) {
                return 1;
            }
            if (f[i][j][x]) {
                return f[i][j][x];
            }
            long ans = 0;
            for (int k = 1; k <= 6; ++k) {
                if (k != j) {
                    ans += dfs(i + 1, k, 1);
                } else if (x < rollMax[j - 1]) {
                    ans += dfs(i + 1, j, x + 1);
                }
            }
            ans %= mod;
            return f[i][j][x] = ans;
        };
        return dfs(0, 0, 0);
    }
};
```

#### Go

```go
func dieSimulator(n int, rollMax []int) int {
	f := make([][7][16]int, n)
	const mod = 1e9 + 7
	var dfs func(i, j, x int) int
	dfs = func(i, j, x int) int {
		if i >= n {
			return 1
		}
		if f[i][j][x] != 0 {
			return f[i][j][x]
		}
		ans := 0
		for k := 1; k <= 6; k++ {
			if k != j {
				ans += dfs(i+1, k, 1)
			} else if x < rollMax[j-1] {
				ans += dfs(i+1, j, x+1)
			}
		}
		f[i][j][x] = ans % mod
		return f[i][j][x]
	}
	return dfs(0, 0, 0)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> Solution 1 already fills the suffix counts from the last roll. The same choices can be stored as a prefix: $f[i][j][x]$ is the number of sequences of $i$ rolls ending with face $j$ and streak $x$, accumulated from layer $i-1$.

<!-- thinking:end -->

Solution 1 already fills this recurrence from the last roll. The counts below are the same choices written for the first $i$ rolls.

Define $f[i][j][x]$ as the number of schemes for the first $i$ dice rolls, with the $i$-th dice roll being $j$, and the number of consecutive times $j$ is rolled being $x$. Initially, $f[1][j][1] = 1$, where $1 \leq j \leq 6$. The answer is:

$$
\sum_{j=1}^6 \sum_{x=1}^{rollMax[j-1]} f[n][j][x]
$$

We enumerate the last dice roll as $j$, and the number of consecutive times $j$ is rolled as $x$. The current dice roll can be $1, 2, \cdots, 6$. If the current dice roll is $k$, there are two cases:

- If $k \neq j$, we can directly roll $k$, and the number of consecutive times $j$ is rolled will be reset to $1$. Therefore, the number of schemes $f[i][k][1]$ will increase by $f[i-1][j][x]$.
- If $k = j$, we need to judge whether $x+1$ is less than or equal to $rollMax[j-1]$. If it is less than or equal to, we can continue to roll $j$, and the number of consecutive times $j$ is rolled will increase by $1$. Therefore, the number of schemes $f[i][j][x+1]$ will increase by $f[i-1][j][x]$.

The final answer is the sum of all $f[n][j][x]$.

The time complexity is $O(n \times k^2 \times M)$, and the space complexity is $O(n \times k \times M)$. Here, $k$ is the range of dice points, and $M$ is the maximum number of times a certain point can be rolled consecutively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def dieSimulator(self, n: int, rollMax: List[int]) -> int:
        f = [[[0] * 16 for _ in range(7)] for _ in range(n + 1)]
        for j in range(1, 7):
            f[1][j][1] = 1
        for i in range(2, n + 1):
            for j in range(1, 7):
                for x in range(1, rollMax[j - 1] + 1):
                    for k in range(1, 7):
                        if k != j:
                            f[i][k][1] += f[i - 1][j][x]
                        elif x + 1 <= rollMax[j - 1]:
                            f[i][j][x + 1] += f[i - 1][j][x]
        mod = 10**9 + 7
        ans = 0
        for j in range(1, 7):
            for x in range(1, rollMax[j - 1] + 1):
                ans = (ans + f[n][j][x]) % mod
        return ans
```

#### Java

```java
class Solution {
    public int dieSimulator(int n, int[] rollMax) {
        int[][][] f = new int[n + 1][7][16];
        for (int j = 1; j <= 6; ++j) {
            f[1][j][1] = 1;
        }
        final int mod = (int) 1e9 + 7;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= 6; ++j) {
                for (int x = 1; x <= rollMax[j - 1]; ++x) {
                    for (int k = 1; k <= 6; ++k) {
                        if (k != j) {
                            f[i][k][1] = (f[i][k][1] + f[i - 1][j][x]) % mod;
                        } else if (x + 1 <= rollMax[j - 1]) {
                            f[i][j][x + 1] = (f[i][j][x + 1] + f[i - 1][j][x]) % mod;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int j = 1; j <= 6; ++j) {
            for (int x = 1; x <= rollMax[j - 1]; ++x) {
                ans = (ans + f[n][j][x]) % mod;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int dieSimulator(int n, vector<int>& rollMax) {
        int f[n + 1][7][16];
        memset(f, 0, sizeof f);
        for (int j = 1; j <= 6; ++j) {
            f[1][j][1] = 1;
        }
        const int mod = 1e9 + 7;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= 6; ++j) {
                for (int x = 1; x <= rollMax[j - 1]; ++x) {
                    for (int k = 1; k <= 6; ++k) {
                        if (k != j) {
                            f[i][k][1] = (f[i][k][1] + f[i - 1][j][x]) % mod;
                        } else if (x + 1 <= rollMax[j - 1]) {
                            f[i][j][x + 1] = (f[i][j][x + 1] + f[i - 1][j][x]) % mod;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int j = 1; j <= 6; ++j) {
            for (int x = 1; x <= rollMax[j - 1]; ++x) {
                ans = (ans + f[n][j][x]) % mod;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func dieSimulator(n int, rollMax []int) (ans int) {
	f := make([][7][16]int, n+1)
	for j := 1; j <= 6; j++ {
		f[1][j][1] = 1
	}
	const mod = 1e9 + 7
	for i := 2; i <= n; i++ {
		for j := 1; j <= 6; j++ {
			for x := 1; x <= rollMax[j-1]; x++ {
				for k := 1; k <= 6; k++ {
					if k != j {
						f[i][k][1] = (f[i][k][1] + f[i-1][j][x]) % mod
					} else if x+1 <= rollMax[j-1] {
						f[i][j][x+1] = (f[i][j][x+1] + f[i-1][j][x]) % mod
					}
				}
			}
		}
	}
	for j := 1; j <= 6; j++ {
		for x := 1; x <= rollMax[j-1]; x++ {
			ans = (ans + f[n][j][x]) % mod
		}
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3: Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> A face may repeat only $rollMax$ times in a row. $n$ reaches $5000$, so listing sequences is impossible. The count depends only on the rolls still left, the previous face, and its current streak.
>
> Whether the next face changes or continues, it reads the next roll. The chain from roll $0$ to roll $n$ has length $n$. Python raises RecursionError at $n=2000$, and Java overflows at $n=5000$.
>
> Fill from the last roll. $f[i][j][x]$ is the number of ways to finish from roll $i$ when the previous face is $j$ with streak $x$, and $f[n][\cdot][\cdot]=1$. A different face moves to $(k,1)$. The same face is allowed only while $x$ is below its limit, and then moves to $(j,x+1)$.

<!-- thinking:end -->

Let $f[i][j][x]$ be the number of ways to finish the remaining rolls starting at roll $i$, when the previous face is $j$ and that face has already appeared $x$ times in a row. $j = 0$ means no roll has been made yet. The answer is $f[0][0][0]$.

Set $f[n][j][x] = 1$, which means all $n$ rolls are done. Fill $i$ from $n - 1$ down to $0$. Enumerate the next face $k$:

- If $k \ne j$, face $k$ may be rolled and the streak resets to $1$, contributing $f[i + 1][k][1]$.
- If $k = j$ and $x < rollMax[j - 1]$, face $j$ may continue and the streak becomes $x + 1$, contributing $f[i + 1][j][x + 1]$.

Add these contributions and reduce modulo $10^9 + 7$ to obtain $f[i][j][x]$.

The time complexity is $O(n \times k^2 \times M)$, and the space complexity is $O(n \times k \times M)$. Here $k$ is the number of faces, and $M$ is the maximum allowed streak.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def dieSimulator(self, n: int, rollMax: List[int]) -> int:
        mod = 10**9 + 7
        f = [[[0] * 16 for _ in range(7)] for _ in range(n + 1)]
        for j in range(7):
            for x in range(16):
                f[n][j][x] = 1
        for i in range(n - 1, -1, -1):
            for j in range(7):
                for x in range(16):
                    ans = 0
                    for k in range(1, 7):
                        if k != j:
                            ans += f[i + 1][k][1]
                        elif x < rollMax[j - 1]:
                            ans += f[i + 1][j][x + 1]
                    f[i][j][x] = ans % mod
        return f[0][0][0]
```

#### Java

```java
class Solution {
    public int dieSimulator(int n, int[] rollMax) {
        final int mod = 1000000007;
        int[][][] f = new int[n + 1][7][16];
        for (int j = 0; j < 7; ++j) {
            for (int x = 0; x < 16; ++x) {
                f[n][j][x] = 1;
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < 7; ++j) {
                for (int x = 0; x < 16; ++x) {
                    long ans = 0;
                    for (int k = 1; k <= 6; ++k) {
                        if (k != j) {
                            ans += f[i + 1][k][1];
                        } else if (x < rollMax[j - 1]) {
                            ans += f[i + 1][j][x + 1];
                        }
                    }
                    f[i][j][x] = (int) (ans % mod);
                }
            }
        }
        return f[0][0][0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int dieSimulator(int n, vector<int>& rollMax) {
        const int mod = 1e9 + 7;
        vector<vector<vector<int>>> f(n + 1, vector<vector<int>>(7, vector<int>(16)));
        for (int j = 0; j < 7; ++j) {
            for (int x = 0; x < 16; ++x) {
                f[n][j][x] = 1;
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < 7; ++j) {
                for (int x = 0; x < 16; ++x) {
                    long ans = 0;
                    for (int k = 1; k <= 6; ++k) {
                        if (k != j) {
                            ans += f[i + 1][k][1];
                        } else if (x < rollMax[j - 1]) {
                            ans += f[i + 1][j][x + 1];
                        }
                    }
                    f[i][j][x] = ans % mod;
                }
            }
        }
        return f[0][0][0];
    }
};
```

#### Go

```go
func dieSimulator(n int, rollMax []int) int {
	const mod = 1e9 + 7
	f := make([][7][16]int, n+1)
	for j := 0; j < 7; j++ {
		for x := 0; x < 16; x++ {
			f[n][j][x] = 1
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := 0; j < 7; j++ {
			for x := 0; x < 16; x++ {
				ans := 0
				for k := 1; k <= 6; k++ {
					if k != j {
						ans += f[i+1][k][1]
					} else if x < rollMax[j-1] {
						ans += f[i+1][j][x+1]
					}
				}
				f[i][j][x] = ans % mod
			}
		}
	}
	return f[0][0][0]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
