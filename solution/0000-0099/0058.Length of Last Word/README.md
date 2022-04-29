# [58. 最后一个单词的长度](https://leetcode.cn/problems/length-of-last-word)

[English Version](/solution/0000-0099/0058.Length%20of%20Last%20Word/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code>，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 <strong>最后一个</strong> 单词的长度。</p>

<p><strong>单词</strong> 是指仅由字母组成、不包含任何空格字符的最大子字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "Hello World"
<strong>输出：</strong>5
<strong>解释：</strong>最后一个单词是“World”，长度为5。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "   fly me   to   the moon  "
<strong>输出：</strong>4<strong>
解释：</strong>最后一个单词是“moon”，长度为4。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "luffy is still joyboy"
<strong>输出：</strong>6
<strong>解释：</strong>最后一个单词是长度为6的“joyboy”。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> 仅有英文字母和空格 <code>' '</code> 组成</li>
	<li><code>s</code> 中至少存在一个单词</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
