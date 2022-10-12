# [1745. Palindrome Partitioning IV](https://leetcode.com/problems/palindrome-partitioning-iv)

[中文文档](/solution/1700-1799/1745.Palindrome%20Partitioning%20IV/README.md)

## Description

<p>Given a string <code>s</code>, return <code>true</code> <em>if it is possible to split the string</em> <code>s</code> <em>into three <strong>non-empty</strong> palindromic substrings. Otherwise, return </em><code>false</code>.​​​​​</p>

<p>A string is said to be palindrome if it the same string when reversed.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcbdd&quot;
<strong>Output:</strong> true
<strong>Explanation: </strong>&quot;abcbdd&quot; = &quot;a&quot; + &quot;bcb&quot; + &quot;dd&quot;, and all three substrings are palindromes.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bcbddxy&quot;
<strong>Output:</strong> false
<strong>Explanation: </strong>s cannot be split into 3 palindromes.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code>​​​​​​ consists only of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def checkPartitioning(self, s: str) -> bool:
        n = len(s)
        g = [[True] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                g[i][j] = s[i] == s[j] and (i + 1 == j or g[i + 1][j - 1])
        for i in range(n - 2):
            for j in range(i + 1, n - 1):
                if g[0][i] and g[i + 1][j] and g[j + 1][-1]:
                    return True
        return False
```

### **Java**

```java
class Solution {
    public boolean checkPartitioning(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n][n];
        for (var e : g) {
            Arrays.fill(e, true);
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s.charAt(i) == s.charAt(j) && (i + 1 == j || g[i + 1][j - 1]);
            }
        }
        for (int i = 0; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                if (g[0][i] && g[i + 1][j] && g[j + 1][n - 1]) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkPartitioning(string s) {
        int n = s.size();
        vector<vector<bool>> g(n, vector<bool>(n, true));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s[i] == s[j] && (i + 1 == j || g[i + 1][j - 1]);
            }
        }
        for (int i = 0; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                if (g[0][i] && g[i + 1][j] && g[j + 1][n - 1]) {
                    return true;
                }
            }
        }
        return false;
    }
};
```

### **Go**

```go
func checkPartitioning(s string) bool {
	n := len(s)
	g := make([][]bool, n)
	for i := range g {
		g[i] = make([]bool, n)
		for j := range g[i] {
			g[i][j] = true
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			g[i][j] = s[i] == s[j] && (i+1 == j || g[i+1][j-1])
		}
	}
	for i := 0; i < n-2; i++ {
		for j := i + 1; j < n-1; j++ {
			if g[0][i] && g[i+1][j] && g[j+1][n-1] {
				return true
			}
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
