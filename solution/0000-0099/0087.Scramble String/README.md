# [87. 扰乱字符串](https://leetcode-cn.com/problems/scramble-string)

[English Version](/solution/0000-0099/0087.Scramble%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个字符串&nbsp;<em>s1</em>，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。</p>

<p>下图是字符串&nbsp;<em>s1</em>&nbsp;=&nbsp;<code>&quot;great&quot;</code>&nbsp;的一种可能的表示形式。</p>

<pre>    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
</pre>

<p>在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。</p>

<p>例如，如果我们挑选非叶节点&nbsp;<code>&quot;gr&quot;</code>&nbsp;，交换它的两个子节点，将会产生扰乱字符串&nbsp;<code>&quot;rgeat&quot;</code>&nbsp;。</p>

<pre>    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
</pre>

<p>我们将&nbsp;<code>&quot;rgeat&rdquo;</code>&nbsp;称作&nbsp;<code>&quot;great&quot;</code>&nbsp;的一个扰乱字符串。</p>

<p>同样地，如果我们继续交换节点&nbsp;<code>&quot;eat&quot;</code>&nbsp;和&nbsp;<code>&quot;at&quot;</code>&nbsp;的子节点，将会产生另一个新的扰乱字符串&nbsp;<code>&quot;rgtae&quot;</code>&nbsp;。</p>

<pre>    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
</pre>

<p>我们将&nbsp;<code>&quot;rgtae&rdquo;</code>&nbsp;称作&nbsp;<code>&quot;great&quot;</code>&nbsp;的一个扰乱字符串。</p>

<p>给出两个长度相等的字符串 <em>s1 </em>和&nbsp;<em>s2</em>，判断&nbsp;<em>s2&nbsp;</em>是否是&nbsp;<em>s1&nbsp;</em>的扰乱字符串。</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong> s1 = &quot;great&quot;, s2 = &quot;rgeat&quot;
<strong>输出:</strong> true
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> s1 = &quot;abcde&quot;, s2 = &quot;caebd&quot;
<strong>输出:</strong> false</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划法。

假设 `dp[i][j][len]` 表示从字符串 S 中 i 开始长度为 len 的字符串是否能变换为从字符串 T 中 j 开始长度为 len 的字符串。题目可转变为求 `dp[0][0][n]`。

在 `len` 为 1 的情况下，只需要判断 `S[i]` 是否等于 `T[j]`。所以可以对 dp 进行初始化：`dp[i][j][1] = S[i] == T[j]`，其中，`i,j ∈ [0, n)`。

在 `len` 大于 1 的情况下，枚举 S 的长度 `i ∈ [1, len-1]`，`dp[i1][i2][i]` 表示 S1 能变成 T1，`dp[i1 + i][i2 + i][len - i]` 表示 S2 能变成 T2；或者 S1 能变成 T2，S2 能变成 T1。

![](./images/demo.png)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isScramble(self, s1: str, s2: str) -> bool:
        n = len(s1)
        dp = [[[False] * (n + 1) for _ in range(n)] for _ in range(n)]
        for i in range(n):
            for j in range(n):
                dp[i][j][1] = s1[i] == s2[j]
        # 枚举长度区间[2, n]
        for l in range(2, n + 1):
            # 枚举s1的起始位置
            for i1 in range(n - l + 1):
                # 枚举s2的起始位置
                for i2 in range(n - l + 1):
                    # 枚举分割的位置
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

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isScramble(String s1, String s2) {
        // 题目已说明 s1.length == s2.length，无须再判断长度是否相等
        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                // 长度为1时，两字符必须相等
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        // 枚举长度区间[2, n]
        for (int len = 2; len <= n; ++len) {
            // 枚举s1的起始位置
            for (int i1 = 0; i1 <= n - len; ++i1) {
                // 枚举s2的起始位置
                for (int i2 = 0; i2 <= n - len; ++i2) {
                    // 枚举分割的位置
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
