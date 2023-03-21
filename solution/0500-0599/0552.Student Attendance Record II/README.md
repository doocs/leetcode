# [552. 学生出勤记录 II](https://leetcode.cn/problems/student-attendance-record-ii)

[English Version](/solution/0500-0599/0552.Student%20Attendance%20Record%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

我们设计一个函数 $dfs(i, j, k)$，表示从第 $i$ 个出勤记录开始，当前缺勤次数为 $j$，目前最后连续迟到次数为 $k$ 时，可获得出勤奖励的情况数量。那么答案就是 $dfs(0, 0, 0)$。

函数 $dfs(i, j, k)$ 的执行过程如下：

-   如果 $i \ge n$，说明已经遍历完所有出勤记录，返回 $1$；
-   如果 $j = 0$，说明当前缺勤次数为 $0$，那么可以选择缺勤，即 $dfs(i + 1, j + 1, 0)$；
-   如果 $k \lt 2$，说明当前连续迟到次数小于 $2$，那么可以选择迟到，即 $dfs(i + 1, j, k + 1)$；
-   无论如何，都可以选择到场，即 $dfs(i + 1, j, 0)$。

我们将上述三种情况的结果相加，即为 $dfs(i, j, k)$ 的结果。

为了避免重复计算，我们可以使用记忆化搜索。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为出勤记录的长度。

**方法二：动态规划**

动态规划，定义 `dp[i][j][k]` 表示前 `i` 天，缺勤 `j` 次，连续迟到 `k` 次时，可获得出勤奖励的情况数量

状态转移需要对第 `i` 天的出勤情况分别讨论：

-   缺勤：之前不能有任何缺勤记录，即 `j == 0`
-   迟到：之前最多连续迟到 1 次，即 `k == 0 || k == 1`
-   到场：无限制

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkRecord(self, n: int) -> int:
        @cache
        def dfs(i, j, k):
            if i >= n:
                return 1
            ans = 0
            if j == 0:
                ans += dfs(i + 1, j + 1, 0)
            if k < 2:
                ans += dfs(i + 1, j, k + 1)
            ans += dfs(i + 1, j, 0)
            return ans % mod

        mod = 10**9 + 7
        ans = dfs(0, 0, 0)
        dfs.cache_clear()
        return ans
```

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private final int mod = (int) 1e9 + 7;
    private int n;
    private Integer[][][] f;

    public int checkRecord(int n) {
        this.n = n;
        f = new Integer[n][2][3];
        return dfs(0, 0, 0);
    }

    private int dfs(int i, int j, int k) {
        if (i >= n) {
            return 1;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int ans = dfs(i + 1, j, 0);
        if (j == 0) {
            ans = (ans + dfs(i + 1, j + 1, 0)) % mod;
        }
        if (k < 2) {
            ans = (ans + dfs(i + 1, j, k + 1)) % mod;
        }
        return f[i][j][k] = ans;
    }
}
```

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

### **Go**

```go
func checkRecord(n int) int {
	f := make([][][]int, n)
	for i := range f {
		f[i] = make([][]int, 2)
		for j := range f[i] {
			f[i][j] = make([]int, 3)
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	const mod = 1e9 + 7
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i >= n {
			return 1
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		ans := dfs(i+1, j, 0)
		if j == 0 {
			ans = (ans + dfs(i+1, j+1, 0)) % mod
		}
		if k < 2 {
			ans = (ans + dfs(i+1, j, k+1)) % mod
		}
		f[i][j][k] = ans
		return ans
	}
	return dfs(0, 0, 0)
}
```

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

### **C++**

```cpp
int f[100010][2][3];
const int mod = 1e9 + 7;

class Solution {
public:
    int checkRecord(int n) {
        this->n = n;
        memset(f, -1, sizeof(f));
        return dfs(0, 0, 0);
    }

    int dfs(int i, int j, int k) {
        if (i >= n) {
            return 1;
        }
        if (f[i][j][k] != -1) {
            return f[i][j][k];
        }
        int ans = dfs(i + 1, j, 0);
        if (j == 0) {
            ans = (ans + dfs(i + 1, j + 1, 0)) % mod;
        }
        if (k < 2) {
            ans = (ans + dfs(i + 1, j, k + 1)) % mod;
        }
        return f[i][j][k] = ans;
    }

private:
    int n;
};
```

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

### **...**

```

```

<!-- tabs:end -->
