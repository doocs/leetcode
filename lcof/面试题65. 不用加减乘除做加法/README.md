# [面试题 65. 不用加减乘除做加法](https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“\*”、“/” 四则运算符号。

**示例:**

```
输入: a = 1, b = 1
输出: 2
```

**提示：**

- `a`, `b`  均可能是负数或 0
- 结果不会溢出 32 位整数

## 解法

<!-- 这里可写通用的实现逻辑 -->

- 对两数进行按位 `^` 异或运算，得到不进位的和；
- 对两数进行按位 `&` 与运算，然后左移一位，得到进位；
- 循环，直至进位为 0。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

由于 python `int` 是无限长整型，左移不会自动溢出，因此需要特殊处理。

```python
class Solution:
    def add(self, a: int, b: int) -> int:
        a, b = a & 0xffffffff, b & 0xffffffff
        s = carry = 0
        while b:
            s = a ^ b
            carry = ((a & b) << 1) & 0xffffffff
            a, b = s, carry
        return a if a < 0x80000000 else ~(a ^ 0xffffffff)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int add(int a, int b) {
        int sum = 0, carry = 0;
        while (b != 0) {
            sum = a ^ b;
            carry = (a & b) << 1;
            a = sum;
            b = carry;
        }
        return a;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} a
 * @param {number} b
 * @return {number}
 */
var add = function (a, b) {
  if (a === 0) return b;
  return add((a & b) << 1, a ^ b);
};
// (a & b) << 1 是 进位和
// a ^ b 是不进位和
// 两者相加得结果，由于本题禁止 + 号，所以递归
```

### **...**

```

```

<!-- tabs:end -->
