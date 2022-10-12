# [871. Minimum Number of Refueling Stops](https://leetcode.com/problems/minimum-number-of-refueling-stops)

[中文文档](/solution/0800-0899/0871.Minimum%20Number%20of%20Refueling%20Stops/README.md)

## Description

<p>A car travels from a starting position to a destination which is <code>target</code> miles east of the starting position.</p>

<p>There are gas stations along the way. The gas stations are represented as an array <code>stations</code> where <code>stations[i] = [position<sub>i</sub>, fuel<sub>i</sub>]</code> indicates that the <code>i<sup>th</sup></code> gas station is <code>position<sub>i</sub></code> miles east of the starting position and has <code>fuel<sub>i</sub></code> liters of gas.</p>

<p>The car starts with an infinite tank of gas, which initially has <code>startFuel</code> liters of fuel in it. It uses one liter of gas per one mile that it drives. When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.</p>

<p>Return <em>the minimum number of refueling stops the car must make in order to reach its destination</em>. If it cannot reach the destination, return <code>-1</code>.</p>

<p>Note that if the car reaches a gas station with <code>0</code> fuel left, the car can still refuel there. If the car reaches the destination with <code>0</code> fuel left, it is still considered to have arrived.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = 1, startFuel = 1, stations = []
<strong>Output:</strong> 0
<strong>Explanation:</strong> We can reach the target without refueling.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = 100, startFuel = 1, stations = [[10,100]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> We can not reach the target (or even the first gas station).
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> We start with 10 liters of fuel.
We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
We made 2 refueling stops along the way, so we return 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target, startFuel &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= stations.length &lt;= 500</code></li>
	<li><code>1 &lt;= position<sub>i</sub> &lt; position<sub>i+1</sub> &lt; target</code></li>
	<li><code>1 &lt;= fuel<sub>i</sub> &lt; 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minRefuelStops(
        self, target: int, startFuel: int, stations: List[List[int]]
    ) -> int:
        q = []
        prev = ans = 0
        stations.append([target, 0])
        for a, b in stations:
            d = a - prev
            startFuel -= d
            while startFuel < 0 and q:
                startFuel -= heappop(q)
                ans += 1
            if startFuel < 0:
                return -1
            heappush(q, -b)
            prev = a
        return ans
```

### **Java**

```java
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        int n = stations.length;
        int prev = 0, ans = 0;
        for (int i = 0; i < n + 1; ++i) {
            int d = (i < n ? stations[i][0] : target) - prev;
            startFuel -= d;
            while (startFuel < 0 && !q.isEmpty()) {
                startFuel += q.poll();
                ++ans;
            }
            if (startFuel < 0) {
                return -1;
            }
            if (i < n) {
                q.offer(stations[i][1]);
                prev = stations[i][0];
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
    int minRefuelStops(int target, int startFuel, vector<vector<int>>& stations) {
        priority_queue<int> q;
        stations.push_back({target, 0});
        int ans = 0, prev = 0;
        for (auto& s : stations) {
            int d = s[0] - prev;
            startFuel -= d;
            while (startFuel < 0 && !q.empty()) {
                startFuel += q.top();
                q.pop();
                ++ans;
            }
            if (startFuel < 0) return -1;
            q.push(s[1]);
            prev = s[0];
        }
        return ans;
    }
};
```

### **Go**

```go
func minRefuelStops(target int, startFuel int, stations [][]int) int {
	stations = append(stations, []int{target, 0})
	ans, prev := 0, 0
	q := &hp{}
	heap.Init(q)
	for _, s := range stations {
		d := s[0] - prev
		startFuel -= d
		for startFuel < 0 && q.Len() > 0 {
			startFuel += q.pop()
			ans++
		}
		if startFuel < 0 {
			return -1
		}
		q.push(s[1])
		prev = s[0]
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool  { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) push(v int) { heap.Push(h, v) }
func (h *hp) pop() int   { return heap.Pop(h).(int) }
```

### **...**

```

```

<!-- tabs:end -->
