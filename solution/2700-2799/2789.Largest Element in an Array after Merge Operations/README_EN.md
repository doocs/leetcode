---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2789.Largest%20Element%20in%20an%20Array%20after%20Merge%20Operations/README_EN.md
rating: 1484
source: Weekly Contest 355 Q2
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [2789. Largest Element in an Array after Merge Operations](https://leetcode.com/problems/largest-element-in-an-array-after-merge-operations)

[中文文档](/solution/2700-2799/2789.Largest%20Element%20in%20an%20Array%20after%20Merge%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> consisting of positive integers.</p>

<p>You can do the following operation on the array <strong>any</strong> number of times:</p>

<ul>
	<li>Choose an index&nbsp;<code>i</code> such that <code>0 &lt;= i &lt; nums.length - 1</code> and <code>nums[i] &lt;= nums[i + 1]</code>. Replace the element <code>nums[i + 1]</code> with <code>nums[i] + nums[i + 1]</code> and delete the element <code>nums[i]</code> from the array.</li>
</ul>

<p>Return <em>the value of the <b>largest</b> element that you can possibly obtain in the final array.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,7,9,3]
<strong>Output:</strong> 21
<strong>Explanation:</strong> We can apply the following operations on the array:
- Choose i = 0. The resulting array will be nums = [<u>5</u>,7,9,3].
- Choose i = 1. The resulting array will be nums = [5,<u>16</u>,3].
- Choose i = 0. The resulting array will be nums = [<u>21</u>,3].
The largest element in the final array is 21. It can be shown that we cannot obtain a larger element.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,3,3]
<strong>Output:</strong> 11
<strong>Explanation:</strong> We can do the following operations on the array:
- Choose i = 1. The resulting array will be nums = [5,<u>6</u>].
- Choose i = 0. The resulting array will be nums = [<u>11</u>].
There is only one element in the final array, which is 11.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Merge in Reverse Order

According to the problem description, in order to maximize the maximum element in the merged array, we should merge the elements on the right first, making the elements on the right as large as possible, so as to perform as many merge operations as possible and finally get the maximum element.

Therefore, we can traverse the array from right to left. For each position $i$, where $i \in [0, n - 2]$, if $nums[i] \leq nums[i + 1]$, we update $nums[i]$ to $nums[i] + nums[i + 1]$. Doing so is equivalent to merging $nums[i]$ and $nums[i + 1]$ and deleting $nums[i]$.

In the end, the maximum element in the array is the maximum element in the merged array.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxArrayValue(self, nums: List[int]) -> int:
        for i in range(len(nums) - 2, -1, -1):
            if nums[i] <= nums[i + 1]:
                nums[i] += nums[i + 1]
        return max(nums)
```

#### Java

```java
class Solution {
    public long maxArrayValue(int[] nums) {
        int n = nums.length;
        long ans = nums[n - 1], t = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] <= t) {
                t += nums[i];
            } else {
                t = nums[i];
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxArrayValue(vector<int>& nums) {
        int n = nums.size();
        long long ans = nums[n - 1], t = nums[n - 1];
        for (int i = n - 2; ~i; --i) {
            if (nums[i] <= t) {
                t += nums[i];
            } else {
                t = nums[i];
            }
            ans = max(ans, t);
        }
        return ans;
    }
};
```

#### Go

```go
func maxArrayValue(nums []int) int64 {
	n := len(nums)
	ans, t := nums[n-1], nums[n-1]
	for i := n - 2; i >= 0; i-- {
		if nums[i] <= t {
			t += nums[i]
		} else {
			t = nums[i]
		}
		ans = max(ans, t)
	}
	return int64(ans)
}
```

#### TypeScript

```ts
function maxArrayValue(nums: number[]): number {
    for (let i = nums.length - 2; i >= 0; --i) {
        if (nums[i] <= nums[i + 1]) {
            nums[i] += nums[i + 1];
        }
    }
    return Math.max(...nums);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
