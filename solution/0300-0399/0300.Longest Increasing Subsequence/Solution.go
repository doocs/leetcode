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

func (this *BinaryIndexedTree) update(x, val int) {
	for x <= this.n {
		if this.c[x] < val {
			this.c[x] = val
		}
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		if s < this.c[x] {
			s = this.c[x]
		}
		x -= this.lowbit(x)
	}
	return s
}

func lengthOfLIS(nums []int) int {
	s := make(map[int]bool)
	for _, v := range nums {
		s[v] = true
	}
	var t []int
	for v, _ := range s {
		t = append(t, v)
	}
	sort.Ints(t)
	m := make(map[int]int)
	for i, v := range t {
		m[v] = i + 1
	}
	ans := 1
	tree := newBinaryIndexedTree(len(m))
	for _, v := range nums {
		x := m[v]
		t := tree.query(x-1) + 1
		if ans < t {
			ans = t
		}
		tree.update(x, t)
	}
	return ans
}