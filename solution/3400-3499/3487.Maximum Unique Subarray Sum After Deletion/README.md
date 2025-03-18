---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3487.Maximum%20Unique%20Subarray%20Sum%20After%20Deletion/README.md
tags:
    - 贪心
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [3487. 删除后的最大子数组元素和](https://leetcode.cn/problems/maximum-unique-subarray-sum-after-deletion)

[English Version](/solution/3400-3499/3487.Maximum%20Unique%20Subarray%20Sum%20After%20Deletion/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>你可以从数组 <code>nums</code> 中删除任意数量的元素，但不能将其变为 <strong>空</strong> 数组。执行删除操作后，选出&nbsp;<code>nums</code>&nbsp;中满足下述条件的一个子数组：</p>

<ol>
	<li>子数组中的所有元素 <strong>互不相同</strong> 。</li>
	<li><strong>最大化</strong> 子数组的元素和。</li>
</ol>

<p>返回子数组的 <strong>最大元素和</strong> 。</p>
<strong>子数组</strong> 是数组的一个连续、<strong>非空</strong> 的元素序列。

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3,4,5]</span></p>

<p><span class="example-io"><b>输出：</b>15</span></p>

<p><b>解释：</b></p>

<p>不删除任何元素，选中整个数组得到最大元素和。</p>
</div>

<p><b>示例 2：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b></span><span class="example-io">nums = [1,1,0,1,1]</span></p>

<p><span class="example-io"><b>输出：</b></span>1</p>

<p><b>解释：</b></p>

<p>删除元素&nbsp;<code>nums[0] == 1</code>、<code>nums[1] == 1</code>、<code>nums[2] == 0</code>&nbsp;和&nbsp;<code>nums[3] == 1</code>&nbsp;。选中整个数组&nbsp;<code>[1]</code>&nbsp;得到最大元素和。</p>
</div>

<p><b>示例 3：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b></span><span class="example-io">nums = [1,2,-1,-2,1,0,-1]</span></p>

<p><span class="example-io"><b>输出：</b></span>3</p>

<p><b>解释：</b></p>

<p>删除元素&nbsp;<code>nums[2] == -1</code>&nbsp;和&nbsp;<code>nums[3] == -2</code>&nbsp;，从&nbsp;<code>[1, 2, 1, 0, -1]</code>&nbsp;中选中子数组&nbsp;<code>[2, 1]</code>&nbsp;以获得最大元素和。</p>
</div>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 哈希表

我们先找出数组中的最大值 $\textit{mx}$，如果 $\textit{mx} \leq 0$，那么数组中所有元素都小于等于 $0$，由于需要选出一个元素和最大的非空子数组，那么最大元素和就是 $\textit{mx}$。

如果 $\textit{mx} > 0$，那么我们需要找出数组中所有不同的正整数，并且这些正整数的和最大。我们可以使用一个哈希表 $\textit{s}$ 来记录所有不同的正整数，然后遍历数组，将所有不同的正整数加起来即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSum(self, nums: List[int]) -> int:
        mx = max(nums)
        if mx <= 0:
            return mx
        ans = 0
        s = set()
        for x in nums:
            if x < 0 or x in s:
                continue
            ans += x
            s.add(x)
        return ans
```

#### Java

```java
class Solution {
    public int maxSum(int[] nums) {
        int mx = Arrays.stream(nums).max().getAsInt();
        if (mx <= 0) {
            return mx;
        }
        boolean[] s = new boolean[201];
        int ans = 0;
        for (int x : nums) {
            if (x < 0 || s[x]) {
                continue;
            }
            ans += x;
            s[x] = true;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxSum(vector<int>& nums) {
        int mx = ranges::max(nums);
        if (mx <= 0) {
            return mx;
        }
        unordered_set<int> s;
        int ans = 0;
        for (int x : nums) {
            if (x < 0 || s.contains(x)) {
                continue;
            }
            ans += x;
            s.insert(x);
        }
        return ans;
    }
};
```

#### Go

```go
func maxSum(nums []int) (ans int) {
	mx := slices.Max(nums)
	if mx <= 0 {
		return mx
	}
	s := make(map[int]bool)
	for _, x := range nums {
		if x < 0 || s[x] {
			continue
		}
		ans += x
		s[x] = true
	}
	return
}
```

#### TypeScript

```ts
function maxSum(nums: number[]): number {
    const mx = Math.max(...nums);
    if (mx <= 0) {
        return mx;
    }
    const s = new Set<number>();
    let ans: number = 0;
    for (const x of nums) {
        if (x < 0 || s.has(x)) {
            continue;
        }
        ans += x;
        s.add(x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
