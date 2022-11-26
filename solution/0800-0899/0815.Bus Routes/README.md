# [815. 公交路线](https://leetcode.cn/problems/bus-routes)

[English Version](/solution/0800-0899/0815.Bus%20Routes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：建图 + BFS**

对于本题，我们可以将公交线路看成图中的节点，对于任意两条公交线路，如果它们有公共的公交站点，那么这两个公交线路之间就有一条边。

我们用 $s[i]$ 表示公交线路 $i$ 上的所有公交站点，用哈希表 $d$ 存储每个公交站对应的所有公交线路。

接下来我们开始建图。遍历哈希表 $d$ 中每个公交站对应的公交线路，同个公交站的任意两条公交线路之间都有一条边，因此我们可以将这些公交线路看成图中的节点，将这些节点之间的边加入图 $g$ 中。

接下来，我们可以通过 BFS 求出从 $source$ 到 $target$ 的最短路径，即为最少乘坐的公交车数量。

时间复杂度 $O(n\times m + n^2)$。其中 $n$, $m$ 分别表示公交线路的数量和公交站的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numBusesToDestination(self, routes: List[List[int]], source: int, target: int) -> int:
        if source == target:
            return 0

        # 一条公交线路有哪些公交站
        s = [set(r) for r in routes]

        # 一个公交站在哪些公交线路有
        d = defaultdict(list)
        for i, r in enumerate(routes):
            for v in r:
                d[v].append(i)

        g = defaultdict(list)
        for ids in d.values():
            m = len(ids)
            for i in range(m):
                for j in range(i + 1, m):
                    a, b = ids[i], ids[j]
                    g[a].append(b)
                    g[b].append(a)
        q = deque(d[source])
        ans = 1
        vis = set(d[source])
        while q:
            for _ in range(len(q)):
                i = q.popleft()
                if target in s[i]:
                    return ans
                for j in g[i]:
                    if j not in vis:
                        vis.add(j)
                        q.append(j)
            ans += 1
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int n = routes.length;
        Set<Integer>[] s = new Set[n];
        List<Integer>[] g = new List[n];
        Arrays.setAll(s, k -> new HashSet<>());
        Arrays.setAll(g, k -> new ArrayList<>());
        Map<Integer, List<Integer>> d = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            for (int v : routes[i]) {
                s[i].add(v);
                d.computeIfAbsent(v, k -> new ArrayList<>()).add(i);
            }
        }
        for (var ids : d.values()) {
            int m = ids.size();
            for (int i = 0; i < m; ++i) {
                for (int j = i + 1; j < m; ++j) {
                    int a = ids.get(i), b = ids.get(j);
                    g[a].add(b);
                    g[b].add(a);
                }
            }
        }
        Deque<Integer> q = new ArrayDeque<>();
        Set<Integer> vis = new HashSet<>();
        int ans = 1;
        for (int v : d.get(source)) {
            q.offer(v);
            vis.add(v);
        }
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                int i = q.pollFirst();
                if (s[i].contains(target)) {
                    return ans;
                }
                for (int j : g[i]) {
                    if (!vis.contains(j)) {
                        vis.add(j);
                        q.offer(j);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numBusesToDestination(vector<vector<int>>& routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int n = routes.size();
        vector<unordered_set<int>> s(n);
        vector<vector<int>> g(n);
        unordered_map<int, vector<int>> d;
        for (int i = 0; i < n; ++i) {
            for (int v : routes[i]) {
                s[i].insert(v);
                d[v].push_back(i);
            }
        }
        for (auto& [_, ids] : d) {
            int m = ids.size();
            for (int i = 0; i < m; ++i) {
                for (int j = i + 1; j < m; ++j) {
                    int a = ids[i], b = ids[j];
                    g[a].push_back(b);
                    g[b].push_back(a);
                }
            }
        }
        queue<int> q;
        unordered_set<int> vis;
        int ans = 1;
        for (int v : d[source]) {
            q.push(v);
            vis.insert(v);
        }
        while (!q.empty()) {
            for (int k = q.size(); k; --k) {
                int i = q.front();
                q.pop();
                if (s[i].count(target)) {
                    return ans;
                }
                for (int j : g[i]) {
                    if (!vis.count(j)) {
                        vis.insert(j);
                        q.push(j);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
};
```

### **Go**

```go
func numBusesToDestination(routes [][]int, source int, target int) int {
	if source == target {
		return 0
	}
	n := len(routes)
	s := make([]map[int]bool, n)
	g := make([][]int, n)
	d := map[int][]int{}
	for i, r := range routes {
		for _, v := range r {
			if s[i] == nil {
				s[i] = make(map[int]bool)
			}
			s[i][v] = true
			d[v] = append(d[v], i)
		}
	}
	for _, ids := range d {
		m := len(ids)
		for i := 0; i < m; i++ {
			for j := i + 1; j < m; j++ {
				a, b := ids[i], ids[j]
				g[a] = append(g[a], b)
				g[b] = append(g[b], a)
			}
		}
	}
	q := d[source]
	vis := map[int]bool{}
	for _, v := range d[source] {
		vis[v] = true
	}
	ans := 1
	for len(q) > 0 {
		for k := len(q); k > 0; k-- {
			i := q[0]
			q = q[1:]
			if s[i][target] {
				return ans
			}
			for _, j := range g[i] {
				if !vis[j] {
					vis[j] = true
					q = append(q, j)
				}
			}
		}
		ans++
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
