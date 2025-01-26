---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1590.Make%20Sum%20Divisible%20by%20P/README_EN.md
rating: 2038
source: Biweekly Contest 35 Q3
tags:
    - Array
    - Hash Table
    - Prefix Sum
---

<!-- problem:start -->

# [1590. Make Sum Divisible by P](https://leetcode.com/problems/make-sum-divisible-by-p)

[中文文档](/solution/1500-1599/1590.Make%20Sum%20Divisible%20by%20P/README.md)

## Description

<!-- description:start -->

<p>Given an array of positive integers <code>nums</code>, remove the <strong>smallest</strong> subarray (possibly <strong>empty</strong>) such that the <strong>sum</strong> of the remaining elements is divisible by <code>p</code>. It is <strong>not</strong> allowed to remove the whole array.</p>

<p>Return <em>the length of the smallest subarray that you need to remove, or </em><code>-1</code><em> if it&#39;s impossible</em>.</p>

<p>A <strong>subarray</strong> is defined as a contiguous block of elements in the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,4,2], p = 6
<strong>Output:</strong> 1
<strong>Explanation:</strong> The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,3,5,2], p = 9
<strong>Output:</strong> 2
<strong>Explanation:</strong> We cannot remove a single element to get a sum divisible by 9. The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3], p = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong> Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= p &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Hash Table

First, we calculate the sum of all elements in the array $\textit{nums}$ modulo $p$, denoted as $k$. If $k$ is $0$, it means the sum of all elements in the array $\textit{nums}$ is a multiple of $p$, so we directly return $0$.

If $k$ is not $0$, we need to find the shortest subarray such that removing this subarray makes the sum of the remaining elements modulo $p$ equal to $0$.

We can traverse the array $\textit{nums}$, maintaining the current prefix sum modulo $p$, denoted as $cur$. We use a hash table $last$ to record the last occurrence of each prefix sum modulo $p$.

If there exists a subarray ending at $\textit{nums}[i]$ such that removing this subarray makes the sum of the remaining elements modulo $p$ equal to $0$, we need to find a previous prefix sum modulo $p$ equal to $target$ at position $j$ such that $(target + k - cur) \bmod p = 0$. If found, we can remove the subarray $\textit{nums}[j+1,..i]$ to make the sum of the remaining elements modulo $p$ equal to $0$.

Therefore, if there exists a $target = (cur - k + p) \bmod p$, we can update the answer to $\min(ans, i - j)$. Then, we update $last[cur]$ to $i$. We continue traversing the array $\textit{nums}$ until the end to get the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSubarray(self, nums: List[int], p: int) -> int:
        k = sum(nums) % p
        if k == 0:
            return 0
        last = {0: -1}
        cur = 0
        ans = len(nums)
        for i, x in enumerate(nums):
            cur = (cur + x) % p
            target = (cur - k + p) % p
            if target in last:
                ans = min(ans, i - last[target])
            last[cur] = i
        return -1 if ans == len(nums) else ans
```

#### Java

```java
class Solution {
    public int minSubarray(int[] nums, int p) {
        int k = 0;
        for (int x : nums) {
            k = (k + x) % p;
        }
        if (k == 0) {
            return 0;
        }
        Map<Integer, Integer> last = new HashMap<>();
        last.put(0, -1);
        int n = nums.length;
        int ans = n;
        int cur = 0;
        for (int i = 0; i < n; ++i) {
            cur = (cur + nums[i]) % p;
            int target = (cur - k + p) % p;
            if (last.containsKey(target)) {
                ans = Math.min(ans, i - last.get(target));
            }
            last.put(cur, i);
        }
        return ans == n ? -1 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSubarray(vector<int>& nums, int p) {
        int k = 0;
        for (int& x : nums) {
            k = (k + x) % p;
        }
        if (k == 0) {
            return 0;
        }
        unordered_map<int, int> last;
        last[0] = -1;
        int n = nums.size();
        int ans = n;
        int cur = 0;
        for (int i = 0; i < n; ++i) {
            cur = (cur + nums[i]) % p;
            int target = (cur - k + p) % p;
            if (last.count(target)) {
                ans = min(ans, i - last[target]);
            }
            last[cur] = i;
        }
        return ans == n ? -1 : ans;
    }
};
```

#### Go

```go
func minSubarray(nums []int, p int) int {
	k := 0
	for _, x := range nums {
		k = (k + x) % p
	}
	if k == 0 {
		return 0
	}
	last := map[int]int{0: -1}
	n := len(nums)
	ans := n
	cur := 0
	for i, x := range nums {
		cur = (cur + x) % p
		target := (cur - k + p) % p
		if j, ok := last[target]; ok {
			ans = min(ans, i-j)
		}
		last[cur] = i
	}
	if ans == n {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function minSubarray(nums: number[], p: number): number {
    let k = 0;
    for (const x of nums) {
        k = (k + x) % p;
    }
    if (k === 0) {
        return 0;
    }
    const last = new Map<number, number>();
    last.set(0, -1);
    const n = nums.length;
    let ans = n;
    let cur = 0;
    for (let i = 0; i < n; ++i) {
        cur = (cur + nums[i]) % p;
        const target = (cur - k + p) % p;
        if (last.has(target)) {
            const j = last.get(target)!;
            ans = Math.min(ans, i - j);
        }
        last.set(cur, i);
    }
    return ans === n ? -1 : ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn min_subarray(nums: Vec<i32>, p: i32) -> i32 {
        let mut k = 0;
        for &x in &nums {
            k = (k + x) % p;
        }
        if k == 0 {
            return 0;
        }

        let mut last = HashMap::new();
        last.insert(0, -1);
        let n = nums.len();
        let mut ans = n as i32;
        let mut cur = 0;

        for i in 0..n {
            cur = (cur + nums[i]) % p;
            let target = (cur - k + p) % p;
            if let Some(&prev_idx) = last.get(&target) {
                ans = ans.min(i as i32 - prev_idx);
            }
            last.insert(cur, i as i32);
        }

        if ans == n as i32 {
            -1
        } else {
            ans
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} p
 * @return {number}
 */
var minSubarray = function (nums, p) {
    let k = 0;
    for (const x of nums) {
        k = (k + x) % p;
    }
    if (k === 0) {
        return 0;
    }
    const last = new Map();
    last.set(0, -1);
    const n = nums.length;
    let ans = n;
    let cur = 0;
    for (let i = 0; i < n; ++i) {
        cur = (cur + nums[i]) % p;
        const target = (cur - k + p) % p;
        if (last.has(target)) {
            const j = last.get(target);
            ans = Math.min(ans, i - j);
        }
        last.set(cur, i);
    }
    return ans === n ? -1 : ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
