# [1771. Maximize Palindrome Length From Subsequences](https://leetcode.com/problems/maximize-palindrome-length-from-subsequences)

[中文文档](/solution/1700-1799/1771.Maximize%20Palindrome%20Length%20From%20Subsequences/README.md)

## Description

<p>You are given two strings, <code>word1</code> and <code>word2</code>. You want to construct a string in the following manner:</p>

<ul>
	<li>Choose some <strong>non-empty</strong> subsequence <code>subsequence1</code> from <code>word1</code>.</li>
	<li>Choose some <strong>non-empty</strong> subsequence <code>subsequence2</code> from <code>word2</code>.</li>
	<li>Concatenate the subsequences: <code>subsequence1 + subsequence2</code>, to make the string.</li>
</ul>

<p>Return <em>the <strong>length</strong> of the longest <strong>palindrome</strong> that can be constructed in the described manner. </em>If no palindromes can be constructed, return <code>0</code>.</p>

<p>A <strong>subsequence</strong> of a string <code>s</code> is a string that can be made by deleting some (possibly none) characters from <code>s</code> without changing the order of the remaining characters.</p>

<p>A <strong>palindrome</strong> is a string that reads the same forward&nbsp;as well as backward.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;cacb&quot;, word2 = &quot;cbba&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> Choose &quot;ab&quot; from word1 and &quot;cba&quot; from word2 to make &quot;abcba&quot;, which is a palindrome.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;ab&quot;, word2 = &quot;ab&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> Choose &quot;ab&quot; from word1 and &quot;a&quot; from word2 to make &quot;aba&quot;, which is a palindrome.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;aa&quot;, word2 = &quot;bb&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> You cannot construct a palindrome from the described method, so return 0.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word1.length, word2.length &lt;= 1000</code></li>
	<li><code>word1</code> and <code>word2</code> consist of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestPalindrome(self, word1: str, word2: str) -> int:
        s = word1 + word2
        n = len(s)
        f = [[0] * n for _ in range(n)]
        for i in range(n):
            f[i][i] = 1
        ans = 0
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                if s[i] == s[j]:
                    f[i][j] = f[i + 1][j - 1] + 2
                    if i < len(word1) and j >= len(word1):
                        ans = max(ans, f[i][j])
                else:
                    f[i][j] = max(f[i + 1][j], f[i][j - 1])
        return ans
```

### **Java**

```java
class Solution {
    public int longestPalindrome(String word1, String word2) {
        String s = word1 + word2;
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = 0; i < n; ++i) {
            f[i][i] = 1;
        }
        int ans = 0;
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                    if (i < word1.length() && j >= word1.length()) {
                        ans = Math.max(ans, f[i][j]);
                    }
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
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
    int longestPalindrome(string word1, string word2) {
        string s = word1 + word2;
        int n = s.size();
        int f[n][n];
        memset(f, 0, sizeof f);
        for (int i = 0; i < n; ++i) f[i][i] = 1;
        int ans = 0;
        for (int i = n - 2; ~i; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s[i] == s[j]) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                    if (i < word1.size() && j >= word1.size()) {
                        ans = max(ans, f[i][j]);
                    }
                } else {
                    f[i][j] = max(f[i + 1][j], f[i][j - 1]);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestPalindrome(word1 string, word2 string) (ans int) {
	s := word1 + word2
	n := len(s)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		f[i][i] = 1
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if s[i] == s[j] {
				f[i][j] = f[i+1][j-1] + 2
				if i < len(word1) && j >= len(word1) && ans < f[i][j] {
					ans = f[i][j]
				}
			} else {
				f[i][j] = max(f[i+1][j], f[i][j-1])
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
