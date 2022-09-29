# [01.09. String Rotation](https://leetcode.cn/problems/string-rotation-lcci)

[中文文档](/lcci/01.09.String%20Rotation/README.md)

## Description

<p>Given two strings, <code>s1</code>&nbsp;and <code>s2</code>, write code to check if <code>s2</code> is a rotation of <code>s1</code> (e.g.,&quot;waterbottle&quot; is a rotation of&quot;erbottlewat&quot;).&nbsp;Can you use&nbsp;only one call to the method that&nbsp;checks if one word is a substring of another?</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>s1 = &quot;waterbottle&quot;, s2 = &quot;erbottlewat&quot;

<strong>Output: </strong>True

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>s1 = &quot;aa&quot;, &quot;aba&quot;

<strong>Output: </strong>False

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code><font face="monospace">0 &lt;= s1.length, s1.length &lt;=&nbsp;</font>100000</code></li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isFlipedString(self, s1: str, s2: str) -> bool:
        return len(s1) == len(s2) and s2 in s1 * 2
```

### **Java**

```java
class Solution {
    public boolean isFlipedString(String s1, String s2) {
        return s1.length() == s2.length() && (s1 + s1).contains(s2);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isFlipedString(string s1, string s2) {
        return s1.size() == s2.size() && (s1 + s1).find(s2) != string::npos;
    }
};
```

### **Go**

```go
func isFlipedString(s1 string, s2 string) bool {
	return len(s1) == len(s2) && strings.Contains(s1+s1, s2)
}
```

### **TypeScript**

```ts
function isFlipedString(s1: string, s2: string): boolean {
    return s1.length === s2.length && (s2 + s2).indexOf(s1) !== -1;
}
```

### **Rust**

```rust
impl Solution {
    pub fn is_fliped_string(s1: String, s2: String) -> bool {
        s1.len() == s2.len() && (s2.clone() + &s2).contains(&s1)
    }
}
```

```rust
impl Solution {
    pub fn is_fliped_string(s1: String, s2: String) -> bool {
        if s1 == s2 {
            return true;
        }
        if s1.len() != s2.len() {
            return false;
        }
        let s2: Vec<char> = (s2.clone() + &s2).chars().collect();
        let n = s1.len();
        let m = s2.len();
        for i in 0..m - n {
            let mut is_pass = true;
            for (j, c) in s1.chars().enumerate() {
                if c != s2[i + j] {
                    is_pass = false;
                    break;
                }
            }
            if is_pass {
                return true;
            };
        }
        false
    }
}
```

### **...**

```

```

<!-- tabs:end -->
