---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3077.Maximum%20Strength%20of%20K%20Disjoint%20Subarrays/README_EN.md
rating: 2556
source: Weekly Contest 388 Q4
tags:
    - Array
    - Dynamic Programming
    - Prefix Sum
---

<!-- problem:start -->

# [3077. Maximum Strength of K Disjoint Subarrays](https://leetcode.com/problems/maximum-strength-of-k-disjoint-subarrays)

[中文文档](/solution/3000-3099/3077.Maximum%20Strength%20of%20K%20Disjoint%20Subarrays/README.md)

## Description

<!-- description:start -->

<p>You are given an array of integers <code>nums</code> with length <code>n</code>, and a positive <strong>odd</strong> integer <code>k</code>.</p>

<p>Select exactly <b><code>k</code></b> disjoint <span data-keyword="subarray-nonempty">subarrays</span> <b><code>sub<sub>1</sub>, sub<sub>2</sub>, ..., sub<sub>k</sub></code></b> from <code>nums</code> such that the last element of <code>sub<sub>i</sub></code> appears before the first element of <code>sub<sub>{i+1}</sub></code> for all <code>1 &lt;= i &lt;= k-1</code>. The goal is to maximize their combined strength.</p>

<p>The strength of the selected subarrays is defined as:</p>

<p><code>strength = k * sum(sub<sub>1</sub>)- (k - 1) * sum(sub<sub>2</sub>) + (k - 2) * sum(sub<sub>3</sub>) - ... - 2 * sum(sub<sub>{k-1}</sub>) + sum(sub<sub>k</sub>)</code></p>

<p>where <b><code>sum(sub<sub>i</sub>)</code></b> is the sum of the elements in the <code>i</code>-th subarray.</p>

<p>Return the <strong>maximum</strong> possible strength that can be obtained from selecting exactly <b><code>k</code></b> disjoint subarrays from <code>nums</code>.</p>

<p><strong>Note</strong> that the chosen subarrays <strong>don&#39;t</strong> need to cover the entire array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,-1,2], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">22</span></p>

<p><strong>Explanation:</strong></p>

<p>The best possible way to select 3 subarrays is: nums[0..2], nums[3..3], and nums[4..4]. The strength is calculated as follows:</p>

<p><code>strength = 3 * (1 + 2 + 3) - 2 * (-1) + 2 = 22</code></p>

<p>&nbsp;</p>

<p><strong class="example">Example 2:</strong></p>

<p><strong>Input:</strong> <span class="example-io">nums = [12,-2,-2,-2,-2], k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">64</span></p>

<p><strong>Explanation:</strong></p>

<p>The only possible way to select 5 disjoint subarrays is: nums[0..0], nums[1..1], nums[2..2], nums[3..3], and nums[4..4]. The strength is calculated as follows:</p>

<p><code>strength = 5 * 12 - 4 * (-2) + 3 * (-2) - 2 * (-2) + (-2) = 64</code></p>

<p><strong class="example">Example 3:</strong></p>

<p><strong>Input:</strong> <span class="example-io">nums = [-1,-2,-3], k = </span>1</p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>The best possible way to select 1 subarray is: nums[0..0]. The strength is -1.</p>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li><code>1 &lt;= n * k &lt;= 10<sup>6</sup></code></li>
	<li><code>k</code> is odd.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

For the $i$th number $nums[i - 1]$, if it is selected and is in the $j$th subarray, then its contribution to the answer is $nums[i - 1] \times (k - j + 1) \times (-1)^{j+1}$. We denote $(-1)^{j+1}$ as $sign$, so its contribution to the answer is $sign \times nums[i - 1] \times (k - j + 1)$.

We define $f[i][j][0]$ as the maximum energy value when selecting $j$ subarrays from the first $i$ numbers, and the $i$th number is not selected. We define $f[i][j][1]$ as the maximum energy value when selecting $j$ subarrays from the first $i$ numbers, and the $i$th number is selected. Initially, $f[0][0][1] = 0$, and the rest of the values are $-\infty$.

When $i > 0$, we consider how $f[i][j]$ transitions.

If the $i$th number is not selected, then the $i-1$th number can either be selected or not selected, so $f[i][j][0] = \max(f[i-1][j][0], f[i-1][j][1])$.

If the $i$th number is selected, if the $i-1$th number and the $i$th number are in the same subarray, then $f[i][j][1] = \max(f[i][j][1], f[i-1][j][1] + sign \times nums[i-1] \times (k - j + 1))$, otherwise $f[i][j][1] = \max(f[i][j][1], \max(f[i-1][j-1][0], f[i-1][j-1][1]) + sign \times nums[i-1] \times (k - j + 1))$.

