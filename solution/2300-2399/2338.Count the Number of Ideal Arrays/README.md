# [2338. 统计理想数组的数目](https://leetcode.cn/problems/count-the-number-of-ideal-arrays)

[English Version](/solution/2300-2399/2338.Count%20the%20Number%20of%20Ideal%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数 <code>n</code> 和 <code>maxValue</code> ，用于描述一个 <strong>理想数组</strong> 。</p>

<p>对于下标从 <strong>0</strong> 开始、长度为 <code>n</code> 的整数数组 <code>arr</code> ，如果满足以下条件，则认为该数组是一个 <strong>理想数组</strong> ：</p>

<ul>
	<li>每个 <code>arr[i]</code> 都是从 <code>1</code> 到 <code>maxValue</code> 范围内的一个值，其中 <code>0 &lt;= i &lt; n</code> 。</li>
	<li>每个 <code>arr[i]</code> 都可以被 <code>arr[i - 1]</code> 整除，其中 <code>0 &lt; i &lt; n</code> 。</li>
</ul>

<p>返回长度为 <code>n</code> 的 <strong>不同</strong> 理想数组的数目。由于答案可能很大，返回对 <code>10<sup>9</sup> + 7</code> 取余的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 2, maxValue = 5
<strong>输出：</strong>10
<strong>解释：</strong>存在以下理想数组：
- 以 1 开头的数组（5 个）：[1,1]、[1,2]、[1,3]、[1,4]、[1,5]
- 以 2 开头的数组（2 个）：[2,2]、[2,4]
- 以 3 开头的数组（1 个）：[3,3]
- 以 4 开头的数组（1 个）：[4,4]
- 以 5 开头的数组（1 个）：[5,5]
共计 5 + 2 + 1 + 1 + 1 = 10 个不同理想数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 5, maxValue = 3
<strong>输出：</strong>11
<strong>解释：</strong>存在以下理想数组：
- 以 1 开头的数组（9 个）：
   - 不含其他不同值（1 个）：[1,1,1,1,1] 
   - 含一个不同值 2（4 个）：[1,1,1,1,2], [1,1,1,2,2], [1,1,2,2,2], [1,2,2,2,2]
   - 含一个不同值 3（4 个）：[1,1,1,1,3], [1,1,1,3,3], [1,1,3,3,3], [1,3,3,3,3]
- 以 2 开头的数组（1 个）：[2,2,2,2,2]
- 以 3 开头的数组（1 个）：[3,3,3,3,3]
共计 9 + 1 + 1 = 11 个不同理想数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= maxValue &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

我们注意到，$maxValue$ 的最大值不超过 $10^4$，如果数组不含重复元素，那么数组最多只会有不超过 $16$ 个元素。

**方法一：记忆化搜索 + 组合计数**

**方法二：动态规划**

设 $dp[i][j]$ 表示以 $i$ 结尾，且由 $j$ 个不同元素构成的序列的方案数。初始值 $dp[i][1]=1$。

考虑 $n$ 个小球，最终划分为 $j$ 份，那么可以用“隔板法”，即在 $n-1$ 个位置上插入 $j-1$ 个隔板，那么组合数为 $C_{n-1}^{j-1}$ 。

我们可以预处理组合数 $C[i][j]$，根据递推公式 $C[i][j]=C[i-1][j]+C[i-1][j-1]$ 求得，特别地，当 $j=0$ 时，$C[i][j]=1$。

最终的答案为 $\sum\limits_{i=1}^{k}\sum\limits_{j=1}^{\log_2 k + 1}dp[i][j] \times C_{n-1}^{j-1}$ 。其中 $k$ 表示数组的最大值，即 $maxValue$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def idealArrays(self, n: int, maxValue: int) -> int:
        @cache
        def dfs(i, cnt):
            res = c[-1][cnt - 1]
            if cnt < n:
                k = 2
                while k * i <= maxValue:
                    res = (res + dfs(k * i, cnt + 1)) % mod
                    k += 1
            return res

        c = [[0] * 16 for _ in range(n)]
        mod = 10**9 + 7
        for i in range(n):
            for j in range(min(16, i + 1)):
                c[i][j] = 1 if j == 0 else (c[i - 1][j] + c[i - 1][j - 1]) % mod
        ans = 0
        for i in range(1, maxValue + 1):
            ans = (ans + dfs(i, 1)) % mod
        return ans
```

```python
class Solution:
    def idealArrays(self, n: int, maxValue: int) -> int:
        c = [[0] * 16 for _ in range(n)]
        mod = 10**9 + 7
        for i in range(n):
            for j in range(min(16, i + 1)):
                c[i][j] = 1 if j == 0 else (c[i - 1][j] + c[i - 1][j - 1]) % mod
        dp = [[0] * 16 for _ in range(maxValue + 1)]
        for i in range(1, maxValue + 1):
            dp[i][1] = 1
        for j in range(1, 15):
            for i in range(1, maxValue + 1):
                k = 2
                while k * i <= maxValue:
                    dp[k * i][j + 1] = (dp[k * i][j + 1] + dp[i][j]) % mod
                    k += 1
        ans = 0
        for i in range(1, maxValue + 1):
            for j in range(1, 16):
                ans = (ans + dp[i][j] * c[-1][j - 1]) % mod
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[][] f;
    private int[][] c;
    private int n;
    private int m;
    private static final int MOD = (int) 1e9 + 7;

    public int idealArrays(int n, int maxValue) {
        this.n = n;
        this.m = maxValue;
        this.f = new int[maxValue + 1][16];
        for (int[] row : f) {
            Arrays.fill(row, -1);
        }
        c = new int[n][16];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i && j < 16; ++j) {
                c[i][j] = j == 0 ? 1 : (c[i - 1][j] + c[i - 1][j - 1]) % MOD;
            }
        }
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            ans = (ans + dfs(i, 1)) % MOD;
        }
        return ans;
    }

    private int dfs(int i, int cnt) {
        if (f[i][cnt] != -1) {
            return f[i][cnt];
        }
        int res = c[n - 1][cnt - 1];
        if (cnt < n) {
            for (int k = 2; k * i <= m; ++k) {
                res = (res + dfs(k * i, cnt + 1)) % MOD;
            }
        }
        f[i][cnt] = res;
        return res;

    }
}
```

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int idealArrays(int n, int maxValue) {
        int[][] c = new int[n][16];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i && j < 16; ++j) {
                c[i][j] = j == 0 ? 1 : (c[i - 1][j] + c[i - 1][j - 1]) % MOD;
            }
        }
        long[][] dp = new long[maxValue + 1][16];
        for (int i = 1; i <= maxValue; ++i) {
            dp[i][1] = 1;
        }
        for (int j = 1; j < 15; ++j) {
            for (int i = 1; i <= maxValue; ++i) {
                int k = 2;
                for (; k * i <= maxValue; ++k) {
                    dp[k * i][j + 1] = (dp[k * i][j + 1] + dp[i][j]) % MOD;
                }
            }
        }
        long ans = 0;
        for (int i = 1; i <= maxValue; ++i) {
            for (int j = 1; j < 16; ++j) {
                ans = (ans + dp[i][j] * c[n - 1][j - 1]) % MOD;
            }
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int m, n;
    const int mod = 1e9 + 7;
    vector<vector<int>> f;
    vector<vector<int>> c;

    int idealArrays(int n, int maxValue) {
        this->m = maxValue;
        this->n = n;
        f.assign(maxValue + 1, vector<int>(16, -1));
        c.assign(n, vector<int>(16, 0));
        for (int i = 0; i < n; ++i)
            for (int j = 0; j <= i && j < 16; ++j)
                c[i][j] = !j ? 1 : (c[i - 1][j] + c[i - 1][j - 1]) % mod;
        int ans = 0;
        for (int i = 1; i <= m; ++i) ans = (ans + dfs(i, 1)) % mod;
        return ans;
    }

    int dfs(int i, int cnt) {
        if (f[i][cnt] != -1) return f[i][cnt];
        int res = c[n - 1][cnt - 1];
        if (cnt < n)
            for (int k = 2; k * i <= m; ++k)
                res = (res + dfs(k * i, cnt + 1)) % mod;
        f[i][cnt] = res;
        return res;
    }
};
```

