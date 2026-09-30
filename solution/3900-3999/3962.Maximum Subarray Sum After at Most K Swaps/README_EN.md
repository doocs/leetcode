---
comments: true
difficulty: Hard
rating: 2672
source: Weekly Contest 506 Q4
tags:
    - Greedy
    - Binary Indexed Tree
    - Array
    - Hash Table
    - Ordered Set
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3962. Maximum Subarray Sum After at Most K Swaps](https://leetcode.com/problems/maximum-subarray-sum-after-at-most-k-swaps)

[中文文档](/solution/3900-3999/3962.Maximum%20Subarray%20Sum%20After%20at%20Most%20K%20Swaps/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>You are allowed to perform <strong>at most</strong> <code>k</code> swap operations on the array.</p>

<p>In one swap operation, you may choose any two indices <code>i</code> and <code>j</code> and swap <code>nums[i]</code> and <code>nums[j]</code>.</p>

<p>Return an integer denoting the <strong>maximum possible <span data-keyword="subarray-nonempty">subarray</span> sum</strong> after performing the swaps.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,-1,0,2], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>We can swap on indices 1 and 3, resulting in the array <code>[1, 2, 0, -1]</code>.</li>
	<li>The subarray <code>[1, 2]</code> has a sum of 3, which is the maximum possible subarray sum after at most <code>k = 1</code>​​​​​​​ swap.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,2,4], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">13</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum possible subarray sum after at most <code>k = 2</code> swaps is the sum of the entire array, which is 13.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-1,-2], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>k = 0</code> swaps are allowed.</li>
	<li>The possible subarrays are <code>[-1]</code>, <code>[-2]</code>, and <code>[-1, -2]</code>, with sums -1, -2, and -3 respectively.</li>
	<li>Among these sums, the maximum is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1500</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumerate Subarrays + Binary Indexed Tree

<!-- thinking:start -->

> **Thinking**
>
> A swap helps the chosen subarray only when it brings in a larger value from outside. With $n \le 1500$ every segment can be enumerated; sorting the inside and the outside of each one would add another $O(n \log n)$ and push the total to $O(n^3 \log n)$.
>
> Once the inside is ordered ascending and the outside descending, the gains are monotone. If the $t$-th pair still has the smaller value inside, the first $t$ pairs are all worth swapping; once a pair does not increase the sum, later pairs do not either. How many swaps to take can be found by binary search.
>
> After coordinate compression, two Fenwick trees store the counts and sums inside and outside the segment, so the smallest or largest values can be read by rank. With the left end fixed, extending the right end moves the current value from the outside tree into the inside tree.

<!-- thinking:end -->

Deduplicate and sort the values in $\textit{nums}$, and use that list as the compressed value domain. Two Fenwick trees keep, for the inside and the outside, how many times each value occurs and what those occurrences sum to. Either tree can report the $k$-th smallest value, or the sum of the smallest or largest several values, in $O(\log n)$.

Enumerate the left end $l$. Every element starts outside the segment. The right end $r$ runs from $l$ to $n - 1$: move $\textit{nums}[r]$ from the outside tree into the inside tree and add it to the segment sum $s$.

Let $c$ be the number of elements inside. At most $t = \min(k, c, n - c)$ swaps are possible. Binary search the largest $mid$ in $[1, t]$ such that the $mid$-th smallest inside value is strictly smaller than the $mid$-th largest outside value, and call it $best$. If $best > 0$, a candidate is $s$ plus the sum of the $best$ largest outside values, minus the sum of the $best$ smallest inside values. The answer is the maximum candidate over all segments.

The time complexity is $O(n^2 \log^2 n)$ and the space complexity is $O(n)$, where $n$ is the length of $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java
class Fenwick {
    int n;
    int[] count;
    long[] sum;

    Fenwick(int n) {
        this.n = n;
        count = new int[n + 1];
        sum = new long[n + 1];
    }

    void add(int idx, int cnt, long val) {
        ++idx;
        while (idx <= n) {
            count[idx] += cnt;
            sum[idx] += val;
            idx += idx & -idx;
        }
    }

    int prefixCount(int idx) {
        int res = 0;
        while (idx > 0) {
            res += count[idx];
            idx -= idx & -idx;
        }
        return res;
    }

    long prefixSum(int idx) {
        long res = 0;
        while (idx > 0) {
            res += sum[idx];
            idx -= idx & -idx;
        }
        return res;
    }

    int kth(int k) {
        int idx = 0;
        for (int bit = Integer.highestOneBit(n); bit > 0; bit >>= 1) {
            int next = idx + bit;
            if (next <= n && count[next] < k) {
                idx = next;
                k -= count[next];
            }
        }
        return idx;
    }

    long sumSmallest(int k, int[] values) {
        if (k <= 0) {
            return 0;
        }
        int pos = kth(k);
        int before = prefixCount(pos);
        return prefixSum(pos) + (long) (k - before) * values[pos];
    }

    long sumLargest(int k, int[] values) {
        int total = prefixCount(n);
        if (k <= 0) {
            return 0;
        }
        if (k >= total) {
            return prefixSum(n);
        }
        return prefixSum(n) - sumSmallest(total - k, values);
    }
}

class Solution {
    public long maxSum(int[] nums, int k) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int m = 0;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                sorted[m++] = sorted[i];
            }
        }
        int[] values = Arrays.copyOf(sorted, m);
        int[] idx = new int[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = Arrays.binarySearch(values, nums[i]);
        }

        long ans = Long.MIN_VALUE;
        for (int l = 0; l < n; ++l) {
            Fenwick inside = new Fenwick(m);
            Fenwick outside = new Fenwick(m);
            for (int i = 0; i < n; ++i) {
                outside.add(idx[i], 1, nums[i]);
            }
            long window = 0;
            for (int r = l; r < n; ++r) {
                outside.add(idx[r], -1, -nums[r]);
                inside.add(idx[r], 1, nums[r]);
                window += nums[r];
                int inCnt = r - l + 1;
                int outCnt = n - inCnt;
                int limit = Math.min(k, Math.min(inCnt, outCnt));
                if (limit == 0) {
                    ans = Math.max(ans, window);
                    continue;
                }
                int lo = 1;
                int hi = limit;
                int best = 0;
                while (lo <= hi) {
                    int mid = (lo + hi) >>> 1;
                    int small = inside.kth(mid);
                    int large = outside.kth(outCnt - mid + 1);
                    if (values[small] < values[large]) {
                        best = mid;
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
                if (best == 0) {
                    ans = Math.max(ans, window);
                } else {
                    long cand = window + outside.sumLargest(best, values)
                        - inside.sumSmallest(best, values);
                    ans = Math.max(ans, cand);
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
