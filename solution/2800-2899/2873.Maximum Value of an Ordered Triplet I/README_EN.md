---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2873.Maximum%20Value%20of%20an%20Ordered%20Triplet%20I/README_EN.md
rating: 1270
source: Weekly Contest 365 Q1
tags:
    - Array
---

<!-- problem:start -->

# [2873. Maximum Value of an Ordered Triplet I](https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-i)

[中文文档](/solution/2800-2899/2873.Maximum%20Value%20of%20an%20Ordered%20Triplet%20I/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>.</p>

<p>Return <em><strong>the maximum value over all triplets of indices</strong></em> <code>(i, j, k)</code> <em>such that</em> <code>i &lt; j &lt; k</code>. If all such triplets have a negative value, return <code>0</code>.</p>

<p>The <strong>value of a triplet of indices</strong> <code>(i, j, k)</code> is equal to <code>(nums[i] - nums[j]) * nums[k]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [12,6,1,2,7]
<strong>Output:</strong> 77
<strong>Explanation:</strong> The value of the triplet (0, 2, 4) is (nums[0] - nums[2]) * nums[4] = 77.
It can be shown that there are no ordered triplets of indices with a value greater than 77. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,10,3,4,19]
<strong>Output:</strong> 133
<strong>Explanation:</strong> The value of the triplet (1, 2, 4) is (nums[1] - nums[2]) * nums[4] = 133.
It can be shown that there are no ordered triplets of indices with a value greater than 133.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The only ordered triplet of indices (0, 1, 2) has a negative value of (nums[0] - nums[1]) * nums[2] = -3. Hence, the answer would be 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Maintain Maximum Prefix Value and Maximum Difference

We can use two variables $mx$ and $mx\_diff$ to maintain the maximum prefix value and maximum difference, respectively. When traversing the array, we update these two variables, and the answer is the maximum value of all $mx\_diff \times nums[i]$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def maximumTripletValue(self, nums: List[int]) -> int:
        ans = mx = mx_diff = 0
        for num in nums:
            ans = max(ans, mx_diff * num)
            mx = max(mx, num)
            mx_diff = max(mx_diff, mx - num)
        return ans
```

```java
class Solution {
    public long maximumTripletValue(int[] nums) {
        long max, maxDiff, ans;
        max = 0;
        maxDiff = 0;
        ans = 0;
        for (int num : nums) {
            ans = Math.max(ans, num * maxDiff);
            max = Math.max(max, num);
            maxDiff = Math.max(maxDiff, max - num);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long maximumTripletValue(vector<int>& nums) {
        long long ans = 0;
        int mx = 0, mx_diff = 0;
        for (int num : nums) {
            ans = max(ans, 1LL * mx_diff * num);
            mx = max(mx, num);
            mx_diff = max(mx_diff, mx - num);
        }
        return ans;
    }
};
```

```go
func maximumTripletValue(nums []int) int64 {
	ans, mx, mx_diff := 0, 0, 0
	for _, num := range nums {
		ans = max(ans, mx_diff*num)
		mx = max(mx, num)
		mx_diff = max(mx_diff, mx-num)
	}
	return int64(ans)
}
```

```ts
function maximumTripletValue(nums: number[]): number {
    let [ans, mx, mx_diff] = [0, 0, 0];
    for (const num of nums) {
        ans = Math.max(ans, mx_diff * num);
        mx = Math.max(mx, num);
        mx_diff = Math.max(mx_diff, mx - num);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
