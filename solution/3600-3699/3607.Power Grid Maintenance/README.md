---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3607.Power%20Grid%20Maintenance/README.md
rating: 1699
source: 第 457 场周赛 Q2
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 图
    - 数组
    - 哈希表
    - 有序集合
    - 堆（优先队列）
---

<!-- problem:start -->

# [3607. 电网维护](https://leetcode.cn/problems/power-grid-maintenance)

[English Version](/solution/3600-3699/3607.Power%20Grid%20Maintenance/README_EN.md)

## 题目描述

<!-- description:start -->

<p data-end="401" data-start="120">给你一个整数 <code data-end="194" data-start="191">c</code>，表示 <code data-end="211" data-start="208">c</code> 个电站，每个电站有一个唯一标识符 <code>id</code>，从 1 到 <code>c</code>&nbsp;编号。</p>

<p data-end="401" data-start="120">这些电站通过 <code data-end="295" data-start="292">n</code> 条&nbsp;<strong>双向&nbsp;</strong>电缆互相连接，表示为一个二维数组 <code data-end="357" data-start="344">connections</code>，其中每个元素 <code data-end="430" data-start="405">connections[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示电站 <code>u<sub>i</sub></code> 和电站 <code>v<sub>i</sub></code> 之间的连接。直接或间接连接的电站组成了一个&nbsp;<strong>电网&nbsp;</strong>。</p>

<p data-end="626" data-start="586">最初，<strong>所有&nbsp;</strong>电站均处于在线（正常运行）状态。</p>

<p data-end="720" data-start="628">另给你一个二维数组 <code data-end="667" data-start="658">queries</code>，其中每个查询属于以下&nbsp;<strong>两种类型之一&nbsp;</strong>：</p>

<ul data-end="995" data-start="722">
	<li data-end="921" data-start="722">
	<p data-end="921" data-start="724"><code data-end="732" data-start="724">[1, x]</code>：请求对电站 <code data-end="782" data-start="779">x</code> 进行维护检查。如果电站 <code>x</code> 在线，则它自行解决检查。如果电站 <code>x</code> 已离线，则检查由与 <code>x</code> 同一&nbsp;<strong>电网&nbsp;</strong>中&nbsp;<strong>编号最小&nbsp;</strong>的在线电站解决。如果该电网中&nbsp;<strong>不存在&nbsp;</strong>任何&nbsp;<strong>在线&nbsp;</strong>电站，则返回 -1。</p>
	</li>
	<li data-end="995" data-start="923">
	<p data-end="995" data-start="925"><code data-end="933" data-start="925">[2, x]</code>：电站 <code data-end="946" data-start="943">x</code> 离线（即变为非运行状态）。</p>
	</li>
</ul>

<p data-end="1106" data-start="997">返回一个整数数组，表示按照查询中出现的顺序，所有类型为 <code data-end="1080" data-start="1072">[1, x]</code> 的查询结果。</p>

<p data-end="1106" data-start="997"><strong>注意：</strong>电网的结构是固定的；离线（非运行）的节点仍然属于其所在的电网，且离线操作不会改变电网的连接性。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">c = 5, connections = [[1,2],[2,3],[3,4],[4,5]], queries = [[1,3],[2,1],[1,1],[2,2],[1,2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[3,2,3]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3607.Power%20Grid%20Maintenance/images/powergrid.jpg" style="width: 361px; height: 42px;" /></p>

<ul>
	<li data-end="223" data-start="143">最初，所有电站 <code>{1, 2, 3, 4, 5}</code> 都在线，并组成一个电网。</li>
	<li data-end="322" data-start="226">查询 <code>[1,3]</code>：电站 3 在线，因此维护检查由电站 3 自行解决。</li>
	<li data-end="402" data-start="325">查询 <code>[2,1]</code>：电站 1 离线。剩余在线电站为 <code>{2, 3, 4, 5}</code>。</li>
	<li data-end="557" data-start="405">查询 <code>[1,1]</code>：电站 1 离线，因此检查由电网中编号最小的在线电站解决，即电站 2。</li>
	<li data-end="641" data-start="560">查询 <code>[2,2]</code>：电站 2 离线。剩余在线电站为 <code>{3, 4, 5}</code>。</li>
	<li data-end="800" data-start="644">查询 <code>[1,2]</code>：电站 2 离线，因此检查由电网中编号最小的在线电站解决，即电站 3。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">c = 3, connections = [], queries = [[1,1],[2,1],[1,1]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,-1]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li data-end="976" data-start="909">没有连接，因此每个电站是一个独立的电网。</li>
	<li data-end="1096" data-start="979">查询 <code>[1,1]</code>：电站 1 在线，且属于其独立电网，因此维护检查由电站 1 自行解决。</li>
	<li data-end="1135" data-start="1099">查询 <code>[2,1]</code>：电站 1 离线。</li>
	<li data-end="1237" data-start="1138">查询 <code>[1,1]</code>：电站 1 离线，且其电网中没有其他电站，因此结果为 -1。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li data-end="155" data-start="139"><code>1 &lt;= c &lt;= 10<sup>5</sup></code></li>
	<li data-end="213" data-start="158"><code>0 &lt;= n == connections.length &lt;= min(10<sup>5</sup>, c * (c - 1) / 2)</code></li>
	<li data-end="244" data-start="216"><code>connections[i].length == 2</code></li>
	<li data-end="295" data-start="247"><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= c</code></li>
	<li data-end="338" data-start="298"><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li data-end="374" data-start="341"><code>1 &lt;= queries.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li data-end="401" data-start="377"><code>queries[i].length == 2</code></li>
	<li data-end="436" data-start="404"><code>queries[i][0]</code> 为 1 或 2。</li>
	<li data-end="462" data-start="439"><code>1 &lt;= queries[i][1] &lt;= c</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：并查集 + 有序集合

我们可以使用并查集（Union-Find）来维护电站之间的连接关系，从而确定每个电站所属的电网。对于每个电网，我们使用有序集合（如 Python 中的 `SortedList`、Java 中的 `TreeSet` 或 C++ 中的 `std::set`）来存储该电网中所有在线的电站编号，以便能够高效地查询和删除电站。

具体步骤如下：

1. 初始化并查集，处理所有连接关系，将连接的电站合并到同一个集合中。
2. 为每个电网创建一个有序集合，初始时将所有电站编号加入对应电网的集合中。
3. 遍历查询列表：
    - 对于查询 $[1, x]$，首先找到电站 $x$ 所属的电网根节点，然后检查该电网的有序集合：
        - 如果电站 $x$ 在线（存在于集合中），则返回 $x$。
        - 否则，返回集合中的最小编号电站（如果集合非空），否则返回 -1。
    - 对于查询 $[2, x]$，找到电站 $x$ 所属的电网根节点，并将电站 $x$ 从该电网的有序集合中删除，表示该电站离线。
4. 最后，返回所有类型为 $[1, x]$ 的查询结果。

时间复杂度 $O((c + n + q) \log c)$，空间复杂度 $O(c)$。其中 $c$ 是电站数量，而 $n$ 和 $q$ 分别是连接数量和查询数量。

<!-- tabs:start -->

#### Python3

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
        if pa == pb:
            return False
        if self.size[pa] > self.size[pb]:
            self.p[pb] = pa
            self.size[pa] += self.size[pb]
        else:
            self.p[pa] = pb
            self.size[pb] += self.size[pa]
        return True


class Solution:
    def processQueries(
        self, c: int, connections: List[List[int]], queries: List[List[int]]
    ) -> List[int]:
        uf = UnionFind(c + 1)
        for u, v in connections:
            uf.union(u, v)
        st = [SortedList() for _ in range(c + 1)]
        for i in range(1, c + 1):
            st[uf.find(i)].add(i)
        ans = []
        for a, x in queries:
            root = uf.find(x)
            if a == 1:
                if x in st[root]:
                    ans.append(x)
                elif len(st[root]):
                    ans.append(st[root][0])
                else:
                    ans.append(-1)
            else:
                st[root].discard(x)
        return ans
```

#### Java

```java
class UnionFind {
    private final int[] p;
    private final int[] size;

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

    public boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }
}

