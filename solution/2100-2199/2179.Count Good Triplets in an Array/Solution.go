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

func goodTriplets(nums1 []int, nums2 []int) int64 {
	n := len(nums1)
	pos := make([]int, n)
	for i, v := range nums2 {
		pos[v] = i + 1
	}
	tree := newBinaryIndexedTree(n)
	var ans int64
	for _, num := range nums1 {
		p := pos[num]
		left := tree.query(p)
		right := n - p - (tree.query(n) - tree.query(p))
		ans += int64(left) * int64(right)
		tree.update(p, 1)
	}
	return ans
}