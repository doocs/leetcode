# [91. 解码方法](https://leetcode-cn.com/problems/decode-ways)

[English Version](/solution/0000-0099/0091.Decode%20Ways/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>一条包含字母&nbsp;<code>A-Z</code> 的消息通过以下方式进行了编码：</p>

<pre>&#39;A&#39; -&gt; 1
&#39;B&#39; -&gt; 2
...
&#39;Z&#39; -&gt; 26
</pre>

<p>给定一个只包含数字的<strong>非空</strong>字符串，请计算解码方法的总数。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> &quot;12&quot;
<strong>输出:</strong> 2
<strong>解释:</strong>&nbsp;它可以解码为 &quot;AB&quot;（1 2）或者 &quot;L&quot;（12）。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> &quot;226&quot;
<strong>输出:</strong> 3
<strong>解释:</strong>&nbsp;它可以解码为 &quot;BZ&quot; (2 26), &quot;VF&quot; (22 6), 或者 &quot;BBF&quot; (2 2 6) 。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划法。

假设 `dp[i]` 表示字符串 s 的前 i 个字符 `s[1..i]` 的解码方法数。

考虑最后一次解码中使用了 s 中的哪些字符：

- 第一种情况是我们使用了一个字符，即 `s[i]` 进行解码，那么只要 `s[i]≠0`，它就可以被解码成 `A∼I` 中的某个字母。由于剩余的前 `i-1` 个字符的解码方法数为 `dp[i-1]`，所以 `dp[i] = dp[i-1]`。
- 第二种情况是我们使用了两个字符，即 `s[i-1]` 和 `s[i]` 进行编码。与第一种情况类似，`s[i-1]` 不能等于 0，并且 `s[i-1]` 和 `s[i]` 组成的整数必须小于等于 26，这样它们就可以被解码成 `J∼Z` 中的某个字母。由于剩余的前 `i-2` 个字符的解码方法数为 `dp[i-2]`，所以 `dp[i] = dp[i-2]`。

将上面的两种状态转移方程在对应的条件满足时进行累加，即可得到 `dp[i]`的值。在动态规划完成后，最终的答案即为 `dp[n]`。

由于 `dp[i]` 的值仅与 `dp[i-1]` 和 `dp[i-2]` 有关，因此可以不定义 dp 数组，可以仅使用三个变量进行状态转移。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

优化空间：

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

优化空间：

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
