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
		if this.c[x] < val {
			this.c[x] = val
		}
		x += x & -x
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		if s < this.c[x] {
			s = this.c[x]
		}
		x -= x & -x
	}
	return s
}

func bestSeqAtIndex(height []int, weight []int) int {
	n := len(height)
	people := make([][2]int, n)
	s := map[int]bool{}
	for i := range people {
		people[i] = [2]int{height[i], weight[i]}
		s[weight[i]] = true
	}
	sort.Slice(people, func(i, j int) bool {
		a, b := people[i], people[j]
		return a[0] < b[0] || a[0] == b[0] && a[1] > b[1]
	})
	alls := make([]int, 0, len(s))
	for k := range s {
		alls = append(alls, k)
	}
	sort.Ints(alls)
	tree := newBinaryIndexedTree(len(alls))
	ans := 1
	for _, p := range people {
		x := sort.SearchInts(alls, p[1]) + 1
		t := tree.query(x-1) + 1
		ans = max(ans, t)
		tree.update(x, t)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}