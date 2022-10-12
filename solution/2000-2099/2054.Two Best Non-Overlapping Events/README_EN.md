# [2054. Two Best Non-Overlapping Events](https://leetcode.com/problems/two-best-non-overlapping-events)

[中文文档](/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> 2D integer array of <code>events</code> where <code>events[i] = [startTime<sub>i</sub>, endTime<sub>i</sub>, value<sub>i</sub>]</code>. The <code>i<sup>th</sup></code> event starts at <code>startTime<sub>i</sub></code><sub> </sub>and ends at <code>endTime<sub>i</sub></code>, and if you attend this event, you will receive a value of <code>value<sub>i</sub></code>. You can choose <strong>at most</strong> <strong>two</strong> <strong>non-overlapping</strong> events to attend such that the sum of their values is <strong>maximized</strong>.</p>

<p>Return <em>this <strong>maximum</strong> sum.</em></p>

<p>Note that the start time and end time is <strong>inclusive</strong>: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time <code>t</code>, the next event must start at or after <code>t + 1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/images/picture5.png" style="width: 400px; height: 75px;" />
<pre>
<strong>Input:</strong> events = [[1,3,2],[4,5,2],[2,4,3]]
<strong>Output:</strong> 4
<strong>Explanation: </strong>Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="Example 1 Diagram" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/images/picture1.png" style="width: 400px; height: 77px;" />
<pre>
<strong>Input:</strong> events = [[1,3,2],[4,5,2],[1,5,5]]
<strong>Output:</strong> 5
<strong>Explanation: </strong>Choose event 2 for a sum of 5.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2054.Two%20Best%20Non-Overlapping%20Events/images/picture3.png" style="width: 400px; height: 66px;" />
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

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

```cpp
class Solution {
public:
    int maxTwoEvents(vector<vector<int>>& events) {
        sort(events.begin(), events.end());
        int n = events.size();
        vector<int> f(n + 1);
        for (int i = n - 1; ~i; --i) f[i] = max(f[i + 1], events[i][2]);
        int ans = 0;
        for (auto& e : events) {
            int v = e[2];
            int left = 0, right = n;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (events[mid][0] > e[1])
                    right = mid;
                else
                    left = mid + 1;
            }
            if (left < n) v += f[left];
            ans = max(ans, v);
        }
        return ans;
    }
};
```

### **Go**

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

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
