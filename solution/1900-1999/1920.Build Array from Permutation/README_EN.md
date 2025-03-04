---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1920.Build%20Array%20from%20Permutation/README_EN.md
rating: 1160
source: Weekly Contest 248 Q1
tags:
    - Array
    - Simulation
---

<!-- problem:start -->

# [1920. Build Array from Permutation](https://leetcode.com/problems/build-array-from-permutation)

[中文文档](/solution/1900-1999/1920.Build%20Array%20from%20Permutation/README.md)

## Description

<!-- description:start -->

<p>Given a <strong>zero-based permutation</strong> <code>nums</code> (<strong>0-indexed</strong>), build an array <code>ans</code> of the <strong>same length</strong> where <code>ans[i] = nums[nums[i]]</code> for each <code>0 &lt;= i &lt; nums.length</code> and return it.</p>

<p>A <strong>zero-based permutation</strong> <code>nums</code> is an array of <strong>distinct</strong> integers from <code>0</code> to <code>nums.length - 1</code> (<strong>inclusive</strong>).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,2,1,5,3,4]
<strong>Output:</strong> [0,1,2,4,5,3]<strong>
Explanation:</strong> The array ans is built as follows: 
ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
    = [nums[0], nums[2], nums[1], nums[5], nums[3], nums[4]]
    = [0,1,2,4,5,3]</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,0,1,2,3,4]
<strong>Output:</strong> [4,5,0,1,2,3]
<strong>Explanation:</strong> The array ans is built as follows:
ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
    = [nums[5], nums[0], nums[1], nums[2], nums[3], nums[4]]
    = [4,5,0,1,2,3]</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt; nums.length</code></li>
	<li>The elements in <code>nums</code> are <strong>distinct</strong>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow-up:</strong> Can you solve it without using an extra space (i.e., <code>O(1)</code> memory)?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can directly simulate the process described in the problem by constructing a new array $\textit{ans}$. For each $i$, let $\textit{ans}[i] = \textit{nums}[\textit{nums}[i]]$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. Ignoring the space consumption of the answer array, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def buildArray(self, nums: List[int]) -> List[int]:
        return [nums[num] for num in nums]
```

#### Java

```java
class Solution {
    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> buildArray(vector<int>& nums) {
        vector<int> ans;
        for (int& num : nums) {
            ans.push_back(nums[num]);
        }
        return ans;
    }
};
```

#### Go

```go
func buildArray(nums []int) []int {
	ans := make([]int, len(nums))
	for i, num := range nums {
		ans[i] = nums[num]
	}
	return ans
}
```

#### TypeScript

```ts
function buildArray(nums: number[]): number[] {
    return nums.map(x => nums[x]);
}
```

#### Rust

```rust
impl Solution {
    pub fn build_array(nums: Vec<i32>) -> Vec<i32> {
        nums.iter().map(|&v| nums[v as usize]).collect()
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var buildArray = function (nums) {
    return nums.map(x => nums[x]);
};
```

#### C

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* buildArray(int* nums, int numsSize, int* returnSize) {
    int* ans = malloc(sizeof(int) * numsSize);
    for (int i = 0; i < numsSize; i++) {
        ans[i] = nums[nums[i]];
    }
    *returnSize = numsSize;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
