---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2765.Longest%20Alternating%20Subarray/README.md
rating: 1580
source: 第 108 场双周赛 Q1
tags:
    - 数组
    - 枚举
---

<!-- problem:start -->

# [2765. 最长交替子数组](https://leetcode.cn/problems/longest-alternating-subarray)

[English Version](/solution/2700-2799/2765.Longest%20Alternating%20Subarray/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。如果 <code>nums</code>&nbsp;中长度为&nbsp;<code>m</code>&nbsp;的子数组&nbsp;<code>s</code>&nbsp;满足以下条件，我们称它是一个 <strong>交替子数组</strong> ：</p>

<ul>
	<li><code>m</code>&nbsp;大于&nbsp;<code>1</code>&nbsp;。</li>
	<li><code>s<sub>1</sub> = s<sub>0</sub> + 1</code>&nbsp;。</li>
	<li>下标从 <strong>0</strong> 开始的子数组&nbsp;<code>s</code>&nbsp;与数组&nbsp;<code>[s<sub>0</sub>, s<sub>1</sub>, s<sub>0</sub>, s<sub>1</sub>,...,s<sub>(m-1) % 2</sub>]</code>&nbsp;一样。也就是说，<code>s<sub>1</sub> - s<sub>0</sub> = 1</code>&nbsp;，<code>s<sub>2</sub> - s<sub>1</sub> = -1</code>&nbsp;，<code>s<sub>3</sub> - s<sub>2</sub> = 1</code>&nbsp;，<code>s<sub>4</sub> - s<sub>3</sub> = -1</code>&nbsp;，以此类推，直到&nbsp;<code>s[m - 1] - s[m - 2] = (-1)<sup>m</sup></code>&nbsp;。</li>
</ul>

<p>请你返回 <code>nums</code>&nbsp;中所有 <strong>交替</strong>&nbsp;子数组中，最长的长度，如果不存在交替子数组，请你返回 <code>-1</code>&nbsp;。</p>

<p>子数组是一个数组中一段连续 <strong>非空</strong>&nbsp;的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block"><b>输入：</b>nums = [2,3,4,3,4]</div>

<div class="example-block"><b>输出：</b>4</div>

<div class="example-block"><b>解释：</b>交替子数组有 <code>[2,3]</code>，<code>[3,4]</code>，<code>[3,4,3]</code> 和 <code>[3,4,3,4]</code>。最长的子数组为 <code>[3,4,3,4]</code>，长度为 4。</div>

<p>&nbsp;</p>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block"><b>输入：</b>nums = [4,5,6]</div>

<div class="example-block"><b>输出：</b>2</div>

<div class="example-block"><strong>解释：</strong><code>[4,5]</code> 和 <code>[5,6]</code> 是仅有的两个交替子数组。它们长度都为 2 。</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以枚举子数组的左端点 $i$，对于每个 $i$，我们需要找到最长的满足条件的子数组。我们可以从 $i$ 开始向右遍历，每次遇到相邻元素差值不满足交替条件时，我们就找到了一个满足条件的子数组。我们可以用一个变量 $k$ 来记录当前元素的差值应该是 $1$ 还是 $-1$，如果当前元素的差值应该是 $-k$，那么我们就将 $k$ 取反。当我们找到一个满足条件的子数组 $nums[i..j]$ 时，我们更新答案为 $\max(ans, j - i + 1)$。

时间复杂度 $O(n^2)$，其中 $n$ 是数组的长度。我们需要枚举子数组的左端点 $i$，对于每个 $i$，我们需要 $O(n)$ 的时间来找到最长的满足条件的子数组。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def alternatingSubarray(self, nums: List[int]) -> int:
        ans, n = -1, len(nums)
        for i in range(n):
            k = 1
            j = i
            while j + 1 < n and nums[j + 1] - nums[j] == k:
                j += 1
                k *= -1
            if j - i + 1 > 1:
                ans = max(ans, j - i + 1)
        return ans
```

#### Java

```java
class Solution {
    public int alternatingSubarray(int[] nums) {
        int ans = -1, n = nums.length;
        for (int i = 0; i < n; ++i) {
            int k = 1;
            int j = i;
            for (; j + 1 < n && nums[j + 1] - nums[j] == k; ++j) {
                k *= -1;
            }
            if (j - i + 1 > 1) {
                ans = Math.max(ans, j - i + 1);
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
    int alternatingSubarray(vector<int>& nums) {
        int ans = -1, n = nums.size();
        for (int i = 0; i < n; ++i) {
            int k = 1;
            int j = i;
            for (; j + 1 < n && nums[j + 1] - nums[j] == k; ++j) {
                k *= -1;
            }
            if (j - i + 1 > 1) {
                ans = max(ans, j - i + 1);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func alternatingSubarray(nums []int) int {
	ans, n := -1, len(nums)
	for i := range nums {
		k := 1
		j := i
		for ; j+1 < n && nums[j+1]-nums[j] == k; j++ {
			k *= -1
		}
		if t := j - i + 1; t > 1 && ans < t {
			ans = t
		}
	}
	return ans
}
```

#### TypeScript

```ts
function alternatingSubarray(nums: number[]): number {
    let ans = -1;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        let k = 1;
        let j = i;
        for (; j + 1 < n && nums[j + 1] - nums[j] === k; ++j) {
            k *= -1;
        }
        if (j - i + 1 > 1) {
            ans = Math.max(ans, j - i + 1);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
