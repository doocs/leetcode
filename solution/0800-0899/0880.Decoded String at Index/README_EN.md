---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0880.Decoded%20String%20at%20Index/README_EN.md
tags:
    - Stack
    - String
---

<!-- problem:start -->

# [880. Decoded String at Index](https://leetcode.com/problems/decoded-string-at-index)

[中文文档](/solution/0800-0899/0880.Decoded%20String%20at%20Index/README.md)

## Description

<!-- description:start -->

<p>You are given an encoded string <code>s</code>. To decode the string to a tape, the encoded string is read one character at a time and the following steps are taken:</p>

<ul>
	<li>If the character read is a letter, that letter is written onto the tape.</li>
	<li>If the character read is a digit <code>d</code>, the entire current tape is repeatedly written <code>d - 1</code> more times in total.</li>
</ul>

<p>Given an integer <code>k</code>, return <em>the </em><code>k<sup>th</sup></code><em> letter (<strong>1-indexed)</strong> in the decoded string</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leet2code3&quot;, k = 10
<strong>Output:</strong> &quot;o&quot;
<strong>Explanation:</strong> The decoded string is &quot;leetleetcodeleetleetcodeleetleetcode&quot;.
The 10<sup>th</sup> letter in the string is &quot;o&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ha22&quot;, k = 5
<strong>Output:</strong> &quot;h&quot;
<strong>Explanation:</strong> The decoded string is &quot;hahahaha&quot;.
The 5<sup>th</sup> letter is &quot;h&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a2345678999999999999999&quot;, k = 1
<strong>Output:</strong> &quot;a&quot;
<strong>Explanation:</strong> The decoded string is &quot;a&quot; repeated 8301530446056247680 times.
The 1<sup>st</sup> letter is &quot;a&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of lowercase English letters and digits <code>2</code> through <code>9</code>.</li>
	<li><code>s</code> starts with a letter.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li>It is guaranteed that <code>k</code> is less than or equal to the length of the decoded string.</li>
	<li>The decoded string is guaranteed to have less than <code>2<sup>63</sup></code> letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Reverse Thinking

We can first calculate the total length $m$ of the decoded string, then traverse the string from back to front. Each time, we update $k$ to be $k \bmod m$, until $k$ is $0$ and the current character is a letter, then we return the current character. Otherwise, if the current character is a number, we divide $m$ by this number. If the current character is a letter, we subtract $1$ from $m$.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def decodeAtIndex(self, s: str, k: int) -> str:
        m = 0
        for c in s:
            if c.isdigit():
                m *= int(c)
            else:
                m += 1
        for c in s[::-1]:
            k %= m
            if k == 0 and c.isalpha():
                return c
            if c.isdigit():
                m //= int(c)
            else:
                m -= 1
```

#### Java

```java
class Solution {
    public String decodeAtIndex(String s, int k) {
        long m = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (Character.isDigit(s.charAt(i))) {
                m *= (s.charAt(i) - '0');
            } else {
                ++m;
            }
        }
        for (int i = s.length() - 1;; --i) {
            k %= m;
            if (k == 0 && !Character.isDigit(s.charAt(i))) {
                return String.valueOf(s.charAt(i));
            }
            if (Character.isDigit(s.charAt(i))) {
                m /= (s.charAt(i) - '0');
            } else {
                --m;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    string decodeAtIndex(string s, int k) {
        long long m = 0;
        for (char& c : s) {
            if (isdigit(c)) {
                m *= (c - '0');
            } else {
                ++m;
            }
        }
        for (int i = s.size() - 1;; --i) {
            k %= m;
            if (k == 0 && isalpha(s[i])) {
                return string(1, s[i]);
            }
            if (isdigit(s[i])) {
                m /= (s[i] - '0');
            } else {
                --m;
            }
        }
    }
};
```

#### Go

```go
func decodeAtIndex(s string, k int) string {
	m := 0
	for _, c := range s {
		if c >= '0' && c <= '9' {
			m *= int(c - '0')
		} else {
			m++
		}
	}
	for i := len(s) - 1; ; i-- {
		k %= m
		if k == 0 && s[i] >= 'a' && s[i] <= 'z' {
			return string(s[i])
		}
		if s[i] >= '0' && s[i] <= '9' {
			m /= int(s[i] - '0')
		} else {
			m--
		}
	}
}
```

#### TypeScript

```ts
function decodeAtIndex(s: string, k: number): string {
    let m = 0n;
    for (const c of s) {
        if (c >= '1' && c <= '9') {
            m *= BigInt(c);
        } else {
            ++m;
        }
    }
    for (let i = s.length - 1; ; --i) {
        if (k >= m) {
            k %= Number(m);
        }
        if (k === 0 && s[i] >= 'a' && s[i] <= 'z') {
            return s[i];
        }
        if (s[i] >= '1' && s[i] <= '9') {
            m = (m / BigInt(s[i])) | 0n;
        } else {
            --m;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
