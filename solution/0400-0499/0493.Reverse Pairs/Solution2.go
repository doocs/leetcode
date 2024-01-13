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

func reversePairs(nums []int) int {
	s := make(map[int]bool)
	for _, num := range nums {
		s[num] = true
		s[num*2] = true
	}
	var alls []int
	for num := range s {
		alls = append(alls, num)
	}
	sort.Ints(alls)
	m := make(map[int]int)
	for i, num := range alls {
		m[num] = i + 1
	}
	tree := newBinaryIndexedTree(len(m))
	ans := 0
	for i := len(nums) - 1; i >= 0; i-- {
		ans += tree.query(m[nums[i]] - 1)
		tree.update(m[nums[i]*2], 1)
	}
	return ans
}