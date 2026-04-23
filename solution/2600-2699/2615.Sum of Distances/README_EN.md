---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2615.Sum%20of%20Distances/README_EN.md
rating: 1793
source: Weekly Contest 340 Q2
tags:
    - Array
    - Hash Table
    - Prefix Sum
---

<!-- problem:start -->

# [2615. Sum of Distances](https://leetcode.com/problems/sum-of-distances)

[中文文档](/solution/2600-2699/2615.Sum%20of%20Distances/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. There exists an array <code>arr</code> of length <code>nums.length</code>, where <code>arr[i]</code> is the sum of <code>|i - j|</code> over all <code>j</code> such that <code>nums[j] == nums[i]</code> and <code>j != i</code>. If there is no such <code>j</code>, set <code>arr[i]</code> to be <code>0</code>.</p>

<p>Return <em>the array </em><code>arr</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,1,1,2]
<strong>Output:</strong> [5,0,3,4,0]
<strong>Explanation:</strong>
When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] = |0 - 2| + |0 - 3| = 5.
When i = 1, arr[1] = 0 because there is no other index with value 3.
When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] = |2 - 0| + |2 - 3| = 3.
When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] = |3 - 0| + |3 - 2| = 4.
When i = 4, arr[4] = 0 because there is no other index with value 2.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,5,3]
<strong>Output:</strong> [0,0,0]
<strong>Explanation:</strong> Since each element in nums is distinct, arr[i] = 0 for all i.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Note:</strong> This question is the same as <a href="https://leetcode.com/problems/intervals-between-identical-elements/description/" target="_blank"> 2121: Intervals Between Identical Elements.</a></p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Map + Prefix Sum

First, use a hash map $d$ to record the list of indices for each element in the array $nums$, that is, $d[x]$ represents the list of all indices in $nums$ where the value is $x$.

For each list of indices $idx$ in the hash map $d$, we can calculate the value of $arr[i]$ for each index $i$ in $idx$. For the first index $idx[0]$, the sum of distances to all indices on the right is $right = \sum_{i=0}^{m-1} - idx[0] \times m$. Then, we iterate through $idx$, and for each iteration, compute $ans[idx[i]] = left + right$, then update $left$ and $right$ as follows: $left = left + (idx[i+1] - idx[i]) \times (i+1)$, and $right = right - (idx[i+1] - idx[i]) \times (m-i-1)$.

After the iteration, we obtain the array $arr$ corresponding to each element in $nums$, which is stored in $ans$.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def distance(self, nums: List[int]) -> List[int]:
        d = defaultdict(list)
        for i, x in enumerate(nums):
            d[x].append(i)
        ans = [0] * len(nums)
        for idx in d.values():
            left, right = 0, sum(idx) - len(idx) * idx[0]
            for i in range(len(idx)):
                ans[idx[i]] = left + right
                if i + 1 < len(idx):
                    left += (idx[i + 1] - idx[i]) * (i + 1)
                    right -= (idx[i + 1] - idx[i]) * (len(idx) - i - 1)
        return ans
```

#### Java

```java
class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] ans = new long[n];
        Map<Integer, List<Integer>> d = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            d.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        for (var idx : d.values()) {
            int m = idx.size();
            long left = 0;
            long right = -1L * m * idx.get(0);
            for (int i : idx) {
                right += i;
            }
            for (int i = 0; i < m; ++i) {
                ans[idx.get(i)] = left + right;
                if (i + 1 < m) {
                    left += (idx.get(i + 1) - idx.get(i)) * (i + 1L);
                    right -= (idx.get(i + 1) - idx.get(i)) * (m - i - 1L);
                }
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
    vector<long long> distance(vector<int>& nums) {
        int n = nums.size();
        vector<long long> ans(n);
        unordered_map<int, vector<int>> d;
        for (int i = 0; i < n; ++i) {
            d[nums[i]].push_back(i);
        }
        for (auto& [_, idx] : d) {
            int m = idx.size();
            long long left = 0;
            long long right = -1LL * m * idx[0];
            for (int i : idx) {
                right += i;
            }
            for (int i = 0; i < m; ++i) {
                ans[idx[i]] = left + right;
                if (i + 1 < m) {
                    left += (idx[i + 1] - idx[i]) * (i + 1);
                    right -= (idx[i + 1] - idx[i]) * (m - i - 1);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func distance(nums []int) []int64 {
	n := len(nums)
	ans := make([]int64, n)
	d := map[int][]int{}
	for i, x := range nums {
		d[x] = append(d[x], i)
	}
	for _, idx := range d {
		m := len(idx)
		left, right := 0, -m*idx[0]
		for _, i := range idx {
			right += i
		}
		for i := range idx {
			ans[idx[i]] = int64(left + right)
			if i+1 < m {
				left += (idx[i+1] - idx[i]) * (i + 1)
				right -= (idx[i+1] - idx[i]) * (m - i - 1)
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function distance(nums: number[]): number[] {
    const n = nums.length;
    const ans = new Array(n).fill(0);
    const d = new Map<number, number[]>();
    for (let i = 0; i < n; ++i) {
        if (!d.has(nums[i])) {
            d.set(nums[i], []);
        }
        d.get(nums[i])!.push(i);
    }
    for (const idx of d.values()) {
        const m = idx.length;
        let left = 0;
        let right = -1 * m * idx[0];
        for (const i of idx) {
            right += i;
        }
        for (let i = 0; i < m; ++i) {
            ans[idx[i]] = left + right;
            if (i + 1 < m) {
                left += (idx[i + 1] - idx[i]) * (i + 1);
                right -= (idx[i + 1] - idx[i]) * (m - i - 1);
            }
        }
    }
    return ans;
};
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn distance(nums: Vec<i32>) -> Vec<i64> {
        let n = nums.len();
        let mut ans = vec![0i64; n];
        let mut d: HashMap<i32, Vec<usize>> = HashMap::new();
        for i in 0..n {
            d.entry(nums[i]).or_insert(Vec::new()).push(i);
        }
        for idx in d.values() {
            let m = idx.len();
            let mut left = 0i64;
            let mut right = 0i64;
            for &i in idx {
                right += i as i64;
            }
            right -= m as i64 * idx[0] as i64;
            for i in 0..m {
                ans[idx[i]] = left + right;
                if i + 1 < m {
                    let diff = (idx[i + 1] - idx[i]) as i64;
                    left += diff * (i + 1) as i64;
                    right -= diff * (m - i - 1) as i64;
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
