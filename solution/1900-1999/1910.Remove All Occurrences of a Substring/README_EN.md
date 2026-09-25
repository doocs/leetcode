---
comments: true
difficulty: Medium
rating: 1460
source: Biweekly Contest 55 Q2
tags:
    - Stack
    - String
    - Simulation
---

<!-- problem:start -->

# [1910. Remove All Occurrences of a Substring](https://leetcode.com/problems/remove-all-occurrences-of-a-substring)

[中文文档](/solution/1900-1999/1910.Remove%20All%20Occurrences%20of%20a%20Substring/README.md)

## Description

<!-- description:start -->

<p>Given two strings <code>s</code> and <code>part</code>, perform the following operation on <code>s</code> until <strong>all</strong> occurrences of the substring <code>part</code> are removed:</p>

<ul>
	<li>Find the <strong>leftmost</strong> occurrence of the substring <code>part</code> and <strong>remove</strong> it from <code>s</code>.</li>
</ul>

<p>Return <code>s</code><em> after removing all occurrences of </em><code>part</code>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters in a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;daabcbaabcbc&quot;, part = &quot;abc&quot;
<strong>Output:</strong> &quot;dab&quot;
<strong>Explanation</strong>: The following operations are done:
- s = &quot;da<strong><u>abc</u></strong>baabcbc&quot;, remove &quot;abc&quot; starting at index 2, so s = &quot;dabaabcbc&quot;.
- s = &quot;daba<strong><u>abc</u></strong>bc&quot;, remove &quot;abc&quot; starting at index 4, so s = &quot;dababc&quot;.
- s = &quot;dab<strong><u>abc</u></strong>&quot;, remove &quot;abc&quot; starting at index 3, so s = &quot;dab&quot;.
Now s has no occurrences of &quot;abc&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;axxxxyyyyb&quot;, part = &quot;xy&quot;
<strong>Output:</strong> &quot;ab&quot;
<strong>Explanation</strong>: The following operations are done:
- s = &quot;axxx<strong><u>xy</u></strong>yyyb&quot;, remove &quot;xy&quot; starting at index 4 so s = &quot;axxxyyyb&quot;.
- s = &quot;axx<strong><u>xy</u></strong>yyb&quot;, remove &quot;xy&quot; starting at index 3 so s = &quot;axxyyb&quot;.
- s = &quot;ax<strong><u>xy</u></strong>yb&quot;, remove &quot;xy&quot; starting at index 2 so s = &quot;axyb&quot;.
- s = &quot;a<strong><u>xy</u></strong>b&quot;, remove &quot;xy&quot; starting at index 1 so s = &quot;ab&quot;.
Now s has no occurrences of &quot;xy&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>1 &lt;= part.length &lt;= 1000</code></li>
	<li><code>s</code>​​​​​​ and <code>part</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Brute Force

<!-- thinking:start -->

> **Thinking**
>
> Both $s$ and $\textit{part}$ are at most $10^3$ long, so repeatedly locating and erasing one occurrence is acceptable.
>
> Each step replaces the leftmost $\textit{part}$. A later concatenation may recreate $\textit{part}$, so the loop continues until none remain.
>
> Every replacement shortens $s$, so the process terminates and matches the required leftmost-first order.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def removeOccurrences(self, s: str, part: str) -> str:
        while part in s:
            s = s.replace(part, '', 1)
        return s
```

#### Java

```java
class Solution {
    public String removeOccurrences(String s, String part) {
        while (s.contains(part)) {
            s = s.replaceFirst(part, "");
        }
        return s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string removeOccurrences(string s, string part) {
        int m = part.size();
        while (s.find(part) != -1) {
            s = s.erase(s.find(part), m);
        }
        return s;
    }
};
```

#### Go

```go
func removeOccurrences(s string, part string) string {
	for strings.Contains(s, part) {
		s = strings.Replace(s, part, "", 1)
	}
	return s
}
```

#### TypeScript

```ts
function removeOccurrences(s: string, part: string): string {
    while (s.includes(part)) {
        s = s.replace(part, '');
    }
    return s;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Stack

<!-- thinking:start -->

> **Thinking**
>
> Solution 1 rescans the whole string after every deletion. Each deletion shortens $s$ by at least one character, so there can be $O(n)$ rounds and the total time is $O(n^2)$.
>
> While reading left to right, any remaining occurrence of $\textit{part}$ that is leftmost must end at the character just read. An earlier match would already have been removed.
>
> Keep the surviving characters on a stack. After each push, pop the last $m$ characters when they equal $\textit{part}$. One pass performs every leftmost deletion.

<!-- thinking:end -->

Scan $s$ from left to right and store the characters that have not been removed in a string $st$. Append the current character. If $st$ has length at least $m = |\textit{part}|$ and its last $m$ characters are $\textit{part}$, delete those $m$ characters. After the scan, $st$ is the answer.

This matches Solution 1. At every moment $st$ contains no occurrence of $\textit{part}$, so the next match must end at the character just appended, which is the leftmost occurrence in what remains.

The time complexity is $O(n \times m)$ and the space complexity is $O(n)$, where $n$ and $m$ are the lengths of $s$ and $\textit{part}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def removeOccurrences(self, s: str, part: str) -> str:
        m = len(part)
        st = []
        for c in s:
            st.append(c)
            if len(st) >= m and ''.join(st[-m:]) == part:
                del st[-m:]
        return ''.join(st)
```

#### Java

```java
class Solution {
    public String removeOccurrences(String s, String part) {
        int m = part.length();
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            st.append(s.charAt(i));
            if (st.length() >= m && st.substring(st.length() - m).equals(part)) {
                st.setLength(st.length() - m);
            }
        }
        return st.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string removeOccurrences(string s, string part) {
        int m = part.size();
        string st;
        for (char c : s) {
            st.push_back(c);
            if ((int) st.size() >= m && st.compare(st.size() - m, m, part) == 0) {
                st.erase(st.size() - m);
            }
        }
        return st;
    }
};
```

#### Go

```go
func removeOccurrences(s string, part string) string {
	m := len(part)
	st := make([]byte, 0, len(s))
	for i := 0; i < len(s); i++ {
		st = append(st, s[i])
		if len(st) >= m && string(st[len(st)-m:]) == part {
			st = st[:len(st)-m]
		}
	}
	return string(st)
}
```

#### TypeScript

```ts
function removeOccurrences(s: string, part: string): string {
    const m = part.length;
    const st: string[] = [];
    for (const c of s) {
        st.push(c);
        if (st.length >= m && st.slice(-m).join('') === part) {
            st.length -= m;
        }
    }
    return st.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
