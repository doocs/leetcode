type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) *BinaryIndexedTree {
	return &BinaryIndexedTree{n: n, c: make([]int, n+1)}
}

func (bit *BinaryIndexedTree) update(x, delta int) {
	for ; x <= bit.n; x += x & -x {
		bit.c[x] += delta
	}
}

func (bit *BinaryIndexedTree) query(x int) int {
	s := 0
	for ; x > 0; x -= x & -x {
		s += bit.c[x]
	}
	return s
}

func getModifiedArray(length int, updates [][]int) (ans []int) {
	bit := NewBinaryIndexedTree(length)
	for _, e := range updates {
		l, r, c := e[0], e[1], e[2]
		bit.update(l+1, c)
		bit.update(r+2, -c)
	}
	for i := 1; i <= length; i++ {
		ans = append(ans, bit.query(i))
	}
	return
}
