---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2588.Count%20the%20Number%20of%20Beautiful%20Subarrays/README_EN.md
rating: 1696
source: Weekly Contest 336 Q3
tags:
    - Bit Manipulation
    - Array
    - Hash Table
    - Prefix Sum
---

<!-- problem:start -->

# [2588. Count the Number of Beautiful Subarrays](https://leetcode.com/problems/count-the-number-of-beautiful-subarrays)

[中文文档](/solution/2500-2599/2588.Count%20the%20Number%20of%20Beautiful%20Subarrays/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. In one operation, you can:</p>

<ul>
	<li>Choose two different indices <code>i</code> and <code>j</code> such that <code>0 &lt;= i, j &lt; nums.length</code>.</li>
	<li>Choose a non-negative integer <code>k</code> such that the <code>k<sup>th</sup></code> bit (<strong>0-indexed</strong>) in the binary representation of <code>nums[i]</code> and <code>nums[j]</code> is <code>1</code>.</li>
	<li>Subtract <code>2<sup>k</sup></code> from <code>nums[i]</code> and <code>nums[j]</code>.</li>
</ul>

<p>A subarray is <strong>beautiful</strong> if it is possible to make all of its elements equal to <code>0</code> after applying the above operation any number of times.</p>

<p>Return <em>the number of <strong>beautiful subarrays</strong> in the array</em> <code>nums</code>.</p>

<p>A subarray is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,3,1,2,4]
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 2 beautiful subarrays in nums: [4,<u>3,1,2</u>,4] and [<u>4,3,1,2,4</u>].
- We can make all elements in the subarray [3,1,2] equal to 0 in the following way:
  - Choose [<u>3</u>, 1, <u>2</u>] and k = 1. Subtract 2<sup>1</sup> from both numbers. The subarray becomes [1, 1, 0].
  - Choose [<u>1</u>, <u>1</u>, 0] and k = 0. Subtract 2<sup>0</sup> from both numbers. The subarray becomes [0, 0, 0].
- We can make all elements in the subarray [4,3,1,2,4] equal to 0 in the following way:
  - Choose [<u>4</u>, 3, 1, 2, <u>4</u>] and k = 2. Subtract 2<sup>2</sup> from both numbers. The subarray becomes [0, 3, 1, 2, 0].
  - Choose [0, <u>3</u>, <u>1</u>, 2, 0] and k = 0. Subtract 2<sup>0</sup> from both numbers. The subarray becomes [0, 2, 0, 2, 0].
  - Choose [0, <u>2</u>, 0, <u>2</u>, 0] and k = 1. Subtract 2<sup>1</sup> from both numbers. The subarray becomes [0, 0, 0, 0, 0].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,10,4]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no beautiful subarrays in nums.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix XOR + Hash Table

We observe that a subarray can become an array of all $0$s if and only if the number of $1$s on each binary bit of all elements in the subarray is even.

If there exist indices $i$ and $j$ such that $i \lt j$ and the subarrays $nums[0,..,i]$ and $nums[0,..,j]$ have the same parity of the number of $1$s on each binary bit, then we can turn the subarray $nums[i + 1,..,j]$ into an array of all $0$s.

Therefore, we can use the prefix XOR method and a hash table $cnt$ to count the occurrences of each prefix XOR value. We traverse the array, for each element $x$, we calculate its prefix XOR value $mask$, then add the number of occurrences of $mask$ to the answer. Then, we increase the number of occurrences of $mask$ by $1$.

Finally, we return the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def beautifulSubarrays(self, nums: List[int]) -> int:
        cnt = Counter({0: 1})
        ans = mask = 0
        for x in nums:
            mask ^= x
            ans += cnt[mask]
            cnt[mask] += 1
        return ans
```

#### Java

```java
class Solution {
    public long beautifulSubarrays(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        long ans = 0;
        int mask = 0;
        for (int x : nums) {
            mask ^= x;
            ans += cnt.merge(mask, 1, Integer::sum) - 1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long beautifulSubarrays(vector<int>& nums) {
        unordered_map<int, int> cnt{{0, 1}};
        long long ans = 0;
        int mask = 0;
        for (int x : nums) {
            mask ^= x;
            ans += cnt[mask]++;
        }
        return ans;
    }
};
```

#### Go

```go
func beautifulSubarrays(nums []int) (ans int64) {
	cnt := map[int]int{0: 1}
	mask := 0
	for _, x := range nums {
		mask ^= x
		ans += int64(cnt[mask])
		cnt[mask]++
	}
	return
}
```

#### TypeScript

```ts
function beautifulSubarrays(nums: number[]): number {
    const cnt = new Map();
    cnt.set(0, 1);
    let ans = 0;
    let mask = 0;
    for (const x of nums) {
        mask ^= x;
        ans += cnt.get(mask) || 0;
        cnt.set(mask, (cnt.get(mask) || 0) + 1);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn beautiful_subarrays(nums: Vec<i32>) -> i64 {
        let mut cnt = HashMap::new();
        cnt.insert(0, 1);
        let mut ans = 0;
        let mut mask = 0;
        for &x in nums.iter() {
            mask ^= x;
            ans += *cnt.get(&mask).unwrap_or(&0);
            *cnt.entry(mask).or_insert(0) += 1;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
