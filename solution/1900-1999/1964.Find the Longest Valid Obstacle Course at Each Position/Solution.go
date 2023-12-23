type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) *BinaryIndexedTree {
	return &BinaryIndexedTree{n, make([]int, n+1)}
}

func (bit *BinaryIndexedTree) update(x, v int) {
	for x <= bit.n {
		bit.c[x] = max(bit.c[x], v)
		x += x & -x
	}
}

func (bit *BinaryIndexedTree) query(x int) (s int) {
	for x > 0 {
		s = max(s, bit.c[x])
		x -= x & -x
	}
	return
}

func longestObstacleCourseAtEachPosition(obstacles []int) (ans []int) {
	nums := slices.Clone(obstacles)
	sort.Ints(nums)
	n := len(nums)
	tree := NewBinaryIndexedTree(n)
	for k, x := range obstacles {
		i := sort.SearchInts(nums, x) + 1
		ans = append(ans, tree.query(i)+1)
		tree.update(i, ans[k])
	}
	return
}