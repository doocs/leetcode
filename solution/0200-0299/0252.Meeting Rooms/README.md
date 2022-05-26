# [252. 会议室](https://leetcode-cn.com/problems/meeting-rooms)

[English Version](/solution/0200-0299/0252.Meeting%20Rooms/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 <code>[[s1,e1],[s2,e2],...]</code> (s<sub>i</sub> < e<sub>i</sub>)，请你判断一个人是否能够参加这里面的全部会议。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> <code>[[0,30],[5,10],[15,20]]</code>
<strong>输出:</strong> false
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> [[7,10],[2,4]]
<strong>输出:</strong> true
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
