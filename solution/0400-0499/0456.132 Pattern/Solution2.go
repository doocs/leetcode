type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) update(x, val int) {
	for x <= this.n {
		this.c[x] += val
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

func find132pattern(nums []int) bool {
	n := len(nums)
	s := make([]int, n)
	left := make([]int, n+1)
	left[0] = 1 << 30
	copy(s, nums)
	sort.Ints(s)
	m := 0
	for i := 0; i < n; i++ {
		left[i+1] = min(left[i], nums[i])
		if i == 0 || s[i] != s[i-1] {
			s[m] = s[i]
			m++
		}
	}
	s = s[:m]
	tree := newBinaryIndexedTree(m)
	for i := n - 1; i >= 0; i-- {
		x := sort.SearchInts(s, nums[i]) + 1
		y := sort.SearchInts(s, left[i]) + 1
		if x > y && tree.query(x-1) > tree.query(y) {
			return true
		}
		tree.update(x, 1)
	}
	return false
}