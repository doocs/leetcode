---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1877.Minimize%20Maximum%20Pair%20Sum%20in%20Array/README_EN.md
rating: 1301
source: Biweekly Contest 53 Q2
tags:
    - Greedy
    - Array
    - Two Pointers
    - Sorting
---

<!-- problem:start -->

# [1877. Minimize Maximum Pair Sum in Array](https://leetcode.com/problems/minimize-maximum-pair-sum-in-array)

[中文文档](/solution/1800-1899/1877.Minimize%20Maximum%20Pair%20Sum%20in%20Array/README.md)

## Description

<!-- description:start -->

<p>The <strong>pair sum</strong> of a pair <code>(a,b)</code> is equal to <code>a + b</code>. The <strong>maximum pair sum</strong> is the largest <strong>pair sum</strong> in a list of pairs.</p>

<ul>

    <li>For example, if we have pairs <code>(1,5)</code>, <code>(2,3)</code>, and <code>(4,4)</code>, the <strong>maximum pair sum</strong> would be <code>max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8</code>.</li>

</ul>

<p>Given an array <code>nums</code> of <strong>even</strong> length <code>n</code>, pair up the elements of <code>nums</code> into <code>n / 2</code> pairs such that:</p>

<ul>

    <li>Each element of <code>nums</code> is in <strong>exactly one</strong> pair, and</li>

    <li>The <strong>maximum pair sum </strong>is <strong>minimized</strong>.</li>

</ul>

<p>Return <em>the minimized <strong>maximum pair sum</strong> after optimally pairing up the elements</em>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> nums = [3,5,2,3]

<strong>Output:</strong> 7

<strong>Explanation:</strong> The elements can be paired up into pairs (3,3) and (5,2).

The maximum pair sum is max(3+3, 5+2) = max(6, 7) = 7.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> nums = [3,5,4,2,4,6]

<strong>Output:</strong> 8

<strong>Explanation:</strong> The elements can be paired up into pairs (3,5), (4,4), and (6,2).

The maximum pair sum is max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>n == nums.length</code></li>

    <li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>

    <li><code>n</code> is <strong>even</strong>.</li>

    <li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>

</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

To minimize the maximum pair sum in the array, we can pair the smallest number with the largest number, the second smallest with the second largest, and so on.

Therefore, we can first sort the array, then use two pointers to point to the two ends of the array. Calculate the sum of the numbers pointed to by the two pointers, update the maximum pair sum, then move the left pointer one step to the right and the right pointer one step to the left. Continue this process until the two pointers meet, and we will get the minimum maximum pair sum.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minPairSum(self, nums: List[int]) -> int:
        nums.sort()
        return max(x + nums[-i - 1] for i, x in enumerate(nums[: len(nums) >> 1]))
```

#### Java

```java
class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int ans = 0, n = nums.length;
        for (int i = 0; i < n >> 1; ++i) {
            ans = Math.max(ans, nums[i] + nums[n - i - 1]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minPairSum(vector<int>& nums) {
        ranges::sort(nums);
        int ans = 0, n = nums.size();
        for (int i = 0; i < n >> 1; ++i) {
            ans = max(ans, nums[i] + nums[n - i - 1]);
        }
        return ans;
    }
};
```

#### Go

```go
func minPairSum(nums []int) (ans int) {
	sort.Ints(nums)
	n := len(nums)
	for i, x := range nums[:n>>1] {
		ans = max(ans, x+nums[n-1-i])
	}
	return
}
```

#### TypeScript

```ts
function minPairSum(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let ans = 0;
    const n = nums.length;
    for (let i = 0; i < n >> 1; ++i) {
        ans = Math.max(ans, nums[i] + nums[n - 1 - i]);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_pair_sum(nums: Vec<i32>) -> i32 {
        let mut nums = nums;
        nums.sort();
        let mut ans = 0;
        let n = nums.len();
        for i in 0..n / 2 {
            ans = ans.max(nums[i] + nums[n - i - 1]);
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
var minPairSum = function (nums) {
    nums.sort((a, b) => a - b);
    let ans = 0;
    const n = nums.length;
    for (let i = 0; i < n >> 1; ++i) {
        ans = Math.max(ans, nums[i] + nums[n - 1 - i]);
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int MinPairSum(int[] nums) {
        Array.Sort(nums);
        int ans = 0, n = nums.Length;
        for (int i = 0; i < n >> 1; ++i) {
            ans = Math.Max(ans, nums[i] + nums[n - i - 1]);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
