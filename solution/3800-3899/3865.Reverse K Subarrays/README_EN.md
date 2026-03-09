---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3865.Reverse%20K%20Subarrays/README_EN.md
---

<!-- problem:start -->

# [3865. Reverse K Subarrays 🔒](https://leetcode.com/problems/reverse-k-subarrays)

[中文文档](/solution/3800-3899/3865.Reverse%20K%20Subarrays/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and an integer <code>k</code>.</p>

<p>You must <strong>partition</strong> the array into <code>k</code> contiguous subarrays of <strong>equal</strong> length and <strong>reverse</strong> each subarray.</p>

<p>It is guaranteed that <code>n</code> is divisible by <code>k</code>.</p>

<p>Return the resulting array after performing the above operation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,4,3,5,6], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,1,3,4,6,5]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The array is partitioned into <code>k = 3</code> subarrays: <code>[1, 2]</code>, <code>[4, 3]</code>, and <code>[5, 6]</code>.</li>
	<li>After reversing each subarray: <code>[2, 1]</code>, <code>[3, 4]</code>, and <code>[6, 5]</code>.</li>
	<li>Combining them gives the final array <code>[2, 1, 3, 4, 6, 5]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,4,4,2], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,4,4,5]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The array is partitioned into <code>k = 1</code> subarray: <code>[5, 4, 4, 2]</code>.</li>
	<li>Reversing it produces <code>[2, 4, 4, 5]</code>, which is the final array.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li><code>n</code> is divisible by <code>k</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

Since we need to partition the array into $k$ subarrays of equal length, the length of each subarray is $m = \frac{n}{k}$. We can use a loop to traverse the array with a step size of $m$, and in each iteration, reverse the current subarray.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$, as we only use a constant amount of extra space.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseSubarrays(self, nums: list[int], k: int) -> list[int]:
        n = len(nums)
        m = n // k
        for i in range(0, n, m):
            nums[i : i + m] = nums[i : i + m][::-1]
        return nums
```

#### Java

```java
class Solution {
    public int[] reverseSubarrays(int[] nums, int k) {
        int n = nums.length;
        int m = n / k;
        for (int i = 0; i < n; i += m) {
            int l = i, r = i + m - 1;
            while (l < r) {
                int t = nums[l];
                nums[l++] = nums[r];
                nums[r--] = t;
            }
        }
        return nums;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> reverseSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        int m = n / k;
        for (int i = 0; i < n; i += m) {
            int l = i, r = i + m - 1;
            while (l < r) {
                swap(nums[l++], nums[r--]);
            }
        }
        return nums;
    }
};
```

#### Go

```go
func reverseSubarrays(nums []int, k int) []int {
	n := len(nums)
	m := n / k
	for i := 0; i < n; i += m {
		l, r := i, i+m-1
		for l < r {
			nums[l], nums[r] = nums[r], nums[l]
			l++
			r--
		}
	}
	return nums
}
```

#### TypeScript

```ts
function reverseSubarrays(nums: number[], k: number): number[] {
    const n = nums.length;
    const m = Math.floor(n / k);
    for (let i = 0; i < n; i += m) {
        let l = i,
            r = i + m - 1;
        while (l < r) {
            const t = nums[l];
            nums[l++] = nums[r];
            nums[r--] = t;
        }
    }
    return nums;
}
```

#### Rust

```rust
impl Solution {
    pub fn reverse_subarrays(mut nums: Vec<i32>, k: i32) -> Vec<i32> {
        let n = nums.len();
        let m = n / k as usize;

        for i in (0..n).step_by(m) {
            let mut l = i;
            let mut r = i + m - 1;
            while l < r {
                nums.swap(l, r);
                l += 1;
                r -= 1;
            }
        }

        nums
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
