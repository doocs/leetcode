---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20001.%20%E6%95%B4%E6%95%B0%E9%99%A4%E6%B3%95/README.md
---

# [剑指 Offer II 001. 整数除法](https://leetcode.cn/problems/xoh6Oh)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个整数 <code>a</code> 和 <code>b</code> ，求它们的除法的商 <code>a/b</code> ，要求不得使用乘号 <code>'*'</code>、除号 <code>'/'</code> 以及求余符号 <code>'%'</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>注意：</strong></p>

<ul>
	<li>整数除法的结果应当截去（<code>truncate</code>）其小数部分，例如：<code>truncate(8.345) = 8</code>&nbsp;以及&nbsp;<code>truncate(-2.7335) = -2</code></li>
	<li>假设我们的环境只能存储 32 位有符号整数，其数值范围是 <code>[−2<sup>31</sup>,&nbsp;2<sup>31</sup>−1]</code>。本题中，如果除法结果溢出，则返回 <code>2<sup>31&nbsp;</sup>− 1</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>a = 15, b = 2
<strong>输出：</strong>7
<strong><span style="white-space: pre-wrap;">解释：</span></strong>15/2 = truncate(7.5) = 7
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>a = 7, b = -3
<strong>输出：</strong><span style="white-space: pre-wrap;">-2</span>
<strong><span style="white-space: pre-wrap;">解释：</span></strong>7/-3 = truncate(-2.33333..) = -2</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>a = 0, b = 1
<strong>输出：</strong><span style="white-space: pre-wrap;">0</span></pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>a = 1, b = 1
<strong>输出：</strong><span style="white-space: pre-wrap;">1</span></pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>-2<sup>31</sup>&nbsp;&lt;= a, b &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
	<li><code>b != 0</code></li>
</ul>

<p>&nbsp;</p>

<p>注意：本题与主站 29&nbsp;题相同：<a href="https://leetcode.cn/problems/divide-two-integers/">https://leetcode.cn/problems/divide-two-integers/</a></p>

<p>&nbsp;</p>

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

<!-- tabs:end -->

<!-- end -->
