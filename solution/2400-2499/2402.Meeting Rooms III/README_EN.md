# [2402. Meeting Rooms III](https://leetcode.com/problems/meeting-rooms-iii)

[中文文档](/solution/2400-2499/2402.Meeting%20Rooms%20III/README.md)

## Description

<p>You are given an integer <code>n</code>. There are <code>n</code> rooms numbered from <code>0</code> to <code>n - 1</code>.</p>

<p>You are given a 2D integer array <code>meetings</code> where <code>meetings[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> means that a meeting will be held during the <strong>half-closed</strong> time interval <code>[start<sub>i</sub>, end<sub>i</sub>)</code>. All the values of <code>start<sub>i</sub></code> are <strong>unique</strong>.</p>

<p>Meetings are allocated to rooms in the following manner:</p>

<ol>
	<li>Each meeting will take place in the unused room with the <strong>lowest</strong> number.</li>
	<li>If there are no available rooms, the meeting will be delayed until a room becomes free. The delayed meeting should have the <strong>same</strong> duration as the original meeting.</li>
	<li>When a room becomes unused, meetings that have an earlier original <strong>start</strong> time should be given the room.</li>
</ol>

<p>Return<em> the <strong>number</strong> of the room that held the most meetings. </em>If there are multiple rooms, return<em> the room with the <strong>lowest</strong> number.</em></p>

<p>A <strong>half-closed interval</strong> <code>[a, b)</code> is the interval between <code>a</code> and <code>b</code> <strong>including</strong> <code>a</code> and <strong>not including</strong> <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
- At time 0, both rooms are not being used. The first meeting starts in room 0.
- At time 1, only room 1 is not being used. The second meeting starts in room 1.
- At time 2, both rooms are being used. The third meeting is delayed.
- At time 3, both rooms are being used. The fourth meeting is delayed.
- At time 5, the meeting in room 1 finishes. The third meeting starts in room 1 for the time period [5,10).
- At time 10, the meetings in both rooms finish. The fourth meeting starts in room 0 for the time period [10,11).
Both rooms 0 and 1 held 2 meetings, so we return 0. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
- At time 1, all three rooms are not being used. The first meeting starts in room 0.
- At time 2, rooms 1 and 2 are not being used. The second meeting starts in room 1.
- At time 3, only room 2 is not being used. The third meeting starts in room 2.
- At time 4, all three rooms are being used. The fourth meeting is delayed.
- At time 5, the meeting in room 2 finishes. The fourth meeting starts in room 2 for the time period [5,10).
- At time 6, all three rooms are being used. The fifth meeting is delayed.
- At time 10, the meetings in rooms 1 and 2 finish. The fifth meeting starts in room 1 for the time period [10,12).
Room 0 held 1 meeting while rooms 1 and 2 each held 2 meetings, so we return 1. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= meetings.length &lt;= 10<sup>5</sup></code></li>
	<li><code>meetings[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt; end<sub>i</sub> &lt;= 5 * 10<sup>5</sup></code></li>
	<li>All the values of <code>start<sub>i</sub></code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def mostBooked(self, n: int, meetings: List[List[int]]) -> int:
        meetings.sort()
        busy = []
        idle = list(range(n))
        heapify(idle)
        cnt = [0] * n
        for s, e in meetings:
            while busy and busy[0][0] <= s:
                heappush(idle, heappop(busy)[1])
            if idle:
                i = heappop(idle)
                cnt[i] += 1
                heappush(busy, (e, i))
            else:
                a, i = heappop(busy)
                cnt[i] += 1
                heappush(busy, (a + e - s, i))
        ans = 0
        for i, v in enumerate(cnt):
            if cnt[ans] < v:
                ans = i
        return ans
```

### **Java**

```java
class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> busy
            = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        PriorityQueue<Integer> idle = new PriorityQueue<>();
        for (int i = 0; i < n; ++i) {
            idle.offer(i);
        }
        int[] cnt = new int[n];
        for (var v : meetings) {
            int s = v[0], e = v[1];
            while (!busy.isEmpty() && busy.peek()[0] <= s) {
                idle.offer(busy.poll()[1]);
            }
            int i = 0;
            if (!idle.isEmpty()) {
                i = idle.poll();
                busy.offer(new int[] {e, i});
            } else {
                var x = busy.poll();
                i = x[1];
                busy.offer(new int[] {x[0] + e - s, i});
            }
            ++cnt[i];
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (cnt[ans] < cnt[i]) {
                ans = i;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
using ll = long long;
using pii = pair<ll, int>;

class Solution {
public:
    int mostBooked(int n, vector<vector<int>>& meetings) {
        priority_queue<int, vector<int>, greater<int>> idle;
        priority_queue<pii, vector<pii>, greater<pii>> busy;
        for (int i = 0; i < n; ++i) idle.push(i);
        vector<int> cnt(n);
        sort(meetings.begin(), meetings.end());
        for (auto& v : meetings) {
            int s = v[0], e = v[1];
            while (!busy.empty() && busy.top().first <= s) {
                idle.push(busy.top().second);
                busy.pop();
            }
            int i = 0;
            if (!idle.empty()) {
                i = idle.top();
                idle.pop();
                busy.push({e, i});
            } else {
                auto x = busy.top();
                busy.pop();
                i = x.second;
                busy.push({x.first + e - s, i});
            }
            ++cnt[i];
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (cnt[ans] < cnt[i]) {
                ans = i;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func mostBooked(n int, meetings [][]int) int {
	sort.Slice(meetings, func(i, j int) bool { return meetings[i][0] < meetings[j][0] })
	idle := hp{make([]int, n)}
	for i := 0; i < n; i++ {
		idle.IntSlice[i] = i
	}
	busy := hp2{}
	cnt := make([]int, n)
	for _, v := range meetings {
		s, e := v[0], v[1]
		for len(busy) > 0 && busy[0].end <= s {
			heap.Push(&idle, heap.Pop(&busy).(pair).i)
		}
		var i int
		if idle.Len() > 0 {
			i = heap.Pop(&idle).(int)
			heap.Push(&busy, pair{e, i})
		} else {
			x := heap.Pop(&busy).(pair)
			i = x.i
			heap.Push(&busy, pair{x.end + e - s, i})
		}
		cnt[i]++
	}
	ans := 0
	for i, v := range cnt {
		if cnt[ans] < v {
			ans = i
		}
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}

type pair struct{ end, i int }
type hp2 []pair

func (h hp2) Len() int { return len(h) }
func (h hp2) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.end < b.end || a.end == b.end && a.i < b.i
}
func (h hp2) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp2) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp2) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
