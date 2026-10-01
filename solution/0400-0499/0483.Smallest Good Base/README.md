---
comments: true
difficulty: 困难
tags:
    - 数学
    - 二分查找
---

<!-- problem:start -->

# [483. 最小好进制](https://leetcode.cn/problems/smallest-good-base)

[English Version](/solution/0400-0499/0483.Smallest%20Good%20Base/README_EN.md)

## 题目描述

<!-- description:start -->

<p>以字符串的形式给出 <code>n</code>&nbsp;, 以字符串的形式返回<em> <code>n</code> 的最小 <strong>好进制</strong> </em>&nbsp;。</p>

<p>如果 <code>n</code> 的 &nbsp;<code>k(k&gt;=2)</code>&nbsp;进制数的所有数位全为1，则称&nbsp;<code>k(k&gt;=2)</code>&nbsp;是 <code>n</code> 的一个&nbsp;<strong>好进制&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = "13"
<strong>输出：</strong>"3"
<strong>解释：</strong>13 的 3 进制是 111。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = "4681"
<strong>输出：</strong>"8"
<strong>解释：</strong>4681 的 8 进制是 11111。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = "1000000000000000000"
<strong>输出：</strong>"999999999999999999"
<strong>解释：</strong>1000000000000000000 的 999999999999999999 进制是 11。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n</code> 的取值范围是&nbsp;<code>[3, 10<sup>18</sup>]</code></li>
	<li><code>n</code> 没有前导 0</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

<!-- thinking:start -->

> **思考**
>
> 找最小进制 $k\ge 2$，使 $n$ 在该进制下全是 $1$。 $k=n-1$ 一定可行（ $11_k$），但 $n$ 达 $10^{18}$，不能从 $2$ 扫到 $n$。
>
> 位数 $m+1$ 满足 $m<60$。从大 $m$ 往小枚举，对 $k$ 二分使 $1+k+\cdots+k^m$ 等于 $n$。更大的 $m$ 对应更小的 $k$，因此从 $63$ 向下能先碰到最小进制。
>
> 等比和关于 $k$ 单调，二分有意义。找不到则退回 $n-1$。

<!-- thinking:end -->

假设 $n$ 在 $k$ 进制下的所有位数均为 $1$，且位数为 $m+1$，那么有式子 ①：

$$
n=k^0+k^1+k^2+...+k^m
$$

当 $m=0$ 时，上式 $n=1$，而题目 $n$ 取值范围为 $[3, 10^{18}]$，因此 $m>0$。

当 $m=1$ 时，上式 $n=k^0+k^1=1+k$，即 $k=n-1>=2$。

我们来证明一般情况下的两个结论，以帮助解决本题。

**结论一：** $m<\log _{k} n$

注意到式子 ① 是个首项为 $1$，且公比为 $k$ 的等比数列。利用等比数列求和公式，我们可以得出：

$$
n=\frac{1-k^{m+1}}{1-k}
$$

变形得：

$$
k^{m+1}=k \times n-n+1 < k \times n
$$

移项得：

$$
m<\log _{k} n
$$

题目 $n$ 取值范围为 $[3, 10^{18}]$，又因为 $k>=2$，因此 $m<\log _{k} n<\log _{2} 10^{18}<60$。

依据结论一，$m$ 的取值落在 $[1, \log_k n)$ 内，且 $m=1$ 时 $k=n-1$ 必然有解。位数越大，对应的进制越小，因此从 $m=63$ 向下枚举，第一个使等比和等于 $n$ 的 $k$ 就是最小好进制。

对固定的 $m$，等比和 $1+k+\cdots+k^m$ 关于 $k$ 单调递增。在 $[2, n-1]$ 上二分 $k$，找到使该和大于等于 $n$ 的最小整数，再检查它是否恰好等于 $n$。乘法溢出时把和视为大于 $n$。若所有位数都未命中，返回 $n-1$。

时间复杂度 $O(\log^2 n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestGoodBase(self, n: str) -> str:
        def cal(k, m):
            p = s = 1
            for i in range(m):
                p *= k
                s += p
            return s

        num = int(n)
        for m in range(63, 1, -1):
            l, r = 2, num - 1
            while l < r:
                mid = (l + r) >> 1
                if cal(mid, m) >= num:
                    r = mid
                else:
                    l = mid + 1
            if cal(l, m) == num:
                return str(l)
        return str(num - 1)
```

#### Java

```java
class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        for (int len = 63; len >= 2; --len) {
            long radix = getRadix(len, num);
            if (radix != -1) {
                return String.valueOf(radix);
            }
        }
        return String.valueOf(num - 1);
    }

    private long getRadix(int len, long num) {
        long l = 2, r = num - 1;
        while (l < r) {
            long mid = l + r >>> 1;
            if (calc(mid, len) >= num)
                r = mid;
            else
                l = mid + 1;
        }
        return calc(r, len) == num ? r : -1;
    }

    private long calc(long radix, int len) {
        long p = 1;
        long sum = 0;
        for (int i = 0; i < len; ++i) {
            if (Long.MAX_VALUE - sum < p) {
                return Long.MAX_VALUE;
            }
            sum += p;
            if (Long.MAX_VALUE / p < radix) {
                p = Long.MAX_VALUE;
            } else {
                p *= radix;
            }
        }
        return sum;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string smallestGoodBase(string n) {
        long long num = stoll(n);
        for (int len = 63; len >= 2; --len) {
            long long radix = getRadix(len, num);
            if (radix != -1) {
                return to_string(radix);
            }
        }
        return to_string(num - 1);
    }

    long long getRadix(int len, long long num) {
        long long l = 2, r = num - 1;
        while (l < r) {
            long long mid = (l + r) >> 1;
            if (calc(mid, len) >= num) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return calc(r, len) == num ? r : -1;
    }

    long long calc(long long radix, int len) {
        long long p = 1, sum = 0;
        for (int i = 0; i < len; ++i) {
            if (LLONG_MAX - sum < p) {
                return LLONG_MAX;
            }
            sum += p;
            if (LLONG_MAX / p < radix) {
                p = LLONG_MAX;
            } else {
                p *= radix;
            }
        }
        return sum;
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
