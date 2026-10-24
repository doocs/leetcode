---
comments: true
difficulty: 中等
rating: 1775
source: 第 462 场周赛 Q2
tags:
    - 位运算
    - 数组
---

<!-- problem:start -->

# [3644. 排序排列](https://leetcode.cn/problems/maximum-k-to-sort-a-permutation)

[English Version](/solution/3600-3699/3644.Maximum%20K%20to%20Sort%20a%20Permutation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>，其中 <code>nums</code> 是范围 <code>[0..n - 1]</code> 内所有数字的一个&nbsp;<strong>排列&nbsp;</strong>。</p>

<p>你可以在满足条件 <code>nums[i] AND nums[j] == k</code> 的情况下交换下标&nbsp;<code>i</code> 和 <code>j</code> 的元素，其中 <code>AND</code> 表示按位与操作，<code>k</code> 是一个<strong>非负整数</strong>。</p>

<p>返回可以使数组按&nbsp;<strong>非递减&nbsp;</strong>顺序排序的最大值 <code>k</code>（允许进行任意次这样的交换）。如果 <code>nums</code> 已经是有序的，返回 0。</p>

<p><strong>排列&nbsp;</strong>是数组所有元素的一种重新排列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [0,3,2,1]</span></p>

<p><strong>输出：</strong><span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>选择 <code>k = 1</code>。交换 <code>nums[1] = 3</code> 和 <code>nums[3] = 1</code>，因为 <code>nums[1] AND nums[3] == 1</code>，从而得到一个排序后的排列：<code>[0, 1, 2, 3]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [0,1,3,2]</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>选择 <code>k = 2</code>。交换 <code>nums[2] = 3</code> 和 <code>nums[3] = 2</code>，因为 <code>nums[2] AND nums[3] == 2</code>，从而得到一个排序后的排列：<code>[0, 1, 2, 3]</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [3,2,1,0]</span></p>

<p><strong>输出：</strong><span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>只有当 <code>k = 0</code> 时，才能进行排序，因为没有更大的 <code>k</code> 能够满足 <code>nums[i] AND nums[j] == k</code> 的交换条件。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= n - 1</code></li>
	<li><code>nums</code> 是从 <code>0</code> 到 <code>n - 1</code> 的一个排列。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

<!-- thinking:start -->

> **思考**
>
> 最直接的做法是从大到小尝试 $k$，再判断错位元素能否只通过「按位与等于 $k$」的交换回到原位。$n \le 10^5$，任意两个值都可能满足交换条件，显式建图已经是平方级别，枚举无法通过。
>
> 必须移动的是那些不在自身下标上的值。每个这样的值至少参与一次交换，因此 $k$ 中每一个为 $1$ 的二进制位，在该值里也必须为 $1$。可行的 $k$ 于是不能超过全体错位值的按位与。
>
> 将这个按位与仍然记作 $k$。数 $k$ 属于排列 $[0,n)$，它与任意错位值的按位与都恰好等于 $k$，故可以作为中转，把错位元素排成任意顺序。若排列已经有序，则不需要交换，答案为 $0$。
>
> 按位与的单位元是全 $1$，所以 $\textit{ans}$ 从 $-1$ 开始，扫描时只并入满足 $\textit{nums}[i] \ne i$ 的值。数组有序时 $\textit{ans}$ 保持 $-1$，最后与 $0$ 取较大者即可。

<!-- thinking:end -->

我们用 $\textit{ans}$ 维护所有不在原位的元素的按位与，初始值为 $-1$。

遍历下标 $i$。若 $\textit{nums}[i] \ne i$，则把 $\textit{nums}[i]$ 按位与进 $\textit{ans}$。

扫描结束后，数组有序时 $\textit{ans}$ 仍为 $-1$，应当返回 $0$；否则 $\textit{ans}$ 就是最大可行的 $k$。因此返回 $\max(\textit{ans}, 0)$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortPermutation(self, nums: List[int]) -> int:
        ans = -1
        for i, x in enumerate(nums):
            if i != x:
                ans &= x
        return max(ans, 0)
```

#### Java

```java
class Solution {
    public int sortPermutation(int[] nums) {
        int ans = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (i != nums[i]) {
                ans &= nums[i];
            }
        }
        return Math.max(ans, 0);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sortPermutation(vector<int>& nums) {
        int ans = -1;
        for (int i = 0; i < nums.size(); ++i) {
            if (i != nums[i]) {
                ans &= nums[i];
            }
        }
        return max(ans, 0);
    }
};
```

#### Go

```go
func sortPermutation(nums []int) int {
	ans := -1
	for i, x := range nums {
		if i != x {
			ans &= x
		}
	}
	return max(ans, 0)
}
```

#### TypeScript

```ts
function sortPermutation(nums: number[]): number {
    let ans = -1;
    for (let i = 0; i < nums.length; ++i) {
        if (i != nums[i]) {
            ans &= nums[i];
        }
    }
    return Math.max(ans, 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn sort_permutation(nums: Vec<i32>) -> i32 {
        let mut ans = -1;
        for (i, &x) in nums.iter().enumerate() {
            if i as i32 != x {
                ans &= x;
            }
        }
        ans.max(0)
    }
}
```

#### C#

```cs
public class Solution {
    public int SortPermutation(int[] nums) {
        int ans = -1;
        for (int i = 0; i < nums.Length; i++) {
            if (i != nums[i]) {
                ans &= nums[i];
            }
        }
        return Math.Max(ans, 0);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
