# [面试题 43. 1 ～ n 整数中 1 出现的次数](https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>输入一个整数 <code>n</code> ，求1～n这n个整数的十进制表示中1出现的次数。</p>

<p>例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 12
<strong>输出：</strong>5
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 13
<strong>输出：</strong>6</pre>

<p> </p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 <= n < 2^31</code></li>
</ul>

<p>注意：本题与主站 233 题相同：<a href="https://leetcode.cn/problems/number-of-digit-one/">https://leetcode.cn/problems/number-of-digit-one/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

将 n 拆为两部分：最高位 high 和低位 lows。按 high 是否为 1 分别递归求解结果 f(n)。

以 n=3356 举例说明。

high=3,lows=356,base=1000。此时 n 可拆分为 `0~999`,`1000~1999`,`2000~2999`,`3000~3356`，其中：

-   0~999 范围内 1 的个数为 f(base-1)
-   1000~1999 范围内 1 的个数可分为两部分：千位、其余位。千位都为 1，所以 1 的个数为 base+f(base-1)
-   2000~2999 范围内 1 的个数为 f(base-1)
-   3000~3356 范围内 1 的个数为 f(lows)

因此，1 的总个数为 `high*f(base-1)+f(lows)+base`。

最高位非 1 的情况，也可以按照同样的方法分析。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    @cache
    def countDigitOne(self, n: int) -> int:
        if n < 1:
            return 0
        s = str(n)
        high = int(s[0])
        base = pow(10, len(s) - 1)
        lows = n % base
        return (
            self.countDigitOne(base - 1) + lows + 1 + self.countDigitOne(lows)
            if high == 1
            else high * self.countDigitOne(base - 1) + base + self.countDigitOne(lows)
        )
```

### **Java**

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

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
var countDigitOne = function (n) {
    let res = 0;
    let i = 1;
    while (i <= n) {
        let high = ~~(n / i / 10);
        let cur = ~~(n / i) % 10;
        let low = n - ~~(n / i) * i;
        switch (cur) {
            case 0:
                res += high * i;
                break;
            case 1:
                res += high * i + low + 1;
                break;
            default:
                res += (high + 1) * i;
        }
        i *= 10;
    }
    return res;
};
```

### **C++**

```cpp
class Solution {
public:
    int countDigitOne(int n) {
        long long digit = 1;
        int count = 0;
        int high = n / 10;
        int cur = n % 10;
        int low = 0;
        while (high != 0 || cur != 0) {
            if (cur == 0) {
                count += high * digit;
            } else if (cur == 1) {
                count += high * digit + low + 1;
            } else {
                count += (high + 1) * digit;
            }
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return count;
    }
};
```

### **C#**

```cs
public class Solution {
    public int CountDigitOne(int n) {
        long mulk = 1;
        int ans = 0;
        for (int k = 0; n >= mulk; ++k) {
            ans += (int) (n / (mulk * 10) * mulk) + (int) Math.Min(Math.Max(n % (mulk * 10) - mulk + 1, 0), mulk);
            mulk *= 10;
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
