---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1353.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended/README_EN.md
rating: 2015
source: Weekly Contest 176 Q3
tags:
    - Greedy
    - Array
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [1353. Maximum Number of Events That Can Be Attended](https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended)

[中文文档](/solution/1300-1399/1353.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Greedy + Priority Queue

We use a hash table $\textit{g}$ to record the start and end times of each event. The key is the start time of the event, and the value is a list containing the end times of all events that start at that time. Two variables, $\textit{l}$ and $\textit{r}$, are used to record the minimum start time and the maximum end time among all events.

For each time point $s$ from $\textit{l}$ to $\textit{r}$ in increasing order, we perform the following steps:

1. Remove from the priority queue all events whose end time is less than the current time $s$.
2. Add the end times of all events that start at the current time $s$ to the priority queue.
3. If the priority queue is not empty, take out the event with the earliest end time, increment the answer count, and remove this event from the priority queue.

In this way, we ensure that at each time point $s$, we always attend the event that ends the earliest, thus maximizing the number of events attended.

The time complexity is $O(M \times \log n)$, and the space complexity is $O(n)$, where $M$ is the maximum end time and $n$ is the number of events.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxEvents(self, events: List[List[int]]) -> int:
        g = defaultdict(list)
        l, r = inf, 0
        for s, e in events:
            g[s].append(e)
            l = min(l, s)
            r = max(r, e)
        pq = []
        ans = 0
        for s in range(l, r + 1):
            while pq and pq[0] < s:
                heappop(pq)
            for e in g[s]:
                heappush(pq, e)
            if pq:
                heappop(pq)
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int maxEvents(int[][] events) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        int l = Integer.MAX_VALUE, r = 0;
        for (int[] event : events) {
            int s = event[0], e = event[1];
            g.computeIfAbsent(s, k -> new ArrayList<>()).add(e);
            l = Math.min(l, s);
            r = Math.max(r, e);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ans = 0;
        for (int s = l; s <= r; s++) {
            while (!pq.isEmpty() && pq.peek() < s) {
                pq.poll();
            }
            for (int e : g.getOrDefault(s, List.of())) {
                pq.offer(e);
            }
            if (!pq.isEmpty()) {
                pq.poll();
                ans++;
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
    int maxEvents(vector<vector<int>>& events) {
        unordered_map<int, vector<int>> g;
        int l = INT_MAX, r = 0;
        for (auto& event : events) {
            int s = event[0], e = event[1];
            g[s].push_back(e);
            l = min(l, s);
            r = max(r, e);
        }
        priority_queue<int, vector<int>, greater<int>> pq;
        int ans = 0;
        for (int s = l; s <= r; ++s) {
            while (!pq.empty() && pq.top() < s) {
                pq.pop();
            }
            for (int e : g[s]) {
                pq.push(e);
            }
            if (!pq.empty()) {
                pq.pop();
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxEvents(events [][]int) (ans int) {
	g := map[int][]int{}
	l, r := math.MaxInt32, 0
	for _, event := range events {
		s, e := event[0], event[1]
		g[s] = append(g[s], e)
		l = min(l, s)
		r = max(r, e)
	}

	pq := &hp{}
	heap.Init(pq)
	for s := l; s <= r; s++ {
		for pq.Len() > 0 && pq.IntSlice[0] < s {
			heap.Pop(pq)
		}
		for _, e := range g[s] {
			heap.Push(pq, e)
		}
		if pq.Len() > 0 {
			heap.Pop(pq)
			ans++
		}
	}
	return
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v any) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	n := len(h.IntSlice)
	v := h.IntSlice[n-1]
	h.IntSlice = h.IntSlice[:n-1]
	return v
}
func (h *hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
```

#### TypeScript

```ts
function maxEvents(events: number[][]): number {
    const g: Map<number, number[]> = new Map();
    let l = Infinity,
        r = 0;
    for (const [s, e] of events) {
        if (!g.has(s)) g.set(s, []);
        g.get(s)!.push(e);
        l = Math.min(l, s);
        r = Math.max(r, e);
    }

    const pq = new MinPriorityQueue<number>();
    let ans = 0;
    for (let s = l; s <= r; s++) {
        while (!pq.isEmpty() && pq.front() < s) {
            pq.dequeue();
        }
        for (const e of g.get(s) || []) {
            pq.enqueue(e);
        }
        if (!pq.isEmpty()) {
            pq.dequeue();
            ans++;
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::{BinaryHeap, HashMap};
use std::cmp::Reverse;

impl Solution {
    pub fn max_events(events: Vec<Vec<i32>>) -> i32 {
        let mut g: HashMap<i32, Vec<i32>> = HashMap::new();
        let mut l = i32::MAX;
        let mut r = 0;

        for event in events {
            let s = event[0];
            let e = event[1];
            g.entry(s).or_default().push(e);
            l = l.min(s);
            r = r.max(e);
        }

        let mut pq = BinaryHeap::new();
        let mut ans = 0;

        for s in l..=r {
            while let Some(&Reverse(top)) = pq.peek() {
                if top < s {
                    pq.pop();
                } else {
                    break;
                }
            }
            if let Some(ends) = g.get(&s) {
                for &e in ends {
                    pq.push(Reverse(e));
                }
            }
            if pq.pop().is_some() {
                ans += 1;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
