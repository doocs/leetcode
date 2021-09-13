# [886. 可能的二分法](https://leetcode-cn.com/problems/possible-bipartition)

[English Version](/solution/0800-0899/0886.Possible%20Bipartition/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一组 <code>N</code> 人（编号为 <code>1, 2, ..., N</code>）， 我们想把每个人分进<strong>任意</strong>大小的两组。</p>

<p>每个人都可能不喜欢其他人，那么他们不应该属于同一组。</p>

<p>形式上，如果 <code>dislikes[i] = [a, b]</code>，表示不允许将编号为 <code>a</code> 和 <code>b</code> 的人归入同一组。</p>

<p>当可以用这种方法将所有人分进两组时，返回 <code>true</code>；否则返回 <code>false</code>。</p>

<p> </p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>N = 4, dislikes = [[1,2],[1,3],[2,4]]
<strong>输出：</strong>true
<strong>解释：</strong>group1 [1,4], group2 [2,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>N = 3, dislikes = [[1,2],[1,3],[2,3]]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= N <= 2000</code></li>
	<li><code>0 <= dislikes.length <= 10000</code></li>
	<li><code>dislikes[i].length == 2</code></li>
	<li><code>1 <= dislikes[i][j] <= N</code></li>
	<li><code>dislikes[i][0] < dislikes[i][1]</code></li>
	<li>对于 <code>dislikes[i] == dislikes[j]</code> 不存在 <code>i != j</code></li>
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
x
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

        mp = collections.defaultdict(list)
        for i, j in dislikes:
            mp[i - 1].append(j - 1)
            mp[j - 1].append(i - 1)
        for i in range(n):
            dis = mp[i]
            for j in dis:
                if find(i) == find(j):
                    return False
                p[find(j)] = find(dis[0])
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
public:
    vector<int> p;

    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        unordered_map<int, vector<int>> mp;
        for (auto e : dislikes)
        {
            mp[e[0] - 1].push_back(e[1] - 1);
            mp[e[1] - 1].push_back(e[0] - 1);
        }
        for (int i = 0; i < n; ++i)
        {
            auto dis = mp[i];
            for (int j : dis)
            {
                if (find(i) == find(j)) return false;
                p[find(j)] = find(dis[0]);
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

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        unordered_map<int, vector<int>> mp;
        for (auto e : dislikes)
        {
            mp[e[0] - 1].push_back(e[1] - 1);
            mp[e[1] - 1].push_back(e[0] - 1);
        }
        for (int i = 0; i < n; ++i)
        {
            auto dis = mp[i];
            for (int j : dis)
            {
                if (find(i) == find(j)) return false;
                p[find(j)] = find(dis[0]);
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
var p []int

func possibleBipartition(n int, dislikes [][]int) bool {
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	mp := make(map[int][]int)
	for _, e := range dislikes {
		mp[e[0]-1] = append(mp[e[0]-1], e[1]-1)
		mp[e[1]-1] = append(mp[e[1]-1], e[0]-1)
	}
	for i := 0; i < n; i++ {
		dis := mp[i]
		for _, j := range dis {
			if find(i) == find(j) {
				return false
			}
			p[find(j)] = find(dis[0])
		}
	}
	return true
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
