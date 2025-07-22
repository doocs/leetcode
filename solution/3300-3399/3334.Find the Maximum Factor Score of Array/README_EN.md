---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3334.Find%20the%20Maximum%20Factor%20Score%20of%20Array/README_EN.md
rating: 1518
source: Weekly Contest 421 Q1
tags:
    - Array
    - Math
    - Number Theory
---

<!-- problem:start -->

# [3334. Find the Maximum Factor Score of Array](https://leetcode.com/problems/find-the-maximum-factor-score-of-array)

[中文文档](/solution/3300-3399/3334.Find%20the%20Maximum%20Factor%20Score%20of%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>The <strong>factor score</strong> of an array is defined as the <em>product</em> of the LCM and GCD of all elements of that array.</p>

<p>Return the <strong>maximum factor score</strong> of <code>nums</code> after removing <strong>at most</strong> one element from it.</p>

<p><strong>Note</strong> that <em>both</em> the <span data-keyword="lcm-function">LCM</span> and <span data-keyword="gcd-function">GCD</span> of a single number are the number itself, and the <em>factor score</em> of an <strong>empty</strong> array is 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,8,16]</span></p>

<p><strong>Output:</strong> <span class="example-io">64</span></p>

<p><strong>Explanation:</strong></p>

<p>On removing 2, the GCD of the rest of the elements is 4 while the LCM is 16, which gives a maximum factor score of <code>4 * 16 = 64</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">60</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum factor score of 60 can be obtained without removing any elements.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3]</span></p>

<p><strong>Output:</strong> 9</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 30</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, nums: List[int]) -> int:
        n = len(nums)
        suf_gcd = [0] * (n + 1)
        suf_lcm = [0] * n + [1]
        for i in range(n - 1, -1, -1):
            suf_gcd[i] = gcd(suf_gcd[i + 1], nums[i])
            suf_lcm[i] = lcm(suf_lcm[i + 1], nums[i])
        ans = suf_gcd[0] * suf_lcm[0]
        pre_gcd, pre_lcm = 0, 1
        for i, x in enumerate(nums):
            ans = max(ans, gcd(pre_gcd, suf_gcd[i + 1]) * lcm(pre_lcm, suf_lcm[i + 1]))
            pre_gcd = gcd(pre_gcd, x)
            pre_lcm = lcm(pre_lcm, x)
        return ans
```

#### Java

```java
class Solution {
    public long maxScore(int[] nums) {
        int n = nums.length;
        long[] sufGcd = new long[n + 1];
        long[] sufLcm = new long[n + 1];
        sufLcm[n] = 1;
        for (int i = n - 1; i >= 0; --i) {
            sufGcd[i] = gcd(sufGcd[i + 1], nums[i]);
            sufLcm[i] = lcm(sufLcm[i + 1], nums[i]);
        }
        long ans = sufGcd[0] * sufLcm[0];
        long preGcd = 0, preLcm = 1;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, gcd(preGcd, sufGcd[i + 1]) * lcm(preLcm, sufLcm[i + 1]));
            preGcd = gcd(preGcd, nums[i]);
            preLcm = lcm(preLcm, nums[i]);
        }
        return ans;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxScore(vector<int>& nums) {
        int n = nums.size();
        vector<long long> sufGcd(n + 1, 0);
        vector<long long> sufLcm(n + 1, 1);
        for (int i = n - 1; i >= 0; --i) {
            sufGcd[i] = gcd(sufGcd[i + 1], nums[i]);
            sufLcm[i] = lcm(sufLcm[i + 1], nums[i]);
        }

        long long ans = sufGcd[0] * sufLcm[0];
        long long preGcd = 0, preLcm = 1;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, gcd(preGcd, sufGcd[i + 1]) * lcm(preLcm, sufLcm[i + 1]));
            preGcd = gcd(preGcd, nums[i]);
            preLcm = lcm(preLcm, nums[i]);
        }
        return ans;
    }
};
```

#### Go

```go
func maxScore(nums []int) int64 {
	n := len(nums)
	sufGcd := make([]int64, n+1)
	sufLcm := make([]int64, n+1)
	sufLcm[n] = 1
	for i := n - 1; i >= 0; i-- {
		sufGcd[i] = gcd(sufGcd[i+1], int64(nums[i]))
		sufLcm[i] = lcm(sufLcm[i+1], int64(nums[i]))
	}

	ans := sufGcd[0] * sufLcm[0]
	preGcd, preLcm := int64(0), int64(1)
	for i := 0; i < n; i++ {
		ans = max(ans, gcd(preGcd, sufGcd[i+1])*lcm(preLcm, sufLcm[i+1]))
		preGcd = gcd(preGcd, int64(nums[i]))
		preLcm = lcm(preLcm, int64(nums[i]))
	}
	return ans
}

func gcd(a, b int64) int64 {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}

func lcm(a, b int64) int64 {
	return a / gcd(a, b) * b
}
```

#### TypeScript

```ts
function maxScore(nums: number[]): number {
    const n = nums.length;
    const sufGcd: number[] = Array(n + 1).fill(0);
    const sufLcm: number[] = Array(n + 1).fill(1);
    for (let i = n - 1; i >= 0; i--) {
        sufGcd[i] = gcd(sufGcd[i + 1], nums[i]);
        sufLcm[i] = lcm(sufLcm[i + 1], nums[i]);
    }

    let ans = sufGcd[0] * sufLcm[0];
    let preGcd = 0,
        preLcm = 1;
    for (let i = 0; i < n; i++) {
        ans = Math.max(ans, gcd(preGcd, sufGcd[i + 1]) * lcm(preLcm, sufLcm[i + 1]));
        preGcd = gcd(preGcd, nums[i]);
        preLcm = lcm(preLcm, nums[i]);
    }
    return ans;
}

function gcd(a: number, b: number): number {
    return b === 0 ? a : gcd(b, a % b);
}

function lcm(a: number, b: number): number {
    return (a / gcd(a, b)) * b;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
