---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2939.Maximum%20Xor%20Product/README.md
rating: 2127
source: 第 372 场周赛 Q3
tags:
    - 贪心
    - 位运算
    - 数学
---

<!-- problem:start -->

# [2939. 最大异或乘积](https://leetcode.cn/problems/maximum-xor-product)

[English Version](/solution/2900-2999/2939.Maximum%20Xor%20Product/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你三个整数&nbsp;<code>a</code>&nbsp;，<code>b</code>&nbsp;和&nbsp;<code>n</code>&nbsp;，请你返回&nbsp;<code>(a XOR x) * (b XOR x)</code>&nbsp;的&nbsp;<strong>最大值</strong>&nbsp;且 <code>x</code>&nbsp;需要满足 <code>0 &lt;= x &lt; 2<sup>n</sup></code>。</p>

<p>由于答案可能会很大，返回它对&nbsp;<code>10<sup>9 </sup>+ 7</code>&nbsp;<strong>取余</strong>&nbsp;后的结果。</p>

<p><strong>注意</strong>，<code>XOR</code>&nbsp;是按位异或操作。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>a = 12, b = 5, n = 4
<b>输出：</b>98
<b>解释：</b>当 x = 2 时，(a XOR x) = 14 且 (b XOR x) = 7 。所以，(a XOR x) * (b XOR x) = 98 。
98 是所有满足 0 &lt;= x &lt; 2<sup>n </sup>中 (a XOR x) * (b XOR x) 的最大值。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>a = 6, b = 7 , n = 5
<b>输出：</b>930
<b>解释：</b>当 x = 25 时，(a XOR x) = 31 且 (b XOR x) = 30 。所以，(a XOR x) * (b XOR x) = 930 。
930 是所有满足 0 &lt;= x &lt; 2<sup>n </sup>中 (a XOR x) * (b XOR x) 的最大值。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>a = 1, b = 6, n = 3
<b>输出：</b>12
<b>解释： </b>当 x = 5 时，(a XOR x) = 4 且 (b XOR x) = 3 。所以，(a XOR x) * (b XOR x) = 12 。
12 是所有满足 0 &lt;= x &lt; 2<sup>n </sup>中 (a XOR x) * (b XOR x) 的最大值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= a, b &lt; 2<sup>50</sup></code></li>
	<li><code>0 &lt;= n &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 位运算

根据题目描述，我们可以给 $a$ 和 $b$ 在二进制下 $[0..n)$ 位上同时分配一个数字，最终使得 $a$ 和 $b$ 的乘积最大。

因此，我们首先提取 $a$ 和 $b$ 高于 $n$ 位的部分，分别记为 $ax$ 和 $bx$。

接下来，从大到小考虑 $[0..n)$ 位上的每一位，我们将 $a$ 和 $b$ 的当前位分别记为 $x$ 和 $y$。

如果 $x = y$，那么我们可以将 $ax$ 和 $bx$ 的当前位同时置为 $1$，因此，我们更新 $ax = ax \mid 1 << i$ 和 $bx = bx \mid 1 << i$。否则，如果 $ax \lt bx$，要使得最终的乘积最大，我们应该让 $ax$ 的当前位置为 $1$，否则我们可以将 $bx$ 的当前位置为 $1$。

最后，我们返回 $ax \times bx \bmod (10^9 + 7)$ 即为答案。

时间复杂度 $O(n)$，其中 $n$ 为题目给定的整数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumXorProduct(self, a: int, b: int, n: int) -> int:
        mod = 10**9 + 7
        ax, bx = (a >> n) << n, (b >> n) << n
        for i in range(n - 1, -1, -1):
            x = a >> i & 1
            y = b >> i & 1
            if x == y:
                ax |= 1 << i
                bx |= 1 << i
            elif ax > bx:
                bx |= 1 << i
            else:
                ax |= 1 << i
        return ax * bx % mod
```

#### Java

```java
class Solution {
    public int maximumXorProduct(long a, long b, int n) {
        final int mod = (int) 1e9 + 7;
        long ax = (a >> n) << n;
        long bx = (b >> n) << n;
        for (int i = n - 1; i >= 0; --i) {
            long x = a >> i & 1;
            long y = b >> i & 1;
            if (x == y) {
                ax |= 1L << i;
                bx |= 1L << i;
            } else if (ax < bx) {
                ax |= 1L << i;
            } else {
                bx |= 1L << i;
            }
        }
        ax %= mod;
        bx %= mod;
        return (int) (ax * bx % mod);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumXorProduct(long long a, long long b, int n) {
        const int mod = 1e9 + 7;
        long long ax = (a >> n) << n, bx = (b >> n) << n;
        for (int i = n - 1; ~i; --i) {
            int x = a >> i & 1, y = b >> i & 1;
            if (x == y) {
                ax |= 1LL << i;
                bx |= 1LL << i;
            } else if (ax < bx) {
                ax |= 1LL << i;
            } else {
                bx |= 1LL << i;
            }
        }
        ax %= mod;
        bx %= mod;
        return ax * bx % mod;
    }
};
```

#### Go

```go
func maximumXorProduct(a int64, b int64, n int) int {
	const mod int64 = 1e9 + 7
	ax := (a >> n) << n
	bx := (b >> n) << n
	for i := n - 1; i >= 0; i-- {
		x, y := (a>>i)&1, (b>>i)&1
		if x == y {
			ax |= 1 << i
			bx |= 1 << i
		} else if ax < bx {
			ax |= 1 << i
		} else {
			bx |= 1 << i
		}
	}
	ax %= mod
	bx %= mod
	return int(ax * bx % mod)
}
```

#### TypeScript

```ts
function maximumXorProduct(a: number, b: number, n: number): number {
    const mod = BigInt(1e9 + 7);
    let ax = (BigInt(a) >> BigInt(n)) << BigInt(n);
    let bx = (BigInt(b) >> BigInt(n)) << BigInt(n);
    for (let i = BigInt(n - 1); ~i; --i) {
        const x = (BigInt(a) >> i) & 1n;
        const y = (BigInt(b) >> i) & 1n;
        if (x === y) {
            ax |= 1n << i;
            bx |= 1n << i;
        } else if (ax < bx) {
            ax |= 1n << i;
        } else {
            bx |= 1n << i;
        }
    }
    ax %= mod;
    bx %= mod;
    return Number((ax * bx) % mod);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
