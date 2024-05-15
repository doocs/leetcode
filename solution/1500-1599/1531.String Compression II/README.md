---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1531.String%20Compression%20II/README.md
rating: 2575
tags:
    - 字符串
    - 动态规划
---

# [1531. 压缩字符串 II](https://leetcode.cn/problems/string-compression-ii)

[English Version](/solution/1500-1599/1531.String%20Compression%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><a href="https://baike.baidu.com/item/%E8%A1%8C%E7%A8%8B%E9%95%BF%E5%BA%A6%E7%BC%96%E7%A0%81/2931940?fr=aladdin" target="_blank">行程长度编码</a> 是一种常用的字符串压缩方法，它将连续的相同字符（重复 2 次或更多次）替换为字符和表示字符计数的数字（行程长度）。例如，用此方法压缩字符串 <code>&quot;aabccc&quot;</code> ，将 <code>&quot;aa&quot;</code> 替换为 <code>&quot;a2&quot;</code> ，<code>&quot;ccc&quot;</code> 替换为` <code>&quot;c3&quot;</code> 。因此压缩后的字符串变为 <code>&quot;a2bc3&quot;</code> 。</p>

<p>注意，本问题中，压缩时没有在单个字符后附加计数 <code>&#39;1&#39;</code> 。</p>

<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code> 。你需要从字符串 <code>s</code> 中删除最多 <code>k</code> 个字符，以使 <code>s</code> 的行程长度编码长度最小。</p>

<p>请你返回删除最多 <code>k</code> 个字符后，<code>s</code> <strong>行程长度编码的最小长度</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;aaabcccd&quot;, k = 2
<strong>输出：</strong>4
<strong>解释：</strong>在不删除任何内容的情况下，压缩后的字符串是 &quot;a3bc3d&quot; ，长度为 6 。最优的方案是删除 &#39;b&#39; 和 &#39;d&#39;，这样一来，压缩后的字符串为 &quot;a3c3&quot; ，长度是 4 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;aabbaa&quot;, k = 2
<strong>输出：</strong>2
<strong>解释：</strong>如果删去两个 &#39;b&#39; 字符，那么压缩后的字符串是长度为 2 的 &quot;a4&quot; 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;aaaaaaaaaaa&quot;, k = 0
<strong>输出：</strong>3
<strong>解释：</strong>由于 k 等于 0 ，不能删去任何字符。压缩后的字符串是 &quot;a11&quot; ，长度为 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
	<li><code>s</code> 仅包含小写英文字母</li>
</ul>

## 解法

### 方法一

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
