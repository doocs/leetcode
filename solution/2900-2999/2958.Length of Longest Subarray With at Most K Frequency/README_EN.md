---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2958.Length%20of%20Longest%20Subarray%20With%20at%20Most%20K%20Frequency/README_EN.md
rating: 1535
source: Biweekly Contest 119 Q3
tags:
    - Array
    - Hash Table
    - Sliding Window
---

<!-- problem:start -->

# [2958. Length of Longest Subarray With at Most K Frequency](https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency)

[中文文档](/solution/2900-2999/2958.Length%20of%20Longest%20Subarray%20With%20at%20Most%20K%20Frequency/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>The <strong>frequency</strong> of an element <code>x</code> is the number of times it occurs in an array.</p>

<p>An array is called <strong>good</strong> if the frequency of each element in this array is <strong>less than or equal</strong> to <code>k</code>.</p>

<p>Return <em>the length of the <strong>longest</strong> <strong>good</strong> subarray of</em> <code>nums</code><em>.</em></p>

<p>A <strong>subarray</strong> is a contiguous non-empty sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,1,2,3,1,2], k = 2
<strong>Output:</strong> 6
<strong>Explanation:</strong> The longest possible good subarray is [1,2,3,1,2,3] since the values 1, 2, and 3 occur at most twice in this subarray. Note that the subarrays [2,3,1,2,3,1] and [3,1,2,3,1,2] are also good.
It can be shown that there are no good subarrays with length more than 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1,2,1,2,1,2], k = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> The longest possible good subarray is [1,2] since the values 1 and 2 occur at most once in this subarray. Note that the subarray [2,1] is also good.
It can be shown that there are no good subarrays with length more than 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,5,5,5,5,5,5], k = 4
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest possible good subarray is [5,5,5,5] since the value 5 occurs 4 times in this subarray.
It can be shown that there are no good subarrays with length more than 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We can use two pointers $l$ and $r$ to represent the left and right endpoints of the subarray, initially both pointers point to the first element of the array.

Next, we iterate over each element $x$ in the array $nums$. For each element $x$, we increment the occurrence count of $x$, then check if the current subarray meets the requirements. If the current subarray does not meet the requirements, we move the pointer $l$ one step to the right, and decrement the occurrence count of $nums[l]$, until the current subarray meets the requirements. Then we update the answer $ans = \max(ans, r - l + 1)$. Continue the iteration until $r$ reaches the end of the array.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSubarrayLength(self, nums: List[int], k: int) -> int:
        ans = l = 0
        cnt = defaultdict(int)
        for r, x in enumerate(nums):
            cnt[x] += 1
            while cnt[x] > k:
                cnt[nums[l]] -= 1
                l += 1
            ans = max(ans, r - l + 1)
        return ans
```

#### Java

```java
class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int l = 0, r = 0; r < nums.length; ++r) {
            cnt.merge(nums[r], 1, Integer::sum);
            while (cnt.get(nums[r]) > k) {
                cnt.merge(nums[l++], -1, Integer::sum);
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxSubarrayLength(vector<int>& nums, int k) {
        int ans = 0;
        unordered_map<int, int> cnt;
        for (int l = 0, r = 0; r < nums.size(); ++r) {
            ++cnt[nums[r]];
            while (cnt[nums[r]] > k) {
                --cnt[nums[l++]];
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func maxSubarrayLength(nums []int, k int) (ans int) {
	cnt := make(map[int]int)
	for l, r := 0, 0; r < len(nums); r++ {
		cnt[nums[r]]++
		for cnt[nums[r]] > k {
			cnt[nums[l]]--
			l++
		}
		ans = max(ans, r-l+1)
	}
	return
}
```

#### TypeScript

```ts
function maxSubarrayLength(nums: number[], k: number): number {
    let ans = 0;
    const cnt = new Map<number, number>();
    for (let l = 0, r = 0; r < nums.length; ++r) {
        cnt.set(nums[r], (cnt.get(nums[r]) ?? 0) + 1);
        while (cnt.get(nums[r])! > k) {
            cnt.set(nums[l], cnt.get(nums[l])! - 1);
            ++l;
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_subarray_length(nums: Vec<i32>, k: i32) -> i32 {
        let mut ans = 0;
        let mut cnt = std::collections::HashMap::new();

        let mut l = 0;
        for r in 0..nums.len() {
            *cnt.entry(nums[r]).or_insert(0) += 1;

            while cnt[&nums[r]] > k {
                *cnt.get_mut(&nums[l]).unwrap() -= 1;
                l += 1;
            }

            ans = ans.max((r - l + 1) as i32);
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
