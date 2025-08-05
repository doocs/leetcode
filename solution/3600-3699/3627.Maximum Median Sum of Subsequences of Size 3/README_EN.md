---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3627.Maximum%20Median%20Sum%20of%20Subsequences%20of%20Size%203/README_EN.md
rating: 1262
source: Weekly Contest 460 Q1
---

<!-- problem:start -->

# [3627. Maximum Median Sum of Subsequences of Size 3](https://leetcode.com/problems/maximum-median-sum-of-subsequences-of-size-3)

[中文文档](/solution/3600-3699/3627.Maximum%20Median%20Sum%20of%20Subsequences%20of%20Size%203/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> with a length divisible by 3.</p>

<p>You want to make the array empty in steps. In each step, you can select any three elements from the array, compute their <strong>median</strong>, and remove the selected elements from the array.</p>

<p>The <strong>median</strong> of an odd-length sequence is defined as the middle element of the sequence when it is sorted in non-decreasing order.</p>

<p>Return the <strong>maximum</strong> possible sum of the medians computed from the selected elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,3,2,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>In the first step, select elements at indices 2, 4, and 5, which have a median 3. After removing these elements, <code>nums</code> becomes <code>[2, 1, 2]</code>.</li>
	<li>In the second step, select elements at indices 0, 1, and 2, which have a median 2. After removing these elements, <code>nums</code> becomes empty.</li>
</ul>

<p>Hence, the sum of the medians is <code>3 + 2 = 5</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,10,10,10,10]</span></p>

<p><strong>Output:</strong> <span class="example-io">20</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>In the first step, select elements at indices 0, 2, and 3, which have a median 10. After removing these elements, <code>nums</code> becomes <code>[1, 10, 10]</code>.</li>
	<li>In the second step, select elements at indices 0, 1, and 2, which have a median 10. After removing these elements, <code>nums</code> becomes empty.</li>
</ul>

<p>Hence, the sum of the medians is <code>10 + 10 = 20</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>nums.length % 3 == 0</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Sorting

To maximize the sum of medians, we need to select larger elements as medians whenever possible. Since each operation can only select three elements, we can sort the array and then start from index $n / 3$, selecting every other element (skipping one) until the end of the array. This ensures that we select the largest possible medians.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumMedianSum(self, nums: List[int]) -> int:
        nums.sort()
        return sum(nums[len(nums) // 3 :: 2])
```

#### Java

```java
class Solution {
    public long maximumMedianSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long ans = 0;
        for (int i = n / 3; i < n; i += 2) {
            ans += nums[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumMedianSum(vector<int>& nums) {
        ranges::sort(nums);
        int n = nums.size();
        long long ans = 0;
        for (int i = n / 3; i < n; i += 2) {
            ans += nums[i];
        }
        return ans;
    }
};
```

#### Go

```go
func maximumMedianSum(nums []int) (ans int64) {
	sort.Ints(nums)
	n := len(nums)
	for i := n / 3; i < n; i += 2 {
		ans += int64(nums[i])
	}
	return
}
```

#### TypeScript

```ts
function maximumMedianSum(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = 0;
    for (let i = n / 3; i < n; i += 2) {
        ans += nums[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
