# [2077. 殊途同归](https://leetcode.cn/problems/paths-in-maze-that-lead-to-same-room)

[English Version](/solution/2000-2099/2077.Paths%20in%20Maze%20That%20Lead%20to%20Same%20Room/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>迷宫由 <code>n</code> 个从 <code>1</code> 到 <code>n</code> 的房间组成，有些房间由走廊连接。给定一个二维整数数组 <code>corridors</code>，其中 <code>corridors[i] = [room1<sub>i</sub>, room2<sub>i</sub>]</code>&nbsp;表示有一条走廊连接 <code>room1<sub>i</sub></code> 和<code>room2<sub>i</sub></code>，允许迷宫中的一个人从 <code>room1<sub>i</sub></code> 到 <code>room1<sub>i</sub></code> ，<strong>反之亦然</strong>。</p>

<p>迷宫的设计者想知道迷宫有多让人困惑。迷宫的&nbsp;<strong>混乱分数&nbsp;</strong>是&nbsp;<strong>长度为 3</strong> 的不同的环的数量。</p>

<ul>
	<li>例如, <code>1 → 2 → 3 → 1</code>&nbsp;是长度为 3 的环, 但&nbsp;<code>1 → 2 → 3 → 4</code> 和&nbsp;<code>1 → 2 → 3 → 2 → 1</code> 不是。</li>
</ul>

<p>如果在第一个环中访问的一个或多个房间&nbsp;<strong>不在&nbsp;</strong>第二个环中，则认为两个环是&nbsp;<strong>不同&nbsp;</strong>的。</p>

<p data-group="1-1">返回<em>迷宫的混乱分数</em>。</p>

<p><strong class="example">示例 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2077.Paths%20in%20Maze%20That%20Lead%20to%20Same%20Room/images/image-20211114164827-1.png" style="width: 440px; height: 350px;" />
<pre>
<strong>输入:</strong> n = 5, corridors = [[1,2],[5,2],[4,1],[2,4],[3,1],[3,4]]
<strong>输出:</strong> 2
<strong>解释:</strong>
一个长度为 3 的环为 4→1→3→4，用红色表示。
注意，这是与 3→4→1→3 或 1→3→4→1 相同的环，因为房间是相同的。
另一个长度为 3 的环为 1→2→4→1，用蓝色表示。
因此，有两个长度为 3 的不同的环。
</pre>

<p><strong class="example">示例&nbsp;2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2077.Paths%20in%20Maze%20That%20Lead%20to%20Same%20Room/images/image-20211114164851-2.png" style="width: 329px; height: 250px;" />
<pre>
<strong>输入:</strong> n = 4, corridors = [[1,2],[3,4]]
<strong>输出:</strong> 0
<strong>解释:</strong>
没有长度为 3 的环。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= corridors.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>corridors[i].length == 2</code></li>
	<li><code>1 &lt;= room1<sub>i</sub>, room2<sub>i</sub> &lt;= n</code></li>
	<li><code>room1<sub>i</sub> != room2<sub>i</sub></code></li>
	<li>
	<p data-group="1-1">没有重复的走廊。</p>
	</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

长度为 `3` 的环，由三个顶点、三条边组成。我们假设三个顶点分别为 `a`, `b`, `c`。

那么一定存在 `c <=> a`，`c <=> b` 以及 `a <=> b`，即环中的每个点都与其他两点直接相连。我们可以用哈希表来存放每个点的相邻点。

由于环的长度为 `3`，每个相同的环会被重复统计 `3` 次，因此答案需除以 `3`。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfPaths(self, n: int, corridors: List[List[int]]) -> int:
        g = defaultdict(set)
        for a, b in corridors:
            g[a].add(b)
            g[b].add(a)
        ans = 0
        for i in range(1, n + 1):
            for j, k in combinations(g[i], 2):
                if j in g[k]:
                    ans += 1
        return ans // 3
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numberOfPaths(int n, int[][] corridors) {
        Set<Integer>[] g = new Set[n + 1];
        for (int i = 0; i <= n; ++i) {
            g[i] = new HashSet<>();
        }
        for (var c : corridors) {
            int a = c[0], b = c[1];
            g[a].add(b);
            g[b].add(a);
        }
        int ans = 0;
        for (int c = 1; c <= n; ++c) {
            var nxt = new ArrayList<>(g[c]);
            int m = nxt.size();
            for (int i = 0; i < m; ++i) {
                for (int j = i + 1; j < m; ++j) {
                    int a = nxt.get(i), b = nxt.get(j);
                    if (g[b].contains(a)) {
                        ++ans;
                    }
                }
            }
        }
        return ans / 3;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfPaths(int n, vector<vector<int>>& corridors) {
        vector<unordered_set<int>> g(n + 1);
        for (auto& c : corridors) {
            int a = c[0], b = c[1];
            g[a].insert(b);
            g[b].insert(a);
        }
        int ans = 0;
        for (int c = 1; c <= n; ++c) {
            vector<int> nxt;
            nxt.assign(g[c].begin(), g[c].end());
            int m = nxt.size();
            for (int i = 0; i < m; ++i) {
                for (int j = i + 1; j < m; ++j) {
                    int a = nxt[i], b = nxt[j];
                    ans += g[b].count(a);
                }
            }
        }
        return ans / 3;
    }
};
```

### **Go**

```go
func numberOfPaths(n int, corridors [][]int) int {
	g := make([]map[int]bool, n+1)
	for i := range g {
		g[i] = make(map[int]bool)
	}
	for _, c := range corridors {
		a, b := c[0], c[1]
		g[a][b] = true
		g[b][a] = true
	}
	ans := 0
	for c := 1; c <= n; c++ {
		nxt := []int{}
		for v := range g[c] {
			nxt = append(nxt, v)
		}
		m := len(nxt)
		for i := 0; i < m; i++ {
			for j := i + 1; j < m; j++ {
				a, b := nxt[i], nxt[j]
				if g[b][a] {
					ans++
				}
			}
		}
	}
	return ans / 3
}
```

### **...**

```

```

<!-- tabs:end -->
