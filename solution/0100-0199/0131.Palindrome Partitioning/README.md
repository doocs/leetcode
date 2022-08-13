# [131. 分割回文串](https://leetcode.cn/problems/palindrome-partitioning)

[English Version](/solution/0100-0199/0131.Palindrome%20Partitioning/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code>，请你将<em> </em><code>s</code><em> </em>分割成一些子串，使每个子串都是 <strong>回文串</strong> 。返回 <code>s</code> 所有可能的分割方案。</p>

<p><strong>回文串</strong> 是正着读和反着读都一样的字符串。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aab"
<strong>输出：</strong>[["a","a","b"],["aa","b"]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a"
<strong>输出：</strong>[["a"]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 16</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def partition(self, s: str) -> List[List[str]]:
        ans = []
        n = len(s)
        dp = [[True] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                dp[i][j] = s[i] == s[j] and dp[i + 1][j - 1]

        def dfs(s, i, t):
            nonlocal n
            if i == n:
                ans.append(t.copy())
                return
            for j in range(i, n):
                if dp[i][j]:
                    t.append(s[i : j + 1])
                    dfs(s, j + 1, t)
                    t.pop(-1)

        dfs(s, 0, [])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private boolean[][] dp;
    private List<List<String>> ans;
    private int n;

    public List<List<String>> partition(String s) {
        ans = new ArrayList<>();
        n = s.length();
        dp = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], true);
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
            }
        }
        dfs(s, 0, new ArrayList<>());
        return ans;
    }

    private void dfs(String s, int i, List<String> t) {
        if (i == n) {
            ans.add(new ArrayList<>(t));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (dp[i][j]) {
                t.add(s.substring(i, j + 1));
                dfs(s, j + 1, t);
                t.remove(t.size() - 1);
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<bool>> dp;
    vector<vector<string>> ans;
    int n;

    vector<vector<string>> partition(string s) {
        n = s.size();
        dp.assign(n, vector<bool>(n, true));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                dp[i][j] = s[i] == s[j] && dp[i + 1][j - 1];
            }
        }
        vector<string> t;
        dfs(s, 0, t);
        return ans;
    }

    void dfs(string& s, int i, vector<string> t) {
        if (i == n) {
            ans.push_back(t);
            return;
        }
        for (int j = i; j < n; ++j) {
            if (dp[i][j]) {
                t.push_back(s.substr(i, j - i + 1));
                dfs(s, j + 1, t);
                t.pop_back();
            }
        }
    }
};
```

### **Go**

```go
func partition(s string) [][]string {
	n := len(s)
	dp := make([][]bool, n)
	var ans [][]string
	for i := 0; i < n; i++ {
		dp[i] = make([]bool, n)
		for j := 0; j < n; j++ {
			dp[i][j] = true
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			dp[i][j] = s[i] == s[j] && dp[i+1][j-1]
		}
	}

	var dfs func(s string, i int, t []string)
	dfs = func(s string, i int, t []string) {
		if i == n {
			ans = append(ans, append([]string(nil), t...))
			return
		}
		for j := i; j < n; j++ {
			if dp[i][j] {
				t = append(t, s[i:j+1])
				dfs(s, j+1, t)
				t = t[:len(t)-1]
			}
		}
	}

	var t []string
	dfs(s, 0, t)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
