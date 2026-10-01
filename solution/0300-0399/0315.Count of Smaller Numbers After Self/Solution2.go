type node struct {
	l, r, v int
}

type segmentTree struct {
	tr []node
}

func newSegmentTree(n int) *segmentTree {
	t := &segmentTree{tr: make([]node, n<<2)}
	t.build(1, 1, n)
	return t
}

func (t *segmentTree) build(u, l, r int) {
	t.tr[u].l, t.tr[u].r = l, r
	if l == r {
		return
	}
	mid := (l + r) >> 1
	t.build(u<<1, l, mid)
	t.build(u<<1|1, mid+1, r)
}

func (t *segmentTree) modify(u, x, v int) {
	if t.tr[u].l == x && t.tr[u].r == x {
		t.tr[u].v += v
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

func (t *segmentTree) pushup(u int) {
	t.tr[u].v = t.tr[u<<1].v + t.tr[u<<1|1].v
}

func (t *segmentTree) query(u, l, r int) int {
	if t.tr[u].l >= l && t.tr[u].r <= r {
		return t.tr[u].v
	}
	mid := (t.tr[u].l + t.tr[u].r) >> 1
	v := 0
	if l <= mid {
		v += t.query(u<<1, l, r)
	}
	if r > mid {
		v += t.query(u<<1|1, l, r)
	}
	return v
}

func countSmaller(nums []int) []int {
	s := map[int]struct{}{}
	for _, v := range nums {
		s[v] = struct{}{}
	}
	alls := make([]int, 0, len(s))
	for v := range s {
		alls = append(alls, v)
	}
	sort.Ints(alls)
	m := map[int]int{}
	for i, v := range alls {
		m[v] = i + 1
	}
	tree := newSegmentTree(len(alls))
	ans := make([]int, len(nums))
	for i := len(nums) - 1; i >= 0; i-- {
		x := m[nums[i]]
		tree.modify(1, x, 1)
		ans[i] = tree.query(1, 1, x-1)
	}
	return ans
}
