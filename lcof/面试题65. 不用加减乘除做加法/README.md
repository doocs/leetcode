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

两数字的二进制形式 a,b ，求和 s = a + b ，a(i)、b(i) 分别表示 a、b 的第 i 个二进制位。一共有 4 种情况：

| a(i) | b(i) | 不进位的和 | 进位 |
| ---- | ---- | ---------- | ---- |
| 0    | 0    | 0          | 0    |
| 0    | 1    | 1          | 0    |
| 1    | 0    | 1          | 0    |
| 1    | 1    | 0          | 1    |

观察可以发现，“不进位的和”与“异或运算”有相同规律，而进位则与“与”运算规律相同，并且需要左移一位。

- 对两数进行按位 `^` 异或运算，得到不进位的和；
- 对两数进行按位 `&` 与运算，然后左移一位，得到进位；
- 问题转换为求：“不进位的数 + 进位” 之和；
- 循环，直至进位为 0，返回不进位的数即可（也可以用递归实现）。

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

迭代：

```java
class Solution {
    public int add(int a, int b) {
        while (b != 0) {
            int s = a ^ b;
            b = (a & b) << 1;
            a = s;
        }
        return a;
    }
}
```

递归：

```java
class Solution {
    public int add(int a, int b) {
        if (b == 0) return a;
        return add(a ^ b, (a & b) << 1);
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
    if (b == 0) return a;
    return add(a ^ b, (a & b) << 1);
};
```

### **Go**

```go
func add(a int, b int) int {
    if b == 0 {
        return a
    }
    return add(a ^ b, (a & b) << 1)
}
```

### **...**

```

```

<!-- tabs:end -->
