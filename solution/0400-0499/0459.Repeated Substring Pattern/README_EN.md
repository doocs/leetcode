# [459. Repeated Substring Pattern](https://leetcode.com/problems/repeated-substring-pattern)

[中文文档](/solution/0400-0499/0459.Repeated%20Substring%20Pattern/README.md)

## Description

<p>Given a string <code>s</code>, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abab&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> It is the substring &quot;ab&quot; twice.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aba&quot;
<strong>Output:</strong> false
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcabcabcabc&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> It is the substring &quot;abc&quot; four times or the substring &quot;abcabc&quot; twice.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def repeatedSubstringPattern(self, s: str) -> bool:
        return (s + s).index(s, 1) < len(s)
```

### **Java**

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
