---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3523.Make%20Array%20Non-decreasing/README_EN.md
tags:
    - Stack
    - Greedy
    - Array
    - Monotonic Stack
---

<!-- problem:start -->

# [3523. Make Array Non-decreasing](https://leetcode.com/problems/make-array-non-decreasing)

[中文文档](/solution/3500-3599/3523.Make%20Array%20Non-decreasing/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>. In one operation, you can select a <span data-keyword="subarray-nonempty">subarray</span> and replace it with a single element equal to its <strong>maximum</strong> value.</p>

<p>Return the <strong>maximum possible size</strong> of the array after performing zero or more operations such that the resulting array is <strong>non-decreasing</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,2,5,3,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>One way to achieve the maximum size is:</p>

<ol>
	<li>Replace subarray <code>nums[1..2] = [2, 5]</code> with <code>5</code> &rarr; <code>[4, 5, 3, 5]</code>.</li>
	<li>Replace subarray <code>nums[2..3] = [3, 5]</code> with <code>5</code> &rarr; <code>[4, 5, 5]</code>.</li>
</ol>

<p>The final array <code>[4, 5, 5]</code> is non-decreasing with size <font face="monospace">3.</font></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>No operation is needed as the array <code>[1,2,3]</code> is already non-decreasing.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2 * 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumPossibleSize(self, nums: List[int]) -> int:
        ans = mx = 0
        for x in nums:
            if mx <= x:
                ans += 1
                mx = x
        return ans
```

#### Java

```java
class Solution {
    public int maximumPossibleSize(int[] nums) {
        int ans = 0, mx = 0;
        for (int x : nums) {
            if (mx <= x) {
                ++ans;
                mx = x;
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
    int maximumPossibleSize(vector<int>& nums) {
        int ans = 0, mx = 0;
        for (int x : nums) {
            if (mx <= x) {
                ++ans;
                mx = x;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maximumPossibleSize(nums []int) int {
	ans, mx := 0, 0
	for _, x := range nums {
		if mx <= x {
			ans++
			mx = x
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maximumPossibleSize(nums: number[]): number {
    let [ans, mx] = [0, 0];
    for (const x of nums) {
        if (mx <= x) {
            ++ans;
            mx = x;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
