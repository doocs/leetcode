---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1942.The%20Number%20of%20the%20Smallest%20Unoccupied%20Chair/README_EN.md
rating: 1695
source: Biweekly Contest 57 Q2
tags:
    - Array
    - Hash Table
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [1942. The Number of the Smallest Unoccupied Chair](https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair)

[中文文档](/solution/1900-1999/1942.The%20Number%20of%20the%20Smallest%20Unoccupied%20Chair/README.md)

## Description

<!-- description:start -->

<p>There is a party where <code>n</code> friends numbered from <code>0</code> to <code>n - 1</code> are attending. There is an <strong>infinite</strong> number of chairs in this party that are numbered from <code>0</code> to <code>infinity</code>. When a friend arrives at the party, they sit on the unoccupied chair with the <strong>smallest number</strong>.</p>

<ul>
	<li>For example, if chairs <code>0</code>, <code>1</code>, and <code>5</code> are occupied when a friend comes, they will sit on chair number <code>2</code>.</li>
</ul>

<p>When a friend leaves the party, their chair becomes unoccupied at the moment they leave. If another friend arrives at that same moment, they can sit in that chair.</p>

<p>You are given a <strong>0-indexed</strong> 2D integer array <code>times</code> where <code>times[i] = [arrival<sub>i</sub>, leaving<sub>i</sub>]</code>, indicating the arrival and leaving times of the <code>i<sup>th</sup></code> friend respectively, and an integer <code>targetFriend</code>. All arrival times are <strong>distinct</strong>.</p>

<p>Return<em> the <strong>chair number</strong> that the friend numbered </em><code>targetFriend</code><em> will sit on</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> times = [[1,4],[2,3],[4,6]], targetFriend = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
- Friend 0 arrives at time 1 and sits on chair 0.
- Friend 1 arrives at time 2 and sits on chair 1.
- Friend 1 leaves at time 3 and chair 1 becomes empty.
- Friend 0 leaves at time 4 and chair 0 becomes empty.
- Friend 2 arrives at time 4 and sits on chair 0.
Since friend 1 sat on chair 1, we return 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> times = [[3,10],[1,5],[2,6]], targetFriend = 0
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
- Friend 1 arrives at time 1 and sits on chair 0.
- Friend 2 arrives at time 2 and sits on chair 1.
- Friend 0 arrives at time 3 and sits on chair 2.
- Friend 1 leaves at time 5 and chair 0 becomes empty.
- Friend 2 leaves at time 6 and chair 1 becomes empty.
- Friend 0 leaves at time 10 and chair 2 becomes empty.
Since friend 0 sat on chair 2, we return 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == times.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>times[i].length == 2</code></li>
	<li><code>1 &lt;= arrival<sub>i</sub> &lt; leaving<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= targetFriend &lt;= n - 1</code></li>
	<li>Each <code>arrival<sub>i</sub></code> time is <strong>distinct</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Priority Queue (Min-Heap)

First, we create a tuple for each friend consisting of their arrival time, leaving time, and index, then sort these tuples by arrival time.

We use a min-heap $\textit{idle}$ to store the currently available chair numbers. Initially, we add $0, 1, \ldots, n-1$ to $\textit{idle}$. We also use a min-heap $\textit{busy}$ to store tuples $(\textit{leaving}, \textit{chair})$, where $\textit{leaving}$ represents the leaving time and $\textit{chair}$ represents the chair number.

We iterate through each friend's arrival time, leaving time, and index. For each friend, we first remove all friends from $\textit{busy}$ whose leaving time is less than or equal to the current friend's arrival time, and add their chair numbers back to $\textit{idle}$. Then we pop a chair number from $\textit{idle}$, assign it to the current friend, and add $(\textit{leaving}, \textit{chair})$ to $\textit{busy}$. If the current friend's index is equal to $\textit{targetFriend}$, we return the assigned chair number.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the number of friends.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestChair(self, times: List[List[int]], targetFriend: int) -> int:
        n = len(times)
        for i in range(n):
            times[i].append(i)
        times.sort()
        idle = list(range(n))
        heapify(idle)
        busy = []
        for arrival, leaving, i in times:
            while busy and busy[0][0] <= arrival:
                heappush(idle, heappop(busy)[1])
            j = heappop(idle)
            if i == targetFriend:
                return j
            heappush(busy, (leaving, j))
