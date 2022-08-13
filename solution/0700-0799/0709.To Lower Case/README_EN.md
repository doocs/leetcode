# [709. To Lower Case](https://leetcode.com/problems/to-lower-case)

[中文文档](/solution/0700-0799/0709.To%20Lower%20Case/README.md)

## Description

<p>Given a string <code>s</code>, return <em>the string after replacing every uppercase letter with the same lowercase letter</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;Hello&quot;
<strong>Output:</strong> &quot;hello&quot;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;here&quot;
<strong>Output:</strong> &quot;here&quot;
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;LOVELY&quot;
<strong>Output:</strong> &quot;lovely&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of printable ASCII characters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def toLowerCase(self, s: str) -> str:
        return ''.join(
            [chr(ord(c) | 32) if ord('A') <= ord(c) <= ord('Z') else c for c in s]
        )
```

### **Java**

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
