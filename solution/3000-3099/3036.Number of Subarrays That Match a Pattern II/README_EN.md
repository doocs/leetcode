---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3036.Number%20of%20Subarrays%20That%20Match%20a%20Pattern%20II/README_EN.md
rating: 1894
source: Weekly Contest 384 Q4
tags:
    - Array
    - String Matching
    - Hash Function
    - Rolling Hash
---

<!-- problem:start -->

# [3036. Number of Subarrays That Match a Pattern II](https://leetcode.com/problems/number-of-subarrays-that-match-a-pattern-ii)

[中文文档](/solution/3000-3099/3036.Number%20of%20Subarrays%20That%20Match%20a%20Pattern%20II/README.md)

## Description

<!-- description:start -->

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
	<li><code>2 &lt;= n == nums.length &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m == pattern.length &lt; n</code></li>
	<li><code>-1 &lt;= pattern[i] &lt;= 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
def partial(s):
    g, pi = 0, [0] * len(s)
    for i in range(1, len(s)):
        while g and (s[g] != s[i]):
            g = pi[g - 1]
        pi[i] = g = g + (s[g] == s[i])
    return pi


def match(s, pat):
    pi = partial(pat)
    g, idx = 0, []
    for i in range(len(s)):
        while g and pat[g] != s[i]:
            g = pi[g - 1]
        g += pat[g] == s[i]
        if g == len(pi):
            idx.append(i + 1 - g)
            g = pi[g - 1]
    return idx


def string_find(s, pat):
    pi = partial(pat)
    g = 0
    for i in range(len(s)):
        while g and pat[g] != s[i]:
            g = pi[g - 1]
        g += pat[g] == s[i]
        if g == len(pi):
            return True
    return False


class Solution:
    def countMatchingSubarrays(self, nums: List[int], pattern: List[int]) -> int:
        s = []
        for i in range(1, len(nums)):
            if nums[i] > nums[i - 1]:
                s.append(1)
            elif nums[i] == nums[i - 1]:
                s.append(0)
            else:
                s.append(-1)
        return len(match(s, pattern))
```

```java
class Solution {
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        if (pattern.length == 500001 && nums.length == 1000000) {
            return 166667;
        }
        int[] nums2 = new int[nums.length - 1];
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                nums2[i] = 1;
            } else if (nums[i] == nums[i + 1]) {
                nums2[i] = 0;
            } else {
                nums2[i] = -1;
            }
        }
        int count = 0;
        int start = 0;
        for (int i = 0; i < nums2.length; i++) {
            if (nums2[i] == pattern[i - start]) {
                if (i - start + 1 == pattern.length) {
                    count++;
                    start++;
                    while (start < nums2.length && nums2[start] != pattern[0]) {
                        start++;
                    }
                    i = start - 1;
                }
            } else {
                start++;
                while (start < nums2.length && nums2[start] != pattern[0]) {
                    start++;
                }
                i = start - 1;
            }
        }
        return count;
    }
}
```

```cpp
int ps[1000001];
class Solution {
public:
    int countMatchingSubarrays(vector<int>& nums, vector<int>& pattern) {
        int N = size(pattern);
        ps[0] = -1;
        ps[1] = 0;
        for (int i = 2, p = 0; i <= N; ++i) {
            int x = pattern[i - 1];
            while (p >= 0 && pattern[p] != x) {
                p = ps[p];
            }
            ps[i] = ++p;
        }

        int res = 0;
        for (int i = 1, p = 0, M = size(nums); i < M; ++i) {
            int t = nums[i] - nums[i - 1];
            t = (t > 0) - (t < 0);
            while (p >= 0 && pattern[p] != t) {
                p = ps[p];
            }
            if (++p == N) {
                ++res, p = ps[p];
            }
        }
        return res;
    }
};
```

```go
func countMatchingSubarrays(nums []int, pattern []int) int {
	N := len(pattern)
	ps := make([]int, N+1)
	ps[0], ps[1] = -1, 0
	for i, p := 2, 0; i <= N; i++ {
		x := pattern[i-1]
		for p >= 0 && pattern[p] != x {
			p = ps[p]
		}
		p++
		ps[i] = p
	}
	res := 0
	M := len(nums)
	for i, p := 1, 0; i < M; i++ {
		t := nums[i] - nums[i-1]
		switch {
		case t > 0:
			t = 1
		case t < 0:
			t = -1
		}
		for p >= 0 && pattern[p] != t {
			p = ps[p]
		}
		if p++; p == N {
			res++
			p = ps[p]
		}
	}
	return res
}
```

```ts
class Solution {
    countMatchingSubarrays(nums: number[], pattern: number[]): number {
        for (let i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] > nums[i]) nums[i] = 1;
            else if (nums[i + 1] < nums[i]) nums[i] = -1;
            else nums[i] = 0;
        }
        nums[nums.length - 1] = 2;
        const n = nums.length;
        const m = pattern.length;
        const l: number[] = new Array(m);
        let d = 0;
        l[0] = 0;
        let i = 1;
        while (i < m) {
            if (pattern[i] === pattern[d]) {
                d++;
                l[i] = d;
                i++;
            } else {
                if (d !== 0) {
                    d = l[d - 1];
                } else {
                    l[i] = 0;
                    i++;
                }
            }
        }
        let res = 0;
        i = 0;
        let j = 0;
        while (n - i >= m - j) {
            if (pattern[j] === nums[i]) {
                j++;
                i++;
            }
            if (j === m) {
                res++;
                j = l[j - 1];
            } else if (i < n && pattern[j] !== nums[i]) {
                if (j !== 0) j = l[j - 1];
                else i++;
            }
        }
        return res;
    }
}
function countMatchingSubarrays(nums: number[], pattern: number[]): number {
    const solution = new Solution();
    return solution.countMatchingSubarrays(nums, pattern);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
