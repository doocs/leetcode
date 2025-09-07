---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0326.Power%20of%20Three/README_EN.md
tags:
    - Recursion
    - Math
---

<!-- problem:start -->

# [326. Power of Three](https://leetcode.com/problems/power-of-three)

[中文文档](/solution/0300-0399/0326.Power%20of%20Three/README.md)

## Description

<!-- description:start -->

<p>Given an integer <code>n</code>, return <em><code>true</code> if it is a power of three. Otherwise, return <code>false</code></em>.</p>

<p>An integer <code>n</code> is a power of three, if there exists an integer <code>x</code> such that <code>n == 3<sup>x</sup></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 27
<strong>Output:</strong> true
<strong>Explanation:</strong> 27 = 3<sup>3</sup>
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 0
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no x where 3<sup>x</sup> = 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = -1
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no x where 3<sup>x</sup> = (-1).
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

### Solution 1: Trial Division

If $n \gt 2$, we can continuously divide $n$ by $3$. If it's not divisible, it means $n$ is not a power of $3$, otherwise we continue dividing by $3$ until $n$ is less than or equal to $2$. If $n$ equals $1$, it means $n$ is a power of $3$, otherwise it's not a power of $3$.

Time complexity $O(\log_3n)$, space complexity $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPowerOfThree(self, n: int) -> bool:
        while n > 2:
            if n % 3:
                return False
            n //= 3
        return n == 1
```

#### Java

```java
class Solution {
    public boolean isPowerOfThree(int n) {
        while (n > 2) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return n == 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPowerOfThree(int n) {
        while (n > 2) {
            if (n % 3) {
                return false;
            }
            n /= 3;
        }
        return n == 1;
    }
};
```

#### Go

```go
func isPowerOfThree(n int) bool {
	for n > 2 {
		if n%3 != 0 {
			return false
		}
		n /= 3
	}
	return n == 1
}
```

#### TypeScript

```ts
function isPowerOfThree(n: number): boolean {
    while (n > 2) {
        if (n % 3 !== 0) {
            return false;
        }
        n = Math.floor(n / 3);
    }
    return n === 1;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_power_of_three(mut n: i32) -> bool {
        while n > 2 {
            if n % 3 != 0 {
                return false;
            }
            n /= 3;
        }
        n == 1
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isPowerOfThree = function (n) {
    while (n > 2) {
        if (n % 3 !== 0) {
            return false;
        }
        n = Math.floor(n / 3);
    }
    return n === 1;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Mathematics

If $n$ is a power of $3$, then the maximum value of $n$ is $3^{19} = 1162261467$. Therefore, we only need to check if $n$ is a divisor of $3^{19}$.

Time complexity $O(1)$, space complexity $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPowerOfThree(self, n: int) -> bool:
        return n > 0 and 1162261467 % n == 0
```

#### Java

```java
class Solution {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
};
```

#### Go

```go
func isPowerOfThree(n int) bool {
	return n > 0 && 1162261467%n == 0
}
```

#### TypeScript

```ts
function isPowerOfThree(n: number): boolean {
    return n > 0 && 1162261467 % n == 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_power_of_three(mut n: i32) -> bool {
        n > 0 && 1162261467 % n == 0
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isPowerOfThree = function (n) {
    return n > 0 && 1162261467 % n == 0;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
