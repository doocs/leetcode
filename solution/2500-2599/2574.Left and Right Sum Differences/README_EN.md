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

We define a variable $left$ to represent the sum of the elements to the left of index $i$ in the array `nums`, and a variable $right$ to represent the sum of the elements to the right of index $i$ in the array `nums`. Initially, $left = 0$, $right = \sum_{i = 0}^{n - 1} nums[i]$.

We iterate over the array `nums`. For the current number $x$ we are iterating over, we update $right = right - x$. At this point, $left$ and $right$ represent the sum of the elements to the left and right of index $i$ in the array `nums`, respectively. We add the absolute difference between $left$ and $right$ to the answer array `ans`, and then update $left = left + x$.

After the iteration is complete, we return the answer array `ans`.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Where $n$ is the length of the array `nums`.

Similar problems:

-   [0724. Find Pivot Index](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0724.Find%20Pivot%20Index/README_EN.md)
-   [1991. Find the Middle Index in Array](https://github.com/doocs/leetcode/blob/main/solution/1900-1999/1991.Find%20the%20Middle%20Index%20in%20Array/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def leftRigthDifference(self, nums: List[int]) -> List[int]:
        left, right = 0, sum(nums)
        ans = []
        for x in nums:
            right -= x
            ans.append(abs(left - right))
            left += x
        return ans
```

#### Java

```java
class Solution {
    public int[] leftRigthDifference(int[] nums) {
        int left = 0, right = Arrays.stream(nums).sum();
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            right -= nums[i];
            ans[i] = Math.abs(left - right);
            left += nums[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> leftRigthDifference(vector<int>& nums) {
        int left = 0, right = accumulate(nums.begin(), nums.end(), 0);
        vector<int> ans;
        for (int& x : nums) {
            right -= x;
            ans.push_back(abs(left - right));
            left += x;
        }
        return ans;
    }
};
```

#### Go

```go
func leftRigthDifference(nums []int) (ans []int) {
	var left, right int
	for _, x := range nums {
		right += x
	}
	for _, x := range nums {
		right -= x
		ans = append(ans, abs(left-right))
		left += x
	}
	return
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
function leftRigthDifference(nums: number[]): number[] {
    let left = 0,
        right = nums.reduce((a, b) => a + b);
    const ans: number[] = [];
    for (const x of nums) {
        right -= x;
        ans.push(Math.abs(left - right));
        left += x;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn left_rigth_difference(nums: Vec<i32>) -> Vec<i32> {
        let mut left = 0;
        let mut right = nums.iter().sum::<i32>();
        nums.iter()
            .map(|v| {
                right -= v;
                let res = (left - right).abs();
                left += v;
                res
            })
            .collect()
    }
}
```

#### C

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* leftRigthDifference(int* nums, int numsSize, int* returnSize) {
    int left = 0;
    int right = 0;
    for (int i = 0; i < numsSize; i++) {
        right += nums[i];
    }
    int* ans = malloc(sizeof(int) * numsSize);
    for (int i = 0; i < numsSize; i++) {
        right -= nums[i];
        ans[i] = abs(left - right);
        left += nums[i];
    }
    *returnSize = numsSize;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### TypeScript

```ts
function leftRigthDifference(nums: number[]): number[] {
    let left = 0;
    let right = nums.reduce((r, v) => r + v);
    return nums.map(v => {
        right -= v;
        const res = Math.abs(left - right);
        left += v;
        return res;
    });
}
```

#### Rust

```rust
impl Solution {
    pub fn left_right_difference(nums: Vec<i32>) -> Vec<i32> {
        let mut ans = vec![];

        for i in 0..nums.len() {
            let mut left = 0;
            for j in 0..i {
                left += nums[j];
            }

            let mut right = 0;
            for k in i + 1..nums.len() {
                right += nums[k];
            }

            ans.push((left - right).abs());
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3

<!-- tabs:start -->

#### Rust

```rust
impl Solution {
    pub fn left_right_difference(nums: Vec<i32>) -> Vec<i32> {
        let mut left = 0;
        let mut right: i32 = nums.iter().sum();
        let mut ans = vec![];

        for &x in &nums {
            right -= x;
            ans.push((left - right).abs());
            left += x;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
