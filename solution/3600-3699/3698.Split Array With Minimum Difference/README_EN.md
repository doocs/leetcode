---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3698.Split%20Array%20With%20Minimum%20Difference/README_EN.md
rating: 1648
source: Weekly Contest 469 Q2
tags:
    - Array
    - Prefix Sum
---

<!-- problem:start -->

# [3698. Split Array With Minimum Difference](https://leetcode.com/problems/split-array-with-minimum-difference)

[中文文档](/solution/3600-3699/3698.Split%20Array%20With%20Minimum%20Difference/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Split the array into <strong>exactly</strong> two <span data-keyword="subarray-nonempty">subarrays</span>, <code>left</code> and <code>right</code>, such that <code>left</code> is <strong><span data-keyword="strictly-increasing-array">strictly increasing</span> </strong> and <code>right</code> is <strong><span data-keyword="strictly-decreasing-array">strictly decreasing</span></strong>.</p>

<p>Return the <strong>minimum possible absolute difference</strong> between the sums of <code>left</code> and <code>right</code>. If no valid split exists, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>left</code></th>
			<th style="border: 1px solid black;"><code>right</code></th>
			<th style="border: 1px solid black;">Validity</th>
			<th style="border: 1px solid black;"><code>left</code> sum</th>
			<th style="border: 1px solid black;"><code>right</code> sum</th>
			<th style="border: 1px solid black;">Absolute difference</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">[1]</td>
			<td style="border: 1px solid black;">[3, 2]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;"><code>|1 - 5| = 4</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[1, 3]</td>
			<td style="border: 1px solid black;">[2]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>|4 - 2| = 2</code></td>
		</tr>
	</tbody>
</table>

<p>Thus, the minimum absolute difference is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,4,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>left</code></th>
			<th style="border: 1px solid black;"><code>right</code></th>
			<th style="border: 1px solid black;">Validity</th>
			<th style="border: 1px solid black;"><code>left</code> sum</th>
			<th style="border: 1px solid black;"><code>right</code> sum</th>
			<th style="border: 1px solid black;">Absolute difference</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">[1]</td>
			<td style="border: 1px solid black;">[2, 4, 3]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">9</td>
			<td style="border: 1px solid black;">-</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[1, 2]</td>
			<td style="border: 1px solid black;">[4, 3]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;"><code>|3 - 7| = 4</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">[1, 2, 4]</td>
			<td style="border: 1px solid black;">[3]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;"><code>|7 - 3| = 4</code></td>
		</tr>
	</tbody>
</table>

<p>Thus, the minimum absolute difference is 4.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>No valid split exists, so the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Two Arrays to Record Monotonicity

We use a prefix sum array $s$ to record the prefix sum of the array, where $s[i]$ represents the sum of the array $[0,..i]$. Then we use two boolean arrays $f$ and $g$ to record the monotonicity of prefixes and suffixes respectively, where $f[i]$ indicates whether the array $[0,..i]$ is strictly increasing, and $g[i]$ indicates whether the array $[i,..n-1]$ is strictly decreasing.

Finally, we traverse array positions $i$ where $0 \leq i < n-1$. If both $f[i]$ and $g[i+1]$ are true, then we can calculate the sums of $left$ and $right$, which are $s[i]$ and $s[n-1]-s[i]$ respectively, and update the answer.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def splitArray(self, nums: List[int]) -> int:
        s = list(accumulate(nums))
        n = len(nums)
        f = [True] * n
        for i in range(1, n):
            f[i] = f[i - 1]
            if nums[i] <= nums[i - 1]:
                f[i] = False
        g = [True] * n
        for i in range(n - 2, -1, -1):
            g[i] = g[i + 1]
            if nums[i] <= nums[i + 1]:
                g[i] = False
        ans = inf
        for i in range(n - 1):
            if f[i] and g[i + 1]:
                s1 = s[i]
                s2 = s[n - 1] - s[i]
                ans = min(ans, abs(s1 - s2))
        return ans if ans < inf else -1
```

#### Java

```java
class Solution {
    public long splitArray(int[] nums) {
        int n = nums.length;
        long[] s = new long[n];
        s[0] = nums[0];
        boolean[] f = new boolean[n];
        Arrays.fill(f, true);
        boolean[] g = new boolean[n];
        Arrays.fill(g, true);
        for (int i = 1; i < n; ++i) {
            s[i] = s[i - 1] + nums[i];
            f[i] = f[i - 1];
            if (nums[i] <= nums[i - 1]) {
                f[i] = false;
            }
        }
        for (int i = n - 2; i >= 0; --i) {
            g[i] = g[i + 1];
            if (nums[i] <= nums[i + 1]) {
                g[i] = false;
            }
        }
        final long inf = Long.MAX_VALUE;
        long ans = inf;
        for (int i = 0; i < n - 1; ++i) {
            if (f[i] && g[i + 1]) {
                long s1 = s[i];
                long s2 = s[n - 1] - s[i];
                ans = Math.min(ans, Math.abs(s1 - s2));
            }
        }
        return ans < inf ? ans : -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long splitArray(vector<int>& nums) {
        int n = nums.size();
        vector<long long> s(n);
        s[0] = nums[0];
        vector<bool> f(n, true), g(n, true);

        for (int i = 1; i < n; ++i) {
            s[i] = s[i - 1] + nums[i];
            f[i] = f[i - 1];
            if (nums[i] <= nums[i - 1]) {
                f[i] = false;
            }
        }
        for (int i = n - 2; i >= 0; --i) {
            g[i] = g[i + 1];
            if (nums[i] <= nums[i + 1]) {
                g[i] = false;
            }
        }

        const long long inf = LLONG_MAX;
        long long ans = inf;
        for (int i = 0; i < n - 1; ++i) {
            if (f[i] && g[i + 1]) {
                long long s1 = s[i];
                long long s2 = s[n - 1] - s[i];
                ans = min(ans, llabs(s1 - s2));
            }
        }
        return ans < inf ? ans : -1;
    }
};
```

#### Go

```go
func splitArray(nums []int) int64 {
	n := len(nums)
	s := make([]int64, n)
	f := make([]bool, n)
	g := make([]bool, n)
	for i := range f {
		f[i] = true
		g[i] = true
	}

	s[0] = int64(nums[0])
	for i := 1; i < n; i++ {
		s[i] = s[i-1] + int64(nums[i])
		f[i] = f[i-1]
		if nums[i] <= nums[i-1] {
			f[i] = false
		}
	}
	for i := n - 2; i >= 0; i-- {
		g[i] = g[i+1]
		if nums[i] <= nums[i+1] {
			g[i] = false
		}
	}

	const inf = int64(^uint64(0) >> 1)
	ans := inf
	for i := 0; i < n-1; i++ {
		if f[i] && g[i+1] {
			s1 := s[i]
			s2 := s[n-1] - s[i]
			ans = min(ans, abs(s1-s2))
		}
	}
	if ans < inf {
		return ans
	}
	return -1
}

func abs(x int64) int64 {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function splitArray(nums: number[]): number {
    const n = nums.length;
    const s: number[] = Array(n);
    const f: boolean[] = Array(n).fill(true);
    const g: boolean[] = Array(n).fill(true);

    s[0] = nums[0];
    for (let i = 1; i < n; ++i) {
        s[i] = s[i - 1] + nums[i];
        f[i] = f[i - 1];
        if (nums[i] <= nums[i - 1]) {
            f[i] = false;
        }
    }

    for (let i = n - 2; i >= 0; --i) {
        g[i] = g[i + 1];
        if (nums[i] <= nums[i + 1]) {
            g[i] = false;
        }
    }

    const INF = Number.MAX_SAFE_INTEGER;
    let ans = INF;

    for (let i = 0; i < n - 1; ++i) {
        if (f[i] && g[i + 1]) {
            const s1 = s[i];
            const s2 = s[n - 1] - s[i];
            ans = Math.min(ans, Math.abs(s1 - s2));
        }
    }

    return ans < INF ? ans : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
