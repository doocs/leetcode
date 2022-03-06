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

func countRangeSum(nums []int, lower int, upper int) int {
	n := len(nums)
	presum := make([]int, n+1)
	for i, v := range nums {
		presum[i+1] = presum[i] + v
	}
	alls := make(map[int]bool)
	for _, s := range presum {
		alls[s] = true
		alls[s-upper] = true
		alls[s-lower] = true
	}
	var t []int
	for s, _ := range alls {
		t = append(t, s)
	}
	sort.Ints(t)
	m := make(map[int]int)
	for i, v := range t {
		m[v] = i + 1
	}
	ans := 0
	tree := newBinaryIndexedTree(len(alls))
	for _, s := range presum {
		i, j := m[s-upper], m[s-lower]
		ans += tree.query(j) - tree.query(i-1)
		tree.update(m[s], 1)
	}
	return ans
}