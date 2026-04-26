---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3904.Smallest%20Stable%20Index%20II/README_EN.md
rating: 1351
source: Weekly Contest 498 Q2
tags:
    - Array
---

<!-- problem:start -->

# [3904. Smallest Stable Index II](https://leetcode.com/problems/smallest-stable-index-ii)

[中文文档](/solution/3900-3999/3904.Smallest%20Stable%20Index%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and an integer <code>k</code>.</p>

<p>For each index <code>i</code>, define its <strong>instability score</strong> as <code>max(nums[0..i]) - min(nums[i..n - 1])</code>.</p>

<p>In other words:</p>

<ul>
	<li><code>max(nums[0..i])</code> is the <strong>largest</strong> value among the elements from index 0 to index <code>i</code>.</li>
	<li><code>min(nums[i..n - 1])</code> is the <strong>smallest</strong> value among the elements from index <code>i</code> to index <code>n - 1</code>.</li>
</ul>

<p>An index <code>i</code> is called <strong>stable</strong> if its instability score is <strong>less than or equal to</strong> <code>k</code>.</p>

<p>Return the <strong>smallest</strong> stable index. If no such index exists, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,0,1,4], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>At index 0: The maximum in <code>[5]</code> is 5, and the minimum in <code>[5, 0, 1, 4]</code> is 0, so the instability score is <code>5 - 0 = 5</code>.</li>
	<li>At index 1: The maximum in <code>[5, 0]</code> is 5, and the minimum in <code>[0, 1, 4]</code> is 0, so the instability score is <code>5 - 0 = 5</code>.</li>
	<li>At index 2: The maximum in <code>[5, 0, 1]</code> is 5, and the minimum in <code>[1, 4]</code> is 1, so the instability score is <code>5 - 1 = 4</code>.</li>
	<li>At index 3: The maximum in <code>[5, 0, 1, 4]</code> is 5, and the minimum in <code>[4]</code> is 4, so the instability score is <code>5 - 4 = 1</code>.</li>
	<li>This is the first index with an instability score less than or equal to <code>k = 3</code>. Thus, the answer is 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,2,1], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>At index 0, the instability score is <code>3 - 1 = 2</code>.</li>
	<li>At index 1, the instability score is <code>3 - 1 = 2</code>.</li>
	<li>At index 2, the instability score is <code>3 - 1 = 2</code>.</li>
	<li>None of these values is less than or equal to <code>k = 1</code>, so the answer is -1.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>At index 0, the instability score is <code>0 - 0 = 0</code>, which is less than or equal to <code>k = 0</code>. Therefore, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Preprocessing + Enumeration

First, we preprocess an array $\textit{right}$, where $\textit{right}[i]$ represents the minimum value among the elements in $nums$ from index $i$ to index $n - 1$. We can compute the $\textit{right}$ array by traversing $nums$ from back to front.

Next, we traverse the $nums$ array from front to back, maintaining a variable $\textit{left}$, which represents the maximum value among the elements in $nums$ from index $0$ to index $i$. For each index $i$, we calculate the instability score as $\textit{left} - \textit{right}[i]$. If the instability score is less than or equal to $k$, we return index $i$. If no such index is found after the traversal, we return $-1$.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the $nums$ array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def firstStableIndex(self, nums: list[int], k: int) -> int:
        n = len(nums)
        right = [nums[-1]] * n
        for i in range(n - 2, -1, -1):
            right[i] = min(right[i + 1], nums[i])
        left = 0
        for i, x in enumerate(nums):
            left = max(left, x)
            if left - right[i] <= k:
                return i
        return -1
```

#### Java

```java
class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.min(right[i + 1], nums[i]);
        }

        int left = 0;
        for (int i = 0; i < n; i++) {
            left = Math.max(left, nums[i]);
            if (left - right[i] <= k) {
                return i;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int firstStableIndex(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> right(n);
        right[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; --i) {
            right[i] = min(right[i + 1], nums[i]);
        }

        int left = 0;
        for (int i = 0; i < n; ++i) {
            left = max(left, nums[i]);
            if (left - right[i] <= k) {
                return i;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func firstStableIndex(nums []int, k int) int {
	n := len(nums)
	right := make([]int, n)
	right[n-1] = nums[n-1]

	for i := n - 2; i >= 0; i-- {
		right[i] = min(right[i+1], nums[i])
	}

	left := 0
	for i, x := range nums {
		left = max(left, x)
		if left-right[i] <= k {
			return i
		}
	}
	return -1
}
```

#### TypeScript

```ts
function firstStableIndex(nums: number[], k: number): number {
    const n = nums.length;
    const right = new Array<number>(n);
    right[n - 1] = nums[n - 1];

    for (let i = n - 2; i >= 0; i--) {
        right[i] = Math.min(right[i + 1], nums[i]);
    }

    let left = 0;
    for (let i = 0; i < n; i++) {
        left = Math.max(left, nums[i]);
        if (left - right[i] <= k) {
            return i;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
