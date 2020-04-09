# [面试题50. 第一个只出现一次的字符](https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/)

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
有序字典解决。

### Python3
```python
import collections

class Solution:
    def firstUniqChar(self, s: str) -> str:
        if s == '':
            return ' '
        cache = collections.OrderedDict()
        for c in s:
            cache[c] = 1 if cache.get(c) is None else cache[c] + 1
        for k, v in cache.items():
            if v == 1:
                return k
        return ' '
```

### Java
```java
class Solution {
    public char firstUniqChar(String s) {
        if ("".equals(s)) {
            return ' ';
        }
        Map<Character, Integer> map = new LinkedHashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            map.put(c, map.get(c) == null ? 1 : 1 + map.get(c));
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return ' ';
    }
}
```

### ...
```

```
