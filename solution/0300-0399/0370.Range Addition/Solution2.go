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

func getModifiedArray(length int, updates [][]int) []int {
	tree := newBinaryIndexedTree(length)
	for _, e := range updates {
		start, end, inc := e[0], e[1], e[2]
		tree.update(start+1, inc)
		tree.update(end+2, -inc)
	}
	ans := make([]int, length)
	for i := range ans {
		ans[i] = tree.query(i + 1)
	}
	return ans
}