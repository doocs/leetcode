---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3467.Transform%20Array%20by%20Parity/README.md
tags:
    - 数组
    - 计数
    - 排序
---

<!-- problem:start -->

# [3467. 将数组按照奇偶性转化](https://leetcode.cn/problems/transform-array-by-parity)

[English Version](/solution/3400-3499/3467.Transform%20Array%20by%20Parity/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。请你按照以下顺序 <strong>依次</strong>&nbsp;执行操作，转换 <code>nums</code>：</p>

<ol>
	<li>将每个偶数替换为 0。</li>
	<li>将每个奇数替换为 1。</li>
	<li>按&nbsp;<strong>非递减&nbsp;</strong>顺序排序修改后的数组。</li>
</ol>

<p>执行完这些操作后，返回结果数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [4,3,2,1]</span></p>

<p><strong>输出：</strong><span class="example-io">[0,0,1,1]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将偶数（4 和 2）替换为 0，将奇数（3 和 1）替换为 1。现在，<code>nums = [0, 1, 0, 1]</code>。</li>
	<li>按非递减顺序排序 <code>nums</code>，得到 <code>nums = [0, 0, 1, 1]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,5,1,4,2]</span></p>

<p><strong>输出：</strong><span class="example-io">[0,0,1,1,1]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将偶数（4 和 2）替换为 0，将奇数（1, 5 和 1）替换为 1。现在，<code>nums = [1, 1, 1, 0, 0]</code>。</li>
	<li>按非递减顺序排序&nbsp;<code>nums</code>，得到 <code>nums = [0, 0, 1, 1, 1]</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们可以遍历数组 $\textit{nums}$，统计其中偶数的个数 $\textit{even}$。然后我们将数组的前 $\textit{even}$ 个元素置为 $0$，剩余的元素置为 $1$。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def transformArray(self, nums: List[int]) -> List[int]:
        even = sum(x % 2 == 0 for x in nums)
        for i in range(even):
            nums[i] = 0
        for i in range(even, len(nums)):
            nums[i] = 1
        return nums
```

#### Java

```java
class Solution {
    public int[] transformArray(int[] nums) {
        int even = 0;
        for (int x : nums) {
            even += (x & 1 ^ 1);
        }
        for (int i = 0; i < even; ++i) {
            nums[i] = 0;
        }
        for (int i = even; i < nums.length; ++i) {
            nums[i] = 1;
        }
        return nums;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> transformArray(vector<int>& nums) {
        int even = 0;
        for (int x : nums) {
            even += (x & 1 ^ 1);
        }
        for (int i = 0; i < even; ++i) {
            nums[i] = 0;
        }
        for (int i = even; i < nums.size(); ++i) {
            nums[i] = 1;
        }
        return nums;
    }
};
```

#### Go

```go
func transformArray(nums []int) []int {
	even := 0
	for _, x := range nums {
		even += x&1 ^ 1
	}
	for i := 0; i < even; i++ {
		nums[i] = 0
	}
	for i := even; i < len(nums); i++ {
		nums[i] = 1
	}
	return nums
}
```

#### TypeScript

```ts
function transformArray(nums: number[]): number[] {
    const even = nums.filter(x => x % 2 === 0).length;
    for (let i = 0; i < even; ++i) {
        nums[i] = 0;
    }
    for (let i = even; i < nums.length; ++i) {
        nums[i] = 1;
    }
    return nums;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
