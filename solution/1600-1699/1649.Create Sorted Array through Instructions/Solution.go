type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) lowbit(x int) int {
	return x & -x
}

func (this *BinaryIndexedTree) update(x, delta int) {
	for x <= this.n {
		this.c[x] += delta
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= this.lowbit(x)
	}
	return s
}

func createSortedArray(instructions []int) int {
	n := 100010
	mod := int(1e9 + 7)
	tree := newBinaryIndexedTree(n)
	ans := 0
	for _, num := range instructions {
		a, b := tree.query(num-1), tree.query(n)-tree.query(num)
		ans += min(a, b)
		ans %= mod
		tree.update(num, 1)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}