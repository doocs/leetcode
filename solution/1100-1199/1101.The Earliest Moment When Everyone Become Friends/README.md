# [1101. 彼此熟识的最早时间](https://leetcode.cn/problems/the-earliest-moment-when-everyone-become-friends)

[English Version](/solution/1100-1199/1101.The%20Earliest%20Moment%20When%20Everyone%20Become%20Friends/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个社交圈子当中，有&nbsp;<code>n</code>&nbsp;个人。每个人都有一个从&nbsp;<code>0</code> 到&nbsp;<code>n - 1</code>&nbsp;的唯一编号。我们有一份日志列表&nbsp;<code>logs</code>，其中&nbsp;<code>logs[i] = [timestamp<sub>i</sub>, x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;表示 <code>x<sub>i</sub></code>&nbsp;和&nbsp;<code>y<sub>i</sub></code>&nbsp;将在同一时间&nbsp;<code>timestamp<sub>i</sub></code><sub>&nbsp;</sub>成为朋友。</p>

<p>友谊是 <strong>相互</strong> 的。也就是说，如果 <code>a</code> 和 <code>b</code> 是朋友，那么&nbsp;<code>b</code>&nbsp;和 <code>a</code> 也是朋友。同样，如果 <code>a</code> 和 <code>b</code> 是朋友，或者&nbsp;<code>a</code> 是 <code>b</code> 朋友的朋友 ，那么 <code>a</code> 和 <code>b</code> 是熟识友。</p>

<p>返回圈子里所有人之间都熟识的最早时间。如果找不到最早时间，就返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]], N = 6
<strong>输出：</strong>20190301
<strong>解释：</strong>
第一次结交发生在 timestamp = 20190101，0 和 1 成为好友，社交朋友圈如下 [0,1], [2], [3], [4], [5]。
第二次结交发生在 timestamp = 20190104，3 和 4 成为好友，社交朋友圈如下 [0,1], [2], [3,4], [5].
第三次结交发生在 timestamp = 20190107，2 和 3 成为好友，社交朋友圈如下 [0,1], [2,3,4], [5].
第四次结交发生在 timestamp = 20190211，1 和 5 成为好友，社交朋友圈如下 [0,1,5], [2,3,4].
第五次结交发生在 timestamp = 20190224，2 和 4 已经是好友了。
第六次结交发生在 timestamp = 20190301，0 和 3 成为好友，大家都互相熟识了。
</pre>

<p><strong class="example">示例 2:</strong></p>

<pre>
<strong>输入:</strong> logs = [[0,2,0],[1,0,1],[3,0,3],[4,1,2],[7,3,1]], n = 4
<strong>输出:</strong> 3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= logs.length &lt;= 10<sup>4</sup></code></li>
	<li><code>logs[i].length == 3</code></li>
	<li><code>0 &lt;= timestamp<sub>i</sub>&nbsp;&lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub>&nbsp;&lt;= n - 1</code></li>
	<li><code>x<sub>i</sub>&nbsp;!= y<sub>i</sub></code></li>
	<li><code>timestamp<sub>i</sub></code>&nbsp;中的所有时间戳&nbsp;<strong>均</strong><strong>不同</strong></li>
	<li>所有的对 <code>(xi, yi)</code> 在输入中最多出现一次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 并查集**

我们将所有的日志按照时间戳从小到大排序，然后遍历排序后的日志，利用并查集判断当前日志中的两个人是否已经是朋友，如果不是朋友，则将两个人合并成一个朋友圈，直到所有人都在一个朋友圈中，返回当前日志的时间戳。

如果遍历完所有日志，还没有所有人都在一个朋友圈中，则返回 $-1$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为日志的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
