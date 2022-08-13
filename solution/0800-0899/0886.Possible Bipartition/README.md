# [886. 可能的二分法](https://leetcode.cn/problems/possible-bipartition)

[English Version](/solution/0800-0899/0886.Possible%20Bipartition/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一组&nbsp;<code>n</code>&nbsp;人（编号为&nbsp;<code>1, 2, ..., n</code>），&nbsp;我们想把每个人分进<strong>任意</strong>大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。</p>

<p>给定整数 <code>n</code>&nbsp;和数组 <code>dislikes</code>&nbsp;，其中&nbsp;<code>dislikes[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;，表示不允许将编号为 <code>a<sub>i</sub></code>&nbsp;和&nbsp;&nbsp;<code>b<sub>i</sub></code>的人归入同一组。当可以用这种方法将所有人分进两组时，返回 <code>true</code>；否则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 4, dislikes = [[1,2],[1,3],[2,4]]
<strong>输出：</strong>true
<strong>解释：</strong>group1 [1,4], group2 [2,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3, dislikes = [[1,2],[1,3],[2,3]]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2000</code></li>
	<li><code>0 &lt;= dislikes.length &lt;= 10<sup>4</sup></code></li>
	<li><code>dislikes[i].length == 2</code></li>
	<li><code>1 &lt;= dislikes[i][j] &lt;= n</code></li>
	<li><code>a<sub>i</sub>&nbsp;&lt; b<sub>i</sub></code></li>
	<li><code>dislikes</code>&nbsp;中每一组都 <strong>不同</strong></li>
</ul>

<p>&nbsp;</p>

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
    def possibleBipartition(self, n: int, dislikes: List[List[int]]) -> bool:
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        dis = defaultdict(list)
        for a, b in dislikes:
            a, b = a - 1, b - 1
            dis[a].append(b)
            dis[b].append(a)

        for i in range(n):
            for j in dis[i]:
                if find(i) == find(j):
                    return False
                p[find(j)] = find(dis[i][0])
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        p = new int[n];
        List<Integer>[] dis = new List[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            dis[i] = new ArrayList<>();
        }
        for (int[] d : dislikes) {
            int a = d[0] - 1, b = d[1] - 1;
            dis[a].add(b);
            dis[b].add(a);
        }
        for (int i = 0; i < n; ++i) {
            for (int j : dis[i]) {
                if (find(i) == find(j)) {
                    return false;
                }
                p[find(j)] = find(dis[i].get(0));
            }
        }
        return true;
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

    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        unordered_map<int, vector<int>> dis;
        for (auto& d : dislikes) {
            int a = d[0] - 1, b = d[1] - 1;
            dis[a].push_back(b);
            dis[b].push_back(a);
        }
        for (int i = 0; i < n; ++i) {
            for (int j : dis[i]) {
                if (find(i) == find(j)) return false;
                p[find(j)] = find(dis[i][0]);
            }
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func possibleBipartition(n int, dislikes [][]int) bool {
	p := make([]int, n)
	dis := make([][]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, d := range dislikes {
		a, b := d[0]-1, d[1]-1
		dis[a] = append(dis[a], b)
		dis[b] = append(dis[b], a)
	}
	for i := 0; i < n; i++ {
		for _, j := range dis[i] {
			if find(i) == find(j) {
				return false
			}
			p[find(j)] = find(dis[i][0])
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
