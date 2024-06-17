---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0633.Sum%20of%20Square%20Numbers/README_EN.md
tags:
    - Math
    - Two Pointers
    - Binary Search
---

<!-- problem:start -->

# [633. Sum of Square Numbers](https://leetcode.com/problems/sum-of-square-numbers)

[中文文档](/solution/0600-0699/0633.Sum%20of%20Square%20Numbers/README.md)

## Description

<!-- description:start -->

<p>Given a non-negative integer <code>c</code>, decide whether there&#39;re two integers <code>a</code> and <code>b</code> such that <code>a<sup>2</sup> + b<sup>2</sup> = c</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> c = 5
<strong>Output:</strong> true
<strong>Explanation:</strong> 1 * 1 + 2 * 2 = 5
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> c = 3
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= c &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics + Two Pointers

We can use the two-pointer method to solve this problem. Define two pointers $a$ and $b$, pointing to $0$ and $\sqrt{c}$ respectively. In each step, we calculate the value of $s = a^2 + b^2$, and then compare the size of $s$ and $c$. If $s = c$, we have found two integers $a$ and $b$ such that $a^2 + b^2 = c$. If $s < c$, we increase the value of $a$ by $1$. If $s > c$, we decrease the value of $b$ by $1$. We continue this process until we find the answer, or the value of $a$ is greater than the value of $b$, and return `false`.

The time complexity is $O(\sqrt{c})$, where $c$ is the given non-negative integer. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def judgeSquareSum(self, c: int) -> bool:
        a, b = 0, int(sqrt(c))
        while a <= b:
            s = a**2 + b**2
            if s == c:
                return True
            if s < c:
                a += 1
            else:
                b -= 1
        return False
```

#### Java

```java
class Solution {
    public boolean judgeSquareSum(int c) {
        long a = 0, b = (long) Math.sqrt(c);
        while (a <= b) {
            long s = a * a + b * b;
            if (s == c) {
                return true;
            }
            if (s < c) {
                ++a;
            } else {
                --b;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool judgeSquareSum(int c) {
        long long a = 0, b = sqrt(c);
        while (a <= b) {
            long long s = a * a + b * b;
            if (s == c) {
                return true;
            }
            if (s < c) {
                ++a;
            } else {
                --b;
            }
        }
        return false;
    }
};
```

#### Go

```go
func judgeSquareSum(c int) bool {
	a, b := 0, int(math.Sqrt(float64(c)))
	for a <= b {
		s := a*a + b*b
		if s == c {
			return true
		}
		if s < c {
			a++
		} else {
			b--
		}
	}
	return false
}
```

#### TypeScript

```ts
function judgeSquareSum(c: number): boolean {
    let [a, b] = [0, Math.floor(Math.sqrt(c))];
    while (a <= b) {
        const s = a * a + b * b;
        if (s === c) {
            return true;
        }
        if (s < c) {
            ++a;
        } else {
            --b;
        }
    }
    return false;
}
```

#### Rust

```rust
use std::cmp::Ordering;

impl Solution {
    pub fn judge_square_sum(c: i32) -> bool {
        let mut a: i64 = 0;
        let mut b: i64 = (c as f64).sqrt() as i64;
        while a <= b {
            let s = a * a + b * b;
            match s.cmp(&(c as i64)) {
                Ordering::Equal => {
                    return true;
                }
                Ordering::Less => {
                    a += 1;
                }
                Ordering::Greater => {
                    b -= 1;
                }
            }
        }
        false
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Mathematics, b should always be an integer, if b = Root(c - a^2)

<!-- tabs:start -->

#### TypeScript

```ts
function judgeSquareSum(c: number): boolean {
    for (let a = 0, inc = -1; a <= c; inc += 2, a += inc) {
        const b = Math.sqrt(c - a);
        if (b === (b | 0)) return true;
    }

    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3: Mathematics, Fermat's theorem on sums of two squares

<!-- tabs:start -->

#### TypeScript

```ts
function judgeSquareSum(c: number): boolean {
    const n = Math.sqrt(c);

    for (let i = 2; i <= n; i++) {
        let count = 0;

        if (c % i === 0) {
            while (c % i === 0) {
                count++;
                c /= i;
            }
            if (i % 4 === 3 && count % 2 !== 0) return false;
        }
    }
    return c % 4 !== 3;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
