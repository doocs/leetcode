---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1922.Count%20Good%20Numbers/README.md
rating: 1674
source: 第 248 场周赛 Q3
tags:
    - 递归
    - 数学
---

<!-- problem:start -->

# [1922. 统计好数字的数目](https://leetcode.cn/problems/count-good-numbers)

[English Version](/solution/1900-1999/1922.Count%20Good%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>我们称一个数字字符串是 <strong>好数字</strong> 当它满足（下标从 <strong>0</strong> 开始）<strong>偶数</strong> 下标处的数字为 <strong>偶数</strong> 且 <strong>奇数</strong> 下标处的数字为 <strong>质数</strong> （<code>2</code>，<code>3</code>，<code>5</code> 或 <code>7</code>）。</p>

<ul>
	<li>比方说，<code>"2582"</code> 是好数字，因为偶数下标处的数字（<code>2</code> 和 <code>8</code>）是偶数且奇数下标处的数字（<code>5</code> 和 <code>2</code>）为质数。但 <code>"3245"</code> <strong>不是</strong> 好数字，因为 <code>3</code> 在偶数下标处但不是偶数。</li>
</ul>

<p>给你一个整数 <code>n</code> ，请你返回长度为 <code>n</code> 且为好数字的数字字符串 <strong>总数</strong> 。由于答案可能会很大，请你将它对<strong> </strong><code>10<sup>9</sup> + 7</code> <strong>取余后返回</strong> 。</p>

<p>一个 <strong>数字字符串</strong> 是每一位都由 <code>0</code> 到 <code>9</code> 组成的字符串，且可能包含前导 0 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>n = 1
<b>输出：</b>5
<b>解释：</b>长度为 1 的好数字包括 "0"，"2"，"4"，"6"，"8" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 4
<b>输出：</b>400
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>n = 50
<b>输出：</b>564908303
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：快速幂

长度为 $n$ 的好数字，偶数下标一共有 $\lceil \frac{n}{2} \rceil = \lfloor \frac{n + 1}{2} \rfloor$ 位，偶数下标可以填入 $5$ 种数字（$0, 2, 4, 6, 8$）；奇数下标一共有 $\lfloor \frac{n}{2} \rfloor$ 位，奇数下标可以填入 $4$ 种数字（$2, 3, 5, 7$）。因此长度为 $n$ 的好数字的个数为：

$$
ans = 5^{\lceil \frac{n}{2} \rceil} \times 4^{\lfloor \frac{n}{2} \rfloor}
$$

我们可以使用快速幂来计算 $5^{\lceil \frac{n}{2} \rceil}$ 和 $4^{\lfloor \frac{n}{2} \rfloor}$，时间复杂度为 $O(\log n)$，空间复杂度为 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countGoodNumbers(self, n: int) -> int:
        mod = 10**9 + 7
        return pow(5, (n + 1) >> 1, mod) * pow(4, n >> 1, mod) % mod
```

#### Java

```java
class Solution {
    private final int mod = (int) 1e9 + 7;

    public int countGoodNumbers(long n) {
        return (int) (qpow(5, (n + 1) >> 1) * qpow(4, n >> 1) % mod);
    }

    private long qpow(long x, long n) {
        long res = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                res = res * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countGoodNumbers(long long n) {
        const int mod = 1e9 + 7;
        auto qpow = [](long long x, long long n) -> long long {
            long long res = 1;
            while (n) {
                if ((n & 1) == 1) {
                    res = res * x % mod;
                }
                x = x * x % mod;
                n >>= 1;
            }
            return res;
        };
        return qpow(5, (n + 1) >> 1) * qpow(4, n >> 1) % mod;
    }
};
```

#### Go

```go
const mod int64 = 1e9 + 7

func countGoodNumbers(n int64) int {
	return int(myPow(5, (n+1)>>1) * myPow(4, n>>1) % mod)
}

func myPow(x, n int64) int64 {
	var res int64 = 1
	for n != 0 {
		if (n & 1) == 1 {
			res = res * x % mod
		}
		x = x * x % mod
		n >>= 1
	}
	return res
}
```

#### TypeScript

```ts
function countGoodNumbers(n: number): number {
    const mod = 1000000007n;
    const qpow = (x: bigint, n: bigint): bigint => {
        let res = 1n;
        while (n > 0n) {
            if (n & 1n) {
                res = (res * x) % mod;
            }
            x = (x * x) % mod;
            n >>= 1n;
        }
        return res;
    };
    const a = qpow(5n, BigInt(n + 1) / 2n);
    const b = qpow(4n, BigInt(n) / 2n);
    return Number((a * b) % mod);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
