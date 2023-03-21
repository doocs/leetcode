# [1101. The Earliest Moment When Everyone Become Friends](https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends)

[中文文档](/solution/1100-1199/1101.The%20Earliest%20Moment%20When%20Everyone%20Become%20Friends/README.md)

## Description

<p>There are n people in a social group labeled from <code>0</code> to <code>n - 1</code>. You are given an array <code>logs</code> where <code>logs[i] = [timestamp<sub>i</sub>, x<sub>i</sub>, y<sub>i</sub>]</code> indicates that <code>x<sub>i</sub></code> and <code>y<sub>i</sub></code> will be friends at the time <code>timestamp<sub>i</sub></code>.</p>

<p>Friendship is <strong>symmetric</strong>. That means if <code>a</code> is friends with <code>b</code>, then <code>b</code> is friends with <code>a</code>. Also, person <code>a</code> is acquainted with a person <code>b</code> if <code>a</code> is friends with <code>b</code>, or <code>a</code> is a friend of someone acquainted with <code>b</code>.</p>

<p>Return <em>the earliest time for which every person became acquainted with every other person</em>. If there is no such earliest time, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]], n = 6
<strong>Output:</strong> 20190301
<strong>Explanation:</strong> 
The first event occurs at timestamp = 20190101, and after 0 and 1 become friends, we have the following friendship groups [0,1], [2], [3], [4], [5].
The second event occurs at timestamp = 20190104, and after 3 and 4 become friends, we have the following friendship groups [0,1], [2], [3,4], [5].
The third event occurs at timestamp = 20190107, and after 2 and 3 become friends, we have the following friendship groups [0,1], [2,3,4], [5].
The fourth event occurs at timestamp = 20190211, and after 1 and 5 become friends, we have the following friendship groups [0,1,5], [2,3,4].
The fifth event occurs at timestamp = 20190224, and as 2 and 4 are already friends, nothing happens.
The sixth event occurs at timestamp = 20190301, and after 0 and 3 become friends, we all become friends.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> logs = [[0,2,0],[1,0,1],[3,0,3],[4,1,2],[7,3,1]], n = 4
<strong>Output:</strong> 3
<strong>Explanation:</strong> At timestamp = 3, all the persons (i.e., 0, 1, 2, and 3) become friends.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= logs.length &lt;= 10<sup>4</sup></code></li>
	<li><code>logs[i].length == 3</code></li>
	<li><code>0 &lt;= timestamp<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>x<sub>i</sub> != y<sub>i</sub></code></li>
	<li>All the values <code>timestamp<sub>i</sub></code> are <strong>unique</strong>.</li>
	<li>All the pairs <code>(x<sub>i</sub>, y<sub>i</sub>)</code> occur at most one time in the input.</li>
</ul>

## Solutions

Union find.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def earliestAcq(self, logs: List[List[int]], n: int) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(n))
        for t, x, y in sorted(logs):
            if find(x) == find(y):
                continue
            p[find(x)] = find(y)
            n -= 1
            if n == 1:
                return t
        return -1
```

```python
class UnionFind:
    def __init__(self, n):
        self.p = list(range(n))
        self.size = [1] * n

    def find(self, x):
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]

    def union(self, a, b):
        pa, pb = self.find(a), self.find(b)
        if pa != pb:
            if self.size[pa] > self.size[pb]:
                self.p[pb] = pa
                self.size[pa] += self.size[pb]
            else:
                self.p[pa] = pb
                self.size[pb] += self.size[pa]

class Solution:
    def earliestAcq(self, logs: List[List[int]], n: int) -> int:
        uf = UnionFind(n)
        for t, x, y in sorted(logs):
            if uf.find(x) == uf.find(y):
                continue
            uf.union(x, y)
            n -= 1
            if n == 1:
                return t
        return -1
```

### **Java**

```java
class Solution {
    private int[] p;

    public int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int[] log : logs) {
            int t = log[0], x = log[1], y = log[2];
            if (find(x) == find(y)) {
                continue;
            }
            p[find(x)] = find(y);
            if (--n == 1) {
                return t;
            }
        }
        return -1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

```java
class UnionFind {
    private int[] p;
    private int[] size;

    public UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            if (size[pa] > size[pb]) {
                p[pb] = pa;
                size[pa] += size[pb];
            } else {
                p[pa] = pb;
                size[pb] += size[pa];
            }
        }
    }
}

class Solution {
    public int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        UnionFind uf = new UnionFind(n);
        for (int[] log : logs) {
            int t = log[0], x = log[1], y = log[2];
            if (uf.find(x) == uf.find(y)) {
                continue;
            }
            uf.union(x, y);
            if (--n == 1) {
                return t;
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int earliestAcq(vector<vector<int>>& logs, int n) {
        sort(logs.begin(), logs.end());
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        function<int(int)> find = [&](int x) {
            return p[x] == x ? x : p[x] = find(p[x]);
        };
        for (auto& log : logs) {
            int x = find(log[1]);
            int y = find(log[2]);
            if (x != y) {
                p[x] = y;
                --n;
            }
            if (n == 1) {
                return log[0];
            }
        }
        return -1;
    }
};
```

```cpp
class UnionFind {
public:
    UnionFind(int n) {
        p = vector<int>(n);
        size = vector<int>(n, 1);
        iota(p.begin(), p.end(), 0);
    }

    void unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            if (size[pa] > size[pb]) {
                p[pb] = pa;
                size[pa] += size[pb];
            } else {
                p[pa] = pb;
                size[pb] += size[pa];
            }
        }
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

private:
    vector<int> p, size;
};

class Solution {
public:
    int earliestAcq(vector<vector<int>>& logs, int n) {
        sort(logs.begin(), logs.end());
        UnionFind uf(n);
        for (auto& log : logs) {
            int t = log[0], x = log[1], y = log[2];
            if (uf.find(x) == uf.find(y)) {
                continue;
            }
            uf.unite(x, y);
            if (--n == 1) {
                return t;
            }
        }
        return -1;
    }
};
```

### **Go**

```go
func earliestAcq(logs [][]int, n int) int {
	sort.Slice(logs, func(i, j int) bool { return logs[i][0] < logs[j][0] })
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, log := range logs {
		t, x, y := log[0], log[1], log[2]
		if find(x) == find(y) {
			continue
		}
		p[find(x)] = find(y)
		n--
		if n == 1 {
			return t
		}
	}
	return -1
}
```

```go
type unionFind struct {
	p, size []int
}

func newUnionFind(n int) *unionFind {
	p := make([]int, n)
	size := make([]int, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	return &unionFind{p, size}
}

func (uf *unionFind) find(x int) int {
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *unionFind) union(a, b int) {
	pa, pb := uf.find(a), uf.find(b)
	if pa != pb {
		if uf.size[pa] > uf.size[pb] {
			uf.p[pb] = pa
			uf.size[pa] += uf.size[pb]
		} else {
			uf.p[pa] = pb
			uf.size[pb] += uf.size[pa]
		}
	}
}

func earliestAcq(logs [][]int, n int) int {
	sort.Slice(logs, func(i, j int) bool { return logs[i][0] < logs[j][0] })
	uf := newUnionFind(n)
	for _, log := range logs {
		t, x, y := log[0], log[1], log[2]
		if uf.find(x) == uf.find(y) {
			continue
		}
		uf.union(x, y)
		n--
		if n == 1 {
			return t
		}
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
