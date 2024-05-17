---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0172.Factorial%20Trailing%20Zeroes/README_EN.md
tags:
    - Math
---

<!-- problem:start -->

# [172. Factorial Trailing Zeroes](https://leetcode.com/problems/factorial-trailing-zeroes)

[中文文档](/solution/0100-0199/0172.Factorial%20Trailing%20Zeroes/README.md)

## Description

<!-- description:start -->

<p>Given an integer <code>n</code>, return <em>the number of trailing zeroes in </em><code>n!</code>.</p>

<p>Note that <code>n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong> 3! = 6, no trailing zero.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> 1
<strong>Explanation:</strong> 5! = 120, one trailing zero.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 0
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you write a solution that works in logarithmic time complexity?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

The problem is actually asking how many factors of $5$ are there in $[1,n]$.

Let's take $130$ as an example for analysis:

1. Divide by $5$ for the first time, get $26$, indicating that there are $26$ numbers containing the factor $5$;
2. Divide by $5$ for the second time, get $5$, indicating that there are $5$ numbers containing the factor $5^2$;
3. Divide by $5$ for the third time, get $1$, indicating that there is $1$ number containing the factor $5^3$;
4. Sum up to get the count of all factors of $5$ in $[1,n]$.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def trailingZeroes(self, n: int) -> int:
        ans = 0
        while n:
            n //= 5
            ans += n
        return ans
```

#### Java

```java
class Solution {
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n > 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int trailingZeroes(int n) {
        int ans = 0;
        while (n) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
};
```

#### Go

```go
func trailingZeroes(n int) int {
	ans := 0
	for n > 0 {
		n /= 5
		ans += n
	}
	return ans
}
```

#### TypeScript

```ts
function trailingZeroes(n: number): number {
    let ans = 0;
    while (n > 0) {
        n = Math.floor(n / 5);
        ans += n;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
