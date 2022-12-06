# [1805. 字符串中不同整数的数目](https://leetcode.cn/problems/number-of-different-integers-in-a-string)

[English Version](/solution/1800-1899/1805.Number%20of%20Different%20Integers%20in%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>word</code> ，该字符串由数字和小写英文字母组成。</p>

<p>请你用空格替换每个不是数字的字符。例如，<code>"a123bc34d8ef34"</code> 将会变成 <code>" 123  34 8  34"</code> 。注意，剩下的这些整数为（相邻彼此至少有一个空格隔开）：<code>"123"</code>、<code>"34"</code>、<code>"8"</code> 和 <code>"34"</code> 。</p>

<p>返回对 <code>word</code> 完成替换后形成的 <strong>不同</strong> 整数的数目。</p>

<p>只有当两个整数的 <strong>不含前导零</strong> 的十进制表示不同， 才认为这两个整数也不同。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word = "a<strong>123</strong>bc<strong>34</strong>d<strong>8</strong>ef<strong>34</strong>"
<strong>输出：</strong>3
<strong>解释：</strong>不同的整数有 "123"、"34" 和 "8" 。注意，"34" 只计数一次。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word = "leet<strong>1234</strong>code<strong>234</strong>"
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>word = "a<strong>1</strong>b<strong>01</strong>c<strong>001</strong>"
<strong>输出：</strong>1
<strong>解释：</strong>"1"、"01" 和 "001" 视为同一个整数的十进制表示，因为在比较十进制值时会忽略前导零的存在。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= word.length <= 1000</code></li>
	<li><code>word</code> 由数字和小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针 + 模拟**

遍历字符串 `word`，找到每个整数的起始位置和结束位置，截取出这一个子串，将其存入哈希表 $s$ 中。

遍历结束，返回哈希表 $s$ 的大小即可。

> 注意，每个子串所表示的整数可能很大，我们不能直接将其转为整数。因此，我们可以去掉每个子串的前导零之后，再存入哈希表。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 `word` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numDifferentIntegers(self, word: str) -> int:
        s = set()
        i, n = 0, len(word)
        while i < n:
            if word[i].isdigit():
                while i < n and word[i] == '0':
                    i += 1
                j = i
                while j < n and word[j].isdigit():
                    j += 1
                s.add(word[i: j])
                i = j
            i += 1
        return len(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numDifferentIntegers(String word) {
        Set<String> s = new HashSet<>();
        int n = word.length();
        for (int i = 0; i < n; ++i) {
            if (Character.isDigit(word.charAt(i))) {
                while (i < n && word.charAt(i) == '0') {
                    ++i;
                }
                int j = i;
                while (j < n && Character.isDigit(word.charAt(j))) {
                    ++j;
                }
                s.add(word.substring(i, j));
                i = j;
            }
        }
        return s.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numDifferentIntegers(string word) {
        unordered_set<string> s;
        int n = word.size();
        for (int i = 0; i < n; ++i) {
            if (isdigit(word[i])) {
                while (i < n && word[i] == '0') ++i;
                int j = i;
                while (j < n && isdigit(word[j])) ++j;
                s.insert(word.substr(i, j - i));
                i = j;
            }
        }
        return s.size();
    }
};
```

### **Go**

```go
func numDifferentIntegers(word string) int {
	s := map[string]struct{}{}
	n := len(word)
	for i := 0; i < n; i++ {
		if word[i] >= '0' && word[i] <= '9' {
			for i < n && word[i] == '0' {
				i++
			}
			j := i
			for j < n && word[j] >= '0' && word[j] <= '9' {
				j++
			}
			s[word[i:j]] = struct{}{}
			i = j
		}
	}
	return len(s)
}
```

### **TypeScript**

```ts
function numDifferentIntegers(word: string): number {
    return new Set(
        word
            .replace(/\D+/g, ' ')
            .trim()
            .split(' ')
            .filter(v => v !== '')
            .map(v => v.replace(/^0+/g, '')),
    ).size;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn num_different_integers(word: String) -> i32 {
        let s = word.as_bytes();
        let n = s.len();
        let mut set = HashSet::new();
        let mut i = 0;
        while i < n {
            if s[i] >= b'0' && s[i] <= b'9' {
                let mut j = i;
                while j < n && s[j] >= b'0' && s[j] <= b'9' {
                    j += 1;
                }
                while i < j - 1 && s[i] == b'0' {
                    i += 1;
                }
                set.insert(String::from_utf8(s[i..j].to_vec()).unwrap());
                i = j;
            } else {
                i += 1;
            }
        }
        set.len() as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
