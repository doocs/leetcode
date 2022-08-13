# [28. 实现 strStr()](https://leetcode.cn/problems/implement-strstr)

[English Version](/solution/0000-0099/0028.Implement%20strStr%28%29/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>实现&nbsp;<a href="https://baike.baidu.com/item/strstr/811469" target="_blank">strStr()</a>&nbsp;函数。</p>

<p>给你两个字符串&nbsp;<code>haystack</code> 和 <code>needle</code> ，请你在 <code>haystack</code> 字符串中找出 <code>needle</code> 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回&nbsp; <code>-1</code><strong> </strong>。</p>

<p><strong>说明：</strong></p>

<p>当&nbsp;<code>needle</code>&nbsp;是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。</p>

<p>对于本题而言，当&nbsp;<code>needle</code>&nbsp;是空字符串时我们应当返回 0 。这与 C 语言的&nbsp;<a href="https://baike.baidu.com/item/strstr/811469" target="_blank">strstr()</a>&nbsp;以及 Java 的&nbsp;<a href="https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#indexOf(java.lang.String)" target="_blank">indexOf()</a>&nbsp;定义相符。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>haystack = "hello", needle = "ll"
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>haystack = "aaaaa", needle = "bba"
<strong>输出：</strong>-1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= haystack.length, needle.length &lt;= 10<sup>4</sup></code></li>
	<li><code>haystack</code> 和 <code>needle</code> 仅由小写英文字符组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def strStr(self, haystack, needle):
        """
        :type haystack: str
        :type needle: str
        :rtype: int
        """
        for i in range(len(haystack) - len(needle) + 1):
            p = i
            q = 0
            while p < len(haystack) and q < len(needle) and haystack[p] == needle[q]:
                p += 1
                q += 1

            if q == len(needle):
                return i

        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **C#**

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

### **Go**

```go
func strStr(haystack string, needle string) int {
	switch {
	case len(needle) == 0:
		return 0
	case len(needle) > len(haystack):
		return -1
	case len(needle) == len(haystack):
		if needle == haystack {
			return 0
		}
		return -1
	}
	cursor := 0
	for i := 0; i < len(haystack); i++ {
		if haystack[i] == needle[cursor] {
			cursor++
			if cursor == len(needle) {
				return i - cursor + 1
			}
		} else {
			i -= cursor
			cursor = 0
		}
	}
	return -1
}
```

### **JavaScript**

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

### **TypeScript**

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

### **Rust**

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

### **...**

```

```

<!-- tabs:end -->
