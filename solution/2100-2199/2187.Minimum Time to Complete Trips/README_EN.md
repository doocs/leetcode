---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2187.Minimum%20Time%20to%20Complete%20Trips/README_EN.md
rating: 1640
source: Weekly Contest 282 Q3
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [2187. Minimum Time to Complete Trips](https://leetcode.com/problems/minimum-time-to-complete-trips)

[中文文档](/solution/2100-2199/2187.Minimum%20Time%20to%20Complete%20Trips/README.md)

## Description

<p>You are given an array <code>time</code> where <code>time[i]</code> denotes the time taken by the <code>i<sup>th</sup></code> bus to complete <strong>one trip</strong>.</p>

<p>Each bus can make multiple trips <strong>successively</strong>; that is, the next trip can start <strong>immediately after</strong> completing the current trip. Also, each bus operates <strong>independently</strong>; that is, the trips of one bus do not influence the trips of any other bus.</p>

<p>You are also given an integer <code>totalTrips</code>, which denotes the number of trips all buses should make <strong>in total</strong>. Return <em>the <strong>minimum time</strong> required for all buses to complete <strong>at least</strong> </em><code>totalTrips</code><em> trips</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> time = [1,2,3], totalTrips = 5
<strong>Output:</strong> 3
<strong>Explanation:</strong>
- At time t = 1, the number of trips completed by each bus are [1,0,0]. 
  The total number of trips completed is 1 + 0 + 0 = 1.
- At time t = 2, the number of trips completed by each bus are [2,1,0]. 
  The total number of trips completed is 2 + 1 + 0 = 3.
- At time t = 3, the number of trips completed by each bus are [3,1,1]. 
  The total number of trips completed is 3 + 1 + 1 = 5.
So the minimum time needed for all buses to complete at least 5 trips is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> time = [2], totalTrips = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong>
There is only one bus, and it will complete its first trip at t = 2.
So the minimum time needed to complete 1 trip is 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= time.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= time[i], totalTrips &lt;= 10<sup>7</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

We notice that if we can complete at least $totalTrips$ trips in $t$ time, then we can also complete at least $totalTrips$ trips in $t' > t$ time. Therefore, we can use the method of binary search to find the smallest $t$.

We define the left boundary of the binary search as $l = 1$, and the right boundary as $r = \min(time) \times totalTrips$. For each binary search, we calculate the middle value $\text{mid} = \frac{l + r}{2}$, and then calculate the number of trips that can be completed in $\text{mid}$ time. If this number is greater than or equal to $totalTrips$, then we reduce the right boundary to $\text{mid}$, otherwise we increase the left boundary to $\text{mid} + 1$.

Finally, return the left boundary.

The time complexity is $O(n \times \log(m \times k))$, where $n$ and $k$ are the length of the array $time$ and $totalTrips$ respectively, and $m$ is the minimum value in the array $time$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minimumTime(self, time: List[int], totalTrips: int) -> int:
        mx = min(time) * totalTrips
        return bisect_left(
            range(mx), totalTrips, key=lambda x: sum(x // v for v in time)
        )
```

```java
class Solution {
    public long minimumTime(int[] time, int totalTrips) {
        int mi = time[0];
        for (int v : time) {
            mi = Math.min(mi, v);
        }
        long left = 1, right = (long) mi * totalTrips;
        while (left < right) {
            long cnt = 0;
            long mid = (left + right) >> 1;
            for (int v : time) {
                cnt += mid / v;
            }
            if (cnt >= totalTrips) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

```cpp
class Solution {
public:
    long long minimumTime(vector<int>& time, int totalTrips) {
        int mi = *min_element(time.begin(), time.end());
        long long left = 1, right = 1LL * mi * totalTrips;
        while (left < right) {
            long long cnt = 0;
            long long mid = (left + right) >> 1;
            for (int v : time) {
                cnt += mid / v;
            }
            if (cnt >= totalTrips) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
```

```go
func minimumTime(time []int, totalTrips int) int64 {
	mx := slices.Min(time) * totalTrips
	return int64(sort.Search(mx, func(x int) bool {
		cnt := 0
		for _, v := range time {
			cnt += x / v
		}
		return cnt >= totalTrips
	}))
}
```

```ts
function minimumTime(time: number[], totalTrips: number): number {
    let left = 1n;
    let right = BigInt(Math.min(...time)) * BigInt(totalTrips);
    while (left < right) {
        const mid = (left + right) >> 1n;
        const cnt = time.reduce((acc, v) => acc + mid / BigInt(v), 0n);
        if (cnt >= BigInt(totalTrips)) {
            right = mid;
        } else {
            left = mid + 1n;
        }
    }
    return Number(left);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
