# [1353. Maximum Number of Events That Can Be Attended](https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended)

[中文文档](/solution/1300-1399/1353.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended/README.md)

## Description

<p>You are given an array of <code>events</code> where <code>events[i] = [startDay<sub>i</sub>, endDay<sub>i</sub>]</code>. Every event <code>i</code> starts at <code>startDay<sub>i</sub></code><sub> </sub>and ends at <code>endDay<sub>i</sub></code>.</p>

<p>You can attend an event <code>i</code> at any day <code>d</code> where <code>startTime<sub>i</sub> &lt;= d &lt;= endTime<sub>i</sub></code>. You can only attend one event at any time <code>d</code>.</p>

<p>Return <em>the maximum number of events you can attend</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1353.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended/images/e1.png" style="width: 400px; height: 267px;" />
<pre>
<strong>Input:</strong> events = [[1,2],[2,3],[3,4]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> You can attend all the three events.
One way to attend them all is as shown.
Attend the first event on day 1.
Attend the second event on day 2.
Attend the third event on day 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> events= [[1,2],[2,3],[3,4],[1,2]]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= events.length &lt;= 10<sup>5</sup></code></li>
	<li><code>events[i].length == 2</code></li>
	<li><code>1 &lt;= startDay<sub>i</sub> &lt;= endDay<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxEvents(self, events: List[List[int]]) -> int:
        d = defaultdict(list)
        i, j = inf, 0
        for s, e in events:
            d[s].append(e)
            i = min(i, s)
            j = max(j, e)
        h = []
        ans = 0
        for s in range(i, j + 1):
            while h and h[0] < s:
                heappop(h)
            for e in d[s]:
                heappush(h, e)
            if h:
                ans += 1
                heappop(h)
        return ans
```

### **Java**

```java
class Solution {
    public int maxEvents(int[][] events) {
        Map<Integer, List<Integer>> d = new HashMap<>();
        int i = Integer.MAX_VALUE, j = 0;
        for (var v : events) {
            int s = v[0], e = v[1];
            d.computeIfAbsent(s, k -> new ArrayList<>()).add(e);
            i = Math.min(i, s);
            j = Math.max(j, e);
        }
        PriorityQueue<Integer> q = new PriorityQueue<>();
        int ans = 0;
        for (int s = i; s <= j; ++s) {
            while (!q.isEmpty() && q.peek() < s) {
                q.poll();
            }
            for (int e : d.getOrDefault(s, Collections.emptyList())) {
                q.offer(e);
            }
            if (!q.isEmpty()) {
                q.poll();
                ++ans;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxEvents(vector<vector<int>>& events) {
        unordered_map<int, vector<int>> d;
        int i = INT_MAX, j = 0;
        for (auto& v : events) {
            int s = v[0], e = v[1];
            d[s].push_back(e);
            i = min(i, s);
            j = max(j, e);
        }
        priority_queue<int, vector<int>, greater<int>> q;
        int ans = 0;
        for (int s = i; s <= j; ++s) {
            while (q.size() && q.top() < s) {
                q.pop();
            }
            for (int e : d[s]) {
                q.push(e);
            }
            if (q.size()) {
                ++ans;
                q.pop();
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxEvents(events [][]int) int {
	d := map[int][]int{}
	i, j := math.MaxInt32, 0
	for _, v := range events {
		s, e := v[0], v[1]
		d[s] = append(d[s], e)
		i = min(i, s)
		j = max(j, e)
	}
	q := hp{}
	ans := 0
	for s := i; s <= j; s++ {
		for q.Len() > 0 && q.IntSlice[0] < s {
			heap.Pop(&q)
		}
		for _, e := range d[s] {
			heap.Push(&q, e)
		}
		if q.Len() > 0 {
			heap.Pop(&q)
			ans++
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
```

### **...**

```

```

<!-- tabs:end -->
