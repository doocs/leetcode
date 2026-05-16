---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3925.Concatenate%20Array%20With%20Reverse/README_EN.md
rating: 1176
source: Weekly Contest 501 Q1
---

<!-- problem:start -->

# [3925. Concatenate Array With Reverse](https://leetcode.com/problems/concatenate-array-with-reverse)

[中文文档](/solution/3900-3999/3925.Concatenate%20Array%20With%20Reverse/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>.</p>

<p>Construct a new array <code>ans</code> of length <code>2 * n</code> such that the first <code>n</code> elements are the same as <code>nums</code>, and the next <code>n</code> elements are the elements of <code>nums</code> in reverse order.</p>

<p>Formally, for <code>0 &lt;= i &lt;= n - 1</code>:</p>

<ul>
	<li><code>ans[i] = nums[i]</code></li>
	<li><code>ans[i + n] = nums[n - i - 1]</code></li>
</ul>

<p>Return an integer array <code>ans</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,3,3,2,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>The first <code>n</code> elements of <code>ans</code> are the same as <code>nums</code>.</p>

<p>For the next <code>n = 3</code> elements, each element is taken from <code>nums</code> in reverse order:</p>

<ul>
	<li><code>ans[3] = nums[2] = 3</code></li>
	<li><code>ans[4] = nums[1] = 2</code></li>
	<li><code>ans[5] = nums[0] = 1</code></li>
</ul>

<p>Thus, <code>ans = [1, 2, 3, 3, 2, 1]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>The array remains the same when reversed. Thus, <code>ans = [1, 1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We create an array $\textit{ans}$ of length $2 \times n$. The first $n$ elements are the same as $\textit{nums}$, and the next $n$ elements are $\textit{nums}$ in reverse order.

Specifically, for $0 \leq i \leq n - 1$, we set $\textit{ans}[i] = \textit{nums}[i]$ and $\textit{ans}[i + n] = \textit{nums}[n - i - 1]$.

Finally, return the array $\textit{ans}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def concatWithReverse(self, nums: list[int]) -> list[int]:
        n = len(nums)
        ans = [0] * (2 * n)
        for i, x in enumerate(nums):
            ans[i] = x
            ans[i + n] = nums[n - i - 1]
        return ans
```

#### Java

```java
class Solution {
    public int[] concatWithReverse(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];
        for (int i = 0; i < n; ++i) {
            ans[i] = nums[i];
            ans[i + n] = nums[n - i - 1];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> concatWithReverse(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(2 * n);
        for (int i = 0; i < n; ++i) {
            ans[i] = nums[i];
            ans[i + n] = nums[n - i - 1];
        }
        return ans;
    }
};
```

#### Go

```go
func concatWithReverse(nums []int) []int {
	n := len(nums)
	ans := make([]int, 2*n)
	for i, x := range nums {
		ans[i] = x
		ans[i+n] = nums[n-i-1]
	}
	return ans
}
```

#### TypeScript

```ts
function concatWithReverse(nums: number[]): number[] {
    const n = nums.length;
    const ans: number[] = new Array(2 * n);
    for (let i = 0; i < n; ++i) {
        ans[i] = nums[i];
        ans[i + n] = nums[n - i - 1];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
