---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2894.Divisible%20and%20Non-divisible%20Sums%20Difference/README_EN.md
rating: 1140
source: Weekly Contest 366 Q1
tags:
    - Math
---

<!-- problem:start -->

# [2894. Divisible and Non-divisible Sums Difference](https://leetcode.com/problems/divisible-and-non-divisible-sums-difference)

[中文文档](/solution/2800-2899/2894.Divisible%20and%20Non-divisible%20Sums%20Difference/README.md)

## Description

<!-- description:start -->

<p>You are given positive integers <code>n</code> and <code>m</code>.</p>

<p>Define two integers, <code>num1</code> and <code>num2</code>, as follows:</p>

<ul>
	<li><code>num1</code>: The sum of all integers in the range <code>[1, n]</code> that are <strong>not divisible</strong> by <code>m</code>.</li>
	<li><code>num2</code>: The sum of all integers in the range <code>[1, n]</code> that are <strong>divisible</strong> by <code>m</code>.</li>
</ul>

<p>Return <em>the integer</em> <code>num1 - num2</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 10, m = 3
<strong>Output:</strong> 19
<strong>Explanation:</strong> In the given example:
- Integers in the range [1, 10] that are not divisible by 3 are [1,2,4,5,7,8,10], num1 is the sum of those integers = 37.
- Integers in the range [1, 10] that are divisible by 3 are [3,6,9], num2 is the sum of those integers = 18.
We return 37 - 18 = 19 as the answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5, m = 6
<strong>Output:</strong> 15
<strong>Explanation:</strong> In the given example:
- Integers in the range [1, 5] that are not divisible by 6 are [1,2,3,4,5], num1 is the sum of those integers = 15.
- Integers in the range [1, 5] that are divisible by 6 are [], num2 is the sum of those integers = 0.
We return 15 - 0 = 15 as the answer.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 5, m = 1
<strong>Output:</strong> -15
<strong>Explanation:</strong> In the given example:
- Integers in the range [1, 5] that are not divisible by 1 are [], num1 is the sum of those integers = 0.
- Integers in the range [1, 5] that are divisible by 1 are [1,2,3,4,5], num2 is the sum of those integers = 15.
We return 0 - 15 = -15 as the answer.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, m &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We traverse every number in the range $[1, n]$. If it is divisible by $m$, we subtract it from the answer. Otherwise, we add it to the answer.

After the traversal, we return the answer.

The time complexity is $O(n)$, where $n$ is the given integer. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def differenceOfSums(self, n: int, m: int) -> int:
        return sum(i if i % m else -i for i in range(1, n + 1))
```

#### Java

```java
class Solution {
    public int differenceOfSums(int n, int m) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans += i % m == 0 ? -i : i;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int differenceOfSums(int n, int m) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans += i % m ? i : -i;
        }
        return ans;
    }
};
```

#### Go

```go
func differenceOfSums(n int, m int) (ans int) {
	for i := 1; i <= n; i++ {
		if i%m == 0 {
			ans -= i
		} else {
			ans += i
		}
	}
	return
}
```

#### TypeScript

```ts
function differenceOfSums(n: number, m: number): number {
    let ans = 0;
    for (let i = 1; i <= n; ++i) {
        ans += i % m ? i : -i;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### Java

```java
class Solution {
    public int differenceOfSums(int n, int m) {
        int sum = n * (n + 1) / 2;
        int k = n / m;
        int nums2 = k * (k + 1) / 2 * m;
        return sum - nums2 * 2;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
