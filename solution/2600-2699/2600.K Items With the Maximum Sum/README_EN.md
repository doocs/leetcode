---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2600.K%20Items%20With%20the%20Maximum%20Sum/README_EN.md
rating: 1434
source: Weekly Contest 338 Q1
tags:
    - Greedy
    - Math
---

<!-- problem:start -->

# [2600. K Items With the Maximum Sum](https://leetcode.com/problems/k-items-with-the-maximum-sum)

[中文文档](/solution/2600-2699/2600.K%20Items%20With%20the%20Maximum%20Sum/README.md)

## Description

<!-- description:start -->

<p>There is a bag that consists of items, each item&nbsp;has a number <code>1</code>, <code>0</code>, or <code>-1</code> written on it.</p>

<p>You are given four <strong>non-negative </strong>integers <code>numOnes</code>, <code>numZeros</code>, <code>numNegOnes</code>, and <code>k</code>.</p>

<p>The bag initially contains:</p>

<ul>
	<li><code>numOnes</code> items with <code>1</code>s written on them.</li>
	<li><code>numZeroes</code> items with <code>0</code>s written on them.</li>
	<li><code>numNegOnes</code> items with <code>-1</code>s written on them.</li>
</ul>

<p>We want to pick exactly <code>k</code> items among the available items. Return <em>the <strong>maximum</strong> possible sum of numbers written on the items</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> numOnes = 3, numZeros = 2, numNegOnes = 0, k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> We have a bag of items with numbers written on them {1, 1, 1, 0, 0}. We take 2 items with 1 written on them and get a sum in a total of 2.
It can be proven that 2 is the maximum possible sum.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> numOnes = 3, numZeros = 2, numNegOnes = 0, k = 4
<strong>Output:</strong> 3
<strong>Explanation:</strong> We have a bag of items with numbers written on them {1, 1, 1, 0, 0}. We take 3 items with 1 written on them, and 1 item with 0 written on it, and get a sum in a total of 3.
It can be proven that 3 is the maximum possible sum.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= numOnes, numZeros, numNegOnes &lt;= 50</code></li>
	<li><code>0 &lt;= k &lt;= numOnes + numZeros + numNegOnes</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

According to the problem description, we should take as many items marked as $1$ as possible, then take items marked as $0$, and finally take items marked as $-1$.

Thus:

-   If the number of items marked as $1$ in the bag is greater than or equal to $k$, we take $k$ items, and the sum of the numbers is $k$.
-   If the number of items marked as $1$ is less than $k$, we take $\textit{numOnes}$ items, resulting in a sum of $\textit{numOnes}$. If the number of items marked as $0$ is greater than or equal to $k - \textit{numOnes}$, we take $k - \textit{numOnes}$ more items, keeping the sum at $\textit{numOnes}$.
-   Otherwise, we take $k - \textit{numOnes} - \textit{numZeros}$ items from those marked as $-1$, resulting in a sum of $\textit{numOnes} - (k - \textit{numOnes} - \textit{numZeros})$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kItemsWithMaximumSum(
        self, numOnes: int, numZeros: int, numNegOnes: int, k: int
    ) -> int:
        if numOnes >= k:
            return k
        if numZeros >= k - numOnes:
            return numOnes
        return numOnes - (k - numOnes - numZeros)
```

#### Java

```java
class Solution {
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if (numOnes >= k) {
            return k;
        }
        if (numZeros >= k - numOnes) {
            return numOnes;
        }
        return numOnes - (k - numOnes - numZeros);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if (numOnes >= k) {
            return k;
        }
        if (numZeros >= k - numOnes) {
            return numOnes;
        }
        return numOnes - (k - numOnes - numZeros);
    }
};
```

#### Go

```go
func kItemsWithMaximumSum(numOnes int, numZeros int, numNegOnes int, k int) int {
	if numOnes >= k {
		return k
	}
	if numZeros >= k-numOnes {
		return numOnes
	}
	return numOnes - (k - numOnes - numZeros)
}
```

#### TypeScript

```ts
function kItemsWithMaximumSum(
    numOnes: number,
    numZeros: number,
    numNegOnes: number,
    k: number,
): number {
    if (numOnes >= k) {
        return k;
    }
    if (numZeros >= k - numOnes) {
        return numOnes;
    }
    return numOnes - (k - numOnes - numZeros);
}
```

#### Rust

```rust
impl Solution {
    pub fn k_items_with_maximum_sum(
        num_ones: i32,
        num_zeros: i32,
        num_neg_ones: i32,
        k: i32,
    ) -> i32 {
        if num_ones > k {
            return k;
        }

        if num_ones + num_zeros > k {
            return num_ones;
        }

        num_ones - (k - num_ones - num_zeros)
    }
}
```

#### C#

```cs
public class Solution {
    public int KItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if (numOnes >= k) {
            return k;
        }
        if (numZeros >= k - numOnes) {
            return numOnes;
        }
        return numOnes - (k - numOnes - numZeros);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
