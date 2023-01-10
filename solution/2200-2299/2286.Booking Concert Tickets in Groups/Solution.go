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