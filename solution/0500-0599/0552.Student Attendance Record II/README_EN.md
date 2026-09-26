---
comments: true
difficulty: Hard
tags:
    - Dynamic Programming
---

<!-- problem:start -->

# [552. Student Attendance Record II](https://leetcode.com/problems/student-attendance-record-ii)

[中文文档](/solution/0500-0599/0552.Student%20Attendance%20Record%20II/README.md)

## Description

<!-- description:start -->

<p>An attendance record for a student can be represented as a string where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:</p>

<ul>
	<li><code>&#39;A&#39;</code>: Absent.</li>
	<li><code>&#39;L&#39;</code>: Late.</li>
	<li><code>&#39;P&#39;</code>: Present.</li>
</ul>

<p>Any student is eligible for an attendance award if they meet <strong>both</strong> of the following criteria:</p>

<ul>
	<li>The student was absent (<code>&#39;A&#39;</code>) for <strong>strictly</strong> fewer than 2 days <strong>total</strong>.</li>
	<li>The student was <strong>never</strong> late (<code>&#39;L&#39;</code>) for 3 or more <strong>consecutive</strong> days.</li>
</ul>

<p>Given an integer <code>n</code>, return <em>the <strong>number</strong> of possible attendance records of length</em> <code>n</code><em> that make a student eligible for an attendance award. The answer may be very large, so return it <strong>modulo</strong> </em><code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 8
<strong>Explanation:</strong> There are 8 records with length 2 that are eligible for an award:
&quot;PP&quot;, &quot;AP&quot;, &quot;PA&quot;, &quot;LP&quot;, &quot;PL&quot;, &quot;AL&quot;, &quot;LA&quot;, &quot;LL&quot;
Only &quot;AA&quot; is not eligible because there are 2 absences (there need to be fewer than 2).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 3
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 10101
<strong>Output:</strong> 183236316
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> A valid length-$n$ record limits absences and consecutive lates. Enumerating $3^n$ strings is impossible for $n$ up to $10^5$.
>
> Six numbers are enough: absences used ($0$ or $1$) and the current late streak ($0$, $1$, or $2$). A top-down search still chains $n$ calls and overflows the stack on the largest $n$.
>
> So we walk the days backward. $f(j,k)$ is the number of ways to finish the remaining days with $j$ absences already used and late streak $k$. After the last day that value is $1$. One day earlier, place `P` (streak returns to $0$), an `A` when $j=0$, or an `L` when $k<2$. The answer is $f(0,0)$ modulo $10^9+7$.

<!-- thinking:end -->

Let $f(j,k)$ be the number of ways to fill every remaining day when $j$ absences are already used and the current late streak is $k$. With no days left, $f(j,k)=1$. The answer is $f(0,0)$ after $n$ backward steps.

Each step replaces the table by the three choices:

- present, which resets the streak: $f(j,0)$;
- absent, only when $j=0$: $f(1,0)$;
- late, only when $k<2$: $f(j,k+1)$.

Write the new values into a fresh table so the previous day is not overwritten. Take every sum modulo $10^9+7$.

The time complexity is $O(n)$ and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkRecord(self, n: int) -> int:
        mod = 10**9 + 7
        f = [[1] * 3 for _ in range(2)]
        for _ in range(n):
            g = [[0] * 3 for _ in range(2)]
            for j in range(2):
                for k in range(3):
                    ans = f[j][0]
                    if j == 0:
                        ans += f[1][0]
                    if k < 2:
                        ans += f[j][k + 1]
                    g[j][k] = ans % mod
            f = g
        return f[0][0]
```

#### Java

```java
class Solution {
    public int checkRecord(int n) {
        final int mod = (int) 1e9 + 7;
        int[][] f = {{1, 1, 1}, {1, 1, 1}};
        for (int i = 0; i < n; ++i) {
            int[][] g = new int[2][3];
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < 3; ++k) {
                    int ans = f[j][0];
                    if (j == 0) {
                        ans = (ans + f[1][0]) % mod;
                    }
                    if (k < 2) {
                        ans = (ans + f[j][k + 1]) % mod;
                    }
                    g[j][k] = ans % mod;
                }
            }
            f = g;
        }
        return f[0][0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int checkRecord(int n) {
        const int mod = 1e9 + 7;
        int f[2][3] = {{1, 1, 1}, {1, 1, 1}};
        for (int i = 0; i < n; ++i) {
            int g[2][3]{};
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < 3; ++k) {
                    int ans = f[j][0];
                    if (j == 0) {
                        ans = (ans + f[1][0]) % mod;
                    }
                    if (k < 2) {
                        ans = (ans + f[j][k + 1]) % mod;
                    }
                    g[j][k] = ans % mod;
                }
            }
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < 3; ++k) {
                    f[j][k] = g[j][k];
                }
            }
        }
        return f[0][0];
    }
};
```

#### Go

```go
func checkRecord(n int) int {
	const mod = int(1e9 + 7)
	f := [2][3]int{{1, 1, 1}, {1, 1, 1}}
	for i := 0; i < n; i++ {
		var g [2][3]int
		for j := 0; j < 2; j++ {
			for k := 0; k < 3; k++ {
				ans := f[j][0]
				if j == 0 {
					ans = (ans + f[1][0]) % mod
				}
				if k < 2 {
					ans = (ans + f[j][k+1]) % mod
				}
				g[j][k] = ans % mod
			}
		}
		f = g
	}
	return f[0][0]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- thinking:start -->

> **Thinking**
>
> The previous method keeps only the six suffix counts. This one records every prefix so the day index stays explicit.
>
> $dp[i][j][k]$ is the number of ways for the first $i+1$ days with $j$ absences and a late streak of $k$. Transitions place `A`, `L`, or `P` from day $i-1$. Sum every $(j,k)$ on the last day.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkRecord(self, n: int) -> int:
        mod = int(1e9 + 7)
        dp = [[[0, 0, 0], [0, 0, 0]] for _ in range(n)]

        # base case
        dp[0][0][0] = dp[0][0][1] = dp[0][1][0] = 1

        for i in range(1, n):
            # A
            dp[i][1][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % mod
            # L
            dp[i][0][1] = dp[i - 1][0][0]
            dp[i][0][2] = dp[i - 1][0][1]
            dp[i][1][1] = dp[i - 1][1][0]
            dp[i][1][2] = dp[i - 1][1][1]
            # P
            dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % mod
            dp[i][1][0] = (
                dp[i][1][0] + dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2]
            ) % mod

        ans = 0
        for j in range(2):
            for k in range(3):
                ans = (ans + dp[n - 1][j][k]) % mod
        return ans
```

#### Java

```java
class Solution {
    private static final int MOD = 1000000007;

    public int checkRecord(int n) {
        long[][][] dp = new long[n][2][3];

        // base case
        dp[0][0][0] = 1;
        dp[0][0][1] = 1;
        dp[0][1][0] = 1;

        for (int i = 1; i < n; i++) {
            // A
            dp[i][1][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % MOD;
            // L
            dp[i][0][1] = dp[i - 1][0][0];
            dp[i][0][2] = dp[i - 1][0][1];
            dp[i][1][1] = dp[i - 1][1][0];
            dp[i][1][2] = dp[i - 1][1][1];
            // P
            dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % MOD;
            dp[i][1][0] = (dp[i][1][0] + dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2]) % MOD;
        }

        long ans = 0;
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 3; k++) {
                ans = (ans + dp[n - 1][j][k]) % MOD;
            }
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
constexpr int MOD = 1e9 + 7;

class Solution {
public:
    int checkRecord(int n) {
        using ll = long long;
        vector<vector<vector<ll>>> dp(n, vector<vector<ll>>(2, vector<ll>(3)));

        // base case
        dp[0][0][0] = dp[0][0][1] = dp[0][1][0] = 1;

        for (int i = 1; i < n; ++i) {
            // A
            dp[i][1][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % MOD;
            // L
            dp[i][0][1] = dp[i - 1][0][0];
            dp[i][0][2] = dp[i - 1][0][1];
            dp[i][1][1] = dp[i - 1][1][0];
            dp[i][1][2] = dp[i - 1][1][1];
            // P
            dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % MOD;
            dp[i][1][0] = (dp[i][1][0] + dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2]) % MOD;
        }

        ll ans = 0;
        for (int j = 0; j < 2; ++j) {
            for (int k = 0; k < 3; ++k) {
                ans = (ans + dp[n - 1][j][k]) % MOD;
            }
        }
        return ans;
    }
};
```

#### Go

```go
const _mod int = 1e9 + 7

func checkRecord(n int) int {
	dp := make([][][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([][]int, 2)
		for j := 0; j < 2; j++ {
			dp[i][j] = make([]int, 3)
		}
	}

	// base case
	dp[0][0][0] = 1
	dp[0][0][1] = 1
	dp[0][1][0] = 1

	for i := 1; i < n; i++ {
		// A
		dp[i][1][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2]) % _mod
		// L
		dp[i][0][1] = dp[i-1][0][0]
		dp[i][0][2] = dp[i-1][0][1]
		dp[i][1][1] = dp[i-1][1][0]
		dp[i][1][2] = dp[i-1][1][1]
		// P
		dp[i][0][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2]) % _mod
		dp[i][1][0] = (dp[i][1][0] + dp[i-1][1][0] + dp[i-1][1][1] + dp[i-1][1][2]) % _mod
	}

	var ans int
	for j := 0; j < 2; j++ {
		for k := 0; k < 3; k++ {
			ans = (ans + dp[n-1][j][k]) % _mod
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
