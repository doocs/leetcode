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

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxFreeTime(
        self, eventTime: int, startTime: List[int], endTime: List[int]
    ) -> int:
        n = len(startTime)
        res = 0

        left_gaps = [0] * n
        left_gaps[0] = startTime[0]
        for meet in range(1, n):
            left_gaps[meet] = max(
                left_gaps[meet - 1], startTime[meet] - endTime[meet - 1]
            )

        right_gaps = [0] * n
        right_gaps[n - 1] = eventTime - endTime[-1]
        for meet in range(n - 2, -1, -1):
            right_gaps[meet] = max(
                right_gaps[meet + 1], startTime[meet + 1] - endTime[meet]
            )

        for meet in range(n):
            left_gap = (
                left_gaps[meet] if meet == 0 else startTime[meet] - endTime[meet - 1]
            )
            right_gap = (
                right_gaps[meet]
                if meet == n - 1
                else startTime[meet + 1] - endTime[meet]
            )

            interval = 0

            if (
                meet != 0
                and left_gaps[meet - 1] >= (endTime[meet] - startTime[meet])
                or meet != n - 1
                and right_gaps[meet + 1] >= (endTime[meet] - startTime[meet])
            ):
                interval = endTime[meet] - startTime[meet]

            res = max(res, left_gap + interval + right_gap)

        return res
```

#### Java

```java
class Solution {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int maxGapBefore = 0;
        int maxFreeTime = 0;
        int lastEnd = 0;        
        for (int i = 0; i < n; i++) {
            int meetingTime = endTime[i] - startTime[i];
            int nextStart = (i == n - 1) ? eventTime : startTime[i + 1];
            int freeTime = nextStart - lastEnd;
            if (meetingTime > maxGapBefore) {
                freeTime -= meetingTime;
            }
            
            maxFreeTime = Math.max(maxFreeTime, freeTime);
            maxGapBefore = Math.max(maxGapBefore, startTime[i] - lastEnd);
            lastEnd = endTime[i];
        }
        int maxGapAfter = 0;
        int lastStart = eventTime;
        for (int i = n - 1; i >= 0; i--) {
            int meetingTime = endTime[i] - startTime[i];
            int prevEnd = (i == 0) ? 0 : endTime[i - 1];
            int freeTime = lastStart - prevEnd;            
            if (meetingTime <= maxGapAfter) {
                maxFreeTime = Math.max(maxFreeTime, freeTime);
            }
            maxGapAfter = Math.max(maxGapAfter, lastStart - endTime[i]);
            lastStart = startTime[i];
        }
        return maxFreeTime;
    }
}
```

#### C++

```cpp
class Solution {
public:
  int maxFreeTime(int eventTime, vector<int>& startTime, vector<int>& endTime) {
    int n = startTime.size();
    int max_gap_before = 0;
    int last_end = 0;
    int max_free_time = 0;

    for (int i = 0; i < n; ++i) {
      int meeting_time = endTime[i] - startTime[i];
      int next_start = (i == n - 1) ? eventTime : startTime[i + 1];
      int free_time = next_start - last_end;
      if (meeting_time > max_gap_before) free_time -= meeting_time;
      max_free_time = max(max_free_time, free_time);
      max_gap_before = max(max_gap_before, startTime[i] - last_end);
      last_end = endTime[i];
    }

    int max_gap_after = 0;
    int last_start = eventTime;
    for (int i = n - 1; i >= 0; --i) {
      int meeting_time = endTime[i] - startTime[i];
      int prev_end = (i == 0) ? 0 : endTime[i - 1];
      int free_time = last_start - prev_end;
      if (meeting_time <= max_gap_after)
        max_free_time = max(max_free_time, free_time);
      max_gap_after = max(max_gap_after, last_start - endTime[i]);
      last_start = startTime[i];
    }

    return max_free_time;
  }
};
```

#### Go

```go

```

#### Javascript 

```javascript
var maxFreeTime = function(eventTime, startTime, endTime) {
    let n = startTime.length;
    let maxGapBefore = 0, maxFreeTime = 0, lastEnd = 0;

    for (let i = 0; i < n; i++) {
        let duration = endTime[i] - startTime[i];
        let nextStart = (i === n - 1) ? eventTime : startTime[i + 1];
        let freeTime = nextStart - lastEnd;
        if (duration > maxGapBefore) freeTime -= duration;
        maxFreeTime = Math.max(maxFreeTime, freeTime);
        maxGapBefore = Math.max(maxGapBefore, startTime[i] - lastEnd);
        lastEnd = endTime[i];
    }

    let maxGapAfter = 0, lastStart = eventTime;
    for (let i = n - 1; i >= 0; i--) {
        let duration = endTime[i] - startTime[i];
            let prevEnd = (i === 0) ? 0 : endTime[i - 1];
            let freeTime = lastStart - prevEnd;
        if (duration <= maxGapAfter)
            maxFreeTime = Math.max(maxFreeTime, freeTime);
        maxGapAfter = Math.max(maxGapAfter, lastStart - endTime[i]);
        lastStart = startTime[i];
    }

    return maxFreeTime;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
