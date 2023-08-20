# [2810. Faulty Keyboard](https://leetcode.com/problems/faulty-keyboard)

[中文文档](/solution/2800-2899/2810.Faulty%20Keyboard/README.md)

## Description

<p>Your laptop keyboard is faulty, and whenever you type a character <code>&#39;i&#39;</code> on it, it reverses the string that you have written. Typing other characters works as expected.</p>

<p>You are given a <strong>0-indexed</strong> string <code>s</code>, and you type each character of <code>s</code> using your faulty keyboard.</p>

<p>Return <em>the final string that will be present on your laptop screen.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;string&quot;
<strong>Output:</strong> &quot;rtsng&quot;
<strong>Explanation:</strong> 
After typing first character, the text on the screen is &quot;s&quot;.
After the second character, the text is &quot;st&quot;. 
After the third character, the text is &quot;str&quot;.
Since the fourth character is an &#39;i&#39;, the text gets reversed and becomes &quot;rts&quot;.
After the fifth character, the text is &quot;rtsn&quot;. 
After the sixth character, the text is &quot;rtsng&quot;. 
Therefore, we return &quot;rtsng&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;poiinter&quot;
<strong>Output:</strong> &quot;ponter&quot;
<strong>Explanation:</strong> 
After the first character, the text on the screen is &quot;p&quot;.
After the second character, the text is &quot;po&quot;. 
Since the third character you type is an &#39;i&#39;, the text gets reversed and becomes &quot;op&quot;. 
Since the fourth character you type is an &#39;i&#39;, the text gets reversed and becomes &quot;po&quot;.
After the fifth character, the text is &quot;pon&quot;.
After the sixth character, the text is &quot;pont&quot;. 
After the seventh character, the text is &quot;ponte&quot;. 
After the eighth character, the text is &quot;ponter&quot;. 
Therefore, we return &quot;ponter&quot;.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>s[0] != &#39;i&#39;</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def finalString(self, s: str) -> str:
        t = []
        for c in s:
            if c == "i":
                t = t[::-1]
            else:
                t.append(c)
        return "".join(t)
```

### **Java**

```java
class Solution {
    public String finalString(String s) {
        StringBuilder t = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == 'i') {
                t.reverse();
            } else {
                t.append(c);
            }
        }
        return t.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string finalString(string s) {
        string t;
        for (char c : s) {
            if (c == 'i') {
                reverse(t.begin(), t.end());
            } else {
                t.push_back(c);
            }
        }
        return t;
    }
};
```

### **Go**

```go
func finalString(s string) string {
	t := []rune{}
	for _, c := range s {
		if c == 'i' {
			for i, j := 0, len(t)-1; i < j; i, j = i+1, j-1 {
				t[i], t[j] = t[j], t[i]
			}
		} else {
			t = append(t, c)
		}
	}
	return string(t)
}
```

### **TypeScript**

```ts
function finalString(s: string): string {
    const t: string[] = [];
    for (const c of s) {
        if (c === 'i') {
            t.reverse();
        } else {
            t.push(c);
        }
    }
    return t.join('');
}
```

### **Rust**

```rust
impl Solution {
    pub fn final_string(s: String) -> String {
        let mut t = Vec::new();
        for c in s.chars() {
            if c == 'i' {
                t.reverse();
            } else {
                t.push(c);
            }
        }
        t.into_iter().collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
