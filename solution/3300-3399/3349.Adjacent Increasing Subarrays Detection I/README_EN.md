---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3349.Adjacent%20Increasing%20Subarrays%20Detection%20I/README_EN.md
tags:
    - Array
---

<!-- problem:start -->

# [3349. Adjacent Increasing Subarrays Detection I](https://leetcode.com/problems/adjacent-increasing-subarrays-detection-i)

[中文文档](/solution/3300-3399/3349.Adjacent%20Increasing%20Subarrays%20Detection%20I/README.md)

## Description

<!-- description:start -->

<p>Given an array <code>nums</code> of <code>n</code> integers and an integer <code>k</code>, determine whether there exist <strong>two</strong> <strong>adjacent</strong> <span data-keyword="subarray-nonempty">subarrays</span> of length <code>k</code> such that both subarrays are <strong>strictly</strong> <strong>increasing</strong>. Specifically, check if there are <strong>two</strong> subarrays starting at indices <code>a</code> and <code>b</code> (<code>a &lt; b</code>), where:</p>

<ul>
	<li>Both subarrays <code>nums[a..a + k - 1]</code> and <code>nums[b..b + k - 1]</code> are <strong>strictly increasing</strong>.</li>
	<li>The subarrays must be <strong>adjacent</strong>, meaning <code>b = a + k</code>.</li>
</ul>

<p>Return <code>true</code> if it is <em>possible</em> to find <strong>two </strong>such subarrays, and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,5,7,8,9,2,3,4,3,1], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The subarray starting at index <code>2</code> is <code>[7, 8, 9]</code>, which is strictly increasing.</li>
	<li>The subarray starting at index <code>5</code> is <code>[2, 3, 4]</code>, which is also strictly increasing.</li>
	<li>These two subarrays are adjacent, so the result is <code>true</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,4,4,4,5,6,7], k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt; 2 * k &lt;= nums.length</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hasIncreasingSubarrays(self, nums: List[int], k: int) -> bool:
        mx = pre = cur = 0
        for i, x in enumerate(nums):
            cur += 1
            if i == len(nums) - 1 or x >= nums[i + 1]:
                mx = max(mx, cur // 2, min(pre, cur))
                pre, cur = cur, 0
        return mx >= k
```

#### Java

```java
class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int mx = 0, pre = 0, cur = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            ++cur;
            if (i == n - 1 || nums.get(i) >= nums.get(i + 1)) {
                mx = Math.max(mx, Math.max(cur / 2, Math.min(pre, cur)));
                pre = cur;
                cur = 0;
            }
        }
        return mx >= k;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool hasIncreasingSubarrays(vector<int>& nums, int k) {
        int mx = 0, pre = 0, cur = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            ++cur;
            if (i == n - 1 || nums[i] >= nums[i + 1]) {
                mx = max({mx, cur / 2, min(pre, cur)});
                pre = cur;
                cur = 0;
            }
        }
        return mx >= k;
    }
};
```

#### Go

```go
func hasIncreasingSubarrays(nums []int, k int) bool {
	mx, pre, cur := 0, 0, 0
	for i, x := range nums {
		cur++
		if i == len(nums)-1 || x >= nums[i+1] {
			mx = max(mx, max(cur/2, min(pre, cur)))
			pre, cur = cur, 0
		}
	}
	return mx >= k
}
```

#### TypeScript

```ts
function hasIncreasingSubarrays(nums: number[], k: number): boolean {
    let [mx, pre, cur] = [0, 0, 0];
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        ++cur;
        if (i === n - 1 || nums[i] >= nums[i + 1]) {
            mx = Math.max(mx, (cur / 2) | 0, Math.min(pre, cur));
            [pre, cur] = [cur, 0];
        }
    }
    return mx >= k;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
