## 实现strStr()
### 题目描述

实现 [strStr()](https://baike.baidu.com/item/strstr/811469) 函数。

给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

**示例 1:**
```
输入: haystack = "hello", needle = "ll"
输出: 2
```

**示例 2:**
```
输入: haystack = "aaaaa", needle = "bba"
输出: -1
```

**说明:**

当 `needle` 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当 `needle` 是空字符串时我们应当返回 0 。这与C语言的 [strstr()](https://baike.baidu.com/item/strstr/811469) 以及 Java的 `indexOf()` 定义相符。

### 解法
遍历 `haystack` 和 `needle`，利用指针 `p`, `q` 分别指向这两个字符串。对于每一个位置对于的字符，如果两字符相等，继续判断下一个位置的字符是否相等；否则 `q` 置为 0，`p` 置为最初匹配的字符的下一个位置，即 `p - q + 1`。

```java
class Solution {
    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        
        int len1 = haystack.length();
        int len2 = needle.length();
        int p = 0;
        int q = 0;
        while (p < len1) {
            if (haystack.charAt(p) == needle.charAt(q)) {
                if (len2 == 1) {
                    return p;
                }
                ++p;
                ++q;
            } else {
                p -= q - 1;
                q = 0;
            }
            
            if (q == len2) {
                return p - q;
            }
        }
        return -1;
    }
}
```