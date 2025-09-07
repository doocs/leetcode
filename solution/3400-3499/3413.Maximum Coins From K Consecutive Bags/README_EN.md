---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3413.Maximum%20Coins%20From%20K%20Consecutive%20Bags/README_EN.md
rating: 2373
source: Weekly Contest 431 Q3
tags:
    - Greedy
    - Array
    - Binary Search
    - Prefix Sum
    - Sorting
    - Sliding Window
---

<!-- problem:start -->

# [3413. Maximum Coins From K Consecutive Bags](https://leetcode.com/problems/maximum-coins-from-k-consecutive-bags)

[中文文档](/solution/3400-3499/3413.Maximum%20Coins%20From%20K%20Consecutive%20Bags/README.md)

## Description

<!-- description:start -->

<p>There are an infinite amount of bags on a number line, one bag for each coordinate. Some of these bags contain coins.</p>

<p>You are given a 2D array <code>coins</code>, where <code>coins[i] = [l<sub>i</sub>, r<sub>i</sub>, c<sub>i</sub>]</code> denotes that every bag from <code>l<sub>i</sub></code> to <code>r<sub>i</sub></code> contains <code>c<sub>i</sub></code> coins.</p>

<p>The segments that <code>coins</code> contain are non-overlapping.</p>

<p>You are also given an integer <code>k</code>.</p>

<p>Return the <strong>maximum</strong> amount of coins you can obtain by collecting <code>k</code> consecutive bags.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">coins = [[8,10,1],[1,3,2],[5,6,4]], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>Selecting bags at positions <code>[3, 4, 5, 6]</code> gives the maximum number of coins:&nbsp;<code>2 + 0 + 4 + 4 = 10</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">coins = [[1,10,3]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>Selecting bags at positions <code>[1, 2]</code> gives the maximum number of coins:&nbsp;<code>3 + 3 = 6</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= coins.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li><code>coins[i] == [l<sub>i</sub>, r<sub>i</sub>, c<sub>i</sub>]</code></li>
	<li><code>1 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= c<sub>i</sub> &lt;= 1000</code></li>
	<li>The given segments are non-overlapping.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumCoins(self, coins: List[List[int]], k: int) -> int:
        coins.sort(key=itemgetter(0))
        i, total, ans, n = 0, 0, 0, len(coins)
        l0, r0, c0 = coins[0]
        print(coins)
        for l, r, c in coins:
            while i < n and r - coins[i][0] + 1 >= k:
                # print(r,coins[i][0],total)
                l0, r0, c0 = coins[i]
                ans = max(total + max(k + l0 - l, 0) * c, ans)
                total -= (r0 - l0 + 1) * c0
                i += 1

            total += (r - l + 1) * c
            # print(total,l,r)
            if i < n and coins[i][0] != l0:
                start_l = r - k + 1
                ans = max(total + max(r0 - start_l + 1, 0) * c0, ans)
        return max(ans, total)
```

#### Java

```java

```

#### C++

```cpp
class Solution {
private:
    struct interval {
        int start = -1, end = -1, value = 0;

        interval() = default;
        interval(int _start, int _end, int _value) : start(_start), end(_end), value(_value) {}

        bool operator < (const interval &other) const {
            return start < other.start || (start == other.start && end < other.end);
        }
        int64_t cost() const { return int64_t(value) * (end - start + 1); }
        int64_t cost(int len) const { return int64_t(value) * len; }
    };
public:
    long long maximumCoins(vector<vector<int>>& coins, int k) {
        int N = int(coins.size());
        vector<interval> A(N);

        for (int i = 0; i < N; i++) A[i] = {coins[i][0], coins[i][1], coins[i][2]};
        sort(A.begin(), A.end());

        int64_t ans = 0LL;
        {
            int64_t value = 0LL;
            int right = 0;
            for (int left = 0; left < N; left++) {
                while (right < N && A[right].end - A[left].start < k) {
                    value += A[right].cost();
                    ++right;
                }

                if (right < N) {
                    int64_t extra = (A[right].start - A[left].start <= k) ? A[right].cost(k - A[right].start + A[left].start) : 0LL;
                    ans = max(ans, value + extra);
                }

                value -= A[left].cost();
            }
        }

        {
            int64_t value = 0LL;
            int left = 0;
            for (int right = 0; right < N; right++) {
                value += A[right].cost();
                while (A[right].end - A[left].end >= k) {
                    value -= A[left].cost();
                    left++;
                }

                int64_t extra = (A[right].end - A[left].start >= k) ? A[left].cost(A[right].end - A[left].start - k + 1) : 0LL;
                ans = max(ans, value - extra);
            }
        }

        return ans;
    }
};
```

#### Go

```go
func maximumCoins(coins [][]int, k int) int64 {
	sort.Slice(coins, func(i, j int) bool {
		return coins[i][0] < coins[j][0]
	})

	n := len(coins)
	var total int64 = 0
	var ans int64 = 0
	i := 0
	l0, r0, c0 := coins[0][0], coins[0][1], coins[0][2]

	for _, seg := range coins {
		l, r, c := seg[0], seg[1], seg[2]

		for i < n && r-coins[i][0]+1 >= k {
			l0, r0, c0 = coins[i][0], coins[i][1], coins[i][2]
			ans = max64(ans, total+int64(max(k+l0-l, 0))*int64(c))
			total -= int64(r0-l0+1) * int64(c0)
			i++
		}

		total += int64(r-l+1) * int64(c)

		if i < n && coins[i][0] != l0 {
			startL := r - k + 1
			ans = max64(ans, total+int64(max(r0-startL+1, 0))*int64(c0))
		}
	}
	if total > ans {
		ans = total
	}
	return ans
}

func max64(a, b int64) int64 {
	if a > b {
		return a
	}
	return b
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

#### Rust

```rust
use std::cmp::{min, max};
fn f(coins: &[(i32, i32, i32)], k: i32) -> i64 {
    let mut result = 0;
    let mut sum = 0;
    let mut r = 0;

    for l in 0..coins.len() {
        while r < coins.len() && coins[l].0 + k > coins[r].1 {
            sum += (coins[r].1 - coins[r].0 + 1) as i64 * coins[r].2 as i64;
            r += 1;
        }
        result = max(result, sum);
        if r == coins.len() {
            break;
        }
        let cnt = coins[l].0 + k - coins[r].0;
        result = max(result, sum + max(cnt, 0) as i64 * coins[r].2 as i64);
        sum -= (coins[l].1 - coins[l].0 + 1) as i64 * coins[l].2 as i64;
    }

    result
}

impl Solution {
    pub fn maximum_coins(mut coins: Vec<Vec<i32>>, k: i32) -> i64 {
        let mut coins: Vec<_> = coins.into_iter()
                                 .map(|xs| (xs[0], xs[1], xs[2]))
                                 .collect();
        coins.sort();
        let a = f(&coins, k);
        coins.reverse();
        for i in 0..coins.len() {
            (coins[i].0, coins[i].1) = (-coins[i].1, -coins[i].0);
        }
        let b = f(&coins, k);

        max(a, b)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
