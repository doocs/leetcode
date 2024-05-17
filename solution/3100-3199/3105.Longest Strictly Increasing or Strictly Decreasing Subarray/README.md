---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3105.Longest%20Strictly%20Increasing%20or%20Strictly%20Decreasing%20Subarray/README.md
rating: 1217
source: 第 392 场周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [3105. 最长的严格递增或递减子数组](https://leetcode.cn/problems/longest-strictly-increasing-or-strictly-decreasing-subarray)

[English Version](/solution/3100-3199/3105.Longest%20Strictly%20Increasing%20or%20Strictly%20Decreasing%20Subarray/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 。</p>

<p>返回数组 <code>nums</code> 中 <strong><span data-keyword="strictly-increasing-array">严格递增</span></strong> 或 <strong><span data-keyword="strictly-decreasing-array">严格递减</span> </strong>的最长非空子数组的长度。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,4,3,3,2]</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code> 中严格递增的子数组有<code>[1]</code>、<code>[2]</code>、<code>[3]</code>、<code>[3]</code>、<code>[4]</code> 以及 <code>[1,4]</code> 。</p>

<p><code>nums</code> 中严格递减的子数组有<code>[1]</code>、<code>[2]</code>、<code>[3]</code>、<code>[3]</code>、<code>[4]</code>、<code>[3,2]</code> 以及 <code>[4,3]</code> 。</p>

<p>因此，返回 <code>2</code> 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [3,3,3,3]</span></p>

<p><strong>输出：</strong><span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code> 中严格递增的子数组有 <code>[3]</code>、<code>[3]</code>、<code>[3]</code> 以及 <code>[3]</code> 。</p>

<p><code>nums</code> 中严格递减的子数组有 <code>[3]</code>、<code>[3]</code>、<code>[3]</code> 以及 <code>[3]</code> 。</p>

<p>因此，返回 <code>1</code> 。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [3,2,1]</span></p>

<p><strong>输出：</strong><span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code> 中严格递增的子数组有 <code>[3]</code>、<code>[2]</code> 以及 <code>[1]</code> 。</p>

<p><code>nums</code> 中严格递减的子数组有 <code>[3]</code>、<code>[2]</code>、<code>[1]</code>、<code>[3,2]</code>、<code>[2,1]</code> 以及 <code>[3,2,1]</code> 。</p>

<p>因此，返回 <code>3</code> 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：两次遍历

我们先进行一次遍历，找出严格递增的最长子数组长度，更新答案。然后再进行一次遍历，找出严格递减的最长子数组长度，再次更新答案。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def longestMonotonicSubarray(self, nums: List[int]) -> int:
        ans = t = 1
        for i, x in enumerate(nums[1:]):
            if nums[i] < x:
                t += 1
                ans = max(ans, t)
            else:
                t = 1
        t = 1
        for i, x in enumerate(nums[1:]):
            if nums[i] > x:
                t += 1
                ans = max(ans, t)
            else:
                t = 1
        return ans
```

```java
class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int ans = 1;
        for (int i = 1, t = 1; i < nums.length; ++i) {
            if (nums[i - 1] < nums[i]) {
                ans = Math.max(ans, ++t);
            } else {
                t = 1;
            }
        }
        for (int i = 1, t = 1; i < nums.length; ++i) {
            if (nums[i - 1] > nums[i]) {
                ans = Math.max(ans, ++t);
            } else {
                t = 1;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int longestMonotonicSubarray(vector<int>& nums) {
        int ans = 1;
        for (int i = 1, t = 1; i < nums.size(); ++i) {
            if (nums[i - 1] < nums[i]) {
                ans = max(ans, ++t);
            } else {
                t = 1;
            }
        }
        for (int i = 1, t = 1; i < nums.size(); ++i) {
            if (nums[i - 1] > nums[i]) {
                ans = max(ans, ++t);
            } else {
                t = 1;
            }
        }
        return ans;
    }
};
```

```go
func longestMonotonicSubarray(nums []int) int {
	ans := 1
	t := 1
	for i, x := range nums[1:] {
		if nums[i] < x {
			t++
			ans = max(ans, t)
		} else {
			t = 1
		}
	}
	t = 1
	for i, x := range nums[1:] {
		if nums[i] > x {
			t++
			ans = max(ans, t)
		} else {
			t = 1
		}
	}
	return ans
}
```

```ts
function longestMonotonicSubarray(nums: number[]): number {
    let ans = 1;
    for (let i = 1, t = 1; i < nums.length; ++i) {
        if (nums[i - 1] < nums[i]) {
            ans = Math.max(ans, ++t);
        } else {
            t = 1;
        }
    }
    for (let i = 1, t = 1; i < nums.length; ++i) {
        if (nums[i - 1] > nums[i]) {
            ans = Math.max(ans, ++t);
        } else {
            t = 1;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
