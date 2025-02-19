---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0034.Find%20First%20and%20Last%20Position%20of%20Element%20in%20Sorted%20Array/README.md
tags:
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array)

[English Version](/solution/0000-0099/0034.Find%20First%20and%20Last%20Position%20of%20Element%20in%20Sorted%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个按照非递减顺序排列的整数数组 <code>nums</code>，和一个目标值 <code>target</code>。请你找出给定目标值在数组中的开始位置和结束位置。</p>

<p>如果数组中不存在目标值 <code>target</code>，返回&nbsp;<code>[-1, -1]</code>。</p>

<p>你必须设计并实现时间复杂度为&nbsp;<code>O(log n)</code>&nbsp;的算法解决此问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [<code>5,7,7,8,8,10]</code>, target = 8
<strong>输出：</strong>[3,4]</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>nums = [<code>5,7,7,8,8,10]</code>, target = 6
<strong>输出：</strong>[-1,-1]</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [], target = 0
<strong>输出：</strong>[-1,-1]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= nums[i]&nbsp;&lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code>&nbsp;是一个非递减数组</li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= target&nbsp;&lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们可以进行两次二分查找，分别查找出左边界和右边界。

时间复杂度 $O(\log n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        l = bisect_left(nums, target)
        r = bisect_left(nums, target + 1)
        return [-1, -1] if l == r else [l, r - 1]
```

#### Java

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int l = search(nums, target);
        int r = search(nums, target + 1);
        return l == r ? new int[] {-1, -1} : new int[] {l, r - 1};
    }

    private int search(int[] nums, int x) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> searchRange(vector<int>& nums, int target) {
        int l = lower_bound(nums.begin(), nums.end(), target) - nums.begin();
        int r = lower_bound(nums.begin(), nums.end(), target + 1) - nums.begin();
        if (l == r) {
            return {-1, -1};
        }
        return {l, r - 1};
    }
};
```

#### Go

```go
func searchRange(nums []int, target int) []int {
	l := sort.SearchInts(nums, target)
	r := sort.SearchInts(nums, target+1)
	if l == r {
		return []int{-1, -1}
	}
	return []int{l, r - 1}
}
```

#### TypeScript

```ts
function searchRange(nums: number[], target: number): number[] {
    const search = (x: number): number => {
        let [left, right] = [0, nums.length];
        while (left < right) {
            const mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    };
    const l = search(target);
    const r = search(target + 1);
    return l === r ? [-1, -1] : [l, r - 1];
}
```

#### Rust

```rust
impl Solution {
    pub fn search_range(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let n = nums.len();
        let search = |x| {
            let mut left = 0;
            let mut right = n;
            while left < right {
                let mid = left + (right - left) / 2;
                if nums[mid] < x {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            left
        };
        let l = search(target);
        let r = search(target + 1);
        if l == r {
            return vec![-1, -1];
        }
        vec![l as i32, (r - 1) as i32]
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var searchRange = function (nums, target) {
    function search(x) {
        let left = 0,
            right = nums.length;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    const l = search(target);
    const r = search(target + 1);
    return l == r ? [-1, -1] : [l, r - 1];
};
```

#### C#

```cs
public class Solution {
    public int[] SearchRange(int[] nums, int target) {
        int l = Search(nums, target);
        int r = Search(nums, target + 1);
        return l == r ? new int[] {-1, -1} : new int[] {l, r - 1};
    }

    private int Search(int[] nums, int x) {
        int left = 0, right = nums.Length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $target
     * @return Integer[]
     */
    function searchRange($nums, $target) {
        $search = function ($x) use ($nums) {
            $left = 0;
            $right = count($nums);
            while ($left < $right) {
                $mid = intdiv($left + $right, 2);
                if ($nums[$mid] >= $x) {
                    $right = $mid;
                } else {
                    $left = $mid + 1;
                }
            }
            return $left;
        };

        $l = $search($target);
        $r = $search($target + 1);
        return $l === $r ? [-1, -1] : [$l, $r - 1];
    }
}
```

#### Kotlin

```kotlin
class Solution {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        val left = this.search(nums, target)
        val right = this.search(nums, target + 1)
        return if (left == right) intArrayOf(-1, -1) else intArrayOf(left, right - 1)
    }

    private fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size
        while (left < right) {
            val middle = (left + right) / 2
            if (nums[middle] < target) {
                left = middle + 1
            } else {
                right = middle
            }
        }
        return left
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
