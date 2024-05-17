---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2972.Count%20the%20Number%20of%20Incremovable%20Subarrays%20II/README_EN.md
rating: 2152
source: Biweekly Contest 120 Q3
tags:
    - Array
    - Two Pointers
    - Binary Search
---

<!-- problem:start -->

# [2972. Count the Number of Incremovable Subarrays II](https://leetcode.com/problems/count-the-number-of-incremovable-subarrays-ii)

[中文文档](/solution/2900-2999/2972.Count%20the%20Number%20of%20Incremovable%20Subarrays%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array of <strong>positive</strong> integers <code>nums</code>.</p>

<p>A subarray of <code>nums</code> is called <strong>incremovable</strong> if <code>nums</code> becomes <strong>strictly increasing</strong> on removing the subarray. For example, the subarray <code>[3, 4]</code> is an incremovable subarray of <code>[5, 3, 4, 6, 7]</code> because removing this subarray changes the array <code>[5, 3, 4, 6, 7]</code> to <code>[5, 6, 7]</code> which is strictly increasing.</p>

<p>Return <em>the total number of <strong>incremovable</strong> subarrays of</em> <code>nums</code>.</p>

<p><strong>Note</strong> that an empty array is considered strictly increasing.</p>

<p>A <strong>subarray</strong> is a contiguous non-empty sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> 10
<strong>Explanation:</strong> The 10 incremovable subarrays are: [1], [2], [3], [4], [1,2], [2,3], [3,4], [1,2,3], [2,3,4], and [1,2,3,4], because on removing any one of these subarrays nums becomes strictly increasing. Note that you cannot select an empty subarray.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,5,7,8]
<strong>Output:</strong> 7
<strong>Explanation:</strong> The 7 incremovable subarrays are: [5], [6], [5,7], [6,5], [5,7,8], [6,5,7] and [6,5,7,8].
It can be shown that there are only 7 incremovable subarrays in nums.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [8,7,6,6]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The 3 incremovable subarrays are: [8,7,6], [7,6,6], and [8,7,6,6]. Note that [8,7] is not an incremovable subarray because after removing [8,7] nums becomes [6,6], which is sorted in ascending order but not strictly increasing.
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

According to the problem description, after removing a subarray, the remaining elements are strictly increasing. Therefore, there are several situations:

1. The remaining elements only include the prefix of the array $nums$ (which can be empty);
2. The remaining elements only include the suffix of the array $nums$;
3. The remaining elements include both the prefix and the suffix of the array $nums$.

The second and third situations can be combined into one, that is, the remaining elements include the suffix of the array $nums$. So there are two situations in total:

1. The remaining elements only include the prefix of the array $nums$ (which can be empty);
2. The remaining elements include the suffix of the array $nums$.

First, consider the first situation, that is, the remaining elements only include the prefix of the array $nums$. We can use a pointer $i$ to point to the last element of the longest increasing prefix of the array $nums$, that is, $nums[0] \lt nums[1] \lt \cdots \lt nums[i]$, then the number of remaining elements is $n - i - 1$, where $n$ is the length of the array $nums$. Therefore, for this situation, to make the remaining elements strictly increasing, we can choose to remove the following subarrays:

1. $nums[i+1,...,n-1]$;
2. $nums[i,...,n-1]$;
3. $nums[i-1,...,n-1]$;
4. $nums[i-2,...,n-1]$;
5. $\cdots$;
6. $nums[0,...,n-1]$.

There are $i + 2$ situations in total, so for this situation, the number of removed increasing subarrays is $i + 2$.

Next, consider the second situation, that is, the remaining elements include the suffix of the array $nums$. We can use a pointer $j$ to point to the first element of the increasing suffix of the array $nums$. We enumerate $j$ as the first element of the increasing suffix in the range $[n - 1,...,1]$. Each time, we need to move the pointer $i$ to make $nums[i] \lt nums[j]$, then the number of removed increasing subarrays increases by $i + 2$. When $nums[j - 1] \ge nums[j]$, we stop enumerating because the suffix is not strictly increasing at this time.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def incremovableSubarrayCount(self, nums: List[int]) -> int:
        i, n = 0, len(nums)
        while i + 1 < n and nums[i] < nums[i + 1]:
            i += 1
        if i == n - 1:
            return n * (n + 1) // 2
        ans = i + 2
        j = n - 1
        while j:
            while i >= 0 and nums[i] >= nums[j]:
                i -= 1
            ans += i + 2
            if nums[j - 1] >= nums[j]:
                break
            j -= 1
        return ans
```

#### Java

```java
class Solution {
    public long incremovableSubarrayCount(int[] nums) {
        int i = 0, n = nums.length;
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            ++i;
        }
        if (i == n - 1) {
            return n * (n + 1L) / 2;
        }
        long ans = i + 2;
        for (int j = n - 1; j > 0; --j) {
            while (i >= 0 && nums[i] >= nums[j]) {
                --i;
            }
            ans += i + 2;
            if (nums[j - 1] >= nums[j]) {
                break;
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
    long long incremovableSubarrayCount(vector<int>& nums) {
        int i = 0, n = nums.size();
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            ++i;
        }
        if (i == n - 1) {
            return n * (n + 1LL) / 2;
        }
        long long ans = i + 2;
        for (int j = n - 1; j > 0; --j) {
            while (i >= 0 && nums[i] >= nums[j]) {
                --i;
            }
            ans += i + 2;
            if (nums[j - 1] >= nums[j]) {
                break;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func incremovableSubarrayCount(nums []int) int64 {
	i, n := 0, len(nums)
	for i+1 < n && nums[i] < nums[i+1] {
		i++
	}
	if i == n-1 {
		return int64(n * (n + 1) / 2)
	}
	ans := int64(i + 2)
	for j := n - 1; j > 0; j-- {
		for i >= 0 && nums[i] >= nums[j] {
			i--
		}
		ans += int64(i + 2)
		if nums[j-1] >= nums[j] {
			break
		}
	}
	return ans
}
```

#### TypeScript

```ts
function incremovableSubarrayCount(nums: number[]): number {
    const n = nums.length;
    let i = 0;
    while (i + 1 < n && nums[i] < nums[i + 1]) {
        i++;
    }
    if (i === n - 1) {
        return (n * (n + 1)) / 2;
    }
    let ans = i + 2;
    for (let j = n - 1; j; --j) {
        while (i >= 0 && nums[i] >= nums[j]) {
            --i;
        }
        ans += i + 2;
        if (nums[j - 1] >= nums[j]) {
            break;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
