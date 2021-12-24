# [面试题 44. 数字序列中某一位的数字](https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

数字以 0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第 5 位（从下标 0 开始计数）是 5，第 13 位是 1，第 19 位是 4，等等。

请写一个函数，求任意第 n 位对应的数字。

**示例 1：**

```
输入：n = 3
输出：3
```

**示例 2：**

```
输入：n = 11
输出：0
```

**限制：**

- `0 <= n < 2^31`

注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/

## 解法

<!-- 这里可写通用的实现逻辑 -->

- pow = 0：0~9 有 10 位
- pow = 1: 10~99 有 `90*2=180` 位
- pow = 2: 100~999 有 `900*3=2700` 位。

先求出第 n 位所在的 pow 和真实数字，进而求出真实数字的第 `n % (pow + 1)` 位即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findNthDigit(self, n: int) -> int:
        def get_bit_num():
            return 10 if p == 0 else 9 * pow(10, p) * (p + 1)

        if n < 10:
            return n
        p = count = 0
        while 1:
            count = get_bit_num()
            if n < count: break
            n -= count
            p += 1
        num = n // (p + 1) + pow(10, p)
        return int(str(num)[n % (p + 1)])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findNthDigit(int n) {
        if (n < 10) return n;
        int pow = 0, count;
        while (true) {
            count = getBitNum(pow);
            if (n < count) break;
            n -= count;
            ++pow;
        }
        int num = n / (pow + 1) + (int) Math.pow(10, pow);
        return String.valueOf(num).charAt(n % (pow + 1)) - '0';
    }

    private int getBitNum(int pow) {
        if (pow == 0) {
            return 10;
        }
        return (int) (9 * Math.pow(10, pow) * (pow + 1));
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
var findNthDigit = function (n) {
    let i = 9;
    let a = 1;
    let remain = n;
    while (i * a < remain) {
        remain -= i * a;
        i *= 10;
        a++;
    }
    let b = remain % a;
    let res = 10 ** (a - 1) + ~~(remain / a);
    if (b === 0) {
        b = a;
        res--;
    }
    return res.toString()[b - 1];
};
```

### **C++**

```cpp
class Solution {
public:
    int findNthDigit(int n) {
        int digit = 1;
        long long start = 0;
        long long count = 10;
        while (n > count) {
            n -= count;
            ++digit;
            start = start == 0 ? 10 : start * 10;
            count = 9 * start * digit;
        }
        long long num = start + n / digit;
        return to_string(num)[n % digit] - '0';
    }
};
```

### **...**

```

```

<!-- tabs:end -->
