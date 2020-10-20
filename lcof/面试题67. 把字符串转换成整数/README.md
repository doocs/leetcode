# [面试题 67. 把字符串转换成整数](https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0。

说明：

假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为  `[−2^31, 2^31 − 1]`。如果数值超过这个范围，请返回  `INT_MAX (2^31 − 1)` 或  `INT_MIN (−2^31)` 。

**示例  1:**

```
输入: "42"
输出: 42
```

**示例  2:**

```
输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
```

**示例  3:**

```
输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
```

**示例  4:**

```
输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。
```

**示例  5:**

```
输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
     因此返回 INT_MIN (−2^31) 。
```

## 解法

<!-- 这里可写通用的实现逻辑 -->

遍历字符串，注意做溢出处理。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def strToInt(self, str: str) -> int:
        if str is None or len(str.strip()) == 0:
            return 0
        str = str.strip()
        i = res = 0
        negative = str[0] == '-'
        i += 1 if str[0] == '-' or str[0] == '+' else 0
        while i < len(str) and str[i].isdigit():
            r = int(str[i])
            if res > (2**31 // 10) or (res == (2**31 // 10) and r > 7):
                return 2**31 - 1 if not negative else -2**31
            res = res * 10 + r
            i += 1
        return -res if negative else res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int strToInt(String str) {
        if (str == null || "".equals(str.trim())) {
            return 0;
        }
        str = str.trim();
        int res = 0, i = 0, flag = 1;
        int n = str.length();
        if (str.charAt(i) == '-') {
            flag = -1;
        }
        if (str.charAt(i) == '-' || str.charAt(i) == '+') {
            ++i;
        }
        while (i < n && Character.isDigit(str.charAt(i))) {
            int r = str.charAt(i) - '0';
            // 溢出处理
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10) && r > 7) {
                return flag > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + r;
            ++i;
        }

        return flag > 0 ? res : -res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} str
 * @return {number}
 */
var strToInt = function (str) {
  let res = "";
  let l = 1;
  for (let i = 0; i < str.length; i++) {
    if (l && str[i] === " ") continue;
    if (l && (str[i] === "+" || str[i] === "-")) {
      l = 0;
      res += str[i];
      continue;
    }
    if (str[i].match(/[0-9]/)) {
      l = 0;
      res += str[i];
    } else break;
  }
  res = isNaN(+res) ? 0 : +res;
  if (res > 2147483647) return 2147483647;
  if (res < -2147483648) return -2147483648;
  return res;
};
```

### **...**

```

```

<!-- tabs:end -->
