---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0154.Find%20Minimum%20in%20Rotated%20Sorted%20Array%20II/README_EN.md
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [154. Find Minimum in Rotated Sorted Array II](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii)

[中文文档](/solution/0100-0199/0154.Find%20Minimum%20in%20Rotated%20Sorted%20Array%20II/README.md)

## Description

<!-- description:start -->

<p>Suppose an array of length <code>n</code> sorted in ascending order is <strong>rotated</strong> between <code>1</code> and <code>n</code> times. For example, the array <code>nums = [0,1,4,4,5,6,7]</code> might become:</p>

<ul>
	<li><code>[4,5,6,7,0,1,4]</code> if it was rotated <code>4</code> times.</li>
	<li><code>[0,1,4,4,5,6,7]</code> if it was rotated <code>7</code> times.</li>
</ul>

<p>Notice that <strong>rotating</strong> an array <code>[a[0], a[1], a[2], ..., a[n-1]]</code> 1 time results in the array <code>[a[n-1], a[0], a[1], a[2], ..., a[n-2]]</code>.</p>

<p>Given the sorted rotated array <code>nums</code> that may contain <strong>duplicates</strong>, return <em>the minimum element of this array</em>.</p>

<p>You must decrease the overall operation steps as much as possible.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,3,5]
<strong>Output:</strong> 1
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [2,2,2,0,1]
<strong>Output:</strong> 0
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>-5000 &lt;= nums[i] &lt;= 5000</code></li>
	<li><code>nums</code> is sorted and rotated between <code>1</code> and <code>n</code> times.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> This problem is similar to&nbsp;<a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/" target="_blank">Find Minimum in Rotated Sorted Array</a>, but&nbsp;<code>nums</code> may contain <strong>duplicates</strong>. Would this affect the runtime complexity? How and why?</p>

<p>&nbsp;</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

We define the left boundary $l = 0$ and right boundary $r = n - 1$ for binary search. Each iteration, we calculate the middle position $mid = (l + r) \gg 1$ and compare the relationship between $nums[mid]$ and $nums[r]$:

- If $nums[mid] > nums[r]$, the minimum value is to the right of $mid$, so we update $l$ to $mid + 1$.
- If $nums[mid] = nums[r]$, we cannot determine the position of the minimum value, but we can move $r$ to the left by one position, i.e., $r = r - 1$, to narrow down the search range.
- If $nums[mid] < nums[r]$, the minimum value is to the left of $mid$ or at $mid$ itself, so we update $r$ to $mid$.

When $l$ and $r$ meet, the pointer $l$ points to the position of the minimum value, and we return $nums[l]$.

The time complexity is $O(n)$, as in the worst case where all elements in the array are the same, we need to traverse the entire array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMin(self, nums: List[int]) -> int:
        l, r = 0, len(nums) - 1
        while l < r:
            mid = (l + r) >> 1
            if nums[mid] > nums[r]:
                l = mid + 1
            elif nums[mid] == nums[r]:
                r -= 1
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
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else if (nums[mid] == nums[r]) {
                r--;
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
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else if (nums[mid] == nums[r]) {
                r--;
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
		if nums[mid] > nums[r] {
			l = mid + 1
		} else if nums[mid] == nums[r] {
			r--
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
        if (nums[mid] > nums[r]) {
            l = mid + 1;
        } else if (nums[mid] == nums[r]) {
            r--;
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
            if nums[mid] > nums[r] {
                l = mid + 1;
            } else if nums[mid] == nums[r] {
                r -= 1;
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
        if (nums[mid] > nums[r]) {
            l = mid + 1;
        } else if (nums[mid] == nums[r]) {
            r--;
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
