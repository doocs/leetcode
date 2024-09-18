---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0815.Bus%20Routes/README_EN.md
tags:
    - Breadth-First Search
    - Array
    - Hash Table
---

<!-- problem:start -->

# [815. Bus Routes](https://leetcode.com/problems/bus-routes)

[中文文档](/solution/0800-0899/0815.Bus%20Routes/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>routes</code> representing bus routes where <code>routes[i]</code> is a bus route that the <code>i<sup>th</sup></code> bus repeats forever.</p>

<ul>
	<li>For example, if <code>routes[0] = [1, 5, 7]</code>, this means that the <code>0<sup>th</sup></code> bus travels in the sequence <code>1 -&gt; 5 -&gt; 7 -&gt; 1 -&gt; 5 -&gt; 7 -&gt; 1 -&gt; ...</code> forever.</li>
</ul>

<p>You will start at the bus stop <code>source</code> (You are not on any bus initially), and you want to go to the bus stop <code>target</code>. You can travel between bus stops by buses only.</p>

<p>Return <em>the least number of buses you must take to travel from </em><code>source</code><em> to </em><code>target</code>. Return <code>-1</code> if it is not possible.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> routes = [[1,2,7],[3,6,7]], source = 1, target = 6
<strong>Output:</strong> 2
<strong>Explanation:</strong> The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= routes.length &lt;= 500</code>.</li>
	<li><code>1 &lt;= routes[i].length &lt;= 10<sup>5</sup></code></li>
	<li>All the values of <code>routes[i]</code> are <strong>unique</strong>.</li>
	<li><code>sum(routes[i].length) &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= routes[i][j] &lt; 10<sup>6</sup></code></li>
	<li><code>0 &lt;= source, target &lt; 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: BFS

First, we check if $\textit{source}$ and $\textit{target}$ are the same. If they are, we directly return $0$.

Next, we use a hash table $\textit{g}$ to build a mapping from stops to bus routes. For each bus route, we traverse all the stops it passes through and map each stop to that bus route, i.e., $\textit{g}[\textit{stop}]$ represents all bus routes passing through stop $\textit{stop}$.

Then, we check if $\textit{source}$ and $\textit{target}$ are in the stop mapping. If they are not, we return $-1$.

We use a queue $\textit{q}$ to perform a breadth-first search (BFS). Each element in the queue is a tuple $(\textit{stop}, \textit{busCount})$, representing the current stop $\textit{stop}$ and the number of buses taken to reach the current stop $\textit{busCount}$.

We initialize a set $\textit{visBus}$ to record the bus routes that have been visited and a set $\textit{visStop}$ to record the stops that have been visited. Then, we add $\textit{source}$ to $\textit{visStop}$ and $(\textit{source}, 0)$ to the queue $\textit{q}$.

Next, we start the BFS. While the queue $\textit{q}$ is not empty, we take out the first element from the queue, which is the current stop $\textit{stop}$ and the number of buses taken to reach the current stop $\textit{busCount}$.

If the current stop $\textit{stop}$ is the target stop $\textit{target}$, we return the number of buses taken to reach the target stop $\textit{busCount}$.

Otherwise, we traverse all bus routes passing through the current stop. For each bus route, we traverse all stops on that route. If a stop $\textit{nextStop}$ has not been visited, we add it to $\textit{visStop}$ and add $(\textit{nextStop}, \textit{busCount} + 1)$ to the queue $\textit{q}$.

Finally, if we cannot reach the target stop, we return $-1$.

The time complexity is $O(L)$, and the space complexity is $O(L)$, where $L$ is the total number of stops on all bus routes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numBusesToDestination(
        self, routes: List[List[int]], source: int, target: int
    ) -> int:
        if source == target:
            return 0
        g = defaultdict(list)
        for i, route in enumerate(routes):
            for stop in route:
                g[stop].append(i)
        if source not in g or target not in g:
            return -1
        q = [(source, 0)]
        vis_bus = set()
        vis_stop = {source}
        for stop, bus_count in q:
            if stop == target:
                return bus_count
            for bus in g[stop]:
                if bus not in vis_bus:
                    vis_bus.add(bus)
                    for next_stop in routes[bus]:
                        if next_stop not in vis_stop:
                            vis_stop.add(next_stop)
                            q.append((next_stop, bus_count + 1))
        return -1
```

#### Java

```java
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                g.computeIfAbsent(stop, k -> new ArrayList<>()).add(i);
            }
        }

        if (!g.containsKey(source) || !g.containsKey(target)) {
            return -1;
        }

        Deque<int[]> q = new ArrayDeque<>();
        Set<Integer> visBus = new HashSet<>();
        Set<Integer> visStop = new HashSet<>();
        q.offer(new int[] {source, 0});
        visStop.add(source);

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int stop = current[0], busCount = current[1];

            if (stop == target) {
                return busCount;
            }
            for (int bus : g.get(stop)) {
                if (visBus.add(bus)) {
                    for (int nextStop : routes[bus]) {
                        if (visStop.add(nextStop)) {
                            q.offer(new int[] {nextStop, busCount + 1});
                        }
                    }
                }
            }
        }

        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numBusesToDestination(vector<vector<int>>& routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        unordered_map<int, vector<int>> g;
        for (int i = 0; i < routes.size(); i++) {
            for (int stop : routes[i]) {
                g[stop].push_back(i);
            }
        }

        if (!g.contains(source) || !g.contains(target)) {
            return -1;
        }

        queue<pair<int, int>> q;
        unordered_set<int> visBus;
        unordered_set<int> visStop;
        q.push({source, 0});
        visStop.insert(source);

        while (!q.empty()) {
            auto [stop, busCount] = q.front();
            q.pop();

            if (stop == target) {
                return busCount;
            }

            for (int bus : g[stop]) {
                if (!visBus.contains(bus)) {
                    for (int nextStop : routes[bus]) {
                        if (!visStop.contains(nextStop)) {
                            visBus.insert(bus);
                            visStop.insert(nextStop);
                            q.push({nextStop, busCount + 1});
                        }
                    }
                }
            }
        }

        return -1;
    }
};
```

#### Go

```go
func numBusesToDestination(routes [][]int, source int, target int) int {
	if source == target {
		return 0
	}

	g := make(map[int][]int)
	for i, route := range routes {
		for _, stop := range route {
			g[stop] = append(g[stop], i)
		}
	}

	if g[source] == nil || g[target] == nil {
		return -1
	}

	q := list.New()
	q.PushBack([2]int{source, 0})
	visBus := make(map[int]bool)
	visStop := make(map[int]bool)
	visStop[source] = true

	for q.Len() > 0 {
		front := q.Front()
		q.Remove(front)
		stop, busCount := front.Value.([2]int)[0], front.Value.([2]int)[1]

		if stop == target {
			return busCount
		}

		for _, bus := range g[stop] {
			if !visBus[bus] {
				visBus[bus] = true
				for _, nextStop := range routes[bus] {
					if !visStop[nextStop] {
						visStop[nextStop] = true
						q.PushBack([2]int{nextStop, busCount + 1})
					}
				}
			}
		}
	}

	return -1
}
```

#### TypeScript

```ts
function numBusesToDestination(routes: number[][], source: number, target: number): number {
    if (source === target) {
        return 0;
    }

    const g: Map<number, number[]> = new Map();
    for (let i = 0; i < routes.length; i++) {
        for (const stop of routes[i]) {
            if (!g.has(stop)) {
                g.set(stop, []);
            }
            g.get(stop)!.push(i);
        }
    }

    if (!g.has(source) || !g.has(target)) {
        return -1;
    }

    const q: [number, number][] = [[source, 0]];
    const visBus: Set<number> = new Set();
    const visStop: Set<number> = new Set([source]);

    for (const [stop, busCount] of q) {
        if (stop === target) {
            return busCount;
        }
        for (const bus of g.get(stop)!) {
            if (!visBus.has(bus)) {
                visBus.add(bus);
                for (const nextStop of routes[bus]) {
                    if (!visStop.has(nextStop)) {
                        visStop.add(nextStop);
                        q.push([nextStop, busCount + 1]);
                    }
                }
            }
        }
    }
    return -1;
}
```

#### C#

```cs
public class Solution {
    public int NumBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        // Use Dictionary to map stops to bus routes
        var g = new Dictionary<int, List<int>>();
        for (int i = 0; i < routes.Length; i++) {
            foreach (int stop in routes[i]) {
                if (!g.ContainsKey(stop)) {
                    g[stop] = new List<int>();
                }
                g[stop].Add(i);
            }
        }

        // If source or target is not in the mapping, return -1
        if (!g.ContainsKey(source) || !g.ContainsKey(target)) {
            return -1;
        }

        // Initialize queue and visited sets
        var q = new Queue<int[]>();
        var visBus = new HashSet<int>();
        var visStop = new HashSet<int>();
        q.Enqueue(new int[] { source, 0 });
        visStop.Add(source);

        // Begin BFS
        while (q.Count > 0) {
            var current = q.Dequeue();
            int stop = current[0], busCount = current[1];

            // If the current stop is the target stop, return the bus count
            if (stop == target) {
                return busCount;
            }

            // Traverse all bus routes passing through the current stop
            foreach (int bus in g[stop]) {
                if (visBus.Add(bus)) {
                    // Traverse all stops on this bus route
                    foreach (int nextStop in routes[bus]) {
                        if (visStop.Add(nextStop)) {
                            q.Enqueue(new int[] { nextStop, busCount + 1 });
                        }
                    }
                }
            }
        }

        return -1; // If unable to reach the target stop, return -1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
