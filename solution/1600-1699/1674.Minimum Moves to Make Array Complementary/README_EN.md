---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1674.Minimum%20Moves%20to%20Make%20Array%20Complementary/README_EN.md
rating: 2333
tags:
    - Array
    - Hash Table
    - Prefix Sum
---

# [1674. Minimum Moves to Make Array Complementary](https://leetcode.com/problems/minimum-moves-to-make-array-complementary)

[中文文档](/solution/1600-1699/1674.Minimum%20Moves%20to%20Make%20Array%20Complementary/README.md)

## Description

<p>You are given an integer array <code>nums</code> of <strong>even</strong> length <code>n</code> and an integer <code>limit</code>. In one move, you can replace any integer from <code>nums</code> with another integer between <code>1</code> and <code>limit</code>, inclusive.</p>

<p>The array <code>nums</code> is <strong>complementary</strong> if for all indices <code>i</code> (<strong>0-indexed</strong>), <code>nums[i] + nums[n - 1 - i]</code> equals the same number. For example, the array <code>[1,2,3,4]</code> is complementary because for all indices <code>i</code>, <code>nums[i] + nums[n - 1 - i] = 5</code>.</p>

<p>Return the <em><strong>minimum</strong> number of moves required to make </em><code>nums</code><em> <strong>complementary</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,4,3], limit = 4
<strong>Output:</strong> 1
<strong>Explanation:</strong> In 1 move, you can change nums to [1,2,<u>2</u>,3] (underlined elements are changed).
nums[0] + nums[3] = 1 + 3 = 4.
nums[1] + nums[2] = 2 + 2 = 4.
nums[2] + nums[1] = 2 + 2 = 4.
nums[3] + nums[0] = 3 + 1 = 4.
Therefore, nums[i] + nums[n-1-i] = 4 for every i, so nums is complementary.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2,1], limit = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> In 2 moves, you can change nums to [<u>2</u>,2,2,<u>2</u>]. You cannot change any number to 3 since 3 &gt; limit.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1,2], limit = 2
<strong>Output:</strong> 0
<strong>Explanation:</strong> nums is already complementary.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n&nbsp;&lt;=&nbsp;10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i]&nbsp;&lt;= limit &lt;=&nbsp;10<sup>5</sup></code></li>
	<li><code>n</code> is even.</li>
</ul>

## Solutions

### Solution 1: Difference Array

Let's denote $a$ as the smaller value between $nums[i]$ and $nums[n-i-1]$, and $b$ as the larger value between $nums[i]$ and $nums[n-i-1]$.

Suppose that after replacement, the sum of the two numbers is $x$. From the problem, we know that the minimum value of $x$ is $2$, which means both numbers are replaced by $1$; the maximum value is $2 \times limit$, which means both numbers are replaced by $limit$. Therefore, the range of $x$ is $[2,... 2 \times limit]$.

How to find the minimum number of replacements for different $x$?

We analyze and find:

-   If $x = a + b$, then the number of replacements we need is $0$, which means the current pair of numbers already meets the complement requirement;
-   Otherwise, if $1 + a \le x \le limit + b $, then the number of replacements we need is $1$, which means we can replace one of the numbers;
-   Otherwise, if $2 \le x \le 2 \times limit$, then the number of replacements we need is $2$, which means we need to replace both numbers.

Therefore, we can iterate over each pair of numbers and perform the following operations:

1. First, add $2$ to the number of operations required in the range $[2,... 2 \times limit]$.
1. Then, subtract $1$ from the number of operations required in the range $[1 + a,... limit + b]$.
1. Finally, subtract $1$ from the number of operations required in the range $[a + b,... a + b]$.

We can see that this is actually adding and subtracting elements in a continuous interval, so we can use a difference array to implement it.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def minMoves(self, nums: List[int], limit: int) -> int:
        d = [0] * (limit * 2 + 2)
        n = len(nums)

        for i in range(n >> 1):
            a, b = min(nums[i], nums[n - i - 1]), max(nums[i], nums[n - i - 1])

            d[2] += 2
            d[limit * 2 + 1] -= 2

            d[a + 1] -= 1
            d[b + limit + 1] += 1

            d[a + b] -= 1
            d[a + b + 1] += 1

        ans, s = n, 0
        for v in d[2 : limit * 2 + 1]:
            s += v
            if ans > s:
                ans = s
        return ans
```

```java
class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] d = new int[limit * 2 + 2];
        for (int i = 0; i < n >> 1; ++i) {
            int a = Math.min(nums[i], nums[n - i - 1]);
            int b = Math.max(nums[i], nums[n - i - 1]);

            d[2] += 2;
            d[limit * 2 + 1] -= 2;

            d[a + 1] -= 1;
            d[b + limit + 1] += 1;

            d[a + b] -= 1;
            d[a + b + 1] += 1;
        }
        int ans = n, s = 0;
        for (int i = 2; i <= limit * 2; ++i) {
            s += d[i];
            if (ans > s) {
                ans = s;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minMoves(vector<int>& nums, int limit) {
        int n = nums.size();
        vector<int> d(limit * 2 + 2);
        for (int i = 0; i < n >> 1; ++i) {
            int a = min(nums[i], nums[n - i - 1]);
            int b = max(nums[i], nums[n - i - 1]);

            d[2] += 2;
            d[limit * 2 + 1] -= 2;

            d[a + 1] -= 1;
            d[b + limit + 1] += 1;

            d[a + b] -= 1;
            d[a + b + 1] += 1;
        }
        int ans = n, s = 0;
        for (int i = 2; i <= limit * 2; ++i) {
            s += d[i];
            if (ans > s) {
                ans = s;
            }
        }
        return ans;
    }
};
```

```go
func minMoves(nums []int, limit int) int {
	d := make([]int, limit*2+2)
	n := len(nums)
	for i := 0; i < n>>1; i++ {
		a, b := min(nums[i], nums[n-i-1]), max(nums[i], nums[n-i-1])
		d[2] += 2
		d[limit*2+1] -= 2

		d[a+1] -= 1
		d[b+limit+1] += 1

		d[a+b] -= 1
		d[a+b+1] += 1
	}
	ans, s := n, 0
	for _, v := range d[2 : limit*2+1] {
		s += v
		if ans > s {
			ans = s
		}
	}
	return ans
}
```

```ts
function minMoves(nums: number[], limit: number): number {
    const n = nums.length;
    const d: number[] = Array(limit * 2 + 2).fill(0);
    for (let i = 0; i < n >> 1; ++i) {
        const a = Math.min(nums[i], nums[n - i - 1]);
        const b = Math.max(nums[i], nums[n - i - 1]);

        d[2] += 2;
        d[limit * 2 + 1] -= 2;

        d[a + 1] -= 1;
        d[b + limit + 1] += 1;

        d[a + b] -= 1;
        d[a + b + 1] += 1;
    }
    let ans = n;
    let s = 0;
    for (let i = 2; i <= limit * 2; ++i) {
        s += d[i];
        if (ans > s) {
            ans = s;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
