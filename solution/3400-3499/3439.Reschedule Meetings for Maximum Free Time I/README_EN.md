---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3439.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20I/README_EN.md
rating: 1728
source: Biweekly Contest 149 Q2
tags:
    - Greedy
    - Array
    - Sliding Window
---

<!-- problem:start -->

# [3439. Reschedule Meetings for Maximum Free Time I](https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-i)

[中文文档](/solution/3400-3499/3439.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>eventTime</code> denoting the duration of an event, where the event occurs from time <code>t = 0</code> to time <code>t = eventTime</code>.</p>

<p>You are also given two integer arrays <code>startTime</code> and <code>endTime</code>, each of length <code>n</code>. These represent the start and end time of <code>n</code> <strong>non-overlapping</strong> meetings, where the <code>i<sup>th</sup></code> meeting occurs during the time <code>[startTime[i], endTime[i]]</code>.</p>

<p>You can reschedule <strong>at most</strong> <code>k</code> meetings by moving their start time while maintaining the <strong>same duration</strong>, to <strong>maximize</strong> the <strong>longest</strong> <em>continuous period of free time</em> during the event.</p>

<p>The <strong>relative</strong> order of all the meetings should stay the<em> same</em> and they should remain non-overlapping.</p>

<p>Return the <strong>maximum</strong> amount of free time possible after rearranging the meetings.</p>

<p><strong>Note</strong> that the meetings can <strong>not</strong> be rescheduled to a time outside the event.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">eventTime = 5, k = 1, startTime = [1,3], endTime = [2,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3439.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20I/images/example0_rescheduled.png" style="width: 375px; height: 123px;" /></p>

<p>Reschedule the meeting at <code>[1, 2]</code> to <code>[2, 3]</code>, leaving no meetings during the time <code>[0, 2]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">eventTime = 10, k = 1, startTime = [0,2,9], endTime = [1,4,10]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3439.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20I/images/example1_rescheduled.png" style="width: 375px; height: 125px;" /></p>

<p>Reschedule the meeting at <code>[2, 4]</code> to <code>[1, 3]</code>, leaving no meetings during the time <code>[3, 9]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">eventTime = 5, k = 2, startTime = [0,1,2,3,4], endTime = [1,2,3,4,5]</span></p>

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
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li><code>0 &lt;= startTime[i] &lt; endTime[i] &lt;= eventTime</code></li>
	<li><code>endTime[i] &lt;= startTime[i + 1]</code> where <code>i</code> lies in the range <code>[0, n - 2]</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window

The problem is essentially about merging adjacent free time intervals into a longer free interval. There are $n + 1$ free intervals in total:

- The first free interval is from the start of the event to the start of the first meeting;
- The middle $n - 1$ free intervals are between each pair of adjacent meetings;
- The last free interval is from the end of the last meeting to the end of the event.

At most $k$ meetings can be rescheduled, which is equivalent to merging up to $k + 1$ free intervals. We need to find the maximum length among all possible merged $k + 1$ free intervals.

We can store the lengths of these free intervals in an array $\textit{nums}$. Then, we use a sliding window of length $k + 1$ to traverse this array, calculate the sum for each window, and find the maximum sum, which is the maximum free time we are looking for.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of meetings.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxFreeTime(
        self, eventTime: int, k: int, startTime: List[int], endTime: List[int]
    ) -> int:
        nums = [startTime[0]]
        for i in range(1, len(endTime)):
            nums.append(startTime[i] - endTime[i - 1])
        nums.append(eventTime - endTime[-1])
        ans = s = 0
        for i, x in enumerate(nums):
            s += x
            if i >= k:
                ans = max(ans, s)
                s -= nums[i - k]
        return ans
```

#### Java

```java
class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = endTime.length;
        int[] nums = new int[n + 1];
        nums[0] = startTime[0];
        for (int i = 1; i < n; ++i) {
            nums[i] = startTime[i] - endTime[i - 1];
        }
        nums[n] = eventTime - endTime[n - 1];
        int ans = 0, s = 0;
        for (int i = 0; i <= n; ++i) {
            s += nums[i];
            if (i >= k) {
                ans = Math.max(ans, s);
                s -= nums[i - k];
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
    int maxFreeTime(int eventTime, int k, vector<int>& startTime, vector<int>& endTime) {
        int n = endTime.size();
        vector<int> nums(n + 1);
        nums[0] = startTime[0];
        for (int i = 1; i < n; ++i) {
            nums[i] = startTime[i] - endTime[i - 1];
        }
        nums[n] = eventTime - endTime[n - 1];

        int ans = 0, s = 0;
        for (int i = 0; i <= n; ++i) {
            s += nums[i];
            if (i >= k) {
                ans = max(ans, s);
                s -= nums[i - k];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxFreeTime(eventTime int, k int, startTime []int, endTime []int) int {
	n := len(endTime)
	nums := make([]int, n+1)
	nums[0] = startTime[0]
	for i := 1; i < n; i++ {
		nums[i] = startTime[i] - endTime[i-1]
	}
	nums[n] = eventTime - endTime[n-1]

	ans, s := 0, 0
	for i := 0; i <= n; i++ {
		s += nums[i]
		if i >= k {
			ans = max(ans, s)
			s -= nums[i-k]
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxFreeTime(eventTime: number, k: number, startTime: number[], endTime: number[]): number {
    const n = endTime.length;
    const nums: number[] = new Array(n + 1);
    nums[0] = startTime[0];
    for (let i = 1; i < n; i++) {
        nums[i] = startTime[i] - endTime[i - 1];
    }
    nums[n] = eventTime - endTime[n - 1];

    let [ans, s] = [0, 0];
    for (let i = 0; i <= n; i++) {
        s += nums[i];
        if (i >= k) {
            ans = Math.max(ans, s);
            s -= nums[i - k];
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_free_time(event_time: i32, k: i32, start_time: Vec<i32>, end_time: Vec<i32>) -> i32 {
        let n = end_time.len();
        let mut nums = vec![0; n + 1];
        nums[0] = start_time[0];
        for i in 1..n {
            nums[i] = start_time[i] - end_time[i - 1];
        }
        nums[n] = event_time - end_time[n - 1];

        let mut ans = 0;
        let mut s = 0;
        for i in 0..=n {
            s += nums[i];
            if i as i32 >= k {
                ans = ans.max(s);
                s -= nums[i - k as usize];
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Sliding Window (Space Optimization)

In Solution 1, we used an array to store the lengths of the free intervals. In fact, we do not need to store the entire array; we can use a function $f(i)$ to represent the length of the $i$-th free interval. This way, we can save space.

The time complexity is $O(n)$, where $n$ is the number of meetings. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxFreeTime(
        self, eventTime: int, k: int, startTime: List[int], endTime: List[int]
    ) -> int:
        def f(i: int) -> int:
            if i == 0:
                return startTime[0]
            if i == len(endTime):
                return eventTime - endTime[-1]
            return startTime[i] - endTime[i - 1]

        ans = s = 0
        for i in range(len(endTime) + 1):
            s += f(i)
            if i >= k:
                ans = max(ans, s)
                s -= f(i - k)
        return ans
```

#### Java

```java
class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = endTime.length;
        IntUnaryOperator f = i -> {
            if (i == 0) {
                return startTime[0];
            }
            if (i == n) {
                return eventTime - endTime[n - 1];
            }
            return startTime[i] - endTime[i - 1];
        };
        int ans = 0, s = 0;
        for (int i = 0; i <= n; i++) {
            s += f.applyAsInt(i);
            if (i >= k) {
                ans = Math.max(ans, s);
                s -= f.applyAsInt(i - k);
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
    int maxFreeTime(int eventTime, int k, vector<int>& startTime, vector<int>& endTime) {
        int n = endTime.size();
        auto f = [&](int i) -> int {
            if (i == 0) {
                return startTime[0];
            }
            if (i == n) {
                return eventTime - endTime[n - 1];
            }
            return startTime[i] - endTime[i - 1];
        };
        int ans = 0, s = 0;
        for (int i = 0; i <= n; ++i) {
            s += f(i);
            if (i >= k) {
                ans = max(ans, s);
                s -= f(i - k);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxFreeTime(eventTime int, k int, startTime []int, endTime []int) int {
	n := len(endTime)
	f := func(i int) int {
		if i == 0 {
			return startTime[0]
		}
		if i == n {
			return eventTime - endTime[n-1]
		}
		return startTime[i] - endTime[i-1]
	}
	ans, s := 0, 0
	for i := 0; i <= n; i++ {
		s += f(i)
		if i >= k {
			ans = max(ans, s)
			s -= f(i - k)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxFreeTime(eventTime: number, k: number, startTime: number[], endTime: number[]): number {
    const n = endTime.length;
    const f = (i: number): number => {
        if (i === 0) {
            return startTime[0];
        }
        if (i === n) {
            return eventTime - endTime[n - 1];
        }
        return startTime[i] - endTime[i - 1];
    };
    let ans = 0;
    let s = 0;
    for (let i = 0; i <= n; i++) {
        s += f(i);
        if (i >= k) {
            ans = Math.max(ans, s);
            s -= f(i - k);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_free_time(event_time: i32, k: i32, start_time: Vec<i32>, end_time: Vec<i32>) -> i32 {
        let n = end_time.len();
        let f = |i: usize| -> i32 {
            if i == 0 {
                start_time[0]
            } else if i == n {
                event_time - end_time[n - 1]
            } else {
                start_time[i] - end_time[i - 1]
            }
        };
        let mut ans = 0;
        let mut s = 0;
        for i in 0..=n {
            s += f(i);
            if i >= k as usize {
                ans = ans.max(s);
                s -= f(i - k as usize);
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
