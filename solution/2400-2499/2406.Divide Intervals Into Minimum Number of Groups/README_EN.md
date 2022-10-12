# [2406. Divide Intervals Into Minimum Number of Groups](https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups)

[中文文档](/solution/2400-2499/2406.Divide%20Intervals%20Into%20Minimum%20Number%20of%20Groups/README.md)

## Description

<p>You are given a 2D integer array <code>intervals</code> where <code>intervals[i] = [left<sub>i</sub>, right<sub>i</sub>]</code> represents the <strong>inclusive</strong> interval <code>[left<sub>i</sub>, right<sub>i</sub>]</code>.</p>

<p>You have to divide the intervals into one or more <strong>groups</strong> such that each interval is in <strong>exactly</strong> one group, and no two intervals that are in the same group <strong>intersect</strong> each other.</p>

<p>Return <em>the <strong>minimum</strong> number of groups you need to make</em>.</p>

<p>Two intervals <strong>intersect</strong> if there is at least one common number between them. For example, the intervals <code>[1, 5]</code> and <code>[5, 8]</code> intersect.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can divide the intervals into the following groups:
- Group 1: [1, 5], [6, 8].
- Group 2: [2, 3], [5, 10].
- Group 3: [1, 10].
It can be proven that it is not possible to divide the intervals into fewer than 3 groups.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,3],[5,6],[8,10],[11,13]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> None of the intervals overlap, so we can put all of them in one group.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 10<sup>5</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>1 &lt;= left<sub>i</sub> &lt;= right<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minGroups(self, intervals: List[List[int]]) -> int:
        h = []
        for a, b in sorted(intervals):
            if h and h[0] < a:
                heappop(h)
            heappush(h, b)
        return len(h)
```

### **Java**

```java
class Solution {
    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (var e : intervals) {
            if (!q.isEmpty() && q.peek() < e[0]) {
                q.poll();
            }
            q.offer(e[1]);
        }
        return q.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minGroups(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end());
        priority_queue<int, vector<int>, greater<int>> q;
        for (auto& e : intervals) {
            if (q.size() && q.top() < e[0]) {
                q.pop();
            }
            q.push(e[1]);
        }
        return q.size();
    }
};
```

### **Go**

```go
func minGroups(intervals [][]int) int {
	sort.Slice(intervals, func(i, j int) bool { return intervals[i][0] < intervals[j][0] })
	q := hp{}
	for _, e := range intervals {
		if q.Len() > 0 && q.IntSlice[0] < e[0] {
			heap.Pop(&q)
		}
		heap.Push(&q, e[1])
	}
	return q.Len()
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
