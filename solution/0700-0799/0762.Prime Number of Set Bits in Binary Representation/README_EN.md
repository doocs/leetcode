---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0762.Prime%20Number%20of%20Set%20Bits%20in%20Binary%20Representation/README_EN.md
tags:
    - Bit Manipulation
    - Math
---

<!-- problem:start -->

# [762. Prime Number of Set Bits in Binary Representation](https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation)

[中文文档](/solution/0700-0799/0762.Prime%20Number%20of%20Set%20Bits%20in%20Binary%20Representation/README.md)

## Description

<!-- description:start -->

<p>Given two integers <code>left</code> and <code>right</code>, return <em>the <strong>count</strong> of numbers in the <strong>inclusive</strong> range </em><code>[left, right]</code><em> having a <strong>prime number of set bits</strong> in their binary representation</em>.</p>

<p>Recall that the <strong>number of set bits</strong> an integer has is the number of <code>1</code>&#39;s present when written in binary.</p>

<ul>
	<li>For example, <code>21</code> written in binary is <code>10101</code>, which has <code>3</code> set bits.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> left = 6, right = 10
<strong>Output:</strong> 4
<strong>Explanation:</strong>
6  -&gt; 110 (2 set bits, 2 is prime)
7  -&gt; 111 (3 set bits, 3 is prime)
8  -&gt; 1000 (1 set bit, 1 is not prime)
9  -&gt; 1001 (2 set bits, 2 is prime)
10 -&gt; 1010 (2 set bits, 2 is prime)
4 numbers have a prime number of set bits.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> left = 10, right = 15
<strong>Output:</strong> 5
<strong>Explanation:</strong>
10 -&gt; 1010 (2 set bits, 2 is prime)
11 -&gt; 1011 (3 set bits, 3 is prime)
12 -&gt; 1100 (2 set bits, 2 is prime)
13 -&gt; 1101 (3 set bits, 3 is prime)
14 -&gt; 1110 (3 set bits, 3 is prime)
15 -&gt; 1111 (4 set bits, 4 is not prime)
5 numbers have a prime number of set bits.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= left &lt;= right &lt;= 10<sup>6</sup></code></li>
	<li><code>0 &lt;= right - left &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### 方法一：数学 + 位运算

题目中 $\textit{left}$ 和 $\textit{right}$ 的范围均在 $10^6$ 以内，而 $2^{20} = 1048576$，因此，二进制中 $1$ 的个数最多也就 $20$ 个，而 $20$ 以内的质数有 $[2, 3, 5, 7, 11, 13, 17, 19]$。

我们枚举 $[\textit{left},.. \textit{right}]$ 范围内的每个数，统计其二进制中 $1$ 的个数，然后判断该个数是否为质数，如果是，答案加一。

时间复杂度 $O(n\times \log m)$。其中 $n = \textit{right} - \textit{left} + 1$，而 $m$ 为 $[\textit{left},.. \textit{right}]$ 范围内的最大数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPrimeSetBits(self, left: int, right: int) -> int:
        primes = {2, 3, 5, 7, 11, 13, 17, 19}
        return sum(i.bit_count() in primes for i in range(left, right + 1))
```

#### Java

```java
class Solution {
    private static Set<Integer> primes = Set.of(2, 3, 5, 7, 11, 13, 17, 19);

    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; ++i) {
            if (primes.contains(Integer.bitCount(i))) {
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countPrimeSetBits(int left, int right) {
        unordered_set<int> primes{2, 3, 5, 7, 11, 13, 17, 19};
        int ans = 0;
        for (int i = left; i <= right; ++i) {
            ans += primes.count(__builtin_popcount(i));
        }
        return ans;
    }
};
```

#### Go

```go
func countPrimeSetBits(left int, right int) (ans int) {
	primes := map[int]int{}
	for _, v := range []int{2, 3, 5, 7, 11, 13, 17, 19} {
		primes[v] = 1
	}
	for i := left; i <= right; i++ {
		ans += primes[bits.OnesCount(uint(i))]
	}
	return
}
```

#### TypeScript

```ts
function countPrimeSetBits(left: number, right: number): number {
    const primes = new Set<number>([2, 3, 5, 7, 11, 13, 17, 19]);
    let ans = 0;

    for (let i = left; i <= right; i++) {
        const bits = bitCount(i);
        if (primes.has(bits)) {
            ans++;
        }
    }

    return ans;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_prime_set_bits(left: i32, right: i32) -> i32 {
        let primes = [2, 3, 5, 7, 11, 13, 17, 19];
        let mut ans = 0;

        for i in left..=right {
            let bits = i.count_ones() as i32;
            if primes.contains(&bits) {
                ans += 1;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
