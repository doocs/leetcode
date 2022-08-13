# [1319. 连通网络的操作次数](https://leetcode.cn/problems/number-of-operations-to-make-network-connected)

[English Version](/solution/1300-1399/1319.Number%20of%20Operations%20to%20Make%20Network%20Connected/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>用以太网线缆将&nbsp;<code>n</code>&nbsp;台计算机连接成一个网络，计算机的编号从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n-1</code>。线缆用&nbsp;<code>connections</code>&nbsp;表示，其中&nbsp;<code>connections[i] = [a, b]</code>&nbsp;连接了计算机&nbsp;<code>a</code>&nbsp;和&nbsp;<code>b</code>。</p>

<p>网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。</p>

<p>给你这个计算机网络的初始布线&nbsp;<code>connections</code>，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回&nbsp;-1 。&nbsp;</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1319.Number%20of%20Operations%20to%20Make%20Network%20Connected/images/sample_1_1677.png" style="height: 167px; width: 570px;"></strong></p>

<pre><strong>输入：</strong>n = 4, connections = [[0,1],[0,2],[1,2]]
<strong>输出：</strong>1
<strong>解释：</strong>拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1319.Number%20of%20Operations%20to%20Make%20Network%20Connected/images/sample_2_1677.png" style="height: 175px; width: 660px;"></strong></p>

<pre><strong>输入：</strong>n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
<strong>输出：</strong>-1
<strong>解释：</strong>线缆数量不足。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li><code>1 &lt;= connections.length &lt;= min(n*(n-1)/2, 10^5)</code></li>
	<li><code>connections[i].length == 2</code></li>
	<li><code>0 &lt;= connections[i][0], connections[i][1]&nbsp;&lt; n</code></li>
	<li><code>connections[i][0] != connections[i][1]</code></li>
	<li>没有重复的连接。</li>
	<li>两台计算机不会通过多条线缆连接。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

并查集。

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

对于本题，先遍历所有的边：

-   如果边的两个节点已经属于同个集合，说明两个节点已经相连，不必再将此边加入到集合中，累加 cnt；
-   否则将边加入集合中。

最后判断集合的数量 total 与 cnt 的大小关系。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def makeConnected(self, n: int, connections: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        cnt, size = 0, n
        p = list(range(n))
        for a, b in connections:
            if find(a) == find(b):
                cnt += 1
            else:
                p[find(a)] = find(b)
                size -= 1
        return -1 if size - 1 > cnt else size - 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public int makeConnected(int n, int[][] connections) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        int cnt = 0;
        for (int[] e : connections) {
            int a = e[0];
            int b = e[1];
            if (find(a) == find(b)) {
                ++cnt;
            } else {
                p[find(a)] = find(b);
                --n;
            }
        }
        return n - 1 > cnt ? -1 : n - 1;
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

    int makeConnected(int n, vector<vector<int>>& connections) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        int cnt = 0;
        for (auto& e : connections) {
            int a = e[0], b = e[1];
            if (find(a) == find(b))
                ++cnt;
            else {
                p[find(a)] = find(b);
                --n;
            }
        }
        return n - 1 > cnt ? -1 : n - 1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func makeConnected(n int, connections [][]int) int {
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	cnt := 0
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, e := range connections {
		a, b := e[0], e[1]
		if find(a) == find(b) {
			cnt++
		} else {
			p[find(a)] = find(b)
			n--
		}
	}
	if n-1 > cnt {
		return -1
	}
	return n - 1
}
```

### **...**

```

```

<!-- tabs:end -->
