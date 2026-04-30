func gcd(a, b int) int {
	for b != 0 {
		a, b = b, a%b
	}
	return a
}

type Node struct {
	l, r int
	g    int
}

func NewNode(l, r int) *Node {
	return &Node{l: l, r: r, g: 0}
}

type SegmentTree struct {
	tr []*Node
}

func NewSegmentTree(n int) *SegmentTree {
	tree := &SegmentTree{tr: make([]*Node, n<<2)}
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

func (st *SegmentTree) pushup(u int) {
	st.tr[u].g = gcd(st.tr[u<<1].g, st.tr[u<<1|1].g)
}

func (st *SegmentTree) modify(u, x, v int) {
	if st.tr[u].l == st.tr[u].r {
		st.tr[u].g = v
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

func (st *SegmentTree) query(u, l, r int) int {
	if l > r {
		return 0
	}
	if st.tr[u].l >= l && st.tr[u].r <= r {
		return st.tr[u].g
	}
	mid := (st.tr[u].l + st.tr[u].r) >> 1
	if r <= mid {
		return st.query(u<<1, l, r)
	}
	if l > mid {
		return st.query(u<<1|1, l, r)
	}
	return gcd(st.query(u<<1, l, mid), st.query(u<<1|1, mid+1, r))
}

func countGoodSubseq(nums []int, p int, queries [][]int) int {
	n := len(nums)
	tree := NewSegmentTree(n)
	cnt := 0
	for i, x := range nums {
		if x%p == 0 {
			tree.modify(1, i+1, x)
			cnt++
		}
	}

	ans := 0
	for _, q := range queries {
		idx, val := q[0], q[1]
		if nums[idx]%p == 0 {
			tree.modify(1, idx+1, 0)
			cnt--
		}
		if val%p == 0 {
			tree.modify(1, idx+1, val)
			cnt++
		}
		nums[idx] = val

		if tree.tr[1].g != p {
			continue
		}
		if cnt < n || n > 6 {
			ans++
			continue
		}
		for i := 1; i <= n; i++ {
			leftG := tree.query(1, 1, i-1)
			rightG := tree.query(1, i+1, n)
			if gcd(leftG, rightG) == p {
				ans++
				break
			}
		}
	}
	return ans
}