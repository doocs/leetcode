# [2309. 兼具大小写的最好英文字母](https://leetcode.cn/problems/greatest-english-letter-in-upper-and-lower-case)

[English Version](/solution/2300-2399/2309.Greatest%20English%20Letter%20in%20Upper%20and%20Lower%20Case/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由英文字母组成的字符串 <code>s</code> ，请你找出并返回 <code>s</code> 中的 <strong>最好</strong> 英文字母。返回的字母必须为大写形式。如果不存在满足条件的字母，则返回一个空字符串。</p>

<p><strong>最好</strong> 英文字母的大写和小写形式必须 <strong>都</strong> 在 <code>s</code> 中出现。</p>

<p>英文字母 <code>b</code> 比另一个英文字母&nbsp;<code>a</code>&nbsp;<strong>更好</strong> 的前提是：英文字母表中，<code>b</code> 在 <code>a</code> 之 <strong>后</strong> 出现。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "l<em><strong>Ee</strong></em>TcOd<em><strong>E</strong></em>"
<strong>输出：</strong>"E"
<strong>解释：</strong>
字母 'E' 是唯一一个大写和小写形式都出现的字母。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a<em><strong>rR</strong></em>AzFif"
<strong>输出：</strong>"R"
<strong>解释：</strong>
字母 'R' 是大写和小写形式都出现的最好英文字母。
注意 'A' 和 'F' 的大写和小写形式也都出现了，但是 'R' 比 'F' 和 'A' 更好。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "AbCdEfGhIjK"
<strong>输出：</strong>""
<strong>解释：</strong>
不存在大写和小写形式都出现的字母。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> 由小写和大写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 枚举**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def greatestLetter(self, s: str) -> str:
        ss = set(s)
        for c in ascii_uppercase[::-1]:
            if c in ss and c.lower() in ss:
                return c
        return ''
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String greatestLetter(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) {
                cnt[c - 'a'] |= 1;
            } else if (Character.isUpperCase(c)) {
                cnt[c - 'A'] |= 2;
            }
        }
        for (int i = 25; i >= 0; --i) {
            if (cnt[i] == 3) {
                return String.valueOf((char) ('A' + i));
            }
        }
        return "";
    }
}
```

```java
class Solution {
    public String greatestLetter(String s) {
        Set<Character> ss = new HashSet<>();
        for (char c : s.toCharArray()) {
            ss.add(c);
        }
        for (char a = 'Z'; a >= 'A'; --a) {
            if (ss.contains(a) && ss.contains((char) (a + 32))) {
                return String.valueOf(a);
            }
        }
        return "";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string greatestLetter(string s) {
        unordered_set<char> ss;
        for (char& c : s) ss.insert(c);
        for (char c = 'Z'; c >= 'A'; --c)
            if (ss.count(c) && ss.count(char(c + 32)))
                return string(1, c);
        return "";
    }
};
```

### **Go**

```go
func greatestLetter(s string) string {
	ss := map[rune]bool{}
	for _, c := range s {
		ss[c] = true
	}
	for c := 'Z'; c >= 'A'; c-- {
		if ss[c] && ss[rune(c+32)] {
			return string(c)
		}
	}
	return ""
}
```

### **TypeScript**

```ts
function greatestLetter(s: string): string {
    let couter = new Array(128).fill(false);
    for (let char of s) {
        couter[char.charCodeAt(0)] = true;
    }
    for (let i = 90; i >= 65; i--) {
        if (couter[i] && couter[i + 32]) return String.fromCharCode(i);
    }
    return '';
}
```

### **Rust**

```rust
impl Solution {
    pub fn greatest_letter(s: String) -> String {
        let mut arr = [0; 26];
        for &c in s.as_bytes().iter() {
            if c >= b'a' {
                arr[(c - b'a') as usize] |= 1;
            } else {
                arr[(c - b'A') as usize] |= 2;
            }
        }
        for i in (0..26).rev() {
            if arr[i] == 3 {
                return char::from(b'A' + i as u8).to_string();
            }
        }
        "".to_string()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
