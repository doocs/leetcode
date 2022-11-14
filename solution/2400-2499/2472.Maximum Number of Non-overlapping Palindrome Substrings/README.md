# [2472. 不重叠回文子字符串的最大数目](https://leetcode.cn/problems/maximum-number-of-non-overlapping-palindrome-substrings)

[English Version](/solution/2400-2499/2472.Maximum%20Number%20of%20Non-overlapping%20Palindrome%20Substrings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 和一个 <strong>正</strong> 整数 <code>k</code> 。</p>

<p>从字符串 <code>s</code> 中选出一组满足下述条件且 <strong>不重叠</strong> 的子字符串：</p>

<ul>
	<li>每个子字符串的长度 <strong>至少</strong> 为 <code>k</code> 。</li>
	<li>每个子字符串是一个 <strong>回文串</strong> 。</li>
</ul>

<p>返回最优方案中能选择的子字符串的 <strong>最大</strong> 数目。</p>

<p><strong>子字符串</strong> 是字符串中一个连续的字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1 ：</strong></p>

<pre>
<strong>输入：</strong>s = "abaccdbbd", k = 3
<strong>输出：</strong>2
<strong>解释：</strong>可以选择 s = "<em><strong>aba</strong></em>cc<em><strong>dbbd</strong></em>" 中斜体加粗的子字符串。"aba" 和 "dbbd" 都是回文，且长度至少为 k = 3 。
可以证明，无法选出两个以上的有效子字符串。
</pre>

<p><strong>示例 2 ：</strong></p>

<pre>
<strong>输入：</strong>s = "adbcda", k = 2
<strong>输出：</strong>0
<strong>解释：</strong>字符串中不存在长度至少为 2 的回文子字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：预处理 + 记忆化搜索**

预处理字符串 $s$，得到 $dp[i][j]$ 表示字符串 $s[i,..j]$ 是否为回文串。

然后定义函数 $dfs(i)$ 表示从字符串 $s[i,..]$ 中选出最多的不重叠回文子串的个数，即：

$$
\begin{aligned}
dfs(i) &= \begin{cases}
0, & i \geq n \\
\max\{dfs(i + 1), \max_{j \geq i + k - 1} \{dfs(j + 1) + 1\}\}, & i < n
\end{cases}
\end{aligned}
$$

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxPalindromes(self, s: str, k: int) -> int:
        @cache
        def dfs(i):
            if i >= n:
                return 0
            ans = dfs(i + 1)
            for j in range(i + k - 1, n):
                if dp[i][j]:
                    ans = max(ans, 1 + dfs(j + 1))
            return ans

        n = len(s)
        dp = [[True] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                dp[i][j] = s[i] == s[j] and dp[i + 1][j - 1]
        ans = dfs(0)
        dfs.cache_clear()
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private boolean[][] dp;
    private int[] f;
    private String s;
    private int n;
    private int k;

    public int maxPalindromes(String s, int k) {
        n = s.length();
        f = new int[n];
        this.s = s;
        this.k = k;
        dp = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], true);
            f[i] = -1;
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
            }
        }
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != -1) {
            return f[i];
        }
        int ans = dfs(i + 1);
        for (int j = i + k - 1; j < n; ++j) {
            if (dp[i][j]) {
                ans = Math.max(ans, 1 + dfs(j + 1));
            }
        }
        f[i] = ans;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxPalindromes(string s, int k) {
        int n = s.size();
        vector<vector<bool>> dp(n, vector<bool>(n, true));
        vector<int> f(n, -1);
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                dp[i][j] = s[i] == s[j] && dp[i + 1][j - 1];
            }
        }
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n) return 0;
            if (f[i] != -1) return f[i];
            int ans = dfs(i + 1);
            for (int j = i + k - 1; j < n; ++j) {
                if (dp[i][j]) {
                    ans = max(ans, 1 + dfs(j + 1));
                }
            }
            f[i] = ans;
            return ans;
        };
        return dfs(0);
    }
};
```

### **Go**

```go
func maxPalindromes(s string, k int) int {
	n := len(s)
	dp := make([][]bool, n)
	f := make([]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]bool, n)
		f[i] = -1
		for j := 0; j < n; j++ {
			dp[i][j] = true
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			dp[i][j] = s[i] == s[j] && dp[i+1][j-1]
		}
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		ans := dfs(i + 1)
		for j := i + k - 1; j < n; j++ {
			if dp[i][j] {
				ans = max(ans, 1+dfs(j+1))
			}
		}
		f[i] = ans
		return ans
	}
	return dfs(0)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
