func reversePairs(nums []int) (ans int) {
	s := map[int]bool{}
	for _, v := range nums {
		s[v] = true
	}
	alls := []int{}
	for v := range s {
		alls = append(alls, v)
	}
	sort.Ints(alls)
	tree := newBinaryIndexedTree(len(alls))
	for i := len(nums) - 1; i >= 0; i-- {
		x := sort.SearchInts(alls, nums[i]) + 1
		ans += tree.query(x - 1)
		tree.update(x, 1)
	}
	return
}

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