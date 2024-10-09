---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0214.Shortest%20Palindrome/README_EN.md
tags:
    - String
    - String Matching
    - Hash Function
    - Rolling Hash
---

<!-- problem:start -->

# [214. Shortest Palindrome](https://leetcode.com/problems/shortest-palindrome)

[中文文档](/solution/0200-0299/0214.Shortest%20Palindrome/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code>. You can convert <code>s</code> to a <span data-keyword="palindrome-string">palindrome</span> by adding characters in front of it.</p>

<p>Return <em>the shortest palindrome you can find by performing this transformation</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s = "aacecaaa"
<strong>Output:</strong> "aaacecaaa"
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s = "abcd"
<strong>Output:</strong> "dcbabcd"
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> consists of lowercase English letters only.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def shortestPalindrome(self, s: str) -> str:
        base = 131
        mod = 10**9 + 7
        n = len(s)
        prefix = suffix = 0
        mul = 1
        idx = 0
        for i, c in enumerate(s):
            prefix = (prefix * base + (ord(c) - ord('a') + 1)) % mod
            suffix = (suffix + (ord(c) - ord('a') + 1) * mul) % mod
            mul = (mul * base) % mod
            if prefix == suffix:
                idx = i + 1
        return s if idx == n else s[idx:][::-1] + s
```

#### Java

```java
class Solution {
    public String shortestPalindrome(String s) {
        int base = 131;
        int mul = 1;
        int mod = (int) 1e9 + 7;
        int prefix = 0, suffix = 0;
        int idx = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int t = s.charAt(i) - 'a' + 1;
            prefix = (int) (((long) prefix * base + t) % mod);
            suffix = (int) ((suffix + (long) t * mul) % mod);
            mul = (int) (((long) mul * base) % mod);
            if (prefix == suffix) {
                idx = i + 1;
            }
        }
        if (idx == n) {
            return s;
        }
        return new StringBuilder(s.substring(idx)).reverse().toString() + s;
    }
}
```

#### C++

```cpp
typedef unsigned long long ull;

