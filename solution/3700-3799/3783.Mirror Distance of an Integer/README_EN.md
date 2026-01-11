---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3783.Mirror%20Distance%20of%20an%20Integer/README_EN.md
rating: 1170
source: Weekly Contest 481 Q1
tags:
    - Math
---

<!-- problem:start -->

# [3783. Mirror Distance of an Integer](https://leetcode.com/problems/mirror-distance-of-an-integer)

[中文文档](/solution/3700-3799/3783.Mirror%20Distance%20of%20an%20Integer/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>.</p>

<p>Define its <strong>mirror distance</strong> as: <code>abs(n - reverse(n))</code>​​​​​​​ where <code>reverse(n)</code> is the integer formed by reversing the digits of <code>n</code>.</p>

<p>Return an integer denoting the mirror distance of <code>n</code>​​​​​​​.</p>

<p><code>abs(x)</code> denotes the absolute value of <code>x</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 25</span></p>

<p><strong>Output:</strong> <span class="example-io">27</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>reverse(25) = 52</code>.</li>
	<li>Thus, the answer is <code>abs(25 - 52) = 27</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>reverse(10) = 01</code> which is 1.</li>
	<li>Thus, the answer is <code>abs(10 - 1) = 9</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 7</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>reverse(7) = 7</code>.</li>
	<li>Thus, the answer is <code>abs(7 - 7) = 0</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We define a function $\text{reverse}(x)$ to reverse the digits of integer $x$. Specifically, we initialize a variable $y$ to $0$, then repeatedly append the last digit of $x$ to the end of $y$, and remove the last digit from $x$, until $x$ becomes $0$. Finally, $y$ is the reversed integer.

Next, we compute the mirror distance of integer $n$, which is $\text{abs}(n - \text{reverse}(n))$, and return the result.

The time complexity is $O(\log n)$ and the space complexity is $O(1)$, where $n$ is the size of the input integer.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mirrorDistance(self, n: int) -> int:
        return abs(n - int(str(n)[::-1]))
```

#### Java

```java
class Solution {
    public int mirrorDistance(int n) {
        return Math.abs(n - reverse(n));
    }

    private int reverse(int x) {
        int y = 0;
        for (; x > 0; x /= 10) {
            y = y * 10 + x % 10;
        }
        return y;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int mirrorDistance(int n) {
        auto reverse = [](int x) -> int {
            int y = 0;
            for (; x; x /= 10) {
                y = y * 10 + x % 10;
            }
            return y;
        };
        return abs(n - reverse(n));
    }
};
```

#### Go

```go
func mirrorDistance(n int) int {
	reverse := func(x int) int {
		y := 0
		for ; x > 0; x /= 10 {
			y = y*10 + x%10
		}
		return y
	}
	return abs(n - reverse(n))
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function mirrorDistance(n: number): number {
    const reverse = (x: number): number => {
        let y = 0;
        for (; x > 0; x = Math.floor(x / 10)) {
            y = y * 10 + (x % 10);
        }
        return y;
    };
    return Math.abs(n - reverse(n));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
