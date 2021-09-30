# [1584. 连接所有点的最小费用](https://leetcode-cn.com/problems/min-cost-to-connect-all-points)

[English Version](/solution/1500-1599/1584.Min%20Cost%20to%20Connect%20All%20Points/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个<code>points</code>&nbsp;数组，表示 2D 平面上的一些点，其中&nbsp;<code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;。</p>

<p>连接点&nbsp;<code>[x<sub>i</sub>, y<sub>i</sub>]</code> 和点&nbsp;<code>[x<sub>j</sub>, y<sub>j</sub>]</code>&nbsp;的费用为它们之间的 <strong>曼哈顿距离</strong>&nbsp;：<code>|x<sub>i</sub> - x<sub>j</sub>| + |y<sub>i</sub> - y<sub>j</sub>|</code>&nbsp;，其中&nbsp;<code>|val|</code>&nbsp;表示&nbsp;<code>val</code>&nbsp;的绝对值。</p>

<p>请你返回将所有点连接的最小总费用。只有任意两点之间 <strong>有且仅有</strong>&nbsp;一条简单路径时，才认为所有点都已连接。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1584.Min%20Cost%20to%20Connect%20All%20Points/images/d.png" style="height:268px; width:214px" /></p>

<pre>
<strong>输入：</strong>points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
<strong>输出：</strong>20
<strong>解释：
</strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1584.Min%20Cost%20to%20Connect%20All%20Points/images/c.png" style="height:268px; width:214px" />
我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
注意到任意两个点之间只有唯一一条路径互相到达。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>points = [[3,12],[-2,5],[-4,1]]
<strong>输出：</strong>18
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>points = [[0,0],[1,1],[1,0],[-1,1]]
<strong>输出：</strong>4
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>points = [[-1000000,-1000000],[1000000,1000000]]
<strong>输出：</strong>4000000
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>points = [[0,0]]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 1000</code></li>
	<li><code>-10<sup>6</sup>&nbsp;&lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li>所有点&nbsp;<code>(x<sub>i</sub>, y<sub>i</sub>)</code>&nbsp;两两不同。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

最小生成树 + 并查集。先将所有边按照长度由小到大进行排序，循环遍历每条边，逐个加入到图中，当所有点达到一个连通状态时，退出循环，返回此时的总费用即可。

模板 1——朴素并查集：

```python
# 初始化，p存储每个点的父节点
p = list(range(n))

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        # 路径压缩
        p[x] = find(p[x])
    return p[x]

# 合并a和b所在的两个集合
p[find(a)] = find(b)
```

模板 2——维护 size 的并查集：

```python
# 初始化，p存储每个点的父节点，size只有当节点是祖宗节点时才有意义，表示祖宗节点所在集合中，点的数量
p = list(range(n))
size = [1] * n

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        # 路径压缩
        p[x] = find(p[x])
    return p[x]

# 合并a和b所在的两个集合
if find(a) != find(b):
    size[find(b)] += size[find(a)]
    p[find(a)] = find(b)
```

模板 3——维护到祖宗节点距离的并查集：

```python
# 初始化，p存储每个点的父节点，d[x]存储x到p[x]的距离
p = list(range(n))
d = [0] * n

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        t = find(p[x])
        d[x] += d[p[x]]
        p[x] = t
    return p[x]

# 合并a和b所在的两个集合
p[find(a)] = find(b)
d[find(a)] = distance
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minCostConnectPoints(self, points: List[List[int]]) -> int:
        n = len(points)
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        edges = []
        for i in range(n):
            x1, y1 = points[i]
            for j in range(i + 1, n):
                x2, y2 = points[j]
                edges.append([abs(x1 - x2) + abs(y1 - y2), i, j])
        edges.sort()
        res = 0
        for cost, i, j in edges:
            if find(i) == find(j):
                continue
            p[find(i)] = find(j)
            n -= 1
            res += cost
            if n == 1:
                return res
        return 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j =  i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                edges.add(new int[]{Math.abs(x1 - x2) + Math.abs(y1 - y2), i, j});
            }
        }
        edges.sort(Comparator.comparingInt(a -> a[0]));
        int res = 0;
        for (int[] e : edges) {
            if (find(e[1]) == find(e[2])) {
                continue;
            }
            p[find(e[1])] = find(e[2]);
            --n;
            res += e[0];
            if (n == 1) {
                break;
            }
        }
        return res;
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
    vector<int> p;

    int minCostConnectPoints(vector<vector<int>>& points) {
        int n = points.size();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        vector<vector<int>> edges;
        for (int i = 0; i < n; ++i)
        {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j)
            {
                int x2 = points[j][0], y2 = points[j][1];
                edges.push_back({abs(x1 - x2) + abs(y1 - y2), i, j});
            }
        }
        sort(edges.begin(), edges.end());
        int res = 0;
        for (auto e : edges)
        {
            if (find(e[1]) == find(e[2])) continue;
            p[find(e[1])] = find(e[2]);
            --n;
            res += e[0];
            if (n == 1) break;
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
var p []int

func minCostConnectPoints(points [][]int) int {
    n := len(points)
    p = make([]int, n)
    for i := 0; i < n; i++ {
        p[i] = i
    }
    var edges [][]int
    for i := 0; i < n; i++ {
        x1, y1 := points[i][0], points[i][1]
        for j := i + 1; j < n; j++ {
            x2, y2 := points[j][0], points[j][1]
            edges = append(edges, []int{abs(x1 - x2) + abs(y1 - y2), i, j})
        }
    }
    sort.Slice(edges, func(i, j int) bool {
        return edges[i][0] < edges[j][0]
    })
    res := 0
    for _, e := range edges {
        if find(e[1]) == find(e[2]) {
            continue
        }
        p[find(e[1])] = find(e[2])
        n--
        res += e[0]
        if n == 1 {
            break
        }
    }
    return res
}

func find(x int) int {
    if p[x] != x {
        p[x] = find(p[x])
    }
    return p[x]
}

func abs(x int) int {
    if x > 0 {
        return x
    }
    return -x
}
```

### **...**

```

```

<!-- tabs:end -->
