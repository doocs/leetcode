# [639. Decode Ways II](https://leetcode.com/problems/decode-ways-ii)

[中文文档](/solution/0600-0699/0639.Decode%20Ways%20II/README.md)

## Description

<p>A message containing letters from <code>A-Z</code> can be <strong>encoded</strong> into numbers using the following mapping:</p>

<pre>
&#39;A&#39; -&gt; &quot;1&quot;
&#39;B&#39; -&gt; &quot;2&quot;
...
&#39;Z&#39; -&gt; &quot;26&quot;
</pre>

<p>To <strong>decode</strong> an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, <code>&quot;11106&quot;</code> can be mapped into:</p>

<ul>
	<li><code>&quot;AAJF&quot;</code> with the grouping <code>(1 1 10 6)</code></li>
	<li><code>&quot;KJF&quot;</code> with the grouping <code>(11 10 6)</code></li>
</ul>

<p>Note that the grouping <code>(1 11 06)</code> is invalid because <code>&quot;06&quot;</code> cannot be mapped into <code>&#39;F&#39;</code> since <code>&quot;6&quot;</code> is different from <code>&quot;06&quot;</code>.</p>

<p><strong>In addition</strong> to the mapping above, an encoded message may contain the <code>&#39;*&#39;</code> character, which can represent any digit from <code>&#39;1&#39;</code> to <code>&#39;9&#39;</code> (<code>&#39;0&#39;</code> is excluded). For example, the encoded message <code>&quot;1*&quot;</code> may represent any of the encoded messages <code>&quot;11&quot;</code>, <code>&quot;12&quot;</code>, <code>&quot;13&quot;</code>, <code>&quot;14&quot;</code>, <code>&quot;15&quot;</code>, <code>&quot;16&quot;</code>, <code>&quot;17&quot;</code>, <code>&quot;18&quot;</code>, or <code>&quot;19&quot;</code>. Decoding <code>&quot;1*&quot;</code> is equivalent to decoding <strong>any</strong> of the encoded messages it can represent.</p>

<p>Given a string <code>s</code> consisting of digits and <code>&#39;*&#39;</code> characters, return <em>the <strong>number</strong> of ways to <strong>decode</strong> it</em>.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;*&quot;
<strong>Output:</strong> 9
<strong>Explanation:</strong> The encoded message can represent any of the encoded messages &quot;1&quot;, &quot;2&quot;, &quot;3&quot;, &quot;4&quot;, &quot;5&quot;, &quot;6&quot;, &quot;7&quot;, &quot;8&quot;, or &quot;9&quot;.
Each of these can be decoded to the strings &quot;A&quot;, &quot;B&quot;, &quot;C&quot;, &quot;D&quot;, &quot;E&quot;, &quot;F&quot;, &quot;G&quot;, &quot;H&quot;, and &quot;I&quot; respectively.
Hence, there are a total of 9 ways to decode &quot;*&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1*&quot;
<strong>Output:</strong> 18
<strong>Explanation:</strong> The encoded message can represent any of the encoded messages &quot;11&quot;, &quot;12&quot;, &quot;13&quot;, &quot;14&quot;, &quot;15&quot;, &quot;16&quot;, &quot;17&quot;, &quot;18&quot;, or &quot;19&quot;.
Each of these encoded messages have 2 ways to be decoded (e.g. &quot;11&quot; can be decoded to &quot;AA&quot; or &quot;K&quot;).
Hence, there are a total of 9 * 2 = 18 ways to decode &quot;1*&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;2*&quot;
<strong>Output:</strong> 15
<strong>Explanation:</strong> The encoded message can represent any of the encoded messages &quot;21&quot;, &quot;22&quot;, &quot;23&quot;, &quot;24&quot;, &quot;25&quot;, &quot;26&quot;, &quot;27&quot;, &quot;28&quot;, or &quot;29&quot;.
&quot;21&quot;, &quot;22&quot;, &quot;23&quot;, &quot;24&quot;, &quot;25&quot;, and &quot;26&quot; have 2 ways of being decoded, but &quot;27&quot;, &quot;28&quot;, and &quot;29&quot; only have 1 way.
Hence, there are a total of (6 * 2) + (3 * 1) = 12 + 3 = 15 ways to decode &quot;2*&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is a digit or <code>&#39;*&#39;</code>.</li>
</ul>

