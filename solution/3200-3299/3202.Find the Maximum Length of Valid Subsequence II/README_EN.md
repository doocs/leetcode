---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3202.Find%20the%20Maximum%20Length%20of%20Valid%20Subsequence%20II/README_EN.md
rating: 1973
source: Weekly Contest 404 Q3
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3202. Find the Maximum Length of Valid Subsequence II](https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-ii)

[中文文档](/solution/3200-3299/3202.Find%20the%20Maximum%20Length%20of%20Valid%20Subsequence%20II/README.md)

## Description

<!-- description:start -->

You are given an integer array <code>nums</code> and a <strong>positive</strong> integer <code>k</code>.

<p>A <span data-keyword="subsequence-array">subsequence</span> <code>sub</code> of <code>nums</code> with length <code>x</code> is called <strong>valid</strong> if it satisfies:</p>

<ul>
	<li><code>(sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k.</code></li>
</ul>
Return the length of the <strong>longest</strong> <strong>valid</strong> subsequence of <code>nums</code>.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest valid subsequence is <code>[1, 2, 3, 4, 5]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,2,3,1,4], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest valid subsequence is <code>[1, 4, 1, 4]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>3</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

Based on the problem description, we know that for a subsequence $a_1, a_2, a_3, \cdots, a_x$, if it satisfies $(a_1 + a_2) \bmod k = (a_2 + a_3) \bmod k$, then $a_1 \bmod k = a_3 \bmod k$. This means that the result of taking modulo $k$ for all odd-indexed elements is the same, and the result for all even-indexed elements is the same as well.

We can solve this problem using dynamic programming. Define the state $f[x][y]$ as the length of the longest valid subsequence where the last element modulo $k$ equals $x$, and the second to last element modulo $k$ equals $y$. Initially, $f[x][y] = 0$.

Iterate through the array $nums$, and for each number $x$, we get $x = x \bmod k$. Then, we can enumerate the sequences where two consecutive numbers modulo $j$ yield the same result, where $j \in [0, k)$. Thus, the previous number modulo $k$ would be $y = (j - x + k) \bmod k$. At this point, $f[x][y] = f[y][x] + 1$.

The answer is the maximum value among all $f[x][y]$.

The time complexity is $O(n \times k)$, and the space complexity is $O(k^2)$. Here, $n$ is the length of the array $\text{nums}$, and $k$ is the given positive integer.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumLength(self, nums: List[int], k: int) -> int:
        f = [[0] * k for _ in range(k)]
        ans = 0
        for x in nums:
            x %= k
            for j in range(k):
                y = (j - x + k) % k
                f[x][y] = f[y][x] + 1
                ans = max(ans, f[x][y])
        return ans
```

#### Java

```java
class Solution {
    public int maximumLength(int[] nums, int k) {
        int[][] f = new int[k][k];
        int ans = 0;
        for (int x : nums) {
            x %= k;
            for (int j = 0; j < k; ++j) {
                int y = (j - x + k) % k;
                f[x][y] = f[y][x] + 1;
                ans = Math.max(ans, f[x][y]);
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
    int maximumLength(vector<int>& nums, int k) {
        int f[k][k];
        memset(f, 0, sizeof(f));
        int ans = 0;
        for (int x : nums) {
            x %= k;
            for (int j = 0; j < k; ++j) {
                int y = (j - x + k) % k;
                f[x][y] = f[y][x] + 1;
                ans = max(ans, f[x][y]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maximumLength(nums []int, k int) (ans int) {
	f := make([][]int, k)
	for i := range f {
		f[i] = make([]int, k)
	}
	for _, x := range nums {
		x %= k
		for j := 0; j < k; j++ {
			y := (j - x + k) % k
			f[x][y] = f[y][x] + 1
			ans = max(ans, f[x][y])
		}
	}
	return
}
```

#### TypeScript

```ts
function maximumLength(nums: number[], k: number): number {
    const f: number[][] = Array.from({ length: k }, () => Array(k).fill(0));
    let ans: number = 0;
    for (let x of nums) {
        x %= k;
        for (let j = 0; j < k; ++j) {
            const y = (j - x + k) % k;
            f[x][y] = f[y][x] + 1;
            ans = Math.max(ans, f[x][y]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
