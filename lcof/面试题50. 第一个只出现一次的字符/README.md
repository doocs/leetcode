# [面试题 50. 第一个只出现一次的字符](https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/)

## 题目描述

<p>在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。</p>

<p><strong>示例 1:</strong></p>

<pre>
输入：s = "abaccdeff"
输出：'b'
</pre>

<p><strong>示例 2:</strong></p>

<pre>
输入：s = "" 
输出：' '
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= s 的长度 &lt;= 50000</code></p>

## 解法

对字符串进行两次遍历：

第一遍，先用 hash 表（或数组）统计字符串中每个字符出现的次数。

第二遍，只要遍历到一个只出现一次的字符，那么就返回该字符，否则在遍历结束后返回空格。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def firstUniqChar(self, s: str) -> str:
        counter = Counter(s)
        for c in s:
            if counter[c] == 1:
                return c
        return ' '
```

### **Java**

```java
class Solution {
    public char firstUniqChar(String s) {
        int n;
        if ((n = s.length()) == 0) return ' ';
        int[] counter = new int[26];
        for (int i = 0; i < n; ++i) {
            int index = s.charAt(i) - 'a';
            ++counter[index];
        }
        for (int i = 0; i < n; ++i) {
            int index = s.charAt(i) - 'a';
            if (counter[index] == 1) return s.charAt(i);
        }
        return ' ';
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {character}
 */
var firstUniqChar = function (s) {
  if (s.length == 0) return " ";
  let counter = new Array(26).fill(0);
  for (let i = 0; i < s.length; ++i) {
    const index = s[i].charCodeAt() - "a".charCodeAt();
    ++counter[index];
  }
  for (let i = 0; i < s.length; ++i) {
    const index = s[i].charCodeAt() - "a".charCodeAt();
    if (counter[index] == 1) return s[i];
  }
  return " ";
};
```

### **C++**

```cpp
class Solution {
public:
    char firstUniqChar(string s) {
        unordered_map<char, bool> um;
        for (char c : s) {
            um[c] = um.find(c) == um.end();
        }
        for (char c : s) {
            if (um[c]) {
                return c;
            }
        }
        return ' ';
    }
};
```

### **TypeScript**

```ts
function firstUniqChar(s: string): string {
  const map = new Map();
  for (const c of s) {
    map.set(c, !map.has(c));
  }
  for (const c of s) {
    if (map.get(c)) {
      return c;
    }
  }
  return " ";
}
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    pub fn first_uniq_char(s: String) -> char {
        let mut map = HashMap::new();
        let s = s.chars().collect::<Vec<char>>();
        for c in s.iter() {
            match map.contains_key(c) {
                true => map.insert(c, false),
                false => map.insert(c, true),
            };
        }
        for c in s.iter() {
            if let Some(is_single) = map.get(c) {
                if *is_single {
                    return *c;
                }
            }
        }
        ' '
    }
}
```

### **...**

```

```

<!-- tabs:end -->
