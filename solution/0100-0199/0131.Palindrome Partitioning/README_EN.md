# [131. Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning)

[中文文档](/solution/0100-0199/0131.Palindrome%20Partitioning/README.md)

## Description

<p>Given a string <code>s</code>, partition <code>s</code> such that every substring of the partition is a <strong>palindrome</strong>. Return all possible palindrome partitioning of <code>s</code>.</p>

<p>A <strong>palindrome</strong> string is a string that reads the same backward as forward.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "aab"
<strong>Output:</strong> [["a","a","b"],["aa","b"]]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "a"
<strong>Output:</strong> [["a"]]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 16</code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
