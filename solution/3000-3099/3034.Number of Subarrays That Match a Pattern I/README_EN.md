---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3034.Number%20of%20Subarrays%20That%20Match%20a%20Pattern%20I/README_EN.md
rating: 1383
source: Weekly Contest 384 Q2
tags:
    - Array
    - String Matching
    - Hash Function
    - Rolling Hash
---

<!-- problem:start -->

# [3034. Number of Subarrays That Match a Pattern I](https://leetcode.com/problems/number-of-subarrays-that-match-a-pattern-i)

[中文文档](/solution/3000-3099/3034.Number%20of%20Subarrays%20That%20Match%20a%20Pattern%20I/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of size <code>n</code>, and a <strong>0-indexed</strong> integer array <code>pattern</code> of size <code>m</code> consisting of integers <code>-1</code>, <code>0</code>, and <code>1</code>.</p>

<p>A <span data-keyword="subarray">subarray</span> <code>nums[i..j]</code> of size <code>m + 1</code> is said to match the <code>pattern</code> if the following conditions hold for each element <code>pattern[k]</code>:</p>

<ul>
	<li><code>nums[i + k + 1] &gt; nums[i + k]</code> if <code>pattern[k] == 1</code>.</li>
	<li><code>nums[i + k + 1] == nums[i + k]</code> if <code>pattern[k] == 0</code>.</li>
	<li><code>nums[i + k + 1] &lt; nums[i + k]</code> if <code>pattern[k] == -1</code>.</li>
</ul>

<p>Return <em>the<strong> count</strong> of subarrays in</em> <code>nums</code> <em>that match the</em> <code>pattern</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6], pattern = [1,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The pattern [1,1] indicates that we are looking for strictly increasing subarrays of size 3. In the array nums, the subarrays [1,2,3], [2,3,4], [3,4,5], and [4,5,6] match this pattern.
Hence, there are 4 subarrays in nums that match the pattern.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,4,1,3,5,5,3], pattern = [1,0,-1]
<strong>Output:</strong> 2
<strong>Explanation: </strong>Here, the pattern [1,0,-1] indicates that we are looking for a sequence where the first number is smaller than the second, the second is equal to the third, and the third is greater than the fourth. In the array nums, the subarrays [1,4,4,1], and [3,5,5,3] match this pattern.
Hence, there are 2 subarrays in nums that match the pattern.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m == pattern.length &lt; n</code></li>
	<li><code>-1 &lt;= pattern[i] &lt;= 1</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can enumerate all subarrays of array `nums` with a length of $m + 1$, and then check whether they match the pattern array `pattern`. If they do, we increment the answer by one.

The time complexity is $O(n \times m)$, where $n$ and $m$ are the lengths of the arrays `nums` and `pattern` respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def countMatchingSubarrays(self, nums: List[int], pattern: List[int]) -> int:
        def f(a: int, b: int) -> int:
            return 0 if a == b else (1 if a < b else -1)

        ans = 0
        for i in range(len(nums) - len(pattern)):
            ans += all(
                f(nums[i + k], nums[i + k + 1]) == p for k, p in enumerate(pattern)
            )
        return ans
```

```java
class Solution {
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int n = nums.length, m = pattern.length;
        int ans = 0;
        for (int i = 0; i < n - m; ++i) {
            int ok = 1;
            for (int k = 0; k < m && ok == 1; ++k) {
                if (f(nums[i + k], nums[i + k + 1]) != pattern[k]) {
                    ok = 0;
                }
            }
            ans += ok;
        }
        return ans;
    }

    private int f(int a, int b) {
        return a == b ? 0 : (a < b ? 1 : -1);
    }
}
```

```cpp
class Solution {
public:
    int countMatchingSubarrays(vector<int>& nums, vector<int>& pattern) {
        int n = nums.size(), m = pattern.size();
        int ans = 0;
        auto f = [](int a, int b) {
            return a == b ? 0 : (a < b ? 1 : -1);
        };
        for (int i = 0; i < n - m; ++i) {
            int ok = 1;
            for (int k = 0; k < m && ok == 1; ++k) {
                if (f(nums[i + k], nums[i + k + 1]) != pattern[k]) {
                    ok = 0;
                }
            }
            ans += ok;
        }
        return ans;
    }
};
```

```go
func countMatchingSubarrays(nums []int, pattern []int) (ans int) {
	f := func(a, b int) int {
		if a == b {
			return 0
		}
		if a < b {
			return 1
		}
		return -1
	}
	n, m := len(nums), len(pattern)
	for i := 0; i < n-m; i++ {
		ok := 1
		for k := 0; k < m && ok == 1; k++ {
			if f(nums[i+k], nums[i+k+1]) != pattern[k] {
				ok = 0
			}
		}
		ans += ok
	}
	return
}
```

```ts
function countMatchingSubarrays(nums: number[], pattern: number[]): number {
    const f = (a: number, b: number) => (a === b ? 0 : a < b ? 1 : -1);
    const n = nums.length;
    const m = pattern.length;
    let ans = 0;
    for (let i = 0; i < n - m; ++i) {
        let ok = 1;
        for (let k = 0; k < m && ok; ++k) {
            if (f(nums[i + k], nums[i + k + 1]) !== pattern[k]) {
                ok = 0;
            }
        }
        ans += ok;
    }
    return ans;
}
```

```cs
public class Solution {
    public int CountMatchingSubarrays(int[] nums, int[] pattern) {
        int n = nums.Length, m = pattern.Length;
        int ans = 0;
        for (int i = 0; i < n - m; ++i) {
            int ok = 1;
            for (int k = 0; k < m && ok == 1; ++k) {
                if (f(nums[i + k], nums[i + k + 1]) != pattern[k]) {
                    ok = 0;
                }
            }
            ans += ok;
        }
        return ans;
    }

    private int f(int a, int b) {
        return a == b ? 0 : (a < b ? 1 : -1);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
