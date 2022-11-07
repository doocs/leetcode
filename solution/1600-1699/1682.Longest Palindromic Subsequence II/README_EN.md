# [1682. Longest Palindromic Subsequence II](https://leetcode.com/problems/longest-palindromic-subsequence-ii)

[中文文档](/solution/1600-1699/1682.Longest%20Palindromic%20Subsequence%20II/README.md)

## Description

<p>A subsequence of a string <code>s</code> is considered a <strong>good palindromic subsequence</strong> if:</p>

<ul>
	<li>It is a subsequence of <code>s</code>.</li>
	<li>It is a palindrome (has the same value if reversed).</li>
	<li>It has an <strong>even</strong> length.</li>
	<li>No two consecutive characters are equal, except the two middle ones.</li>
</ul>

<p>For example, if <code>s = &quot;abcabcabb&quot;</code>, then <code>&quot;abba&quot;</code> is considered a <strong>good palindromic subsequence</strong>, while <code>&quot;bcb&quot;</code> (not even length) and <code>&quot;bbbb&quot;</code> (has equal consecutive characters) are not.</p>

<p>Given a string <code>s</code>, return <em>the <strong>length</strong> of the <strong>longest good palindromic subsequence</strong> in </em><code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bbabab&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest good palindromic subsequence of s is &quot;baab&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;dcbccacdb&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest good palindromic subsequence of s is &quot;dccd&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 250</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestPalindromeSubseq(self, s: str) -> int:
        @cache
        def dfs(i, j, x):
            if i >= j:
                return 0
            if s[i] == s[j] and s[i] != x:
                return dfs(i + 1, j - 1, s[i]) + 2
            return max(dfs(i + 1, j, x), dfs(i, j - 1, x))

        ans = dfs(0, len(s) - 1, '')
        dfs.cache_clear()
        return ans
```

### **Java**

```java
class Solution {
    private int[][][] f;
    private String s;

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        this.s = s;
        f = new int[n][n][27];
        for (var a : f) {
            for (var b : a) {
                Arrays.fill(b, -1);
            }
        }
        return dfs(0, n - 1, 26);
    }

    private int dfs(int i, int j, int x) {
        if (i >= j) {
            return 0;
        }
        if (f[i][j][x] != -1) {
            return f[i][j][x];
        }
        int ans = 0;
        if (s.charAt(i) == s.charAt(j) && s.charAt(i) - 'a' != x) {
            ans = dfs(i + 1, j - 1, s.charAt(i) - 'a') + 2;
        } else {
            ans = Math.max(dfs(i + 1, j, x), dfs(i, j - 1, x));
        }
        f[i][j][x] = ans;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int f[251][251][27];

    int longestPalindromeSubseq(string s) {
        int n = s.size();
        memset(f, -1, sizeof f);
        function<int(int, int, int)> dfs = [&](int i, int j, int x) -> int {
            if (i >= j) return 0;
            if (f[i][j][x] != -1) return f[i][j][x];
            int ans = 0;
            if (s[i] == s[j] && s[i] - 'a' != x) ans = dfs(i + 1, j - 1, s[i] - 'a') + 2;
            else ans = max(dfs(i + 1, j, x), dfs(i, j - 1, x));
            f[i][j][x] = ans;
            return ans;
        };
        return dfs(0, n - 1, 26);
    }
};
```

### **Go**

```go
func longestPalindromeSubseq(s string) int {
	n := len(s)
	f := make([][][]int, n)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, 27)
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	var dfs func(i, j, x int) int
	dfs = func(i, j, x int) int {
		if i >= j {
			return 0
		}
		if f[i][j][x] != -1 {
			return f[i][j][x]
		}
		ans := 0
		if s[i] == s[j] && int(s[i]-'a') != x {
			ans = dfs(i+1, j-1, int(s[i]-'a')) + 2
		} else {
			ans = max(dfs(i+1, j, x), dfs(i, j-1, x))
		}
		f[i][j][x] = ans
		return ans
	}
	return dfs(0, n-1, 26)
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
