---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3440.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20II/README.md
rating: 1997
source: 第 149 场双周赛 Q3
tags:
    - 贪心
    - 数组
    - 枚举
---

<!-- problem:start -->

# [3440. 重新安排会议得到最多空余时间 II](https://leetcode.cn/problems/reschedule-meetings-for-maximum-free-time-ii)

[English Version](/solution/3400-3499/3440.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数&nbsp;<code>eventTime</code>&nbsp;表示一个活动的总时长，这个活动开始于&nbsp;<code>t = 0</code>&nbsp;，结束于&nbsp;<code>t = eventTime</code>&nbsp;。</p>

<p>同时给你两个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>startTime</code> 和&nbsp;<code>endTime</code>&nbsp;。它们表示这次活动中 <code>n</code>&nbsp;个时间&nbsp;<strong>没有重叠</strong>&nbsp;的会议，其中第&nbsp;<code>i</code>&nbsp;个会议的时间为&nbsp;<code>[startTime[i], endTime[i]]</code>&nbsp;。</p>

<p>你可以重新安排 <strong>至多</strong>&nbsp;一个会议，安排的规则是将会议时间平移，且保持原来的 <strong>会议时长</strong>&nbsp;，你的目的是移动会议后 <strong>最大化</strong>&nbsp;相邻两个会议之间的 <strong>最长</strong> 连续空余时间。</p>

<p>请你返回重新安排会议以后，可以得到的 <strong>最大</strong>&nbsp;空余时间。</p>

<p><b>注意</b>，会议 <strong>不能</strong>&nbsp;安排到整个活动的时间以外，且会议之间需要保持互不重叠。</p>

<p><b>注意：</b>重新安排会议以后，会议之间的顺序可以发生改变。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>eventTime = 5, startTime = [1,3], endTime = [2,5]</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3440.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20II/images/example0_rescheduled.png" style="width: 375px; height: 123px;" /></p>

<p>将&nbsp;<code>[1, 2]</code>&nbsp;的会议安排到&nbsp;<code>[2, 3]</code>&nbsp;，得到空余时间&nbsp;<code>[0, 2]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>eventTime = 10, startTime = [0,7,9], endTime = [1,8,10]</span></p>

<p><span class="example-io"><b>输出：</b>7</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3440.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20II/images/rescheduled_example0.png" style="width: 375px; height: 125px;" /></p>

<p>将&nbsp;<code>[0, 1]</code>&nbsp;的会议安排到&nbsp;<code>[8, 9]</code>&nbsp;，得到空余时间&nbsp;<code>[0, 7]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>eventTime = 10, startTime = [0,3,7,9], endTime = [1,4,8,10]</span></p>

<p><b>输出：</b>6</p>

<p><b>解释：</b></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3440.Reschedule%20Meetings%20for%20Maximum%20Free%20Time%20II/images/image3.png" style="width: 375px; height: 125px;" /></strong></p>

<p>将&nbsp;<code>[3, 4]</code>&nbsp;的会议安排到&nbsp;<code>[8, 9]</code>&nbsp;，得到空余时间&nbsp;<code>[1, 7]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>eventTime = 5, startTime = [0,1,2,3,4], endTime = [1,2,3,4,5]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><b>解释：</b></p>

<p>活动中的所有时间都被会议安排满了。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= eventTime &lt;= 10<sup>9</sup></code></li>
	<li><code>n == startTime.length == endTime.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= startTime[i] &lt; endTime[i] &lt;= eventTime</code></li>
	<li><code>endTime[i] &lt;= startTime[i + 1]</code> 其中&nbsp;<code>i</code> 在范围&nbsp;<code>[0, n - 2]</code>&nbsp;之间。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
var maxFreeTime = function (eventTime, startTime, endTime) {
    let n = startTime.length;
    let maxGapBefore = 0,
        maxFreeTime = 0,
        lastEnd = 0;

    for (let i = 0; i < n; i++) {
        let duration = endTime[i] - startTime[i];
        let nextStart = i === n - 1 ? eventTime : startTime[i + 1];
        let freeTime = nextStart - lastEnd;
        if (duration > maxGapBefore) freeTime -= duration;
        maxFreeTime = Math.max(maxFreeTime, freeTime);
        maxGapBefore = Math.max(maxGapBefore, startTime[i] - lastEnd);
        lastEnd = endTime[i];
    }

    let maxGapAfter = 0,
        lastStart = eventTime;
    for (let i = n - 1; i >= 0; i--) {
        let duration = endTime[i] - startTime[i];
        let prevEnd = i === 0 ? 0 : endTime[i - 1];
        let freeTime = lastStart - prevEnd;
        if (duration <= maxGapAfter) maxFreeTime = Math.max(maxFreeTime, freeTime);
        maxGapAfter = Math.max(maxGapAfter, lastStart - endTime[i]);
        lastStart = startTime[i];
    }

    return maxFreeTime;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
