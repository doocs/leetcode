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

func processQueries(queries []int, m int) []int {
	n := len(queries)
	pos := make([]int, m+1)
	tree := newBinaryIndexedTree(m + n)
	for i := 1; i <= m; i++ {
		pos[i] = n + i
		tree.update(n+i, 1)
	}
	ans := []int{}
	for i, v := range queries {
		j := pos[v]
		tree.update(j, -1)
		ans = append(ans, tree.query(j))
		pos[v] = n - i
		tree.update(n-i, 1)
	}
	return ans
}