---
comments: true
difficulty: 简单
rating: 1417
source: 第 528 场周赛 Q1
---

<!-- problem:start -->

# [4111. 统计一致子数组数目 I](https://leetcode.cn/problems/count-consistent-subarrays-i)

[English Version](/solution/4100-4199/4111.Count%20Consistent%20Subarrays%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>如果一个子数组中所有元素的&nbsp;<strong>按位与&nbsp;</strong>运算结果等于该子数组的&nbsp;<strong>最小值&nbsp;</strong>，则称该子数组为&nbsp;<strong>一致子数组&nbsp;</strong>。</p>

<p>返回 <code>nums</code> 中&nbsp;<strong>一致子数组&nbsp;</strong>的个数。</p>

<p><strong>子数组&nbsp;</strong>是数组中一个连续、非空的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5,3,1,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<p>以下子数组是一致子数组：</p>

<table style="border: 1px solid black; text-align: center;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">子数组</th>
			<th style="border: 1px solid black;">包含的值</th>
			<th style="border: 1px solid black;">按位与</th>
			<th style="border: 1px solid black;">最小值</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[0..0]</td>
			<td style="border: 1px solid black;">[5]</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[1..1]</td>
			<td style="border: 1px solid black;">[3]</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[2..2]</td>
			<td style="border: 1px solid black;">[1]</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[3..3]</td>
			<td style="border: 1px solid black;">[2]</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[1..2]</td>
			<td style="border: 1px solid black;">[3, 1]</td>
			<td style="border: 1px solid black;">3 & 1 = 1</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[0..2]</td>
			<td style="border: 1px solid black;">[5, 3, 1]</td>
			<td style="border: 1px solid black;">5 & 3 & 1 = 1</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
	</tbody>
</table>

<p>因此，一致子数组的个数为 6。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,1,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<p>对于每个子数组，其按位与运算结果和最小值均等于 1。因此，答案为 6。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [0]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>唯一的子数组是 <code>[0]</code>，其按位与运算结果和最小值均为 0。因此，答案为 1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

<!-- thinking:start -->

> **思考**
>
> $n$ 不超过 $100$。对每个子数组从头计算按位与与最小值，复杂度是 $O(n^3)$，仍在时限以内。右端点每向右扩一位，整段都会被重新计算。
>
> 子数组 $\textit{nums}[i..j]$ 的按位与与最小值都由 $\textit{nums}[i..j-1]$ 和 $\textit{nums}[j]$ 决定，右移一位时都可以用 $O(1)$ 更新。
>
> 因此固定左端点 $i$，再把右端点 $j$ 从 $i$ 扫到 $n-1$。用 $x$ 记下当前按位与，用 $y$ 记下当前最小值，并在 $x=y$ 时计数。

<!-- thinking:end -->

我们枚举左端点 $i$。令 $x$ 与 $y$ 分别为子数组 $\textit{nums}[i..j]$ 的按位与和最小值，初始时二者都等于 $\textit{nums}[i]$。右端点 $j$ 从 $i$ 走到 $n-1$，每走一步先做 $x \mathrel{\&}= \textit{nums}[j]$，再把 $y$ 更新为 $\min(y,\textit{nums}[j])$。 $x=y$ 时当前子数组一致，答案加一。

时间复杂度 $O(n^2)$，空间复杂度 $O(1)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countConsistent(self, nums: list[int]) -> int:
        n = len(nums)
        ans = 0
        for i, x in enumerate(nums):
            y = x
            for j in range(i, n):
                x &= nums[j]
                y = min(y, nums[j])
                if x == y:
                    ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countConsistent(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int y = x;
            for (int j = i; j < n; j++) {
                x &= nums[j];
                y = Math.min(y, nums[j]);
                if (x == y) {
                    ans++;
                }
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
    int countConsistent(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int y = x;
            for (int j = i; j < n; j++) {
                x &= nums[j];
                y = min(y, nums[j]);
                if (x == y) {
                    ans++;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countConsistent(nums []int) int {
	n := len(nums)
	ans := 0
	for i, x := range nums {
		y := x
		for j := i; j < n; j++ {
			x &= nums[j]
			y = min(y, nums[j])
			if x == y {
				ans++
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countConsistent(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        let x = nums[i];
        let y = x;
        for (let j = i; j < n; j++) {
            x &= nums[j];
            y = Math.min(y, nums[j]);
            if (x === y) {
                ans++;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
