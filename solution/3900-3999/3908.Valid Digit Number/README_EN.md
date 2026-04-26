---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3908.Valid%20Digit%20Number/README_EN.md
---

<!-- problem:start -->

# [3908. Valid Digit Number](https://leetcode.com/problems/valid-digit-number)

[中文文档](/solution/3900-3999/3908.Valid%20Digit%20Number/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> and a digit <code>x</code>.</p>

<p>A number is considered <strong>valid</strong> if:</p>

<ul>
	<li>It contains <strong>at least one</strong> occurrence of digit <code>x</code>, and</li>
	<li>It <strong>does not start</strong> with digit <code>x</code>.</li>
</ul>

<p>Return <code>true</code> if <code>n</code> is <strong>valid</strong>, otherwise return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 101, x = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>The number contains digit 0 at index 1. It does not start with 0, so it satisfies both conditions. Thus, the answer is <code>true</code>​​​​​​​.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 232, x = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>The number starts with 2, which violates the condition. Thus, the answer is <code>false</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, x = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>The number does not contain digit 1. Thus, the answer is <code>false</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>5</sup>​​​​​​​</code></li>
	<li><code>0 &lt;= x &lt;= 9</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We use a boolean variable $\textit{hasX}$ to record whether the digit $x$ appears in $n$.

We repeatedly take the last digit of $n$ and compare it with $x$. If they are equal, we set $\textit{hasX}$ to $\texttt{true}$. At the same time, we divide $n$ by $10$ to remove the last digit. When $n$ is less than or equal to $9$, it means we have checked all the digits. At this point, if $\textit{hasX}$ is $\texttt{true}$ and $n$ is not equal to $x$, then $n$ is a valid number and we return $\texttt{true}$; otherwise, we return $\texttt{false}$.

The time complexity is $O(\log n)$, where $n$ is the input integer. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def validDigit(self, n: int, x: int) -> bool:
        has_x = False
        while n > 9:
            has_x = has_x or n % 10 == x
            n //= 10
        return has_x and n != x
```

#### Java

```java
class Solution {
    public boolean validDigit(int n, int x) {
        boolean hasX = false;
        while (n > 9) {
            hasX = hasX || (n % 10 == x);
            n /= 10;
        }
        return hasX && (n != x);
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool validDigit(int n, int x) {
        bool hasX = false;
        while (n > 9) {
            hasX = hasX || (n % 10 == x);
            n /= 10;
        }
        return hasX && (n != x);
    }
};
```

#### Go

```go
func validDigit(n int, x int) bool {
	hasX := false
	for n > 9 {
		hasX = hasX || (n%10 == x)
		n /= 10
	}
	return hasX && (n != x)
}
```

#### TypeScript

```ts
function validDigit(n: number, x: number): boolean {
    let hasX: boolean = false;
    while (n > 9) {
        hasX = hasX || n % 10 === x;
        n = Math.floor(n / 10);
    }
    return hasX && n !== x;
}
```

#### Rust

```rust
impl Solution {
    pub fn valid_digit(mut n: i32, x: i32) -> bool {
        let mut has_x = false;
        while n > 9 {
            has_x = has_x || (n % 10 == x);
            n /= 10;
        }
        has_x && (n != x)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
