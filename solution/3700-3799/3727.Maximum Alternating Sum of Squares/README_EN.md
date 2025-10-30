---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3727.Maximum%20Alternating%20Sum%20of%20Squares/README_EN.md
rating: 1454
source: Weekly Contest 473 Q2
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [3727. Maximum Alternating Sum of Squares](https://leetcode.com/problems/maximum-alternating-sum-of-squares)

[中文文档](/solution/3700-3799/3727.Maximum%20Alternating%20Sum%20of%20Squares/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>. You may <strong>rearrange the elements</strong> in any order.</p>

<p>The <strong>alternating score</strong> of an array <code>arr</code> is defined as:</p>

<ul>
	<li><code>score = arr[0]<sup>2</sup> - arr[1]<sup>2</sup> + arr[2]<sup>2</sup> - arr[3]<sup>2</sup> + ...</code></li>
</ul>

<p>Return an integer denoting the <strong>maximum possible alternating score</strong> of <code>nums</code> after rearranging its elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>A possible rearrangement for <code>nums</code> is <code>[2,1,3]</code>, which gives the maximum alternating score among all possible rearrangements.</p>

<p>The alternating score is calculated as:</p>

<p><code>score = 2<sup>2</sup> - 1<sup>2</sup> + 3<sup>2</sup> = 4 - 1 + 9 = 12</code></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,-1,2,-2,3,-3]</span></p>

<p><strong>Output:</strong> <span class="example-io">16</span></p>

<p><strong>Explanation:</strong></p>

<p>A possible rearrangement for <code>nums</code> is <code>[-3,-1,-2,1,3,2]</code>, which gives the maximum alternating score among all possible rearrangements.</p>

<p>The alternating score is calculated as:</p>

<p><code>score = (-3)<sup>2</sup> - (-1)<sup>2</sup> + (-2)<sup>2</sup> - (1)<sup>2</sup> + (3)<sup>2</sup> - (2)<sup>2</sup> = 9 - 1 + 4 - 1 + 9 - 4 = 16</code></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-4 * 10<sup>4</sup> &lt;= nums[i] &lt;= 4 * 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

We can sort the elements of the array by their squared values, then place the elements with larger squared values at even indices and those with smaller squared values at odd indices.

The final alternating score is the sum of the squared values of the larger elements minus the sum of the squared values of the smaller elements, that is, the sum of the squares of the latter half of the sorted array $\text{nums}$ minus the sum of the squares of the first half.

The time complexity is $O(n \log n)$ and the space complexity is $O(\log n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxAlternatingSum(self, nums: List[int]) -> int:
        nums.sort(key=lambda x: x * x)
        n = len(nums)
        s1 = sum(x * x for x in nums[: n // 2])
        s2 = sum(x * x for x in nums[n // 2 :])
        return s2 - s1
```

#### Java

```java
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        long ans = 0;
        int m = n / 2;
        for (int i = 0; i < m; ++i) {
            ans -= nums[i];
        }
        for (int i = m; i < n; ++i) {
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
    long long maxAlternatingSum(vector<int>& nums) {
        for (int& x : nums) {
            x = x * x;
        }
        ranges::sort(nums);
        long long ans = 0, m = nums.size() / 2;
        for (int i = 0; i < m; ++i) {
            ans -= nums[i];
        }
        for (int i = m; i < nums.size(); ++i) {
            ans += nums[i];
        }
        return ans;
    }
};
```

#### Go

```go
func maxAlternatingSum(nums []int) (ans int64) {
	for i, x := range nums {
		nums[i] *= x
	}
	slices.Sort(nums)
	m := len(nums) / 2
	for _, x := range nums[:m] {
		ans -= int64(x)
	}
	for _, x := range nums[m:] {
		ans += int64(x)
	}
	return
}
```

#### TypeScript

```ts
function maxAlternatingSum(nums: number[]): number {
    const n = nums.length;
    for (let i = 0; i < n; i++) {
        nums[i] = nums[i] ** 2;
    }
    nums.sort((a, b) => a - b);
    const m = Math.floor(n / 2);
    let ans = 0;
    for (let i = 0; i < m; i++) {
        ans -= nums[i];
    }
    for (let i = m; i < n; i++) {
        ans += nums[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
