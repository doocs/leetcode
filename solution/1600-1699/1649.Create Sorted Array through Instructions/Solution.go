type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) update(x, delta int) {
	for x <= this.n {
		this.c[x] += delta
		x += x & -x
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= x & -x
	}
	return s
}

func createSortedArray(instructions []int) (ans int) {
	m := 0
	for _, x := range instructions {
		m = max(m, x)
	}
	tree := newBinaryIndexedTree(m)
	const mod = 1e9 + 7
	for i, x := range instructions {
		cost := min(tree.query(x-1), i-tree.query(x))
		ans = (ans + cost) % mod
		tree.update(x, 1)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}