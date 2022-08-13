# [709. 转换成小写字母](https://leetcode.cn/problems/to-lower-case)

[English Version](/solution/0700-0799/0709.To%20Lower%20Case/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "Hello"
<strong>输出：</strong>"hello"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "here"
<strong>输出：</strong>"here"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "LOVELY"
<strong>输出：</strong>"lovely"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 100</code></li>
	<li><code>s</code> 由 ASCII 字符集中的可打印字符组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

遍历字符串，遇到大写的字符，转小写。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def toLowerCase(self, s: str) -> str:
        return ''.join(
            [chr(ord(c) | 32) if ord('A') <= ord(c) <= ord('Z') else c for c in s]
        )
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String toLowerCase(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] |= 32;
            }
        }
        return new String(chars);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string toLowerCase(string s) {
        for (char& c : s)
            if (c >= 'A' && c <= 'Z')
                c |= 32;
        return s;
    }
};
```

### **Go**

```go
func toLowerCase(s string) string {
	sb := &strings.Builder{}
	sb.Grow(len(s))
	for _, c := range s {
		if c >= 'A' && c <= 'Z' {
			c |= 32
		}
		sb.WriteRune(c)
	}
	return sb.String()
}
```

### **Rust**

```rust
impl Solution {
    pub fn to_lower_case(s: String) -> String {
        s.to_ascii_lowercase()
    }
}
```

```rust
impl Solution {
    pub fn to_lower_case(s: String) -> String {
        String::from_utf8(
            s.as_bytes()
                .iter()
                .map(|&c| c + if c >= b'A' && c <= b'Z' { 32 } else { 0 })
                .collect(),
        )
        .unwrap()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
