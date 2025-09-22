---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2429.Minimize%20XOR/README_EN.md
rating: 1532
source: Weekly Contest 313 Q3
tags:
    - Greedy
    - Bit Manipulation
---

<!-- problem:start -->

# [2429. Minimize XOR](https://leetcode.com/problems/minimize-xor)

[中文文档](/solution/2400-2499/2429.Minimize%20XOR/README.md)

## Description

<!-- description:start -->

<p>Given two positive integers <code>num1</code> and <code>num2</code>, find the positive integer <code>x</code> such that:</p>

<ul>
	<li><code>x</code> has the same number of set bits as <code>num2</code>, and</li>
	<li>The value <code>x XOR num1</code> is <strong>minimal</strong>.</li>
</ul>

<p>Note that <code>XOR</code> is the bitwise XOR operation.</p>

<p>Return <em>the integer </em><code>x</code>. The test cases are generated such that <code>x</code> is <strong>uniquely determined</strong>.</p>

<p>The number of <strong>set bits</strong> of an integer is the number of <code>1</code>&#39;s in its binary representation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num1 = 3, num2 = 5
<strong>Output:</strong> 3
<strong>Explanation:</strong>
The binary representations of num1 and num2 are 0011 and 0101, respectively.
The integer <strong>3</strong> has the same number of set bits as num2, and the value <code>3 XOR 3 = 0</code> is minimal.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num1 = 1, num2 = 12
<strong>Output:</strong> 3
<strong>Explanation:</strong>
The binary representations of num1 and num2 are 0001 and 1100, respectively.
The integer <strong>3</strong> has the same number of set bits as num2, and the value <code>3 XOR 1 = 2</code> is minimal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num1, num2 &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Bit Manipulation

According to the problem description, we first calculate the number of set bits in $\textit{num2}$, denoted as $\textit{cnt}$. Then, we iterate from the highest to the lowest bit of $\textit{num1}$; if the current bit is $1$, we set the corresponding bit in $x$ to $1$ and decrement $\textit{cnt}$, until $\textit{cnt}$ becomes $0$. If $\textit{cnt}$ is still not $0$, we iterate from the lowest bit upwards, setting positions where $\textit{num1}$ has $0$ to $1$ in $x$, and decrement $\textit{cnt}$ until it reaches $0$.

The time complexity is $O(\log n)$, where $n$ is the maximum value of $\textit{num1}$ and $\textit{num2}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimizeXor(self, num1: int, num2: int) -> int:
        cnt = num2.bit_count()
        x = 0
        for i in range(30, -1, -1):
            if num1 >> i & 1 and cnt:
                x |= 1 << i
                cnt -= 1
        for i in range(30):
            if num1 >> i & 1 ^ 1 and cnt:
                x |= 1 << i
                cnt -= 1
        return x
```

#### Java

```java
class Solution {
    public int minimizeXor(int num1, int num2) {
        int cnt = Integer.bitCount(num2);
        int x = 0;
        for (int i = 30; i >= 0 && cnt > 0; --i) {
            if ((num1 >> i & 1) == 1) {
                x |= 1 << i;
                --cnt;
            }
        }
        for (int i = 0; cnt > 0; ++i) {
            if ((num1 >> i & 1) == 0) {
                x |= 1 << i;
                --cnt;
            }
        }
        return x;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimizeXor(int num1, int num2) {
        int cnt = __builtin_popcount(num2);
        int x = 0;
        for (int i = 30; ~i && cnt; --i) {
            if (num1 >> i & 1) {
                x |= 1 << i;
                --cnt;
            }
        }
        for (int i = 0; cnt; ++i) {
            if (num1 >> i & 1 ^ 1) {
                x |= 1 << i;
                --cnt;
            }
        }
        return x;
    }
};
```

#### Go

```go
func minimizeXor(num1 int, num2 int) int {
	cnt := bits.OnesCount(uint(num2))
	x := 0
	for i := 30; i >= 0 && cnt > 0; i-- {
		if num1>>i&1 == 1 {
			x |= 1 << i
			cnt--
		}
	}
	for i := 0; cnt > 0; i++ {
		if num1>>i&1 == 0 {
			x |= 1 << i
			cnt--
		}
	}
	return x
}
```

#### TypeScript

```ts
function minimizeXor(num1: number, num2: number): number {
    let cnt = 0;
    while (num2) {
        num2 &= num2 - 1;
        ++cnt;
    }
    let x = 0;
    for (let i = 30; i >= 0 && cnt > 0; --i) {
        if ((num1 >> i) & 1) {
            x |= 1 << i;
            --cnt;
        }
    }
    for (let i = 0; cnt > 0; ++i) {
        if (!((num1 >> i) & 1)) {
            x |= 1 << i;
            --cnt;
        }
    }
    return x;
}
```

#### Rust

```rust
impl Solution {
    pub fn minimize_xor(num1: i32, mut num2: i32) -> i32 {
        let mut cnt = 0;
        while num2 > 0 {
            num2 -= num2 & -num2;
            cnt += 1;
        }
        let mut x = 0;
        let mut c = cnt;
        for i in (0..=30).rev() {
            if c > 0 && (num1 >> i) & 1 == 1 {
                x |= 1 << i;
                c -= 1;
            }
        }
        for i in 0..=30 {
            if c == 0 {
                break;
            }
            if ((num1 >> i) & 1) == 0 {
                x |= 1 << i;
                c -= 1;
            }
        }
        x
    }
}
```

#### C#

```cs
public class Solution {
    public int MinimizeXor(int num1, int num2) {
        int cnt = BitOperations.PopCount((uint)num2);
        int x = 0;
        for (int i = 30; i >= 0 && cnt > 0; --i) {
            if (((num1 >> i) & 1) == 1) {
                x |= 1 << i;
                --cnt;
            }
        }
        for (int i = 0; cnt > 0; ++i) {
            if (((num1 >> i) & 1) == 0) {
                x |= 1 << i;
                --cnt;
            }
        }
        return x;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
