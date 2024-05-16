---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2939.Maximum%20Xor%20Product/README_EN.md
rating: 2127
source: Weekly Contest 372 Q3
tags:
    - Greedy
    - Bit Manipulation
    - Math
---

<!-- problem:start -->

# [2939. Maximum Xor Product](https://leetcode.com/problems/maximum-xor-product)

[中文文档](/solution/2900-2999/2939.Maximum%20Xor%20Product/README.md)

## Description

<p>Given three integers <code>a</code>, <code>b</code>, and <code>n</code>, return <em>the <strong>maximum value</strong> of</em> <code>(a XOR x) * (b XOR x)</code> <em>where</em> <code>0 &lt;= x &lt; 2<sup>n</sup></code>.</p>

<p>Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9 </sup>+ 7</code>.</p>

<p><strong>Note</strong> that <code>XOR</code> is the bitwise XOR operation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = 12, b = 5, n = 4
<strong>Output:</strong> 98
<strong>Explanation:</strong> For x = 2, (a XOR x) = 14 and (b XOR x) = 7. Hence, (a XOR x) * (b XOR x) = 98. 
It can be shown that 98 is the maximum value of (a XOR x) * (b XOR x) for all 0 &lt;= x &lt; 2<sup>n</sup><span style="font-size: 10.8333px;">.</span>
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = 6, b = 7 , n = 5
<strong>Output:</strong> 930
<strong>Explanation:</strong> For x = 25, (a XOR x) = 31 and (b XOR x) = 30. Hence, (a XOR x) * (b XOR x) = 930.
It can be shown that 930 is the maximum value of (a XOR x) * (b XOR x) for all 0 &lt;= x &lt; 2<sup>n</sup>.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> a = 1, b = 6, n = 3
<strong>Output:</strong> 12
<strong>Explanation:</strong> For x = 5, (a XOR x) = 4 and (b XOR x) = 3. Hence, (a XOR x) * (b XOR x) = 12.
It can be shown that 12 is the maximum value of (a XOR x) * (b XOR x) for all 0 &lt;= x &lt; 2<sup>n</sup>.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= a, b &lt; 2<sup>50</sup></code></li>
	<li><code>0 &lt;= n &lt;= 50</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Bitwise Operation

According to the problem description, we can assign a number to the $[0..n)$ bits of $a$ and $b$ in binary at the same time, so that the product of $a$ and $b$ is maximized.

Therefore, we first extract the parts of $a$ and $b$ that are higher than the $n$ bits, denoted as $ax$ and $bx$.

Next, we consider each bit in $[0..n)$ from high to low. We denote the current bits of $a$ and $b$ as $x$ and $y$.

If $x = y$, then we can set the current bit of $ax$ and $bx$ to $1$ at the same time. Therefore, we update $ax = ax \mid 1 << i$ and $bx = bx \mid 1 << i$. Otherwise, if $ax < bx$, to maximize the final product, we should set the current bit of $ax$ to $1$. Otherwise, we can set the current bit of $bx$ to $1$.

Finally, we return $ax \times bx \bmod (10^9 + 7)$ as the answer.

The time complexity is $O(n)$, where $n$ is the integer given in the problem. The space complexity is $O(1)$.

<!-- tabs:start -->

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
