---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1716.Calculate%20Money%20in%20Leetcode%20Bank/README_EN.md
rating: 1294
source: Biweekly Contest 43 Q1
tags:
    - Math
---

<!-- problem:start -->

# [1716. Calculate Money in Leetcode Bank](https://leetcode.com/problems/calculate-money-in-leetcode-bank)

[中文文档](/solution/1700-1799/1716.Calculate%20Money%20in%20Leetcode%20Bank/README.md)

## Description

<!-- description:start -->

<p>Hercy wants to save money for his first car. He puts money in the Leetcode&nbsp;bank <strong>every day</strong>.</p>

<p>He starts by putting in <code>$1</code> on Monday, the first day. Every day from Tuesday to Sunday, he will put in <code>$1</code> more than the day before. On every subsequent Monday, he will put in <code>$1</code> more than the <strong>previous Monday</strong>.<span style="display: none;"> </span></p>

<p>Given <code>n</code>, return <em>the total amount of money he will have in the Leetcode bank at the end of the </em><code>n<sup>th</sup></code><em> day.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> 10
<strong>Explanation:</strong>&nbsp;After the 4<sup>th</sup> day, the total is 1 + 2 + 3 + 4 = 10.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 37
<strong>Explanation:</strong>&nbsp;After the 10<sup>th</sup> day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37. Notice that on the 2<sup>nd</sup> Monday, Hercy only puts in $2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 20
<strong>Output:</strong> 96
<strong>Explanation:</strong>&nbsp;After the 20<sup>th</sup> day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Math

According to the problem description, the deposit situation for each week is as follows:

```bash
Week 1: 1, 2, 3, 4, 5, 6, 7
Week 2: 2, 3, 4, 5, 6, 7, 8
Week 3: 3, 4, 5, 6, 7, 8, 9
...
Week k: k, k+1, k+2, k+3, k+4, k+5, k+6
```

Given $n$ days of deposits, the number of complete weeks is $k = \lfloor n / 7 \rfloor$, and the remaining days is $b = n \mod 7$.

The total deposit for the complete $k$ weeks can be calculated using the arithmetic sequence sum formula:

$$
S_1 = \frac{k}{2} \times (28 + 28 + 7 \times (k - 1))
$$

The total deposit for the remaining $b$ days can also be calculated using the arithmetic sequence sum formula:

$$
S_2 = \frac{b}{2} \times (k + 1 + k + 1 + b - 1)
$$

The final total deposit amount is $S = S_1 + S_2$.

The time complexity is $O(1)$ and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def totalMoney(self, n: int) -> int:
        k, b = divmod(n, 7)
        s1 = (28 + 28 + 7 * (k - 1)) * k // 2
        s2 = (k + 1 + k + 1 + b - 1) * b // 2
        return s1 + s2
```

#### Java

```java
class Solution {
    public int totalMoney(int n) {
        int k = n / 7, b = n % 7;
        int s1 = (28 + 28 + 7 * (k - 1)) * k / 2;
        int s2 = (k + 1 + k + 1 + b - 1) * b / 2;
        return s1 + s2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int totalMoney(int n) {
        int k = n / 7, b = n % 7;
        int s1 = (28 + 28 + 7 * (k - 1)) * k / 2;
        int s2 = (k + 1 + k + 1 + b - 1) * b / 2;
        return s1 + s2;
    }
};
```

#### Go

```go
func totalMoney(n int) int {
	k, b := n/7, n%7
	s1 := (28 + 28 + 7*(k-1)) * k / 2
	s2 := (k + 1 + k + 1 + b - 1) * b / 2
	return s1 + s2
}
```

#### TypeScript

```ts
function totalMoney(n: number): number {
    const k = (n / 7) | 0;
    const b = n % 7;
    const s1 = (((28 + 28 + 7 * (k - 1)) * k) / 2) | 0;
    const s2 = (((k + 1 + k + 1 + b - 1) * b) / 2) | 0;
    return s1 + s2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
