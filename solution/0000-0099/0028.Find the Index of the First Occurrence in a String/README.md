---
comments: true
difficulty: 简单
tags:
    - 双指针
    - 字符串
    - 字符串匹配
---

<!-- problem:start -->

# [28. 找出字符串中第一个匹配项的下标](https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string)

[English Version](/solution/0000-0099/0028.Find%20the%20Index%20of%20the%20First%20Occurrence%20in%20a%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个字符串&nbsp;<code>haystack</code> 和 <code>needle</code> ，请你在 <code>haystack</code> 字符串中找出 <code>needle</code> 字符串的第一个匹配项的下标（下标从 0 开始）。如果&nbsp;<code>needle</code> 不是 <code>haystack</code> 的一部分，则返回&nbsp; <code>-1</code><strong> </strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>haystack = "sadbutsad", needle = "sad"
<strong>输出：</strong>0
<strong>解释：</strong>"sad" 在下标 0 和 6 处匹配。
第一个匹配项的下标是 0 ，所以返回 0 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>haystack = "leetcode", needle = "leeto"
<strong>输出：</strong>-1
<strong>解释：</strong>"leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= haystack.length, needle.length &lt;= 10<sup>4</sup></code></li>
	<li><code>haystack</code> 和 <code>needle</code> 仅由小写英文字符组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历

<!-- thinking:start -->

> **思考**
>
> 若以 $haystack$ 的每个位置 $i$ 为起点，检查长度为 $m$ 的子串是否等于 $needle$，时间复杂度为 $O((n-m)m)$。在 $n,m \le 10^4$ 的数据范围下可以接受。
>
> 我们只关心第一次出现的下标，因此一旦某起点匹配成功即可返回；失配则换下一个 $i$，不必收集全部出现位置。窗口之间不复用比较结果，直接比对即可，额外空间 $O(1)$。

<!-- thinking:end -->

以字符串 `haystack` 的每一个字符为起点与字符串 `needle` 进行比较，若发现能够匹配的索引，直接返回即可。

假设字符串 `haystack` 长度为 $n$，字符串 `needle` 长度为 $m$，则时间复杂度为 $O((n-m) \times m)$，空间复杂度 $O(1)$。

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

### 方法二：Rabin-Karp 字符串匹配算法

<!-- thinking:start -->

> **思考**
>
> 方法一最坏每个起点都要比满 $m$ 个字符，接近 $O(nm)$。我们希望窗口滑动时复用上一次的比较信息。
>
> 固定长度的子串可以用滚动哈希：右端纳入一个字符、左端移出一个字符，哈希值即可 $O(1)$ 更新。当窗口哈希等于 $needle$ 的哈希时再核对原串，以避免冲突造成误报。
>
> 因此只需扫描一遍 $haystack$，期望时间 $O(n+m)$。

<!-- thinking:end -->

[Rabin-Karp 算法](https://zh.wikipedia.org/zh-hans/%E6%8B%89%E5%AE%BE-%E5%8D%A1%E6%99%AE%E7%AE%97%E6%B3%95)本质上是利用滑动窗口配合哈希函数对固定长度的字符串哈希之后进行比较，可以将比较两个字符串是否相同的时间复杂度降为 $O(1)$。

假设字符串 `haystack` 长度为 $n$，字符串 `needle` 长度为 $m$，则时间复杂度为 $O(n+m)$，空间复杂度 $O(1)$。

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

### 方法三：KMP 算法

<!-- thinking:start -->

> **思考**
>
> 方法二把窗口比较降到期望 $O(1)$，但仍依赖取模哈希，冲突时还要核对原串。我们希望最坏情况也是线性，且不引入哈希。
>
> 失配后不必把 $\textit{haystack}$ 的指针退回起点。$\textit{needle}$ 的前缀函数给出“当前已匹配前缀的最长真后缀”，从而知道下一次该从模式串的哪一位继续。
>
> 先对 $\textit{needle}$ 求 $\textit{next}$，再单次扫描 $\textit{haystack}$，失配只沿 $\textit{next}$ 回退。时间 $O(n+m)$，额外空间 $O(m)$。

<!-- thinking:end -->

先对模式串 $\textit{needle}$ 求前缀函数 $\textit{next}$，其中 $\textit{next}[i]$ 表示 $\textit{needle}[0..i]$ 的最长真前后缀长度。随后在 $\textit{haystack}$ 上扫描：字符相等则匹配长度加一，失配则令匹配长度跳到 $\textit{next}[j-1]$。当匹配长度达到 $m$ 时，返回起始下标 $i-m+1$。

时间复杂度 $O(n+m)$，空间复杂度 $O(m)$。其中 $n$ 和 $m$ 分别为 $\textit{haystack}$ 与 $\textit{needle}$ 的长度。

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
