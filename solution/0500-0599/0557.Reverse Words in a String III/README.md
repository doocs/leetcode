# [557. 反转字符串中的单词 III](https://leetcode.cn/problems/reverse-words-in-a-string-iii)

[English Version](/solution/0500-0599/0557.Reverse%20Words%20in%20a%20String%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串<meta charset="UTF-8" />&nbsp;<code>s</code>&nbsp;，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "Let's take LeetCode contest"
<strong>输出：</strong>"s'teL ekat edoCteeL tsetnoc"
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong> s = "God Ding"
<strong>输出：</strong>"doG gniD"
</pre>

<p>&nbsp;</p>

<p><strong><strong><strong><strong>提示：</strong></strong></strong></strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><meta charset="UTF-8" /><code>s</code>&nbsp;包含可打印的 <strong>ASCII</strong> 字符。</li>
	<li><meta charset="UTF-8" /><code>s</code>&nbsp;不包含任何开头或结尾空格。</li>
	<li><meta charset="UTF-8" /><code>s</code>&nbsp;里 <strong>至少</strong> 有一个词。</li>
	<li><meta charset="UTF-8" /><code>s</code>&nbsp;中的所有单词都用一个空格隔开。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reverseWords(self, s: str) -> str:
        return ' '.join([t[::-1] for t in s.split(' ')])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        for (String t : s.split(" ")) {
            for (int i = t.length() - 1; i >= 0; --i) {
                res.append(t.charAt(i));
            }
            res.append(" ");
        }
        return res.substring(0, res.length() - 1);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string reverseWords(string s) {
        for (int i = 0, n = s.size(); i < n; ++i) {
            int j = i;
            while (++j < n && s[j] != ' ')
                ;
            reverse(s.begin() + i, s.begin() + j);
            i = j;
        }
        return s;
    }
};
```

### **Go**

```go
func reverseWords(s string) string {
	t := []byte(s)
	for i := 0; i < len(t); i++ {
		j := i
		for j < len(t) && t[j] != ' ' {
			j++
		}
		for st, ed := i, j-1; st < ed; st, ed = st+1, ed-1 {
			t[st], t[ed] = t[ed], t[st]
		}
		i = j
	}
	return string(t)
}
```

### **TypeScript**

```ts
function reverseWords(s: string): string {
    return s
        .split(/\s+/)
        .map(str => {
            let res = '';
            for (const c of str) {
                res = c + res;
            }
            return res;
        })
        .join(' ');
}
```

### **Rust**

```rust
impl Solution {
    pub fn reverse_words(s: String) -> String {
        s.split(' ')
            .map(|s| s.chars().rev().collect::<String>())
            .collect::<Vec<_>>()
            .join(" ")
    }
}
```

### **...**

```

```

<!-- tabs:end -->
