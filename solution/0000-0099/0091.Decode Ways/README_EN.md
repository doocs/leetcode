# [91. Decode Ways](https://leetcode.com/problems/decode-ways)

[中文文档](/solution/0000-0099/0091.Decode%20Ways/README.md)

## Description

<p>A message containing letters from <code>A-Z</code> is being encoded to numbers using the following mapping:</p>

<pre>

&#39;A&#39; -&gt; 1

&#39;B&#39; -&gt; 2

...

&#39;Z&#39; -&gt; 26

</pre>

<p>Given a <strong>non-empty</strong> string containing only digits, determine the total number of ways to decode it.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> &quot;12&quot;

<strong>Output:</strong> 2

<strong>Explanation:</strong>&nbsp;It could be decoded as &quot;AB&quot; (1 2) or &quot;L&quot; (12).

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> &quot;226&quot;

<strong>Output:</strong> 3

<strong>Explanation:</strong>&nbsp;It could be decoded as &quot;BZ&quot; (2 26), &quot;VF&quot; (22 6), or &quot;BBF&quot; (2 2 6).</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

Solution1:

```python
class Solution:
    def numDecodings(self, s: str) -> int:
        n = len(s)
        dp = [0] * (n + 1)
        dp[0] = 1
        for i in range(1, n + 1):
            if s[i - 1] != '0':
                dp[i] += dp[i - 1]
            if i > 1 and s[i - 2] != '0' and (int(s[i - 2]) * 10 + int(s[i - 1]) <= 26):
                dp[i] += dp[i - 2]
        return dp[n]
```

Solution2:

```python
class Solution:
    def numDecodings(self, s: str) -> int:
        n = len(s)
        a, b, c = 0, 1, 0
        for i in range(1, n + 1):
            c = 0
            if s[i - 1] != '0':
                c += b
            if i > 1 and s[i - 2] != '0' and (int(s[i - 2]) * 10 + int(s[i - 1]) <= 26):
                c += a
            a, b = b, c
        return c
```

### **Java**

Solution1:

```java
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0') <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
}
```

Solution2:

```java
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int a = 0, b = 1, c = 0;
        for (int i = 1; i <= n; ++i) {
            c = 0;
            if (s.charAt(i - 1) != '0') {
                c += b;
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0') <= 26) {
                c += a;
            }
            a = b;
            b = c;
        }
        return c;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
