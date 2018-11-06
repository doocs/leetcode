## 两数相除
### 题目描述
给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

返回被除数 dividend 除以除数 divisor 得到的商。

```
示例 1:

输入: dividend = 10, divisor = 3
输出: 3

示例 2:

输入: dividend = 7, divisor = -3
输出: -2
```

说明:

被除数和除数均为 32 位有符号整数。
除数不为 0。
假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31 ,  2^31 − 1]。
本题中，如果除法结果溢出，则返回 2^31 − 1。

### 解法
1. 考虑用位运算来代替乘除，用二进制表示商，则只要确定了每一个二进制位，则把这些位加和即可得到商；
2. 对除数进行移位，找到最高位，然后从高到低依次比较每一位对应的数与除数的乘积，若大于则说明商的该位为1，否则为0；

```java
class Solution {
    public int divide(int dividend, int divisor) {
        if(dividend == 0 || divisor == 1) {
            return dividend;
        }
        if(divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
            return Integer.MAX_VALUE;
        }
        // 商的符号，true 为正，false 为负
        boolean flag = true;
        if((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            flag = false;
        }
        long dividendLong = Math.abs((long)dividend);
        long divisorLong = Math.abs((long)divisor);

        int re = 0;
        long factor = 0x1;

        while (dividendLong >= (divisorLong << 1)) {
            divisorLong <<= 1;
            factor <<= 1;
        }

        while (factor > 0 && dividendLong > 0) {
            if(dividendLong >= divisorLong) {
                dividendLong -= divisorLong;
                re += factor;
            }
            factor >>>= 1;
            divisorLong >>>= 1;
        }

        return flag ? re : -re;
    }
}
```