---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3098.Find%20the%20Sum%20of%20Subsequence%20Powers/README_EN.md
rating: 2552
source: Biweekly Contest 127 Q4
tags:
    - Array
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [3098. Find the Sum of Subsequence Powers](https://leetcode.com/problems/find-the-sum-of-subsequence-powers)

[中文文档](/solution/3000-3099/3098.Find%20the%20Sum%20of%20Subsequence%20Powers/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>, and a <strong>positive</strong> integer <code>k</code>.</p>

<p>The <strong>power</strong> of a <span data-keyword="subsequence-array">subsequence</span> is defined as the <strong>minimum</strong> absolute difference between <strong>any</strong> two elements in the subsequence.</p>

<p>Return <em>the <strong>sum</strong> of <strong>powers</strong> of <strong>all</strong> subsequences of </em><code>nums</code><em> which have length</em> <strong><em>equal to</em></strong> <code>k</code>.</p>

<p>Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9 </sup>+ 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 4 subsequences in <code>nums</code> which have length 3: <code>[1,2,3]</code>, <code>[1,3,4]</code>, <code>[1,2,4]</code>, and <code>[2,3,4]</code>. The sum of powers is <code>|2 - 3| + |3 - 4| + |2 - 1| + |3 - 4| = 4</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The only subsequence in <code>nums</code> which has length 2 is&nbsp;<code>[2,2]</code>. The sum of powers is <code>|2 - 2| = 0</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,-1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 3 subsequences in <code>nums</code> which have length 2: <code>[4,3]</code>, <code>[4,-1]</code>, and <code>[3,-1]</code>. The sum of powers is <code>|4 - 3| + |4 - (-1)| + |3 - (-1)| = 10</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 50</code></li>
	<li><code>-10<sup>8</sup> &lt;= nums[i] &lt;= 10<sup>8</sup> </code></li>
	<li><code>2 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

We design a function $dfs(i, j, k, mi)$, which represents the energy sum value when we are currently processing the $i$-th element, the last selected element is the $j$-th element, we still need to select $k$ elements, and the current minimum difference is $mi$. The answer is $dfs(0, n, k, +\infty)$.

The execution process of the function $dfs(i, j, k, mi)$ is as follows:

-   If $i \geq n$, it means that all elements have been processed. If $k = 0$, return $mi$, otherwise return $0$;
-   Otherwise, we can choose not to select the $i$-th element, and the energy sum obtained is $dfs(i + 1, j, k, mi)$;
-   We can also choose to select the $i$-th element. If $j = n$, it means that no element has been selected before, and the energy sum obtained is $dfs(i + 1, i, k - 1, mi)$; otherwise, the energy sum obtained is $dfs(i + 1, i, k - 1, \min(mi, \text{nums}[i] - \text{nums}[j]))$.
-   We add up the above results, take the modulus of $10^9 + 7$, and return.

To avoid repeated calculations, we can use the method of memoization search to save the calculated results.

The time complexity is $O(n^4 \times k)$, and the space complexity is $O(n^4 \times k)$. Where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOfPowers(self, nums: List[int], k: int) -> int:
        @cache
        def dfs(i: int, j: int, k: int, mi: int) -> int:
            if i >= n:
                return mi if k == 0 else 0
            ans = dfs(i + 1, j, k, mi)
            if j == n:
                ans += dfs(i + 1, i, k - 1, mi)
            else:
                ans += dfs(i + 1, i, k - 1, min(mi, nums[i] - nums[j]))
            ans %= mod
            return ans

        mod = 10**9 + 7
        n = len(nums)
        nums.sort()
        return dfs(0, n, k, inf)
```

#### Java

```java
class Solution {
    private Map<Long, Integer> f = new HashMap<>();
    private final int mod = (int) 1e9 + 7;
    private int[] nums;

    public int sumOfPowers(int[] nums, int k) {
        Arrays.sort(nums);
        this.nums = nums;
        return dfs(0, nums.length, k, Integer.MAX_VALUE);
    }

    private int dfs(int i, int j, int k, int mi) {
        if (i >= nums.length) {
            return k == 0 ? mi : 0;
        }
        long key = (1L * mi) << 18 | (i << 12) | (j << 6) | k;
        if (f.containsKey(key)) {
            return f.get(key);
        }
        int ans = dfs(i + 1, j, k, mi);
        if (j == nums.length) {
            ans += dfs(i + 1, i, k - 1, mi);
        } else {
            ans += dfs(i + 1, i, k - 1, Math.min(mi, nums[i] - nums[j]));
        }
        ans %= mod;
        f.put(key, ans);
        return ans;
    }
}
```

#### Go

```go
func sumOfPowers(nums []int, k int) int {
	const mod int = 1e9 + 7
	sort.Ints(nums)
	n := len(nums)
	f := map[int]int{}
	var dfs func(i, j, k, mi int) int
	dfs = func(i, j, k, mi int) int {
		if i >= n {
			if k == 0 {
				return mi
			}
			return 0
		}
		key := mi<<18 | (i << 12) | (j << 6) | k
		if v, ok := f[key]; ok {
			return v
		}
		ans := dfs(i+1, j, k, mi)
		if j == n {
			ans += dfs(i+1, i, k-1, mi)
		} else {
			ans += dfs(i+1, i, k-1, min(mi, nums[i]-nums[j]))
		}
		ans %= mod
		f[key] = ans
		return ans
	}
	return dfs(0, n, k, math.MaxInt)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
