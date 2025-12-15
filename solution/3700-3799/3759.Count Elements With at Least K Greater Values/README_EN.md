---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3759.Count%20Elements%20With%20at%20Least%20K%20Greater%20Values/README_EN.md
rating: 1372
source: Weekly Contest 478 Q1
tags:
    - Array
    - Binary Search
    - Divide and Conquer
    - Quickselect
    - Sorting
---

<!-- problem:start -->

# [3759. Count Elements With at Least K Greater Values](https://leetcode.com/problems/count-elements-with-at-least-k-greater-values)

[中文文档](/solution/3700-3799/3759.Count%20Elements%20With%20at%20Least%20K%20Greater%20Values/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and an integer <code>k</code>.</p>

<p>An element in <code>nums</code> is said to be <strong>qualified</strong> if there exist <strong>at least</strong> <code>k</code> elements in the array that are <strong>strictly greater</strong> than it.</p>

<p>Return an integer denoting the total number of qualified elements in <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,2], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The elements 1 and 2 each have at least <code>k = 1</code> element greater than themselves.<br />
​​​​​​​No element is greater than 3. Therefore, the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,5,5], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>Since all elements are equal to 5, no element is greater than the other. Therefore, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt; n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

If $k = 0$, then all elements in the array are qualified elements, and we can directly return the length of the array.

Otherwise, we sort the array, and let $n$ be the length of the sorted array. For each index $i$ satisfying $0 \leq i < n - k$, if the element at index $i$ is strictly less than the element at index $n - k$, then it is a qualified element. We just need to count the number of such elements and return it.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countElements(self, nums: List[int], k: int) -> int:
        n = len(nums)
        if k == 0:
            return n
        nums.sort()
        return sum(nums[n - k] > nums[i] for i in range(n - k))
```

#### Java

```java
class Solution {
    public int countElements(int[] nums, int k) {
        int n = nums.length;
        if (k == 0) {
            return n;
        }
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n - k; ++i) {
            if (nums[n - k] > nums[i]) {
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countElements(vector<int>& nums, int k) {
        int n = nums.size();
        if (k == 0) {
            return n;
        }
        ranges::sort(nums);
        int ans = 0;
        for (int i = 0; i < n - k; ++i) {
            if (nums[n - k] > nums[i]) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countElements(nums []int, k int) int {
	n := len(nums)
	if k == 0 {
		return n
	}
	sort.Ints(nums)
	ans := 0
	for i := 0; i < n-k; i++ {
		if nums[n-k] > nums[i] {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countElements(nums: number[], k: number): number {
    const n = nums.length;
    if (k === 0) {
        return n;
    }
    nums.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0; i < n - k; ++i) {
        if (nums[n - k] > nums[i]) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
