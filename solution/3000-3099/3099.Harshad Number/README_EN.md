---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3099.Harshad%20Number/README_EN.md
rating: 1100
source: Weekly Contest 391 Q1
tags:
    - Math
---

<!-- problem:start -->

# [3099. Harshad Number](https://leetcode.com/problems/harshad-number)

[中文文档](/solution/3000-3099/3099.Harshad%20Number/README.md)

## Description

<p>An integer divisible by the <strong>sum</strong> of its digits is said to be a <strong>Harshad</strong> number. You are given an integer <code>x</code>. Return<em> the sum of the digits </em>of<em> </em><code>x</code><em> </em>if<em> </em><code>x</code><em> </em>is a <strong>Harshad</strong> number, otherwise, return<em> </em><code>-1</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">x = 18</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<p>The sum of digits of <code>x</code> is <code>9</code>. <code>18</code> is divisible by <code>9</code>. So <code>18</code> is a Harshad number and the answer is <code>9</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">x = 23</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>The sum of digits of <code>x</code> is <code>5</code>. <code>23</code> is not divisible by <code>5</code>. So <code>23</code> is not a Harshad number and the answer is <code>-1</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= x &lt;= 100</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can calculate the sum of the digits of $x$, denoted as $s$, by simulation. If $x$ can be divided evenly by $s$, then we return $s$, otherwise, we return $-1$.

The time complexity is $O(\log x)$, where $x$ is the input integer. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def sumOfTheDigitsOfHarshadNumber(self, x: int) -> int:
        s, y = 0, x
        while y:
            s += y % 10
            y //= 10
        return s if x % s == 0 else -1
```

```java
class Solution {
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int s = 0;
        for (int y = x; y > 0; y /= 10) {
            s += y % 10;
        }
        return x % s == 0 ? s : -1;
    }
}
```

```cpp
class Solution {
public:
    int sumOfTheDigitsOfHarshadNumber(int x) {
        int s = 0;
        for (int y = x; y > 0; y /= 10) {
            s += y % 10;
        }
        return x % s == 0 ? s : -1;
    }
};
```

```go
func sumOfTheDigitsOfHarshadNumber(x int) int {
	s := 0
	for y := x; y > 0; y /= 10 {
		s += y % 10
	}
	if x%s == 0 {
		return s
	}
	return -1
}
```

```ts
function sumOfTheDigitsOfHarshadNumber(x: number): number {
    let s = 0;
    for (let y = x; y; y = Math.floor(y / 10)) {
        s += y % 10;
    }
    return x % s === 0 ? s : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
