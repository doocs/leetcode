---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3194.Minimum%20Average%20of%20Smallest%20and%20Largest%20Elements/README_EN.md
tags:
    - Array
    - Two Pointers
    - Sorting
---

<!-- problem:start -->

# [3194. Minimum Average of Smallest and Largest Elements](https://leetcode.com/problems/minimum-average-of-smallest-and-largest-elements)

[中文文档](/solution/3100-3199/3194.Minimum%20Average%20of%20Smallest%20and%20Largest%20Elements/README.md)

## Description

<!-- description:start -->

<p>You have an array of floating point numbers <code>averages</code> which is initially empty. You are given an array <code>nums</code> of <code>n</code> integers where <code>n</code> is even.</p>

<p>You repeat the following procedure <code>n / 2</code> times:</p>

<ul>
	<li>Remove the <strong>smallest</strong> element, <code>minElement</code>, and the <strong>largest</strong> element <code>maxElement</code>,&nbsp;from <code>nums</code>.</li>
	<li>Add <code>(minElement + maxElement) / 2</code> to <code>averages</code>.</li>
</ul>

<p>Return the <strong>minimum</strong> element in <code>averages</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,8,3,4,15,13,4,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">5.5</span></p>

<p><strong>Explanation:</strong></p>

<table>
	<tbody>
		<tr>
			<th>step</th>
			<th>nums</th>
			<th>averages</th>
		</tr>
		<tr>
			<td>0</td>
			<td>[7,8,3,4,15,13,4,1]</td>
			<td>[]</td>
		</tr>
		<tr>
			<td>1</td>
			<td>[7,8,3,4,13,4]</td>
			<td>[8]</td>
		</tr>
		<tr>
			<td>2</td>
			<td>[7,8,4,4]</td>
			<td>[8,8]</td>
		</tr>
		<tr>
			<td>3</td>
			<td>[7,4]</td>
			<td>[8,8,6]</td>
		</tr>
		<tr>
			<td>4</td>
			<td>[]</td>
			<td>[8,8,6,5.5]</td>
		</tr>
	</tbody>
</table>
The smallest element of averages, 5.5, is returned.</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,9,8,3,10,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">5.5</span></p>

<p><strong>Explanation:</strong></p>

<table>
	<tbody>
		<tr>
			<th>step</th>
			<th>nums</th>
			<th>averages</th>
		</tr>
		<tr>
			<td>0</td>
			<td><span class="example-io">[1,9,8,3,10,5]</span></td>
			<td>[]</td>
		</tr>
		<tr>
			<td>1</td>
			<td><span class="example-io">[9,8,3,5]</span></td>
			<td>[5.5]</td>
		</tr>
		<tr>
			<td>2</td>
			<td><span class="example-io">[8,5]</span></td>
			<td>[5.5,6]</td>
		</tr>
		<tr>
			<td>3</td>
			<td>[]</td>
			<td>[5.5,6,6.5]</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,7,8,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">5.0</span></p>

<p><strong>Explanation:</strong></p>

<table>
	<tbody>
		<tr>
			<th>step</th>
			<th>nums</th>
			<th>averages</th>
		</tr>
		<tr>
			<td>0</td>
			<td><span class="example-io">[1,2,3,7,8,9]</span></td>
			<td>[]</td>
		</tr>
		<tr>
			<td>1</td>
			<td><span class="example-io">[2,3,7,8]</span></td>
			<td>[5]</td>
		</tr>
		<tr>
			<td>2</td>
			<td><span class="example-io">[3,7]</span></td>
			<td>[5,5]</td>
		</tr>
		<tr>
			<td>3</td>
			<td><span class="example-io">[]</span></td>
			<td>[5,5,5]</td>
		</tr>
	</tbody>
</table>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 50</code></li>
	<li><code>n</code> is even.</li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

First, we sort the array $\text{nums}$. Then, we start taking elements from both ends of the array, calculating the sum of the two elements, and taking the minimum value. Finally, we return the minimum value divided by 2 as the answer.

The time complexity is $O(n \log n)$, and the space complexity is $O(\log n)$, where $n$ is the length of the array $\text{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumAverage(self, nums: List[int]) -> float:
        nums.sort()
        n = len(nums)
        return min(nums[i] + nums[n - i - 1] for i in range(n // 2)) / 2
```

#### Java

```java
class Solution {
    public double minimumAverage(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 1 << 30;
        for (int i = 0; i < n / 2; ++i) {
            ans = Math.min(ans, nums[i] + nums[n - i - 1]);
        }
        return ans / 2.0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    double minimumAverage(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 1 << 30, n = nums.size();
        for (int i = 0; i < n; ++i) {
            ans = min(ans, nums[i] + nums[n - i - 1]);
        }
        return ans / 2.0;
    }
};
```

#### Go

```go
func minimumAverage(nums []int) float64 {
	sort.Ints(nums)
	n := len(nums)
	ans := 1 << 30
	for i, x := range nums[:n/2] {
		ans = min(ans, x+nums[n-i-1])
	}
	return float64(ans) / 2
}
```

#### TypeScript

```ts
function minimumAverage(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = Infinity;
    for (let i = 0; i * 2 < n; ++i) {
        ans = Math.min(ans, nums[i] + nums[n - 1 - i]);
    }
    return ans / 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
