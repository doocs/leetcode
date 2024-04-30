# [1201. 丑数 III](https://leetcode.cn/problems/ugly-number-iii)

[English Version](/solution/1200-1299/1201.Ugly%20Number%20III/README_EN.md)

<!-- tags:数学,二分查找,组合数学,数论 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>丑数是可以被&nbsp;<code>a</code>&nbsp;<strong>或</strong>&nbsp;<code>b</code>&nbsp;<strong>或</strong> <code>c</code>&nbsp;整除的 <strong>正整数</strong> 。</p>

<p>给你四个整数：<code>n</code> 、<code>a</code> 、<code>b</code> 、<code>c</code> ，请你设计一个算法来找出第&nbsp;<code>n</code>&nbsp;个丑数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3, a = 2, b = 3, c = 5
<strong>输出：</strong>4
<strong>解释：</strong>丑数序列为 2, 3, 4, 5, 6, 8, 9, 10... 其中第 3 个是 4。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 4, a = 2, b = 3, c = 4
<strong>输出：</strong>6
<strong>解释：</strong>丑数序列为 2, 3, 4, 6, 8, 9, 10, 12... 其中第 4 个是 6。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 5, a = 2, b = 11, c = 13
<strong>输出：</strong>10
<strong>解释：</strong>丑数序列为 2, 4, 6, 8, 10, 11, 12, 13... 其中第 5 个是 10。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n, a, b, c &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= a * b * c &lt;= 10<sup>18</sup></code></li>
	<li>本题结果在&nbsp;<code>[1,&nbsp;2 * 10<sup>9</sup>]</code>&nbsp;的范围内</li>
</ul>

## 解法

### 方法一：二分查找 + 容斥原理

我们可以将题目转换为：找到最小的正整数 $x$，使得小于等于 $x$ 的丑数个数恰好为 $n$ 个。

对于一个正整数 $x$，能被 $a$ 整除的数有 $\left\lfloor \frac{x}{a} \right\rfloor$ 个，能被 $b$ 整除的数有 $\left\lfloor \frac{x}{b} \right\rfloor$ 个，能被 $c$ 整除的数有 $\left\lfloor \frac{x}{c} \right\rfloor$ 个，能被 $a$ 和 $b$ 同时整除的数有 $\left\lfloor \frac{x}{lcm(a, b)} \right\rfloor$ 个，能被 $a$ 和 $c$ 同时整除的数有 $\left\lfloor \frac{x}{lcm(a, c)} \right\rfloor$ 个，能被 $b$ 和 $c$ 同时整除的数有 $\left\lfloor \frac{x}{lcm(b, c)} \right\rfloor$ 个，能被 $a$, $b$ 和 $c$ 同时整除的数有 $\left\lfloor \frac{x}{lcm(a, b, c)} \right\rfloor$ 个。根据容斥原理，小于等于 $x$ 的丑数个数为：

$$
\left\lfloor \frac{x}{a} \right\rfloor + \left\lfloor \frac{x}{b} \right\rfloor + \left\lfloor \frac{x}{c} \right\rfloor - \left\lfloor \frac{x}{lcm(a, b)} \right\rfloor - \left\lfloor \frac{x}{lcm(a, c)} \right\rfloor - \left\lfloor \frac{x}{lcm(b, c)} \right\rfloor + \left\lfloor \frac{x}{lcm(a, b, c)} \right\rfloor
$$

我们可以使用二分查找的方法找到最小的正整数 $x$，使得小于等于 $x$ 的丑数个数恰好为 $n$ 个。

定义二分查找的左边界为 $l=1$，右边界为 $r=2 \times 10^9$，其中 $2 \times 10^9$ 是题目给定的最大值。在二分查找的每一步中，我们找出中间数 $mid$，如果小于等于 $mid$ 的丑数个数大于等于 $n$，那么说明最小的正整数 $x$ 落在 $[l,mid]$ 区间内，否则落在 $[mid+1,r]$ 区间内。在二分查找的过程中，我们需要不断更新小于等于 $mid$ 的丑数个数，直到找到最小的正整数 $x$。

时间复杂度 $O(\log m)$，其中 $m = 2 \times 10^9$。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def nthUglyNumber(self, n: int, a: int, b: int, c: int) -> int:
        ab = lcm(a, b)
        bc = lcm(b, c)
        ac = lcm(a, c)
        abc = lcm(a, b, c)
        l, r = 1, 2 * 10**9
        while l < r:
            mid = (l + r) >> 1
            if (
                mid // a
                + mid // b
                + mid // c
                - mid // ab
                - mid // bc
                - mid // ac
                + mid // abc
                >= n
            ):
                r = mid
            else:
                l = mid + 1
        return l
```

```java
class Solution {
    public int nthUglyNumber(int n, int a, int b, int c) {
        long ab = lcm(a, b);
        long bc = lcm(b, c);
        long ac = lcm(a, c);
        long abc = lcm(ab, c);
        long l = 1, r = 2000000000;
        while (l < r) {
            long mid = (l + r) >> 1;
            if (mid / a + mid / b + mid / c - mid / ab - mid / bc - mid / ac + mid / abc >= n) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return (int) l;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
}
```

```cpp
class Solution {
public:
    int nthUglyNumber(int n, int a, int b, int c) {
        long long ab = lcm(a, b);
        long long bc = lcm(b, c);
        long long ac = lcm(a, c);
        long long abc = lcm(ab, c);
        long long l = 1, r = 2000000000;
        while (l < r) {
            long long mid = (l + r) >> 1;
            if (mid / a + mid / b + mid / c - mid / ab - mid / bc - mid / ac + mid / abc >= n) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    long long lcm(long long a, long long b) {
        return a * b / gcd(a, b);
    }

    long long gcd(long long a, long long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
};
```

```go
func nthUglyNumber(n int, a int, b int, c int) int {
	ab, bc, ac := lcm(a, b), lcm(b, c), lcm(a, c)
	abc := lcm(ab, c)
	var l, r int = 1, 2e9
	for l < r {
		mid := (l + r) >> 1
		if mid/a+mid/b+mid/c-mid/ab-mid/bc-mid/ac+mid/abc >= n {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}

func lcm(a, b int) int {
	return a * b / gcd(a, b)
}
```

```ts
function nthUglyNumber(n: number, a: number, b: number, c: number): number {
    const ab = lcm(BigInt(a), BigInt(b));
    const bc = lcm(BigInt(b), BigInt(c));
    const ac = lcm(BigInt(a), BigInt(c));
    const abc = lcm(BigInt(a), bc);
    let l = 1n;
    let r = BigInt(2e9);
    while (l < r) {
        const mid = (l + r) >> 1n;
        const count =
            mid / BigInt(a) +
            mid / BigInt(b) +
            mid / BigInt(c) -
            mid / ab -
            mid / bc -
            mid / ac +
            mid / abc;
        if (count >= BigInt(n)) {
            r = mid;
        } else {
            l = mid + 1n;
        }
    }
    return Number(l);
}

function gcd(a: bigint, b: bigint): bigint {
    return b === 0n ? a : gcd(b, a % b);
}

function lcm(a: bigint, b: bigint): bigint {
    return (a * b) / gcd(a, b);
}
```

<!-- tabs:end -->

<!-- end -->
