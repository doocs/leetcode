# [459. 重复的子字符串](https://leetcode.cn/problems/repeated-substring-pattern)

[English Version](/solution/0400-0499/0459.Repeated%20Substring%20Pattern/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非空的字符串<meta charset="UTF-8" />&nbsp;<code>s</code>&nbsp;，检查是否可以通过由它的一个子串重复多次构成。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> s = "abab"
<strong>输出:</strong> true
<strong>解释:</strong> 可由子串 "ab" 重复两次构成。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "aba"
<strong>输出:</strong> false
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = "abcabcabcabc"
<strong>输出:</strong> true
<strong>解释:</strong> 可由子串 "abc" 重复四次构成。 (或子串 "abcabc" 重复两次构成。)
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code>&nbsp;由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双倍字符串**

若长度为 $n$ 的字符串 `s` 由 $m$ 个重复子串组成，将 `s` 拼接在自身上，得到字符串 `ss`，长度为 $2n$，此时若从下标 `1` 开始查找 `s`，那么查找到的下标一定小于 `s.length`。

若长度为 $n$ 的字符串 `s` 不由重复子串组成，将 `s` 拼接在自身上，得到字符串 `ss`，长度为 $2n$，此时若从下标 `1` 开始查找 `s`，那么查找到的下标一定等于 `s.length`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def repeatedSubstringPattern(self, s: str) -> bool:
        return (s + s).index(s, 1) < len(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool repeatedSubstringPattern(string s) {
        return (s + s).find(s, 1) < s.size();
    }
};
```

### **Go**

```go
func repeatedSubstringPattern(s string) bool {
	return strings.Index(s[1:]+s, s) < len(s)-1
}
```

### **TypeScript**

```ts
function repeatedSubstringPattern(s: string): boolean {
    return (s + s).slice(1, (s.length << 1) - 1).includes(s);
}
```

```ts
function repeatedSubstringPattern(s: string): boolean {
    const n = s.length;
    for (let i = 0; i < n >> 1; i++) {
        const len = i + 1;
        if (n % len !== 0) {
            continue;
        }
        const t = s.slice(0, len);
        let j: number;
        for (j = len; j < n; j += len) {
            if (s.slice(j, j + len) !== t) {
                break;
            }
        }
        if (j === n) {
            return true;
        }
    }
    return false;
}
```

### **Rust**

```rust
impl Solution {
    pub fn repeated_substring_pattern(s: String) -> bool {
        (s.clone() + &s)[1..s.len() * 2 - 1].contains(&s)
    }
}
```

### **...**

```

```

<!-- tabs:end -->
