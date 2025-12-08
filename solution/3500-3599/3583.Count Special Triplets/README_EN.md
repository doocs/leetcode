---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3583.Count%20Special%20Triplets/README_EN.md
rating: 1509
source: Weekly Contest 454 Q2
tags:
    - Array
    - Hash Table
    - Counting
---

<!-- problem:start -->

# [3583. Count Special Triplets](https://leetcode.com/problems/count-special-triplets)

[中文文档](/solution/3500-3599/3583.Count%20Special%20Triplets/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>A <strong>special triplet</strong> is defined as a triplet of indices <code>(i, j, k)</code> such that:</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; k &lt; n</code>, where <code>n = nums.length</code></li>
	<li><code>nums[i] == nums[j] * 2</code></li>
	<li><code>nums[k] == nums[j] * 2</code></li>
</ul>

<p>Return the total number of <strong>special triplets</strong> in the array.</p>

<p>Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,3,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The only special triplet is <code>(i, j, k) = (0, 1, 2)</code>, where:</p>

<ul>
	<li><code>nums[0] = 6</code>, <code>nums[1] = 3</code>, <code>nums[2] = 6</code></li>
	<li><code>nums[0] = nums[1] * 2 = 3 * 2 = 6</code></li>
	<li><code>nums[2] = nums[1] * 2 = 3 * 2 = 6</code></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,1,0,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The only special triplet is <code>(i, j, k) = (0, 2, 3)</code>, where:</p>

<ul>
	<li><code>nums[0] = 0</code>, <code>nums[2] = 0</code>, <code>nums[3] = 0</code></li>
	<li><code>nums[0] = nums[2] * 2 = 0 * 2 = 0</code></li>
	<li><code>nums[3] = nums[2] * 2 = 0 * 2 = 0</code></li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [8,4,2,8,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>There are exactly two special triplets:</p>

<ul>
	<li><code>(i, j, k) = (0, 1, 3)</code>

    <ul>
    	<li><code>nums[0] = 8</code>, <code>nums[1] = 4</code>, <code>nums[3] = 8</code></li>
    	<li><code>nums[0] = nums[1] * 2 = 4 * 2 = 8</code></li>
    	<li><code>nums[3] = nums[1] * 2 = 4 * 2 = 8</code></li>
    </ul>
    </li>
    <li><code>(i, j, k) = (1, 2, 4)</code>
    <ul>
    	<li><code>nums[1] = 4</code>, <code>nums[2] = 2</code>, <code>nums[4] = 4</code></li>
    	<li><code>nums[1] = nums[2] * 2 = 2 * 2 = 4</code></li>
    	<li><code>nums[4] = nums[2] * 2 = 2 * 2 = 4</code></li>
    </ul>
    </li>

</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumerate Middle Number + Hash Table

We can enumerate the middle number $\textit{nums}[j]$, and use two hash tables, $\textit{left}$ and $\textit{right}$, to record the occurrence counts of numbers to the left and right of $\textit{nums}[j]$, respectively.

First, we add all numbers to $\textit{right}$. Then, we traverse each number $\textit{nums}[j]$ from left to right. During the traversal:

1. Remove $\textit{nums}[j]$ from $\textit{right}$.
2. Count the occurrences of the number $\textit{nums}[i] = \textit{nums}[j] * 2$ to the left of $\textit{nums}[j]$, denoted as $\textit{left}[\textit{nums}[j] * 2]$.
3. Count the occurrences of the number $\textit{nums}[k] = \textit{nums}[j] * 2$ to the right of $\textit{nums}[j]$, denoted as $\textit{right}[\textit{nums}[j] * 2]$.
4. Multiply $\textit{left}[\textit{nums}[j] * 2]$ and $\textit{right}[\textit{nums}[j] * 2]$ to get the number of special triplets with $\textit{nums}[j]$ as the middle number, and add the result to the answer.
5. Add $\textit{nums}[j]$ to $\textit{left}$.

Finally, return the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def specialTriplets(self, nums: List[int]) -> int:
        left = Counter()
        right = Counter(nums)
        ans = 0
        mod = 10**9 + 7
        for x in nums:
            right[x] -= 1
            ans = (ans + left[x * 2] * right[x * 2] % mod) % mod
            left[x] += 1
        return ans
```

#### Java

```java
class Solution {
    public int specialTriplets(int[] nums) {
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        for (int x : nums) {
            right.merge(x, 1, Integer::sum);
        }
        long ans = 0;
        final int mod = (int) 1e9 + 7;
        for (int x : nums) {
            right.merge(x, -1, Integer::sum);
            ans = (ans + 1L * left.getOrDefault(x * 2, 0) * right.getOrDefault(x * 2, 0) % mod)
                % mod;
            left.merge(x, 1, Integer::sum);
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int specialTriplets(vector<int>& nums) {
        unordered_map<int, int> left, right;
        for (int x : nums) {
            right[x]++;
        }
        long long ans = 0;
        const int mod = 1e9 + 7;
        for (int x : nums) {
            right[x]--;
            ans = (ans + 1LL * left[x * 2] * right[x * 2] % mod) % mod;
            left[x]++;
        }
        return (int) ans;
    }
};
```

#### Go

```go
func specialTriplets(nums []int) int {
	left := make(map[int]int)
	right := make(map[int]int)
	for _, x := range nums {
		right[x]++
	}
	ans := int64(0)
	mod := int64(1e9 + 7)
	for _, x := range nums {
		right[x]--
		ans = (ans + int64(left[x*2])*int64(right[x*2])%mod) % mod
		left[x]++
	}
	return int(ans)
}
```

#### TypeScript

```ts
function specialTriplets(nums: number[]): number {
    const left = new Map<number, number>();
    const right = new Map<number, number>();
    for (const x of nums) {
        right.set(x, (right.get(x) || 0) + 1);
    }
    let ans = 0;
    const mod = 1e9 + 7;
    for (const x of nums) {
        right.set(x, (right.get(x) || 0) - 1);
        const lx = left.get(x * 2) || 0;
        const rx = right.get(x * 2) || 0;
        ans = (ans + ((lx * rx) % mod)) % mod;
        left.set(x, (left.get(x) || 0) + 1);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn special_triplets(nums: Vec<i32>) -> i32 {
        let mut left: HashMap<i32, i64> = HashMap::new();
        let mut right: HashMap<i32, i64> = HashMap::new();

        for &x in &nums {
            *right.entry(x).or_insert(0) += 1;
        }

        let modulo: i64 = 1_000_000_007;
        let mut ans: i64 = 0;

        for &x in &nums {
            if let Some(v) = right.get_mut(&x) {
                *v -= 1;
            }

            let t = x * 2;

            let l = *left.get(&t).unwrap_or(&0);
            let r = *right.get(&t).unwrap_or(&0);

            ans = (ans + (l * r) % modulo) % modulo;

            *left.entry(x).or_insert(0) += 1;
        }

        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
