# [1101. 彼此熟识的最早时间](https://leetcode.cn/problems/the-earliest-moment-when-everyone-become-friends)

[English Version](/solution/1100-1199/1101.The%20Earliest%20Moment%20When%20Everyone%20Become%20Friends/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个社交圈子当中，有&nbsp;<code>n</code>&nbsp;个人。每个人都有一个从&nbsp;<code>0</code> 到&nbsp;<code>n - 1</code>&nbsp;的唯一编号。我们有一份日志列表&nbsp;<code>logs</code>，其中&nbsp;<code>logs[i] = [timestamp<sub>i</sub>, x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;表示 <code>x<sub>i</sub></code>&nbsp;和&nbsp;<code>y<sub>i</sub></code>&nbsp;将在同一时间&nbsp;<code>timestamp<sub>i</sub></code><sub>&nbsp;</sub>成为朋友。</p>

<p>友谊是 <strong>相互</strong> 的。也就是说，如果 <code>a</code> 和 <code>b</code> 是朋友，那么&nbsp;<code>b</code>&nbsp;和 <code>a</code> 也是朋友。同样，如果 <code>a</code> 和 <code>b</code> 是朋友，或者&nbsp;<code>a</code> 是 <code>b</code> 朋友的朋友 ，那么 <code>a</code> 和 <code>b</code> 是熟识友。</p>

<p>返回圈子里所有人之间都熟识的最早时间。如果找不到最早时间，就返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

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

<p><strong>示例 2:</strong></p>

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

对于本题，先对日志列表按照时间升序排列。然后遍历日志列表，每条日志对应的两个人合并为同一个连通分量。当连通分量个数减为 1 时，说明圈子里所有人之间都熟识了，返回当前遍历到的时间。否则遍历结束返回 -1。

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
        logs.sort()
        for t, a, b in logs:
            if find(a) == find(b):
                continue
            p[find(a)] = find(b)
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
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        Arrays.sort(logs, Comparator.comparingInt(a -> a[0]));
        for (int[] log : logs) {
            int t = log[0], a = log[1], b = log[2];
            if (find(a) == find(b)) {
                continue;
            }
            p[find(a)] = find(b);
            --n;
            if (n == 1) {
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

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    int earliestAcq(vector<vector<int>>& logs, int n) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        sort(logs.begin(), logs.end());
        for (auto& log : logs) {
            int t = log[0], a = log[1], b = log[2];
            if (find(a) == find(b)) continue;
            p[find(a)] = find(b);
            if (--n == 1) return t;
        }
        return -1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func earliestAcq(logs [][]int, n int) int {
	p := make([]int, n)
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
	sort.Slice(logs, func(i, j int) bool {
		return logs[i][0] < logs[j][0]
	})
	for _, log := range logs {
		t, a, b := log[0], log[1], log[2]
		if find(a) == find(b) {
			continue
		}
		p[find(a)] = find(b)
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
