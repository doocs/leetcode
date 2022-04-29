# [91. 解码方法](https://leetcode.cn/problems/decode-ways)

[English Version](/solution/0000-0099/0091.Decode%20Ways/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一条包含字母&nbsp;<code>A-Z</code> 的消息通过以下映射进行了 <strong>编码</strong> ：</p>

<pre>
'A' -&gt; "1"
'B' -&gt; "2"
...
'Z' -&gt; "26"</pre>

<p>要 <strong>解码</strong> 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，<code>"11106"</code> 可以映射为：</p>

<ul>
	<li><code>"AAJF"</code> ，将消息分组为 <code>(1 1 10 6)</code></li>
	<li><code>"KJF"</code> ，将消息分组为 <code>(11 10 6)</code></li>
</ul>

<p>注意，消息不能分组为&nbsp; <code>(1 11 06)</code> ，因为 <code>"06"</code> 不能映射为 <code>"F"</code> ，这是由于 <code>"6"</code> 和 <code>"06"</code> 在映射中并不等价。</p>

<p>给你一个只含数字的 <strong>非空 </strong>字符串 <code>s</code> ，请计算并返回 <strong>解码</strong> 方法的 <strong>总数</strong> 。</p>

<p>题目数据保证答案肯定是一个 <strong>32 位</strong> 的整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "12"
<strong>输出：</strong>2
<strong>解释：</strong>它可以解码为 "AB"（1 2）或者 "L"（12）。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "226"
<strong>输出：</strong>3
<strong>解释：</strong>它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "0"
<strong>输出：</strong>0
<strong>解释：</strong>没有字符映射到以 0 开头的数字。
含有 0 的有效映射是 'J' -&gt; "10" 和 'T'-&gt; "20" 。
由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 只包含数字，并且可能包含前导零。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划法。

假设 `dp[i]` 表示字符串 s 的前 i 个字符 `s[1..i]` 的解码方法数。

考虑最后一次解码中使用了 s 中的哪些字符：

-   第一种情况是我们使用了一个字符，即 `s[i]` 进行解码，那么只要 `s[i]≠0`，它就可以被解码成 `A∼I` 中的某个字母。由于剩余的前 `i-1` 个字符的解码方法数为 `dp[i-1]`，所以 `dp[i] = dp[i-1]`。
-   第二种情况是我们使用了两个字符，即 `s[i-1]` 和 `s[i]` 进行编码。与第一种情况类似，`s[i-1]` 不能等于 0，并且 `s[i-1]` 和 `s[i]` 组成的整数必须小于等于 26，这样它们就可以被解码成 `J∼Z` 中的某个字母。由于剩余的前 `i-2` 个字符的解码方法数为 `dp[i-2]`，所以 `dp[i] = dp[i-2]`。

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

### **C++**

```cpp
class Solution {
public:
    int numDecodings(string s) {
        int n = s.size();
        vector<int> dp(n + 1);
        dp[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s[i - 1] != '0') {
                dp[i] += dp[i - 1];
            }
            if (i > 1 && s[i - 2] != '0') {
                if ((s[i - 2] - '0') * 10 + s[i - 1] - '0' <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[n];
    }
};
```

### **Go**

```go
func numDecodings(s string) int {
	n := len(s)
	dp := make([]int, n+1)
	dp[0] = 1
	for i := 1; i <= n; i++ {
		if s[i-1] != '0' {
			dp[i] += dp[i-1]
		}
		if i > 1 && s[i-2] != '0' {
			if (s[i-2]-'0')*10+(s[i-1]-'0') <= 26 {
				dp[i] += dp[i-2]
			}
		}
	}
	return dp[n]
}
```

### **C#**

```cs
public class Solution {
    public int NumDecodings(string s) {
        if (s.Length == 0) return 0;

        var f0 = 1;
        var f1 = 1;
        var f2 = 1;
        for (var i = 0; i < s.Length; ++i)
        {
            f0 = f1;
            f1 = f2;
            f2 = 0;
            var two = i > 0 ? int.Parse(string.Format("{0}{1}", s[i - 1], s[i])) : 0;
            if (two >= 10 && two <= 26)
            {
               f2 += f0;
            }
            if (s[i] != '0')
            {
                f2 += f1;
            }
        }
        return f2;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