class Solution {
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        UnionFind uf = new UnionFind(c + 1);
        for (int[] e : connections) {
            uf.union(e[0], e[1]);
        }

        TreeSet<Integer>[] st = new TreeSet[c + 1];
        Arrays.setAll(st, k -> new TreeSet<>());
        for (int i = 1; i <= c; i++) {
            int root = uf.find(i);
            st[root].add(i);
        }

        List<Integer> ans = new ArrayList<>();
        for (int[] q : queries) {
            int a = q[0], x = q[1];
            int root = uf.find(x);

            if (a == 1) {
                if (st[root].contains(x)) {
                    ans.add(x);
                } else if (!st[root].isEmpty()) {
                    ans.add(st[root].first());
                } else {
                    ans.add(-1);
                }
            } else {
                st[root].remove(x);
            }
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
```

#### C++

```cpp
class UnionFind {
public:
    UnionFind(int n) {
        p = vector<int>(n);
        size = vector<int>(n, 1);
        iota(p.begin(), p.end(), 0);
    }

    bool unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
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
    vector<int> processQueries(int c, vector<vector<int>>& connections, vector<vector<int>>& queries) {
        UnionFind uf(c + 1);
        for (auto& e : connections) {
            uf.unite(e[0], e[1]);
        }

        vector<set<int>> st(c + 1);
        for (int i = 1; i <= c; i++) {
            st[uf.find(i)].insert(i);
        }

        vector<int> ans;
        for (auto& q : queries) {
            int a = q[0], x = q[1];
            int root = uf.find(x);
            if (a == 1) {
                if (st[root].count(x)) {
                    ans.push_back(x);
                } else if (!st[root].empty()) {
                    ans.push_back(*st[root].begin());
                } else {
                    ans.push_back(-1);
                }
            } else {
                st[root].erase(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
