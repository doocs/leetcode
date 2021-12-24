# [面试题 20. 表示数值的字符串](https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"0123"及"-1E-16"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。

注意：本题与主站 65 题相同：https://leetcode-cn.com/problems/valid-number/

## 解法

<!-- 这里可写通用的实现逻辑 -->

遍历字符串：

- 出现 `+`/`-` 时，位置必须是在第 0 位，或者 `e`/`E` 的后面一位
- 出现 `.` 时，在此之前不能出现 `.` 或者 `e`/`E`
- 出现 `e`/`E` 时，前面不能出现 `e`/`E`，并且必须出现过数字

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isNumber(self, s: str) -> bool:
        if not s or not s.strip():
            return False
        s = s.strip()
        find_num = find_dot = find_e = False
        for i in range(len(s)):
            if s[i] == '+' or s[i] == '-':
                if i != 0 and s[i - 1] != 'e' and s[i - 1] != 'E':
                    return False
            elif s[i] >= '0' and s[i] <= '9':
                find_num = True
            elif s[i] == '.':
                if find_dot or find_e:
                    return False
                find_dot = True
            elif s[i] == 'e' or s[i] == 'E':
                if not find_num or find_e:
                    return False
                find_e = True
                find_num = False
            else:
                return False
        return find_num
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isNumber(String s) {
        if (s == null || s.trim().length() == 0) {
            return false;
        }
        char[] chars = s.trim().toCharArray();
        boolean findNum = false;
        boolean findE = false;
        boolean findDot = false;
        for (int i = 0, n = chars.length; i < n; ++i) {
            if (chars[i] == '+' || chars[i] == '-') {
                if (i != 0 && chars[i - 1] != 'e' && chars[i - 1] != 'E') {
                    return false;
                }
            } else if (chars[i] >= '0' && chars[i] <= '9') {
                findNum = true;
            } else if (chars[i] == '.') {
                if (findDot || findE) {
                    return false;
                }
                findDot = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                if (findE || !findNum) {
                    return false;
                }
                findE = true;
                findNum = false; // 确保e之后也出现数
            } else {
                return false;
            }
        }
        return findNum;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var isNumber = function (s) {
    return s !== " " && !isNaN(+s);
};
```

### **...**

```

```

<!-- tabs:end -->
