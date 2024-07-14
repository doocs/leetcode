---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1969.Minimum%20Non-Zero%20Product%20of%20the%20Array%20Elements/README_EN.md
rating: 1966
source: Weekly Contest 254 Q3
tags:
    - Greedy
    - Recursion
    - Math
---

<!-- problem:start -->

# [1969. Minimum Non-Zero Product of the Array Elements](https://leetcode.com/problems/minimum-non-zero-product-of-the-array-elements)

[中文文档](/solution/1900-1999/1969.Minimum%20Non-Zero%20Product%20of%20the%20Array%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer <code>p</code>. Consider an array <code>nums</code> (<strong>1-indexed</strong>) that consists of the integers in the <strong>inclusive</strong> range <code>[1, 2<sup>p</sup> - 1]</code> in their binary representations. You are allowed to do the following operation <strong>any</strong> number of times:</p>

<ul>
	<li>Choose two elements <code>x</code> and <code>y</code> from <code>nums</code>.</li>
	<li>Choose a bit in <code>x</code> and swap it with its corresponding bit in <code>y</code>. Corresponding bit refers to the bit that is in the <strong>same position</strong> in the other integer.</li>
</ul>

<p>For example, if <code>x = 11<u>0</u>1</code> and <code>y = 00<u>1</u>1</code>, after swapping the <code>2<sup>nd</sup></code> bit from the right, we have <code>x = 11<u>1</u>1</code> and <code>y = 00<u>0</u>1</code>.</p>

<p>Find the <strong>minimum non-zero</strong> product of <code>nums</code> after performing the above operation <strong>any</strong> number of times. Return <em>this product</em><em> <strong>modulo</strong> </em><code>10<sup>9</sup> + 7</code>.</p>

<p><strong>Note:</strong> The answer should be the minimum product <strong>before</strong> the modulo operation is done.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> p = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> nums = [1].
There is only one element, so the product equals that element.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> p = 2
<strong>Output:</strong> 6
<strong>Explanation:</strong> nums = [01, 10, 11].
Any swap would either make the product 0 or stay the same.
Thus, the array product of 1 * 2 * 3 = 6 is already minimized.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> p = 3
<strong>Output:</strong> 1512
<strong>Explanation:</strong> nums = [001, 010, 011, 100, 101, 110, 111]
- In the first operation we can swap the leftmost bit of the second and fifth elements.
    - The resulting array is [001, <u>1</u>10, 011, 100, <u>0</u>01, 110, 111].
- In the second operation we can swap the middle bit of the third and fourth elements.
    - The resulting array is [001, 110, 0<u>0</u>1, 1<u>1</u>0, 001, 110, 111].
The array product is 1 * 6 * 1 * 6 * 1 * 6 * 7 = 1512, which is the minimum possible product.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= p &lt;= 60</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Fast Power

We notice that each operation does not change the sum of the elements. When the sum of the elements remains unchanged, to minimize the product, we should maximize the difference between the elements as much as possible.

Since the largest element is $2^p - 1$, no matter which element it exchanges with, it will not increase the difference. Therefore, we do not need to consider the case of exchanging with the largest element.

For the other elements in $[1,..2^p-2]$, we pair the first and last elements one by one, that is, pair $x$ with $2^p-1-x$. After several operations, each pair of elements becomes $(1, 2^p-2)$. The final product is $(2^p-1) \times (2^p-2)^{2^{p-1}-1}$.

The time complexity is $O(p)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minNonZeroProduct(self, p: int) -> int:
        mod = 10**9 + 7
        return (2**p - 1) * pow(2**p - 2, 2 ** (p - 1) - 1, mod) % mod
```

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

<!-- solution:end -->

<!-- problem:end -->
