---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2169.Count%20Operations%20to%20Obtain%20Zero/README_EN.md
rating: 1199
source: Weekly Contest 280 Q1
tags:
    - Math
    - Simulation
---

<!-- problem:start -->

# [2169. Count Operations to Obtain Zero](https://leetcode.com/problems/count-operations-to-obtain-zero)

[中文文档](/solution/2100-2199/2169.Count%20Operations%20to%20Obtain%20Zero/README.md)

## Description

<!-- description:start -->

<p>You are given two <strong>non-negative</strong> integers <code>num1</code> and <code>num2</code>.</p>

<p>In one <strong>operation</strong>, if <code>num1 &gt;= num2</code>, you must subtract <code>num2</code> from <code>num1</code>, otherwise subtract <code>num1</code> from <code>num2</code>.</p>

<ul>
	<li>For example, if <code>num1 = 5</code> and <code>num2 = 4</code>, subtract <code>num2</code> from <code>num1</code>, thus obtaining <code>num1 = 1</code> and <code>num2 = 4</code>. However, if <code>num1 = 4</code> and <code>num2 = 5</code>, after one operation, <code>num1 = 4</code> and <code>num2 = 1</code>.</li>
</ul>

<p>Return <em>the <strong>number of operations</strong> required to make either</em> <code>num1 = 0</code> <em>or</em> <code>num2 = 0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num1 = 2, num2 = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
- Operation 1: num1 = 2, num2 = 3. Since num1 &lt; num2, we subtract num1 from num2 and get num1 = 2, num2 = 3 - 2 = 1.
- Operation 2: num1 = 2, num2 = 1. Since num1 &gt; num2, we subtract num2 from num1.
- Operation 3: num1 = 1, num2 = 1. Since num1 == num2, we subtract num2 from num1.
Now num1 = 0 and num2 = 1. Since num1 == 0, we do not need to perform any further operations.
So the total number of operations required is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num1 = 10, num2 = 10
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
- Operation 1: num1 = 10, num2 = 10. Since num1 == num2, we subtract num2 from num1 and get num1 = 10 - 10 = 0.
Now num1 = 0 and num2 = 10. Since num1 == 0, we are done.
So the total number of operations required is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= num1, num2 &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can directly simulate this process by repeatedly performing the following operations:

-   If $\textit{num1} \ge \textit{num2}$, then $\textit{num1} = \textit{num1} - \textit{num2}$;
-   Otherwise, $\textit{num2} = \textit{num2} - \textit{num1}$.
-   Each time an operation is performed, increment the operation count by one.

When either $\textit{num1}$ or $\textit{num2}$ becomes $0$, stop the loop and return the operation count.

The time complexity is $O(m)$, where $m$ is the maximum of $\textit{num1}$ and $\textit{num2}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countOperations(self, num1: int, num2: int) -> int:
        ans = 0
        while num1 and num2:
            if num1 >= num2:
                num1 -= num2
            else:
                num2 -= num1
            ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countOperations(int num1, int num2) {
        int ans = 0;
        for (; num1 != 0 && num2 != 0; ++ans) {
            if (num1 >= num2) {
                num1 -= num2;
            } else {
                num2 -= num1;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countOperations(int num1, int num2) {
        int ans = 0;
        for (; num1 && num2; ++ans) {
            if (num1 >= num2) {
                num1 -= num2;
            } else {
                num2 -= num1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countOperations(num1 int, num2 int) (ans int) {
	for ; num1 != 0 && num2 != 0; ans++ {
		if num1 >= num2 {
			num1 -= num2
		} else {
			num2 -= num1
		}
	}
	return
}
```

#### TypeScript

```ts
function countOperations(num1: number, num2: number): number {
    let ans = 0;
    for (; num1 && num2; ++ans) {
        if (num1 >= num2) {
            num1 -= num2;
        } else {
            num2 -= num1;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_operations(mut num1: i32, mut num2: i32) -> i32 {
        let mut ans = 0;
        while num1 != 0 && num2 != 0 {
            ans += 1;
            if num1 >= num2 {
                num1 -= num2;
            } else {
                num2 -= num1;
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number} num1
 * @param {number} num2
 * @return {number}
 */
var countOperations = function (num1, num2) {
    let ans = 0;
    for (; num1 && num2; ++ans) {
        if (num1 >= num2) {
            num1 -= num2;
        } else {
            num2 -= num1;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countOperations(self, num1: int, num2: int) -> int:
        ans = 0
        while num1 and num2:
            if num1 >= num2:
                ans += num1 // num2
                num1 %= num2
            else:
                ans += num2 // num1
                num2 %= num1
        return ans
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Mathematics

Following the simulation process in Solution 1, we notice that if $\textit{num1}$ is much larger than $\textit{num2}$, each operation will only reduce the value of $\textit{num1}$ slightly, leading to an excessive number of operations. We can optimize this process by directly adding the quotient of $\textit{num1}$ divided by $\textit{num2}$ to the answer in each operation, then taking the remainder of $\textit{num1}$ divided by $\textit{num2}$. This reduces the number of operations.

The time complexity is $O(\log m)$, where $m$ is the maximum of $\textit{num1}$ and $\textit{num2}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countOperations(self, num1: int, num2: int) -> int:
        ans = 0
        while num1 and num2:
            if num1 >= num2:
                ans += num1 // num2
                num1 %= num2
            else:
                ans += num2 // num1
                num2 %= num1
        return ans
```

#### Java

```java
class Solution {
    public int countOperations(int num1, int num2) {
        int ans = 0;
        while (num1 != 0 && num2 != 0) {
            if (num1 >= num2) {
                ans += num1 / num2;
                num1 %= num2;
            } else {
                ans += num2 / num1;
                num2 %= num1;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countOperations(int num1, int num2) {
        int ans = 0;
        while (num1 && num2) {
            if (num1 >= num2) {
                ans += num1 / num2;
                num1 %= num2;
            } else {
                ans += num2 / num1;
                num2 %= num1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countOperations(num1 int, num2 int) (ans int) {
	for num1 != 0 && num2 != 0 {
		if num1 >= num2 {
			ans += num1 / num2
			num1 %= num2
		} else {
			ans += num2 / num1
			num2 %= num1
		}
	}
	return
}
```

#### TypeScript

```ts
function countOperations(num1: number, num2: number): number {
    let ans = 0;
    while (num1 && num2) {
        if (num1 >= num2) {
            ans += (num1 / num2) | 0;
            num1 %= num2;
        } else {
            ans += (num2 / num1) | 0;
            num2 %= num1;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_operations(mut num1: i32, mut num2: i32) -> i32 {
        let mut ans = 0;
        while num1 != 0 && num2 != 0 {
            if num1 >= num2 {
                ans += num1 / num2;
                num1 %= num2;
            } else {
                ans += num2 / num1;
                num2 %= num1;
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number} num1
 * @param {number} num2
 * @return {number}
 */
var countOperations = function (num1, num2) {
    let ans = 0;
    while (num1 && num2) {
        if (num1 >= num2) {
            ans += (num1 / num2) | 0;
            num1 %= num2;
        } else {
            ans += (num2 / num1) | 0;
            num2 %= num1;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
