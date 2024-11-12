---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3351.Sum%20of%20Good%20Subsequences/README_EN.md
tags:
    - Array
    - Hash Table
    - Dynamic Programming
---

<!-- problem:start -->

# [3351. Sum of Good Subsequences](https://leetcode.com/problems/sum-of-good-subsequences)

[中文文档](/solution/3300-3399/3351.Sum%20of%20Good%20Subsequences/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>. A <strong>good </strong><span data-keyword="subsequence-array">subsequence</span> is defined as a subsequence of <code>nums</code> where the absolute difference between any <strong>two</strong> consecutive elements in the subsequence is <strong>exactly</strong> 1.</p>

<p>Return the <strong>sum</strong> of all <em>possible</em> <strong>good subsequences</strong> of <code>nums</code>.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p><strong>Note </strong>that a subsequence of size 1 is considered good by definition.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Good subsequences are: <code>[1]</code>, <code>[2]</code>, <code>[1]</code>, <code>[1,2]</code>, <code>[2,1]</code>, <code>[1,2,1]</code>.</li>
	<li>The sum of elements in these subsequences is 14.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">40</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Good subsequences are: <code>[3]</code>, <code>[4]</code>, <code>[5]</code>, <code>[3,4]</code>, <code>[4,5]</code>, <code>[3,4,5]</code>.</li>
	<li>The sum of elements in these subsequences is 40.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOfGoodSubsequences(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        f = defaultdict(int)
        g = defaultdict(int)
        for x in nums:
            f[x] += x
            g[x] += 1
            f[x] += f[x - 1] + g[x - 1] * x
            g[x] += g[x - 1]
            f[x] += f[x + 1] + g[x + 1] * x
            g[x] += g[x + 1]
        return sum(f.values()) % mod
```

#### Java

```java
class Solution {
    public int sumOfGoodSubsequences(int[] nums) {
        final int mod = (int) 1e9 + 7;
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        long[] f = new long[mx + 1];
        long[] g = new long[mx + 1];
        for (int x : nums) {
            f[x] += x;
            g[x] += 1;
            if (x > 0) {
                f[x] = (f[x] + f[x - 1] + g[x - 1] * x % mod) % mod;
                g[x] = (g[x] + g[x - 1]) % mod;
            }
            if (x + 1 <= mx) {
                f[x] = (f[x] + f[x + 1] + g[x + 1] * x % mod) % mod;
                g[x] = (g[x] + g[x + 1]) % mod;
            }
        }
        long ans = 0;
        for (long x : f) {
            ans = (ans + x) % mod;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sumOfGoodSubsequences(vector<int>& nums) {
        const int mod = 1e9 + 7;
        int mx = ranges::max(nums);

        vector<long long> f(mx + 1), g(mx + 1);
        for (int x : nums) {
            f[x] += x;
            g[x] += 1;

            if (x > 0) {
                f[x] = (f[x] + f[x - 1] + g[x - 1] * x % mod) % mod;
                g[x] = (g[x] + g[x - 1]) % mod;
            }

            if (x + 1 <= mx) {
                f[x] = (f[x] + f[x + 1] + g[x + 1] * x % mod) % mod;
                g[x] = (g[x] + g[x + 1]) % mod;
            }
        }

        return accumulate(f.begin(), f.end(), 0LL) % mod;
    }
};
```

#### Go

```go
func sumOfGoodSubsequences(nums []int) (ans int) {
	mod := int(1e9 + 7)
	mx := slices.Max(nums)

	f := make([]int, mx+1)
	g := make([]int, mx+1)

	for _, x := range nums {
		f[x] += x
		g[x] += 1

		if x > 0 {
			f[x] = (f[x] + f[x-1] + g[x-1]*x%mod) % mod
			g[x] = (g[x] + g[x-1]) % mod
		}

		if x+1 <= mx {
			f[x] = (f[x] + f[x+1] + g[x+1]*x%mod) % mod
			g[x] = (g[x] + g[x+1]) % mod
		}
	}

	for _, x := range f {
		ans = (ans + x) % mod
	}
	return
}
```

#### TypeScript

```ts
function sumOfGoodSubsequences(nums: number[]): number {
    const mod = 10 ** 9 + 7;
    const mx = Math.max(...nums);
    const f: number[] = Array(mx + 1).fill(0);
    const g: number[] = Array(mx + 1).fill(0);
    for (const x of nums) {
        f[x] += x;
        g[x] += 1;
        if (x > 0) {
            f[x] = (f[x] + f[x - 1] + ((g[x - 1] * x) % mod)) % mod;
            g[x] = (g[x] + g[x - 1]) % mod;
        }
        if (x + 1 <= mx) {
            f[x] = (f[x] + f[x + 1] + ((g[x + 1] * x) % mod)) % mod;
            g[x] = (g[x] + g[x + 1]) % mod;
        }
    }
    return f.reduce((acc, cur) => (acc + cur) % mod, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
