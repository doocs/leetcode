---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0081.Search%20in%20Rotated%20Sorted%20Array%20II/README_EN.md
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [81. Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii)

[中文文档](/solution/0000-0099/0081.Search%20in%20Rotated%20Sorted%20Array%20II/README.md)

## Description

<!-- description:start -->

<p>There is an integer array <code>nums</code> sorted in non-decreasing order (not necessarily with <strong>distinct</strong> values).</p>

<p>Before being passed to your function, <code>nums</code> is <strong>rotated</strong> at an unknown pivot index <code>k</code> (<code>0 &lt;= k &lt; nums.length</code>) such that the resulting array is <code>[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]</code> (<strong>0-indexed</strong>). For example, <code>[0,1,2,4,4,4,5,6,6,7]</code> might be rotated at pivot index <code>5</code> and become <code>[4,5,6,6,7,0,1,2,4,4]</code>.</p>

<p>Given the array <code>nums</code> <strong>after</strong> the rotation and an integer <code>target</code>, return <code>true</code><em> if </em><code>target</code><em> is in </em><code>nums</code><em>, or </em><code>false</code><em> if it is not in </em><code>nums</code><em>.</em></p>

<p>You must decrease the overall operation steps as much as possible.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [2,5,6,0,0,1,2], target = 0
<strong>Output:</strong> true
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [2,5,6,0,0,1,2], target = 3
<strong>Output:</strong> false
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> is guaranteed to be rotated at some pivot.</li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> This problem is similar to&nbsp;<a href="/problems/search-in-rotated-sorted-array/description/" target="_blank">Search in Rotated Sorted Array</a>, but&nbsp;<code>nums</code> may contain <strong>duplicates</strong>. Would this affect the runtime complexity? How and why?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

We define the left boundary of the binary search as $l = 0$ and the right boundary as $r = n - 1$, where $n$ is the length of the array.

Each time during the binary search, we get the current midpoint $\textit{mid} = (l + r) / 2$.

-   If $\textit{nums}[\textit{mid}] > \textit{nums}[r]$, it means $[l, \textit{mid}]$ is ordered. If $\textit{nums}[l] \le \textit{target} \le \textit{nums}[\textit{mid}]$, it means $\textit{target}$ is in $[l, \textit{mid}]$. Otherwise, $\textit{target}$ is in $[\textit{mid} + 1, r]$.
-   If $\textit{nums}[\textit{mid}] < \textit{nums}[r]$, it means $[\textit{mid} + 1, r]$ is ordered. If $\textit{nums}[\textit{mid}] < \textit{target} \le \textit{nums}[r]$, it means $\textit{target}$ is in $[\textit{mid} + 1, r]$. Otherwise, $\textit{target}$ is in $[l, \textit{mid}]$.
-   If $\textit{nums}[\textit{mid}] = \textit{nums}[r]$, it means the elements $\textit{nums}[\textit{mid}]$ and $\textit{nums}[r]$ are equal. In this case, we cannot determine which interval $\textit{target}$ is in, so we can only decrease $r$ by $1$.

After the binary search, if $\textit{nums}[l] = \textit{target}$, it means the target value $\textit{target}$ exists in the array. Otherwise, it does not exist.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

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

#### Rust

```rust
impl Solution {
    pub fn search(nums: Vec<i32>, target: i32) -> bool {
        let (mut l, mut r) = (0, nums.len() - 1);
        while l < r {
            let mid = (l + r) >> 1;
            if nums[mid] > nums[r] {
                if nums[l] <= target && target <= nums[mid] {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            } else if nums[mid] < nums[r] {
                if nums[mid] < target && target <= nums[r] {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            } else {
                r -= 1;
            }
        }
        nums[l] == target
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {boolean}
 */
var search = function (nums, target) {
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
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