```cpp
using ll = long long;

class Solution {
public:
    const int mod = 1e9 + 7;

    int idealArrays(int n, int maxValue) {
        vector<vector<int>> c(n, vector<int>(16));
        for (int i = 0; i < n; ++i)
            for (int j = 0; j <= i && j < 16; ++j)
                c[i][j] = j == 0 ? 1 : (c[i - 1][j] + c[i - 1][j - 1]) % mod;
        vector<vector<ll>> dp(maxValue + 1, vector<ll>(16));
        for (int i = 1; i <= maxValue; ++i) dp[i][1] = 1;
        for (int j = 1; j < 15; ++j)
        {
            for (int i = 1; i <= maxValue; ++i)
            {
                int k = 2;
                for (; k * i <= maxValue; ++k) dp[k * i][j + 1] = (dp[k * i][j + 1] + dp[i][j]) % mod;
            }
        }
        ll ans = 0;
        for (int i = 1; i <= maxValue; ++i)
            for (int j = 1; j < 16; ++j)
                ans = (ans + dp[i][j] * c[n - 1][j - 1]) % mod;
        return (int) ans;
    }
};
```

### **Go**

```go
func idealArrays(n int, maxValue int) int {
	mod := int(1e9) + 7
	m := maxValue
	c := make([][]int, n)
	f := make([][]int, m+1)
	for i := range c {
		c[i] = make([]int, 16)
	}
	for i := range f {
		f[i] = make([]int, 16)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(int, int) int
	dfs = func(i, cnt int) int {
		if f[i][cnt] != -1 {
			return f[i][cnt]
		}
		res := c[n-1][cnt-1]
		if cnt < n {
			for k := 2; k*i <= m; k++ {
				res = (res + dfs(k*i, cnt+1)) % mod
			}
		}
		f[i][cnt] = res
		return res
	}
	for i := 0; i < n; i++ {
		for j := 0; j <= i && j < 16; j++ {
			if j == 0 {
				c[i][j] = 1
			} else {
				c[i][j] = (c[i-1][j] + c[i-1][j-1]) % mod
			}
		}
	}
	ans := 0
	for i := 1; i <= m; i++ {
		ans = (ans + dfs(i, 1)) % mod
	}
	return ans
}
```

```go
func idealArrays(n int, maxValue int) int {
	mod := int(1e9) + 7
	c := make([][]int, n)
	for i := range c {
		c[i] = make([]int, 16)
	}
	for i := 0; i < n; i++ {
		for j := 0; j <= i && j < 16; j++ {
			if j == 0 {
				c[i][j] = 1
			} else {
				c[i][j] = (c[i-1][j] + c[i-1][j-1]) % mod
			}
		}
	}
	dp := make([][]int, maxValue+1)
	for i := range dp {
		dp[i] = make([]int, 16)
		dp[i][1] = 1
	}
	for j := 1; j < 15; j++ {
		for i := 1; i <= maxValue; i++ {
			k := 2
			for ; k*i <= maxValue; k++ {
				dp[k*i][j+1] = (dp[k*i][j+1] + dp[i][j]) % mod
			}
		}
	}
	ans := 0
	for i := 1; i <= maxValue; i++ {
		for j := 1; j < 16; j++ {
			ans = (ans + dp[i][j]*c[n-1][j-1]) % mod
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
