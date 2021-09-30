# [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring)

[中文文档](/solution/0000-0099/0005.Longest%20Palindromic%20Substring/README.md)

## Description

<p>Given a string <code>s</code>, return&nbsp;<em>the longest palindromic substring</em> in <code>s</code>.</p>



<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>



<pre>

<strong>Input:</strong> s = &quot;babad&quot;

<strong>Output:</strong> &quot;bab&quot;

<strong>Note:</strong> &quot;aba&quot; is also a valid answer.

</pre>



<p><strong>Example 2:</strong></p>



<pre>

<strong>Input:</strong> s = &quot;cbbd&quot;

<strong>Output:</strong> &quot;bb&quot;

</pre>



<p><strong>Example 3:</strong></p>



<pre>

<strong>Input:</strong> s = &quot;a&quot;

<strong>Output:</strong> &quot;a&quot;

</pre>



<p><strong>Example 4:</strong></p>



<pre>

<strong>Input:</strong> s = &quot;ac&quot;

<strong>Output:</strong> &quot;a&quot;

</pre>



<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>



<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consist of only digits and English letters (lower-case and/or upper-case),</li>
</ul>

## Solutions

Dynamic programming.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestPalindrome(self, s: str) -> str:
        n = len(s)
        dp = [[False] * n for _ in range(n)]
        start, mx = 0, 1
        for j in range(n):
            for i in range(j + 1):
                if j - i < 2:
                    dp[i][j] = s[i] == s[j]
                else:
                    dp[i][j] = dp[i + 1][j - 1] and s[i] == s[j]
                if dp[i][j] and mx < j - i + 1:
                    start, mx = i, j - i + 1
        return s[start:start + mx]
```

### **Java**

```java
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int mx = 1, start = 0;
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i <= j; ++i) {
                if (j - i < 2) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }
                if (dp[i][j] && mx < j - i + 1) {
                    mx = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + mx);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string longestPalindrome(string s) {
        int n = s.size();
        vector<vector<bool>> dp(n, vector<bool>(n, false));
        int start = 0, mx = 1;
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i <= j; ++i) {
                if (j - i < 2) {
                    dp[i][j] = s[i] == s[j];
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && s[i] == s[j];
                }
                if (dp[i][j] && mx < j - i + 1) {
                    start = i;
                    mx = j - i + 1;
                }
            }
        }
        return s.substr(start, mx);
    }
};
```

### **Go**

```go
func longestPalindrome(s string) string {
	n := len(s)
	dp := make([][]bool, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]bool, n)
	}
	mx, start := 1, 0
	for j := 0; j < n; j++ {
		for i := 0; i <= j; i++ {
			if j-i < 2 {
				dp[i][j] = s[i] == s[j]
			} else {
				dp[i][j] = dp[i+1][j-1] && s[i] == s[j]
			}
			if dp[i][j] && mx < j-i+1 {
				mx, start = j-i+1, i
			}
		}
	}
	return s[start : start+mx]
}
```

### **C#**

```cs
public class Solution{
    public string LongestPalindrome(string s) {
        int n = s.Length;
        bool[,] dp = new bool[n, n];
        int mx = 1, start = 0;
        for (int j = 0; j < n; ++j)
        {
            for (int i = 0; i <= j; ++i)
            {
                if (j - i < 2)
                {
                    dp[i, j] = s[i] == s[j];
                }
                else
                {
                    dp[i, j] = dp[i + 1, j - 1] && s[i] == s[j];
                }
                if (dp[i, j] && mx < j - i + 1)
                {
                    mx = j - i + 1;
                    start = i;
                }
            }
        }
        return s.Substring(start, mx);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
