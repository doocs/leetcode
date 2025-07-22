---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2576.Find%20the%20Maximum%20Number%20of%20Marked%20Indices/README.md
rating: 1843
source: 第 334 场周赛 Q3
tags:
    - 贪心
    - 数组
    - 双指针
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [2576. 求出最多标记下标](https://leetcode.cn/problems/find-the-maximum-number-of-marked-indices)

[English Version](/solution/2500-2599/2576.Find%20the%20Maximum%20Number%20of%20Marked%20Indices/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>一开始，所有下标都没有被标记。你可以执行以下操作任意次：</p>

<ul>
	<li>选择两个 <strong>互不相同且未标记</strong>&nbsp;的下标&nbsp;<code>i</code> 和&nbsp;<code>j</code>&nbsp;，满足&nbsp;<code>2 * nums[i] &lt;= nums[j]</code>&nbsp;，标记下标&nbsp;<code>i</code> 和&nbsp;<code>j</code>&nbsp;。</li>
</ul>

<p>请你执行上述操作任意次，返回<em>&nbsp;</em><code>nums</code>&nbsp;中最多可以标记的下标数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [3,5,2,4]
<b>输出：</b>2
<strong>解释：</strong>第一次操作中，选择 i = 2 和 j = 1 ，操作可以执行的原因是 2 * nums[2] &lt;= nums[1] ，标记下标 2 和 1 。
没有其他更多可执行的操作，所以答案为 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [9,2,5,4]
<b>输出：</b>4
<strong>解释：</strong>第一次操作中，选择 i = 3 和 j = 0 ，操作可以执行的原因是 2 * nums[3] &lt;= nums[0] ，标记下标 3 和 0 。
第二次操作中，选择 i = 1 和 j = 2 ，操作可以执行的原因是 2 * nums[1] &lt;= nums[2] ，标记下标 1 和 2 。
没有其他更多可执行的操作，所以答案为 4 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [7,6,8]
<b>输出：</b>0
<strong>解释：</strong>没有任何可以执行的操作，所以答案为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 双指针

根据题目描述，题目最多产生 $n / 2$ 组标记，其中 $n$ 为数组 $\textit{nums}$ 的长度。

为了将下标尽可能多地标记，我们可以将数组 $\textit{nums}$ 排序，接下来，我们遍历右半部分的每个元素 $\textit{nums}[j]$，用一个指针 $\textit{i}$ 指向左半部分的最小元素，如果 $\textit{nums}[i] \times 2 \leq \textit{nums}[j]$，则可以标记下标 $\textit{i}$ 和 $\textit{j}$，我们将 $\textit{i}$ 向右移动一个位置。继续遍历右半部分的元素，直到到达数组的末尾。此时，我们可以标记的下标数目为 $\textit{i} \times 2$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxNumOfMarkedIndices(self, nums: List[int]) -> int:
        nums.sort()
        i, n = 0, len(nums)
        for x in nums[(n + 1) // 2 :]:
            if nums[i] * 2 <= x:
                i += 1
        return i * 2
```

#### Java

```java
class Solution {
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int i = 0, n = nums.length;
        for (int j = (n + 1) / 2; j < n; ++j) {
            if (nums[i] * 2 <= nums[j]) {
                ++i;
            }
        }
        return i * 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxNumOfMarkedIndices(vector<int>& nums) {
        ranges::sort(nums);
        int i = 0, n = nums.size();
        for (int j = (n + 1) / 2; j < n; ++j) {
            if (nums[i] * 2 <= nums[j]) {
                ++i;
            }
        }
        return i * 2;
    }
};
```

#### Go

```go
func maxNumOfMarkedIndices(nums []int) (ans int) {
	sort.Ints(nums)
	i, n := 0, len(nums)
	for _, x := range nums[(n+1)/2:] {
		if nums[i]*2 <= x {
			i++
		}
	}
	return i * 2
}
```

#### TypeScript

```ts
function maxNumOfMarkedIndices(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let i = 0;
    for (let j = (n + 1) >> 1; j < n; ++j) {
        if (nums[i] * 2 <= nums[j]) {
            ++i;
        }
    }
    return i * 2;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_num_of_marked_indices(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let mut i = 0;
        let n = nums.len();
        for j in (n + 1) / 2..n {
            if nums[i] * 2 <= nums[j] {
                i += 1;
            }
        }
        (i * 2) as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
