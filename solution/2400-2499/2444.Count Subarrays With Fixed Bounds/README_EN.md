---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2444.Count%20Subarrays%20With%20Fixed%20Bounds/README_EN.md
rating: 2092
source: Weekly Contest 315 Q4
tags:
    - Queue
    - Array
    - Sliding Window
    - Monotonic Queue
---

<!-- problem:start -->

# [2444. Count Subarrays With Fixed Bounds](https://leetcode.com/problems/count-subarrays-with-fixed-bounds)

[中文文档](/solution/2400-2499/2444.Count%20Subarrays%20With%20Fixed%20Bounds/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and two integers <code>minK</code> and <code>maxK</code>.</p>

<p>A <strong>fixed-bound subarray</strong> of <code>nums</code> is a subarray that satisfies the following conditions:</p>

<ul>
	<li>The <strong>minimum</strong> value in the subarray is equal to <code>minK</code>.</li>
	<li>The <strong>maximum</strong> value in the subarray is equal to <code>maxK</code>.</li>
</ul>

<p>Return <em>the <strong>number</strong> of fixed-bound subarrays</em>.</p>

<p>A <strong>subarray</strong> is a <strong>contiguous</strong> part of an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,2,7,5], minK = 1, maxK = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> The fixed-bound subarrays are [1,3,5] and [1,3,5,2].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1], minK = 1, maxK = 1
<strong>Output:</strong> 10
<strong>Explanation:</strong> Every subarray of nums is a fixed-bound subarray. There are 10 possible subarrays.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], minK, maxK &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumerate the Right Endpoint

According to the problem description, we know that all elements of a bounded subarray are within the range $[\textit{minK}, \textit{maxK}]$, and the minimum value must be $\textit{minK}$, while the maximum value must be $\textit{maxK}$.

We iterate through the array $\textit{nums}$ and count the number of bounded subarrays with $\textit{nums}[i]$ as the right endpoint. Then, we sum up all the counts.

The specific implementation logic is as follows:

1. Maintain the index $k$ of the most recent element that is not within the range $[\textit{minK}, \textit{maxK}]$, initialized to $-1$. The left endpoint of the current element $\textit{nums}[i]$ must be greater than $k$.
2. Maintain the most recent index $j_1$ where the value is $\textit{minK}$ and the most recent index $j_2$ where the value is $\textit{maxK}$, both initialized to $-1$. The left endpoint of the current element $\textit{nums}[i]$ must be less than or equal to $\min(j_1, j_2)$.
3. Based on the above, the number of bounded subarrays with the current element as the right endpoint is $\max\bigl(0,\ \min(j_1, j_2) - k\bigr)$. Accumulate all these counts to get the result.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSubarrays(self, nums: List[int], minK: int, maxK: int) -> int:
        j1 = j2 = k = -1
        ans = 0
        for i, v in enumerate(nums):
            if v < minK or v > maxK:
                k = i
            if v == minK:
                j1 = i
            if v == maxK:
                j2 = i
            ans += max(0, min(j1, j2) - k)
        return ans
```

#### Java

```java
class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long ans = 0;
        int j1 = -1, j2 = -1, k = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < minK || nums[i] > maxK) {
                k = i;
            }
            if (nums[i] == minK) {
                j1 = i;
            }
            if (nums[i] == maxK) {
                j2 = i;
            }
            ans += Math.max(0, Math.min(j1, j2) - k);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countSubarrays(vector<int>& nums, int minK, int maxK) {
        long long ans = 0;
        int j1 = -1, j2 = -1, k = -1;
        for (int i = 0; i < static_cast<int>(nums.size()); ++i) {
            if (nums[i] < minK || nums[i] > maxK) {
                k = i;
            }
            if (nums[i] == minK) {
                j1 = i;
            }
            if (nums[i] == maxK) {
                j2 = i;
            }
            ans += max(0, min(j1, j2) - k);
        }
        return ans;
    }
};
```

#### Go

```go
func countSubarrays(nums []int, minK int, maxK int) int64 {
	ans := 0
	j1, j2, k := -1, -1, -1
	for i, v := range nums {
		if v < minK || v > maxK {
			k = i
		}
		if v == minK {
			j1 = i
		}
		if v == maxK {
			j2 = i
		}
		ans += max(0, min(j1, j2)-k)
	}
	return int64(ans)
}
```

#### TypeScript

```ts
function countSubarrays(nums: number[], minK: number, maxK: number): number {
    let ans = 0;
    let [j1, j2, k] = [-1, -1, -1];
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] < minK || nums[i] > maxK) k = i;
        if (nums[i] === minK) j1 = i;
        if (nums[i] === maxK) j2 = i;
        ans += Math.max(0, Math.min(j1, j2) - k);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_subarrays(nums: Vec<i32>, min_k: i32, max_k: i32) -> i64 {
        let mut ans: i64 = 0;
        let mut j1: i64 = -1;
        let mut j2: i64 = -1;
        let mut k: i64 = -1;
        for (i, &v) in nums.iter().enumerate() {
            let i = i as i64;
            if v < min_k || v > max_k {
                k = i;
            }
            if v == min_k {
                j1 = i;
            }
            if v == max_k {
                j2 = i;
            }
            let m = j1.min(j2);
            if m > k {
                ans += m - k;
            }
        }
        ans
    }
}
```

#### C

```c
long long countSubarrays(int* nums, int numsSize, int minK, int maxK) {
    long long ans = 0;
    int j1 = -1, j2 = -1, k = -1;
    for (int i = 0; i < numsSize; ++i) {
        if (nums[i] < minK || nums[i] > maxK) k = i;
        if (nums[i] == minK) j1 = i;
        if (nums[i] == maxK) j2 = i;
        int m = j1 < j2 ? j1 : j2;
        if (m > k) ans += (long long) (m - k);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
