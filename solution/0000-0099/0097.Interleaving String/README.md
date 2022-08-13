# [97. 交错字符串](https://leetcode.cn/problems/interleaving-string)

[English Version](/solution/0000-0099/0097.Interleaving%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定三个字符串&nbsp;<code>s1</code>、<code>s2</code>、<code>s3</code>，请你帮忙验证&nbsp;<code>s3</code>&nbsp;是否是由&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code><em> </em><strong>交错 </strong>组成的。</p>

<p>两个字符串 <code>s</code> 和 <code>t</code> <strong>交错</strong> 的定义与过程如下，其中每个字符串都会被分割成若干 <strong>非空</strong> 子字符串：</p>

<ul>
	<li><code>s = s<sub>1</sub> + s<sub>2</sub> + ... + s<sub>n</sub></code></li>
	<li><code>t = t<sub>1</sub> + t<sub>2</sub> + ... + t<sub>m</sub></code></li>
	<li><code>|n - m| &lt;= 1</code></li>
	<li><strong>交错</strong> 是 <code>s<sub>1</sub> + t<sub>1</sub> + s<sub>2</sub> + t<sub>2</sub> + s<sub>3</sub> + t<sub>3</sub> + ...</code> 或者 <code>t<sub>1</sub> + s<sub>1</sub> + t<sub>2</sub> + s<sub>2</sub> + t<sub>3</sub> + s<sub>3</sub> + ...</code></li>
</ul>

<p><strong>注意：</strong><code>a + b</code> 意味着字符串 <code>a</code> 和 <code>b</code> 连接。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0097.Interleaving%20String/images/interleave.jpg" />
<pre>
<strong>输入：</strong>s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s1 = "", s2 = "", s3 = ""
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= s1.length, s2.length &lt;= 100</code></li>
	<li><code>0 &lt;= s3.length &lt;= 200</code></li>
	<li><code>s1</code>、<code>s2</code>、和 <code>s3</code> 都由小写英文字母组成</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>您能否仅使用 <code>O(s2.length)</code> 额外的内存空间来解决它?</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

题目描述带有一定迷惑性，“交错”的过程其实就类似归并排序的 merge 过程，每次从 `s1` 或 `s2` 的首部取一个字符，最终组成 `s3`，用记忆化搜索或者动态规划都可以解决。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        m, n = len(s1), len(s2)
        if m + n != len(s3):
            return False

        @cache
        def dfs(i, j):
            if i == m and j == n:
                return True

            return (
                i < m
                and s1[i] == s3[i + j]
                and dfs(i + 1, j)
                or j < n
                and s2[j] == s3[i + j]
                and dfs(i, j + 1)
            )

        return dfs(0, 0)
```

```python
class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        m, n = len(s1), len(s2)
        if m + n != len(s3):
            return False
        dp = [[False] * (n + 1) for _ in range(m + 1)]
        dp[0][0] = True
        for i in range(m + 1):
            for j in range(n + 1):
                k = i + j - 1
                if i:
                    dp[i][j] = s1[i - 1] == s3[k] and dp[i - 1][j]
                if j:
                    dp[i][j] |= (s2[j - 1] == s3[k] and dp[i][j - 1])
        return dp[-1][-1]
```

```python
class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        m, n = len(s1), len(s2)
        if m + n != len(s3):
            return False
        dp = [False] * (n + 1)
        dp[0] = True
        for i in range(m + 1):
            for j in range(n + 1):
                k = i + j - 1
                if i:
                    dp[j] &= (s1[i - 1] == s3[k])
                if j:
                    dp[j] |= (s2[j - 1] == s3[k] and dp[j - 1])
        return dp[-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int m;
    private int n;
    private String s1;
    private String s2;
    private String s3;
    private Map<Integer, Boolean> memo = new HashMap<>();

    public boolean isInterleave(String s1, String s2, String s3) {
        m = s1.length();
        n = s2.length();
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        if (m + n != s3.length()) {
            return false;
        }
        return dfs(0, 0);
    }

    private boolean dfs(int i, int j) {
        System.out.println(i + ", " + j);
        if (i == m && j == n) {
            return true;
        }
        if (memo.containsKey(i * 100 + j)) {
            return memo.get(i * 100 + j);
        }

        boolean ret = (i < m && s1.charAt(i) == s3.charAt(i + j) && dfs(i + 1, j)) ||
                (j < n && s2.charAt(j) == s3.charAt(i + j) && dfs(i, j + 1));

        memo.put(i * 100 + j, ret);
        return ret;
    }
}
```

```java
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                int k = i + j - 1;
                if (i > 0) {
                    dp[j] &= (s1.charAt(i - 1) == s3.charAt(k));
                }
                if (j > 0) {
                    dp[j] |= (s2.charAt(j - 1) == s3.charAt(k) && dp[j - 1]);
                }
            }
        }
        return dp[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isInterleave(string s1, string s2, string s3) {
        int m = s1.size(), n = s2.size();
        if (m + n != s3.size()) return false;

        unordered_map<int, bool> memo;

        function<bool(int, int)> dfs;
        dfs = [&](int i, int j) {
            if (i == m && j == n) return true;
            auto it = memo.find(i * 100 + j);
            if (it != memo.end()) return it->second;

            bool ret = (i < m && s1[i] == s3[i + j] && dfs(i + 1, j)) || (j < n && s2[j] == s3[i + j] && dfs(i, j + 1));

            memo[i * 100 + j] = ret;
            return ret;
        };

        return dfs(0, 0);
    }
};
```

```cpp
class Solution {
public:
    bool isInterleave(string s1, string s2, string s3) {
        int m = s1.size(), n = s2.size();
        if (m + n != s3.size()) return false;
        vector<int> dp(n + 1);
        dp[0] = 1;
        for (int i = 0; i <= m; ++i)
        {
            for (int j = 0; j <= n; ++j)
            {
                int k = i + j - 1;
                if (i) dp[j] &= (s1[i - 1] == s3[k]);
                if (j) dp[j] |= (s2[j - 1] == s3[k] && dp[j - 1]);
            }
        }
        return dp[n];
    }
};
```

### **Go**

```go
func isInterleave(s1 string, s2 string, s3 string) bool {
	m, n := len(s1), len(s2)
	if m+n != len(s3) {
		return false
	}

	memo := make(map[int]bool)

	var dfs func(int, int) bool
	dfs = func(i, j int) bool {
		if i == m && j == n {
			return true
		}
		if v, ok := memo[i*100+j]; ok {
			return v
		}

		ret := (i < m && s1[i] == s3[i+j] && dfs(i+1, j)) ||
			(j < n && s2[j] == s3[i+j] && dfs(i, j+1))

		memo[i*100+j] = ret
		return ret
	}

	return dfs(0, 0)
}
```

```go
func isInterleave(s1 string, s2 string, s3 string) bool {
	m, n := len(s1), len(s2)
	if m+n != len(s3) {
		return false
	}
	dp := make([]bool, n+1)
	dp[0] = true
	for i := 0; i <= m; i++ {
		for j := 0; j <= n; j++ {
			k := i + j - 1
			if i > 0 {
				dp[j] = dp[j] && (s1[i-1] == s3[k])
			}
			if j > 0 {
				dp[j] = dp[j] || (s2[j-1] == s3[k] && dp[j-1])
			}
		}
	}
	return dp[n]
}
```

### **...**

```

```

<!-- tabs:end -->
