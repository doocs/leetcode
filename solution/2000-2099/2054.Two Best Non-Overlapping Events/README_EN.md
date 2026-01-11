---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/README_EN.md
rating: 1883
source: Biweekly Contest 64 Q2
tags:
    - Array
    - Binary Search
    - Dynamic Programming
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2054. Two Best Non-Overlapping Events](https://leetcode.com/problems/two-best-non-overlapping-events)

[中文文档](/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> 2D integer array of <code>events</code> where <code>events[i] = [startTime<sub>i</sub>, endTime<sub>i</sub>, value<sub>i</sub>]</code>. The <code>i<sup>th</sup></code> event starts at <code>startTime<sub>i</sub></code><sub> </sub>and ends at <code>endTime<sub>i</sub></code>, and if you attend this event, you will receive a value of <code>value<sub>i</sub></code>. You can choose <strong>at most</strong> <strong>two</strong> <strong>non-overlapping</strong> events to attend such that the sum of their values is <strong>maximized</strong>.</p>

<p>Return <em>this <strong>maximum</strong> sum.</em></p>

<p>Note that the start time and end time is <strong>inclusive</strong>: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time <code>t</code>, the next event must start at or after <code>t + 1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/images/untitled-diagramdrawio.png" style="width: 400px; height: 86px;" />
<pre>
<strong>Input:</strong> events = [[1,3,2],[4,5,2],[2,4,3]]
<strong>Output:</strong> 4
<strong>Explanation: </strong>Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="Example 1 Diagram" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/images/2054b.png" style="width: 400px; height: 86px;" />
<pre>
<strong>Input:</strong> events = [[1,3,2],[4,5,2],[1,5,5]]
<strong>Output:</strong> 5
<strong>Explanation: </strong>Choose event 2 for a sum of 5.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/images/2054c.png" style="width: 400px; height: 74px;" />
<pre>
<strong>Input:</strong> events = [[1,5,3],[1,5,1],[6,6,5]]
<strong>Output:</strong> 8
<strong>Explanation: </strong>Choose events 0 and 2 for a sum of 3 + 5 = 8.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= events.length &lt;= 10<sup>5</sup></code></li>
	<li><code>events[i].length == 3</code></li>
	<li><code>1 &lt;= startTime<sub>i</sub> &lt;= endTime<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= value<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Binary Search

We can sort the events by their start times, and then preprocess the maximum value starting from each event, i.e., $f[i]$ represents the maximum value of choosing one event from the $i$-th event to the last event.

Then we enumerate each event. For each event, we use binary search to find the first event whose start time is greater than the end time of the current event, denoted as $\textit{idx}$. The maximum value starting from the current event is $f[\textit{idx}]$ plus the value of the current event, which is the maximum value that can be obtained by choosing the current event as the first event. We take the maximum value among all these values.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the number of events.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxTwoEvents(self, events: List[List[int]]) -> int:
        events.sort()
        n = len(events)
        f = [events[-1][2]] * n
        for i in range(n - 2, -1, -1):
            f[i] = max(f[i + 1], events[i][2])
        ans = 0
        for _, e, v in events:
            idx = bisect_right(events, e, key=lambda x: x[0])
            if idx < n:
                v += f[idx]
            ans = max(ans, v)
        return ans
```

#### Java

```java
class Solution {
    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int n = events.length;
        int[] f = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            f[i] = Math.max(f[i + 1], events[i][2]);
        }
        int ans = 0;
        for (int[] e : events) {
            int v = e[2];
            int left = 0, right = n;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (events[mid][0] > e[1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (left < n) {
                v += f[left];
            }
            ans = Math.max(ans, v);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxTwoEvents(vector<vector<int>>& events) {
        ranges::sort(events);
        int n = events.size();
        vector<int> f(n + 1);
        for (int i = n - 1; ~i; --i) {
            f[i] = max(f[i + 1], events[i][2]);
        }
        int ans = 0;
        for (const auto& e : events) {
            int v = e[2];
            int left = 0, right = n;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (events[mid][0] > e[1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (left < n) {
                v += f[left];
            }
            ans = max(ans, v);
        }
        return ans;
    }
};
```

#### Go

```go
func maxTwoEvents(events [][]int) int {
	sort.Slice(events, func(i, j int) bool {
		return events[i][0] < events[j][0]
	})
	n := len(events)
	f := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		f[i] = max(f[i+1], events[i][2])
	}
	ans := 0
	for _, e := range events {
		v := e[2]
		left, right := 0, n
		for left < right {
			mid := (left + right) >> 1
			if events[mid][0] > e[1] {
				right = mid
			} else {
				left = mid + 1
			}
		}
		if left < n {
			v += f[left]
		}
		ans = max(ans, v)
	}
	return ans
}
```

#### TypeScript

```ts
function maxTwoEvents(events: number[][]): number {
    events.sort((a, b) => a[0] - b[0]);
    const n = events.length;
    const f: number[] = Array(n + 1).fill(0);
    for (let i = n - 1; ~i; --i) {
        f[i] = Math.max(f[i + 1], events[i][2]);
    }
    let ans = 0;
    for (const [_, end, v] of events) {
        let [left, right] = [0, n];
        while (left < right) {
            const mid = (left + right) >> 1;
            if (events[mid][0] > end) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        const t = left < n ? f[left] : 0;
        ans = Math.max(ans, t + v);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_two_events(mut events: Vec<Vec<i32>>) -> i32 {
        events.sort_by(|a, b| a[0].cmp(&b[0]));

        let n: usize = events.len();
        let mut f: Vec<i32> = vec![0; n + 1];

        for i in (0..n).rev() {
            f[i] = f[i + 1].max(events[i][2]);
        }

        let mut ans: i32 = 0;

        for e in &events {
            let mut v: i32 = e[2];

            let mut left: usize = 0;
            let mut right: usize = n;
            while left < right {
                let mid = (left + right) >> 1;
                if events[mid][0] > e[1] {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            if left < n {
                v += f[left];
            }

            ans = ans.max(v);
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
