---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2401.Longest%20Nice%20Subarray/README_EN.md
rating: 1749
source: Weekly Contest 309 Q3
tags:
    - Bit Manipulation
    - Array
    - Sliding Window
---

<!-- problem:start -->

# [2401. Longest Nice Subarray](https://leetcode.com/problems/longest-nice-subarray)

[中文文档](/solution/2400-2499/2401.Longest%20Nice%20Subarray/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> consisting of <strong>positive</strong> integers.</p>

<p>We call a subarray of <code>nums</code> <strong>nice</strong> if the bitwise <strong>AND</strong> of every pair of elements that are in <strong>different</strong> positions in the subarray is equal to <code>0</code>.</p>

<p>Return <em>the length of the <strong>longest</strong> nice subarray</em>.</p>

<p>A <strong>subarray</strong> is a <strong>contiguous</strong> part of an array.</p>

<p><strong>Note</strong> that subarrays of length <code>1</code> are always considered nice.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,8,48,10]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The longest nice subarray is [3,8,48]. This subarray satisfies the conditions:
- 3 AND 8 = 0.
- 3 AND 48 = 0.
- 8 AND 48 = 0.
It can be proven that no longer nice subarray can be obtained, so we return 3.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,5,11,13]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The length of the longest nice subarray is 1. Any subarray of length 1 can be chosen.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

According to the problem description, the position of the binary $1$ in each element of the subarray must be unique to ensure that the bitwise AND result of any two elements is $0$.

Therefore, we can use two pointers, $l$ and $r$, to maintain a sliding window such that the elements within the window satisfy the problem's conditions.

We use a variable $\textit{mask}$ to represent the bitwise OR result of the elements within the window. Next, we traverse each element of the array. For the current element $x$, if the bitwise AND result of $\textit{mask}$ and $x$ is not $0$, it means that the current element $x$ has overlapping binary bits with the elements in the window. At this point, we need to move the left pointer $l$ until the bitwise AND result of $\textit{mask}$ and $x$ is $0$. Then, we assign the bitwise OR result of $\textit{mask}$ and $x$ to $\textit{mask}$ and update the answer $\textit{ans} = \max(\textit{ans}, r - l + 1)$.

After the traversal, return the answer $\textit{ans}$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestNiceSubarray(self, nums: List[int]) -> int:
        ans = mask = l = 0
        for r, x in enumerate(nums):
            while mask & x:
                mask ^= nums[l]
                l += 1
            mask |= x
            ans = max(ans, r - l + 1)
        return ans
```

#### Java

```java
class Solution {
    public int longestNiceSubarray(int[] nums) {
        int ans = 0, mask = 0;
        for (int l = 0, r = 0; r < nums.length; ++r) {
            while ((mask & nums[r]) != 0) {
                mask ^= nums[l++];
            }
            mask |= nums[r];
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestNiceSubarray(vector<int>& nums) {
        int ans = 0, mask = 0;
        for (int l = 0, r = 0; r < nums.size(); ++r) {
            while (mask & nums[r]) {
                mask ^= nums[l++];
            }
            mask |= nums[r];
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func longestNiceSubarray(nums []int) (ans int) {
	mask, l := 0, 0
	for r, x := range nums {
		for mask&x != 0 {
			mask ^= nums[l]
			l++
		}
		mask |= x
		ans = max(ans, r-l+1)
	}
	return
}
```

#### TypeScript

```ts
function longestNiceSubarray(nums: number[]): number {
    let [ans, mask] = [0, 0];
    for (let l = 0, r = 0; r < nums.length; ++r) {
        while (mask & nums[r]) {
            mask ^= nums[l++];
        }
        mask |= nums[r];
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn longest_nice_subarray(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut mask = 0;
        let mut l = 0;
        for (r, &x) in nums.iter().enumerate() {
            while mask & x != 0 {
                mask ^= nums[l];
                l += 1;
            }
            mask |= x;
            ans = ans.max((r - l + 1) as i32);
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int LongestNiceSubarray(int[] nums) {
        int ans = 0, mask = 0;
        for (int l = 0, r = 0; r < nums.Length; ++r) {
            while ((mask & nums[r]) != 0) {
                mask ^= nums[l++];
            }
            mask |= nums[r];
            ans = Math.Max(ans, r - l + 1);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
