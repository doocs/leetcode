---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3350.Adjacent%20Increasing%20Subarrays%20Detection%20II/README.md
tags:
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [3350. 检测相邻递增子数组 II](https://leetcode.cn/problems/adjacent-increasing-subarrays-detection-ii)

[English Version](/solution/3300-3399/3350.Adjacent%20Increasing%20Subarrays%20Detection%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由 <code>n</code> 个整数组成的数组 <code>nums</code> ，请你找出 <code>k</code> 的 <strong>最大值</strong>，使得存在 <strong>两个</strong> <strong>相邻</strong> 且长度为 <code>k</code> 的 <strong>严格递增</strong> <span data-keyword="subarray-nonempty">子数组</span>。具体来说，需要检查是否存在从下标 <code>a</code> 和 <code>b</code> (<code>a &lt; b</code>) 开始的 <strong>两个</strong> 子数组，并满足下述全部条件：</p>

<ul>
	<li>这两个子数组 <code>nums[a..a + k - 1]</code> 和 <code>nums[b..b + k - 1]</code> 都是 <strong>严格递增</strong> 的。</li>
	<li>这两个子数组必须是 <strong>相邻的</strong>，即 <code>b = a + k</code>。</li>
</ul>

<p>返回 <code>k</code> 的 <strong>最大可能 </strong>值。</p>

<p><strong>子数组</strong> 是数组中的一个连续<b> 非空</b> 的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [2,5,7,8,9,2,3,4,3,1]</span></p>

<p><strong>输出：</strong><span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>从下标 2 开始的子数组是 <code>[7, 8, 9]</code>，它是严格递增的。</li>
	<li>从下标 5 开始的子数组是 <code>[2, 3, 4]</code>，它也是严格递增的。</li>
	<li>这两个子数组是相邻的，因此 3 是满足题目条件的 <strong>最大</strong> <code>k</code> 值。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,2,3,4,4,4,4,5,6,7]</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>从下标 0 开始的子数组是 <code>[1, 2]</code>，它是严格递增的。</li>
	<li>从下标 2 开始的子数组是 <code>[3, 4]</code>，它也是严格递增的。</li>
	<li>这两个子数组是相邻的，因此 2 是满足题目条件的 <strong>最大</strong> <code>k</code> 值。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxIncreasingSubarrays(self, nums: List[int]) -> int:
        ans = pre = cur = 0
        for i, x in enumerate(nums):
            cur += 1
            if i == len(nums) - 1 or x >= nums[i + 1]:
                ans = max(ans, cur // 2, min(pre, cur))
                pre, cur = cur, 0
        return ans
```

#### Java

```java
class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int ans = 0, pre = 0, cur = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            ++cur;
            if (i == n - 1 || nums.get(i) >= nums.get(i + 1)) {
                ans = Math.max(ans, Math.max(cur / 2, Math.min(pre, cur)));
                pre = cur;
                cur = 0;
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
    int maxIncreasingSubarrays(vector<int>& nums) {
        int ans = 0, pre = 0, cur = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            ++cur;
            if (i == n - 1 || nums[i] >= nums[i + 1]) {
                ans = max({ans, cur / 2, min(pre, cur)});
                pre = cur;
                cur = 0;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxIncreasingSubarrays(nums []int) (ans int) {
	pre, cur := 0, 0
	for i, x := range nums {
		cur++
		if i == len(nums)-1 || x >= nums[i+1] {
			ans = max(ans, max(cur/2, min(pre, cur)))
			pre, cur = cur, 0
		}
	}
	return
}
```

#### TypeScript

```ts
function maxIncreasingSubarrays(nums: number[]): number {
    let [ans, pre, cur] = [0, 0, 0];
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        ++cur;
        if (i === n - 1 || nums[i] >= nums[i + 1]) {
            ans = Math.max(ans, (cur / 2) | 0, Math.min(pre, cur));
            [pre, cur] = [cur, 0];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
