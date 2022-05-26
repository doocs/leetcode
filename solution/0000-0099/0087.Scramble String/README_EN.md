# [87. Scramble String](https://leetcode.com/problems/scramble-string)

[中文文档](/solution/0000-0099/0087.Scramble%20String/README.md)

## Description

<p>We can scramble a string s to get a string t using the following algorithm:</p>

<ol>
	<li>If the length of the string is 1, stop.</li>
	<li>If the length of the string is &gt; 1, do the following:
	<ul>
		<li>Split the string into two non-empty substrings at a random index, i.e., if the string is <code>s</code>, divide it to <code>x</code> and <code>y</code> where <code>s = x + y</code>.</li>
		<li><strong>Randomly</strong>&nbsp;decide to swap the two substrings or to keep them in the same order. i.e., after this step, <code>s</code> may become <code>s = x + y</code> or <code>s = y + x</code>.</li>
		<li>Apply step 1 recursively on each of the two substrings <code>x</code> and <code>y</code>.</li>
	</ul>
	</li>
</ol>

<p>Given two strings <code>s1</code> and <code>s2</code> of <strong>the same length</strong>, return <code>true</code> if <code>s2</code> is a scrambled string of <code>s1</code>, otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;great&quot;, s2 = &quot;rgeat&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> One possible scenario applied on s1 is:
&quot;great&quot; --&gt; &quot;gr/eat&quot; // divide at random index.
&quot;gr/eat&quot; --&gt; &quot;gr/eat&quot; // random decision is not to swap the two substrings and keep them in order.
&quot;gr/eat&quot; --&gt; &quot;g/r / e/at&quot; // apply the same algorithm recursively on both substrings. divide at random index each of them.
&quot;g/r / e/at&quot; --&gt; &quot;r/g / e/at&quot; // random decision was to swap the first substring and to keep the second substring in the same order.
&quot;r/g / e/at&quot; --&gt; &quot;r/g / e/ a/t&quot; // again apply the algorithm recursively, divide &quot;at&quot; to &quot;a/t&quot;.
&quot;r/g / e/ a/t&quot; --&gt; &quot;r/g / e/ a/t&quot; // random decision is to keep both substrings in the same order.
The algorithm stops now, and the result string is &quot;rgeat&quot; which is s2.
As one possible scenario led s1 to be scrambled to s2, we return true.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;abcde&quot;, s2 = &quot;caebd&quot;
<strong>Output:</strong> false
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;a&quot;, s2 = &quot;a&quot;
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>s1.length == s2.length</code></li>
	<li><code>1 &lt;= s1.length &lt;= 30</code></li>
	<li><code>s1</code> and <code>s2</code> consist of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isScramble(self, s1: str, s2: str) -> bool:
        n = len(s1)
        dp = [[[False] * (n + 1) for _ in range(n)] for _ in range(n)]
        for i in range(n):
            for j in range(n):
                dp[i][j][1] = s1[i] == s2[j]
        for l in range(2, n + 1):
            for i1 in range(n - l + 1):
                for i2 in range(n - l + 1):
                    for i in range(1, l):
                        if dp[i1][i2][i] and dp[i1 + i][i2 + i][l - i]:
                            dp[i1][i2][l] = True
                            break
                        if dp[i1][i2 + l - i][i] and dp[i1 + i][i2][l - i]:
                            dp[i1][i2][l] = True
                            break
        return dp[0][0][n]
```

### **Java**

```java
class Solution {
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        for (int len = 2; len <= n; ++len) {
            for (int i1 = 0; i1 <= n - len; ++i1) {
                for (int i2 = 0; i2 <= n - len; ++i2) {
                    for (int i = 1; i < len; ++i) {
                        if (dp[i1][i2][i] && dp[i1 + i][i2 + i][len - i]) {
                            dp[i1][i2][len] = true;
                            break;
                        }
                        if (dp[i1][i2 + len - i][i] && dp[i1 + i][i2][len - i]) {
                            dp[i1][i2][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
