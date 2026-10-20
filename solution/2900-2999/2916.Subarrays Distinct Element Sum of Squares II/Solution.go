const mod = 1_000_000_007

type node struct {
	l, r       int
	sum, sumSq int
	lazy       int
}

type segmentTree struct {
	tr []node
}

func newSegmentTree(n int) *segmentTree {
	st := &segmentTree{tr: make([]node, n<<2)}
	st.build(1, 0, n-1)
	return st
}

func (st *segmentTree) build(u, l, r int) {
	st.tr[u].l, st.tr[u].r = l, r
	if l == r {
		return
	}
	mid := (l + r) >> 1
	st.build(u<<1, l, mid)
	st.build(u<<1|1, mid+1, r)
}

func (st *segmentTree) apply(u, v int) {
	length := st.tr[u].r - st.tr[u].l + 1
	st.tr[u].sumSq += 2*v*st.tr[u].sum + v*v*length
	st.tr[u].sum += v * length
	st.tr[u].lazy += v
}

func (st *segmentTree) pushdown(u int) {
	if st.tr[u].lazy != 0 {
		st.apply(u<<1, st.tr[u].lazy)
		st.apply(u<<1|1, st.tr[u].lazy)
		st.tr[u].lazy = 0
	}
}

func (st *segmentTree) pushup(u int) {
	st.tr[u].sum = st.tr[u<<1].sum + st.tr[u<<1|1].sum
	st.tr[u].sumSq = st.tr[u<<1].sumSq + st.tr[u<<1|1].sumSq
}

func (st *segmentTree) modify(u, l, r, v int) {
	if st.tr[u].l >= l && st.tr[u].r <= r {
		st.apply(u, v)
		return
	}
	st.pushdown(u)
	mid := (st.tr[u].l + st.tr[u].r) >> 1
	if l <= mid {
		st.modify(u<<1, l, r, v)
	}
	if r > mid {
		st.modify(u<<1|1, l, r, v)
	}
	st.pushup(u)
}

func sumCounts(nums []int) int {
	n := len(nums)
	st := newSegmentTree(n)
	last := map[int]int{}
	ans := 0
	for i, x := range nums {
		p, ok := last[x]
		if !ok {
			p = -1
		}
		st.modify(1, p+1, i, 1)
		last[x] = i
		ans = (ans + st.tr[1].sumSq) % mod
	}
	return ans
}
