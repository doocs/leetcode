# [1697. 检查边长度限制的路径是否存在](https://leetcode.cn/problems/checking-existence-of-edge-length-limited-paths)

[English Version](/solution/1600-1699/1697.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>n</code> 个点组成的无向图边集 <code>edgeList</code> ，其中 <code>edgeList[i] = [u<sub>i</sub>, v<sub>i</sub>, dis<sub>i</sub>]</code> 表示点 <code>u<sub>i</sub></code> 和点 <code>v<sub>i</sub></code> 之间有一条长度为 <code>dis<sub>i</sub></code> 的边。请注意，两个点之间可能有 <strong>超过一条边 </strong>。</p>

<p>给你一个查询数组<code>queries</code> ，其中 <code>queries[j] = [p<sub>j</sub>, q<sub>j</sub>, limit<sub>j</sub>]</code> ，你的任务是对于每个查询 <code>queries[j]</code> ，判断是否存在从 <code>p<sub>j</sub></code> 到 <code>q<sub>j</sub></code><sub> </sub>的路径，且这条路径上的每一条边都 <strong>严格小于</strong> <code>limit<sub>j</sub></code> 。</p>

<p>请你返回一个 <b>布尔数组</b><em> </em><code>answer</code><em> </em>，其中<em> </em><code>answer.length == queries.length</code> ，当 <code>queries[j]</code> 的查询结果为 <code>true</code> 时， <code>answer</code> 第<em> </em><code>j</code> 个值为<em> </em><code>true</code><em> </em>，否则为 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1697.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths/images/h.png" style="width: 267px; height: 262px;" />
<pre>
<b>输入：</b>n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
<b>输出：</b>[false,true]
<b>解释：</b>上图为给定的输入数据。注意到 0 和 1 之间有两条重边，分别为 2 和 16 。
对于第一个查询，0 和 1 之间没有小于 2 的边，所以我们返回 false 。
对于第二个查询，有一条路径（0 -> 1 -> 2）两条边都小于 5 ，所以这个查询我们返回 true 。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1697.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths/images/q.png" style="width: 390px; height: 358px;" />
<pre>
<b>输入：</b>n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
<b>输出：</b>[true,false]
<b>解释：</b>上图为给定数据。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 10<sup>5</sup></code></li>
	<li><code>1 <= edgeList.length, queries.length <= 10<sup>5</sup></code></li>
	<li><code>edgeList[i].length == 3</code></li>
	<li><code>queries[j].length == 3</code></li>
	<li><code>0 <= u<sub>i</sub>, v<sub>i</sub>, p<sub>j</sub>, q<sub>j</sub> <= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>p<sub>j</sub> != q<sub>j</sub></code></li>
	<li><code>1 <= dis<sub>i</sub>, limit<sub>j</sub> <= 10<sup>9</sup></code></li>
	<li>两个点之间可能有 <strong>多条</strong> 边。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

这是一道**离线思维**的题目。

**离线**的意思是，一道题目会给出若干 query，而这些 query 会全部提前给出。也就是说，我们可以不必按照 query 的顺序依次对它们进行处理，而是可以按照另外某种顺序进行处理。与**离线**相对应的是**在线**，即所有 query 会依次给出，在返回第 k 个 query 的答案之前，不会获得第 k+1 个 query。

对于本题，可以转换为：将小于 limit 的所有边加入图中，判断此时 pj, qj 是否连通。可以用并查集来实现。

以下是并查集的几个常用模板。

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
    def distanceLimitedPathsExist(self, n: int, edgeList: List[List[int]], queries: List[List[int]]) -> List[bool]:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(n))
        edgeList.sort(key=lambda x: x[2])

        m = len(queries)
        indexes = list(range(m))
        indexes.sort(key=lambda i: queries[i][2])
        ans = [False] * m
        i = 0
        for j in indexes:
            pj, qj, limit = queries[j]
            while i < len(edgeList) and edgeList[i][2] < limit:
                u, v, _ = edgeList[i]
                p[find(u)] = find(v)
                i += 1
            ans[j] = find(pj) == find(qj)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        int m = queries.length;
        Integer[] indexes = new Integer[m];
        for (int i = 0; i < m; ++i) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, Comparator.comparingInt(i -> queries[i][2]));
        Arrays.sort(edgeList, Comparator.comparingInt(a -> a[2]));
        boolean[] ans = new boolean[m];
        int i = 0;
        for (int j : indexes) {
            int pj = queries[j][0], qj = queries[j][1], limit = queries[j][2];
            while (i < edgeList.length && edgeList[i][2] < limit) {
                int u = edgeList[i][0], v = edgeList[i][1];
                p[find(u)] = find(v);
                ++i;
            }
            ans[j] = find(pj) == find(qj);
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
    vector<int> p;

    vector<bool> distanceLimitedPathsExist(int n, vector<vector<int>>& edgeList, vector<vector<int>>& queries) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        sort(edgeList.begin(), edgeList.end(), [](const auto& e1, const auto& e2) {
            return e1[2] < e2[2];
        });
        int m = queries.size();
        vector<int> indexes(m);
        for (int i = 0; i < m; ++i) indexes[i] = i;
        sort(indexes.begin(), indexes.end(), [&](int i, int j) {
            return queries[i][2] < queries[j][2];
        });

        vector<bool> ans(m, false);
        int i = 0;
        for (int j : indexes) {
            int pj = queries[j][0], qj = queries[j][1], limit = queries[j][2];
            while (i < edgeList.size() && edgeList[i][2] < limit) {
                int u = edgeList[i][0], v = edgeList[i][1];
                p[find(u)] = find(v);
                ++i;
            }
            ans[j] = find(pj) == find(qj);
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func distanceLimitedPathsExist(n int, edgeList [][]int, queries [][]int) []bool {
	p := make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	sort.Slice(edgeList, func(i, j int) bool {
		return edgeList[i][2] < edgeList[j][2]
	})
	m := len(queries)
	indexes := make([]int, m)
	for i := 0; i < m; i++ {
		indexes[i] = i
	}
	sort.Slice(indexes, func(i, j int) bool {
		return queries[indexes[i]][2] < queries[indexes[j]][2]
	})
	ans := make([]bool, m)
	i := 0
	for _, j := range indexes {
		pj, qj, limit := queries[j][0], queries[j][1], queries[j][2]
		for i < len(edgeList) && edgeList[i][2] < limit {
			u, v := edgeList[i][0], edgeList[i][1]
			p[find(u)] = find(v)
			i++
		}
		ans[j] = find(pj) == find(qj)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
