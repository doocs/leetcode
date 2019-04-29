## 无重复字符的最长子串
### 题目描述

给定一个字符串，找出不含有重复字符的**最长子串**的长度。

**示例 1:**
```
输入: "abcabcbb"
输出: 3 
解释: 无重复字符的最长子串是 "abc"，其长度为 3。
```

**示例 2:**
```
输入: "bbbbb"
输出: 1
解释: 无重复字符的最长子串是 "b"，其长度为 1。
```

**示例 3:**
```
输入: "pwwkew"
输出: 3
解释: 无重复字符的最长子串是 "wke"，其长度为 3。
     请注意，答案必须是一个子串，"pwke" 是一个子序列 而不是子串。
```

### 解法
利用指针 `p`, `q`，初始指向字符串开头。遍历字符串，`q` 向右移动，若指向的字符在 map 中，说明出现了重复字符，此时，`p` 要在出现**重复字符的下一个位置** `map.get(chars[q]) + 1` 和**当前位置** `p` 之间取较大值，防止 `p` 指针回溯。循环的过程中，要将 chars[q] 及对应位置放入 map 中，也需要不断计算出`max` 与 `q - p + 1` 的较大值，赋给 `max`。最后输出 `max` 即可。

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        int p = 0, q = 0;
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (q < len) {
            if (map.containsKey(chars[q])) {
                // 防止p指针回溯，导致计算到重复字符的长度
                // eg. abba,当q指向最右的a时，若简单把p赋为map.get(chars[q] + 1)，则出现指针回溯
                p = Math.max(p, map.get(chars[q]) + 1);
            }
            map.put(chars[q], q);
            max = Math.max(max, q - p + 1);
            ++q;
        }
        
        return max;
    }
}
```