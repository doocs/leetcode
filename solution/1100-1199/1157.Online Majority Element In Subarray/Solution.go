type node struct {
	l, r, x, cnt int
}

type segmentTree struct {
	nums []int
	tr   []*node
}

type pair struct{ x, cnt int }

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
		t.tr[u].x = t.nums[l-1]
		t.tr[u].cnt = 1
		return
	}
	mid := (l + r) >> 1
	t.build(u<<1, l, mid)
	t.build(u<<1|1, mid+1, r)
	t.pushup(u)
}

func (t *segmentTree) query(u, l, r int) pair {
	if t.tr[u].l >= l && t.tr[u].r <= r {
		return pair{t.tr[u].x, t.tr[u].cnt}
	}
	mid := (t.tr[u].l + t.tr[u].r) >> 1
	if r <= mid {
		return t.query(u<<1, l, r)
	}
	if l > mid {
		return t.query(u<<1|1, l, r)
	}
	left, right := t.query(u<<1, l, r), t.query(u<<1|1, l, r)
	if left.x == right.x {
		left.cnt += right.cnt
	} else if left.cnt >= right.cnt {
		left.cnt -= right.cnt
	} else {
		right.cnt -= left.cnt
		left = right
	}
	return left
}

func (t *segmentTree) pushup(u int) {
	if t.tr[u<<1].x == t.tr[u<<1|1].x {
		t.tr[u].x = t.tr[u<<1].x
		t.tr[u].cnt = t.tr[u<<1].cnt + t.tr[u<<1|1].cnt
	} else if t.tr[u<<1].cnt >= t.tr[u<<1|1].cnt {
		t.tr[u].x = t.tr[u<<1].x
		t.tr[u].cnt = t.tr[u<<1].cnt - t.tr[u<<1|1].cnt
	} else {
		t.tr[u].x = t.tr[u<<1|1].x
		t.tr[u].cnt = t.tr[u<<1|1].cnt - t.tr[u<<1].cnt
	}
}

type MajorityChecker struct {
	tree *segmentTree
	d    map[int][]int
}

func Constructor(arr []int) MajorityChecker {
	tree := newSegmentTree(arr)
	d := map[int][]int{}
	for i, x := range arr {
		d[x] = append(d[x], i)
	}
	return MajorityChecker{tree, d}
}

func (this *MajorityChecker) Query(left int, right int, threshold int) int {
	x := this.tree.query(1, left+1, right+1).x
	l := sort.SearchInts(this.d[x], left)
	r := sort.SearchInts(this.d[x], right+1)
	if r-l >= threshold {
		return x
	}
	return -1
}

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * obj := Constructor(arr);
 * param_1 := obj.Query(left,right,threshold);
 */