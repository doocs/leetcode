---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2537.Count%20the%20Number%20of%20Good%20Subarrays/README_EN.md
rating: 1891
source: Weekly Contest 328 Q3
tags:
    - Array
    - Hash Table
    - Sliding Window
---

<!-- problem:start -->

# [2537. Count the Number of Good Subarrays](https://leetcode.com/problems/count-the-number-of-good-subarrays)

[中文文档](/solution/2500-2599/2537.Count%20the%20Number%20of%20Good%20Subarrays/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the number of <strong>good</strong> subarrays of</em> <code>nums</code>.</p>

<p>A subarray <code>arr</code> is <strong>good</strong> if there are <strong>at least </strong><code>k</code> pairs of indices <code>(i, j)</code> such that <code>i &lt; j</code> and <code>arr[i] == arr[j]</code>.</p>

<p>A <strong>subarray</strong> is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1], k = 10
<strong>Output:</strong> 1
<strong>Explanation:</strong> The only good subarray is the array nums itself.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,4,3,2,2,4], k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 different good subarrays:
- [3,1,4,3,2,2] that has 2 pairs.
- [3,1,4,3,2,2,4] that has 3 pairs.
- [1,4,3,2,2,4] that has 2 pairs.
- [4,3,2,2,4] that has 2 pairs.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Two Pointers

If a subarray contains $k$ pairs of identical elements, then this subarray must contain at least $k$ pairs of identical elements.

We use a hash table $\textit{cnt}$ to count the occurrences of elements within the sliding window, a variable $\textit{cur}$ to count the number of identical pairs within the window, and a pointer $i$ to maintain the left boundary of the window.

As we iterate through the array $\textit{nums}$, we treat the current element $x$ as the right boundary of the window. The number of identical pairs in the window increases by $\textit{cnt}[x]$, and we increment the count of $x$ by 1, i.e., $\textit{cnt}[x] \leftarrow \textit{cnt}[x] + 1$. Next, we repeatedly check if the number of identical pairs in the window is greater than or equal to $k$ after removing the leftmost element. If it is, we decrement the count of the leftmost element, i.e., $\textit{cnt}[\textit{nums}[i]] \leftarrow \textit{cnt}[\textit{nums}[i]] - 1$, reduce the number of identical pairs in the window by $\textit{cnt}[\textit{nums}[i]]$, i.e., $\textit{cur} \leftarrow \textit{cur} - \textit{cnt}[\textit{nums}[i]]$, and move the left boundary to the right, i.e., $i \leftarrow i + 1$. At this point, all elements to the left of and including the left boundary can serve as the left boundary for the current right boundary, so we add $i + 1$ to the answer.

Finally, we return the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countGood(self, nums: List[int], k: int) -> int:
        cnt = Counter()
        ans = cur = 0
        i = 0
        for x in nums:
            cur += cnt[x]
            cnt[x] += 1
            while cur - cnt[nums[i]] + 1 >= k:
                cnt[nums[i]] -= 1
                cur -= cnt[nums[i]]
                i += 1
            if cur >= k:
                ans += i + 1
        return ans
```

#### Java

```java
class Solution {
    public long countGood(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        long ans = 0, cur = 0;
        int i = 0;
        for (int x : nums) {
            cur += cnt.merge(x, 1, Integer::sum) - 1;
            while (cur - cnt.get(nums[i]) + 1 >= k) {
                cur -= cnt.merge(nums[i++], -1, Integer::sum);
            }
            if (cur >= k) {
                ans += i + 1;
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
    long long countGood(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        long long ans = 0;
        long long cur = 0;
        int i = 0;
        for (int& x : nums) {
            cur += cnt[x]++;
            while (cur - cnt[nums[i]] + 1 >= k) {
                cur -= --cnt[nums[i++]];
            }
            if (cur >= k) {
                ans += i + 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countGood(nums []int, k int) int64 {
	cnt := map[int]int{}
	ans, cur := 0, 0
	i := 0
	for _, x := range nums {
		cur += cnt[x]
		cnt[x]++
		for cur-cnt[nums[i]]+1 >= k {
			cnt[nums[i]]--
			cur -= cnt[nums[i]]
			i++
		}
		if cur >= k {
			ans += i + 1
		}
	}
	return int64(ans)
}
```

#### TypeScript

```ts
function countGood(nums: number[], k: number): number {
    const cnt: Map<number, number> = new Map();
    let [ans, cur, i] = [0, 0, 0];

    for (const x of nums) {
        const count = cnt.get(x) || 0;
        cur += count;
        cnt.set(x, count + 1);

        while (cur - (cnt.get(nums[i])! - 1) >= k) {
            const countI = cnt.get(nums[i])!;
            cnt.set(nums[i], countI - 1);
            cur -= countI - 1;
            i += 1;
        }

        if (cur >= k) {
            ans += i + 1;
        }
    }

    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn count_good(nums: Vec<i32>, k: i32) -> i64 {
        let mut cnt = HashMap::new();
        let (mut ans, mut cur, mut i) = (0i64, 0i64, 0);

        for &x in &nums {
            cur += *cnt.get(&x).unwrap_or(&0);
            *cnt.entry(x).or_insert(0) += 1;

            while cur - (cnt[&nums[i]] - 1) >= k as i64 {
                *cnt.get_mut(&nums[i]).unwrap() -= 1;
                cur -= cnt[&nums[i]];
                i += 1;
            }

            if cur >= k as i64 {
                ans += (i + 1) as i64;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
