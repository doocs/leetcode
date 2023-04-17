# [2414. 最长的字母序连续子字符串的长度](https://leetcode.cn/problems/length-of-the-longest-alphabetical-continuous-substring)

[English Version](/solution/2400-2499/2414.Length%20of%20the%20Longest%20Alphabetical%20Continuous%20Substring/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>字母序连续字符串</strong> 是由字母表中连续字母组成的字符串。换句话说，字符串 <code>"abcdefghijklmnopqrstuvwxyz"</code> 的任意子字符串都是 <strong>字母序连续字符串</strong> 。</p>

<ul>
	<li>例如，<code>"abc"</code> 是一个字母序连续字符串，而 <code>"acb"</code> 和 <code>"za"</code> 不是。</li>
</ul>

<p>给你一个仅由小写英文字母组成的字符串 <code>s</code> ，返回其 <strong>最长</strong> 的 字母序连续子字符串 的长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "abacaba"
<strong>输出：</strong>2
<strong>解释：</strong>共有 4 个不同的字母序连续子字符串 "a"、"b"、"c" 和 "ab" 。
"ab" 是最长的字母序连续子字符串。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "abcde"
<strong>输出：</strong>5
<strong>解释：</strong>"abcde" 是最长的字母序连续子字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

我们用双指针 $i$ 和 $j$ 分别指向当前连续子字符串的起始位置和结束位置。遍历字符串 $s$，如果当前字符 $s[j]$ 比 $s[j-1]$ 大，则 $j$ 向右移动一位，否则更新 $i$ 为 $j$，并更新最长连续子字符串的长度。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestContinuousSubstring(self, s: str) -> int:
        ans = 0
        i, j = 0, 1
        while j < len(s):
            ans = max(ans, j - i)
            if ord(s[j]) - ord(s[j - 1]) != 1:
                i = j
            j += 1
        ans = max(ans, j - i)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestContinuousSubstring(String s) {
        int ans = 0;
        int i = 0, j = 1;
        for (; j < s.length(); ++j) {
            ans = Math.max(ans, j - i);
            if (s.charAt(j) - s.charAt(j - 1) != 1) {
                i = j;
            }
        }
        ans = Math.max(ans, j - i);
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestContinuousSubstring(string s) {
        int ans = 0;
        int i = 0, j = 1;
        for (; j < s.size(); ++j) {
            ans = max(ans, j - i);
            if (s[j] - s[j - 1] != 1) {
                i = j;
            }
        }
        ans = max(ans, j - i);
        return ans;
    }
};
```

### **Go**

```go
func longestContinuousSubstring(s string) int {
	ans := 0
	i, j := 0, 1
	for ; j < len(s); j++ {
		ans = max(ans, j-i)
		if s[j]-s[j-1] != 1 {
			i = j
		}
	}
	ans = max(ans, j-i)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **C**

```c
#define max(a,b) (((a) > (b)) ? (a) : (b))

int longestContinuousSubstring(char *s) {
    int n = strlen(s);
    int i = 0;
    int res = 1;
    for (int j = 1; j < n; j++) {
        if (s[j] - s[j - 1] != 1) {
            res = max(res, j - i);
            i = j;
        }
    }
    return max(res, n - i);
}
```

### **TypeScript**

```ts
function longestContinuousSubstring(s: string): number {
    const n = s.length;
    let res = 1;
    let i = 0;
    for (let j = 1; j < n; j++) {
        if (s[j].charCodeAt(0) - s[j - 1].charCodeAt(0) !== 1) {
            res = Math.max(res, j - i);
            i = j;
        }
    }
    return Math.max(res, n - i);
}
```

### **Rust**

```rust
impl Solution {
    pub fn longest_continuous_substring(s: String) -> i32 {
        let s = s.as_bytes();
        let n = s.len();
        let mut res = 1;
        let mut i = 0;
        for j in 1..n {
            if s[j] - s[j - 1] != 1 {
                res = res.max(j - i);
                i = j;
            }
        }
        res.max(n - i) as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
