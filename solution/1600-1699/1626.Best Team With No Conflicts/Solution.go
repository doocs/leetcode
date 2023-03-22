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
		this.c[x] = max(this.c[x], val)
		x += x & -x
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s = max(s, this.c[x])
		x -= x & -x
	}
	return s
}

func bestTeamScore(scores []int, ages []int) int {
	n := len(ages)
	arr := make([][2]int, n)
	m := 0
	for i, age := range ages {
		m = max(m, age)
		arr[i] = [2]int{scores[i], age}
	}
	sort.Slice(arr, func(i, j int) bool {
		a, b := arr[i], arr[j]
		return a[0] < b[0] || a[0] == b[0] && a[1] < b[1]
	})
	tree := newBinaryIndexedTree(m)
	for _, x := range arr {
		tree.update(x[1], x[0]+tree.query(x[1]))
	}
	return tree.query(m)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}