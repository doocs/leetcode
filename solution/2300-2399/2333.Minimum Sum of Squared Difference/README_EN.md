---
comments: true
difficulty: Medium
rating: 2011
source: Biweekly Contest 82 Q3
tags:
    - Greedy
    - Array
    - Binary Search
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2333. Minimum Sum of Squared Difference](https://leetcode.com/problems/minimum-sum-of-squared-difference)

[中文文档](/solution/2300-2399/2333.Minimum%20Sum%20of%20Squared%20Difference/README.md)

## Description

<!-- description:start -->

<p>You are given two positive <strong>0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code>, both of length <code>n</code>.</p>

<p>The <strong>sum of squared difference</strong> of arrays <code>nums1</code> and <code>nums2</code> is defined as the <strong>sum</strong> of <code>(nums1[i] - nums2[i])<sup>2</sup></code> for each <code>0 &lt;= i &lt; n</code>.</p>

<p>You are also given two positive integers <code>k1</code> and <code>k2</code>. You can modify any of the elements of <code>nums1</code> by <code>+1</code> or <code>-1</code> at most <code>k1</code> times. Similarly, you can modify any of the elements of <code>nums2</code> by <code>+1</code> or <code>-1</code> at most <code>k2</code> times.</p>

<p>Return <em>the minimum <strong>sum of squared difference</strong> after modifying array </em><code>nums1</code><em> at most </em><code>k1</code><em> times and modifying array </em><code>nums2</code><em> at most </em><code>k2</code><em> times</em>.</p>

<p><strong>Note</strong>: You are allowed to modify the array elements to become <strong>negative</strong> integers.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,3,4], nums2 = [2,10,20,19], k1 = 0, k2 = 0
<strong>Output:</strong> 579
<strong>Explanation:</strong> The elements in nums1 and nums2 cannot be modified because k1 = 0 and k2 = 0. 
The sum of square difference will be: (1 - 2)<sup>2 </sup>+ (2 - 10)<sup>2 </sup>+ (3 - 20)<sup>2 </sup>+ (4 - 19)<sup>2</sup>&nbsp;= 579.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,4,10,12], nums2 = [5,8,6,9], k1 = 1, k2 = 1
<strong>Output:</strong> 43
<strong>Explanation:</strong> One way to obtain the minimum sum of square difference is: 
- Increase nums1[0] once.
- Increase nums2[2] once.
The minimum of the sum of square difference will be: 
(2 - 5)<sup>2 </sup>+ (4 - 8)<sup>2 </sup>+ (10 - 7)<sup>2 </sup>+ (12 - 9)<sup>2</sup>&nbsp;= 43.
Note that, there are other ways to obtain the minimum of the sum of square difference, but there is no way to obtain a sum smaller than 43.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k1, k2 &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Counting

<!-- thinking:start -->

> **Thinking**
>
> One $\pm 1$ on either array decreases the absolute difference at that index by one, so $k_1$ and $k_2$ form a single budget $k$. With $n \le 10^5$ and up to $2 \times 10^9$ operations, simulating each step or maintaining a heap of the current maxima is too slow.
>
> The square is strictly convex, so the same operation reduces the sum of squares by more when it is applied to a larger difference. Differences lie in $[0, 10^5]$, and equal values can be lowered together.
>
> Count the frequency of each difference and scan from the largest downward: if the remaining budget covers a whole level, merge that level into the next lower value; otherwise spend what is left and stop. When the sum of differences is already at most $k$, the answer is $0$.

<!-- thinking:end -->

Set $k = k_1 + k_2$ and let $d_i = |nums1_i - nums2_i|$. If the sum of these differences is at most $k$, every difference can be reduced to $0$ and the answer is $0$.

Otherwise, record the frequency of each difference in $cnt$, and let $m$ be the maximum difference. Scan $v$ downward from $m$: this level contains $cnt[v]$ values, and $\textit{take} = \min(cnt[v], k)$ of them can be decreased by one. Move those $\textit{take}$ counts to $v - 1$ and subtract $\textit{take}$ from $k$. Stop when $k$ is exhausted.

The answer is $\sum_v v^2 \cdot cnt[v]$.

The time complexity is $O(n + M)$ and the space complexity is $O(M)$, where $n$ is the array length and $M$ is the maximum difference. In this problem, $M \le 10^5$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSumSquareDiff(
        self, nums1: List[int], nums2: List[int], k1: int, k2: int
    ) -> int:
        k = k1 + k2
        s = mx = 0
        cnt = [0] * 100001
        for a, b in zip(nums1, nums2):
            v = abs(a - b)
            cnt[v] += 1
            s += v
            mx = max(mx, v)
        if s <= k:
            return 0
        for v in range(mx, 0, -1):
            if cnt[v] == 0:
                continue
            take = min(cnt[v], k)
            k -= take
            cnt[v] -= take
            cnt[v - 1] += take
            if k == 0:
                break
        return sum(v * v * cnt[v] for v in range(mx + 1))
```

#### Java

```java
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int k = k1 + k2;
        long s = 0;
        int mx = 0;
        int[] cnt = new int[100001];
        for (int i = 0; i < nums1.length; ++i) {
            int v = Math.abs(nums1[i] - nums2[i]);
            ++cnt[v];
            s += v;
            mx = Math.max(mx, v);
        }
        if (s <= k) {
            return 0;
        }
        for (int v = mx; v > 0 && k > 0; --v) {
            if (cnt[v] == 0) {
                continue;
            }
            int take = Math.min(cnt[v], k);
            k -= take;
            cnt[v] -= take;
            cnt[v - 1] += take;
        }
        long ans = 0;
        for (int v = 0; v <= mx; ++v) {
            ans += (long) v * v * cnt[v];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minSumSquareDiff(vector<int>& nums1, vector<int>& nums2, int k1, int k2) {
        int k = k1 + k2;
        long long s = 0;
        int mx = 0;
        vector<int> cnt(100001);
        for (int i = 0; i < nums1.size(); ++i) {
            int v = abs(nums1[i] - nums2[i]);
            ++cnt[v];
            s += v;
            mx = max(mx, v);
        }
        if (s <= k) {
            return 0;
        }
        for (int v = mx; v > 0 && k > 0; --v) {
            if (cnt[v] == 0) {
                continue;
            }
            int take = min(cnt[v], k);
            k -= take;
            cnt[v] -= take;
            cnt[v - 1] += take;
        }
        long long ans = 0;
        for (int v = 0; v <= mx; ++v) {
            ans += 1LL * v * v * cnt[v];
        }
        return ans;
    }
};
```

#### Go

```go
func minSumSquareDiff(nums1 []int, nums2 []int, k1 int, k2 int) int64 {
	k := k1 + k2
	var s int64
	mx := 0
	cnt := make([]int, 100001)
	for i, a := range nums1 {
		v := abs(a - nums2[i])
		cnt[v]++
		s += int64(v)
		mx = max(mx, v)
	}
	if s <= int64(k) {
		return 0
	}
	for v := mx; v > 0 && k > 0; v-- {
		if cnt[v] == 0 {
			continue
		}
		take := min(cnt[v], k)
		k -= take
		cnt[v] -= take
		cnt[v-1] += take
	}
	var ans int64
	for v := 0; v <= mx; v++ {
		ans += int64(v) * int64(v) * int64(cnt[v])
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
