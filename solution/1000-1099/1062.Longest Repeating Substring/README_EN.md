# [1062. Longest Repeating Substring](https://leetcode.com/problems/longest-repeating-substring)

[中文文档](/solution/1000-1099/1062.Longest%20Repeating%20Substring/README.md)

## Description

<p>Given a string <code>s</code>, return <em>the length of the longest repeating substrings</em>. If no repeating substring exists, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;
<strong>Output:</strong> 0
<strong>Explanation: </strong>There is no repeating substring.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abbaba&quot;
<strong>Output:</strong> 2
<strong>Explanation: </strong>The longest repeating substrings are &quot;ab&quot; and &quot;ba&quot;, each of which occurs twice.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabcaabdaab&quot;
<strong>Output:</strong> 3
<strong>Explanation: </strong>The longest repeating substring is &quot;aab&quot;, which occurs <code>3</code> times.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestRepeatingSubstring(self, s: str) -> int:
        n = len(s)
        dp = [[0] * n for _ in range(n)]
        ans = 0
        for i in range(n):
            for j in range(i + 1, n):
                if s[i] == s[j]:
                    dp[i][j] = dp[i - 1][j - 1] + 1 if i else 1
                    ans = max(ans, dp[i][j])
        return ans
```

### **Java**

```java
class Solution {
    public int longestRepeatingSubstring(String s) {
        int n = s.length();
        int ans = 0;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = i > 0 ? dp[i - 1][j - 1] + 1 : 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestRepeatingSubstring(string s) {
        int n = s.size();
        vector<vector<int>> dp(n, vector<int>(n));
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (s[i] == s[j]) {
                    dp[i][j] = i ? dp[i - 1][j - 1] + 1 : 1;
                    ans = max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestRepeatingSubstring(s string) int {
	n := len(s)
	dp := make([][]int, n)
	for i := range dp {
		dp[i] = make([]int, n)
	}
	ans := 0
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if s[i] == s[j] {
				if i == 0 {
					dp[i][j] = 1
				} else {
					dp[i][j] = dp[i-1][j-1] + 1
				}
				ans = max(ans, dp[i][j])
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
