---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0081.Search%20in%20Rotated%20Sorted%20Array%20II/README.md
tags:
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [81. 搜索旋转排序数组 II](https://leetcode.cn/problems/search-in-rotated-sorted-array-ii)

[English Version](/solution/0000-0099/0081.Search%20in%20Rotated%20Sorted%20Array%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>已知存在一个按非降序排列的整数数组 <code>nums</code> ，数组中的值不必互不相同。</p>

<p>在传递给函数之前，<code>nums</code> 在预先未知的某个下标 <code>k</code>（<code>0 &lt;= k &lt; nums.length</code>）上进行了 <strong>旋转 </strong>，使数组变为 <code>[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]</code>（下标 <strong>从 0 开始</strong> 计数）。例如， <code>[0,1,2,4,4,4,5,6,6,7]</code> 在下标 <code>5</code> 处经旋转后可能变为 <code>[4,5,6,6,7,0,1,2,4,4]</code> 。</p>

<p>给你 <strong>旋转后</strong> 的数组 <code>nums</code> 和一个整数 <code>target</code> ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 <code>nums</code> 中存在这个目标值 <code>target</code> ，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p>你必须尽可能减少整个操作步骤。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2<code>,5,6,0,0,1,2]</code>, target = 0
<strong>输出：</strong>true
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2<code>,5,6,0,0,1,2]</code>, target = 3
<strong>输出：</strong>false</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li>题目数据保证 <code>nums</code> 在预先未知的某个下标上进行了旋转</li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>此题与&nbsp;<a href="https://leetcode.cn/problems/search-in-rotated-sorted-array/description/">搜索旋转排序数组</a>&nbsp;相似，但本题中的&nbsp;<code>nums</code>&nbsp; 可能包含 <strong>重复</strong> 元素。这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？</li>
</ul>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们定义二分查找的左边界 $l=0$，右边界 $r=n-1$，其中 $n$ 为数组的长度。

每次在二分查找的过程中，我们会得到当前的中点 $mid=(l+r)/2$。

-   如果 $nums[mid] \gt nums[r]$，说明 $[l,mid]$ 是有序的，此时如果 $nums[l] \le target \le nums[mid]$，说明 $target$ 位于 $[l,mid]$，否则 $target$ 位于 $[mid+1,r]$。
-   如果 $nums[mid] \lt nums[r]$，说明 $[mid+1,r]$ 是有序的，此时如果 $nums[mid] \lt target \le nums[r]$，说明 $target$ 位于 $[mid+1,r]$，否则 $target$ 位于 $[l,mid]$。
-   如果 $nums[mid] = nums[r]$，说明元素 $nums[mid]$ 和 $nums[r]$ 相等，此时无法判断 $target$ 位于哪个区间，我们只能将 $r$ 减少 $1$。

二分查找结束后，如果 $nums[l] = target$，则说明数组中存在目标值 $target$，否则说明不存在。

时间复杂度近似 $O(\log n)$，空间复杂度 $O(1)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def search(self, nums: List[int], target: int) -> bool:
        n = len(nums)
        l, r = 0, n - 1
        while l < r:
            mid = (l + r) >> 1
            if nums[mid] > nums[r]:
                if nums[l] <= target <= nums[mid]:
                    r = mid
                else:
                    l = mid + 1
            elif nums[mid] < nums[r]:
                if nums[mid] < target <= nums[r]:
                    l = mid + 1
                else:
                    r = mid
            else:
                r -= 1
        return nums[l] == target
```

#### Java

```java
class Solution {
    public boolean search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > nums[r]) {
                if (nums[l] <= target && target <= nums[mid]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            } else if (nums[mid] < nums[r]) {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            } else {
                --r;
            }
        }
        return nums[l] == target;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool search(vector<int>& nums, int target) {
        int l = 0, r = nums.size() - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > nums[r]) {
                if (nums[l] <= target && target <= nums[mid]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            } else if (nums[mid] < nums[r]) {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            } else {
                --r;
            }
        }
        return nums[l] == target;
    }
};
```

#### Go

```go
func search(nums []int, target int) bool {
	l, r := 0, len(nums)-1
	for l < r {
		mid := (l + r) >> 1
		if nums[mid] > nums[r] {
			if nums[l] <= target && target <= nums[mid] {
				r = mid
			} else {
				l = mid + 1
			}
		} else if nums[mid] < nums[r] {
			if nums[mid] < target && target <= nums[r] {
				l = mid + 1
			} else {
				r = mid
			}
		} else {
			r--
		}
	}
	return nums[l] == target
}
```

#### TypeScript

```ts
function search(nums: number[], target: number): boolean {
    let [l, r] = [0, nums.length - 1];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] > nums[r]) {
            if (nums[l] <= target && target <= nums[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        } else if (nums[mid] < nums[r]) {
            if (nums[mid] < target && target <= nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        } else {
            --r;
        }
    }
    return nums[l] === target;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
