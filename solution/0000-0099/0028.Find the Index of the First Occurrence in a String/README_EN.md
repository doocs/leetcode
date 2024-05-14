# [28. Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string)

[中文文档](/solution/0000-0099/0028.Find%20the%20Index%20of%20the%20First%20Occurrence%20in%20a%20String/README.md)

<!-- tags:Two Pointers,String,String Matching -->

<!-- difficulty:Easy -->

## Description

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

## Solutions

### Solution 1: Traversal

We compare the string `needle` with each character of the string `haystack` as the starting point. If we find a matching index, we return it directly.

Assuming the length of the string `haystack` is $n$ and the length of the string `needle` is $m$, the time complexity is $O((n-m) \times m)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        n, m = len(haystack), len(needle)
        for i in range(n - m + 1):
            if haystack[i : i + m] == needle:
                return i
        return -1
```

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

```cpp
class Solution {
private:
    vector<int> Next(string str) {
        vector<int> n(str.length());
        n[0] = -1;
        int i = 0, pre = -1;
        int len = str.length();
        while (i < len) {
            while (pre >= 0 && str[i] != str[pre])
                pre = n[pre];
            ++i, ++pre;
            if (i >= len)
                break;
            if (str[i] == str[pre])
                n[i] = n[pre];
            else
                n[i] = pre;
        }
        return n;
    }

public:
    int strStr(string haystack, string needle) {
        if (0 == needle.length())
            return 0;

        vector<int> n(Next(needle));

        int len = haystack.length() - needle.length() + 1;
        for (int i = 0; i < len; ++i) {
            int j = 0, k = i;
            while (j < needle.length() && k < haystack.length()) {
                if (haystack[k] != needle[j]) {
                    if (n[j] >= 0) {
                        j = n[j];
                        continue;
                    } else
                        break;
                }
                ++k, ++j;
            }
            if (j >= needle.length())
                return k - j;
        }

        return -1;
    }
};
```

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

```rust
impl Solution {
    pub fn str_str(haystack: String, needle: String) -> i32 {
        let haystack = haystack.as_bytes();
        let needle = needle.as_bytes();
        let m = haystack.len();
        let n = needle.len();
        let mut next = vec![0; n];
        let mut j = 0;
        for i in 1..n {
            while j > 0 && needle[i] != needle[j] {
                j = next[j - 1];
            }
            if needle[i] == needle[j] {
                j += 1;
            }
            next[i] = j;
        }
        j = 0;
        for i in 0..m {
            while j > 0 && haystack[i] != needle[j] {
                j = next[j - 1];
            }
            if haystack[i] == needle[j] {
                j += 1;
            }
            if j == n {
                return (i - n + 1) as i32;
            }
        }
        -1
    }
}
```

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

```cs
public class Solution {
    public int StrStr(string haystack, string needle) {
        for (var i = 0; i < haystack.Length - needle.Length + 1; ++i)
        {
            var j = 0;
            for (; j < needle.Length; ++j)
            {
                if (haystack[i + j] != needle[j]) break;
            }
            if (j == needle.Length) return i;
        }
        return -1;
    }
}
```

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

### Solution 2: Rabin-Karp String Matching Algorithm

The [Rabin-Karp algorithm](https://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_algorithm) essentially uses a sliding window combined with a hash function to compare the hashes of fixed-length strings, which can reduce the time complexity of comparing whether two strings are the same to $O(1)$.

Assuming the length of the string `haystack` is $n$ and the length of the string `needle` is $m$, the time complexity is $O(n+m)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

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

```ts
function strStr(haystack: string, needle: string): number {
    const m = haystack.length;
    const n = needle.length;
    const next = new Array(n).fill(0);
    let j = 0;
    for (let i = 1; i < n; i++) {
        while (j > 0 && needle[i] !== needle[j]) {
            j = next[j - 1];
        }
        if (needle[i] === needle[j]) {
            j++;
        }
        next[i] = j;
    }
    j = 0;
    for (let i = 0; i < m; i++) {
        while (j > 0 && haystack[i] !== needle[j]) {
            j = next[j - 1];
        }
        if (haystack[i] === needle[j]) {
            j++;
        }
        if (j === n) {
            return i - n + 1;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

### Solution 3: KMP String Matching Algorithm

Assuming the length of the string `haystack` is $n$ and the length of the string `needle` is $m$, the time complexity is $O(n+m)$, and the space complexity is $O(m)$.

<!-- end -->
