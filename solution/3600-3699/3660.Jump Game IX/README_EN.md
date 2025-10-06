---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3660.Jump%20Game%20IX/README_EN.md
rating: 2187
source: Weekly Contest 464 Q3
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3660. Jump Game IX](https://leetcode.com/problems/jump-game-ix)

[中文文档](/solution/3600-3699/3660.Jump%20Game%20IX/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>From any index <code>i</code>, you can jump to another index <code>j</code> under the following rules:</p>

<ul>
	<li>Jump to index <code>j</code> where <code>j &gt; i</code> is allowed only if <code>nums[j] &lt; nums[i]</code>.</li>
	<li>Jump to index <code>j</code> where <code>j &lt; i</code> is allowed only if <code>nums[j] &gt; nums[i]</code>.</li>
</ul>

<p>For each index <code>i</code>, find the <strong>maximum</strong> <strong>value</strong> in <code>nums</code> that can be reached by following <strong>any</strong> sequence of valid jumps starting at <code>i</code>.</p>

<p>Return an array <code>ans</code> where <code>ans[i]</code> is the <strong>maximum</strong> <strong>value</strong> reachable starting from index <code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,2,3]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>i = 0</code>: No jump increases the value.</li>
	<li>For <code>i = 1</code>: Jump to <code>j = 0</code> as <code>nums[j] = 2</code> is greater than <code>nums[i]</code>.</li>
	<li>For <code>i = 2</code>: Since <code>nums[2] = 3</code> is the maximum value in <code>nums</code>, no jump increases the value.</li>
</ul>

<p>Thus, <code>ans = [2, 2, 3]</code>.</p>

<ul>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,3,3]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>i = 0</code>: Jump forward to <code>j = 2</code> as <code>nums[j] = 1</code> is less than <code>nums[i] = 2</code>, then from <code>i = 2</code> jump to <code>j = 1</code> as <code>nums[j] = 3</code> is greater than <code>nums[2]</code>.</li>
	<li>For <code>i = 1</code>: Since <code>nums[1] = 3</code> is the maximum value in <code>nums</code>, no jump increases the value.</li>
	<li>For <code>i = 2</code>: Jump to <code>j = 1</code> as <code>nums[j] = 3</code> is greater than <code>nums[2] = 1</code>.</li>
</ul>

<p>Thus, <code>ans = [3, 3, 3]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup>​​​​​​​</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

If $i = n - 1$, then it can jump to the maximum value in $\textit{nums}$, so $\textit{ans}[i] = \max(\textit{nums})$. For other positions $i$, we can calculate by maintaining a prefix maximum array and a suffix minimum variable.

The specific steps are as follows:

1. Create an array $\textit{preMax}$, where $\textit{preMax}[i]$ represents the maximum value in the interval $[0, i]$ when traversing from left to right.
2. Create a variable $\textit{sufMin}$, which represents the minimum value to the right of the current element when traversing from right to left. Initially $\textit{sufMin} = \infty$.
3. First preprocess the $\textit{preMax}$ array.
4. Next, traverse the array from right to left. For each position $i$, if $\textit{preMax}[i] > \textit{sufMin}$, it means we can jump from $i$ to the position where $\textit{preMax}$ is located, then jump to the position where $\textit{sufMin}$ is located, and finally jump to $i + 1$. Therefore, the numbers that can be reached from $i + 1$ can also be reached from $i$, so $\textit{ans}[i] = \textit{ans}[i + 1]$; otherwise update to $\textit{preMax}[i]$. Then update $\textit{sufMin}$.
5. Finally return the result array $\textit{ans}$.

Time complexity $O(n)$, space complexity $O(n)$. Where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxValue(self, nums: List[int]) -> List[int]:
        n = len(nums)
        ans = [0] * n
        pre_max = [nums[0]] * n
        for i in range(1, n):
            pre_max[i] = max(pre_max[i - 1], nums[i])
        suf_min = inf
        for i in range(n - 1, -1, -1):
            ans[i] = ans[i + 1] if pre_max[i] > suf_min else pre_max[i]
            suf_min = min(suf_min, nums[i])
        return ans
```

#### Java

```java
class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int[] preMax = new int[n];
        preMax[0] = nums[0];
        for (int i = 1; i < n; ++i) {
            preMax[i] = Math.max(preMax[i - 1], nums[i]);
        }
        int sufMin = 1 << 30;
        for (int i = n - 1; i >= 0; --i) {
            ans[i] = preMax[i] > sufMin ? ans[i + 1] : preMax[i];
            sufMin = Math.min(sufMin, nums[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> maxValue(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n);
        vector<int> preMax(n, nums[0]);
        for (int i = 1; i < n; ++i) {
            preMax[i] = max(preMax[i - 1], nums[i]);
        }
        int sufMin = 1 << 30;
        for (int i = n - 1; i >= 0; --i) {
            ans[i] = preMax[i] > sufMin ? ans[i + 1] : preMax[i];
            sufMin = min(sufMin, nums[i]);
        }
        return ans;
    }
};
```

#### Go

```go
func maxValue(nums []int) []int {
	n := len(nums)
	ans := make([]int, n)
	preMax := make([]int, n)
	preMax[0] = nums[0]
	for i := 1; i < n; i++ {
		preMax[i] = max(preMax[i-1], nums[i])
	}
	sufMin := 1 << 30
	for i := n - 1; i >= 0; i-- {
		if preMax[i] > sufMin {
			ans[i] = ans[i+1]
		} else {
			ans[i] = preMax[i]
		}
		sufMin = min(sufMin, nums[i])
	}
	return ans
}
```

#### TypeScript

```ts
function maxValue(nums: number[]): number[] {
    const n = nums.length;
    const ans = Array(n).fill(0);
    const preMax = Array(n).fill(nums[0]);
    for (let i = 1; i < n; i++) {
        preMax[i] = Math.max(preMax[i - 1], nums[i]);
    }
    let sufMin = 1 << 30;
    for (let i = n - 1; i >= 0; i--) {
        ans[i] = preMax[i] > sufMin ? ans[i + 1] : preMax[i];
        sufMin = Math.min(sufMin, nums[i]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
