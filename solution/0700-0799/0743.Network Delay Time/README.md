# [743. 网络延迟时间](https://leetcode-cn.com/problems/network-delay-time)

[English Version](/solution/0700-0799/0743.Network%20Delay%20Time/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code> 个网络节点，标记为 <code>1</code> 到 <code>n</code>。</p>

<p>给你一个列表 <code>times</code>，表示信号经过 <strong>有向</strong> 边的传递时间。 <code>times[i] = (u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>)</code>，其中 <code>u<sub>i</sub></code> 是源节点，<code>v<sub>i</sub></code> 是目标节点， <code>w<sub>i</sub></code> 是一个信号从源节点传递到目标节点的时间。</p>

<p>现在，从某个节点 <code>K</code> 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 <code>-1</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0743.Network%20Delay%20Time/images/931_example_1.png" style="height: 220px; width: 200px;" /></p>

<pre>
<strong>输入：</strong>times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>times = [[1,2,1]], n = 2, k = 1
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>times = [[1,2,1]], n = 2, k = 2
<strong>输出：</strong>-1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= k <= n <= 100</code></li>
	<li><code>1 <= times.length <= 6000</code></li>
	<li><code>times[i].length == 3</code></li>
	<li><code>1 <= u<sub>i</sub>, v<sub>i</sub> <= n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>0 <= w<sub>i</sub> <= 100</code></li>
	<li>所有 <code>(u<sub>i</sub>, v<sub>i</sub>)</code> 对都 <strong>互不相同</strong>（即，不含重复边）</li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
from collections import deque
class Solution:
    def networkDelayTime(self, times: List[List[int]], N: int, K: int) -> int:

        # Build N+1 because index is from 1-N
        travel_times = [[] for y in range(N+1)]

        # Build the array of travel times to reduce cost of searching later
        for time in times:
            origin, dest, time_travel = time
            travel_times[origin].append((dest, time_travel))

        # Store the shortest amount of time to reach i-th node
        visited_times = [float('inf') for x in range(N+1)]
        visited_times[0] = 0
        visited_times[K] = 0


        # Store next traverse in line
        visited_queue = deque()
        visited_queue.append(K)

        # BFS
        while visited_queue:
            cur_node = visited_queue.popleft()
            for time in travel_times[cur_node]:
                (dest, time_travel) = time
                if time_travel + visited_times[cur_node] < visited_times[dest]:
                    visited_times[dest] = time_travel + visited_times[cur_node]
                    visited_queue.append(dest)

        # Only return the max if all were traversed. Return -1 otherwise
        return max(visited_times) if max(visited_times) != float('inf') else -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int INF = 0x3f3f3f3f;

    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] t : times) {
            int from = t[0] - 1, to = t[1] - 1, time = t[2];
            graph.get(from).add(new Pair(to, time));
        }

        List<Integer> dis = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dis.add(INF);
        }
        dis.set(k - 1, 0);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(k - 1);
        while (!queue.isEmpty()) {
            int from = queue.poll();
            for (Pair e : graph.get(from)) {
                int to = e.first, time = e.second;
                if (time + dis.get(from) < dis.get(to)) {
                    dis.set(to, time + dis.get(from));
                    queue.offer(to);
                }
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int d : dis) {
            ans = Math.max(ans, d);
        }

        return ans == INF ? -1 : ans;
    }

    static class Pair {
        private int first;
        private int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
```

### **Go**

Dijkstra

```go
const Inf = 0x3f3f3f3f

type pair struct {
	first  int
	second int
}

var _ heap.Interface = (*pairs)(nil)

type pairs []pair

func (a pairs) Len() int { return len(a) }
func (a pairs) Less(i int, j int) bool {
	return a[i].first < a[j].first || a[i].first == a[j].first && a[i].second < a[j].second
}
func (a pairs) Swap(i int, j int)   { a[i], a[j] = a[j], a[i] }
func (a *pairs) Push(x interface{}) { *a = append(*a, x.(pair)) }
func (a *pairs) Pop() interface{}   { l := len(*a); t := (*a)[l-1]; *a = (*a)[:l-1]; return t }

func networkDelayTime(times [][]int, n int, k int) int {
	graph := make([]pairs, n)
	for _, time := range times {
		from, to, time := time[0]-1, time[1]-1, time[2]
		graph[from] = append(graph[from], pair{to, time})
	}

	dis := make([]int, n)
	for i := range dis {
		dis[i] = Inf
	}
	dis[k-1] = 0

	vis := make([]bool, n)
	h := make(pairs, 0)
	heap.Push(&h, pair{0, k - 1})
	for len(h) > 0 {
		from := heap.Pop(&h).(pair).second
		if vis[from] {
			continue
		}
		vis[from] = true
		for _, e := range graph[from] {
			to, d := e.first, dis[from]+e.second
			if d < dis[to] {
				dis[to] = d
				heap.Push(&h, pair{d, to})
			}
		}
	}

	ans := math.MinInt32
	for _, d := range dis {
		ans = max(ans, d)
	}
	if ans == Inf {
		return -1
	}
	return ans
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
```

### **...**

```

```

<!-- tabs:end -->
