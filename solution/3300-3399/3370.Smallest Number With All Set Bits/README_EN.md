---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3370.Smallest%20Number%20With%20All%20Set%20Bits/README_EN.md
rating: 1198
source: Weekly Contest 426 Q1
tags:
    - Bit Manipulation
    - Math
---

<!-- problem:start -->

# [3370. Smallest Number With All Set Bits](https://leetcode.com/problems/smallest-number-with-all-set-bits)

[中文文档](/solution/3300-3399/3370.Smallest%20Number%20With%20All%20Set%20Bits/README.md)

## Description

<!-- description:start -->

<p>You are given a <em>positive</em> number <code>n</code>.</p>

<p>Return the <strong>smallest</strong> number <code>x</code> <strong>greater than</strong> or <strong>equal to</strong> <code>n</code>, such that the binary representation of <code>x</code> contains only <span data-keyword="set-bit">set bits</span></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>The binary representation of 7 is <code>&quot;111&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<p>The binary representation of 15 is <code>&quot;1111&quot;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The binary representation of 3 is <code>&quot;11&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

We start with $x = 1$ and continuously left shift $x$ until $x - 1 \geq n$. At this point, $x - 1$ is the answer we are looking for.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestNumber(self, n: int) -> int:
        x = 1
        while x - 1 < n:
            x <<= 1
        return x - 1
```

#### Java

```java
class Solution {
    public int smallestNumber(int n) {
        int x = 1;
        while (x - 1 < n) {
            x <<= 1;
        }
        return x - 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestNumber(int n) {
        int x = 1;
        while (x - 1 < n) {
            x <<= 1;
        }
        return x - 1;
    }
};
```

#### Go

```go
func smallestNumber(n int) int {
	x := 1
	for x-1 < n {
		x <<= 1
	}
	return x - 1
}
```

#### TypeScript

```ts
function smallestNumber(n: number): number {
    let x = 1;
    while (x - 1 < n) {
        x <<= 1;
    }
    return x - 1;
}
```

#### Rust

```rust
impl Solution {
    pub fn smallest_number(n: i32) -> i32 {
        let mut x = 1;
        while x - 1 < n {
            x <<= 1;
        }
        x - 1
    }
}
```

#### C#

```cs
public class Solution {
    public int SmallestNumber(int n) {
        int x = 1;
        while (x - 1 < n) {
            x <<= 1;
        }
        return x - 1;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
