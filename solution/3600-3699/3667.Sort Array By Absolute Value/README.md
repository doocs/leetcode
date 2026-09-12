---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3667.Sort%20Array%20By%20Absolute%20Value/README.md
tags:
    - 数组
    - 数学
    - 双指针
    - 排序
---

<!-- problem:start -->

# [3667. 按绝对值排序数组 🔒](https://leetcode.cn/problems/sort-array-by-absolute-value)

[English Version](/solution/3600-3699/3667.Sort%20Array%20By%20Absolute%20Value/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>nums</code>。</p>

<p>将 <code>nums</code> 中的元素按照它们的绝对值 <strong>非递减</strong> 顺序排列。</p>

<p>返回 <strong>任何</strong> 满足此条件的重新排列数组。</p>

<p><strong>注意：</strong>整数 <code>x</code> 的绝对值定义为：</p>

<ul>
	<li><code>x</code>&nbsp;若&nbsp;<code>x &gt;= 0</code></li>
	<li><code>-x</code> 若&nbsp;<code>x &lt; 0</code></li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [3,-1,-4,1,5]</span></p>

<p><span class="example-io"><b>输出：</b>[-1,1,3,-4,5]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>nums</code>&nbsp;中元素的绝对值分别是 3，1，4，1，5。</li>
	<li>将它们按升序排序，得到&nbsp;1，1，3，4，5。</li>
	<li>这对应于&nbsp;<code>[-1, 1, 3, -4, 5]</code>。另一种可能的排序是&nbsp;<code>[1, -1, 3, -4, 5]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [-100,100]</span></p>

<p><span class="example-io"><b>输出：</b>[-100,100]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>nums</code>&nbsp;中元素的绝对值分别是 100，100。</li>
	<li>将它们按升序排列，得到 100，100。</li>
	<li>这对应于 <code>[-100, 100]</code>。另一种可能的排序是&nbsp;<code>[100, -100]</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：自定义排序

<!-- thinking:start -->

> **思考**
>
> 按绝对值升序排列，题目不要求稳定或对原号的二次键。$n\le 100$，自定义排序即可。
>
> 排序键取 $\lvert x\rvert$。相等绝对值的相对顺序由语言稳定排序保留，不影响正确性。

<!-- thinking:end -->

我们可以使用自定义的排序函数来对数组进行排序，排序的依据是每个元素的绝对值。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortByAbsoluteValue(self, nums: List[int]) -> List[int]:
        return sorted(nums, key=lambda x: abs(x))
```

#### Java

```java
class Solution {
    public int[] sortByAbsoluteValue(int[] nums) {
        return Arrays.stream(nums)
            .boxed()
            .sorted(Comparator.comparingInt(Math::abs))
            .mapToInt(Integer::intValue)
            .toArray();
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> sortByAbsoluteValue(vector<int>& nums) {
        sort(nums.begin(), nums.end(), [](int a, int b) {
            return abs(a) < abs(b);
        });
        return nums;
    }
};
```

#### Go

```go
func sortByAbsoluteValue(nums []int) []int {
	slices.SortFunc(nums, func(a, b int) int {
		return abs(a) - abs(b)
	})
	return nums
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function sortByAbsoluteValue(nums: number[]): number[] {
    return nums.sort((a, b) => Math.abs(a) - Math.abs(b));
}
```

#### Rust

```rust
impl Solution {
    pub fn sort_by_absolute_value(mut nums: Vec<i32>) -> Vec<i32> {
        nums.sort_by_key(|&x| x.abs());
        nums
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
