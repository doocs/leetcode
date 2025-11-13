---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3653.XOR%20After%20Range%20Multiplication%20Queries%20I/README_EN.md
rating: 1556
source: Weekly Contest 463 Q2
tags:
    - Array
    - Divide and Conquer
    - Simulation
---

<!-- problem:start -->

# [3653. XOR After Range Multiplication Queries I](https://leetcode.com/problems/xor-after-range-multiplication-queries-i)

[中文文档](/solution/3600-3699/3653.XOR%20After%20Range%20Multiplication%20Queries%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and a 2D integer array <code>queries</code> of size <code>q</code>, where <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>, k<sub>i</sub>, v<sub>i</sub>]</code>.</p>

<p>For each query, you must apply the following operations in order:</p>

<ul>
	<li>Set <code>idx = l<sub>i</sub></code>.</li>
	<li>While <code>idx &lt;= r<sub>i</sub></code>:
	<ul>
		<li>Update: <code>nums[idx] = (nums[idx] * v<sub>i</sub>) % (10<sup>9</sup> + 7)</code></li>
		<li>Set <code>idx += k<sub>i</sub></code>.</li>
	</ul>
	</li>
</ul>

<p>Return the <strong>bitwise XOR</strong> of all elements in <code>nums</code> after processing all queries.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1], queries = [[0,2,1,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li data-end="106" data-start="18">A single query <code data-end="44" data-start="33">[0, 2, 1, 4]</code> multiplies every element from index 0 through index 2 by 4.</li>
	<li data-end="157" data-start="109">The array changes from <code data-end="141" data-start="132">[1, 1, 1]</code> to <code data-end="154" data-start="145">[4, 4, 4]</code>.</li>
	<li data-end="205" data-start="160">The XOR of all elements is <code data-end="202" data-start="187">4 ^ 4 ^ 4 = 4</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,1,5,4], queries = [[1,4,2,3],[0,2,1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">31</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li data-end="350" data-start="230">The first query <code data-end="257" data-start="246">[1, 4, 2, 3]</code> multiplies the elements at indices 1 and 3 by 3, transforming the array to <code data-end="347" data-start="333">[2, 9, 1, 15, 4]</code>.</li>
	<li data-end="466" data-start="353">The second query <code data-end="381" data-start="370">[0, 2, 1, 2]</code> multiplies the elements at indices 0, 1, and 2 by 2, resulting in <code data-end="463" data-start="448">[4, 18, 2, 15, 4]</code>.</li>
	<li data-end="532" data-is-last-node="" data-start="469">Finally, the XOR of all elements is <code data-end="531" data-start="505">4 ^ 18 ^ 2 ^ 15 ^ 4 = 31</code>.​​​​​​​<strong>​​​​​​​</strong></li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= q == queries.length &lt;= 10<sup>3</sup></code></li>
	<li><code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>, k<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= k<sub>i</sub> &lt;= n</code></li>
	<li><code>1 &lt;= v<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can directly simulate the operations described in the problem by iterating through each query and updating the corresponding elements in the array $\textit{nums}$. Finally, we calculate the bitwise XOR of all elements in the array and return the result.

The time complexity is $O(q \times \frac{n}{k})$, where $n$ is the length of the array $\textit{nums}$ and $q$ is the number of queries. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def xorAfterQueries(self, nums: List[int], queries: List[List[int]]) -> int:
        mod = 10**9 + 7
        for l, r, k, v in queries:
            for idx in range(l, r + 1, k):
                nums[idx] = nums[idx] * v % mod
        return reduce(xor, nums)
```

#### Java

```java
class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        final int mod = (int) 1e9 + 7;
        for (var q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            for (int idx = l; idx <= r; idx += k) {
                nums[idx] = (int) (1L * nums[idx] * v % mod);
            }
        }
        int ans = 0;
        for (int x : nums) {
            ans ^= x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int xorAfterQueries(vector<int>& nums, vector<vector<int>>& queries) {
        const int mod = 1e9 + 7;
        for (const auto& q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            for (int idx = l; idx <= r; idx += k) {
                nums[idx] = 1LL * nums[idx] * v % mod;
            }
        }
        int ans = 0;
        for (int x : nums) {
            ans ^= x;
        }
        return ans;
    }
};
```

#### Go

```go
func xorAfterQueries(nums []int, queries [][]int) int {
	const mod = int(1e9 + 7)
	for _, q := range queries {
		l, r, k, v := q[0], q[1], q[2], q[3]
		for idx := l; idx <= r; idx += k {
			nums[idx] = nums[idx] * v % mod
		}
	}
	ans := 0
	for _, x := range nums {
		ans ^= x
	}
	return ans
}
```

#### TypeScript

```ts
function xorAfterQueries(nums: number[], queries: number[][]): number {
    const mod = 1e9 + 7;
    for (const [l, r, k, v] of queries) {
        for (let idx = l; idx <= r; idx += k) {
            nums[idx] = (nums[idx] * v) % mod;
        }
    }
    return nums.reduce((acc, x) => acc ^ x, 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn xor_after_queries(mut nums: Vec<i32>, queries: Vec<Vec<i32>>) -> i32 {
        let modv: i64 = 1_000_000_007;
        for q in queries {
            let (l, r, k, v) = (q[0] as usize, q[1] as usize, q[2] as usize, q[3] as i64);
            let mut idx = l;
            while idx <= r {
                nums[idx] = ((nums[idx] as i64 * v) % modv) as i32;
                idx += k;
            }
        }
        let mut ans = 0;
        for x in nums {
            ans ^= x;
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
