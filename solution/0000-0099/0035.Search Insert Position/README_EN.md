---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0035.Search%20Insert%20Position/README_EN.md
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [35. Search Insert Position](https://leetcode.com/problems/search-insert-position)

[中文文档](/solution/0000-0099/0035.Search%20Insert%20Position/README.md)

## Description

<!-- description:start -->

<p>Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.</p>

<p>You must&nbsp;write an algorithm with&nbsp;<code>O(log n)</code> runtime complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,6], target = 5
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,6], target = 2
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,6], target = 7
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> contains <strong>distinct</strong> values sorted in <strong>ascending</strong> order.</li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

Since the array $nums$ is already sorted, we can use the binary search method to find the insertion position of the target value $target$.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        left, right = 0, len(nums)
        while left < right:
            mid = (left + right) >> 1
            if nums[mid] >= target:
                right = mid
            else:
                left = mid + 1
        return left
```

#### Java

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= target) {
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
    int searchInsert(vector<int>& nums, int target) {
        int left = 0, right = nums.size();
        while (left < right) {
            int mid = left + right >> 1;
            if (nums[mid] >= target)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
};
```

#### Go

```go
func searchInsert(nums []int, target int) int {
	left, right := 0, len(nums)
	for left < right {
		mid := (left + right) >> 1
		if nums[mid] >= target {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}
```

#### Rust

```rust
use std::cmp::Ordering;
impl Solution {
    pub fn search_insert(nums: Vec<i32>, target: i32) -> i32 {
        let mut left = 0;
        let mut right = nums.len();
        while left < right {
            let mid = left + (right - left) / 2;
            match nums[mid].cmp(&target) {
                Ordering::Less => {
                    left = mid + 1;
                }
                Ordering::Greater => {
                    right = mid;
                }
                Ordering::Equal => {
                    return mid as i32;
                }
            }
        }
        left as i32
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
var searchInsert = function (nums, target) {
    let left = 0;
    let right = nums.length;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[mid] >= target) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Binary Search (Built-in Function)

We can also directly use the built-in function for binary search.

The time complexity is $O(\log n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        return bisect_left(nums, target)
```

#### Java

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int i = Arrays.binarySearch(nums, target);
        return i < 0 ? -i - 1 : i;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int searchInsert(vector<int>& nums, int target) {
        return lower_bound(nums.begin(), nums.end(), target) - nums.begin();
    }
};
```

#### Go

```go
func searchInsert(nums []int, target int) int {
	return sort.SearchInts(nums, target)
}
```

#### PHP

```php
class Solution {
    /**
     * @param integer[] $nums
     * @param integer $target
     * @return integer
     */

    function searchInsert($nums, $target) {
        $key = array_search($target, $nums);
        if ($key !== false) {
            return $key;
        }

        $nums[] = $target;
        sort($nums);
        return array_search($target, $nums);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
