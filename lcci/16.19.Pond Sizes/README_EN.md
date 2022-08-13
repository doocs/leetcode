# [16.19. Pond Sizes](https://leetcode.cn/problems/pond-sizes-lcci)

[中文文档](/lcci/16.19.Pond%20Sizes/README.md)

## Description

<p>You have an integer matrix representing a plot of land, where the value at that loca&shy;tion represents the height above sea level. A value of zero indicates water. A pond is a region of water connected vertically, horizontally, or diagonally. The size of the pond is the total number of connected water cells. Write a method to compute the sizes of all ponds in the matrix.</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong>

[

  [0,2,1,0],

  [0,1,0,1],

  [1,1,0,1],

  [0,1,0,1]

]

<strong>Output: </strong> [1,2,4]

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>0 &lt; len(land) &lt;= 1000</code></li>
	<li><code>0 &lt; len(land[i]) &lt;= 1000</code></li>
</ul>

## Solutions

Union find.

<!-- tabs:start -->

### **Python3**

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
