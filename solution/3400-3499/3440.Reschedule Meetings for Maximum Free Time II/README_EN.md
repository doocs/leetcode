---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3440.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20II/README_EN.md
rating: 1997
source: Biweekly Contest 149 Q3
tags:
    - Greedy
    - Array
    - Enumeration
---

<!-- problem:start -->

# [3440. Reschedule Meetings for Maximum Free Time II](https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-ii)

[中文文档](/solution/3400-3499/3440.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>eventTime</code> denoting the duration of an event. You are also given two integer arrays <code>startTime</code> and <code>endTime</code>, each of length <code>n</code>.</p>

<p>These represent the start and end times of <code>n</code> <strong>non-overlapping</strong> meetings that occur during the event between time <code>t = 0</code> and time <code>t = eventTime</code>, where the <code>i<sup>th</sup></code> meeting occurs during the time <code>[startTime[i], endTime[i]].</code></p>

<p>You can reschedule <strong>at most </strong>one meeting by moving its start time while maintaining the <strong>same duration</strong>, such that the meetings remain non-overlapping, to <strong>maximize</strong> the <strong>longest</strong> <em>continuous period of free time</em> during the event.</p>

<p>Return the <strong>maximum</strong> amount of free time possible after rearranging the meetings.</p>

<p><strong>Note</strong> that the meetings can <strong>not</strong> be rescheduled to a time outside the event and they should remain non-overlapping.</p>

<p><strong>Note:</strong> <em>In this version</em>, it is <strong>valid</strong> for the relative ordering of the meetings to change after rescheduling one meeting.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">eventTime = 5, startTime = [1,3], endTime = [2,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3440.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20II/images/example0_rescheduled.png" style="width: 375px; height: 123px;" /></p>

<p>Reschedule the meeting at <code>[1, 2]</code> to <code>[2, 3]</code>, leaving no meetings during the time <code>[0, 2]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">eventTime = 10, startTime = [0,7,9], endTime = [1,8,10]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3440.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20II/images/rescheduled_example0.png" style="width: 375px; height: 125px;" /></p>

<p>Reschedule the meeting at <code>[0, 1]</code> to <code>[8, 9]</code>, leaving no meetings during the time <code>[0, 7]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">eventTime = 10, startTime = [0,3,7,9], endTime = [1,4,8,10]</span></p>

<p><strong>Output:</strong> 6</p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3440.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20II/images/image3.png" style="width: 375px; height: 125px;" /></strong></p>

<p>Reschedule the meeting at <code>[3, 4]</code> to <code>[8, 9]</code>, leaving no meetings during the time <code>[1, 7]</code>.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">eventTime = 5, startTime = [0,1,2,3,4], endTime = [1,2,3,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no time during the event not occupied by meetings.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= eventTime &lt;= 10<sup>9</sup></code></li>
	<li><code>n == startTime.length == endTime.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= startTime[i] &lt; endTime[i] &lt;= eventTime</code></li>
	<li><code>endTime[i] &lt;= startTime[i + 1]</code> where <code>i</code> lies in the range <code>[0, n - 2]</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

According to the problem description, for meeting $i$, let $l_i$ be the non-free position to its left, $r_i$ be the non-free position to its right, and let the duration of meeting $i$ be $w_i = \text{endTime}[i] - \text{startTime}[i]$. Then:

$$
l_i = \begin{cases}
0 & i = 0 \\\\
\text{endTime}[i - 1] & i > 0
\end{cases}
$$

$$
r_i = \begin{cases}
\text{eventTime} & i = n - 1 \\\\
\text{startTime}[i + 1] & i < n - 1
\end{cases}
$$

The meeting can be moved to the left or right, and the free time in this case is:

$$
r_i - l_i - w_i
$$

If there exists a maximum free time on the left, $\text{pre}_{i - 1}$, such that $\text{pre}_{i - 1} \geq w_i$, then meeting $i$ can be moved to that position on the left, resulting in a new free time:

$$
r_i - l_i
$$

Similarly, if there exists a maximum free time on the right, $\text{suf}_{i + 1}$, such that $\text{suf}_{i + 1} \geq w_i$, then meeting $i$ can be moved to that position on the right, resulting in a new free time:

$$
r_i - l_i
$$

Therefore, we first preprocess two arrays, $\text{pre}$ and $\text{suf}$, where $\text{pre}[i]$ represents the maximum free time in the range $[0, i]$, and $\text{suf}[i]$ represents the maximum free time in the range $[i, n - 1]$. Then, for each meeting $i$, we calculate the maximum free time after moving it, and take the maximum value.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of meetings.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxFreeTime(
        self, eventTime: int, startTime: List[int], endTime: List[int]
    ) -> int:
        n = len(startTime)
        pre = [0] * n
        suf = [0] * n
        pre[0] = startTime[0]
        suf[n - 1] = eventTime - endTime[-1]
        for i in range(1, n):
            pre[i] = max(pre[i - 1], startTime[i] - endTime[i - 1])
        for i in range(n - 2, -1, -1):
            suf[i] = max(suf[i + 1], startTime[i + 1] - endTime[i])
        ans = 0
        for i in range(n):
            l = 0 if i == 0 else endTime[i - 1]
            r = eventTime if i == n - 1 else startTime[i + 1]
            w = endTime[i] - startTime[i]
            ans = max(ans, r - l - w)
            if i and pre[i - 1] >= w:
                ans = max(ans, r - l)
            elif i + 1 < n and suf[i + 1] >= w:
                ans = max(ans, r - l)
        return ans
```

#### Java

```java
class Solution {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[] pre = new int[n];
        int[] suf = new int[n];

        pre[0] = startTime[0];
        suf[n - 1] = eventTime - endTime[n - 1];

        for (int i = 1; i < n; i++) {
            pre[i] = Math.max(pre[i - 1], startTime[i] - endTime[i - 1]);
        }

        for (int i = n - 2; i >= 0; i--) {
            suf[i] = Math.max(suf[i + 1], startTime[i + 1] - endTime[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int l = (i == 0) ? 0 : endTime[i - 1];
            int r = (i == n - 1) ? eventTime : startTime[i + 1];
            int w = endTime[i] - startTime[i];
            ans = Math.max(ans, r - l - w);

            if (i > 0 && pre[i - 1] >= w) {
                ans = Math.max(ans, r - l);
            } else if (i + 1 < n && suf[i + 1] >= w) {
                ans = Math.max(ans, r - l);
            }
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxFreeTime(int eventTime, vector<int>& startTime, vector<int>& endTime) {
        int n = startTime.size();
        vector<int> pre(n), suf(n);

        pre[0] = startTime[0];
        suf[n - 1] = eventTime - endTime[n - 1];

        for (int i = 1; i < n; ++i) {
            pre[i] = max(pre[i - 1], startTime[i] - endTime[i - 1]);
        }

        for (int i = n - 2; i >= 0; --i) {
            suf[i] = max(suf[i + 1], startTime[i + 1] - endTime[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int l = (i == 0) ? 0 : endTime[i - 1];
            int r = (i == n - 1) ? eventTime : startTime[i + 1];
            int w = endTime[i] - startTime[i];
            ans = max(ans, r - l - w);

            if (i > 0 && pre[i - 1] >= w) {
                ans = max(ans, r - l);
            } else if (i + 1 < n && suf[i + 1] >= w) {
                ans = max(ans, r - l);
            }
        }

        return ans;
    }
};
```

#### Go

```go
func maxFreeTime(eventTime int, startTime []int, endTime []int) int {
	n := len(startTime)
	pre := make([]int, n)
	suf := make([]int, n)

	pre[0] = startTime[0]
	suf[n-1] = eventTime - endTime[n-1]

	for i := 1; i < n; i++ {
		pre[i] = max(pre[i-1], startTime[i]-endTime[i-1])
	}

	for i := n - 2; i >= 0; i-- {
		suf[i] = max(suf[i+1], startTime[i+1]-endTime[i])
	}

	ans := 0
	for i := 0; i < n; i++ {
		l := 0
		if i > 0 {
			l = endTime[i-1]
		}
		r := eventTime
		if i < n-1 {
			r = startTime[i+1]
		}
		w := endTime[i] - startTime[i]
		ans = max(ans, r-l-w)

		if i > 0 && pre[i-1] >= w {
			ans = max(ans, r-l)
		} else if i+1 < n && suf[i+1] >= w {
			ans = max(ans, r-l)
		}
	}

	return ans
}
```

#### TypeScript

```ts
function maxFreeTime(eventTime: number, startTime: number[], endTime: number[]): number {
    const n = startTime.length;
    const pre: number[] = Array(n).fill(0);
    const suf: number[] = Array(n).fill(0);

    pre[0] = startTime[0];
    suf[n - 1] = eventTime - endTime[n - 1];

    for (let i = 1; i < n; i++) {
        pre[i] = Math.max(pre[i - 1], startTime[i] - endTime[i - 1]);
    }

    for (let i = n - 2; i >= 0; i--) {
        suf[i] = Math.max(suf[i + 1], startTime[i + 1] - endTime[i]);
    }

    let ans = 0;
    for (let i = 0; i < n; i++) {
        const l = i === 0 ? 0 : endTime[i - 1];
        const r = i === n - 1 ? eventTime : startTime[i + 1];
        const w = endTime[i] - startTime[i];

        ans = Math.max(ans, r - l - w);

        if (i > 0 && pre[i - 1] >= w) {
            ans = Math.max(ans, r - l);
        } else if (i + 1 < n && suf[i + 1] >= w) {
            ans = Math.max(ans, r - l);
        }
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_free_time(event_time: i32, start_time: Vec<i32>, end_time: Vec<i32>) -> i32 {
        let n = start_time.len();
        let mut pre = vec![0; n];
        let mut suf = vec![0; n];

        pre[0] = start_time[0];
        suf[n - 1] = event_time - end_time[n - 1];

        for i in 1..n {
            pre[i] = pre[i - 1].max(start_time[i] - end_time[i - 1]);
        }

        for i in (0..n - 1).rev() {
            suf[i] = suf[i + 1].max(start_time[i + 1] - end_time[i]);
        }

        let mut ans = 0;
        for i in 0..n {
            let l = if i == 0 { 0 } else { end_time[i - 1] };
            let r = if i == n - 1 { event_time } else { start_time[i + 1] };
            let w = end_time[i] - start_time[i];
            ans = ans.max(r - l - w);

            if i > 0 && pre[i - 1] >= w {
                ans = ans.max(r - l);
            } else if i + 1 < n && suf[i + 1] >= w {
                ans = ans.max(r - l);
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
