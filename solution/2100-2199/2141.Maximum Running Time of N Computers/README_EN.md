---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2141.Maximum%20Running%20Time%20of%20N%20Computers/README_EN.md
rating: 2265
source: Weekly Contest 276 Q4
tags:
    - Greedy
    - Array
    - Binary Search
    - Sorting
---

<!-- problem:start -->

# [2141. Maximum Running Time of N Computers](https://leetcode.com/problems/maximum-running-time-of-n-computers)

[中文文档](/solution/2100-2199/2141.Maximum%20Running%20Time%20of%20N%20Computers/README.md)

## Description

<!-- description:start -->

<p>You have <code>n</code> computers. You are given the integer <code>n</code> and a <strong>0-indexed</strong> integer array <code>batteries</code> where the <code>i<sup>th</sup></code> battery can <strong>run</strong> a computer for <code>batteries[i]</code> minutes. You are interested in running <strong>all</strong> <code>n</code> computers <strong>simultaneously</strong> using the given batteries.</p>

<p>Initially, you can insert <strong>at most one battery</strong> into each computer. After that and at any integer time moment, you can remove a battery from a computer and insert another battery <strong>any number of times</strong>. The inserted battery can be a totally new battery or a battery from another computer. You may assume that the removing and inserting processes take no time.</p>

<p>Note that the batteries cannot be recharged.</p>

<p>Return <em>the <strong>maximum</strong> number of minutes you can run all the </em><code>n</code><em> computers simultaneously.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2141.Maximum%20Running%20Time%20of%20N%20Computers/images/example1-fit.png" style="width: 762px; height: 150px;" />
<pre>
<strong>Input:</strong> n = 2, batteries = [3,3,3]
<strong>Output:</strong> 4
<strong>Explanation:</strong> 
Initially, insert battery 0 into the first computer and battery 1 into the second computer.
After two minutes, remove battery 1 from the second computer and insert battery 2 instead. Note that battery 1 can still run for one minute.
At the end of the third minute, battery 0 is drained, and you need to remove it from the first computer and insert battery 1 instead.
By the end of the fourth minute, battery 1 is also drained, and the first computer is no longer running.
We can run the two computers simultaneously for at most 4 minutes, so we return 4.

</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2141.Maximum%20Running%20Time%20of%20N%20Computers/images/example2.png" style="width: 629px; height: 150px;" />
<pre>
<strong>Input:</strong> n = 2, batteries = [1,1,1,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
Initially, insert battery 0 into the first computer and battery 2 into the second computer. 
After one minute, battery 0 and battery 2 are drained so you need to remove them and insert battery 1 into the first computer and battery 3 into the second computer. 
After another minute, battery 1 and battery 3 are also drained so the first and second computers are no longer running.
We can run the two computers simultaneously for at most 2 minutes, so we return 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= batteries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= batteries[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

We notice that if we can run $n$ computers simultaneously for $t$ minutes, then we can also run $n$ computers simultaneously for $t' \le t$ minutes, which shows monotonicity. Therefore, we can use the binary search method to find the maximum $t$.

We define the left boundary of the binary search as $l=0$ and the right boundary as $r=\sum_{i=0}^{n-1} batteries[i]$. During each binary search iteration, we use a variable $mid$ to represent the current middle value, i.e., $mid = (l + r + 1) >> 1$. We check if there exists a scheme that allows $n$ computers to run simultaneously for $mid$ minutes. If such a scheme exists, then we update $l$ to $mid$; otherwise, we update $r$ to $mid - 1$. Finally, we return $l$ as the answer.

The problem is transformed into how to determine if there exists a scheme that allows $n$ computers to run simultaneously for $mid$ minutes. If a battery can run for more minutes than $mid$, since the computers run simultaneously for $mid$ minutes and a battery can only power one computer at a time, we can only use this battery for $mid$ minutes. If a battery can run for minutes less than or equal to $mid$, we can use all the power of this battery. Therefore, we calculate the total minutes $s$ that all batteries can power, and if $s \ge n \times mid$, then we can make $n$ computers run simultaneously for $mid$ minutes.

The time complexity is $O(n \times \log M)$, where $M$ is the total power of all batteries, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxRunTime(self, n: int, batteries: List[int]) -> int:
        l, r = 0, sum(batteries)
        while l < r:
            mid = (l + r + 1) >> 1
            if sum(min(x, mid) for x in batteries) >= n * mid:
                l = mid
            else:
                r = mid - 1
        return l
```

#### Java

```java
class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long l = 0, r = 0;
        for (int x : batteries) {
            r += x;
        }
        while (l < r) {
            long mid = (l + r + 1) >> 1;
            long s = 0;
            for (int x : batteries) {
                s += Math.min(mid, x);
            }
            if (s >= n * mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxRunTime(int n, vector<int>& batteries) {
        long long l = 0, r = 0;
        for (int x : batteries) {
            r += x;
        }
        while (l < r) {
            long long mid = (l + r + 1) >> 1;
            long long s = 0;
            for (int x : batteries) {
                s += min(1LL * x, mid);
            }
            if (s >= n * mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
func maxRunTime(n int, batteries []int) int64 {
	l, r := 0, 0
	for _, x := range batteries {
		r += x
	}
	for l < r {
		mid := (l + r + 1) >> 1
		s := 0
		for _, x := range batteries {
			s += min(x, mid)
		}
		if s >= n*mid {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return int64(l)
}
```

#### TypeScript

```ts
function maxRunTime(n: number, batteries: number[]): number {
    let l = 0n;
    let r = 0n;
    for (const x of batteries) {
        r += BigInt(x);
    }
    while (l < r) {
        const mid = (l + r + 1n) >> 1n;
        let s = 0n;
        for (const x of batteries) {
            s += BigInt(Math.min(x, Number(mid)));
        }
        if (s >= mid * BigInt(n)) {
            l = mid;
        } else {
            r = mid - 1n;
        }
    }
    return Number(l);
}
```

#### Rust

```rust
impl Solution {
    pub fn max_run_time(n: i32, batteries: Vec<i32>) -> i64 {
        let n = n as i64;
        let mut l: i64 = 0;
        let mut r: i64 = batteries.iter().map(|&x| x as i64).sum();

        while l < r {
            let mid = (l + r + 1) >> 1;
            let mut s: i64 = 0;

            for &x in &batteries {
                let v = x as i64;
                s += if v < mid { v } else { mid };
            }

            if s >= n * mid {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        l
    }
}
```

#### C#

```cs
public class Solution {
    public long MaxRunTime(int n, int[] batteries) {
        long l = 0, r = 0;
        foreach (int x in batteries) {
            r += x;
        }

        while (l < r) {
            long mid = (l + r + 1) >> 1;
            long s = 0;

            foreach (int x in batteries) {
                s += Math.Min(mid, (long)x);
            }

            if (s >= (long)n * mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
