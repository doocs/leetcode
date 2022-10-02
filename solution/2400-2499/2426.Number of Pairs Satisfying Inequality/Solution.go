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

func numberOfPairs(nums1 []int, nums2 []int, diff int) int64 {
	tree := newBinaryIndexedTree(100000)
	ans := 0
	for i := range nums1 {
		v := nums1[i] - nums2[i]
		ans += tree.query(v + diff + 40000)
		tree.update(v+40000, 1)
	}
	return int64(ans)
}