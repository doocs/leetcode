---
comments: true
difficulty: 简单
rating: 1232
source: 第 193 场双周赛 Q1
---

<!-- problem:start -->

# [4074. 和为目标值的最大乘积对](https://leetcode.cn/problems/maximum-product-pair-with-target-sum)

[English Version](/solution/4000-4099/4074.Maximum%20Product%20Pair%20With%20Target%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>target</code>。</p>

<p>当一个由&nbsp;<strong>不同&nbsp;</strong>下标组成的二元组 <code>(i, j)</code>&nbsp;满足以下条件，称它是&nbsp;<strong>有效的&nbsp;</strong>：</p>

<ul>
	<li><code>nums[i] + nums[j] == target</code></li>
	<li><code>nums[i] &gt; nums[j]</code></li>
</ul>

<p>返回一个&nbsp;<strong>有效的&nbsp;</strong>二元组 <code>[i, j]</code>，使其&nbsp;<strong>乘积</strong> <code>nums[i] * nums[j]</code> 在所有有效二元组中是&nbsp;<strong>最大&nbsp;</strong>的。如果不存在有效的二元组，返回 <code>[-1, -1]</code>。</p>

<p>如果有多个有效二元组都能达到&nbsp;<strong>最大&nbsp;</strong>乘积，你可以返回其中的&nbsp;<strong>任意一个&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3,4], target = 5</span></p>

<p><strong>输出：</strong> <span class="example-io">[2,1]</span></p>

<p><strong>解释：</strong></p>

<p>存在 2 个有效的二元组：</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th scope="col" style="text-align:center;">序号</th>
			<th scope="col" style="text-align:center;"><code>(i, j)</code></th>
			<th scope="col" style="text-align:center;"><code>nums[i]</code></th>
			<th scope="col" style="text-align:center;"><code>nums[j]</code></th>
			<th scope="col" style="text-align:center;">和</th>
			<th scope="col" style="text-align:center;">积</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">(3, 0)</td>
			<td style="text-align:center;">4</td>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">5</td>
			<td style="text-align:center;">4</td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">(2, 1)</td>
			<td style="text-align:center;">3</td>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">5</td>
			<td style="text-align:center;">6</td>
		</tr>
	</tbody>
</table>

<p>两个二元组的和均为 5 且满足 <code>nums[i] &gt; nums[j]</code>。第二个二元组的乘积更大，<code>3 * 2 = 6</code>，因此答案是 <code>[2, 1]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [-3,-1,4,2], target = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">[3,1]</span></p>

<p><strong>解释：</strong></p>

<p>存在 2 个有效的二元组：</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th scope="col" style="text-align:center;">序号</th>
			<th scope="col" style="text-align:center;"><code>(i, j)</code></th>
			<th scope="col" style="text-align:center;"><code>nums[i]</code></th>
			<th scope="col" style="text-align:center;"><code>nums[j]</code></th>
			<th scope="col" style="text-align:center;">和</th>
			<th scope="col" style="text-align:center;">积</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">(2, 0)</td>
			<td style="text-align:center;">4</td>
			<td style="text-align:center;">-3</td>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">-12</td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">(3, 1)</td>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">-1</td>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">-2</td>
		</tr>
	</tbody>
</table>

<p>由于 <code>-2 &gt; -12</code>，因此选择下标 <code>(3, 1)</code> 对应的二元组，答案是 <code>[3, 1]</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,3,5], target = 6</span></p>

<p><strong>输出：</strong> <span class="example-io">[-1,-1]</span></p>

<p><strong>解释：</strong></p>

<p>不存在有效的二元组，因为 <code>nums[0]</code> 和 <code>nums[1]</code> 的和虽然为 6，但它们是相等的。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i], target &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

<!-- thinking:start -->

> **思考**
>
> $n$ 不超过 $100$，把每一对下标都检查一遍也来得及。需要单独处理的是负数：和已经固定以后，两数更靠近 $\textit{target}/2$ 并不使乘积变大，每一对的乘积都要直接比较。
>
> $x$ 的补数始终是 $y=\textit{target}-x$。有效对还要求两个数不相等，因此 $x=y$ 的情况可以直接跳过。
>
> 乘积只由这两个数值决定。从左到右扫描时，用哈希表记下每个值最近一次出现的下标；见到 $x$ 就查询 $y$，并保留乘积更大的那一对。

<!-- thinking:end -->

用哈希表 $\textit{pos}$ 记录每个数值最近一次出现的下标。从左到右扫描 $\textit{nums}[i]=x$，令 $y=\textit{target}-x$。当 $x\neq y$、 $y$ 已经出现，且 $x\cdot y$ 大于当前最大乘积时更新答案，并把较大的数放在前面，使 $\textit{nums}[i]>\textit{nums}[j]$。扫描结束后如果仍没有合法对，答案保持 $[-1,-1]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组长度。

<!-- tabs:start -->

#### Python3

```python
from math import inf


class Solution:
    def maxProductPair(self, nums: list[int], target: int) -> list[int]:
        pos = {}
        mx = -inf
        ans = [-1, -1]
        for i, x in enumerate(nums):
            y = target - x
            if x != y and x * y > mx and y in pos:
                j = pos[y]
                mx = x * y
                ans = [i, j] if x > y else [j, i]
            pos[x] = i
        return ans
```

#### Java

```java
class Solution {
    public int[] maxProductPair(int[] nums, int target) {
        Map<Integer, Integer> pos = new HashMap<>();
        int mx = Integer.MIN_VALUE;
        int[] ans = {-1, -1};
        for (int i = 0; i < nums.length; ++i) {
            int x = nums[i], y = target - x;
            if (x != y && x * y > mx && pos.containsKey(y)) {
                int j = pos.get(y);
                mx = x * y;
                ans = x > y ? new int[] {i, j} : new int[] {j, i};
            }
            pos.put(x, i);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> maxProductPair(vector<int>& nums, int target) {
        unordered_map<int, int> pos;
        int mx = INT_MIN;
        vector<int> ans = {-1, -1};
        for (int i = 0; i < nums.size(); ++i) {
            int x = nums[i], y = target - x;
            if (x != y && x * y > mx && pos.contains(y)) {
                int j = pos[y];
                mx = x * y;
                ans = x > y ? vector<int>{i, j} : vector<int>{j, i};
            }
            pos[x] = i;
        }
        return ans;
    }
};
```

#### Go

```go
func maxProductPair(nums []int, target int) []int {
	pos := map[int]int{}
	mx := -int(^uint(0)>>1) - 1
	ans := []int{-1, -1}
	for i, x := range nums {
		y := target - x
		if j, ok := pos[y]; x != y && ok && x*y > mx {
			mx = x * y
			if x > y {
				ans = []int{i, j}
			} else {
				ans = []int{j, i}
			}
		}
		pos[x] = i
	}
	return ans
}
```

#### TypeScript

```ts
function maxProductPair(nums: number[], target: number): number[] {
    const pos = new Map<number, number>();
    let mx = -Infinity;
    let ans = [-1, -1];
    for (let i = 0; i < nums.length; ++i) {
        const x = nums[i],
            y = target - x;
        if (x !== y && x * y > mx && pos.has(y)) {
            const j = pos.get(y)!;
            mx = x * y;
            ans = x > y ? [i, j] : [j, i];
        }
        pos.set(x, i);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
