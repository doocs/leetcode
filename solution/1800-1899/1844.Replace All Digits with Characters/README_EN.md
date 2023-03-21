# [1844. Replace All Digits with Characters](https://leetcode.com/problems/replace-all-digits-with-characters)

[中文文档](/solution/1800-1899/1844.Replace%20All%20Digits%20with%20Characters/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>s</code> that has lowercase English letters in its <strong>even</strong> indices and digits in its <strong>odd</strong> indices.</p>

<p>There is a function <code>shift(c, x)</code>, where <code>c</code> is a character and <code>x</code> is a digit, that returns the <code>x<sup>th</sup></code> character after <code>c</code>.</p>

<ul>
	<li>For example, <code>shift(&#39;a&#39;, 5) = &#39;f&#39;</code> and <code>shift(&#39;x&#39;, 0) = &#39;x&#39;</code>.</li>
</ul>

<p>For every <strong>odd</strong>&nbsp;index <code>i</code>, you want to replace the digit <code>s[i]</code> with <code>shift(s[i-1], s[i])</code>.</p>

<p>Return <code>s</code><em> after replacing all digits. It is <strong>guaranteed</strong> that </em><code>shift(s[i-1], s[i])</code><em> will never exceed </em><code>&#39;z&#39;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a1c1e1&quot;
<strong>Output:</strong> &quot;abcdef&quot;
<strong>Explanation: </strong>The digits are replaced as follows:
- s[1] -&gt; shift(&#39;a&#39;,1) = &#39;b&#39;
- s[3] -&gt; shift(&#39;c&#39;,1) = &#39;d&#39;
- s[5] -&gt; shift(&#39;e&#39;,1) = &#39;f&#39;</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a1b2c3d4e&quot;
<strong>Output:</strong> &quot;abbdcfdhe&quot;
<strong>Explanation: </strong>The digits are replaced as follows:
- s[1] -&gt; shift(&#39;a&#39;,1) = &#39;b&#39;
- s[3] -&gt; shift(&#39;b&#39;,2) = &#39;d&#39;
- s[5] -&gt; shift(&#39;c&#39;,3) = &#39;f&#39;
- s[7] -&gt; shift(&#39;d&#39;,4) = &#39;h&#39;</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists only of lowercase English letters and digits.</li>
	<li><code>shift(s[i-1], s[i]) &lt;= &#39;z&#39;</code> for all <strong>odd</strong> indices <code>i</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def replaceDigits(self, s: str) -> str:
        s = list(s)
        for i in range(1, len(s), 2):
            s[i] = chr(ord(s[i - 1]) + int(s[i]))
        return ''.join(s)
```

### **Java**

```java
class Solution {
    public String replaceDigits(String s) {
        char[] cs = s.toCharArray();
        for (int i = 1; i < cs.length; i += 2) {
            cs[i] = (char) (cs[i - 1] + (cs[i] - '0'));
        }
        return String.valueOf(cs);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string replaceDigits(string s) {
        int n = s.size();
        for (int i = 1; i < n; i += 2) {
            s[i] = s[i - 1] + s[i] - '0';
        }
        return s;
    }
};
```

### **Go**

```go
func replaceDigits(s string) string {
	cs := []byte(s)
	for i := 1; i < len(s); i += 2 {
		cs[i] = cs[i-1] + cs[i] - '0'
	}
	return string(cs)
}
```

### **TypeScript**

```ts
function replaceDigits(s: string): string {
    const n = s.length;
    const ans = [...s];
    for (let i = 1; i < n; i += 2) {
        ans[i] = String.fromCharCode(ans[i - 1].charCodeAt(0) + Number(ans[i]));
    }
    return ans.join('');
}
```

### **Rust**

```rust
impl Solution {
    pub fn replace_digits(s: String) -> String {
        let n = s.len();
        let mut ans = s.into_bytes();
        let mut i = 1;
        while i < n {
            ans[i] = ans[i - 1] + (ans[i] - b'0');
            i += 2;
        }
        ans.into_iter().map(char::from).collect()
    }
}
```

### **C**

```c
char *replaceDigits(char *s) {
    int n = strlen(s);
    for (int i = 1; i < n; i += 2) {
        s[i] = s[i - 1] + s[i] - '0';
    }
    return s;
}
```

### **...**

```

```

<!-- tabs:end -->
