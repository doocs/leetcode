---
comments: true
difficulty: 中等
rating: 2030
source: 第 369 场周赛 Q3
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [2919. 使数组变美的最小增量运算数](https://leetcode.cn/problems/minimum-increment-operations-to-make-array-beautiful)

[English Version](/solution/2900-2999/2919.Minimum%20Increment%20Operations%20to%20Make%20Array%20Beautiful/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为 <code>n</code>、下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 和一个整数 <code>k</code>。</p>

<p>你可以执行以下&nbsp;<strong>递增&nbsp;</strong>操作&nbsp;<strong>任意&nbsp;</strong>次（<strong>包括零次</strong>）：</p>

<ul>
	<li>选择一个在范围 <code>[0, n - 1]</code> 内的下标 <code>i</code>，将 <code>nums[i]</code> 增加 1。</li>
</ul>

<p>如果对于&nbsp;<strong>每个&nbsp;</strong>长度为 3 或&nbsp;<strong>更长&nbsp;</strong>的&nbsp;<strong>子数组</strong>，其&nbsp;<strong>最大&nbsp;</strong>元素都&nbsp;<strong>大于或等于</strong> <code>k</code>，则认为该数组是&nbsp;<strong>美丽数组</strong>。</p>

<p>返回使 <code>nums</code> 成为&nbsp;<strong>美丽数组&nbsp;</strong>所需的&nbsp;<strong>最少&nbsp;</strong>递增操作次数。</p>

<p>子数组是数组中一段连续且&nbsp;<strong>非空&nbsp;</strong>的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,3,0,0,2], k = 4
<strong>输出：</strong>3
<strong>解释：</strong>我们可以执行以下递增操作，使 nums 成为美丽数组：
选择下标 i = 1，将 nums[1] 增加 1 -&gt; [2,4,0,0,2]。
选择下标 i = 4，将 nums[4] 增加 1 -&gt; [2,4,0,0,3]。
选择下标 i = 4，将 nums[4] 增加 1 -&gt; [2,4,0,0,4]。
长度为 3 或更长的子数组有：[2,4,0]、[4,0,0]、[0,0,4]、[2,4,0,0]、[4,0,0,4]、[2,4,0,0,4]。
在所有这些子数组中，最大元素都等于 k = 4，因此 nums 现在是美丽数组。
可以证明，无法通过少于 3 次递增操作使 nums 成为美丽数组。
因此，答案为 3。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,3,3], k = 5
<strong>输出：</strong>2
<strong>解释：</strong>我们可以执行以下递增操作，使 nums 成为美丽数组：
选择下标 i = 2，将 nums[2] 增加 1 -&gt; [0,1,4,3]。
选择下标 i = 2，将 nums[2] 增加 1 -&gt; [0,1,5,3]。
长度为 3 或更长的子数组有：[0,1,5]、[1,5,3]、[0,1,5,3]。
在所有这些子数组中，最大元素都等于 k = 5，因此 nums 现在是美丽数组。
可以证明，无法通过少于 2 次递增操作使 nums 成为美丽数组。
因此，答案为 2。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,2], k = 1
<strong>输出：</strong>0
<strong>解释：</strong>在这个示例中，唯一一个长度为 3 或更长的子数组是 [1,1,2]。
其最大元素为 2，已经大于 k = 1，因此不需要执行任何递增操作。
所以，答案为 0。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

<!-- thinking:start -->

> **思考**
>
> 美丽数组要求每个长度为 $3$ 的窗口最大值至少为 $k$。 $n \le 10^5$，不能对每个窗口单独枚举加多少。对位置 $i$ 加 $\max(k-\textit{nums}[i],0)$ 后，它能覆盖包含它的窗口，且一个窗口只需其中一个位置达标。
>
> 因此状态只需记住「最近三个位置谁被选为覆盖者」的最小代价。滚动 $f,g,h$ 分别对应以倒数第三、二、一项为覆盖点的最优值，读入 $x$ 时用 $\min(f,g,h)+\max(k-x,0)$ 推进。最终三者取最小。

<!-- thinking:end -->

我们定义 $f$, $g$, $h$ 表示前 $i$ 项中，分别以最后三项作为子数组的最大值所需要的最小增量运算数，初始时 $f = 0$, $g = 0$, $h = 0$。

接下来，我们遍历数组 $\textit{nums}$，对于每个 $x$，我们需要更新 $f$, $g$, $h$ 的值，使其满足题目要求，即：

$$
\begin{aligned}
f' &= g \\
g' &= h \\
h' &= \min(f, g, h) + \max(k - x, 0)
\end{aligned}
$$

最后，我们只需要返回 $f$, $g$, $h$ 中的最小值即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minIncrementOperations(self, nums: list[int], k: int) -> int:
        f = g = h = 0
        for x in nums:
            f, g, h = g, h, min(f, g, h) + max(k - x, 0)
        return min(f, g, h)
```

#### Java

```java
class Solution {
    public long minIncrementOperations(int[] nums, int k) {
        long f = 0, g = 0, h = 0;
        for (int x : nums) {
            long hh = Math.min(Math.min(f, g), h) + Math.max(k - x, 0);
            f = g;
            g = h;
            h = hh;
        }
        return Math.min(Math.min(f, g), h);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minIncrementOperations(vector<int>& nums, int k) {
        long long f = 0, g = 0, h = 0;
        for (int x : nums) {
            long long hh = min({f, g, h}) + max(k - x, 0);
            f = g;
            g = h;
            h = hh;
        }
        return min({f, g, h});
    }
};
```

#### Go

```go
func minIncrementOperations(nums []int, k int) int64 {
	var f, g, h int
	for _, x := range nums {
		f, g, h = g, h, min(f, g, h)+max(k-x, 0)
	}
	return int64(min(f, g, h))
}
```

#### TypeScript

```ts
function minIncrementOperations(nums: number[], k: number): number {
    let [f, g, h] = [0, 0, 0];
    for (const x of nums) {
        [f, g, h] = [g, h, Math.min(f, g, h) + Math.max(k - x, 0)];
    }
    return Math.min(f, g, h);
}
```

#### C#

```cs
public class Solution {
    public long MinIncrementOperations(int[] nums, int k) {
        long f = 0, g = 0, h = 0;
        foreach (int x in nums) {
            long hh = Math.Min(Math.Min(f, g), h) + Math.Max(k - x, 0);
            f = g;
            g = h;
            h = hh;
        }
        return Math.Min(Math.Min(f, g), h);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
