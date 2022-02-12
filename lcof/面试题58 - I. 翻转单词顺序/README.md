# [面试题 58 - I. 翻转单词顺序](https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/)

## 题目描述

输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。

**示例 1：**

```
输入: "the sky is blue"
输出: "blue is sky the"
```

**示例 2：**

```
输入: "  hello world!  "
输出: "world! hello"
解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
```

**示例 3：**

```
输入: "a good   example"
输出: "example good a"
解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
```

**说明：**

- 无空格字符构成一个单词。
- 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
- 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。

**注意：** 本题与主站 151 题相同：https://leetcode-cn.com/problems/reverse-words-in-a-string/

**注意：** 此题对比原题有改动

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

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {string}
 */
var reverseWords = function (s) {
    return s
        .split(" ")
        .reduce((acc, cur) => (cur !== "" ? acc.concat(cur) : acc), [])
        .reverse()
        .join(" ");
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

### **...**

```

```

<!-- tabs:end -->
