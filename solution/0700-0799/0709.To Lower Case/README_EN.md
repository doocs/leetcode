# [709. To Lower Case](https://leetcode.com/problems/to-lower-case)

[中文文档](/solution/0700-0799/0709.To%20Lower%20Case/README.md)

## Description

<p>Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.</p>

<p>&nbsp;</p>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">&quot;Hello&quot;</span>

<strong>Output: </strong><span id="example-output-1">&quot;hello&quot;</span>

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-2-1">&quot;here&quot;</span>

<strong>Output: </strong><span id="example-output-2">&quot;here&quot;</span>

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-3-1">&quot;LOVELY&quot;</span>

<strong>Output: </strong><span id="example-output-3">&quot;lovely&quot;</span>

</pre>

</div>

</div>

</div>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def toLowerCase(self, s: str) -> str:
        return ''.join([chr(ord(c) | 32) if ord('A') <= ord(c) <= ord('Z') else c for c in s])
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

### **...**

```

```

<!-- tabs:end -->
