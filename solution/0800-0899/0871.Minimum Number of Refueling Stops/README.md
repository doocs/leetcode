---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0871.Minimum%20Number%20of%20Refueling%20Stops/README.md
tags:
    - 贪心
    - 数组
    - 动态规划
    - 堆（优先队列）
---

<!-- problem:start -->

# [871. 最低加油次数](https://leetcode.cn/problems/minimum-number-of-refueling-stops)

[English Version](/solution/0800-0899/0871.Minimum%20Number%20of%20Refueling%20Stops/README_EN.md)

## 题目描述

<!-- description:start -->

<p>汽车从起点出发驶向目的地，该目的地位于出发位置东面 <code>target</code>&nbsp;英里处。</p>

<p>沿途有加油站，用数组&nbsp;<code>stations</code> 表示。其中 <code>stations[i] = [position<sub>i</sub>, fuel<sub>i</sub>]</code> 表示第 <code>i</code> 个加油站位于出发位置东面&nbsp;<code>position<sub>i</sub></code> 英里处，并且有&nbsp;<code>fuel<sub>i</sub></code>&nbsp;升汽油。</p>

<p>假设汽车油箱的容量是无限的，其中最初有&nbsp;<code>startFuel</code>&nbsp;升燃料。它每行驶 1 英里就会用掉 1 升汽油。当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。</p>

<p>为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 <code>-1</code> 。</p>

<p>注意：如果汽车到达加油站时剩余燃料为 <code>0</code>，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 <code>0</code>，仍然认为它已经到达目的地。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>target = 1, startFuel = 1, stations = []
<strong>输出：</strong>0
<strong>解释：</strong>可以在不加油的情况下到达目的地。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>target = 100, startFuel = 1, stations = [[10,100]]
<strong>输出：</strong>-1
<strong>解释：</strong>无法抵达目的地，甚至无法到达第一个加油站。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
<strong>输出：</strong>2
<strong>解释：</strong>
出发时有 10 升燃料。
开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。
然后，从 10 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料），
并将汽油从 10 升加到 50 升。然后开车抵达目的地。
沿途在两个加油站停靠，所以返回 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target, startFuel &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= stations.length &lt;= 500</code></li>
	<li><code>1 &lt;= position<sub>i</sub> &lt; position<sub>i+1</sub> &lt; target</code></li>
	<li><code>1 &lt;= fuel<sub>i</sub> &lt; 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 优先队列（大根堆）

我们可以利用优先队列（大根堆） $\textit{pq}$ 记录所有已经到达过的加油站的加油量，每次当油量不足时，贪心地取出最大加油量，即 $\textit{pq}$ 的堆顶元素，并累计加油次数 $\textit{ans}$。如果 $\textit{pq}$ 为空并且当前油量仍然不足，说明无法到达目的地，返回 $-1$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 表示加油站的数量。

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
    const pq = new MaxPriorityQueue();
    let [ans, pre] = [0, 0];
    stations.push([target, 0]);
    for (const [pos, fuel] of stations) {
        const dist = pos - pre;
        startFuel -= dist;
        while (startFuel < 0 && !pq.isEmpty()) {
            startFuel += pq.dequeue().element;
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
