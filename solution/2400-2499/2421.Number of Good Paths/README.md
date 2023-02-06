# [2421. 好路径的数目](https://leetcode.cn/problems/number-of-good-paths)

[English Version](/solution/2400-2499/2421.Number%20of%20Good%20Paths/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵 <code>n</code>&nbsp;个节点的树（连通无向无环的图），节点编号从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;且恰好有&nbsp;<code>n - 1</code>&nbsp;条边。</p>

<p>给你一个长度为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>vals</code>&nbsp;，分别表示每个节点的值。同时给你一个二维整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示节点&nbsp;<code>a<sub>i</sub></code> 和&nbsp;<code>b<sub>i</sub></code><sub>&nbsp;</sub>之间有一条&nbsp;<strong>无向</strong>&nbsp;边。</p>

<p>一条 <strong>好路径</strong>&nbsp;需要满足以下条件：</p>

<ol>
	<li>开始节点和结束节点的值 <strong>相同</strong>&nbsp;。</li>
	<li>开始节点和结束节点中间的所有节点值都 <strong>小于等于</strong>&nbsp;开始节点的值（也就是说开始节点的值应该是路径上所有节点的最大值）。</li>
</ol>

<p>请你返回不同好路径的数目。</p>

<p>注意，一条路径和它反向的路径算作 <strong>同一</strong>&nbsp;路径。比方说，&nbsp;<code>0 -&gt; 1</code>&nbsp;与&nbsp;<code>1 -&gt; 0</code>&nbsp;视为同一条路径。单个节点也视为一条合法路径。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2421.Number%20of%20Good%20Paths/images/f9caaac15b383af9115c5586779dec5.png" style="width: 400px; height: 333px;"></p>

<pre><b>输入：</b>vals = [1,3,2,1,3], edges = [[0,1],[0,2],[2,3],[2,4]]
<b>输出：</b>6
<b>解释：</b>总共有 5 条单个节点的好路径。
还有 1 条好路径：1 -&gt; 0 -&gt; 2 -&gt; 4 。
（反方向的路径 4 -&gt; 2 -&gt; 0 -&gt; 1 视为跟 1 -&gt; 0 -&gt; 2 -&gt; 4 一样的路径）
注意 0 -&gt; 2 -&gt; 3 不是一条好路径，因为 vals[2] &gt; vals[0] 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2421.Number%20of%20Good%20Paths/images/149d3065ec165a71a1b9aec890776ff.png" style="width: 273px; height: 350px;"></p>

<pre><b>输入：</b>vals = [1,1,2,2,3], edges = [[0,1],[1,2],[2,3],[2,4]]
<b>输出：</b>7
<strong>解释：</strong>总共有 5 条单个节点的好路径。
还有 2 条好路径：0 -&gt; 1 和 2 -&gt; 3 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2421.Number%20of%20Good%20Paths/images/31705e22af3d9c0a557459bc7d1b62d.png" style="width: 100px; height: 88px;"></p>

<pre><b>输入：</b>vals = [1], edges = []
<b>输出：</b>1
<b>解释：</b>这棵树只有一个节点，所以只有一条好路径。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == vals.length</code></li>
	<li><code>1 &lt;= n &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= vals[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>edges</code>&nbsp;表示一棵合法的树。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 并查集**

要保证路径起点（终点）大于等于路径上的所有点，因此我们可以考虑先把所有点按值从小到大排序，然后再进行遍历，添加到连通块中，具体如下：

当遍历到点 $a$ 时， 对于小于等于 $vals[a]$ 的邻接点 $b$ 来说，若二者不在同一个连通块，则可以合并，并且可以以点 $a$ 所在的连通块中所有值为 $vals[a]$ 的点为起点，以点 $b$ 所在的连通块中所有值为 $vals[a]$ 的点为终点，两种点的个数的乘积即为加入点 $a$ 时对答案的贡献。

时间复杂度 $O(n\log n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfGoodPaths(self, vals: List[int], edges: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)

        n = len(vals)
        p = list(range(n))
        size = defaultdict(Counter)
        for i, v in enumerate(vals):
            size[i][v] = 1

        ans = n
        for v, a in sorted(zip(vals, range(n))):
            for b in g[a]:
                if vals[b] > v:
                    continue
                pa, pb = find(a), find(b)
                if pa != pb:
                    ans += size[pa][v] * size[pb][v]
                    p[pa] = pb
                    size[pb][v] += size[pa][v]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        p = new int[n];
        int[][] arr = new int[n][2];
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        Map<Integer, Map<Integer, Integer>> size = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            arr[i] = new int[] {vals[i], i};
            size.computeIfAbsent(i, k -> new HashMap<>()).put(vals[i], 1);
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        int ans = n;
        for (var e : arr) {
            int v = e[0], a = e[1];
            for (int b : g[a]) {
                if (vals[b] > v) {
                    continue;
                }
                int pa = find(a), pb = find(b);
                if (pa != pb) {
                    ans += size.get(pa).getOrDefault(v, 0) * size.get(pb).getOrDefault(v, 0);
                    p[pa] = pb;
                    size.get(pb).put(
                        v, size.get(pb).getOrDefault(v, 0) + size.get(pa).getOrDefault(v, 0));
                }
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfGoodPaths(vector<int>& vals, vector<vector<int>>& edges) {
        int n = vals.size();
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        function<int(int)> find;
        find = [&](int x) {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        unordered_map<int, unordered_map<int, int>> size;
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {vals[i], i};
            size[i][vals[i]] = 1;
        }
        sort(arr.begin(), arr.end());
        int ans = n;
        for (auto [v, a] : arr) {
            for (int b : g[a]) {
                if (vals[b] > v) {
                    continue;
                }
                int pa = find(a), pb = find(b);
                if (pa != pb) {
                    ans += size[pa][v] * size[pb][v];
                    p[pa] = pb;
                    size[pb][v] += size[pa][v];
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func numberOfGoodPaths(vals []int, edges [][]int) int {
	n := len(vals)
	p := make([]int, n)
	size := map[int]map[int]int{}
	type pair struct{ v, i int }
	arr := make([]pair, n)
	for i, v := range vals {
		p[i] = i
		if size[i] == nil {
			size[i] = map[int]int{}
		}
		size[i][v] = 1
		arr[i] = pair{v, i}
	}

	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}

	sort.Slice(arr, func(i, j int) bool { return arr[i].v < arr[j].v })
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	ans := n
	for _, e := range arr {
		v, a := e.v, e.i
		for _, b := range g[a] {
			if vals[b] > v {
				continue
			}
			pa, pb := find(a), find(b)
			if pa != pb {
				ans += size[pb][v] * size[pa][v]
				p[pa] = pb
				size[pb][v] += size[pa][v]
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
