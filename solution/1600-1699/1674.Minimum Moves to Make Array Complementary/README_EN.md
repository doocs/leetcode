---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1674.Minimum%20Moves%20to%20Make%20Array%20Complementary/README_EN.md
rating: 2333
source: Weekly Contest 217 Q3
tags:
    - Array
    - Hash Table
    - Prefix Sum
---

<!-- problem:start -->

# [1674. Minimum Moves to Make Array Complementary](https://leetcode.com/problems/minimum-moves-to-make-array-complementary)

[中文文档](/solution/1600-1699/1674.Minimum%20Moves%20to%20Make%20Array%20Complementary/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Difference Array

Assume that in the final array, the sum of the pair $\textit{nums}[i]$ and $\textit{nums}[n-i-1]$ is $s$.

Let's denote $x$ as the smaller value between $\textit{nums}[i]$ and $\textit{nums}[n-i-1]$, and $y$ as the larger value.

For each pair of numbers, we have the following scenarios:

-   If no replacement is needed, then $x + y = s$.
-   If one replacement is made, then $x + 1 \le s \le y + \textit{limit}$.
-   If two replacements are made, then $2 \le s \le x$ or $y + \textit{limit} + 1 \le s \le 2 \times \textit{limit}$.

That is:

-   In the range $[2,..x]$, $2$ replacements are needed.
-   In the range $[x+1,..x+y-1]$, $1$ replacement is needed.
-   At $[x+y]$, no replacement is needed.
-   In the range $[x+y+1,..y + \textit{limit}]$, $1$ replacement is needed.
-   In the range $[y + \textit{limit} + 1,..2 \times \textit{limit}]$, $2$ replacements are needed.

We enumerate each pair of numbers and use a difference array to update the number of replacements needed in different ranges for each pair.

Finally, we find the minimum value among the prefix sums from index $2$ to $2 \times \textit{limit}$, which is the minimum number of replacements needed.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

Similar problems:

-   [3224. Minimum Array Changes to Make Differences Equal](https://github.com/doocs/leetcode/blob/main/solution/3200-3299/3224.Minimum%20Array%20Changes%20to%20Make%20Differences%20Equal/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMoves(self, nums: List[int], limit: int) -> int:
        d = [0] * (2 * limit + 2)
        n = len(nums)
        for i in range(n // 2):
            x, y = nums[i], nums[-i - 1]
            if x > y:
                x, y = y, x
            d[2] += 2
            d[x + 1] -= 2
            d[x + 1] += 1
            d[x + y] -= 1
            d[x + y + 1] += 1
            d[y + limit + 1] -= 1
            d[y + limit + 1] += 2
        return min(accumulate(d[2:]))
```

#### Java

```java
class Solution {
    public int minMoves(int[] nums, int limit) {
        int[] d = new int[2 * limit + 2];
        int n = nums.length;
        for (int i = 0; i < n / 2; ++i) {
            int x = Math.min(nums[i], nums[n - i - 1]);
            int y = Math.max(nums[i], nums[n - i - 1]);
            d[2] += 2;
            d[x + 1] -= 2;
            d[x + 1] += 1;
            d[x + y] -= 1;
            d[x + y + 1] += 1;
            d[y + limit + 1] -= 1;
            d[y + limit + 1] += 2;
        }
        int ans = n;
        for (int i = 2, s = 0; i < d.length; ++i) {
            s += d[i];
            ans = Math.min(ans, s);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMoves(vector<int>& nums, int limit) {
        int n = nums.size();
        int d[limit * 2 + 2];
        memset(d, 0, sizeof(d));
        for (int i = 0; i < n / 2; ++i) {
            int x = nums[i], y = nums[n - i - 1];
            if (x > y) {
                swap(x, y);
            }
            d[2] += 2;
            d[x + 1] -= 2;
            d[x + 1] += 1;
            d[x + y] -= 1;
            d[x + y + 1] += 1;
            d[y + limit + 1] -= 1;
            d[y + limit + 1] += 2;
        }
        int ans = n;
        for (int i = 2, s = 0; i <= limit * 2; ++i) {
            s += d[i];
            ans = min(ans, s);
        }
        return ans;
    }
};
```

#### Go

```go
func minMoves(nums []int, limit int) int {
	n := len(nums)
	d := make([]int, 2*limit+2)
	for i := 0; i < n/2; i++ {
		x, y := nums[i], nums[n-1-i]
		if x > y {
			x, y = y, x
		}
		d[2] += 2
		d[x+1] -= 2
		d[x+1] += 1
		d[x+y] -= 1
		d[x+y+1] += 1
		d[y+limit+1] -= 1
		d[y+limit+1] += 2
	}
	ans, s := n, 0
	for _, x := range d[2:] {
		s += x
		ans = min(ans, s)
	}
	return ans
}
```

#### TypeScript

```ts
function minMoves(nums: number[], limit: number): number {
    const n = nums.length;
    const d: number[] = Array(limit * 2 + 2).fill(0);
    for (let i = 0; i < n >> 1; ++i) {
        const x = Math.min(nums[i], nums[n - 1 - i]);
        const y = Math.max(nums[i], nums[n - 1 - i]);
        d[2] += 2;
        d[x + 1] -= 2;
        d[x + 1] += 1;
        d[x + y] -= 1;
        d[x + y + 1] += 1;
        d[y + limit + 1] -= 1;
        d[y + limit + 1] += 2;
    }
    let ans = n;
    let s = 0;
    for (let i = 2; i < d.length; ++i) {
        s += d[i];
        ans = Math.min(ans, s);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
