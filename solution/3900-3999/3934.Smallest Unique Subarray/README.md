---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3934.Smallest%20Unique%20Subarray/README.md
rating: 2162
source: 第 502 场周赛 Q4
---

<!-- problem:start -->

# [3934. 最短唯一子数组](https://leetcode.cn/problems/smallest-unique-subarray)

[English Version](/solution/3900-3999/3934.Smallest%20Unique%20Subarray/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 。</p>

<p>找出 <code>nums</code> 中与其他任何 <strong>子数组</strong> 均 <strong>不</strong> <strong>相同</strong> 的 <strong>子数组</strong> 的 <strong>最小 </strong>长度。</p>

<p>返回一个整数，表示此类 <strong>子数组</strong> 的 <strong>最小可能长度</strong> 。</p>

<p><strong>子数组</strong> 是数组中的一个连续的非空元素序列。</p>

<p>如果两个 <strong>子数组</strong> 具有相同的长度，并且对应位置的元素也相同，则认为这两个 <strong>子数组</strong> 是相同的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,3,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>长度为 1 的子数组：<code>[3]</code> → 出现 3 次</li>
	<li>长度为 2 的子数组：<code>[3, 3]</code> → 出现 2 次</li>
	<li>长度为 3 的子数组：<code>[3, 3, 3]</code> → 出现 1 次</li>
</ul>

<p>子数组 <code>[3, 3, 3]</code> 是唯一的，因此最小唯一子数组的长度为 3。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,1,2,3,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>长度为 1 的子数组：</p>

<ul>
	<li><code>[2]</code> → 出现 2 次</li>
	<li><code>[1]</code> → 出现 1 次</li>
	<li><code>[3]</code> → 出现 2 次</li>
</ul>
子数组 <code>[1]</code> 是唯一的，因此最小唯一子数组的长度为 1。</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,1,2,2,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>长度为 1 的子数组：</p>

<ul>
	<li><code>[1]</code> → 出现 3 次</li>
	<li><code>[2]</code> → 出现 2 次</li>
</ul>

<p>长度为 2 的子数组：</p>

<ul>
	<li><code>[1, 1]</code> → 出现 1 次</li>
	<li><code>[1, 2]</code> → 出现 1 次</li>
	<li><code>[2, 2]</code> → 出现 1 次</li>
	<li><code>[2, 1]</code> → 出现 1 次</li>
</ul>
至少有一个长度为 2 的子数组是唯一的，因此最小唯一子数组的长度为 2。</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滚动哈希 + 二分查找

在 $\textit{mid_len} = \frac{\textit{min_len} + \textit{max_len}}{2}$ 下，对于每个候选子数组长度 $\textit{mid_len}$，

用滚动哈希遍历所有长度为 $\textit{mid_len}$ 的子数组，记录每个哈希值出现的次数。

若存在某个哈希值恰好出现一次，则说明该长度存在唯一子数组，能因此尝试把 $\textit{max_len}$ 缩小到 $\textit{mid_len} - 1$；

否则说明该长度不存在唯一子数组，必须让 $\textit{min_len}$ 上升到 $\textit{mid_len} + 1$。

这是因为一旦长度为 $l$ 的子数组做到了唯一性，长度 $> l$ 的子数组也必然有唯一性。

如此操作的时间复杂度是 $O(n \log n)$，空间复杂度是 $O(n)$，其中 $n$ 为数组长度。

原因是做了 $O(\log n)$ 次的二分查找，每次滚动哈希需要 $O(n)$ 的时间。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestUniqueSubarray(self, nums: list[int]) -> int:
        self.nums = nums

        self.base = 19
        self.modulo = 10**9 + 7
        self.powers = [1] * (len(nums) + 1)

        self.hash_values: dict[int, int] = dict()

        min_possible_len = len(nums)  # Base case.

        min_len, max_len = 1, len(nums)  # For binary search usage.

        while min_len <= max_len:
            mid_len = (min_len + max_len) // 2

            if self._check_uniqueness(mid_len):
                if mid_len < min_possible_len:
                    min_possible_len = mid_len
                max_len = mid_len - 1

            else:
                min_len = mid_len + 1

        return min_possible_len

    def _check_uniqueness(self, subarray_len: int) -> bool:
        # Only need to reset leftmost power to 1 before usage.
        self.powers[0] = 1  # Because powers are calculated by bottom-up.

        for idx in range(1, len(self.nums) + 1):
            self.powers[idx] = (self.powers[idx - 1] * self.base) % self.modulo

        current_hash = 0
        for idx in range(subarray_len):
            current_hash *= self.base
            current_hash += self.nums[idx]
            current_hash %= self.modulo

        self.hash_values.clear()  # Clear before usage.
        self.hash_values[current_hash] = 1

        for idx in range(1, len(self.nums) - subarray_len + 1):
            # Window shifts: deduct leftmost num's share from hash value.
            current_hash -= self.powers[subarray_len - 1] * self.nums[idx - 1]

            # Integrate newly added num's share into hash value.
            current_hash *= self.base
            current_hash += self.nums[idx + subarray_len - 1]
            current_hash %= self.modulo

            if current_hash not in self.hash_values.keys():
                self.hash_values.update({current_hash: 0})
            self.hash_values[current_hash] += 1

        return 1 in self.hash_values.values()
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
