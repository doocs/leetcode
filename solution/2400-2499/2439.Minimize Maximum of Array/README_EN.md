---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2439.Minimize%20Maximum%20of%20Array/README_EN.md
rating: 1965
source: Biweekly Contest 89 Q3
tags:
    - Greedy
    - Array
    - Binary Search
    - Dynamic Programming
    - Prefix Sum
---

# [2439. Minimize Maximum of Array](https://leetcode.com/problems/minimize-maximum-of-array)

[中文文档](/solution/2400-2499/2439.Minimize%20Maximum%20of%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> comprising of <code>n</code> non-negative integers.</p>

<p>In one operation, you must:</p>

<ul>
	<li>Choose an integer <code>i</code> such that <code>1 &lt;= i &lt; n</code> and <code>nums[i] &gt; 0</code>.</li>
	<li>Decrease <code>nums[i]</code> by 1.</li>
	<li>Increase <code>nums[i - 1]</code> by 1.</li>
</ul>

<p>Return<em> the <strong>minimum</strong> possible value of the <strong>maximum</strong> integer of </em><code>nums</code><em> after performing <strong>any</strong> number of operations</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,7,1,6]
<strong>Output:</strong> 5
<strong>Explanation:</strong>
One set of optimal operations is as follows:
1. Choose i = 1, and nums becomes [4,6,1,6].
2. Choose i = 3, and nums becomes [4,6,2,5].
3. Choose i = 1, and nums becomes [5,5,2,5].
The maximum integer of nums is 5. It can be shown that the maximum number cannot be less than 5.
Therefore, we return 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,1]
<strong>Output:</strong> 10
<strong>Explanation:</strong>
It is optimal to leave nums as is, and since 10 is the maximum value, we return 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Binary Search

To minimize the maximum value of the array, it is intuitive to use binary search. We binary search for the maximum value $mx$ of the array, and find the smallest $mx$ that satisfies the problem requirements.

The time complexity is $O(n \times \log M)$, where $n$ is the length of the array, and $M$ is the maximum value in the array.

<!-- tabs:start -->

```python
class Solution:
    def minimizeArrayValue(self, nums: List[int]) -> int:
        def check(mx):
            d = 0
            for x in nums[:0:-1]:
                d = max(0, d + x - mx)
            return nums[0] + d <= mx

        left, right = 0, max(nums)
        while left < right:
            mid = (left + right) >> 1
            if check(mid):
                right = mid
            else:
                left = mid + 1
        return left
```

```java
class Solution {
    private int[] nums;

    public int minimizeArrayValue(int[] nums) {
        this.nums = nums;
        int left = 0, right = max(nums);
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int mx) {
        long d = 0;
        for (int i = nums.length - 1; i > 0; --i) {
            d = Math.max(0, d + nums[i] - mx);
        }
        return nums[0] + d <= mx;
    }

    private int max(int[] nums) {
        int v = nums[0];
        for (int x : nums) {
            v = Math.max(v, x);
        }
        return v;
    }
}
```

```cpp
class Solution {
public:
    int minimizeArrayValue(vector<int>& nums) {
        int left = 0, right = *max_element(nums.begin(), nums.end());
        auto check = [&](int mx) {
            long d = 0;
            for (int i = nums.size() - 1; i; --i) {
                d = max(0l, d + nums[i] - mx);
            }
            return nums[0] + d <= mx;
        };
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(mid))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
};
```

```go
func minimizeArrayValue(nums []int) int {
	check := func(mx int) bool {
		d := 0
		for i := len(nums) - 1; i > 0; i-- {
			d = max(0, nums[i]+d-mx)
		}
		return nums[0]+d <= mx
	}

	left, right := 0, slices.Max(nums)
	for left < right {
		mid := (left + right) >> 1
		if check(mid) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}
```

<!-- tabs:end -->

<!-- end -->
