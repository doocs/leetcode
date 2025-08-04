type SegmentTree struct {
	nums, tr []int
}

func NewSegmentTree(nums []int) *SegmentTree {
	n := len(nums)
	tree := &SegmentTree{
		nums: nums,
		tr:   make([]int, n*4),
	}
	tree.build(1, 1, n)
	return tree
}

func (st *SegmentTree) build(u, l, r int) {
	if l == r {
		st.tr[u] = st.nums[l-1]
		return
	}
	mid := (l + r) >> 1
	st.build(u*2, l, mid)
	st.build(u*2+1, mid+1, r)
	st.pushup(u)
}

func (st *SegmentTree) modify(u, l, r, i, v int) {
	if l == r {
		st.tr[u] = v
		return
	}
	mid := (l + r) >> 1
	if i <= mid {
		st.modify(u*2, l, mid, i, v)
	} else {
		st.modify(u*2+1, mid+1, r, i, v)
	}
	st.pushup(u)
}

func (st *SegmentTree) query(u, l, r, v int) int {
	if st.tr[u] < v {
		return -1
	}
	if l == r {
		return l
	}
	mid := (l + r) >> 1
	if st.tr[u*2] >= v {
		return st.query(u*2, l, mid, v)
	}
	return st.query(u*2+1, mid+1, r, v)
}

func (st *SegmentTree) pushup(u int) {
	st.tr[u] = max(st.tr[u*2], st.tr[u*2+1])
}

func numOfUnplacedFruits(fruits []int, baskets []int) (ans int) {
	tree := NewSegmentTree(baskets)
	n := len(baskets)
	for _, x := range fruits {
		i := tree.query(1, 1, n, x)
		if i < 0 {
			ans++
		} else {
			tree.modify(1, 1, n, i, 0)
		}
	}
	return
}
