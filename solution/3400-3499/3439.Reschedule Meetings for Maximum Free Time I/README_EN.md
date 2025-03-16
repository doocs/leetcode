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

### Solution 1

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
