# [2286. 以组为单位订音乐会的门票](https://leetcode.cn/problems/booking-concert-tickets-in-groups)

[English Version](/solution/2200-2299/2286.Booking%20Concert%20Tickets%20in%20Groups/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个音乐会总共有&nbsp;<code>n</code>&nbsp;排座位，编号从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;，每一排有&nbsp;<code>m</code>&nbsp;个座椅，编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>m - 1</code>&nbsp;。你需要设计一个买票系统，针对以下情况进行座位安排：</p>

<ul>
	<li>同一组的 <code>k</code>&nbsp;位观众坐在<strong> 同一排座位，且座位连续 </strong>。</li>
	<li><code>k</code>&nbsp;位观众中 <strong>每一位</strong>&nbsp;都有座位坐，但他们 <strong>不一定</strong>&nbsp;坐在一起。</li>
</ul>

<p>由于观众非常挑剔，所以：</p>

<ul>
	<li>只有当一个组里所有成员座位的排数都 <strong>小于等于</strong>&nbsp;<code>maxRow</code>&nbsp;，这个组才能订座位。每一组的&nbsp;<code>maxRow</code>&nbsp;可能 <strong>不同</strong>&nbsp;。</li>
	<li>如果有多排座位可以选择，优先选择 <strong>最小</strong>&nbsp;的排数。如果同一排中有多个座位可以坐，优先选择号码 <strong>最小</strong>&nbsp;的。</li>
</ul>

<p>请你实现&nbsp;<code>BookMyShow</code>&nbsp;类：</p>

<ul>
	<li><code>BookMyShow(int n, int m)</code>&nbsp;，初始化对象，<code>n</code>&nbsp;是排数，<code>m</code>&nbsp;是每一排的座位数。</li>
	<li><code>int[] gather(int k, int maxRow)</code>&nbsp;返回长度为 <code>2</code>&nbsp;的数组，表示 <code>k</code>&nbsp;个成员中 <strong>第一个座位</strong>&nbsp;的排数和座位编号，这 <code>k</code>&nbsp;位成员必须坐在 <strong>同一排座位，且座位连续 </strong>。换言之，返回最小可能的&nbsp;<code>r</code> 和&nbsp;<code>c</code>&nbsp;满足第&nbsp;<code>r</code>&nbsp;排中&nbsp;<code>[c, c + k - 1]</code>&nbsp;的座位都是空的，且&nbsp;<code>r &lt;= maxRow</code>&nbsp;。如果&nbsp;<strong>无法</strong>&nbsp;安排座位，返回&nbsp;<code>[]</code>&nbsp;。</li>
	<li><code>boolean scatter(int k, int maxRow)</code>&nbsp;如果组里所有&nbsp;<code>k</code>&nbsp;个成员&nbsp;<strong>不一定</strong>&nbsp;要坐在一起的前提下，都能在第&nbsp;<code>0</code> 排到第&nbsp;<code>maxRow</code>&nbsp;排之间找到座位，那么请返回&nbsp;<code>true</code>&nbsp;。这种情况下，每个成员都优先找排数&nbsp;<strong>最小</strong>&nbsp;，然后是座位编号最小的座位。如果不能安排所有&nbsp;<code>k</code>&nbsp;个成员的座位，请返回&nbsp;<code>false</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["BookMyShow", "gather", "gather", "scatter", "scatter"]
[[2, 5], [4, 0], [2, 0], [5, 1], [5, 1]]
<strong>输出：</strong>
[null, [0, 0], [], true, false]

<strong>解释：</strong>
BookMyShow bms = new BookMyShow(2, 5); // 总共有 2 排，每排 5 个座位。
bms.gather(4, 0); // 返回 [0, 0]
                  // 这一组安排第 0 排 [0, 3] 的座位。
bms.gather(2, 0); // 返回 []
                  // 第 0 排只剩下 1 个座位。
                  // 所以无法安排 2 个连续座位。
bms.scatter(5, 1); // 返回 True
                   // 这一组安排第 0 排第 4 个座位和第 1 排 [0, 3] 的座位。
bms.scatter(5, 1); // 返回 False
                   // 总共只剩下 2 个座位。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m, k &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= maxRow &lt;= n - 1</code></li>
	<li><code>gather</code> 和&nbsp;<code>scatter</code>&nbsp;<strong>总</strong> 调用次数不超过&nbsp;<code>5 * 10<sup>4</sup></code> 次。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：线段树**

分析题意我们得知：

-   对于 `gather(k, maxRow)` 操作，要求 $k$ 个人坐在同一行并且座位连续，也就是说，我们要找到最小的行，满足该行的剩余座位大于等于 $k$。
-   对于 `scatter(k, maxRow)` 操作，只需要找到 $k$ 个座位就行，但是要求这 $k$ 个座位的行数尽可能小，因此，我们要找到第一个满足剩余座位数大于 $0$ 的行，进行座位分配，然后继续往后查找。

我们可以用线段树来实现。线段树每个节点的信息有：

-   `l`：节点对应的区间左端点
-   `r`：节点对应的区间右端点
-   `s`：节点对应的区间总的剩余座位数
-   `mx`：节点对应的区间最大剩余座位数

注意，线段树节点区间的下标从 $1$ 开始。

线段树的操作有：

-   `build(u, l, r)`：建立节点 $u$，对应区间 $[l, r]$，并递归建立其左右子节点。
-   `modify(u, x, v)`：从节点 $u$ 开始，找到对应区间 $[l, r]$ 中的第一个满足 $l = r = x$ 的节点，将该节点的 `s` 和 `mx` 修改为 $v$，并向上更新。
-   `query_sum(u, l, r)`：从节点 $u$ 开始，统计对应区间 $[l, r]$ 中的 `s` 之和。
-   `query_idx(u, l, r, k)`：从节点 $u$ 开始，找到对应区间 $[l, r]$ 中的第一个满足 `mx` 大于等于 $k$ 的节点，返回该节点的 `l`。查找时，我们从最大的区间 $[1, maxRow]$ 开始，由于我们要找最左边的满足 `mx` 大于等于 $k$ 的节点。因此，对于当前区间，我们判断前半部分区间的 `mx` 是否符合要求，是则说明答案就在前半部分区间，递归查找即可。否则说明答案在后半部分区间，递归查找后半部分区间。
-   `pushup(u)`：利用 $u$ 的子节点信息更新当前 $u$ 的信息。

对于 `gather(k, maxRow)` 操作，我们先用 `query_idx(1, 1, n, k)` 找到第一个满足剩余座位数大于等于 $k$ 的行，记为 $i$。然后我们用 `query_sum(1, i, i)` 得到该行的剩余座位数，记为 $s$。接下来，我们用 `modify(1, i, s - k)` 将该行的剩余座位数修改为 $s - k$，并向上更新。最后，我们返回 $[i - 1, m - s]$ 即可。

对于 `scatter(k, maxRow)` 操作，我们先用 `query_sum(1, 1, maxRow)` 统计前 $maxRow$ 行的剩余座位数，记为 $s$。如果 $s \lt k$，说明没有足够的座位，返回 `false`；否则，我们用 `query_idx(1, 1, maxRow, 1)` 找到第一个满足剩余座位数大于等于 $1$ 的行，记为 $i$。从该行开始，每次用 `query_sum(1, i, i)` 得到该行的剩余座位数，记为 $s_i$。如果 $s_i \geq k$，我们直接用 `modify(1, i, s_i - k)` 将该行的剩余座位数修改为 $s_i - k$，并向上更新，然后返回 `true`。否则，我们更新 $k = k - s_i$，然后将该行的剩余座位数修改为 $0$，并向上更新。最后，我们返回 `true`。

时间复杂度：

-   初始化的时间复杂度为 $O(n)$。
-   `gather(k, maxRow)` 的时间复杂度为 $O(\log n)$。
-   `scatter(k, maxRow)` 的时间复杂度为 $O((n + q) \times \log n)$。

整体时间复杂度为 $O(n + q \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 和 $q$ 分别为行数和操作数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Node:
    def __init__(self):
        self.l = self.r = 0
        self.s = self.mx = 0


class SegmentTree:
    def __init__(self, n, m):
        self.m = m
        self.tr = [Node() for _ in range(n << 2)]
        self.build(1, 1, n)

    def build(self, u, l, r):
        self.tr[u].l, self.tr[u].r = l, r
        if l == r:
            self.tr[u].s = self.tr[u].mx = self.m
            return
        mid = (l + r) >> 1
        self.build(u << 1, l, mid)
        self.build(u << 1 | 1, mid + 1, r)
        self.pushup(u)

    def modify(self, u, x, v):
        if self.tr[u].l == x and self.tr[u].r == x:
            self.tr[u].s = self.tr[u].mx = v
            return
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        if x <= mid:
            self.modify(u << 1, x, v)
        else:
            self.modify(u << 1 | 1, x, v)
        self.pushup(u)

    def query_sum(self, u, l, r):
        if self.tr[u].l >= l and self.tr[u].r <= r:
            return self.tr[u].s
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        v = 0
        if l <= mid:
            v += self.query_sum(u << 1, l, r)
        if r > mid:
            v += self.query_sum(u << 1 | 1, l, r)
        return v

    def query_idx(self, u, l, r, k):
        if self.tr[u].mx < k:
            return 0
        if self.tr[u].l == self.tr[u].r:
            return self.tr[u].l
        mid = (self.tr[u].l + self.tr[u].r) >> 1
        if self.tr[u << 1].mx >= k:
            return self.query_idx(u << 1, l, r, k)
        if r > mid:
            return self.query_idx(u << 1 | 1, l, r, k)
        return 0

    def pushup(self, u):
        self.tr[u].s = self.tr[u << 1].s + self.tr[u << 1 | 1].s
        self.tr[u].mx = max(self.tr[u << 1].mx, self.tr[u << 1 | 1].mx)


class BookMyShow:

    def __init__(self, n: int, m: int):
        self.n = n
        self.tree = SegmentTree(n, m)

    def gather(self, k: int, maxRow: int) -> List[int]:
        maxRow += 1
        i = self.tree.query_idx(1, 1, maxRow, k)
        if i == 0:
            return []
        s = self.tree.query_sum(1, i, i)
        self.tree.modify(1, i, s - k)
        return [i - 1, self.tree.m - s]

    def scatter(self, k: int, maxRow: int) -> bool:
        maxRow += 1
        if self.tree.query_sum(1, 1, maxRow) < k:
            return False
        i = self.tree.query_idx(1, 1, maxRow, 1)
        for j in range(i, self.n + 1):
            s = self.tree.query_sum(1, j, j)
            if s >= k:
                self.tree.modify(1, j, s - k)
                return True
            k -= s
            self.tree.modify(1, j, 0)
        return True


# Your BookMyShow object will be instantiated and called as such:
# obj = BookMyShow(n, m)
# param_1 = obj.gather(k,maxRow)
# param_2 = obj.scatter(k,maxRow)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Node {
    int l, r;
    long mx, s;
}

class SegmentTree {
    private Node[] tr;
    private int m;

    public SegmentTree(int n, int m) {
        this.m = m;
        tr = new Node[n << 2];
        for (int i = 0; i < tr.length; ++i) {
            tr[i] = new Node();
        }
        build(1, 1, n);
    }

    private void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l == r) {
            tr[u].s = m;
            tr[u].mx = m;
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    public void modify(int u, int x, long v) {
        if (tr[u].l == x && tr[u].r == x) {
            tr[u].s = v;
            tr[u].mx = v;
            return;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (x <= mid) {
            modify(u << 1, x, v);
        } else {
            modify(u << 1 | 1, x, v);
        }
        pushup(u);
    }

    public long querySum(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u].s;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        long v = 0;
        if (l <= mid) {
            v += querySum(u << 1, l, r);
        }
        if (r > mid) {
            v += querySum(u << 1 | 1, l, r);
        }
        return v;
    }

    public int queryIdx(int u, int l, int r, int k) {
        if (tr[u].mx < k) {
            return 0;
        }
        if (tr[u].l == tr[u].r) {
            return tr[u].l;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (tr[u << 1].mx >= k) {
            return queryIdx(u << 1, l, r, k);
        }
        if (r > mid) {
            return queryIdx(u << 1 | 1, l, r, k);
        }
        return 0;
    }

    private void pushup(int u) {
        tr[u].s = tr[u << 1].s + tr[u << 1 | 1].s;
        tr[u].mx = Math.max(tr[u << 1].mx, tr[u << 1 | 1].mx);
    }
}

class BookMyShow {
    private int n;
    private int m;
    private SegmentTree tree;

    public BookMyShow(int n, int m) {
        this.n = n;
        this.m = m;
        tree = new SegmentTree(n, m);
    }

    public int[] gather(int k, int maxRow) {
        ++maxRow;
        int i = tree.queryIdx(1, 1, maxRow, k);
        if (i == 0) {
            return new int[] {};
        }
        long s = tree.querySum(1, i, i);
        tree.modify(1, i, s - k);
        return new int[] {i - 1, (int) (m - s)};
    }

    public boolean scatter(int k, int maxRow) {
        ++maxRow;
        if (tree.querySum(1, 1, maxRow) < k) {
            return false;
        }
        int i = tree.queryIdx(1, 1, maxRow, 1);
        for (int j = i; j <= n; ++j) {
            long s = tree.querySum(1, j, j);
            if (s >= k) {
                tree.modify(1, j, s - k);
                return true;
            }
            k -= s;
            tree.modify(1, j, 0);
        }
        return true;
    }
}

/**
 * Your BookMyShow object will be instantiated and called as such:
 * BookMyShow obj = new BookMyShow(n, m);
 * int[] param_1 = obj.gather(k,maxRow);
 * boolean param_2 = obj.scatter(k,maxRow);
 */
```

### **C++**

```cpp
class Node {
public:
    int l, r;
    long s, mx;
};

class SegmentTree {
public:
    SegmentTree(int n, int m) {
        this->m = m;
        tr.resize(n << 2);
        for (int i = 0; i < n << 2; ++i) {
            tr[i] = new Node();
        }
        build(1, 1, n);
    }

    void modify(int u, int x, int v) {
        if (tr[u]->l == x && tr[u]->r == x) {
            tr[u]->s = tr[u]->mx = v;
            return;
        }
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (x <= mid) {
            modify(u << 1, x, v);
        } else {
            modify(u << 1 | 1, x, v);
        }
        pushup(u);
    }

    long querySum(int u, int l, int r) {
        if (tr[u]->l >= l && tr[u]->r <= r) {
            return tr[u]->s;
        }
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        long v = 0;
        if (l <= mid) {
            v += querySum(u << 1, l, r);
        }
        if (r > mid) {
            v += querySum(u << 1 | 1, l, r);
        }
        return v;
    }

    int queryIdx(int u, int l, int r, int k) {
        if (tr[u]->mx < k) {
            return 0;
        }
        if (tr[u]->l == tr[u]->r) {
            return tr[u]->l;
        }
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (tr[u << 1]->mx >= k) {
            return queryIdx(u << 1, l, r, k);
        }
        if (r > mid) {
            return queryIdx(u << 1 | 1, l, r, k);
        }
        return 0;
    }

private:
    vector<Node*> tr;
    int m;

    void build(int u, int l, int r) {
        tr[u]->l = l;
        tr[u]->r = r;
        if (l == r) {
            tr[u]->s = m;
            tr[u]->mx = m;
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    void pushup(int u) {
        tr[u]->s = tr[u << 1]->s + tr[u << 1 | 1]->s;
        tr[u]->mx = max(tr[u << 1]->mx, tr[u << 1 | 1]->mx);
    }
};

class BookMyShow {
public:
    BookMyShow(int n, int m) {
        this->n = n;
        this->m = m;
        tree = new SegmentTree(n, m);
    }

    vector<int> gather(int k, int maxRow) {
        ++maxRow;
        int i = tree->queryIdx(1, 1, maxRow, k);
        if (i == 0) {
            return {};
        }
        long s = tree->querySum(1, i, i);
        tree->modify(1, i, s - k);
        return {i - 1, (int) (m - s)};
    }

    bool scatter(int k, int maxRow) {
        ++maxRow;
        if (tree->querySum(1, 1, maxRow) < k) {
            return false;
        }
        int i = tree->queryIdx(1, 1, maxRow, 1);
        for (int j = i; j <= n; ++j) {
            long s = tree->querySum(1, j, j);
            if (s >= k) {
                tree->modify(1, j, s - k);
                return true;
            }
            k -= s;
            tree->modify(1, j, 0);
        }
        return true;
    }

private:
    SegmentTree* tree;
    int m, n;
};

/**
 * Your BookMyShow object will be instantiated and called as such:
 * BookMyShow* obj = new BookMyShow(n, m);
 * vector<int> param_1 = obj->gather(k,maxRow);
 * bool param_2 = obj->scatter(k,maxRow);
 */
```

### **Go**

```go
type BookMyShow struct {
	n, m int
	tree *segmentTree
}

func Constructor(n int, m int) BookMyShow {
	return BookMyShow{n, m, newSegmentTree(n, m)}
}

func (this *BookMyShow) Gather(k int, maxRow int) []int {
	maxRow++
	i := this.tree.queryIdx(1, 1, maxRow, k)
	if i == 0 {
		return []int{}
	}
	s := this.tree.querySum(1, i, i)
	this.tree.modify(1, i, s-k)
	return []int{i - 1, this.m - s}
}

func (this *BookMyShow) Scatter(k int, maxRow int) bool {
	maxRow++
	if this.tree.querySum(1, 1, maxRow) < k {
		return false
	}
	i := this.tree.queryIdx(1, 1, maxRow, 1)
	for j := i; j <= this.n; j++ {
		s := this.tree.querySum(1, j, j)
		if s >= k {
			this.tree.modify(1, j, s-k)
			return true
		}
		k -= s
		this.tree.modify(1, j, 0)
	}
	return true
}

type node struct {
	l, r, s, mx int
}

type segmentTree struct {
	tr []*node
	m  int
}

func newSegmentTree(n, m int) *segmentTree {
	tr := make([]*node, n<<2)
	for i := range tr {
		tr[i] = &node{}
	}
	t := &segmentTree{tr, m}
	t.build(1, 1, n)
	return t
}

func (t *segmentTree) build(u, l, r int) {
	t.tr[u].l, t.tr[u].r = l, r
	if l == r {
		t.tr[u].s, t.tr[u].mx = t.m, t.m
		return
	}
	mid := (l + r) >> 1
	t.build(u<<1, l, mid)
	t.build(u<<1|1, mid+1, r)
	t.pushup(u)
}

func (t *segmentTree) modify(u, x, v int) {
	if t.tr[u].l == x && t.tr[u].r == x {
		t.tr[u].s, t.tr[u].mx = v, v
		return
	}
	mid := (t.tr[u].l + t.tr[u].r) >> 1
	if x <= mid {
		t.modify(u<<1, x, v)
	} else {
		t.modify(u<<1|1, x, v)
	}
	t.pushup(u)
}

func (t *segmentTree) querySum(u, l, r int) int {
	if t.tr[u].l >= l && t.tr[u].r <= r {
		return t.tr[u].s
	}
	mid := (t.tr[u].l + t.tr[u].r) >> 1
	v := 0
	if l <= mid {
		v = t.querySum(u<<1, l, r)
	}
	if r > mid {
		v += t.querySum(u<<1|1, l, r)
	}
	return v
}

func (t *segmentTree) queryIdx(u, l, r, k int) int {
	if t.tr[u].mx < k {
		return 0
	}
	if t.tr[u].l == t.tr[u].r {
		return t.tr[u].l
	}
	mid := (t.tr[u].l + t.tr[u].r) >> 1
	if t.tr[u<<1].mx >= k {
		return t.queryIdx(u<<1, l, r, k)
	}
	if r > mid {
		return t.queryIdx(u<<1|1, l, r, k)
	}
	return 0
}

func (t *segmentTree) pushup(u int) {
	t.tr[u].s = t.tr[u<<1].s + t.tr[u<<1|1].s
	t.tr[u].mx = max(t.tr[u<<1].mx, t.tr[u<<1|1].mx)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

/**
 * Your BookMyShow object will be instantiated and called as such:
 * obj := Constructor(n, m);
 * param_1 := obj.Gather(k,maxRow);
 * param_2 := obj.Scatter(k,maxRow);
 */
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
