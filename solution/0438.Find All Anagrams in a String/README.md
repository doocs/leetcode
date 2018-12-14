## 找到字符串中所有字母异位词

### 问题描述

给定一个字符串 `s` 和一个非空字符串 `p`，找到 `s` 中所有是 `p` 的字母异位词的子串，返回这些子串的起始索引。

字符串只包含小写英文字母，并且字符串 `s` 和 `p` 的长度都不超过 20100。

**示例1:**
```
输入:
s: "cbaebabacd" p: "abc"

输出:
[0, 6]

解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
```
**示例2:**
```
输入:
s: "abab" p: "ab"

输出:
[0, 1, 2]

解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
```
**提示:**
- 字母异位词指字母相同，但排列不同的字符串。
- 不考虑答案输出的顺序。

### 解法
同`第0567`题。  
使用滑动窗口。窗口宽为`p`的长度，从左到右在`s`上滑动，若窗口内的字母出现频率和`p`中字母出现的频率一样，则此窗口的起始位置为一个起始索引。

```python
class Solution:
    def findAnagrams(self, s, p):
        lens = len(s)
        lenp = len(p)
        if lens < lenp:
            return []
        flag1 = [0] * 26
        flag2 = [0] * 26
        for i, x in enumerate(p):
            flag1[ord(x) - 97] += 1
        ans = []
        for i, x in enumerate(s):
            flag2[ord(x) - 97] += 1
            if i >= lenp:
                flag2[ord(s[i - lenp]) - 97] -= 1
            if flag1 == flag2:
                ans.append(i - lenp + 1)
        return ans
```
