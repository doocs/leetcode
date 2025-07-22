---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0871.Minimum%20Number%20of%20Refueling%20Stops/README_EN.md
tags:
    - Greedy
    - Array
    - Dynamic Programming
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [871. Minimum Number of Refueling Stops](https://leetcode.com/problems/minimum-number-of-refueling-stops)

[中文文档](/solution/0800-0899/0871.Minimum%20Number%20of%20Refueling%20Stops/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Priority Queue (Max-Heap)

We can use a priority queue (max-heap) $\textit{pq}$ to record the fuel amounts of all the gas stations we have passed. Each time the fuel is insufficient, we greedily take out the maximum fuel amount, which is the top element of $\textit{pq}$, and accumulate the number of refuels $\textit{ans}$. If $\textit{pq}$ is empty and the current fuel is still insufficient, it means we cannot reach the destination, and we return $-1$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ represents the number of gas stations.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minRefuelStops(
        self, target: int, startFuel: int, stations: List[List[int]]
    ) -> int:
        pq = []
        ans = pre = 0
        stations.append([target, 0])
        for pos, fuel in stations:
            dist = pos - pre
            startFuel -= dist
            while startFuel < 0 and pq:
                startFuel -= heappop(pq)
                ans += 1
            if startFuel < 0:
                return -1
            heappush(pq, -fuel)
            pre = pos
        return ans
```

#### Java

```java
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int n = stations.length;
        int ans = 0, pre = 0;
        for (int i = 0; i <= n; ++i) {
            int pos = i < n ? stations[i][0] : target;
            int dist = pos - pre;
            startFuel -= dist;
            while (startFuel < 0 && !pq.isEmpty()) {
                startFuel += pq.poll();
                ++ans;
            }
            if (startFuel < 0) {
                return -1;
            }
            if (i < n) {
                pq.offer(stations[i][1]);
                pre = stations[i][0];
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minRefuelStops(int target, int startFuel, vector<vector<int>>& stations) {
        priority_queue<int> pq;
        stations.push_back({target, 0});
        int ans = 0, pre = 0;
        for (const auto& station : stations) {
            int pos = station[0], fuel = station[1];
            int dist = pos - pre;
            startFuel -= dist;
            while (startFuel < 0 && !pq.empty()) {
                startFuel += pq.top();
                pq.pop();
                ++ans;
            }
            if (startFuel < 0) {
                return -1;
            }
            pq.push(fuel);
            pre = pos;
        }
        return ans;
    }
};
```

#### Go

```go
func minRefuelStops(target int, startFuel int, stations [][]int) int {
	pq := &hp{}
	ans, pre := 0, 0
	stations = append(stations, []int{target, 0})
	for _, station := range stations {
		pos, fuel := station[0], station[1]
		dist := pos - pre
		startFuel -= dist
		for startFuel < 0 && pq.Len() > 0 {
			startFuel += heap.Pop(pq).(int)
			ans++
		}
		if startFuel < 0 {
			return -1
		}
		heap.Push(pq, fuel)
		pre = pos
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

#### TypeScript

```ts
function minRefuelStops(target: number, startFuel: number, stations: number[][]): number {
    const pq = new MaxPriorityQueue<number>();
    let [ans, pre] = [0, 0];
    stations.push([target, 0]);
    for (const [pos, fuel] of stations) {
        const dist = pos - pre;
        startFuel -= dist;
        while (startFuel < 0 && !pq.isEmpty()) {
            startFuel += pq.dequeue();
            ans++;
        }
        if (startFuel < 0) {
            return -1;
        }
        pq.enqueue(fuel);
        pre = pos;
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::BinaryHeap;

impl Solution {
    pub fn min_refuel_stops(target: i32, mut start_fuel: i32, mut stations: Vec<Vec<i32>>) -> i32 {
        let mut pq = BinaryHeap::new();
        let mut ans = 0;
        let mut pre = 0;

        stations.push(vec![target, 0]);

        for station in stations {
            let pos = station[0];
            let fuel = station[1];
            let dist = pos - pre;
            start_fuel -= dist;

            while start_fuel < 0 && !pq.is_empty() {
                start_fuel += pq.pop().unwrap();
                ans += 1;
            }

            if start_fuel < 0 {
                return -1;
            }

            pq.push(fuel);
            pre = pos;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
