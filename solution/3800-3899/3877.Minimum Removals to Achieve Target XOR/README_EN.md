---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3877.Minimum%20Removals%20to%20Achieve%20Target%20XOR/README_EN.md
rating: 1745
source: Weekly Contest 494 Q3
tags:
    - Bit Manipulation
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3877. Minimum Removals to Achieve Target XOR](https://leetcode.com/problems/minimum-removals-to-achieve-target-xor)

[中文文档](/solution/3800-3899/3877.Minimum%20Removals%20to%20Achieve%20Target%20XOR/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>target</code>.</p>

<p>You may remove <strong>any</strong> number of elements from <code>nums</code> (possibly zero).</p>

<p>Return the <strong>minimum</strong> number of removals required so that the <strong>bitwise XOR</strong> of the remaining elements equals <code>target</code>. If it is impossible to achieve <code>target</code>, return -1.</p>

<p>The bitwise XOR of an empty array is 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], target = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Removing <code>nums[1] = 2</code> leaves <code>[nums[0], nums[2]] = [1, 3]</code>.</li>
	<li>The XOR of <code>[1, 3]</code> is 2, which equals <code>target</code>.</li>
	<li>It is not possible to achieve XOR = 2 in less than one removal, therefore the answer is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4], target = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>It is impossible to remove elements to achieve <code>target</code>. Thus, the answer is -1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7], target = 7</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The XOR of all elements is <code>nums[0] = 7</code>, which equals <code>target</code>. Thus, no removal is needed.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 40</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define a 2D array $f$, where $f[i][j]$ represents the maximum number of elements we can select from the first $i$ elements such that their XOR sum equals $j$. Initially, $f[0][0] = 0$ and all other $f[0][j]$ are negative infinity.

For each element $nums[i - 1]$, we can choose not to use it, in which case $f[i][j]$ equals $f[i - 1][j]$; or we can choose to use it, in which case $f[i][j]$ equals $f[i - 1][j \oplus nums[i - 1]] + 1$. Thus, the transition equation is:

$$
\begin{aligned}
f[i][j] = \max(f[i - 1][j], f[i - 1][j \oplus nums[i - 1]] + 1)
\end{aligned}
$$

Finally, if $f[n][target]$ is less than $0$, it means the target XOR value cannot be achieved, and we return $-1$; otherwise, we return $n - f[n][target]$, which is the number of elements that need to be removed.

The time complexity is $O(n \times 2^m)$ and the space complexity is $O(n \times 2^m)$, where $n$ is the length of the array and $m$ is the number of binary bits of the maximum element in the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minRemovals(self, nums: List[int], target: int) -> int:
        m = max(nums).bit_length()
        if (1 << m) <= target:
            return -1
        n = len(nums)
        f = [[-inf] * (1 << m) for _ in range(n + 1)]
        f[0][0] = 0
        for i, x in enumerate(nums, 1):
            for j in range(1 << m):
                f[i][j] = max(f[i - 1][j], f[i - 1][j ^ x] + 1)
        if f[n][target] < 0:
            return -1
        return n - f[n][target]
```

#### Java

```java
class Solution {
    public int minRemovals(int[] nums, int target) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int m = 32 - Integer.numberOfLeadingZeros(mx);
        if ((1 << m) <= target) {
            return -1;
        }

        int n = nums.length;
        int[][] f = new int[n + 1][1 << m];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(f[i], Integer.MIN_VALUE);
        }
        f[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = 0; j < (1 << m); j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i - 1][j ^ x] + 1);
            }
        }

        if (f[n][target] < 0) {
            return -1;
        }
        return n - f[n][target];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minRemovals(vector<int>& nums, int target) {
        int mx = ranges::max(nums);
        int m = 0;
        while ((1 << m) <= mx) {
            ++m;
        }
        if ((1 << m) <= target) {
            return -1;
        }

        int n = nums.size();
        vector<vector<int>> f(n + 1, vector<int>(1 << m, INT_MIN));
        f[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = 0; j < (1 << m); j++) {
                f[i][j] = max(f[i - 1][j], f[i - 1][j ^ x] + 1);
            }
        }

        if (f[n][target] < 0) {
            return -1;
        }
        return n - f[n][target];
    }
};
```

#### Go

```go
func minRemovals(nums []int, target int) int {
	m := bits.Len(uint(slices.Max(nums)))
	if (1 << m) <= target {
		return -1
	}

	n := len(nums)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, 1<<m)
		for j := range f[i] {
			f[i][j] = math.MinInt
		}
	}
	f[0][0] = 0

	for i := 1; i <= n; i++ {
		x := nums[i-1]
		for j := 0; j < (1 << m); j++ {
			f[i][j] = max(f[i-1][j], f[i-1][j^x]+1)
		}
	}

	if f[n][target] < 0 {
		return -1
	}
	return n - f[n][target]
}
```

#### TypeScript

```ts
function minRemovals(nums: number[], target: number): number {
    let mx = Math.max(...nums);

    let m = 0;
    while (1 << m <= mx) {
        m++;
    }
    if (1 << m <= target) {
        return -1;
    }

    const n = nums.length;
    const f = Array.from({ length: n + 1 }, () => Array(1 << m).fill(-Infinity));

    f[0][0] = 0;

    for (let i = 1; i <= n; i++) {
        const x = nums[i - 1];
        for (let j = 0; j < 1 << m; j++) {
            f[i][j] = Math.max(f[i - 1][j], f[i - 1][j ^ x] + 1);
        }
    }

    if (f[n][target] < 0) {
        return -1;
    }
    return n - f[n][target];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