class Solution {
public:
    string shortestPalindrome(string s) {
        int base = 131;
        ull mul = 1;
        ull prefix = 0;
        ull suffix = 0;
        int idx = 0, n = s.size();
        for (int i = 0; i < n; ++i) {
            int t = s[i] - 'a' + 1;
            prefix = prefix * base + t;
            suffix = suffix + mul * t;
            mul *= base;
            if (prefix == suffix) idx = i + 1;
        }
        if (idx == n) return s;
        string x = s.substr(idx, n - idx);
        reverse(x.begin(), x.end());
        return x + s;
    }
};
```

#### Go

```go
func shortestPalindrome(s string) string {
	n := len(s)
	base, mod := 131, int(1e9)+7
	prefix, suffix, mul := 0, 0, 1
	idx := 0
	for i, c := range s {
		t := int(c-'a') + 1
		prefix = (prefix*base + t) % mod
		suffix = (suffix + t*mul) % mod
		mul = (mul * base) % mod
		if prefix == suffix {
			idx = i + 1
		}
	}
	if idx == n {
		return s
	}
	x := []byte(s[idx:])
	for i, j := 0, len(x)-1; i < j; i, j = i+1, j-1 {
		x[i], x[j] = x[j], x[i]
	}
	return string(x) + s
}
```

#### Rust

```rust
impl Solution {
    pub fn shortest_palindrome(s: String) -> String {
        let base = 131;
        let (mut idx, mut prefix, mut suffix, mut mul) = (0, 0, 0, 1);
        for (i, c) in s.chars().enumerate() {
            let t = (c as u64) - ('0' as u64) + 1;
            prefix = prefix * base + t;
            suffix = suffix + t * mul;
            mul *= base;
            if prefix == suffix {
                idx = i + 1;
            }
        }
        if idx == s.len() {
            s
        } else {
            let x: String = (&s[idx..]).chars().rev().collect();
            String::from(x + &s)
        }
    }
}
```

#### C#

```cs
public class Solution {
    public string ShortestPalindrome(string s) {
        int baseValue = 131;
        int mul = 1;
        int mod = (int)1e9 + 7;
        int prefix = 0, suffix = 0;
        int idx = 0;
        int n = s.Length;

        for (int i = 0; i < n; ++i) {
            int t = s[i] - 'a' + 1;
            prefix = (int)(((long)prefix * baseValue + t) % mod);
            suffix = (int)((suffix + (long)t * mul) % mod);
            mul = (int)(((long)mul * baseValue) % mod);
            if (prefix == suffix) {
                idx = i + 1;
            }
        }

        if (idx == n) {
            return s;
        }

        return new string(s.Substring(idx).Reverse().ToArray()) + s;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: KMP Algorithm

According to the problem description, we need to reverse the string $s$ to obtain the string $\textit{rev}$, and then find the longest common part of the suffix of the string $\textit{rev}$ and the prefix of the string $s$. We can use the KMP algorithm to concatenate the string $s$ and the string $\textit{rev}$ and find the longest common part of the longest prefix and the longest suffix.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def shortestPalindrome(self, s: str) -> str:
        t = s + "#" + s[::-1] + "$"
        n = len(t)
        next = [0] * n
        next[0] = -1
        i, j = 2, 0
        while i < n:
            if t[i - 1] == t[j]:
                j += 1
                next[i] = j
                i += 1
            elif j:
                j = next[j]
            else:
                next[i] = 0
                i += 1
        return s[::-1][: -next[-1]] + s
```

#### Java

```java
class Solution {
    public String shortestPalindrome(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        char[] t = (s + "#" + rev + "$").toCharArray();
        int n = t.length;
        int[] next = new int[n];
        next[0] = -1;
        for (int i = 2, j = 0; i < n;) {
            if (t[i - 1] == t[j]) {
                next[i++] = ++j;
            } else if (j > 0) {
                j = next[j];
            } else {
                next[i++] = 0;
            }
        }
        return rev.substring(0, s.length() - next[n - 1]) + s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string shortestPalindrome(string s) {
        string t = s + "#" + string(s.rbegin(), s.rend()) + "$";
        int n = t.size();
        int next[n];
        next[0] = -1;
        next[1] = 0;
        for (int i = 2, j = 0; i < n;) {
            if (t[i - 1] == t[j]) {
                next[i++] = ++j;
            } else if (j > 0) {
                j = next[j];
            } else {
                next[i++] = 0;
            }
        }
        return string(s.rbegin(), s.rbegin() + s.size() - next[n - 1]) + s;
    }
};
```

#### Go

```go
func shortestPalindrome(s string) string {
	t := s + "#" + reverse(s) + "$"
	n := len(t)
	next := make([]int, n)
	next[0] = -1
	for i, j := 2, 0; i < n; {
		if t[i-1] == t[j] {
			j++
			next[i] = j
			i++
		} else if j > 0 {
			j = next[j]
		} else {
			next[i] = 0
			i++
		}
	}
	return reverse(s)[:len(s)-next[n-1]] + s
}

func reverse(s string) string {
	t := []byte(s)
	for i, j := 0, len(t)-1; i < j; i, j = i+1, j-1 {
		t[i], t[j] = t[j], t[i]
	}
	return string(t)
}
```

#### TypeScript

```ts
function shortestPalindrome(s: string): string {
    const rev = s.split('').reverse().join('');
    const t = s + '#' + rev + '$';
    const n = t.length;
    const next: number[] = Array(n).fill(0);
    next[0] = -1;
    for (let i = 2, j = 0; i < n; ) {
        if (t[i - 1] === t[j]) {
            next[i++] = ++j;
        } else if (j > 0) {
            j = next[j];
        } else {
            next[i++] = 0;
        }
    }
    return rev.slice(0, -next[n - 1]) + s;
}
```

#### C#

```cs
public class Solution {
    public string ShortestPalindrome(string s) {
        char[] t = (s + "#" + new string(s.Reverse().ToArray()) + "$").ToCharArray();
        int n = t.Length;
        int[] next = new int[n];
        next[0] = -1;
        for (int i = 2, j = 0; i < n;) {
            if (t[i - 1] == t[j]) {
                next[i++] = ++j;
            } else if (j > 0) {
                j = next[j];
            } else {
                next[i++] = 0;
            }
        }
        return new string(s.Substring(next[n - 1]).Reverse().ToArray()).Substring(0, s.Length - next[n - 1]) + s;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
