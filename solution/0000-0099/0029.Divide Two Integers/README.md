# [29. 两数相除](https://leetcode.cn/problems/divide-two-integers)

[English Version](/solution/0000-0099/0029.Divide%20Two%20Integers/README_EN.md)

<!-- tags:位运算,数学 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数，被除数&nbsp;<code>dividend</code>&nbsp;和除数&nbsp;<code>divisor</code>。将两数相除，要求 <strong>不使用</strong> 乘法、除法和取余运算。</p>

<p>整数除法应该向零截断，也就是截去（<code>truncate</code>）其小数部分。例如，<code>8.345</code> 将被截断为 <code>8</code> ，<code>-2.7335</code> 将被截断至 <code>-2</code> 。</p>

<p>返回被除数&nbsp;<code>dividend</code>&nbsp;除以除数&nbsp;<code>divisor</code>&nbsp;得到的 <strong>商</strong> 。</p>

<p><strong>注意：</strong>假设我们的环境只能存储 <strong>32 位</strong> 有符号整数，其数值范围是 <code>[−2<sup>31</sup>,&nbsp; 2<sup>31&nbsp;</sup>− 1]</code> 。本题中，如果商 <strong>严格大于</strong> <code>2<sup>31&nbsp;</sup>− 1</code> ，则返回 <code>2<sup>31&nbsp;</sup>− 1</code> ；如果商 <strong>严格小于</strong> <code>-2<sup>31</sup></code> ，则返回 <code>-2<sup>31</sup></code><sup> </sup>。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> dividend = 10, divisor = 3
<strong>输出:</strong> 3
<strong>解释: </strong>10/3 = 3.33333.. ，向零截断后得到 3 。</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> dividend = 7, divisor = -3
<strong>输出:</strong> -2
<strong>解释:</strong> 7/-3 = -2.33333.. ，向零截断后得到 -2 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= dividend, divisor &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>divisor != 0</code></li>
</ul>

## 解法

### 方法一：模拟 + 快速幂

除法本质上就是减法，题目要求我们计算出两个数相除之后的取整结果，其实就是计算被除数是多少个除数加上一个小于除数的数构成的。但是一次循环只能做一次减法，效率太低会导致超时，可借助快速幂的思想进行优化。

需要注意的是，由于题目明确要求最大只能使用 32 位有符号整数，所以需要将除数和被除数同时转换为负数进行计算。因为转换正数可能会导致溢出，如当被除数为 `INT32_MIN` 时，转换为正数时会大于 `INT32_MAX`。

假设被除数为 $a$，除数为 $b$，则时间复杂度为 $O(\log a \times \log b)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def divide(self, a: int, b: int) -> int:
        if b == 1:
            return a
        if a == -(2**31) and b == -1:
            return 2**31 - 1
        sign = (a > 0 and b > 0) or (a < 0 and b < 0)
        a = -a if a > 0 else a
        b = -b if b > 0 else b
        ans = 0
        while a <= b:
            x = b
            cnt = 1
            while x >= (-(2**30)) and a <= (x << 1):
                x <<= 1
                cnt <<= 1
            a -= x
            ans += cnt
        return ans if sign else -ans
```

```java
class Solution {
    public int divide(int a, int b) {
        if (b == 1) {
            return a;
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        boolean sign = (a > 0 && b > 0) || (a < 0 && b < 0);
        a = a > 0 ? -a : a;
        b = b > 0 ? -b : b;
        int ans = 0;
        while (a <= b) {
            int x = b;
            int cnt = 1;
            while (x >= (Integer.MIN_VALUE >> 1) && a <= (x << 1)) {
                x <<= 1;
                cnt <<= 1;
            }
            ans += cnt;
            a -= x;
        }
        return sign ? ans : -ans;
    }
}
```

```cpp
class Solution {
public:
    int divide(int a, int b) {
        if (b == 1) {
            return a;
        }
        if (a == INT_MIN && b == -1) {
            return INT_MAX;
        }
        bool sign = (a > 0 && b > 0) || (a < 0 && b < 0);
        a = a > 0 ? -a : a;
        b = b > 0 ? -b : b;
        int ans = 0;
        while (a <= b) {
            int x = b;
            int cnt = 1;
            while (x >= (INT_MIN >> 1) && a <= (x << 1)) {
                x <<= 1;
                cnt <<= 1;
            }
            ans += cnt;
            a -= x;
        }
        return sign ? ans : -ans;
    }
};
```

```go
func divide(a int, b int) int {
	if b == 1 {
		return a
	}
	if a == math.MinInt32 && b == -1 {
		return math.MaxInt32
	}

	sign := (a > 0 && b > 0) || (a < 0 && b < 0)
	if a > 0 {
		a = -a
	}
	if b > 0 {
		b = -b
	}
	ans := 0

	for a <= b {
		x := b
		cnt := 1
		for x >= (math.MinInt32>>1) && a <= (x<<1) {
			x <<= 1
			cnt <<= 1
		}
		ans += cnt
		a -= x
	}

	if sign {
		return ans
	}
	return -ans
}
```

```ts
function divide(a: number, b: number): number {
    if (b === 1) {
        return a;
    }
    if (a === -(2 ** 31) && b === -1) {
        return 2 ** 31 - 1;
    }

    const sign: boolean = (a > 0 && b > 0) || (a < 0 && b < 0);
    a = a > 0 ? -a : a;
    b = b > 0 ? -b : b;
    let ans: number = 0;

    while (a <= b) {
        let x: number = b;
        let cnt: number = 1;

        while (x >= -(2 ** 30) && a <= x << 1) {
            x <<= 1;
            cnt <<= 1;
        }

        ans += cnt;
        a -= x;
    }

    return sign ? ans : -ans;
}
```

```cs
public class Solution {
    public int Divide(int a, int b) {
        if (b == 1) {
            return a;
        }
        if (a == int.MinValue && b == -1) {
            return int.MaxValue;
        }
        bool sign = (a > 0 && b > 0) || (a < 0 && b < 0);
        a = a > 0 ? -a : a;
        b = b > 0 ? -b : b;
        int ans = 0;
        while (a <= b) {
            int x = b;
            int cnt = 1;
            while (x >= (int.MinValue >> 1) && a <= (x << 1)) {
                x <<= 1;
                cnt <<= 1;
            }
            ans += cnt;
            a -= x;
        }
        return sign ? ans : -ans;
    }
}
```

```php
class Solution {
    /**
     * @param integer $dividend
     * @param integer $divisor
     * @return integer
     */

    function divide($dividend, $divisor) {

        if ($divisor == 0) {
            throw new Exception("Can not divide by 0");
        } else if ($dividend == 0) {
            return 0;
        }
        if ($dividend == -2147483648 && $divisor == -1) {
            return 2147483647;
        }
        $isNegative = ($dividend < 0) != ($divisor < 0);

        $dividend = abs($dividend);
        $divisor = abs($divisor);
        $quotient = 0;
        while ($dividend >= $divisor) {
            $tempDivisor = $divisor;
            $count = 1;
            while ($dividend >= ($tempDivisor << 1)) {
                $tempDivisor <<= 1;
                $count <<= 1;
            }
            $dividend -= $tempDivisor;
            $quotient += $count;
        }

        return $isNegative ? -$quotient : $quotient;
    }
}
```

<!-- tabs:end -->

<!-- end -->
