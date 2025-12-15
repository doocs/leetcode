---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0462.Minimum%20Moves%20to%20Equal%20Array%20Elements%20II/README_EN.md
tags:
    - Array
    - Math
    - Sorting
---

<!-- problem:start -->

# [462. Minimum Moves to Equal Array Elements II](https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii)

[中文文档](/solution/0400-0499/0462.Minimum%20Moves%20to%20Equal%20Array%20Elements%20II/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code> of size <code>n</code>, return <em>the minimum number of moves required to make all array elements equal</em>.</p>

<p>In one move, you can increment or decrement an element of the array by <code>1</code>.</p>

<p>Test cases are designed so that the answer will fit in a <strong>32-bit</strong> integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
Only two moves are needed (remember each move increments or decrements one element):
[<u>1</u>,2,3]  =&gt;  [2,2,<u>3</u>]  =&gt;  [2,2,2]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,10,2,9]
<strong>Output:</strong> 16
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Median

This problem can be abstracted to finding a point on a number line such that the sum of distances from $n$ points to this point is minimized. The answer is the median of the $n$ points.

The median has the property that the sum of distances from all numbers to the median is minimized.

Proof:

First, given a sorted sequence $a_1, a_2, \cdots, a_n$, we assume $x$ is the point that minimizes the sum of distances from $a_1$ to $a_n$. Clearly, $x$ must lie between $a_1$ and $a_n$. Since the distances from $a_1$ and $a_n$ to $x$ are equal and both equal to $a_n - a_1$, we can ignore $a_1$ and $a_n$ and only consider $a_2, a_3, \cdots, a_{n-1}$. This reduces the problem to finding a point within $a_2, a_3, \cdots, a_{n-1}$ that minimizes the sum of distances. By iterating this process, we conclude that the median of a sequence minimizes the sum of distances to the other numbers.

In this problem, we can first sort the array, then find the median, and finally calculate the sum of distances from all numbers to the median.

The time complexity is $O(n \log n)$, and the space complexity is $O(\log n)$.

Similar problems:

- [296. Best Meeting Point](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0296.Best%20Meeting%20Point/README_EN.md)
- [2448. Minimum Cost to Make Array Equal](https://github.com/doocs/leetcode/blob/main/solution/2400-2499/2448.Minimum%20Cost%20to%20Make%20Array%20Equal/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMoves2(self, nums: List[int]) -> int:
        nums.sort()
        k = nums[len(nums) >> 1]
        return sum(abs(v - k) for v in nums)
```

#### Java

```java
class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int k = nums[nums.length >> 1];
        int ans = 0;
        for (int v : nums) {
            ans += Math.abs(v - k);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMoves2(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int k = nums[nums.size() >> 1];
        int ans = 0;
        for (int& v : nums) {
            ans += abs(v - k);
        }
        return ans;
    }
};
```

#### Go

```go
func minMoves2(nums []int) int {
	sort.Ints(nums)
	k := nums[len(nums)>>1]
	ans := 0
	for _, v := range nums {
		ans += abs(v - k)
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
function minMoves2(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const k = nums[nums.length >> 1];
    return nums.reduce((r, v) => r + Math.abs(v - k), 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn min_moves2(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let k = nums[nums.len() / 2];
        let mut ans = 0;
        for num in nums.iter() {
            ans += (num - k).abs();
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
