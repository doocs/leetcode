---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0325.Maximum%20Size%20Subarray%20Sum%20Equals%20k/README_EN.md
tags:
    - Array
    - Hash Table
    - Prefix Sum
---

<!-- problem:start -->

# [325. Maximum Size Subarray Sum Equals k 🔒](https://leetcode.com/problems/maximum-size-subarray-sum-equals-k)

[中文文档](/solution/0300-0399/0325.Maximum%20Size%20Subarray%20Sum%20Equals%20k/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the maximum length of a </em><span data-keyword="subarray"><em>subarray</em></span><em> that sums to</em> <code>k</code>. If there is not one, return <code>0</code> instead.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-1,5,-2,3], k = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> The subarray [1, -1, 5, -2] sums to 3 and is the longest.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,-1,2,1], k = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> The subarray [-1, 2] sums to 1 and is the longest.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Prefix Sum

We can use a hash table $\textit{d}$ to record the first occurrence index of each prefix sum in the array $\textit{nums}$, initializing $\textit{d}[0] = -1$. Additionally, we define a variable $\textit{s}$ to keep track of the current prefix sum.

Next, we iterate through the array $\textit{nums}$. For the current number $\textit{nums}[i]$, we update the prefix sum $\textit{s} = \textit{s} + \textit{nums}[i]$. If $\textit{s} - k$ exists in the hash table $\textit{d}$, let $\textit{j} = \textit{d}[\textit{s} - k]$, then the length of the subarray that ends at $\textit{nums}[i]$ and satisfies the condition is $i - j$. We use a variable $\textit{ans}$ to maintain the length of the longest subarray that satisfies the condition. After that, if $\textit{s}$ does not exist in the hash table, we record $\textit{s}$ and its corresponding index $i$ by setting $\textit{d}[\textit{s}] = i$. Otherwise, we do not update $\textit{d}[\textit{s}]$. It is important to note that there may be multiple positions $i$ with the same value of $\textit{s}$, so we only record the smallest $i$ to ensure the subarray length is the longest.

After the iteration ends, we return $\textit{ans}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSubArrayLen(self, nums: List[int], k: int) -> int:
        d = {0: -1}
        ans = s = 0
        for i, x in enumerate(nums):
            s += x
            if s - k in d:
                ans = max(ans, i - d[s - k])
            if s not in d:
                d[s] = i
        return ans
```

#### Java

```java
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Long, Integer> d = new HashMap<>();
        d.put(0L, -1);
        int ans = 0;
        long s = 0;
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            ans = Math.max(ans, i - d.getOrDefault(s - k, i));
            d.putIfAbsent(s, i);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxSubArrayLen(vector<int>& nums, int k) {
        unordered_map<long long, int> d{{0, -1}};
        int ans = 0;
        long long s = 0;
        for (int i = 0; i < nums.size(); ++i) {
            s += nums[i];
            if (d.count(s - k)) {
                ans = max(ans, i - d[s - k]);
            }
            if (!d.count(s)) {
                d[s] = i;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxSubArrayLen(nums []int, k int) (ans int) {
	d := map[int]int{0: -1}
	s := 0
	for i, x := range nums {
		s += x
		if j, ok := d[s-k]; ok && ans < i-j {
			ans = i - j
		}
		if _, ok := d[s]; !ok {
			d[s] = i
		}
	}
	return
}
```

#### TypeScript

```ts
function maxSubArrayLen(nums: number[], k: number): number {
    const d: Map<number, number> = new Map();
    d.set(0, -1);
    let ans = 0;
    let s = 0;
    for (let i = 0; i < nums.length; ++i) {
        s += nums[i];
        if (d.has(s - k)) {
            ans = Math.max(ans, i - d.get(s - k)!);
        }
        if (!d.has(s)) {
            d.set(s, i);
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn max_sub_array_len(nums: Vec<i32>, k: i32) -> i32 {
        let mut d = HashMap::new();
        d.insert(0, -1);
        let mut ans = 0;
        let mut s = 0;

        for (i, &x) in nums.iter().enumerate() {
            s += x;
            if let Some(&j) = d.get(&(s - k)) {
                ans = ans.max((i as i32) - j);
            }
            d.entry(s).or_insert(i as i32);
        }

        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var maxSubArrayLen = function (nums, k) {
    const d = new Map();
    d.set(0, -1);
    let ans = 0;
    let s = 0;
    for (let i = 0; i < nums.length; ++i) {
        s += nums[i];
        if (d.has(s - k)) {
            ans = Math.max(ans, i - d.get(s - k));
        }
        if (!d.has(s)) {
            d.set(s, i);
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int MaxSubArrayLen(int[] nums, int k) {
        var d = new Dictionary<int, int>();
        d[0] = -1;
        int ans = 0;
        int s = 0;
        for (int i = 0; i < nums.Length; i++) {
            s += nums[i];
            if (d.ContainsKey(s - k)) {
                ans = Math.Max(ans, i - d[s - k]);
            }
            if (!d.ContainsKey(s)) {
                d[s] = i;
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
