// Segment tree node
type Node struct {
	l, r   int // segment range
	mn, mx int // minimum / maximum prefix sum
	lazy   int // lazy propagation (range add)
}

// Segment tree
type SegmentTree struct {
	tr []Node
}

// Build a segment tree for range [0, n]
func NewSegmentTree(n int) *SegmentTree {
	st := &SegmentTree{
		tr: make([]Node, n<<2),
	}
	st.build(1, 0, n)
	return st
}

// Initialize all prefix sums to 0
func (st *SegmentTree) build(u, l, r int) {
	st.tr[u] = Node{l: l, r: r, mn: 0, mx: 0, lazy: 0}
	if l == r {
		return
	}
	mid := (l + r) >> 1
	st.build(u<<1, l, mid)
	st.build(u<<1|1, mid+1, r)
}

// Add v to all prefix sums in [l, r]
func (st *SegmentTree) modify(u, l, r, v int) {
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

// Binary search on the segment tree
// Find the smallest index where prefix sum == target
func (st *SegmentTree) query(u, target int) int {
	if st.tr[u].l == st.tr[u].r {
		return st.tr[u].l
	}
	st.pushdown(u)
	left, right := u<<1, u<<1|1
	if st.tr[left].mn <= target && target <= st.tr[left].mx {
		return st.query(left, target)
	}
	return st.query(right, target)
}

// Apply range addition
func (st *SegmentTree) apply(u, v int) {
	st.tr[u].mn += v
	st.tr[u].mx += v
	st.tr[u].lazy += v
}

// Update from children
func (st *SegmentTree) pushup(u int) {
	st.tr[u].mn = min(st.tr[u<<1].mn, st.tr[u<<1|1].mn)
	st.tr[u].mx = max(st.tr[u<<1].mx, st.tr[u<<1|1].mx)
}

// Push lazy value down
func (st *SegmentTree) pushdown(u int) {
	if st.tr[u].lazy != 0 {
		v := st.tr[u].lazy
		st.apply(u<<1, v)
		st.apply(u<<1|1, v)
		st.tr[u].lazy = 0
	}
}

// Main function
func longestBalanced(nums []int) int {
	n := len(nums)
	st := NewSegmentTree(n)

	// last[x] = last position where value x appeared
	last := make(map[int]int)

	now := 0 // current prefix sum
	ans := 0 // answer

	// Enumerate right endpoint
	for i := 1; i <= n; i++ {
		x := nums[i-1]
		det := -1
		if x&1 == 1 {
			det = 1
		}

		// Remove previous contribution if x appeared before
		if pos, ok := last[x]; ok {
			st.modify(1, pos, n, -det)
			now -= det
		}

		// Add current contribution
		last[x] = i
		st.modify(1, i, n, det)
		now += det

		// Find earliest position with same prefix sum
		pos := st.query(1, now)
		ans = max(ans, i-pos)
	}

	return ans
}
