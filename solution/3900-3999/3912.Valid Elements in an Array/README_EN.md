---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3912.Valid%20Elements%20in%20an%20Array/README_EN.md
---

<!-- problem:start -->

# [3912. Valid Elements in an Array](https://leetcode.com/problems/valid-elements-in-an-array)

[中文文档](/solution/3900-3999/3912.Valid%20Elements%20in%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>An element <code>nums[i]</code> is considered <strong>valid</strong> if it satisfies <strong>at least</strong> one of the following conditions:</p>

<ul>
	<li>It is <strong>strictly greater</strong> than every element to its left.</li>
	<li>It is <strong>strictly greater</strong> than every element to its right.</li>
</ul>

<p>The first and last elements are always valid.</p>

<p>Return an array of all valid elements in the same order as they appear in <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,4,2,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,4,3,2]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>nums[0]</code> and <code>nums[5]</code> are always valid.</li>
	<li><code>nums[1]</code> and <code>nums[2]</code> are strictly greater than every element to their left.</li>
	<li><code>nums[4]</code> is strictly greater than every element to its right.</li>
	<li>Thus, the answer is <code>[1, 2, 4, 3, 2]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,5,5,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">[5,5]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The first and last elements are always valid.</li>
	<li>No other elements are strictly greater than all elements to their left or to their right.</li>
	<li>Thus, the answer is <code>[5, 5]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1]</span></p>

<p><strong>Explanation:</strong></p>

<p>Since there is only one element, it is always valid. Thus, the answer is <code>[1]</code>.</p>
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

### Solution 1: Preprocessing the Array

We can preprocess the array to compute the maximum value to the right of each element and store it in an array $\textit{right}$.

Then, we traverse the array from left to right, using a variable $\textit{left}$ to keep track of the maximum value to the left of the current element. For each element, if it satisfies any of the following conditions, we add it to the answer:

- It is strictly greater than $\textit{left}$.
- It is the last element of the array.
- It is strictly greater than $\textit{right}[i + 1]$.

During the traversal, we continuously update the value of $\textit{left}$.

After the traversal, we return the answer.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findValidElements(self, nums: list[int]) -> list[int]:
        n = len(nums)
        right = [nums[-1]] * n
        for i in range(n - 2, -1, -1):
            right[i] = max(right[i + 1], nums[i])
        left = 0
        ans = []
        for i, x in enumerate(nums):
            if x > left or i == n - 1 or x > right[i + 1]:
                ans.append(x)
            left = max(left, x)
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> findValidElements(int[] nums) {
        int n = nums.length;
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], nums[i]);
        }
        int left = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x > left || i == n - 1 || x > right[i + 1]) {
                ans.add(x);
            }
            left = Math.max(left, x);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findValidElements(vector<int>& nums) {
        int n = nums.size();
        vector<int> right(n);
        right[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = max(right[i + 1], nums[i]);
        }
        int left = 0;
        vector<int> ans;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x > left || i == n - 1 || x > right[i + 1]) {
                ans.push_back(x);
            }
            left = max(left, x);
        }
        return ans;
    }
};
```

#### Go

```go
func findValidElements(nums []int) []int {
	n := len(nums)
	right := make([]int, n)
	right[n-1] = nums[n-1]
	for i := n - 2; i >= 0; i-- {
		right[i] = max(right[i+1], nums[i])
	}
	left := 0
	ans := []int{}
	for i, x := range nums {
		if x > left || i == n-1 || x > right[i+1] {
			ans = append(ans, x)
		}
		left = max(left, x)
	}
	return ans
}
```

#### TypeScript

```ts
function findValidElements(nums: number[]): number[] {
    const n = nums.length;
    const right = new Array(n);
    right[n - 1] = nums[n - 1];
    for (let i = n - 2; i >= 0; i--) {
        right[i] = Math.max(right[i + 1], nums[i]);
    }
    let left = 0;
    const ans: number[] = [];
    for (let i = 0; i < n; i++) {
        const x = nums[i];
        if (x > left || i === n - 1 || x > right[i + 1]) {
            ans.push(x);
        }
        left = Math.max(left, x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
