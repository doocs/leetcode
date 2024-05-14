# [709. To Lower Case](https://leetcode.com/problems/to-lower-case)

[中文文档](/solution/0700-0799/0709.To%20Lower%20Case/README.md)

<!-- tags:String -->

<!-- difficulty:Easy -->

## Description

<p>Given a string <code>s</code>, return <em>the string after replacing every uppercase letter with the same lowercase letter</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;Hello&quot;
<strong>Output:</strong> &quot;hello&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;here&quot;
<strong>Output:</strong> &quot;here&quot;
</pre>

<p><strong class="example">Example 3:</strong></p>

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

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def toLowerCase(self, s: str) -> str:
        return "".join([chr(ord(c) | 32) if c.isupper() else c for c in s])
```

```java
class Solution {
    public String toLowerCase(String s) {
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; ++i) {
            if (cs[i] >= 'A' && cs[i] <= 'Z') {
                cs[i] |= 32;
            }
        }
        return String.valueOf(cs);
    }
}
```

```cpp
class Solution {
public:
    string toLowerCase(string s) {
        for (char& c : s) {
            if (c >= 'A' && c <= 'Z') {
                c |= 32;
            }
        }
        return s;
    }
};
```

```go
func toLowerCase(s string) string {
	cs := []byte(s)
	for i, c := range cs {
		if c >= 'A' && c <= 'Z' {
			cs[i] |= 32
		}
	}
	return string(cs)
}
```

```ts
function toLowerCase(s: string): string {
    return s.toLowerCase();
}
```

```rust
impl Solution {
    pub fn to_lower_case(s: String) -> String {
        s.to_ascii_lowercase()
    }
}
```

```c
char* toLowerCase(char* s) {
    int n = strlen(s);
    for (int i = 0; i < n; i++) {
        if (s[i] >= 'A' && s[i] <= 'Z') {
            s[i] |= 32;
        }
    }
    return s;
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```ts
function toLowerCase(s: string): string {
    return [...s].map(c => String.fromCharCode(c.charCodeAt(0) | 32)).join('');
}
```

```rust
impl Solution {
    pub fn to_lower_case(s: String) -> String {
        s.as_bytes()
            .iter()
            .map(|&c| char::from(if c >= b'A' && c <= b'Z' { c | 32 } else { c }))
            .collect()
    }
}
```

<!-- tabs:end -->

<!-- end -->
