# [1653. Minimum Deletions to Make String Balanced](https://leetcode.com/problems/minimum-deletions-to-make-string-balanced)

[中文文档](/solution/1600-1699/1653.Minimum%20Deletions%20to%20Make%20String%20Balanced/README.md)

## Description

<p>You are given a string <code>s</code> consisting only of characters <code>&#39;a&#39;</code> and <code>&#39;b&#39;</code>​​​​.</p>

<p>You can delete any number of characters in <code>s</code> to make <code>s</code> <strong>balanced</strong>. <code>s</code> is <strong>balanced</strong> if there is no pair of indices <code>(i,j)</code> such that <code>i &lt; j</code> and <code>s[i] = &#39;b&#39;</code> and <code>s[j]= &#39;a&#39;</code>.</p>

<p>Return <em>the <strong>minimum</strong> number of deletions needed to make </em><code>s</code><em> <strong>balanced</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aababbab&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can either:
Delete the characters at 0-indexed positions 2 and 6 (&quot;aa<u>b</u>abb<u>a</u>b&quot; -&gt; &quot;aaabbb&quot;), or
Delete the characters at 0-indexed positions 3 and 6 (&quot;aab<u>a</u>bb<u>a</u>b&quot; -&gt; &quot;aabbbb&quot;).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bbaaaaabb&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The only solution is to delete the first two characters.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is&nbsp;<code>&#39;a&#39;</code> or <code>&#39;b&#39;</code>​​.</li>
</ul>

## Solutions

Dynamic programming.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumDeletions(self, s: str) -> int:
        n = len(s)
        dp = [0] * (n + 1)
        b = 0
        for i, c in enumerate(s, 1):
            if c == 'b':
                dp[i] = dp[i - 1]
                b += 1
            else:
                dp[i] = min(dp[i - 1] + 1, b)
        return dp[n]
```

```python
class Solution:
    def minimumDeletions(self, s: str) -> int:
        ans = b = 0
        for c in s:
            if c == 'b':
                b += 1
            else:
                ans = min(b, ans + 1)
        return ans
```

### **Java**

```java
class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        int b = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == 'b') {
                dp[i + 1] = dp[i];
                ++b;
            } else {
                dp[i + 1] = Math.min(dp[i] + 1, b);
            }
        }
        return dp[n];
    }
}
```

```java
class Solution {
    public int minimumDeletions(String s) {
        int ans = 0, b = 0;
        for (char c : s.toCharArray()) {
            if (c == 'b') {
                ++b;
            } else {
                ans = Math.min(b, ans + 1);
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
    int minimumDeletions(string s) {
        int n = s.size();
        vector<int> dp(n + 1);
        int b = 0;
        for (int i = 0; i < n; ++i) {
            if (s[i] == 'b') {
                dp[i + 1] = dp[i];
                ++b;
            } else {
                dp[i + 1] = min(dp[i] + 1, b);
            }
        }
        return dp[n];
    }
};
```

```cpp
class Solution {
public:
    int minimumDeletions(string s) {
        int ans = 0, b = 0;
        for (char c : s) {
            if (c == 'b') {
                ++b;
            } else {
                ans = min(b, ans + 1);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumDeletions(s string) int {
	n := len(s)
	dp := make([]int, n+1)
	b := 0
	for i, c := range s {
		if c == 'b' {
			b++
			dp[i+1] = dp[i]
		} else {
			dp[i+1] = min(dp[i]+1, b)
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

```go
func minimumDeletions(s string) int {
	ans, b := 0, 0
	for _, c := range s {
		if c == 'b' {
			b++
		} else {
			ans = min(b, ans+1)
		}
	}
	return ans
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
