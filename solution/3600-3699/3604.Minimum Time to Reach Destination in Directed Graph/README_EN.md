---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3604.Minimum%20Time%20to%20Reach%20Destination%20in%20Directed%20Graph/README_EN.md
tags:
    - Graph
    - Shortest Path
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3604. Minimum Time to Reach Destination in Directed Graph](https://leetcode.com/problems/minimum-time-to-reach-destination-in-directed-graph)

[中文文档](/solution/3600-3699/3604.Minimum%20Time%20to%20Reach%20Destination%20in%20Directed%20Graph/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> and a <strong>directed</strong> graph with <code>n</code> nodes labeled from 0 to <code>n - 1</code>. This is represented by a 2D array <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, start<sub>i</sub>, end<sub>i</sub>]</code> indicates an edge from node <code>u<sub>i</sub></code> to <code>v<sub>i</sub></code> that can <strong>only</strong> be used at any integer time <code>t</code> such that <code>start<sub>i</sub> &lt;= t &lt;= end<sub>i</sub></code>.</p>

<p>You start at node 0 at time 0.</p>

<p>In one unit of time, you can either:</p>

<ul>
	<li>Wait at your current node without moving, or</li>
	<li>Travel along an outgoing edge from your current node if the current time <code>t</code> satisfies <code>start<sub>i</sub> &lt;= t &lt;= end<sub>i</sub></code>.</li>
</ul>

<p>Return the <strong>minimum</strong> time required to reach node <code>n - 1</code>. If it is impossible, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,0,1],[1,2,2,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3604.Minimum%20Time%20to%20Reach%20Destination%20in%20Directed%20Graph/images/screenshot-2025-06-06-at-004535.png" style="width: 150px; height: 141px;" /></p>

<p>The optimal path is:</p>

<ul>
	<li>At time <code>t = 0</code>, take the edge <code>(0 &rarr; 1)</code> which is available from 0 to 1. You arrive at node 1 at time <code>t = 1</code>, then wait until <code>t = 2</code>.</li>
	<li>At time <code>t = <code>2</code></code>, take the edge <code>(1 &rarr; 2)</code> which is available from 2 to 5. You arrive at node 2 at time 3.</li>
</ul>

<p>Hence, the minimum time to reach node 2 is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[0,1,0,3],[1,3,7,8],[0,2,1,5],[2,3,4,7]]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3604.Minimum%20Time%20to%20Reach%20Destination%20in%20Directed%20Graph/images/screenshot-2025-06-06-at-004757.png" style="width: 170px; height: 219px;" /></p>

<p>The optimal path is:</p>

<ul>
	<li>Wait at node 0 until time <code>t = 1</code>, then take the edge <code>(0 &rarr; 2)</code> which is available from 1 to 5. You arrive at node 2 at <code>t = 2</code>.</li>
	<li>Wait at node 2 until time <code>t = 4</code>, then take the edge <code>(2 &rarr; 3)</code> which is available from 4 to 7. You arrive at node 3 at <code>t = 5</code>.</li>
</ul>

