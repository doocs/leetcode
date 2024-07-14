---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0829.Consecutive%20Numbers%20Sum/README_EN.md
tags:
    - Math
    - Enumeration
---

<!-- problem:start -->

# [829. Consecutive Numbers Sum](https://leetcode.com/problems/consecutive-numbers-sum)

[中文文档](/solution/0800-0899/0829.Consecutive%20Numbers%20Sum/README.md)

## Description

<!-- description:start -->

<p>Given an integer <code>n</code>, return <em>the number of ways you can write </em><code>n</code><em> as the sum of consecutive positive integers.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> 5 = 2 + 3
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 9
<strong>Output:</strong> 3
<strong>Explanation:</strong> 9 = 4 + 5 = 2 + 3 + 4
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 15
<strong>Output:</strong> 4
<strong>Explanation:</strong> 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematical Derivation

Consecutive positive integers form an arithmetic sequence with a common difference $d = 1$. Let's assume the first term of the sequence is $a$, and the number of terms is $k$. Then, $n = (a + a + k - 1) \times k / 2$, which simplifies to $n \times 2 = (a \times 2 + k - 1) \times k$. From this, we can deduce that $k$ must divide $n \times 2$ evenly, and $(n \times 2) / k - k + 1$ must be an even number.

Given that $a \geq 1$, it follows that $n \times 2 = (a \times 2 + k - 1) \times k \geq k \times (k + 1)$.

In summary, we can conclude:

1. $k$ must divide $n \times 2$ evenly;
2. $k \times (k + 1) \leq n \times 2$;
3. $(n \times 2) / k - k + 1$ must be an even number.

We start enumerating from $k = 1$, and we can stop when $k \times (k + 1) > n \times 2$. During the enumeration, we check if $k$ divides $n \times 2$ evenly, and if $(n \times 2) / k - k + 1$ is an even number. If both conditions are met, it satisfies the criteria, and we increment the answer by one.

After finishing the enumeration, we return the answer.

The time complexity is $O(\sqrt{n})$, where $n$ is the given positive integer. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def consecutiveNumbersSum(self, n: int) -> int:
        n <<= 1
        ans, k = 0, 1
        while k * (k + 1) <= n:
            if n % k == 0 and (n // k - k + 1) % 2 == 0:
                ans += 1
            k += 1
        return ans
```

#### Java

```java
class Solution {

    public int consecutiveNumbersSum(int n) {
        n <<= 1;
        int ans = 0;
        for (int k = 1; k * (k + 1) <= n; ++k) {
            if (n % k == 0 && (n / k + 1 - k) % 2 == 0) {
                ++ans;
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
    int consecutiveNumbersSum(int n) {
        n <<= 1;
        int ans = 0;
        for (int k = 1; k * (k + 1) <= n; ++k) {
            if (n % k == 0 && (n / k + 1 - k) % 2 == 0) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func consecutiveNumbersSum(n int) int {
	n <<= 1
	ans := 0
	for k := 1; k*(k+1) <= n; k++ {
		if n%k == 0 && (n/k+1-k)%2 == 0 {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function consecutiveNumbersSum(n: number): number {
    let ans = 0;
    n <<= 1;
    for (let k = 1; k * (k + 1) <= n; ++k) {
        if (n % k === 0 && (Math.floor(n / k) + 1 - k) % 2 === 0) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
