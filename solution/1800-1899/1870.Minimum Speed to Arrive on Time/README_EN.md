---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1870.Minimum%20Speed%20to%20Arrive%20on%20Time/README_EN.md
rating: 1675
source: Weekly Contest 242 Q2
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [1870. Minimum Speed to Arrive on Time](https://leetcode.com/problems/minimum-speed-to-arrive-on-time)

[中文文档](/solution/1800-1899/1870.Minimum%20Speed%20to%20Arrive%20on%20Time/README.md)

## Description

<!-- description:start -->

<p>You are given a floating-point number <code>hour</code>, representing the amount of time you have to reach the office. To commute to the office, you must take <code>n</code> trains in sequential order. You are also given an integer array <code>dist</code> of length <code>n</code>, where <code>dist[i]</code> describes the distance (in kilometers) of the <code>i<sup>th</sup></code> train ride.</p>

<p>Each train can only depart at an integer hour, so you may need to wait in between each train ride.</p>

<ul>
	<li>For example, if the <code>1<sup>st</sup></code> train ride takes <code>1.5</code> hours, you must wait for an additional <code>0.5</code> hours before you can depart on the <code>2<sup>nd</sup></code> train ride at the 2 hour mark.</li>
</ul>

<p>Return <em>the <strong>minimum positive integer</strong> speed <strong>(in kilometers per hour)</strong> that all the trains must travel at for you to reach the office on time, or </em><code>-1</code><em> if it is impossible to be on time</em>.</p>

<p>Tests are generated such that the answer will not exceed <code>10<sup>7</sup></code> and <code>hour</code> will have <strong>at most two digits after the decimal point</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> dist = [1,3,2], hour = 6
<strong>Output:</strong> 1
<strong>Explanation: </strong>At speed 1:
- The first train ride takes 1/1 = 1 hour.
- Since we are already at an integer hour, we depart immediately at the 1 hour mark. The second train takes 3/1 = 3 hours.
- Since we are already at an integer hour, we depart immediately at the 4 hour mark. The third train takes 2/1 = 2 hours.
- You will arrive at exactly the 6 hour mark.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> dist = [1,3,2], hour = 2.7
<strong>Output:</strong> 3
<strong>Explanation: </strong>At speed 3:
- The first train ride takes 1/3 = 0.33333 hours.
- Since we are not at an integer hour, we wait until the 1 hour mark to depart. The second train ride takes 3/3 = 1 hour.
- Since we are already at an integer hour, we depart immediately at the 2 hour mark. The third train takes 2/3 = 0.66667 hours.
- You will arrive at the 2.66667 hour mark.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> dist = [1,3,2], hour = 1.9
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is impossible because the earliest the third train can depart is at the 2 hour mark.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == dist.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= dist[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= hour &lt;= 10<sup>9</sup></code></li>
	<li>There will be at most two digits after the decimal point in <code>hour</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

We notice that if a speed value $v$ allows us to arrive within the stipulated time, then for any $v' > v$, we can also definitely arrive within the stipulated time. This exhibits monotonicity, hence we can use binary search to find the smallest speed value that meets the condition.

Before conducting the binary search, we need to first determine if it is possible to arrive within the stipulated time. If the number of trains is greater than the ceiling of the stipulated time, then it is definitely impossible to arrive within the stipulated time, and we should directly return $-1$.

Next, we define the left and right boundaries for the binary search as $l = 1$, $r = 10^7 + 1$, and then we take the middle value $\textit{mid} = \frac{l + r}{2}$ each time to check if it meets the condition. If it does, we move the right boundary to $\textit{mid}$; otherwise, we move the left boundary to $\textit{mid} + 1$.

The problem is transformed into determining whether a speed value $v$ can allow us to arrive within the stipulated time. We can traverse each train trip, calculate the running time of each trip $t = \frac{d}{v}$, if it is the last trip, we directly add $t$; otherwise, we round up and add $t$. Finally, we check if the total time is less than or equal to the stipulated time, if so, it means the condition is met.

After the binary search ends, if the left boundary exceeds $10^7$, it means we cannot arrive within the stipulated time, and we return $-1$; otherwise, we return the left boundary.

The time complexity is $O(n \times \log M)$, where $n$ and $M$ are the number of train trips and the upper bound of the speed, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSpeedOnTime(self, dist: List[int], hour: float) -> int:
        def check(v: int) -> bool:
            s = 0
            for i, d in enumerate(dist):
                t = d / v
                s += t if i == len(dist) - 1 else ceil(t)
            return s <= hour

        if len(dist) > ceil(hour):
            return -1
        r = 10**7 + 1
        ans = bisect_left(range(1, r), True, key=check) + 1
        return -1 if ans == r else ans
```

#### Java

```java
class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        if (dist.length > Math.ceil(hour)) {
            return -1;
        }
        final int m = (int) 1e7;
        int l = 1, r = m + 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(dist, mid, hour)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l > m ? -1 : l;
    }

    private boolean check(int[] dist, int v, double hour) {
        double s = 0;
        int n = dist.length;
        for (int i = 0; i < n; ++i) {
            double t = dist[i] * 1.0 / v;
            s += i == n - 1 ? t : Math.ceil(t);
        }
        return s <= hour;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSpeedOnTime(vector<int>& dist, double hour) {
        if (dist.size() > ceil(hour)) {
            return -1;
        }
        const int m = 1e7;
        int l = 1, r = m + 1;
        int n = dist.size();
        auto check = [&](int v) {
            double s = 0;
            for (int i = 0; i < n; ++i) {
                double t = dist[i] * 1.0 / v;
                s += i == n - 1 ? t : ceil(t);
            }
            return s <= hour;
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
func minSpeedOnTime(dist []int, hour float64) int {
	if float64(len(dist)) > math.Ceil(hour) {
		return -1
	}
	const m int = 1e7
	n := len(dist)
	ans := sort.Search(m+1, func(v int) bool {
		v++
		s := 0.0
		for i, d := range dist {
			t := float64(d) / float64(v)
			if i == n-1 {
				s += t
			} else {
				s += math.Ceil(t)
			}
		}
		return s <= hour
	}) + 1
	if ans > m {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function minSpeedOnTime(dist: number[], hour: number): number {
    if (dist.length > Math.ceil(hour)) {
        return -1;
    }
    const n = dist.length;
    const m = 10 ** 7;
    const check = (v: number): boolean => {
        let s = 0;
        for (let i = 0; i < n; ++i) {
            const t = dist[i] / v;
            s += i === n - 1 ? t : Math.ceil(t);
        }
        return s <= hour;
    };
    let [l, r] = [1, m + 1];
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
    pub fn min_speed_on_time(dist: Vec<i32>, hour: f64) -> i32 {
        if dist.len() as f64 > hour.ceil() {
            return -1;
        }
        const M: i32 = 10_000_000;
        let (mut l, mut r) = (1, M + 1);
        let n = dist.len();
        let check = |v: i32| -> bool {
            let mut s = 0.0;
            for i in 0..n {
                let t = dist[i] as f64 / v as f64;
                s += if i == n - 1 { t } else { t.ceil() };
            }
            s <= hour
        };
        while l < r {
            let mid = (l + r) / 2;
            if check(mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if l > M {
            -1
        } else {
            l
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} dist
 * @param {number} hour
 * @return {number}
 */
var minSpeedOnTime = function (dist, hour) {
    if (dist.length > Math.ceil(hour)) {
        return -1;
    }
    const n = dist.length;
    const m = 10 ** 7;
    const check = v => {
        let s = 0;
        for (let i = 0; i < n; ++i) {
            const t = dist[i] / v;
            s += i === n - 1 ? t : Math.ceil(t);
        }
        return s <= hour;
    };
    let [l, r] = [1, m + 1];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l > m ? -1 : l;
};
```

#### Kotlin

```kotlin
class Solution {
    fun minSpeedOnTime(dist: IntArray, hour: Double): Int {
        val n = dist.size
        if (n > Math.ceil(hour)) {
            return -1
        }
        val m = 1e7.toInt()
        var left = 1
        var right = m + 1
        while (left < right) {
            val middle = (left + right) / 2
            var time = 0.0
            dist.forEachIndexed { i, item ->
                val t = item.toDouble() / middle
                time += if (i == n - 1) t else Math.ceil(t)
            }
            if (time > hour) {
                left = middle + 1
            } else {
                right = middle
            }
        }
        return if (left > m) -1 else left
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
