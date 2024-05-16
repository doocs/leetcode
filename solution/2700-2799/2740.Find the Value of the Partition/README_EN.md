---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2740.Find%20the%20Value%20of%20the%20Partition/README_EN.md
rating: 1301
source: Weekly Contest 350 Q2
tags:
    - Array
    - Sorting
---

<!-- problem:start -->

# [2740. Find the Value of the Partition](https://leetcode.com/problems/find-the-value-of-the-partition)

[中文文档](/solution/2700-2799/2740.Find%20the%20Value%20of%20the%20Partition/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>positive</strong> integer array <code>nums</code>.</p>

<p>Partition <code>nums</code> into two arrays,&nbsp;<code>nums1</code> and <code>nums2</code>, such that:</p>

<ul>
	<li>Each element of the array <code>nums</code> belongs to either the array <code>nums1</code> or the array <code>nums2</code>.</li>
	<li>Both arrays are <strong>non-empty</strong>.</li>
	<li>The value of the partition is <strong>minimized</strong>.</li>
</ul>

<p>The value of the partition is <code>|max(nums1) - min(nums2)|</code>.</p>

<p>Here, <code>max(nums1)</code> denotes the maximum element of the array <code>nums1</code>, and <code>min(nums2)</code> denotes the minimum element of the array <code>nums2</code>.</p>

<p>Return <em>the integer denoting the value of such partition</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,2,4]
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can partition the array nums into nums1 = [1,2] and nums2 = [3,4].
- The maximum element of the array nums1 is equal to 2.
- The minimum element of the array nums2 is equal to 3.
The value of the partition is |2 - 3| = 1. 
It can be proven that 1 is the minimum value out of all partitions.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [100,1,10]
<strong>Output:</strong> 9
<strong>Explanation:</strong> We can partition the array nums into nums1 = [10] and nums2 = [100,1].
- The maximum element of the array nums1 is equal to 10.
- The minimum element of the array nums2 is equal to 1.
The value of the partition is |10 - 1| = 9.
It can be proven that 9 is the minimum value out of all partitions.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

The problem requires us to minimize the partition value. Therefore, we can sort the array and then take the minimum difference between two adjacent numbers.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def findValueOfPartition(self, nums: List[int]) -> int:
        nums.sort()
        return min(b - a for a, b in pairwise(nums))
```

```java
class Solution {
    public int findValueOfPartition(int[] nums) {
        Arrays.sort(nums);
        int ans = 1 << 30;
        for (int i = 1; i < nums.length; ++i) {
            ans = Math.min(ans, nums[i] - nums[i - 1]);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findValueOfPartition(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 1 << 30;
        for (int i = 1; i < nums.size(); ++i) {
            ans = min(ans, nums[i] - nums[i - 1]);
        }
        return ans;
    }
};
```

```go
func findValueOfPartition(nums []int) int {
	sort.Ints(nums)
	ans := 1 << 30
	for i, x := range nums[1:] {
		ans = min(ans, x-nums[i])
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
