## 字符串解码

### 问题描述

给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: `k[encoded_string]`，表示其中方括号内部的 `encoded_string` 正好重复 `k`
 次。注意 `k` 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要
求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 `k` ，例如不会出现像
`3a` 或 `2[4]` 的输入。

**示例:**
```
s = "3[a]2[bc]", 返回 "aaabcbc".
s = "3[a2[c]]", 返回 "accaccacc".
s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
```


### 解法

递归求解。设所求为`ans`。遍历字符串，每次遇到字母，则加入`ans`；每次遇到数字，则存下；
遇到`[`，则找到与其匹配的`]`，并个位置之间的进行递归求解，并将所得结果乘以遇到的数字，
再加入`ans`。

```python
class Solution(object):
    def decodeString(self, s):
        def deco(s):
            if '[' not in s and ']' not in s:
                return s
            i = j = 0
            ans = ''
            count = ''
            while i < len(s):
                if s[i].isdigit():
                    count += s[i]
                    i += 1
                elif s[i].isalpha():
                    ans += s[i]
                    i += 1
                elif s[i] == '[':
                    j = i + 1
                    zuo = 0
                    while j < len(s):
                        if s[j] == '[':
                            zuo += 1
                            j += 1
                        elif s[j] == ']':
                            if zuo != 0:
                                zuo -= 1
                                j += 1
                            else:
                                if not count:
                                    ans += deco(s[i + 1:j])
                                else:
                                    ans += int(count) * deco(s[i + 1:j])
                                    count = ''
                                i = j + 1
                                break
                        else:
                            j += 1
            return ans
        return deco(s)
```
