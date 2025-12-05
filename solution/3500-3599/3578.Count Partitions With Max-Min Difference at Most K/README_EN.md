---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3578.Count%20Partitions%20With%20Max-Min%20Difference%20at%20Most%20K/README_EN.md
rating: 2032
source: Weekly Contest 453 Q3
tags:
    - Queue
    - Array
    - Dynamic Programming
    - Prefix Sum
    - Sliding Window
    - Monotonic Queue
---

<!-- problem:start -->

# [3578. Count Partitions With Max-Min Difference at Most K](https://leetcode.com/problems/count-partitions-with-max-min-difference-at-most-k)

[中文文档](/solution/3500-3599/3578.Count%20Partitions%20With%20Max-Min%20Difference%20at%20Most%20K/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>. Your task is to partition <code>nums</code> into one or more <strong>non-empty</strong> contiguous segments such that in each segment, the difference between its <strong>maximum</strong> and <strong>minimum</strong> elements is <strong>at most</strong> <code>k</code>.</p>

<p>Return the total number of ways to partition <code>nums</code> under this condition.</p>

<p>Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [9,4,1,3,7], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 6 valid partitions where the difference between the maximum and minimum elements in each segment is at most <code>k = 4</code>:</p>

<ul>
	<li><code>[[9], [4], [1], [3], [7]]</code></li>
	<li><code>[[9], [4], [1], [3, 7]]</code></li>
	<li><code>[[9], [4], [1, 3], [7]]</code></li>
	<li><code>[[9], [4, 1], [3], [7]]</code></li>
	<li><code>[[9], [4, 1], [3, 7]]</code></li>
	<li><code>[[9], [4, 1, 3], [7]]</code></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,3,4], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 2 valid partitions that satisfy the given conditions:</p>

<ul>
	<li><code>[[3], [3], [4]]</code></li>
	<li><code>[[3, 3], [4]]</code></li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming + Two Pointers + Ordered Set

We define $f[i]$ as the number of ways to partition the first $i$ elements. If an array satisfies that the difference between its maximum and minimum values does not exceed $k$, then any of its subarrays also satisfies this condition. Therefore, we can use two pointers to maintain a sliding window representing the current subarray.

When we reach the $r$-th element, we need to find the left pointer $l$ such that the subarray from $l$ to $r$ satisfies that the difference between the maximum and minimum values does not exceed $k$. We can use an ordered set to maintain the elements in the current window, so that we can quickly get the maximum and minimum values.

Each time we add a new element, we insert it into the ordered set and check the difference between the maximum and minimum values in the current window. If it exceeds $k$, we move the left pointer $l$ until the condition is satisfied. The number of partition ways ending at the $r$-th element is $f[l - 1] + f[l] + \ldots + f[r - 1]$. We can use a prefix sum array to quickly calculate this value.

The answer is $f[n]$, where $n$ is the length of the array.

The time complexity is $O(n \times \log n)$ and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPartitions(self, nums: List[int], k: int) -> int:
        mod = 10**9 + 7
        sl = SortedList()
        n = len(nums)
        f = [1] + [0] * n
        g = [1] + [0] * n
        l = 1
        for r, x in enumerate(nums, 1):
            sl.add(x)
            while sl[-1] - sl[0] > k:
                sl.remove(nums[l - 1])
                l += 1
            f[r] = (g[r - 1] - (g[l - 2] if l >= 2 else 0) + mod) % mod
            g[r] = (g[r - 1] + f[r]) % mod
        return f[n]
