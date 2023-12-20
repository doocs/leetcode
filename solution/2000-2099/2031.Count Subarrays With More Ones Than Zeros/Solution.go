type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	return &BinaryIndexedTree{n: n, c: make([]int, n+1)}
}

func (bit *BinaryIndexedTree) update(x, v int) {
	for ; x <= bit.n; x += x & -x {
		bit.c[x] += v
	}
}

func (bit *BinaryIndexedTree) query(x int) (s int) {
	for ; x > 0; x -= x & -x {
		s += bit.c[x]
	}
	return
}

func subarraysWithMoreZerosThanOnes(nums []int) (ans int) {
	n := len(nums)
	base := n + 1
	tree := newBinaryIndexedTree(n + base)
	tree.update(base, 1)
	const mod = int(1e9) + 7
	s := 0
	for _, x := range nums {
		if x == 0 {
			s--
		} else {
			s++
		}
		ans += tree.query(s - 1 + base)
		ans %= mod
		tree.update(s+base, 1)
	}
	return
}