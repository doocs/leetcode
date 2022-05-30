# [58. Length of Last Word](https://leetcode.com/problems/length-of-last-word)

[中文文档](/solution/0000-0099/0058.Length%20of%20Last%20Word/README.md)

## Description

<p>Given a string <code>s</code> consisting of words and spaces, return <em>the length of the <strong>last</strong> word in the string.</em></p>

<p>A <strong>word</strong> is a maximal substring consisting of non-space characters only.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;Hello World&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The last word is &quot;World&quot; with length 5.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;   fly me   to   the moon  &quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> The last word is &quot;moon&quot; with length 4.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;luffy is still joyboy&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> The last word is &quot;joyboy&quot; with length 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of only English letters and spaces <code>&#39; &#39;</code>.</li>
	<li>There will be at least one word in <code>s</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def lengthOfLastWord(self, s: str) -> int:
        last_word_length = 0
        meet_word = False
        for i in range(len(s) - 1, -1, -1):
            ch = ord(s[i])
            if ch >= 65 and ch <= 122:
                meet_word = True
                last_word_length += 1
            elif meet_word:
                break
        return last_word_length
```

### **Java**

```java
class Solution {
    public int lengthOfLastWord(String s) {
        int n = s.length();
        int lastWordLength = 0;
        boolean meetWord = false;
        for (int i = n - 1; i >= 0; --i) {
            char ch = s.charAt(i);
            if (ch >= 'A' && ch <= 'z') {
                meetWord = true;
                ++lastWordLength;
            } else if (meetWord) {
                break;
            }
        }
        return lastWordLength;
    }
}
```

### **Go**

```go
func lengthOfLastWord(s string) int {
	if len(s) == 0 {
		return 0
	}
	space := []byte(" ")[0]
	for len(s) != 0 && s[len(s)-1] == space {
		s = s[:len(s)-1]
	}
	ret := 0
	for i := len(s) - 1; i >= 0; i-- {
		if s[i] != space {
			ret++
		} else {
			return ret
		}
	}
	return ret
}
```

### **JavaScript**

```js
var lengthOfLastWord = function (s) {
    s = s.trim();
    return s.length - s.lastIndexOf(' ') - 1;
};

var lengthOfLastWord2 = function (s) {
    let res = 0;
    for (let i = 0; i < s.length; i++) {
        if (s[i] !== ' ' && (i === 0 || s[i - 1] === ' ')) {
            res = 1;
        } else if (s[i] !== ' ') {
            res++;
        }
    }
    return res;
};
```

### **TypeScript**

```ts
function lengthOfLastWord(s: string): number {
    s = s.trimEnd();
    const n = s.length;
    const index = s.lastIndexOf(' ');
    if (index !== -1) {
        return n - index - 1;
    }
    return n;
}
```

### **Rust**

```rust
impl Solution {
    pub fn length_of_last_word(s: String) -> i32 {
        let s = s.trim_end();
        let n = s.len();
        for (i, c) in s.char_indices().rev() {
            if c == ' ' {
                return (n - i - 1) as i32;
            }
        }
        n as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
