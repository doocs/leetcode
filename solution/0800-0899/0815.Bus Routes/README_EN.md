# [815. Bus Routes](https://leetcode.com/problems/bus-routes)

[中文文档](/solution/0800-0899/0815.Bus%20Routes/README.md)

## Description

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
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= routes.length &lt;= 500</code>.</li>
	<li><code>1 &lt;= routes[i].length &lt;= 10<sup>5</sup></code></li>
	<li>All the values of <code>routes[i]</code> are <strong>unique</strong>.</li>
	<li><code>sum(routes[i].length) &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= routes[i][j] &lt; 10<sup>6</sup></code></li>
	<li><code>0 &lt;= source, target &lt; 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numBusesToDestination(self, routes: List[List[int]], source: int, target: int) -> int:
        if source == target:
            return 0

        s = [set(r) for r in routes]
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
