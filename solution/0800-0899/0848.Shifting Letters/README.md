## 字母移位

### 问题描述

有一个由小写字母组成的字符串 `S`，和一个整数数组 `shifts`。

我们将字母表中的下一个字母称为原字母的 移位（由于字母表是环绕的， `'z'` 将会变成 `'a'`）。

例如·，`shift('a') = 'b'`， `shift('t') = 'u'`,， 以及 `shift('z') = 'a'`。

对于每个 `shifts[i] = x`， 我们会将 `S` 中的前 `i+1` 个字母移位 `x` 次。

返回将所有这些移位都应用到 `S` 后最终得到的字符串。

**示例:**
```
输入：S = "abc", shifts = [3,5,9]
输出："rpl"
解释：
我们以 "abc" 开始。
将 S 中的第 1 个字母移位 3 次后，我们得到 "dbc"。
再将 S 中的前 2 个字母移位 5 次后，我们得到 "igc"。
最后将 S 中的这 3 个字母移位 9 次后，我们得到答案 "rpl"。
```

**提示:**
- 1 <= S.length = shifts.length <= 20000
- 0 <= shifts[i] <= 10 ^ 9

### 解法

对于`S`中的每个字母，先将需要移动的长度求出，然后直接移位即可。从后往前遍历会比从前往后遍历快一点点，因为从前往后需要先求出`shifts`的和。

```python
class Solution:
    def shiftingLetters(self, S, shifts):
        mov = 0
        ans = list(S)
        for i in range(len(S) - 1, -1, -1):
            mov += shifts[i]
            ans[i] = chr((ord(S[i]) - 97 + mov % 26) % 26 + 97)
        return ''.join(ans)

```
