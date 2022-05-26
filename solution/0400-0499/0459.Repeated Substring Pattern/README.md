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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

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
