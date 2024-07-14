---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2486.Append%20Characters%20to%20String%20to%20Make%20Subsequence/README_EN.md
rating: 1362
source: Weekly Contest 321 Q2
tags:
    - Greedy
    - Two Pointers
    - String
---

<!-- problem:start -->

# [2486. Append Characters to String to Make Subsequence](https://leetcode.com/problems/append-characters-to-string-to-make-subsequence)

[中文文档](/solution/2400-2499/2486.Append%20Characters%20to%20String%20to%20Make%20Subsequence/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We define two pointers $i$ and $j$, pointing to the first characters of strings $s$ and $t$ respectively. We iterate through string $s$, if $s[i] = t[j]$, then we move $j$ one step forward. Finally, we return $n - j$, where $n$ is the length of string $t$.

The time complexity is $O(m + n)$, where $m$ and $n$ are the lengths of strings $s$ and $t$ respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def appendCharacters(self, s: str, t: str) -> int:
        n, j = len(t), 0
        for c in s:
            if j < n and c == t[j]:
                j += 1
        return n - j
```

#### Java

```java
class Solution {
    public int appendCharacters(String s, String t) {
        int n = t.length(), j = 0;
        for (int i = 0; i < s.length() && j < n; ++i) {
            if (s.charAt(i) == t.charAt(j)) {
                ++j;
            }
        }
        return n - j;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int appendCharacters(string s, string t) {
        int n = t.length(), j = 0;
        for (int i = 0; i < s.size() && j < n; ++i) {
            if (s[i] == t[j]) {
                ++j;
            }
        }
        return n - j;
    }
};
```

#### Go

```go
func appendCharacters(s string, t string) int {
	n, j := len(t), 0
	for _, c := range s {
		if j < n && byte(c) == t[j] {
			j++
		}
	}
	return n - j
}
```

#### TypeScript

```ts
function appendCharacters(s: string, t: string): number {
    let j = 0;
    for (const c of s) {
        if (c === t[j]) {
            ++j;
        }
    }
    return t.length - j;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
