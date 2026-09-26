---
comments: true
difficulty: 困难
tags:
    - 动态规划
---

<!-- problem:start -->

# [552. 学生出勤记录 II](https://leetcode.cn/problems/student-attendance-record-ii)

[English Version](/solution/0500-0599/0552.Student%20Attendance%20Record%20II/README_EN.md)

## 题目描述

<!-- description:start -->

可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
<ul>
	<li><code>'A'</code>：Absent，缺勤</li>
	<li><code>'L'</code>：Late，迟到</li>
	<li><code>'P'</code>：Present，到场</li>
</ul>

<p>如果学生能够 <strong>同时</strong> 满足下面两个条件，则可以获得出勤奖励：</p>

<ul>
	<li>按 <strong>总出勤</strong> 计，学生缺勤（<code>'A'</code>）<strong>严格</strong> 少于两天。</li>
	<li>学生 <strong>不会</strong> 存在 <strong>连续</strong> 3 天或 <strong>连续</strong> 3 天以上的迟到（<code>'L'</code>）记录。</li>
</ul>

<p>给你一个整数 <code>n</code> ，表示出勤记录的长度（次数）。请你返回记录长度为 <code>n</code> 时，可能获得出勤奖励的记录情况 <strong>数量</strong> 。答案可能很大，所以返回对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>8
<strong>解释：
</strong>有 8 种长度为 2 的记录将被视为可奖励：
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL" 
只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>3
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 10101
<strong>输出：</strong>183236316
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

<!-- thinking:start -->

> **思考**
>
> 长度为 $n$ 的合法串要限制缺勤次数与连续迟到。直接枚举 $3^n$ 种串不可行，$n$ 可达 $10^5$。
>
> 缺勤只用过 $0$ 次或 $1$ 次，连续迟到只有 $0,1,2$ 三档，六个状态就够了。若从第 $0$ 天递归到第 $n$ 天，调用栈深度为 $n$，在上限处会溢出。
>
> 因此从最后一天倒着填。$f(j,k)$ 表示已缺勤 $j$ 次、当前连续迟到 $k$ 次时，把剩余记录填完的方案数。没有剩余天数时方案数为 $1$。往前一天可以放 `P`（连续迟到清零）、在 $j=0$ 时放一次 `A`，或在 $k<2$ 时放 `L`。答案是 $f(0,0)$，对 $10^9+7$ 取模。

<!-- thinking:end -->

设 $f(j, k)$ 表示已经缺勤 $j$ 次、当前连续迟到 $k$ 次时，把尚未填写的出勤记录全部填完的方案数。没有剩余天数时，$f(j, k) = 1$。答案是倒推 $n$ 天之后的 $f(0, 0)$。

从最后一天往前推。对于每一个状态 $(j, k)$，新的方案数是三种选择之和：

- 到场：连续迟到清零，对应 $f(j, 0)$；
- 若 $j = 0$，可以缺勤一次，对应 $f(1, 0)$；
- 若 $k \lt 2$，可以迟到，对应 $f(j, k + 1)$。

每一天都用新数组接住结果，避免覆盖仍要读取的旧状态。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为出勤记录的长度。

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

### 方法二：动态规划

<!-- thinking:start -->

> **思考**
>
> 上一种做法只保留六个后缀方案数。这里把每一天的前缀方案都记下来，天数下标留在表里。
>
> $dp[i][j][k]$ 表示前 $i+1$ 天、缺勤 $j$ 次、连续迟到 $k$ 次的方案数。按放 `A` / `L` / `P` 从上一天转移。最后对最后一天的所有 $(j,k)$ 求和。

<!-- thinking:end -->

动态规划，定义 `dp[i][j][k]` 表示前 `i` 天，缺勤 `j` 次，连续迟到 `k` 次时，可获得出勤奖励的情况数量

状态转移需要对第 `i` 天的出勤情况分别讨论：

- 缺勤：之前不能有任何缺勤记录，即 `j == 0`
- 迟到：之前最多连续迟到 1 次，即 `k == 0 || k == 1`
- 到场：无限制

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