```

#### Java

```java
class Solution {
    public int countPartitions(int[] nums, int k) {
        final int mod = (int) 1e9 + 7;
        TreeMap<Integer, Integer> sl = new TreeMap<>();
        int n = nums.length;
        int[] f = new int[n + 1];
        int[] g = new int[n + 1];
        f[0] = 1;
        g[0] = 1;
        int l = 1;
        for (int r = 1; r <= n; r++) {
            int x = nums[r - 1];
            sl.merge(x, 1, Integer::sum);
            while (sl.lastKey() - sl.firstKey() > k) {
                if (sl.merge(nums[l - 1], -1, Integer::sum) == 0) {
                    sl.remove(nums[l - 1]);
                }
                ++l;
            }
            f[r] = (g[r - 1] - (l >= 2 ? g[l - 2] : 0) + mod) % mod;
            g[r] = (g[r - 1] + f[r]) % mod;
        }
        return f[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countPartitions(vector<int>& nums, int k) {
        const int mod = 1e9 + 7;
        multiset<int> sl;
        int n = nums.size();
        vector<int> f(n + 1, 0), g(n + 1, 0);
        f[0] = 1;
        g[0] = 1;
        int l = 1;
        for (int r = 1; r <= n; ++r) {
            int x = nums[r - 1];
            sl.insert(x);
            while (*sl.rbegin() - *sl.begin() > k) {
                sl.erase(sl.find(nums[l - 1]));
                ++l;
            }
            f[r] = (g[r - 1] - (l >= 2 ? g[l - 2] : 0) + mod) % mod;
            g[r] = (g[r - 1] + f[r]) % mod;
        }
        return f[n];
    }
};
```

#### Go

```go
func countPartitions(nums []int, k int) int {
	const mod int = 1e9 + 7
	sl := redblacktree.New[int, int]()
	merge := func(st *redblacktree.Tree[int, int], x, v int) {
		c, _ := st.Get(x)
		if c+v == 0 {
			st.Remove(x)
		} else {
			st.Put(x, c+v)
		}
	}
	n := len(nums)
	f := make([]int, n+1)
	g := make([]int, n+1)
	f[0], g[0] = 1, 1
	for l, r := 1, 1; r <= n; r++ {
		merge(sl, nums[r-1], 1)
		for sl.Right().Key-sl.Left().Key > k {
			merge(sl, nums[l-1], -1)
			l++
		}
		f[r] = g[r-1]
		if l >= 2 {
			f[r] = (f[r] - g[l-2] + mod) % mod
		}
		g[r] = (g[r-1] + f[r]) % mod
	}
	return f[n]
}
```

#### TypeScript

```ts
import { AvlTree } from 'datastructures-js';

function countPartitions(nums: number[], k: number): number {
    const mod = 1_000_000_007;
    const n = nums.length;

    const cnt = new Map<number, number>();
    const st = new AvlTree<number>((a, b) => a - b);

    function insert(x: number) {
        const c = (cnt.get(x) || 0) + 1;
        cnt.set(x, c);
        if (c === 1) {
            st.insert(x);
        }
    }

    function erase(x: number) {
        const c = cnt.get(x)! - 1;
        cnt.set(x, c);
        if (c === 0) {
            st.remove(x);
        }
    }

    const f = Array(n + 1).fill(0);
    const g = Array(n + 1).fill(0);
    f[0] = 1;
    g[0] = 1;

    for (let l = 1, r = 1; r <= n; ++r) {
        const x = nums[r - 1];
        insert(x);

        while (st.max()!.getValue() - st.min()!.getValue() > k) {
            erase(nums[l - 1]);
            l++;
        }

        f[r] = (g[r - 1] - (l >= 2 ? g[l - 2] : 0) + mod) % mod;
        g[r] = (g[r - 1] + f[r]) % mod;
    }

    return f[n];
}
```

#### Rust

```rust
use std::collections::BTreeMap;

impl Solution {
    pub fn count_partitions(nums: Vec<i32>, k: i32) -> i32 {
        const mod_val: i32 = 1_000_000_007;
        let n = nums.len();
        let mut f = vec![0; n + 1];
        let mut g = vec![0; n + 1];
        f[0] = 1;
        g[0] = 1;
        let mut sl = BTreeMap::new();
        let mut l = 1;
        for r in 1..=n {
            let x = nums[r - 1];
            *sl.entry(x).or_insert(0) += 1;
            while sl.keys().last().unwrap() - sl.keys().next().unwrap() > k {
                let val = nums[l - 1];
                if let Some(cnt) = sl.get_mut(&val) {
                    *cnt -= 1;
                    if *cnt == 0 {
                        sl.remove(&val);
                    }
                }
                l += 1;
            }
            f[r] = (g[r - 1] - if l >= 2 { g[l - 2] } else { 0 } + mod_val) % mod_val;
            g[r] = (g[r - 1] + f[r]) % mod_val;
        }
        f[n]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
