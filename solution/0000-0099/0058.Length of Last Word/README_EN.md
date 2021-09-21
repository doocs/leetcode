# [58. Length of Last Word](https://leetcode.com/problems/length-of-last-word)

[中文文档](/solution/0000-0099/0058.Length%20of%20Last%20Word/README.md)

## Description

<p>Given a string <code>s</code> consists of some words separated by spaces, return <em>the length of the last word&nbsp;in the string. If the last word does not exist, return </em><code>0</code>.</p>

<p>A <strong>word</strong> is a maximal substring consisting&nbsp;of non-space characters only.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "Hello World"
<strong>Output:</strong> 5
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = " "
<strong>Output:</strong> 0
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of only English letters and spaces <code>&#39; &#39;</code>.</li>
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

### **Rust**

```rust
impl Solution {
    pub fn length_of_last_word(s: String) -> i32 {
        let s = s.trim_end();
        if s.len() == 0 {
            return 0;
        }
        for (i, c) in s.char_indices().rev() {
            if c == ' ' {
                return (s.len() - i - 1) as i32;
            }
        }
        s.len() as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
