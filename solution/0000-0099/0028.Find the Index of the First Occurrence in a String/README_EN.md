---
comments: true
difficulty: Easy
tags:
    - Two Pointers
    - String
    - String Matching
    - KMP
    - Boyer–Moore
    - Extended KMP
---

<!-- problem:start -->

# [28. Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string)

[中文文档](/solution/0000-0099/0028.Find%20the%20Index%20of%20the%20First%20Occurrence%20in%20a%20String/README.md)

## Description

<!-- description:start -->

<p>Given two strings <code>needle</code> and <code>haystack</code>, return the index of the first occurrence of <code>needle</code> in <code>haystack</code>, or <code>-1</code> if <code>needle</code> is not part of <code>haystack</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> haystack = &quot;sadbutsad&quot;, needle = &quot;sad&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> &quot;sad&quot; occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> haystack = &quot;leetcode&quot;, needle = &quot;leeto&quot;
<strong>Output:</strong> -1
<strong>Explanation:</strong> &quot;leeto&quot; did not occur in &quot;leetcode&quot;, so we return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= haystack.length, needle.length &lt;= 10<sup>4</sup></code></li>
	<li><code>haystack</code> and <code>needle</code> consist of only lowercase English characters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal

<!-- thinking:start -->

> **Thinking**
>
> The first idea is to try every start $i$ in $haystack$ and test whether the length-$m$ slice equals $needle$. With $n,m \le 10^4$, the worst case $O((n-m)m)$ is about $10^8$ and usually passes.
>
> The bottleneck is paying $m$ character comparisons at many near-matches. Smarter matchers amortize toward linear time, but this size does not force them yet.
>
> We only need the first hit; a mismatch just moves on to the next $i$.
>
> So scan $i$ from $0$ to $n-m$, return $i$ on equality, else $-1$. Extra space is $O(1)$.

<!-- thinking:end -->

We compare the string `needle` with each character of the string `haystack` as the starting point. If we find a matching index, we return it directly.

Assuming the length of the string `haystack` is $n$ and the length of the string `needle` is $m$, the time complexity is $O((n-m) \times m)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        n, m = len(haystack), len(needle)
        for i in range(n - m + 1):
            if haystack[i : i + m] == needle:
                return i
        return -1
