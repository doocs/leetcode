---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2176.Count%20Equal%20and%20Divisible%20Pairs%20in%20an%20Array/README_EN.md
rating: 1215
source: Biweekly Contest 72 Q1
tags:
    - Array
---

<!-- problem:start -->

# [2176. Count Equal and Divisible Pairs in an Array](https://leetcode.com/problems/count-equal-and-divisible-pairs-in-an-array)

[中文文档](/solution/2100-2199/2176.Count%20Equal%20and%20Divisible%20Pairs%20in%20an%20Array/README.md)

## Description

<!-- description:start -->

Given a <strong>0-indexed</strong> integer array <code>nums</code> of length <code>n</code> and an integer <code>k</code>, return <em>the <strong>number of pairs</strong></em> <code>(i, j)</code> <em>where</em> <code>0 &lt;= i &lt; j &lt; n</code>, <em>such that</em> <code>nums[i] == nums[j]</code> <em>and</em> <code>(i \* j)</code> <em>is divisible by</em> <code>k</code>.

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,2,2,2,1,3], k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong>
There are 4 pairs that meet all the requirements:
- nums[0] == nums[6], and 0 * 6 == 0, which is divisible by 2.
- nums[2] == nums[3], and 2 * 3 == 6, which is divisible by 2.
- nums[2] == nums[4], and 2 * 4 == 8, which is divisible by 2.
- nums[3] == nums[4], and 3 * 4 == 12, which is divisible by 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4], k = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> Since no value in nums is repeated, there are no pairs (i,j) that meet all the requirements.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We first enumerate the index $j$ in the range $[0, n)$, and then enumerate the index $i$ in the range $[0, j)$. We count the number of pairs that satisfy $\textit{nums}[i] = \textit{nums}[j]$ and $(i \times j) \bmod k = 0$.

The time complexity is $O(n^2)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPairs(self, nums: List[int], k: int) -> int:
        ans = 0
        for j, y in enumerate(nums):
            for i, x in enumerate(nums[:j]):
                ans += int(x == y and i * j % k == 0)
        return ans
```

#### Java

```java
class Solution {
    public int countPairs(int[] nums, int k) {
        int ans = 0;
        for (int j = 1; j < nums.length; ++j) {
            for (int i = 0; i < j; ++i) {
                ans += nums[i] == nums[j] && (i * j % k) == 0 ? 1 : 0;
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
    int countPairs(vector<int>& nums, int k) {
        int ans = 0;
        for (int j = 1; j < nums.size(); ++j) {
            for (int i = 0; i < j; ++i) {
                ans += nums[i] == nums[j] && (i * j % k) == 0;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countPairs(nums []int, k int) (ans int) {
	for j, y := range nums {
		for i, x := range nums[:j] {
			if x == y && (i*j%k) == 0 {
				ans++
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function countPairs(nums: number[], k: number): number {
    let ans = 0;
    for (let j = 1; j < nums.length; ++j) {
        for (let i = 0; i < j; ++i) {
            if (nums[i] === nums[j] && (i * j) % k === 0) {
                ++ans;
            }
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_pairs(nums: Vec<i32>, k: i32) -> i32 {
        let mut ans = 0;
        for j in 1..nums.len() {
            for (i, &x) in nums[..j].iter().enumerate() {
                if x == nums[j] && (i * j) as i32 % k == 0 {
                    ans += 1;
                }
            }
        }
        ans
    }
}
```

#### C

```c
int countPairs(int* nums, int numsSize, int k) {
    int ans = 0;
    for (int j = 1; j < numsSize; ++j) {
        for (int i = 0; i < j; ++i) {
            ans += (nums[i] == nums[j] && (i * j % k) == 0);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
