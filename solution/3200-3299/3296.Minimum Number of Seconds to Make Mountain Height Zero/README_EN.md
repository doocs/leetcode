---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3296.Minimum%20Number%20of%20Seconds%20to%20Make%20Mountain%20Height%20Zero/README_EN.md
rating: 1694
source: Weekly Contest 416 Q2
tags:
    - Array
    - Math
    - Binary Search
---

<!-- problem:start -->

# [3296. Minimum Number of Seconds to Make Mountain Height Zero](https://leetcode.com/problems/minimum-number-of-seconds-to-make-mountain-height-zero)

[中文文档](/solution/3200-3299/3296.Minimum%20Number%20of%20Seconds%20to%20Make%20Mountain%20Height%20Zero/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>mountainHeight</code> denoting the height of a mountain.</p>

<p>You are also given an integer array <code>workerTimes</code> representing the work time of workers in <strong>seconds</strong>.</p>

<p>The workers work <strong>simultaneously</strong> to <strong>reduce</strong> the height of the mountain. For worker <code>i</code>:</p>

<ul>
	<li>To decrease the mountain&#39;s height by <code>x</code>, it takes <code>workerTimes[i] + workerTimes[i] * 2 + ... + workerTimes[i] * x</code> seconds. For example:

    <ul>
    	<li>To reduce the height of the mountain by 1, it takes <code>workerTimes[i]</code> seconds.</li>
    	<li>To reduce the height of the mountain by 2, it takes <code>workerTimes[i] + workerTimes[i] * 2</code> seconds, and so on.</li>
    </ul>
    </li>

</ul>

<p>Return an integer representing the <strong>minimum</strong> number of seconds required for the workers to make the height of the mountain 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">mountainHeight = 4, workerTimes = [2,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>One way the height of the mountain can be reduced to 0 is:</p>

<ul>
	<li>Worker 0 reduces the height by 1, taking <code>workerTimes[0] = 2</code> seconds.</li>
	<li>Worker 1 reduces the height by 2, taking <code>workerTimes[1] + workerTimes[1] * 2 = 3</code> seconds.</li>
	<li>Worker 2 reduces the height by 1, taking <code>workerTimes[2] = 1</code> second.</li>
</ul>

<p>Since they work simultaneously, the minimum time needed is <code>max(2, 3, 1) = 3</code> seconds.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">mountainHeight = 10, workerTimes = [3,2,2,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Worker 0 reduces the height by 2, taking <code>workerTimes[0] + workerTimes[0] * 2 = 9</code> seconds.</li>
	<li>Worker 1 reduces the height by 3, taking <code>workerTimes[1] + workerTimes[1] * 2 + workerTimes[1] * 3 = 12</code> seconds.</li>
	<li>Worker 2 reduces the height by 3, taking <code>workerTimes[2] + workerTimes[2] * 2 + workerTimes[2] * 3 = 12</code> seconds.</li>
	<li>Worker 3 reduces the height by 2, taking <code>workerTimes[3] + workerTimes[3] * 2 = 12</code> seconds.</li>
</ul>

<p>The number of seconds needed is <code>max(9, 12, 12, 12) = 12</code> seconds.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">mountainHeight = 5, workerTimes = [1]</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<p>There is only one worker in this example, so the answer is <code>workerTimes[0] + workerTimes[0] * 2 + workerTimes[0] * 3 + workerTimes[0] * 4 + workerTimes[0] * 5 = 15</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= mountainHeight &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= workerTimes.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= workerTimes[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

We notice that if all workers can reduce the mountain height to $0$ in $t$ seconds, then for any $t' > t$, the workers can also reduce the mountain height to $0$ in $t'$ seconds. Therefore, we can use binary search to find the minimum $t$ such that the workers can reduce the mountain height to $0$ in $t$ seconds.

We define a function $\textit{check}(t)$, which indicates whether the workers can reduce the mountain height to $0$ in $t$ seconds. Specifically, we iterate through each worker. For the current worker $\textit{workerTimes}[i]$, assuming they reduce the height by $h'$ in $t$ seconds, we can derive the inequality:

$$
\left(1 + h'\right) \cdot \frac{h'}{2} \cdot \textit{workerTimes}[i] \leq t
$$

Solving the inequality, we get:

$$
h' \leq \left\lfloor \sqrt{\frac{2t}{\textit{workerTimes}[i]} + \frac{1}{4}} - \frac{1}{2} \right\rfloor
$$

We can sum up all the $h'$ values for the workers to get the total reduced height $h$. If $h \geq \textit{mountainHeight}$, it means the workers can reduce the mountain height to $0$ in $t$ seconds.

Next, we determine the left boundary of the binary search $l = 1$. Since there is at least one worker, and each worker's working time does not exceed $10^6$, to reduce the mountain height to $0$, it takes at least $(1 + \textit{mountainHeight}) \cdot \textit{mountainHeight} / 2 \cdot \textit{workerTimes}[i] \leq 10^{16}$ seconds. Therefore, we can set the right boundary of the binary search to $r = 10^{16}$. Then, we continuously halve the interval $[l, r]$ until $l = r$. At this point, $l$ is the answer.

The time complexity is $O(n \times \log M)$, where $n$ is the number of workers, and $M$ is the right boundary of the binary search, which is $10^{16}$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minNumberOfSeconds(self, mountainHeight: int, workerTimes: List[int]) -> int:
        def check(t: int) -> bool:
            h = 0
            for wt in workerTimes:
                h += int(sqrt(2 * t / wt + 1 / 4) - 1 / 2)
            return h >= mountainHeight

        return bisect_left(range(10**16), True, key=check)
```

#### Java

```java
class Solution {
    private int mountainHeight;
    private int[] workerTimes;

    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        this.mountainHeight = mountainHeight;
        this.workerTimes = workerTimes;
        long l = 1, r = (long) 1e16;
        while (l < r) {
            long mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(long t) {
        long h = 0;
        for (int wt : workerTimes) {
            h += (long) (Math.sqrt(t * 2.0 / wt + 0.25) - 0.5);
        }
        return h >= mountainHeight;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minNumberOfSeconds(int mountainHeight, vector<int>& workerTimes) {
        using ll = long long;
        ll l = 1, r = 1e16;
        auto check = [&](ll t) -> bool {
            ll h = 0;
            for (int& wt : workerTimes) {
                h += (long long) (sqrt(t * 2.0 / wt + 0.25) - 0.5);
            }
            return h >= mountainHeight;
        };
        while (l < r) {
            ll mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
func minNumberOfSeconds(mountainHeight int, workerTimes []int) int64 {
	return int64(sort.Search(1e16, func(t int) bool {
		var h int64
		for _, wt := range workerTimes {
			h += int64(math.Sqrt(float64(t)*2.0/float64(wt)+0.25) - 0.5)
		}
		return h >= int64(mountainHeight)
	}))
}
```

#### TypeScript

```ts
function minNumberOfSeconds(mountainHeight: number, workerTimes: number[]): number {
    const check = (t: bigint): boolean => {
        let h = BigInt(0);
        for (const wt of workerTimes) {
            h += BigInt(Math.floor(Math.sqrt((Number(t) * 2.0) / wt + 0.25) - 0.5));
        }
        return h >= BigInt(mountainHeight);
    };

    let l = BigInt(1);
    let r = BigInt(1e16);

    while (l < r) {
        const mid = (l + r) >> BigInt(1);
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1n;
        }
    }

    return Number(l);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
