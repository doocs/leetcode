---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2750.Ways%20to%20Split%20Array%20Into%20Good%20Subarrays/README_EN.md
rating: 1597
source: Weekly Contest 351 Q3
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [2750. Ways to Split Array Into Good Subarrays](https://leetcode.com/problems/ways-to-split-array-into-good-subarrays)

[中文文档](/solution/2700-2799/2750.Ways%20to%20Split%20Array%20Into%20Good%20Subarrays/README.md)

## Description

<!-- description:start -->

<p>You are given a binary array <code>nums</code>.</p>

<p>A subarray of an array is <strong>good</strong> if it contains <strong>exactly</strong> <strong>one</strong> element with the value <code>1</code>.</p>

<p>Return <em>an integer denoting the number of ways to split the array </em><code>nums</code><em> into <strong>good</strong> subarrays</em>. As the number may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>A subarray is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,0,0,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 ways to split nums into good subarrays:
- [0,1] [0,0,1]
- [0,1,0] [0,1]
- [0,1,0,0] [1]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,0]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is 1 way to split nums into good subarrays:
- [0,1,0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Multiplication Principle

Based on the problem description, we can draw a dividing line between two $1$s. Assuming the indices of the two $1$s are $j$ and $i$ respectively, then the number of different dividing lines that can be drawn is $i - j$. We find all the pairs of $j$ and $i$ that meet the condition, and then multiply all the $i - j$ together. If no dividing line can be found between two $1$s, it means there are no $1$s in the array, and the answer is $0$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfGoodSubarraySplits(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        ans, j = 1, -1
        for i, x in enumerate(nums):
            if x == 0:
                continue
            if j > -1:
                ans = ans * (i - j) % mod
            j = i
        return 0 if j == -1 else ans
```

#### Java

```java
class Solution {
    public int numberOfGoodSubarraySplits(int[] nums) {
        final int mod = (int) 1e9 + 7;
        int ans = 1, j = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                continue;
            }
            if (j > -1) {
                ans = (int) ((long) ans * (i - j) % mod);
            }
            j = i;
        }
        return j == -1 ? 0 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfGoodSubarraySplits(vector<int>& nums) {
        const int mod = 1e9 + 7;
        int ans = 1, j = -1;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == 0) {
                continue;
            }
            if (j > -1) {
                ans = 1LL * ans * (i - j) % mod;
            }
            j = i;
        }
        return j == -1 ? 0 : ans;
    }
};
```

#### Go

```go
func numberOfGoodSubarraySplits(nums []int) int {
	const mod int = 1e9 + 7
	ans, j := 1, -1
	for i, x := range nums {
		if x == 0 {
			continue
		}
		if j > -1 {
			ans = ans * (i - j) % mod
		}
		j = i
	}
	if j == -1 {
		return 0
	}
	return ans
}
```

#### TypeScript

```ts
function numberOfGoodSubarraySplits(nums: number[]): number {
    let ans = 1;
    let j = -1;
    const mod = 10 ** 9 + 7;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        if (nums[i] === 0) {
            continue;
        }
        if (j > -1) {
            ans = (ans * (i - j)) % mod;
        }
        j = i;
    }
    return j === -1 ? 0 : ans;
}
```

#### C#

```cs
public class Solution {
    public int NumberOfGoodSubarraySplits(int[] nums) {
        long ans = 1, j = -1;
        int mod = 1000000007;
        int n = nums.Length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                continue;
            }
            if (j > -1) {
                ans = ans * (i - j) % mod;
            }
            j = i;
        }
        return j == -1 ? 0 : (int) ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
