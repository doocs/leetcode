# [252. Meeting Rooms](https://leetcode.com/problems/meeting-rooms)

[中文文档](/solution/0200-0299/0252.Meeting%20Rooms/README.md)

## Description

<p>Given an array of meeting time intervals consisting of start and end times <code>[[s1,e1],[s2,e2],...]</code> (s<sub>i</sub> < e<sub>i</sub>), determine if a person could attend all meetings.</p>

<p><b>Example 1:</b></p>

<pre>
<b>Input:</b> <code>[[0,30],[5,10],[15,20]]</code>
<b>Output:</b> false
</pre>

<p><b>Example 2:</b></p>

<pre>
<b>Input:</b> [[7,10],[2,4]]
<b>Output:</b> true
</pre>

<p><strong>NOTE:</strong> input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canAttendMeetings(self, intervals: List[List[int]]) -> bool:
        intervals.sort(key=lambda x: x[0])
        for i in range(len(intervals) - 1):
            if intervals[i][1] > intervals[i + 1][0]:
                return False
        return True
```

### **Java**

```java
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        for (int i = 0, n = intervals.length; i < n - 1; ++i) {
            if (intervals[i][1] > intervals[i + 1][0]) return false;
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
