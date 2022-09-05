# [650. 只有两个键的键盘](https://leetcode.cn/problems/2-keys-keyboard)

[English Version](/solution/0600-0699/0650.2%20Keys%20Keyboard/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>最初记事本上只有一个字符 <code>'A'</code> 。你每次可以对这个记事本进行两种操作：</p>

<ul>
	<li><code>Copy All</code>（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。</li>
	<li><code>Paste</code>（粘贴）：粘贴<strong> 上一次 </strong>复制的字符。</li>
</ul>

<p>给你一个数字&nbsp;<code>n</code> ，你需要使用最少的操作次数，在记事本上输出 <strong>恰好</strong>&nbsp;<code>n</code>&nbsp;个 <code>'A'</code> 。返回能够打印出&nbsp;<code>n</code>&nbsp;个 <code>'A'</code> 的最少操作次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>3
<strong>输出：</strong>3
<strong>解释：</strong>
最初, 只有一个字符 'A'。
第 1 步, 使用 <strong>Copy All</strong> 操作。
第 2 步, 使用 <strong>Paste </strong>操作来获得 'AA'。
第 3 步, 使用 <strong>Paste</strong> 操作来获得 'AAA'。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

定义 $dfs(i)$ 为输出 $i$ 个字符的最少操作次数。初始化 `dfs(1)=0`。

当 $i\gt 1$ 时，有：

$$
dfs(i)=\min _{j \mid i} (dfs(\frac{i}{j})+j, i), 2\leq j\lt i
$$

时间复杂度 $O(n\sqrt{n})$。

**方法二：动态规划**

记忆化搜索也可以改成动态规划。

$$
dp[i]=\min _{j \mid i} (dp[\frac{i}{j}]+j, i), 2\leq j\lt i
$$

时间复杂度 $O(n\sqrt{n})$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
