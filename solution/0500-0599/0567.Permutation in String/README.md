## 字符串的排列

### 问题描述

给定两个字符串 `s1` 和 `s2`，写一个函数来判断 `s2` 是否包含 `s1` 的排列。

换句话说，第一个字符串的排列之一是第二个字符串的子串。

**示例1:**
```
输入: s1 = "ab" s2 = "eidbaooo"
输出: True
解释: s2 包含 s1 的排列之一 ("ba").
```
**示例2:**
```
输入: s1= "ab" s2 = "eidboaoo"
输出: False
```
**提示:**
- 输入的字符串只包含小写字母
- 两个字符串的长度都在 [1, 10,000] 之间

### 解法
使用滑动窗口，窗口宽为`s1`的长度，从左到右在`s2`上滑动，若窗口内的字母出现频率和`s1`中字母出现的频率一样，则`s2` 包含 `s1` 的排列。若不存在则`s2` 不包含 `s1` 的排列。

```python
class Solution:
    def checkInclusion(self, s1, s2):
        if len(s1) > len(s2):
            return False
        flag1 = [0] * 26
        flag2 = [0] * 26
        for i, x in enumerate(s1):
            flag1[ord(x) - ord('a')] += 1
        for i, x in enumerate(s2):
            flag2[ord(x) - ord('a')] += 1
            if i >= len(s1):
                flag2[ord(s2[i - len(s1)]) - ord('a')] -= 1
            if flag1 == flag2:
                return True
        return False
```
