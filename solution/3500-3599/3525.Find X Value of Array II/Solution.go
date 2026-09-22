type node struct {
	l, r, prod int
	cnt        []int
}

type segmentTree struct {
	k  int
	tr []*node
}

func newSegmentTree(nums []int, k int) *segmentTree {
	n := len(nums)
	tr := make([]*node, n<<2)
	t := &segmentTree{k, tr}
	t.build(1, 1, n, nums)
	return t
}

func (t *segmentTree) merge(a, b *node) *node {
	c := &node{prod: a.prod * b.prod % t.k, cnt: make([]int, t.k)}
	copy(c.cnt, a.cnt)
	for r, v := range b.cnt {
		c.cnt[a.prod*r%t.k] += v
	}
	return c
}

func (t *segmentTree) pushup(u int) {
	p := t.merge(t.tr[u<<1], t.tr[u<<1|1])
	t.tr[u].prod = p.prod
	copy(t.tr[u].cnt, p.cnt)
}

func (t *segmentTree) build(u, l, r int, nums []int) {
	t.tr[u] = &node{l: l, r: r, prod: 1, cnt: make([]int, t.k)}
	if l == r {
		v := nums[l-1] % t.k
		t.tr[u].prod = v
		t.tr[u].cnt[v] = 1
		return
	}
	mid := (l + r) >> 1
	t.build(u<<1, l, mid, nums)
	t.build(u<<1|1, mid+1, r, nums)
	t.pushup(u)
}

func (t *segmentTree) modify(u, x, v int) {
	if t.tr[u].l == t.tr[u].r {
		v %= t.k
		t.tr[u].prod = v
		for i := range t.tr[u].cnt {
			t.tr[u].cnt[i] = 0
		}
		t.tr[u].cnt[v] = 1
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

func (t *segmentTree) query(u, l, r int) *node {
	if t.tr[u].l >= l && t.tr[u].r <= r {
		return t.tr[u]
	}
	mid := (t.tr[u].l + t.tr[u].r) >> 1
	if r <= mid {
		return t.query(u<<1, l, r)
	}
	if l > mid {
		return t.query(u<<1|1, l, r)
	}
	return t.merge(t.query(u<<1, l, r), t.query(u<<1|1, l, r))
}

func resultArray(nums []int, k int, queries [][]int) []int {
	n := len(nums)
	tree := newSegmentTree(nums, k)
	ans := make([]int, len(queries))
	for i, q := range queries {
		tree.modify(1, q[0]+1, q[1])
		ans[i] = tree.query(1, q[2]+1, n).cnt[q[3]]
	}
	return ans
}
