---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1793.Maximum%20Score%20of%20a%20Good%20Subarray/README_EN.md
rating: 1945
source: Weekly Contest 232 Q4
tags:
    - Stack
    - Array
    - Two Pointers
    - Binary Search
    - Monotonic Stack
---

<!-- problem:start -->

# [1793. Maximum Score of a Good Subarray](https://leetcode.com/problems/maximum-score-of-a-good-subarray)

[中文文档](/solution/1700-1799/1793.Maximum%20Score%20of%20a%20Good%20Subarray/README.md)

## Description

<!-- description:start -->

<p>You are given an array of integers <code>nums</code> <strong>(0-indexed)</strong> and an integer <code>k</code>.</p>

<p>The <strong>score</strong> of a subarray <code>(i, j)</code> is defined as <code>min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1)</code>. A <strong>good</strong> subarray is a subarray where <code>i &lt;= k &lt;= j</code>.</p>

<p>Return <em>the maximum possible <strong>score</strong> of a <strong>good</strong> subarray.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,3,7,4,5], k = 3
<strong>Output:</strong> 15
<strong>Explanation:</strong> The optimal subarray is (1, 5) with a score of min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,5,4,5,4,1,1,1], k = 0
<strong>Output:</strong> 20
<strong>Explanation:</strong> The optimal subarray is (0, 4) with a score of min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= k &lt; nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Monotonic Stack

We can enumerate each element $nums[i]$ in $nums$ as the minimum value of the subarray, and use a monotonic stack to find the first position $left[i]$ on the left that is less than $nums[i]$ and the first position $right[i]$ on the right that is less than or equal to $nums[i]$. Then, the score of the subarray with $nums[i]$ as the minimum value is $nums[i] \times (right[i] - left[i] - 1)$.

It should be noted that the answer can only be updated when the left and right boundaries $left[i]$ and $right[i]$ satisfy $left[i]+1 \leq k \leq right[i]-1$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumScore(self, nums: List[int], k: int) -> int:
        n = len(nums)
        left = [-1] * n
        right = [n] * n
        stk = []
        for i, v in enumerate(nums):
            while stk and nums[stk[-1]] >= v:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        stk = []
        for i in range(n - 1, -1, -1):
            v = nums[i]
            while stk and nums[stk[-1]] > v:
                stk.pop()
            if stk:
                right[i] = stk[-1]
            stk.append(i)
        ans = 0
        for i, v in enumerate(nums):
            if left[i] + 1 <= k <= right[i] - 1:
                ans = max(ans, v * (right[i] - left[i] - 1))
        return ans
```

#### Java

```java
class Solution {
    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            while (!stk.isEmpty() && nums[stk.peek()] >= v) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            int v = nums[i];
            while (!stk.isEmpty() && nums[stk.peek()] > v) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (left[i] + 1 <= k && k <= right[i] - 1) {
                ans = Math.max(ans, nums[i] * (right[i] - left[i] - 1));
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
    int maximumScore(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> left(n, -1);
        vector<int> right(n, n);
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            while (!stk.empty() && nums[stk.top()] >= v) {
                stk.pop();
            }
            if (!stk.empty()) {
                left[i] = stk.top();
            }
            stk.push(i);
        }
        stk = stack<int>();
        for (int i = n - 1; i >= 0; --i) {
            int v = nums[i];
            while (!stk.empty() && nums[stk.top()] > v) {
                stk.pop();
            }
            if (!stk.empty()) {
                right[i] = stk.top();
            }
            stk.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (left[i] + 1 <= k && k <= right[i] - 1) {
                ans = max(ans, nums[i] * (right[i] - left[i] - 1));
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maximumScore(nums []int, k int) (ans int) {
	n := len(nums)
	left := make([]int, n)
	right := make([]int, n)
	for i := range left {
		left[i] = -1
		right[i] = n
	}
	stk := []int{}
	for i, v := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]] >= v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	stk = []int{}
	for i := n - 1; i >= 0; i-- {
		v := nums[i]
		for len(stk) > 0 && nums[stk[len(stk)-1]] > v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			right[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	for i, v := range nums {
		if left[i]+1 <= k && k <= right[i]-1 {
			ans = max(ans, v*(right[i]-left[i]-1))
		}
	}
	return
}
```

#### TypeScript

```ts
function maximumScore(nums: number[], k: number): number {
    const n = nums.length;
    const left: number[] = Array(n).fill(-1);
    const right: number[] = Array(n).fill(n);
    const stk: number[] = [];
    for (let i = 0; i < n; ++i) {
        while (stk.length && nums[stk.at(-1)] >= nums[i]) {
            stk.pop();
        }
        if (stk.length) {
            left[i] = stk.at(-1);
        }
        stk.push(i);
    }
    stk.length = 0;
    for (let i = n - 1; ~i; --i) {
        while (stk.length && nums[stk.at(-1)] > nums[i]) {
            stk.pop();
        }
        if (stk.length) {
            right[i] = stk.at(-1);
        }
        stk.push(i);
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (left[i] + 1 <= k && k <= right[i] - 1) {
            ans = Math.max(ans, nums[i] * (right[i] - left[i] - 1));
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
