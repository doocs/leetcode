---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3940.Limit%20Occurrences%20in%20Sorted%20Array/README_EN.md
rating: 1201
source: Weekly Contest 503 Q1
tags:
    - Array
    - Two Pointers
---

<!-- problem:start -->

# [3940. Limit Occurrences in Sorted Array](https://leetcode.com/problems/limit-occurrences-in-sorted-array)

[中文文档](/solution/3900-3999/3940.Limit%20Occurrences%20in%20Sorted%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>sorted</strong> integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>Return an array such that each <strong>distinct</strong> element appears <strong>at most</strong> <code>k</code> times, while preserving the relative order of the elements in <code>nums</code>.</p>

<p>Note: If a distinct element appears <strong>at least</strong> <code>k</code> times, then it must appear <strong>exactly</strong> <code>k</code> times in the resulting array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1,2,2,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1,2,2,3]</span></p>

<p><strong>Explanation:</strong></p>

<p>Each element can appear at most 2 times.</p>

<ul>
	<li>The element 1 appears 3 times, so only 2 occurrences are kept.</li>
	<li>The element 2 appears 2 times, so both occurrences are kept.</li>
	<li>The element 3 appears 1 time, so it is kept.</li>
</ul>

<p>Thus, the resulting array is <code>[1, 1, 2, 2, 3]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,3]</span></p>

<p><strong>Explanation:</strong></p>

<p>All elements are distinct and already appear at most once, so the array remains unchanged.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>nums</code> is sorted in non-decreasing order.</li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow-up:</strong></p>

<ul>
	<li>Can you solve this in-place using O(1) extra space?</li>
	<li>Note that the space used for returning or resizing the result does not count toward the space complexity mentioned above, as some languages do not support in-place resizing.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We define two pointers, $l$ and $r$, where $l$ is the write position and $r$ is the current read position. We also use a counter $cnt$ to record how many times the current value has appeared. Initially, both $l$ and $cnt$ are set to 1.

Then we traverse the array starting from $r = 1$:

1. If $nums[r] \ne nums[r - 1]$, we meet a new value, so reset $cnt$ to 1.
2. If $nums[r] = nums[r - 1]$, it is a duplicate, so increment $cnt$ by 1.

If $cnt \le k$, the occurrence limit is not exceeded, so we keep this element by writing $nums[r]$ to $nums[l]$, then move $l$ one step to the right.

Finally, return the first $l$ elements, i.e., $nums[:l]$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$, since only constant extra space is used.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def limitOccurrences(self, nums: list[int], k: int) -> list[int]:
        n = len(nums)
        cnt = l = 1
        for r in range(1, n):
            if nums[r] != nums[r - 1]:
                cnt = 1
            else:
                cnt += 1
            if cnt <= k:
                nums[l] = nums[r]
                l += 1
        return nums[:l]
```

#### Java

```java
class Solution {
    public int[] limitOccurrences(int[] nums, int k) {
        int n = nums.length;
        int cnt = 1, l = 1;

        for (int r = 1; r < n; r++) {
            if (nums[r] != nums[r - 1]) {
                cnt = 1;
            } else {
                cnt++;
            }

            if (cnt <= k) {
                nums[l] = nums[r];
                l++;
            }
        }

        return Arrays.copyOf(nums, l);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> limitOccurrences(vector<int>& nums, int k) {
        int n = nums.size();
        int cnt = 1, l = 1;

        for (int r = 1; r < n; r++) {
            if (nums[r] != nums[r - 1]) {
                cnt = 1;
            } else {
                cnt++;
            }

            if (cnt <= k) {
                nums[l] = nums[r];
                l++;
            }
        }

        nums.resize(l);
        return nums;
    }
};
```

#### Go

```go
func limitOccurrences(nums []int, k int) []int {
	n := len(nums)
	cnt, l := 1, 1

	for r := 1; r < n; r++ {
		if nums[r] != nums[r-1] {
			cnt = 1
		} else {
			cnt++
		}

		if cnt <= k {
			nums[l] = nums[r]
			l++
		}
	}

	return nums[:l]
}
```

#### TypeScript

```ts
function limitOccurrences(nums: number[], k: number): number[] {
    const n = nums.length;
    let cnt = 1;
    let l = 1;

    for (let r = 1; r < n; r++) {
        if (nums[r] !== nums[r - 1]) {
            cnt = 1;
        } else {
            cnt++;
        }

        if (cnt <= k) {
            nums[l] = nums[r];
            l++;
        }
    }

    return nums.slice(0, l);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
