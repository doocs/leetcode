---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3979.Maximum%20Valid%20Pair%20Sum/README_EN.md
---

<!-- problem:start -->

# [3979. Maximum Valid Pair Sum](https://leetcode.com/problems/maximum-valid-pair-sum)

[中文文档](/solution/3900-3999/3979.Maximum%20Valid%20Pair%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and an integer <code>k</code>.</p>

<p>A pair of indices <code>(i, j)</code> is called <strong>valid</strong> if:</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; n</code></li>
	<li><code>j - i &gt;= k</code></li>
</ul>

<p>Return the <strong>maximum</strong> value of <code>nums[i] + nums[j]</code> among all valid pairs.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,5,2,8], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">13</span></p>

<p><strong>Explanation:</strong></p>

<p>The valid pairs are:</p>

<ul>
	<li><code>(0, 2)</code>: <code>nums[0] + nums[2] = 6</code></li>
	<li><code>(0, 3)</code>: <code>nums[0] + nums[3] = 3</code></li>
	<li><code>(0, 4)</code>: <code>nums[0] + nums[4] = 9</code></li>
	<li><code>(1, 3)</code>: <code>nums[1] + nums[3] = 5</code></li>
	<li><code>(1, 4)</code>: <code>nums[1] + nums[4] = 11</code></li>
	<li><code>(2, 4)</code>: <code>nums[2] + nums[4] = 13</code></li>
</ul>

<p>Thus, the answer is 13.​​​​​​​</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,1,9], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Since <code>k = 1</code>, every pair is valid.</li>
	<li>The maximum value is obtained from a pair <code>(0, 2)</code>​​​​​​​, which is <code>nums[0] + nums[2] = 5 + 9 = 14</code>.</li>
	<li>Thus, the answer is 14.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window

For a valid pair $(i, j)$, we require $j - i \geq k$, i.e., $i \leq j - k$. We enumerate the right endpoint $j$ starting from $k$. For each $j$, the maximum left endpoint is $j - k$. We maintain the maximum value $x$ of $\textit{nums}[i]$ in the range $[0, j - k]$, and update the answer with $x + \textit{nums}[j]$.

The time complexity is $O(n)$, and the space complexity is $O(1)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxValidPairSum(self, nums: list[int], k: int) -> int:
        ans = x = 0
        for j in range(k, len(nums)):
            y = nums[j]
            x = max(x, nums[j - k])
            ans = max(ans, x + y)
        return ans
```

#### Java

```java
class Solution {
    public int maxValidPairSum(int[] nums, int k) {
        int ans = 0;
        int x = 0;
        for (int j = k; j < nums.length; ++j) {
            int y = nums[j];
            x = Math.max(x, nums[j - k]);
            ans = Math.max(ans, x + y);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxValidPairSum(vector<int>& nums, int k) {
        int ans = 0;
        int x = 0;
        for (int j = k; j < nums.size(); ++j) {
            int y = nums[j];
            x = max(x, nums[j - k]);
            ans = max(ans, x + y);
        }
        return ans;
    }
};
```

#### Go

```go
func maxValidPairSum(nums []int, k int) int {
	var ans, x int
	for j := k; j < len(nums); j++ {
		y := nums[j]
		x = max(x, nums[j-k])
		ans = max(ans, x+y)
	}
	return ans
}
```

#### TypeScript

```ts
function maxValidPairSum(nums: number[], k: number): number {
    let [ans, x] = [0, 0];
    for (let j = k; j < nums.length; ++j) {
        const y = nums[j];
        x = Math.max(x, nums[j - k]);
        ans = Math.max(ans, x + y);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
