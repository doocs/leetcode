---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2447.Number%20of%20Subarrays%20With%20GCD%20Equal%20to%20K/README_EN.md
rating: 1602
source: Weekly Contest 316 Q2
tags:
    - Array
    - Math
    - Number Theory
---

<!-- problem:start -->

# [2447. Number of Subarrays With GCD Equal to K](https://leetcode.com/problems/number-of-subarrays-with-gcd-equal-to-k)

[中文文档](/solution/2400-2499/2447.Number%20of%20Subarrays%20With%20GCD%20Equal%20to%20K/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the number of <strong>subarrays</strong> of </em><code>nums</code><em> where the greatest common divisor of the subarray&#39;s elements is </em><code>k</code>.</p>

<p>A <strong>subarray</strong> is a contiguous non-empty sequence of elements within an array.</p>

<p>The <strong>greatest common divisor of an array</strong> is the largest integer that evenly divides all the array elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [9,3,1,2,6,3], k = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> The subarrays of nums where 3 is the greatest common divisor of all the subarray&#39;s elements are:
- [9,<u><strong>3</strong></u>,1,2,6,3]
- [9,3,1,2,6,<u><strong>3</strong></u>]
- [<u><strong>9,3</strong></u>,1,2,6,3]
- [9,3,1,2,<u><strong>6,3</strong></u>]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4], k = 7
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no subarrays of nums where 7 is the greatest common divisor of all the subarray&#39;s elements.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Direct Enumeration

We can enumerate $nums[i]$ as the left endpoint of the subarray, and then enumerate $nums[j]$ as the right endpoint of the subarray, where $i \le j$. During the enumeration of the right endpoint, we can use a variable $g$ to maintain the greatest common divisor of the current subarray. Each time we enumerate a new right endpoint, we update the greatest common divisor $g = \gcd(g, nums[j])$. If $g=k$, then the greatest common divisor of the current subarray equals $k$, and we increase the answer by $1$.

After the enumeration ends, return the answer.

The time complexity is $O(n \times (n + \log M))$, where $n$ and $M$ are the length of the array $nums$ and the maximum value in the array $nums$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def subarrayGCD(self, nums: List[int], k: int) -> int:
        ans = 0
        for i in range(len(nums)):
            g = 0
            for x in nums[i:]:
                g = gcd(g, x)
                ans += g == k
        return ans
```

#### Java

```java
class Solution {
    public int subarrayGCD(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int g = 0;
            for (int j = i; j < n; ++j) {
                g = gcd(g, nums[j]);
                if (g == k) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int subarrayGCD(vector<int>& nums, int k) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int g = 0;
            for (int j = i; j < n; ++j) {
                g = gcd(g, nums[j]);
                ans += g == k;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func subarrayGCD(nums []int, k int) (ans int) {
	for i := range nums {
		g := 0
		for _, x := range nums[i:] {
			g = gcd(g, x)
			if g == k {
				ans++
			}
		}
	}
	return
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

#### TypeScript

```ts
function subarrayGCD(nums: number[], k: number): number {
    let ans = 0;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        let g = 0;
        for (let j = i; j < n; ++j) {
            g = gcd(g, nums[j]);
            if (g === k) {
                ++ans;
            }
        }
    }
    return ans;
}

function gcd(a: number, b: number): number {
    return b === 0 ? a : gcd(b, a % b);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
