---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2928.Distribute%20Candies%20Among%20Children%20I/README_EN.md
rating: 1393
source: Biweekly Contest 117 Q1
tags:
    - Math
    - Combinatorics
    - Enumeration
---

# [2928. Distribute Candies Among Children I](https://leetcode.com/problems/distribute-candies-among-children-i)

[中文文档](/solution/2900-2999/2928.Distribute%20Candies%20Among%20Children%20I/README.md)

## Description

<p>You are given two positive integers <code>n</code> and <code>limit</code>.</p>

<p>Return <em>the <strong>total number</strong> of ways to distribute </em><code>n</code> <em>candies among </em><code>3</code><em> children such that no child gets more than </em><code>limit</code><em> candies.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5, limit = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 ways to distribute 5 candies such that no child gets more than 2 candies: (1, 2, 2), (2, 1, 2) and (2, 2, 1).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, limit = 3
<strong>Output:</strong> 10
<strong>Explanation:</strong> There are 10 ways to distribute 3 candies such that no child gets more than 3 candies: (0, 0, 3), (0, 1, 2), (0, 2, 1), (0, 3, 0), (1, 0, 2), (1, 1, 1), (1, 2, 0), (2, 0, 1), (2, 1, 0) and (3, 0, 0).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= limit &lt;= 50</code></li>
</ul>

## Solutions

### Solution 1: Combinatorial Mathematics + Principle of Inclusion-Exclusion

According to the problem description, we need to distribute $n$ candies to $3$ children, with each child receiving between $[0, limit]$ candies.

This is equivalent to placing $n$ balls into $3$ boxes. Since the boxes can be empty, we can add $3$ virtual balls, and then use the method of inserting partitions, i.e., there are a total of $n + 3$ balls, and we insert $2$ partitions among the $n + 3 - 1$ positions, thus dividing the actual $n$ balls into $3$ groups, and allowing the boxes to be empty. Therefore, the initial number of schemes is $C_{n + 2}^2$.

We need to exclude the schemes where the number of balls in a box exceeds $limit$. Consider that there is a box where the number of balls exceeds $limit$, then the remaining balls (including virtual balls) have at most $n + 3 - (limit + 1) = n - limit + 2$, and the number of positions is $n - limit + 1$, so the number of schemes is $C_{n - limit + 1}^2$. Since there are $3$ boxes, the number of such schemes is $3 \times C_{n - limit + 1}^2$. In this way, we will exclude too many schemes where the number of balls in two boxes exceeds $limit$ at the same time, so we need to add the number of such schemes, i.e., $3 \times C_{n - 2 \times limit}^2$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def distributeCandies(self, n: int, limit: int) -> int:
        if n > 3 * limit:
            return 0
        ans = comb(n + 2, 2)
        if n > limit:
            ans -= 3 * comb(n - limit + 1, 2)
        if n - 2 >= 2 * limit:
            ans += 3 * comb(n - 2 * limit, 2)
        return ans
```

```java
class Solution {
    public int distributeCandies(int n, int limit) {
        if (n > 3 * limit) {
            return 0;
        }
        long ans = comb2(n + 2);
        if (n > limit) {
            ans -= 3 * comb2(n - limit + 1);
        }
        if (n - 2 >= 2 * limit) {
            ans += 3 * comb2(n - 2 * limit);
        }
        return (int) ans;
    }

    private long comb2(int n) {
        return 1L * n * (n - 1) / 2;
    }
}
```

```cpp
class Solution {
public:
    int distributeCandies(int n, int limit) {
        auto comb2 = [](int n) {
            return 1LL * n * (n - 1) / 2;
        };
        if (n > 3 * limit) {
            return 0;
        }
        long long ans = comb2(n + 2);
        if (n > limit) {
            ans -= 3 * comb2(n - limit + 1);
        }
        if (n - 2 >= 2 * limit) {
            ans += 3 * comb2(n - 2 * limit);
        }
        return ans;
    }
};
```

```go
func distributeCandies(n int, limit int) int {
	comb2 := func(n int) int {
		return n * (n - 1) / 2
	}
	if n > 3*limit {
		return 0
	}
	ans := comb2(n + 2)
	if n > limit {
		ans -= 3 * comb2(n-limit+1)
	}
	if n-2 >= 2*limit {
		ans += 3 * comb2(n-2*limit)
	}
	return ans
}
```

```ts
function distributeCandies(n: number, limit: number): number {
    const comb2 = (n: number) => (n * (n - 1)) / 2;
    if (n > 3 * limit) {
        return 0;
    }
    let ans = comb2(n + 2);
    if (n > limit) {
        ans -= 3 * comb2(n - limit + 1);
    }
    if (n - 2 >= 2 * limit) {
        ans += 3 * comb2(n - 2 * limit);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
