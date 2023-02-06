# [2286. Booking Concert Tickets in Groups](https://leetcode.com/problems/booking-concert-tickets-in-groups)

[中文文档](/solution/2200-2299/2286.Booking%20Concert%20Tickets%20in%20Groups/README.md)

## Description

<p>A concert hall has <code>n</code> rows numbered from <code>0</code> to <code>n - 1</code>, each with <code>m</code> seats, numbered from <code>0</code> to <code>m - 1</code>. You need to design a ticketing system that can allocate seats in the following cases:</p>

<ul>
	<li>If a group of <code>k</code> spectators can sit <strong>together</strong> in a row.</li>
	<li>If <strong>every</strong> member of a group of <code>k</code> spectators can get a seat. They may or <strong>may not</strong> sit together.</li>
</ul>

<p>Note that the spectators are very picky. Hence:</p>

<ul>
	<li>They will book seats only if each member of their group can get a seat with row number <strong>less than or equal</strong> to <code>maxRow</code>. <code>maxRow</code> can <strong>vary</strong> from group to group.</li>
	<li>In case there are multiple rows to choose from, the row with the <strong>smallest</strong> number is chosen. If there are multiple seats to choose in the same row, the seat with the <strong>smallest</strong> number is chosen.</li>
</ul>

<p>Implement the <code>BookMyShow</code> class:</p>

<ul>
	<li><code>BookMyShow(int n, int m)</code> Initializes the object with <code>n</code> as number of rows and <code>m</code> as number of seats per row.</li>
	<li><code>int[] gather(int k, int maxRow)</code> Returns an array of length <code>2</code> denoting the row and seat number (respectively) of the <strong>first seat</strong> being allocated to the <code>k</code> members of the group, who must sit <strong>together</strong>. In other words, it returns the smallest possible <code>r</code> and <code>c</code> such that all <code>[c, c + k - 1]</code> seats are valid and empty in row <code>r</code>, and <code>r &lt;= maxRow</code>. Returns <code>[]</code> in case it is <strong>not possible</strong> to allocate seats to the group.</li>
	<li><code>boolean scatter(int k, int maxRow)</code> Returns <code>true</code> if all <code>k</code> members of the group can be allocated seats in rows <code>0</code> to <code>maxRow</code>, who may or <strong>may not</strong> sit together. If the seats can be allocated, it allocates <code>k</code> seats to the group with the <strong>smallest</strong> row numbers, and the smallest possible seat numbers in each row. Otherwise, returns <code>false</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;BookMyShow&quot;, &quot;gather&quot;, &quot;gather&quot;, &quot;scatter&quot;, &quot;scatter&quot;]
[[2, 5], [4, 0], [2, 0], [5, 1], [5, 1]]
<strong>Output</strong>
[null, [0, 0], [], true, false]

<strong>Explanation</strong>
BookMyShow bms = new BookMyShow(2, 5); // There are 2 rows with 5 seats each 
bms.gather(4, 0); // return [0, 0]
                  // The group books seats [0, 3] of row 0. 
bms.gather(2, 0); // return []
                  // There is only 1 seat left in row 0,
                  // so it is not possible to book 2 consecutive seats. 
bms.scatter(5, 1); // return True
                   // The group books seat 4 of row 0 and seats [0, 3] of row 1. 
bms.scatter(5, 1); // return False
                   // There is only one seat left in the hall.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m, k &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= maxRow &lt;= n - 1</code></li>
	<li>At most <code>5 * 10<sup>4</sup></code> calls <strong>in total</strong> will be made to <code>gather</code> and <code>scatter</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
