---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3774.Absolute%20Difference%20Between%20Maximum%20and%20Minimum%20K%20Elements/README_EN.md
rating: 1206
source: Weekly Contest 480 Q1
tags:
    - Array
    - Sorting
---

<!-- problem:start -->

# [3774. Absolute Difference Between Maximum and Minimum K Elements](https://leetcode.com/problems/absolute-difference-between-maximum-and-minimum-k-elements)

[中文文档](/solution/3700-3799/3774.Absolute%20Difference%20Between%20Maximum%20and%20Minimum%20K%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>Find the absolute difference between:</p>

<ul>
	<li>the <strong>sum</strong> of the <code>k</code> <strong>largest</strong> elements in the array; and</li>
	<li>the <strong>sum</strong> of the <code>k</code> <strong>smallest</strong> elements in the array.</li>
</ul>

<p>Return an integer denoting this difference.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,2,2,4], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The <code>k = 2</code> largest elements are 4 and 5. Their sum is <code>4 + 5 = 9</code>.</li>
	<li>The <code>k = 2</code> smallest elements are 2 and 2. Their sum is <code>2 + 2 = 4</code>.</li>
	<li>The absolute difference is <code>abs(9 - 4) = 5</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [100], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The largest element is 100.</li>
	<li>The smallest element is 100.</li>
	<li>The absolute difference is <code>abs(100 - 100) = 0</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

We first sort the array $\textit{nums}$. Then we calculate the sum of the first $k$ elements and the sum of the last $k$ elements in the array, and finally return the difference between them.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def absDifference(self, nums: List[int], k: int) -> int:
        nums.sort()
        return sum(nums[-k:]) - sum(nums[:k])
```

#### Java

```java
class Solution {
    public int absDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < k; ++i) {
            ans += nums[n - i - 1] - nums[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int absDifference(vector<int>& nums, int k) {
        ranges::sort(nums);
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < k; ++i) {
            ans += nums[n - i - 1] - nums[i];
        }
        return ans;
    }
};
```

#### Go

```go
func absDifference(nums []int, k int) (ans int) {
	slices.Sort(nums)
	for i := 0; i < k; i++ {
		ans += nums[len(nums)-i-1] - nums[i]
	}
	return
}
```

#### TypeScript

```ts
function absDifference(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0; i < k; ++i) {
        ans += nums.at(-i - 1)! - nums[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
