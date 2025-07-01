---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3599.Partition%20Array%20to%20Minimize%20XOR/README_EN.md
tags:
    - Bit Manipulation
    - Array
    - Dynamic Programming
    - Prefix Sum
---

<!-- problem:start -->

# [3599. Partition Array to Minimize XOR](https://leetcode.com/problems/partition-array-to-minimize-xor)

[中文文档](/solution/3500-3599/3599.Partition%20Array%20to%20Minimize%20XOR/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>Your task is to partition <code>nums</code> into <code>k</code><strong> </strong>non-empty <strong><span data-keyword="subarray-nonempty">subarrays</span></strong>. For each subarray, compute the bitwise <strong>XOR</strong> of all its elements.</p>

<p>Return the <strong>minimum</strong> possible value of the <strong>maximum XOR</strong> among these <code>k</code> subarrays.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal partition is <code>[1]</code> and <code>[2, 3]</code>.</p>

<ul>
	<li>XOR of the first subarray is <code>1</code>.</li>
	<li>XOR of the second subarray is <code>2 XOR 3 = 1</code>.</li>
</ul>

<p>The maximum XOR among the subarrays is 1, which is the minimum possible.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,3,2], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal partition is <code>[2]</code>, <code>[3, 3]</code>, and <code>[2]</code>.</p>

<ul>
	<li>XOR of the first subarray is <code>2</code>.</li>
	<li>XOR of the second subarray is <code>3 XOR 3 = 0</code>.</li>
	<li>XOR of the third subarray is <code>2</code>.</li>
</ul>

<p>The maximum XOR among the subarrays is 2, which is the minimum possible.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2,3,1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal partition is <code>[1, 1]</code> and <code>[2, 3, 1]</code>.</p>

<ul>
	<li>XOR of the first subarray is <code>1 XOR 1 = 0</code>.</li>
	<li>XOR of the second subarray is <code>2 XOR 3 XOR 1 = 0</code>.</li>
</ul>

<p>The maximum XOR among the subarrays is 0, which is the minimum possible.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 250</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j]$ as the minimum possible value of the maximum XOR among all ways to partition the first $i$ elements into $j$ subarrays. Initially, set $f[0][0] = 0$, and all other $f[i][j] = +\infty$.

To quickly compute the XOR of a subarray, we can use a prefix XOR array $g$, where $g[i]$ represents the XOR of the first $i$ elements. For the subarray $[h + 1...i]$ (with indices starting from $1$), its XOR value is $g[i] \oplus g[h]$.

Next, we iterate $i$ from $1$ to $n$, $j$ from $1$ to $\min(i, k)$, and $h$ from $j - 1$ to $i - 1$, where $h$ represents the end position of the previous subarray (indices starting from $1$). We update $f[i][j]$ using the following state transition equation:

$$
f[i][j] = \min_{h \in [j - 1, i - 1]} \max(f[h][j - 1], g[i] \oplus g[h])
$$

Finally, we return $f[n][k]$, which is the minimum possible value of the maximum XOR when partitioning the entire array into $k$ subarrays.

The time complexity is $O(n^2 \times k)$, and the space complexity is $O(n \times k)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
min = lambda a, b: a if a < b else b
max = lambda a, b: a if a > b else b


class Solution:
    def minXor(self, nums: List[int], k: int) -> int:
        n = len(nums)
        g = [0] * (n + 1)
        for i, x in enumerate(nums, 1):
            g[i] = g[i - 1] ^ x

        f = [[inf] * (k + 1) for _ in range(n + 1)]
        f[0][0] = 0
        for i in range(1, n + 1):
            for j in range(1, min(i, k) + 1):
                for h in range(j - 1, i):
                    f[i][j] = min(f[i][j], max(f[h][j - 1], g[i] ^ g[h]))
        return f[n][k]
```

#### Java

```java
class Solution {
    public int minXor(int[] nums, int k) {
        int n = nums.length;
        int[] g = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            g[i] = g[i - 1] ^ nums[i - 1];
        }

        int[][] f = new int[n + 1][k + 1];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
        }
        f[0][0] = 0;

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= Math.min(i, k); ++j) {
                for (int h = j - 1; h < i; ++h) {
                    f[i][j] = Math.min(f[i][j], Math.max(f[h][j - 1], g[i] ^ g[h]));
                }
            }
        }

        return f[n][k];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minXor(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> g(n + 1);
        for (int i = 1; i <= n; ++i) {
            g[i] = g[i - 1] ^ nums[i - 1];
        }

        const int inf = numeric_limits<int>::max();
        vector f(n + 1, vector(k + 1, inf));
        f[0][0] = 0;

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= min(i, k); ++j) {
                for (int h = j - 1; h < i; ++h) {
                    f[i][j] = min(f[i][j], max(f[h][j - 1], g[i] ^ g[h]));
                }
            }
        }

        return f[n][k];
    }
};
```

#### Go

```go
func minXor(nums []int, k int) int {
	n := len(nums)
	g := make([]int, n+1)
	for i := 1; i <= n; i++ {
		g[i] = g[i-1] ^ nums[i-1]
	}

	const inf = math.MaxInt32
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
		for j := range f[i] {
			f[i][j] = inf
		}
	}
	f[0][0] = 0

	for i := 1; i <= n; i++ {
		for j := 1; j <= min(i, k); j++ {
			for h := j - 1; h < i; h++ {
				f[i][j] = min(f[i][j], max(f[h][j-1], g[i]^g[h]))
			}
		}
	}

	return f[n][k]
}
```

#### TypeScript

```ts
function minXor(nums: number[], k: number): number {
    const n = nums.length;
    const g: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        g[i] = g[i - 1] ^ nums[i - 1];
    }

    const inf = Number.MAX_SAFE_INTEGER;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(inf));
    f[0][0] = 0;

    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j <= Math.min(i, k); ++j) {
            for (let h = j - 1; h < i; ++h) {
                f[i][j] = Math.min(f[i][j], Math.max(f[h][j - 1], g[i] ^ g[h]));
            }
        }
    }

    return f[n][k];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
