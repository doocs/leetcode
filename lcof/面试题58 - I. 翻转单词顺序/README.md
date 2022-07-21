# [面试题 58 - I. 翻转单词顺序](https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/)

## 题目描述

<p>输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串&quot;I am a student. &quot;，则输出&quot;student. a am I&quot;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> &quot;<code>the sky is blue</code>&quot;
<strong>输出:&nbsp;</strong>&quot;<code>blue is sky the</code>&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> &quot; &nbsp;hello world! &nbsp;&quot;
<strong>输出:&nbsp;</strong>&quot;world! hello&quot;
<strong>解释: </strong>输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入:</strong> &quot;a good &nbsp; example&quot;
<strong>输出:&nbsp;</strong>&quot;example good a&quot;
<strong>解释: </strong>如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
</pre>

<p>&nbsp;</p>

<p><strong>说明：</strong></p>

<ul>
	<li>无空格字符构成一个单词。</li>
	<li>输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。</li>
	<li>如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。</li>
</ul>

<p><strong>注意：</strong>本题与主站 151 题相同：<a href="https://leetcode.cn/problems/reverse-words-in-a-string/">https://leetcode.cn/problems/reverse-words-in-a-string/</a></p>

<p><strong>注意：</strong>此题对比原题有改动</p>

## 解法

按空格分割字符串后逆序。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reverseWords(self, s: str) -> str:
        if s is None:
            return s
        return ' '.join(list(filter(lambda x: x != '', s.strip(' ').split(' ')))[::-1])
```

### **Java**

```java
class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        int len = words.length;
        for (int i = len - 1; i >= 0; --i) {
            if (!"".equals(words[i])) {
                sb.append(words[i]).append(" ");
            }
        }
        s = sb.toString();
        len = s.length();
        return len > 0 ? s.substring(0, len - 1) : "";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string reverseWords(string s) {
        string res;
        int i = s.size() - 1;
        while (i >= 0) {
            if (s[i] == ' ') {
                i--;
            } else {
                int j = i;
                while (i >= 0 && s[i] != ' ') {
                    i--;
                }
                res += s.substr(i + 1, j - i);
                res.push_back(' ');
            }
        }
        return res.substr(0, res.size() - 1);
    }
};
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {string}
 */
var reverseWords = function (s) {
    return s
        .split(' ')
        .reduce((acc, cur) => (cur !== '' ? acc.concat(cur) : acc), [])
        .reverse()
        .join(' ');
};
```

### **Go**

```go
func reverseWords(s string) string {
	s = strings.Trim(s, " ")
	n := len(s) - 1
	builder := new(strings.Builder)
	for i, j := n, n; i >= 0; j = i {
		for i >= 0 && s[i] != ' ' {
			i--
		}
		if builder.Len() != 0 {
			builder.WriteRune(' ')
		}
		builder.WriteString(s[i+1 : j+1])
		for i >= 0 && s[i] == ' ' {
			i--
		}
	}
	return builder.String()
}
```

### **TypeScript**

API：

```ts
function reverseWords(s: string): string {
    return s.trim().split(/\s+/).reverse().join(' ');
}
```

双指针：

```ts
function reverseWords(s: string): string {
    s = s.trim();
    const res = [];
    let l = s.length - 1;
    let r = s.length - 1;
    while (l >= 0) {
        while (s[l] !== ' ' && l >= 0) {
            l--;
        }
        res.push(s.substring(l + 1, r + 1));
        while (s[l] === ' ' && l >= 0) {
            l--;
        }
        r = l;
    }
    return res.join(' ');
}
```

### **Rust**

传统：

```rust
impl Solution {
    pub fn reverse_words(mut s: String) -> String {
        let mut res = s.trim().split(' ').rev().collect::<Vec<&str>>();
        for i in (0..res.len()).rev() {
            if res[i] == "" {
                res.remove(i);
            }
        }
        res.join(" ")
    }
}
```

函数式：

```rust
impl Solution {
    pub fn reverse_words(s: String) -> String {
        s.split(' ')
            .filter(|str| str != &"")
            .rev()
            .collect::<Vec<_>>()
            .join("")
    }
}
```

使用 `split_whitespace()`：

```rust
impl Solution {
    pub fn reverse_words(s: String) -> String {
        s.split_whitespace().rev().collect::<Vec<_>>().join(" ")
    }
}
```

双指针：

```rust
impl Solution {
    pub fn reverse_words(mut s: String) -> String {
        s = s.trim().to_string();
        // 添加辅助空格，防止 usize 破界
        s.insert_str(0, " ");
        let chars = s.chars().collect::<Vec<char>>();
        let mut res = vec![];
        let mut l = chars.len() - 1;
        let mut r = chars.len() - 1;
        while l > 0 {
            while chars[l] == ' ' {
                if l == 0 {
                    break;
                }
                l -= 1;
            }
            r = l;
            while chars[l] != ' ' {
                if l == 0 {
                    break;
                }
                l -= 1;
            }
            let mut str = String::new();
            for i in l + 1..r + 1 {
                str.push(chars[i]);
            }
            res.push(str);
        }
        res.join(" ")
    }
}
```

### **C#**

```cs
public class Solution {
    public string ReverseWords(string s) {
        string[] tmp = s.Split(' ', StringSplitOptions.RemoveEmptyEntries);
        Stack<string> ss = new Stack<string>();
        string res = "";

        foreach (var i in tmp) {
            ss.Push(i);
        }

        while (ss.Count > 0) {
            res += ss.Pop();
            if (ss.Count > 0) {
                res += " ";
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
