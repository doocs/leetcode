# [2338. Count the Number of Ideal Arrays](https://leetcode.com/problems/count-the-number-of-ideal-arrays)

[中文文档](/solution/2300-2399/2338.Count%20the%20Number%20of%20Ideal%20Arrays/README.md)

## Description

<p>You are given two integers <code>n</code> and <code>maxValue</code>, which are used to describe an <strong>ideal</strong> array.</p>

<p>A <strong>0-indexed</strong> integer array <code>arr</code> of length <code>n</code> is considered <strong>ideal</strong> if the following conditions hold:</p>

<ul>
	<li>Every <code>arr[i]</code> is a value from <code>1</code> to <code>maxValue</code>, for <code>0 &lt;= i &lt; n</code>.</li>
	<li>Every <code>arr[i]</code> is divisible by <code>arr[i - 1]</code>, for <code>0 &lt; i &lt; n</code>.</li>
</ul>

<p>Return <em>the number of <strong>distinct</strong> ideal arrays of length </em><code>n</code>. Since the answer may be very large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2, maxValue = 5
<strong>Output:</strong> 10
<strong>Explanation:</strong> The following are the possible ideal arrays:
- Arrays starting with the value 1 (5 arrays): [1,1], [1,2], [1,3], [1,4], [1,5]
- Arrays starting with the value 2 (2 arrays): [2,2], [2,4]
- Arrays starting with the value 3 (1 array): [3,3]
- Arrays starting with the value 4 (1 array): [4,4]
- Arrays starting with the value 5 (1 array): [5,5]
There are a total of 5 + 2 + 1 + 1 + 1 = 10 distinct ideal arrays.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5, maxValue = 3
<strong>Output:</strong> 11
<strong>Explanation:</strong> The following are the possible ideal arrays:
- Arrays starting with the value 1 (9 arrays): 
   - With no other distinct values (1 array): [1,1,1,1,1] 
   - With 2<sup>nd</sup> distinct value 2 (4 arrays): [1,1,1,1,2], [1,1,1,2,2], [1,1,2,2,2], [1,2,2,2,2]
   - With 2<sup>nd</sup> distinct value 3 (4 arrays): [1,1,1,1,3], [1,1,1,3,3], [1,1,3,3,3], [1,3,3,3,3]
- Arrays starting with the value 2 (1 array): [2,2,2,2,2]
- Arrays starting with the value 3 (1 array): [3,3,3,3,3]
There are a total of 9 + 1 + 1 = 11 distinct ideal arrays.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= maxValue &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
