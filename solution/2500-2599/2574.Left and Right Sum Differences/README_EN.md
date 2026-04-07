---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2574.Left%20and%20Right%20Sum%20Differences/README_EN.md
rating: 1206
source: Weekly Contest 334 Q1
tags:
    - Array
    - Prefix Sum
---

<!-- problem:start -->

# [2574. Left and Right Sum Differences](https://leetcode.com/problems/left-and-right-sum-differences)

[中文文档](/solution/2500-2599/2574.Left%20and%20Right%20Sum%20Differences/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of size <code>n</code>.</p>

<p>Define two arrays <code>leftSum</code> and <code>rightSum</code> where:</p>

<ul>
	<li><code>leftSum[i]</code> is the sum of elements to the left of the index <code>i</code> in the array <code>nums</code>. If there is no such element, <code>leftSum[i] = 0</code>.</li>
	<li><code>rightSum[i]</code> is the sum of elements to the right of the index <code>i</code> in the array <code>nums</code>. If there is no such element, <code>rightSum[i] = 0</code>.</li>
</ul>

<p>Return an integer array <code>answer</code> of size <code>n</code> where <code>answer[i] = |leftSum[i] - rightSum[i]|</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,4,8,3]
<strong>Output:</strong> [15,1,11,22]
<strong>Explanation:</strong> The array leftSum is [0,10,14,22] and the array rightSum is [15,11,3,0].
The array answer is [|0 - 15|,|10 - 11|,|14 - 3|,|22 - 0|] = [15,1,11,22].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> [0]
<strong>Explanation:</strong> The array leftSum is [0] and the array rightSum is [0].
The array answer is [|0 - 0|] = [0].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum

We define a variable $l$ to represent the sum of elements to the left of index $i$ in the array $\textit{nums}$, and a variable $r$ to represent the sum of elements to the right of index $i$ in the array $\textit{nums}$. Initially, $l = 0$, $r = \sum_{i = 0}^{n - 1} \textit{nums}[i]$.

We traverse the array $\textit{nums}$. For the current number $x$, we update $r = r - x$. At this point, $l$ and $r$ represent the sum of elements to the left and right of index $i$ in the array $\textit{nums}$, respectively. We add the absolute difference of $l$ and $r$ to the answer array $\textit{ans}$, then update $l = l + x$.

After the traversal, we return the answer array $\textit{ans}$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$, not counting the space for the return value.

Similar problems:

- [0724. Find Pivot Index](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0724.Find%20Pivot%20Index/README_EN.md)
- [1991. Find the Middle Index in Array](https://github.com/doocs/leetcode/blob/main/solution/1900-1999/1991.Find%20the%20Middle%20Index%20in%20Array/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def leftRightDifference(self, nums: List[int]) -> List[int]:
        l, r = 0, sum(nums)
        ans = []
        for x in nums:
            r -= x
            ans.append(abs(l - r))
            l += x
        return ans
```

#### Java

```java
class Solution {
    public int[] leftRightDifference(int[] nums) {
        int l = 0, r = 0;
        for (int x : nums) {
            r += x;
        }
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            r -= nums[i];
            ans[i] = Math.abs(l - r);
            l += nums[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> leftRightDifference(vector<int>& nums) {
        int l = 0, r = 0;
        for (int x : nums) {
            r += x;
        }
        int n = nums.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            r -= nums[i];
            ans[i] = abs(l - r);
            l += nums[i];
        }
        return ans;
    }
};
```

#### Go

```go
func leftRightDifference(nums []int) []int {
	l, r := 0, 0
	for _, x := range nums {
		r += x
	}
	n := len(nums)
	ans := make([]int, n)
	for i, x := range nums {
		r -= x
		ans[i] = abs(l - r)
		l += x
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function leftRightDifference(nums: number[]): number[] {
    let [l, r] = [0, nums.reduce((a, b) => a + b, 0)];
    const ans: number[] = [];
    for (const x of nums) {
        r -= x;
        ans.push(Math.abs(l - r));
        l += x;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn left_right_difference(nums: Vec<i32>) -> Vec<i32> {
        let mut l = 0;
        let mut r: i32 = nums.iter().sum();
        let mut ans = Vec::with_capacity(nums.len());
        for x in nums {
            r -= x;
            ans.push((l - r).abs());
            l += x;
        }
        ans
    }
}
```

#### C

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* leftRightDifference(int* nums, int numsSize, int* returnSize) {
    *returnSize = numsSize;
    int* ans = (int*) malloc(sizeof(int) * numsSize);

    int l = 0, r = 0;
    for (int i = 0; i < numsSize; ++i) {
        r += nums[i];
    }

    for (int i = 0; i < numsSize; ++i) {
        r -= nums[i];
        ans[i] = abs(l - r);
        l += nums[i];
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
