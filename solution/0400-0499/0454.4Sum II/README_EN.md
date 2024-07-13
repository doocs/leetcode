---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0454.4Sum%20II/README_EN.md
tags:
    - Array
    - Hash Table
---

<!-- problem:start -->

# [454. 4Sum II](https://leetcode.com/problems/4sum-ii)

[中文文档](/solution/0400-0499/0454.4Sum%20II/README.md)

## Description

<!-- description:start -->

<p>Given four integer arrays <code>nums1</code>, <code>nums2</code>, <code>nums3</code>, and <code>nums4</code> all of length <code>n</code>, return the number of tuples <code>(i, j, k, l)</code> such that:</p>

<ul>
	<li><code>0 &lt;= i, j, k, l &lt; n</code></li>
	<li><code>nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0</code></li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
The two tuples are:
1. (0, 0, 0, 1) -&gt; nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -&gt; nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length</code></li>
	<li><code>n == nums2.length</code></li>
	<li><code>n == nums3.length</code></li>
	<li><code>n == nums4.length</code></li>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>-2<sup>28</sup> &lt;= nums1[i], nums2[i], nums3[i], nums4[i] &lt;= 2<sup>28</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can add the elements $a$ and $b$ in arrays $nums1$ and $nums2$ respectively, and store all possible sums in a hash table $cnt$, where the key is the sum of the two numbers, and the value is the count of the sum.

Then we iterate through the elements $c$ and $d$ in arrays $nums3$ and $nums4$, let $c+d$ be the target value, then the answer is the cumulative sum of $cnt[-(c+d)]$.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def fourSumCount(
        self, nums1: List[int], nums2: List[int], nums3: List[int], nums4: List[int]
    ) -> int:
        cnt = Counter(a + b for a in nums1 for b in nums2)
        return sum(cnt[-(c + d)] for c in nums3 for d in nums4)
```

#### Java

```java
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int a : nums1) {
            for (int b : nums2) {
                cnt.merge(a + b, 1, Integer::sum);
            }
        }
        int ans = 0;
        for (int c : nums3) {
            for (int d : nums4) {
                ans += cnt.getOrDefault(-(c + d), 0);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int fourSumCount(vector<int>& nums1, vector<int>& nums2, vector<int>& nums3, vector<int>& nums4) {
        unordered_map<int, int> cnt;
        for (int a : nums1) {
            for (int b : nums2) {
                ++cnt[a + b];
            }
        }
        int ans = 0;
        for (int c : nums3) {
            for (int d : nums4) {
                ans += cnt[-(c + d)];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func fourSumCount(nums1 []int, nums2 []int, nums3 []int, nums4 []int) (ans int) {
	cnt := map[int]int{}
	for _, a := range nums1 {
		for _, b := range nums2 {
			cnt[a+b]++
		}
	}
	for _, c := range nums3 {
		for _, d := range nums4 {
			ans += cnt[-(c + d)]
		}
	}
	return
}
```

#### TypeScript

```ts
function fourSumCount(nums1: number[], nums2: number[], nums3: number[], nums4: number[]): number {
    const cnt: Record<number, number> = {};
    for (const a of nums1) {
        for (const b of nums2) {
            const x = a + b;
            cnt[x] = (cnt[x] || 0) + 1;
        }
    }
    let ans = 0;
    for (const c of nums3) {
        for (const d of nums4) {
            const x = c + d;
            ans += cnt[-x] || 0;
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn four_sum_count(
        nums1: Vec<i32>,
        nums2: Vec<i32>,
        nums3: Vec<i32>,
        nums4: Vec<i32>,
    ) -> i32 {
        let mut cnt = HashMap::new();
        for &a in &nums1 {
            for &b in &nums2 {
                *cnt.entry(a + b).or_insert(0) += 1;
            }
        }
        let mut ans = 0;
        for &c in &nums3 {
            for &d in &nums4 {
                if let Some(&count) = cnt.get(&(0 - (c + d))) {
                    ans += count;
                }
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
