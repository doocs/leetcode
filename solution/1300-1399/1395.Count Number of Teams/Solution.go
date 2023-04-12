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

func numTeams(rating []int) (ans int) {
	nums := make([]int, len(rating))
	copy(nums, rating)
	sort.Ints(nums)
	m := 0
	for i, x := range nums {
		if i == 0 || x != nums[i-1] {
			nums[m] = x
			m++
		}
	}
	nums = nums[:m]
	tree1 := newBinaryIndexedTree(m)
	tree2 := newBinaryIndexedTree(m)
	for _, x := range rating {
		tree2.update(sort.SearchInts(nums, x)+1, 1)
	}
	n := len(rating)
	for i, v := range rating {
		x := sort.SearchInts(nums, v) + 1
		tree1.update(x, 1)
		tree2.update(x, -1)
		l := tree1.query(x - 1)
		r := n - i - 1 - tree2.query(x)
		ans += l * r
		ans += (i - l) * (n - i - 1 - r)
	}
	return
}