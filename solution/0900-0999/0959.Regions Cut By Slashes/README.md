# [959. 由斜杠划分区域](https://leetcode-cn.com/problems/regions-cut-by-slashes)

[English Version](/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在由 1 x 1 方格组成的 N x N 网格&nbsp;<code>grid</code> 中，每个 1 x 1&nbsp;方块由 <code>/</code>、<code>\</code> 或空格构成。这些字符会将方块划分为一些共边的区域。</p>

<p>（请注意，反斜杠字符是转义的，因此 <code>\</code> 用 <code>&quot;\\&quot;</code>&nbsp;表示。）。</p>

<p>返回区域的数目。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：
</strong>[
&nbsp; &quot; /&quot;,
&nbsp; &quot;/ &quot;
]
<strong>输出：</strong>2
<strong>解释：</strong>2x2 网格如下：
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/images/1.png"></pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：
</strong>[
&nbsp; &quot; /&quot;,
&nbsp; &quot;  &quot;
]
<strong>输出：</strong>1
<strong>解释：</strong>2x2 网格如下：
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/images/2.png"></pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：
</strong>[
&nbsp; &quot;\\/&quot;,
&nbsp; &quot;/\\&quot;
]
<strong>输出：</strong>4
<strong>解释：</strong>（回想一下，因为 \ 字符是转义的，所以 &quot;\\/&quot; 表示 \/，而 &quot;/\\&quot; 表示 /\。）
2x2 网格如下：
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/images/3.png"></pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：
</strong>[
&nbsp; &quot;/\\&quot;,
&nbsp; &quot;\\/&quot;
]
<strong>输出：</strong>5
<strong>解释：</strong>（回想一下，因为 \ 字符是转义的，所以 &quot;/\\&quot; 表示 /\，而 &quot;\\/&quot; 表示 \/。）
2x2 网格如下：
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/images/4.png"></pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：
</strong>[
&nbsp; &quot;//&quot;,
&nbsp; &quot;/ &quot;
]
<strong>输出：</strong>3
<strong>解释：</strong>2x2 网格如下：
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/images/5.png">
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= grid.length == grid[0].length &lt;= 30</code></li>
	<li><code>grid[i][j]</code> 是&nbsp;<code>&#39;/&#39;</code>、<code>&#39;\&#39;</code>、或&nbsp;<code>&#39; &#39;</code>。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

并查集。

并查集模板：

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

对于本题，可以把每个方块看成四个三角形，从上开始顺时针编号 0,1,2,3，`'/'`代表 0、3，1、2 连通，`'\\'` 代表 0、1，2、3 连通，`' '` 代表 0、1、2、3 都联通，然后再和方块周围的三角形联通，最后返回总的连通分量就得到结果了。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def regionsBySlashes(self, grid: List[str]) -> int:
        n = len(grid)
        p = list(range(n * n * 4))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(n):
            for j in range(n):
                idx = i * n + j
                if i < n - 1:
                    p[find(idx * 4 + 2)] = find((idx + n) * 4)
                if j < n - 1:
                    p[find(idx * 4 + 1)] = find((idx + 1) * 4 + 3)

                if grid[i][j] == '/':
                    p[find(idx * 4)] = find(idx * 4 + 3)
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2)
                elif grid[i][j] == '\\':
                    p[find(idx * 4)] = find(idx * 4 + 1)
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3)
                else:
                    p[find(idx * 4)] = find(idx * 4 + 1)
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2)
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3)
        s = set()
        for i in range(len(p)):
            s.add(find(i))
        return len(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        p = new int[n * n * 4];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < n; ++i) {
            char[] row = grid[i].toCharArray();
            for (int j = 0; j < n; ++j) {
                int idx = i * n + j;
                if (i < n - 1) {
                    p[find(idx * 4 + 2)] = find((idx + n) * 4);
                }
                if (j < n - 1) {
                    p[find(idx * 4 + 1)] = find((idx + 1) * 4 + 3);
                }

                if (row[j] == '/') {
                    p[find(idx * 4)] = find(idx * 4 + 3);
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2);
                } else if (row[j] == '\\') {
                    p[find(idx * 4)] = find(idx * 4 + 1);
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3);
                } else {
                    p[find(idx * 4)] = find(idx * 4 + 1);
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2);
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3);
                }
            }
        }
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < p.length; ++i) {
            s.add(find(i));
        }
        return s.size();
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

    int regionsBySlashes(vector<string>& grid) {
        int n = grid.size();
        for (int i = 0; i < n * n * 4; ++i) p.push_back(i);
        for (int i = 0; i < n; ++i) {
            string row = grid[i];
            for (int j = 0; j < n; ++j) {
                int idx = i * n + j;
                if (i < n - 1) p[find(idx * 4 + 2)] = find((idx + n) * 4);
                if (j < n - 1) p[find(idx * 4 + 1)] = find((idx + 1) * 4 + 3);
                if (row[j] == '/')
                {
                    p[find(idx * 4)] = find(idx * 4 + 3);
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2);
                }
                else if (row[j] == '\\')
                {
                    p[find(idx * 4)] = find(idx * 4 + 1);
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3);
                }
                else
                {
                    p[find(idx * 4)] = find(idx * 4 + 1);
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2);
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3);
                }
            }
        }
        unordered_set<int> s;
        for (int i = 0; i < p.size(); ++i)
            s.insert(find(i));
        return s.size();
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

func regionsBySlashes(grid []string) int {
	n := len(grid)
	p = make([]int, n*n*4)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	for i := 0; i < n; i++ {
		row := grid[i]
		for j := 0; j < n; j++ {
			idx := i*n + j
			if i < n-1 {
				p[find(idx*4+2)] = find((idx + n) * 4)
			}
			if j < n-1 {
				p[find(idx*4+1)] = find((idx+1)*4 + 3)
			}
			if row[j] == '/' {
				p[find(idx*4)] = find(idx*4 + 3)
				p[find(idx*4+1)] = find(idx*4 + 2)
			} else if row[j] == '\\' {
				p[find(idx*4)] = find(idx*4 + 1)
				p[find(idx*4+2)] = find(idx*4 + 3)
			} else {
				p[find(idx*4)] = find(idx*4 + 1)
				p[find(idx*4+1)] = find(idx*4 + 2)
				p[find(idx*4+2)] = find(idx*4 + 3)
			}
		}
	}
	s := make(map[int]bool)
	for i := 0; i < len(p); i++ {
		s[find(i)] = true
	}
	return len(s)
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
