type Node struct {
	l, r, v int
}

type SegmentTree struct {
	tr []Node
}

func NewSegmentTree(n int) *SegmentTree {
	tr := make([]Node, 4*n)
	st := &SegmentTree{tr: tr}
	st.build(1, 1, n)
	return st
}

func (st *SegmentTree) build(u, l, r int) {
	st.tr[u].l = l
	st.tr[u].r = r
	if l == r {
		return
	}
	mid := (l + r) >> 1
	st.build(u<<1, l, mid)
	st.build(u<<1|1, mid+1, r)
}

func (st *SegmentTree) modify(u, x, v int) {
	if st.tr[u].l == x && st.tr[u].r == x {
		st.tr[u].v += v
		return
	}
	mid := (st.tr[u].l + st.tr[u].r) >> 1
	if x <= mid {
		st.modify(u<<1, x, v)
	} else {
		st.modify(u<<1|1, x, v)
	}
	st.pushup(u)
}

func (st *SegmentTree) pushup(u int) {
	st.tr[u].v = st.tr[u<<1].v + st.tr[u<<1|1].v
}

func (st *SegmentTree) query(u, l, r int) int {
	if st.tr[u].l >= l && st.tr[u].r <= r {
		return st.tr[u].v
	}
	mid := (st.tr[u].l + st.tr[u].r) >> 1
	res := 0
	if l <= mid {
		res += st.query(u<<1, l, r)
	}
	if r > mid {
		res += st.query(u<<1|1, l, r)
	}
	return res
}

func goodTriplets(nums1 []int, nums2 []int) int64 {
	n := len(nums1)
	pos := make(map[int]int)
	for i, v := range nums2 {
		pos[v] = i + 1
	}

	tree := NewSegmentTree(n)
	var ans int64

	for _, num := range nums1 {
		p := pos[num]
		left := tree.query(1, 1, p)
		right := n - p - (tree.query(1, 1, n) - tree.query(1, 1, p))
		ans += int64(left * right)
		tree.modify(1, p, 1)
	}

	return ans
}
