---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0091.Decode%20Ways/README_EN.md
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [91. Decode Ways](https://leetcode.com/problems/decode-ways)

[中文文档](/solution/0000-0099/0091.Decode%20Ways/README.md)

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

<p>Given a string <code>s</code> containing only digits, return <em>the <strong>number</strong> of ways to <strong>decode</strong> it</em>.</p>

<p>The test cases are generated so that the answer fits in a <strong>32-bit</strong> integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;12&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> &quot;12&quot; could be decoded as &quot;AB&quot; (1 2) or &quot;L&quot; (12).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;226&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> &quot;226&quot; could be decoded as &quot;BZ&quot; (2 26), &quot;VF&quot; (22 6), or &quot;BBF&quot; (2 2 6).
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;06&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> &quot;06&quot; cannot be mapped to &quot;F&quot; because of the leading zero (&quot;6&quot; is different from &quot;06&quot;).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> contains only digits and may contain leading zero(s).</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i]$ to represent the number of decoding methods for the first $i$ characters of the string. Initially, $f[0]=1$, and the rest $f[i]=0$.

Consider how $f[i]$ transitions.

-   If the $i$th character (i.e., $s[i-1]$) forms a code on its own, it corresponds to one decoding method, i.e., $f[i]=f[i-1]$. The premise is $s[i-1] \neq 0$.
-   If the string formed by the $i-1$th character and the $i$th character is within the range $[1,26]$, then they can be treated as a whole, corresponding to one decoding method, i.e., $f[i] = f[i] + f[i-2]$. The premise is $s[i-2] \neq 0$, and $s[i-2]s[i-1]$ is within the range $[1,26]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string.

<!-- tabs:start -->

```python
class Solution:
    def numDecodings(self, s: str) -> int:
        n = len(s)
        f = [1] + [0] * n
        for i, c in enumerate(s, 1):
            if c != "0":
                f[i] = f[i - 1]
            if i > 1 and s[i - 2] != "0" and int(s[i - 2 : i]) <= 26:
                f[i] += f[i - 2]
        return f[n]
```

```java
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                f[i] = f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && Integer.valueOf(s.substring(i - 2, i)) <= 26) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }
}
```

```cpp
class Solution {
public:
    int numDecodings(string s) {
        int n = s.size();
        int f[n + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s[i - 1] != '0') {
                f[i] = f[i - 1];
            }
            if (i > 1 && (s[i - 2] == '1' || s[i - 2] == '2' && s[i - 1] <= '6')) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }
};
```

```go
func numDecodings(s string) int {
	n := len(s)
	f := make([]int, n+1)
	f[0] = 1
	for i := 1; i <= n; i++ {
		if s[i-1] != '0' {
			f[i] = f[i-1]
		}
		if i > 1 && (s[i-2] == '1' || (s[i-2] == '2' && s[i-1] <= '6')) {
			f[i] += f[i-2]
		}
	}
	return f[n]
}
```

```ts
function numDecodings(s: string): number {
    const n = s.length;
    const f: number[] = new Array(n + 1).fill(0);
    f[0] = 1;
    for (let i = 1; i <= n; ++i) {
        if (s[i - 1] !== '0') {
            f[i] = f[i - 1];
        }
        if (i > 1 && (s[i - 2] === '1' || (s[i - 2] === '2' && s[i - 1] <= '6'))) {
            f[i] += f[i - 2];
        }
    }
    return f[n];
}
```

```cs
public class Solution {
    public int NumDecodings(string s) {
        int n = s.Length;
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s[i - 1] != '0') {
                f[i] = f[i - 1];
            }
            if (i > 1 && (s[i - 2] == '1' || (s[i - 2] == '2' && s[i - 1] <= '6'))) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }
}
```

<!-- tabs:end -->

We notice that the state $f[i]$ is only related to the states $f[i-1]$ and $f[i-2]$, and is irrelevant to other states. Therefore, we can use two variables to replace these two states, reducing the original space complexity from $O(n)$ to $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def numDecodings(self, s: str) -> int:
        f, g = 0, 1
        for i, c in enumerate(s, 1):
            h = g if c != "0" else 0
            if i > 1 and s[i - 2] != "0" and int(s[i - 2 : i]) <= 26:
                h += f
            f, g = g, h
        return g
```

```java
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int f = 0, g = 1;
        for (int i = 1; i <= n; ++i) {
            int h = s.charAt(i - 1) != '0' ? g : 0;
            if (i > 1 && s.charAt(i - 2) != '0' && Integer.valueOf(s.substring(i - 2, i)) <= 26) {
                h += f;
            }
            f = g;
            g = h;
        }
        return g;
    }
}
```

```cpp
class Solution {
public:
    int numDecodings(string s) {
        int n = s.size();
        int f = 0, g = 1;
        for (int i = 1; i <= n; ++i) {
            int h = s[i - 1] != '0' ? g : 0;
            if (i > 1 && (s[i - 2] == '1' || (s[i - 2] == '2' && s[i - 1] <= '6'))) {
                h += f;
            }
            f = g;
            g = h;
        }
        return g;
    }
};
```

```go
func numDecodings(s string) int {
	n := len(s)
	f, g := 0, 1
	for i := 1; i <= n; i++ {
		h := 0
		if s[i-1] != '0' {
			h = g
		}
		if i > 1 && (s[i-2] == '1' || (s[i-2] == '2' && s[i-1] <= '6')) {
			h += f
		}
		f, g = g, h
	}
	return g
}
```

```ts
function numDecodings(s: string): number {
    const n = s.length;
    let [f, g] = [0, 1];
    for (let i = 1; i <= n; ++i) {
        let h = s[i - 1] !== '0' ? g : 0;
        if (i > 1 && (s[i - 2] === '1' || (s[i - 2] === '2' && s[i - 1] <= '6'))) {
            h += f;
        }
        [f, g] = [g, h];
    }
    return g;
}
```

```cs
public class Solution {
    public int NumDecodings(string s) {
        int n = s.Length;
        int f = 0, g = 1;
        for (int i = 1; i <= n; ++i) {
            int h = s[i - 1] != '0' ? g : 0;
            if (i > 1 && (s[i - 2] == '1' || (s[i - 2] == '2' && s[i - 1] <= '6'))) {
                h += f;
            }
            f = g;
            g = h;
        }
        return g;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