The final answer is $\max(f[n][k][0], f[n][k][1])$.

The time complexity is $O(n \times k)$, and the space complexity is $O(n \times k)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumStrength(self, nums: List[int], k: int) -> int:
        n = len(nums)
        f = [[[-inf, -inf] for _ in range(k + 1)] for _ in range(n + 1)]
        f[0][0][0] = 0
        for i, x in enumerate(nums, 1):
            for j in range(k + 1):
                sign = 1 if j & 1 else -1
                f[i][j][0] = max(f[i - 1][j][0], f[i - 1][j][1])
                f[i][j][1] = max(f[i][j][1], f[i - 1][j][1] + sign * x * (k - j + 1))
                if j:
                    f[i][j][1] = max(
                        f[i][j][1], max(f[i - 1][j - 1]) + sign * x * (k - j + 1)
                    )
        return max(f[n][k])
```

#### Java

```java
class Solution {
    public long maximumStrength(int[] nums, int k) {
        int n = nums.length;
        long[][][] f = new long[n + 1][k + 1][2];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                Arrays.fill(f[i][j], Long.MIN_VALUE / 2);
            }
        }
        f[0][0][0] = 0;
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = 0; j <= k; j++) {
                long sign = (j & 1) == 1 ? 1 : -1;
                long val = sign * x * (k - j + 1);
                f[i][j][0] = Math.max(f[i - 1][j][0], f[i - 1][j][1]);
                f[i][j][1] = Math.max(f[i][j][1], f[i - 1][j][1] + val);
                if (j > 0) {
                    long t = Math.max(f[i - 1][j - 1][0], f[i - 1][j - 1][1]) + val;
                    f[i][j][1] = Math.max(f[i][j][1], t);
                }
            }
        }
        return Math.max(f[n][k][0], f[n][k][1]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumStrength(vector<int>& nums, int k) {
        int n = nums.size();
        long long f[n + 1][k + 1][2];
        memset(f, -0x3f3f3f3f3f3f3f3f, sizeof(f));
        f[0][0][0] = 0;
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = 0; j <= k; j++) {
                long long sign = (j & 1) == 1 ? 1 : -1;
                long long val = sign * x * (k - j + 1);
                f[i][j][0] = max(f[i - 1][j][0], f[i - 1][j][1]);
                f[i][j][1] = max(f[i][j][1], f[i - 1][j][1] + val);
                if (j > 0) {
                    long long t = max(f[i - 1][j - 1][0], f[i - 1][j - 1][1]) + val;
                    f[i][j][1] = max(f[i][j][1], t);
                }
            }
        }
        return max(f[n][k][0], f[n][k][1]);
    }
};
```

#### Go

```go
func maximumStrength(nums []int, k int) int64 {
	n := len(nums)
	f := make([][][]int64, n+1)
	const inf int64 = math.MinInt64 / 2
	for i := range f {
		f[i] = make([][]int64, k+1)
		for j := range f[i] {
			f[i][j] = []int64{inf, inf}
		}
	}
	f[0][0][0] = 0
	for i := 1; i <= n; i++ {
		x := nums[i-1]
		for j := 0; j <= k; j++ {
			sign := int64(-1)
			if j&1 == 1 {
				sign = 1
			}
			val := sign * int64(x) * int64(k-j+1)
			f[i][j][0] = max(f[i-1][j][0], f[i-1][j][1])
			f[i][j][1] = max(f[i][j][1], f[i-1][j][1]+val)
			if j > 0 {
				t := max(f[i-1][j-1][0], f[i-1][j-1][1]) + val
				f[i][j][1] = max(f[i][j][1], t)
			}
		}
	}
	return max(f[n][k][0], f[n][k][1])
}
```

#### TypeScript

```ts
function maximumStrength(nums: number[], k: number): number {
    const n: number = nums.length;
    const f: number[][][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: k + 1 }, () => [-Infinity, -Infinity]),
    );
    f[0][0][0] = 0;
    for (let i = 1; i <= n; i++) {
        const x: number = nums[i - 1];
        for (let j = 0; j <= k; j++) {
            const sign: number = (j & 1) === 1 ? 1 : -1;
            const val: number = sign * x * (k - j + 1);
            f[i][j][0] = Math.max(f[i - 1][j][0], f[i - 1][j][1]);
            f[i][j][1] = Math.max(f[i][j][1], f[i - 1][j][1] + val);
            if (j > 0) {
                f[i][j][1] = Math.max(f[i][j][1], Math.max(...f[i - 1][j - 1]) + val);
            }
        }
    }
    return Math.max(...f[n][k]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
