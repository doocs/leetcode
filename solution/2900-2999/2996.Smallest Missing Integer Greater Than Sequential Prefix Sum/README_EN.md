---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2996.Smallest%20Missing%20Integer%20Greater%20Than%20Sequential%20Prefix%20Sum/README_EN.md
rating: 1405
source: Biweekly Contest 121 Q1
tags:
    - Array
    - Hash Table
    - Sorting
---

<!-- problem:start -->

# [2996. Smallest Missing Integer Greater Than Sequential Prefix Sum](https://leetcode.com/problems/smallest-missing-integer-greater-than-sequential-prefix-sum)

[中文文档](/solution/2900-2999/2996.Smallest%20Missing%20Integer%20Greater%20Than%20Sequential%20Prefix%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array of integers <code>nums</code>.</p>

<p>A prefix <code>nums[0..i]</code> is <strong>sequential</strong> if, for all <code>1 &lt;= j &lt;= i</code>, <code>nums[j] = nums[j - 1] + 1</code>. In particular, the prefix consisting only of <code>nums[0]</code> is <strong>sequential</strong>.</p>

<p>Return <em>the <strong>smallest</strong> integer</em> <code>x</code> <em>missing from</em> <code>nums</code> <em>such that</em> <code>x</code> <em>is greater than or equal to the sum of the <strong>longest</strong> sequential prefix.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,2,5]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The longest sequential prefix of nums is [1,2,3] with a sum of 6. 6 is not in the array, therefore 6 is the smallest missing integer greater than or equal to the sum of the longest sequential prefix.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,5,1,12,14,13]
<strong>Output:</strong> 15
<strong>Explanation:</strong> The longest sequential prefix of nums is [3,4,5] with a sum of 12. 12, 13, and 14 belong to the array while 15 does not. Therefore 15 is the smallest missing integer greater than or equal to the sum of the longest sequential prefix.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

First, we calculate the sum $s$ of the longest sequential prefix of the array $nums$. Then, starting from $s$, we enumerate the integer $x$. If $x$ is not in the array $nums$, then $x$ is the answer.

Since $nums[i] \leq 50$ in this problem, we can use an array of length $51$ (or a hash table) to record the integers that appear in the array, so as to quickly determine whether an integer is in the array $nums$.

The time complexity is $O(n + M)$, and the space complexity is $O(M)$. Where $n$ is the length of the array $nums$, and $M$ is the upper bound of the array elements, which is $51$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def missingInteger(self, nums: List[int]) -> int:
        s = nums[0]
        for x, y in pairwise(nums):
            if x + 1 != y:
                break
            s += y
        st = set(nums)
        while s in st:
            s += 1
        return s
```

#### Java

```java
class Solution {
    public int missingInteger(int[] nums) {
        int s = nums[0];
        for (int j = 1; j < nums.length && nums[j] == nums[j - 1] + 1; ++j) {
            s += nums[j];
        }
        final int m = 51;
        boolean[] st = new boolean[m];
        for (int x : nums) {
            st[x] = true;
        }
        while (s < m && st[s]) {
            ++s;
        }
        return s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int missingInteger(vector<int>& nums) {
        int s = nums[0];
        for (int j = 1; j < nums.size() && nums[j] == nums[j - 1] + 1; ++j) {
            s += nums[j];
        }

        const int m = 51;
        bool st[m] = {};
        for (int x : nums) {
            st[x] = true;
        }

        while (s < m && st[s]) {
            ++s;
        }
        return s;
    }
};
```

#### Go

```go
func missingInteger(nums []int) int {
	s := nums[0]
	for j := 1; j < len(nums) && nums[j] == nums[j-1]+1; j++ {
		s += nums[j]
	}

	const m = 51
	st := make([]bool, m)
	for _, x := range nums {
		st[x] = true
	}

	for s < m && st[s] {
		s++
	}
	return s
}
```

#### TypeScript

```ts
function missingInteger(nums: number[]): number {
    let s = nums[0];
    for (let j = 1; j < nums.length && nums[j] === nums[j - 1] + 1; ++j) {
        s += nums[j];
    }

    const m = 51;
    const st = new Array<boolean>(m).fill(false);
    for (const x of nums) {
        st[x] = true;
    }

    while (s < m && st[s]) {
        ++s;
    }
    return s;
}
```

#### Rust

```rust
impl Solution {
    pub fn missing_integer(nums: Vec<i32>) -> i32 {
        let mut s = nums[0];

        for j in 1..nums.len() {
            if nums[j] != nums[j - 1] + 1 {
                break;
            }
            s += nums[j];
        }

        const M: usize = 51;
        let mut st = [false; M];

        for &x in &nums {
            st[x as usize] = true;
        }

        while s < M as i32 && st[s as usize] {
            s += 1;
        }

        s
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
