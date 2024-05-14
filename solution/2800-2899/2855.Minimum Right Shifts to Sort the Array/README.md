---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2855.Minimum%20Right%20Shifts%20to%20Sort%20the%20Array/README.md
rating: 1379
tags:
    - 数组
---

# [2855. 使数组成为递增数组的最少右移次数](https://leetcode.cn/problems/minimum-right-shifts-to-sort-the-array)

[English Version](/solution/2800-2899/2855.Minimum%20Right%20Shifts%20to%20Sort%20the%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的数组&nbsp;<code>nums</code>&nbsp;，数组中的元素为&nbsp;<strong>互不相同</strong>&nbsp;的正整数。请你返回让 <code>nums</code>&nbsp;成为递增数组的 <strong>最少右移</strong>&nbsp;次数，如果无法得到递增数组，返回 <code>-1</code>&nbsp;。</p>

<p>一次 <strong>右移</strong>&nbsp;指的是同时对所有下标进行操作，将下标为 <code>i</code>&nbsp;的元素移动到下标&nbsp;<code>(i + 1) % n</code>&nbsp;处。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [3,4,5,1,2]
<b>输出：</b>2
<b>解释：</b>
第一次右移后，nums = [2,3,4,5,1] 。
第二次右移后，nums = [1,2,3,4,5] 。
现在 nums 是递增数组了，所以答案为 2 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,3,5]
<b>输出：</b>0
<b>解释：</b>nums 已经是递增数组了，所以答案为 0 。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,4]
<b>输出：</b>-1
<b>解释：</b>无法将数组变为递增数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>nums</code>&nbsp;中的整数互不相同。</li>
</ul>

## 解法

### 方法一：直接遍历

我们先用一个指针 $i$ 从左到右遍历数组 $nums$，找出一段连续的递增序列，直到 $i$ 到达数组末尾或者 $nums[i - 1] \gt nums[i]$。接下来我们用另一个指针 $k$ 从 $i + 1$ 开始遍历数组 $nums$，找出一段连续的递增序列，直到 $k$ 到达数组末尾或者 $nums[k - 1] \gt nums[k]$ 且 $nums[k] \gt nums[0]$。如果 $k$ 到达数组末尾，说明数组已经是递增的，返回 $n - i$；否则返回 $-1$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minimumRightShifts(self, nums: List[int]) -> int:
        n = len(nums)
        i = 1
        while i < n and nums[i - 1] < nums[i]:
            i += 1
        k = i + 1
        while k < n and nums[k - 1] < nums[k] < nums[0]:
            k += 1
        return -1 if k < n else n - i
```

```java
class Solution {
    public int minimumRightShifts(List<Integer> nums) {
        int n = nums.size();
        int i = 1;
        while (i < n && nums.get(i - 1) < nums.get(i)) {
            ++i;
        }
        int k = i + 1;
        while (k < n && nums.get(k - 1) < nums.get(k) && nums.get(k) < nums.get(0)) {
            ++k;
        }
        return k < n ? -1 : n - i;
    }
}
```

```cpp
class Solution {
public:
    int minimumRightShifts(vector<int>& nums) {
        int n = nums.size();
        int i = 1;
        while (i < n && nums[i - 1] < nums[i]) {
            ++i;
        }
        int k = i + 1;
        while (k < n && nums[k - 1] < nums[k] && nums[k] < nums[0]) {
            ++k;
        }
        return k < n ? -1 : n - i;
    }
};
```

```go
func minimumRightShifts(nums []int) int {
	n := len(nums)
	i := 1
	for i < n && nums[i-1] < nums[i] {
		i++
	}
	k := i + 1
	for k < n && nums[k-1] < nums[k] && nums[k] < nums[0] {
		k++
	}
	if k < n {
		return -1
	}
	return n - i
}
```

```ts
function minimumRightShifts(nums: number[]): number {
    const n = nums.length;
    let i = 1;
    while (i < n && nums[i - 1] < nums[i]) {
        ++i;
    }
    let k = i + 1;
    while (k < n && nums[k - 1] < nums[k] && nums[k] < nums[0]) {
        ++k;
    }
    return k < n ? -1 : n - i;
}
```

<!-- tabs:end -->

<!-- end -->
