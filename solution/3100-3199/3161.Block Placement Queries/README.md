---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3161.Block%20Placement%20Queries/README.md
rating: 2513
source: 第 131 场双周赛 Q4
tags:
    - 树状数组
    - 线段树
    - 数组
    - 二分查找
    - 有序集合
---

<!-- problem:start -->

# [3161. 物块放置查询](https://leetcode.cn/problems/block-placement-queries)

[English Version](/solution/3100-3199/3161.Block%20Placement%20Queries/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有一条无限长的数轴，原点在 0 处，沿着 x 轴 <strong>正</strong>&nbsp;方向无限延伸。</p>

<p>给你一个二维数组&nbsp;<code>queries</code>&nbsp;，它包含两种操作：</p>

<ol>
	<li>操作类型 1 ：<code>queries[i] = [1, x]</code>&nbsp;。在距离原点 <code>x</code>&nbsp;处建一个障碍物。数据保证当操作执行的时候，位置 <code>x</code>&nbsp;处 <strong>没有</strong>&nbsp;任何障碍物。</li>
	<li>操作类型 2 ：<code>queries[i] = [2, x, sz]</code>&nbsp;。判断在数轴范围&nbsp;<code>[0, x]</code>&nbsp;内是否可以放置一个长度为&nbsp;<code>sz</code>&nbsp;的物块，这个物块需要&nbsp;<strong>完全</strong>&nbsp;放置在范围&nbsp;<code>[0, x]</code>&nbsp;内。如果物块与任何障碍物有重合，那么这个物块&nbsp;<strong>不能</strong>&nbsp;被放置，但物块可以与障碍物刚好接触。注意，你只是进行查询，并&nbsp;<strong>不是</strong>&nbsp;真的放置这个物块。每个查询都是相互独立的。</li>
</ol>

<p>请你返回一个 boolean 数组<code>results</code>&nbsp;，如果第&nbsp;<code>i</code> 个操作类型 2 的操作你可以放置物块，那么&nbsp;<code>results[i]</code>&nbsp;为&nbsp;<code>true</code>&nbsp;，否则为 <code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>queries = [[1,2],[2,3,3],[2,3,1],[2,2,2]]</span></p>

<p><span class="example-io"><b>输出：</b>[false,true,true]</span></p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3161.Block%20Placement%20Queries/images/example0block.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 309px; height: 129px;" /></strong></p>

<p>查询 0 ，在&nbsp;<code>x = 2</code>&nbsp;处放置一个障碍物。在&nbsp;<code>x = 3</code>&nbsp;之前任何大小不超过 2 的物块都可以被放置。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>queries = </span>[[1,7],[2,7,6],[1,2],[2,7,5],[2,7,6]]<!-- notionvc: 4a471445-5af1-4d72-b11b-94d351a2c8e9 --></p>

<p><b>输出：</b>[true,true,false]</p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3161.Block%20Placement%20Queries/images/example1block.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 310px; height: 130px;" /></strong></p>

<ul>
	<li>查询 0 在&nbsp;<code>x = 7</code>&nbsp;处放置一个障碍物。在&nbsp;<code>x = 7</code>&nbsp;之前任何大小不超过 7 的物块都可以被放置。</li>
	<li>查询 2 在&nbsp;<code>x = 2</code>&nbsp;处放置一个障碍物。现在，在&nbsp;<code>x = 7</code>&nbsp;之前任何大小不超过 5 的物块可以被放置，<code>x = 2</code>&nbsp;之前任何大小不超过 2 的物块可以被放置。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= queries.length &lt;= 15 * 10<sup>4</sup></code></li>
	<li><code>2 &lt;= queries[i].length &lt;= 3</code></li>
	<li><code>1 &lt;= queries[i][0] &lt;= 2</code></li>
	<li><code>1 &lt;= x, sz &lt;= min(5 * 10<sup>4</sup>, 3 * queries.length)</code></li>
	<li>输入保证操作 1 中，<code>x</code>&nbsp;处不会有障碍物。</li>
	<li>输入保证至少有一个操作类型 2 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：树状数组 + 有序集合

障碍物只会增加，因此可以离线倒序处理询问，将「加入障碍物」转化为「删除障碍物」。删除后相邻空隙只会变大，而树状数组可以高效维护前缀最大值。

我们先将所有障碍物放入有序集合，并在两端加入哨兵 $0$ 和 $m+1$（$m$ 为出现过的最大坐标）。对每一对相邻障碍物 $x_1, x_2$，在树状数组的 $x_2$ 位置更新空隙长度 $x_2 - x_1$。

然后倒序遍历询问：

- 类型 $2$：找到不超过 $x$ 的最后一个障碍物 $pre$。若 $[0, pre]$ 内的最大空隙或尾部落在 $(pre, x]$ 的空隙不小于 $sz$，则可以放置物块。
- 类型 $1$：删除障碍物 $x$，并将后继 $nxt$ 处的空隙更新为 $nxt - pre$。

时间复杂度 $O(q \times \log m)$，空间复杂度 $O(m)$。其中 $q$ 是询问数，$m$ 是坐标的最大值。

<!-- tabs:start -->

#### Python3

```python
class BinaryIndexedTree:
    def __init__(self, n: int):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, v: int):
        while x <= self.n:
            self.c[x] = max(self.c[x], v)
            x += x & -x

    def query(self, x: int) -> int:
        mx = 0
        while x:
            mx = max(mx, self.c[x])
            x -= x & -x
        return mx


class Solution:
    def getResults(self, queries: List[List[int]]) -> List[bool]:
        m = max(q[1] for q in queries)
        sl = SortedList([0, m + 1])
        for q in queries:
            if q[0] == 1:
                sl.add(q[1])
        tree = BinaryIndexedTree(m + 1)
        for x1, x2 in pairwise(sl):
            tree.update(x2, x2 - x1)
        ans = []
        for q in reversed(queries):
            x = q[1]
            if q[0] == 1:
                i = sl.index(x)
                tree.update(sl[i + 1], sl[i + 1] - sl[i - 1])
                sl.remove(x)
            else:
                i = sl.bisect_right(x)
                pre = sl[i - 1]
                ans.append(tree.query(pre) >= q[2] or x - pre >= q[2])
        return ans[::-1]
```

#### Java

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] = Math.max(c[x], v);
            x += x & -x;
        }
    }

    public int query(int x) {
        int mx = 0;
        while (x > 0) {
            mx = Math.max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
}

class Solution {
    public List<Boolean> getResults(int[][] queries) {
        int m = 0;
        for (int[] q : queries) {
            m = Math.max(m, q[1]);
        }
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(0);
        ts.add(m + 1);
        for (int[] q : queries) {
            if (q[0] == 1) {
                ts.add(q[1]);
            }
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m + 1);
        int pre = 0;
        for (int x : ts) {
            if (x > 0) {
                tree.update(x, x - pre);
            }
            pre = x;
        }
        List<Boolean> ans = new ArrayList<>();
        for (int i = queries.length - 1; i >= 0; --i) {
            int[] q = queries[i];
            int x = q[1];
            if (q[0] == 1) {
                int nxt = ts.higher(x);
                tree.update(nxt, nxt - ts.lower(x));
                ts.remove(x);
            } else {
                int p = ts.floor(x);
                ans.add(tree.query(p) >= q[2] || x - p >= q[2]);
            }
        }
        Collections.reverse(ans);
        return ans;
    }
}
```

#### C++

```cpp
class BinaryIndexedTree {
private:
    int n;
    vector<int> c;

public:
    BinaryIndexedTree(int n) {
        this->n = n;
        c.resize(n + 1);
    }

    void update(int x, int v) {
        while (x <= n) {
            c[x] = max(c[x], v);
            x += x & -x;
        }
    }

    int query(int x) {
        int mx = 0;
        while (x > 0) {
            mx = max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
};

class Solution {
public:
    vector<bool> getResults(vector<vector<int>>& queries) {
        int m = 0;
        for (auto& q : queries) {
            m = max(m, q[1]);
        }
        set<int> ts{0, m + 1};
        for (auto& q : queries) {
            if (q[0] == 1) {
                ts.insert(q[1]);
            }
        }
        BinaryIndexedTree tree(m + 1);
        int pre = 0;
        for (int x : ts) {
            if (x) {
                tree.update(x, x - pre);
            }
            pre = x;
        }
        vector<bool> ans;
        for (int i = queries.size() - 1; i >= 0; --i) {
            int x = queries[i][1];
            if (queries[i][0] == 1) {
                auto it = ts.find(x);
                tree.update(*next(it), *next(it) - *prev(it));
                ts.erase(it);
            } else {
                auto it = prev(ts.upper_bound(x));
                ans.push_back(tree.query(*it) >= queries[i][2] || x - *it >= queries[i][2]);
            }
        }
        ranges::reverse(ans);
        return ans;
    }
};
```

#### Go

```go
func getResults(queries [][]int) []bool {
	m := 0
	for _, q := range queries {
		m = max(m, q[1])
	}
	st := redblacktree.New[int, struct{}]()
	st.Put(0, struct{}{})
	st.Put(m+1, struct{}{})
	for _, q := range queries {
		if q[0] == 1 {
			st.Put(q[1], struct{}{})
		}
	}
	tree := newBinaryIndexedTree(m + 1)
	it := st.Iterator()
	it.Next()
	pre := it.Key()
	for it.Next() {
		x := it.Key()
		tree.update(x, x-pre)
		pre = x
	}
	ans := []bool{}
	for i := len(queries) - 1; i >= 0; i-- {
		q := queries[i]
		x := q[1]
		if q[0] == 1 {
			nxt, _ := st.Ceiling(x + 1)
			p, _ := st.Floor(x - 1)
			st.Remove(x)
			tree.update(nxt.Key, nxt.Key-p.Key)
		} else {
			node, _ := st.Floor(x)
			p := node.Key
			ans = append(ans, tree.query(p) >= q[2] || x-p >= q[2])
		}
	}
	slices.Reverse(ans)
	return ans
}

type binaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *binaryIndexedTree {
	return &binaryIndexedTree{n: n, c: make([]int, n+1)}
}

func (t *binaryIndexedTree) update(x, v int) {
	for x <= t.n {
		t.c[x] = max(t.c[x], v)
		x += x & -x
	}
}

func (t *binaryIndexedTree) query(x int) int {
	mx := 0
	for x > 0 {
		mx = max(mx, t.c[x])
		x -= x & -x
	}
	return mx
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
