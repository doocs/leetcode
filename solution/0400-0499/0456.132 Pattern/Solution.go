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

func find132pattern(nums []int) bool {
	s := make(map[int]bool)
	for _, v := range nums {
		s[v] = true
	}
	var alls []int
	for v := range s {
		alls = append(alls, v)
	}
	sort.Ints(alls)
	m := make(map[int]int)
	for i, v := range alls {
		m[v] = i + 1
	}
	tree := newBinaryIndexedTree(len(m))
	for _, v := range nums {
		tree.update(m[v], 1)
	}
	mi := nums[0]
	for _, v := range nums {
		tree.update(m[v], -1)
		if tree.query(m[v]-1)-tree.query(m[mi]) > 0 {
			return true
		}
		if v < mi {
			mi = v
		}
	}
	return false
}