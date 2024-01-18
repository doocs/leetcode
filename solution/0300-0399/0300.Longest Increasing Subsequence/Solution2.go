type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	return &BinaryIndexedTree{n, make([]int, n+1)}
}

func (bit *BinaryIndexedTree) update(x, v int) {
	for x <= bit.n {
		bit.c[x] = max(bit.c[x], v)
		x += x & -x
	}
}

func (bit *BinaryIndexedTree) query(x int) int {
	mx := 0
	for x > 0 {
		mx = max(mx, bit.c[x])
		x -= x & -x
	}
	return mx
}

func lengthOfLIS(nums []int) int {
	n := len(nums)
	s := make([]int, n)
	copy(s, nums)
	sort.Ints(s)
	m := 0
	for i, x := range s {
		if i == 0 || x != s[i-1] {
			s[m] = x
			m++
		}
	}
	tree := newBinaryIndexedTree(m)
	for _, x := range nums {
		x = sort.SearchInts(s[:m], x) + 1
		t := tree.query(x-1) + 1
		tree.update(x, t)
	}
	return tree.query(m)
}