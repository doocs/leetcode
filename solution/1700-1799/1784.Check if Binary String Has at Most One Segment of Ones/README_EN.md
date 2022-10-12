# [1784. Check if Binary String Has at Most One Segment of Ones](https://leetcode.com/problems/check-if-binary-string-has-at-most-one-segment-of-ones)

[中文文档](/solution/1700-1799/1784.Check%20if%20Binary%20String%20Has%20at%20Most%20One%20Segment%20of%20Ones/README.md)

## Description

<p>Given a binary string <code>s</code> <strong>​​​​​without leading zeros</strong>, return <code>true</code>​​​ <em>if </em><code>s</code><em> contains <strong>at most one contiguous segment of ones</strong></em>. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1001&quot;
<strong>Output:</strong> false
<strong>Explanation: </strong>The ones do not form a contiguous segment.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;110&quot;
<strong>Output:</strong> true</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s[i]</code>​​​​ is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li><code>s[0]</code> is&nbsp;<code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def checkOnesSegment(self, s: str) -> bool:
        return '01' not in s
```

### **Java**

```java
class Solution {
    public boolean checkOnesSegment(String s) {
        return !s.contains("01");
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkOnesSegment(string s) {
        return s.find("01") == -1;
    }
};
```

### **Go**

```go
func checkOnesSegment(s string) bool {
	return !strings.Contains(s, "01")
}
```

### **TypeScript**

```ts
function checkOnesSegment(s: string): boolean {
    let pre = s[0];
    for (const c of s) {
        if (pre !== c && c === '1') {
            return false;
        }
        pre = c;
    }
    return true;
}
```

```ts
function checkOnesSegment(s: string): boolean {
    return !s.includes('01');
}
```

### **Rust**

```rust
impl Solution {
    pub fn check_ones_segment(s: String) -> bool {
        !s.contains("01")
    }
}
```

### **...**

```

```

<!-- tabs:end -->
