---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3364.Minimum%20Positive%20Sum%20Subarray/README.md
tags:
    - 数组
    - 前缀和
    - 滑动窗口
---

<!-- problem:start -->

# [3364. 最小正和子数组](https://leetcode.cn/problems/minimum-positive-sum-subarray)

[English Version](/solution/3300-3399/3364.Minimum%20Positive%20Sum%20Subarray/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和 <strong>两个</strong> 整数 <code>l</code> 和 <code>r</code>。你的任务是找到一个长度在 <code>l</code> 和 <code>r</code> 之间（包含）且和大于 0 的 <strong>子数组</strong> 的 <strong>最小</strong> 和。</p>

<p>返回满足条件的子数组的 <strong>最小</strong> 和。如果不存在这样的子数组，则返回 -1。</p>

<p><strong>子数组</strong> 是数组中的一个连续 <b>非空</b> 元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3, -2, 1, 4], l = 2, r = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>长度在 <code>l = 2</code> 和 <code>r = 3</code> 之间且和大于 0 的子数组有：</p>

<ul>
	<li><code>[3, -2]</code> 和为 1</li>
	<li><code>[1, 4]</code> 和为 5</li>
	<li><code>[3, -2, 1]</code> 和为 2</li>
	<li><code>[-2, 1, 4]</code> 和为 3</li>
</ul>

<p>其中，子数组 <code>[3, -2]</code> 的和为 1，是所有正和中最小的。因此，答案为 1。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [-2, 2, -3, 1], l = 2, r = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>不存在长度在 <code>l</code> 和 <code>r</code> 之间且和大于 0 的子数组。因此，答案为 -1。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1, 2, 3, 4], l = 2, r = 4</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>子数组 <code>[1, 2]</code> 的长度为 2，和为&nbsp;3，是所有正和中最小的。因此，答案为 3。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= l &lt;= r &lt;= nums.length</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以枚举子数组的左端点 $i$，然后在 $[i, n)$ 的区间内从左往右枚举右端点 $j$，计算区间 $[i, j]$ 的和 $s$，如果 $s$ 大于 0 且区间长度在 $[l, r]$ 之间，我们就更新答案。

最后，如果答案仍然是初始值，说明没有找到符合条件的子数组，返回 $-1$，否则返回答案。

时间复杂度 $O(n^2)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumSumSubarray(self, nums: List[int], l: int, r: int) -> int:
        n = len(nums)
        ans = inf
        for i in range(n):
            s = 0
            for j in range(i, n):
                s += nums[j]
                if l <= j - i + 1 <= r and s > 0:
                    ans = min(ans, s)
        return -1 if ans == inf else ans
```

#### Java

```java
class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        final int inf = Integer.MAX_VALUE;
        int ans = inf;
        for (int i = 0; i < n; ++i) {
            int s = 0;
            for (int j = i; j < n; ++j) {
                s += nums.get(j);
                int k = j - i + 1;
                if (k >= l && k <= r && s > 0) {
                    ans = Math.min(ans, s);
                }
            }
        }
        return ans == inf ? -1 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumSumSubarray(vector<int>& nums, int l, int r) {
        int n = nums.size();
        const int inf = INT_MAX;
        int ans = inf;
        for (int i = 0; i < n; ++i) {
            int s = 0;
            for (int j = i; j < n; ++j) {
                s += nums[j];
                int k = j - i + 1;
                if (k >= l && k <= r && s > 0) {
                    ans = min(ans, s);
                }
            }
        }
        return ans == inf ? -1 : ans;
    }
};
```

#### Go

```go
func minimumSumSubarray(nums []int, l int, r int) int {
	const inf int = 1 << 30
	ans := inf
	for i := range nums {
		s := 0
		for j := i; j < len(nums); j++ {
			s += nums[j]
			k := j - i + 1
			if k >= l && k <= r && s > 0 {
				ans = min(ans, s)
			}
		}
	}
	if ans == inf {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function minimumSumSubarray(nums: number[], l: number, r: number): number {
    const n = nums.length;
    let ans = Infinity;
    for (let i = 0; i < n; ++i) {
        let s = 0;
        for (let j = i; j < n; ++j) {
            s += nums[j];
            const k = j - i + 1;
            if (k >= l && k <= r && s > 0) {
                ans = Math.min(ans, s);
            }
        }
    }
    return ans == Infinity ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
