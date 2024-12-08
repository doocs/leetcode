---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3379.Transformed%20Array/README_EN.md
---

<!-- problem:start -->

# [3379. Transformed Array](https://leetcode.com/problems/transformed-array)

[中文文档](/solution/3300-3399/3379.Transformed%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> that represents a circular array. Your task is to create a new array <code>result</code> of the <strong>same</strong> size, following these rules:</p>
For each index <code>i</code> (where <code>0 &lt;= i &lt; nums.length</code>), perform the following <strong>independent</strong> actions:

<ul>
	<li>If <code>nums[i] &gt; 0</code>: Start at index <code>i</code> and move <code>nums[i]</code> steps to the <strong>right</strong> in the circular array. Set <code>result[i]</code> to the value of the index where you land.</li>
	<li>If <code>nums[i] &lt; 0</code>: Start at index <code>i</code> and move <code>abs(nums[i])</code> steps to the <strong>left</strong> in the circular array. Set <code>result[i]</code> to the value of the index where you land.</li>
	<li>If <code>nums[i] == 0</code>: Set <code>result[i]</code> to <code>nums[i]</code>.</li>
</ul>

<p>Return the new array <code>result</code>.</p>

<p><strong>Note:</strong> Since <code>nums</code> is circular, moving past the last element wraps around to the beginning, and moving before the first element wraps back to the end.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,-2,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1,1,3]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>nums[0]</code> that is equal to 3, If we move 3 steps to right, we reach <code>nums[3]</code>. So <code>result[0]</code> should be 1.</li>
	<li>For <code>nums[1]</code> that is equal to -2, If we move 2 steps to left, we reach <code>nums[3]</code>. So <code>result[1]</code> should be 1.</li>
	<li>For <code>nums[2]</code> that is equal to 1, If we move 1 step to right, we reach <code>nums[3]</code>. So <code>result[2]</code> should be 1.</li>
	<li>For <code>nums[3]</code> that is equal to 1, If we move 1 step to right, we reach <code>nums[0]</code>. So <code>result[3]</code> should be 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-1,4,-1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,-1,4]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>nums[0]</code> that is equal to -1, If we move 1 step to left, we reach <code>nums[2]</code>. So <code>result[0]</code> should be -1.</li>
	<li>For <code>nums[1]</code> that is equal to 4, If we move 4 steps to right, we reach <code>nums[2]</code>. So <code>result[1]</code> should be -1.</li>
	<li>For <code>nums[2]</code> that is equal to -1, If we move 1 step to left, we reach <code>nums[1]</code>. So <code>result[2]</code> should be 4.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def constructTransformedArray(self, nums: List[int]) -> List[int]:
        ans = []
        n = len(nums)
        for i, x in enumerate(nums):
            ans.append(nums[(i + x + n) % n] if x else 0)
        return ans
```

#### Java

```java
class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = nums[i] != 0 ? nums[(i + nums[i] % n + n) % n] : 0;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> constructTransformedArray(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = nums[i] ? nums[(i + nums[i] % n + n) % n] : 0;
        }
        return ans;
    }
};
```

#### Go

```go
func constructTransformedArray(nums []int) []int {
	n := len(nums)
	ans := make([]int, n)
	for i, x := range nums {
		if x != 0 {
			ans[i] = nums[(i+x%n+n)%n]
		}
	}
	return ans
}
```

#### TypeScript

```ts
function constructTransformedArray(nums: number[]): number[] {
    const n = nums.length;
    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        ans.push(nums[i] ? nums[(i + (nums[i] % n) + n) % n] : 0);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
