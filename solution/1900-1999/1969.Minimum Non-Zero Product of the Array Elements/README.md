---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1969.Minimum%20Non-Zero%20Product%20of%20the%20Array%20Elements/README.md
rating: 1966
source: 第 254 场周赛 Q3
tags:
    - 贪心
    - 递归
    - 数学
---

# [1969. 数组元素的最小非零乘积](https://leetcode.cn/problems/minimum-non-zero-product-of-the-array-elements)

[English Version](/solution/1900-1999/1969.Minimum%20Non-Zero%20Product%20of%20the%20Array%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数&nbsp;<code>p</code>&nbsp;。你有一个下标从 <strong>1</strong>&nbsp;开始的数组&nbsp;<code>nums</code>&nbsp;，这个数组包含范围&nbsp;<code>[1, 2<sup>p</sup> - 1]</code>&nbsp;内所有整数的二进制形式（两端都 <strong>包含</strong>）。你可以进行以下操作 <strong>任意</strong>&nbsp;次：</p>

<ul>
	<li>从 <code>nums</code>&nbsp;中选择两个元素&nbsp;<code>x</code>&nbsp;和&nbsp;<code>y</code>&nbsp; 。</li>
	<li>选择 <code>x</code>&nbsp;中的一位与 <code>y</code>&nbsp;对应位置的位交换。对应位置指的是两个整数 <strong>相同位置</strong>&nbsp;的二进制位。</li>
</ul>

<p>比方说，如果&nbsp;<code>x = 11<em><strong>0</strong></em>1</code>&nbsp;且&nbsp;<code>y = 00<em><strong>1</strong></em>1</code>&nbsp;，交换右边数起第 <code>2</code>&nbsp;位后，我们得到&nbsp;<code>x = 11<em><strong>1</strong></em>1</code> 和&nbsp;<code>y = 00<em><strong>0</strong></em>1</code>&nbsp;。</p>

<p>请你算出进行以上操作 <strong>任意次</strong>&nbsp;以后，<code>nums</code>&nbsp;能得到的 <strong>最小非零</strong>&nbsp;乘积。将乘积对<em>&nbsp;</em><code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong> 后返回。</p>

<p><strong>注意：</strong>答案应为取余 <strong>之前</strong>&nbsp;的最小值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>p = 1
<b>输出：</b>1
<b>解释：</b>nums = [1] 。
只有一个元素，所以乘积为该元素。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>p = 2
<b>输出：</b>6
<b>解释：</b>nums = [01, 10, 11] 。
所有交换要么使乘积变为 0 ，要么乘积与初始乘积相同。
所以，数组乘积 1 * 2 * 3 = 6 已经是最小值。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>p = 3
<b>输出：</b>1512
<b>解释：</b>nums = [001, 010, 011, 100, 101, 110, 111]
- 第一次操作中，我们交换第二个和第五个元素最左边的数位。
    - 结果数组为 [001, <em><strong>1</strong></em>10, 011, 100, <em><strong>0</strong></em>01, 110, 111] 。
- 第二次操作中，我们交换第三个和第四个元素中间的数位。
    - 结果数组为 [001, 110, 0<em><strong>0</strong></em>1, 1<em><strong>1</strong></em>0, 001, 110, 111] 。
数组乘积 1 * 6 * 1 * 6 * 1 * 6 * 7 = 1512 是最小乘积。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= p &lt;= 60</code></li>
</ul>

## 解法

### 方法一：贪心 + 快速幂

我们注意到，每一次操作，并不会改变元素的和，而在元素和不变的情况下，要想使得乘积最小，应该尽可能最大化元素的差值。

由于最大的元素为 $2^p - 1$，无论与哪个元素交换，都不会使得差值变大，因此我们不需要考虑与最大元素交换的情况。

对于其它的 $[1,..2^p-2]$ 的元素，我们依次将首尾元素两两配对，即 $x$ 与 $2^p-1-x$ 进行配置，那么经过若干次操作过后，每一对元素都变成了 $(1, 2^p-2)$，那么最终的乘积为 $(2^p-1) \times (2^p-2)^{2^{p-1}-1}$。

时间复杂度 $O(p)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minNonZeroProduct(self, p: int) -> int:
        mod = 10**9 + 7
        return (2**p - 1) * pow(2**p - 2, 2 ** (p - 1) - 1, mod) % mod
```

```java
class Solution {
    public int minNonZeroProduct(int p) {
        final int mod = (int) 1e9 + 7;
        long a = ((1L << p) - 1) % mod;
        long b = qpow(((1L << p) - 2) % mod, (1L << (p - 1)) - 1, mod);
        return (int) (a * b % mod);
    }

    private long qpow(long a, long n, int mod) {
        long ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minNonZeroProduct(int p) {
        using ll = long long;
        const int mod = 1e9 + 7;
        auto qpow = [](ll a, ll n) {
            ll ans = 1;
            for (; n; n >>= 1) {
                if (n & 1) {
                    ans = ans * a % mod;
                }
                a = a * a % mod;
            }
            return ans;
        };
        ll a = ((1LL << p) - 1) % mod;
        ll b = qpow(((1LL << p) - 2) % mod, (1L << (p - 1)) - 1);
        return a * b % mod;
    }
};
```

```go
func minNonZeroProduct(p int) int {
	const mod int = 1e9 + 7
	qpow := func(a, n int) int {
		ans := 1
		for ; n > 0; n >>= 1 {
			if n&1 == 1 {
				ans = ans * a % mod
			}
			a = a * a % mod
		}
		return ans
	}
	a := ((1 << p) - 1) % mod
	b := qpow(((1<<p)-2)%mod, (1<<(p-1))-1)
	return a * b % mod
}
```

```ts
function minNonZeroProduct(p: number): number {
    const mod = BigInt(1e9 + 7);

    const qpow = (a: bigint, n: bigint): bigint => {
        let ans = BigInt(1);
        for (; n; n >>= BigInt(1)) {
            if (n & BigInt(1)) {
                ans = (ans * a) % mod;
            }
            a = (a * a) % mod;
        }
        return ans;
    };
    const a = (2n ** BigInt(p) - 1n) % mod;
    const b = qpow((2n ** BigInt(p) - 2n) % mod, 2n ** (BigInt(p) - 1n) - 1n);
    return Number((a * b) % mod);
}
```

<!-- tabs:end -->

<!-- end -->
