type Node struct {
	l, r   int
	cnt    int
	length int
}

type SegmentTree struct {
	tr   []Node
	nums []int
}

func NewSegmentTree(nums []int) *SegmentTree {
	n := len(nums) - 1
	tr := make([]Node, n<<2)
	t := &SegmentTree{tr: tr, nums: nums}
	t.build(1, 0, n-1)
	return t
}

func (t *SegmentTree) build(u, l, r int) {
	t.tr[u].l = l
	t.tr[u].r = r
	if l != r {
		mid := (l + r) >> 1
		t.build(u<<1, l, mid)
		t.build(u<<1|1, mid+1, r)
	}
}

func (t *SegmentTree) modify(u, l, r, k int) {
	if l > r {
		return
	}
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

func (t *SegmentTree) pushup(u int) {
	if t.tr[u].cnt > 0 {
		t.tr[u].length = t.nums[t.tr[u].r+1] - t.nums[t.tr[u].l]
	} else if t.tr[u].l == t.tr[u].r {
		t.tr[u].length = 0
	} else {
		t.tr[u].length = t.tr[u<<1].length + t.tr[u<<1|1].length
	}
}

func (t *SegmentTree) query() int {
	return t.tr[1].length
}

func separateSquares(squares [][]int) float64 {
	pos := make(map[int]bool)
	xs := make([]int, 0)
	segs := make([][]int, 0, len(squares)*2)
	for _, sq := range squares {
		x1, y1, l := sq[0], sq[1], sq[2]
		x2, y2 := x1+l, y1+l
		if !pos[x1] {
			pos[x1] = true
			xs = append(xs, x1)
		}
		if !pos[x2] {
			pos[x2] = true
			xs = append(xs, x2)
		}
		segs = append(segs, []int{y1, x1, x2, 1})
		segs = append(segs, []int{y2, x1, x2, -1})
	}
	sort.Slice(segs, func(i, j int) bool { return segs[i][0] < segs[j][0] })
	sort.Ints(xs)
	tree := NewSegmentTree(xs)
	d := make(map[int]int, len(xs))
	for i, x := range xs {
		d[x] = i
	}
	area := 0.0
	y0 := 0
	for _, s := range segs {
		y, x1, x2, k := s[0], s[1], s[2], s[3]
		area += float64(y-y0) * float64(tree.query())
		tree.modify(1, d[x1], d[x2]-1, k)
		y0 = y
	}
	target := area / 2.0
	area = 0.0
	y0 = 0
	for _, s := range segs {
		y, x1, x2, k := s[0], s[1], s[2], s[3]
		curLen := tree.query()
		t := float64(y-y0) * float64(curLen)
		if area+t >= target {
			return float64(y0) + (target-area)/float64(curLen)
		}
		area += t
		tree.modify(1, d[x1], d[x2]-1, k)
		y0 = y
	}
	return 0.0
}
