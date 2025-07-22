---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0815.Bus%20Routes/README.md
tags:
    - 广度优先搜索
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [815. 公交路线](https://leetcode.cn/problems/bus-routes)

[English Version](/solution/0800-0899/0815.Bus%20Routes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组 <code>routes</code> ，表示一系列公交线路，其中每个 <code>routes[i]</code> 表示一条公交线路，第 <code>i</code> 辆公交车将会在上面循环行驶。</p>

<ul>
	<li>例如，路线 <code>routes[0] = [1, 5, 7]</code> 表示第 <code>0</code> 辆公交车会一直按序列 <code>1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ...</code> 这样的车站路线行驶。</li>
</ul>

<p>现在从 <code>source</code> 车站出发（初始时不在公交车上），要前往 <code>target</code> 车站。 期间仅可乘坐公交车。</p>

<p>求出 <strong>最少乘坐的公交车数量</strong> 。如果不可能到达终点车站，返回 <code>-1</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>routes = [[1,2,7],[3,6,7]], source = 1, target = 6
<strong>输出：</strong>2
<strong>解释：</strong>最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
<strong>输出：</strong>-1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= routes.length <= 500</code>.</li>
	<li><code>1 <= routes[i].length <= 10<sup>5</sup></code></li>
	<li><code>routes[i]</code> 中的所有值 <strong>互不相同</strong></li>
	<li><code>sum(routes[i].length) <= 10<sup>5</sup></code></li>
	<li><code>0 <= routes[i][j] < 10<sup>6</sup></code></li>
	<li><code>0 <= source, target < 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

我们首先判断 $\textit{source}$ 和 $\textit{target}$ 是否相同，如果相同则直接返回 $0$。

然后我们使用一个哈希表 $\textit{g}$ 来构建站点到公交线路的映射。对于每一条公交线路，我们遍历其经过的所有站点，将每个站点映射到该公交线路，即 $\textit{g}[\textit{stop}]$ 为经过站点 $\textit{stop}$ 的所有公交线路。

接着我们判断 $\textit{source}$ 和 $\textit{target}$ 是否在站点映射中，如果不在则返回 $-1$。

我们使用一个队列 $\textit{q}$ 来进行广度优先搜索，队列中的每个元素为一个二元组 $(\textit{stop}, \textit{busCount})$，表示当前站点 $\textit{stop}$ 和到达当前站点所需的公交次数 $\textit{busCount}$。

我们初始化一个集合 $\textit{visBus}$ 来记录已经访问过的公交线路，一个集合 $\textit{visStop}$ 来记录已经访问过的站点。然后我们将 $\textit{source}$ 加入到 $\textit{visStop}$ 中，并将 $(\textit{source}, 0)$ 加入到队列 $\textit{q}$ 中。

接下来我们开始广度优先搜索，当队列 $\textit{q}$ 不为空时，我们取出队列的第一个元素，即当前站点 $\textit{stop}$ 和到达当前站点所需的公交次数 $\textit{busCount}$。

如果当前站点 $\textit{stop}$ 是目标站点 $\textit{target}$，我们返回到达目标站点所需的公交次数 $\textit{busCount}$。

否则，我们遍历经过当前站点的所有公交线路，对于每一条公交线路，我们遍历该线路上的所有站点，如果某个站点 $\textit{nextStop}$ 没有被访问过，则将其加入到 $\textit{visStop}$ 中，并将 $(\textit{nextStop}, \textit{busCount} + 1)$ 加入到队列 $\textit{q}$ 中。

最后，如果无法到达目标站点，则返回 $-1$。

时间复杂度 $O(L)$，空间复杂度 $O(L)$，其中 $L$ 为所有公交线路上的站点总数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numBusesToDestination(
        self, routes: List[List[int]], source: int, target: int
    ) -> int:
        if source == target:
            return 0
        # 使用 defaultdict 构建站点到公交线路的映射
        g = defaultdict(list)
        for i, route in enumerate(routes):
            for stop in route:
                g[stop].append(i)

        # 如果 source 或 target 不在站点映射中，返回 -1
        if source not in g or target not in g:
            return -1

        # 初始化队列和访问集合
        q = [(source, 0)]
        vis_bus = set()
        vis_stop = {source}

        # 开始广度优先搜索
        for stop, bus_count in q:
            # 如果当前站点是目标站点，返回所需的公交次数
            if stop == target:
                return bus_count

            # 遍历经过当前站点的所有公交线路
            for bus in g[stop]:
                if bus not in vis_bus:
                    vis_bus.add(bus)

                    # 遍历该线路上的所有站点
                    for next_stop in routes[bus]:
                        if next_stop not in vis_stop:
                            vis_stop.add(next_stop)
                            q.append((next_stop, bus_count + 1))
        return -1 # 如果无法到达目标站点，返回 -1
```

#### Java

```java
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        // 使用 HashMap 构建站点到公交线路的映射
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                g.computeIfAbsent(stop, k -> new ArrayList<>()).add(i);
            }
        }

        // 如果 source 或 target 不在站点映射中，返回 -1
        if (!g.containsKey(source) || !g.containsKey(target)) {
            return -1;
        }

        // 初始化队列和访问集合
        Deque<int[]> q = new ArrayDeque<>();
        Set<Integer> visBus = new HashSet<>();
        Set<Integer> visStop = new HashSet<>();
        q.offer(new int[] {source, 0});
        visStop.add(source);

        // 开始广度优先搜索
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int stop = current[0], busCount = current[1];

            // 如果当前站点是目标站点，返回所需的公交次数
            if (stop == target) {
                return busCount;
            }

            // 遍历经过当前站点的所有公交线路
            for (int bus : g.get(stop)) {
                if (visBus.add(bus)) {
                    // 遍历该线路上的所有站点
                    for (int nextStop : routes[bus]) {
                        if (visStop.add(nextStop)) {
                            q.offer(new int[] {nextStop, busCount + 1});
                        }
                    }
                }
            }
        }

        return -1; // 如果无法到达目标站点，返回 -1
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

        // 使用 unordered_map 构建站点到公交线路的映射
        unordered_map<int, vector<int>> g;
        for (int i = 0; i < routes.size(); i++) {
            for (int stop : routes[i]) {
                g[stop].push_back(i);
            }
        }

        // 如果 source 或 target 不在站点映射中，返回 -1
        if (!g.contains(source) || !g.contains(target)) {
            return -1;
        }

        // 初始化队列和访问集合
        queue<pair<int, int>> q;
        unordered_set<int> visBus;
        unordered_set<int> visStop;
        q.push({source, 0});
        visStop.insert(source);

        // 开始广度优先搜索
        while (!q.empty()) {
            auto [stop, busCount] = q.front();
            q.pop();

            // 如果当前站点是目标站点，返回所需的公交次数
            if (stop == target) {
                return busCount;
            }

            // 遍历经过当前站点的所有公交线路
            for (int bus : g[stop]) {
                if (!visBus.contains(bus)) {
                    visBus.insert(bus);
                    // 遍历该线路上的所有站点
                    for (int nextStop : routes[bus]) {
                        if (!visStop.contains(nextStop)) {
                            visStop.insert(nextStop);
                            q.push({nextStop, busCount + 1});
                        }
                    }
                }
            }
        }

        return -1; // 如果无法到达目标站点，返回 -1
    }
};
```

#### Go

```go
func numBusesToDestination(routes [][]int, source int, target int) int {
	if source == target {
		return 0
	}

	// 使用 map 构建站点到公交线路的映射
	g := make(map[int][]int)
	for i, route := range routes {
		for _, stop := range route {
			g[stop] = append(g[stop], i)
		}
	}

	// 如果 source 或 target 不在站点映射中，返回 -1
	if g[source] == nil || g[target] == nil {
		return -1
	}

	// 初始化队列和访问集合
	q := list.New()
	q.PushBack([2]int{source, 0})
	visBus := make(map[int]bool)
	visStop := make(map[int]bool)
	visStop[source] = true

	// 开始广度优先搜索
	for q.Len() > 0 {
		front := q.Front()
		q.Remove(front)
		stop, busCount := front.Value.([2]int)[0], front.Value.([2]int)[1]

		// 如果当前站点是目标站点，返回所需的公交次数
		if stop == target {
			return busCount
		}

		// 遍历经过当前站点的所有公交线路
		for _, bus := range g[stop] {
			if !visBus[bus] {
				visBus[bus] = true
				// 遍历该线路上的所有站点
				for _, nextStop := range routes[bus] {
					if !visStop[nextStop] {
						visStop[nextStop] = true
						q.PushBack([2]int{nextStop, busCount + 1})
					}
				}
			}
		}
	}

	return -1 // 如果无法到达目标站点，返回
}
```

#### TypeScript

```ts
function numBusesToDestination(routes: number[][], source: number, target: number): number {
    if (source === target) {
        return 0;
    }

    // 使用 Map 构建站点到公交线路的映射
    const g: Map<number, number[]> = new Map();
    for (let i = 0; i < routes.length; i++) {
        for (const stop of routes[i]) {
            if (!g.has(stop)) {
                g.set(stop, []);
            }
            g.get(stop)!.push(i);
        }
    }

    // 如果 source 或 target 不在站点映射中，返回 -1
    if (!g.has(source) || !g.has(target)) {
        return -1;
    }

    // 初始化队列和访问集合
    const q: [number, number][] = [[source, 0]];
    const visBus: Set<number> = new Set();
    const visStop: Set<number> = new Set([source]);

    // 开始广度优先搜索
    for (const [stop, busCount] of q) {
        // 如果当前站点是目标站点，返回所需的公交次数
        if (stop === target) {
            return busCount;
        }

        // 遍历经过当前站点的所有公交线路
        for (const bus of g.get(stop)!) {
            if (!visBus.has(bus)) {
                visBus.add(bus);
                // 遍历该线路上的所有站点
                for (const nextStop of routes[bus]) {
                    if (!visStop.has(nextStop)) {
                        visStop.add(nextStop);
                        q.push([nextStop, busCount + 1]);
                    }
                }
            }
        }
    }

    return -1; // 如果无法到达目标站点，返回 -1
}
```

#### C#

```cs
public class Solution {
    public int NumBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0; // 如果起点和终点相同，直接返回 0
        }

        // 使用 Dictionary 构建站点到公交线路的映射
        var g = new Dictionary<int, List<int>>();
        for (int i = 0; i < routes.Length; i++) {
            foreach (int stop in routes[i]) {
                if (!g.ContainsKey(stop)) {
                    g[stop] = new List<int>();
                }
                g[stop].Add(i); // 将公交线路编号添加到该站点的列表中
            }
        }

        // 如果 source 或 target 不在站点映射中，返回 -1
        if (!g.ContainsKey(source) || !g.ContainsKey(target)) {
            return -1;
        }

        // 初始化队列和访问集合
        var q = new Queue<int[]>();
        var visBus = new HashSet<int>(); // 记录访问过的公交线路
        var visStop = new HashSet<int>(); // 记录访问过的站点
        q.Enqueue(new int[] { source, 0 }); // 将起点加入队列，公交次数初始化为 0
        visStop.Add(source); // 将起点标记为已访问

        // 开始广度优先搜索
        while (q.Count > 0) {
            var current = q.Dequeue(); // 从队列中取出当前站点
            int stop = current[0], busCount = current[1];

            // 如果当前站点是目标站点，返回所需的公交次数
            if (stop == target) {
                return busCount;
            }

            // 遍历经过当前站点的所有公交线路
            foreach (int bus in g[stop]) {
                if (visBus.Add(bus)) { // 如果公交线路没有被访问过
                    // 遍历该线路上的所有站点
                    foreach (int nextStop in routes[bus]) {
                        if (visStop.Add(nextStop)) { // 如果该站点没有被访问过
                            q.Enqueue(new int[] { nextStop, busCount + 1 }); // 将新站点加入队列，公交次数加 1
                        }
                    }
                }
            }
        }

        return -1; // 如果无法到达目标站点，返回 -1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
