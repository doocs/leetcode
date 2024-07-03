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

我们定义二分查找的左边界 $l=0$，右边界 $r=n-1$。

每一次循环，我们计算中间位置 $\text{mid}=(l+r)/2$，然后比较 $\text{nums}[\text{mid}]$ 和 $\text{target}$ 的大小。

-   如果 $\text{nums}[\text{mid}] \geq \text{target}$，说明 $\text{target}$ 在左半部分，我们将右边界 $r$ 移动到 $\text{mid}$；
-   否则，说明 $\text{target}$ 在右半部分，我们将左边界 $l$ 移动到 $\text{mid}+1$。

循环结束的条件是 $l<r$，此时 $\text{nums}[l]$ 就是我们要找的目标值，如果 $\text{nums}[l]=\text{target}$，返回 $l$，否则返回 $-1$。

时间复杂度 $O(\log n)$，其中 $n$ 是数组 $\text{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def search(self, nums: List[int], target: int) -> int:
        l, r = 0, len(nums) - 1
        while l < r:
            mid = (l + r) >> 1
            if nums[mid] >= target:
                r = mid
            else:
                l = mid + 1
        return l if nums[l] == target else -1
```

#### Java

```java
class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l] == target ? l : -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int search(vector<int>& nums, int target) {
        int l = 0, r = nums.size() - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l] == target ? l : -1;
    }
};
```

#### Go

```go
func search(nums []int, target int) int {
	l, r := 0, len(nums)-1
	for l < r {
		mid := (l + r) >> 1
		if nums[mid] >= target {
			r = mid
		} else {
			l = mid + 1
		}
	}
	if nums[l] == target {
		return l
	}
	return -1
}
```

#### TypeScript

```ts
function search(nums: number[], target: number): number {
    let [l, r] = [0, nums.length - 1];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] >= target) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return nums[l] === target ? l : -1;
}
```

#### Rust

```rust
impl Solution {
    pub fn search(nums: Vec<i32>, target: i32) -> i32 {
        let mut l: usize = 0;
        let mut r: usize = nums.len() - 1;
        while l < r {
            let mid = (l + r) >> 1;
            if nums[mid] >= target {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if nums[l] == target {
            l as i32
        } else {
            -1
        }
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
    let [l, r] = [0, nums.length - 1];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] >= target) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return nums[l] === target ? l : -1;
};
```

#### C#

```cs
public class Solution {
    public int Search(int[] nums, int target) {
        int l = 0, r = nums.Length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l] == target ? l : -1;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
