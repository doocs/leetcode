# [面试题 44. 数字序列中某一位的数字](https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>数字以0123456789101112131415&hellip;的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。</p>

<p>请写一个函数，求任意第n位对应的数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 3
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 11
<strong>输出：</strong>0</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;&nbsp;2^31</code></li>
</ul>

<p>注意：本题与主站 400 题相同：<a href="https://leetcode.cn/problems/nth-digit/">https://leetcode.cn/problems/nth-digit/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

-   pow = 0：0~9 有 10 位
-   pow = 1: 10~99 有 `90*2=180` 位
-   pow = 2: 100~999 有 `900*3=2700` 位。

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
            if n < count:
                break
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

### **C#**

```cs
public class Solution {
    public int FindNthDigit(int n) {
        long length = 1;
        long count = 10;
        long tenBase = 9;
        long lastCount = 0;

        while (count < n)
        {
            length++;
            tenBase *= 10;
            var currentCount = tenBase * length;
            lastCount = count;
            count += currentCount;
        }

        var remainder = n - lastCount;
        var value = remainder / length;
        if (length > 1)
        {
            value += (int)Math.Pow(10, length - 1);
        }

        remainder %= length;
        return value.ToString()[(int)remainder] - '0';
    }
}

```

### **...**

```

```

<!-- tabs:end -->
