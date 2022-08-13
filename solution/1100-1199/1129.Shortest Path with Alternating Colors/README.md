# [1129. 颜色交替的最短路径](https://leetcode.cn/problems/shortest-path-with-alternating-colors)

[English Version](/solution/1100-1199/1129.Shortest%20Path%20with%20Alternating%20Colors/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个有向图中，节点分别标记为&nbsp;<code>0, 1, ..., n-1</code>。图中每条边为红色或者蓝色，且存在自环或平行边。</p>

<p><code>red_edges</code>&nbsp;中的每一个&nbsp;<code>[i, j]</code>&nbsp;对表示从节点 <code>i</code> 到节点 <code>j</code> 的红色有向边。类似地，<code>blue_edges</code>&nbsp;中的每一个&nbsp;<code>[i, j]</code>&nbsp;对表示从节点 <code>i</code> 到节点 <code>j</code> 的蓝色有向边。</p>

<p>返回长度为 <code>n</code> 的数组&nbsp;<code>answer</code>，其中&nbsp;<code>answer[X]</code>&nbsp;是从节点&nbsp;<code>0</code>&nbsp;到节点&nbsp;<code>X</code>&nbsp;的红色边和蓝色边交替出现的最短路径的长度。如果不存在这样的路径，那么 <code>answer[x] = -1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
<strong>输出：</strong>[0,1,-1]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
<strong>输出：</strong>[0,1,-1]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
<strong>输出：</strong>[0,-1,-1]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
<strong>输出：</strong>[0,1,2]
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
<strong>输出：</strong>[0,1,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>red_edges.length &lt;= 400</code></li>
	<li><code>blue_edges.length &lt;= 400</code></li>
	<li><code>red_edges[i].length == blue_edges[i].length == 2</code></li>
	<li><code>0 &lt;= red_edges[i][j], blue_edges[i][j] &lt; n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
