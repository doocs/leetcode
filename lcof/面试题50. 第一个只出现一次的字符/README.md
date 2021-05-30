# [面试题 50. 第一个只出现一次的字符](https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/)

## 题目描述

在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。

**示例:**

```
s = "abaccdeff"
返回 "b"

s = ""
返回 " "
```

**限制：**

- `0 <= s 的长度 <= 50000`

## 解法

对字符串进行两次遍历：

第一遍，先用 hash 表（或数组）统计字符串中每个字符出现的次数。

第二遍，只要遍历到一个只出现一次的字符，那么就返回该字符，否则在遍历结束后返回空格。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def firstUniqChar(self, s: str) -> str:
        counter = collections.Counter(s)
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

### **...**

```

```

<!-- tabs:end -->
