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

func kEmptySlots(bulbs []int, k int) int {
	n := len(bulbs)
	tree := newBinaryIndexedTree(n)
	for i, x := range bulbs {
		tree.update(x, 1)
		case1 := x-k-1 > 0 && tree.query(x-k-1)-tree.query(x-k-2) == 1 && tree.query(x-1)-tree.query(x-k-1) == 0
		case2 := x+k+1 <= n && tree.query(x+k+1)-tree.query(x+k) == 1 && tree.query(x+k)-tree.query(x) == 0
		if case1 || case2 {
			return i + 1
		}
	}
	return -1
}