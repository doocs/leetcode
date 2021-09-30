# [261. 以图判树](https://leetcode-cn.com/problems/graph-valid-tree)

[English Version](/solution/0200-0299/0261.Graph%20Valid%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定从 <code>0</code> 到 <code>n-1</code>&nbsp;标号的&nbsp;<code>n</code> 个结点，和一个无向边列表（每条边以结点对来表示），请编写一个函数用来判断这些边是否能够形成一个合法有效的树结构。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> <code>n = 5</code>, 边列表 edges<code> = [[0,1], [0,2], [0,3], [1,4]]</code>
<strong>输出:</strong> true</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> <code>n = 5, </code>边列表 edges<code> = [[0,1], [1,2], [2,3], [1,3], [1,4]]</code>
<strong>输出:</strong> false</pre>

<p><strong>注意：</strong>你可以假定边列表 <code>edges</code> 中不会出现重复的边。由于所有的边是无向边，边&nbsp;<code>[0,1]</code>&nbsp;和边 <code>[1,0]</code>&nbsp;是相同的，因此不会同时出现在边列表 <code>edges</code> 中。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

并查集模板题。

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
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for a, b in edges:
            if find(a) == find(b):
                return False
            p[find(a)] = find(b)
            n -= 1
        return n == 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public boolean validTree(int n, int[][] edges) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int[] e : edges) {
            if (find(e[0]) == find(e[1])) {
                return false;
            }
            p[find(e[0])] = find(e[1]);
            --n;
        }
        return n == 1;
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

    bool validTree(int n, vector<vector<int>> &edges) {
        for (int i = 0; i < n; ++i)
        {
            p.push_back(i);
        }
        for (auto e : edges)
        {
            if (find(e[0]) == find(e[1]))
                return false;
            p[find(e[0])] = find(e[1]);
            --n;
        }
        return n == 1;
    }

    int find(int x) {
        if (p[x] != x)
        {
            p[x] = find(p[x]);
        }
        return p[x];
    }
};
```

### **Go**

```go
var p []int

func validTree(n int, edges [][]int) bool {
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	for _, e := range edges {
		if find(e[0]) == find(e[1]) {
			return false
		}
		p[find(e[0])] = find(e[1])
		n--
	}
	return n == 1
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
