---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2486.Append%20Characters%20to%20String%20to%20Make%20Subsequence/README_EN.md
rating: 1362
tags:
    - Greedy
    - Two Pointers
    - String
---

# [2486. Append Characters to String to Make Subsequence](https://leetcode.com/problems/append-characters-to-string-to-make-subsequence)

[中文文档](/solution/2400-2499/2486.Append%20Characters%20to%20String%20to%20Make%20Subsequence/README.md)

## Description

<p>You are given two strings <code>s</code> and <code>t</code> consisting of only lowercase English letters.</p>

<p>Return <em>the minimum number of characters that need to be appended to the end of </em><code>s</code><em> so that </em><code>t</code><em> becomes a <strong>subsequence</strong> of </em><code>s</code>.</p>

<p>A <strong>subsequence</strong> is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;coaching&quot;, t = &quot;coding&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> Append the characters &quot;ding&quot; to the end of s so that s = &quot;coachingding&quot;.
Now, t is a subsequence of s (&quot;<u><strong>co</strong></u>aching<u><strong>ding</strong></u>&quot;).
It can be shown that appending any 3 characters to the end of s will never make t a subsequence.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcde&quot;, t = &quot;a&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> t is already a subsequence of s (&quot;<u><strong>a</strong></u>bcde&quot;).
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;z&quot;, t = &quot;abcde&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> Append the characters &quot;abcde&quot; to the end of s so that s = &quot;zabcde&quot;.
Now, t is a subsequence of s (&quot;z<u><strong>abcde</strong></u>&quot;).
It can be shown that appending any 4 characters to the end of s will never make t a subsequence.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> and <code>t</code> consist only of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Two Pointers

We define two pointers $i$ and $j$, pointing to the first characters of strings $s$ and $t$ respectively. We traverse string $t$, when $s[i] \neq t[j]$, we move pointer $i$ forward until $s[i] = t[j]$ or $i$ reaches the end of string $s$. If $i$ reaches the end of string $s$, it means that the character $t[j]$ in $t$ cannot find the corresponding character in $s$, so we return the remaining number of characters in $t$. Otherwise, we move both pointers $i$ and $j$ forward and continue to traverse string $t$.

The time complexity is $O(m + n)$, and the space complexity is $O(1)$. Where $m$ and $n$ are the lengths of strings $s$ and $t$ respectively.

<!-- tabs:start -->

```python
class Solution:
    def appendCharacters(self, s: str, t: str) -> int:
        i, m = 0, len(s)
        for j, c in enumerate(t):
            while i < m and s[i] != c:
                i += 1
            if i == m:
                return len(t) - j
            i += 1
        return 0
```

```java
class Solution {
    public int appendCharacters(String s, String t) {
        int m = s.length(), n = t.length();
        for (int i = 0, j = 0; j < n; ++j) {
            while (i < m && s.charAt(i) != t.charAt(j)) {
                ++i;
            }
            if (i++ == m) {
                return n - j;
            }
        }
        return 0;
    }
}
```

```cpp
class Solution {
public:
    int appendCharacters(string s, string t) {
        int m = s.size(), n = t.size();
        for (int i = 0, j = 0; j < n; ++j) {
            while (i < m && s[i] != t[j]) {
                ++i;
            }
            if (i++ == m) {
                return n - j;
            }
        }
        return 0;
    }
};
```

```go
func appendCharacters(s string, t string) int {
	m, n := len(s), len(t)
	for i, j := 0, 0; j < n; i, j = i+1, j+1 {
		for i < m && s[i] != t[j] {
			i++
		}
		if i == m {
			return n - j
		}
	}
	return 0
}
```

```ts
function appendCharacters(s: string, t: string): number {
    const [m, n] = [s.length, t.length];
    for (let i = 0, j = 0; j < n; ++j) {
        while (i < m && s[i] !== t[j]) {
            ++i;
        }
        if (i === m) {
            return n - j;
        }
        ++i;
    }
    return 0;
}
```

<!-- tabs:end -->

<!-- end -->
