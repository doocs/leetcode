---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0034.Find%20First%20and%20Last%20Position%20of%20Element%20in%20Sorted%20Array/README_EN.md
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array)

[中文文档](/solution/0000-0099/0034.Find%20First%20and%20Last%20Position%20of%20Element%20in%20Sorted%20Array/README.md)

## Description

<!-- description:start -->

<p>Given an array of integers <code>nums</code> sorted in non-decreasing order, find the starting and ending position of a given <code>target</code> value.</p>

<p>If <code>target</code> is not found in the array, return <code>[-1, -1]</code>.</p>

<p>You must&nbsp;write an algorithm with&nbsp;<code>O(log n)</code> runtime complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [5,7,7,8,8,10], target = 8
<strong>Output:</strong> [3,4]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [5,7,7,8,8,10], target = 6
<strong>Output:</strong> [-1,-1]
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> nums = [], target = 0
<strong>Output:</strong> [-1,-1]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= nums[i]&nbsp;&lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code> is a non-decreasing array.</li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= target&nbsp;&lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

We can perform binary search twice to find the left and right boundaries respectively.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the array $nums$.

Below are two general templates for binary search:

Template 1:

```java
boolean check(int x) {
}

int search(int left, int right) {
    while (left < right) {
        int mid = (left + right) >> 1;
        if (check(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

Template 2:

```java
boolean check(int x) {
}

int search(int left, int right) {
    while (left < right) {
        int mid = (left + right + 1) >> 1;
        if (check(mid)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
}
```

When doing binary search problems, you can follow the following routine:

1. Write out the loop condition $left < right$;
2. Inside the loop, you might as well write $mid = \lfloor \frac{left + right}{2} \rfloor$ first;
3. According to the specific problem, implement the $check()$ function (sometimes the logic is very simple, you can not define $check$), think about whether to use $right = mid$ (Template $1$) or $left = mid$ (Template $2$);
    - If $right = mid$, then write the else statement $left = mid + 1$, and there is no need to change the calculation of $mid$, that is, keep $mid = \lfloor \frac{left + right}{2} \rfloor$;
    - If $left = mid$, then write the else statement $right = mid - 1$, and add +1 when calculating $mid$, that is, $mid = \lfloor \frac{left + right + 1}{2} \rfloor$;
4. When the loop ends, $left$ equals $right$.

Note that the advantage of these two templates is that they always keep the answer within the binary search interval, and the value corresponding to the end condition of the binary search is exactly at the position of the answer. For the case that may have no solution, just check whether the $left$ or $right$ after the binary search ends satisfies the problem.

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
        if (l == r) return {-1, -1};
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

#### PHP

```php
class Solution {
    /**
     * @param integer[] $nums
     * @param integer $target
     * @return integer[]
     */

    function searchRange($nums, $target) {
        $min = -1;
        $max = -1;
        foreach ($nums as $key => $value) {
            if ($value == $target) {
                if ($min == -1) {
                    $min = $key;
                }

                if ($key > $max) {
                    $max = $key;
                }
            }
        }
        return [$min, $max];
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
