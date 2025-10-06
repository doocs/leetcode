---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3658.GCD%20of%20Odd%20and%20Even%20Sums/README_EN.md
rating: 1220
source: Weekly Contest 464 Q1
tags:
    - Math
    - Number Theory
---

<!-- problem:start -->

# [3658. GCD of Odd and Even Sums](https://leetcode.com/problems/gcd-of-odd-and-even-sums)

[中文文档](/solution/3600-3699/3658.GCD%20of%20Odd%20and%20Even%20Sums/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>. Your task is to compute the <strong>GCD</strong> (greatest common divisor) of two values:</p>

<ul>
	<li>
	<p><code>sumOdd</code>: the sum of the first <code>n</code> odd numbers.</p>
	</li>
	<li>
	<p><code>sumEven</code>: the sum of the first <code>n</code> even numbers.</p>
	</li>
</ul>

<p>Return the GCD of <code>sumOdd</code> and <code>sumEven</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Sum of the first 4 odd numbers <code>sumOdd = 1 + 3 + 5 + 7 = 16</code></li>
	<li>Sum of the first 4 even numbers <code>sumEven = 2 + 4 + 6 + 8 = 20</code></li>
</ul>

<p>Hence, <code>GCD(sumOdd, sumEven) = GCD(16, 20) = 4</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Sum of the first 5 odd numbers <code>sumOdd = 1 + 3 + 5 + 7 + 9 = 25</code></li>
	<li>Sum of the first 5 even numbers <code>sumEven = 2 + 4 + 6 + 8 + 10 = 30</code></li>
</ul>

<p>Hence, <code>GCD(sumOdd, sumEven) = GCD(25, 30) = 5</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10​​​​​​​00</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

The sum of the first $n$ odd numbers is $n^2$, while the sum of the first $n$ even numbers is $n(n + 1)$. The greatest common divisor of these two is at least $n$. Since $n$ and $n + 1$ are coprime, the answer is $n$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def gcdOfOddEvenSums(self, n: int) -> int:
        return n
```

#### Java

```java
class Solution {
    public int gcdOfOddEvenSums(int n) {
        return n;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int gcdOfOddEvenSums(int n) {
        return n;
    }
};
```

#### Go

```go
func gcdOfOddEvenSums(n int) int {
	return n
}
```

#### TypeScript

```ts
function gcdOfOddEvenSums(n: number): number {
    return n;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
