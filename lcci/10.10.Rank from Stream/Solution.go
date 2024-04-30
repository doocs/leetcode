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

type StreamRank struct {
	tree *BinaryIndexedTree
}

func Constructor() StreamRank {
	return StreamRank{NewBinaryIndexedTree(50010)}
}

func (this *StreamRank) Track(x int) {
	this.tree.update(x+1, 1)
}

func (this *StreamRank) GetRankOfNumber(x int) int {
	return this.tree.query(x + 1)
}

/**
 * Your StreamRank object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Track(x);
 * param_2 := obj.GetRankOfNumber(x);
 */