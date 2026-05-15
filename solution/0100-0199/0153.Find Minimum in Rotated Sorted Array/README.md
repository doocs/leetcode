---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0153.Find%20Minimum%20in%20Rotated%20Sorted%20Array/README.md
tags:
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [153. 寻找旋转排序数组中的最小值](https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array)

[English Version](/solution/0100-0199/0153.Find%20Minimum%20in%20Rotated%20Sorted%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

已知一个长度为 <code>n</code> 的数组，预先按照升序排列，经由 <code>1</code> 到 <code>n</code> 次 <strong>旋转</strong> 后，得到输入数组。例如，原数组 <code>nums = [0,1,2,4,5,6,7]</code> 在变化后可能得到：

<ul>
	<li>若旋转 <code>4</code> 次，则可以得到 <code>[4,5,6,7,0,1,2]</code></li>
	<li>若旋转 <code>7</code> 次，则可以得到 <code>[0,1,2,4,5,6,7]</code></li>
</ul>

<p>注意，数组 <code>[a[0], a[1], a[2], ..., a[n-1]]</code> <strong>旋转一次</strong> 的结果为数组 <code>[a[n-1], a[0], a[1], a[2], ..., a[n-2]]</code> 。</p>

<p>给你一个元素值 <strong>互不相同</strong> 的数组 <code>nums</code> ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 <strong>最小元素</strong> 。</p>

<p>你必须设计一个时间复杂度为&nbsp;<code>O(log n)</code> 的算法解决此问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,4,5,1,2]
<strong>输出：</strong>1
<strong>解释：</strong>原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,5,6,7,0,1,2]
<strong>输出：</strong>0
<strong>解释：</strong>原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [11,13,15,17]
<strong>输出：</strong>11
<strong>解释：</strong>原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>-5000 &lt;= nums[i] &lt;= 5000</code></li>
	<li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li>
	<li><code>nums</code> 原来是一个升序排序的数组，并进行了 <code>1</code> 至 <code>n</code> 次旋转</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们可以使用二分查找来解决这个问题。

首先，我们定义两个指针 $l$ 和 $r$，分别指向数组的起始位置和结束位置。然后，我们进入一个循环，直到 $l$ 小于 $r$。

在每次循环中，我们计算中间位置 $mid$，并比较 $nums[mid]$ 和 $nums[n-1]$ 的值。如果 $nums[mid]$ 大于 $nums[n-1]$，说明最小值在 $mid$ 的右侧，因此我们将 $l$ 更新为 $mid + 1$。否则，最小值在 $mid$ 的左侧或 $mid$ 本身，因此我们将 $r$ 更新为 $mid$。当循环结束时，指针 $l$ 将指向最小值的位置，我们返回 $nums[l]$ 即可。

时间复杂度 $O(\log n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMin(self, nums: List[int]) -> int:
        l, r = 0, len(nums) - 1
        while l < r:
            mid = (l + r) >> 1
            if nums[mid] > nums[-1]:
                l = mid + 1
            else:
                r = mid
        return nums[l]
```

#### Java

```java
class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > nums[nums.length - 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMin(vector<int>& nums) {
        int l = 0, r = nums.size() - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > nums.back()) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }
};
```

#### Go

```go
func findMin(nums []int) int {
	l, r := 0, len(nums)-1
	for l < r {
		mid := (l + r) >> 1
		if nums[mid] > nums[len(nums)-1] {
			l = mid + 1
		} else {
			r = mid
		}
	}
	return nums[l]
}
```

#### TypeScript

```ts
function findMin(nums: number[]): number {
    let l = 0,
        r = nums.length - 1;
    while (l < r) {
        let mid = (l + r) >> 1;
        if (nums[mid] > nums[nums.length - 1]) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    return nums[l];
}
```

#### Rust

```rust
impl Solution {
    pub fn find_min(nums: Vec<i32>) -> i32 {
        let (mut l, mut r) = (0, nums.len() - 1);
        while l < r {
            let mid = (l + r) >> 1;
            if nums[mid] > nums[nums.len() - 1] {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        nums[l]
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findMin = function (nums) {
    let l = 0,
        r = nums.length - 1;
    while (l < r) {
        let mid = (l + r) >> 1;
        if (nums[mid] > nums[nums.length - 1]) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    return nums[l];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
