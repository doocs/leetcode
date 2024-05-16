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

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

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

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn max_run_time(n: i32, batteries: Vec<i32>) -> i64 {
        // First sort the batteries
        let mut batteries = batteries;
        let m = batteries.len() as i32;
        batteries.sort();

        let mut extra_sum: i64 = 0;
        for i in 0..(m - n) as usize {
            extra_sum += batteries[i] as i64;
        }

        let mut i = (m - n) as usize;
        let mut cur_height = batteries[i];
        let mut ret = cur_height as i64;
        while extra_sum != 0 {
            if i + 1 == (m as usize) {
                assert!(cur_height == *batteries.last().unwrap());
                ret += extra_sum / (n as i64);
                break;
            }

            if batteries[i] == batteries[i + 1] {
                i += 1;
                continue;
            }

            let diff = extra_sum / ((i - ((m - n) as usize) + 1) as i64);

            if (cur_height as i64) + diff <= (batteries[i + 1] as i64) {
                ret = (cur_height as i64) + diff;
                break;
            } else {
                extra_sum -=
                    ((batteries[i + 1] - batteries[i]) as i64) *
                    ((i - ((m - n) as usize) + 1) as i64);
                ret = batteries[i + 1] as i64;
            }

            i += 1;
            if i != (m as usize) {
                cur_height = batteries[i];
            }
        }

        ret
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
