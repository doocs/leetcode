---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3400.Maximum%20Number%20of%20Matching%20Indices%20After%20Right%20Shifts/README_EN.md
tags:
    - Array
    - Two Pointers
    - Simulation
---

<!-- problem:start -->

# [3400. Maximum Number of Matching Indices After Right Shifts 🔒](https://leetcode.com/problems/maximum-number-of-matching-indices-after-right-shifts)

[中文文档](/solution/3400-3499/3400.Maximum%20Number%20of%20Matching%20Indices%20After%20Right%20Shifts/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays, <code>nums1</code> and <code>nums2</code>, of the same length.</p>

<p>An index <code>i</code> is considered <strong>matching</strong> if <code>nums1[i] == nums2[i]</code>.</p>

<p>Return the <strong>maximum</strong> number of <strong>matching</strong> indices after performing any number of <strong>right shifts</strong> on <code>nums1</code>.</p>

<p>A <strong>right shift</strong> is defined as shifting the element at index <code>i</code> to index <code>(i + 1) % n</code>, for all indices.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [3,1,2,3,1,2], nums2 = [1,2,3,1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>If we right shift <code>nums1</code> 2 times, it becomes <code>[1, 2, 3, 1, 2, 3]</code>. Every index matches, so the output is 6.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [1,4,2,5,3,1], nums2 = [2,3,1,2,4,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>If we right shift <code>nums1</code> 3 times, it becomes <code>[5, 3, 1, 1, 4, 2]</code>. Indices 1, 2, and 4 match, so the output is 3.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 3000</code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can enumerate the number of right shifts $k$, where $0 \leq k < n$. For each $k$, we can calculate the number of matching indices between the array $\textit{nums1}$ after right shifting $k$ times and $\textit{nums2}$. The maximum value is taken as the answer.

The time complexity is $O(n^2)$, where $n$ is the length of the array $\textit{nums1}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumMatchingIndices(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums1)
        ans = 0
        for k in range(n):
            t = sum(nums1[(i + k) % n] == x for i, x in enumerate(nums2))
            ans = max(ans, t)
        return ans
```

#### Java

```java
class Solution {
    public int maximumMatchingIndices(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int ans = 0;
        for (int k = 0; k < n; ++k) {
            int t = 0;
            for (int i = 0; i < n; ++i) {
                if (nums1[(i + k) % n] == nums2[i]) {
                    ++t;
                }
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumMatchingIndices(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        int ans = 0;
        for (int k = 0; k < n; ++k) {
            int t = 0;
            for (int i = 0; i < n; ++i) {
                if (nums1[(i + k) % n] == nums2[i]) {
                    ++t;
                }
            }
            ans = max(ans, t);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumMatchingIndices(nums1 []int, nums2 []int) (ans int) {
	n := len(nums1)
	for k := range nums1 {
		t := 0
		for i, x := range nums2 {
			if nums1[(i+k)%n] == x {
				t++
			}
		}
		ans = max(ans, t)
	}
	return
}
```

#### TypeScript

```ts
function maximumMatchingIndices(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    let ans: number = 0;
    for (let k = 0; k < n; ++k) {
        let t: number = 0;
        for (let i = 0; i < n; ++i) {
            if (nums1[(i + k) % n] === nums2[i]) {
                ++t;
            }
        }
        ans = Math.max(ans, t);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
