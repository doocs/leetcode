---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4121. 使数组中所有元素相等的最小成本](https://leetcode.cn/problems/minimum-cost-to-make-all-array-elements-equal)

[English Version](/solution/4100-4199/4121.Minimum%20Cost%20to%20Make%20All%20Array%20Elements%20Equal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>。</p>

<p>你可以从以下操作中 <strong>任意</strong> 选择，执行 <strong>任意</strong> 次，顺序 <strong>任意</strong>。</p>

<ul>
	<li><strong>前缀操作</strong>：选择一个满足 <code>0 &lt; i &lt; n - 1</code> 的下标 <code>i</code>，将下标从 0 到 <code>i - 1</code> 的 <strong>所有</strong> 元素设为 <code>nums[i]</code>。该操作的成本为 <code>i * nums[i]</code>。</li>
	<li><strong>后缀操作</strong>：选择一个满足 <code>0 &lt; i &lt; n - 1</code> 的下标 <code>i</code>，将下标从 <code>i + 1</code> 到 <code>n - 1</code> 的 <strong>所有</strong> 元素设为 <code>nums[i]</code>。该操作的成本为 <code>(n - i - 1) * nums[i]</code>。</li>
</ul>

<p>操作的成本根据 <code>nums[i]</code> 的 <strong>当前</strong> 值计算。所选前缀或后缀中的每个元素都计入成本，即使该元素已经等于 <code>nums[i]</code>。</p>

<p>返回使 <code>nums</code> 中 <strong>所有</strong> 元素都相等所需的 <strong>最小总成本</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>以下是一种达到最小成本的操作序列：</p>

<table style="border: 1px solid black; text-align: center;">
	<thead style="text-align: center;">
		<tr>
			<th style="border: 1px solid black;">nums</th>
			<th style="border: 1px solid black;">操作</th>
			<th style="border: 1px solid black;">i</th>
			<th style="border: 1px solid black;">修改后的 nums</th>
			<th style="border: 1px solid black;">成本</th>
		</tr>
	</thead>
	<tbody style="text-align: center;">
		<tr>
			<td style="border: 1px solid black;">[1, 2, 3]</td>
			<td style="border: 1px solid black;">后缀</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[1, 2, 2]</td>
			<td style="border: 1px solid black;">(3 - 1 - 1) * 2 = 2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">[1, 2, 2]</td>
			<td style="border: 1px solid black;">前缀</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[2, 2, 2]</td>
			<td style="border: 1px solid black;">1 * 2 = 2</td>
		</tr>
	</tbody>
</table>

<p>因此，总成本为 <code>2 + 2 = 4</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,3,3,6,3,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">9</span></p>

<p><strong>解释：</strong></p>

<p>以下是一种达到最小成本的操作序列：</p>

<table style="border: 1px solid black; text-align: center;">
	<thead style="text-align: center;">
		<tr>
			<th style="border: 1px solid black;">nums</th>
			<th style="border: 1px solid black;">操作</th>
			<th style="border: 1px solid black;">i</th>
			<th style="border: 1px solid black;">修改后的 nums</th>
			<th style="border: 1px solid black;">成本</th>
		</tr>
	</thead>
	<tbody style="text-align: center;">
		<tr>
			<td style="border: 1px solid black;">[3, 3, 3, 6, 3, 3]</td>
			<td style="border: 1px solid black;">后缀</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">[3, 3, 3, 3, 3, 3]</td>
			<td style="border: 1px solid black;">(6 - 2 - 1) * 3 = 9</td>
		</tr>
	</tbody>
</table>

<p>因此，总成本为 9。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,4,4,5,4,4,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">20</span></p>

<p><strong>解释：</strong></p>

<p>以下是一种达到最小成本的操作序列：</p>

<table style="border: 1px solid black; text-align: center;">
	<thead style="text-align: center;">
		<tr>
			<th style="border: 1px solid black;">nums</th>
			<th style="border: 1px solid black;">操作</th>
			<th style="border: 1px solid black;">i</th>
			<th style="border: 1px solid black;">修改后的 nums</th>
			<th style="border: 1px solid black;">成本</th>
		</tr>
	</thead>
	<tbody style="text-align: center;">
		<tr>
			<td style="border: 1px solid black;">[3, 4, 4, 5, 4, 4, 3]</td>
			<td style="border: 1px solid black;">前缀</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">[4, 4, 4, 4, 4, 4, 3]</td>
			<td style="border: 1px solid black;">4 * 4 = 16</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">[4, 4, 4, 4, 4, 4, 3]</td>
			<td style="border: 1px solid black;">后缀</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">[4, 4, 4, 4, 4, 4, 4]</td>
			<td style="border: 1px solid black;">(7 - 5 - 1) * 4 = 4</td>
		</tr>
	</tbody>
</table>

<p>因此，总成本为 <code>16 + 4 = 20</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

<!-- thinking:start -->

> **思考**
>
> $n$ 达到 $10^5$，操作可以反复执行，枚举操作序列无法通过。前缀操作和后缀操作都只能选下标 $1$ 到 $n-2$，并且一次改掉的是整段前缀或整段后缀。
>
> 操作只会把已经存在的值复制出去，所以最终全数组相等时，这个公共值必须是原数组里某个可操作下标上的值。
>
> 一段连续相等的区间 $[l, r]$ 只要与 $(0, n-1)$ 相交，区间外的位置就可以由这段里的可操作下标覆盖。左侧有 $l$ 个位置，右侧有 $n-r-1$ 个位置，成本是这两侧的长度之和乘上这段的值。
>
> 同一段再缩短，外侧只会更长。于是按相等段扫描，用 $l$ 和 $r$ 记下每段两端，取合法段成本的最小值。

<!-- thinking:end -->

我们用 $l$ 表示当前相等段的起点，初始为 $0$。 $r$ 从左到右扫描 $\textit{nums}$，当前元素记为 $x$。当 $r=n-1$，或 $x\neq\textit{nums}[r+1]$ 时， $[l, r]$ 构成一段连续相等的区间。

这段与开区间 $(0, n-1)$ 相交，也就是 $r>0$ 且 $l<n-1$ 时，区间外共有 $l+n-r-1$ 个位置。 $l>0$ 时，下标 $l$ 上的前缀操作覆盖左侧 $l$ 个位置； $r<n-1$ 时，下标 $r$ 上的后缀操作覆盖右侧 $n-r-1$ 个位置。成本为 $(l+n-r-1)\cdot x$。整段已经覆盖整个数组时，两侧都是空的，成本为 $0$。处理完后把 $l$ 更新为 $r+1$。

答案是所有合法段成本的最小值。同一个值被分成多段时，一次前缀或后缀总会覆盖整侧，扩展其中成本较小的一段已经付过外侧的费用，再拼接另一段不会更低。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, nums: list[int]) -> int:
        n = len(nums)
        ans = inf
        l = 0
        for r, x in enumerate(nums):
            if r == n - 1 or x != nums[r + 1]:
                if r > 0 and l < n - 1:
                    ans = min(ans, (l + n - r - 1) * x)
                l = r + 1
        return ans
```

#### Java

```java
class Solution {
    public long minCost(int[] nums) {
        int n = nums.length;
        long ans = Long.MAX_VALUE;
        int l = 0;
        for (int r = 0; r < n; r++) {
            int x = nums[r];
            if (r == n - 1 || x != nums[r + 1]) {
                if (r > 0 && l < n - 1) {
                    ans = Math.min(ans, (long) (l + n - r - 1) * x);
                }
                l = r + 1;
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
    long long minCost(vector<int>& nums) {
        int n = nums.size();
        long long ans = LLONG_MAX;
        int l = 0;
        for (int r = 0; r < n; r++) {
            int x = nums[r];
            if (r == n - 1 || x != nums[r + 1]) {
                if (r > 0 && l < n - 1) {
                    ans = min(ans, 1LL * (l + n - r - 1) * x);
                }
                l = r + 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minCost(nums []int) int64 {
	n := len(nums)
	ans := int64(math.MaxInt64)
	l := 0
	for r, x := range nums {
		if r == n-1 || x != nums[r+1] {
			if r > 0 && l < n-1 {
				ans = min(ans, int64(l+n-r-1)*int64(x))
			}
			l = r + 1
		}
	}
	return ans
}
```

#### TypeScript

```ts
function minCost(nums: number[]): number {
    const n = nums.length;
    let ans = Number.MAX_SAFE_INTEGER;
    let l = 0;
    for (let r = 0; r < n; r++) {
        const x = nums[r];
        if (r === n - 1 || x !== nums[r + 1]) {
            if (r > 0 && l < n - 1) {
                ans = Math.min(ans, (l + n - r - 1) * x);
            }
            l = r + 1;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
