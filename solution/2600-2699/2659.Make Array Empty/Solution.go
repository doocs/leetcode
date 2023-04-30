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

func countOperationsToEmptyArray(nums []int) int64 {
	n := len(nums)
	pos := map[int]int{}
	for i, x := range nums {
		pos[x] = i
	}
	sort.Ints(nums)
	tree := newBinaryIndexedTree(n)
	ans := pos[nums[0]] + 1
	for k := 0; k < n-1; k++ {
		i, j := pos[nums[k]], pos[nums[k+1]]
		d := j - i - (tree.query(j+1) - tree.query(i+1))
		if i > j {
			d += n - k
		}
		ans += d
		tree.update(i+1, 1)
	}
	return int64(ans)
}