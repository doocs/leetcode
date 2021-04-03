# [1143. 最长公共子序列](https://leetcode-cn.com/problems/longest-common-subsequence)

[English Version](/solution/1100-1199/1143.Longest%20Common%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定两个字符串&nbsp;<code>text1</code> 和&nbsp;<code>text2</code>，返回这两个字符串的最长公共子序列。</p>

<p>一个字符串的&nbsp;<em>子序列&nbsp;</em>是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。<br>
例如，&quot;ace&quot; 是 &quot;abcde&quot; 的子序列，但 &quot;aec&quot; 不是 &quot;abcde&quot; 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。</p>

<p>若这两个字符串没有公共子序列，则返回 0。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入：</strong>text1 = &quot;abcde&quot;, text2 = &quot;ace&quot; 
<strong>输出：</strong>3  
<strong>解释：</strong>最长公共子序列是 &quot;ace&quot;，它的长度为 3。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入：</strong>text1 = &quot;abc&quot;, text2 = &quot;abc&quot;
<strong>输出：</strong>3
<strong>解释：</strong>最长公共子序列是 &quot;abc&quot;，它的长度为 3。
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入：</strong>text1 = &quot;abc&quot;, text2 = &quot;def&quot;
<strong>输出：</strong>0
<strong>解释：</strong>两个字符串没有公共子序列，返回 0。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= text1.length &lt;= 1000</code></li>
	<li><code>1 &lt;= text2.length &lt;= 1000</code></li>
	<li>输入的字符串只含有小写英文字符。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划法。

定义 `dp[i][j]` 表示 `text1[0:i-1]` 和 `text2[0:j-1]` 的最长公共子序列（闭区间）。

递推公式如下：

![](./images/gif.gif)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        m, n = len(text1), len(text2)
        dp = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                dp[i][j] = dp[i - 1][j - 1] + 1 if text1[i - 1] == text2[j - 1] else max(dp[i - 1][j], dp[i][j - 1])
        return dp[m][n]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                char c1 = text1.charAt(i - 1), c2 = text2.charAt(j - 1);
                dp[i][j] = c1 == c2 ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m][n];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
