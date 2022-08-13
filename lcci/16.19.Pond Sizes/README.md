# [面试题 16.19. 水域大小](https://leetcode.cn/problems/pond-sizes-lcci)

[English Version](/lcci/16.19.Pond%20Sizes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>你有一个用于表示一片土地的整数矩阵<code>land</code>，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong>
[
  [0,2,1,0],
  [0,1,0,1],
  [1,1,0,1],
  [0,1,0,1]
]
<strong>输出：</strong> [1,2,4]
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>0 < len(land) <= 1000</code></li>
<li><code>0 < len(land[i]) <= 1000</code></li>
</ul>

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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def pondSizes(self, land: List[List[int]]) -> List[int]:
        m, n = len(land), len(land[0])
        p = list(range(m * n))
        size = [1] * (m * n)

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa == pb:
                return
            size[pb] += size[pa]
            p[pa] = pb

        for i in range(m):
            for j in range(n):
                if land[i][j] != 0:
                    continue
                idx = i * n + j
                if i < m - 1 and land[i + 1][j] == 0:
                    union(idx, (i + 1) * n + j)
                if j < n - 1 and land[i][j + 1] == 0:
                    union(idx, i * n + j + 1)
                if i < m - 1 and j < n - 1 and land[i + 1][j + 1] == 0:
                    union(idx, (i + 1) * n + j + 1)
                if i < m - 1 and j > 0 and land[i + 1][j - 1] == 0:
                    union(idx, (i + 1) * n + j - 1)

        s = set()
        res = []
        for i in range(m * n):
            if land[i // n][i % n] != 0:
                continue
            root = find(i)
            if root not in s:
                s.add(root)
                res.append(size[root])
        res.sort()
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;
    private int[] size;

    public int[] pondSizes(int[][] land) {
        int m = land.length, n = land[0].length;
        p = new int[m * n];
        size = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (land[i][j] != 0) {
                    continue;
                }
                int idx = i * n + j;
                if (i < m - 1 && land[i + 1][j] == 0) {
                    union(idx, (i + 1) * n + j);
                }
                if (j < n - 1 && land[i][j + 1] == 0) {
                    union(idx, i * n + j + 1);
                }
                if (i < m - 1 && j < n - 1 && land[i + 1][j + 1] == 0) {
                    union(idx, (i + 1) * n + j + 1);
                }
                if (i < m - 1 && j > 0 && land[i + 1][j - 1] == 0) {
                    union(idx, (i + 1) * n + j - 1);
                }
            }
        }
        Set<Integer> s = new HashSet<>();
        List<Integer> t = new ArrayList<>();
        for (int i = 0; i < m * n; ++i) {
            if (land[i / n][i % n] != 0) {
                continue;
            }
            int root = find(i);
            if (!s.contains(root)) {
                s.add(root);
                t.add(size[root]);
            }
        }
        Collections.sort(t);
        int[] res = new int[t.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = t.get(i);
        }
        return res;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return;
        }
        size[pb] += size[pa];
        p[pa] = pb;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;
    vector<int> size;

    vector<int> pondSizes(vector<vector<int>>& land) {
        int m = land.size(), n = land[0].size();
        for (int i = 0; i < m * n; ++i) {
            p.push_back(i);
            size.push_back(1);
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (land[i][j] != 0) continue;
                int idx = i * n + j;
                if (i < m - 1 && land[i + 1][j] == 0) unite(idx, (i + 1) * n + j);
                if (j < n - 1 && land[i][j + 1] == 0) unite(idx, i * n + j + 1);
                if (i < m - 1 && j < n - 1 && land[i + 1][j + 1] == 0) unite(idx, (i + 1) * n + j + 1);
                if (i < m - 1 && j > 0 && land[i + 1][j - 1] == 0) unite(idx, (i + 1) * n + j - 1);
            }
        }
        unordered_set<int> s;
        vector<int> res;
        for (int i = 0; i < m * n; ++i) {
            if (land[i / n][i % n] != 0) continue;
            int root = find(i);
            if (s.find(root) == s.end()) {
                s.insert(root);
                res.push_back(size[root]);
            }
        }
        sort(res.begin(), res.end());
        return res;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return;
        size[pb] += size[pa];
        p[pa] = pb;
    }
};
```

### **Go**

```go
var p []int
var size []int

func pondSizes(land [][]int) []int {
	m, n := len(land), len(land[0])
	p = make([]int, m*n)
	size = make([]int, m*n)
	for i := 0; i < m*n; i++ {
		p[i] = i
		size[i] = 1
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if land[i][j] != 0 {
				continue
			}
			idx := i*n + j
			if i < m-1 && land[i+1][j] == 0 {
				union(idx, (i+1)*n+j)
			}
			if j < n-1 && land[i][j+1] == 0 {
				union(idx, i*n+j+1)
			}
			if i < m-1 && j < n-1 && land[i+1][j+1] == 0 {
				union(idx, (i+1)*n+j+1)
			}
			if i < m-1 && j > 0 && land[i+1][j-1] == 0 {
				union(idx, (i+1)*n+j-1)
			}
		}
	}
	s := make(map[int]bool)
	var res []int
	for i := 0; i < m*n; i++ {
		if land[i/n][i%n] != 0 {
			continue
		}
		root := find(i)
		if !s[root] {
			s[root] = true
			res = append(res, size[root])
		}
	}
	sort.Ints(res)
	return res
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}

func union(a, b int) {
	pa, pb := find(a), find(b)
	if pa == pb {
		return
	}
	size[pb] += size[pa]
	p[pa] = pb
}
```

### **...**

```

```

<!-- tabs:end -->
