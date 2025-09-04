---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0231.Power%20of%20Two/README_EN.md
tags:
    - Bit Manipulation
    - Recursion
    - Math
---

<!-- problem:start -->

# [231. Power of Two](https://leetcode.com/problems/power-of-two)

[中文文档](/solution/0200-0299/0231.Power%20of%20Two/README.md)

## Description

<!-- description:start -->

<p>Given an integer <code>n</code>, return <em><code>true</code> if it is a power of two. Otherwise, return <code>false</code></em>.</p>

<p>An integer <code>n</code> is a power of two, if there exists an integer <code>x</code> such that <code>n == 2<sup>x</sup></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> true
<strong>Explanation: </strong>2<sup>0</sup> = 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 16
<strong>Output:</strong> true
<strong>Explanation: </strong>2<sup>4</sup> = 16
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> false
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

According to the properties of bit manipulation, executing $\texttt{n\&(n-1)}$ can eliminate the last bit $1$ in the binary form of $n$. Therefore, if $n > 0$ and $\texttt{n\&(n-1)}$ results in $0$, then $n$ is a power of $2$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPowerOfTwo(self, n: int) -> bool:
        return n > 0 and (n & (n - 1)) == 0
```

#### Java

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
};
```

#### Go

```go
func isPowerOfTwo(n int) bool {
	return n > 0 && (n&(n-1)) == 0
}
```

#### TypeScript

```ts
function isPowerOfTwo(n: number): boolean {
    return n > 0 && (n & (n - 1)) === 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_power_of_two(n: i32) -> bool {
        n > 0 && (n & (n - 1)) == 0
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isPowerOfTwo = function (n) {
    return n > 0 && (n & (n - 1)) == 0;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Lowbit

According to the definition of $\text{lowbit}$, we know that $\text{lowbit}(x) = x \& (-x)$, which can get the decimal number represented by the last bit $1$ of $n$. Therefore, if $n > 0$ and $\text{lowbit}(n)$ equals $n$, then $n$ is a power of $2$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPowerOfTwo(self, n: int) -> bool:
        return n > 0 and n == n & (-n)
```

#### Java

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && n == (n & (-n));
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPowerOfTwo(int n) {
        return n > 0 && n == (n & (-n));
    }
};
```

#### Go

```go
func isPowerOfTwo(n int) bool {
	return n > 0 && n == (n&(-n))
}
```

#### TypeScript

```ts
function isPowerOfTwo(n: number): boolean {
    return n > 0 && n === (n & -n);
}
```

#### Rust

```rust
impl Solution {
    pub fn is_power_of_two(n: i32) -> bool {
        n > 0 && n == (n & (-n))
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isPowerOfTwo = function (n) {
    return n > 0 && n === (n & -n);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
