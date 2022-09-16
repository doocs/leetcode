func rectangleArea(rectangles [][]int) int {
	var mod int = 1e9 + 7
	segs := [][]int{}
	alls := map[int]bool{}
	for _, e := range rectangles {
		x1, y1, x2, y2 := e[0], e[1], e[2], e[3]
		segs = append(segs, []int{x1, y1, y2, 1})
		segs = append(segs, []int{x2, y1, y2, -1})
		alls[y1] = true
		alls[y2] = true
	}
	nums := []int{}
	for v := range alls {
		nums = append(nums, v)
	}
	sort.Ints(nums)
	sort.Slice(segs, func(i, j int) bool { return segs[i][0] < segs[j][0] })
	m := map[int]int{}
	for i, v := range nums {
		m[v] = i
	}
	tree := newSegmentTree(nums)
	ans := 0
	for i, e := range segs {
		x, y1, y2, k := e[0], e[1], e[2], e[3]
		if i > 0 {
			ans += tree.query() * (x - segs[i-1][0])
			ans %= mod
		}
		tree.modify(1, m[y1], m[y2]-1, k)
	}
	return ans
}

type node struct {
	l      int
	r      int
	cnt    int
	length int
}

type segmentTree struct {
	tr   []*node
	nums []int
}

func newSegmentTree(nums []int) *segmentTree {
	n := len(nums) - 1
	tr := make([]*node, n<<2)
	for i := range tr {
		tr[i] = &node{}
	}
	t := &segmentTree{tr, nums}
	t.build(1, 0, n-1)
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

func (t *segmentTree) modify(u, l, r, k int) {
	if t.tr[u].l >= l && t.tr[u].r <= r {
		t.tr[u].cnt += k
	} else {
		mid := (t.tr[u].l + t.tr[u].r) >> 1
		if l <= mid {
			t.modify(u<<1, l, r, k)
		}
		if r > mid {
			t.modify(u<<1|1, l, r, k)
		}
	}
	t.pushup(u)
}

func (t *segmentTree) query() int {
	return t.tr[1].length
}

func (t *segmentTree) pushup(u int) {
	if t.tr[u].cnt > 0 {
		t.tr[u].length = t.nums[t.tr[u].r+1] - t.nums[t.tr[u].l]
	} else if t.tr[u].l == t.tr[u].r {
		t.tr[u].length = 0
	} else {
		t.tr[u].length = t.tr[u<<1].length + t.tr[u<<1|1].length
	}
}