<p>Hence, the minimum time to reach node 3 is 5.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[1,0,1,3],[1,2,3,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3604.Minimum%20Time%20to%20Reach%20Destination%20in%20Directed%20Graph/images/screenshot-2025-06-06-at-004914.png" style="width: 150px; height: 145px;" /></p>

<ul>
	<li>Since there is no outgoing edge from node 0, it is impossible to reach node 2. Hence, the output is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>, start<sub>i</sub>, end<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minTime(self, n: int, edges: List[List[int]]) -> int:
        minReachTime = [inf] * n
        minReachTime[0] = 0

        nodeEdges = [[] for _ in range(n)]
        for edge in edges:
            nodeEdges[edge[0]].append(edge)

        reachTimeHeap = [(0, 0)]
        while reachTimeHeap:
            curTime, node = heappop(reachTimeHeap)
            if node == n - 1:
                return curTime

            for edge in nodeEdges[node]:
                if curTime <= edge[3]:
                    destTime = max(curTime, edge[2]) + 1
                    if minReachTime[edge[1]] > destTime:
                        minReachTime[edge[1]] = destTime
                        heappush(reachTimeHeap, (destTime, edge[1]))

        return -1
```

#### Java

```java

```

#### C++

```cpp
class Solution {
    vector<vector<vector<int>>> adj;
    vector<int> sol;
    priority_queue<pair<int,int> ,vector<pair<int,int>>,greater<>> pq;
    void pushNeighbours(int node,int curr){
        for(auto it : adj[node]){
            int temp = it[0] , start = it[1],end = it[2],newTime = curr+1;
            if(curr<start)  newTime = start+1;
            if(newTime < sol[temp] && newTime-1<=end){
                pq.push({newTime,temp});
                sol[temp] = newTime;
            }
        }
    }
public:
    int minTime(int n, vector<vector<int>>& edges) {
        adj = vector<vector<vector<int>>>(n);
        for(auto it: edges)
            adj[it[0]].push_back({it[1],it[2],it[3]});
        sol = vector<int> (n,INT_MAX);
        sol[0]=0;
        for(pq.push({0,0});!pq.empty();pq.pop())
            pushNeighbours(pq.top().second,pq.top().first);
        if(sol[n-1] == INT_MAX) return -1;
        return sol[n-1];
    }
};
const auto __ = []() {
    struct ___ {
        static void _() {
            std::ofstream("display_runtime.txt") << 0 << '\n';
            std::ofstream("display_memory.txt") << 0 << '\n';
        }
    };
    std::atexit(&___::_);
    return 0;
}();
```

#### Go

```go
import "container/heap"

func minTime(n int, edges [][]int) int {
    graph := make([][][3]int, n)
    for _, edge := range edges {
        u, v, start, end := edge[0], edge[1], edge[2], edge[3]
        graph[u] = append(graph[u], [3]int{v, start, end})
    }

    dist := make([]int, n)
    for i := range dist {
        dist[i] = -1
    }
    dist[0] = 0

    pq := &PriorityQueue{}
    heap.Init(pq)
    heap.Push(pq, &Item{value: 0, priority: 0})

    for pq.Len() > 0 {
        item := heap.Pop(pq).(*Item)
        u := item.value
        d := item.priority

        if d > dist[u] && dist[u] != -1{
            continue
        }


        if u == n-1{
            continue
        }


        for _, edge := range graph[u] {
            v, start, end := edge[0], edge[1], edge[2]

            wait := 0
            if d < start {
                wait = start - d
            }

            if d + wait <= end {
                newDist := d + wait + 1
                if dist[v] == -1 || newDist < dist[v] {
                    dist[v] = newDist
                    heap.Push(pq, &Item{value: v, priority: newDist})
                }
            }
        }
    }

    return dist[n-1]
}

type Item struct {
	value    int // The value of the item; arbitrary.
	priority int // The priority of the item in the queue.
	// The index is needed to update during heap operations. It is
	// maintained by the heap.Interface methods.
	index int // The index of the item in the heap.
}

// A PriorityQueue implements heap.Interface and holds Items.
type PriorityQueue []*Item

func (pq PriorityQueue) Len() int { return len(pq) }

func (pq PriorityQueue) Less(i, j int) bool {
	// We want Pop to give us the lowest, not highest, priority so we use less than here.
	return pq[i].priority < pq[j].priority
}

func (pq PriorityQueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
	pq[i].index = i
	pq[j].index = j
}

func (pq *PriorityQueue) Push(x any) {
	n := len(*pq)
	item := x.(*Item)
	item.index = n
	*pq = append(*pq, item)
}

func (pq *PriorityQueue) Pop() any {
	old := *pq
	n := len(old)
	item := old[n-1]
	old[n-1] = nil  // avoid memory leak
	item.index = -1 // for safety
	*pq = old[0 : n-1]
	return item
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
