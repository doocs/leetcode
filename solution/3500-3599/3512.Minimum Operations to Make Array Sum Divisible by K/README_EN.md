---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3512.Minimum%20Operations%20to%20Make%20Array%20Sum%20Divisible%20by%20K/README_EN.md
rating: 1228
source: Biweekly Contest 154 Q1
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [3512. Minimum Operations to Make Array Sum Divisible by K](https://leetcode.com/problems/minimum-operations-to-make-array-sum-divisible-by-k)

[中文文档](/solution/3500-3599/3512.Minimum%20Operations%20to%20Make%20Array%20Sum%20Divisible%20by%20K/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>. You can perform the following operation any number of times:</p>

<ul>
	<li>Select an index <code>i</code> and replace <code>nums[i]</code> with <code>nums[i] - 1</code>.</li>
</ul>

<p>Return the <strong>minimum</strong> number of operations required to make the sum of the array divisible by <code>k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,9,7], k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Perform 4 operations on <code>nums[1] = 9</code>. Now, <code>nums = [3, 5, 7]</code>.</li>
	<li>The sum is 15, which is divisible by 5.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,1,3], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The sum is 8, which is already divisible by 4. Hence, no operations are needed.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,2], k = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Perform 3 operations on <code>nums[0] = 3</code> and 2 operations on <code>nums[1] = 2</code>. Now, <code>nums = [0, 0]</code>.</li>
	<li>The sum is 0, which is divisible by 6.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sum and Modulo

The problem essentially asks for the result of the sum of the array elements modulo $k$. Therefore, we only need to iterate through the array, calculate the sum of all elements, and then take the modulo $k$. Finally, return this result.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int], k: int) -> int:
        return sum(nums) % k
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums, int k) {
        return Arrays.stream(nums).sum() % k;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int k) {
        return reduce(nums.begin(), nums.end(), 0) % k;
    }
};
```

#### Go

```go
func minOperations(nums []int, k int) (ans int) {
	for _, x := range nums {
		ans = (ans + x) % k
	}
	return
}
```

#### TypeScript

```ts
function minOperations(nums: number[], k: number): number {
    return nums.reduce((acc, x) => acc + x, 0) % k;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
