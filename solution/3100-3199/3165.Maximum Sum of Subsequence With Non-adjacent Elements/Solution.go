type Node struct {
	l, r               int
	s00, s01, s10, s11 int
}

func NewNode(l, r int) *Node {
	return &Node{l: l, r: r, s00: 0, s01: 0, s10: 0, s11: 0}
}

type SegmentTree struct {
	tr []*Node
}

func NewSegmentTree(n int) *SegmentTree {
	tr := make([]*Node, n*4)
	tree := &SegmentTree{tr: tr}
	tree.build(1, 1, n)
	return tree
}

func (st *SegmentTree) build(u, l, r int) {
	st.tr[u] = NewNode(l, r)
	if l == r {
		return
	}
	mid := (l + r) >> 1
	st.build(u<<1, l, mid)
	st.build(u<<1|1, mid+1, r)
}

func (st *SegmentTree) query(u, l, r int) int {
	if st.tr[u].l >= l && st.tr[u].r <= r {
		return st.tr[u].s11
	}
	mid := (st.tr[u].l + st.tr[u].r) >> 1
	ans := 0
	if r <= mid {
		ans = st.query(u<<1, l, r)
	}
	if l > mid {
		ans = max(ans, st.query(u<<1|1, l, r))
	}
	return ans
}

func (st *SegmentTree) pushup(u int) {
	left := st.tr[u<<1]
	right := st.tr[u<<1|1]
	st.tr[u].s00 = max(left.s00+right.s10, left.s01+right.s00)
	st.tr[u].s01 = max(left.s00+right.s11, left.s01+right.s01)
	st.tr[u].s10 = max(left.s10+right.s10, left.s11+right.s00)
	st.tr[u].s11 = max(left.s10+right.s11, left.s11+right.s01)
}

func (st *SegmentTree) modify(u, x, v int) {
	if st.tr[u].l == st.tr[u].r {
		st.tr[u].s11 = max(0, v)
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

func maximumSumSubsequence(nums []int, queries [][]int) (ans int) {
	n := len(nums)
	tree := NewSegmentTree(n)
	for i, x := range nums {
		tree.modify(1, i+1, x)
	}
	const mod int = 1e9 + 7
	for _, q := range queries {
		tree.modify(1, q[0]+1, q[1])
		ans = (ans + tree.query(1, 1, n)) % mod
	}
	return
}
