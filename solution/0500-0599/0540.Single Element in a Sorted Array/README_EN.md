---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0540.Single%20Element%20in%20a%20Sorted%20Array/README_EN.md
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [540. Single Element in a Sorted Array](https://leetcode.com/problems/single-element-in-a-sorted-array)

[中文文档](/solution/0500-0599/0540.Single%20Element%20in%20a%20Sorted%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.</p>

<p>Return <em>the single element that appears only once</em>.</p>

<p>Your solution must run in <code>O(log n)</code> time and <code>O(1)</code> space.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,1,2,3,3,4,4,8,8]
<strong>Output:</strong> 2
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [3,3,7,7,10,11,11]
<strong>Output:</strong> 10
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

The given array $\textit{nums}$ is sorted, and we need to find the element that appears only once in $\textit{O}(\log n)$ time. Therefore, we consider using binary search to solve this problem.

We define the left boundary of the binary search as $\textit{l} = 0$ and the right boundary as $\textit{r} = n - 1$, where $n$ is the length of the array.

In each step, we take the middle position $\textit{mid} = (l + r) / 2$. If the index $\textit{mid}$ is even, we should compare $\textit{nums}[\textit{mid}]$ with $\textit{nums}[\textit{mid} + 1]$. If the index $\textit{mid}$ is odd, we should compare $\textit{nums}[\textit{mid}]$ with $\textit{nums}[\textit{mid} - 1]$. Therefore, we can uniformly compare $\textit{nums}[\textit{mid}]$ with $\textit{nums}[\textit{mid} \oplus 1]$, where $\oplus$ denotes the XOR operation.

If $\textit{nums}[\textit{mid}] \neq \textit{nums}[\textit{mid} \oplus 1]$, then the answer is in $[\textit{l}, \textit{mid}]$, so we set $\textit{r} = \textit{mid}$. If $\textit{nums}[\textit{mid}] = \textit{nums}[\textit{mid} \oplus 1]$, then the answer is in $[\textit{mid} + 1, \textit{r}]$, so we set $\textit{l} = \textit{mid} + 1$. We continue the binary search until $\textit{l} = \textit{r}$, at which point $\textit{nums}[\textit{l}]$ is the element that appears only once.

The time complexity is $\textit{O}(\log n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $\textit{O}(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def singleNonDuplicate(self, nums: List[int]) -> int:
        l, r = 0, len(nums) - 1
        while l < r:
            mid = (l + r) >> 1
            if nums[mid] != nums[mid ^ 1]:
                r = mid
            else:
                l = mid + 1
        return nums[l]
```

#### Java

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] != nums[mid ^ 1]) {
                r = mid;
            } else {
                l = mid + 1;
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
    int singleNonDuplicate(vector<int>& nums) {
        int l = 0, r = nums.size() - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] != nums[mid ^ 1]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }
};
```

#### Go

```go
func singleNonDuplicate(nums []int) int {
	l, r := 0, len(nums)-1
	for l < r {
		mid := (l + r) >> 1
		if nums[mid] != nums[mid^1] {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return nums[l]
}
```

#### TypeScript

```ts
function singleNonDuplicate(nums: number[]): number {
    let [l, r] = [0, nums.length - 1];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] !== nums[mid ^ 1]) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return nums[l];
}
```

#### Rust

```rust
impl Solution {
    pub fn single_non_duplicate(nums: Vec<i32>) -> i32 {
        let mut l = 0;
        let mut r = nums.len() - 1;
        while l < r {
            let mid = (l + r) >> 1;
            if nums[mid] != nums[mid ^ 1] {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        nums[l]
    }
}
```

#### C

```c
int singleNonDuplicate(int* nums, int numsSize) {
    int l = 0, r = numsSize - 1;
    while (l < r) {
        int mid = (l + r) >> 1;
        if (nums[mid] != nums[mid ^ 1]) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return nums[l];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
