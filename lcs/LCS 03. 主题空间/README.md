# [LCS 03. 主题空间](https://leetcode-cn.com/problems/YesdPw/)

## 题目描述

<!-- 这里写题目描述 -->

「以扣会友」线下活动所在场地由若干主题空间与走廊组成，场地的地图记作由一维字符串型数组 `grid`，字符串中仅包含 `"0"～"5"` 这 6 个字符。地图上每一个字符代表面积为 1 的区域，其中 `"0"` 表示走廊，其他字符表示主题空间。相同且连续（连续指上、下、左、右四个方向连接）的字符组成同一个主题空间。

假如整个 `grid` 区域的外侧均为走廊。请问，不与走廊直接相邻的主题空间的最大面积是多少？如果不存在这样的空间请返回 `0`。

**示例 1:**

> 输入：`grid = ["110","231","221"]`
>
> 输出：`1`
>
> 解释：4 个主题空间中，只有 1 个不与走廊相邻，面积为 1。
> ![image.png](https://cdn.jsdelivr.net/gh/doocs/leetcode@main/lcs/LCS%2003.%20主题空间/images/1613708145-rscctN-image.png)

**示例 2:**

> 输入：`grid = ["11111100000","21243101111","21224101221","11111101111"]`
>
> 输出：`3`
>
> 解释：8 个主题空间中，有 5 个不与走廊相邻，面积分别为 3、1、1、1、2，最大面积为 3。
> ![image.png](https://cdn.jsdelivr.net/gh/doocs/leetcode@main/lcs/LCS%2003.%20主题空间/images/1613707985-KJyiXJ-image.png)

**提示：**

- `1 <= grid.length <= 500`
- `1 <= grid[i].length <= 500`
- `grid[i][j]` 仅可能是 `"0"～"5"`

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
    def largestArea(self, grid: List[str]) -> int:
        m, n = len(grid), len(grid[0])
        p = list(range(m * n + 1))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(m):
            for j in range(n):
                if i == 0 or i == m - 1 or j == 0 or j == n - 1 or grid[i][j] == '0':
                    p[find(i * n + j)] = find(m * n)
                else:
                    for x, y in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                        if grid[i + x][j + y] == '0' or grid[i][j] == grid[i + x][j + y]:
                            p[find(i * n + j)] = find((i + x) * n + j + y)

        mp = collections.defaultdict(int)
        res = 0
        for i in range(m):
            for j in range(n):
                root = find(i * n + j)
                if root != find(m * n):
                    mp[root] += 1
                    res = max(res, mp[root])
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;
    private int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int largestArea(String[] grid) {
        int m = grid.length, n = grid[0].length();
        p = new int[m * n + 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1 || grid[i].charAt(j) == '0') {
                    p[find(i * n + j)] = find(m * n);
                } else {
                    for (int[] e : dirs) {
                        if (grid[i + e[0]].charAt(j + e[1]) == '0' || grid[i].charAt(j) == grid[i + e[0]].charAt(j + e[1])) {
                            p[find(i * n + j)] = find((i + e[0]) * n + j + e[1]);
                        }
                    }
                }
            }
        }
        Map<Integer, Integer> mp = new HashMap<>();
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int root = find(i * n + j);
                if (root != find(m * n)) {
                    mp.put(root, mp.getOrDefault(root, 0) + 1);
                    res = Math.max(res, mp.get(root));
                }
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
    int dirs[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    int largestArea(vector<string>& grid) {
        int m = grid.size(), n = grid[0].size();
        p.resize(m * n + 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1 || grid[i][j] == '0') 
                    p[find(i * n + j)] = find(m * n);
                else
                {
                    for (auto e : dirs)
                    {
                        if (grid[i + e[0]][j + e[1]] == '0' || grid[i][j]== grid[i + e[0]][j + e[1]])
                            p[find(i * n + j)] = find((i + e[0]) * n + j + e[1]);
                    }
                }
            }
        }
        unordered_map<int, int> mp;
        int res = 0;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                int root = find(i * n + j);
                if (root != find(m * n))
                {
                    ++mp[root];
                    res = max(res, mp[root]);
                }
            }
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

func largestArea(grid []string) int {
	m, n := len(grid), len(grid[0])
	p = make([]int, m*n+1)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}

	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if i == 0 || i == m-1 || j == 0 || j == n-1 || grid[i][j] == '0' {
				p[find(i*n+j)] = find(m * n)
			} else {
				for _, e := range dirs {
					if grid[i+e[0]][j+e[1]] == '0' || grid[i][j] == grid[i+e[0]][j+e[1]] {
						p[find(i*n+j)] = find((i+e[0])*n + j + e[1])
					}
				}
			}
		}
	}
	mp := make(map[int]int, 0)
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			root := find(i*n + j)
			if root != find(m*n) {
				mp[root]++
				res = max(res, mp[root])
			}
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

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
