# [剑指 Offer II 116. 朋友圈](https://leetcode-cn.com/problems/bLyHh0)

## 题目描述

<!-- 这里写题目描述 -->

<div class="original__bRMd">
<p>一个班上有 <code>n</code> 个同学，其中一些彼此是朋友，另一些不是。朋友关系是可以传递的，如果&nbsp;<font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="caret-color: rgb(199, 37, 78); font-size: 12.600000381469727px; background-color: rgb(249, 242, 244);">a</span></font>&nbsp;与&nbsp;<code>b</code>&nbsp;直接是朋友，且&nbsp;<code>b</code> 与&nbsp;<code>c</code>&nbsp;是直接朋友，那么&nbsp;<code>a</code> 与&nbsp;<code>c</code>&nbsp;就是间接朋友。</p>

<p>定义&nbsp;<strong>朋友圈&nbsp;</strong>就是一组直接或者间接朋友的同学集合。</p>

<p>给定一个 <code>n x n</code> 的矩阵 <code>isConnected</code>&nbsp;表示班上的朋友关系，其中 <code>isConnected[i][j] = 1</code> 表示第&nbsp;<code>i</code>&nbsp;个同学和第&nbsp;<code>j</code>&nbsp;个同学是直接朋友，而 <code>isConnected[i][j] = 0</code> 表示二人不是直接朋友。</p>

<p>返回矩阵中 <b>朋友圈</b>的数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20116.%20%E6%9C%8B%E5%8F%8B%E5%9C%88/images/graph1.jpg" style="width: 222px; height: 142px;" />
<pre>
<strong>输入：</strong><code>isConnected</code> = [[1,1,0],[1,1,0],[0,0,1]]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20116.%20%E6%9C%8B%E5%8F%8B%E5%9C%88/images/graph2.jpg" style="width: 222px; height: 142px;" />
<pre>
<strong>输入：</strong><code>isConnected</code><strong> </strong>= [[1,0,0],[0,1,0],[0,0,1]]
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>n == isConnected.length</code></li>
	<li><code>n == isConnected[i].length</code></li>
	<li><code>isConnected[i][j]</code> 为 <code>1</code> 或 <code>0</code></li>
	<li><code>isConnected[i][i] == 1</code></li>
	<li><code>isConnected[i][j] == isConnected[j][i]</code></li>
</ul>
</div>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 547&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/number-of-provinces/">https://leetcode-cn.com/problems/number-of-provinces/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：深度优先搜索**

判断城市之间是否属于同一个连通分量，最后连通分量的总数即为结果。

**方法二：并查集**

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

深度优先搜索：

```python
class Solution:
    def findCircleNum(self, isConnected: List[List[int]]) -> int:
        def dfs(i):
            for j in range(n):
                if not visited[j] and isConnected[i][j] == 1:
                    visited[j] = True
                    dfs(j)

        n = len(isConnected)
        visited = [False] * n
        num = 0
        for i in range(n):
            if not visited[i]:
                dfs(i)
                num += 1
        return num
```

并查集：

```python
class Solution:
    def findCircleNum(self, isConnected: List[List[int]]) -> int:
        n = len(isConnected)
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(n):
            for j in range(n):
                if i != j and isConnected[i][j] == 1:
                    p[find(i)] = find(j)
        return sum(i == find(i) for i in range(n))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

深度优先搜索：

```java
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int num = 0;
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                dfs(isConnected, visited, i, n);
                ++num;
            }
        }
        return num;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int i, int n) {
        for (int j = 0; j < n; ++j) {
            if (!visited[j] && isConnected[i][j] == 1) {
                visited[j] = true;
                dfs(isConnected, visited, j, n);
            }
        }
    }
}
```

并查集：

```java
class Solution {
    private int[] p;

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (isConnected[i][j] == 1) {
                    p[find(i)] = find(j);
                }
            }
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

    int findCircleNum(vector<vector<int>> &isConnected) {
        int n = isConnected.size();
        p.resize(n);
        for (int i = 0; i < n; ++i)
        {
            p[i] = i;
        }
        for (int i = 0; i < n; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (isConnected[i][j])
                {
                    p[find(i)] = find(j);
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; ++i)
        {
            if (i == find(i))
                ++cnt;
        }
        return cnt;
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

func findCircleNum(isConnected [][]int) int {
	n := len(isConnected)
	p = make([]int, n)
	for i := 1; i < n; i++ {
		p[i] = i
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if isConnected[i][j] == 1 {
				p[find(i)] = find(j)
			}
		}
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
