# [323. 无向图中连通分量的数目](https://leetcode-cn.com/problems/number-of-connected-components-in-an-undirected-graph)

[English Version](/solution/0300-0399/0323.Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定编号从 <code>0</code> 到 <code>n-1</code> 的 <code>n</code> 个节点和一个无向边列表（每条边都是一对节点），请编写一个函数来计算无向图中连通分量的数目。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入: </strong><code>n = 5</code> 和 <code>edges = [[0, 1], [1, 2], [3, 4]]</code>

     0          3
     |          |
     1 --- 2    4 

<strong>输出: </strong>2
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入: </strong><code>n = 5</code> 和 <code>edges = [[0, 1], [1, 2], [2, 3], [3, 4]]</code>

     0           4
     |           |
     1 --- 2 --- 3

<strong>输出:&nbsp;&nbsp;</strong>1
</pre>

<p><strong>注意:</strong><br>
你可以假设在 <code>edges</code> 中不会出现重复的边。而且由于所以的边都是无向边，<code>[0, 1]</code> 与 <code>[1, 0]</code>&nbsp; 相同，所以它们不会同时在 <code>edges</code> 中出现。</p>

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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countComponents(self, n: int, edges: List[List[int]]) -> int:
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for a, b in edges:
            p[find(a)] = find(b)
        return sum(i == find(i) for i in range(n))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;
    
    public int countComponents(int n, int[][] edges) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int[] e : edges) {
            p[find(e[0])] = find(e[1]);
        }

        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (i == find(i)) {
                ++cnt;
            }
        }
        return cnt;
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

    int countComponents(int n, vector<vector<int>>& edges) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        for (auto e : edges) p[find(e[0])] = find(e[1]);
        int cnt = 0;
        for (int i = 0; i < n; ++i)
        {
            if (i == find(i)) ++cnt;
        }
        return cnt;
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

func countComponents(n int, edges [][]int) int {
	p = make([]int, n)
	for i := 1; i < n; i++ {
		p[i] = i
	}
	for _, e := range edges {
		p[find(e[0])] = find(e[1])
	}
	cnt := 0
	for i := 0; i < n; i++ {
		if i == find(i) {
			cnt++
		}
	}
	return cnt
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}
```

### **...**

```

```

<!-- tabs:end -->
