# [2595. Number of Even and Odd Bits](https://leetcode.com/problems/number-of-even-and-odd-bits)

[中文文档](/solution/2500-2599/2595.Number%20of%20Even%20and%20Odd%20Bits/README.md)

<!-- tags:Bit Manipulation -->

<!-- difficulty:Easy -->

## Description

<p>You are given a <strong>positive</strong> integer <code>n</code>.</p>

<p>Let <code>even</code> denote the number of even indices in the binary representation of <code>n</code> (<strong>0-indexed</strong>) with value <code>1</code>.</p>

<p>Let <code>odd</code> denote the number of odd indices in the binary representation of <code>n</code> (<strong>0-indexed</strong>) with value <code>1</code>.</p>

<p>Return <em>an integer array </em><code>answer</code><em> where </em><code>answer = [even, odd]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 17
<strong>Output:</strong> [2,0]
<strong>Explanation:</strong> The binary representation of 17 is 10001. 
It contains 1 on the 0<sup>th</sup> and 4<sup>th</sup> indices. 
There are 2 even and 0 odd indices.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> [0,1]
<strong>Explanation:</strong> The binary representation of 2 is 10.
It contains 1 on the 1<sup>st</sup> index. 
There are 0 even and 1 odd indices.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## Solutions

### Solution 1: Enumerate

According to the problem description, enumerate the binary representation of $n$ from the low bit to the high bit. If the bit is $1$, add $1$ to the corresponding counter according to whether the index of the bit is odd or even.

The time complexity is $O(\log n)$ and the space complexity is $O(1)$. Where $n$ is the given integer.

<!-- tabs:start -->

```python
class Solution:
    def evenOddBit(self, n: int) -> List[int]:
        ans = [0, 0]
        i = 0
        while n:
            ans[i] += n & 1
            i ^= 1
            n >>= 1
        return ans
```

```java
class Solution {
    public int[] evenOddBit(int n) {
        int[] ans = new int[2];
        for (int i = 0; n > 0; n >>= 1, i ^= 1) {
            ans[i] += n & 1;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> evenOddBit(int n) {
        vector<int> ans(2);
        for (int i = 0; n > 0; n >>= 1, i ^= 1) {
            ans[i] += n & 1;
        }
        return ans;
    }
};
```

```go
func evenOddBit(n int) []int {
	ans := make([]int, 2)
	for i := 0; n != 0; n, i = n>>1, i^1 {
		ans[i] += n & 1
	}
	return ans
}
```

```ts
function evenOddBit(n: number): number[] {
    const ans = new Array(2).fill(0);
    for (let i = 0; n > 0; n >>= 1, i ^= 1) {
        ans[i] += n & 1;
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn even_odd_bit(mut n: i32) -> Vec<i32> {
        let mut ans = vec![0; 2];

        let mut i = 0;
        while n != 0 {
            ans[i] += n & 1;

            n >>= 1;
            i ^= 1;
        }

        ans
    }
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def evenOddBit(self, n: int) -> List[int]:
        mask = 0x5555
        even = (n & mask).bit_count()
        odd = (n & ~mask).bit_count()
        return [even, odd]
```

```java
class Solution {
    public int[] evenOddBit(int n) {
        int mask = 0x5555;
        int even = Integer.bitCount(n & mask);
        int odd = Integer.bitCount(n & ~mask);
        return new int[] {even, odd};
    }
}
```

```cpp
class Solution {
public:
    vector<int> evenOddBit(int n) {
        int mask = 0x5555;
        int even = __builtin_popcount(n & mask);
        int odd = __builtin_popcount(n & ~mask);
        return {even, odd};
    }
};
```

```go
func evenOddBit(n int) []int {
	mask := 0x5555
	even := bits.OnesCount32(uint32(n & mask))
	odd := bits.OnesCount32(uint32(n & ^mask))
	return []int{even, odd}
}
```

```ts
function evenOddBit(n: number): number[] {
    const mask = 0x5555;
    const even = bitCount(n & mask);
    const odd = bitCount(n & ~mask);
    return [even, odd];
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

```rust
impl Solution {
    pub fn even_odd_bit(n: i32) -> Vec<i32> {
        let mask: i32 = 0x5555;
        let even = (n & mask).count_ones() as i32;
        let odd = (n & !mask).count_ones() as i32;
        vec![even, odd]
    }
}
```

<!-- tabs:end -->

<!-- end -->
