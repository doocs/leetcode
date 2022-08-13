# [1129. Shortest Path with Alternating Colors](https://leetcode.com/problems/shortest-path-with-alternating-colors)

[中文文档](/solution/1100-1199/1129.Shortest%20Path%20with%20Alternating%20Colors/README.md)

## Description

<p>You are given an integer <code>n</code>, the number of nodes in a directed graph where the nodes are labeled from <code>0</code> to <code>n - 1</code>. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.</p>

<p>You are given two arrays <code>redEdges</code> and <code>blueEdges</code> where:</p>

<ul>
	<li><code>redEdges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is a directed red edge from node <code>a<sub>i</sub></code> to node <code>b<sub>i</sub></code> in the graph, and</li>
	<li><code>blueEdges[j] = [u<sub>j</sub>, v<sub>j</sub>]</code> indicates that there is a directed blue edge from node <code>u<sub>j</sub></code> to node <code>v<sub>j</sub></code> in the graph.</li>
</ul>

<p>Return an array <code>answer</code> of length <code>n</code>, where each <code>answer[x]</code> is the length of the shortest path from node <code>0</code> to node <code>x</code> such that the edge colors alternate along the path, or <code>-1</code> if such a path does not exist.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
<strong>Output:</strong> [0,1,-1]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
<strong>Output:</strong> [0,1,-1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= redEdges.length,&nbsp;blueEdges.length &lt;= 400</code></li>
	<li><code>redEdges[i].length == blueEdges[j].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub>, u<sub>j</sub>, v<sub>j</sub> &lt; n</code></li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def shortestAlternatingPaths(
        self, n: int, redEdges: List[List[int]], blueEdges: List[List[int]]
    ) -> List[int]:
        red = defaultdict(list)
        blue = defaultdict(list)
        for i, j in redEdges:
            red[i].append(j)
        for i, j in blueEdges:
            blue[i].append(j)
        vis_blue = [False] * n
        vis_red = [False] * n
        q = deque([(0, True), (0, False)])
        ans = [-1] * n
        d = -1
        while q:
            d += 1
            for _ in range(len(q)):
                i, b = q.popleft()
                if ans[i] == -1 or ans[i] > d:
                    ans[i] = d
                vis = vis_blue if b else vis_red
                vis[i] = True
                ne = red[i] if b else blue[i]
                v = vis_red if b else vis_blue
                for j in ne:
                    if not v[j]:
                        v[j] = True
                        q.append((j, not b))
        return ans
```

### **Java**

```java
class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] red = get(n, redEdges);
        List<Integer>[] blue = get(n, blueEdges);
        boolean[] visBlue = new boolean[n];
        boolean[] visRed = new boolean[n];
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 1});
        q.offer(new int[]{0, 0});
        int d = -1;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        while (!q.isEmpty()) {
            ++d;
            for (int t = q.size(); t > 0; --t) {
                int[] p = q.poll();
                int i = p[0];
                boolean b = p[1] == 1;
                if (ans[i] == -1 || ans[i] > d) {
                    ans[i] = d;
                }
                boolean[] vis = b ? visBlue : visRed;
                vis[i] = true;
                List<Integer> ne = b ? red[i] : blue[i];
                boolean[] v = b ? visRed : visBlue;
                for (int j : ne) {
                    if (!v[j]) {
                        v[j] = true;
                        q.offer(new int[]{j, 1 - p[1]});
                    }
                }
            }
        }
        return ans;
    }

    private List<Integer>[] get(int n, int[][] edges) {
        List<Integer>[] res = new List[n];
        for (int i = 0; i < n; ++i) {
            res[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            res[e[0]].add(e[1]);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> shortestAlternatingPaths(int n, vector<vector<int>>& redEdges, vector<vector<int>>& blueEdges) {
        vector<vector<int>> red = get(n, redEdges);
        vector<vector<int>> blue = get(n, blueEdges);
        vector<bool> visBlue(n);
        vector<bool> visRed(n);
        queue<pair<int, bool>> q;
        q.push({0, true});
        q.push({0, false});
        int d = -1;
        vector<int> ans(n, -1);
        while (!q.empty()) {
            ++d;
            for (int t = q.size(); t > 0; --t) {
                auto p = q.front();
                q.pop();
                int i = p.first;
                bool b = p.second;
                if (ans[i] == -1 || ans[i] > d) ans[i] = d;
                vector<bool>& vis = b ? visBlue : visRed;
                vis[i] = true;
                vector<int>& ne = b ? red[i] : blue[i];
                vector<bool>& v = b ? visRed : visBlue;
                for (int j : ne) {
                    if (v[j]) continue;
                    v[j] = true;
                    q.push({j, !b});
                }
            }
        }
        return ans;
    }

    vector<vector<int>> get(int n, vector<vector<int>>& edges) {
        vector<vector<int>> res(n);
        for (auto& e : edges) res[e[0]].push_back(e[1]);
        return res;
    }
};
```

### **Go**

```go
func shortestAlternatingPaths(n int, redEdges [][]int, blueEdges [][]int) []int {
	get := func(edges [][]int) [][]int {
		res := make([][]int, n)
		for _, e := range edges {
			res[e[0]] = append(res[e[0]], e[1])
		}
		return res
	}
	red := get(redEdges)
	blue := get(blueEdges)
	visBlue := make([]bool, n)
	visRed := make([]bool, n)
	q := [][]int{{0, 1}, {0, 0}}
	ans := make([]int, n)
	for i := range ans {
		ans[i] = -1
	}
	d := -1
	for len(q) > 0 {
		d++
		for t := len(q); t > 0; t-- {
			p := q[0]
			q = q[1:]
			i := p[0]
			b := p[1] == 1
			if ans[i] == -1 || ans[i] > d {
				ans[i] = d
			}
			vis := visRed
			ne := blue[i]
			v := visBlue
			if b {
				vis = visBlue
				ne = red[i]
				v = visRed
			}
			vis[i] = true
			for _, j := range ne {
				if !v[j] {
					v[j] = true
					q = append(q, []int{j, 1 - p[1]})
				}
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
