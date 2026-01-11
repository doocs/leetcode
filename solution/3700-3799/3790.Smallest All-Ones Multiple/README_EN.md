---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3790.Smallest%20All-Ones%20Multiple/README_EN.md
rating: 1593
source: Weekly Contest 482 Q3
tags:
    - Hash Table
    - Math
---

<!-- problem:start -->

# [3790. Smallest All-Ones Multiple](https://leetcode.com/problems/smallest-all-ones-multiple)

[中文文档](/solution/3700-3799/3790.Smallest%20All-Ones%20Multiple/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer <code>k</code>.</p>

<p>Find the <strong>smallest</strong> integer <code>n</code> divisible by <code>k</code> that consists of <strong>only the digit 1</strong> in its decimal representation (e.g., 1, 11, 111, ...).</p>

<p>Return an integer denoting the <strong>number of digits</strong> in the decimal representation of <code>n</code>. If no such <code>n</code> exists, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><code>n = 111</code> because 111 is divisible by 3, but 1 and 11 are not. The length of <code>n = 111</code> is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">k = 7</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p><code>n = 111111</code>. The length of <code>n = 111111</code> is 6.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>There does not exist a valid <code>n</code> that is a multiple of 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation + Modulo Operation

First, if $k$ is even, there is no valid $n$ that satisfies the condition, so we directly return $-1$.

Next, we can simulate the process of constructing an all-ones number $n$ while taking the modulo with $k$ to determine whether a valid $n$ exists.

We loop $k$ times to check whether there exists an all-ones number $n$ divisible by $k$ within these $k$ iterations. In each iteration, we multiply the current remainder by $10$, add $1$, and then take the modulo with $k$. If the remainder becomes $0$ in some iteration, it means we have found a valid $n$, and we return the current iteration count (i.e., the number of digits in the all-ones number). If no valid $n$ is found after the loop ends, we return $-1$.

The time complexity is $O(k)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minAllOneMultiple(self, k: int) -> int:
        if k % 2 == 0:
            return -1
        x = 1 % k
        ans = 1
        for _ in range(k):
            x = (x * 10 + 1) % k
            ans += 1
            if x == 0:
                return ans
        return -1
```

#### Java

```java
class Solution {
    public int minAllOneMultiple(int k) {
        if ((k & 1) == 0) {
            return -1;
        }

        int x = 1 % k;
        int ans = 1;

        for (int i = 0; i < k; i++) {
            x = (x * 10 + 1) % k;
            ans++;
            if (x == 0) {
                return ans;
            }
        }

        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minAllOneMultiple(int k) {
        if ((k & 1) == 0) {
            return -1;
        }

        int x = 1 % k;
        int ans = 1;

        for (int i = 0; i < k; ++i) {
            x = (x * 10 + 1) % k;
            ++ans;
            if (x == 0) {
                return ans;
            }
        }

        return -1;
    }
};
```

#### Go

```go
func minAllOneMultiple(k int) int {
	if k&1 == 0 {
		return -1
	}

	x := 1 % k
	ans := 1

	for i := 0; i < k; i++ {
		x = (x*10 + 1) % k
		ans++
		if x == 0 {
			return ans
		}
	}

	return -1
}
```

#### TypeScript

```ts
function minAllOneMultiple(k: number): number {
    if ((k & 1) === 0) {
        return -1;
    }

    let x = 1 % k;
    let ans = 1;

    for (let i = 0; i < k; i++) {
        x = (x * 10 + 1) % k;
        ans++;
        if (x === 0) {
            return ans;
        }
    }

    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
