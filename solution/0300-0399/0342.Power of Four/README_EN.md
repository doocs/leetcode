---
comments: true
difficulty: Easy
tags:
    - Bit Manipulation
    - Recursion
    - Math
---

<!-- problem:start -->

# [342. Power of Four](https://leetcode.com/problems/power-of-four)

[中文文档](/solution/0300-0399/0342.Power%20of%20Four/README.md)

## Description

<!-- description:start -->

<p>Given an integer <code>n</code>, return <em><code>true</code> if it is a power of four. Otherwise, return <code>false</code></em>.</p>

<p>An integer <code>n</code> is a power of four, if there exists an integer <code>x</code> such that <code>n == 4<sup>x</sup></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> n = 16
<strong>Output:</strong> true
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> n = 5
<strong>Output:</strong> false
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> n = 1
<strong>Output:</strong> true
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you solve it without loops/recursion?

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

<!-- thinking:start -->

> **Thinking**
>
> Decide whether $n$ is a power of four. Dividing by four works; a bit test is closed form. $4^x=2^{2x}$, so $n$ must be positive, have a single $1$, and that $1$ must sit on an even bit.
>
> $n\&(n-1)=0$ forces a power of two; $n\&\texttt{0xAAAAAAAA}=0$ rejects a $1$ on an odd bit. All three checks together suffice.

<!-- thinking:end -->

If a number is a power of $4$, then it must be greater than $0$. Suppose this number is $4^x$, which is $2^{2x}$. Therefore, its binary representation has only one $1$, and this $1$ appears at an even position.

First, we check if the number is greater than $0$. Then, we verify if the number is $2^{2x}$ by checking if the bitwise AND of $n$ and $n-1$ is $0$. Finally, we check if the $1$ appears at an even position by verifying if the bitwise AND of $n$ and $\textit{0xAAAAAAAA}$ is $0$. If all three conditions are met, then the number is a power of $4$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPowerOfFour(self, n: int) -> bool:
        return n > 0 and (n & (n - 1)) == 0 and (n & 0xAAAAAAAA) == 0
```

#### Java

```java
class Solution {
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }
};
```

#### Go

```go
func isPowerOfFour(n int) bool {
	return n > 0 && (n&(n-1)) == 0 && (n&0xaaaaaaaa) == 0
}
```

#### TypeScript

```ts
function isPowerOfFour(n: number): boolean {
    return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_power_of_four(n: i32) -> bool {
        n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa_u32 as i32) == 0
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isPowerOfFour = function (n) {
    return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
};
```

#### C#

```cs
public class Solution {
    public bool IsPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
