---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2219.Maximum%20Sum%20Score%20of%20Array/README_EN.md
tags:
    - Array
    - Prefix Sum
---

<!-- problem:start -->

# [2219. Maximum Sum Score of Array 🔒](https://leetcode.com/problems/maximum-sum-score-of-array)

[中文文档](/solution/2200-2299/2219.Maximum%20Sum%20Score%20of%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of length <code>n</code>.</p>

<p>The <strong>sum </strong><strong>score</strong> of <code>nums</code> at an index <code>i</code> where <code>0 &lt;= i &lt; n</code> is the <strong>maximum</strong> of:</p>

<ul>
	<li>The sum of the <strong>first</strong> <code>i + 1</code> elements of <code>nums</code>.</li>
	<li>The sum of the <strong>last</strong> <code>n - i</code> elements of <code>nums</code>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> <strong>sum </strong><strong>score</strong> of </em><code>nums</code><em> at any index.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,3,-2,5]
<strong>Output:</strong> 10
<strong>Explanation:</strong>
The sum score at index 0 is max(4, 4 + 3 + -2 + 5) = max(4, 10) = 10.
The sum score at index 1 is max(4 + 3, 3 + -2 + 5) = max(7, 6) = 7.
The sum score at index 2 is max(4 + 3 + -2, -2 + 5) = max(5, 3) = 5.
The sum score at index 3 is max(4 + 3 + -2 + 5, 5) = max(10, 5) = 10.
The maximum sum score of nums is 10.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-3,-5]
<strong>Output:</strong> -3
<strong>Explanation:</strong>
The sum score at index 0 is max(-3, -3 + -5) = max(-3, -8) = -3.
The sum score at index 1 is max(-3 + -5, -5) = max(-8, -5) = -5.
The maximum sum score of nums is -3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum

We can use two variables $l$ and $r$ to represent the prefix sum and suffix sum of the array, respectively. Initially, $l = 0$ and $r = \sum_{i=0}^{n-1} \textit{nums}[i]$.

Next, we traverse the array $\textit{nums}$. For each element $x$, we add $x$ to $l$ and update the answer $\textit{ans} = \max(\textit{ans}, l, r)$, then subtract $x$ from $r$.

After the traversal, return the answer $\textit{ans}$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumSumScore(self, nums: List[int]) -> int:
        l, r = 0, sum(nums)
        ans = -inf
        for x in nums:
            l += x
            ans = max(ans, l, r)
            r -= x
        return ans
```

#### Java

```java
class Solution {
    public long maximumSumScore(int[] nums) {
        long l = 0, r = 0;
        for (int x : nums) {
            r += x;
        }
        long ans = Long.MIN_VALUE;
        for (int x : nums) {
            l += x;
            ans = Math.max(ans, Math.max(l, r));
            r -= x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumSumScore(vector<int>& nums) {
        long long l = 0, r = accumulate(nums.begin(), nums.end(), 0LL);
        long long ans = -1e18;
        for (int x : nums) {
            l += x;
            ans = max({ans, l, r});
            r -= x;
        }
        return ans;
    }
};
```

#### Go

```go
func maximumSumScore(nums []int) int64 {
	l, r := 0, 0
	for _, x := range nums {
		r += x
	}
	ans := math.MinInt64
	for _, x := range nums {
		l += x
		ans = max(ans, max(l, r))
		r -= x
	}
	return int64(ans)
}
```

#### TypeScript

```ts
function maximumSumScore(nums: number[]): number {
    let l = 0;
    let r = nums.reduce((a, b) => a + b, 0);
    let ans = -Infinity;
    for (const x of nums) {
        l += x;
        ans = Math.max(ans, l, r);
        r -= x;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_sum_score(nums: Vec<i32>) -> i64 {
        let mut l = 0;
        let mut r: i64 = nums.iter().map(|&x| x as i64).sum();
        let mut ans = std::i64::MIN;
        for &x in &nums {
            l += x as i64;
            ans = ans.max(l).max(r);
            r -= x as i64;
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var maximumSumScore = function (nums) {
    let l = 0;
    let r = nums.reduce((a, b) => a + b, 0);
    let ans = -Infinity;
    for (const x of nums) {
        l += x;
        ans = Math.max(ans, l, r);
        r -= x;
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
