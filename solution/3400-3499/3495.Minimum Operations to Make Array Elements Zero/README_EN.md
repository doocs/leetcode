---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3495.Minimum%20Operations%20to%20Make%20Array%20Elements%20Zero/README_EN.md
rating: 2205
source: Weekly Contest 442 Q4
tags:
    - Bit Manipulation
    - Array
    - Math
---

<!-- problem:start -->

# [3495. Minimum Operations to Make Array Elements Zero](https://leetcode.com/problems/minimum-operations-to-make-array-elements-zero)

[中文文档](/solution/3400-3499/3495.Minimum%20Operations%20to%20Make%20Array%20Elements%20Zero/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D array <code>queries</code>, where <code>queries[i]</code> is of the form <code>[l, r]</code>. Each <code>queries[i]</code> defines an array of integers <code>nums</code> consisting of elements ranging from <code>l</code> to <code>r</code>, both <strong>inclusive</strong>.</p>

<p>In one operation, you can:</p>

<ul>
	<li>Select two integers <code>a</code> and <code>b</code> from the array.</li>
	<li>Replace them with <code>floor(a / 4)</code> and <code>floor(b / 4)</code>.</li>
</ul>

<p>Your task is to determine the <strong>minimum</strong> number of operations required to reduce all elements of the array to zero for each query. Return the sum of the results for all queries.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">queries = [[1,2],[2,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>For <code>queries[0]</code>:</p>

<ul>
	<li>The initial array is <code>nums = [1, 2]</code>.</li>
	<li>In the first operation, select <code>nums[0]</code> and <code>nums[1]</code>. The array becomes <code>[0, 0]</code>.</li>
	<li>The minimum number of operations required is 1.</li>
</ul>

<p>For <code>queries[1]</code>:</p>

<ul>
	<li>The initial array is <code>nums = [2, 3, 4]</code>.</li>
	<li>In the first operation, select <code>nums[0]</code> and <code>nums[2]</code>. The array becomes <code>[0, 3, 1]</code>.</li>
	<li>In the second operation, select <code>nums[1]</code> and <code>nums[2]</code>. The array becomes <code>[0, 0, 0]</code>.</li>
	<li>The minimum number of operations required is 2.</li>
</ul>

<p>The output is <code>1 + 2 = 3</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">queries = [[2,6]]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>For <code>queries[0]</code>:</p>

<ul>
	<li>The initial array is <code>nums = [2, 3, 4, 5, 6]</code>.</li>
	<li>In the first operation, select <code>nums[0]</code> and <code>nums[3]</code>. The array becomes <code>[0, 3, 4, 1, 6]</code>.</li>
	<li>In the second operation, select <code>nums[2]</code> and <code>nums[4]</code>. The array becomes <code>[0, 3, 1, 1, 1]</code>.</li>
	<li>In the third operation, select <code>nums[1]</code> and <code>nums[2]</code>. The array becomes <code>[0, 0, 0, 1, 1]</code>.</li>
	<li>In the fourth operation, select <code>nums[3]</code> and <code>nums[4]</code>. The array becomes <code>[0, 0, 0, 0, 0]</code>.</li>
	<li>The minimum number of operations required is 4.</li>
</ul>

<p>The output is 4.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>queries[i] == [l, r]</code></li>
	<li><code>1 &lt;= l &lt; r &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum

According to the problem description, suppose the minimum number of operations required to make an element $x$ become $0$ is $p$, where $p$ is the smallest integer such that $4^p > x$.

Once we know the minimum number of operations for each element, for a range $[l, r]$, let $s$ be the sum of the minimum operations for all elements in $[l, r]$, and let $mx$ be the maximum number of operations, which is the number of operations for element $r$. Then, the minimum number of operations to make all elements in $[l, r]$ become $0$ is $\max(\lceil s / 2 \rceil, mx)$.

We define a function $f(x)$ to represent the sum of the minimum operations for all elements in the range $[1, x]$. For each query $[l, r]$, we can compute $s = f(r) - f(l - 1)$ and $mx = f(r) - f(r - 1)$ to obtain the answer.

The time complexity is $O(q \log M)$, where $q$ is the number of queries and $M$ is the maximum value in the query range. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, queries: List[List[int]]) -> int:
        def f(x: int) -> int:
            res = 0
            p = i = 1
            while p <= x:
                cnt = min(p * 4 - 1, x) - p + 1
                res += cnt * i
                i += 1
                p *= 4
            return res

        ans = 0
        for l, r in queries:
            s = f(r) - f(l - 1)
            mx = f(r) - f(r - 1)
            ans += max((s + 1) // 2, mx)
        return ans
```

#### Java

```java
class Solution {
    public long minOperations(int[][] queries) {
        long ans = 0;
        for (int[] q : queries) {
            int l = q[0], r = q[1];
            long s = f(r) - f(l - 1);
            long mx = f(r) - f(r - 1);
            ans += Math.max((s + 1) / 2, mx);
        }
        return ans;
    }

    private long f(long x) {
        long res = 0;
        long p = 1;
        int i = 1;
        while (p <= x) {
            long cnt = Math.min(p * 4 - 1, x) - p + 1;
            res += cnt * i;
            i++;
            p *= 4;
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minOperations(vector<vector<int>>& queries) {
        auto f = [&](long long x) {
            long long res = 0;
            long long p = 1;
            int i = 1;
            while (p <= x) {
                long long cnt = min(p * 4 - 1, x) - p + 1;
                res += cnt * i;
                i++;
                p *= 4;
            }
            return res;
        };

        long long ans = 0;
        for (auto& q : queries) {
            int l = q[0], r = q[1];
            long long s = f(r) - f(l - 1);
            long long mx = f(r) - f(r - 1);
            ans += max((s + 1) / 2, mx);
        }
        return ans;
    }
};
```

#### Go

```go
func minOperations(queries [][]int) (ans int64) {
	f := func(x int64) (res int64) {
		var p int64 = 1
		i := int64(1)
		for p <= x {
			cnt := min(p*4-1, x) - p + 1
			res += cnt * i
			i++
			p *= 4
		}
		return
	}
	for _, q := range queries {
		l, r := int64(q[0]), int64(q[1])
		s := f(r) - f(l-1)
		mx := f(r) - f(r-1)
		ans += max((s+1)/2, mx)
	}
	return
}
```

#### TypeScript

```ts
function minOperations(queries: number[][]): number {
    const f = (x: number): number => {
        let res = 0;
        let p = 1;
        let i = 1;
        while (p <= x) {
            const cnt = Math.min(p * 4 - 1, x) - p + 1;
            res += cnt * i;
            i++;
            p *= 4;
        }
        return res;
    };

    let ans = 0;
    for (const [l, r] of queries) {
        const s = f(r) - f(l - 1);
        const mx = f(r) - f(r - 1);
        ans += Math.max(Math.ceil(s / 2), mx);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_operations(queries: Vec<Vec<i32>>) -> i64 {
        let f = |x: i64| -> i64 {
            let mut res: i64 = 0;
            let mut p: i64 = 1;
            let mut i: i64 = 1;
            while p <= x {
                let cnt = std::cmp::min(p * 4 - 1, x) - p + 1;
                res += cnt * i;
                i += 1;
                p *= 4;
            }
            res
        };

        let mut ans: i64 = 0;
        for q in queries {
            let l = q[0] as i64;
            let r = q[1] as i64;
            let s = f(r) - f(l - 1);
            let mx = f(r) - f(r - 1);
            ans += std::cmp::max((s + 1) / 2, mx);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
