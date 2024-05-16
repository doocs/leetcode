---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2663.Lexicographically%20Smallest%20Beautiful%20String/README_EN.md
rating: 2415
source: Weekly Contest 343 Q4
tags:
    - Greedy
    - String
---

<!-- problem:start -->

# [2663. Lexicographically Smallest Beautiful String](https://leetcode.com/problems/lexicographically-smallest-beautiful-string)

[中文文档](/solution/2600-2699/2663.Lexicographically%20Smallest%20Beautiful%20String/README.md)

## Description

<p>A string is <strong>beautiful</strong> if:</p>

<ul>
	<li>It consists of the first <code>k</code> letters of the English lowercase alphabet.</li>
	<li>It does not contain any substring of length <code>2</code> or more which is a palindrome.</li>
</ul>

<p>You are given a beautiful string <code>s</code> of length <code>n</code> and a positive integer <code>k</code>.</p>

<p>Return <em>the lexicographically smallest string of length </em><code>n</code><em>, which is larger than </em><code>s</code><em> and is <strong>beautiful</strong></em>. If there is no such string, return an empty string.</p>

<p>A string <code>a</code> is lexicographically larger than a string <code>b</code> (of the same length) if in the first position where <code>a</code> and <code>b</code> differ, <code>a</code> has a character strictly larger than the corresponding character in <code>b</code>.</p>

<ul>
	<li>For example, <code>&quot;abcd&quot;</code> is lexicographically larger than <code>&quot;abcc&quot;</code> because the first position they differ is at the fourth character, and <code>d</code> is greater than <code>c</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcz&quot;, k = 26
<strong>Output:</strong> &quot;abda&quot;
<strong>Explanation:</strong> The string &quot;abda&quot; is beautiful and lexicographically larger than the string &quot;abcz&quot;.
It can be proven that there is no string that is lexicographically larger than the string &quot;abcz&quot;, beautiful, and lexicographically smaller than the string &quot;abda&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;dc&quot;, k = 4
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> It can be proven that there is no string that is lexicographically larger than the string &quot;dc&quot; and is beautiful.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>4 &lt;= k &lt;= 26</code></li>
	<li><code>s</code> is a beautiful string.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

We can find that a palindrome string of length $2$ must have two adjacent characters equal; and a palindrome string of length $3$ must have two characters at the beginning and end equal. Therefore, a beautiful string does not contain any palindrome substring of length $2$ or longer, which means that each character in the string is different from its previous two adjacent characters.

We can greedily search backwards from the last index of the string, find an index $i$ such that the character at index $i$ can be replaced by a slightly larger character, while ensuring that it is different from its two previous adjacent characters.

-   If such an index $i$ is found, then we replace $s[i]$ with $c$, and replace the characters from $s[i+1]$ to $s[n-1]$ with the characters in the first $k$ characters of the alphabet in the order of the minimum dictionary that are not the same as the previous two adjacent characters. After the replacement is completed, we obtain a beautiful string that is the smallest in the dictionary and greater than $s$.
-   If such an index $i$ cannot be found, then we cannot construct a beautiful string greater than $s$ in dictionary order, so return an empty string.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def smallestBeautifulString(self, s: str, k: int) -> str:
        n = len(s)
        cs = list(s)
        for i in range(n - 1, -1, -1):
            p = ord(cs[i]) - ord('a') + 1
            for j in range(p, k):
                c = chr(ord('a') + j)
                if (i > 0 and cs[i - 1] == c) or (i > 1 and cs[i - 2] == c):
                    continue
                cs[i] = c
                for l in range(i + 1, n):
                    for m in range(k):
                        c = chr(ord('a') + m)
                        if (l > 0 and cs[l - 1] == c) or (l > 1 and cs[l - 2] == c):
                            continue
                        cs[l] = c
                        break
                return ''.join(cs)
        return ''
```

```java
class Solution {
    public String smallestBeautifulString(String s, int k) {
        int n = s.length();
        char[] cs = s.toCharArray();
        for (int i = n - 1; i >= 0; --i) {
            int p = cs[i] - 'a' + 1;
            for (int j = p; j < k; ++j) {
                char c = (char) ('a' + j);
                if ((i > 0 && cs[i - 1] == c) || (i > 1 && cs[i - 2] == c)) {
                    continue;
                }
                cs[i] = c;
                for (int l = i + 1; l < n; ++l) {
                    for (int m = 0; m < k; ++m) {
                        c = (char) ('a' + m);
                        if ((l > 0 && cs[l - 1] == c) || (l > 1 && cs[l - 2] == c)) {
                            continue;
                        }
                        cs[l] = c;
                        break;
                    }
                }
                return String.valueOf(cs);
            }
        }
        return "";
    }
}
```

```cpp
class Solution {
public:
    string smallestBeautifulString(string s, int k) {
        int n = s.size();
        for (int i = n - 1; i >= 0; --i) {
            int p = s[i] - 'a' + 1;
            for (int j = p; j < k; ++j) {
                char c = (char) ('a' + j);
                if ((i > 0 && s[i - 1] == c) || (i > 1 && s[i - 2] == c)) {
                    continue;
                }
                s[i] = c;
                for (int l = i + 1; l < n; ++l) {
                    for (int m = 0; m < k; ++m) {
                        c = (char) ('a' + m);
                        if ((l > 0 && s[l - 1] == c) || (l > 1 && s[l - 2] == c)) {
                            continue;
                        }
                        s[l] = c;
                        break;
                    }
                }
                return s;
            }
        }
        return "";
    }
};
```

```go
func smallestBeautifulString(s string, k int) string {
	cs := []byte(s)
	n := len(cs)
	for i := n - 1; i >= 0; i-- {
		p := int(cs[i] - 'a' + 1)
		for j := p; j < k; j++ {
			c := byte('a' + j)
			if (i > 0 && cs[i-1] == c) || (i > 1 && cs[i-2] == c) {
				continue
			}
			cs[i] = c
			for l := i + 1; l < n; l++ {
				for m := 0; m < k; m++ {
					c = byte('a' + m)
					if (l > 0 && cs[l-1] == c) || (l > 1 && cs[l-2] == c) {
						continue
					}
					cs[l] = c
					break
				}
			}
			return string(cs)
		}
	}
	return ""
}
```

```ts
function smallestBeautifulString(s: string, k: number): string {
    const cs: string[] = s.split('');
    const n = cs.length;
    for (let i = n - 1; i >= 0; --i) {
        const p = cs[i].charCodeAt(0) - 97 + 1;
        for (let j = p; j < k; ++j) {
            let c = String.fromCharCode(j + 97);
            if ((i > 0 && cs[i - 1] === c) || (i > 1 && cs[i - 2] === c)) {
                continue;
            }
            cs[i] = c;
            for (let l = i + 1; l < n; ++l) {
                for (let m = 0; m < k; ++m) {
                    c = String.fromCharCode(m + 97);
                    if ((l > 0 && cs[l - 1] === c) || (l > 1 && cs[l - 2] === c)) {
                        continue;
                    }
                    cs[l] = c;
                    break;
                }
            }
            return cs.join('');
        }
    }
    return '';
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
