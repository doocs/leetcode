---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1531.String%20Compression%20II/README_EN.md
rating: 2575
source: Weekly Contest 199 Q4
tags:
    - String
    - Dynamic Programming
---

# [1531. String Compression II](https://leetcode.com/problems/string-compression-ii)

[中文文档](/solution/1500-1599/1531.String%20Compression%20II/README.md)

## Description

<p><a href="http://en.wikipedia.org/wiki/Run-length_encoding">Run-length encoding</a> is a string compression method that works by&nbsp;replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string&nbsp;<code>&quot;aabccc&quot;</code>&nbsp;we replace <font face="monospace"><code>&quot;aa&quot;</code></font>&nbsp;by&nbsp;<font face="monospace"><code>&quot;a2&quot;</code></font>&nbsp;and replace <font face="monospace"><code>&quot;ccc&quot;</code></font>&nbsp;by&nbsp;<font face="monospace"><code>&quot;c3&quot;</code></font>. Thus the compressed string becomes <font face="monospace"><code>&quot;a2bc3&quot;</code>.</font></p>

<p>Notice that in this problem, we are not adding&nbsp;<code>&#39;1&#39;</code>&nbsp;after single characters.</p>

<p>Given a&nbsp;string <code>s</code>&nbsp;and an integer <code>k</code>. You need to delete <strong>at most</strong>&nbsp;<code>k</code> characters from&nbsp;<code>s</code>&nbsp;such that the run-length encoded version of <code>s</code>&nbsp;has minimum length.</p>

<p>Find the <em>minimum length of the run-length encoded&nbsp;version of </em><code>s</code><em> after deleting at most </em><code>k</code><em> characters</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaabcccd&quot;, k = 2
<strong>Output:</strong> 4
<b>Explanation: </b>Compressing s without deleting anything will give us &quot;a3bc3d&quot; of length 6. Deleting any of the characters &#39;a&#39; or &#39;c&#39; would at most decrease the length of the compressed string to 5, for instance delete 2 &#39;a&#39; then we will have s = &quot;abcccd&quot; which compressed is abc3d. Therefore, the optimal way is to delete &#39;b&#39; and &#39;d&#39;, then the compressed version of s will be &quot;a3c3&quot; of length 4.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabbaa&quot;, k = 2
<strong>Output:</strong> 2
<b>Explanation: </b>If we delete both &#39;b&#39; characters, the resulting compressed string would be &quot;a4&quot; of length 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaaaaaaaaaa&quot;, k = 0
<strong>Output:</strong> 3
<strong>Explanation: </strong>Since k is zero, we cannot delete anything. The compressed string is &quot;a11&quot; of length 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```java
class Solution {
    public int getLengthOfOptimalCompression(String s, int k) {
        // dp[i][k] := the length of the optimal compression of s[i..n) with at most
        // k deletion
        dp = new int[s.length()][k + 1];
        Arrays.stream(dp).forEach(A -> Arrays.fill(A, K_MAX));
        return compression(s, 0, k);
    }

    private static final int K_MAX = 101;
    private int[][] dp;

    private int compression(final String s, int i, int k) {
        if (k < 0) {
            return K_MAX;
        }
        if (i == s.length() || s.length() - i <= k) {
            return 0;
        }
        if (dp[i][k] != K_MAX) {
            return dp[i][k];
        }
        int maxFreq = 0;
        int[] count = new int[128];
        // Make letters in s[i..j] be the same.
        // Keep the letter that has the maximum frequency in this range and remove
        // the other letters.
        for (int j = i; j < s.length(); ++j) {
            maxFreq = Math.max(maxFreq, ++count[s.charAt(j)]);
            dp[i][k] = Math.min(
                dp[i][k], getLength(maxFreq) + compression(s, j + 1, k - (j - i + 1 - maxFreq)));
        }
        return dp[i][k];
    }

    // Returns the length to compress `maxFreq`.
    private int getLength(int maxFreq) {
        if (maxFreq == 1) {
            return 1; // c
        }
        if (maxFreq < 10) {
            return 2; // [1-9]c
        }
        if (maxFreq < 100) {
            return 3; // [1-9][0-9]c
        }
        return 4; // [1-9][0-9][0-9]c
    }
}
```

<!-- tabs:end -->

<!-- end -->
