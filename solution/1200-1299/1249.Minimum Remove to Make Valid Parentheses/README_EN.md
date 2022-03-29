# [1249. Minimum Remove to Make Valid Parentheses](https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses)

[中文文档](/solution/1200-1299/1249.Minimum%20Remove%20to%20Make%20Valid%20Parentheses/README.md)

## Description

<p>Given a string <font face="monospace">s</font> of <code>&#39;(&#39;</code> , <code>&#39;)&#39;</code> and lowercase English characters.</p>

<p>Your task is to remove the minimum number of parentheses ( <code>&#39;(&#39;</code> or <code>&#39;)&#39;</code>, in any positions ) so that the resulting <em>parentheses string</em> is valid and return <strong>any</strong> valid string.</p>

<p>Formally, a <em>parentheses string</em> is valid if and only if:</p>

<ul>
	<li>It is the empty string, contains only lowercase characters, or</li>
	<li>It can be written as <code>AB</code> (<code>A</code> concatenated with <code>B</code>), where <code>A</code> and <code>B</code> are valid strings, or</li>
	<li>It can be written as <code>(A)</code>, where <code>A</code> is a valid string.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;lee(t(c)o)de)&quot;
<strong>Output:</strong> &quot;lee(t(c)o)de&quot;
<strong>Explanation:</strong> &quot;lee(t(co)de)&quot; , &quot;lee(t(c)ode)&quot; would also be accepted.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a)b(c)d&quot;
<strong>Output:</strong> &quot;ab(c)d&quot;
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;))((&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> An empty string is also valid.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either<code>&#39;(&#39;</code> , <code>&#39;)&#39;</code>, or lowercase English letter<code>.</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts
function minRemoveToMakeValid(s: string): string {
    let left = 0;
    let right = 0;
    for (const c of s) {
        if (c === '(') {
            left++;
        } else if (c === ')') {
            if (right < left) {
                right++;
            }
        }
    }

    let hasLeft = 0;
    let res = '';
    for (const c of s) {
        if (c === '(') {
            if (hasLeft < right) {
                hasLeft++;
                res += c;
            }
        } else if (c === ')') {
            if (hasLeft != 0 && right !== 0) {
                right--;
                hasLeft--;
                res += c;
            }
        } else {
            res += c;
        }
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_remove_to_make_valid(s: String) -> String {
        let bs = s.as_bytes();
        let mut right = {
            let mut left = 0;
            let mut right = 0;
            for c in bs.iter() {
                match c {
                    &b'(' => left += 1,
                    &b')' if right < left => right += 1,
                    _ => {}
                }
            }
            right
        };
        let mut has_left = 0;
        let mut res = vec![];
        for c in bs.iter() {
            match c {
                &b'(' => {
                    if has_left < right {
                        has_left += 1;
                        res.push(*c);
                    }
                }
                &b')' => {
                    if has_left != 0 && right != 0 {
                        right -= 1;
                        has_left -= 1;
                        res.push(*c);
                    }
                }
                _ => {
                    res.push(*c);
                }
            }
        }
        String::from_utf8_lossy(&res).to_string()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
