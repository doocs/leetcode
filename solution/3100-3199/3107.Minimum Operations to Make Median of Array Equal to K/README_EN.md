---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3107.Minimum%20Operations%20to%20Make%20Median%20of%20Array%20Equal%20to%20K/README_EN.md
rating: 1604
source: Weekly Contest 392 Q3
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [3107. Minimum Operations to Make Median of Array Equal to K](https://leetcode.com/problems/minimum-operations-to-make-median-of-array-equal-to-k)

[中文文档](/solution/3100-3199/3107.Minimum%20Operations%20to%20Make%20Median%20of%20Array%20Equal%20to%20K/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and a <strong>non-negative</strong> integer <code>k</code>. In one operation, you can increase or decrease any element by 1.</p>

<p>Return the <strong>minimum</strong> number of operations needed to make the <strong>median</strong> of <code>nums</code> <em>equal</em> to <code>k</code>.</p>

<p>The median of an array is defined as the middle element of the array when it is sorted in non-decreasing order. If there are two choices for a median, the larger of the two values is taken.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,5,6,8,5], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>We can subtract one from <code>nums[1]</code> and <code>nums[4]</code> to obtain <code>[2, 4, 6, 8, 4]</code>. The median of the resulting array is equal to <code>k</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,5,6,8,5], k = 7</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>We can add one to <code>nums[1]</code> twice and add one to <code>nums[2]</code> once to obtain <code>[2, 7, 7, 8, 5]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5,6], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The median of the array is already equal to <code>k</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Sorting

First, we sort the array $nums$ and find the position $m$ of the median. The initial number of operations we need is $|nums[m] - k|$.

Next, we discuss in two cases:

- If $nums[m] > k$, then all elements to the right of $m$ are greater than or equal to $k$. We only need to reduce the elements greater than $k$ on the left of $m$ to $k$.
- If $nums[m] \le k$, then all elements to the left of $m$ are less than or equal to $k$. We only need to increase the elements less than $k$ on the right of $m$ to $k$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperationsToMakeMedianK(self, nums: List[int], k: int) -> int:
        nums.sort()
        n = len(nums)
        m = n >> 1
        ans = abs(nums[m] - k)
        if nums[m] > k:
            for i in range(m - 1, -1, -1):
                if nums[i] <= k:
                    break
                ans += nums[i] - k
        else:
            for i in range(m + 1, n):
                if nums[i] >= k:
                    break
                ans += k - nums[i]
        return ans
```

#### Java

```java
class Solution {
    public long minOperationsToMakeMedianK(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int m = n >> 1;
        long ans = Math.abs(nums[m] - k);
        if (nums[m] > k) {
            for (int i = m - 1; i >= 0 && nums[i] > k; --i) {
                ans += nums[i] - k;
            }
        } else {
            for (int i = m + 1; i < n && nums[i] < k; ++i) {
                ans += k - nums[i];
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
    long long minOperationsToMakeMedianK(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int m = n >> 1;
        long long ans = abs(nums[m] - k);
        if (nums[m] > k) {
            for (int i = m - 1; i >= 0 && nums[i] > k; --i) {
                ans += nums[i] - k;
            }
        } else {
            for (int i = m + 1; i < n && nums[i] < k; ++i) {
                ans += k - nums[i];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minOperationsToMakeMedianK(nums []int, k int) (ans int64) {
	sort.Ints(nums)
	n := len(nums)
	m := n >> 1
	ans = int64(abs(nums[m] - k))
	if nums[m] > k {
		for i := m - 1; i >= 0 && nums[i] > k; i-- {
			ans += int64(nums[i] - k)
		}
	} else {
		for i := m + 1; i < n && nums[i] < k; i++ {
			ans += int64(k - nums[i])
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minOperationsToMakeMedianK(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const m = n >> 1;
    let ans = Math.abs(nums[m] - k);
    if (nums[m] > k) {
        for (let i = m - 1; i >= 0 && nums[i] > k; --i) {
            ans += nums[i] - k;
        }
    } else {
        for (let i = m + 1; i < n && nums[i] < k; ++i) {
            ans += k - nums[i];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
