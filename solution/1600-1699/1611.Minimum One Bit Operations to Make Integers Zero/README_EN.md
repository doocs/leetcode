---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1611.Minimum%20One%20Bit%20Operations%20to%20Make%20Integers%20Zero/README_EN.md
rating: 2345
source: Weekly Contest 209 Q4
tags:
    - Bit Manipulation
    - Memoization
    - Dynamic Programming
---

<!-- problem:start -->

# [1611. Minimum One Bit Operations to Make Integers Zero](https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero)

[中文文档](/solution/1600-1699/1611.Minimum%20One%20Bit%20Operations%20to%20Make%20Integers%20Zero/README.md)

## Description

<!-- description:start -->

<p>Given an integer <code>n</code>, you must transform it into <code>0</code> using the following operations any number of times:</p>

<ul>
	<li>Change the rightmost (<code>0<sup>th</sup></code>) bit in the binary representation of <code>n</code>.</li>
	<li>Change the <code>i<sup>th</sup></code> bit in the binary representation of <code>n</code> if the <code>(i-1)<sup>th</sup></code> bit is set to <code>1</code> and the <code>(i-2)<sup>th</sup></code> through <code>0<sup>th</sup></code> bits are set to <code>0</code>.</li>
</ul>

<p>Return <em>the minimum number of operations to transform </em><code>n</code><em> into </em><code>0</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> The binary representation of 3 is &quot;11&quot;.
&quot;<u>1</u>1&quot; -&gt; &quot;<u>0</u>1&quot; with the 2<sup>nd</sup> operation since the 0<sup>th</sup> bit is 1.
&quot;0<u>1</u>&quot; -&gt; &quot;0<u>0</u>&quot; with the 1<sup>st</sup> operation.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 6
<strong>Output:</strong> 4
<strong>Explanation:</strong> The binary representation of 6 is &quot;110&quot;.
&quot;<u>1</u>10&quot; -&gt; &quot;<u>0</u>10&quot; with the 2<sup>nd</sup> operation since the 1<sup>st</sup> bit is 1 and 0<sup>th</sup> through 0<sup>th</sup> bits are 0.
&quot;01<u>0</u>&quot; -&gt; &quot;01<u>1</u>&quot; with the 1<sup>st</sup> operation.
&quot;0<u>1</u>1&quot; -&gt; &quot;0<u>0</u>1&quot; with the 2<sup>nd</sup> operation since the 0<sup>th</sup> bit is 1.
&quot;00<u>1</u>&quot; -&gt; &quot;00<u>0</u>&quot; with the 1<sup>st</sup> operation.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Gray Code Inverse Transform (Gray Code to Binary Code)

This problem essentially asks for the inverse transformation of Gray code at position $n$, i.e., constructing the original number from the Gray code.

Let's first review how to convert binary code to binary Gray code. The rule is to keep the most significant bit of the binary code as the most significant bit of the Gray code, while the second most significant bit of the Gray code is obtained by XORing the most significant bit and the second most significant bit of the binary code. The remaining bits of the Gray code are computed similarly to the second most significant bit.

Suppose a binary number is represented as $B_{n-1}B_{n-2}...B_2B_1B_0$, and its Gray code representation is $G_{n-1}G_{n-2}...G_2G_1G_0$. The most significant bit is kept, so $G_{n-1} = B_{n-1}$; and for other bits $G_i = B_{i+1} \oplus B_{i}$, where $i=0,1,2..,n-2$.

So what is the inverse transformation from Gray code to binary code?

We can observe that the most significant bit of the Gray code is kept, so $B_{n-1} = G_{n-1}$; and $B_{n-2} = G_{n-2} \oplus B_{n-1} = G_{n-2} \oplus G_{n-1}$; and for other bits $B_i = G_{i} \oplus G_{i+1} \cdots \oplus G_{n-1}$, where $i=0,1,2..,n-2$. Therefore, we can use the following function $rev(x)$ to obtain its binary code:

```java
int rev(int x) {
    int n = 0;
    for (; x != 0; x >>= 1) {
        n ^= x;
    }
    return n;
}
```

The time complexity is $O(\log n)$, where $n$ is the integer given in the problem. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumOneBitOperations(self, n: int) -> int:
        ans = 0
        while n:
            ans ^= n
            n >>= 1
        return ans
```

#### Java

```java
class Solution {
    public int minimumOneBitOperations(int n) {
        int ans = 0;
        for (; n > 0; n >>= 1) {
            ans ^= n;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumOneBitOperations(int n) {
        int ans = 0;
        for (; n > 0; n >>= 1) {
            ans ^= n;
        }
        return ans;
    }
};
```

#### Go

```go
func minimumOneBitOperations(n int) (ans int) {
	for ; n > 0; n >>= 1 {
		ans ^= n
	}
	return
}
```

#### TypeScript

```ts
function minimumOneBitOperations(n: number): number {
    let ans = 0;
    for (; n > 0; n >>= 1) {
        ans ^= n;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_one_bit_operations(mut n: i32) -> i32 {
        let mut ans = 0;
        while n > 0 {
            ans ^= n;
            n >>= 1;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
