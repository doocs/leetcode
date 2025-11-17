---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3738.Longest%20Non-Decreasing%20Subarray%20After%20Replacing%20at%20Most%20One%20Element/README_EN.md
rating: 1811
source: Biweekly Contest 169 Q3
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3738. Longest Non-Decreasing Subarray After Replacing at Most One Element](https://leetcode.com/problems/longest-non-decreasing-subarray-after-replacing-at-most-one-element)

[中文文档](/solution/3700-3799/3738.Longest%20Non-Decreasing%20Subarray%20After%20Replacing%20at%20Most%20One%20Element/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>You are allowed to replace <strong>at most</strong> one element in the array with any other integer value of your choice.</p>

<p>Return the length of the <strong>longest non-decreasing <span data-keyword="subarray">subarray</span></strong> that can be obtained after performing at most one replacement.</p>

<p>An array is said to be <strong>non-decreasing</strong> if each element is greater than or equal to its previous one (if it exists).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Replacing <code>nums[3] = 1</code> with 3 gives the array [1, 2, 3, 3, 2].</p>

<p>The longest non-decreasing subarray is [1, 2, 3, 3], which has a length of 4.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2,2,2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>All elements in <code>nums</code> are equal, so it is already non-decreasing and the entire <code>nums</code> forms a subarray of length 5.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code>​​​​​​​</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix and Suffix Decomposition + Enumeration

We can use two arrays $\textit{left}$ and $\textit{right}$ to record the length of the longest non-decreasing subarray ending and starting at each position, respectively. Initially, $\textit{left}[i] = 1$ and $\textit{right}[i] = 1$.

Then, we traverse the array in the range $[1, n-1]$. If $\textit{nums}[i] \geq \textit{nums}[i-1]$, we update $\textit{left}[i]$ to $\textit{left}[i-1] + 1$. Similarly, we traverse the array backwards in the range $[n-2, 0]$. If $\textit{nums}[i] \leq \textit{nums}[i+1]$, we update $\textit{right}[i]$ to $\textit{right}[i+1] + 1$.

Next, we can compute the final answer by enumerating each position. For each position $i$, we can calculate the length of the longest non-decreasing subarray centered at $i$ in the following way:

1. If the elements on the left and right sides of $i$ do not satisfy $\textit{nums}[i-1] \leq \textit{nums}[i+1]$, we can only choose the non-decreasing subarray from either the left or right side, so the answer is $\max(\textit{left}[i-1], \textit{right}[i+1]) + 1$.
2. Otherwise, we can replace position $i$ with an appropriate value so that the non-decreasing subarrays on the left and right can be connected, so the answer is $\textit{left}[i-1] + \textit{right}[i+1] + 1$.

Finally, we take the maximum value across all positions as the final answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        n = len(nums)
        left = [1] * n
        right = [1] * n
        for i in range(1, n):
            if nums[i] >= nums[i - 1]:
                left[i] = left[i - 1] + 1
        for i in range(n - 2, -1, -1):
            if nums[i] <= nums[i + 1]:
                right[i] = right[i + 1] + 1
        ans = max(left)
        for i in range(n):
            a = 0 if i - 1 < 0 else left[i - 1]
            b = 0 if i + 1 >= n else right[i + 1]
            if i - 1 >= 0 and i + 1 < n and nums[i - 1] > nums[i + 1]:
                ans = max(ans, a + 1, b + 1)
            else:
                ans = max(ans, a + b + 1)
        return ans
```

#### Java

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        int ans = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] >= nums[i - 1]) {
                left[i] = left[i - 1] + 1;
                ans = Math.max(ans, left[i]);
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= nums[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            int a = (i - 1 < 0) ? 0 : left[i - 1];
            int b = (i + 1 >= n) ? 0 : right[i + 1];
            if (i - 1 >= 0 && i + 1 < n && nums[i - 1] > nums[i + 1]) {
                ans = Math.max(ans, Math.max(a + 1, b + 1));
            } else {
                ans = Math.max(ans, a + b + 1);
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
    int longestSubarray(vector<int>& nums) {
        int n = nums.size();
        vector<int> left(n, 1), right(n, 1);

        for (int i = 1; i < n; ++i) {
            if (nums[i] >= nums[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] <= nums[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }

        int ans = ranges::max(left);

        for (int i = 0; i < n; ++i) {
            int a = (i - 1 < 0) ? 0 : left[i - 1];
            int b = (i + 1 >= n) ? 0 : right[i + 1];
            if (i - 1 >= 0 && i + 1 < n && nums[i - 1] > nums[i + 1]) {
                ans = max({ans, a + 1, b + 1});
            } else {
                ans = max(ans, a + b + 1);
            }
        }

        return ans;
    }
};
```

#### Go

```go
func longestSubarray(nums []int) int {
	n := len(nums)
	left := make([]int, n)
	right := make([]int, n)
	for i := range left {
		left[i], right[i] = 1, 1
	}

	for i := 1; i < n; i++ {
		if nums[i] >= nums[i-1] {
			left[i] = left[i-1] + 1
		}
	}

	for i := n - 2; i >= 0; i-- {
		if nums[i] <= nums[i+1] {
			right[i] = right[i+1] + 1
		}
	}

	ans := slices.Max(left)

	for i := 0; i < n; i++ {
		a := 0
		if i > 0 {
			a = left[i-1]
		}
		b := 0
		if i+1 < n {
			b = right[i+1]
		}
		if i > 0 && i+1 < n && nums[i-1] > nums[i+1] {
			ans = max(ans, max(a+1, b+1))
		} else {
			ans = max(ans, a+b+1)
		}
	}

	return ans
}
```

#### TypeScript

```ts
function longestSubarray(nums: number[]): number {
    const n = nums.length;
    const left: number[] = Array(n).fill(1);
    const right: number[] = Array(n).fill(1);

    for (let i = 1; i < n; i++) {
        if (nums[i] >= nums[i - 1]) {
            left[i] = left[i - 1] + 1;
        }
    }

    for (let i = n - 2; i >= 0; i--) {
        if (nums[i] <= nums[i + 1]) {
            right[i] = right[i + 1] + 1;
        }
    }

    let ans = Math.max(...left);

    for (let i = 0; i < n; i++) {
        const a = i - 1 < 0 ? 0 : left[i - 1];
        const b = i + 1 >= n ? 0 : right[i + 1];
        if (i - 1 >= 0 && i + 1 < n && nums[i - 1] > nums[i + 1]) {
            ans = Math.max(ans, Math.max(a + 1, b + 1));
        } else {
            ans = Math.max(ans, a + b + 1);
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
