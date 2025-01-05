---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3397.Maximum%20Number%20of%20Distinct%20Elements%20After%20Operations/README.md
rating: 1687
source: 第 429 场周赛 Q2
---

<!-- problem:start -->

# [3397. 执行操作后不同元素的最大数量](https://leetcode.cn/problems/maximum-number-of-distinct-elements-after-operations)

[English Version](/solution/3300-3399/3397.Maximum%20Number%20of%20Distinct%20Elements%20After%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code>。</p>

<p>你可以对数组中的每个元素&nbsp;<strong>最多</strong> 执行 <strong>一次&nbsp;</strong>以下操作：</p>

<ul>
	<li>将一个在范围&nbsp;<code>[-k, k]</code> 内的整数加到该元素上。</li>
</ul>

<p>返回执行这些操作后，<code>nums</code> 中可能拥有的不同元素的&nbsp;<strong>最大&nbsp;</strong>数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,2,3,3,4], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<p>对前四个元素执行操作，<code>nums</code> 变为 <code>[-1, 0, 1, 2, 3, 4]</code>，可以获得 6 个不同的元素。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,4,4,4], k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>对 <code>nums[0]</code> 加 -1，以及对 <code>nums[1]</code> 加 1，<code>nums</code> 变为 <code>[3, 5, 4, 4]</code>，可以获得 3 个不同的元素。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 排序

我们不妨对数组 $\textit{nums}$ 排序，然后从左到右考虑每个元素 $x$。

对于第一个元素，我们可以贪心地将其变为 $x - k$，这样可以使得 $x$ 尽可能小，给后续的元素留下更多的空间。我们用变量 $\textit{pre}$ 当前使用到的元素的最大值，初始化为负无穷大。

对于后续的元素 $x$，我们可以贪心地将其变为 $\min(x + k, \max(x - k, \textit{pre} + 1))$。这里的 $\max(x - k, \textit{pre} + 1)$ 表示我们尽可能地将 $x$ 变得更小，但不能小于 $\textit{pre} + 1$，如果存在该值，且小于 $x + k$，我们就可以将 $x$ 变为该值，不重复元素数加一，然后我们更新 $\textit{pre}$ 为该值。

遍历结束，我们就得到了不重复元素的最大数量。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDistinctElements(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans = 0
        pre = -inf
        for x in nums:
            cur = min(x + k, max(x - k, pre + 1))
            if cur > pre:
                ans += 1
                pre = cur
        return ans
```

#### Java

```java
class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0, pre = Integer.MIN_VALUE;
        for (int x : nums) {
            int cur = Math.min(x + k, Math.max(x - k, pre + 1));
            if (cur > pre) {
                ++ans;
                pre = cur;
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
    int maxDistinctElements(vector<int>& nums, int k) {
        ranges::sort(nums);
        int ans = 0, pre = INT_MIN;
        for (int x : nums) {
            int cur = min(x + k, max(x - k, pre + 1));
            if (cur > pre) {
                ++ans;
                pre = cur;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxDistinctElements(nums []int, k int) (ans int) {
	sort.Ints(nums)
	pre := math.MinInt32
	for _, x := range nums {
		cur := min(x+k, max(x-k, pre+1))
		if cur > pre {
			ans++
			pre = cur
		}
	}
	return
}
```

#### TypeScript

```ts
function maxDistinctElements(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let [ans, pre] = [0, -Infinity];
    for (const x of nums) {
        const cur = Math.min(x + k, Math.max(x - k, pre + 1));
        if (cur > pre) {
            ++ans;
            pre = cur;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