```

#### Java

```java
class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length;
        PriorityQueue<Integer> idle = new PriorityQueue<>();
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < n; ++i) {
            times[i] = new int[] {times[i][0], times[i][1], i};
            idle.offer(i);
        }
        Arrays.sort(times, Comparator.comparingInt(a -> a[0]));
        for (var e : times) {
            int arrival = e[0], leaving = e[1], i = e[2];
            while (!busy.isEmpty() && busy.peek()[0] <= arrival) {
                idle.offer(busy.poll()[1]);
            }
            int j = idle.poll();
            if (i == targetFriend) {
                return j;
            }
            busy.offer(new int[] {leaving, j});
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestChair(vector<vector<int>>& times, int targetFriend) {
        using pii = pair<int, int>;
        priority_queue<pii, vector<pii>, greater<pii>> busy;
        priority_queue<int, vector<int>, greater<int>> idle;
        int n = times.size();
        for (int i = 0; i < n; ++i) {
            times[i].push_back(i);
            idle.push(i);
        }
        ranges::sort(times);
        for (const auto& e : times) {
            int arrival = e[0], leaving = e[1], i = e[2];
            while (!busy.empty() && busy.top().first <= arrival) {
                idle.push(busy.top().second);
                busy.pop();
            }
            int j = idle.top();
            if (i == targetFriend) {
                return j;
            }
            idle.pop();
            busy.emplace(leaving, j);
        }
        return -1;
    }
};
```

#### Go

```go
func smallestChair(times [][]int, targetFriend int) int {
	idle := hp{}
	busy := hp2{}
	for i := range times {
		times[i] = append(times[i], i)
		heap.Push(&idle, i)
	}
	sort.Slice(times, func(i, j int) bool { return times[i][0] < times[j][0] })
	for _, e := range times {
		arrival, leaving, i := e[0], e[1], e[2]
		for len(busy) > 0 && busy[0].t <= arrival {
			heap.Push(&idle, heap.Pop(&busy).(pair).i)
		}
		j := heap.Pop(&idle).(int)
		if i == targetFriend {
			return j
		}
		heap.Push(&busy, pair{leaving, j})
	}
	return -1
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}

type pair struct{ t, i int }
type hp2 []pair

func (h hp2) Len() int           { return len(h) }
func (h hp2) Less(i, j int) bool { return h[i].t < h[j].t || (h[i].t == h[j].t && h[i].i < h[j].i) }
func (h hp2) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp2) Push(v any)        { *h = append(*h, v.(pair)) }
func (h *hp2) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

#### TypeScript

```ts
function smallestChair(times: number[][], targetFriend: number): number {
    const n = times.length;
    const idle = new MinPriorityQueue();
    const busy = new MinPriorityQueue({ priority: v => v[0] });
    for (let i = 0; i < n; ++i) {
        times[i].push(i);
        idle.enqueue(i);
    }
    times.sort((a, b) => a[0] - b[0]);
    for (const [arrival, leaving, i] of times) {
        while (busy.size() > 0 && busy.front().element[0] <= arrival) {
            idle.enqueue(busy.dequeue().element[1]);
        }
        const j = idle.dequeue().element;
        if (i === targetFriend) {
            return j;
        }
        busy.enqueue([leaving, j]);
    }
    return -1;
}
```

#### JavaScript

```js
/**
 * @param {number[][]} times
 * @param {number} targetFriend
 * @return {number}
 */
var smallestChair = function (times, targetFriend) {
    const n = times.length;
    const idle = new MinPriorityQueue();
    const busy = new MinPriorityQueue({ priority: v => v[0] });
    for (let i = 0; i < n; ++i) {
        times[i].push(i);
        idle.enqueue(i);
    }
    times.sort((a, b) => a[0] - b[0]);
    for (const [arrival, leaving, i] of times) {
        while (busy.size() > 0 && busy.front().element[0] <= arrival) {
            idle.enqueue(busy.dequeue().element[1]);
        }
        const j = idle.dequeue().element;
        if (i === targetFriend) {
            return j;
        }
        busy.enqueue([leaving, j]);
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