```

#### Java

```java
class Solution {
    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }

        int len1 = haystack.length();
        int len2 = needle.length();
        int p = 0;
        int q = 0;
        while (p < len1) {
            if (haystack.charAt(p) == needle.charAt(q)) {
                if (len2 == 1) {
                    return p;
                }
                ++p;
                ++q;
            } else {
                p -= q - 1;
                q = 0;
            }

            if (q == len2) {
                return p - q;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int strStr(string haystack, string needle) {
        int n = haystack.size(), m = needle.size();
        for (int i = 0; i + m <= n; ++i) {
            if (haystack.substr(i, m) == needle) {
                return i;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func strStr(haystack string, needle string) int {
	n, m := len(haystack), len(needle)
	for i := 0; i <= n-m; i++ {
		if haystack[i:i+m] == needle {
			return i
		}
	}
	return -1
}
```

#### TypeScript

```ts
function strStr(haystack: string, needle: string): number {
    const m = haystack.length;
    const n = needle.length;
    for (let i = 0; i <= m - n; i++) {
        let isEqual = true;
        for (let j = 0; j < n; j++) {
            if (haystack[i + j] !== needle[j]) {
                isEqual = false;
                break;
            }
        }
        if (isEqual) {
            return i;
        }
    }
    return -1;
}
```

#### Rust

```rust
impl Solution {
    pub fn str_str(haystack: String, needle: String) -> i32 {
        let n = haystack.len();
        let m = needle.len();
        if m > n {
            return -1;
        }
        for i in 0..=n - m {
            if &haystack[i..i + m] == needle {
                return i as i32;
            }
        }
        -1
    }
}
```

#### JavaScript

```js
/**
 * @param {string} haystack
 * @param {string} needle
 * @return {number}
 */
var strStr = function (haystack, needle) {
    const slen = haystack.length;
    const plen = needle.length;
    if (slen == plen) {
        return haystack == needle ? 0 : -1;
    }
    for (let i = 0; i <= slen - plen; i++) {
        let j;
        for (j = 0; j < plen; j++) {
            if (haystack[i + j] != needle[j]) {
                break;
            }
        }
        if (j == plen) return i;
    }
    return -1;
};
```

#### C#

```cs
public class Solution {
    public int StrStr(string haystack, string needle) {
        for (var i = 0; i < haystack.Length - needle.Length + 1; ++i) {
            var j = 0;
            for (; j < needle.Length; ++j) {
                if (haystack[i + j] != needle[j]) break;
            }
            if (j == needle.Length) return i;
        }
        return -1;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $haystack
     * @param String $needle
     * @return Integer
     */
    function strStr($haystack, $needle) {
        $strNew = str_replace($needle, '+', $haystack);
        $cnt = substr_count($strNew, '+');
        if ($cnt > 0) {
            for ($i = 0; $i < strlen($strNew); $i++) {
                if ($strNew[$i] == '+') {
                    return $i;
                }
            }
        } else {
            return -1;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Rabin-Karp String Matching Algorithm

<!-- thinking:start -->

> **Thinking**
>
> Solution 1 may compare all $m$ characters at every start, approaching $O(nm)$. We want the next window to reuse work from the last one.
>
> A fixed-length substring can be rolling-hashed: add the incoming character, drop the outgoing one, update in $O(1)$. When the window hash equals $needle$'s hash, compare the raw strings to rule out a collision.
>
> One scan of $haystack$ then costs expected $O(n+m)$.

<!-- thinking:end -->

The [Rabin-Karp algorithm](https://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_algorithm) essentially uses a sliding window combined with a hash function to compare the hashes of fixed-length strings, which can reduce the time complexity of comparing whether two strings are the same to $O(1)$.

Assuming the length of the string `haystack` is $n$ and the length of the string `needle` is $m$, the time complexity is $O(n+m)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        n, m = len(haystack), len(needle)
        mod = (1 << 31) - 1
        target = sha = 0
        multi = 1
        for i in range(m):
            target = (target * 256 + ord(needle[i])) % mod
        for _ in range(1, m):
            multi = multi * 256 % mod
        left = 0
        for right in range(n):
            sha = (sha * 256 + ord(haystack[right])) % mod
            if right - left + 1 < m:
                continue
            if sha == target and haystack[left : right + 1] == needle:
                return left
            sha = (sha - ord(haystack[left]) * multi % mod + mod) % mod
            left += 1
        return -1
```

#### Java

```java
class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        final int mod = (1 << 31) - 1;
        long target = 0, sha = 0, multi = 1;
        for (int i = 0; i < m; ++i) {
            target = (target * 256 + needle.charAt(i)) % mod;
        }
        for (int i = 1; i < m; ++i) {
            multi = multi * 256 % mod;
        }
        int left = 0;
        for (int right = 0; right < n; ++right) {
            sha = (sha * 256 + haystack.charAt(right)) % mod;
            if (right - left + 1 < m) {
                continue;
            }
            if (sha == target && haystack.substring(left, right + 1).equals(needle)) {
                return left;
            }
            sha = (sha - haystack.charAt(left) * multi % mod + mod) % mod;
            ++left;
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int strStr(string haystack, string needle) {
        int n = haystack.size(), m = needle.size();
        const int mod = (1 << 31) - 1;
        long long target = 0, sha = 0, multi = 1;
        for (int i = 0; i < m; ++i) {
            target = (target * 256 + needle[i]) % mod;
        }
        for (int i = 1; i < m; ++i) {
            multi = multi * 256 % mod;
        }
        int left = 0;
        for (int right = 0; right < n; ++right) {
            sha = (sha * 256 + haystack[right]) % mod;
            if (right - left + 1 < m) {
                continue;
            }
            if (sha == target && haystack.substr(left, m) == needle) {
                return left;
            }
            sha = (sha - haystack[left] * multi % mod + mod) % mod;
            ++left;
        }
        return -1;
    }
};
```

#### Go

```go
func strStr(haystack string, needle string) int {
	n, m := len(haystack), len(needle)
	sha, target, left, right, mod := 0, 0, 0, 0, 1<<31-1
	multi := 1
	for i := 0; i < m; i++ {
		target = (target*256%mod + int(needle[i])) % mod
	}
	for i := 1; i < m; i++ {
		multi = multi * 256 % mod
	}

	for ; right < n; right++ {
		sha = (sha*256%mod + int(haystack[right])) % mod
		if right-left+1 < m {
			continue
		}
		// 此时 left~right 的长度已经为 needle 的长度 m 了，只需要比对 sha 值与 target 是否一致即可
		// 为避免 hash 冲突，还需要确保 haystack[left:right+1] 与 needle 相同
		if sha == target && haystack[left:right+1] == needle {
			return left
		}
		// 未匹配成功，left 右移一位
		sha = (sha - (int(haystack[left])*multi)%mod + mod) % mod
		left++
	}
	return -1
}
```

#### TypeScript

```ts
function strStr(haystack: string, needle: string): number {
    const n = haystack.length;
    const m = needle.length;
    const mod = 2 ** 31 - 1;
    let target = 0;
    let sha = 0;
    let multi = 1;
    for (let i = 0; i < m; ++i) {
        target = (target * 256 + needle.charCodeAt(i)) % mod;
    }
    for (let i = 1; i < m; ++i) {
        multi = (multi * 256) % mod;
    }
    let left = 0;
    for (let right = 0; right < n; ++right) {
        sha = (sha * 256 + haystack.charCodeAt(right)) % mod;
        if (right - left + 1 < m) {
            continue;
        }
        if (sha === target && haystack.slice(left, right + 1) === needle) {
            return left;
        }
        sha = (sha - ((haystack.charCodeAt(left) * multi) % mod) + mod) % mod;
        ++left;
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3: KMP

<!-- thinking:start -->

> **Thinking**
>
> Solution 2 makes a window comparison expected $O(1)$, but it still hashes modulo a prime and must verify the raw strings on a collision. We want a worst-case linear scan without hashing.
>
> After a mismatch we need not rewind $\textit{haystack}$ to the start of the window. The prefix function of $\textit{needle}$ stores the longest proper border of the matched prefix, so we know where in the pattern to resume.
>
> Build $\textit{next}$ for $\textit{needle}$, then scan $\textit{haystack}$ once, falling back only along $\textit{next}$. Time $O(n+m)$ and extra space $O(m)$.

<!-- thinking:end -->

Compute the prefix function $\textit{next}$ of $\textit{needle}$, where $\textit{next}[i]$ is the longest proper border of $\textit{needle}[0..i]$. Scan $\textit{haystack}$: equal characters grow the match length, and a mismatch jumps it to $\textit{next}[j-1]$. When the match length reaches $m$, return the start index $i-m+1$.

The time complexity is $O(n+m)$ and the space complexity is $O(m)$, where $n$ and $m$ are the lengths of $\textit{haystack}$ and $\textit{needle}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        n, m = len(haystack), len(needle)
        nxt = [0] * m
        j = 0
        for i in range(1, m):
            while j and needle[i] != needle[j]:
                j = nxt[j - 1]
            if needle[i] == needle[j]:
                j += 1
            nxt[i] = j
        j = 0
        for i, ch in enumerate(haystack):
            while j and ch != needle[j]:
                j = nxt[j - 1]
            if ch == needle[j]:
                j += 1
            if j == m:
                return i - m + 1
        return -1
```

#### Java

```java
class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        int[] nxt = new int[m];
        for (int i = 1, j = 0; i < m; ++i) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = nxt[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                ++j;
            }
            nxt[i] = j;
        }
        for (int i = 0, j = 0; i < n; ++i) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = nxt[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                ++j;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int strStr(string haystack, string needle) {
        int n = haystack.size(), m = needle.size();
        vector<int> nxt(m);
        for (int i = 1, j = 0; i < m; ++i) {
            while (j > 0 && needle[i] != needle[j]) {
                j = nxt[j - 1];
            }
            if (needle[i] == needle[j]) {
                ++j;
            }
            nxt[i] = j;
        }
        for (int i = 0, j = 0; i < n; ++i) {
            while (j > 0 && haystack[i] != needle[j]) {
                j = nxt[j - 1];
            }
            if (haystack[i] == needle[j]) {
                ++j;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func strStr(haystack string, needle string) int {
	n, m := len(haystack), len(needle)
	nxt := make([]int, m)
	for i, j := 1, 0; i < m; i++ {
		for j > 0 && needle[i] != needle[j] {
			j = nxt[j-1]
		}
		if needle[i] == needle[j] {
			j++
		}
		nxt[i] = j
	}
	for i, j := 0, 0; i < n; i++ {
		for j > 0 && haystack[i] != needle[j] {
			j = nxt[j-1]
		}
		if haystack[i] == needle[j] {
			j++
		}
		if j == m {
			return i - m + 1
		}
	}
	return -1
}
```

#### TypeScript

```ts
function strStr(haystack: string, needle: string): number {
    const n = haystack.length;
    const m = needle.length;
    const nxt = Array(m).fill(0);
    for (let i = 1, j = 0; i < m; ++i) {
        while (j > 0 && needle[i] !== needle[j]) {
            j = nxt[j - 1];
        }
        if (needle[i] === needle[j]) {
            ++j;
        }
        nxt[i] = j;
    }
    for (let i = 0, j = 0; i < n; ++i) {
        while (j > 0 && haystack[i] !== needle[j]) {
            j = nxt[j - 1];
        }
        if (haystack[i] === needle[j]) {
            ++j;
        }
        if (j === m) {
            return i - m + 1;
        }
    }
    return -1;
}
```

#### Rust

```rust
impl Solution {
    pub fn str_str(haystack: String, needle: String) -> i32 {
        let haystack = haystack.as_bytes();
        let needle = needle.as_bytes();
        let n = haystack.len();
        let m = needle.len();
        let mut nxt = vec![0; m];
        let mut j = 0;
        for i in 1..m {
            while j > 0 && needle[i] != needle[j] {
                j = nxt[j - 1];
            }
            if needle[i] == needle[j] {
                j += 1;
            }
            nxt[i] = j;
        }
        j = 0;
        for i in 0..n {
            while j > 0 && haystack[i] != needle[j] {
                j = nxt[j - 1];
            }
            if haystack[i] == needle[j] {
                j += 1;
            }
            if j == m {
                return (i - m + 1) as i32;
            }
        }
        -1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
