---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3788.Maximum%20Score%20of%20a%20Split/README_EN.md
rating: 1306
source: Weekly Contest 482 Q1
tags:
    - Array
    - Prefix Sum
---

<!-- problem:start -->

# [3788. Maximum Score of a Split](https://leetcode.com/problems/maximum-score-of-a-split)

[中文文档](/solution/3700-3799/3788.Maximum%20Score%20of%20a%20Split/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>.</p>

<p>Choose an index <code>i</code> such that <code>0 &lt;= i &lt; n - 1</code>.</p>

<p>For a chosen split index <code>i</code>:</p>

<ul>
	<li>Let <code>prefixSum(i)</code> be the sum of <code>nums[0] + nums[1] + ... + nums[i]</code>.</li>
	<li>Let <code>suffixMin(i)</code> be the minimum value among <code>nums[i + 1], nums[i + 2], ..., nums[n - 1]</code>.</li>
</ul>

<p>The <strong>score</strong> of a split at index <code>i</code> is defined as:</p>

<p><code>score(i) = prefixSum(i) - suffixMin(i)</code></p>

<p>Return an integer denoting the <strong>maximum</strong> score over all valid split indices.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [10,-1,3,-4,-5]</span></p>

<p><strong>Output:</strong> <span class="example-io">17</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal split is at <code>i = 2</code>, <code>score(2) = prefixSum(2) - suffixMin(2) = (10 + (-1) + 3) - (-5) = 17</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-7,-5,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">-2</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal split is at <code>i = 0</code>, <code>score(0) = prefixSum(0) - suffixMin(0) = (-7) - (-5) = -2</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The only valid split is at <code>i = 0</code>, <code>score(0) = prefixSum(0) - suffixMin(0) = 1 - 1 = 0</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup>​​​​​​​ &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Enumeration

We first define an array $\textit{suf}$ of length $n$, where $\textit{suf}[i]$ represents the minimum value of the array $\textit{nums}$ from index $i$ to index $n - 1$. We can traverse the array $\textit{nums}$ from back to front to compute the array $\textit{suf}$.

Next, we define a variable $\textit{pre}$ to represent the prefix sum of the array $\textit{nums}$. We traverse the first $n - 1$ elements of the array $\textit{nums}$. For each index $i$, we add $\textit{nums}[i]$ to $\textit{pre}$ and calculate the split score $\textit{score}(i) = \textit{pre} - \textit{suf}[i + 1]$. We use a variable $\textit{ans}$ to maintain the maximum value among all split scores.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumScore(self, nums: List[int]) -> int:
        n = len(nums)
        suf = [nums[-1]] * n
        for i in range(n - 2, -1, -1):
            suf[i] = min(nums[i], suf[i + 1])
        ans = -inf
        pre = 0
        for i in range(n - 1):
            pre += nums[i]
            ans = max(ans, pre - suf[i + 1])
        return ans
```

#### Java

```java
class Solution {
    public long maximumScore(int[] nums) {
        int n = nums.length;
        long[] suf = new long[n];
        suf[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            suf[i] = Math.min(nums[i], suf[i + 1]);
        }
        long ans = Long.MIN_VALUE;
        long pre = 0;
        for (int i = 0; i < n - 1; ++i) {
            pre += nums[i];
            ans = Math.max(ans, pre - suf[i + 1]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumScore(vector<int>& nums) {
        int n = nums.size();
        vector<long long> suf(n);
        suf[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            suf[i] = min((long long) nums[i], suf[i + 1]);
        }
        long long ans = LLONG_MIN;
        long long pre = 0;
        for (int i = 0; i < n - 1; ++i) {
            pre += nums[i];
            ans = max(ans, pre - suf[i + 1]);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumScore(nums []int) int64 {
	n := len(nums)
	suf := make([]int64, n)
	suf[n-1] = int64(nums[n-1])
	for i := n - 2; i >= 0; i-- {
		suf[i] = min(int64(nums[i]), suf[i+1])
	}
	var pre int64 = 0
	var ans int64 = math.MinInt64
	for i := 0; i < n-1; i++ {
		pre += int64(nums[i])
		ans = max(ans, pre-suf[i+1])
	}
	return ans
}
```

#### TypeScript

```ts
function maximumScore(nums: number[]): number {
    const n = nums.length;
    const suf: number[] = new Array(n);
    suf[n - 1] = nums[n - 1];
    for (let i = n - 2; i >= 0; --i) {
        suf[i] = Math.min(nums[i], suf[i + 1]);
    }
    let ans = Number.NEGATIVE_INFINITY;
    let pre = 0;
    for (let i = 0; i < n - 1; ++i) {
        pre += nums[i];
        ans = Math.max(ans, pre - suf[i + 1]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
