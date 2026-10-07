---
comments: true
difficulty: Medium
rating: 1559
source: Weekly Contest 319 Q2
tags:
    - Array
    - Math
    - Number Theory
---

<!-- problem:start -->

# [2470. Number of Subarrays With LCM Equal to K](https://leetcode.com/problems/number-of-subarrays-with-lcm-equal-to-k)

[中文文档](/solution/2400-2499/2470.Number%20of%20Subarrays%20With%20LCM%20Equal%20to%20K/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the number of <strong>subarrays</strong> of </em><code>nums</code><em> where the least common multiple of the subarray&#39;s elements is </em><code>k</code>.</p>

<p>A <strong>subarray</strong> is a contiguous non-empty sequence of elements within an array.</p>

<p>The <strong>least common multiple of an array</strong> is the smallest positive integer that is divisible by all the array elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,6,2,7,1], k = 6
<strong>Output:</strong> 4
<strong>Explanation:</strong> The subarrays of nums where 6 is the least common multiple of all the subarray&#39;s elements are:
- [<u><strong>3</strong></u>,<u><strong>6</strong></u>,2,7,1]
- [<u><strong>3</strong></u>,<u><strong>6</strong></u>,<u><strong>2</strong></u>,7,1]
- [3,<u><strong>6</strong></u>,2,7,1]
- [3,<u><strong>6</strong></u>,<u><strong>2</strong></u>,7,1]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3], k = 2
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no subarrays of nums where 2 is the least common multiple of all the subarray&#39;s elements.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

<!-- thinking:start -->

> **Thinking**
>
> With $n \le 1000$, fix the left end and extend to the right. A subarray whose LCM is $k$ can contain only divisors of $k$, so the first element that does not divide $k$ ends the segment. Until then the running LCM itself divides $k$ and stays at most $k$. Multiplying before dividing by the GCD overflows a 32-bit product even when the true LCM still fits, and the wrapped value can equal $k$.

<!-- thinking:end -->

Enumerate each index as the left end and extend to the right. Stop at the first value that does not divide $k$. While extending, the running LCM stays a divisor of $k$; count the positions where it equals $k$.

The time complexity is $O(n^2)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
from math import lcm


class Solution:
    def subarrayLCM(self, nums: List[int], k: int) -> int:
        ans = 0
        for i in range(len(nums)):
            a = 1
            for b in nums[i:]:
                if k % b:
                    break
                a = lcm(a, b)
                ans += a == k
        return ans
```

#### Java

```java
class Solution {
    public int subarrayLCM(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            int a = 1;
            for (int j = i; j < nums.length; ++j) {
                if (k % nums[j] != 0) {
                    break;
                }
                a = lcm(a, nums[j]);
                if (a == k) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
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
    int subarrayLCM(vector<int>& nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            int a = 1;
            for (int j = i; j < nums.size(); ++j) {
                if (k % nums[j] != 0) {
                    break;
                }
                a = lcm(a, nums[j]);
                ans += a == k;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func subarrayLCM(nums []int, k int) (ans int) {
	for i := range nums {
		a := 1
		for _, b := range nums[i:] {
			if k%b != 0 {
				break
			}
			a = lcm(a, b)
			if a == k {
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

func lcm(a, b int) int {
	return a / gcd(a, b) * b
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
