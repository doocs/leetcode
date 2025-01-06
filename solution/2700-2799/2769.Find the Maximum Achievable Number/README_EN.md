---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2769.Find%20the%20Maximum%20Achievable%20Number/README_EN.md
rating: 1191
source: Weekly Contest 353 Q1
tags:
    - Math
---

<!-- problem:start -->

# [2769. Find the Maximum Achievable Number](https://leetcode.com/problems/find-the-maximum-achievable-number)

[中文文档](/solution/2700-2799/2769.Find%20the%20Maximum%20Achievable%20Number/README.md)

## Description

<!-- description:start -->

<p>Given two integers, <code>num</code> and <code>t</code>. A <strong>number </strong><code>x</code><strong> </strong>is<strong> achievable</strong> if it can become equal to <code>num</code> after applying the following operation <strong>at most</strong> <code>t</code> times:</p>

<ul>
	<li>Increase or decrease <code>x</code> by <code>1</code>, and <em>simultaneously</em> increase or decrease <code>num</code> by <code>1</code>.</li>
</ul>

<p>Return the <strong>maximum </strong>possible value of <code>x</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">num = 4, t = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>Apply the following operation once to make the maximum achievable number equal to <code>num</code>:</p>

<ul>
	<li>Decrease the maximum achievable number by 1, and increase <code>num</code> by 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">num = 3, t = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>Apply the following operation twice to make the maximum achievable number equal to <code>num</code>:</p>

<ul>
	<li>Decrease the maximum achievable number by 1, and increase <code>num</code> by 1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num, t&nbsp;&lt;= 50</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

Notice that every time we can decrease $x$ by $1$ and increase $num$ by $1$, the difference between $x$ and $num$ will decrease by $2$, and we can do this operation at most $t$ times, so the maximum reachable number is $num + t \times 2$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def theMaximumAchievableX(self, num: int, t: int) -> int:
        return num + t * 2
```

#### Java

```java
class Solution {
    public int theMaximumAchievableX(int num, int t) {
        return num + t * 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int theMaximumAchievableX(int num, int t) {
        return num + t * 2;
    }
};
```

#### Go

```go
func theMaximumAchievableX(num int, t int) int {
	return num + t*2
}
```

#### TypeScript

```ts
function theMaximumAchievableX(num: number, t: number): number {
    return num + t * 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
