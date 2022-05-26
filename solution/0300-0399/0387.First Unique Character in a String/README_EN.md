# [387. First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string)

[中文文档](/solution/0300-0399/0387.First%20Unique%20Character%20in%20a%20String/README.md)

## Description

<p>

Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

</p>

<p><b>Examples:</b>

<pre>

s = "leetcode"

return 0.



s = "loveleetcode",

return 2.

</pre>

</p>

<p>

<b>Note:</b> You may assume the string contain only lowercase letters.

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def firstUniqChar(self, s: str) -> int:
        chars = {}
        for ch in s:
            ch = ord(ch)
            chars[ch] = chars.get(ch, 0) + 1
        for i, ch in enumerate(s):
            ch = ord(ch)
            if chars[ch] == 1:
                return i
        return -1
```

### **Java**

```java
class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> chars = new HashMap<>(26);
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            chars.put(ch, chars.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            if (chars.get(ch) == 1) return i;
        }
        return -1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
