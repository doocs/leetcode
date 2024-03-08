# [29. Divide Two Integers](https://leetcode.com/problems/divide-two-integers)

[中文文档](/solution/0000-0099/0029.Divide%20Two%20Integers/README.md)

<!-- tags:Bit Manipulation,Math -->

## Description

<p>Given two integers <code>dividend</code> and <code>divisor</code>, divide two integers <strong>without</strong> using multiplication, division, and mod operator.</p>

<p>The integer division should truncate toward zero, which means losing its fractional part. For example, <code>8.345</code> would be truncated to <code>8</code>, and <code>-2.7335</code> would be truncated to <code>-2</code>.</p>

<p>Return <em>the <strong>quotient</strong> after dividing </em><code>dividend</code><em> by </em><code>divisor</code>.</p>

<p><strong>Note: </strong>Assume we are dealing with an environment that could only store integers within the <strong>32-bit</strong> signed integer range: <code>[&minus;2<sup>31</sup>, 2<sup>31</sup> &minus; 1]</code>. For this problem, if the quotient is <strong>strictly greater than</strong> <code>2<sup>31</sup> - 1</code>, then return <code>2<sup>31</sup> - 1</code>, and if the quotient is <strong>strictly less than</strong> <code>-2<sup>31</sup></code>, then return <code>-2<sup>31</sup></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> dividend = 10, divisor = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> 10/3 = 3.33333.. which is truncated to 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> dividend = 7, divisor = -3
<strong>Output:</strong> -2
<strong>Explanation:</strong> 7/-3 = -2.33333.. which is truncated to -2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= dividend, divisor &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>divisor != 0</code></li>
</ul>

## Solutions

### Solution 1: Simulation + Fast Power

Division is essentially subtraction. The problem requires us to calculate the integer result after dividing two numbers, which is actually calculating how many divisors and a number less than the divisor constitute the dividend. However, only one subtraction can be done in one loop, which is too inefficient and will lead to timeout. This can be optimized by using the idea of fast power.

It should be noted that since the problem explicitly requires that only 32-bit signed integers can be used at most, the divisor and dividend need to be converted to negative numbers for calculation. Because converting to positive numbers may cause overflow, such as when the dividend is `INT32_MIN`, it will be greater than `INT32_MAX` when converted to a positive number.

Assuming the dividend is $a$ and the divisor is $b$, the time complexity is $O(\log a \times \log b)$, and the space complexity is $O(1)$.

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
     * @param integer $a
     * @param integer $b
     * @return integer
     */

    function divide($a, $b) {

        if ($b == 0) {
            throw new Exception("Can not divide by 0");
        } else if ($a == 0) {
            return 0;
        }
        if ($a == -2147483648 && $b == -1) {
            return 2147483647;
        }
        $sign = ($a < 0) != ($b < 0);

        $a = abs($a);
        $b = abs($b);
        $ans = 0;
        while ($a >= $b) {
            $x = $b;
            $cnt = 1;
            while ($a >= ($x << 1)) {
                $x <<= 1;
                $cnt <<= 1;
            }
            $a -= $x;
            $ans += $cnt;
        }

        return $sign ? -$ans : $ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
