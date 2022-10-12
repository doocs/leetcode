# [650. 2 Keys Keyboard](https://leetcode.com/problems/2-keys-keyboard)

[中文文档](/solution/0600-0699/0650.2%20Keys%20Keyboard/README.md)

## Description

<p>There is only one character <code>&#39;A&#39;</code> on the screen of a notepad. You can perform one of two operations on this notepad for each step:</p>

<ul>
	<li>Copy All: You can copy all the characters present on the screen (a partial copy is not allowed).</li>
	<li>Paste: You can paste the characters which are copied last time.</li>
</ul>

<p>Given an integer <code>n</code>, return <em>the minimum number of operations to get the character</em> <code>&#39;A&#39;</code> <em>exactly</em> <code>n</code> <em>times on the screen</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> Initially, we have one character &#39;A&#39;.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get &#39;AA&#39;.
In step 3, we use Paste operation to get &#39;AAA&#39;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minSteps(self, n: int) -> int:
        @cache
        def dfs(n):
            if n == 1:
                return 0
            i, ans = 2, n
            while i * i <= n:
                if n % i == 0:
                    ans = min(ans, dfs(n // i) + i)
                i += 1
            return ans

        return dfs(n)
```

```python
class Solution:
    def minSteps(self, n: int) -> int:
        dp = list(range(n + 1))
        dp[1] = 0
        for i in range(2, n + 1):
            j = 2
            while j * j <= i:
                if i % j == 0:
                    dp[i] = min(dp[i], dp[i // j] + j)
                j += 1
        return dp[-1]
```

### **Java**

```java
class Solution {
    private int[] f;

    public int minSteps(int n) {
        f = new int[n + 1];
        Arrays.fill(f, -1);
        return dfs(n);
    }

    private int dfs(int n) {
        if (n == 1) {
            return 0;
        }
        if (f[n] != -1) {
            return f[n];
        }
        int ans = n;
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                ans = Math.min(ans, dfs(n / i) + i);
            }
        }
        f[n] = ans;
        return ans;
    }
}
```

```java
class Solution {
    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; ++i) {
            dp[i] = i;
        }
        dp[1] = 0;
        for (int i = 2; i < n + 1; ++i) {
            for (int j = 2; j * j <= i; ++j) {
                if (i % j == 0) {
                    dp[i] = Math.min(dp[i], dp[i / j] + j);
                }
            }
        }
        return dp[n];
    }
}
```

```java
class Solution {
    public int minSteps(int n) {
        int res = 0;
        for (int i = 2; n > 1; ++i) {
            while (n % i == 0) {
                res += i;
                n /= i;
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> f;

    int minSteps(int n) {
        f.assign(n + 1, -1);
        return dfs(n);
    }

    int dfs(int n) {
        if (n == 1) return 0;
        if (f[n] != -1) return f[n];
        int ans = n;
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                ans = min(ans, dfs(n / i) + i);
            }
        }
        f[n] = ans;
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int minSteps(int n) {
        vector<int> dp(n + 1);
        iota(dp.begin(), dp.end(), 0);
        dp[1] = 0;
        for (int i = 2; i < n + 1; ++i) {
            for (int j = 2; j * j <= i; ++j) {
                if (i % j == 0) {
                    dp[i] = min(dp[i], dp[i / j] + j);
                }
            }
        }
        return dp[n];
    }
};
```

### **Go**

```go
func minSteps(n int) int {
	f := make([]int, n+1)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(n int) int {
		if n == 1 {
			return 0
		}
		if f[n] != -1 {
			return f[n]
		}
		ans := n
		for i := 2; i*i <= n; i++ {
			if n%i == 0 {
				ans = min(ans, dfs(n/i)+i)
			}
		}
		return ans
	}
	return dfs(n)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func minSteps(n int) int {
	dp := make([]int, n+1)
	for i := range dp {
		dp[i] = i
	}
	dp[1] = 0
	for i := 2; i < n+1; i++ {
		for j := 2; j*j <= i; j++ {
			if i%j == 0 {
				dp[i] = min(dp[i], dp[i/j]+j)
			}
		}
	}
	return dp[n]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
