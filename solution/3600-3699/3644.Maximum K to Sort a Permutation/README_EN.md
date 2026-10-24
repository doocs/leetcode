---
comments: true
difficulty: Medium
rating: 1775
source: Weekly Contest 462 Q2
tags:
    - Bit Manipulation
    - Array
---

<!-- problem:start -->

# [3644. Maximum K to Sort a Permutation](https://leetcode.com/problems/maximum-k-to-sort-a-permutation)

[中文文档](/solution/3600-3699/3644.Maximum%20K%20to%20Sort%20a%20Permutation/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>, where <code>nums</code> is a <strong><span data-keyword="permutation-array">permutation</span></strong> of the numbers in the range <code>[0..n - 1]</code>.</p>

<p>You may swap elements at indices <code>i</code> and <code>j</code> <strong>only if</strong> <code>nums[i] AND nums[j] == k</code>, where <code>AND</code> denotes the bitwise AND operation and <code>k</code> is a <strong>non-negative</strong> integer.</p>

<p>Return the <strong>maximum</strong> value of <code>k</code> such that the array can be sorted in <strong>non-decreasing</strong> order using any number of such swaps. If <code>nums</code> is already sorted, return 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,3,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Choose <code>k = 1</code>. Swapping <code>nums[1] = 3</code> and <code>nums[3] = 1</code> is allowed since <code>nums[1] AND nums[3] == 1</code>, resulting in a sorted permutation: <code>[0, 1, 2, 3]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,1,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Choose <code>k = 2</code>. Swapping <code>nums[2] = 3</code> and <code>nums[3] = 2</code> is allowed since <code>nums[2] AND nums[3] == 2</code>, resulting in a sorted permutation: <code>[0, 1, 2, 3]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,2,1,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>Only <code>k = 0</code> allows sorting since no greater <code>k</code> allows the required swaps where <code>nums[i] AND nums[j] == k</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= n - 1</code></li>
	<li><code>nums</code> is a permutation of integers from <code>0</code> to <code>n - 1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

<!-- thinking:start -->

> **Thinking**
>
> The direct approach tries $k$ from large to small and asks whether the misplaced values can be sorted using only swaps whose bitwise AND equals $k$. With $n \le 10^5$, any pair of values may be swapped, so building that graph is quadratic and enumeration does not fit.
>
> What must move is each value that is not stored at its own index. Such a value takes part in at least one swap, so every bit set in $k$ has to be set in that value as well. A feasible $k$ therefore cannot exceed the bitwise AND of all misplaced values.
>
> Call that AND $k$ again. The number $k$ itself belongs to the permutation of $[0,n)$, and its AND with every misplaced value is exactly $k$, so it can be used as a hub to arrange those values in any order. An array that is already sorted needs no swap, and the answer is $0$.
>
> The identity element of AND is all-ones, so $\textit{ans}$ starts at $-1$ and only combines entries with $\textit{nums}[i] \ne i$. When the permutation is sorted, $\textit{ans}$ stays $-1$, and $\max(\textit{ans}, 0)$ turns that sentinel into $0$.

<!-- thinking:end -->

We maintain $\textit{ans}$ as the bitwise AND of every element that is not in its home position, starting from $-1$.

Scan each index $i$. If $\textit{nums}[i] \ne i$, fold $\textit{nums}[i]$ into $\textit{ans}$ with a bitwise AND.

When the scan finishes, $\textit{ans}$ is still $-1$ if the array is already sorted, in which case the result is $0$. Otherwise $\textit{ans}$ is the largest feasible $k$. Return $\max(\textit{ans}, 0)$.

The time complexity is $O(n)$, and the space complexity is $O(1)$, where $n$ is the length of $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortPermutation(self, nums: List[int]) -> int:
        ans = -1
        for i, x in enumerate(nums):
            if i != x:
                ans &= x
        return max(ans, 0)
```

#### Java

```java
class Solution {
    public int sortPermutation(int[] nums) {
        int ans = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (i != nums[i]) {
                ans &= nums[i];
            }
        }
        return Math.max(ans, 0);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sortPermutation(vector<int>& nums) {
        int ans = -1;
        for (int i = 0; i < nums.size(); ++i) {
            if (i != nums[i]) {
                ans &= nums[i];
            }
        }
        return max(ans, 0);
    }
};
```

#### Go

```go
func sortPermutation(nums []int) int {
	ans := -1
	for i, x := range nums {
		if i != x {
			ans &= x
		}
	}
	return max(ans, 0)
}
```

#### TypeScript

```ts
function sortPermutation(nums: number[]): number {
    let ans = -1;
    for (let i = 0; i < nums.length; ++i) {
        if (i != nums[i]) {
            ans &= nums[i];
        }
    }
    return Math.max(ans, 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn sort_permutation(nums: Vec<i32>) -> i32 {
        let mut ans = -1;
        for (i, &x) in nums.iter().enumerate() {
            if i as i32 != x {
                ans &= x;
            }
        }
        ans.max(0)
    }
}
```

#### C#

```cs
public class Solution {
    public int SortPermutation(int[] nums) {
        int ans = -1;
        for (int i = 0; i < nums.Length; i++) {
            if (i != nums[i]) {
                ans &= nums[i];
            }
        }
        return Math.Max(ans, 0);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
