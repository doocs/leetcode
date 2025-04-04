---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3496.Maximize%20Score%20After%20Pair%20Deletions/README.md
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [3496. 最大化配对删除后的得分 🔒](https://leetcode.cn/problems/maximize-score-after-pair-deletions)

[English Version](/solution/3400-3499/3496.Maximize%20Score%20After%20Pair%20Deletions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>nums</code>。当数组中元素超过两个时，你 <strong>必须</strong> 重复执行以下操作中的一个：</p>

<ul>
	<li>删除最前面的两个元素。</li>
	<li>删除最后面的两个元素。</li>
	<li>删除第一和最后一个元素。</li>
</ul>

<p>对于每次操作，将移除的元素之和加到你的总分上。</p>

<p>返回你可以达到的 <strong>最高</strong> 分数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2,4,1]</span></p>

<p><span class="example-io"><b>输出：</b>6</span></p>

<p><b>解释：</b></p>

<p>可能的操作有：</p>

<ul>
	<li>删除最前面的两个元素&nbsp;<code>(2 + 4) = 6</code>。剩余的数组是&nbsp;<code>[1]</code>。</li>
	<li>删除最后面的两个元素&nbsp;<code>(4 + 1) = 5</code>。剩余的数组是 <code>[2]</code>。</li>
	<li>删除第一个和最后一个元素&nbsp;<code>(2 + 1) = 3</code>。剩余的数组是 <code>[4]</code>。</li>
</ul>

<p>通过删除最前面的两个元素可以得到最高分，因此最终分数是 6。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [5,-1,4,2]</span></p>

<p><strong>输出：</strong><span class="example-io">7</span></p>

<p><strong>解释：</strong></p>

<p>可能的操作是：</p>

<ul>
	<li>删除第一个和最后一个元素&nbsp;<code>(5 + 2) = 7</code>。剩余的数组是&nbsp;<code>[-1, 4]</code>。</li>
	<li>删除最前面的两个元素 <code>(5 + -1) = 4</code>。剩余的数组是&nbsp;<code>[4, 2]</code>。</li>
	<li>删除最后面的两个元素 <code>(4 + 2) = 6</code>。剩余的数组是&nbsp;<code>[5, -1]</code>。</li>
</ul>

<p>通过删除第一个和最后一个元素可以得到最高分，因此最终分数是 7。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：逆向思维

根据题目描述，每次操作会移除掉端点的两个元素。因此，当元素个数为奇数时，最终会剩下 1 个元素；当元素个数为偶数时，最终会剩下数组中的连续两个元素。

为了使得删除后的得分最大化，我们应该使得剩下的元素最小。

因此，如果数组 $\textit{nums}$ 元素个数为奇数，那么答案就是数组 $\textit{nums}$ 所有元素的总和 $s$，减去数组 $\textit{nums}$ 中的最小值 $\textit{mi}$；如果数组 $\textit{nums}$ 元素个数为偶数，那么答案就是数组 $\textit{nums}$ 所有元素的总和 $s$，减去数组连续两个元素之和的最小值。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, nums: List[int]) -> int:
        s = sum(nums)
        if len(nums) & 1:
            return s - min(nums)
        return s - min(a + b for a, b in pairwise(nums))
```

#### Java

```java
class Solution {
    public int maxScore(int[] nums) {
        final int inf = 1 << 30;
        int n = nums.length;
        int s = 0, mi = inf;
        int t = inf;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            mi = Math.min(mi, nums[i]);
            if (i + 1 < n) {
                t = Math.min(t, nums[i] + nums[i + 1]);
            }
        }
        if (n % 2 == 1) {
            return s - mi;
        }
        return s - t;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxScore(vector<int>& nums) {
        const int inf = 1 << 30;
        int n = nums.size();
        int s = 0, mi = inf;
        int t = inf;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            mi = min(mi, nums[i]);
            if (i + 1 < n) {
                t = min(t, nums[i] + nums[i + 1]);
            }
        }
        if (n % 2 == 1) {
            return s - mi;
        }
        return s - t;
    }
};
```

#### Go

```go
func maxScore(nums []int) int {
	const inf = 1 << 30
	n := len(nums)
	s, mi, t := 0, inf, inf
	for i, x := range nums {
		s += x
		mi = min(mi, x)
		if i+1 < n {
			t = min(t, x+nums[i+1])
		}
	}
	if n%2 == 1 {
		return s - mi
	}
	return s - t
}
```

#### TypeScript

```ts
function maxScore(nums: number[]): number {
    const inf = Infinity;
    const n = nums.length;
    let [s, mi, t] = [0, inf, inf];
    for (let i = 0; i < n; ++i) {
        s += nums[i];
        mi = Math.min(mi, nums[i]);
        if (i + 1 < n) {
            t = Math.min(t, nums[i] + nums[i + 1]);
        }
    }
    return n % 2 ? s - mi : s - t;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