## Solutions

It's just that some conditional judgments about `*` have been added to the [91. Decode Ways](/solution/0000-0099/0091.Decode%20Ways/README_EN.md).

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numDecodings(self, s: str) -> int:
        mod = int(1e9 + 7)
        n = len(s)

        # dp[i - 2], dp[i - 1], dp[i]
        a, b, c = 0, 1, 0
        for i in range(1, n + 1):
            # 1 digit
            if s[i - 1] == "*":
                c = 9 * b % mod
            elif s[i - 1] != "0":
                c = b
            else:
                c = 0

            # 2 digits
            if i > 1:
                if s[i - 2] == "*" and s[i - 1] == "*":
                    c = (c + 15 * a) % mod
                elif s[i - 2] == "*":
                    if s[i - 1] > "6":
                        c = (c + a) % mod
                    else:
                        c = (c + 2 * a) % mod
                elif s[i - 1] == "*":
                    if s[i - 2] == "1":
                        c = (c + 9 * a) % mod
                    elif s[i - 2] == "2":
                        c = (c + 6 * a) % mod
                elif (
                    s[i - 2] != "0"
                    and (ord(s[i - 2]) - ord("0")) * 10 + ord(s[i - 1]) - ord("0") <= 26
                ):
                    c = (c + a) % mod

            a, b = b, c

        return c
```

### **Java**

```java
class Solution {

    private static final int MOD = 1000000007;

    public int numDecodings(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();

        // dp[i - 2], dp[i - 1], dp[i]
        long a = 0, b = 1, c = 0;
        for (int i = 1; i <= n; i++) {
            // 1 digit
            if (cs[i - 1] == '*') {
                c = 9 * b % MOD;
            } else if (cs[i - 1] != '0') {
                c = b;
            } else {
                c = 0;
            }

            // 2 digits
            if (i > 1) {
                if (cs[i - 2] == '*' && cs[i - 1] == '*') {
                    c = (c + 15 * a) % MOD;
                } else if (cs[i - 2] == '*') {
                    if (cs[i - 1] > '6') {
                        c = (c + a) % MOD;
                    } else {
                        c = (c + 2 * a) % MOD;
                    }
                } else if (cs[i - 1] == '*') {
                    if (cs[i - 2] == '1') {
                        c = (c + 9 * a) % MOD;
                    } else if (cs[i - 2] == '2') {
                        c = (c + 6 * a) % MOD;
                    }
                } else if (cs[i - 2] != '0' && (cs[i - 2] - '0') * 10 + cs[i - 1] - '0' <= 26) {
                    c = (c + a) % MOD;
                }
            }

            a = b;
            b = c;
        }

        return (int) c;
    }
}
```

### **Go**

```go
const mod int = 1e9 + 7

func numDecodings(s string) int {
	n := len(s)

	// dp[i - 2], dp[i - 1], dp[i]
	a, b, c := 0, 1, 0
	for i := 1; i <= n; i++ {
		// 1 digit
		if s[i-1] == '*' {
			c = 9 * b % mod
		} else if s[i-1] != '0' {
			c = b
		} else {
			c = 0
		}

		// 2 digits
		if i > 1 {
			if s[i-2] == '*' && s[i-1] == '*' {
				c = (c + 15*a) % mod
			} else if s[i-2] == '*' {
				if s[i-1] > '6' {
					c = (c + a) % mod
				} else {
					c = (c + 2*a) % mod
				}
			} else if s[i-1] == '*' {
				if s[i-2] == '1' {
					c = (c + 9*a) % mod
				} else if s[i-2] == '2' {
					c = (c + 6*a) % mod
				}
			} else if s[i-2] != '0' && (s[i-2]-'0')*10+s[i-1]-'0' <= 26 {
				c = (c + a) % mod
			}
		}

		a, b = b, c
	}
	return c
}
```

### **...**

```

```

<!-- tabs:end -->
