type node struct {
	l, r, s, lazy int
}

type segmentTree struct {
	nums []int
	tr   []*node
}

func newSegmentTree(nums []int) *segmentTree {
	n := len(nums)
	tr := make([]*node, n<<2)
	for i := range tr {
		tr[i] = &node{}
	}
	t := &segmentTree{nums, tr}
	t.build(1, 1, n)
	return t
}

func (t *segmentTree) build(u, l, r int) {
	t.tr[u].l, t.tr[u].r = l, r
	if l == r {
		t.tr[u].s = t.nums[l-1]
		return
	}
	mid := (l + r) >> 1
	t.build(u<<1, l, mid)
	t.build(u<<1|1, mid+1, r)
	t.pushup(u)
}

func (t *segmentTree) modify(u, l, r int) {
	if t.tr[u].l >= l && t.tr[u].r <= r {
		t.tr[u].lazy ^= 1
		t.tr[u].s = t.tr[u].r - t.tr[u].l + 1 - t.tr[u].s
		return
	}
	t.pushdown(u)
	mid := (t.tr[u].l + t.tr[u].r) >> 1
	if l <= mid {
		t.modify(u<<1, l, r)
	}
	if r > mid {
		t.modify(u<<1|1, l, r)
	}
	t.pushup(u)
}

func (t *segmentTree) query(u, l, r int) int {
	if t.tr[u].l >= l && t.tr[u].r <= r {
		return t.tr[u].s
	}
	t.pushdown(u)
	mid := (t.tr[u].l + t.tr[u].r) >> 1
	res := 0
	if l <= mid {
		res += t.query(u<<1, l, r)
	}
	if r > mid {
		res += t.query(u<<1|1, l, r)
	}
	return res
}

func (t *segmentTree) pushup(u int) {
	t.tr[u].s = t.tr[u<<1].s + t.tr[u<<1|1].s
}

func (t *segmentTree) pushdown(u int) {
	if t.tr[u].lazy == 1 {
		mid := (t.tr[u].l + t.tr[u].r) >> 1
		t.tr[u<<1].s = mid - t.tr[u].l + 1 - t.tr[u<<1].s
		t.tr[u<<1].lazy ^= 1
		t.tr[u<<1|1].s = t.tr[u].r - mid - t.tr[u<<1|1].s
		t.tr[u<<1|1].lazy ^= 1
		t.tr[u].lazy ^= 1
	}
}

func handleQuery(nums1 []int, nums2 []int, queries [][]int) (ans []int64) {
	tree := newSegmentTree(nums1)
	var s int64
	for _, x := range nums2 {
		s += int64(x)
	}
	for _, q := range queries {
		if q[0] == 1 {
			tree.modify(1, q[1]+1, q[2]+1)
		} else if q[0] == 2 {
			s += int64(q[1] * tree.query(1, 1, len(nums1)))
		} else {
			ans = append(ans, s)
		}
	}
	return
}