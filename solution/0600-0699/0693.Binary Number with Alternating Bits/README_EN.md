---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0693.Binary%20Number%20with%20Alternating%20Bits/README_EN.md
tags:
    - Bit Manipulation
---

<!-- problem:start -->

# [693. Binary Number with Alternating Bits](https://leetcode.com/problems/binary-number-with-alternating-bits)

[中文文档](/solution/0600-0699/0693.Binary%20Number%20with%20Alternating%20Bits/README.md)

## Description

<!-- description:start -->

<p>Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> true
<strong>Explanation:</strong> The binary representation of 5 is: 101
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 7
<strong>Output:</strong> false
<strong>Explanation:</strong> The binary representation of 7 is: 111.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 11
<strong>Output:</strong> false
<strong>Explanation:</strong> The binary representation of 11 is: 1011.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We cyclically right-shift $n$ until it becomes $0$, checking whether the binary bits of $n$ appear alternately. If during the loop we find that $0$ and $1$ do not appear alternately, we directly return $\textit{false}$. Otherwise, when the loop ends, we return $\textit{true}$.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hasAlternatingBits(self, n: int) -> bool:
        prev = -1
        while n:
            curr = n & 1
            if prev == curr:
                return False
            prev = curr
            n >>= 1
        return True
```

#### Java

```java
class Solution {
    public boolean hasAlternatingBits(int n) {
        int prev = -1;
        while (n != 0) {
            int curr = n & 1;
            if (prev == curr) {
                return false;
            }
            prev = curr;
            n >>= 1;
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool hasAlternatingBits(int n) {
        int prev = -1;
        while (n) {
            int curr = n & 1;
            if (prev == curr) return false;
            prev = curr;
            n >>= 1;
        }
        return true;
    }
};
```

#### Go

```go
func hasAlternatingBits(n int) bool {
	prev := -1
	for n != 0 {
		curr := n & 1
		if prev == curr {
			return false
		}
		prev = curr
		n >>= 1
	}
	return true
}
```

#### TypeScript

```ts
function hasAlternatingBits(n: number): boolean {
    let prev = -1;

    while (n !== 0) {
        const curr = n & 1;
        if (prev === curr) {
            return false;
        }
        prev = curr;
        n >>= 1;
    }

    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn has_alternating_bits(mut n: i32) -> bool {
        let mut prev: i32 = -1;

        while n != 0 {
            let curr = n & 1;
            if prev == curr {
                return false;
            }
            prev = curr;
            n >>= 1;
        }

        true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Bit Manipulation

Assuming $\text{01}$ appears alternately, we can convert all trailing bits to $\text{1}$ through misaligned XOR. Adding $\text{1}$ gives us a power of $2$, which is a number $n$ (where $n$ has only one bit that is $\text{1}$). Then, using $\text{n} \& (\text{n} + 1)$ can eliminate the last $\text{1}$ bit.

At this point, check if it equals $\text{0}$. If so, the assumption holds, and it is an alternating $\text{01}$ sequence.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hasAlternatingBits(self, n: int) -> bool:
        n ^= n >> 1
        return (n & (n + 1)) == 0
```

#### Java

```java
class Solution {
    public boolean hasAlternatingBits(int n) {
        n ^= (n >> 1);
        return (n & (n + 1)) == 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool hasAlternatingBits(int n) {
        n ^= (n >> 1);
        return (n & ((long) n + 1)) == 0;
    }
};
```

#### Go

```go
func hasAlternatingBits(n int) bool {
	n ^= (n >> 1)
	return (n & (n + 1)) == 0
}
```

#### TypeScript

```ts
function hasAlternatingBits(n: number): boolean {
    n ^= n >> 1;
    return (n & (n + 1)) === 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn has_alternating_bits(n: i32) -> bool {
        let mut x = n ^ (n >> 1);
        (x & (x + 1)) == 0
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
