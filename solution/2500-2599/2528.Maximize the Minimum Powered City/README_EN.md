---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2528.Maximize%20the%20Minimum%20Powered%20City/README_EN.md
rating: 2235
source: Biweekly Contest 95 Q4
tags:
    - Greedy
    - Queue
    - Array
    - Binary Search
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [2528. Maximize the Minimum Powered City](https://leetcode.com/problems/maximize-the-minimum-powered-city)

[中文文档](/solution/2500-2599/2528.Maximize%20the%20Minimum%20Powered%20City/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>stations</code> of length <code>n</code>, where <code>stations[i]</code> represents the number of power stations in the <code>i<sup>th</sup></code> city.</p>

<p>Each power station can provide power to every city in a fixed <strong>range</strong>. In other words, if the range is denoted by <code>r</code>, then a power station at city <code>i</code> can provide power to all cities <code>j</code> such that <code>|i - j| &lt;= r</code> and <code>0 &lt;= i, j &lt;= n - 1</code>.</p>

<ul>
	<li>Note that <code>|x|</code> denotes <strong>absolute</strong> value. For example, <code>|7 - 5| = 2</code> and <code>|3 - 10| = 7</code>.</li>
</ul>

<p>The <strong>power</strong> of a city is the total number of power stations it is being provided power from.</p>

<p>The government has sanctioned building <code>k</code> more power stations, each of which can be built in any city, and have the same range as the pre-existing ones.</p>

<p>Given the two integers <code>r</code> and <code>k</code>, return <em>the <strong>maximum possible minimum power</strong> of a city, if the additional power stations are built optimally.</em></p>

<p><strong>Note</strong> that you can build the <code>k</code> power stations in multiple cities.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> stations = [1,2,4,5,0], r = 1, k = 2
<strong>Output:</strong> 5
<strong>Explanation:</strong> 
One of the optimal ways is to install both the power stations at city 1. 
So stations will become [1,4,4,5,0].
- City 0 is provided by 1 + 4 = 5 power stations.
- City 1 is provided by 1 + 4 + 4 = 9 power stations.
- City 2 is provided by 4 + 4 + 5 = 13 power stations.
- City 3 is provided by 5 + 4 = 9 power stations.
- City 4 is provided by 5 + 0 = 5 power stations.
So the minimum power of a city is 5.
Since it is not possible to obtain a larger power, we return 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> stations = [4,4,4,4], r = 0, k = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> 
It can be proved that we cannot make the minimum power of a city greater than 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == stations.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= stations[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= r&nbsp;&lt;= n - 1</code></li>
	<li><code>0 &lt;= k&nbsp;&lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search + Difference Array + Greedy

According to the problem description, the minimum number of power stations increases as the value of $k$ increases. Therefore, we can use binary search to find the largest minimum number of power stations, ensuring that the additional power stations needed do not exceed $k$.

First, we use a difference array and prefix sum to calculate the initial number of power stations in each city, recording it in the array $s$, where $s[i]$ represents the number of power stations in the $i$-th city.

Next, we define the left boundary of the binary search as $0$ and the right boundary as $2^{40}$. Then, we implement a function $check(x, k)$ to determine whether the minimum number of power stations in the cities can be $x$, ensuring that the additional power stations needed do not exceed $k$.

The implementation logic of the function $check(x, k)$ is as follows:

Traverse each city. If the number of power stations in the current city $i$ is less than $x$, we can greedily build a power station at the rightmost possible position, $j = \min(i + r, n - 1)$, to cover as many cities as possible. During this process, we can use the difference array to add a certain value to a continuous segment. If the number of additional power stations needed exceeds $k$, then $x$ does not meet the condition, and we return `false`. Otherwise, after the traversal, return `true`.

The time complexity is $O(n \times \log M)$, and the space complexity is $O(n)$. Here, $n$ is the number of cities, and $M$ is fixed at $2^{40}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxPower(self, stations: List[int], r: int, k: int) -> int:
        def check(x, k):
            d = [0] * (n + 1)
            t = 0
            for i in range(n):
                t += d[i]
                dist = x - (s[i] + t)
                if dist > 0:
                    if k < dist:
                        return False
                    k -= dist
                    j = min(i + r, n - 1)
                    left, right = max(0, j - r), min(j + r, n - 1)
                    d[left] += dist
                    d[right + 1] -= dist
                    t += dist
            return True

        n = len(stations)
        d = [0] * (n + 1)
        for i, v in enumerate(stations):
            left, right = max(0, i - r), min(i + r, n - 1)
            d[left] += v
            d[right + 1] -= v
        s = list(accumulate(d))
        left, right = 0, 1 << 40
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid, k):
                left = mid
            else:
                right = mid - 1
        return left
```

#### Java

```java
class Solution {
    private long[] s;
    private long[] d;
    private int n;

    public long maxPower(int[] stations, int r, int k) {
        n = stations.length;
        d = new long[n + 1];
        s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            int left = Math.max(0, i - r), right = Math.min(i + r, n - 1);
            d[left] += stations[i];
            d[right + 1] -= stations[i];
        }
        s[0] = d[0];
        for (int i = 1; i < n + 1; ++i) {
            s[i] = s[i - 1] + d[i];
        }
        long left = 0, right = 1l << 40;
        while (left < right) {
            long mid = (left + right + 1) >>> 1;
            if (check(mid, r, k)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(long x, int r, int k) {
        Arrays.fill(d, 0);
        long t = 0;
        for (int i = 0; i < n; ++i) {
            t += d[i];
            long dist = x - (s[i] + t);
            if (dist > 0) {
                if (k < dist) {
                    return false;
                }
                k -= dist;
                int j = Math.min(i + r, n - 1);
                int left = Math.max(0, j - r), right = Math.min(j + r, n - 1);
                d[left] += dist;
                d[right + 1] -= dist;
                t += dist;
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
    long long maxPower(vector<int>& stations, int r, int k) {
        int n = stations.size();
        long d[n + 1];
        memset(d, 0, sizeof d);
        for (int i = 0; i < n; ++i) {
            int left = max(0, i - r), right = min(i + r, n - 1);
            d[left] += stations[i];
            d[right + 1] -= stations[i];
        }
        long s[n + 1];
        s[0] = d[0];
        for (int i = 1; i < n + 1; ++i) {
            s[i] = s[i - 1] + d[i];
        }
        auto check = [&](long x, int k) {
            memset(d, 0, sizeof d);
            long t = 0;
            for (int i = 0; i < n; ++i) {
                t += d[i];
                long dist = x - (s[i] + t);
                if (dist > 0) {
                    if (k < dist) {
                        return false;
                    }
                    k -= dist;
                    int j = min(i + r, n - 1);
                    int left = max(0, j - r), right = min(j + r, n - 1);
                    d[left] += dist;
                    d[right + 1] -= dist;
                    t += dist;
                }
            }
            return true;
        };
        long left = 0, right = 1e12;
        while (left < right) {
            long mid = (left + right + 1) >> 1;
            if (check(mid, k)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};
```

#### Go

```go
func maxPower(stations []int, r int, k int) int64 {
	n := len(stations)
	d := make([]int, n+1)
	s := make([]int, n+1)
	for i, v := range stations {
		left, right := max(0, i-r), min(i+r, n-1)
		d[left] += v
		d[right+1] -= v
	}
	s[0] = d[0]
	for i := 1; i < n+1; i++ {
		s[i] = s[i-1] + d[i]
	}
	check := func(x, k int) bool {
		d := make([]int, n+1)
		t := 0
		for i := range stations {
			t += d[i]
			dist := x - (s[i] + t)
			if dist > 0 {
				if k < dist {
					return false
				}
				k -= dist
				j := min(i+r, n-1)
				left, right := max(0, j-r), min(j+r, n-1)
				d[left] += dist
				d[right+1] -= dist
				t += dist
			}
		}
		return true
	}
	left, right := 0, 1<<40
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid, k) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return int64(left)
}
```

#### TypeScript

```ts
function maxPower(stations: number[], r: number, k: number): number {
    function check(x: bigint, k: bigint): boolean {
        d.fill(0n);
        let t = 0n;
        for (let i = 0; i < n; ++i) {
            t += d[i];
            const dist = x - (s[i] + t);
            if (dist > 0) {
                if (k < dist) {
                    return false;
                }
                k -= dist;
                const j = Math.min(i + r, n - 1);
                const left = Math.max(0, j - r);
                const right = Math.min(j + r, n - 1);
                d[left] += dist;
                d[right + 1] -= dist;
                t += dist;
            }
        }
        return true;
    }
    const n = stations.length;
    const d: bigint[] = new Array(n + 1).fill(0n);
    const s: bigint[] = new Array(n + 1).fill(0n);

    for (let i = 0; i < n; ++i) {
        const left = Math.max(0, i - r);
        const right = Math.min(i + r, n - 1);
        d[left] += BigInt(stations[i]);
        d[right + 1] -= BigInt(stations[i]);
    }

    s[0] = d[0];
    for (let i = 1; i < n + 1; ++i) {
        s[i] = s[i - 1] + d[i];
    }

    let left = 0n,
        right = 1n << 40n;
    while (left < right) {
        const mid = (left + right + 1n) >> 1n;
        if (check(mid, BigInt(k))) {
            left = mid;
        } else {
            right = mid - 1n;
        }
    }
    return Number(left);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
