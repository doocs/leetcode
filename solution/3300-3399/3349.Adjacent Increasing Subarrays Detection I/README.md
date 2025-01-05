---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3349.Adjacent%20Increasing%20Subarrays%20Detection%20I/README.md
rating: 1298
source: 第 423 场周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [3349. 检测相邻递增子数组 I](https://leetcode.cn/problems/adjacent-increasing-subarrays-detection-i)

[English Version](/solution/3300-3399/3349.Adjacent%20Increasing%20Subarrays%20Detection%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由 <code>n</code> 个整数组成的数组 <code>nums</code> 和一个整数 <code>k</code>，请你确定是否存在 <strong>两个</strong> <strong>相邻</strong> 且长度为 <code>k</code> 的 <strong>严格递增</strong> 子数组。具体来说，需要检查是否存在从下标 <code>a</code> 和 <code>b</code> (<code>a &lt; b</code>) 开始的 <strong>两个</strong> 子数组，并满足下述全部条件：</p>

<ul>
	<li>这两个子数组 <code>nums[a..a + k - 1]</code> 和 <code>nums[b..b + k - 1]</code> 都是 <strong>严格递增</strong> 的。</li>
	<li>这两个子数组必须是 <strong>相邻的</strong>，即 <code>b = a + k</code>。</li>
</ul>

<p>如果可以找到这样的 <strong>两个</strong> 子数组，请返回 <code>true</code>；否则返回 <code>false</code>。</p>

<p><strong>子数组</strong> 是数组中的一个连续<b> 非空</b> 的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [2,5,7,8,9,2,3,4,3,1], k = 3</span></p>

<p><strong>输出：</strong><span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>从下标 <code>2</code> 开始的子数组为 <code>[7, 8, 9]</code>，它是严格递增的。</li>
	<li>从下标 <code>5</code> 开始的子数组为 <code>[2, 3, 4]</code>，它也是严格递增的。</li>
	<li>两个子数组是相邻的，因此结果为 <code>true</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,2,3,4,4,4,4,5,6,7], k = 5</span></p>

<p><strong>输出：</strong><span class="example-io">false</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= 2 * k &lt;= nums.length</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
