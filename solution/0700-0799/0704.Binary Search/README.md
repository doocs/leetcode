---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0704.Binary%20Search/README.md
tags:
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [704. 二分查找](https://leetcode.cn/problems/binary-search)

[English Version](/solution/0700-0799/0704.Binary%20Search/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个&nbsp;<code>n</code>&nbsp;个元素有序的（升序）整型数组&nbsp;<code>nums</code> 和一个目标值&nbsp;<code>target</code> &nbsp;，写一个函数搜索&nbsp;<code>nums</code>&nbsp;中的 <code>target</code>，如果目标值存在返回下标，否则返回 <code>-1</code>。</p>

<p><br>
<strong>示例 1:</strong></p>

<pre><strong>输入:</strong> <code>nums</code> = [-1,0,3,5,9,12], <code>target</code> = 9
<strong>输出:</strong> 4
<strong>解释:</strong> 9 出现在 <code>nums</code> 中并且下标为 4
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> <code>nums</code> = [-1,0,3,5,9,12], <code>target</code> = 2
<strong>输出:</strong> -1
<strong>解释:</strong> 2 不存在 <code>nums</code> 中因此返回 -1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>你可以假设 <code>nums</code>&nbsp;中的所有元素是不重复的。</li>
	<li><code>n</code>&nbsp;将在&nbsp;<code>[1, 10000]</code>之间。</li>
	<li><code>nums</code>&nbsp;的每个元素都将在&nbsp;<code>[-9999, 9999]</code>之间。</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们定义二分查找的左边界 $left=0$，右边界 $right=n-1$。

每一次循环，我们计算中间位置 $mid=(left+right)/2$，然后判断 $nums[mid]$ 和 $target$ 的大小关系：

-   如果 $nums[mid] \geq target$，则说明 $target$ 在 $[left, mid]$ 之间，我们将 $right$ 更新为 $mid$；
-   否则，说明 $target$ 在 $[mid+1, right]$ 之间，我们将 $left$ 更新为 $mid+1$。

当 $left \geq right$ 时，我们判断 $nums[left]$ 是否等于 $target$，如果等于则返回 $left$，否则返回 $-1$。

时间复杂度 $O(\log n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def search(self, nums: List[int], target: int) -> int:
        left, right = 0, len(nums) - 1
        while left < right:
            mid = (left + right) >> 1
            if nums[mid] >= target:
                right = mid
            else:
                left = mid + 1
        return left if nums[left] == target else -1
```

#### Java

```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left] == target ? left : -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int search(vector<int>& nums, int target) {
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (nums[mid] >= target)
                right = mid;
            else
                left = mid + 1;
        }
        return nums[left] == target ? left : -1;
    }
};
```

#### Go

```go
func search(nums []int, target int) int {
	left, right := 0, len(nums)-1
	for left < right {
		mid := (left + right) >> 1
		if nums[mid] >= target {
			right = mid
		} else {
			left = mid + 1
		}
	}
	if nums[left] == target {
		return left
	}
	return -1
}
```

#### Rust

```rust
use std::cmp::Ordering;

impl Solution {
    pub fn search(nums: Vec<i32>, target: i32) -> i32 {
        let mut l = 0;
        let mut r = nums.len();
        while l < r {
            let mid = (l + r) >> 1;
            match nums[mid].cmp(&target) {
                Ordering::Less => {
                    l = mid + 1;
                }
                Ordering::Greater => {
                    r = mid;
                }
                Ordering::Equal => {
                    return mid as i32;
                }
            }
        }
        -1
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var search = function (nums, target) {
    let left = 0;
    let right = nums.length - 1;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[mid] >= target) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return nums[left] == target ? left : -1;
};
```

#### C#

```cs
public class Solution {
    public int Search(int[] nums, int target) {
        int left = 0, right = nums.Length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left] == target ? left : -1;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
