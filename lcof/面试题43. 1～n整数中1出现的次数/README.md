# [面试题43. 1～n整数中1出现的次数](https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/)

## 题目描述
<!-- 这里写题目描述 -->
输入一个整数 `n` ，求1～n这n个整数的十进制表示中1出现的次数。

例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。

**示例 1：**

```
输入：n = 12
输出：5
```

**示例 2：**

```
输入：n = 13
输出：6
```

**限制：**

- `1 <= n < 2^31`

注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/

## 解法
<!-- 这里可写通用的实现逻辑 -->
将 n 拆为两部分：最高位 high 和低位 lows。按 high 是否为 1 分别递归求解结果 f(n)。

以 n=3356 举例说明。

high=3,lows=356,base=1000。此时 n 可拆分为 `0~999`,`1000~1999`,`2000~2999`,`3000~3356`，其中：

- 0~999 范围内 1 的个数为 f(base-1)
- 1000~1999 范围内 1 的个数可分为两部分：千位、其余位。千位都为 1，所以 1 的个数为 base+f(base-1)
- 2000~2999 范围内 1 的个数为 f(base-1)
- 3000~3356 范围内 1 的个数为 f(lows)

因此，1 的总个数为 `high*f(base-1)+f(lows)+base`。

最高位非 1 的情况，也可以按照同样的方法分析。

### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
from functools import lru_cache

class Solution:
    @lru_cache
    def countDigitOne(self, n: int) -> int:
        if n < 1:
            return 0
        s = str(n)
        high = int(s[0])
        base = pow(10, len(s) - 1)
        lows = n % base
        return self.countDigitOne(base - 1) + lows + 1 + self.countDigitOne(lows) if high == 1 else high * self.countDigitOne(base - 1) + base + self.countDigitOne(lows)
```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countDigitOne(int n) {
        if (n < 1) {
            return 0;
        }
        String s = String.valueOf(n);
        int high = s.charAt(0) - '0'; // 最高位
        int base = (int) Math.pow(10, s.length() - 1); // 基数
        int lows = n % base; // 低位
        return high == 1
            ? countDigitOne(base - 1) + countDigitOne(lows) + lows + 1
            : high * countDigitOne(base - 1) + countDigitOne(lows) + base;
    }
}
```

### JavaScript
```js
/**
 * @param {number} n
 * @return {number}
 */
var countDigitOne = function(n) {
    let res = 0
    let i=1
    while(i <= n) {
        let high = ~~(n / i / 10)
        let cur = ~~(n / i)  % 10
        let low = n - ~~(n / i) * i
        switch(cur) {
            case 0: res += high * i;break
            case 1: res += high * i + low + 1;break
            default: res += (high + 1) * i
        }
        i *= 10
    }
    return res
};
```

### ...
```

```
