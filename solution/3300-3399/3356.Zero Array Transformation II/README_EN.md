---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3356.Zero%20Array%20Transformation%20II/README_EN.md
rating: 1913
source: Weekly Contest 424 Q3
tags:
    - Array
    - Binary Search
    - Prefix Sum
---

<!-- problem:start -->

# [3356. Zero Array Transformation II](https://leetcode.com/problems/zero-array-transformation-ii)

[中文文档](/solution/3300-3399/3356.Zero%20Array%20Transformation%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and a 2D array <code>queries</code> where <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>, val<sub>i</sub>]</code>.</p>

<p>Each <code>queries[i]</code> represents the following action on <code>nums</code>:</p>

<ul>
	<li>Decrement the value at each index in the range <code>[l<sub>i</sub>, r<sub>i</sub>]</code> in <code>nums</code> by <strong>at most</strong> <code>val<sub>i</sub></code>.</li>
	<li>The amount by which each value is decremented<!-- notionvc: b232c9d9-a32d-448c-85b8-b637de593c11 --> can be chosen <strong>independently</strong> for each index.</li>
</ul>

<p>A <strong>Zero Array</strong> is an array with all its elements equal to 0.</p>

<p>Return the <strong>minimum</strong> possible <strong>non-negative</strong> value of <code>k</code>, such that after processing the first <code>k</code> queries in <strong>sequence</strong>, <code>nums</code> becomes a <strong>Zero Array</strong>. If no such <code>k</code> exists, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,0,2], queries = [[0,2,1],[0,2,1],[1,1,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>For i = 0 (l = 0, r = 2, val = 1):</strong>

    <ul>
    	<li>Decrement values at indices <code>[0, 1, 2]</code> by <code>[1, 0, 1]</code> respectively.</li>
    	<li>The array will become <code>[1, 0, 1]</code>.</li>
    </ul>
    </li>
    <li><strong>For i = 1 (l = 0, r = 2, val = 1):</strong>
    <ul>
    	<li>Decrement values at indices <code>[0, 1, 2]</code> by <code>[1, 0, 1]</code> respectively.</li>
    	<li>The array will become <code>[0, 0, 0]</code>, which is a Zero Array. Therefore, the minimum value of <code>k</code> is 2.</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,2,1], queries = [[1,3,2],[0,2,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>For i = 0 (l = 1, r = 3, val = 2):</strong>

    <ul>
    	<li>Decrement values at indices <code>[1, 2, 3]</code> by <code>[2, 2, 1]</code> respectively.</li>
    	<li>The array will become <code>[4, 1, 0, 0]</code>.</li>
    </ul>
    </li>
    <li><strong>For i = 1 (l = 0, r = 2, val<span style="font-size: 13.3333px;"> </span>= 1):</strong>
    <ul>
    	<li>Decrement values at indices <code>[0, 1, 2]</code> by <code>[1, 1, 0]</code> respectively.</li>
    	<li>The array will become <code>[3, 0, 0, 0]</code>, which is not a Zero Array.</li>
    </ul>
    </li>

</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 3</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; nums.length</code></li>
	<li><code>1 &lt;= val<sub>i</sub> &lt;= 5</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Difference Array + Binary Search

We notice that the more queries we use, the easier it is to turn the array into a zero array, which shows monotonicity. Therefore, we can use binary search to enumerate the number of queries and check whether the array can be turned into a zero array after the first $k$ queries.

We define the left boundary $l$ and right boundary $r$ for binary search, initially $l = 0$, $r = m + 1$, where $m$ is the number of queries. We define a function $\text{check}(k)$ to indicate whether the array can be turned into a zero array after the first $k$ queries. We can use a difference array to maintain the value of each element.

Define an array $d$ of length $n + 1$, initialized to all $0$. For each of the first $k$ queries $[l, r, val]$, we add $val$ to $d[l]$ and subtract $val$ from $d[r + 1]$.

Then we iterate through the array $d$ in the range $[0, n - 1]$, accumulating the prefix sum $s$. If $\textit{nums}[i] > s$, it means $\textit{nums}$ cannot be transformed into a zero array, so we return $\textit{false}$.

During the binary search, if $\text{check}(k)$ returns $\text{true}$, it means the array can be turned into a zero array, so we update the right boundary $r$ to $k$; otherwise, we update the left boundary $l$ to $k + 1$.

Finally, we check whether $l > m$. If so, return -1; otherwise, return $l$.

The time complexity is $O((n + m) \times \log m)$, and the space complexity is $O(n)$, where $n$ and $m$ are the lengths of the array $\textit{nums}$ and $\textit{queries}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minZeroArray(self, nums: List[int], queries: List[List[int]]) -> int:
        def check(k: int) -> bool:
            d = [0] * (len(nums) + 1)
            for l, r, val in queries[:k]:
                d[l] += val
                d[r + 1] -= val
            s = 0
            for x, y in zip(nums, d):
                s += y
                if x > s:
                    return False
            return True

        m = len(queries)
        l = bisect_left(range(m + 1), True, key=check)
        return -1 if l > m else l
```

#### Java

```java
class Solution {
    private int n;
    private int[] nums;
    private int[][] queries;

    public int minZeroArray(int[] nums, int[][] queries) {
        this.nums = nums;
        this.queries = queries;
        n = nums.length;
        int m = queries.length;
        int l = 0, r = m + 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l > m ? -1 : l;
    }

    private boolean check(int k) {
        int[] d = new int[n + 1];
        for (int i = 0; i < k; ++i) {
            int l = queries[i][0], r = queries[i][1], val = queries[i][2];
            d[l] += val;
            d[r + 1] -= val;
        }
        for (int i = 0, s = 0; i < n; ++i) {
            s += d[i];
            if (nums[i] > s) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minZeroArray(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        int d[n + 1];
        int m = queries.size();
        int l = 0, r = m + 1;
        auto check = [&](int k) -> bool {
            memset(d, 0, sizeof(d));
            for (int i = 0; i < k; ++i) {
                int l = queries[i][0], r = queries[i][1], val = queries[i][2];
                d[l] += val;
                d[r + 1] -= val;
            }
            for (int i = 0, s = 0; i < n; ++i) {
                s += d[i];
                if (nums[i] > s) {
                    return false;
                }
            }
            return true;
        };
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l > m ? -1 : l;
    }
};
```

#### Go

```go
func minZeroArray(nums []int, queries [][]int) int {
	n, m := len(nums), len(queries)
	l := sort.Search(m+1, func(k int) bool {
		d := make([]int, n+1)
		for _, q := range queries[:k] {
			l, r, val := q[0], q[1], q[2]
			d[l] += val
			d[r+1] -= val
		}
		s := 0
		for i, x := range nums {
			s += d[i]
			if x > s {
				return false
			}
		}
		return true
	})
	if l > m {
		return -1
	}
	return l
}
```

#### TypeScript

```ts
function minZeroArray(nums: number[], queries: number[][]): number {
    const [n, m] = [nums.length, queries.length];
    const d: number[] = Array(n + 1);
    let [l, r] = [0, m + 1];
    const check = (k: number): boolean => {
        d.fill(0);
        for (let i = 0; i < k; ++i) {
            const [l, r, val] = queries[i];
            d[l] += val;
            d[r + 1] -= val;
        }
        for (let i = 0, s = 0; i < n; ++i) {
            s += d[i];
            if (nums[i] > s) {
                return false;
            }
        }
        return true;
    };
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l > m ? -1 : l;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_zero_array(nums: Vec<i32>, queries: Vec<Vec<i32>>) -> i32 {
        let n = nums.len();
        let m = queries.len();
        let mut d: Vec<i64> = vec![0; n + 1];
        let (mut l, mut r) = (0_usize, m + 1);

        let check = |k: usize, d: &mut Vec<i64>| -> bool {
            d.fill(0);
            for i in 0..k {
                let (l, r, val) = (
                    queries[i][0] as usize,
                    queries[i][1] as usize,
                    queries[i][2] as i64,
                );
                d[l] += val;
                d[r + 1] -= val;
            }
            let mut s: i64 = 0;
            for i in 0..n {
                s += d[i];
                if nums[i] as i64 > s {
                    return false;
                }
            }
            true
        };

        while l < r {
            let mid = (l + r) >> 1;
            if check(mid, &mut d) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if l > m { -1 } else { l as i32 }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
