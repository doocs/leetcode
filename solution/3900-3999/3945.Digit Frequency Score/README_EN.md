---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3945.Digit%20Frequency%20Score/README_EN.md
---

<!-- problem:start -->

# [3945. Digit Frequency Score](https://leetcode.com/problems/digit-frequency-score)

[中文文档](/solution/3900-3999/3945.Digit%20Frequency%20Score/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>.</p>

<p>The <strong>score</strong> of <code>n</code> is defined as the <strong>sum</strong> of <code>d * freq(d)</code> over all <strong>distinct</strong> digits <code>d</code>, where <code>freq(d)</code> denotes the number of times the digit <code>d</code> appears in <code>n</code>.</p>

<p>Return an integer denoting the score of <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 122</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The digit 1 appears 1 time, contributing <code>1 * 1 = 1</code>.</li>
	<li>The digit 2 appears 2 times, contributing <code>2 * 2 = 4</code>.</li>
	<li>Thus, the score of <code>n</code> is <code>1 + 4 = 5</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 101</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The digit 0 appears 1 time, contributing <code>0 * 1 = 0</code>.</li>
	<li>The digit 1 appears 2 times, contributing <code>1 * 2 = 2</code>.</li>
	<li>Thus, the score of <code>n</code> is 2.</li>
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

The problem is equivalent to finding the sum of each digit of a number. We can obtain each digit by repeatedly taking the modulus and dividing by 10, and accumulate the result.

The time complexity is $O(\log n)$, where $\log n$ is the number of digits in $n$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def digitFrequencyScore(self, n: int) -> int:
        ans = 0
        while n:
            n, x = divmod(n, 10)
            ans += x
        return ans
```

#### Java

```java
class Solution {
    public int digitFrequencyScore(int n) {
        int ans = 0;
        for (; n > 0; n /= 10) {
            ans += n % 10;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int digitFrequencyScore(int n) {
        int ans = 0;
        for (; n > 0; n /= 10) {
            ans += n % 10;
        }
        return ans;
    }
};
```

#### Go

```go
func digitFrequencyScore(n int) (ans int) {
	for ; n > 0; n /= 10 {
		ans += n % 10
	}
	return
}
```

#### TypeScript

```ts
function digitFrequencyScore(n: number): number {
    let ans = 0;
    for (; n; n = Math.floor(n / 10)) {
        ans += n % 10;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
