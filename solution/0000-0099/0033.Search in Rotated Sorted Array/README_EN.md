# [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array)

[中文文档](/solution/0000-0099/0033.Search%20in%20Rotated%20Sorted%20Array/README.md)

## Description

<p>There is an integer array <code>nums</code> sorted in ascending order (with <strong>distinct</strong> values).</p>

<p>Prior to being passed to your function, <code>nums</code> is <strong>possibly rotated</strong> at an unknown pivot index <code>k</code> (<code>1 &lt;= k &lt; nums.length</code>) such that the resulting array is <code>[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]</code> (<strong>0-indexed</strong>). For example, <code>[0,1,2,4,5,6,7]</code> might be rotated at pivot index <code>3</code> and become <code>[4,5,6,7,0,1,2]</code>.</p>

<p>Given the array <code>nums</code> <strong>after</strong> the possible rotation and an integer <code>target</code>, return <em>the index of </em><code>target</code><em> if it is in </em><code>nums</code><em>, or </em><code>-1</code><em> if it is not in </em><code>nums</code>.</p>

<p>You must write an algorithm with <code>O(log n)</code> runtime complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [4,5,6,7,0,1,2], target = 0
<strong>Output:</strong> 4
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [4,5,6,7,0,1,2], target = 3
<strong>Output:</strong> -1
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> nums = [1], target = 0
<strong>Output:</strong> -1
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li>All values of <code>nums</code> are <strong>unique</strong>.</li>
	<li><code>nums</code> is an ascending array that is possibly rotated.</li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

**Solution 1: Binary Search**

We use binary search to divide the array into two parts, $[left,.. mid]$ and $[mid + 1,.. right]$. At this point, we can find that one part must be sorted.

Therefore, we can determine whether $target$ is in this part based on the sorted part:

-   If the elements in the range $[0,.. mid]$ form a sorted array:
    -   If $nums[0] \leq target \leq nums[mid]$, then our search range can be narrowed down to $[left,.. mid]$;
    -   Otherwise, search in $[mid + 1,.. right]$;
-   If the elements in the range $[mid + 1, n - 1]$ form a sorted array:
    -   If $nums[mid] \lt target \leq nums[n - 1]$, then our search range can be narrowed down to $[mid + 1,.. right]$;
    -   Otherwise, search in $[left,.. mid]$.

The termination condition for binary search is $left \geq right$. If at the end we find that $nums[left]$ is not equal to $target$, it means that there is no element with a value of $target$ in the array, and we return $-1$. Otherwise, we return the index $left$.

The time complexity is $O(\log n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def search(self, nums: List[int], target: int) -> int:
        n = len(nums)
        left, right = 0, n - 1
        while left < right:
            mid = (left + right) >> 1
            if nums[0] <= nums[mid]:
                if nums[0] <= target <= nums[mid]:
                    right = mid
                else:
                    left = mid + 1
            else:
                if nums[mid] < target <= nums[n - 1]:
                    left = mid + 1
                else:
                    right = mid
        return left if nums[left] == target else -1
```

### **Java**

```java
class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target <= nums[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }
        return nums[left] == target ? left : -1;
    }
}
```

### **C++**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```cpp
class Solution {
public:
    int search(vector<int>& nums, int target) {
        int n = nums.size();
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target <= nums[mid])
                    right = mid;
                else
                    left = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[n - 1])
                    left = mid + 1;
                else
                    right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }
};
```

### **Go**

```go
func search(nums []int, target int) int {
	n := len(nums)
	left, right := 0, n-1
	for left < right {
		mid := (left + right) >> 1
		if nums[0] <= nums[mid] {
			if nums[0] <= target && target <= nums[mid] {
				right = mid
			} else {
				left = mid + 1
			}
		} else {
			if nums[mid] < target && target <= nums[n-1] {
				left = mid + 1
			} else {
				right = mid
			}
		}
	}
	if nums[left] == target {
		return left
	}
	return -1
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var search = function (nums, target) {
    const n = nums.length;
    let left = 0,
        right = n - 1;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[0] <= nums[mid]) {
            if (nums[0] <= target && target <= nums[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        } else {
            if (nums[mid] < target && target <= nums[n - 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
    }
    return nums[left] == target ? left : -1;
};
```

### **Rust**

```rust
impl Solution {
    pub fn search(nums: Vec<i32>, target: i32) -> i32 {
        let mut l = 0;
        let mut r = nums.len() - 1;
        while l <= r {
            let mid = (l + r) >> 1;
            if nums[mid] == target {
                return mid as i32;
            }

            if nums[l] <= nums[mid] {
                if target < nums[mid] && target >= nums[l] {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if target > nums[mid] && target <= nums[r] {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        -1
    }
}
```

### **TypeScript**

```ts
function search(nums: number[], target: number): number {
    const n = nums.length;
    let left = 0,
        right = n - 1;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[0] <= nums[mid]) {
            if (nums[0] <= target && target <= nums[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        } else {
            if (nums[mid] < target && target <= nums[n - 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
    }
    return nums[left] == target ? left : -1;
}
```

### **...**

```

```

<!-- tabs:end -->
